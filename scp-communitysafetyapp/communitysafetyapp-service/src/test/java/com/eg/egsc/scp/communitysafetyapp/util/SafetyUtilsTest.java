package com.eg.egsc.scp.communitysafetyapp.util;

import com.eg.egsc.scp.communitysafetyapp.MainServiceApplication;
import com.eg.egsc.scp.communitysafetyapp.web.vo.EventTypeVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gongxin
 * @since 2018-01-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainServiceApplication.class})
public class SafetyUtilsTest {
    /**
     * 测试事件类型类
     */
    @Test
    @Transactional
    @Rollback
    public void getSafetyUtils() {

        try {
            Class<?> classType = SafetyUtils.class;
            Constructor<?> safetyUtilsConstructor = classType.getDeclaredConstructor();
            safetyUtilsConstructor.setAccessible(true);
            SafetyUtils errorCodeConstant = (SafetyUtils) safetyUtilsConstructor.newInstance();
            LoggerUtil.info(errorCodeConstant.toString());

            LoggerUtil.info("单元测试成功");
        } catch (Exception e) {
            LoggerUtil.info("单元测试失败");
        }

        EventTypeVo event = new EventTypeVo(1, "zhangsan");
        event.setCode(1);
        event.setName("zhangsan");
        List<EventTypeVo> eventTypeVoList = new ArrayList<>();
        eventTypeVoList.add(event);
        Map<Integer, String> eventTypeMap = SafetyUtils.getEventTypeMap(eventTypeVoList);
        LoggerUtil.info(eventTypeMap.get(1));
    }

}
