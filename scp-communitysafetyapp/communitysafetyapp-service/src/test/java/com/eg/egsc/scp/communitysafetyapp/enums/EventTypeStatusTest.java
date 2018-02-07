/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.enums;

import com.eg.egsc.scp.communitysafetyapp.util.LoggerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author gucunyang
 * @since 2018-01-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EventTypeStatusTest {

    /**
     * EventTypeEnumTest
     */
    @Test
    public void eventTypeEnumTest() {

        String eventTypeName = EventStatusEnum.getName(1);
        String eventTypeNameNull = EventStatusEnum.getName(4);
        LoggerUtil.info(eventTypeName+eventTypeNameNull);
    }

}