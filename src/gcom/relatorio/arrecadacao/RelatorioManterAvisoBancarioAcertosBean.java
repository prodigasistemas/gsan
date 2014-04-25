package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * @author Rafael Corrêa
 * @date 01/09/2006
 */
public class RelatorioManterAvisoBancarioAcertosBean implements RelatorioBean {

	private String numeroDocumento;

	private String agencia;

	private String banco;

	private String numeroConta;

	private String data;

	private String tipo;

	private String valor;

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean
	 * 
	 * @param contaIndicadorContaRevisao
	 * @param contaMesAnoReferenciaConta
	 * @param contaDataVencimentoConta
	 * @param contaValorOriginal
	 * @param contaValorTotalOriginal
	 * @param contaCodigoBarras
	 * @param contaValorTotalAtualizado
	 * @param jrColecaoRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 * @param arrayRelatorioGerarRelacaoDebitosTipoDebitoCreditoBean
	 */
	public RelatorioManterAvisoBancarioAcertosBean(String numeroDocumento,
			String banco, String agencia, String numeroConta, String data,
			String valor, String tipo) {
		this.numeroDocumento = numeroDocumento;
		this.banco = banco;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.data = data;
		this.valor = valor;
		this.tipo = tipo;

	}

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean
	 * 
	 */
	public RelatorioManterAvisoBancarioAcertosBean() {

	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
