package traffic_web.tools;

import java.util.Properties;

public class Configuration {
	private static Properties _servletContextProperties = new Properties();
	
	private static final String ONTOLOGY_URI = "OntologyURI";

    public static void setServletContextProperties(Properties servletContextProperties) {
        _servletContextProperties = servletContextProperties;
    }
        
    public static String getOntologyURI(){
    	return _servletContextProperties.getProperty(ONTOLOGY_URI);
    }
}
