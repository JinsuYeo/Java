package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<html><head><title>회원 등록</title></head>");
//		out.println("<body><h1>회원 등록</h1>");
//		out.println("<form action='add' method='post'>");
//		out.println("이름: <input type='text' name='name'><br>");
//		out.println("이메일: <input type='text' name='email'><br>");
//		out.println("암호: <input type='password' name='password'><br>");
//		out.println("<input type='submit' value='추가'>");
//		out.println("<input type='reset' value='취소'>");
//		out.println("</form>");
//		out.println("</body></html>");
		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberAdd.jsp");
		rd.include(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//						sc.getInitParameter("url"),
//						sc.getInitParameter("username"),
//						sc.getInitParameter("password")
//					);
//			conn = (Connection)sc.getAttribute("conn");
			
			Member member = new Member()
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password"))
					.setName(request.getParameter("name"));
			
//			MemberDao memberDao = new MemberDao();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			
			memberDao.insert(member);
		
			response.sendRedirect("list");
		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
////		out.println("<html><head><title>회원등록결과</title></head>");
//		out.println("<html><head><title>회원등록결과</title>");
//		out.println("<meta http-equiv='Refresh' content='1; url=list'></head>");
//		out.println("<body>");
//		out.println("<p>등록 성공입니다!</p>");
//		out.println("</body></html>");
		
//		response.addHeader("Refresh", "1;url=list");
		} catch(Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
//			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

}
