package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

/**
 * Inserir Débitos para as contas impressas via Impressão Simultânea de Contas que sairam com o valor da conta errada (Alguns grupos com tarifa proporcional
 *  que não estava levando em consideração a quantidade de economias)
 *
 * @author Sávio Luiz
 * @date 12/01/2011
 */

public class InserirDebitosContasComValorFaixasErradasActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String referenciaConta;
	private String quatidadeConta;
	
	public String getReferenciaConta() {
		return referenciaConta;
	}
	public void setReferenciaConta(String referenciaConta) {
		this.referenciaConta = referenciaConta;
	}
	public String getQuatidadeConta() {
		return quatidadeConta;
	}
	public void setQuatidadeConta(String quatidadeConta) {
		this.quatidadeConta = quatidadeConta;
	}
	
}
