/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghai
 * @since 2017/12/20
 */
public class ParamUtils {
    private ParamUtils() {
    }

    /**
     * string 转换成String 集合
     *
     * @author xinghai
     * @date 2017/12/20 9:31
     * @Param
     */
    public static final List<String> toStringList(String str, String splitStr) {
        ArrayList<String> longList = new ArrayList<>();
        if (str != null) {
            String[] strList = str.split(splitStr);
            for (String string : strList) {
                longList.add(string);
            }
        }
        return longList;
    }
}
