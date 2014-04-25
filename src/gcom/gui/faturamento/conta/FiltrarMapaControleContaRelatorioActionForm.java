package gcom.gui.faturamento.conta;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarMapaControleContaRelatorioActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idGrupoFaturamento;
	
	private String mesAno;	

	private String firma;
	
	private String indicadorFichaCompensacao;
	
	private String tipoImpressao;
	
	/**
	 * @return Retorna o campo indicadorFichaCompensacao.
	 */
	public String getIndicadorFichaCompensacao() {
		return indicadorFichaCompensacao;
	}

	/**
	 * @param indicadorFichaCompensacao O indicadorFichaCompensacao a ser setado.
	 */
	public void setIndicadorFichaCompensacao(String indicadorFichaCompensacao) {
		this.indicadorFichaCompensacao = indicadorFichaCompensacao;
	}

	/**
	 * @return Retorna o campo firma.
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * @param firma O firma a ser setado.
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	public String getTipoImpressao() {
		return tipoImpressao;
	}

	public void setTipoImpressao(String tipoImpressao) {
		this.tipoImpressao = tipoImpressao;
	}

	

}
