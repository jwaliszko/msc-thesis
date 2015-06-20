package traffic_web.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import traffic_web.logic.Location;
import traffic_web.logic.LocationQuestionsLoader;
import traffic_web.logic.OntologyGuard;
import traffic_web.logic.Synchronizer;
import traffic_web.logic.VariousQuestionsLoader;
import traffic_web.model.BoardModel;
import traffic_web.tools.Assert;
import traffic_web.tools.Configuration;
import traffic_web.tools.MessageAgent;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import traffic_domain.logic.LocationFacade;
import traffic_domain.tools.HibernateUtil;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	private static final String ONTOLOGY_URI = Configuration.getOntologyURI();
	private static final Logger log = Logger.getLogger(BoardController.class);	
	private static final String LANGUAGE = "en";
	
	private String selectedLanguage;
	private Location selectedLocation;
	private String failureError;
	
	VariousQuestionsLoader variousQuestions;
	LocationQuestionsLoader locationQuestions;
	
	public BoardController() {
		
		this.selectedLanguage = LANGUAGE;		
		this.selectedLocation = new Location();
		
		this.variousQuestions = new VariousQuestionsLoader();
		this.locationQuestions = new LocationQuestionsLoader();		
		synchronize();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showBoard(){				 
		
		BoardModel data = new BoardModel();
		data.setError(this.failureError);
		
		if(Assert.isNullOrEmpty(data.getError()))
		{
			this.variousQuestions.load(OntologyGuard.getInstance().getOntologyFacade(), !Assert.isNullOrEmpty(this.selectedLanguage) ? this.selectedLanguage : "en");
			this.locationQuestions.load(OntologyGuard.getInstance().getOntologyFacade(), !Assert.isNullOrEmpty(this.selectedLanguage) ? this.selectedLanguage : "en", selectedLocation);
						
			data.setVariousQuestions(variousQuestions);
			data.setLocationQuestions(locationQuestions);
			data.setLanguages(GetLanguagesList());
			data.setSelectedLanguage(this.selectedLanguage);
			data.setSelectedLocation(this.selectedLocation);
			
			try {	
				HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();										
									
				fillLocations(data);			
				
				HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			}catch (Exception e) {
				HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}									 
		}			
		
		ModelAndView mav = new ModelAndView("board", "model", data);
		return mav;
	}	
	
	@RequestMapping(params = "method=saveCore", method = RequestMethod.POST)
	public void onSaveCoreClick(HttpServletResponse response) {						
				
		try {
			URL resource = new URL(ONTOLOGY_URI);
			InputStream input = resource.openStream();
			 
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			
			int c;
			while ((c = input.read()) != -1) {
				output.write((char) c);
			}

			// Prepare header
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=\"TrafficDanger.owl\"");

			// Write file contents to output stream...
			response.getOutputStream().write(output.toByteArray());
			output.close();
			
			failureError = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			failureError = MessageAgent.getMessage("error.internalError", null);
		}
	}
	
	@RequestMapping(params = "method=saveSynchronized", method = RequestMethod.POST)
	public void onSaveSynchronizedClick(HttpServletResponse response) {						
				
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();	
			OntologyGuard.getInstance().getOntologyFacade().saveOntology(output);		
		
			// Prepare header
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=\"TrafficDangerSynchronized.owl\"");

			// Write file contents to output stream...
			response.getOutputStream().write(output.toByteArray());
			output.close();
			
			failureError = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			failureError = MessageAgent.getMessage("error.internalError", null);
		}
	}
	
	@RequestMapping(params = "method=changeLanguage", method = RequestMethod.POST)
	public ModelAndView onLanguageChange(String lang) {		
		
		if((this.selectedLanguage == null) || (this.selectedLanguage != null && !this.selectedLanguage.equals(lang))) {				
			this.selectedLanguage = lang;
		}
		
		return new ModelAndView("redirect:board.html");
	}
		
	@RequestMapping(params = "method=synchronize", method = RequestMethod.POST)
	public ModelAndView onSynchronizeClick() {						
		
		synchronize();
		return new ModelAndView("redirect:board.html");
	}
	
	@RequestMapping(params = "method=changeLocation", method = RequestMethod.POST)
	public ModelAndView onLocationChange(Location location) {		
		
		this.selectedLocation.setPostalCode(location.getPostalCode());
		this.selectedLocation.setStreet(location.getStreet());
		this.selectedLocation.setDistrict(location.getDistrict());
		
		return new ModelAndView("redirect:board.html");
	}
	
	private void synchronize()
	{
		try {								
			OntologyGuard.getInstance().reconfigure(ONTOLOGY_URI);			
			(new Synchronizer()).Execute(OntologyGuard.getInstance().getOntologyFacade());
			
			failureError = null;
		} catch (Exception e) {
			
			failureError = MessageAgent.getMessage("error.unexpectedFailure", null);
			log.error(failureError, e);
		}				
	}

	private List<String> GetLanguagesList()
	{
		List<String> languages = new ArrayList<String>();
		languages.add("en");
		languages.add("pl");
		return languages;
	}
	
	private void fillLocations(BoardModel data)
	{		
		LocationFacade facade = new LocationFacade();		
		data.setPostalCodes(facade.getPostalCodes());
		data.setStreets(facade.getStreets());
		data.setDistricts(facade.getDistricts());					
	}
}
