package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

import spms.dao.MySqlMemberDao;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	try {
		ServletContext sc = this.getServletContext();

		MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
		
		memberDao.delete(Integer.parseInt(request.getParameter("no")));

//		response.sendRedirect("list");
		request.setAttribute("viewUrl", "redirect:list.do");
	} catch(Exception e) {
		throw new ServletException(e);
	} finally {
		try {if(rs != null) rs.close();} catch(Exception e) {}
		try {if(stmt != null) stmt.close();} catch(Exception e) {}
	}
		
	}
}