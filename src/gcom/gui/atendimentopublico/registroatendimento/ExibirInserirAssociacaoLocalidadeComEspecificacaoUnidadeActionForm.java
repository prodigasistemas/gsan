package gcom.gui.atendimentopublico.registroatendimento;


import org.apache.struts.action.ActionForm;

/**
 * [UC1092] Inserir Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 03/11/2010
 */

public class ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idLocalidade; 
	private String nomeLocalidade;
	private String idTipoSolicitacao;
	private String idTipoEspecificacao;
	private String idUnidadeAtendimento; 
	private String nomeUnidadeAtendimento;

	public void reset(){

		this.idLocalidade = null;
		this.nomeLocalidade = null;
		this.idTipoSolicitacao = null;
		this.idTipoEspecificacao = null;
		this.idUnidadeAtendimento = null;
		this.nomeUnidadeAtendimento = null;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getIdTipoEspecificacao() {
		return idTipoEspecificacao;
	}

	public void setIdTipoEspecificacao(String idTipoEspecificacao) {
		this.idTipoEspecificacao = idTipoEspecificacao;
	}

	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}

	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}

	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	public String getNomeUnidadeAtendimento() {
		return nomeUnidadeAtendimento;
	}

	public void setNomeUnidadeAtendimento(String nomeUnidadeAtendimento) {
		this.nomeUnidadeAtendimento = nomeUnidadeAtendimento;
	}

}
