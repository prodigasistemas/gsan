package gcom.gui.micromedicao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Form do Atualizar Tipo de RateioPopup [UC00098] Manter Vinculos Imoveis
 * Rateio Consumo
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 11/01/2006, 16/08/2011
 */
public class AtualizarTipoRateioPopupActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String rateioTipoAgua;

	private String rateioTipoPoco;

	private String botao;

	private String matriculaImovelAreaComum;

	private String inscricaoImovelAreaComum;

	private String matriculaImovelAreaComumAtual;

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Returns the rateioTipoAgua.
	 */
	public String getRateioTipoAgua() {
		return rateioTipoAgua;
	}

	/**
	 * @param rateioTipoAgua
	 *            The rateioTipoAgua to set.
	 */
	public void setRateioTipoAgua(String rateioTipoAgua) {
		this.rateioTipoAgua = rateioTipoAgua;
	}

	/**
	 * @return Returns the rateioTipoPoco.
	 */
	public String getRateioTipoPoco() {
		return rateioTipoPoco;
	}

	/**
	 * @param rateioTipoPoco
	 *            The rateioTipoPoco to set.
	 */
	public void setRateioTipoPoco(String rateioTipoPoco) {
		this.rateioTipoPoco = rateioTipoPoco;
	}

	/**
	 * @return Returns the botao.
	 */
	public String getBotao() {
		return botao;
	}

	/**
	 * @param botao
	 *            The botao to set.
	 */
	public void setBotao(String botao) {
		this.botao = botao;
	}

	/**
	 * @return Returns the matriculaImovelAreaComum.
	 */
	public String getMatriculaImovelAreaComum() {
		return matriculaImovelAreaComum;
	}

	/**
	 * @param matriculaImovelAreaComum
	 *            The matriculaImovelAreaComum to set.
	 */
	public void setMatriculaImovelAreaComum(String matriculaImovelAreaComum) {
		this.matriculaImovelAreaComum = matriculaImovelAreaComum;
	}

	/**
	 * @return Returns the inscricaoImovelAreaComum.
	 */
	public String getInscricaoImovelAreaComum() {
		return inscricaoImovelAreaComum;
	}

	/**
	 * @param inscricaoImovelAreaComum
	 *            The inscricaoImovelAreaComum to set.
	 */
	public void setInscricaoImovelAreaComum(String inscricaoImovelAreaComum) {
		this.inscricaoImovelAreaComum = inscricaoImovelAreaComum;
	}

	/**
	 * @return Returns the matriculaImovelAreaComumAtual.
	 */
	public String getMatriculaImovelAreaComumAtual() {
		return matriculaImovelAreaComumAtual;
	}

	/**
	 * @param matriculaImovelAreaComumAtual
	 *            The matriculaImovelAreaComumAtual to set.
	 */
	public void setMatriculaImovelAreaComumAtual(String matriculaImovelAreaComumAtual) {
		this.matriculaImovelAreaComumAtual = matriculaImovelAreaComumAtual;
	}
}
