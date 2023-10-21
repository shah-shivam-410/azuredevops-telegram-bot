package temp;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.env.BasicIniEnvironment;
//import org.apache.shiro.env.DefaultEnvironment;
//import org.apache.shiro.env.Environment;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;

public class SessionPractice {

//	public static void main(String[] args) {
//
//		Environment env = new BasicIniEnvironment("classpath:shiro.ini");
//		SecurityManager securityManager = env.getSecurityManager();
//
//		SecurityUtils.setSecurityManager(securityManager);
//
//		UsernamePasswordToken token = new UsernamePasswordToken("root", "secret");
//
//		Subject currentUser = SecurityUtils.getSubject();
//		Session session = currentUser.getSession();
//		
//		System.out.println(token);
//		System.out.println(currentUser.getPrincipal());
//		System.out.println(session.getAttributeKeys());
//		
//		System.out.println(currentUser.isAuthenticated());
//		
//		if (!currentUser.isAuthenticated()) {
//			currentUser.login(token);
//		}
//
//		System.out.println("User [" + currentUser.getPrincipal() + "] logged in successfully.");
//		currentUser.logout();
//		
//	}

}
