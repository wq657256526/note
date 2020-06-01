package com.qst.note.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.NoteBean;
import com.qst.note.dao.NoteDao;
/**
 * 处理用户获取所有备忘记录的请求
 * @author 65725
 *
 */
@WebServlet("/GetAllNoteServlet")
public class GetAllNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllNoteServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); //设置编码格式
		
		String tel = request.getParameter("tel"); //获取请求参数tel
		ArrayList<NoteBean> all = new NoteDao().getAllNotes(tel);
		response.getWriter().append(new Gson().toJson(all)); //将集合转换为Json字符串
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
