package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC0298] Manter Abrangencia Usuario
 * @author Thiago Tenório
 * @since 10/04/2006
 */
public class AtualizarTipoRetornoOrdemServicoReferidaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoTipoRetorno;
	private String descricao;
	private String servicoTipoReferencia;
	private String deferimento;
	private String trocaServico;
	private String situacao;
	private String atendimentoMotivoEncerramento;
	private String indicadorUso;
	private String abreviatura;
	private String documentoCobranca;

	/**
	 * @return Retorna o campo codigoTipoRetorno.
	 */
	public String getCodigoTipoRetorno() {
		return codigoTipoRetorno;
	}
	/**
	 * @param codigoTipoRetorno O codigoTipoRetorno a ser setado.
	 */
	public void setCodigoTipoRetorno(String codigoTipoRetorno) {
		this.codigoTipoRetorno = codigoTipoRetorno;
	}
	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}
	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	/**
	 * @return Retorna o campo abreviatura.
	 */
	public String getAbreviatura() {
		return abreviatura;
	}
	/**
	 * @param abreviatura O abreviatura a ser setado.
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	/**
	 * @return Retorna o campo atendimentoMotivoEncerramento.
	 */
	public String getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}
	/**
	 * @param atendimentoMotivoEncerramento O atendimentoMotivoEncerramento a ser setado.
	 */
	public void setAtendimentoMotivoEncerramento(
			String atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}
	/**
	 * @return Retorna o campo deferimento.
	 */
	public String getDeferimento() {
		return deferimento;
	}
	/**
	 * @param deferimento O deferimento a ser setado.
	 */
	public void setDeferimento(String deferimento) {
		this.deferimento = deferimento;
	}
	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Retorna o campo documentoCobranca.
	 */
	public String getDocumentoCobranca() {
		return documentoCobranca;
	}
	/**
	 * @param documentoCobranca O documentoCobranca a ser setado.
	 */
	public void setDocumentoCobranca(String documentoCobranca) {
		this.documentoCobranca = documentoCobranca;
	}
	/**
	 * @return Retorna o campo servicoTipoReferencia.
	 */
	public String getServicoTipoReferencia() {
		return servicoTipoReferencia;
	}
	/**
	 * @param servicoTipoReferencia O servicoTipoReferencia a ser setado.
	 */
	public void setServicoTipoReferencia(String servicoTipoReferencia) {
		this.servicoTipoReferencia = servicoTipoReferencia;
	}
	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}
	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	/**
	 * @return Retorna o campo trocaServico.
	 */
	public String getTrocaServico() {
		return trocaServico;
	}
	/**
	 * @param trocaServico O trocaServico a ser setado.
	 */
	public void setTrocaServico(String trocaServico) {
		this.trocaServico = trocaServico;
	}







}
