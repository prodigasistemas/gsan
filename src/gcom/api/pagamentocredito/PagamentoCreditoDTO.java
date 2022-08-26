package gcom.api.pagamentocredito;

import java.math.BigDecimal;

public class PagamentoCreditoDTO {
	
	String Matricula;
	
	String cadastroCliente;
	
	String identificadorConta;
	
	BigDecimal valorTotalConta;	
	
	String codigoBarras;
	
	String dataVencimento;
	

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorTotalConta() {
		return valorTotalConta;
	}

	public void setValorTotalConta(BigDecimal valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}

	public String getCadastroCliente() {
		return cadastroCliente;
	}

	public void setCadastroCliente(String cadastroCliente) {
		this.cadastroCliente = cadastroCliente;
	}

	public String getIdentificadorConta() {
		return identificadorConta;
	}

	public void setIdentificadorConta(String identificadorConta) {
		this.identificadorConta = identificadorConta;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	
	
}
