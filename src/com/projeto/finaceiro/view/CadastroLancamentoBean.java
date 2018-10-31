package com.projeto.finaceiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.projeto.finaceiro.model.Lancamento;
import com.projeto.finaceiro.model.Pessoa;
import com.projeto.finaceiro.model.TipoLancamento;
import com.projeto.finaceiro.util.FacesUtil;
import com.projeto.finaceiro.util.HibernateUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Lancamento lancamento = new Lancamento();
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		
 Session session = (Session) FacesUtil.getRequestAttribute("session");
	
	//Session session = HibernateUtil.getSession();
		
	this.pessoas = session.createCriteria(Pessoa.class)
			.addOrder(Order.asc("nome"))
			.list();
	
	session.close();


	
	}
	public void lancamentoPagoModificado(ValueChangeEvent event){
		this.lancamento.setPago((Boolean)event.getNewValue());
		this.lancamento.setDataPagamento(null);
		FacesContext.getCurrentInstance().renderResponse();
	}

	public void cadastrar() {
		
		Session session = HibernateUtil.getSession();
		Transaction trx = session.beginTransaction();
		
		
		session.merge(this.lancamento);		
		
		trx.commit();
		session.close();
		
		
	

		this.lancamento = new Lancamento();
		
		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public List<Pessoa> getPessoa() {
		return pessoas;
	}
	
}