/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author gucunyang
 * @since 2018-01-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ExtendVoTest {
    /**
     * ExtendVoTest
     */
    @Test
    public void extendVoTest() {
        ExtendVo extendVo = new ExtendVo();
        extendVo.setImgUrl("/img/01.jpg");
        String imgUrl = extendVo.getImgUrl();
        LoggerUtil.info(imgUrl);
        LoggerUtil.info("单元测试成功");
    }

}