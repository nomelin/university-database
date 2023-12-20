package nomelin;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        Connection con = null;
        PreparedStatement pstat = null;
        Statement stat=null;
        ResultSet rs = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2、获取数据库的连接对象
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/学籍管理系统", "root", "123456");

            //3、定义插入数据的sql语句
            //String sql = "INSERT INTO 专业 (专业号, 专业名) VALUES (?, ?)";
            String sql="SELECT * FROM 专业";
            stat=con.createStatement();
            rs=stat.executeQuery(sql);
            //4、获取 PreparedStatement 对象
            //pstat = con.prepareStatement(sql);

            //5、设置参数值
            //pstat.setString(1, "010308");
            //pstat.setString(2, "物联网工程");

            //6、执行插入操作
            //pstat.executeUpdate();


            //6、处理结果
            while (rs.next()){  //循环一次，游标移动一行
                System.out.println("id：" + rs.getString(1)); //  获取第一列的数据
                System.out.println("name：" + rs.getString("专业名"));  //获取字段为name的数据
                System.out.println("name：" + rs.getString(2));  //获取字段为name的数据

                System.out.println("-------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {   //避免空指针异常
                //7、释放资源
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstat != null) {  //避免空指针异常
                //7、释放资源
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {  //避免空指针异常
                //7、释放资源
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null){  //避免空指针异常
                //7、释放资源
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}