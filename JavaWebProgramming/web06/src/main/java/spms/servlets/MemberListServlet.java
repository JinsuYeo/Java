package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;
import spms.dao.MemberDao;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
//				);
//			conn = (Connection)sc.getAttribute("conn");
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//					"select MNO,MNAME,EMAIL,CRE_DATE" + 
//					" from MEMBERS" + 
//					" order by MNO ASC");
//			
//			res.setContentType("text/html; charset=UTF-8");
//			ArrayList<Member> members = new ArrayList<>();
//			
//			while(rs.next()) {
//				members.add(new Member()	
//											.setNo(rs.getInt("MNO"))
//											.setName(rs.getString("MNAME"))
//											.setEmail(rs.getString("EMAIL"))
//											.setCreatedDate(rs.getDate("CRE_DATE")));
//			}
			
//			req.setAttribute("members", members);
			
//			conn = (Connection)sc.getAttribute("conn");
			
//			MemberDao memberDao = new MemberDao();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
//			memberDao.setConnection(conn);
			
			req.setAttribute("members", memberDao.selectList());
			
			req.setAttribute("viewUrl", "/member/MemberList.jsp");
			
//			res.setContentType("text/html; charset=UTF-8");
			
//			RequestDispatcher rd = req.getRequestDispatcher(
//						"/member/MemberList.jsp");
//			rd.include(req, res);
		} catch(Exception e) {
			throw new ServletException(e);
//			req.setAttribute("error", e);
//			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
//			rd.forward(req, res);
		} finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
//			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}
}
