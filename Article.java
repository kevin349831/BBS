package sockettest6;

import java.io.FileInputStream;
import java.io.IOException;

public class Article
{

    public Article(String filepath)
        throws IOException
    {
        FileInputStream fi = new FileInputStream(filepath);
        byte ba[] = new byte[fi.available()];
        fi.read(ba);
        String str = new String(ba, "biG5");
        String s[] = str.split("\r\n", 4);
        title = s[0].substring(6);
        auther = s[1].substring(7);
        content = s[3].substring(8);
        fi.close();
    }

    public String title;
    public String auther;
    public String content;
}
