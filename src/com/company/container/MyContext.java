package com.company.container;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;
import oracle.jrockit.jfr.events.RequestableEventEnvironment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-28.
 */
public class MyContext {

    //组件大全

    Pipeline pipeline=new Pipeline();



    BasicValve valve=new BasicValve();

    List<Wrapper> wrappers=new ArrayList<>();

    public void addValve(Valve valve){
        pipeline.addValve(valve);
    }

    public void addWrapper(Wrapper wrapper){
        wrappers.add(wrapper);
    }

    public Wrapper getWrapper(String name){
        for (int i = 0; i < wrappers.size(); i++) {
             if (wrappers.get(i).getName()==name){
                return wrappers.get(i);
             }
        }
        return null;
    };

    //核心类
    public void invoke(HttpRequest request, HttpResponse response){

        response.setContext(this);
        System.out.println("测试context");
        pipeline.invoke(request,response);
    }
}
