package traffic.logic;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import traffic.tools.MapEntry;
import traffic.tools.Utility;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.ReasonerProgressMonitor;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.DublinCoreVocabulary;


public class OntologyFacade {
	
	private static final String HAS_LOCATION = "hasLocation";
	private static final String HAS_TRAFFIC_CONDITION = "hasTrafficCondition";
	private static final String TRAFFIC_DANGER = "TrafficDanger";
	private static final String TRAFFIC_CONDITION = "TrafficCondition";	
	private static final String OWL_NOTHING = "owl:Nothing";
	private static final String DELIMITER = "#";
	
	private OWLOntologyManager ontologyManager;
	private OWLOntology ontology;
	private ReasonerManager reasonerManager;
				
	public OntologyFacade(){	
		
	}
	
	public void prepare(String documentLocation) throws Exception{
		
		// Get hold of an ontology manager.
		this.ontologyManager = OWLManager.createOWLOntologyManager();		
		// Ask the manager to load the ontology.
		this.ontology = this.ontologyManager.loadOntologyFromOntologyDocument(IRI.create(documentLocation));
								
		ReasonerProgressMonitor monitor = new ConsoleProgressMonitor();
		this.reasonerManager = new ReasonerManager(this.ontology, monitor);
	}	
	
	public void inferDependencies(){
		
		this.reasonerManager.infer();
	}
	
	public List<String> getSubclassesNames(String className){
		        
        DefaultPrefixManager pm = getPrefixManager();                
        Collection<OWLClass> classes = getSubclasses(className);
        
        List<String> names = new ArrayList<String>();
        for(OWLClass cls : classes) {
        	names.add(pm.getShortForm(cls));
        }        
        
        return names;
	}
	
	public List<String> getSubclassesDescriptions(String className, String lang){
		
        Collection<OWLClass> classes = getSubclasses(className);                
        OWLDataFactory df = this.ontologyManager.getOWLDataFactory();
        
        // Get the annotation property for dc:description.
        OWLAnnotationProperty desc = df.getOWLAnnotationProperty(DublinCoreVocabulary.DESCRIPTION.getIRI());
        
        List<String> descriptions = new ArrayList<String>();
        
        for (OWLClass cls : classes) {        	
        	// Get the annotations on the class that use the label property.
        	for (OWLAnnotation annotation : cls.getAnnotations(this.ontology, desc)){
        		if (annotation.getValue() instanceof OWLLiteral){
        			OWLLiteral val = (OWLLiteral) annotation.getValue();
        			if (!val.isOWLTypedLiteral()){
        				// The value isn't a typed constant, so we can safely obtain it as an
        				// OWLRDFTextLiteral and check the lang provided as function parameter.
        				if (val.asOWLStringLiteral().hasLang(lang)){
        					descriptions.add(val.getLiteral());
        				}        				
        			}
        		}
        	}
        }

        return descriptions;
	}
	
	public List<Entry<String, Boolean>> getClassesDescriptionsWithAggregationFlag(List<String> classesNames, String lang){
		
		List<Entry<String, Boolean>> descriptions = new ArrayList<Entry<String, Boolean>>();
		for(String className: classesNames)
		{
			descriptions.add(getClassDescriptionWithAggregationFlag(className, lang));
		}
		
        return descriptions;
	}

	public List<Entry<String, Boolean>> getSubclassesDescriptionsWithAggregationFlag(String className, String lang){
		
        Collection<OWLClass> classes = getSubclasses(className);                
        OWLDataFactory df = this.ontologyManager.getOWLDataFactory();
        
        // Get the annotation property for dc:description.
        OWLAnnotationProperty desc = df.getOWLAnnotationProperty(DublinCoreVocabulary.DESCRIPTION.getIRI());

        List<Entry<String, Boolean>> descriptions = new ArrayList<Entry<String, Boolean>>();
        DefaultPrefixManager pm = getPrefixManager();   
        
        for (OWLClass cls : classes){
        	
        	boolean isGrouping = !getSubclasses(pm.getShortForm(cls)).toString().contains(OWL_NOTHING);
        	// Get the annotations on the class that use the label property.
        	for (OWLAnnotation annotation : cls.getAnnotations(this.ontology, desc)){
        		if (annotation.getValue() instanceof OWLLiteral){
        			OWLLiteral val = (OWLLiteral) annotation.getValue();
        			if (!val.isOWLTypedLiteral()){
        				// The value isn't a typed constant, so we can safely obtain it as an
        				// OWLRDFTextLiteral and check the lang provided as function parameter.
        				if (val.asOWLStringLiteral().hasLang(lang)){
        					descriptions.add(new MapEntry<String, Boolean>(val.getLiteral(), isGrouping));
        				}        				
        			}
        		}
        	}
        }

        return descriptions;
	}
	
