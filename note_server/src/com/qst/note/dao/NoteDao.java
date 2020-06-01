package com.qst.note.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;
import com.qst.note.bean.NoteBean;
import com.qst.note.util.DBUtil;

public class NoteDao {
	// ���note_table����һ������
	public Boolean insert(String title, String content, String noteTime, String tel) {
		int id = new UserDao().getIDbyTel(tel); // �����û��绰��ȡ�û�id
		if (id < 1) // ����û�idС��1��ʾ�û������ڣ�����false
			return false;
		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String nowTime = sdf.format(new Date()); // ��ȡ��ǰʱ��
		try {

			String sql = "insert into note_table(title,content,note_time,user_id,create_time)values(?,?,?,?,?)";
			ps = (PreparedStatement) conn.prepareStatement(sql);

			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, noteTime);
			ps.setInt(4, id);
			ps.setString(5, nowTime);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(null, ps, conn);

		}
	}

	// ����id��ѯ���ݿⲢ����һ��note
	public NoteBean getNoteByID(int id) {
		NoteBean note = new NoteBean();
		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from note_table where id=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.first()) {
				note.setId(id);
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return note;
	}

	// ����Id�޸�һ��������¼
	public boolean ModifyNote(int id, String title, String content, String noteTime) {
		if (id < 1) // ����û�idС��1��ʾ�û������ڣ�����false
			return false;
		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String nowTime = sdf.format(new Date()); // ��ȡ��ǰʱ��
		try {
			String sql = "update note_table set title=?,content=?,note_time=?,update_time=? where id=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, noteTime);
			ps.setString(4, nowTime);
			ps.setInt(5, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(null, ps, conn);
		}
	}

	// ���ݵ绰���룬�����û������б�����¼
	public ArrayList<NoteBean> getAllNotes(String tel) {
		ArrayList<NoteBean> all = new ArrayList<NoteBean>();

		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		int id = new UserDao().getIDbyTel(tel); // �����û�����ȡ�û�id
		try {
			String sql = "select * from note_table where user_id=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				NoteBean note = new NoteBean();
				note.setId(rs.getInt("id"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getInt("user_id"));
				all.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return all;
	}

	// ����idɾ��һ��������¼��ɾ���ɹ�����true��ʧ�ܷ���false
	public boolean deleteById(int id) {
		Connection conn = DBUtil.getMySQLConn();
		PreparedStatement ps = null;
		try {
			String sql = "delete from note_table where id=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.close(null, ps, conn);
		}
	}
}
