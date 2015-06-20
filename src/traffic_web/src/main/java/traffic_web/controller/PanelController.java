package traffic_web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import traffic_web.logic.Conditions;
import traffic_web.model.PanelModel;
import traffic_web.tools.Assert;
import traffic_web.tools.Session;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import traffic_domain.bean.PostalCode;
import traffic_domain.bean.TrafficCondition;
import traffic_domain.logic.LocationFacade;
import traffic_domain.logic.TrafficConditionFacade;
import traffic_domain.tools.HibernateUtil;


@Controller
@RequestMapping(value = "/panel")
public class PanelController {
	
	private String selectedCode;
	
	public PanelController() {
		
		this.selectedCode = null;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView redirect()
	{
		String isAuthenticated = (String) Session.getInstance().getSession().getAttribute("isAuthenticated");		
		if("true".equals(isAuthenticated))
		{
			String tree = null;
			List<PostalCode> postalCodes = null;
			try {	
				HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	
				
				tree = (!Assert.isNullOrEmpty(this.selectedCode)) ? prepareTree(this.selectedCode) : tree;
				postalCodes = getPostalCodes();
				
		        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			}catch (Exception ex) {
				HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			
			PanelModel data = new PanelModel();
			data.setUsername((String) Session.getInstance().getSession().getAttribute("username"));          
			data.setTree(tree);
			data.setPostalCodes(postalCodes);
			data.setSelectedCode(this.selectedCode);
					
			return new ModelAndView("panel", "model", data);
		}
		return new ModelAndView("redirect:login.html");
	}
	
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public ModelAndView onUpdate(Conditions checkedConditions) {

		try {	
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();										
			updateTrafficConditions(this.selectedCode, checkedConditions.getNames());						
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
		return new ModelAndView("redirect:panel.html");
	}	
	
	@RequestMapping(params = "postalCode", method = RequestMethod.POST)
	public ModelAndView onCodeChange(Conditions checkedConditions) {
		
		if((this.selectedCode == null) || (this.selectedCode != null && !this.selectedCode.equals(checkedConditions.getPostalCode()))) {				
			this.selectedCode = checkedConditions.getPostalCode();
		}
		
		return new ModelAndView("redirect:panel.html");
	}
	
	private static String generateConditionsTree(TrafficCondition root, int deep, List<String> existingConditions) {
		
		deep++;
		List<TrafficCondition> subCond = root.getSubConditions();
		
		StringBuilder tree = new StringBuilder();
		
		tree.append(
				subCond.size() != 0 ? 
						String.format("%s<label class=\"label-main\">%s</label><br />", prepareIndent(deep - 1), root.getName())
						: String.format("%s<input name=\"names\" type=\"checkbox\" value=\"%s\" %s/><label class=\"label\">%s</label><br />", prepareIndent(deep - 1), root.getName(), (existingConditions != null && existingConditions.contains(root.getName())) ? "checked" : "", root.getName()));        
		
        for(int i = 0; i< subCond.size(); i++)
        {
        	TrafficCondition current = subCond.get(i);        	        	
        	tree.append(generateConditionsTree(current, deep, existingConditions));
        }
        return tree.toString();
	}
	
	private static String prepareIndent(int deep) {
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<deep; i++) {
            sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
        }
		return sb.toString();
	}	
	
	private static void updateTrafficConditions(String postalCode, String[] newConditions){
		
		LocationFacade lf = new LocationFacade();
		PostalCode code = lf.getPostalCode(postalCode);		
	
		if(postalCode != null)
		{
			TrafficCondition[] connectedConditions  = code.getTrafficConditions().toArray(new TrafficCondition[0]);
			List<String> connectedConditionsNames = new ArrayList<String>();
			for(int i=0; i<connectedConditions.length; i++)
			{
				TrafficCondition current = connectedConditions[i];
				// If checked conditions don't contain condition already connected with that location in database, we are removing that from database.
				if(!ArrayUtils.contains(newConditions, current.getName()))
				{
					current.removeFromPostalCode(code);
				}
			}

			// We are going to add conditions to database only when we checked something.
			if(newConditions != null && newConditions.length > 0)
			{
				TrafficConditionFacade tcf = new TrafficConditionFacade();			
				for(int i=0; i<newConditions.length; i++)
				{				
					String current = newConditions[i];
					// If conditions already connected with that location in database don't contain checked condition, we are adding that to database.
					if(!connectedConditionsNames.contains(current))
					{
						TrafficCondition condition = tcf.getTrafficCondition(current);
						if(condition != null)
						{
							tcf.addPostalCodeToTrafficCondition(code.getId(), condition.getId());
						}
					}
				}
			}
		}
	}
	
	private List<String> getTrafficConditionsNames(String postalCode) {
		
		LocationFacade lf = new LocationFacade();
        PostalCode code = lf.getPostalCode(postalCode);            
        Set<TrafficCondition> connectedConditions = code.getTrafficConditions();

        List<String> connectedConditionsNames = new ArrayList<String>();
        for(TrafficCondition current : connectedConditions)
		{
        	connectedConditionsNames.add(current.getName());
		}
	
        return connectedConditionsNames;
	}
	
	private String prepareTree(String postalCode) {
		
		TrafficConditionFacade tcf = new TrafficConditionFacade();
		TrafficCondition trafficCondition = tcf.getTrafficCondition("TrafficCondition");
		return generateConditionsTree(trafficCondition, 0, getTrafficConditionsNames(postalCode));
	}
	
	private List<PostalCode> getPostalCodes()
	{
		LocationFacade lf = new LocationFacade();
		return lf.getPostalCodes();
	}
}
