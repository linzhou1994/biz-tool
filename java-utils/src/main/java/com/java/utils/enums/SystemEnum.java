package com.java.utils.enums;

/**
 *
 * 系统类
 * @author linzhou
 */

public enum SystemEnum {

    SSO_SYSTEM("001","单点登录系统"),
    WMS_BASE_SYSTEM("002","wms基础服务系统"),
    WMS_GOODS_SYSTEM("003","wms商品服务系统"),
    WMS_STOCK_SYSTEM("004","wms库存服务系统"),
    WMS_ORDER_IN_SYSTEM("005","wms入库服务系统"),
    WMS_ORDER_OUT_SYSTEM("006","wms出库服务系统"),
    OMS_ORDER_SYSTEM("007","OMS订单服务系统"),
    OMS_ORDER_ESB_SYSTEM("008","OMS订单外部订阅服务系统"),
    ;

    private String code;
    private String msg;

    SystemEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
