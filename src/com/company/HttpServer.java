package com.company;

import com.company.container.MyContext;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public class HttpServer {

    public HttpServer() {
        while (curProcessors<minProcessors){
            addProcessor();
        }
    }

    //容器
    MyContext context=new MyContext();

    public MyContext getContext() {
        return context;
    }

    //处理类
    List<HttpProcessor> processors=new ArrayList<>();

    int curProcessors=0;

    int maxProcessors=30;

    int minProcessors=10;

    public void  recycle(HttpProcessor processor){
        processors.add(processor);
    }

    private void addProcessor(){
        recycle(new HttpProcessor(this));
        curProcessors++;
        System.out.println("现存processors的总数"+processors.size());
    }

    public HttpProcessor getProcessor(){
        new Config(this).start();
        if (processors.size()==0){
            if (curProcessors<maxProcessors){
                addProcessor();
                System.out.println("当前的processor名字为"+processors.get(processors.size()-1).getName());
                return processors.remove(processors.size()-1);
            }else {
                System.out.println("已经用完了processor");
                return null;
            }
        }else {
            System.out.println("当前的processor名字为"+processors.get(processors.size()-1).getName());
            return processors.remove(processors.size()-1);
        }
    }

    public static void main(String[] args) {
        new HttpServer().await();
    }

    //启动接收socket
    public void await(){
        try {
            ServerSocket serverSocket=new ServerSocket(8080);
            boolean stopped=false;
            while (!stopped){
                Socket socket=serverSocket.accept();
                HttpProcessor processor=getProcessor();
                processor.process(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