	public String getClassDescription(String className, String lang){
		
        OWLClass cls = getClass(className);                
        OWLDataFactory df = this.ontologyManager.getOWLDataFactory();
        
        // Get the annotation property for dc:description.
        OWLAnnotationProperty desc = df.getOWLAnnotationProperty(DublinCoreVocabulary.DESCRIPTION.getIRI());
        
        String description = null;        
        for (OWLAnnotation annotation : cls.getAnnotations(this.ontology, desc)){
        	if (annotation.getValue() instanceof OWLLiteral){
        		OWLLiteral val = (OWLLiteral) annotation.getValue();
        		if (!val.isOWLTypedLiteral()){
        			// The value isn't a typed constant, so we can safely obtain it as an
        			// OWLRDFTextLiteral and check the lang provided as function parameter.
        			if (val.asOWLStringLiteral().hasLang(lang)){
        				description = val.getLiteral();
        			}        				
        		}
        	}
        }

        return description;
	}
	
	public Entry<String, Boolean> getClassDescriptionWithAggregationFlag(String className, String lang){		                        
        
        DefaultPrefixManager pm = getPrefixManager();
        String description  = getClassDescription(className, lang);
        OWLClass cls = getClass(className);
        boolean isGrouping = !getSubclasses(pm.getShortForm(cls)).toString().contains(OWL_NOTHING);
        return new MapEntry<String, Boolean>(description, isGrouping);       
	}
	
	public List<String> getIndividualsNames(String className){
		
		DefaultPrefixManager pm = getPrefixManager(); 
        Collection<OWLNamedIndividual> individuals = getIndividuals(className);
        
        List<String> names = new ArrayList<String>();
        for(OWLNamedIndividual ind : individuals){
        	names.add(pm.getShortForm(ind));
        }        
        
        return names;
	}
	
	public void addLocationToClass(String className, String locationName){
	
		OWLAxiom axiom = prepareAxiomForClass(className, HAS_LOCATION, locationName);
		AddAxiom addAx = new AddAxiom(this.ontology, axiom);
		this.ontologyManager.applyChange(addAx);
	}
	
	public void removeLocationFromClass(String className, String locationName){
	
		OWLAxiom axiom = prepareAxiomForClass(className, HAS_LOCATION, locationName);		
		RemoveAxiom remAx = new RemoveAxiom(this.ontology, axiom);
		this.ontologyManager.applyChange(remAx);		
	}
	
	public void addLocationToIndividual(String individualName, String locationName){
	
		OWLAxiom axiom = prepareAxiomForIndividual(individualName, HAS_LOCATION, locationName);
		AddAxiom addAx = new AddAxiom(this.ontology, axiom);
		this.ontologyManager.applyChange(addAx);		
	}
	
	public void removeLocationFromIndividual(String individualName, String locationName){
	
		OWLAxiom axiom = prepareAxiomForIndividual(individualName, HAS_LOCATION, locationName);		
		RemoveAxiom remAx = new RemoveAxiom(this.ontology, axiom);
		this.ontologyManager.applyChange(remAx);
	}
	
	public void setIndividualAsInstanceOfClass(String individualName, String className){

		DefaultPrefixManager pm = getPrefixManager(); 
		OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();
		OWLClass cls = fac.getOWLClass(className, pm);
		OWLIndividual ind = fac.getOWLNamedIndividual(individualName, pm);	
		OWLClassAssertionAxiom axiom = fac.getOWLClassAssertionAxiom(cls, ind);
		
		AddAxiom addAx = new AddAxiom(this.ontology, axiom);
		this.ontologyManager.applyChange(addAx);		
	}
	
	public List<String> getTrafficDangersNamesConnectedWithLocation(String locationName){
	
		DefaultPrefixManager pm = getPrefixManager();
		List<String> dangersNames = new ArrayList<String>(); 
		
		Collection<OWLClass> conditions = getTrafficConditionsConnectedWithLocation(locationName);
		for(OWLClass condition: conditions)
		{
			Collection<OWLClass> dangers = getTrafficDangersConnectedWithTrafficCondition(pm.getShortForm(condition));
			for(OWLClass danger: dangers)
			{
				String name = pm.getShortForm(danger);
				if(!name.contains(OWL_NOTHING))
				{
					dangersNames.add(name);
				}
			}
		}
				
		Utility.removeDuplicates(dangersNames);
		return dangersNames;
	}	
	
	public void saveOntology(String documentLocation) throws OWLOntologyStorageException{

		// RDF/XML is the default format
		this.ontologyManager.saveOntology(this.ontology, IRI.create(documentLocation));						
	}	
	
	public void saveOntology(OutputStream stream) throws OWLOntologyStorageException {
		
		this.ontologyManager.saveOntology(this.ontology, stream);		
	}
	
	private OWLAxiom prepareAxiomForClass(String className, String propertyName, String valueName){
		
		DefaultPrefixManager pm = getPrefixManager(); 
		OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();
               
		OWLClass condition = fac.getOWLClass(className, pm);
		OWLIndividual individual = fac.getOWLNamedIndividual(valueName, pm);
		OWLObjectPropertyExpression propertyExpression = fac.getOWLObjectProperty(propertyName, pm);
		OWLClassExpression classExpression = fac.getOWLObjectHasValue(propertyExpression, individual);           
		return fac.getOWLSubClassOfAxiom(condition, classExpression);			
	}
	
