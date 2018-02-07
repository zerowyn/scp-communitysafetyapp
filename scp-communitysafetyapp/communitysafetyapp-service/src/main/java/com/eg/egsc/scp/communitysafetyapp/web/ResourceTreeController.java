package com.eg.egsc.scp.communitysafetyapp.web;

import com.eg.egsc.framework.service.base.web.BaseWebController;
import com.eg.egsc.scp.communitysafetyapp.exception.SafetyException;
import com.eg.egsc.scp.communitysafetyapp.service.ResourceTreeService;
import com.eg.egsc.scp.communitysafetyapp.util.ErrorCodeConstant;
import com.eg.egsc.scp.communitysafetyapp.util.ValidationUtils;
import com.eg.egsc.scp.communitysafetyapp.web.result.ApiResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;

/**
 * 组数据获取
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceTreeController  extends BaseWebController{
    @Autowired
    private ResourceTreeService resourceTreeServiceImpl;

    /**
     * 获取主数据根节点
     */
    @ApiOperation("获取主数据根节点")
    @RequestMapping(value = "/root/org", method = RequestMethod.GET)
    public ApiResult getRootBaseOrg(){
        return ApiResult.successFul(resourceTreeServiceImpl.getAllOrgs());
    }

    /**
     * 设备树
     */
    @ApiOperation("设备树")
    @RequestMapping(value = "/device/tree", method = RequestMethod.GET)
    public ApiResult getDeviceList(@RequestParam(name = "code") String code,
                                   @RequestParam(name = "uuid") String uuid) {
        if (!ValidationUtils.isDeviceCodeValid(code)) {
            throw new SafetyException(ErrorCodeConstant.TRIGGER_DEVICECODE_ILLEGAL);
        }
        List codeList = new ArrayList();
        codeList.add(code);
        if ("2009".equals(code)) {
            //增加门禁对讲
            codeList.add("2011");
            codeList.add("2012");
        }

        if ("2013".equals(code)) {
            //增加门禁对讲
            codeList.add("2011");
            codeList.add("2012");
            codeList.add("2014");
        }

        return ApiResult.successFul(resourceTreeServiceImpl.getDeviceTree(uuid, codeList));
    }
}
