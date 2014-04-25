package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;

/**
 * @author Rafael Corrêa
 * @date 01/09/2006
 */
public class RelatorioManterAvisoBancarioDeducoesBean implements RelatorioBean {

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
	public RelatorioManterAvisoBancarioDeducoesBean(String tipo, String valor) {
		this.tipo = tipo;
		this.valor = valor;

	}

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosContasBean
	 * 
	 */
	public RelatorioManterAvisoBancarioDeducoesBean() {

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
