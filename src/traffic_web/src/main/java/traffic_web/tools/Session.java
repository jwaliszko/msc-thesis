package traffic_web.tools;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class Session {
	
	private static Session instance;

	private Session() {
	}
	
	public synchronized HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	public static synchronized Session getInstance(){
		return (instance == null) ? new Session() : instance;		
	}
}
