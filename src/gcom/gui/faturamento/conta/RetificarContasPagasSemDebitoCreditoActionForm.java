package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

/**
 * Retificação de um conjunto de contas que foram pagas e que o pagamento não estava o débito e/ou crédito (Conta paga via Impressão Simultânea) 
 *
 * @author Sávio Luiz
 * @date 12/27/2010
 */

public class RetificarContasPagasSemDebitoCreditoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String referenciaConta;
	private String idGrupo;
	private String idDebitoTipo;
	private String idCreditoTipo;
	private String quatidadeConta;
	
	public String getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getIdCreditoTipo() {
		return idCreditoTipo;
	}
	public void setIdCreditoTipo(String idCreditoTipo) {
		this.idCreditoTipo = idCreditoTipo;
	}
	public String getIdDebitoTipo() {
		return idDebitoTipo;
	}
	public void setIdDebitoTipo(String idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
	}
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
