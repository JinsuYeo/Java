package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	DataSource ds;
	
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
										  .setStartDate(rs.getDate("STA_DATE"))
										  .setEndDate(rs.getDate("END_DATE"))
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
			
			stmt.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
		
		return 0;
	}

}
