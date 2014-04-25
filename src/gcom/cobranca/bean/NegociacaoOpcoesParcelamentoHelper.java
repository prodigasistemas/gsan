package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.ParcelamentoPerfil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * [UC0214] Efetuar Parcelamento de Débitos 
 *
 * @author Roberta Costa
 * @date 28/03/2006
 */
public class NegociacaoOpcoesParcelamentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public NegociacaoOpcoesParcelamentoHelper() {
	}

	private Collection<OpcoesParcelamentoHelper> opcoesParcelamento;
	
	private BigDecimal valorEntradaMinima;
	
	private BigDecimal valorDescontoAcrecismosImpotualidade;

	private BigDecimal valorDescontoInatividade;

	private BigDecimal valorDescontoAntiguidade;
	
	private BigDecimal percentualDescontoAcrescimosImpontualidade;
	
	private BigDecimal percentualDescontoAntiguidadeDebito;
	
	private BigDecimal percentualDescontoInatividadeLigacaoAgua;
	
	private ParcelamentoPerfil parcelamentoPerfil;
	
	private BigDecimal valorDescontoSancoesRDEspecial;
	
	private BigDecimal valorDescontoTarifaSocialRDEspecial;
	
	private BigDecimal valorTotalDescontoPagamentoAVista;
	
	private Collection colecaoContasEmAntiguidade;
	
	private Collection colecaoContasParaParcelamento;

	private Short indicadorExisteParcelamentoEmAndamento;
	
	private BigDecimal valorDebitoDesconto;
	
	/**
	 * Valor minímo da prestação para usar na aba de negociação
	 */
	private BigDecimal valorMinimoPrestacao;
	
	private BigDecimal percentualDescontoInatividadeAvistaLigacaoAgua;
	/**
	 * @return Retorna o campo valorEntradaMinima.
	 */
	public BigDecimal getValorEntradaMinima() {
		return valorEntradaMinima;
	}

	/**
	 * @param valorEntradaMinima O valorEntradaMinima a ser setado.
	 */
	public void setValorEntradaMinima(BigDecimal valorEntradaMinima) {
		this.valorEntradaMinima = valorEntradaMinima;
	}

	/**
	 * @return Retorna o campo valorDescontoAcrecismosImpotualidade.
	 */
	public BigDecimal getValorDescontoAcrecismosImpotualidade() {
		return valorDescontoAcrecismosImpotualidade;
	}

	/**
	 * @param valorDescontoAcrecismosImpotualidade O valorDescontoAcrecismosImpotualidade a ser setado.
	 */
	public void setValorDescontoAcrecismosImpotualidade(
			BigDecimal valorDescontoAcrecismosImpotualidade) {
		this.valorDescontoAcrecismosImpotualidade = valorDescontoAcrecismosImpotualidade;
	}

	/**
	 * @return Retorna o campo valorDescontoAntiguidade.
	 */
	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	/**
	 * @param valorDescontoAntiguidade O valorDescontoAntiguidade a ser setado.
	 */
	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	/**
	 * @return Retorna o campo valorDescontoInatividade.
	 */
	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	/**
	 * @param valorDescontoInatividade O valorDescontoInatividade a ser setado.
	 */
	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	/**
	 * @return Retorna o campo opcoesParcelamento.
	 */
	public Collection<OpcoesParcelamentoHelper> getOpcoesParcelamento() {
		return opcoesParcelamento;
	}

	/**
	 * @param opcoesParcelamento O opcoesParcelamento a ser setado.
	 */
	public void setOpcoesParcelamento(
			Collection<OpcoesParcelamentoHelper> opcoesParcelamento) {
		this.opcoesParcelamento = opcoesParcelamento;
	}

	/**
	 * @return Retorna o campo parcelamentoPerfil.
	 */
	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	/**
	 * @param parcelamentoPerfil O parcelamentoPerfil a ser setado.
	 */
	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	/**
	 * @return Retorna o campo percentualDescontoAcrescimosImpontualidade.
	 */
	public BigDecimal getPercentualDescontoAcrescimosImpontualidade() {
		return percentualDescontoAcrescimosImpontualidade;
	}

	/**
	 * @param percentualDescontoAcrescimosImpontualidade O percentualDescontoAcrescimosImpontualidade a ser setado.
	 */
	public void setPercentualDescontoAcrescimosImpontualidade(
			BigDecimal percentualDescontoAcrescimosImpontualidade) {
		this.percentualDescontoAcrescimosImpontualidade = percentualDescontoAcrescimosImpontualidade;
	}

	/**
	 * @return Retorna o campo percentualDescontoAntiguidadeDebito.
	 */
	public BigDecimal getPercentualDescontoAntiguidadeDebito() {
		return percentualDescontoAntiguidadeDebito;
	}

	/**
	 * @param percentualDescontoAntiguidadeDebito O percentualDescontoAntiguidadeDebito a ser setado.
	 */
	public void setPercentualDescontoAntiguidadeDebito(
			BigDecimal percentualDescontoAntiguidadeDebito) {
		this.percentualDescontoAntiguidadeDebito = percentualDescontoAntiguidadeDebito;
	}

	/**
	 * @return Retorna o campo percentualDescontoInatividadeLigacaoAgua.
	 */
	public BigDecimal getPercentualDescontoInatividadeLigacaoAgua() {
		return percentualDescontoInatividadeLigacaoAgua;
	}

	/**
	 * @param percentualDescontoInatividadeLigacaoAgua O percentualDescontoInatividadeLigacaoAgua a ser setado.
	 */
	public void setPercentualDescontoInatividadeLigacaoAgua(
			BigDecimal percentualDescontoInatividadeLigacaoAgua) {
		this.percentualDescontoInatividadeLigacaoAgua = percentualDescontoInatividadeLigacaoAgua;
	}
	
	/**
	 * @return Retorna o campo valorMinimoPrestacao.
	 */
	public BigDecimal getValorMinimoPrestacao() {
		return valorMinimoPrestacao;
	}

	/**
	 * @param valorMinimoPrestacao O valorMinimoPrestacao a ser setado.
	 */
	public void setValorMinimoPrestacao(BigDecimal valorMinimoPrestacao) {
		this.valorMinimoPrestacao = valorMinimoPrestacao;
	}

	public BigDecimal getValorDescontoSancoesRDEspecial() {
		return valorDescontoSancoesRDEspecial;
	}

	public void setValorDescontoSancoesRDEspecial(
			BigDecimal valorDescontoSancoesRDEspecial) {
		this.valorDescontoSancoesRDEspecial = valorDescontoSancoesRDEspecial;
	}

	/**
	 * @return Retorna o campo valorDescontoTarifaSocialRDEspecial.
	 */
	public BigDecimal getValorDescontoTarifaSocialRDEspecial() {
		return valorDescontoTarifaSocialRDEspecial;
	}

	/**
	 * @param valorDescontoTarifaSocialRDEspecial O valorDescontoTarifaSocialRDEspecial a ser setado.
	 */
	public void setValorDescontoTarifaSocialRDEspecial(
			BigDecimal valorDescontoTarifaSocialRDEspecial) {
		this.valorDescontoTarifaSocialRDEspecial = valorDescontoTarifaSocialRDEspecial;
	}

	public BigDecimal getValorTotalDescontoPagamentoAVista() {
		return valorTotalDescontoPagamentoAVista;
	}

	public void setValorTotalDescontoPagamentoAVista(
			BigDecimal valorTotalDescontoPagamentoAVista) {
		this.valorTotalDescontoPagamentoAVista = valorTotalDescontoPagamentoAVista;
	}

	public Collection getColecaoContasEmAntiguidade() {
		return colecaoContasEmAntiguidade;
	}

	public void setColecaoContasEmAntiguidade(Collection colecaoContasEmAntiguidade) {
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
	}

	public Collection getColecaoContasParaParcelamento() {
		return colecaoContasParaParcelamento;
	}

	public void setColecaoContasParaParcelamento(
			Collection colecaoContasParaParcelamento) {
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}

	public Short getIndicadorExisteParcelamentoEmAndamento() {
		return indicadorExisteParcelamentoEmAndamento;
	}

	public void setIndicadorExisteParcelamentoEmAndamento(
			Short indicadorExisteParcelamentoEmAndamento) {
		this.indicadorExisteParcelamentoEmAndamento = indicadorExisteParcelamentoEmAndamento;
	}

	public BigDecimal getPercentualDescontoInatividadeAvistaLigacaoAgua() {
		return percentualDescontoInatividadeAvistaLigacaoAgua;
	}

	public void setPercentualDescontoInatividadeAvistaLigacaoAgua(
			BigDecimal percentualDescontoInatividadeAvistaLigacaoAgua) {
		this.percentualDescontoInatividadeAvistaLigacaoAgua = percentualDescontoInatividadeAvistaLigacaoAgua;
	}

	public BigDecimal getValorDebitoDesconto() {
		return valorDebitoDesconto;
	}

	public void setValorDebitoDesconto(BigDecimal valorDebitoDesconto) {
		this.valorDebitoDesconto = valorDebitoDesconto;
	}
}
