package spms.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Project {
	protected int no;
	protected String title;
	protected String content;
	protected Date startDate;
	protected Date endDate;
	protected int state;
	protected Date createdDate;
	protected String tags;
	
	public int getNo() {
		return no;
	}
	
	public Project setNo(int no) {
		this.no = no;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Project setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	
	public Project setContent(String content) {
		this.content = content;
		return this;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Project setStartDate(String startDate) throws Exception {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		this.startDate = sm.parse(startDate);
		return this;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public Project setEndDate(String endDate)throws Exception {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		this.endDate = sm.parse(endDate);
		return this;
	}
	
	public int getState() {
		return state;
	}
	
	public Project setState(int state) {
		this.state = state;
		return this;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Project setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	
	public String getTags() {
		return tags;
	}
	
	public Project setTags(String tags) {
		this.tags = tags;
		return this;
	}	
}
