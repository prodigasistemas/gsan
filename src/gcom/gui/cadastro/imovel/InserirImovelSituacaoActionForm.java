package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Rômulo Aurélio
 * @created 24 de Março de 2006
 */
public class InserirImovelSituacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String imovelSituacaoTipo;

	private String ligacaoAguaSituacao;

	private String ligacaoEsgotoSituacao;

	/**
	 * @return Retorna o campo imovelSituacaoTipo.
	 */
	public String getImovelSituacaoTipo() {
		return imovelSituacaoTipo;
	}

	/**
	 * @param imovelSituacaoTipo
	 *            O imovelSituacaoTipo a ser setado.
	 */
	public void setImovelSituacaoTipo(String imovelSituacaoTipo) {
		this.imovelSituacaoTipo = imovelSituacaoTipo;
	}

	/**
	 * @return Retorna o campo ligacaoAguaSituacao.
	 */
	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	/**
	 * @param ligacaoAguaSituacao
	 *            O ligacaoAguaSituacao a ser setado.
	 */
	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacao.
	 */
	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	/**
	 * @param ligacaoEsgotoSituacao
	 *            O ligacaoEsgotoSituacao a ser setado.
	 */
	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

}
