package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MySqlProjectDao;
import spms.vo.Project;
import spms.annotation.Component;

@Component("/project/add.do")
public class ProjectAddController implements Controller, DataBinding {
	MySqlProjectDao projectDao;
	
	public ProjectAddController setProjectDao(MySqlProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"project", spms.vo.Project.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project)model.get("project");
		if(project.getTitle() == null) {
			return "/project/ProjectForm.jsp";
		} else {
			projectDao.insert(project);
			return "redirect:list.do";	
		}
	}

}
