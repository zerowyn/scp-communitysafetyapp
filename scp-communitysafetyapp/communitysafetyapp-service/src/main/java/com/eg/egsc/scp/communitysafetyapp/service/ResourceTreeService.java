/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service;

import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TreeVo;
import com.eg.egsc.scp.devicemgmt.dto.DeviceDetailResponseDto;
import com.eg.egsc.scp.devicemgmt.dto.DeviceListResponseDto;

import java.util.List;

/**
 * 资源树
 *
 * @author zpc
 * @since 2017/12/29
 */
public interface ResourceTreeService {
    TreeVo getAllOrgs() throws SafetyException;

    DeviceListResponseDto getDevices(String uuId, List<String> deviceTypeCode) throws SafetyException;

    Object getDeviceTree(String uuid,List<String> deviceTypeCode) throws SafetyException;

    DeviceDetailResponseDto queryDeviceDetail(String deviceId) throws SafetyException;
}
