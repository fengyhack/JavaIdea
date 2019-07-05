/**
 * Author Administrator
 * Date   2015/11/16
 * Website http://fengyh.cn/
 */

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class EchoServerThread extends Thread
{
    private Socket clientSock=null;
    private int id=-1;

    public EchoServerThread(Socket sock,int id)
    {
        super("EchoServerThread");
        clientSock=sock;
        this.id=id;
    }

    public void run()
    {
        PrintWriter printWriter=null;
        BufferedReader buffReader=null;

        try
        {
            System.out.println("#"+id+"# ["+LocalDateTime.now().toString()+"] Accepted a client.");

            printWriter=new PrintWriter(clientSock.getOutputStream(),true);
            buffReader=new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

            System.out.println("#"+id+"# ["+LocalDateTime.now().toString()+"] Send welcome message...");

            // // 商定好的欢迎语，共5行
            printWriter.println("****************************************");
            printWriter.println("\tWelcome to ECHO\n\tPress BYE to exit");
            printWriter.println("\tYour client id: "+id);
            printWriter.println("****************************************");
            printWriter.flush();

            System.out.println("#"+id+"# ["+LocalDateTime.now().toString()+"] Enter ECHO loop...");

            while(true)
            {
                String str=buffReader.readLine();
                if(str==null)
                {
                    break;
                }

                System.out.println("#"+id+"# ["+LocalDateTime.now().toString()+"] [RECV] "+str);

                printWriter.println("[ECHO] "+str);
                printWriter.flush();

                if(str.trim().equalsIgnoreCase("BYE"))
                {
                    break;
                }
            }

            System.out.println("#"+id+"# ["+LocalDateTime.now().toString()+"] goodbye (connection reset).");

            printWriter.close();
            buffReader.close();
            clientSock.close();
        }
        catch(Exception e)
        {
            System.out.println("#"+id+"# "+e.getMessage());
        }
    }
}