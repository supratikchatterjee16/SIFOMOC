package sifomoc.server;

import sifomoc.server.main.Parser;

import java.net.Socket;

import java.io.File;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Client implements Runnable{
    private Socket sock;
    private String address;
    Client(Socket s){
        sock=s;
        address = s.getInetAddress().toString();
        Parser.init();
    }
    public void run(){
        BufferedReader in = null;
        PrintStream out = null;
        String get[]=new String[20],post="",resp[]=new String[2];
        boolean flag = true;
        int ctr=0,pos=0,n=0;
        for(int i=0;i<20;i++)get[i]="";
        try{
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintStream(sock.getOutputStream());
            //GET section
            while(flag){
                n = in.read();
                switch(n){
                    case 10:ctr++;break;
                    case 13:pos++;ctr++;break;
                    default:{get[pos] += (char)n;ctr=0;}
                }
                if(ctr>2)flag=false;
            }
            //GET read end

            ctr=0;
            if(get[0].indexOf("GET")!=-1)  resp=Parser.doGet(get);   //GET Parsing isbeing done here
            else{																	//POST Parsing is done here
                for(int i=0;i<20;i++)
                    if(get[i].indexOf("Content-Length")!=-1){
                        ctr = Integer.parseInt(get[i].substring(get[i].indexOf(" ")+1));
                        break;
                    }
                
                in.read();//Removes the last \r character
                
                while(ctr--!=0)post+=(char)in.read();

                resp=Parser.doPost(get,post);
            }

            File f = new File(resp[0]);
             
             //Section : Headers Settings
            out.print("HTTP/1.1 200 OK\r\n");
            out.print("");//Date: Mon,27 July 2009 12:28:53 GMT
            out.print("Server: GRIMOIRE1\r\n");
            out.print("");//Last-Modified: Same date format as above
            out.print("");//E-Tag: Some-gibberish
            out.print("");//Vary: Authorization,Accept
            out.print("Accept-Ranges: bytes\r\n");
            out.print("Content-Length: "+f.length()+"\r\n");//Content-Length: #
            out.print("Content-Type: "+resp[1]+"\r\n");
            out.print("Connection: Closed\r\n\r\n");
            //out.print(res);
            //Section-End

            //Section : Send Logic
            if(f.exists()){
                 InputStream fr=new FileInputStream(f);
                byte b[]=new byte[8192];
                while((n=fr.read(b))!=-1){out.write(b,0,n);}
            }
            //Section-End
            out.flush();
            sock.close();
        }catch(Exception e){e.printStackTrace();}
    }
}
