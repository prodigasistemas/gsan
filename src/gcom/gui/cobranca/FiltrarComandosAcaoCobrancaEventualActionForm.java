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
package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0326] Filtrar Comandos de Ação de Cobrança
 * @author Rafael Santos
 * @since 08/05/2006
 */
public class FiltrarComandosAcaoCobrancaEventualActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String[] grupoCobranca;
	
	private String[] acaoCobranca;
	
	private String[] atividadeCobranca;	
	
	private String periodoReferenciaContasInicial;
	
	private String periodoReferenciaContasFinal;
	
	private String periodoVencimentoContasInicial;
	
	private String periodoVencimentoContasFinal;
	
	private String periodoComandoInicial;
	
	private String periodoComandoFinal;
	
	private String periodoRealizacaoComandoInicial;
	
	private String periodoRealizacaoComandoFinal;	
	
	private String intervaloQuantidadeDocumentosInicial;
	
	private String intervaloQuantidadeDocumentosFinal;
	
	private String intervaloValorDocumentosInicial;
	
	private String intervaloValorDocumentosFinal;
		
	private String intervaloQuantidadeItensDocumentosInicial;
	
	private String intervaloQuantidadeItensDocumentosFinal;
	
	private String situacaoComando;
	
	private String gerenciaRegional;
	
	private String unidadeNegocio;	

	private String localidadeOrigemID;

	private String localidadeDestinoID;

	private String nomeLocalidadeOrigem;

	private String nomeLocalidadeDestino;

	private String setorComercialOrigemCD;

	private String setorComercialOrigemID;

	private String nomeSetorComercialOrigem;

	private String setorComercialDestinoCD;

	private String setorComercialDestinoID;

	private String nomeSetorComercialDestino;
	
	private String rotaInicial;

	private String rotaFinal;

	private String idCliente;

	private String nomeCliente;

	private String clienteRelacaoTipo;

	private String indicadorCriterio;
	
	private String criterioCobranca;
	
	private String nomeCriterioCobranca;
	
	private String inscricaoTipo;
	
	private String dataEmissaoInicio;
	
	private String dataEmissaoFim;
	
	private String[] IdCobrancaAcaoAtividadeComando;
	
	private String consumoMedioInicial;
	
	private String consumoMedioFinal;
	
	private String tipoConsumo;
	
	private String periodoInicialFiscalizacao;

	private String periodoFinalFiscalizacao;
	
	private String[] situacaoFiscalizacao;
	
	private String numeroQuadraInicial;
	private String numeroQuadraFinal;

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Retorna o campo acaoCobranca.
	 */
	public String[] getAcaoCobranca() {
		return acaoCobranca;
	}

	/**
	 * @param acaoCobranca O acaoCobranca a ser setado.
	 */
	public void setAcaoCobranca(String[] acaoCobranca) {
		this.acaoCobranca = acaoCobranca;
	}

	/**
	 * @return Retorna o campo atividadeCobranca.
	 */
	public String[] getAtividadeCobranca() {
		return atividadeCobranca;
	}

	/**
	 * @param atividadeCobranca O atividadeCobranca a ser setado.
	 */
	public void setAtividadeCobranca(String[] atividadeCobranca) {
		this.atividadeCobranca = atividadeCobranca;
	}

	/**
	 * @return Retorna o campo clienteRelacaoTipo.
	 */
	public String getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	/**
	 * @param clienteRelacaoTipo O clienteRelacaoTipo a ser setado.
	 */
	public void setClienteRelacaoTipo(String clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * @return Retorna o campo grupoCobranca.
	 */
	public String[] getGrupoCobranca() {
		return grupoCobranca;
	}

	/**
	 * @param grupoCobranca O grupoCobranca a ser setado.
	 */
	public void setGrupoCobranca(String[] grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}

	/**
	 * @return Retorna o campo idCliente.
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Retorna o campo indicadorCriterio.
	 */
	public String getIndicadorCriterio() {
		return indicadorCriterio;
	}

	/**
	 * @param indicadorCriterio O indicadorCriterio a ser setado.
	 */
	public void setIndicadorCriterio(String indicadorCriterio) {
		this.indicadorCriterio = indicadorCriterio;
	}

	/**
	 * @return Retorna o campo intervaloQuantidadeDocumentosFinal.
	 */
	public String getIntervaloQuantidadeDocumentosFinal() {
		return intervaloQuantidadeDocumentosFinal;
	}

	/**
	 * @param intervaloQuantidadeDocumentosFinal O intervaloQuantidadeDocumentosFinal a ser setado.
	 */
	public void setIntervaloQuantidadeDocumentosFinal(
			String intervaloQuantidadeDocumentosFinal) {
		this.intervaloQuantidadeDocumentosFinal = intervaloQuantidadeDocumentosFinal;
	}

	/**
	 * @return Retorna o campo intervaloQuantidadeDocumentosInicial.
	 */
	public String getIntervaloQuantidadeDocumentosInicial() {
		return intervaloQuantidadeDocumentosInicial;
	}

	/**
	 * @param intervaloQuantidadeDocumentosInicial O intervaloQuantidadeDocumentosInicial a ser setado.
	 */
	public void setIntervaloQuantidadeDocumentosInicial(
			String intervaloQuantidadeDocumentosInicial) {
		this.intervaloQuantidadeDocumentosInicial = intervaloQuantidadeDocumentosInicial;
	}

	/**
	 * @return Retorna o campo intervaloQuantidadeItensDocumentosFinal.
	 */
	public String getIntervaloQuantidadeItensDocumentosFinal() {
		return intervaloQuantidadeItensDocumentosFinal;
	}

	/**
	 * @param intervaloQuantidadeItensDocumentosFinal O intervaloQuantidadeItensDocumentosFinal a ser setado.
	 */
	public void setIntervaloQuantidadeItensDocumentosFinal(
			String intervaloQuantidadeItensDocumentosFinal) {
		this.intervaloQuantidadeItensDocumentosFinal = intervaloQuantidadeItensDocumentosFinal;
	}

	/**
	 * @return Retorna o campo intervaloQuantidadeItensDocumentosInicial.
	 */
	public String getIntervaloQuantidadeItensDocumentosInicial() {
		return intervaloQuantidadeItensDocumentosInicial;
	}

	/**
	 * @param intervaloQuantidadeItensDocumentosInicial O intervaloQuantidadeItensDocumentosInicial a ser setado.
	 */
	public void setIntervaloQuantidadeItensDocumentosInicial(
			String intervaloQuantidadeItensDocumentosInicial) {
		this.intervaloQuantidadeItensDocumentosInicial = intervaloQuantidadeItensDocumentosInicial;
	}

	/**
	 * @return Retorna o campo intervaloValorDocumentosFinal.
	 */
	public String getIntervaloValorDocumentosFinal() {
		return intervaloValorDocumentosFinal;
	}

	/**
	 * @param intervaloValorDocumentosFinal O intervaloValorDocumentosFinal a ser setado.
	 */
	public void setIntervaloValorDocumentosFinal(
			String intervaloValorDocumentosFinal) {
		this.intervaloValorDocumentosFinal = intervaloValorDocumentosFinal;
	}

	/**
	 * @return Retorna o campo intervaloValorDocumentosInicial.
	 */
	public String getIntervaloValorDocumentosInicial() {
		return intervaloValorDocumentosInicial;
	}

	/**
	 * @param intervaloValorDocumentosInicial O intervaloValorDocumentosInicial a ser setado.
	 */
	public void setIntervaloValorDocumentosInicial(
			String intervaloValorDocumentosInicial) {
		this.intervaloValorDocumentosInicial = intervaloValorDocumentosInicial;
	}

	/**
	 * @return Retorna o campo localidadeDestinoID.
	 */
	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	/**
	 * @param localidadeDestinoID O localidadeDestinoID a ser setado.
	 */
	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	/**
	 * @return Retorna o campo localidadeOrigemID.
	 */
	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	/**
	 * @param localidadeOrigemID O localidadeOrigemID a ser setado.
	 */
	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeDestino.
	 */
	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	/**
	 * @param nomeLocalidadeDestino O nomeLocalidadeDestino a ser setado.
	 */
	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeOrigem.
	 */
	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	/**
	 * @param nomeLocalidadeOrigem O nomeLocalidadeOrigem a ser setado.
	 */
	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialDestino.
	 */
	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	/**
	 * @param nomeSetorComercialDestino O nomeSetorComercialDestino a ser setado.
	 */
	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialOrigem.
	 */
	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	/**
	 * @param nomeSetorComercialOrigem O nomeSetorComercialOrigem a ser setado.
	 */
	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	/**
	 * @return Retorna o campo periodoComandoFinal.
	 */
	public String getPeriodoComandoFinal() {
		return periodoComandoFinal;
	}

	/**
	 * @param periodoComandoFinal O periodoComandoFinal a ser setado.
	 */
	public void setPeriodoComandoFinal(String periodoComandoFinal) {
		this.periodoComandoFinal = periodoComandoFinal;
	}

	/**
	 * @return Retorna o campo periodoComandoInicial.
	 */
	public String getPeriodoComandoInicial() {
		return periodoComandoInicial;
	}

	/**
	 * @param periodoComandoInicial O periodoComandoInicial a ser setado.
	 */
	public void setPeriodoComandoInicial(String periodoComandoInicial) {
		this.periodoComandoInicial = periodoComandoInicial;
	}

	/**
	 * @return Retorna o campo periodoRealizacaoComandoFinal.
	 */
	public String getPeriodoRealizacaoComandoFinal() {
		return periodoRealizacaoComandoFinal;
	}

	/**
	 * @param periodoRealizacaoComandoFinal O periodoRealizacaoComandoFinal a ser setado.
	 */
	public void setPeriodoRealizacaoComandoFinal(
			String periodoRealizacaoComandoFinal) {
		this.periodoRealizacaoComandoFinal = periodoRealizacaoComandoFinal;
	}

	/**
	 * @return Retorna o campo periodoRealizacaoComandoInicial.
	 */
	public String getPeriodoRealizacaoComandoInicial() {
		return periodoRealizacaoComandoInicial;
	}

	/**
	 * @param periodoRealizacaoComandoInicial O periodoRealizacaoComandoInicial a ser setado.
	 */
	public void setPeriodoRealizacaoComandoInicial(
			String periodoRealizacaoComandoInicial) {
		this.periodoRealizacaoComandoInicial = periodoRealizacaoComandoInicial;
	}

	/**
	 * @return Retorna o campo periodoReferenciaContasFinal.
	 */
	public String getPeriodoReferenciaContasFinal() {
		return periodoReferenciaContasFinal;
	}

	/**
	 * @param periodoReferenciaContasFinal O periodoReferenciaContasFinal a ser setado.
	 */
	public void setPeriodoReferenciaContasFinal(String periodoReferenciaContasFinal) {
		this.periodoReferenciaContasFinal = periodoReferenciaContasFinal;
	}

	/**
	 * @return Retorna o campo periodoReferenciaContasInicial.
	 */
	public String getPeriodoReferenciaContasInicial() {
		return periodoReferenciaContasInicial;
	}

	/**
	 * @param periodoReferenciaContasInicial O periodoReferenciaContasInicial a ser setado.
	 */
	public void setPeriodoReferenciaContasInicial(
			String periodoReferenciaContasInicial) {
		this.periodoReferenciaContasInicial = periodoReferenciaContasInicial;
	}

	/**
	 * @return Retorna o campo periodoVencimentoContasFinal.
	 */
	public String getPeriodoVencimentoContasFinal() {
		return periodoVencimentoContasFinal;
	}

	/**
	 * @param periodoVencimentoContasFinal O periodoVencimentoContasFinal a ser setado.
	 */
	public void setPeriodoVencimentoContasFinal(String periodoVencimentoContasFinal) {
		this.periodoVencimentoContasFinal = periodoVencimentoContasFinal;
	}

	/**
	 * @return Retorna o campo periodoVencimentoContasInicial.
	 */
	public String getPeriodoVencimentoContasInicial() {
		return periodoVencimentoContasInicial;
	}

	/**
	 * @param periodoVencimentoContasInicial O periodoVencimentoContasInicial a ser setado.
	 */
	public void setPeriodoVencimentoContasInicial(
			String periodoVencimentoContasInicial) {
		this.periodoVencimentoContasInicial = periodoVencimentoContasInicial;
	}

	/**
	 * @return Retorna o campo rotaFinal.
	 */
	public String getRotaFinal() {
		return rotaFinal;
	}

	/**
	 * @param rotaFinal O rotaFinal a ser setado.
	 */
	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	/**
	 * @return Retorna o campo rotaInicial.
	 */
	public String getRotaInicial() {
		return rotaInicial;
	}

	/**
	 * @param rotaInicial O rotaInicial a ser setado.
	 */
	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoCD.
	 */
	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	/**
	 * @param setorComercialDestinoCD O setorComercialDestinoCD a ser setado.
	 */
	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoID.
	 */
	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	/**
	 * @param setorComercialDestinoID O setorComercialDestinoID a ser setado.
	 */
	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemCD.
	 */
	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	/**
	 * @param setorComercialOrigemCD O setorComercialOrigemCD a ser setado.
	 */
	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemID.
	 */
	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	/**
	 * @param setorComercialOrigemID O setorComercialOrigemID a ser setado.
	 */
	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	/**
	 * @return Retorna o campo situacaoComando.
	 */
	public String getSituacaoComando() {
		return situacaoComando;
	}

	/**
	 * @param situacaoComando O situacaoComando a ser setado.
	 */
	public void setSituacaoComando(String situacaoComando) {
		this.situacaoComando = situacaoComando;
	}

	/**
	 * @return Retorna o campo criterioCobranca.
	 */
	public String getCriterioCobranca() {
		return criterioCobranca;
	}

	/**
	 * @param criterioCobranca O criterioCobranca a ser setado.
	 */
	public void setCriterioCobranca(String criterioCobranca) {
		this.criterioCobranca = criterioCobranca;
	}

	/**
	 * @return Retorna o campo inscricaoTipo.
	 */
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	/**
	 * @param inscricaoTipo O inscricaoTipo a ser setado.
	 */
	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	/**
	 * @return Retorna o campo nomeCriterioCobranca.
	 */
	public String getNomeCriterioCobranca() {
		return nomeCriterioCobranca;
	}

	/**
	 * @param nomeCriterioCobranca O nomeCriterioCobranca a ser setado.
	 */
	public void setNomeCriterioCobranca(String nomeCriterioCobranca) {
		this.nomeCriterioCobranca = nomeCriterioCobranca;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
	public String getDataEmissaoFim() {
		return dataEmissaoFim;
	}

	public void setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim = dataEmissaoFim;
	}

	public String getDataEmissaoInicio() {
		return dataEmissaoInicio;
	}

	public void setDataEmissaoInicio(String dataEmissaoInicio) {
		this.dataEmissaoInicio = dataEmissaoInicio;
	}
	
	public String[] getIdCobrancaAcaoAtividadeComando() {
		return IdCobrancaAcaoAtividadeComando;
	}

	public void setIdCobrancaAcaoAtividadeComando(
			String[] idCobrancaAcaoAtividadeComando) {
		IdCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}

	public String getConsumoMedioInicial() {
		return consumoMedioInicial;
	}

	public void setConsumoMedioInicial(String consumoMedioInicial) {
		this.consumoMedioInicial = consumoMedioInicial;
	}

	public String getConsumoMedioFinal() {
		return consumoMedioFinal;
	}

	public void setConsumoMedioFinal(String consumoMedioFinal) {
		this.consumoMedioFinal = consumoMedioFinal;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getPeriodoInicialFiscalizacao() {
		return periodoInicialFiscalizacao;
	}

	public void setPeriodoInicialFiscalizacao(String periodoInicialFiscalizacao) {
		this.periodoInicialFiscalizacao = periodoInicialFiscalizacao;
	}

	public String getPeriodoFinalFiscalizacao() {
		return periodoFinalFiscalizacao;
	}

	public void setPeriodoFinalFiscalizacao(String periodoFinalFiscalizacao) {
		this.periodoFinalFiscalizacao = periodoFinalFiscalizacao;
	}

	public String[] getSituacaoFiscalizacao() {
		return situacaoFiscalizacao;
	}

	public void setSituacaoFiscalizacao(String[] situacaoFiscalizacao) {
		this.situacaoFiscalizacao = situacaoFiscalizacao;
	}

	public String getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(String numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public String getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(String numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}
	
	

}

