package com.six.jd.bean;

public class LoginMessageEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginMessageEvent(String msg) {

        this.msg = msg;
    }
}
