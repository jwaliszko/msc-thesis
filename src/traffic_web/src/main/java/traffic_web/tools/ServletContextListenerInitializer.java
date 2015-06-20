package traffic_web.tools;

import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerInitializer implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent event) {
    }

    public void contextInitialized(ServletContextEvent event) {
        Properties properties = new Properties();
        ServletContext servletContext = event.getServletContext();
        Enumeration<?> keys = servletContext.getInitParameterNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = servletContext.getInitParameter(key);
            properties.setProperty(key, value);
        }
        Configuration.setServletContextProperties(properties);                
    }
}
