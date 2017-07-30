package com.company.container;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

public class WrapperBaase implements Wrapper{

    Pipeline pipeline=new Pipeline();

    BasicWrapperValve basicWrapperValve= new BasicWrapperValve();

    public void addValve(Valve valve){
        pipeline.addValve(valve);
    }

    String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void invoke(HttpRequest request, HttpResponse response) {
        pipeline.invoke(request,response);
    }
}
