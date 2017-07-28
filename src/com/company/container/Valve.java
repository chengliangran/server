package com.company.container;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

/**
 * Created by Administrator on 2017-07-28.
 */
public interface Valve {
    public void invoke(HttpRequest request, HttpResponse response, Pipeline.ValveContext valveContext);


}
