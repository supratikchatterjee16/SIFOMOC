package sifomoc.server.lib;

import sifomoc.server.main.Log;

import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;

public final class SQLHandler{
    private static Class c;
    private static Connection conn;
    final private static String fname="./sqlconfig.txt";
    public SQLHandler(){
            File f = new File(fname);
            String db="sifomoc",user="",password="";
            if(f.exists()){
                try{
                    int ctr=0;
                    Scanner sc=new Scanner(new FileReader(f));
                    while(sc.hasNext()){
                        switch(ctr++){
                            case 0:db=sc.nextLine();break;
                            case 1:user=sc.nextLine();break;
                            case 2:password=sc.nextLine();break;
                            default:;
                        }
                    }
                    //f.delete();
                }catch(IOException e){e.printStackTrace();}
            }
            try{
            c=Class.forName("org.mariadb.jdbc.Driver");
            conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db,user,password);}catch(Exception e){
            	//e.printStackTrace();
            	Log.reportError(e.toString());
            }
    }

    public String executeQuery(String str){
        Statement st=null;
        ResultSet rs =  null;
        ResultSetMetaData rsmd = null;
        String resp="";
        //System.out.println(str);
        try{
            st =conn.createStatement();
            rs = st.executeQuery(str);
            rsmd = rs.getMetaData();
            int nofcol=rsmd.getColumnCount();
            while(rs.next()){
                for(int i=1;i<=nofcol;i++){
                    switch(rsmd.getColumnType(i)){
                        case 4:resp+=rs.getInt(i);break;
                        case 12:resp+=rs.getString(i);break;
                        default:;
                    }
                    resp+=",";
                }
                resp+="\n";
            }
        }catch(SQLException e){
            //e.printStackTrace();
            Log.reportError(e.toString());
        }
        return resp;
    }
    public String execute(String str){
        Statement st=null;
        ResultSet rs =  null;
        String resp="Unsuccesful";
        try{
            st =conn.createStatement();
            boolean success = st.execute(str);
            if(success)resp="Succesful";
        }catch(SQLException e){
        	//e.printStackTrace();
        	Log.reportError(e.toString());
        	}
        return resp;
    }
    public String executeStatement(String str){
        String resp="";
        String command = str.substring(0,str.indexOf(" "));
        command=command.trim();
        System.out.println(str +command);
        if(command.equalsIgnoreCase("select")||command.equalsIgnoreCase("delete")||command.equalsIgnoreCase("update")){resp=executeQuery(str);}
        else {resp=execute(str);}
        //System.out.println(resp);
        return resp;
    }
    public String close(){try{conn.close();}catch(SQLException e){return "Already closed";}return "Connection is now closed";}
    public static void main(String[] args){
        SQLHandler sh = new SQLHandler();
        System.out.println(sh.executeStatement("select * from test;"));
    }
}
