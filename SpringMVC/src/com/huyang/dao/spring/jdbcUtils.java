package com.huyang.dao.spring;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class jdbcUtils {
	
	
	

	    public static Connection getNewConnection() throws SQLException, ClassNotFoundException  {
	        //Ҫ���ӵ����ݿ�URL
	        String url = "jdbc:mysql://localhost:3306/Market";
	        //���ӵ����ݿ�ʱʹ�õ��û���
	        String username = "root";
	        //���ӵ����ݿ�ʱʹ�õ�����
	        String password = "19941206";
	       
	        //1.��������
	        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());���Ƽ�ʹ�����ַ�ʽ����������
	        Class.forName("com.mysql.jdbc.Driver");//�Ƽ�ʹ�����ַ�ʽ����������
	        //2.��ȡ�����ݿ������
	        Connection conn = DriverManager.getConnection(url, username, password);
	        return conn;
	        
//	        //3.��ȡ���������ݿⷢ��sql����statement
//	        Statement st = conn.createStatement();
//	        
//	        String sql = "select shopID,shopName from shopInformation";
//	        //4.�����ݿⷢsql,����ȡ����������resultset
//	        ResultSet rs = st.executeQuery(sql);
//	        
//	        //5.ȡ�������������
//	        while(rs.next()){
//	            System.out.println("id=" + rs.getObject("shopID"));
//	            System.out.println("name=" + rs.getObject("shopName"));
//	            
//	        }
//	        
//	        //6.�ر����ӣ��ͷ���Դ
//	        rs.close();
//	        st.close();
//	        conn.close();
	    }
	

}
