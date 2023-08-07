package qna.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.MyQnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class AllQnaWriteController
 */
@WebServlet("/qna/write.do")
public class AllQnaWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllQnaWriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/qna/allQnaWrite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MyQnaService service = new MyQnaService();
		String myQnaTitle = request.getParameter("qnaList");
		String myQnaContent = request.getParameter("content");
		String myQnaWriter = request.getParameter("writer");
		String myQnaPw = request.getParameter("password");
		Qna qna = new Qna(myQnaTitle, myQnaContent, myQnaWriter, myQnaPw);
		int result = service.insertQna(qna);
		response.setContentType("text/html; charset=UTF-8");
		if(result > 0) {
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('문의가 등록되었습니다.'); location.href='/qna/list.do';</script>");
			writer.close();
		} else {
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('문의 등록을 실패하였습니다.'); history.back();</script>");
			writer.close();
		}
	}

}
