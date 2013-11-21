package gcom.relatorio.cobranca.contratoparcelamento;

import java.io.Serializable;

public class EmitirExtratoContratoParcelamentoPorClienteHelper implements Serializable {
	private static final long serialVersionUID = 1L;

	private String numeroContrato;
	
	private String codigoCliente;
	
	private String nomeCliente;
	
	private String cnpjCliente;
	
	private String dataImplantacao;
	
	private String parcelaEmissao;
	
	private Integer inicioParcelas;
	
	private Integer fimParcelas;
	
	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(String dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public Integer getFimParcelas() {
		return fimParcelas;
	}

	public void setFimParcelas(Integer fimParcelas) {
		this.fimParcelas = fimParcelas;
	}

	public Integer getInicioParcelas() {
		return inicioParcelas;
	}

	public void setInicioParcelas(Integer inicioParcelas) {
		this.inicioParcelas = inicioParcelas;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getParcelaEmissao() {
		return parcelaEmissao;
	}

	public void setParcelaEmissao(String parcelaEmissao) {
		this.parcelaEmissao = parcelaEmissao;
	}


}
