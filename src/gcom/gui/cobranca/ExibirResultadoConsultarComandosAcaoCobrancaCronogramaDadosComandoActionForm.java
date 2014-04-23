package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0325] Consultar Comandos de Ação de Cobrança
 * @author Rafael Santos
 * @since 11/05/2006
 */
public class ExibirResultadoConsultarComandosAcaoCobrancaCronogramaDadosComandoActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String grupoCobranca;
	
	private String referenciaCobranca;
	
	private String acaoCobranca;
	
	private String atividadeCobranca;
	
	private String dataPrevistaCronograma;
	
	private String dataComando;
	
	private String horaComando;
	
	private String dataRealizacao;
	
	private String horaRealizacao;
	
	private String valorDocumentos;
	
	private String quantidadeDocumentos;
	
	private String quantidadeItensDocumentos;
	
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

	/**
	 * @return Retorna o campo dataPrevistaCronograma.
	 */
	public String getDataPrevistaCronograma() {
		return dataPrevistaCronograma;
	}

	/**
	 * @param dataPrevistaCronograma O dataPrevistaCronograma a ser setado.
	 */
	public void setDataPrevistaCronograma(String dataPrevistaCronograma) {
		this.dataPrevistaCronograma = dataPrevistaCronograma;
	}

	/**
	 * @return Retorna o campo quantidadeDocumentos.
	 */
	public String getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	/**
	 * @param quantidadeDocumentos O quantidadeDocumentos a ser setado.
	 */
	public void setQuantidadeDocumentos(String quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	/**
	 * @return Retorna o campo quantidadeItensDocumentos.
	 */
	public String getQuantidadeItensDocumentos() {
		return quantidadeItensDocumentos;
	}

	/**
	 * @param quantidadeItensDocumentos O quantidadeItensDocumentos a ser setado.
	 */
	public void setQuantidadeItensDocumentos(String quantidadeItensDocumentos) {
		this.quantidadeItensDocumentos = quantidadeItensDocumentos;
	}

	/**
	 * @return Retorna o campo referenciaCobranca.
	 */
	public String getReferenciaCobranca() {
		return referenciaCobranca;
	}

	/**
	 * @param referenciaCobranca O referenciaCobranca a ser setado.
	 */
	public void setReferenciaCobranca(String referenciaCobranca) {
		this.referenciaCobranca = referenciaCobranca;
	}

	/**
	 * @return Retorna o campo valorDocumentos.
	 */
	public String getValorDocumentos() {
		return valorDocumentos;
	}

	/**
	 * @param valorDocumentos O valorDocumentos a ser setado.
	 */
	public void setValorDocumentos(String valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}

	/**
	 * @param acaoCobranca O acaoCobranca a ser setado.
	 */
	public void setAcaoCobranca(String acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	/**
	 * @param atividadeCobranca O atividadeCobranca a ser setado.
	 */
	public void setAtividadeCobranca(String atividadeCobranca) {
		this.atividadeCobranca = atividadeCobranca;
	}

	/**
	 * @param grupoCobranca O grupoCobranca a ser setado.
	 */
	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	/**
	 * @return Retorna o campo acaoCobranca.
	 */
	public String getAcaoCobranca() {
		return acaoCobranca;
	}

	/**
	 * @return Retorna o campo atividadeCobranca.
	 */
	public String getAtividadeCobranca() {
		return atividadeCobranca;
	}

	/**
	 * @return Retorna o campo grupoCobranca.
	 */
	public String getGrupoCobranca() {
		return grupoCobranca;
	}

	/**
	 * @return Retorna o campo dataComando.
	 */
	public String getDataComando() {
		return dataComando;
	}

	/**
	 * @param dataComando O dataComando a ser setado.
	 */
	public void setDataComando(String dataComando) {
		this.dataComando = dataComando;
	}

	/**
	 * @return Retorna o campo horaComando.
	 */
	public String getHoraComando() {
		return horaComando;
	}

	/**
	 * @param horaComando O horaComando a ser setado.
	 */
	public void setHoraComando(String horaComando) {
		this.horaComando = horaComando;
	}

	/**
	 * @return Retorna o campo dataRealizacao.
	 */
	public String getDataRealizacao() {
		return dataRealizacao;
	}

	/**
	 * @param dataRealizacao O dataRealizacao a ser setado.
	 */
	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	/**
	 * @return Retorna o campo horaRealizacao.
	 */
	public String getHoraRealizacao() {
		return horaRealizacao;
	}

	/**
	 * @param horaRealizacao O horaRealizacao a ser setado.
	 */
	public void setHoraRealizacao(String horaRealizacao) {
		this.horaRealizacao = horaRealizacao;
	}

}
