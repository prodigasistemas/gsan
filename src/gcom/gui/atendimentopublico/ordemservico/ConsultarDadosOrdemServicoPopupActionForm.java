/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.atendimentopublico.ordemservico;


import gcom.atendimentopublico.ordemservico.bean.ObterDadosAtividadeIdOSHelper;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosOrdemServicoPopupActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
	private String numeroOS;
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
    private String indicadorDiagnostico;
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
    private String unidadeEncerramentoId;
    private String unidadeEncerramentoDescricao;
    private String usuarioEncerramentoId;
    private String usuarioEncerramentoNome;
    
    //Dados do Pavimento
    private String tipoPavimentoRua;
    private String areaPavimentoRua;
    private String tipoPavimentoCalcada; 
    private String areaPavimentoCalcada;
    private String tipoPavimentoRuaRetorno;
    private String areaPavimentoRuaRetorno;
    private String tipoPavimentoCalcadaRetorno; 
    private String areaPavimentoCalcadaRetorno;
    private String idUnidadeRepavimentadora;
    private String descricaoUnidadeRepavimentadora;
    
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
 
    private String descricaoFiscalizacaoSituacao;
    private String dataFiscalizacaoSituacao;
    
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
        this.indicadorDiagnostico = null;
	    
	    // Dados de Execução de OS
	    this.dataEncerramento = null;
	    this.parecerEncerramento = null;
	    this.areaPavimentacao = null;
	    this.comercialAtualizado = null;
	    this.servicoCobrado = null;
	    this.motivoNaoCobranca = null;
	    this.percentualCobranca = null;
	    this.valorCobrado = null;
	    this.unidadeEncerramentoId = null;
	    this.unidadeEncerramentoDescricao = null;
	    this.usuarioEncerramentoId = null;
	    this.usuarioEncerramentoNome = null;
	    
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
	public void setColecaoOSAtividade(
			Collection<ObterDadosAtividadeIdOSHelper> colecaoOSAtividade) {
		this.colecaoOSAtividade = colecaoOSAtividade;
	}
	
    public void resetarConsultarDadosOSPopup() {
    	this.numeroOS = null;
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
        this.dataEncerramento = null;
        this.parecerEncerramento = null;
        this.areaPavimentacao = null;
        this.comercialAtualizado = null;
        this.servicoCobrado = null;
        this.motivoNaoCobranca = null;
        this.percentualCobranca = null;
        this.valorCobrado = null;
        this.unidadeEncerramentoId = null;
        this.unidadeEncerramentoDescricao = null;
        this.usuarioEncerramentoId = null;
        this.usuarioEncerramentoNome = null;
        this.colecaoOSAtividade = new ArrayList<ObterDadosAtividadeIdOSHelper>();
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

    public String getIndicadorDiagnostico() {
        return indicadorDiagnostico;
    }

    public void setIndicadorDiagnostico(String indicadorDiagnostico) {
        this.indicadorDiagnostico = indicadorDiagnostico;
    }

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	/**
	 * @return Returns the areaPavimentoCalcada.
	 */
	public String getAreaPavimentoCalcada() {
		return areaPavimentoCalcada;
	}
	/**
	 * @param areaPavimentoCalcada The areaPavimentoCalcada to set.
	 */
	public void setAreaPavimentoCalcada(String areaPavimentoCalcada) {
		this.areaPavimentoCalcada = areaPavimentoCalcada;
	}

	/**
	 * @return Returns the areaPavimentoCalcadaRetorno.
	 */
	public String getAreaPavimentoCalcadaRetorno() {
		return areaPavimentoCalcadaRetorno;
	}

	/**
	 * @param areaPavimentoCalcadaRetorno The areaPavimentoCalcadaRetorno to set.
	 */
	public void setAreaPavimentoCalcadaRetorno(String areaPavimentoCalcadaRetorno) {
		this.areaPavimentoCalcadaRetorno = areaPavimentoCalcadaRetorno;
	}

	/**
	 * @return Returns the areaPavimentoRua.
	 */
	public String getAreaPavimentoRua() {
		return areaPavimentoRua;
	}

	/**
	 * @param areaPavimentoRua The areaPavimentoRua to set.
	 */
	public void setAreaPavimentoRua(String areaPavimentoRua) {
		this.areaPavimentoRua = areaPavimentoRua;
	}

	/**
	 * @return Returns the areaPavimentoRuaRetorno.
	 */
	public String getAreaPavimentoRuaRetorno() {
		return areaPavimentoRuaRetorno;
	}

	/**
	 * @param areaPavimentoRuaRetorno The areaPavimentoRuaRetorno to set.
	 */
	public void setAreaPavimentoRuaRetorno(String areaPavimentoRuaRetorno) {
		this.areaPavimentoRuaRetorno = areaPavimentoRuaRetorno;
	}
	/**
	 * @return Returns the tipoPavimentoCalcada.
	 */
	public String getTipoPavimentoCalcada() {
		return tipoPavimentoCalcada;
	}

	/**
	 * @param tipoPavimentoCalcada The tipoPavimentoCalcada to set.
	 */
	public void setTipoPavimentoCalcada(String tipoPavimentoCalcada) {
		this.tipoPavimentoCalcada = tipoPavimentoCalcada;
	}

	/**
	 * @return Returns the tipoPavimentoCalcadaRetorno.
	 */
	public String getTipoPavimentoCalcadaRetorno() {
		return tipoPavimentoCalcadaRetorno;
	}

	/**
	 * @param tipoPavimentoCalcadaRetorno The tipoPavimentoCalcadaRetorno to set.
	 */
	public void setTipoPavimentoCalcadaRetorno(String tipoPavimentoCalcadaRetorno) {
		this.tipoPavimentoCalcadaRetorno = tipoPavimentoCalcadaRetorno;
	}

	/**
	 * @return Returns the tipoPavimentoRua.
	 */
	public String getTipoPavimentoRua() {
		return tipoPavimentoRua;
	}

	/**
	 * @param tipoPavimentoRua The tipoPavimentoRua to set.
	 */
	public void setTipoPavimentoRua(String tipoPavimentoRua) {
		this.tipoPavimentoRua = tipoPavimentoRua;
	}

	/**
	 * @return Returns the tipoPavimentoRuaRetorno.
	 */
	public String getTipoPavimentoRuaRetorno() {
		return tipoPavimentoRuaRetorno;
	}

	/**
	 * @param tipoPavimentoRuaRetorno The tipoPavimentoRuaRetorno to set.
	 */
	public void setTipoPavimentoRuaRetorno(String tipoPavimentoRuaRetorno) {
		this.tipoPavimentoRuaRetorno = tipoPavimentoRuaRetorno;
	}




	/**
	 * @return Returns the descricaoUnidadeRepavimentadora.
	 */
	public String getDescricaoUnidadeRepavimentadora() {
		return descricaoUnidadeRepavimentadora;
	}




	/**
	 * @param descricaoUnidadeRepavimentadora The descricaoUnidadeRepavimentadora to set.
	 */
	public void setDescricaoUnidadeRepavimentadora(
			String descricaoUnidadeRepavimentadora) {
		this.descricaoUnidadeRepavimentadora = descricaoUnidadeRepavimentadora;
	}




	/**
	 * @return Returns the idUnidadeRepavimentadora.
	 */
	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}




	/**
	 * @param idUnidadeRepavimentadora The idUnidadeRepavimentadora to set.
	 */
	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}




	public String getDescricaoFiscalizacaoSituacao() {
		return descricaoFiscalizacaoSituacao;
	}




	public void setDescricaoFiscalizacaoSituacao(
			String descricaoFiscalizacaoSituacao) {
		this.descricaoFiscalizacaoSituacao = descricaoFiscalizacaoSituacao;
	}




	public String getDataFiscalizacaoSituacao() {
		return dataFiscalizacaoSituacao;
	}




	public void setDataFiscalizacaoSituacao(String dataFiscalizacaoSituacao) {
		this.dataFiscalizacaoSituacao = dataFiscalizacaoSituacao;
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