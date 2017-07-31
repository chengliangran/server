package com.company.container;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
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
//            Map<String,String> headers=request.getHeaders();
//            Iterator iterator=headers.entrySet().iterator();
//             while (iterator.hasNext()){
//                     Entry entry =(Entry)iterator.next();
//             }
            OutputStream outputStream= response.getOutputStream();
            outputStream.write("test123".getBytes());
            System.out.println("这里开始输出语句结束");
            outputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
