

package sockettest6;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

// Referenced classes of package sockettest6:
//            Handler

public class SocketTest6
{

    public SocketTest6()
    {
    }

    public static void main(String args[])
    {
        try
        {
            ServerSocket serversocket = new ServerSocket(12345);
            System.out.println("Server is working");
            do
            {
                java.net.Socket socket = serversocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
                System.out.println("+1");
            } while(true);
        }
        catch(IOException ioexception)
        {
            return;
        }
    }
}

