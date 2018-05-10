package sifomoc.server.main;

import sifomoc.server.lib.SQLHandler;

import java.util.Scanner;

class QueryResolver{
	private static SQLHandler sh = new SQLHandler();
    private static String fetchList(String str){
        String resp="L";
        Scanner sc=new Scanner(str);
        String word = sc.next().toLowerCase();
        //System.out.println("List ready for "+word);
        //System.out.println(sh.executeStatement("select * from "+word));
        resp+=word+"\n";
        switch(word){
            case "faculty":resp+=sh.executeStatement("select id,name from "+word);break;
            case "students":resp+=sh.executeStatement("select id,name from "+word);break;
            case "subjects":resp+=sh.executeStatement("select id,name from "+word);break;
            default:resp="Invalid request";
        }
        //System.out.println(resp);
        return resp;
    }
    private static String fetchDetails(String str){
    	String resp="D";
    	Scanner sc=new Scanner(str);
    	String word = sc.next().toLowerCase();
    	System.out.println("Details ready for "+word);
    	resp+=sh.executeStatement("select * from students where id=\'"+word+"\'");
    	return resp;
    }
    private static String update(String str){
    	String resp="U";
    	return resp;
    }
    private static String fetch(String str){
        String resp="F";
        Scanner sc= new Scanner(str);
        //System.out.println(str);
        switch(sc.next()){
        	case "details":resp+=fetchDetails(str.substring(str.indexOf(" ")+1));break;
            case "list":resp+=fetchList(str.substring(str.indexOf(" ")+1));break;
            default:resp="Not found";
        }
        return resp;
    }
    public static String resolve(String str){
        String resp="Starting soon";
        //System.out.println(str);
        Scanner sc=new Scanner(str);
        String comm = sc.next();
        switch(comm){
            case "fetch":resp=fetch(str.substring(str.indexOf(" ")+1));break;
            case "update":resp=update(str.substring(str.indexOf(" ")+1));break;
            default:resp="Not found";
        }
        return resp;
    }
}
