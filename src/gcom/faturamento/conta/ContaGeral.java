package gcom.faturamento.conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaGeral implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private short indicadorHistorico;
	private Date ultimaAlteracao;
	private ContaImpressao contaImpressao;
	private Conta conta;
	private ContaHistorico contaHistorico;
	@SuppressWarnings("rawtypes")
	private Set debitoAutomaticoMovimentos;
	
    public final static short INDICADOR_HISTORICO = 1;

    public ContaGeral() {
    }

    @SuppressWarnings("rawtypes")
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

	@SuppressWarnings("rawtypes")
	public ContaGeral(Integer id, short indicadorHistorico,
			Date ultimaAlteracao, Set debitoAutomaticoMovimentos) {
		this.id = id;
		this.indicadorHistorico = indicadorHistorico;
		this.ultimaAlteracao = ultimaAlteracao;
		this.debitoAutomaticoMovimentos = debitoAutomaticoMovimentos;
	}

	public ContaGeral(Integer id) {
		this.id = id;
	}

	public ContaImpressao getContaImpressao() {
		return this.contaImpressao;
	}

	public void setContaImpressao(ContaImpressao contaImpressao) {
		this.contaImpressao = contaImpressao;
	}

	@SuppressWarnings("rawtypes")
	public Set getDebitoAutomaticoMovimentos() {
		return this.debitoAutomaticoMovimentos;
	}

	@SuppressWarnings("rawtypes")
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

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public void setConta(IConta conta) {
		this.conta = (Conta) conta;
	}

	public ContaHistorico getContaHistorico() {
		return contaHistorico;
	}

	public void setContaHistorico(ContaHistorico contaHistorico) {
		this.contaHistorico = contaHistorico;
	}
	
	public BigDecimal obterValorConta() {
		
		BigDecimal valorConta = null;
		
		if (conta != null) {
			valorConta = conta.getValorTotalContaBigDecimal(); 
		} else if (contaHistorico != null) {
			valorConta = contaHistorico.getValorTotalContaBigDecimal(); 
		}
		
		return valorConta;
	}
}
