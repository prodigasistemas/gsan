package gcom.gui.atendimentopublico.registroatendimento;


import gcom.atendimentopublico.registroatendimento.Tramite;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class ConsultarRegistroAtendimentoTramiteActionForm extends ActionForm{
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

    private String idUnidadeAtendimento;
    private String unidadeAtendimento;
    
    private String tramiteId;
    private String unidadeDestinoId;
    private String unidadeDestinoDescricao;
    private String usuarioResponsavelId;
    private String usuarioResponsavelNome;
    private String usuarioRegistroId;
    private String usuarioRegistroNome;
    private String dataTramitacao;
    private String horaTramitacao;
    private String parecer;
    
    //Dados do Trâmite
    private Collection<Tramite> colecaoTramites = new ArrayList();

	public Collection<Tramite> getColecaoTramites() {
		return colecaoTramites;
	}

	public void setColecaoTramites(Collection<Tramite> colecaoTramites) {
		this.colecaoTramites = colecaoTramites;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
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

	public String getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(String unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getTramiteId() {
		return tramiteId;
	}

	public void setTramiteId(String tramiteId) {
		this.tramiteId = tramiteId;
	}

	public String getDataTramitacao() {
		return dataTramitacao;
	}

	public void setDataTramitacao(String dataTramitacao) {
		this.dataTramitacao = dataTramitacao;
	}

	public String getHoraTramitacao() {
		return horaTramitacao;
	}

	public void setHoraTramitacao(String horaTramitacao) {
		this.horaTramitacao = horaTramitacao;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getUnidadeDestinoDescricao() {
		return unidadeDestinoDescricao;
	}

	public void setUnidadeDestinoDescricao(String unidadeDestinoDescricao) {
		this.unidadeDestinoDescricao = unidadeDestinoDescricao;
	}

	public String getUnidadeDestinoId() {
		return unidadeDestinoId;
	}

	public void setUnidadeDestinoId(String unidadeDestinoId) {
		this.unidadeDestinoId = unidadeDestinoId;
	}

	public String getUsuarioRegistroId() {
		return usuarioRegistroId;
	}

	public void setUsuarioRegistroId(String usuarioRegistroId) {
		this.usuarioRegistroId = usuarioRegistroId;
	}

	public String getUsuarioRegistroNome() {
		return usuarioRegistroNome;
	}

	public void setUsuarioRegistroNome(String usuarioRegistroNome) {
		this.usuarioRegistroNome = usuarioRegistroNome;
	}

	public String getUsuarioResponsavelId() {
		return usuarioResponsavelId;
	}

	public void setUsuarioResponsavelId(String usuarioResponsavelId) {
		this.usuarioResponsavelId = usuarioResponsavelId;
	}

	public String getUsuarioResponsavelNome() {
		return usuarioResponsavelNome;
	}

	public void setUsuarioResponsavelNome(String usuarioResponsavelNome) {
		this.usuarioResponsavelNome = usuarioResponsavelNome;
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
}
