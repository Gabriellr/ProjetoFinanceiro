package com.projeto.finaceiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.Session;

import com.projeto.finaceiro.model.Pessoa;
import com.projeto.finaceiro.util.FacesUtil;
import com.projeto.finaceiro.util.HibernateUtil;


@FacesConverter(forClass=Pessoa.class)
public class PessoaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
			Pessoa retorno = null;
			if(value != null){
				Session session = (Session) FacesUtil.getRequestAttribute("session");
				retorno = (Pessoa)session.get(Pessoa.class, new Integer(value));
							
			}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
	
		if(value != null){
		return ((Pessoa) value).getCodigo().toString();
	}
	return null;
	}
	

	

}
