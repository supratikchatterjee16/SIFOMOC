package sifomoc;

import java.util.HashMap;

class Driver{
    public static void main(String[] args){
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        Scanner sc=new Scanner(System.in);
        String str="",command="",arg="",commandsList[]={"start","stop","help","exit"};
        int temp=0;
        Thread t=new Thread(new Server());
        for(int i=0;i<commandsList.length;i++)hm.insert(commandsList[i],i);
        System.out.println("This is the SIFMOC CLI.\n For a brief intro on basic commands, type help.\nFor help on particular topics, type help <topic>\n>>");
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
        }catch(Exception e){}
    }
}
