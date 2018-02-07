/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.eg.egsc.common.component.redis.RedisUtils;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceTreeService;
import com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.util.SafetyConstant;
import com.eg.egsc.scp.communitysafetyapp.util.SafetyUtils;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TreeVo;
import com.eg.egsc.scp.devicemgmt.client.IDeviceClient;
import com.eg.egsc.scp.devicemgmt.dto.DeviceDetailRequestDto;
import com.eg.egsc.scp.devicemgmt.dto.DeviceDetailResponseDto;
import com.eg.egsc.scp.devicemgmt.dto.DeviceDto;
import com.eg.egsc.scp.devicemgmt.dto.DeviceListRequestDto;
import com.eg.egsc.scp.devicemgmt.dto.DeviceListResponseDto;
import com.eg.egsc.scp.devicemgmt.dto.SubDeviceDto;
import com.eg.egsc.scp.mdm.component.client.OrgClient;
import com.eg.egsc.scp.mdm.component.dto.OrgDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.DEVICE_GET_FAIL;
import static com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant.REQUEST_DEVICE_TIMEOUT;

/**
 * 资源树接口代码
 *
 * @author zpc
 * @since 2017/12/29
 */
@Service("resourceTreeServiceImpl")
public class ResourceTreeServiceImpl implements ResourceTreeService {
    @Resource
    IDeviceClient deviceClient;

    @Resource
    OrgClient orgClientImpl;

    @Autowired
    RedisUtils redisUtils;

    /**
     * 查询资源树所有结构
     *
     * @return OrgDto
     */
    @Override
    public TreeVo getAllOrgs() {
        try {
            OrgDto root = orgClientImpl.getRootBaseOrg(new OrgDto());
            TreeVo treeVo = new TreeVo();
            treeVo.setUuid(root.getUuid());
            treeVo.setLabel(root.getName());
            treeVo.setIsParent(root.getIsParent());
            return treeVo;
        } catch (Exception ex) {
            LoggerUtil.info(ex.getMessage());
            throw new SafetyException(ErrorCodeConstant.REQUEST_MDM_TIMEOUT);
        }
    }


    /**
     * 通过设备类型编码查询设备
     *
     * @return DeviceListResponseDto
     */
    @Override
    public DeviceListResponseDto getDevices(String uuid, List<String> deviceTypeCode) {
        List<String> uuidList = new ArrayList<>();
        uuidList.add(uuid);
        DeviceListRequestDto deviceListRequestDto = new DeviceListRequestDto();
        deviceListRequestDto.setListOrgID(uuidList);
        deviceListRequestDto.setListDeviceTypeCode(deviceTypeCode);
        try {
            StringBuilder sbf = new StringBuilder();
            for (String str : deviceTypeCode) {
                sbf.append(str);
            }
            Object obj = redisUtils.get("DEVICE.CODE." + sbf + uuid);
            if (obj != null) {
                return (DeviceListResponseDto) obj;
            }

            DeviceListResponseDto dto = deviceClient.listDevices(deviceListRequestDto);
            redisUtils.set("DEVICE.CODE." + sbf + uuid, dto, SafetyConstant.REDIS_EXPIRE_TIME);
            return dto;
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
            throw new SafetyException(REQUEST_DEVICE_TIMEOUT);
        }
    }

    /**
     * 获取设备详情
     *
     * @return DeviceDetailResponseDto
     */
    @Override
    public DeviceDetailResponseDto queryDeviceDetail(String deviceId) {
        try {
            DeviceDetailRequestDto deviceDetailRequestDto = new DeviceDetailRequestDto();
            deviceDetailRequestDto.setDeviceCode(deviceId);
            return deviceClient.getDevice(deviceDetailRequestDto);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
            throw new SafetyException(REQUEST_DEVICE_TIMEOUT);
        }
    }

    /**
     * 获取设备树
     *
     * @return TreeVo
     */
    public Object getDeviceTree(String uuid, List<String> deviceTypeCode) {
        try {
            OrgDto orgDto = new OrgDto();
            orgDto.setIsParent(true);
            orgDto.setType("org");
            orgDto.setUuid(uuid);
            List<OrgDto> children = orgClientImpl.getOrgNextLevel(orgDto);
             DeviceListResponseDto responseDto = getDevices(uuid, deviceTypeCode);
            List<DeviceDto> deviceDtoList;
            if (responseDto == null || SafetyUtils.isEmpty(responseDto.getDeviceList())) {
                deviceDtoList = new ArrayList();
            } else {
                deviceDtoList = responseDto.getDeviceList();
            }
            return assemble(orgDto, children, deviceDtoList);
        } catch (SafetyException se) {
            LoggerUtil.info(se.getMessage());
            throw new SafetyException(DEVICE_GET_FAIL);
        }
    }


    /**
     *
     * 封装返回的设备字段
     * @return TreeVo
     */
    public List<TreeVo> assemble(OrgDto parent, List<OrgDto> list, List<DeviceDto> deviceDto) {
        List<TreeVo> dList = setDeviceNode(parent, deviceDto);
        List<TreeVo> treeVos = new ArrayList<>();
        treeVos.addAll(dList);
        for (OrgDto orgDto : list) {
            TreeVo treeVo = new TreeVo();
            treeVo.setLabel(orgDto.getName());
            treeVo.setUuid(orgDto.getUuid());
            treeVo.setIsParent(orgDto.getIsParent());
            treeVos.add(treeVo);
        }

        return treeVos;
    }


    /**
     * 设置设备节点
     *
     * @return List
     */
    private List<TreeVo> setDeviceNode(OrgDto orgDto, List<DeviceDto> list) {
        List<TreeVo> result = new ArrayList();
        for (DeviceDto deviceDto : list) {
            if (!orgDto.getUuid().equals(deviceDto.getOrgID())) continue;
            TreeVo treeVo = new TreeVo();
            treeVo.setLabel(deviceDto.getDeviceName());
            treeVo.setUuid(deviceDto.getDeviceID());
            treeVo.setDeviceId(deviceDto.getDeviceID());
            treeVo.setInstallAddress(deviceDto.getInstallAddress());
            treeVo.setIsDevice(true);
            result.add(treeVo);
            if (!SafetyUtils.isEmpty(deviceDto.getSubDeviceList())) {
                result.addAll(setSubDeviceNode(deviceDto.getSubDeviceList()));
            }
        }
        return result;
    }


    /**
     * 设置设备子节点
     *
     * @return List
     */
    private List setSubDeviceNode(List<SubDeviceDto> list) {
        List result = new ArrayList();
        for (SubDeviceDto subDeviceDto : list) {
            TreeVo treeVo = new TreeVo();
            treeVo.setUuid(subDeviceDto.getSubDeviceID());
            treeVo.setLabel(subDeviceDto.getSubDeviceName());
            treeVo.setIsDevice(true);
            treeVo.setInstallAddress(subDeviceDto.getSubDeviceInstallAddress());
            treeVo.setDeviceId(subDeviceDto.getSubDeviceID());
            result.add(treeVo);
        }
        return result;
    }
}
