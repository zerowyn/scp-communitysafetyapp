package com.eg.egsc.scp.communitysafetyapp.vo;


import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import com.eg.egsc.scp.communitysafetyapp.web.vo.TreeVo;
import org.junit.Test;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author gongxin
 * @since 2018-01-16
 */
public class TreeVoTest {


    @Test
    @Transactional
    @Rollback
    public void getTreeVoTest(){
        TreeVo treeVo =new TreeVo();
        String uuid= UUID.randomUUID().toString();
        uuid=uuid.replace("-", "");
        List  list = new ArrayList();
        list.add("A");
        treeVo.setUuid(uuid);
        treeVo.setDeviceId("1");
        treeVo.setLabel("描述");
        treeVo.setIsChecked(false);
        treeVo.setIsParent(true);
        treeVo.setChildren(list);
        treeVo.setIsDevice(true);

        treeVo.isIsChecked();
        treeVo.isIsParent();
        treeVo.isIsDevice();

        LoggerUtil.info(treeVo.getUuid()+"--"+treeVo.getLabel()+"--"+treeVo.getDeviceId()
                +"--"+treeVo.getChildren());
    }

}
