package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

public class CadastrarMensagemAcompanhamentoServicoPopupActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String mensagem;

	private String[] ids;

	/**
	 * @return Returns the mensagem.
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem
	 *            The mensagem to set.
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return Returns the ids.
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            The ids to set.
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
