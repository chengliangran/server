package com.company.container;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Administrator on 2017-07-28.
 */
public class BasicValve implements Valve {
    @Override
    public void invoke(HttpRequest request, HttpResponse response, Pipeline.ValveContext valveContext) {
        System.out.println("达到基础阀门");
        try {
            Map<String,String> headers=request.getHeaders();
            Iterator iterator=headers.entrySet().iterator();
            System.out.println("测试长度"+headers.size());
            while (iterator.hasNext()){
                     Entry entry =(Entry)iterator.next();
                System.out.println(entry.getKey()+"="+entry.getValue());
            }
            response.getOutputStream().write("测试socket".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
