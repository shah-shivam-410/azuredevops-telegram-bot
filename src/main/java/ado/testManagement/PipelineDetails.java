package ado.testManagement;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import org.apache.http.client.utils.URIBuilder;

import ado.modal.Pipeline;
import ado.utils.ConfigReader;
import ado.utils.HttpRequests;

public class PipelineDetails {

	public static String ORG_NAME;
	public static String PROJECT_NAME;
	Properties props;
	
	public PipelineDetails() throws IOException {
		props = ConfigReader.loadProps();
		ORG_NAME = props.getProperty("ORGNAME");
		PROJECT_NAME = props.getProperty("PROJECTNAME");
	}
	
	public String runPipeline(String pipelineId) throws Exception {
		String s = String.join("/", HttpRequests.BASE_URI, ORG_NAME, PROJECT_NAME, "_apis/pipelines", pipelineId, "runs");
		URI uri = new URIBuilder(s).addParameter("api-version", "7.1-preview.1").build();
		HttpRequests httpRequests = new HttpRequests();
		JsonRequestHandler requestHandler = new JsonRequestHandler();
		String payload = requestHandler.createEmptyRequest();
		String resp = httpRequests.post(uri, payload);
		Pipeline pipeline = new JsonResponseHandler().parseRunPipelineResponse(resp);
		return pipeline.toString();
	}
}
