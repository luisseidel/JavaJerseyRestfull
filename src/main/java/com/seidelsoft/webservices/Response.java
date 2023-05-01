package com.seidelsoft.webservices;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {
    private String status;
    private String msg;
    private String body;

    public Response() {
    }

    public static Response Ok(String msg) {
        Response r = new Response();
        r.setStatus("OK");
        r.setMsg(msg);
        return r;
    }

    public static Response Ok(String msg, String body) {
        Response r = new Response();
        r.setStatus("OK");
        r.setMsg(msg);
        r.setBody(body);
        return r;
    }

    public static Response Error(String string) {
        Response r = new Response();
        r.setStatus("ERROR");
        r.setMsg(string);
        return r;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

