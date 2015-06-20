package traffic_domain.bean;

import java.util.HashSet;
import java.util.Set;

public class Street {

	private int id;
	private String name;
	private Set<District> districts = new HashSet<District>();
	private Set<PostalCode> postalCodes = new HashSet<PostalCode>();
	
	public Street() {		
	}
	
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}

	public Set<District> getDistricts() {
		return districts;
	}
	
	public void setPostalCodes(Set<PostalCode> postalCodes) {
		this.postalCodes = postalCodes;
	}
	
	public Set<PostalCode> getPostalCodes() {
		return postalCodes;
	}
	
	public void addToDistrict(District entity) {
        this.getDistricts().add(entity);
        entity.getStreets().add(this);
    }

    public void removeFromDistrict(District entity) {
        this.getDistricts().remove(entity);
        entity.getStreets().remove(this);
    }
    
    public void addToPostalCode(PostalCode entity) {
        this.getPostalCodes().add(entity);
        entity.getStreets().add(this);
    }

    public void removeFromPostalCode(PostalCode entity) {
        this.getPostalCodes().remove(entity);
        entity.getStreets().remove(this);
    }
}
