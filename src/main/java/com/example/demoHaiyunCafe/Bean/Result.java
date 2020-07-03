package com.example.demoHaiyunCafe.Bean;

public class Result<T> {
    private String msg;//返回消息
    private boolean success;//数据是否正常请求
    private T detail;//放回的数据

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getDetail() {
        return detail;
    }
}
