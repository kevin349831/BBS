

package sockettest6;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

// Referenced classes of package sockettest6:
//            Handler

class ChatRoom
{

    public ChatRoom(Handler handler)
        throws IOException
    {
        this.handler = handler;
        in = new BufferedReader(new InputStreamReader(handler.socket.getInputStream(), "BIG5"));
        out = new PrintWriter(new OutputStreamWriter(handler.socket.getOutputStream(), "BIG5"));
        synchronized(room)
        {
            if(!set)
            {
                for(int i = 0; i < room.length; i++)
                    room[i] = new Vector(10);

                set = true;
            }
        }
    }

    public void creRoom()
    {
        out.println("\033[1;31m\u8ACB\u8F38\u5165\u623F\u540D\033[m");
        out.flush();
        try
        {
            name = in.readLine();
        }
        catch(IOException ioexception) { }
        int i = 0;
        do
        {
            if(i >= room.length)
                break;
            if(room[i].isEmpty())
            {
                num = i;
                System.out.println(i);
                break;
            }
            i++;
        } while(true);
    }

    public void selRoom(int n)
    {
        synchronized(room)
        {
            name = ((ChatRoom)room[n].lastElement()).name;
        }
        num = n;
    }

    public void chat()
        throws IOException
    {
        synchronized(room)
        {
            room[num].addElement(this);
        }
        do
        {
            String msg = in.readLine();
            if(msg.equalsIgnoreCase("/b"))
                break;
            synchronized(room)
            {
                for(int i = 0; i < room[num].size(); i++)
                {
                    ChatRoom cr = (ChatRoom)room[num].elementAt(i);
                    if(this != cr)
                    {
                        cr.out.println((new StringBuilder()).append(handler.ID).append(" : ").append(msg).toString());
                        cr.out.flush();
                    }
                }

            }
        } while(true);
        synchronized(room)
        {
            room[num].removeElement(this);
        }
    }

    public void showRoomList()
    {
        synchronized(room)
        {
            boolean no_room = true;
            for(int i = 0; i < room.length; i++)
                if(!room[i].isEmpty())
                {
                    no_room = false;
                    out.println((new StringBuilder()).append("\033[1;31m").append(i).append("\t").append(((ChatRoom)room[i].lastElement()).name).append("\033[m").toString());
                }

            if(no_room)
                out.println("\033[1;31m\u6C92\u623F\u9593 \u5275\u4E00\u500B\u5427\033[m");
            out.flush();
        }
    }

    public static Vector room[] = new Vector[20];
    public static boolean set = false;
    public int num;
    public String name;
    public BufferedReader in;
    public PrintWriter out;
    public Handler handler;

}

