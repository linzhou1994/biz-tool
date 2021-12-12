package com.biz.tool.math;

import java.util.Objects;

/**
 * @author linzhou
 * @ClassName MathUtil.java
 * @createTime 2021年11月18日 13:55:00
 * @Description
 */
public class MathUtil {
    public static int add(Integer a, Integer b) {
        if (Objects.isNull(a)) {
            a = 0;
        }
        if (Objects.isNull(b)) {
            b = 0;
        }
        return a + b;
    }
}
