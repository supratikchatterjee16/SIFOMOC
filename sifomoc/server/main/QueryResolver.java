package sifomoc.server.main;

import sifomoc.server.lib.SQLHandler;

import java.util.Scanner;

class QueryResolver{
	private SQLHandler mysql;
	private char ch[];
	private String queryClause;
	final private String tables[] ={"students","faculty","subjects"};
	QueryResolver(){
		mysql = new SQLHandler();
		ch=new char[4];
	}
	private String list(){
		String resp="";
		resp = mysql.executeQuery("select id, name from "+queryClause.substring(1));
		return 'l'+queryClause.substring(1)+','+resp;
	}
	private String detail(){
		String resp="";
		int i=0;
		System.out.println(queryClause);
		String query = queryClause.substring(1).trim();
		for(;i<3;i++){
			if(resp.length()==0)
				resp = mysql.executeStatement("select * from "+tables[i]+" where id = \'"+query+"\'");
			else break;
			//System.out.print(resp);
		}
		return 'd'+tables[i-1]+','+resp;
	}
	private String update(){
		String resp = "";
		return 'u'+resp;
	}
	private String create(){
		String resp = "";
		return 'c'+resp;
	}
	public String resolve(String str){
		str = str.toLowerCase();
		for(int i=0;i<str.length();i++)if(i<4)ch[i] = str.charAt(i); else break;
		queryClause = str;
		//System.out.println(str + ch[0]);
		switch(ch[0]){
			case 'c':return create();
			case 'd':return detail();
			case 'l':return list();
			case 'u':return update();
		}
		return "null";
	}
}
