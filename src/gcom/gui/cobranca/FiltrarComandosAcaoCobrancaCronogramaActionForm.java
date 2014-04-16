package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0326] Filtrar Comandos de Ação de Cobrança
 * @author Rafael Santos
 * @since 08/05/2006
 */
public class FiltrarComandosAcaoCobrancaCronogramaActionForm  extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String[] grupoCobranca;
	
	private String[] acaoCobranca;
	
	private String[] atividadeCobranca;
	
	private String periodoReferenciaCobrancaInicial;
	
	private String periodoReferenciaCobrancaFinal;
	
	private String periodoPrevisaoComandoInicial;
	
	private String periodoPrevisaoComandoFinal;
	
	private String periodoComandoInicial;
	
	private String periodoComandoFinal;
	
	private String periodoRealizacaoComandoInicial;
	
	private String periodoRealizacaoComandoFinal;
	
	private String intervaloQuantidadeDocumentosInicial;
	
	private String intervaloQuantidadeDocumentosFinal;
	
	private String intervaloValorDocumentosInicial;
	
	private String intervaloValorDocumentosFinal;
		
	private String intervaloQuantidadeItensDocumentosInicial;
	
	private String intervaloQuantidadeItensDocumentosFinal;
	
	private String situacaoCronograma;
	
	private String situacaoComando;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Retorna o campo periodoReferenciaCobrancaFinal.
	 */
	public String getPeriodoReferenciaCobrancaFinal() {
		return periodoReferenciaCobrancaFinal;
	}

	/**
	 * @param periodoReferenciaCobrancaFinal O periodoReferenciaCobrancaFinal a ser setado.
	 */
	public void setPeriodoReferenciaCobrancaFinal(
			String periodoReferenciaCobrancaFinal) {
		this.periodoReferenciaCobrancaFinal = periodoReferenciaCobrancaFinal;
	}

	/**
	 * @return Retorna o campo periodoReferenciaCobrancaInicial.
	 */
	public String getPeriodoReferenciaCobrancaInicial() {
		return periodoReferenciaCobrancaInicial;
	}

	/**
	 * @param periodoReferenciaCobrancaInicial O periodoReferenciaCobrancaInicial a ser setado.
	 */
	public void setPeriodoReferenciaCobrancaInicial(
			String periodoReferenciaCobrancaInicial) {
		this.periodoReferenciaCobrancaInicial = periodoReferenciaCobrancaInicial;
	}

	/**
	 * @return Retorna o campo periodoPrevisaoComandoFinal.
	 */
	public String getPeriodoPrevisaoComandoFinal() {
		return periodoPrevisaoComandoFinal;
	}

	/**
	 * @param periodoPrevisaoComandoFinal O periodoPrevisaoComandoFinal a ser setado.
	 */
	public void setPeriodoPrevisaoComandoFinal(String periodoPrevisaoComandoFinal) {
		this.periodoPrevisaoComandoFinal = periodoPrevisaoComandoFinal;
	}

	/**
	 * @return Retorna o campo periodoPrevisaoComandoInicial.
	 */
	public String getPeriodoPrevisaoComandoInicial() {
		return periodoPrevisaoComandoInicial;
	}

	/**
	 * @param periodoPrevisaoComandoInicial O periodoPrevisaoComandoInicial a ser setado.
	 */
	public void setPeriodoPrevisaoComandoInicial(
			String periodoPrevisaoComandoInicial) {
		this.periodoPrevisaoComandoInicial = periodoPrevisaoComandoInicial;
	}

	/**
	 * @return Retorna o campo periodoComandoFinal.
	 */
	public String getPeriodoComandoFinal() {
		return periodoComandoFinal;
	}

	/**
	 * @param periodoComandoFinal O periodoComandoFinal a ser setado.
	 */
	public void setPeriodoComandoFinal(String periodoComandoFinal) {
		this.periodoComandoFinal = periodoComandoFinal;
	}

	/**
	 * @return Retorna o campo periodoComandoInicial.
	 */
	public String getPeriodoComandoInicial() {
		return periodoComandoInicial;
	}

	/**
	 * @param periodoComandoInicial O periodoComandoInicial a ser setado.
	 */
	public void setPeriodoComandoInicial(String periodoComandoInicial) {
		this.periodoComandoInicial = periodoComandoInicial;
	}

	/**
	 * @return Retorna o campo periodoRealizacaoComandoFinal.
	 */
	public String getPeriodoRealizacaoComandoFinal() {
		return periodoRealizacaoComandoFinal;
	}

	/**
	 * @param periodoRealizacaoComandoFinal O periodoRealizacaoComandoFinal a ser setado.
	 */
	public void setPeriodoRealizacaoComandoFinal(
			String periodoRealizacaoComandoFinal) {
		this.periodoRealizacaoComandoFinal = periodoRealizacaoComandoFinal;
	}

	/**
	 * @return Retorna o campo periodoRealizacaoComandoInicial.
	 */
	public String getPeriodoRealizacaoComandoInicial() {
		return periodoRealizacaoComandoInicial;
	}

	/**
	 * @param periodoRealizacaoComandoInicial O periodoRealizacaoComandoInicial a ser setado.
	 */
	public void setPeriodoRealizacaoComandoInicial(
			String periodoRealizacaoComandoInicial) {
		this.periodoRealizacaoComandoInicial = periodoRealizacaoComandoInicial;
	}

	/**
	 * @return Retorna o campo intervalorValorDocumentosFinal.
	 */
	public String getIntervaloQuantidadeDocumentosFinal() {
		return intervaloQuantidadeDocumentosFinal;
	}

	/**
	 * @param intervalorValorDocumentosFinal O intervalorValorDocumentosFinal a ser setado.
	 */
	public void setIntervaloQuantidadeDocumentosFinal(
			String intervalorValorDocumentosFinal) {
		this.intervaloQuantidadeDocumentosFinal = intervalorValorDocumentosFinal;
	}

	/**
	 * @return Retorna o campo intervalorValorDocumentosInicial.
	 */
	public String getIntervaloQuantidadeDocumentosInicial() {
		return intervaloQuantidadeDocumentosInicial;
	}

	/**
	 * @param intervalorValorDocumentosInicial O intervalorValorDocumentosInicial a ser setado.
	 */
	public void setIntervaloQuantidadeDocumentosInicial(
			String intervalorValorDocumentosInicial) {
		this.intervaloQuantidadeDocumentosInicial = intervalorValorDocumentosInicial;
	}

	/**
	 * @return Retorna o campo intervaloQuantidadeItensDocumentosFinal.
	 */
	public String getIntervaloQuantidadeItensDocumentosFinal() {
		return intervaloQuantidadeItensDocumentosFinal;
	}

	/**
	 * @param intervaloQuantidadeItensDocumentosFinal O intervaloQuantidadeItensDocumentosFinal a ser setado.
	 */
	public void setIntervaloQuantidadeItensDocumentosFinal(
			String intervaloQuantidadeItensDocumentosFinal) {
		this.intervaloQuantidadeItensDocumentosFinal = intervaloQuantidadeItensDocumentosFinal;
	}

	/**
	 * @return Retorna o campo intervaloQuantidadeItensDocumentosInicial.
	 */
	public String getIntervaloQuantidadeItensDocumentosInicial() {
		return intervaloQuantidadeItensDocumentosInicial;
	}

	/**
	 * @param intervaloQuantidadeItensDocumentosInicial O intervaloQuantidadeItensDocumentosInicial a ser setado.
	 */
	public void setIntervaloQuantidadeItensDocumentosInicial(
			String intervaloQuantidadeItensDocumentosInicial) {
		this.intervaloQuantidadeItensDocumentosInicial = intervaloQuantidadeItensDocumentosInicial;
	}

	/**
	 * @return Retorna o campo intervaloValorDocumentosFinal.
	 */
	public String getIntervaloValorDocumentosFinal() {
		return intervaloValorDocumentosFinal;
	}

	/**
	 * @param intervaloValorDocumentosFinal O intervaloValorDocumentosFinal a ser setado.
	 */
	public void setIntervaloValorDocumentosFinal(
			String intervaloValorDocumentosFinal) {
		this.intervaloValorDocumentosFinal = intervaloValorDocumentosFinal;
	}

	/**
	 * @return Retorna o campo intervaloValorDocumentosInicial.
	 */
	public String getIntervaloValorDocumentosInicial() {
		return intervaloValorDocumentosInicial;
	}

	/**
	 * @param intervaloValorDocumentosInicial O intervaloValorDocumentosInicial a ser setado.
	 */
	public void setIntervaloValorDocumentosInicial(
			String intervaloValorDocumentosInicial) {
		this.intervaloValorDocumentosInicial = intervaloValorDocumentosInicial;
	}

	/**
	 * @return Retorna o campo acaoCobranca.
	 */
	public String[] getAcaoCobranca() {
		return acaoCobranca;
	}

	/**
	 * @param acaoCobranca O acaoCobranca a ser setado.
	 */
	public void setAcaoCobranca(String[] acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	/**
	 * @return Retorna o campo atividadeCobranca.
	 */
	public String[] getAtividadeCobranca() {
		return atividadeCobranca;
	}

	/**
	 * @param atividadeCobranca O atividadeCobranca a ser setado.
	 */
	public void setAtividadeCobranca(String[] atividadeCobranca) {
		this.atividadeCobranca = atividadeCobranca;
	}

	/**
	 * @return Retorna o campo grupoCobranca.
	 */
	public String[] getGrupoCobranca() {
		return grupoCobranca;
	}

	/**
	 * @param grupoCobranca O grupoCobranca a ser setado.
	 */
	public void setGrupoCobranca(String[] grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	/**
	 * @return Retorna o campo situacaoComando.
	 */
	public String getSituacaoComando() {
		return situacaoComando;
	}

	/**
	 * @param situacaoComando O situacaoComando a ser setado.
	 */
	public void setSituacaoComando(String situacaoComando) {
		this.situacaoComando = situacaoComando;
	}

	/**
	 * @return Retorna o campo situacaoCronograma.
	 */
	public String getSituacaoCronograma() {
		return situacaoCronograma;
	}

	/**
	 * @param situacaoCronograma O situacaoCronograma a ser setado.
	 */
	public void setSituacaoCronograma(String situacaoCronograma) {
		this.situacaoCronograma = situacaoCronograma;
	}

}
