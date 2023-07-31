package ado.testManagement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ado.modal.WorkItem;

public class JsonResponseHandler {
	
	public WorkItem parseWorkItemDetails(String s) {
		JSONObject json = new JSONObject(s);
		JSONObject fields = json.getJSONArray("value").getJSONObject(0).getJSONObject("fields");
		
		Integer id = json.getJSONArray("value").getJSONObject(0).getInt("id");
		String title = fields.getString("System.Title");
		String createdDate = fields.getString("System.CreatedDate");
		String createdBy = fields.getJSONObject("System.CreatedBy").getString("uniqueName");
		String workItemType = fields.getString("System.WorkItemType");
		Integer priority = fields.getInt("Microsoft.VSTS.Common.Priority");

		WorkItem item = new WorkItem();
		item.setId(id);
		item.setCreatedBy(createdBy);
		item.setPriority(priority);
		item.setCreationDate(createdDate);
		item.setTitle(title);
		item.setType(workItemType);
		return item;
		
	}
	
	public List<Integer> parseActiveWorkItemsAsignedTo(String s) {
		List<Integer> result = new ArrayList<>();
		JSONObject json = new JSONObject(s);
		JSONArray jsonArray = json.getJSONArray("workItems");
		JSONObject temp;
		for(int i=0; i<jsonArray.length(); i++) {
			temp = jsonArray.getJSONObject(i);
			result.add(temp.getInt("id"));
		}
		return result;
	}
	
}
