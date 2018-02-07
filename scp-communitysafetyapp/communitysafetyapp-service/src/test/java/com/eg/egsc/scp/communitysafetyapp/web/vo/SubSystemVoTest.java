package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class SubSystemVoTest {


    /**
     * 测试 SubSystemVo set get 方法的调用
     */
    @Test
    public void setSubSystemVoTest() {
        SubSystemVo v1 = new SubSystemVo();
        SubSystemVo v2 = new SubSystemVo("1", "2");
        v1.setCode("1");
        v2.setName("2");
        LoggerUtil.info(v1.getCode() + "--" + v2.getName());
    }
}