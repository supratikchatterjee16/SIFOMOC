package sifomoc.server.lib;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

import java.util.Date;
import java.util.Calendar;

public class FileHandler{
    private static Date d = new Date();
    public static String createFile(String str){
        String res="";
        try{
            File f = new File(str);
            if(f.exists()) res="File already exists";
            else {
                String dir="";
                try{
                    dir = str.substring(0,str.lastIndexOf("/"));
                }catch(Exception e){
                    //e.printStackTrace();
                    
                }
                File fdir=new File(dir);
                if(!fdir.exists())fdir.mkdirs();
                f.createNewFile();
                f.setExecutable(false);
                f.setReadable(false);
                f.setWritable(false);
            }
        }catch(Exception e){res="Process could not complete";}
        return res;
    }
    public static String createFile(File f){ return createFile(f.getPath()); }
    public static String write(String fpath, String str){
        try{
            File f = new File(fpath);
            if(!f.exists()) createFile(f);
            FileWriter fw = new FileWriter(f);
            fw.write(str);
            fw.flush();
            fw.close();
        }catch(Exception e){return "Error in writing";}
        return "Success";
    }
    public static String log(String str){ return write("./logs"+d.toString(),str); }
}
