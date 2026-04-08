package com.smartcouriersystem.Smart.Courier.Management.System.DTO.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import javax.xml.transform.sax.SAXResult;

public class Response {
    Object response;
    HttpStatusCode statusCode;
    String message;

    public Response(HttpStatusCode statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Response(Object response, HttpStatusCode statusCode, String message) {
        this.response = response;
        this.statusCode = statusCode;
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
