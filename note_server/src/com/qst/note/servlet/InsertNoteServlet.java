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
 * ���ڴ���ͻ�������һ��������¼������
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
		response.setContentType("text/html;charset=utf-8"); //���ñ����ʽ
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tel = request.getParameter("tel");
		String noteTime = request.getParameter("noteTime"); //��ȡ�������
		NoteDao dao = new NoteDao(); //dao��
		Gson gson = new Gson(); //Gson�����࣬���ڽ����������JSON���ݷ��ؿͻ���
		Result result = new Result(); //��������
		result.isSuccess = dao.insert(title, content, noteTime, tel); //����һ�����ݣ�����������浽���result������
		result.msg = result.isSuccess?"��¼�ɹ�":"����ʧ��";
		response.getWriter().append(gson.toJson(result)); //�����������JSON����ͨ����Ӧ���󣬷��ظ��ͻ���

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
