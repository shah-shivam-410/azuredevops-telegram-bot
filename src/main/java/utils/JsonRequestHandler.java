package utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonRequestHandler {
	
	public String createActiveWorkItemAssignedToTeamRequest(String release, String teamname) {
		JSONObject obj = new JSONObject();
		String query = String.format("Select [System.Id] From WorkItems Where [System.WorkItemType] = \'Defect\' AND [State] = \'Assigned\' AND [Custom.Application_Name] = \'%s\' AND [System.Title] contains \'%s\' AND [System.IterationPath] = \'TestProject1\\CCSX_%s\'", teamname, release, release);
		obj.put("query", query);
		return obj.toString();
	}
	
	public String createEmptyRequest() {
		JSONObject obj = new JSONObject();
		return obj.toString();
	}
	
	public String createWorkItemListDetailsRequest(List<Integer> list) {
		JSONObject payload = new JSONObject();
		JSONArray fieldsArray = new JSONArray();
		
		fieldsArray.put("System.Id");
        fieldsArray.put("System.Title");
        fieldsArray.put("System.State");
        fieldsArray.put("System.AreaPath");
        fieldsArray.put("System.CreatedDate");
        fieldsArray.put("System.CreatedBy");
        fieldsArray.put("System.WorkItemType");
        fieldsArray.put("Custom.Application_Name");
        fieldsArray.put("Custom.NoofTestsBlocked");
        fieldsArray.put("Microsoft.VSTS.Common.Priority");
        payload.put("ids", list); 
        payload.put("fields", fieldsArray);

		return payload.toString();
	}

	public String createWorkitemsByStatusRequest(String release, String status) {
		JSONObject obj = new JSONObject();
		String query = String.format("Select [System.Id] From WorkItems Where [System.WorkItemType] = \'Defect\' AND [State] = \'%s\' AND [System.Title] contains \'%s\' AND [System.IterationPath] = \'TestProject1\\CCSX_%s\'", status, release, release);
		obj.put("query", query);
		return obj.toString();
	}
	
	public String createWorkitemsByCRRequest(String release, String crname) {
		JSONObject obj = new JSONObject();
		String query = String.format("Select [System.Id] From WorkItems Where [System.WorkItemType] = \'Defect\' AND [System.Title] contains \'%s\'  AND [System.Title] contains \'%s\' AND [System.IterationPath] = \'TestProject1\\CCSX_%s\'", crname, release, release);
		obj.put("query", query);
		return obj.toString();
	}
	

}
