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


import gcom.util.ConstantesSistema;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class EncerrarRegistroAtendimentoActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	// Dados Gerais, Dados do Solicitante & Dados do Local de Ocorrência
    private String numeroRA;
    private String situacaoRA;
    private String codigoSituacaoRA;
    private String numeroRaAssociado;
    private String situacaoRaAssociado;
    private String tipoSolicitacaoId;
    private String tipoSolicitacaoDescricao;
    private String tipoSolicitacaoIndicadorTarifaSocial;
    private String especificacaoId;
    private String especificacaoDescricao;
    private String especificacaoIndicadorParecer;
    private String meioSolicitacaoId;
    private String meioSolicitacaoDescricao;    
    private String matriculaImovel;
    private String inscricaoImovel;
    private String dataAtendimento;
    private String horaAtendimento;
    private String dataPrevista;
    private String dataEncerramentoRA;
    private String idMotivoEncerramento;
    private String motivoEncerramento;
    private String idClienteSolicitante;
    private String clienteSolicitante;
    private String idUnidadeSolicitante;
    private String unidadeSolicitante;
    private String nomeSolicitante;
    private String enderecoOcorrencia;
    private String pontoReferencia;
    private String bairroId;
    private String bairroDescricao;
    private String areaBairroId;
    private String areaBairroDescricao;
    private String localidadeId;
    private String localidadeDescricao;
    private String setorComercialId;
    private String setorComercialDescricao;
    private String quadraId;
    private String quadraDescricao;
    private String divisaoEsgotoId;
    private String divisaoEsgotoDescricao;
    private String unidadeAtendimentoId;
    private String unidadeAtendimentoDescricao;
    private String unidadeAtualId;
    private String unidadeAtualDescricao;
    private String unidadeAtualCodigoTipo;    
    private String unidadeAtualIdCentralizadora;
    private String indicadorAutorizacaoManutencaoRA;
    
    // Dados da Tramitação
    private String motivoEncerramentoId;
    private String motivoEncerramentoDescricao;
    private String numeroRAReferencia;
    private String numeroRAReferenciaRetorno;    
    private String dataEncerramento;
    private String horaEncerramento;
    private String parecerEncerramento;
    
    // Login do Usuário
    private String usuarioRegistroId;
    private String usuarioRegistroNome;
    private String usuarioRegistroUnidade;
    private String usuarioRegistroUnidadeIndicadorTarifaSocial;
    private String usuarioRegistroUnidadeIndicadorCentralAtendimento;
    
    // Controle
	private String validaNumeroRAReferencia = "false";
	private String resetarEncerramento = "false";
	private Date dataConcorrenciaRA;
	
	private String indicadorDuplicidade;
	
	//Dados do Débito
	private String idTipoDebito;
	private String descricaoTipoDebito;
	private String valorDebito;
	private String motivoNaoCobranca;
	private String percentualCobranca;
	private String quantidadeParcelas;
	private String valorParcelas;
	private String alteracaoValor;
	
    
	public String getIndicadorDuplicidade() {
		return indicadorDuplicidade;
	}

	public void setIndicadorDuplicidade(String indicadorDuplicidade) {
		this.indicadorDuplicidade = indicadorDuplicidade;
	}

	public void resetarEncerramento(){
		this.setMotivoEncerramentoId(ConstantesSistema.NUMERO_NAO_INFORMADO+"");
		this.setMotivoEncerramentoDescricao(null);
		this.setNumeroRAReferencia(null);
		this.setNumeroRAReferenciaRetorno(null);
		this.setDataEncerramento(null);
		this.setHoraEncerramento(null);
		this.setParecerEncerramento(null);
		this.setValidaNumeroRAReferencia("false");
		this.setResetarEncerramento("false");
//		this.setDataConcorrenciaRA(new Date());
	}
	
	public String getResetarEncerramento() {
		return resetarEncerramento;
	}
	public void setResetarEncerramento(String resetarEncerramento) {
		this.resetarEncerramento = resetarEncerramento;
	}
	public String getAreaBairroDescricao() {
		return areaBairroDescricao;
	}
	public void setAreaBairroDescricao(String areaBairroDescricao) {
		this.areaBairroDescricao = areaBairroDescricao;
	}
	public String getAreaBairroId() {
		return areaBairroId;
	}
	public void setAreaBairroId(String areaBairroId) {
		this.areaBairroId = areaBairroId;
	}
	public String getBairroDescricao() {
		return bairroDescricao;
	}
	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}
	public String getBairroId() {
		return bairroId;
	}
	public void setBairroId(String bairroId) {
		this.bairroId = bairroId;
	}
	public String getClienteSolicitante() {
		return clienteSolicitante;
	}
	public void setClienteSolicitante(String clienteSolicitante) {
		this.clienteSolicitante = clienteSolicitante;
	}
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getDivisaoEsgotoDescricao() {
		return divisaoEsgotoDescricao;
	}
	public void setDivisaoEsgotoDescricao(String divisaoEsgotoDescricao) {
		this.divisaoEsgotoDescricao = divisaoEsgotoDescricao;
	}
	public String getDivisaoEsgotoId() {
		return divisaoEsgotoId;
	}
	public void setDivisaoEsgotoId(String divisaoEsgotoId) {
		this.divisaoEsgotoId = divisaoEsgotoId;
	}
	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}
	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}
	public String getEspecificacaoDescricao() {
		return especificacaoDescricao;
	}
	public void setEspecificacaoDescricao(String especificacaoDescricao) {
		this.especificacaoDescricao = especificacaoDescricao;
	}
	public String getEspecificacaoId() {
		return especificacaoId;
	}
	public void setEspecificacaoId(String especificacaoId) {
		this.especificacaoId = especificacaoId;
	}
	public String getHoraAtendimento() {
		return horaAtendimento;
	}
	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	public String getIdClienteSolicitante() {
		return idClienteSolicitante;
	}
	public void setIdClienteSolicitante(String idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
	}
	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}
	public String getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}
	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	public String getLocalidadeId() {
		return localidadeId;
	}
	public void setLocalidadeId(String localidadeId) {
		this.localidadeId = localidadeId;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMeioSolicitacaoDescricao() {
		return meioSolicitacaoDescricao;
	}
	public void setMeioSolicitacaoDescricao(String meioSolicitacaoDescricao) {
		this.meioSolicitacaoDescricao = meioSolicitacaoDescricao;
	}
	public String getMeioSolicitacaoId() {
		return meioSolicitacaoId;
	}
	public void setMeioSolicitacaoId(String meioSolicitacaoId) {
		this.meioSolicitacaoId = meioSolicitacaoId;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
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
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	public String getQuadraDescricao() {
		return quadraDescricao;
	}
	public void setQuadraDescricao(String quadraDescricao) {
		this.quadraDescricao = quadraDescricao;
	}
	public String getQuadraId() {
		return quadraId;
	}
	public void setQuadraId(String quadraId) {
		this.quadraId = quadraId;
	}
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}
	public String getSetorComercialId() {
		return setorComercialId;
	}
	public void setSetorComercialId(String setorComercialId) {
		this.setorComercialId = setorComercialId;
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
	public String getTipoSolicitacaoDescricao() {
		return tipoSolicitacaoDescricao;
	}
	public void setTipoSolicitacaoDescricao(String tipoSolicitacaoDescricao) {
		this.tipoSolicitacaoDescricao = tipoSolicitacaoDescricao;
	}
	public String getTipoSolicitacaoId() {
		return tipoSolicitacaoId;
	}
	public void setTipoSolicitacaoId(String tipoSolicitacaoId) {
		this.tipoSolicitacaoId = tipoSolicitacaoId;
	}
	public String getUnidadeAtendimentoDescricao() {
		return unidadeAtendimentoDescricao;
	}
	public void setUnidadeAtendimentoDescricao(String unidadeAtendimentoDescricao) {
		this.unidadeAtendimentoDescricao = unidadeAtendimentoDescricao;
	}
	public String getUnidadeAtendimentoId() {
		return unidadeAtendimentoId;
	}
	public void setUnidadeAtendimentoId(String unidadeAtendimentoId) {
		this.unidadeAtendimentoId = unidadeAtendimentoId;
	}
	public String getUnidadeAtualDescricao() {
		return unidadeAtualDescricao;
	}
	public void setUnidadeAtualDescricao(String unidadeAtualDescricao) {
		this.unidadeAtualDescricao = unidadeAtualDescricao;
	}
	public String getUnidadeAtualId() {
		return unidadeAtualId;
	}
	public void setUnidadeAtualId(String unidadeAtualId) {
		this.unidadeAtualId = unidadeAtualId;
	}
	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}
	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
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
	public String getTipoSolicitacaoIndicadorTarifaSocial() {
		return tipoSolicitacaoIndicadorTarifaSocial;
	}
	public void setTipoSolicitacaoIndicadorTarifaSocial(
			String tipoSolicitacaoIndicadorTarifaSocial) {
		this.tipoSolicitacaoIndicadorTarifaSocial = tipoSolicitacaoIndicadorTarifaSocial;
	}
	public String getUsuarioRegistroUnidadeIndicadorTarifaSocial() {
		return usuarioRegistroUnidadeIndicadorTarifaSocial;
	}
	public void setUsuarioRegistroUnidadeIndicadorTarifaSocial(
			String usuarioRegistroUnidadeIndicadorTarifaSocial) {
		this.usuarioRegistroUnidadeIndicadorTarifaSocial = usuarioRegistroUnidadeIndicadorTarifaSocial;
	}
	public String getUsuarioRegistroUnidade() {
		return usuarioRegistroUnidade;
	}
	public void setUsuarioRegistroUnidade(String usuarioRegistroUnidade) {
		this.usuarioRegistroUnidade = usuarioRegistroUnidade;
	}
	public String getCodigoSituacaoRA() {
		return codigoSituacaoRA;
	}
	public void setCodigoSituacaoRA(String codigoSituacaoRA) {
		this.codigoSituacaoRA = codigoSituacaoRA;
	}
	public String getUnidadeAtualCodigoTipo() {
		return unidadeAtualCodigoTipo;
	}
	public void setUnidadeAtualCodigoTipo(String unidadeAtualCodigoTipo) {
		this.unidadeAtualCodigoTipo = unidadeAtualCodigoTipo;
	}
	public String getUnidadeAtualIdCentralizadora() {
		return unidadeAtualIdCentralizadora;
	}
	public void setUnidadeAtualIdCentralizadora(String unidadeAtualIdCentralizadora) {
		this.unidadeAtualIdCentralizadora = unidadeAtualIdCentralizadora;
	}

	public Date getDataConcorrenciaRA() {
		return dataConcorrenciaRA;
	}

	public void setDataConcorrenciaRA(Date dataConcorrenciaRA) {
		this.dataConcorrenciaRA = dataConcorrenciaRA;
	}

	public String getHoraEncerramento() {
		return horaEncerramento;
	}

	public void setHoraEncerramento(String horaEncerramento) {
		this.horaEncerramento = horaEncerramento;
	}

	public String getMotivoEncerramentoDescricao() {
		return motivoEncerramentoDescricao;
	}

	public void setMotivoEncerramentoDescricao(String motivoEncerramentoDescricao) {
		this.motivoEncerramentoDescricao = motivoEncerramentoDescricao;
	}

	public String getMotivoEncerramentoId() {
		return motivoEncerramentoId;
	}

	public void setMotivoEncerramentoId(String motivoEncerramentoId) {
		this.motivoEncerramentoId = motivoEncerramentoId;
	}

	public String getNumeroRAReferencia() {
		return numeroRAReferencia;
	}

	public void setNumeroRAReferencia(String numeroRAReferencia) {
		this.numeroRAReferencia = numeroRAReferencia;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getValidaNumeroRAReferencia() {
		return validaNumeroRAReferencia;
	}

	public void setValidaNumeroRAReferencia(String validaNumeroRAReferencia) {
		this.validaNumeroRAReferencia = validaNumeroRAReferencia;
	}

	public String getNumeroRAReferenciaRetorno() {
		return numeroRAReferenciaRetorno;
	}

	public void setNumeroRAReferenciaRetorno(String numeroRAReferenciaRetorno) {
		this.numeroRAReferenciaRetorno = numeroRAReferenciaRetorno;
	}

	public String getIndicadorAutorizacaoManutencaoRA() {
		return indicadorAutorizacaoManutencaoRA;
	}

	public void setIndicadorAutorizacaoManutencaoRA(
			String indicadorAutorizacaoManutencaoRA) {
		this.indicadorAutorizacaoManutencaoRA = indicadorAutorizacaoManutencaoRA;
	}

	public String getDataEncerramentoRA() {
		return dataEncerramentoRA;
	}

	public void setDataEncerramentoRA(String dataEncerramentoRA) {
		this.dataEncerramentoRA = dataEncerramentoRA;
	}

	public String getUsuarioRegistroUnidadeIndicadorCentralAtendimento() {
		return usuarioRegistroUnidadeIndicadorCentralAtendimento;
	}

	public void setUsuarioRegistroUnidadeIndicadorCentralAtendimento(
			String usuarioRegistroUnidadeIndicadorCentralAtendimento) {
		this.usuarioRegistroUnidadeIndicadorCentralAtendimento = usuarioRegistroUnidadeIndicadorCentralAtendimento;
	}

	public String getEspecificacaoIndicadorParecer() {
		return especificacaoIndicadorParecer;
	}

	public void setEspecificacaoIndicadorParecer(
			String especificacaoIndicadorParecer) {
		this.especificacaoIndicadorParecer = especificacaoIndicadorParecer;
	}

	public String getPercentualCobranca() {
		return percentualCobranca;
	}

	public void setPercentualCobranca(String percentualCobranca) {
		this.percentualCobranca = percentualCobranca;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getAlteracaoValor() {
		return alteracaoValor;
	}

	public void setAlteracaoValor(String alteracaoValor) {
		this.alteracaoValor = alteracaoValor;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getMotivoNaoCobranca() {
		return motivoNaoCobranca;
	}

	public void setMotivoNaoCobranca(String motivoNaoCobranca) {
		this.motivoNaoCobranca = motivoNaoCobranca;
	}

	public String getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(String quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getValorParcelas() {
		return valorParcelas;
	}

	public void setValorParcelas(String valorParcelas) {
		this.valorParcelas = valorParcelas;
	}
	
	
}