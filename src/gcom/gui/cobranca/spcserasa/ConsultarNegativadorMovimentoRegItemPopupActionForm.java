package gcom.gui.cobranca.spcserasa;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0674] Pesquisar Movimento Negativador
 * 
 * @author Yara Taciane
 * @date 27/12/2007
 */
public class ConsultarNegativadorMovimentoRegItemPopupActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idNegativadorMovimentoRegItem;

	private String negativador;
	private String matriculaImovel;
	private String inscricao;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	/**
	 * @return Retorna o campo idNegativadorMovimentoRegItem.
	 */
	public String getIdNegativadorMovimentoRegItem() {
		return idNegativadorMovimentoRegItem;
	}

	/**
	 * @param idNegativadorMovimentoRegItem
	 *            O idNegativadorMovimentoRegItem a ser setado.
	 */
	public void setIdNegativadorMovimentoRegItem(
			String idNegativadorMovimentoRegItem) {
		this.idNegativadorMovimentoRegItem = idNegativadorMovimentoRegItem;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador
	 *            O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	
	
	
	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	/**
	 * @param matriculaImovel O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoAgua.
	 */
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	/**
	 * @param situacaoLigacaoAgua O situacaoLigacaoAgua a ser setado.
	 */
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoLigacaoEsgoto.
	 */
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	/**
	 * @param situacaoLigacaoEsgoto O situacaoLigacaoEsgoto a ser setado.
	 */
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.idNegativadorMovimentoRegItem = "";
		this.negativador = "";
		this.matriculaImovel= "";
		this.inscricao= "";
		this.situacaoLigacaoAgua= "";
		this.situacaoLigacaoEsgoto= "";

	}

}
