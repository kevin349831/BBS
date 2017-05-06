
package sockettest6;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

// Referenced classes of package sockettest6:
//            ooxx, Handler

public class GameRoom
{

    public GameRoom(Handler handler)
        throws IOException
    {
        this.handler = handler;
        in = new BufferedReader(new InputStreamReader(handler.socket.getInputStream(), "BIG5"));
        out = new PrintWriter(new OutputStreamWriter(handler.socket.getOutputStream(), "BIG5"));
        synchronized(room1)
        {
            if(!set1)
            {
                for(int i = 0; i < room1.length; i++)
                    room1[i] = new Vector(10);

                set1 = true;
            }
        }
    }

    public void creRoom()
    {
        out.println("\033[1;31m\u8ACB\u8F38\u5165\u623F\u540D\033[m");
        out.flush();
        try
        {
            name1 = in.readLine();
        }
        catch(IOException ioexception) { }
        int i = 0;
        do
        {
            if(i >= room1.length)
                break;
            if(room1[i].isEmpty())
            {
                num1 = i;
                System.out.println(i);
                break;
            }
            i++;
        } while(true);
    }

    public void selRoom(int n)
    {
        synchronized(room1)
        {
            name1 = ((GameRoom)room1[n].lastElement()).name1;
        }
        num1 = n;
    }

    public void chat()
        throws IOException
    {
        synchronized(room1)
        {
            room1[num1].addElement(this);
        }
        do
        {
            String msg = in.readLine();
            if(!msg.equalsIgnoreCase("/b"))
            {
                if(msg.equalsIgnoreCase("/ss"))
                    synchronized(room1)
                    {
                        for(int i = 0; i < room1[num1].size(); i++)
                        {
                            GameRoom cr = (GameRoom)room1[num1].elementAt(i);
                            if(this != cr)
                            {
                                ooxx oo = new ooxx(cr.handler.socket, cr.handler.ID, handler.socket, handler.ID);
                                oo.start();
                            }
                        }

                    }
                else
                    synchronized(room1)
                    {
                        for(int i = 0; i < room1[num1].size(); i++)
                        {
                            GameRoom cr = (GameRoom)room1[num1].elementAt(i);
                            if(this != cr)
                            {
                                cr.out.println((new StringBuilder()).append(handler.ID).append(" : ").append(msg).toString());
                                cr.out.flush();
                            }
                        }

                    }
            } else
            {
                synchronized(room1)
                {
                    room1[num1].removeElement(this);
                }
                return;
            }
        } while(true);
    }

    public void showRoomList()
    {
        synchronized(room1)
        {
            boolean no_room = true;
            for(int i = 0; i < room1.length; i++)
                if(!room1[i].isEmpty())
                {
                    no_room = false;
                    out.println((new StringBuilder()).append("\033[1;31m").append(i).append("\t").append(((GameRoom)room1[i].lastElement()).name1).append("\033[m").toString());
                }

            if(no_room)
                out.println("\033[1;31m\u6C92\u623F\u9593 \u5275\u4E00\u500B\u5427\033[m");
            out.flush();
        }
    }

    public static Vector room1[] = new Vector[20];
    public static boolean set1 = false;
    public int num1;
    public String name1;
    public BufferedReader in;
    public PrintWriter out;
    public Handler handler;

}

