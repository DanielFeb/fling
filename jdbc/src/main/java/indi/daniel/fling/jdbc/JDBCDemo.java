package indi.daniel.fling.jdbc;

import java.sql.*;

import java.net.URL;
import java.net.URLClassLoader;


public class JDBCDemo {

    public static void main(String[] args) {

        Connection c = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fling?characterEncoding=UTF-8", "root",
                    "Xqf123123");
            // transaction start
            c.setAutoCommit(false);
//            insertByStatement(c);
//            insertByPreparedStatement(c);
            query(c);
//            showDBMetaData(c);

            // transaction commit
            c.commit();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 数据库的连接时有限资源，相关操作结束后，养成关闭数据库的好习惯
            // 后关闭Connection
            if (c != null)
                try {
                    c.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

        }

    }

    public static void showDBMetaData(Connection c) {
        // 查看数据库层面的元数据
        // 即数据库服务器版本，驱动版本，都有哪些数据库等等

        DatabaseMetaData dbmd = null;
        try {
            dbmd = c.getMetaData();
            // 获取数据库服务器产品名称
            System.out.println("数据库产品名称:\t"+dbmd.getDatabaseProductName());
            // 获取数据库服务器产品版本号
            System.out.println("数据库产品版本:\t"+dbmd.getDatabaseProductVersion());
            // 获取数据库服务器用作类别和表名之间的分隔符 如test.user
            System.out.println("数据库和表分隔符:\t"+dbmd.getCatalogSeparator());
            // 获取驱动版本
            System.out.println("驱动版本:\t"+dbmd.getDriverVersion());

            System.out.println("可用的数据库列表：");
            // 获取数据库名称
            ResultSet rs = dbmd.getCatalogs();

            while (rs.next()) {
                System.out.println("数据库名称:\t"+rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void query(Connection c) {
        PreparedStatement ps = null;
        try {
            String sql = "select * from hero where hero.name=?";
            ps = c.prepareStatement(sql);
//            ps.setString(1,"大盖伦 or 1=1"); // 防止注入
            ps.setString(1,"诺手");
            // 执行查询语句，并把结果集返回给ResultSet
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");// 可以使用字段名
                String name = rs.getString(2);// 也可以使用字段的顺序
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    // 防止 sql 注入
    public static void insertByPreparedStatement(Connection c) {

        PreparedStatement ps = null;
        try {
            String sql = "insert into hero values(null,?,?,?)";
            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Get GeneratedKey
//            ps = c.prepareStatement(sql);

            ps.setString(1, "诺手");
            ps.setFloat(2, 500.0f);
            ps.setInt(3, 60);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println(id);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 先关闭PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertByStatement(Connection c) {

        Statement s = null;
        try {
            s = c.createStatement();

            String sql = "insert into hero values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";

            s.execute(sql);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 先关闭Statement
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void showClassPath() {

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }

    }
}