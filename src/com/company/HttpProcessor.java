package com.company;

import com.company.component.HttpRequest;
import com.company.component.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public void run() {
      if (socket!=null){
          InputStream inputStream=null;
          OutputStream outputStream=null;
          try {
              inputStream=socket.getInputStream();
              outputStream=socket.getOutputStream();
              StringBuffer requestString=new StringBuffer();
              int index=0;
              byte[] buf=new byte[2048];
              while (index!=-1){
                  index=inputStream.read(buf);
                  requestString.append(new String(buf,0,buf.length));
              }
              HttpRequest request=new HttpRequest(inputStream);
              HttpResponse response=new HttpResponse(request,outputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
          //处理完毕关闭socket
          try {
              socket.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      } else {
          System.out.println("没有socket");
      }
    }

}
