/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

import com.eg.egsc.scp.eventcomponent.dto.FilterDto;

/**
 * @author xinghai
 * @since 2017/12/26
 */
public class FilterUtils {
    private FilterUtils() {
    }

    /**
     * 组合查询条件
     */
    public static FilterDto getFilterDto(String item, String separator,
                                         String val, String operator) {
        FilterDto f = new FilterDto();
        f.setItem(item);
        f.setVal(val);
        f.setSeparator(separator);
        f.setOperator(operator);
        return f;
    }
}
