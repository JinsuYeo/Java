package spms.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import spms.dao.MemberDao;
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
			
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
			
//			connPool = new DBConnectionPool(
//					sc.getInitParameter("driver"),
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
			
//			ds = new BasicDataSource();
//			ds.setDriverClassName(sc.getInitParameter("driver"));
//			ds.setUrl(sc.getInitParameter("url"));
//			ds.setUsername(sc.getInitParameter("username"));
//			ds.setPassword(sc.getInitParameter("password"));
			
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb");
			
			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
//			memberDao.setConnectionPool(connPool);
			memberDao.setDataSource(ds);
			
			sc.setAttribute("memberDao", memberDao);
			
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
