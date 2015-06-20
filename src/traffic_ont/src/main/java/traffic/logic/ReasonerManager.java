package traffic.logic;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.ReasonerProgressMonitor;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;

public class ReasonerManager {
	
	private OWLReasoner reasoner;
	private OWLOntology ontology;
	private ReasonerProgressMonitor monitor;
	
	public ReasonerManager(OWLOntology ontology, ReasonerProgressMonitor monitor) {
		this.ontology = ontology;
		this.monitor = monitor;
		reconfigureReasoner(ontology, monitor);
	}
	
	public OWLReasoner getReasoner() {
		return reasoner;
	}			
	
	public void infer(){
		
		// We have to recreate reasoner if the ontology has changed since last time, to infer properly.
		this.reasoner.dispose();
		reconfigureReasoner(this.ontology, this.monitor);
        // Ask the reasoner to do all the necessary work now.
		this.reasoner.prepareReasoner();        
	}
	
	private void reconfigureReasoner(OWLOntology ontology, ReasonerProgressMonitor monitor){
		
		this.ontology = ontology;
		this.monitor = monitor;
		
        // We'll use HermiT reasoner. HermiT can be downloaded from http://hermit-reasoner.com
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        
        // Specify the progress monitor via a configuration.  We could also specify other setup parameters in
        // the configuration, and different reasoners may accept their own defined parameters this way.
        OWLReasonerConfiguration config = ( monitor != null ) ? new SimpleConfiguration(monitor) : null;
        
        // Create a reasoner that will reason over our ontology and its imports closure.  Pass in the configuration.
        this.reasoner = ( config != null ) ? reasonerFactory.createReasoner(ontology, config) : reasonerFactory.createReasoner(ontology);              
	}		
}
