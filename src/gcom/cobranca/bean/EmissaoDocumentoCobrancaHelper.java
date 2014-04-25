package gcom.cobranca.bean;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaGrupo;

import java.io.Serializable;
import java.util.Date;

public class EmissaoDocumentoCobrancaHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CobrancaAcao acaoCobranca;
	
	private CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;
	
	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	
	private Date dataAtual;
	
	private CobrancaGrupo grupoCobranca;
	
	private CobrancaCriterio criterioCobranca;
	
	public EmissaoDocumentoCobrancaHelper(){}

	public CobrancaAcao getAcaoCobranca() {
		return acaoCobranca;
	}

	public void setAcaoCobranca(CobrancaAcao acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}

	public void setCobrancaAcaoAtividadeCronograma(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public CobrancaCriterio getCriterioCobranca() {
		return criterioCobranca;
	}

	public void setCriterioCobranca(CobrancaCriterio criterioCobranca) {
		this.criterioCobranca = criterioCobranca;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public CobrancaGrupo getGrupoCobranca() {
		return grupoCobranca;
	}

	public void setGrupoCobranca(CobrancaGrupo grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}
}
