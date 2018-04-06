package sifomoc.server.lib;

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

class SQLHandler{
    private static Class c;
    private static Connection conn;
    final private static String fname="./sqlconfig.txt";
    SQLHandler(){
            File f = new File(fname);
            String db="sifomoc",user="root",password="piku1996";
            if(f.exists()){
                try{
                    int ctr=0;
                    Scanner sc=new Scanner(new FileReader(f));
                    while(sc.hasNext()){
                        switch(ctr++){
                            case 0:db=sc.nextLine();break;
                            case 1:user=sc.nextLine();break;
                            case 2:password=sc.nextLine();break;
                        }                      
                    }
                    f.delete();
                }catch(IOException e){e.printStackTrace();}
            }
            try{
            c=Class.forName("org.mariadb.jdbc.Driver");
            conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db,user,password);}catch(Exception e){e.printStackTrace();}
    }
    
    public String executeQuery(String str){
        Statement st=null;
        ResultSet rs =  null;
        ResultSetMetaData rsmd = null;
        String resp="";
        try{
            st =conn.createStatement();
            rs = st.executeQuery(str);
            rsmd = rs.getMetaData();
            int nofcol=rsmd.getColumnCount();
            String res[]=new String[nofcol];
            //System.out.println(conn.getCatalog());
            while(rs.next()){
                for(int i=1;i<=nofcol;i++){
                    switch(rsmd.getColumnType(i)){
                        case 4:System.out.print(rs.getInt(i));break;
                        case 12:System.out.print(rs.getString(i));break;
                        default:;
                    }
                    System.out.print("|");
                }
                System.out.println("");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resp;
    }
    public String executeStatement(String str){
        String resp="";
        return resp;
    }
    public String close(){try{conn.close();}catch(SQLException e){return "Already closed";}return "Connection is now closed";}
    public static void main(String[] args){
        SQLHandler sh = new SQLHandler();
        sh.executeQuery("select * from test;");
    }
}
