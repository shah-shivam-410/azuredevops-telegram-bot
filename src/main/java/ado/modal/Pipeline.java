package ado.modal;

public class Pipeline {
	
	Integer id;
	String name;
	String state;
	String folder;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	@Override
	public String toString() {
		return  "<b>id : </b>" + id + "\n"
				+ "<b>name : </b>" + name + "\n"
				+ "<b>state : </b>" + state + "\n"
				+ "<b>folder : </b>" + folder + "\n"
				;
	}
	

}
