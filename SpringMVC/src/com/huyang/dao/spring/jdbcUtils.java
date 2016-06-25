package com.huyang.dao.spring;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class jdbcUtils {
	
	
	

	    public static Connection getNewConnection() throws SQLException, ClassNotFoundException  {
	        //要连接的数据库URL
	        String url = "jdbc:mysql://localhost:3306/Market";
	        //连接的数据库时使用的用户名
	        String username = "root";
	        //连接的数据库时使用的密码
	        String password = "19941206";
	       
	        //1.加载驱动
	        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());不推荐使用这种方式来加载驱动
	        Class.forName("com.mysql.jdbc.Driver");//推荐使用这种方式来加载驱动
	        //2.获取与数据库的链接
	        Connection conn = DriverManager.getConnection(url, username, password);
	        return conn;
	        
//	        //3.获取用于向数据库发送sql语句的statement
//	        Statement st = conn.createStatement();
//	        
//	        String sql = "select shopID,shopName from shopInformation";
//	        //4.向数据库发sql,并获取代表结果集的resultset
//	        ResultSet rs = st.executeQuery(sql);
//	        
//	        //5.取出结果集的数据
//	        while(rs.next()){
//	            System.out.println("id=" + rs.getObject("shopID"));
//	            System.out.println("name=" + rs.getObject("shopName"));
//	            
//	        }
//	        
//	        //6.关闭链接，释放资源
//	        rs.close();
//	        st.close();
//	        conn.close();
	    }
	

}
