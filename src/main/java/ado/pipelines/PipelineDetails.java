package ado.pipelines;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.utils.URIBuilder;

import ado.modal.Pipeline;
import constants.Proplist;
import utils.ConfigReader;
import utils.HttpRequests;
import utils.JsonRequestHandler;
import utils.JsonResponseHandler;

public class PipelineDetails {

	final String baseURI;
	final String orgName;
	final String projectName;
	
	Properties props;

	public PipelineDetails() throws IOException {
		props = ConfigReader.loadProps();
		baseURI = props.getProperty(Proplist.ADO_BASEURI);
		orgName = props.getProperty(Proplist.ADO_ORGNAME);
		projectName = props.getProperty(Proplist.ADO_PROJECT);
	}

	public String runPipeline(String pipelineId) throws Exception {
		String s = String.join("/", baseURI, orgName, projectName, "_apis/pipelines", pipelineId,
				"runs");
		URI uri = new URIBuilder(s).addParameter("api-version", "7.1-preview.1").build();
		HttpRequests httpRequests = new HttpRequests();
		JsonRequestHandler requestHandler = new JsonRequestHandler();
		String payload = requestHandler.createEmptyRequest();
		String resp = httpRequests.post(uri, payload);
		Pipeline pipeline = new JsonResponseHandler().parseRunPipelineResponse(resp);
		return "<b>Pipeline triggered successfully.</b>\n" + pipeline.toString();
	}

	public String getListofJobs() throws Exception {
		String s = String.join("/", baseURI, orgName, projectName, "_apis/pipelines");
		URI uri = new URIBuilder(s).addParameter("api-version", "7.1-preview.1").build();
		HttpRequests httpRequests = new HttpRequests();
		String resp = httpRequests.get(uri);
		List<Pipeline> list = new JsonResponseHandler().parseListofPipelines(resp);
		StringBuilder sb = new StringBuilder();
		sb.append("<b>Total pipelines: " + list.size() + "</b>\n");
		list.forEach(e -> sb.append(e + "\n"));
		return sb.toString();
	}
}
