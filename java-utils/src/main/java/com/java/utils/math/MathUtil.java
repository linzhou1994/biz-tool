package com.java.utils.math;

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

    public static double add(Double a, Double b) {
        if (Objects.isNull(a)) {
            a = 0D;
        }
        if (Objects.isNull(b)) {
            b = 0D;
        }
        return a + b;
    }

    public static float add(Float a, Float b) {
        if (Objects.isNull(a)) {
            a = 0F;
        }
        if (Objects.isNull(b)) {
            b = 0F;
        }
        return a + b;
    }

    public static long add(Long a, Long b) {
        if (Objects.isNull(a)) {
            a = 0L;
        }
        if (Objects.isNull(b)) {
            b = 0L;
        }
        return a + b;
    }

    public static Integer sub(Integer a, Integer b) {
        if (Objects.isNull(a)) {
            a = 0;
        }
        if (Objects.isNull(b)) {
            b = 0;
        }
        return a - b;
    }

    public static Double sub(Double a, Double b) {
        if (Objects.isNull(a)) {
            a = 0D;
        }
        if (Objects.isNull(b)) {
            b = 0D;
        }
        return a - b;
    }

    public static Float sub(Float a, Float b) {
        if (Objects.isNull(a)) {
            a = 0F;
        }
        if (Objects.isNull(b)) {
            b = 0F;
        }
        return a - b;
    }

    public static Long sub(Long a, Long b) {
        if (Objects.isNull(a)) {
            a = 0L;
        }
        if (Objects.isNull(b)) {
            b = 0L;
        }
        return a - b;
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }


    public static long min(long a, long b) {
        return Math.min(a, b);
    }

    public static float min(float a, float b) {
        return Math.min(a, b);
    }

    public static double min(double a, double b) {
        return Math.min(a, b);
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static long max(long a, long b) {
        return Math.max(a, b);
    }

    public static float max(float a, float b) {
        return Math.max(a, b);
    }


    public static double max(double a, double b) {
        return Math.max(a, b);
    }

}
