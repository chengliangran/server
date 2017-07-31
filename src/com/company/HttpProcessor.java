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

    private static int no=0;

    String name=null;

    public String getName() {
        return name;
    }

    Socket socket=null;

    HttpServer server=null;

    public HttpProcessor(HttpServer server) {
        this.server = server;
        name="第"+no+++"个处理器";
    }

    public void process(Socket socket){
        this.socket=socket;
        System.out.println("now we are processing socket");
        System.out.println("now we are finishing socket");

        Thread thread=new Thread(this);
        thread.start();
    }

    @Override
    //处理socket并将结果发送给context
    public void run() {

        if (socket!=null){
          InputStream inputStream=null;
          OutputStream outputStream=null;
          try {
              inputStream=socket.getInputStream();
              outputStream=socket.getOutputStream();
              StringBuffer requestString=new StringBuffer();
              HttpRequest request=new HttpRequest(inputStream);
              HttpResponse response=new HttpResponse(request,outputStream);
              try {
                  request.init();
                  if (requestString==null){

                      System.out.println("名为"+this.getName()+"的处理者收到的请求信息"+request.getRequestStr());
                  }else {
                      System.out.println("名为"+this.getName()+"的处理者收到的请求信息为空");

                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
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
              System.out.println("名为"+this.getName()+"的处理者完成了 socket的处理");
          } catch (IOException e) {
              e.printStackTrace();
          }
          server.recycle(this);
      } else {
          System.out.println("没有socket");
      }
    }

}
