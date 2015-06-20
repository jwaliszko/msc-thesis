package traffic_web.controller;

import traffic_web.logic.AuthenticationProvider;
import traffic_web.logic.Credentials;
import traffic_web.tools.MessageAgent;
import traffic_web.tools.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import traffic_domain.tools.HibernateUtil;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showLoginForm()  {
	    return new ModelAndView("login");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(Credentials credentials) {
		
		boolean validCredentials = false;
		try {	
			// Begin unit of work
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();		
			
			AuthenticationProvider authProvider = new AuthenticationProvider();
			validCredentials = authProvider.CheckIfCredentialsAreValid(credentials);
			
			// End unit of work
	        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
		
		if(!validCredentials)
		{						
			Session.getInstance().getSession().setAttribute("isAuthenticated", "false");			
			return new ModelAndView("login", "error", MessageAgent.getMessage("error.invalidCredentials", null));
		}
		
		Session.getInstance().getSession().setAttribute("isAuthenticated", "true");
		Session.getInstance().getSession().setAttribute("username", credentials.getUsername());
		// We redirect to the "panel.html" instead of just returning view "panel".
		// If we simply return "panel" view, the panel.jsp page will be displayed which
		// is ok, but when we refresh page, the form will be resubmitted. This 
		// is we don't want. In our case the "PanelController" 
		// intercept redirection to "panel.html.html", and then return the view "panel". 		
		return new ModelAndView("redirect:panel.html");
	}
}
