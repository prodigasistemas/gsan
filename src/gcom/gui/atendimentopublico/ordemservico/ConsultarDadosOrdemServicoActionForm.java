package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeIdOSHelper;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosOrdemServicoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
	private String numeroOS;
	private String numeroOSPesquisada;
	private String numeroOSParametro;
	private String situacaoOS;
	private String situacaoOSId;
    private String numeroRA;
    private String situacaoRA;
    private String numeroDocumentoCobranca;
    private String dataGeracao;
    private String numeroOSReferencia;
    private String tipoServicoId;
    private String tipoServicoDescricao;
    private String tipoServicoReferenciaId;
    private String tipoServicoReferenciaDescricao;
    private String retornoOSReferida;
    private String observacao;
    private String valorServicoOriginal;
    private String valorServicoAtual;
    private String prioridadeOriginal;
    private String prioridadeAtual;
    private String unidadeGeracaoId;
    private String unidadeGeracaoDescricao;
    private String usuarioGeracaoId;
    private String usuarioGeracaoNome;
    private String dataUltimaEmissao;
    private String nomeProjeto;
    
    //Dados da Programação
    private String dataProgramacao;
    private String equipeProgramacao;
    
    // Dados Local Ocorrência    
    private String enderecoOcorrencia;
    private String matriculaImovel;
    private String inscricaoImovel;
    private String rota;
    private String sequencialRota;
    
    // Dados de Execução de OS
    private String dataEncerramento;
    private String parecerEncerramento;
    private String areaPavimentacao;
    private String comercialAtualizado;
    private String servicoCobrado;
    private String motivoNaoCobranca;
    private String percentualCobranca;
    private String valorCobrado;
    private String motivoEncerramento;
    private String unidadeEncerramentoId;
    private String unidadeEncerramentoDescricao;
    private String usuarioEncerramentoId;
    private String usuarioEncerramentoNome;
    
    
    //Dados de Repavimentação
    private String unidadeRepavimentadoraId;
    private String unidadeRepavimentadoraDescricao;
    private String tipoPavimentoRua;
    private String areaPavimentoRua;
    private String tipoPavimentoRuaRet;
    private String areaPavimentoRuaRet;
    private String tipoPavimentoCalcada;
    private String areaPavimentoCalcada;
    private String tipoPavimentoCalcadaRet;
    private String areaPavimentoCalcadaRet;
    private String dataRetornoRepavimentacao;
    private String observacaoRetornoRepavimentacao;
    
    private String dataRejeicaoRepavimentacao;
    private String motivoRejeicaoRepavimentacao;
    private String descricaoRejeicaoRepavimentacao;
    
    private String situacaoAceiteRepavimentacao;
    private String dataAceiteRepavimentacao;
    private String usuarioAceiteId;
    private String usuarioAceiteNome;
    private String descricaoMotivoAceite;
    
    
    private Collection<ObterDadosAtividadeIdOSHelper> colecaoOSAtividade = new ArrayList<ObterDadosAtividadeIdOSHelper>();
    
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

		this.situacaoOS = null;
		this.situacaoOSId = null;
	    this.numeroRA = null;
	    this.situacaoRA = null;
	    this.numeroDocumentoCobranca = null;
	    this.dataGeracao = null;
	    this.numeroOSReferencia = null;
	    this.tipoServicoId = null;
	    this.tipoServicoDescricao = null;
	    this.tipoServicoReferenciaId = null;
	    this.tipoServicoReferenciaDescricao = null;
	    this.retornoOSReferida = null;
	    this.observacao = null;
	    this.valorServicoOriginal = null;
	    this.valorServicoAtual = null;
	    this.prioridadeOriginal = null;
	    this.prioridadeAtual = null;
	    this.unidadeGeracaoId = null;
	    this.unidadeGeracaoDescricao = null;
	    this.usuarioGeracaoId = null;
	    this.usuarioGeracaoNome = null;
	    this.dataUltimaEmissao = null;
	    
	    // Dados de Execução de OS
	    this.dataEncerramento = null;
	    this.parecerEncerramento = null;
	    this.areaPavimentacao = null;
	    this.comercialAtualizado = null;
	    this.servicoCobrado = null;
	    this.motivoNaoCobranca = null;
	    this.percentualCobranca = null;
	    this.valorCobrado = null;
	    this.motivoEncerramento = null;
	    this.unidadeEncerramentoId = null;
	    this.unidadeEncerramentoDescricao = null;
	    this.usuarioEncerramentoId = null;
	    this.usuarioEncerramentoNome = null;
	    
	    this.unidadeRepavimentadoraId = null;
	    this.unidadeRepavimentadoraDescricao = null;
	    this.tipoPavimentoRua = null;
	    this.areaPavimentoRua = null;
	    this.tipoPavimentoRuaRet = null;
	    this.areaPavimentoRuaRet = null;
	    this.tipoPavimentoCalcada = null;
	    this.areaPavimentoCalcada = null;
	    this.tipoPavimentoCalcadaRet = null;
	    this.areaPavimentoCalcadaRet = null;
	    this.dataRetornoRepavimentacao = null;
	    this.observacaoRetornoRepavimentacao = null;
	    
	    this.dataRejeicaoRepavimentacao = null;
	    this.motivoRejeicaoRepavimentacao = null;
	    this.descricaoRejeicaoRepavimentacao = null;
	    
	    this.situacaoAceiteRepavimentacao = null;
	    this.dataAceiteRepavimentacao = null;
	    this.usuarioAceiteId = null;
	    this.usuarioAceiteNome = null;
	    this.descricaoMotivoAceite = null;
	    
	    
	}	
	
    	
	
    /**
	 * @return Retorna o campo motivoEncerramento.
	 */
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}



	/**
	 * @param motivoEncerramento O motivoEncerramento a ser setado.
	 */
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}



	public String getNumeroOSParametro() {
		return numeroOSParametro;
	}

	public void setNumeroOSParametro(String numeroOSParametro) {
		this.numeroOSParametro = numeroOSParametro;
	}

	public String getNumeroOSPesquisada() {
		return numeroOSPesquisada;
	}

	public void setNumeroOSPesquisada(String numeroOSPesquisada) {
		this.numeroOSPesquisada = numeroOSPesquisada;
	}

	public String getAreaPavimentacao() {
		return areaPavimentacao;
	}
	public void setAreaPavimentacao(String areaPavimentacao) {
		this.areaPavimentacao = areaPavimentacao;
	}
	public String getComercialAtualizado() {
		return comercialAtualizado;
	}
	public void setComercialAtualizado(String comercialAtualizado) {
		this.comercialAtualizado = comercialAtualizado;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getDataUltimaEmissao() {
		return dataUltimaEmissao;
	}
	public void setDataUltimaEmissao(String dataUltimaEmissao) {
		this.dataUltimaEmissao = dataUltimaEmissao;
	}
	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}
	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
	}
	public String getNumeroDocumentoCobranca() {
		return numeroDocumentoCobranca;
	}
	public void setNumeroDocumentoCobranca(String numeroDocumentoCobranca) {
		this.numeroDocumentoCobranca = numeroDocumentoCobranca;
	}
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getNumeroOSReferencia() {
		return numeroOSReferencia;
	}
	public void setNumeroOSReferencia(String numeroOSReferencia) {
		this.numeroOSReferencia = numeroOSReferencia;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getParecerEncerramento() {
		return parecerEncerramento;
	}
	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}
	public String getPercentualCobranca() {
		return percentualCobranca;
	}
	public void setPercentualCobranca(String percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}
	public String getPrioridadeAtual() {
		return prioridadeAtual;
	}
	public void setPrioridadeAtual(String prioridadeAtual) {
		this.prioridadeAtual = prioridadeAtual;
	}
	public String getPrioridadeOriginal() {
		return prioridadeOriginal;
	}
	public void setPrioridadeOriginal(String prioridadeOriginal) {
		this.prioridadeOriginal = prioridadeOriginal;
	}
	public String getRetornoOSReferida() {
		return retornoOSReferida;
	}
	public void setRetornoOSReferida(String retornoOSReferida) {
		this.retornoOSReferida = retornoOSReferida;
	}
	public String getSituacaoOS() {
		return situacaoOS;
	}
	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
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
	public String getTipoServicoReferenciaDescricao() {
		return tipoServicoReferenciaDescricao;
	}
	public void setTipoServicoReferenciaDescricao(
			String tipoServicoReferenciaDescricao) {
		this.tipoServicoReferenciaDescricao = tipoServicoReferenciaDescricao;
	}
	public String getTipoServicoReferenciaId() {
		return tipoServicoReferenciaId;
	}
	public void setTipoServicoReferenciaId(String tipoServicoReferenciaId) {
		this.tipoServicoReferenciaId = tipoServicoReferenciaId;
	}
	public String getUnidadeEncerramentoDescricao() {
		return unidadeEncerramentoDescricao;
	}
	public void setUnidadeEncerramentoDescricao(String unidadeEncerramentoDescricao) {
		this.unidadeEncerramentoDescricao = unidadeEncerramentoDescricao;
	}
	public String getUnidadeEncerramentoId() {
		return unidadeEncerramentoId;
	}
	public void setUnidadeEncerramentoId(String unidadeEncerramentoId) {
		this.unidadeEncerramentoId = unidadeEncerramentoId;
	}
	public String getUnidadeGeracaoDescricao() {
		return unidadeGeracaoDescricao;
	}
	public void setUnidadeGeracaoDescricao(String unidadeGeracaoDescricao) {
		this.unidadeGeracaoDescricao = unidadeGeracaoDescricao;
	}
	public String getUnidadeGeracaoId() {
		return unidadeGeracaoId;
	}
	public void setUnidadeGeracaoId(String unidadeGeracaoId) {
		this.unidadeGeracaoId = unidadeGeracaoId;
	}
	public String getUsuarioEncerramentoId() {
		return usuarioEncerramentoId;
	}
	public void setUsuarioEncerramentoId(String usuarioEncerramentoId) {
		this.usuarioEncerramentoId = usuarioEncerramentoId;
	}
	public String getUsuarioEncerramentoNome() {
		return usuarioEncerramentoNome;
	}
	public void setUsuarioEncerramentoNome(String usuarioEncerramentoNome) {
		this.usuarioEncerramentoNome = usuarioEncerramentoNome;
	}
	public String getUsuarioGeracaoId() {
		return usuarioGeracaoId;
	}
	public void setUsuarioGeracaoId(String usuarioGeracaoId) {
		this.usuarioGeracaoId = usuarioGeracaoId;
	}
	public String getUsuarioGeracaoNome() {
		return usuarioGeracaoNome;
	}
	public void setUsuarioGeracaoNome(String usuarioGeracaoNome) {
		this.usuarioGeracaoNome = usuarioGeracaoNome;
	}
	public String getValorServicoAtual() {
		return valorServicoAtual;
	}
	public void setValorServicoAtual(String valorServicoAtual) {
		this.valorServicoAtual = valorServicoAtual;
	}
	public String getValorServicoOriginal() {
		return valorServicoOriginal;
	}
	public void setValorServicoOriginal(String valorServicoOriginal) {
		this.valorServicoOriginal = valorServicoOriginal;
	}
	public String getServicoCobrado() {
		return servicoCobrado;
	}
	public void setServicoCobrado(String servicoCobrado) {
		this.servicoCobrado = servicoCobrado;
	}
	public String getValorCobrado() {
		return valorCobrado;
	}
	public void setValorCobrado(String valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
	public String getSituacaoOSId() {
		return situacaoOSId;
	}
	public void setSituacaoOSId(String situacaoOSId) {
		this.situacaoOSId = situacaoOSId;
	}
	public Collection<ObterDadosAtividadeIdOSHelper> getColecaoOSAtividade() {
		return colecaoOSAtividade;
	}
	public void setColecaoOSAtividade(Collection<ObterDadosAtividadeIdOSHelper> colecaoOSAtividade) {
		this.colecaoOSAtividade = colecaoOSAtividade;
	}



	/**
	 * @return Retorna o campo dataProgramacao.
	 */
	public String getDataProgramacao() {
		return dataProgramacao;
	}



	/**
	 * @param dataProgramacao O dataProgramacao a ser setado.
	 */
	public void setDataProgramacao(String dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}



	/**
	 * @return Retorna o campo equipeProgramacao.
	 */
	public String getEquipeProgramacao() {
		return equipeProgramacao;
	}



	/**
	 * @param equipeProgramacao O equipeProgramacao a ser setado.
	 */
	public void setEquipeProgramacao(String equipeProgramacao) {
		this.equipeProgramacao = equipeProgramacao;
	}



	/**
	 * @return Retorna o campo enderecoOcorrencia.
	 */
	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}



	/**
	 * @param enderecoOcorrencia O enderecoOcorrencia a ser setado.
	 */
	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}



	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}



	/**
	 * @param inscricaoImovel O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}



	/**
	 * @return Retorna o campo matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}



	/**
	 * @param matriculaImovel O matriculaImovel a ser setado.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}



	/**
	 * @return Retorna o campo rota.
	 */
	public String getRota() {
		return rota;
	}



	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}



	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public String getSequencialRota() {
		return sequencialRota;
	}



	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}



	public String getNomeProjeto() {
		return nomeProjeto;
	}



	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}



	public String getAreaPavimentoCalcada() {
		return areaPavimentoCalcada;
	}



	public void setAreaPavimentoCalcada(String areaPavimentoCalcada) {
		this.areaPavimentoCalcada = areaPavimentoCalcada;
	}



	public String getAreaPavimentoCalcadaRet() {
		return areaPavimentoCalcadaRet;
	}



	public void setAreaPavimentoCalcadaRet(String areaPavimentoCalcadaRet) {
		this.areaPavimentoCalcadaRet = areaPavimentoCalcadaRet;
	}



	public String getAreaPavimentoRua() {
		return areaPavimentoRua;
	}



	public void setAreaPavimentoRua(String areaPavimentoRua) {
		this.areaPavimentoRua = areaPavimentoRua;
	}



	public String getAreaPavimentoRuaRet() {
		return areaPavimentoRuaRet;
	}



	public void setAreaPavimentoRuaRet(String areaPavimentoRuaRet) {
		this.areaPavimentoRuaRet = areaPavimentoRuaRet;
	}



	public String getDataAceiteRepavimentacao() {
		return dataAceiteRepavimentacao;
	}



	public void setDataAceiteRepavimentacao(String dataAceiteRepavimentacao) {
		this.dataAceiteRepavimentacao = dataAceiteRepavimentacao;
	}



	public String getDataRejeicaoRepavimentacao() {
		return dataRejeicaoRepavimentacao;
	}



	public void setDataRejeicaoRepavimentacao(String dataRejeicaoRepavimentacao) {
		this.dataRejeicaoRepavimentacao = dataRejeicaoRepavimentacao;
	}



	public String getDataRetornoRepavimentacao() {
		return dataRetornoRepavimentacao;
	}



	public void setDataRetornoRepavimentacao(String dataRetornoRepavimentacao) {
		this.dataRetornoRepavimentacao = dataRetornoRepavimentacao;
	}



	public String getDescricaoMotivoAceite() {
		return descricaoMotivoAceite;
	}



	public void setDescricaoMotivoAceite(String descricaoMotivoAceite) {
		this.descricaoMotivoAceite = descricaoMotivoAceite;
	}



	public String getDescricaoRejeicaoRepavimentacao() {
		return descricaoRejeicaoRepavimentacao;
	}



	public void setDescricaoRejeicaoRepavimentacao(
			String descricaoRejeicaoRepavimentacao) {
		this.descricaoRejeicaoRepavimentacao = descricaoRejeicaoRepavimentacao;
	}



	public String getMotivoRejeicaoRepavimentacao() {
		return motivoRejeicaoRepavimentacao;
	}



	public void setMotivoRejeicaoRepavimentacao(String motivoRejeicaoRepavimentacao) {
		this.motivoRejeicaoRepavimentacao = motivoRejeicaoRepavimentacao;
	}



	public String getObservacaoRetornoRepavimentacao() {
		return observacaoRetornoRepavimentacao;
	}



	public void setObservacaoRetornoRepavimentacao(
			String observacaoRetornoRepavimentacao) {
		this.observacaoRetornoRepavimentacao = observacaoRetornoRepavimentacao;
	}



	public String getSituacaoAceiteRepavimentacao() {
		return situacaoAceiteRepavimentacao;
	}



	public void setSituacaoAceiteRepavimentacao(String situacaoAceiteRepavimentacao) {
		this.situacaoAceiteRepavimentacao = situacaoAceiteRepavimentacao;
	}



	public String getTipoPavimentoCalcada() {
		return tipoPavimentoCalcada;
	}



	public void setTipoPavimentoCalcada(String tipoPavimentoCalcada) {
		this.tipoPavimentoCalcada = tipoPavimentoCalcada;
	}



	public String getTipoPavimentoCalcadaRet() {
		return tipoPavimentoCalcadaRet;
	}



	public void setTipoPavimentoCalcadaRet(String tipoPavimentoCalcadaRet) {
		this.tipoPavimentoCalcadaRet = tipoPavimentoCalcadaRet;
	}



	public String getTipoPavimentoRua() {
		return tipoPavimentoRua;
	}



	public void setTipoPavimentoRua(String tipoPavimentoRua) {
		this.tipoPavimentoRua = tipoPavimentoRua;
	}



	public String getTipoPavimentoRuaRet() {
		return tipoPavimentoRuaRet;
	}



	public void setTipoPavimentoRuaRet(String tipoPavimentoRuaRet) {
		this.tipoPavimentoRuaRet = tipoPavimentoRuaRet;
	}



	public String getUnidadeRepavimentadoraDescricao() {
		return unidadeRepavimentadoraDescricao;
	}



	public void setUnidadeRepavimentadoraDescricao(
			String unidadeRepavimentadoraDescricao) {
		this.unidadeRepavimentadoraDescricao = unidadeRepavimentadoraDescricao;
	}



	public String getUnidadeRepavimentadoraId() {
		return unidadeRepavimentadoraId;
	}



	public void setUnidadeRepavimentadoraId(String unidadeRepavimentadoraId) {
		this.unidadeRepavimentadoraId = unidadeRepavimentadoraId;
	}



	public String getUsuarioAceiteId() {
		return usuarioAceiteId;
	}



	public void setUsuarioAceiteId(String usuarioAceiteId) {
		this.usuarioAceiteId = usuarioAceiteId;
	}



	public String getUsuarioAceiteNome() {
		return usuarioAceiteNome;
	}



	public void setUsuarioAceiteNome(String usuarioAceiteNome) {
		this.usuarioAceiteNome = usuarioAceiteNome;
	}
	
	
	

}
