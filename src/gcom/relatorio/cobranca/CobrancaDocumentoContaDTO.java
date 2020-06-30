package gcom.relatorio.cobranca;

public class CobrancaDocumentoContaDTO {

	private String referencia;
	private String vencimento;
	private String valor;

	public CobrancaDocumentoContaDTO() {
		super();
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}