package com.company;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/7/27.
 */
public class HttpProcessor implements Runnable {

    Socket socket=null;

    HttpServer server=null;

    public HttpProcessor(HttpServer server) {
        this.server = server;
    }

    public void process(Socket socket){
        this.socket=socket;
        Thread thread=new Thread(this);
        thread.start();
    }

    @Override
    //处理socket并将结果发送给context
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (socket!=null){
          InputStream inputStream=null;
          OutputStream outputStream=null;
          try {
              inputStream=socket.getInputStream();
              outputStream=socket.getOutputStream();
              StringBuffer requestString=new StringBuffer();
              HttpRequest request=new HttpRequest(inputStream);
              HttpResponse response=new HttpResponse(request,outputStream);
              if (server.getContext()!=null){
                  server.getContext().invoke(request,response);
              }else {
                  System.out.println("没有拿到context");
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
          //处理完毕关闭socket
          try {
              socket.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
          server.recycle(this);
      } else {
          System.out.println("没有socket");
      }
    }

}
