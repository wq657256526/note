package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;

/**
 * 用于处理客户端根据id获得一条note的请求
 * @author 65725
 *
 */
@WebServlet("/GetNoteServlet")
public class GetNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetNoteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); //设置编码格式
		int id = Integer.valueOf(request.getParameter("id")); //将获取的参数id转换成int类型
		
		NoteDao noteDao = new NoteDao();
		NoteBean noteBean = noteDao.getNoteByID(id);
		
		Gson gson = new Gson();
		
		response.getWriter().append(gson.toJson(noteBean));

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
