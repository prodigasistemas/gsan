package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class ConsultarRegistroAtendimentoSolicitanteActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
    private String numeroRA;
    private String situacaoRA;
    private String idTipoSolicitacao;
    private String tipoSolicitacao;
    
    private String idEspecificacao;
    private String especificacao;
    
    private String idUnidadeAtual;
    private String unidadeAtual;

    //Dados Solicitante
    private String solicitanteId;
    private String indicadorPrincipal;
    private String nomeSolicitante;
    private String clienteSolicitante;
    
    private String idUnidadeSolicitante;
    private String unidadeSolicitante;
    
    private String idFuncionarioResponsavel;
    private String funcionarioResponsavel;
    
    private String enderecoSolicitante;
    private String pontoReferencia;
    
    private String protocoloAtendimento;
    
    private Collection<SolicitanteFone> colecaoSolicitanteFone = new ArrayList();
    
    private Collection<RegistroAtendimentoSolicitante> colecaoRegistroAtendimentoSolicitante = new ArrayList();

	public Collection<RegistroAtendimentoSolicitante> getColecaoRegistroAtendimentoSolicitante() {
		return colecaoRegistroAtendimentoSolicitante;
	}

	public void setColecaoRegistroAtendimentoSolicitante(Collection<RegistroAtendimentoSolicitante> colecaoRegistroAtendimentoSolicitante) {
		this.colecaoRegistroAtendimentoSolicitante = colecaoRegistroAtendimentoSolicitante;
	}

	public Collection<SolicitanteFone> getColecaoSolicitanteFone() {
		return colecaoSolicitanteFone;
	}
	public void setColecaoSolicitanteFone(Collection<SolicitanteFone> colecaoSolicitanteFone) {
		this.colecaoSolicitanteFone = colecaoSolicitanteFone;
	}


	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}

	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getClienteSolicitante() {
		return clienteSolicitante;
	}

	public void setClienteSolicitante(String clienteSolicitante) {
		this.clienteSolicitante = clienteSolicitante;
	}

	public String getEnderecoSolicitante() {
		return enderecoSolicitante;
	}

	public void setEnderecoSolicitante(String enderecoSolicitante) {
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public String getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(String funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public String getIdFuncionarioResponsavel() {
		return idFuncionarioResponsavel;
	}

	public void setIdFuncionarioResponsavel(String idFuncionarioResponsavel) {
		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
	}

	public String getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}

	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getSolicitanteId() {
		return solicitanteId;
	}

	public void setSolicitanteId(String solicitanteId) {
		this.solicitanteId = solicitanteId;
	}

	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}

	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}

	public String getIdEspecificacao() {
		return idEspecificacao;
	}

	public void setIdEspecificacao(String idEspecificacao) {
		this.idEspecificacao = idEspecificacao;
	}

	public String getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}

	public void setIdTipoSolicitacao(String idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}

	public String getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(String indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	public String getProtocoloAtendimento() {
		return protocoloAtendimento;
	}

	public void setProtocoloAtendimento(String protocoloAtendimento) {
		this.protocoloAtendimento = protocoloAtendimento;
	}
}
