package utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ado.modal.Pipeline;
import ado.modal.WorkItem;

public class JsonResponseHandler {

	public WorkItem parseWorkItemDetails(String s) {
		JSONObject json = new JSONObject(s);
		JSONObject fields = json.getJSONArray("value").getJSONObject(0).getJSONObject("fields");
		WorkItem item = new WorkItem();

		item.setId(json.getJSONArray("value").getJSONObject(0).getInt("id"));
		item.setCreatedBy(fields.getJSONObject("System.CreatedBy").getString("uniqueName"));
		item.setPriority(fields.getInt("Microsoft.VSTS.Common.Priority"));
		item.setCreationDate(fields.getString("System.CreatedDate"));
		item.setTitle(fields.getString("System.Title"));
		item.setType(fields.getString("System.WorkItemType"));
		item.setStatus(fields.getString("System.State"));
		item.setAreaPath(fields.getString("System.AreaPath"));
		item.setNoOfTCsBlocked(fields.getInt("Custom.NoofTestsBlocked"));
		item.setAssignedToTeam(fields.getString("Custom.Application_Name"));

		return item;

	}

	public List<Integer> parseActiveWorkItemsAsignedTo(String s) {
		List<Integer> result = new ArrayList<>();
		JSONObject json = new JSONObject(s);
		JSONArray jsonArray = json.getJSONArray("workItems");
		JSONObject temp;
		for (int i = 0; i < jsonArray.length(); i++) {
			temp = jsonArray.getJSONObject(i);
			result.add(temp.getInt("id"));
		}
		return result;
	}

	public Pipeline parseRunPipelineResponse(String s) {
		Pipeline pipeline = new Pipeline();
		JSONObject json = new JSONObject(s);
		pipeline.setState(json.getString("state"));
		pipeline.setId(json.getJSONObject("pipeline").getInt("id"));
		pipeline.setName(json.getJSONObject("pipeline").getString("name"));
		pipeline.setFolder(json.getJSONObject("pipeline").getString("folder"));
		return pipeline;
	}

	public List<Pipeline> parseListofPipelines(String s) {
		List<Pipeline> list = new ArrayList<>();
		JSONObject json = new JSONObject(s);
		JSONArray arr = json.getJSONArray("value");
		Pipeline temp;
		JSONObject obj;
		for (int i = 0; i < arr.length(); i++) {
			temp = new Pipeline();
			obj = (JSONObject) arr.get(i);
			temp.setId(obj.getInt("id"));
			temp.setName(obj.getString("name"));
			temp.setFolder(obj.getString("folder"));
			list.add(temp);
			temp = null;
		}
		return list;
	}

	public String parseWorkItemsListetails(String resp) {
		JSONObject json = new JSONObject(resp);
		JSONArray arr = json.getJSONArray("value");
		List<WorkItem> list = new ArrayList<>();

		WorkItem item = null;
		JSONObject fields = null;

		for(int i=0; i<arr.length(); i++) {
			fields = arr.getJSONObject(i).getJSONObject("fields");
			item = new WorkItem();
			item.setId(json.getJSONArray("value").getJSONObject(0).getInt("id"));
			item.setCreatedBy(fields.getJSONObject("System.CreatedBy").getString("uniqueName"));
			item.setPriority(fields.getInt("Microsoft.VSTS.Common.Priority"));
			item.setCreationDate(fields.getString("System.CreatedDate"));
			item.setTitle(fields.getString("System.Title"));
			item.setType(fields.getString("System.WorkItemType"));
			item.setStatus(fields.getString("System.State"));
			item.setAreaPath(fields.getString("System.AreaPath"));
			item.setNoOfTCsBlocked(fields.getInt("Custom.NoofTestsBlocked"));
			item.setAssignedToTeam(fields.getString("Custom.Application_Name"));
			list.add(item);
		};
		
		StringBuilder sb = new StringBuilder();
		list.forEach(i -> {
			sb.append(i.toString()).append("\n\n");
		});
		return sb.toString();
	}

	public List<Integer> parseListOfDefectWiql(String resp) {
		JSONObject json = new JSONObject(resp);
		JSONArray arr = json.getJSONArray("workItems");
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<arr.length(); i++) {
			list.add(arr.getJSONObject(i).getInt("id"));
		}
		return list;
	}

}
