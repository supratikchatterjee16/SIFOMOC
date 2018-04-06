package sifomoc.server.main;

import sifomoc.server.lib.FileHandler;

import java.util.Hashtable;

public class Parser{
    private static Hashtable<String, String> mimeList = null;
    public static void init(){
        mimeList=new Hashtable<String, String>();
        mimeList.put("ico","image/png");
        mimeList.put("png","image/png");
        mimeList.put("jpeg","image/jpeg");
        mimeList.put("jpg","image/jpeg");
        mimeList.put("html","text/html");
        mimeList.put("htm","text/html");
        mimeList.put("css","text/css");
        mimeList.put("js","text/javascript");
        mimeList.put("svg","image/svg+xml");
        mimeList.put("txt","text/plain");
        mimeList.put("ttf","font/ttf");
    }
    public static String[] doGet(String[] args){
      String fname = args[0],mime="",resp[]=new String[2];
      fname = fname.substring(fname.indexOf(" "));
      fname = fname.substring(0,fname.indexOf(" "));
      if(fname.equals("/"))fname="/pages/main.html";
      fname="."+fname;
      mime = mimeList.get(fname.indexOf(".")+1);
      resp[0]=fname;
      resp[2]=mime;
      return resp;
    }
    public static String[] doPost(String[] args, String msg){
        String res[]={"message.sfmc","text/plain"};
        FileHandler.write("message.sfmc","");
        return res;
    }
}
