package bot.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.session.Session;

public class SessionOperations {

	public static final Map<Integer, Session> SESSION_MAP = new ConcurrentHashMap<>();

	public void setSession() {
		
	}

}
