package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public class HttpServer {

    List<HttpProcessor> processors=new ArrayList<>();

    int curProcessors=0;

    int maxProcessors=30;

    int minProcessors=10;

    public void  recycle(HttpProcessor processor){
        processors.add(processor);
    }

    private void addProcessor(){
        recycle(new HttpProcessor());
        curProcessors++;
    }

    public HttpProcessor getProcessor(){
        if (processors.get(processors.size()-1)==null){
            if (curProcessors<maxProcessors){
                addProcessor();
                return processors.remove(processors.size()-1);
            }else {
                System.out.println("已经用完了processor");
                return null;
            }
        }else {
            return processors.remove(processors.size()-1);
        }
    }

    public HttpServer() {
    }

    public static void main(String[] args) {
        new HttpServer().await();
    }

    public void await(){
        try {
            ServerSocket serverSocket=new ServerSocket(8080);
            boolean stopped=false;
            while (!stopped){
                Socket socket=serverSocket.accept();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
