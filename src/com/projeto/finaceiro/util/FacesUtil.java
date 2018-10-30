package com.projeto.finaceiro.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtil { 
	
	
	  public static void addicionarMensagem(Severity tipo, String msg){
		  FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							msg, msg));
			
	  }

}
