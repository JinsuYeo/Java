package spms.dao;

// 애노테이션 적용 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Member;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
  SqlSessionFactory sqlSessionFactory;
	
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Member> selectList(HashMap<String, Object> paramMap) throws Exception {
	SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      return sqlSession.selectList("spms.dao.MemberDao.selectList", paramMap);

    } finally {
      sqlSession.close();
    }
  }

  public int insert(Member member) throws Exception  {
    SqlSession sqlSession = sqlSessionFactory.openSession(); 
    try {
      int count = sqlSession.insert("spms.dao.MemberDao.insert", member);
      sqlSession.commit();
      return count;
    } finally {
      sqlSession.close();
    }
  }

  public int delete(int no) throws Exception {  
	  SqlSession sqlSession = sqlSessionFactory.openSession(); 
    try {
      int count = sqlSession.delete("spms.dao.MemberDao.delete", no);
      sqlSession.commit();
      return count;
    } finally {
      sqlSession.close();
    }
  }

  public Member selectOne(int no) throws Exception { 
	SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      return sqlSession.selectOne("spms.dao.MemberDao.selectOne", no);
    } finally {
      sqlSession.close();
    }
  }

  public int update(Member member) throws Exception { 
	SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Member origin = sqlSession.selectOne("spms.dao.MemberDao.selectOne", member.getNo());
    	
      Hashtable<String, Object> paramMap = new Hashtable<>();
      
      if(!member.getName().equals(origin.getName())) {
    	  paramMap.put("name", member.getName());
      }
      if(!member.getEmail().equals(origin.getEmail())) {
    	  paramMap.put("email", member.getEmail());
      }
      
      if(paramMap.size() > 0) {
    	  int count = sqlSession.update("spms.dao.MemberDao.update", member);
          sqlSession.commit();
          return count;
      } else {
    	  return 0;
      }
     
    } finally {
        sqlSession.close();
      }
  }
  
  public Member exist(String email, String password) throws Exception {
	HashMap<String, String> paramMap = new HashMap<>();
	paramMap.put("email", email);
	paramMap.put("password", password);
	  
	SqlSession sqlSession = sqlSessionFactory.openSession(); 
    try {
      return sqlSession.selectOne("spms.dao.MemberDao.exist", paramMap);
    } finally {
        sqlSession.close();
      }
  }

}
