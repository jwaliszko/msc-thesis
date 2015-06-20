package traffic_domain.bean;

import java.util.HashSet;
import java.util.Set;

public class PostalCode {

	private int id;
	private String value;
	private Set<TrafficCondition> trafficConditions = new HashSet<TrafficCondition>();
	private Set<Street> streets = new HashSet<Street>();
	
	public PostalCode() {		
	}
	
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setTrafficConditions(Set<TrafficCondition> trafficConditions) {
		this.trafficConditions = trafficConditions;
	}
	
	public Set<TrafficCondition> getTrafficConditions() {
		return trafficConditions;
	}
	
	public void setStreets(Set<Street> streets) {
		this.streets = streets;
	}

	public Set<Street> getStreets() {
		return streets;
	}
	
	public void addToTrafficCondition(TrafficCondition entity) {
        this.getTrafficConditions().add(entity);
        entity.getPostalCodes().add(this);
    }

    public void removeFromTrafficCondition(TrafficCondition entity) {
        this.getTrafficConditions().remove(entity);
        entity.getPostalCodes().remove(this);
    }
    
    public void addToStreet(Street entity) {
        this.getStreets().add(entity);
        entity.getPostalCodes().add(this);
    }

    public void removeFromStreet(Street entity) {
        this.getStreets().remove(entity);
        entity.getPostalCodes().remove(this);
    }
}
