package sifomoc.server;

import java.net.ServerSocket;

import java.io.PrintStream;

public class ServerManager implements Runnable{
    private String name;
    private static int port;
    private static boolean on;
    private static ServerSocket ss=null;
    public ServerManager(int n){
        port = n;
    }
    public void setPort(int n){port =n;}
    public void run(){
        on=true;
        try{
            Thread t=null;
            ss = new ServerSocket(port);
            while(on){
                t= new Thread(new Client(ss.accept()));
                t.start();
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public static void toggle(){
        on=!on;
        ss=null;
        }
    public static int getPort(){return port;}
    public static boolean isRunning(){return on;}
}
