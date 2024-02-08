package com.unnamed.cruddemoREST.Models;
public class CustomResponseModel {
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResponse() {
        return Response;
    }

    public void setResponse(Object response) {
        this.Response = response;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public CustomResponseModel() {
    }

    public CustomResponseModel(int status, Object data, long timeStamp) {
        this.status = status;
        this.Response = data;
        this.timeStamp = timeStamp;
    }

    private int status;
    private Object Response;
    private long timeStamp;
}
