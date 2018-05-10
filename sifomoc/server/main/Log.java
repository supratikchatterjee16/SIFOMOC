package sifomoc.server.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Date;

public class Log{
	private static Date d=new Date();
	private static File f= new File("log.sfmc");
	public static void init(){
		try{
			if(f.exists())f.delete();
			FileWriter fw = new FileWriter(f);
			fw.write(d.toString()+"\n");
		}catch(IOException e){}
	}
	public static void reportError(String str){
		try{
			d=new Date();
			FileWriter fw= new FileWriter(f);
			fw.write(d.toString()+" : "+str+"\n");
			fw.flush();
			fw.close();
			}catch(IOException e){}
	}
}
