package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class RetirarDebitoCobradoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    private String idMotivoRetificacao;
    private String idTipoDebito;
    private String descricaoDebito;
	/**
	 * @return Retorna o campo descricaoDebito.
	 */
	public String getDescricaoDebito() {
		return descricaoDebito;
	}
	/**
	 * @param descricaoDebito O descricaoDebito a ser setado.
	 */
	public void setDescricaoDebito(String descricaoDebito) {
		this.descricaoDebito = descricaoDebito;
	}
	/**
	 * @return Retorna o campo idMotivoRetificacao.
	 */
	public String getIdMotivoRetificacao() {
		return idMotivoRetificacao;
	}
	/**
	 * @param idMotivoRetificacao O idMotivoRetificacao a ser setado.
	 */
	public void setIdMotivoRetificacao(String idMotivoRetificacao) {
		this.idMotivoRetificacao = idMotivoRetificacao;
	}
	/**
	 * @return Retorna o campo idTipoDebito.
	 */
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	/**
	 * @param idTipoDebito O idTipoDebito a ser setado.
	 */
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
    
}
