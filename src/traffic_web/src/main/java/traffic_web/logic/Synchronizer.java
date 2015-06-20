package traffic_web.logic;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import traffic.logic.ClassesNamesProvider;
import traffic.logic.OntologyFacade;
import traffic_domain.bean.District;
import traffic_domain.bean.PostalCode;
import traffic_domain.bean.Street;
import traffic_domain.bean.TrafficCondition;
import traffic_domain.logic.LocationFacade;
import traffic_domain.logic.TrafficConditionFacade;
import traffic_domain.tools.HibernateUtil;
import traffic_web.tools.MessageAgent;

public class Synchronizer {
		
	private static final Logger log = Logger.getLogger(Synchronizer.class);
	
	public void Execute(OntologyFacade facade) throws Exception
	{	
		try {
            // Begin unit of work
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();         
    		           
            LocationFacade lf = new LocationFacade();
            
            List<District> districts = lf.getDistricts();
            for(int i=0; i<districts.size(); i++)
            {
            	District district = districts.get(i);	            	
            	facade.setIndividualAsInstanceOfClass(district.getName(), ClassesNamesProvider.DISTRICT_LOCATION);
            }
            
            List<Street> streets = lf.getStreets();
            for(int i=0; i<streets.size(); i++)
            {
            	Street street = streets.get(i);
            	facade.setIndividualAsInstanceOfClass(street.getName(), ClassesNamesProvider.STREET_LOCATION);
            	
            	Set<District> connectedDistricts = street.getDistricts();
            	for(District district : connectedDistricts) {
            		facade.addLocationToIndividual(street.getName(), district.getName());
	            }
            }
            
            List<PostalCode> postalCodes = lf.getPostalCodes();
            for(int i=0; i<postalCodes.size(); i++)
            {
            	PostalCode postalCode = postalCodes.get(i);	            	
            	facade.setIndividualAsInstanceOfClass(postalCode.getValue(), ClassesNamesProvider.POSTAL_CODE_LOCATION);
            	
            	Set<Street> connectedStreets = postalCode.getStreets();
            	for(Street street : connectedStreets) {
            		facade.addLocationToIndividual(postalCode.getValue(), street.getName());
	            }
            }
            
            TrafficConditionFacade traffic = new TrafficConditionFacade();
            List<TrafficCondition> trafficConditions = traffic.getTrafficConditions();
            for(int i=0; i<trafficConditions.size(); i++)
            {
            	TrafficCondition condition = trafficConditions.get(i);
            	Set<PostalCode> connectedPostalCodes = condition.getPostalCodes();
            	for(PostalCode code : connectedPostalCodes)
            	{
            		facade.addLocationToClass(condition.getName(), code.getValue());	            		
            	}
            }
	    	                     
            facade.inferDependencies();
            
            // End unit of work
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
        }
        catch (Exception e) {
			log.error(MessageAgent.getMessage("error.synchronizationFailed", null), e);
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            throw e;
        }        						
	}
}
