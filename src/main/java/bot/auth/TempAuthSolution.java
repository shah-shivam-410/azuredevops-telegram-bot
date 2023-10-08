package bot.auth;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TempAuthSolution {

	public static Map<Long, User> usermap = new HashMap<>();
	
	public static void loadUserMap() throws JSONException, IOException {
		JSONArray arr = new JSONArray(FileUtils.readFileToString(new File("src//main//resources//validuserids.json"), StandardCharsets.UTF_8));
		JSONObject obj;
		for (int i = 0; i < arr.length(); i++) {
			obj = (JSONObject) arr.get(i);
			usermap.put(obj.getLong("id"), new User(obj.getLong("id"), obj.getString("firstname"), obj.getString("lastname")));
		}
	}
	
	public static Map<Long, User> getUserMap() throws JSONException, IOException {
		if(usermap.isEmpty())
			loadUserMap();
		return Collections.unmodifiableMap(usermap);
	}
	
}
