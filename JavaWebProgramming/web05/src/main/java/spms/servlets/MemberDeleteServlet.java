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

import spms.dao.MemberDao;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	try {
		ServletContext sc = this.getServletContext();
//		Class.forName(sc.getInitParameter("driver"));
//		conn = DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")
//				);
//		conn = (Connection)sc.getAttribute("conn");
//		MemberDao memberDao = new MemberDao();
		MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//		memberDao.setConnection(conn);
		
		memberDao.delete(Integer.parseInt(request.getParameter("no")));
//		stmt = conn.prepareStatement("DELETE FROM MEMBERS"
//					+ " WHERE MNO=" + request.getParameter("no"));
//		stmt.executeUpdate();

		response.sendRedirect("list");
	} catch(Exception e) {
		throw new ServletException(e);
	} finally {
		try {if(rs != null) rs.close();} catch(Exception e) {}
		try {if(stmt != null) stmt.close();} catch(Exception e) {}
//		try {if(conn != null) conn.close();} catch(Exception e) {}
	}
		
	}
}