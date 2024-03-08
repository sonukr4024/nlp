package com.nlp.nlp.utils;

public class Response {
    Object data;

    public static Response setResponse(Object data) {
        Response response = new Response();
        response.data = data;
        return response;
    }

    public Object getData() {
        return data;
    }

}
