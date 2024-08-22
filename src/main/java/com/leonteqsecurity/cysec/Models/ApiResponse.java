package com.leonteqsecurity.cysec.Models;

public class ApiResponse {
    private String message;
    private int statuscode;
    private Object responsedata;
    public ApiResponse(String message, int statuscode, Object responsedata) {
        this.message = message;
        this.statuscode = statuscode;
        this.responsedata = responsedata;
    }
    public ApiResponse(String message, int statuscode)
    {
        this.message = message;
        this.statuscode = statuscode;
    }

    public ApiResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public Object getResponsedata() {
        return responsedata;
    }

    public void setResponsedata(Object responsedata) {
        this.responsedata = responsedata;
    }
}
