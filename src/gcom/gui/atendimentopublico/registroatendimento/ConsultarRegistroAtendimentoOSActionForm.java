package gcom.gui.atendimentopublico.registroatendimento;


import gcom.atendimentopublico.ordemservico.OrdemServico;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class ConsultarRegistroAtendimentoOSActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
    private String numeroRA;
    private String situacaoRA;
    private String tipoSolicitacao;
    private String tipoSolicitacaoId;
    private String especificacao;
    private String especificacaoId;
    
    private String idUnidadeAtual;
    private String unidadeAtual;

    // OS
    private String osId;
    private String tipoServicoId;
    private String tipoServicoDescricao;
    private String dataGeracao;
    private String situacao;
    
    //Coleção de OS
    private Collection<OrdemServico> colecaoOS = new ArrayList();

	public Collection<OrdemServico> getColecaoOS() {
		return colecaoOS;
	}

	public void setColecaoOS(Collection<OrdemServico> colecaoOS) {
		this.colecaoOS = colecaoOS;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
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

	public String getOsId() {
		return osId;
	}

	public void setOsId(String osId) {
		this.osId = osId;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getTipoServicoDescricao() {
		return tipoServicoDescricao;
	}

	public void setTipoServicoDescricao(String tipoServicoDescricao) {
		this.tipoServicoDescricao = tipoServicoDescricao;
	}

	public String getTipoServicoId() {
		return tipoServicoId;
	}

	public void setTipoServicoId(String tipoServicoId) {
		this.tipoServicoId = tipoServicoId;
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

	public String getEspecificacaoId() {
		return especificacaoId;
	}

	public void setEspecificacaoId(String especificacaoId) {
		this.especificacaoId = especificacaoId;
	}

	public String getTipoSolicitacaoId() {
		return tipoSolicitacaoId;
	}

	public void setTipoSolicitacaoId(String tipoSolicitacaoId) {
		this.tipoSolicitacaoId = tipoSolicitacaoId;
	}

}
