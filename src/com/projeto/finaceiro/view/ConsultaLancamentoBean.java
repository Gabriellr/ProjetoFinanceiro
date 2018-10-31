package com.projeto.finaceiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.projeto.finaceiro.model.Lancamento;
import com.projeto.finaceiro.util.FacesUtil;
import com.projeto.finaceiro.util.HibernateUtil;

@SuppressWarnings("serial")
@ManagedBean
public class ConsultaLancamentoBean implements Serializable {

	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSelecionado;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar() {
		Session session = (Session) FacesUtil.getRequestAttribute("session");
		this.lancamentos =session.createCriteria(Lancamento.class)
				.addOrder(Order.desc("dataVencimento"))
				.list();
	}

	public void excluir(){
		if(this.lancamentoSelecionado.isPago()){
			FacesUtil.addicionarMensagem(FacesMessage.SEVERITY_ERROR, "Lancamento ja foi pago e não pode ser excluido!");
		}else{
			Session session = (Session) FacesUtil.getRequestAttribute("session");
			session.delete(this.lancamentoSelecionado);
			
			this.inicializar();


			FacesUtil.addicionarMensagem(FacesMessage.SEVERITY_INFO, "Lançamento excluido com sucesso!");

		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

}