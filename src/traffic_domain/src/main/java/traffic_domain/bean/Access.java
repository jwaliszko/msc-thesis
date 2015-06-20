package traffic_domain.bean;

public class Access {

	private int id;
	private String username;
	private String password;
	
	public Access() {		
	}
	
	// Private setter for the identity of the object. For security reasons. 
	// Hibernate still can access private setters.
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}	
}
