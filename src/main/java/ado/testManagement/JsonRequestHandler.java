package ado.testManagement;

import org.json.JSONObject;

public class JsonRequestHandler {
	
	public String createActiveWorkItemAssignedToRequest(String emailid) {
		JSONObject obj = new JSONObject();
		String query = String.format("Select [System.Id] From WorkItems Where [System.AssignedTo] = \'%s\' and [System.State] = \'Active\' and [System.TeamProject] = \'TestProject1\' order by [Microsoft.VSTS.Common.Priority] asc, [System.CreatedDate] desc", emailid);
		obj.put("query", query);
		//System.out.println(obj.toString());
		return obj.toString();
	}
	
	
}
