package gcom.gui.portal;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <p>
 * Classe Responsável pelo formulário de cadastro para solicitação de serviços
 * na Loja Virtual da Compesa
 * </p>
 * 
 * @author Magno Gouveia
 * @date 28/06/2011
 */
public class InserirSolicitacaoServicosPortalActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 6585396434051685339L;

	private String solicitacaoTipo;

	private String especificacao;

	private String nomeSolicitante;

	private String telefoneContato;

	private String email;

	private String pontoReferencia;

	private String observacoes;

	private String matricula;
	
	public void reset() {
		this.email = null;
		this.telefoneContato = null;
		this.especificacao = null;
		this.solicitacaoTipo = null;
		this.nomeSolicitante = null;
		this.observacoes = null;
		this.pontoReferencia = null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getSolicitacaoTipo() {
		return solicitacaoTipo;
	}

	public void setSolicitacaoTipo(String solicitacaoTipo) {
		this.solicitacaoTipo = solicitacaoTipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
