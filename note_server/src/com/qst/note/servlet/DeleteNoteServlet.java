package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.dao.NoteDao;
import com.qst.note.result.Result;

/**
 * ���ڴ����û�ɾ��ָ��������¼������
 * 
 * @author 65725
 *
 */
@WebServlet("/DeleteNoteServlet")
public class DeleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteNoteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); // ���ñ����ʽ

		int id = Integer.parseInt(request.getParameter("id")); // ��ȡ�������id,����idת����int����
		NoteDao dao = new NoteDao();
		Result rs = new Result();
		rs.isSuccess = dao.deleteById(id);
		rs.msg = rs.isSuccess ? "ɾ���ɹ�" : "ɾ��ʧ��";
		response.getWriter().append(new Gson().toJson(rs));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
