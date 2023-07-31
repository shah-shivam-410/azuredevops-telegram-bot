package ado.testManagement;

import java.net.URI;

import org.apache.http.client.utils.URIBuilder;

import ado.modal.WorkItem;
import ado.utils.HttpRequests;

public class WorkItemsDetails {

	public static final String ORG_NAME = "shahshivam410";
	public static final String PROJECT_NAME = "spring-blog-app";

	public WorkItem getWorkItemDetails(String id) throws Exception {
		String s = String.join("/", HttpRequests.BASE_URI, ORG_NAME, PROJECT_NAME, "_apis/wit/workitems");
		URI uri = new URIBuilder(s).addParameter("ids", id).addParameter("api-version", "7.1-preview.3").build();
		//System.out.println(uri);
		HttpRequests httpRequests = new HttpRequests();
		String resp = httpRequests.get(uri);
		return new JsonResponseHandler().parseWorkItemDetails(resp);
	}

	public static void main(String[] args) throws Exception {
		String st = new WorkItemsDetails().getWorkItemDetails("1").toString();
		System.out.println(st);
	}
	
}
