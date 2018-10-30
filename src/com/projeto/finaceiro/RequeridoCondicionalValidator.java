package com.projeto.finaceiro;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("com.projeto.RequeridoCondicional")
public class RequeridoCondicionalValidator  implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		Boolean checado = (Boolean) component.getAttributes().get("checado");
		
		if (checado != null && checado && value == null) {
			Object label = "data errada";
			
			String descricaoErro = "Preencha o campo " + label + ".";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					descricaoErro, descricaoErro);
			throw new ValidatorException(message);
		}
	}

}