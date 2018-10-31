package com.projeto.finaceiro.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import com.projeto.finaceiro.model.Lancamento;
import com.projeto.finaceiro.repository.Lancamentos;
import com.projeto.finaceiro.util.FacesUtil;
import com.projeto.finaceiro.util.Repositorios;

@SuppressWarnings("serial")
@ManagedBean
public class ConsultaLancamentoBean implements Serializable {

	private Repositorios repositorios = new Repositorios();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSelecionado;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar() {
		Lancamentos lancamentos = this.repositorios.getLancamentos();
		this.lancamentos = lancamentos.todos();
	}

	public void excluir(){
		if(this.lancamentoSelecionado.isPago()){
			FacesUtil.addicionarMensagem(FacesMessage.SEVERITY_ERROR, "Lancamento ja foi pago e não pode ser excluido!");
		}else{
			Lancamentos lancamentos = this.repositorios.getLancamentos();
			lancamentos.remover(this.lancamentoSelecionado);

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