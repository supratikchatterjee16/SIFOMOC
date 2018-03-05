package sifomoc.server;

import java.net.Socket;

class Client implements Runnable{
    private Socket sock;
    private String address;
    Client(Socket s){
        sock=s;
        address = s.getInetAddress().toString();
    }
    public void run(){
        
    }
}
