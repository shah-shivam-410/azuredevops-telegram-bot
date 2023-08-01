package ado.testManagement;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.utils.URIBuilder;

import ado.modal.WorkItem;
import ado.utils.ConfigReader;
import ado.utils.HttpRequests;

public class WorkItemsDetails {

	public static String ORG_NAME;
	public static String PROJECT_NAME;
	Properties props;
	
	public WorkItemsDetails() throws IOException {
		props = ConfigReader.loadProps();
		ORG_NAME = props.getProperty("ORGNAME");
		PROJECT_NAME = props.getProperty("PROJECTNAME");
	}

	public WorkItem getWorkItemDetails(String id) throws Exception {
		String s = String.join("/", HttpRequests.BASE_URI, ORG_NAME, PROJECT_NAME, "_apis/wit/workitems");
		URI uri = new URIBuilder(s).addParameter("ids", id).addParameter("api-version", "7.1-preview.3").build();
		//System.out.println(uri);
		HttpRequests httpRequests = new HttpRequests();
		String resp = httpRequests.get(uri);
		return new JsonResponseHandler().parseWorkItemDetails(resp);
	}
	
	public String getActiveWorkItemsAsignedTo(String emailid) throws Exception {
		String s = String.join("/", HttpRequests.BASE_URI, ORG_NAME, PROJECT_NAME, "_apis/wit/wiql");
		URI uri = new URIBuilder(s).addParameter("api-version", "5.1").build();
		HttpRequests httpRequests = new HttpRequests();
		JsonRequestHandler requestHandler = new JsonRequestHandler();
		String payload = requestHandler.createActiveWorkItemAssignedToRequest(emailid);
		String resp = httpRequests.post(uri, payload);
		
		List<Integer> list = new JsonResponseHandler().parseActiveWorkItemsAsignedTo(resp);
		StringBuilder sb = new StringBuilder();
		sb.append("<b>Total: " + list.size() + "</b>\n");
		list.forEach(e -> sb.append(e + "\n"));
		return sb.toString();
	}
	
}
