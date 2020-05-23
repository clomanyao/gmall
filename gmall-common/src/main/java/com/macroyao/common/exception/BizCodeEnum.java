package com.macroyao.common.exception;

public enum BizCodeEnum {

    UNKNOWN_EXCEPTION(10000,"未知错误!"),
    VALID_EXCEPTION(10001,"参数校检错误!");
    private int code;
    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
