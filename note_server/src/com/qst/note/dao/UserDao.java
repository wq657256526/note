package com.qst.note.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qst.note.bean.UserBean;
import com.qst.note.util.DBUtil;

public class UserDao {

	// 向user表中插入一条数据（注册）
	public boolean regist(UserBean user) {
		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		try {
			String sql = "insert into user(name,pass,tel,qq,wechat)values(?,?,?,?,?)";

			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getName());
			ps.setString(2, user.getPass());
			ps.setString(3, user.getTel());
			ps.setString(4, user.getQq());
			ps.setString(5, user.getWechat());

			boolean result = ps.execute();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.close(null, ps, conn);
		}
	}

	// 登录
	public boolean login(String tel, String pass) {
		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		try {
			String sql = "select pass from user where tel=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tel);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				String passInDb = rs.getString("pass");
				if (passInDb.equals(pass))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, ps, conn);
		}
		return false;
	}

	// 根据电话返回ID
	public int getIDbyTel(String tel) {
		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		int id = 0;
		try {

			String spl = "select id from user where tel=?";
			ps = conn.prepareStatement(spl);
			ps.setString(1, tel);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, ps, conn);
		}
		return id;
	}

	

}
