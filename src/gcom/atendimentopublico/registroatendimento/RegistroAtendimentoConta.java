package gcom.atendimentopublico.registroatendimento;

import java.util.Date;

import gcom.faturamento.conta.Conta;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

public class RegistroAtendimentoConta extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private RegistroAtendimentoContaPK comp_id;
	
	private Date ultimaAlteracao;
	
	private RegistroAtendimento registroAtendimento;
	
	private Conta conta;
	
	
	public RegistroAtendimentoContaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(RegistroAtendimentoContaPK comp_id) {
		this.comp_id = comp_id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		// TODO Auto-generated method stub
		return null;
	}
}
