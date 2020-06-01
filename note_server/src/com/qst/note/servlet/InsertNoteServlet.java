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
 * 用于处理客户端新增一条备忘记录的请求
 * @author 65725
 *
 */
@WebServlet("/InsertNoteServlet")
public class InsertNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertNoteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); //设置编码格式
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tel = request.getParameter("tel");
		String noteTime = request.getParameter("noteTime"); //获取请求参数
		NoteDao dao = new NoteDao(); //dao类
		Gson gson = new Gson(); //Gson解析类，用于将结果解析成JSON数据返回客户端
		Result result = new Result(); //请求结果类
		result.isSuccess = dao.insert(title, content, noteTime, tel); //插入一条数据，并将结果保存到结果result对象中
		result.msg = result.isSuccess?"记录成功":"保存失败";
		response.getWriter().append(gson.toJson(result)); //将结果解析成JSON数据通过响应对象，返回给客户端

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
