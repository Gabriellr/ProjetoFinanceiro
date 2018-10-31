package com.projeto.finaceiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.projeto.finaceiro.model.Lancamento;
import com.projeto.finaceiro.model.Pessoa;
import com.projeto.finaceiro.model.TipoLancamento;
import com.projeto.finaceiro.repository.Lancamentos;
import com.projeto.finaceiro.repository.Pessoas;
import com.projeto.finaceiro.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	
    private Repositorios repositorios = new Repositorios();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Lancamento lancamento = new Lancamento();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
      
		Pessoas pessoas = this.repositorios.getPessoas();
        this.pessoas = pessoas.todas();

	}
	public void lancamentoPagoModificado(ValueChangeEvent event){
		this.lancamento.setPago((Boolean)event.getNewValue());
		this.lancamento.setDataPagamento(null);
		FacesContext.getCurrentInstance().renderResponse();
	}

	public void cadastrar() {
		Lancamentos lancamentos = this.repositorios.getLancamentos();
		lancamentos.guardar(this.lancamento);
		
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