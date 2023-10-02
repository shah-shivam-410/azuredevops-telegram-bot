package ado.defects;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.utils.URIBuilder;

import ado.modal.WorkItem;
import constants.Proplist;
import utils.ConfigReader;
import utils.HttpRequests;
import utils.JsonRequestHandler;
import utils.JsonResponseHandler;

public class DefectOperations {

	final String baseURI;
	final String orgName;
	final String projectName;

	Properties props;

	public DefectOperations() throws IOException {
		props = ConfigReader.loadProps();
		baseURI = props.getProperty(Proplist.ADO_BASEURI);
		orgName = props.getProperty(Proplist.ADO_ORGNAME);
		projectName = props.getProperty(Proplist.ADO_PROJECT);
	}

	public WorkItem getWorkItemDetails(String id) throws Exception {
		String s = String.join("/", baseURI, orgName, projectName, "_apis/wit/workitems");
		URI uri = new URIBuilder(s).addParameter("ids", id).addParameter("api-version", "7.1-preview.3").build();
		HttpRequests httpRequests = new HttpRequests();
		String resp = httpRequests.get(uri);
		return new JsonResponseHandler().parseWorkItemDetails(resp);
	}
	
	public String getWorkItemListDetails(List<Integer> list) throws URISyntaxException {
		String s = String.join("/", baseURI, orgName, projectName, "_apis/wit/workitemsbatch");
		URI uri = new URIBuilder(s).addParameter("api-version", "7.1-preview.3").build();
		String request = new JsonRequestHandler().createWorkItemListDetailsRequest(list);
		HttpRequests httpRequests = new HttpRequests();
		String resp = httpRequests.post(uri, request);
		return new JsonResponseHandler().parseWorkItemsListetails(resp);
	}
	
	public List<Integer> getListOfDefectsByTeam(String release, String teamname) throws Exception {
		String s = String.join("/", baseURI, orgName, projectName, "_apis/wit/wiql");
		URI uri = new URIBuilder(s).addParameter("api-version", "7.1-preview.2").build();
		String request = new JsonRequestHandler().createActiveWorkItemAssignedToTeamRequest(release, teamname);
		HttpRequests httpRequests = new HttpRequests();
		String resp = httpRequests.post(uri, request);
		return new JsonResponseHandler().parseListOfDefectWiql(resp);
	}
	
	public List<Integer> getListOfDefectsByStatus(String release, String status) throws Exception {
		String s = String.join("/", baseURI, orgName, projectName, "_apis/wit/wiql");
		URI uri = new URIBuilder(s).addParameter("api-version", "7.1-preview.2").build();
		String request = new JsonRequestHandler().createWorkitemsByStatusRequest(release, status);
		HttpRequests httpRequests = new HttpRequests();
		String resp = httpRequests.post(uri, request);
		return new JsonResponseHandler().parseListOfDefectWiql(resp);
	}
	



}
