package ado.modal;

public class WorkItem {
	
	Integer id;
	String title;
	String creationDate;
	String areaPath;
	String type;
	String status;
	String createdBy;
	Integer priority;
	Integer noOfTCsBlocked;
	String assignedToTeam;
	
	@Override
	public String toString() {
		return "<b>WorkItem</b>\n"
			+ "<b>id : </b>" + id + "\n"
			+ "<b>title : </b>" + title + "\n"
			+ "<b>creationDate : </b>" + creationDate + "\n"
			+ "<b>areaPath : </b>" + areaPath + "\n"
			+ "<b>type : </b>" + type + "\n"
			+ "<b>status : </b>" + status + "\n"
			+ "<b>createdBy : </b>" + createdBy + "\n"
			+ "<b>priority : </b>" + priority + "\n"
			+ "<b>noOfTCsBlocked : </b>" + noOfTCsBlocked + "\n"
			+ "<b>assignedToTeam : </b>" + assignedToTeam + "\n"
			;
	}
	
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

	public String getAreaPath() {
		return areaPath;
	}

	public void setAreaPath(String areaPath) {
		this.areaPath = areaPath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getNoOfTCsBlocked() {
		return noOfTCsBlocked;
	}

	public void setNoOfTCsBlocked(Integer noOfTCsBlocked) {
		this.noOfTCsBlocked = noOfTCsBlocked;
	}

	public String getAssignedToTeam() {
		return assignedToTeam;
	}

	public void setAssignedToTeam(String assignedToTeam) {
		this.assignedToTeam = assignedToTeam;
	}

}
