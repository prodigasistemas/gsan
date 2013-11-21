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
package gcom.gui.atendimentopublico.registroatendimento;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarRegistroAtendimentoPopupActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
    private String numeroRA;
    private String numeroRAManual;
    private String situacaoRA;
    private String numeroRaAssociado;
    private String situacaoRaAssociado;
    private String idTipoSolicitacao;
    private String tipoSolicitacao;
    private String idEspecificacao;
    private String especificacao;
    private String tipoAtendimento;
    private String dataAtendimento;
    private String horaAtendimento;
    private String tempoEsperaInicio;
    private String tempoEsperaTermino;
    private String dataPrevista;
    private String valorSugerido;
    
    private String idMeioSolicitacao;
    private String meioSolicitacao;
    
    private String idUnidadeAtendimento;
    private String unidadeAtendimento;
    private String idUsuarioAbrirRA;
    private String usuarioAbrirRA;
    private String idUnidadeAtual;
    private String unidadeAtual;
    private String observacao;
    
    //Dados do Local de Ocorrência
    private String matriculaImovel;
    private String inscricaoImovel;
    private String rota;
    private String sequencialRota;
    private String enderecoOcorrencia;
    private String pontoReferencia;
    private String idMunicipio;
    private String municipio;
    private String idBairro;
    private String bairro;
    private String idAreaBairro;
    private String areaBairro;
    
    private String idLocalidade;
    private String localidade;
    private String idSetorComercial;
    private String setorComercial;
    private String idQuadra;
    private String quadra;
    private String idDivisaoEsgoto;
    private String divisaoEsgoto;
    
    private String localOcorrencia;
    private String pavimentoRua;
    private String pavimentoCalcada;
    
    private String descricaoLocalOcorrencia;
    
    //Dados do Solicitante
    private String idClienteSolicitante;
    private String clienteSolicitante;
    private String idUnidadeSolicitante;
    private String unidadeSolicitante;
    private String idFuncionarioResponsavel;
    private String funcionarioResponsavel;
    private String nomeSolicitante;
    private String enderecoSolicitante;
    private String pontoReferenciaSolicitante;
    private String fone;
    private String foneDDD;
    private String foneRamal;
    
    private String numeroProtocolo;
    
    
    //Dados da ultima Tramitação
    private String idUnidadeOrigem;
    private String unidadeOrigem;
    private String idUnidadeAtualTramitacao;
    private String unidadeAtualTramitacao;
    private String dataTramite;
    private String horaTramite;
    private String idUsuarioResponsavel;
    private String usuarioResponsavel;
    private String idUsuarioRegistro;
    private String usuarioRegistro;
    private String parecerTramite;
    
    //Dados da Ultima Reiteração
    private String quantidade;
    private String dataUltimaReiteracao;
    private String horaUltimaReiteracao;
    
    //Dados da Reativação
    private String numeroRaAtual;
    private String situacaoRaAtual;
    private String idMotivoReativacao;
    private String motivoReativacao;
    private String dataReativacao;
    private String horaReativacao;
    private String dataPrevistaRaAtual;
    private String idUnidadeReativacao;
    private String unidadeReativacao;
    private String idUnidadeRaAtual;
    private String unidadeRaAtual;
    private String observacaoReativacao;
    
    //Dados do Encerramento
    private String idMotivoEncerramento;
    private String motivoEncerramento;
    private String numeroRaReferencia;
    private String situacaoRaReferencia;
    private String dataEncerramento;
    private String horaEncerramento;
    private String dataPrevistaEncerramento;
    private String idUnidadeEncerramento;
    private String unidadeEncerramento;
    private String idUsuarioEncerramento;
    private String usuarioEncerramento;
    private String parecerEncerramento;
    
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

        numeroRA = null;
        numeroRAManual = null;
        situacaoRA = null;
        numeroRaAssociado = null;
        situacaoRaAssociado = null;
        idTipoSolicitacao = null;
        tipoSolicitacao = null;
        idEspecificacao = null;
        especificacao = null;
        tipoAtendimento = null;
        dataAtendimento = null;
        horaAtendimento = null;
        tempoEsperaInicio = null;
        tempoEsperaTermino = null;
        dataPrevista = null;
        idMeioSolicitacao = null;
        meioSolicitacao = null;
        
        idUnidadeAtendimento = null;
        unidadeAtendimento = null;
        
        idUnidadeAtual = null;
        unidadeAtual = null;
        observacao = null;
        
        //Dados do Local de Ocorrência
        matriculaImovel = null;
        inscricaoImovel = null;
        rota = null;
        sequencialRota = null;
        enderecoOcorrencia = null;
        pontoReferencia = null;
        idMunicipio = null;
        municipio = null;
        idBairro = null;
        bairro = null;
        idAreaBairro = null;
        areaBairro = null;
        
        idLocalidade = null;
        localidade = null;
        idSetorComercial = null;
        setorComercial = null;
        idQuadra = null;
        quadra = null;
        idDivisaoEsgoto = null;
        divisaoEsgoto = null;
        
        localOcorrencia = null;
        pavimentoRua = null;
        pavimentoCalcada = null;
        
        descricaoLocalOcorrencia = null;
        
        //Dados do Solicitante
        idClienteSolicitante = null;
        clienteSolicitante = null;
        idUnidadeSolicitante = null;
        unidadeSolicitante = null;
        idFuncionarioResponsavel = null;
        funcionarioResponsavel = null;
        nomeSolicitante = null;
        enderecoSolicitante = null;
        pontoReferenciaSolicitante = null;
        fone = null;
        foneDDD = null;
        foneRamal = null;
        
        
        //Dados da ultima Tramitação
        idUnidadeOrigem = null;
        unidadeOrigem = null;
        idUnidadeAtualTramitacao = null;
        unidadeAtualTramitacao = null;
        dataTramite = null;
        horaTramite = null;
        idUsuarioResponsavel = null;
        usuarioResponsavel = null;
        parecerTramite = null;
        
        //Dados da Ultima Reiteração
        quantidade = null;
        dataUltimaReiteracao = null;
        horaUltimaReiteracao = null;
        
        //Dados da Reativação
        numeroRaAtual = null;
        situacaoRaAtual = null;
        idMotivoReativacao = null;
        motivoReativacao = null;
        dataReativacao = null;
        horaReativacao = null;
        dataPrevistaRaAtual = null;
        idUnidadeReativacao = null;
        unidadeReativacao = null;
        idUnidadeRaAtual = null;
        unidadeRaAtual = null;
        observacaoReativacao = null;
        
        //Dados do Encerramento
        idMotivoEncerramento = null;
        motivoEncerramento = null;
        numeroRaReferencia = null;
        situacaoRaReferencia = null;
        dataEncerramento = null;
        horaEncerramento = null;
        dataPrevistaEncerramento = null;
        idUnidadeEncerramento = null;
        unidadeEncerramento = null;
        idUsuarioEncerramento = null;
        usuarioEncerramento = null;
        parecerEncerramento = null;
        
	}

    //Valida Buttons
    private String codigoSituacao;
    
    
    
	public String getNumeroRAManual() {
		return numeroRAManual;
	}

	public void setNumeroRAManual(String numeroRAManual) {
		this.numeroRAManual = numeroRAManual;
	}

	public String getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(String codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getDataPrevistaEncerramento() {
		return dataPrevistaEncerramento;
	}

	public void setDataPrevistaEncerramento(String dataPrevistaEncerramento) {
		this.dataPrevistaEncerramento = dataPrevistaEncerramento;
	}

	public String getDataPrevistaRaAtual() {
		return dataPrevistaRaAtual;
	}

	public void setDataPrevistaRaAtual(String dataPrevistaRaAtual) {
		this.dataPrevistaRaAtual = dataPrevistaRaAtual;
	}

	public String getDataReativacao() {
		return dataReativacao;
	}

	public void setDataReativacao(String dataReativacao) {
		this.dataReativacao = dataReativacao;
	}

	public String getDataUltimaReiteracao() {
		return dataUltimaReiteracao;
	}

	public void setDataUltimaReiteracao(String dataUltimaReiteracao) {
		this.dataUltimaReiteracao = dataUltimaReiteracao;
	}

	public String getHoraEncerramento() {
		return horaEncerramento;
	}

	public void setHoraEncerramento(String horaEncerramento) {
		this.horaEncerramento = horaEncerramento;
	}

	public String getHoraReativacao() {
		return horaReativacao;
	}

	public void setHoraReativacao(String horaReativacao) {
		this.horaReativacao = horaReativacao;
	}

	public String getHoraUltimaReiteracao() {
		return horaUltimaReiteracao;
	}

	public void setHoraUltimaReiteracao(String horaUltimaReiteracao) {
		this.horaUltimaReiteracao = horaUltimaReiteracao;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getIdMotivoReativacao() {
		return idMotivoReativacao;
	}

	public void setIdMotivoReativacao(String idMotivoReativacao) {
		this.idMotivoReativacao = idMotivoReativacao;
	}

	public String getIdUnidadeEncerramento() {
		return idUnidadeEncerramento;
	}

	public void setIdUnidadeEncerramento(String idUnidadeEncerramento) {
		this.idUnidadeEncerramento = idUnidadeEncerramento;
	}

	public String getIdUnidadeRaAtual() {
		return idUnidadeRaAtual;
	}

	public void setIdUnidadeRaAtual(String idUnidadeRaAtual) {
		this.idUnidadeRaAtual = idUnidadeRaAtual;
	}

	public String getIdUnidadeReativacao() {
		return idUnidadeReativacao;
	}

	public void setIdUnidadeReativacao(String idUnidadeReativacao) {
		this.idUnidadeReativacao = idUnidadeReativacao;
	}

	public String getIdUsuarioEncerramento() {
		return idUsuarioEncerramento;
	}

	public void setIdUsuarioEncerramento(String idUsuarioEncerramento) {
		this.idUsuarioEncerramento = idUsuarioEncerramento;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getMotivoReativacao() {
		return motivoReativacao;
	}

	public void setMotivoReativacao(String motivoReativacao) {
		this.motivoReativacao = motivoReativacao;
	}

	public String getNumeroRaAtual() {
		return numeroRaAtual;
	}

	public void setNumeroRaAtual(String numeroRaAtual) {
		this.numeroRaAtual = numeroRaAtual;
	}

	public String getNumeroRaReferencia() {
		return numeroRaReferencia;
	}

	public void setNumeroRaReferencia(String numeroRaReferencia) {
		this.numeroRaReferencia = numeroRaReferencia;
	}

	public String getObservacaoReativacao() {
		return observacaoReativacao;
	}

	public void setObservacaoReativacao(String observacaoReativacao) {
		this.observacaoReativacao = observacaoReativacao;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getSituacaoRaAtual() {
		return situacaoRaAtual;
	}

	public void setSituacaoRaAtual(String situacaoRaAtual) {
		this.situacaoRaAtual = situacaoRaAtual;
	}

	public String getSituacaoRaReferencia() {
		return situacaoRaReferencia;
	}

	public void setSituacaoRaReferencia(String situacaoRaReferencia) {
		this.situacaoRaReferencia = situacaoRaReferencia;
	}

	public String getUnidadeEncerramento() {
		return unidadeEncerramento;
	}

	public void setUnidadeEncerramento(String unidadeEncerramento) {
		this.unidadeEncerramento = unidadeEncerramento;
	}

	public String getUnidadeRaAtual() {
		return unidadeRaAtual;
	}

	public void setUnidadeRaAtual(String unidadeRaAtual) {
		this.unidadeRaAtual = unidadeRaAtual;
	}

	public String getUnidadeReativacao() {
		return unidadeReativacao;
	}

	public void setUnidadeReativacao(String unidadeReativacao) {
		this.unidadeReativacao = unidadeReativacao;
	}

	public String getUsuarioEncerramento() {
		return usuarioEncerramento;
	}

	public void setUsuarioEncerramento(String usuarioEncerramento) {
		this.usuarioEncerramento = usuarioEncerramento;
	}

	public String getDataTramite() {
		return dataTramite;
	}

	public void setDataTramite(String dataTramite) {
		this.dataTramite = dataTramite;
	}

	public String getHoraTramite() {
		return horaTramite;
	}

	public void setHoraTramite(String horaTramite) {
		this.horaTramite = horaTramite;
	}

	public String getIdUnidadeAtualTramitacao() {
		return idUnidadeAtualTramitacao;
	}

	public void setIdUnidadeAtualTramitacao(String idUnidadeAtualTramitacao) {
		this.idUnidadeAtualTramitacao = idUnidadeAtualTramitacao;
	}

	public String getIdUnidadeOrigem() {
		return idUnidadeOrigem;
	}

	public void setIdUnidadeOrigem(String idUnidadeOrigem) {
		this.idUnidadeOrigem = idUnidadeOrigem;
	}

	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}

	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}

	public String getParecerTramite() {
		return parecerTramite;
	}

	public void setParecerTramite(String parecerTramite) {
		this.parecerTramite = parecerTramite;
	}

	public String getUnidadeAtualTramitacao() {
		return unidadeAtualTramitacao;
	}

	public void setUnidadeAtualTramitacao(String unidadeAtualTramitacao) {
		this.unidadeAtualTramitacao = unidadeAtualTramitacao;
	}

	public String getUnidadeOrigem() {
		return unidadeOrigem;
	}

	public void setUnidadeOrigem(String unidadeOrigem) {
		this.unidadeOrigem = unidadeOrigem;
	}

	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
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

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(String funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public String getIdClienteSolicitante() {
		return idClienteSolicitante;
	}

	public void setIdClienteSolicitante(String idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
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

	public String getPontoReferenciaSolicitante() {
		return pontoReferenciaSolicitante;
	}

	public void setPontoReferenciaSolicitante(String pontoReferenciaSolicitante) {
		this.pontoReferenciaSolicitante = pontoReferenciaSolicitante;
	}

	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}

	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}

	public String getDescricaoLocalOcorrencia() {
		return descricaoLocalOcorrencia;
	}

	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}

	public String getLocalOcorrencia() {
		return localOcorrencia;
	}

	public void setLocalOcorrencia(String localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}

	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public String getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public String getDivisaoEsgoto() {
		return divisaoEsgoto;
	}

	public void setDivisaoEsgoto(String divisaoEsgoto) {
		this.divisaoEsgoto = divisaoEsgoto;
	}

	public String getIdDivisaoEsgoto() {
		return idDivisaoEsgoto;
	}

	public void setIdDivisaoEsgoto(String idDivisaoEsgoto) {
		this.idDivisaoEsgoto = idDivisaoEsgoto;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getAreaBairro() {
		return areaBairro;
	}

	public void setAreaBairro(String areaBairro) {
		this.areaBairro = areaBairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public String getIdAreaBairro() {
		return idAreaBairro;
	}

	public void setIdAreaBairro(String idAreaBairro) {
		this.idAreaBairro = idAreaBairro;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}
	
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	
	public String getDataPrevista() {
		return dataPrevista;
	}
	
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public String getEspecificacao() {
		return especificacao;
	}
	
	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}
	
	public String getHoraAtendimento() {
		return horaAtendimento;
	}
	
	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	
	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}
	
	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}
	
	public String getNumeroRA() {
		return numeroRA;
	}
	
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	
	public String getNumeroRaAssociado() {
		return numeroRaAssociado;
	}
	
	public void setNumeroRaAssociado(String numeroRaAssociado) {
		this.numeroRaAssociado = numeroRaAssociado;
	}
	
	public String getObservacao() {
		return observacao;
	}
	
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public String getSituacaoRA() {
		return situacaoRA;
	}
	
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}
	
	public String getSituacaoRaAssociado() {
		return situacaoRaAssociado;
	}
	
	public void setSituacaoRaAssociado(String situacaoRaAssociado) {
		this.situacaoRaAssociado = situacaoRaAssociado;
	}
	
	public String getTempoEsperaInicio() {
		return tempoEsperaInicio;
	}
	
	public void setTempoEsperaInicio(String tempoEsperaInicio) {
		this.tempoEsperaInicio = tempoEsperaInicio;
	}
	
	public String getTempoEsperaTermino() {
		return tempoEsperaTermino;
	}
	
	public void setTempoEsperaTermino(String tempoEsperaTermino) {
		this.tempoEsperaTermino = tempoEsperaTermino;
	}
	
	public String getTipoAtendimento() {
		return tipoAtendimento;
	}
	
	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
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

	public String getFoneDDD() {
		return foneDDD;
	}

	public void setFoneDDD(String foneDDD) {
		this.foneDDD = foneDDD;
	}

	public String getFoneRamal() {
		return foneRamal;
	}

	public void setFoneRamal(String foneRamal) {
		this.foneRamal = foneRamal;
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

	public String getIdMeioSolicitacao() {
		return idMeioSolicitacao;
	}

	public void setIdMeioSolicitacao(String idMeioSolicitacao) {
		this.idMeioSolicitacao = idMeioSolicitacao;
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

	public String getIdUsuarioAbrirRA() {
		return idUsuarioAbrirRA;
	}

	public void setIdUsuarioAbrirRA(String idUsuarioAbrirRA) {
		this.idUsuarioAbrirRA = idUsuarioAbrirRA;
	}

	public String getUsuarioAbrirRA() {
		return usuarioAbrirRA;
	}

	public void setUsuarioAbrirRA(String usuarioAbrirRA) {
		this.usuarioAbrirRA = usuarioAbrirRA;
	}

	public String getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}

	public void setIdUsuarioRegistro(String idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getValorSugerido() {
		return valorSugerido;
	}

	public void setValorSugerido(String valorSugerido) {
		this.valorSugerido = valorSugerido;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}
	
}