	private OWLAxiom prepareAxiomForIndividual(String individualName, String propertyName, String valueName){
		
		DefaultPrefixManager pm = getPrefixManager(); 
		OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();
               
		OWLIndividual source = fac.getOWLNamedIndividual(individualName, pm);		
		OWLIndividual destination = fac.getOWLNamedIndividual(valueName, pm);		
		OWLObjectPropertyExpression propertyExpression = fac.getOWLObjectProperty(propertyName, pm);		
		return fac.getOWLObjectPropertyAssertionAxiom(propertyExpression, source, destination);		
	}		
	
	private OWLClass getClass(String className){
		        
        DefaultPrefixManager pm = getPrefixManager();              
        OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();
        return fac.getOWLClass(className, pm);                     
	}
	
	private Collection<OWLNamedIndividual> getIndividuals(String className){
		        
        DefaultPrefixManager pm = getPrefixManager();               
        OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();
        OWLClass cls = fac.getOWLClass(className, pm);

        NodeSet<OWLNamedIndividual> subClses = this.reasonerManager.getReasoner().getInstances(cls, true);
        return subClses.getFlattened();
	}
	
	private Collection<OWLClass> getTrafficDangersConnectedWithTrafficCondition(String trafficCondition){
	
		DefaultPrefixManager pm = getPrefixManager();
		OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();
		
		OWLClass danger = fac.getOWLClass(TRAFFIC_DANGER, pm);
		OWLObjectProperty conditionProperty = fac.getOWLObjectProperty(HAS_TRAFFIC_CONDITION, pm);		
		OWLClassExpression condition = fac.getOWLClass(trafficCondition, pm);		
		OWLObjectSomeValuesFrom someRestr = fac.getOWLObjectSomeValuesFrom(conditionProperty, condition);	// hasTrafficCondition some "trafficCondition"
		OWLObjectIntersectionOf dangerIntersection = fac.getOWLObjectIntersectionOf(danger, someRestr);		// TrafficDanger and hasTrafficCondition some "trafficCondition"
		 
        NodeSet<OWLClass> subClses = this.reasonerManager.getReasoner().getSubClasses(dangerIntersection, true);
        return subClses.getFlattened();        		
	}
	
	private Collection<OWLClass> getTrafficConditionsConnectedWithLocation(String locationName){
		
		DefaultPrefixManager pm = getPrefixManager();
		OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();
		
		OWLClass condition = fac.getOWLClass(TRAFFIC_CONDITION, pm);
		OWLObjectProperty locationProperty = fac.getOWLObjectProperty(HAS_LOCATION, pm);				
		OWLNamedIndividual location = fac.getOWLNamedIndividual(locationName, pm);		
		OWLObjectHasValue valueRestr = fac.getOWLObjectHasValue(locationProperty, location);					// hasLocation value "locationName"							
		OWLObjectIntersectionOf conditionIntersection = fac.getOWLObjectIntersectionOf(condition, valueRestr);	// TrafficCondition and hasLocation value "locationName"		
		
        NodeSet<OWLClass> subClses = this.reasonerManager.getReasoner().getSubClasses(conditionIntersection, true);
        return subClses.getFlattened();
	}
	
	private Collection<OWLClass> getSubclasses(String className){
        
        DefaultPrefixManager pm = getPrefixManager();
        
        // Now we want to query the reasoner for all descendants of desired class.
        OWLDataFactory fac = this.ontologyManager.getOWLDataFactory();

        // Get a reference to the desired class so that we can as the reasoner about it.
        OWLClass cls = fac.getOWLClass(className, pm);

        // Now use the reasoner to obtain the subclasses of desired class.
        // We can ask for the direct subclasses of the class or all of the (proper) subclasses of the class.
        // In this case we just want the direct ones (which we specify by the "true" flag).
        NodeSet<OWLClass> subClses = this.reasonerManager.getReasoner().getSubClasses(cls, true);                

        // The reasoner returns a NodeSet, which represents a set of Nodes.
        // Each node in the set represents a subclass of desired class.  A node of classes 
        // contains classes, where each class in the node is equivalent. For example, if we asked for the
        // subclasses of some class A and got back a NodeSet containing two nodes {B, C} and {D}, then A would have
        // two proper subclasses.  One of these subclasses would be equivalent to the class D, and the other would
        // be the class that is equivalent to class B and class C.

        // In this case, we don't particularly care about the equivalences, so we will flatten this
        // set of sets and print the result.
        return subClses.getFlattened();                           
	}
	
	private DefaultPrefixManager getPrefixManager(){
		
		String prefix = this.ontology.getOntologyID().getOntologyIRI() + DELIMITER;
        DefaultPrefixManager pm = new DefaultPrefixManager(prefix);
        return pm;
	}		
}
