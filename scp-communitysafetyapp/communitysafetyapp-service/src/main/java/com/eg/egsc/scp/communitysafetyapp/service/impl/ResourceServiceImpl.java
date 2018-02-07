/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.common.component.redis.RedisUtils;
import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceService;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant;
import com.eg.egsc.scp.communitysafetyapp.web.vo.EventTypeVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.SubSystemVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerTypeVo;
import com.eg.egsc.scp.eventcomponent.client.TriggerTypeClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.TRIGGER_TYPE_FAIL;

/**
 * Created by xinghai on 2017/12/26.
 */
@Service("resourceServiceImpl")
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private TriggerTypeClient triggerTypeClientImpl;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public List<SubSystemVo> getSubSystemList() {
        List<SubSystemVo> subSystemVoList = new ArrayList<>();
        subSystemVoList.add(new SubSystemVo("2001", "智能摄像机"));
        subSystemVoList.add(new SubSystemVo("2004", "停车场"));
        subSystemVoList.add(new SubSystemVo("2009", "门禁"));
        subSystemVoList.add(new SubSystemVo("2013", "对讲"));
        subSystemVoList.add(new SubSystemVo("2016", "梯控"));
        subSystemVoList.add(new SubSystemVo("2018", "广告屏"));
        subSystemVoList.add(new SubSystemVo("2017", "巡更单兵设备"));
        subSystemVoList.add(new SubSystemVo("2019", "广播"));

        return subSystemVoList;
    }

    /**
     * 获取联动事件类型
     */
    @Override
    public List<EventTypeVo> getTriggerEventTypeList(String subSystemCode) {
        List<EventTypeVo> eventTypeVoList = new ArrayList<>();
        if ("2001".equals(subSystemCode)) {
            eventTypeVoList.add(new EventTypeVo(20114, "人脸识别"));
        } else if ("2009".equals(subSystemCode)) {
            eventTypeVoList.add(new EventTypeVo(30000, "人脸开门上报"));
            eventTypeVoList.add(new EventTypeVo(30001, "远程开门上报"));
            eventTypeVoList.add(new EventTypeVo(30002, "CPU卡开门上报"));
            eventTypeVoList.add(new EventTypeVo(30003, "二维码开门上报"));
            eventTypeVoList.add(new EventTypeVo(30004, "指纹开门上报"));
            eventTypeVoList.add(new EventTypeVo(30005, "密码开门上报"));
        } else if ("2013".equals(subSystemCode)) {
            eventTypeVoList.add(new EventTypeVo(40006, "呼梯"));
        }

        return eventTypeVoList;
    }

    /**
     * 获取联动方式
     */
    @Override
    public List<TriggerTypeVo> getTriggerTypeVoList() {
        List<TriggerTypeVo> listTriggerTypeVos = new ArrayList<>();
        try {

            Object object = redisUtils.get("TRIGGER.METHOD");
            if (object != null) {
                listTriggerTypeVos = (List<TriggerTypeVo>) object;
                return listTriggerTypeVos;
            } else {
                BaseBusinessDto baseBusinessDto = new BaseBusinessDto();
                baseBusinessDto.setExtAttributes(new HashMap<>());
                ResponseDto triggerTypeList = triggerTypeClientImpl.getTriggerTypeList(baseBusinessDto);
                Object obj = triggerTypeList.getData();
                if (obj != null) {
                    List list = (ArrayList) obj;
                    for (int i = 0; i < list.size(); i++) {
                        LinkedHashMap linkedHashMap = (LinkedHashMap) list.get(i);
                        JSONObject jsonObject = new JSONObject(linkedHashMap);
                        String deviceTypeCode = (String) jsonObject.get("deviceTypeCode");
                        String to = (String) jsonObject.get("to");
                        String triggerName = (String) jsonObject.get("triggerName");
                        String triggerType = (String) jsonObject.get("triggerType");
                        TriggerTypeVo triggerTypeVo = new TriggerTypeVo(triggerName, triggerType, deviceTypeCode, to);
                        listTriggerTypeVos.add(triggerTypeVo);
                    }
                }
                redisUtils.set("TRIGGER.METHOD", listTriggerTypeVos, SafetyConstant.REDIS_EXPIRE_TIME);
            }
        } catch (Exception ex) {
            LoggerUtil.info(ex.getMessage());
            throw new SafetyException(TRIGGER_TYPE_FAIL);
        }
        return listTriggerTypeVos;
    }

    /**
     * 获取事件列表
     */
    @Override
    public List<EventTypeVo> getEventTypeList(String subSystemCode) {
        String typeName = "未知事件";
        if (null != globalMap.get(subSystemCode)) {
            typeName = globalMap.get(subSystemCode);
        }

        List<EventTypeVo> eventTypeVoList = new ArrayList<>();
        eventTypeVoList.add(new EventTypeVo(Integer.parseInt(subSystemCode), typeName));
        return eventTypeVoList;
    }

    /**
     * 获取事件日志事件类型
     *
     * @return map
     */
    @Override
    public Map getEventLogEventTypeList() throws SafetyException {
        List result = new ArrayList();
        for (Map.Entry<String, String> entry : globalMap.entrySet()) {
            result.add(new EventTypeVo(Integer.parseInt(entry.getKey()), entry.getValue()));
        }
        Map map = new HashMap();
        map.put("eventType", result);
        return map;
    }

    private static Map<String, String> globalMap = new HashMap();

    static {
        String[] eventTypeArr = {
                "刷卡开闸事件上报:10001",
                "车牌开闸事件上报:10002",
                "远程开闸事件上报:10003",
                "手动开闸事件上报:10004",
                "砸车报警:10005",
                "冲关报警:10006",
                "设备故障报警:10007",
                "视频丢失报警:20001",
                "设备防拆报警:20002",
                "存储设备磁盘满报警:20003",
                "设备高温报警:20004",
                "设备低温报警:20005",
                "存储设备磁盘故障报警:20006",
                "存储设备风扇故障报警:20007",
                "人工视频报警:20101",
                "运动目标检测报警:20102",
                "遗留物检测报警:20103",
                "物体移除检测报警:20104",
                "绊线检测报警:20105",
                "入侵检测报警:20106",
                "逆行检测报警:20107",
                "徘徊检测报警:20108",
                "流量统计报警:20109",
                "密度检测报警:20110",
                "视频异常检测报警:20111",
                "快速移动报警:20112",
                "人脸检测:20113",
                "人脸识别:20114",
                "车辆检测:20115",
                "车辆比对:20116",
                "车位有车:20117",
                "车位无车:20118",
                "违停:20119",
                "手动抓拍事件:20301",
                "人脸检测(分析API):25010",
                "周界事件:25019",
                "发现有区域入侵:25020",
                "发现有人员徘徊:25022",
                "人脸抓拍:25050",
                "脱岗:25311",
                "人脸开门上报:30000",
                "远程开门上报:30001",
                "CPU卡开门上报:30002",
                "二维码开门上报:30003",
                "指纹开门上报:30004",
                "密码开门上报:30005",
                "按钮开门上报:30006",
                "黑名单开门上报:30007",
                "人脸验证失败上报:30008",
                "远程验证失败上报:30009",
                "CPU卡验证失败上报:30010",
                "二维码验证失败上报:30011",
                "指纹验证失败上报:30012",
                "密码验证失败上报:30013",
                "道闸状态:30014",
                "设备参数上传:30015",
                "门开关状态监测:30016",
                "主机上电:30017",
                "主机刷卡:30018",
                "主机巡检:30019",
                "查询版本:30020",
                "人脸抓拍记录:30023",
                "卡未分配权限:30024",
                "卡号过期:30025",
                "无此卡号:30026",
                "合法卡认证通过:30027",
                "卡号过期:30028",
                "刷卡失败次数超出:30029",
                "人证比对通过:30030",
                "人证比对失败:30040",
                "人证比对失败次数超出:30050",
                "多重认证成功:30060",
                "多重人证失败:30070",
                "防拆报警:30300",
                "门开超时报警:30301",
                "异常开门报警:30302",
                "尾随报警:30303",
                "设备故障报警:30304",
                "对讲记录上报:40005",
                "呼梯:40006",
                "胁迫开门报警:40013",
                "开门超时报警:40014",
                "设备参数上传:50009",
                "状态上报:60000",
                "上报GPS信息:70000",
                "上报NFC刷卡数据:70001",
                "用户登录:70002",
                "用户登出:70003",
                "获取任务信息:70004",
                "上报应急事件:70005",
                "设备创建、删除更新通知:90000",
                "下发设备列表:90001",
                "下发设备参数获取请求:90002",
                "下发设备参数配置请求:90003",
                "下发设备固件升级请求:90004",
                "控制设备开关重启:90005",
                "设备注册信息上报:90006",
                "获取设备列表请求:90007",
                "上报设备参数获取响应:90008",
                "上报设备参数配置响应:90009",
                "上报设备离在线状态:90010",
                "固件升级结果上报:90011",
                "设备开关重启控制结果上报:90012"
        };
        for (String eventType : eventTypeArr) {
            String[] result = eventType.split(":");
            globalMap.put(result[1], result[0]);
        }
    }
}


