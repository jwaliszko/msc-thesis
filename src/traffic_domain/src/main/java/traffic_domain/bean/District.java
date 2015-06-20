package traffic_domain.bean;

import java.util.HashSet;
import java.util.Set;

public class District {

	private int id;
	private String name;
	private Set<Street> streets = new HashSet<Street>();
	
	public District() {		
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

	public void setStreets(Set<Street> streets) {
		this.streets = streets;
	}

	public Set<Street> getStreets() {
		return streets;
	}
	
	public void addToStreet(Street entity) {
        this.getStreets().add(entity);
        entity.getDistricts().add(this);
    }

    public void removeFromStreet(Street entity) {
        this.getStreets().remove(entity);
        entity.getDistricts().remove(this);
    }
}
