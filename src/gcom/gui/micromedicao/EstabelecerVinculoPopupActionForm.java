package gcom.gui.micromedicao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Form do Estabelecer Vinculo Popup
 * 
 * @author Rafael Santos
 * @since 11/01/2006 [UC00098] Manter Vinculos Imoveis Rateio Consumo
 */
public class EstabelecerVinculoPopupActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String rateioTipoAgua;

	private String rateioTipoPoco;

	private String botao;

	private String codigoImovel;

	private String matriculaImovel;

	private String imoveisVinculados;

	private String imovel;

	private String indicadorImovelAreaComum;

	private boolean possuiImovelAreaComum = false;

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
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
	 * @return Returns the codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel
	 *            The codigoImovel to set.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Returns the imoveisVinculados.
	 */
	public String getImoveisVinculados() {
		return imoveisVinculados;
	}

	/**
	 * @param imoveisVinculados
	 *            The imoveisVinculados to set.
	 */
	public void setImoveisVinculados(String imoveisVinculados) {
		this.imoveisVinculados = imoveisVinculados;
	}

	/**
	 * @return Returns the imovel.
	 */
	public String getImovel() {
		return imovel;
	}

	/**
	 * @param imovel
	 *            The imovel to set.
	 */
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getIndicadorImovelAreaComum() {
		return indicadorImovelAreaComum;
	}

	public void setIndicadorImovelAreaComum(String indicadorImovelAreaComum) {
		this.indicadorImovelAreaComum = indicadorImovelAreaComum;
	}

	public boolean isPossuiImovelAreaComum() {
		return possuiImovelAreaComum;
	}

	public void setPossuiImovelAreaComum(boolean possuiImovelAreaComum) {
		this.possuiImovelAreaComum = possuiImovelAreaComum;
	}
}
