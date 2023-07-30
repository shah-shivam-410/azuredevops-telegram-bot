package ado.testManagement;

import java.util.HashMap;
import java.util.Map;

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
	
}
