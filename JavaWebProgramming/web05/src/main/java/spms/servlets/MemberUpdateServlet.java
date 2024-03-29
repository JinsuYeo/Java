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

//@WebServlet(
//		urlPatterns={"/member/update"},
//		initParams= {
//				@WebInitParam(name="driver",value="com.mysql.jdbc.Driver"),
//				@WebInitParam(name="url",value="jdbc:mysql://localhost/studydb"),
//				@WebInitParam(name="username",value="root"),
//				@WebInitParam(name="password",value="jinsoo97"),
//		}
//		)
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")
//					);
//			conn = (Connection)sc.getAttribute("conn");
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//						"select MNO,EMAIL,MNAME,CRE_DATE from MEMBERS" +
//						" where MNO=" + request.getParameter("no")
//					);
//			rs.next();
//			
//			Member member = new Member()
//										.setNo(Integer.parseInt(request.getParameter("no")))
//										.setName(rs.getString("MNAME"))
//										.setEmail(rs.getString("EMAIL"))
//										.setCreatedDate(rs.getDate("CRE_DATE"));
//			request.setAttribute("member", member);
			
//			MemberDao memberDao = new MemberDao();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			
//			request.setAttribute("member", member);
			request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));

			
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdate.jsp");
			rd.forward(request, response);
			
			
//			PrintWriter out = response.getWriter();
//			out.println("<html><head><title>회원정보</title></head>");
//			out.println("<body><h1>회원정보</h1>");
//			out.println("<form action='update' method='post'>");
//			out.println("번호: <input type='text' name='no' value='" + request.getParameter("no") + "'readonly><br>");
//			out.println("이름: <input type='text' name='name' value='" + rs.getString("MNAME") + "'><br>");
//			out.println("이메일: <input type='text' name='email' value='" + rs.getString("EMAIL") + "'><br>");
//			out.println("가입일: " + rs.getDate("CRE_DATE") + "<br>");
//			out.println("<input type='submit' value='저장'>");
//			out.println("<input type='button' value='삭제'" + 
//					" onclick='location.href=\"delete?no="  + request.getParameter("no") + "\"'>");
//			out.println("<input type='button' value='취소'" + 
//					" onclick='location.href=\"list\"'>");
//			out.println("</form>");
//			out.println("</body></html>");
			
		} catch(Exception e) {
//			throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
//			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
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
//			stmt = conn.prepareStatement(
//					"UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
//					+ " WHERE MNO=?");
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("name"));
//			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
//			stmt.executeUpdate();
			
			Member member = new Member()
					.setEmail(request.getParameter("email"))
					.setName(request.getParameter("name"))
					.setNo(Integer.parseInt(request.getParameter("no")));
					
//			MemberDao memberDao = new MemberDao();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			
			memberDao.update(member);
		
			response.sendRedirect("list");
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
//			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

}
