package sifomoc.server;

import java.net.ServerSocket;

import java.io.PrintStream;

public class ServerManager implements Runnable{
    private String name;
    private static int port;
    private static boolean on,sigkill;
    private static ServerSocket ss=null;
    public ServerManager(int n){
        port = n;
    }
    public void setPort(int n){port =n;}
    public void run(){
    	while(true)
        if(!on){
       	 on=true;
        	try{
            	Thread t=null;
            	ss = new ServerSocket(port);
            	//ss.setSoTimeout(1000);
            	while(on){
            	    t= new Thread(new Client(ss.accept()));
            	    t.start();
            	    if(sigkill){
            	    	on = false;
            	    	ss=null;
            	    }
            	}
        	}catch(Exception e){e.printStackTrace();}
        }
        else{
        	sifomoc.server.main.Log.reportError("Attempted reopening of the block");
        	return;
        }
    }

    public static void stop(){sigkill = true;}

    public static int getPort(){return port;}

    public static boolean isRunning(){return on;}

    public static void main(String[] args){Thread t=new Thread(new ServerManager(80));}

}
