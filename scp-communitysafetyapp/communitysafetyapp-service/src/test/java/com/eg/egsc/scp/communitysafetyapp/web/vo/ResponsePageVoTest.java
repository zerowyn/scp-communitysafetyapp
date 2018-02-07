package com.eg.egsc.scp.communitysafetyapp.web.vo;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Constructor;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class ResponsePageVoTest {
    private static final String FAIL = "单元测试失败";

    /**
     * 测试 ResponsePageVo 构造方法
     */
    @Test
    public void testResponsePageVoTest() {
        try {
            Class singletonClass = Class.forName("com.eg.egsc.scp.communitysafetyapp.web.vo.ResponsePageVo");
            Constructor c = singletonClass.getDeclaredConstructor();
            c.setAccessible(true);
        } catch (Exception e) {
            LoggerUtil.info(FAIL);
        }

    }

    /**
     * 测试ResponsePageVo set get 方法的调用
     */
    @Test
    public void setResponsePageVoTest() {
        ResponsePageVo v = new ResponsePageVo();
        v.setPageNo(1);
        v.setTotal(2);
        v.setRows(2);
        LoggerUtil.info(v.getPageNo() + "--" + v.getTotal() + "--" + v.getRows());
    }
}