package traffic_web.model;

import java.util.List;

import traffic_domain.bean.PostalCode;

public class PanelModel {
	private String tree;
	private String username;
	private List<PostalCode> postalCodes;
	private String selectedCode;
	
	public void setTree(String tree) {
		this.tree = tree;
	}
	
	public String getTree() {
		return tree;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setPostalCodes(List<PostalCode> postalCodes) {
		this.postalCodes = postalCodes;
	}

	public List<PostalCode> getPostalCodes() {
		return postalCodes;
	}

	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}

	public String getSelectedCode() {
		return selectedCode;
	}
}
