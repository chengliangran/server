package com.company.component;

import com.company.container.MyContext;

import java.io.OutputStream;

/**
 * Created by Administrator on 2017/7/27.
 */
public class HttpResponse {
    HttpRequest request;

    OutputStream outputStream;

    MyContext context=null;

    public MyContext getContext() {
        return context;
    }

    public void setContext(MyContext context) {
        this.context = context;
    }

    public HttpResponse(HttpRequest request, OutputStream outputStream) {
        this.request = request;
        this.outputStream = outputStream;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }


}
