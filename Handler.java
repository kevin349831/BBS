
package sockettest6;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package sockettest6:
//            Jdbc, GameRoom, ChatRoom

class Handler extends Thread
{

    public Handler(Socket socket)
        throws IOException
    {
        load = false;
        auther2 = null;
        auther = null;
        title = null;
        content = null;
        tt = null;
        talk = null;
        aid = null;
        update = null;
        game = null;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "big5"));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "big5"));
    }

    public void setID(String id)
    {
        ID = id;
    }

    public void run()
    {
        Jdbc j;
        while(true) 
        {
            out.println("\033[1;45m\u6307\u4EE4: /in \u767B\u5165 , /new \u5275\u65B0\u5E33\u865F         \033[m");
            out.println("\033[1;33m\u9810\u8A2D\u5E33\u865F\u5BC6\u78BC kevin(123),acc(acc),kk(kk)\033[m");
            out.println("");
            out.flush();
            String tt = in.readLine();
            if(tt.equals("/in"))
            {
                out.print("\033[H\033[2J");
                do
                {
                    out.println("\033[1;42m\u8ACB\u8F38\u5165\u5E33\u865F\033[m");
                    out.flush();
                    String id = in.readLine();
                    if(id.equals("/b"))
                    {
                        out.print("\033[H\033[2J");
                        continue;
                    }
                    setID(id);
                    out.println("\033[1;42m\u8ACB\u8F38\u5165\u5BC6\u78BC\033[m");
                    out.flush();
                    String passwd = in.readLine();
                    if(passwd.equals("/b"))
                    {
                        out.print("\033[H\033[2J");
                        continue;
                    }
                    j = new Jdbc();
                    ResultSet r = j.Query((new StringBuilder()).append("select * from bbs_user where acc='").append(ID).append("'").toString());
                    do
                    {
                        if(!r.next())
                            break;
                        out.println((new StringBuilder()).append(r.getString("userid")).append("\t").append(r.getString("acc")).append(r.getString("passwd")).toString());
                        if(!r.getString("passwd").equals(passwd))
                            continue;
                        load = true;
                        break;
                    } while(true);
                    if(load)
                    {
                        out.print("\033[H\033[2J");
                        break;
                    }
                    out.print("\033[H\033[2J");
                    out.println("\033[1;31m\u5E33\u865F\u5BC6\u78BC\u932F\u8AA4\033[m");
                } while(true);
                break;
            }
            if(tt.equals("/new"))
            {
                out.print("\033[H\033[2J");
                out.println("\033[1;31m\u5275\u65B0\u5E33\u865F  /b\u8FD4\u56DE\033[m");
                out.println("\033[1;42m\u5E33\u865F\033[m");
                out.flush();
                String id = in.readLine();
                if(id.equals("/b"))
                {
                    out.print("\033[H\033[2J");
                    continue;
                }
                setID(id);
                out.println("\033[1;42m\u5BC6\u78BC\033[m");
                out.flush();
                String passwd = in.readLine();
                if(passwd.equals("/b"))
                {
                    out.print("\033[H\033[2J");
                    continue;
                }
                load = true;
                j = new Jdbc();
                String sql = (new StringBuilder()).append("INSERT INTO bbs_user VALUES (NULL, '").append(ID).append("', '").append(passwd).append("')").toString();
                ResultSet r9 = j.Add(sql);
                out.print("\033[H\033[2J");
                break;
            }
            out.print("\033[H\033[2J");
            out.println("\033[1;31m\u932F\u8AA4\u6307\u4EE4\033[m");
        }
_L15:
        byte byte0;
        if(!load)
            break; /* Loop/switch isn't completed */
        out.print("\033[H\033[2J");
        out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340\033[m").toString());
        out.println("\033[1;33m\u6307\u4EE4: /f \u9032\u5165\u8AD6\u58C7\u5217\u8868 , /c \u9032\u5165\u804A\u5929\u5BA4\u5217\u8868 , /g \u9032\u5165\u904A\u6232\u5BA4 , /b \u96E2\u958B\033[m");
        out.println();
        out.println("\033[1;31m\u8ACB\u9078\u64C7\u529F\u80FD\033[m");
        out.flush();
        String mode = in.readLine();
        if(mode.equals("/b"))
            break; /* Loop/switch isn't completed */
        String s = mode.toLowerCase();
        byte0 = -1;
        switch(s.hashCode())
        {
        case 1560: 
            if(s.equals("/g"))
                byte0 = 1;
            break;

        case 1559: 
            if(s.equals("/f"))
                byte0 = 2;
            break;

        case 1556: 
            if(s.equals("/c"))
                byte0 = 3;
            break;
        }
        byte0;
        JVM INSTR tableswitch 1 3: default 764
    //                   1 792
    //                   2 1180
    //                   3 2835;
           goto _L1 _L2 _L3 _L4
_L1:
        out.println("\033[1;31m\u8F38\u5165\u932F\u8AA4 \u518D\u8A66\u4E00\u6B21\033[m");
        out.print("\033[H\033[2J");
        out.flush();
        continue; /* Loop/switch isn't completed */
_L2:
        do
        {
            out.print("\033[H\033[2J");
            out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u904A\u6232\u5340\033[m").toString());
            out.println("\033[1;33m\u6307\u4EE4: /c \u5EFA\u7ACB\u65B0\u904A\u6232\u5BA4 , (\u904A\u6232\u5BA4\u7DE8\u865F) \u9032\u5165\u904A\u6232\u5BA4 , /b \u96E2\u958B\033[m");
            out.println();
            out.flush();
            room1 = new GameRoom(this);
            room1.showRoomList();
            String mode2 = in.readLine();
            if(mode2.equalsIgnoreCase("/b"))
                continue; /* Loop/switch isn't completed */
            if(mode2.equalsIgnoreCase("/c"))
            {
                room1.creRoom();
                out.print("\033[H\033[2J");
                out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u904A\u6232\u5340>\u904A\u6232\u5BA4 ").append(room1.num1).append(" ").append(room1.name1).append("\033[m").toString());
                out.println("\033[1;33m\u6307\u4EE4: /b \u96E2\u958B\033[m");
                out.println("\033[1;33m\u904A\u6232\u8AAA\u660E:\u7576\u623F\u9593\u6709\u5169\u500B\u4EBA\u4EE5\u4E0A\u6642,\u8F38\u5165/ss\u6703\u81EA\u52D5\u958B\u59CB\u5C0D\u6230\033[m");
                out.flush();
                room1.chat();
            } else
            if(mode2.matches("[0-9]|[1-9][0-9]"))
            {
                int n = Integer.valueOf(mode2).intValue();
                room1.selRoom(n);
                out.print("\033[H\033[2J");
                out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u904A\u6232\u5340>\u904A\u6232\u5BA4 ").append(room1.num1).append(" ").append(room1.name1).append("\033[m").toString());
                out.println("\033[1;33m\u6307\u4EE4: /ss \u958B\u59CB\u904A\u6232, /b \u96E2\u958B\033[m");
                out.println("\033[1;33m\u904A\u6232\u8AAA\u660E:\u7576\u623F\u9593\u6709\u5169\u500B\u4EBA\u4EE5\u4E0A\u6642,\u8F38\u5165/ss\u6703\u81EA\u52D5\u958B\u59CB\u5C0D\u6230\033[m");
                out.flush();
                room1.chat();
            } else
            {
                out.println("\033[1;31m\u8F38\u5165\u932F\u8AA4 \u518D\u8A66\u4E00\u6B21\033[m");
                out.flush();
            }
        } while(true);
_L3:
        int count;
        int f_id;
label0:
        do
            do
            {
                mode2 = new Jdbc();
                ResultSet r = mode2.Query("select * from bbs_forum");
                out.print("\033[H\033[2J");
                out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u8AD6\u58C7\u5217\u8868\033[m").toString());
                out.println("\033[1;33m\u6307\u4EE4: (\u8AD6\u58C7\u7DE8\u865F) \u9032\u5165\u8AD6\u58C7 , /b \u96E2\u958B\033[m");
                out.println();
                out.println("\033[1;33m\u7DE8\u865F\t\u8AD6\u58C7\u540D\u7A31\033[m");
                count = 0;
                for(; r.next(); out.println((new StringBuilder()).append("\033[1;35m").append(r.getString("f_id")).append("\t").append(r.getString("name")).append("\033[m").toString()))
                    count++;

                out.flush();
                String mode2 = in.readLine();
                if(mode2.equalsIgnoreCase("/b"))
                    continue; /* Loop/switch isn't completed */
                try
                {
                    f_id = Integer.valueOf(mode2).intValue();
                    System.out.println(f_id);
                    continue label0;
                }
                catch(NumberFormatException e)
                {
                    System.out.println("continue");
                }
            } while(true);
        while(f_id > count || f_id <= 0);
_L11:
        int count2;
        String mode3;
        ResultSet r2 = mode2.Query((new StringBuilder()).append("select * from bbs_article where f_id=").append(f_id).toString());
        out.print("\033[H\033[2J");
        out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u8AD6\u58C7\u5217\u8868>\u8AD6\u58C7").append(f_id).append("\033[m").toString());
        out.println("\033[1;33m\u6307\u4EE4: (\u6587\u7AE0\u7DE8\u865F) \u95B1\u8B80\u6587\u7AE0 , /b \u96E2\u958B , /n \u5275\u6587\u7AE0\033[m");
        out.println();
        out.println("\033[1;33m\u7DE8\u865F\t\u6A19\u984C\t\u4F5C\u8005\033[m");
        count2 = 0;
        for(; r2.next(); out.println((new StringBuilder()).append("\033[1;44m").append(count2).append("\t").append(r2.getString("title")).append("\t").append(r2.getString("auther")).append("\033[m").toString()))
            count2++;

        out.flush();
        mode3 = in.readLine();
        if(!mode3.equalsIgnoreCase("/b")) goto _L5; else goto _L3
_L5:
        if(!mode3.equalsIgnoreCase("/n")) goto _L7; else goto _L6
_L6:
        out.print("\033[2J");
        out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u8AD6\u58C7\u5217\u8868>\u8AD6\u58C7").append(f_id).append(">\u65B0\u589E\u6587\u7AE0\033[m").toString());
        out.println("\033[1;33m/title \u6587\u7AE0\u6A19\u984C, /content \u6587\u7AE0\u5167\u5BB9, /b \u653E\u68C4, /save \u5B58\u6A94\033[m");
        out.println();
        out.flush();
_L9:
        mode3 = in.readLine();
        if(!mode3.equals("/b")) goto _L8; else goto _L7
_L8:
        if(mode3.equals("/title"))
        {
            System.out.println("title");
            out.println("\033[1;33m\u8ACB\u8F38\u5165\u6A19\u984C\033[m");
            out.flush();
            title = in.readLine();
        } else
        {
            if(!mode3.equals("/content"))
                continue; /* Loop/switch isn't completed */
            System.out.println("content");
            out.println("\033[1;33m\u8ACB\u8F38\u5165\u6587\u7AE0\u5167\u6587\033[m");
            out.flush();
            content = in.readLine();
        }
          goto _L9
        if(!mode3.equals("/save")) goto _L9; else goto _L10
_L10:
        String sql = (new StringBuilder()).append("INSERT INTO bbs_article VALUES (NULL, '").append(title).append("', '").append(ID).append("', '").append(content).append("', '").append(f_id).append("',NULL)").toString();
        ResultSet r9 = mode2.Add(sql);
_L7:
        int list_n;
        try
        {
            list_n = Integer.valueOf(mode3).intValue();
            System.out.println(list_n);
            continue; /* Loop/switch isn't completed */
        }
        catch(NumberFormatException e)
        {
            System.out.println("continue");
        }
          goto _L11
        if(list_n <= count2 && list_n >= 0) goto _L12; else goto _L11
_L12:
        ResultSet r3 = mode2.Query((new StringBuilder()).append("select * from bbs_article where f_id=").append(f_id).append(" limit ").append(list_n - 1).append(",1").toString());
        out.print("\033[H\033[2J");
        out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u8AD6\u58C7\u5217\u8868>\u8AD6\u58C7").append(f_id).append("\033[m").toString());
        if(r3.next())
        {
            if(ID.equals(r3.getString("auther")))
                out.println("\033[1;33m\u6307\u4EE4: /b \u96E2\u958B, /talk \u7559\u8A00, /delete \u522A\u9664\u6587\u7AE0, /edit \u4FEE\u6539\u6587\u7AE0\033[m");
            else
                out.println("\033[1;33m\u6307\u4EE4: /b \u96E2\u958B, /talk \u7559\u8A00\033[m");
            out.println();
            out.println((new StringBuilder()).append("\033[1;33m\u6587\u7AE0\u7DE8\u865F: ").append(r3.getString("a_id")).append("\033[m").toString());
            out.println((new StringBuilder()).append("\033[1;33m\u6587\u7AE0\u540D\u7A31: ").append(r3.getString("title")).append("\033[m").toString());
            out.println((new StringBuilder()).append("\033[1;33m\u6587\u7AE0\u4F5C\u8005: ").append(r3.getString("auther")).append("\033[m").toString());
            out.println("\033[1;33m\u6587\u7AE0\u5167\u5BB9: \033[m");
            out.println((new StringBuilder()).append("\033[1;32m").append(r3.getString("content")).append("\033[m").toString());
            out.println("");
            out.println("\033[1;33m\u7559\u8A00\u5167\u5BB9: \033[m");
            auther2 = r3.getString("auther");
            aid = r3.getString("a_id");
            System.out.println(aid);
            for(ResultSet r4 = mode2.Query((new StringBuilder()).append("select * from bbs_remark where a_id=").append(r3.getString("a_id")).toString()); r4.next(); out.println((new StringBuilder()).append("\033[1;36m").append(r4.getString("name")).append("\t").append(r4.getString("remark")).append("\033[m").toString()));
            out.flush();
        }
        this.tt = in.readLine();
        if(!this.tt.equals("/b")) goto _L13; else goto _L11
_L13:
        if(this.tt.equals("/talk"))
        {
            out.println("\033[1;33m\u8F38\u5165\u7559\u8A00\033[m");
            out.flush();
            talk = in.readLine();
            System.out.println(talk);
            String sql = (new StringBuilder()).append("INSERT INTO bbs_remark VALUES (NULL, '").append(talk).append("','").append(ID).append("','").append(aid).append("')").toString();
            ResultSet r9 = mode2.Add(sql);
        } else
        if(this.tt.equals("/delete"))
        {
            System.out.println("delete");
            if(auther2.equals(ID))
            {
                System.out.println("it's your");
                String sql = (new StringBuilder()).append("DELETE FROM bbs_article WHERE a_id=").append(aid).toString();
                ResultSet resultset = mode2.Add(sql);
            } else
            {
                System.out.println("not your");
            }
        } else
        if(this.tt.equals("/edit"))
        {
            System.out.println("edit");
            if(auther2.equals(ID))
            {
                System.out.println("it's your");
                out.println("\033[1;33m\u64B0\u5BEB\u66F4\u65B0\u5167\u5BB9\033[m");
                out.flush();
                update = in.readLine();
                System.out.println(update);
                String sql = (new StringBuilder()).append("UPDATE bbs_article SET content='").append(update).append("' WHERE a_id=").append(aid).toString();
                ResultSet resultset1 = mode2.Add(sql);
            } else
            {
                System.out.println("not your");
            }
        }
          goto _L12
_L4:
        do
        {
            out.print("\033[H\033[2J");
            out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u804A\u5929\u5217\u8868\033[m").toString());
            out.println("\033[1;33m\u6307\u4EE4: /c \u5EFA\u7ACB\u65B0\u623F\u9593 , (\u623F\u9593\u7DE8\u865F) \u9032\u5165\u623F\u9593 , /b \u96E2\u958B\033[m");
            out.println();
            out.flush();
            room = new ChatRoom(this);
            room.showRoomList();
            String mode2 = in.readLine();
            if(mode2.equalsIgnoreCase("/b"))
                break;
            if(mode2.equalsIgnoreCase("/c"))
            {
                room.creRoom();
                out.print("\033[H\033[2J");
                out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u804A\u5929\u5217\u8868>\u623F\u9593 ").append(room.num).append(" ").append(room.name).append("\033[m").toString());
                out.println("\033[1;33m\u6307\u4EE4: /b \u96E2\u958B\033[m");
                out.flush();
                room.chat();
            } else
            if(mode2.matches("[0-9]|[1-9][0-9]"))
            {
                int n = Integer.valueOf(mode2).intValue();
                room.selRoom(n);
                out.print("\033[H\033[2J");
                out.println((new StringBuilder()).append("\033[1;33m\u66B1\u7A31 :").append(ID).append(" \u76EE\u524D\u4F4D\u7F6E \u529F\u80FD\u5340>\u804A\u5929\u5217\u8868>\u623F\u9593 ").append(room.num).append(" ").append(room.name).append("\033[m").toString());
                out.println("\033[1;33m\u6307\u4EE4: /b \u96E2\u958B\033[m");
                out.flush();
                room.chat();
            } else
            {
                out.println("\033[1;31m\u8F38\u5165\u932F\u8AA4 \u518D\u8A66\u4E00\u6B21\033[m");
                out.flush();
            }
        } while(true);
        if(true) goto _L15; else goto _L14
_L14:
        try
        {
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException ioexception) { }
        break MISSING_BLOCK_LABEL_3386;
        SQLException ex;
        ex;
        try
        {
            in.close();
            out.close();
            socket.close();
        }
        // Misplaced declaration of an exception variable
        catch(SQLException ex) { }
        break MISSING_BLOCK_LABEL_3386;
        ex;
        Logger.getLogger(sockettest6/Handler.getName()).log(Level.SEVERE, null, ex);
        try
        {
            in.close();
            out.close();
            socket.close();
        }
        // Misplaced declaration of an exception variable
        catch(SQLException ex) { }
        break MISSING_BLOCK_LABEL_3386;
        ex;
        Logger.getLogger(sockettest6/Handler.getName()).log(Level.SEVERE, null, ex);
        try
        {
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException ioexception1) { }
        break MISSING_BLOCK_LABEL_3386;
        Exception exception;
        exception;
        try
        {
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException ioexception2) { }
        throw exception;
    }

    public boolean load;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";
    public String ID;
    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;
    public ChatRoom room;
    public GameRoom room1;
    private String auther2;
    private String auther;
    private String title;
    private String content;
    private String tt;
    private String talk;
    private String aid;
    private String update;
    private String game;
}

