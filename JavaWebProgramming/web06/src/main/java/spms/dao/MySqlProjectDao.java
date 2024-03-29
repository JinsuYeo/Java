package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	DataSource ds;
	SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
	
	public void setDataSource(DataSource ds) throws Exception {
		this.ds = ds;
	}
	
	@Override
	public List<Project> selectList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT PNO,PNAME,STA_DATE,END_DATE,STATE"
					+ " FROM PROJECTS"
					+ " ORDER BY PNO DESC");
			
			ArrayList<Project> projects = new ArrayList<>();
			
			while(rs.next()) {
				projects.add(new Project()
										  .setNo(rs.getInt("PNO"))
										  .setTitle(rs.getString("PNAME"))
										  .setStartDate(sm.format(rs.getDate("STA_DATE")))
										  .setEndDate(sm.format(rs.getDate("END_DATE")))
										  .setState(rs.getInt("STATE")));
			}
			return projects;
			
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

	@Override
	public int insert(Project project) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO PROJECTS(PNAME,CONTENT,STA_DATE,END_DATE,STATE,CRE_DATE,TAGS)"
					+ " VALUES (?,?,?,?,0,NOW(),?)");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setString(5, project.getTags());
			
			return stmt.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

	@Override
	public Project selectOne(int no) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT PNAME,CONTENT,STA_DATE,END_DATE,STATE,TAGS FROM PROJECTS"
					+ " WHERE PNO="
					+ no);
			rs.next();
			
			return new Project()
								.setNo(no)
								.setTitle(rs.getString("PNAME"))
								.setContent(rs.getString("CONTENT"))
								.setStartDate(sm.format(rs.getDate("STA_DATE")))
								.setEndDate(sm.format(rs.getDate("END_DATE")))
								.setState(rs.getInt("STATE"))
								.setTags(rs.getString("TAGS"));
			
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	 }

	@Override
	public int update(Project project) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE PROJECTS SET PNAME=?,CONTENT=?,STA_DATE=?,END_DATE=?,STATE=?,TAGS=?"
					+ " WHERE PNO=?");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setInt(5, project.getState());
			stmt.setString(6, project.getTags());
			stmt.setInt(7, project.getNo());
			
			return stmt.executeUpdate();
			
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

	@Override
	public int delete(int no) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"DELETE FROM PROJECTS"
					+ " WHERE PNO=" + no);
			
			return stmt.executeUpdate();
			
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}

}
