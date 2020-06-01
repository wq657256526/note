package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.dao.UserDao;
import com.qst.note.result.Result;

/**
 * ÓÃÓÚ´¦ÀíAPP¶ËµÇÂ¼Âß¼­
 * @author 65725
 *
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String tel = request.getParameter("tel");
		String pass = request.getParameter("pass");
		
		UserDao userDao = new UserDao();
		Result rs = new Result();
		Gson gson = new Gson();
		rs.isSuccess = userDao.login(tel, pass);
		rs.msg = rs.isSuccess?"µÇÂ¼³É¹¦":"µÇÂ¼Ê§°Ü£¬ÇëÖØÐÂµÇÂ¼£¡";
		response.getWriter().append(gson.toJson(rs));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
