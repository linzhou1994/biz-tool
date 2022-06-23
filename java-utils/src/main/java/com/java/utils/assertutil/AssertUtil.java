package com.java.utils.assertutil;

import com.java.utils.exception.BizException;
import com.java.utils.exception.SysErrorCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author linzhou
 * @version 1.0.0
 * @ClassName Assert.java
 * @Description 断言类
 * @createTime 2021年07月14日 14:57:00
 */
public class AssertUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(AssertUtil.class);


    public static void isBlank(String str, SysErrorCode code, Object... org) throws BizException {
        isTrue(StringUtils.isBlank(str), code, org);
    }

    public static void isNotBlank(String str, SysErrorCode code, Object... org) throws BizException {
        isTrue(StringUtils.isNotBlank(str), code, org);
    }

    public static void isEmpty(String str, SysErrorCode code, Object... org) throws BizException {
        isTrue(StringUtils.isEmpty(str), code, org);
    }

    public static void isNotEmpty(String str, SysErrorCode code, Object... org) throws BizException {
        isTrue(StringUtils.isNotEmpty(str), code, org);
    }

    public static void isNotNull(Object o, SysErrorCode code, Object... org) throws BizException {
        isTrue(o != null, code, org);
    }

    public static void isNull(Object o, SysErrorCode code, Object... org) throws BizException {
        isTrue(o == null, code, org);
    }

    public static void isNotEmpty(Collection c, SysErrorCode code, Object... org) throws BizException {
        isTrue(CollectionUtils.isNotEmpty(c), code, org);
    }

    public static void isEmpty(Collection c, SysErrorCode code, Object... org) throws BizException {
        isTrue(CollectionUtils.isEmpty(c), code, org);
    }

    public static void isMapNotEmpty(Map m, SysErrorCode code, Object... org) throws BizException {
        isTrue(m != null && !m.isEmpty(), code, org);
    }

    public static void isMapEmpty(Map m, SysErrorCode code, Object... org) throws BizException {
        isTrue(m == null || m.isEmpty(), code, org);
    }

    public static void isEquals(Object o1, Object o2, SysErrorCode code, Object... org) throws BizException {
        isTrue(Objects.equals(o1,o2), code, org);
    }
    public static void isNotEquals(Object o1, Object o2, SysErrorCode code, Object... org) throws BizException {
        isTrue(!Objects.equals(o1,o2), code, org);
    }


    public static void isTrue(boolean as, SysErrorCode code, Object... org) throws BizException {
        if (code == null) {
            code = SysErrorCode.DEFAULT_ERROR;
        }
        if (!as) {
            String errorMsg = getErrorMsg(code.getMsg(), org);
            BizException bizException = new BizException(code,org);
            LOGGER.warn("", bizException);
            throw bizException;
        }
    }

    public static String getErrorMsg(String str, Object... objects) {
        String rlt = str;
        if (objects != null) {
            for (Object object : objects) {
                rlt = rlt.replaceFirst("\\[.*?]", object == null ? "null" : object.toString());
            }
        }
        return rlt;
    }

    public static void main(String[] args) {

        System.out.println(getErrorMsg("你好[name1]!,我是[name2]....", "lz", 3));

    }

}