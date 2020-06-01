package com.qst.note.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;




public class DBUtil {
	static Properties pros = null; // 可以帮助读取和处理在源文件中的信息

	static{ //加载JDBCUtil类 时被调用 
		pros = new Properties();
		
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getMySQLConn() {

		try {
			Class.forName(pros.getProperty("mysqlDriver"));
			Connection conn = DriverManager.getConnection(pros.getProperty("mysqlUrl"), pros.getProperty("mysqlUser"), pros.getProperty("mysqlPwd"));
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	//释放资源
	public static void close(ResultSet rs, Statement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 关闭ps
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 关闭conn
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//插入一条数据，测试数据库是否连接成功
//	public static void main(String[] args) {
//		Connection conn = getMySQLConn();
//		
//		String sql = "insert into user (name,pass) values (?,?)"; //? 占位符
//		
//		PreparedStatement ps;
//		try {
//			
//			ps = conn.prepareStatement(sql);
//			ps.setObject(1, "zhangsan");
//			ps.setObject(2, "123");
//			ps.execute();
//			
//			close(null, ps, conn);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
