package sifomoc.server.main;

import sifomoc.server.lib.SQLHandler;

import java.util.Scanner;

class QueryResolver{
    private static String fetchList(String str){
        String resp="";
        Scanner sc=new Scanner(str);
        String word = sc.next().toLowerCase();
        SQLHandler sh = new SQLHandler();
        switch(word){
            case "faculty":;
            case "students":;
            case "subjects":resp=sh.execute("select id,name from "+word);break;
            default:resp="Invalid request";
        }
        return resp;
    }
    private static String fetch(String str){
        String resp="";
        Scanner sc= new Scanner(str);
        
        switch(sc.next()){
            case "list":resp=fetchList(str.substring(str.indexOf(" ")+1));break;
            default:resp="Not found";
        }
        return resp;
    }
    public static String resolve(String str){
        String resp="Starting soon";
        Scanner sc=new Scanner(str);
        String comm = sc.next();
        switch(comm){
            case "fetch":resp=fetch(str.substring(str.indexOf(" ")+1));break;
            default:resp="Not found";
        }
        return resp;
    }
}
