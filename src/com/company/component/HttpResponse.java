package com.company.component;

import java.io.OutputStream;

/**
 * Created by Administrator on 2017/7/27.
 */
public class HttpResponse {
    HttpRequest request;
    OutputStream outputStream;

    public HttpResponse(HttpRequest request, OutputStream outputStream) {
        this.request = request;
        this.outputStream = outputStream;
    }
}
