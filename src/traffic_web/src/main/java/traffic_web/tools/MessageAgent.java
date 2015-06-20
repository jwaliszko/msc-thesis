package traffic_web.tools;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MessageAgent{

	private static MessageSource messageSource;
	
	public static void setMessageSource(MessageSource source) {
		messageSource = source;
	}

	public static String getMessage(String key, Object[] params){
	      return messageSource.getMessage(key, params, Locale.getDefault());
	}	
}
