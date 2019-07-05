/**
 * Author Administrator
 * Date   2015/11/16
 * Website http://fengyh.cn/
 */

import java.io.*;
import java.net.*;

public class EchoClient
{
    public static void main(String args[])
    {
        Socket sock=null;
        PrintWriter printWriter=null;
        BufferedReader buffReader=null;
        BufferedReader userInput=null;

        try
        {
            sock=new Socket("127.0.0.1",9999);

            printWriter=new PrintWriter(sock.getOutputStream(),true);
            buffReader=new BufferedReader(new InputStreamReader(sock.getInputStream()));
            userInput=new BufferedReader(new InputStreamReader(System.in));

            String str;
            for(int i=0;i<5;++i) // 商定好的欢迎语，共5行
            {
                str=buffReader.readLine();
                System.out.println(str);
            }

            while(true)
            {
                System.out.print("\n[SEND] ");
                str=userInput.readLine();
                if(str==null||str.trim().equalsIgnoreCase("BYE"))
                {
                    break;
                }

                printWriter.println(str);
                System.out.println(buffReader.readLine());
            }

            printWriter.close();
            buffReader.close();
            userInput.close();
            sock.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}