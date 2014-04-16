package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela com os registros
 * de atendimento de falta de água no imóvel da mesma área do bairro (Aba nº 02 -
 * Dados do local de ocorrência)
 * 
 * @author Sávio Luiz
 * @date 17/07/2006
 */
public class PesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idSolicitacaoTipo;
	
	private String descricaoSolicitacaoTipo;
	
	private String idSolicitacaoTipoEspecificacao;
	
	private String descricaoSolicitacaoTipoEspecificacao;
	
	private String codigoBairro;
	
	private String nomeBairro;
	
	private String idBairroArea;
	
	private String nomeBairroArea;

	public String getCodigoBairro() {
		return codigoBairro;
	}

	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	public String getDescricaoSolicitacaoTipo() {
		return descricaoSolicitacaoTipo;
	}

	public void setDescricaoSolicitacaoTipo(String descricaoSolicitacaoTipo) {
		this.descricaoSolicitacaoTipo = descricaoSolicitacaoTipo;
	}

	public String getDescricaoSolicitacaoTipoEspecificacao() {
		return descricaoSolicitacaoTipoEspecificacao;
	}

	public void setDescricaoSolicitacaoTipoEspecificacao(
			String descricaoSolicitacaoTipoEspecificacao) {
		this.descricaoSolicitacaoTipoEspecificacao = descricaoSolicitacaoTipoEspecificacao;
	}

	public String getIdBairroArea() {
		return idBairroArea;
	}

	public void setIdBairroArea(String idBairroArea) {
		this.idBairroArea = idBairroArea;
	}

	public String getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public void setIdSolicitacaoTipo(String idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public String getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}

	public void setIdSolicitacaoTipoEspecificacao(
			String idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeBairroArea() {
		return nomeBairroArea;
	}

	public void setNomeBairroArea(String nomeBairroArea) {
		this.nomeBairroArea = nomeBairroArea;
	}
	
	

}
