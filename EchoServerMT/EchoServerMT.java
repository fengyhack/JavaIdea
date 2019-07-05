/**
 * Author Administrator
 * Date   2015/11/16
 * Website http://fengyh.cn/
 */

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class EchoServerMT
{
    public static void main(String args[])
    {
        ServerSocket serverSock=null;
        boolean listen=true;
        int id=0;

        try
        {
            serverSock=new ServerSocket(9999);

            System.out.println("### ["+LocalDateTime.now().toString()+"] Server started, waiting for a client...");

            while(listen)
            {
                Socket clientSock=serverSock.accept();
                Thread t=new EchoServerThread(clientSock,++id);
                t.start();
            }

            serverSock.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}