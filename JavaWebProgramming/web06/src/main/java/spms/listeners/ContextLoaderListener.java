package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import spms.controls.*;
import spms.dao.MySqlMemberDao;
import spms.util.DBConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@WebListener
public class ContextLoaderListener implements ServletContextListener {
//	Connection conn;
//	DBConnectionPool connPool;
	BasicDataSource ds;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb");
			
			MySqlMemberDao memberDao = new MySqlMemberDao(); 
			memberDao.setDataSource(ds);
			
//			sc.setAttribute("memberDao", memberDao);
			
			sc.setAttribute("/auth/login.do", new LoginController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogOutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
//		try {
//			conn.close();
//		} catch(Exception e) {}
//		connPool.closeAll();
//		try {if(ds != null) ds.close();} catch(SQLException e) {}
	}
}