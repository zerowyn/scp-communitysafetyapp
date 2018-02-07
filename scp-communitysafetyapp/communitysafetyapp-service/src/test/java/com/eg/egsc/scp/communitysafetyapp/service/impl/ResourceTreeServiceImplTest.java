/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.service.impl;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceTreeService;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TreeVo;
import com.eg.egsc.scp.devicemgmt.dto.DeviceDetailResponseDto;
import com.eg.egsc.scp.devicemgmt.dto.DeviceListResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ResourceTreeServiceImplTest {

    private static final String START = "单元测试开始";
    private static final String END = "单元测试结束";
    @Autowired
    private ResourceTreeService resourceTreeServiceImpl;

    /**
     * 获取设备树
     */
    @Test
    public void getAllOrgs(){
        LoggerUtil.info(START);
        try {
            TreeVo resultOrg = resourceTreeServiceImpl.getAllOrgs();
            ObjectMapper objectMapper = new ObjectMapper();
            String ro = objectMapper.writeValueAsString(resultOrg);
            LoggerUtil.info(ro);
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
    }

    /**
     *获得设备
     */
    @Test
    public void getDevices() {
        LoggerUtil.info(START);
        try{
            List<String> deviceTypeCode = new ArrayList<>();
            deviceTypeCode.add("2009");
            DeviceListResponseDto dto = resourceTreeServiceImpl.getDevices("c69aeede4f6341929721e2892beec3cb", deviceTypeCode);
            ObjectMapper objectMapper = new ObjectMapper();
            String str = objectMapper.writeValueAsString(dto);
            LoggerUtil.info(str);
            LoggerUtil.info(END);
        }catch (Exception ex){
            LoggerUtil.info(ex.getMessage());
        }

    }

    /**
     * 获得设备详情
     */
    @Test
    public void queryDeviceDetail(){
        LoggerUtil.info(START);
        try {
            DeviceDetailResponseDto deviceDetailResponseDto
                    = resourceTreeServiceImpl.queryDeviceDetail("10022011123344532b33");
            ObjectMapper objectMapper = new ObjectMapper();
            String dto = objectMapper.writeValueAsString(deviceDetailResponseDto);
            LoggerUtil.info(dto);
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }

    }

    /**
     * 获得设备
     */
    @Test
    public void getDeviceTree() {
        LoggerUtil.info(START);
        List<String> deviceTypeCode = new ArrayList<>();
        try {
            deviceTypeCode.add("2009");
            deviceTypeCode.add("2011");
            deviceTypeCode.add("2012");
            String uuid = "c69aeede4f6341929721e2892beec3cb";
            Object treeVo = resourceTreeServiceImpl.getDeviceTree(uuid, deviceTypeCode);
            ObjectMapper objectMapper = new ObjectMapper();
            String devTree = objectMapper.writeValueAsString(treeVo);
            LoggerUtil.info(devTree);
            LoggerUtil.info(END);
        } catch (Exception e) {
            LoggerUtil.info(e.getMessage());
        }
    }




}
