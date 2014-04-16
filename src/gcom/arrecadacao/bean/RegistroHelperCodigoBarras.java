package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 [UC0264] - Distribuir Dados do Código de
 *          Barras
 */
public class RegistroHelperCodigoBarras implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoBarras() {
	}

	private String idProduto;

	private String idSegmento;

	private String idValorReal;

	private String digitoVerificadorGeral;

	private String valorPagamento;

	private String idEmpresa;

	private RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento;

	private String tipoPagamento;

	public String getDigitoVerificadorGeral() {
		return digitoVerificadorGeral;
	}

	public void setDigitoVerificadorGeral(String digitoVerificadorGeral) {
		this.digitoVerificadorGeral = digitoVerificadorGeral;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}

	public String getIdValorReal() {
		return idValorReal;
	}

	public void setIdValorReal(String idValorReal) {
		this.idValorReal = idValorReal;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public RegistroHelperCodigoBarrasTipoPagamento getRegistroHelperCodigoBarrasTipoPagamento() {
		return registroHelperCodigoBarrasTipoPagamento;
	}

	public void setRegistroHelperCodigoBarrasTipoPagamento(
			RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento) {
		this.registroHelperCodigoBarrasTipoPagamento = registroHelperCodigoBarrasTipoPagamento;
	}
	

}
