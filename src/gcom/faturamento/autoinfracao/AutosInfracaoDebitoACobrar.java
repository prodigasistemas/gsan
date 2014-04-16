package gcom.faturamento.autoinfracao;

import gcom.faturamento.debito.DebitoACobrarGeral;

import java.io.Serializable;
import java.util.Date;

public class AutosInfracaoDebitoACobrar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private AutosInfracao autosInfracao;
	
	private DebitoACobrarGeral debitoACobrarGeral;
	
	private Date ultimaAlteracao;
	
	private short indicadorMultaInfracao;

	public AutosInfracaoDebitoACobrar(){}

	public AutosInfracao getAutosInfracao() {
		return autosInfracao;
	}

	public void setAutosInfracao(AutosInfracao autosInfracao) {
		this.autosInfracao = autosInfracao;
	}


	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorMultaInfracao() {
		return indicadorMultaInfracao;
	}

	public void setIndicadorMultaInfracao(short indicadorMultaInfracao) {
		this.indicadorMultaInfracao = indicadorMultaInfracao;
	}
	
	
}
