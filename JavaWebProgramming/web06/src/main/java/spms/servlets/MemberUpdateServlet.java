package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));

//			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdate.jsp");
//			rd.forward(request, response);
			request.setAttribute("viewUrl", "/member/MemberUpdate.jsp");
			
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
//			Member member = new Member()
//					.setEmail(request.getParameter("email"))
//					.setName(request.getParameter("name"))
//					.setNo(Integer.parseInt(request.getParameter("no")));
			Member member = (Member)request.getAttribute("member");
					
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			memberDao.update(member);
		
//			response.sendRedirect("list");
			request.setAttribute("viewUrl", "redirect:list.do");
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
	}

}
