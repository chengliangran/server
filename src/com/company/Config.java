package com.company;

import com.company.container.MyContext;

/**
 * Created by Administrator on 2017-07-28.
 */
public class Config {
    Config(HttpServer server){
        MyContext context=server.getContext();
    }
    public void start(){
        System.out.println("配置完毕");
    }
}
