package sifomoc;

import sifomoc.server.main.Log;

import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

class Driver{
    private static Thread t =null;
    private static void start(String arg){
        int p=0;
        if(sifomoc.server.ServerManager.isRunning())System.out.println("Server is already running.");
        else{
            if(arg.length()==0){
                t=new Thread(new sifomoc.server.ServerManager(80));
                t.start();
            }
            else {
                try{
                    
                }catch(InputMismatchException e){System.err.println("Required a numeric input. Found a non numeric argument.\nPlease try again.\nSample command : start 80");}
            }
            System.out.println("Server started");
        }
    }
    private static void stop(String arg){
    }    
    private static void help(String arg){}
    private static void exit(String arg){System.exit(0);}
    public static void main(String[] args){
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        Scanner sc=new Scanner(System.in);
        Log.init();
        String str="",command="",arg="",commandsList[]={"start","stop","help","exit"};
        int temp=0;
        for(int i=0;i<commandsList.length;i++)hm.put(commandsList[i],i);
        //System.out.println("This is the SIFMOC CLI.\nFor a brief intro on basic commands, type help.\nFor help on particular topics, type help <topic>\n>>");
        try{
            while(true){
                System.out.print(">>");
                str = sc.nextLine();
                temp=str.indexOf(" ");
                if(temp==-1){command = str;arg="";}
                else {command = str.substring(0,temp);arg=str.substring(temp);}
                switch(hm.get(str)){
                    case 0:start(arg);break;
                    case 1:stop(arg);break;
                    case 2:help(arg);break;
                    case 3:exit(arg);break;
                    default : ;
                }
            }
        }catch(Exception e){e.printStackTrace();System.exit(0);}
    }
}
