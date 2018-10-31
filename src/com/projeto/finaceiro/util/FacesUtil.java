package com.projeto.finaceiro.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil { 
	
	
	  public static void addicionarMensagem(Severity tipo, String msg){
		  FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							msg, msg));
			
	  }
	  public  static Object getRequestAttribute(String name){
		  FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
			return request.getAttribute(name);
		  
	  }
	  
	  
	  
	  
}
