package gcom.cadastro.tarifasocial;

import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.Conta;
import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialCartaDebito extends ObjetoGcom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TarifaSocialCartaDebitoPK comp_id;
	private Integer referenciaConta;
	private BigDecimal valorConta;
	private Date dataVencimentoConta;
	private Date ultimaAlteracao;
	private Imovel imovel;
	private Conta conta;
	private TarifaSocialComandoCarta tarifaSocialComandoCarta;
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TarifaSocialCartaDebitoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(TarifaSocialCartaDebitoPK comp_id) {
		this.comp_id = comp_id;
	}

	public Date getDataVencimentoConta() {
		return dataVencimentoConta;
	}

	public void setDataVencimentoConta(Date dataVencimentoConta) {
		this.dataVencimentoConta = dataVencimentoConta;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Integer getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(Integer referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

	public TarifaSocialComandoCarta getTarifaSocialComandoCarta() {
		return tarifaSocialComandoCarta;
	}

	public void setTarifaSocialComandoCarta(
			TarifaSocialComandoCarta tarifaSocialComandoCarta) {
		this.tarifaSocialComandoCarta = tarifaSocialComandoCarta;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

}
