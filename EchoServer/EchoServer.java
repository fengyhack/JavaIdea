/**
 * Author Administrator
 * Date   2015/11/16
 * Website http://fengyh.cn/
 */

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class EchoServer
{
    public static void main(String args[])
    {
        ServerSocket serverSock=null;
        Socket clientSock=null;
        PrintWriter printWriter=null;
        BufferedReader buffReader=null;

        try
        {
            serverSock=new ServerSocket(9999);


            System.out.println("["+LocalDateTime.now().toString()+"] Server started, waiting for a client...");

            clientSock=serverSock.accept();

            System.out.println("["+LocalDateTime.now().toString()+"] Accepted a client.");

            printWriter=new PrintWriter(clientSock.getOutputStream(),true);
            buffReader=new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

            System.out.println("["+LocalDateTime.now().toString()+"] Send welcome message...");

            // // 商定好的欢迎语，共4行
            printWriter.println("****************************************");
            printWriter.println("\tWelcome to ECHO !\n\tPress BYE to exit");
            printWriter.println("****************************************");
            printWriter.flush();

            System.out.println("["+LocalDateTime.now().toString()+"] Enter ECHO loop...");

            while(true)
            {
                String str=buffReader.readLine();
                if(str==null)
                {
                    break;
                }

                System.out.println("\n["+LocalDateTime.now().toString()+"] [RECV] "+str);

                printWriter.println("[ECHO] "+str);
                printWriter.flush();

                if(str.trim().equalsIgnoreCase("BYE"))
                {
                    break;
                }
            }

            System.out.println("\nPress any key to continue...");
            System.in.read();

            printWriter.close();
            buffReader.close();
            clientSock.close();
            serverSock.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}