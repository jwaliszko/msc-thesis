package traffic_domain.logic;



import java.util.List;

import traffic_domain.bean.District;
import traffic_domain.bean.PostalCode;
import traffic_domain.bean.Street;
import traffic_domain.tools.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class LocationFacade {

    public int createAndStoreDistrict(String name) {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        District entity = new District();
        entity.setName(name);
        session.save(entity);
        
        return entity.getId();
    }
    
    @SuppressWarnings("unchecked")
	public List<District> getDistricts() {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();        
        return session.createQuery("from District").list();        
    }
    
    public int createAndStoreStreet(String name) {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Street entity = new Street();
        entity.setName(name);
        session.save(entity);

        return entity.getId();
    }
    
    @SuppressWarnings("unchecked")
	public List<Street> getStreets() {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from Street").list();
    }
    
    public int createAndStorePostalCode(String value) {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        PostalCode entity = new PostalCode();
        entity.setValue(value);
        session.save(entity);
        
        return entity.getId();
    }
    
    @SuppressWarnings("unchecked")
	public List<PostalCode> getPostalCodes() {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.createQuery("from PostalCode").list();
    }
    
    public void addStreetToDistrict(int streetId, int districtId) {
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Street street = (Street) session.load(Street.class, new Integer(streetId));
        District district = (District) session.load(District.class, new Integer(districtId));
        district.addToStreet(street);
    }
    
    public void addStreetToPostalCode(int streetId, int postalCodeId) {
    	
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Street street = (Street) session.load(Street.class, new Integer(streetId));
        PostalCode postalCode = (PostalCode) session.load(PostalCode.class, new Integer(postalCodeId));
        postalCode.addToStreet(street);
    }
    
    public PostalCode getPostalCode(String value){
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 	
    	Criteria criteria = session.createCriteria(PostalCode.class)
    		.add(Restrictions.eq("value", value));    	
    	PostalCode code = criteria.list().size() > 0 ? (PostalCode) criteria.list().get(0) : null;
    	return code;
    }    
}
