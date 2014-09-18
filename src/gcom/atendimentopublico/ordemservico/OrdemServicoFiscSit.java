package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OrdemServicoFiscSit implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Date dataFiscalizacaoSituacao;

	/** persistent field */
	private OrdemServico ordemServico;

	/** persistent field */
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	
	private Short indicadorDebito;

	public OrdemServicoFiscSit() {
		super();
		
	}

	public OrdemServicoFiscSit(Integer id, Date ultimaAlteracao, Date dataFiscalizacaoSituacao, OrdemServico ordemServico, FiscalizacaoSituacao fiscalizacaoSituacao) {
		super();
		
		this.id = id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataFiscalizacaoSituacao = dataFiscalizacaoSituacao;
		this.ordemServico = ordemServico;
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}
	
	public Date getDataFiscalizacaoSituacao() {
		return dataFiscalizacaoSituacao;
	}

	public void setDataFiscalizacaoSituacao(Date dataFiscalizacaoSituacao) {
		this.dataFiscalizacaoSituacao = dataFiscalizacaoSituacao;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof OrdemServicoFiscSit)) {
			return false;
		}
		OrdemServicoFiscSit castOther = (OrdemServicoFiscSit) other;

		return ((this.getId().equals(castOther.getId()))
		&& (this.getUltimaAlteracao().equals(castOther.getUltimaAlteracao()))
		&& (this.getFiscalizacaoSituacao().getId().equals(castOther.getFiscalizacaoSituacao().getId()))
		&& (this.getDataFiscalizacaoSituacao().equals(castOther.getDataFiscalizacaoSituacao())) 
		&& (this.getIndicadorDebito().equals(castOther.getIndicadorDebito())) 
		&& (this.getOrdemServico().getId().equals(castOther.getOrdemServico().getId())));
	}

	public Short getIndicadorDebito() {
		return indicadorDebito;
	}

	public void setIndicadorDebito(Short indicadorDebito) {
		this.indicadorDebito = indicadorDebito;
	}

	
}
