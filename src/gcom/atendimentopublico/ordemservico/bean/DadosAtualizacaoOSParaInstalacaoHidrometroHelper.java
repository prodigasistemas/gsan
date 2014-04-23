package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC0460] Efetuar Instalação de Hidrômetro
 * 
 * Atualizar OS
 * 
 * @author Leonardo Regis
 * @date 19/09/2006
 */
public class DadosAtualizacaoOSParaInstalacaoHidrometroHelper {

	private Short indicadorComercial;
	private BigDecimal valorDebito;
	private ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo;
	private BigDecimal percentualCobranca;
	private Date ultimaAlteracao;
	private Integer qtdeParcelas;
	private BigDecimal valorParcela;
	private Integer idOs;	
	
	public Integer getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(Integer qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public DadosAtualizacaoOSParaInstalacaoHidrometroHelper(){}

	public Short getIndicadorComercial() {
		return indicadorComercial;
	}

	public void setIndicadorComercial(Short indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	public BigDecimal getPercentualCobranca() {
		return percentualCobranca;
	}

	public void setPercentualCobranca(BigDecimal percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}

	public ServicoNaoCobrancaMotivo getServicoNaoCobrancaMotivo() {
		return servicoNaoCobrancaMotivo;
	}

	public void setServicoNaoCobrancaMotivo(
			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo) {
		this.servicoNaoCobrancaMotivo = servicoNaoCobrancaMotivo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Integer getIdOs() {
		return idOs;
	}

	public void setIdOs(Integer idOs) {
		this.idOs = idOs;
	}

}
