/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.alibaba.fastjson.JSON;
import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.service.TriggerRuleService;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleCondition;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggerRuleDto;
import com.eg.egsc.scp.communitysafetyapp.web.dto.TriggersDto;
import com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TriggerRuleVo;
import com.eg.egsc.scp.eventcomponent.dto.TriggerDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class TriggerRuleServiceImplTest {
    @Autowired
    private TriggerRuleService triggerRuleServiceImpl;
    private static final String SUCCESS = "单元测试成功";
    private static final String FAIL = "单元测试失败";

    private TriggerRuleDto mockTriggerRuleUpdateDto() {
        //联动参数设置
        Map<String, String> map = new HashMap<>();
        TriggerRuleDto triggerRuleDto = new TriggerRuleDto();
        List<TriggersDto> triggerDtoList = new ArrayList<>();
        TriggersDto triggersDto = new TriggersDto();
        triggersDto.setOrder(1);
        triggersDto.setMethod("FAC_CALLING");
        triggersDto.setTo("MSG/EVENT/LIFTCONTROL");
        map.put("deviceId:", "1004201658FCDBD8341E");
        map.put("sourceLists", "uuId:50ee7595f5994a3e9807f2a00889fc56");
        triggersDto.setParams(map);
        triggerRuleDto.setEventSourceCode("11111111586858ABCDEE");
        triggerRuleDto.setTriggerRuleId("1");
        triggerRuleDto.setCreator("事件应用单元测试数据");
        triggerRuleDto.setEventType(30000);
        triggerDtoList.add(triggersDto);
        triggerRuleDto.setIsSequential(true);
        triggerRuleDto.setTriggers(triggerDtoList);
        return triggerRuleDto;
    }

    private TriggerRuleCondition mockTriggerRuleCondition() {
        TriggerRuleCondition condition = new TriggerRuleCondition();
        condition.setEventSourceCode("");
        condition.setEventType(30000);
        condition.setPageNo(1);
        condition.setPageSize(5);
        return condition;
    }

    private TriggerRuleCondition mockTriggerRuleBranchCondition() {
        TriggerRuleCondition condition = new TriggerRuleCondition();
        condition.setPageNo(2);
        condition.setPageSize(10);
        return condition;
    }

    //新增联动规则测试
    @Test
    @Transactional
    @Rollback
    public void addTriggerRuleTest() {
        try {
            TriggerRuleDto triggerRuleDto = this.mockTriggerRuleUpdateDto();
            triggerRuleServiceImpl.addTriggerRule(triggerRuleDto);
            LoggerUtil.info(SUCCESS);
        } catch (SafetyException ex) {
            LoggerUtil.info(ex.getMessage());
        }
    }

    @Test
    @Transactional
    @Rollback
    public void updateTriggerRuleTest() {
        try {
            TriggerRuleDto triggerRuleDto = this.mockTriggerRuleUpdateDto();
            triggerRuleServiceImpl.updateTriggerRule(triggerRuleDto);
            LoggerUtil.info(SUCCESS);
        } catch (SafetyException ex) {
            LoggerUtil.info(ex.getMessage());
        }
    }

    /**
     * 删除联动规则测试
     */
    @Test
    @Transactional
    @Rollback
    public void delTriggerRuleTest() {
        try {
            List<String> triggerRuleIds = new ArrayList<>();
            triggerRuleIds.add("1238");
            triggerRuleIds.add("2222");

            triggerRuleServiceImpl.delTriggerRule(triggerRuleIds);
            LoggerUtil.info(SUCCESS);
        } catch (SafetyException ex) {
            LoggerUtil.info(ex.getMessage());
        }
    }

    /**
     * 根据id获取联动规则
     */
    @Test
    @Transactional
    @Rollback
    public void getTriggerRuleTest() {
        try {
            TriggerRuleVo triggerRuleVo = triggerRuleServiceImpl.getTriggerRuleById("108");
            ObjectMapper objectMapper = new ObjectMapper();
            String tro = objectMapper.writeValueAsString(triggerRuleVo);
            LoggerUtil.info(tro);
            LoggerUtil.info(SUCCESS);
        } catch (Exception e) {
            LoggerUtil.info(FAIL);
        }
    }


    //分页查测试
    @Test
    @Transactional
    @Rollback
    public void getTriggerRulePageTest() {
        TriggerRuleCondition condition = this.mockTriggerRuleCondition();
        try {
            ResponsePageVo responsePageVo
                    = triggerRuleServiceImpl.getTriggerRulePage(condition);
            condition.setEventType(null);
            condition.setDeviceType("2004");
            ResponsePageVo responsePageVoOne=triggerRuleServiceImpl.getTriggerRulePage(condition);
            condition.setDeviceType("2001");
            ResponsePageVo responsePageVoTwo= triggerRuleServiceImpl.getTriggerRulePage(condition);
            condition.setDeviceType("2009");
            ResponsePageVo responsePageVoThree= triggerRuleServiceImpl.getTriggerRulePage(condition);
            condition.setDeviceType("2016");
            ResponsePageVo responsePageVoFour= triggerRuleServiceImpl.getTriggerRulePage(condition);
            condition.setDeviceType("2018");
            ResponsePageVo responsePageVoFive= triggerRuleServiceImpl.getTriggerRulePage(condition);
            condition.setDeviceType("2019");
            ResponsePageVo responsePageVoSix= triggerRuleServiceImpl.getTriggerRulePage(condition);
            condition.setDeviceType("2017");
            ResponsePageVo responsePageVoSeven= triggerRuleServiceImpl.getTriggerRulePage(condition);
            List<ResponsePageVo> responsePageVoList= new ArrayList<>();
            responsePageVoList.add(responsePageVo);
            responsePageVoList.add(responsePageVoOne);
            responsePageVoList.add(responsePageVoTwo);
            responsePageVoList.add(responsePageVoThree);
            responsePageVoList.add(responsePageVoFour);
            responsePageVoList.add(responsePageVoSix);
            responsePageVoList.add(responsePageVoFive);
            responsePageVoList.add(responsePageVoSeven);
            ObjectMapper objectMapper = new ObjectMapper();
            String rpo = objectMapper.writeValueAsString(responsePageVoList);
            LoggerUtil.info(rpo);
            LoggerUtil.info(SUCCESS);
        }catch (Exception e) {
            LoggerUtil.info(FAIL);
        }
    }


    //测试联动规则分页查询分支
    @Test
    @Transactional
    @Rollback
    public void getTriggerRulePageBranchTest() {
        TriggerRuleCondition branchCondition = this.mockTriggerRuleBranchCondition();
        try {
            List<String> list = new ArrayList<>();
            list.add("2004");
            list.add("2001");
            list.add("2009");
            list.add("2016");
            list.add("2011");
            list.add("2018");
            list.add("2019");
            list.add("2017");
            for (String deviceType : list) {
                branchCondition.setDeviceType(deviceType);
                ObjectMapper objectMapper = new ObjectMapper();
                ResponsePageVo responsePageVo
                        = triggerRuleServiceImpl.getTriggerRulePage(branchCondition);
                String rpo = objectMapper.writeValueAsString(responsePageVo);
                LoggerUtil.info(rpo);
            }
            LoggerUtil.info(SUCCESS);
        } catch (Exception e) {
            LoggerUtil.info(FAIL);
        }
    }

    //获取联动规则测试
    @Test
    public void getTriggerRuleVoTest() {
        Map<String, String> tRo = new HashMap<>();
        tRo.put("EventSourceCode", "301058FCDB0000EB0001");
        //得到方法
        try {
            Class clz = Class.forName("com.eg.egsc.scp.communitysafetyapp.service.impl.TriggerRuleServiceImpl");
            Object o = clz.newInstance();
            TriggerRuleServiceImpl user = (TriggerRuleServiceImpl) o;
            Method getTriggerRuleVo = clz.getDeclaredMethod("getTriggerRuleVo", Map.class);
            getTriggerRuleVo.setAccessible(true);
            Object result = getTriggerRuleVo.invoke(user, tRo);
            String str = JSON.toJSONString(result);
            LoggerUtil.info(str);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

    }

    @Test
    public void getPrivateMethod() {
        try {
            List list = new ArrayList();
            Map<String, Object> map = new LinkedHashMap<>();
            TriggerDto triggerDto = new TriggerDto();
            triggerDto.setCreator("");
            triggerDto.setEventSourceCode("");
            triggerDto.setEventType(1);
            triggerDto.setIsSequential(false);
            triggerDto.setTriggers(null);
            map.put("triggerDto", triggerDto);
            list.add(map);
            Class clz = Class.forName("com.eg.egsc.scp.communitysafetyapp.service.impl.TriggerRuleServiceImpl");
            Object o = clz.newInstance();
            TriggerRuleServiceImpl triggerRuleService = (TriggerRuleServiceImpl) o;
            Method getTriggerRuleVoList = clz.getDeclaredMethod("getTriggerRuleVoList", List.class);
            getTriggerRuleVoList.setAccessible(true);
            Object invoke = getTriggerRuleVoList.invoke(triggerRuleService, list);
            String str = JSON.toJSONString(invoke);
            LoggerUtil.info(str);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
    }

}
