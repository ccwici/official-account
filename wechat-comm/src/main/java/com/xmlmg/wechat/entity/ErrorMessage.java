package com.xmlmg.wechat.entity;

/**
 * 所有的微信接口，出错都会返回errcode和errmsg
 */
public class ErrorMessage {
    private String errcode;
    private String errmsg;

    public ErrorMessage() {
    }

    public ErrorMessage( String errcode, String errmsg){
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
