package traffic_domain.logic;



import java.util.List;

import traffic_domain.bean.Access;
import traffic_domain.tools.HibernateUtil;

import org.hibernate.Session;


public class AccessFacade {

    public int createAndStoreAccess(String username, String password) {    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        Access entity = new Access();
        entity.setUsername(username);
        entity.setPassword(password);
        session.save(entity);
   
        return entity.getId();
    }
    
	@SuppressWarnings("unchecked")
	public List<Access> getAccessList() {		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        return session.createQuery("from Access").list();
    }
}
