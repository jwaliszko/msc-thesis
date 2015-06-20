package traffic_domain.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrafficCondition {

	private int id;	
	private String name;
	private String description;
	private TrafficCondition parentCondition;
	private List<TrafficCondition> subConditions = new ArrayList<TrafficCondition>();
	private Set<PostalCode> postalCodes = new HashSet<PostalCode>();	
	
	public TrafficCondition() {		
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
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setParentCondition(TrafficCondition parentCondition) {
		this.parentCondition = parentCondition;
	}

	public TrafficCondition getParentCondition() {
		return parentCondition;
	}
	
	public  void setSubConditions(List<TrafficCondition> subConditions) {
		this.subConditions = subConditions;
	}
	
	public  List<TrafficCondition> getSubConditions() {
		return subConditions;
	}
	
	public  void setPostalCodes(Set<PostalCode> postalCodes) {
		this.postalCodes = postalCodes;
	}
	
	public  Set<PostalCode> getPostalCodes() {
		return postalCodes;
	}
	
	public void addToPostalCode(PostalCode entity) {
        this.getPostalCodes().add(entity);
        entity.getTrafficConditions().add(this);
    }

    public void removeFromPostalCode(PostalCode entity) {
        this.getPostalCodes().remove(entity);
        entity.getTrafficConditions().remove(this);
    }	
}
