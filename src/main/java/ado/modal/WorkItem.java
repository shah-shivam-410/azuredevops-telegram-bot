package ado.modal;

public class WorkItem {
	
	Integer id;
	String title;
	String creationDate;
	String type;
	String createdBy;
	Integer priority;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "<b>WorkItem</b>\n"
				+ "<b>id : </b>" + id + "\n"
				+ "<b>title : </b>" + title + "\n"
				+ "<b>creationDate : </b>" + creationDate + "\n"
				+ "<b>type : </b>" + type + "\n"
				+ "<b>createdBy : </b>" + createdBy + "\n"
				+ "<b>priority : </b>" + priority + "\n"
				;
	}
	
	

}
