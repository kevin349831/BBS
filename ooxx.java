

package sockettest6;

import java.io.*;
import java.net.Socket;

class ooxx
{

    public ooxx(Socket player1, String name1, Socket player2, String name2)
        throws IOException
    {
        display = new char[3][3];
        this.name1 = name1;
        this.name2 = name2;
        in1 = new BufferedReader(new InputStreamReader(player1.getInputStream(), "BIG5"));
        out1 = new PrintWriter(new OutputStreamWriter(player1.getOutputStream(), "BIG5"));
        in2 = new BufferedReader(new InputStreamReader(player2.getInputStream(), "BIG5"));
        out2 = new PrintWriter(new OutputStreamWriter(player2.getOutputStream(), "BIG5"));
        for(int i = 0; i < display.length; i++)
        {
            for(int j = 0; j < display[i].length; j++)
                display[i][j] = '-';

        }

    }

    public ooxx()
    {
        display = new char[3][3];
        for(int i = 0; i < display.length; i++)
        {
            for(int j = 0; j < display[i].length; j++)
                display[i][j] = '-';

        }

    }

    public void ad1(Socket player, String name)
        throws IOException
    {
        name1 = name;
        in1 = new BufferedReader(new InputStreamReader(player.getInputStream(), "BIG5"));
        out1 = new PrintWriter(new OutputStreamWriter(player.getOutputStream(), "BIG5"));
    }

    public void ad2(Socket player, String name)
        throws IOException
    {
        name2 = name;
        in2 = new BufferedReader(new InputStreamReader(player.getInputStream(), "BIG5"));
        out2 = new PrintWriter(new OutputStreamWriter(player.getOutputStream(), "BIG5"));
    }

    public void start()
        throws IOException
    {
label0:
        for(int count = 0; winnner(display).equals("nobody") && count != 9; count++)
        {
            newdisplay(out1, out2);
            if(count % 2 == 0)
            {
                out1.println("\u63DB\u4F60\u4E86!");
                out1.flush();
                out2.println("\u7B49\u5F85\u5C0D\u65B9");
                out2.flush();
                do
                {
                    if(!winnner(display).equals("nobody"))
                        continue label0;
                    System.out.println("a");
                    String s = in1.readLine();
                    if(!s.matches("[0-9][0-9]"))
                    {
                        out1.println("\u8F38\u5165\u932F\u8AA4!");
                        out1.flush();
                    } else
                    {
                        int x = s.charAt(0) - 48;
                        int y = s.charAt(1) - 48;
                        if(x > 2 || y > 2)
                        {
                            out1.println("\u8D85\u51FA\u7BC4\u570D\u5594!");
                        } else
                        {
                            if(display[x][y] == '-')
                            {
                                display[x][y] = 'O';
                                continue label0;
                            }
                            out1.println("\u9019\u88E1\u4E0B\u904E\u4E86!");
                        }
                        out1.flush();
                    }
                } while(true);
            }
            out2.println("\u63DB\u4F60\u4E86!");
            out2.flush();
            out1.println("\u7B49\u5F85\u5C0D\u65B9");
            out1.flush();
            do
            {
                if(!winnner(display).equals("nobody"))
                    continue label0;
                System.out.println("b");
                String s = in2.readLine();
                if(!s.matches("[0-9][0-9]"))
                {
                    out2.println("\u8F38\u5165\u932F\u8AA4!");
                    out2.flush();
                } else
                {
                    int x = s.charAt(0) - 48;
                    int y = s.charAt(1) - 48;
                    if(x > 2 || y > 2)
                    {
                        out2.println("\u8D85\u51FA\u7BC4\u570D\u5594!");
                    } else
                    {
                        if(display[x][y] == '-')
                        {
                            display[x][y] = 'X';
                            continue label0;
                        }
                        out2.println("\u9019\u88E1\u4E0B\u904E\u4E86!");
                    }
                    out2.flush();
                }
            } while(true);
        }

        newdisplay(out1, out2);
        String winner = winnner(display);
        out1.println((new StringBuilder()).append("Winner is ").append(winner).append("!!").toString());
        out1.println("\u8F38\u5165/ss \u627E\u4EBA\u5C0D\u6230");
        out1.flush();
        out2.println((new StringBuilder()).append("Winner is ").append(winner).append("!!").toString());
        out2.println("\u8F38\u5165/ss \u627E\u4EBA\u5C0D\u6230");
        out2.flush();
    }

