package traffic_web.logic;

import traffic.logic.OntologyFacade;

public final class OntologyGuard {

	private static OntologyGuard instance;
	private OntologyFacade facade;
	
	private OntologyGuard() {
		this.facade = new OntologyFacade();		
	}
	
	public OntologyFacade getOntologyFacade() {
		return this.facade;
	}
	
	public static synchronized OntologyGuard getInstance() {
		if(instance == null)
		{
			instance = new OntologyGuard();
		}
		return instance;
	}	
	
	public void reconfigure(String ontologyLocation) throws Exception {
		this.facade.prepare(ontologyLocation);
	}
}
