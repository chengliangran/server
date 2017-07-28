package com.company.component;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/7/27.
 */
public class HttpRequest {

    InputStream inputStream;

    String requestStr=null;

    public HttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr;
    }

    public void parse(){

    }

    public void init() throws Exception {
        if (inputStream==null){
            throw  new Exception("没有inputstre");
        }else {
            int i=0;
            byte[] buf=new byte[2048];
            StringBuffer sb=new StringBuffer();
            while (i!=-1){
                i=inputStream.read(buf);
                sb.append(new String(buf,0,buf.length));
            }
            requestStr=sb.toString();
        }
    }

    public String getUrl(){
        if (requestStr!=null){
            int start=requestStr.indexOf(" ");
            int end=-1;
            if(start!=-1){
                end=requestStr.indexOf(" ",start+1);
            }
            if (end!=-1){
                return  requestStr.substring(start+1,end);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

}
