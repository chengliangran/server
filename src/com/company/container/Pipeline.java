package com.company.container;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-28.
 */
public class Pipeline {

    ValveContext valveContext=new ValveContext();



    BasicValve basicValve=new BasicValve();

    List<Valve> valves=new ArrayList<Valve>();

    public void addValve(Valve valve){
        valves.add(valve);
    }



    public void invoke(HttpRequest request,HttpResponse response){
        valveContext.invokeNext(request,response);
    }

    class ValveContext {
        int index=0;
        public void invokeNext(HttpRequest request, HttpResponse response){
            int curIndex=index;
            index++;
            if (curIndex<valves.size()){
                valves.get(curIndex).invoke(request,response,this);
            }else {
                if (basicValve!=null){
                    basicValve.invoke(request,response,this);
                }
            }

        }
    }


}
