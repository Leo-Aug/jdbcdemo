package com.ssdut.liuyang_jdbcdemo;

/**
 * Hello world!
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsqlConnectionTool {
    private String url = "jdbc:postgresql://lese.rocks:5432/classwork";
    private String username = "postgres";
    private String password = "613613";
    private Connection connection = null;

    public Connection getConn() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    public ResultSet query(Connection conn, String sql) {
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {
            pStatement = conn.prepareStatement(sql);
            rs = pStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return rs;
    }

    public boolean queryUpdate(Connection conn, String sql) {
        PreparedStatement pStatement = null;
        int rs = 0;
        try {
            pStatement = conn.prepareStatement(sql);
            rs = pStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (rs > 0) {
            return true;
        }
        return false;
    }



    public static void main(String[] args) throws SQLException {
        PsqlConnectionTool pgtool = new PsqlConnectionTool();
        Connection myconn = pgtool.getConn();
        
        ResultSet rs = pgtool.query(myconn, "select * from dept");
        while(rs.next()){                       
            int id = rs.getInt("deptno");       
            String name = rs.getString("dname");
            System.out.println("id:"+id+" 姓名："+name);
            myconn.close();
        }
    }
}