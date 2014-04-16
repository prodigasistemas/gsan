package gcom.gui.atendimentopublico.registroatendimento;


import org.apache.struts.action.ActionForm;

/**
 * [UC1067] - Informar Obrigatoriedade Documento Especificação
 * 
 * @author Hugo Leonardo
 *
 * @date 23/08/2010
 */

public class ExibirInformarSolicitacaoDocumentoObrigatorioActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idTipoSolicitacao;
	private String icDocumentoSolicitanteObrigatorio;
	private String icValidarDocumentoClienteResponsavel;
	private String[] idsTipoEspecificacao;
	private Integer[] idsMeioSolicitacaoNaoObrigatorio;
	private Integer[] idsMeioSolicitacaoObrigatorio;


	public void reset(){
		
		this.idTipoSolicitacao = null;
		this.idsTipoEspecificacao = null;
		this.icDocumentoSolicitanteObrigatorio = null;
		this.icValidarDocumentoClienteResponsavel = null;
		this.idsMeioSolicitacaoNaoObrigatorio = null;
		this.idsMeioSolicitacaoObrigatorio = null;
	}

	public String getIcDocumentoSolicitanteObrigatorio() {
		return icDocumentoSolicitanteObrigatorio;
	}

	public void setIcDocumentoSolicitanteObrigatorio(
			String icDocumentoSolicitanteObrigatorio) {
		this.icDocumentoSolicitanteObrigatorio = icDocumentoSolicitanteObrigatorio;
	}

	public String getIcValidarDocumentoClienteResponsavel() {
		return icValidarDocumentoClienteResponsavel;
	}

	public void setIcValidarDocumentoClienteResponsavel(
			String icValidarDocumentoClienteResponsavel) {
		this.icValidarDocumentoClienteResponsavel = icValidarDocumentoClienteResponsavel;
	}
	
	public Integer[] getIdsMeioSolicitacaoNaoObrigatorio() {
		return idsMeioSolicitacaoNaoObrigatorio;
	}

	public void setIdsMeioSolicitacaoNaoObrigatorio(
			Integer[] idsMeioSolicitacaoNaoObrigatorio) {
		this.idsMeioSolicitacaoNaoObrigatorio = idsMeioSolicitacaoNaoObrigatorio;
	}

	public Integer[] getIdsMeioSolicitacaoObrigatorio() {
		return idsMeioSolicitacaoObrigatorio;
	}

	public void setIdsMeioSolicitacaoObrigatorio(
			Integer[] idsMeioSolicitacaoObrigatorio) {
		this.idsMeioSolicitacaoObrigatorio = idsMeioSolicitacaoObrigatorio;
	}

	public String[] getIdsTipoEspecificacao() {
		return idsTipoEspecificacao;
	}

	public void setIdsTipoEspecificacao(String[] idsTipoEspecificacao) {
		this.idsTipoEspecificacao = idsTipoEspecificacao;
	}

	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}

	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}
	
}
