package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;
import spms.annotation.Component;

@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {
	MySqlMemberDao memberDao;
	
	public LoginController setMemberDao(MySqlMemberDao memberDao){
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {"LoginInfo", spms.vo.Member.class};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
//		MemberDao memberDao = (MemberDao)model.get("memberDao");
		Member loginInfo = (Member)model.get("LoginInfo");
		if(loginInfo.getEmail() == null) {
			return "/auth/LoginForm.jsp";
		} else {
//			Member loginInfo = (Member)model.get("LoginInfo");
			Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());
			
			if(member != null) {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
			} else {
				return "redirect:LoginFail.jsp";
			}
		}
	}

}
