package gcom.atendimentopublico.registroatendimento;

import gcom.integracao.webservice.spc.ConsultaWebServiceStub.Endereco;

import java.util.Collection;

public class RASolicitanteHelper {

	private Integer idCliente; 
	private String pontoReferenciaSolicitante;
	private String nomeSolicitante; 
	private boolean novoSolicitante;
	private Integer idUnidadeSolicitante; 
	private Integer idServicoTipo; 
	private Collection colecaoFone; 
	private Collection<Endereco> colecaoEnderecoSolicitante;
	private String habilitarCampoSatisfacaoEmail; 
	private String enviarEmailSatisfacao; 
	private String enderecoEmail;
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getPontoReferenciaSolicitante() {
		return pontoReferenciaSolicitante;
	}
	public void setPontoReferenciaSolicitante(String pontoReferenciaSolicitante) {
		this.pontoReferenciaSolicitante = pontoReferenciaSolicitante;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public boolean isNovoSolicitante() {
		return novoSolicitante;
	}
	public void setNovoSolicitante(boolean novoSolicitante) {
		this.novoSolicitante = novoSolicitante;
	}
	public Integer getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}
	public void setIdUnidadeSolicitante(Integer idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}
	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	public Collection getColecaoFone() {
		return colecaoFone;
	}
	public void setColecaoFone(Collection colecaoFone) {
		this.colecaoFone = colecaoFone;
	}
	public Collection<Endereco> getColecaoEnderecoSolicitante() {
		return colecaoEnderecoSolicitante;
	}
	public void setColecaoEnderecoSolicitante(
			Collection<Endereco> colecaoEnderecoSolicitante) {
		this.colecaoEnderecoSolicitante = colecaoEnderecoSolicitante;
	}
	public String getHabilitarCampoSatisfacaoEmail() {
		return habilitarCampoSatisfacaoEmail;
	}
	public void setHabilitarCampoSatisfacaoEmail(
			String habilitarCampoSatisfacaoEmail) {
		this.habilitarCampoSatisfacaoEmail = habilitarCampoSatisfacaoEmail;
	}
	public String getEnviarEmailSatisfacao() {
		return enviarEmailSatisfacao;
	}
	public void setEnviarEmailSatisfacao(String enviarEmailSatisfacao) {
		this.enviarEmailSatisfacao = enviarEmailSatisfacao;
	}
	public String getEnderecoEmail() {
		return enderecoEmail;
	}
	public void setEnderecoEmail(String enderecoEmail) {
		this.enderecoEmail = enderecoEmail;
	}
	
}
