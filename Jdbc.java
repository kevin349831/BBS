

package sockettest6;

import java.io.PrintStream;
import java.sql.*;

public class Jdbc
{

    public Jdbc()
    {
        stmt = null;
        rs2 = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db.mis.kuas.edu.tw/s1103137222?user=s1103137222&password=haoyu");
            aStatement = conn.createStatement();
        }
        catch(Exception exception) { }
    }

    public ResultSet Add(String sql)
        throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db.mis.kuas.edu.tw/s1103137222?user=s1103137222&password=haoyu");
            aStatement = conn.createStatement();
            aStatement.executeUpdate(sql);
            System.out.println("\u65B0\u589E\u6210\u529F");
        }
        catch(SQLException sqlexception) { }
        return rs2;
    }

    public ResultSet Query(String sql)
    {
        try
        {
            rs = aStatement.executeQuery(sql);
            System.out.println("\u641C\u5C0B\u6210\u529F");
        }
        catch(SQLException sqlexception) { }
        return rs;
    }

    public Connection conn;
    public Statement aStatement;
    public ResultSet rs;
    public Statement stmt;
    public ResultSet rs2;
}

