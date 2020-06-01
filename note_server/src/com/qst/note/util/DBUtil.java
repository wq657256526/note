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
	static Properties pros = null; // ���԰�����ȡ�ʹ�����Դ�ļ��е���Ϣ

	static{ //����JDBCUtil�� ʱ������ 
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
	//�ͷ���Դ
	public static void close(ResultSet rs, Statement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �ر�ps
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �ر�conn
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//����һ�����ݣ��������ݿ��Ƿ����ӳɹ�
//	public static void main(String[] args) {
//		Connection conn = getMySQLConn();
//		
//		String sql = "insert into user (name,pass) values (?,?)"; //? ռλ��
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
