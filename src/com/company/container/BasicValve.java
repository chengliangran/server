package com.company.container;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

import java.io.IOException;

/**
 * Created by Administrator on 2017-07-28.
 */
public class BasicValve implements Valve {
    @Override
    public void invoke(HttpRequest request, HttpResponse response, Pipeline.ValveContext valveContext) {
        System.out.println("达到基础阀门");
        try {
            response.getOutputStream().write("测试socket".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
