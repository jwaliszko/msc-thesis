package traffic_web.logic;

import java.util.List;

import traffic_domain.bean.Access;
import traffic_domain.logic.AccessFacade;
import traffic_web.tools.Encryptor;

public class AuthenticationProvider {

	public boolean CheckIfCredentialsAreValid(Credentials credentials)
	{
		boolean isValid = false;
		
		try {
    		String passwordHash = Encryptor.getInstance().encrypt(credentials.getPassword());
    		
    		AccessFacade access = new AccessFacade();
    		List<Access> list = access.getAccessList();
    		
    		for(int i=0; i< list.size(); i++)
    		{
    			Access current = list.get(i);
    			if(current.getUsername() != null 
    					&& current.getPassword() != null
    					&& current.getUsername().equals(credentials.getUsername()) 
    					&& current.getPassword().equals(passwordHash))
    			{
    				isValid = true;
    				break;
    			}
    		}
        }
        catch (Exception ex) {
            isValid = false;
        }        				
		
		return isValid;
	}
}