    public String winnner(char display[][])
    {
        String result = "nobody";
        if(display[0][0] == 'O' && display[1][0] == 'O' && display[2][0] == 'O' || display[0][1] == 'O' && display[1][1] == 'O' && display[2][1] == 'O' || display[0][2] == 'O' && display[1][2] == 'O' && display[2][2] == 'O' || display[0][0] == 'O' && display[0][1] == 'O' && display[0][2] == 'O' || display[1][0] == 'O' && display[1][1] == 'O' && display[1][2] == 'O' || display[2][0] == 'O' && display[2][1] == 'O' && display[2][2] == 'O' || display[0][0] == 'O' && display[1][1] == 'O' && display[2][2] == 'O' || display[2][0] == 'O' && display[1][1] == 'O' && display[0][2] == 'O')
            result = "play1";
        if(display[0][0] == 'X' && display[1][0] == 'X' && display[2][0] == 'X' || display[0][1] == 'X' && display[1][1] == 'X' && display[2][1] == 'X' || display[0][2] == 'X' && display[1][2] == 'X' && display[2][2] == 'X' || display[0][0] == 'X' && display[0][1] == 'X' && display[0][2] == 'X' || display[1][0] == 'X' && display[1][1] == 'X' && display[1][2] == 'X' || display[2][0] == 'X' && display[2][1] == 'X' && display[2][2] == 'X' || display[0][0] == 'X' && display[1][1] == 'X' && display[2][2] == 'X' || display[2][0] == 'X' && display[1][1] == 'X' && display[0][2] == 'X')
            result = "play2";
        return result;
    }

    public void newdisplay(PrintWriter out1, PrintWriter out2)
    {
        out1.print("\033[H\033[2J");
        out1.println((new StringBuilder()).append("\u66B1\u7A31 ").append(name1).append(" \u76EE\u524D\u4F4D\u5728 \u5927\u5EF3>\u904A\u6232\u5BA4").toString());
        out1.println("\u5708\u5708\u53C9\u53C9\u73A9\u6CD5: \u8F38\u5165\u5EA7\u6A19\u5360\u9818\u4F4D\u7F6E \u5148\u4E09\u500B\u9023\u6210\u4E00\u76F4\u7DDA\u8005\u7372\u52DD(\u6578\u5B57\u9806\u5E8F\u5DE6\u4E0A)");
        out1.println();
        out1.println("     y");
        out1.println("   0 1 2");
        out2.print("\033[H\033[2J");
        out2.println((new StringBuilder()).append("\u66B1\u7A31 ").append(name2).append(" \u76EE\u524D\u4F4D\u5728 \u5927\u5EF3>\u904A\u6232\u5BA4").toString());
        out2.println("\u5708\u5708\u53C9\u53C9\u73A9\u6CD5: \u8F38\u5165\u5EA7\u6A19\u5360\u9818\u4F4D\u7F6E \u5148\u4E09\u500B\u9023\u6210\u4E00\u76F4\u7DDA\u8005\u7372\u52DD(\u6578\u5B57\u9806\u5E8F\u5DE6\u4E0A)");
        out2.println();
        out2.println("     y");
        out2.println("   0 1 2");
        out1.flush();
        out2.flush();
        for(int i = 0; i < display.length; i++)
        {
            if(i == 1)
                out1.print((new StringBuilder()).append("x").append(i).append(" ").toString());
            else
                out1.print((new StringBuilder()).append(" ").append(i).append(" ").toString());
            for(int j = 0; j < display[i].length; j++)
                out1.print((new StringBuilder()).append(display[i][j]).append(" ").toString());

            out1.println();
        }

        out1.println();
        out1.flush();
        for(int i = 0; i < display.length; i++)
        {
            if(i == 1)
                out2.print((new StringBuilder()).append("x").append(i).append(" ").toString());
            else
                out2.print((new StringBuilder()).append(" ").append(i).append(" ").toString());
            for(int j = 0; j < display[i].length; j++)
                out2.print((new StringBuilder()).append(display[i][j]).append(" ").toString());

            out2.println();
        }

        out2.println();
        out2.flush();
    }

    BufferedReader in1;
    PrintWriter out1;
    BufferedReader in2;
    PrintWriter out2;
    char display[][];
    String name1;
    String name2;
}

