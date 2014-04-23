package gcom.arrecadacao.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Movimento do Tipo 1 do TXT do retorno do cartão de crédito
 * 
 * @author Raphael Rossiter
 * @date 29/01/2010
 */
public class RegistroCartaoCreditoTipo2Helper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private String estabelecimentoSubmissor;
	
	private String numeroResumoOperacao;
	
	private String numeroCartao;
	
	private Date dataCompraAjuste;
	
	private String sinalValorCompraParcela;
	
	private BigDecimal valorCompraParcela;
	
	private Integer parcela;
	
	private Integer totalParcelas;
	
	private String motivoRejeicao;
	
	private String codigoAutorizacao;
	
	private String tid;
	
	private String nsu;
	
	private BigDecimal valorTrocoFacil;
	
	private String numeroDigitosCartao;
	
	private String numeroNotaFiscal;
	
	private String codigoPaisEmissorCartao;
	
	private String numeroLogicoTerminal;
	
	private String identificadorTaxaEmbarqueOUValorEntrada;
	
	
	//CONSTANTE TIPO MOVIMENTO 2
	public final static Short CODIGO_MOVIMENTO_TIPO_2 = new Short("2");
	
	
	public RegistroCartaoCreditoTipo2Helper(){}


	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}


	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}


	public String getCodigoPaisEmissorCartao() {
		return codigoPaisEmissorCartao;
	}


	public void setCodigoPaisEmissorCartao(String codigoPaisEmissorCartao) {
		this.codigoPaisEmissorCartao = codigoPaisEmissorCartao;
	}


	public Date getDataCompraAjuste() {
		return dataCompraAjuste;
	}


	public void setDataCompraAjuste(Date dataCompraAjuste) {
		this.dataCompraAjuste = dataCompraAjuste;
	}


	public String getEstabelecimentoSubmissor() {
		return estabelecimentoSubmissor;
	}


	public void setEstabelecimentoSubmissor(String estabelecimentoSubmissor) {
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
	}


	public String getIdentificadorTaxaEmbarqueOUValorEntrada() {
		return identificadorTaxaEmbarqueOUValorEntrada;
	}


	public void setIdentificadorTaxaEmbarqueOUValorEntrada(
			String identificadorTaxaEmbarqueOUValorEntrada) {
		this.identificadorTaxaEmbarqueOUValorEntrada = identificadorTaxaEmbarqueOUValorEntrada;
	}


	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}


	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}


	public String getNsu() {
		return nsu;
	}


	public void setNsu(String nsu) {
		this.nsu = nsu;
	}


	public String getNumeroCartao() {
		return numeroCartao;
	}


	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}


	public String getNumeroDigitosCartao() {
		return numeroDigitosCartao;
	}


	public void setNumeroDigitosCartao(String numeroDigitosCartao) {
		this.numeroDigitosCartao = numeroDigitosCartao;
	}


	public String getNumeroLogicoTerminal() {
		return numeroLogicoTerminal;
	}


	public void setNumeroLogicoTerminal(String numeroLogicoTerminal) {
		this.numeroLogicoTerminal = numeroLogicoTerminal;
	}


	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}


	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}


	public String getNumeroResumoOperacao() {
		return numeroResumoOperacao;
	}


	public void setNumeroResumoOperacao(String numeroResumoOperacao) {
		this.numeroResumoOperacao = numeroResumoOperacao;
	}


	public String getSinalValorCompraParcela() {
		return sinalValorCompraParcela;
	}


	public void setSinalValorCompraParcela(String sinalValorCompraParcela) {
		this.sinalValorCompraParcela = sinalValorCompraParcela;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public Short getTipoRegistro() {
		return tipoRegistro;
	}


	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}


	public BigDecimal getValorCompraParcela() {
		return valorCompraParcela;
	}


	public void setValorCompraParcela(BigDecimal valorCompraParcela) {
		this.valorCompraParcela = valorCompraParcela;
	}


	public BigDecimal getValorTrocoFacil() {
		return valorTrocoFacil;
	}


	public void setValorTrocoFacil(BigDecimal valorTrocoFacil) {
		this.valorTrocoFacil = valorTrocoFacil;
	}


	public Integer getParcela() {
		return parcela;
	}


	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}


	public Integer getTotalParcelas() {
		return totalParcelas;
	}


	public void setTotalParcelas(Integer totalParcelas) {
		this.totalParcelas = totalParcelas;
	}
}
