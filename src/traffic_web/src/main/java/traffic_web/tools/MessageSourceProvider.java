package traffic_web.tools;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class MessageSourceProvider implements MessageSourceAware {

	@Override
	public void setMessageSource(MessageSource arg0) {
		MessageAgent.setMessageSource(arg0);
	}
}
