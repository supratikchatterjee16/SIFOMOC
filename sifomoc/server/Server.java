package sifomoc.server;

import java.net.ServerSocket;

public class Server implements Runnable{
    private String name;
    private int port;
    private static boolean on;
    public Server(int n){
        port = n;
        on=true;
    }
    public static void toggle(){on=false;}
    public void setPort(int n){port =n;}
    public void run(){
        ServerSocket ss=null;
        try{
            Thread t=null;
            while(on){
                t= new Thread(new Client(ss.accept()));
                System.out.println("Thread created");
                t.start();
            }
        }catch(Exception e){e.printStackTrace();}
    }
}
