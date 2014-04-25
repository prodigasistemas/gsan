package gcom.faturamento.conta;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ContaGeral implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** persistent field */
	private short indicadorHistorico;

	/** persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private gcom.faturamento.conta.ContaImpressao contaImpressao;

	/** persistent field */
	private Set debitoAutomaticoMovimentos;

	/** nullable persistent field */
	private gcom.faturamento.conta.Conta conta;

	/** nullable persistent field */
	private ContaHistorico contaHistorico;
	
	 /**
     * Description of the Field
     */

    public final static short INDICADOR_HISTORICO = 1;

	/** full constructor */
	public ContaGeral(Integer id, short indicadorHistorico,
			Date ultimaAlteracao, ContaImpressao contaImpressao,
			Set debitoAutomaticoMovimentos, Conta conta) {
		this.id = id;
		this.indicadorHistorico = indicadorHistorico;
		this.ultimaAlteracao = ultimaAlteracao;
		this.contaImpressao = contaImpressao;
		this.debitoAutomaticoMovimentos = debitoAutomaticoMovimentos;
		this.conta = conta;
	}

	/** default constructor */
	public ContaGeral() {
	}

	/** minimal constructor */
	public ContaGeral(Integer id, short indicadorHistorico,
			Date ultimaAlteracao, Set debitoAutomaticoMovimentos) {
		this.id = id;
		this.indicadorHistorico = indicadorHistorico;
		this.ultimaAlteracao = ultimaAlteracao;
		this.debitoAutomaticoMovimentos = debitoAutomaticoMovimentos;
	}

	public ContaImpressao getContaImpressao() {
		return this.contaImpressao;
	}

	public void setContaImpressao(ContaImpressao contaImpressao) {
		this.contaImpressao = contaImpressao;
	}

	public Set getDebitoAutomaticoMovimentos() {
		return this.debitoAutomaticoMovimentos;
	}

	public void setDebitoAutomaticoMovimentos(Set debitoAutomaticoMovimentos) {
		this.debitoAutomaticoMovimentos = debitoAutomaticoMovimentos;
	}

	public String toString() {
		return new ToStringBuilder(this).append("cntaId", getId()).toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorHistorico() {
		return indicadorHistorico;
	}

	public void setIndicadorHistorico(short indicadorHistorico) {
		this.indicadorHistorico = indicadorHistorico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public gcom.faturamento.conta.Conta getConta() {
		return conta;
	}

	public void setConta(gcom.faturamento.conta.Conta conta) {
		this.conta = conta;
	}
	
	public void setConta(gcom.faturamento.conta.IConta conta) {
		this.conta = (Conta) conta;
	}

	public ContaHistorico getContaHistorico() {
		return contaHistorico;
	}

	public void setContaHistorico(ContaHistorico contaHistorico) {
		this.contaHistorico = contaHistorico;
	}
	
	
}
