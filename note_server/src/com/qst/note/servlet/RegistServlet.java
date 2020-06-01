package com.qst.note.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qst.note.bean.UserBean;
import com.qst.note.dao.UserDao;
import com.qst.note.result.Result;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserBean user = new UserBean();
		user.setName(request.getParameter("name"));
		user.setPass(request.getParameter("pass"));
		user.setTel(request.getParameter("tel"));
		
		UserDao userDao = new UserDao();
		
		Boolean flag = userDao.regist(user);
		
		Result r = new Result();
		r.isSuccess = flag;
		//
		if (flag) {
			r.msg = "¹§Ï²Äú×¢²á³É¹¦£¡";
		}else {
			r.msg = "×¢²áÊ§°Ü£¬¸ÃÊÖ»úºÅÒÑ±»×¢²á£¡";
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(r); 
		
		response.getWriter().append(result);
	}

}
