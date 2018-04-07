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
      fname = fname.substring(fname.indexOf(" ")+1);
      fname = fname.substring(0,fname.indexOf(" "));
      //System.out.println(fname+" "+args[0]);
      if(fname.equals("/"))fname="/html/main.html";
      fname="."+fname;
      mime = mimeList.get(fname.substring(fname.lastIndexOf(".")+1));
      resp[0]=fname;
      resp[1]=mime;
      return resp;
    }
    public static String[] doPost(String[] args, String msg){
        String output="message.sfmc",resp="";
        String res[]={output,"text/plain"};
        //for(int i=0;i<args.length;i++)System.out.println(args[i]);
        resp=QueryResolver.resolve(msg);
        FileHandler.write(output,resp);
        return res;
    }
}
