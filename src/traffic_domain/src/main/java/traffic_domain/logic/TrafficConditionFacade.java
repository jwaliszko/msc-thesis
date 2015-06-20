package traffic_domain.logic;

import java.util.List;

import traffic_domain.bean.PostalCode;
import traffic_domain.bean.TrafficCondition;
import traffic_domain.tools.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class TrafficConditionFacade {

	public int createAndStoreTrafficCondition(String name) {
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        TrafficCondition entity = new TrafficCondition();
        entity.setName(name);
        session.save(entity);

        return entity.getId();
    }
    
    @SuppressWarnings("unchecked")
	public List<TrafficCondition> getTrafficConditions() {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from TrafficCondition").list();        
    }
     
    public TrafficCondition getTrafficCondition(String name){
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 	
    	Criteria criteria = session.createCriteria(TrafficCondition.class)
    		.add(Restrictions.eq("name", name));    	
    	TrafficCondition condition = criteria.list().size() > 0 ? (TrafficCondition) criteria.list().get(0) : null;
    	return condition;
    }
    
	public void addPostalCodeToTrafficCondition(int postalCodeId, int trafficConditionId) {
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        PostalCode postalCode = (PostalCode) session.load(PostalCode.class, new Integer(postalCodeId));
        TrafficCondition trafficCondition = (TrafficCondition) session.load(TrafficCondition.class, new Integer(trafficConditionId));
        trafficCondition.addToPostalCode(postalCode);
    }
}
