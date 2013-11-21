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

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CriterioSituacaoCobranca;
import gcom.cobranca.CriterioSituacaoCobrancaPK;
import gcom.cobranca.CriterioSituacaoLigacaoAgua;
import gcom.cobranca.CriterioSituacaoLigacaoAguaPK;
import gcom.cobranca.CriterioSituacaoLigacaoEsgoto;
import gcom.cobranca.CriterioSituacaoLigacaoEsgotoPK;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para inserir o critério da cobrança e as linhas do criterio da
 * cobrança
 * 
 * @author Sávio Luiz
 * @date 03/05/2006
 */
public class InserirCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		Integer idCobrancaCriterio = null;

		// cria o objeto criterio cobrança para ser inserido
		CobrancaCriterio cobrancaCriterio = new CobrancaCriterio();

		if (criterioCobrancaActionForm.getDescricaoCriterio() != null
				&& !criterioCobrancaActionForm.getDescricaoCriterio()
						.equals("")) {
			cobrancaCriterio
					.setDescricaoCobrancaCriterio(criterioCobrancaActionForm
							.getDescricaoCriterio());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição do Critério de Cobrança");
		}
		Date dataInicio = null;
		if (criterioCobrancaActionForm.getDataInicioVigencia() != null
				&& !criterioCobrancaActionForm.getDataInicioVigencia().equals(
						"")) {
			String dataInicioVigencia = criterioCobrancaActionForm
					.getDataInicioVigencia();
			if (Util.validarDiaMesAno(dataInicioVigencia)) {
				throw new ActionServletException(
						"atencao.data.inicio.Vigencia.invalida");
			}

			dataInicio = Util.converteStringParaDate(dataInicioVigencia);
			Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
			if (dataInicio.before(dataAtualSemHora)) {
				String dataAtual = Util.formatarData(new Date());
				throw new ActionServletException(
						"atencao.data.inicio.nao.superior.data.corrente", null,
						dataAtual);
			}
		} else {
			throw new ActionServletException("atencao.required", null,
					"Data de Início de Vigência do Critério");
		}
		cobrancaCriterio.setDataInicioVigencia(dataInicio);
		if (criterioCobrancaActionForm.getNumeroAnoContaAntiga() != null
				&& !criterioCobrancaActionForm.getNumeroAnoContaAntiga()
						.equals("")) {
			boolean valorNaoNumerico = Util
					.validarValorNaoNumerico(criterioCobrancaActionForm
							.getNumeroAnoContaAntiga());
			if (!valorNaoNumerico) {
				cobrancaCriterio.setNumeroContaAntiga(new Short(
						criterioCobrancaActionForm.getNumeroAnoContaAntiga()));
			} else {
				throw new ActionServletException("atencao.integer", null,
						"Número de Anos para Determinar Conta Antiga");
			}
		} else {
			throw new ActionServletException("atencao.required", null,
					"Número de Anos para Determinar Conta Antiga");
		}

		if (criterioCobrancaActionForm.getValorLimitePrioridade() != null
				&& !criterioCobrancaActionForm.getValorLimitePrioridade()
						.equals("")) {
			BigDecimal valorLimitePrioridade = new BigDecimal(
					criterioCobrancaActionForm.getValorLimitePrioridade().replace(".", "")
							.replace(",", "."));
			cobrancaCriterio.setValorLimitePrioridade(valorLimitePrioridade);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Valor Limite para Prioridade");
		}

		if (criterioCobrancaActionForm
				.getPercentualValorMinimoPagoParceladoCancelado() != null
				&& !criterioCobrancaActionForm
						.getPercentualValorMinimoPagoParceladoCancelado()
						.equals("")) {
			BigDecimal percentualValorMinimoPagoParceladoCancelado = new BigDecimal(
					criterioCobrancaActionForm
							.getPercentualValorMinimoPagoParceladoCancelado()
							.replace(".", "").replace(",", "."));
			cobrancaCriterio
					.setPercentualValorMinimoPagoParceladoCancelado(percentualValorMinimoPagoParceladoCancelado);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Percentual Valor");
		}

		if (criterioCobrancaActionForm
				.getPercentualQuantidadeMinimoPagoParceladoCancelado() != null
				&& !criterioCobrancaActionForm
						.getPercentualQuantidadeMinimoPagoParceladoCancelado()
						.equals("")) {
			BigDecimal percentualQuantidadeMinimoPagoParceladoCancelado = new BigDecimal(
					criterioCobrancaActionForm
							.getPercentualQuantidadeMinimoPagoParceladoCancelado().replace(".", "")
							.replace(",", "."));
			cobrancaCriterio
					.setPercentualQuantidadeMinimoPagoParceladoCancelado(percentualQuantidadeMinimoPagoParceladoCancelado);
		} else {
			throw new ActionServletException("atencao.required", null,
					"Percentual Quantidade de Itens");
		}

		if (criterioCobrancaActionForm.getOpcaoAcaoImovelSitEspecial() != null
				&& !criterioCobrancaActionForm.getOpcaoAcaoImovelSitEspecial()
						.equals("")) {
			cobrancaCriterio
					.setIndicadorEmissaoImovelParalisacao(new Short(
							criterioCobrancaActionForm
									.getOpcaoAcaoImovelSitEspecial()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Sit. Especial de Cobrança");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelSit() != null
				&& !criterioCobrancaActionForm.getOpcaoAcaoImovelSit().equals(
						"")) {
			cobrancaCriterio
					.setIndicadorEmissaoImovelSituacaoCobranca(new Short(
							criterioCobrancaActionForm.getOpcaoAcaoImovelSit()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Sit. de Cobrança");
		}
		if (criterioCobrancaActionForm.getOpcaoContasRevisao() != null
				&& !criterioCobrancaActionForm.getOpcaoContasRevisao().equals(
						"")) {
			cobrancaCriterio
					.setIndicadorEmissaoContaRevisao(new Short(
							criterioCobrancaActionForm
									.getOpcaoAcaoImovelSitEspecial()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Considerar Contas em Revisão");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelDebitoMesConta() != null
				&& !criterioCobrancaActionForm
						.getOpcaoAcaoImovelDebitoMesConta().equals("")) {
			cobrancaCriterio.setIndicadorEmissaoDebitoContaMes(new Short(
					criterioCobrancaActionForm
							.getOpcaoAcaoImovelDebitoMesConta()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Débito só da Conta do Mês");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoInquilinoDebitoMesConta() != null
				&& !criterioCobrancaActionForm
						.getOpcaoAcaoInquilinoDebitoMesConta().equals("")) {
			cobrancaCriterio
					.setIndicadorEmissaoInquilinoDebitoContaMes(new Short(
							criterioCobrancaActionForm
									.getOpcaoAcaoInquilinoDebitoMesConta()));
		} else {
			throw new ActionServletException(
					"atencao.required",
					null,
					"Emissão da Ação para Inquilino Com Débito só da Conta do Mês Independentemente do Valor da Conta");
		}
		if (criterioCobrancaActionForm.getOpcaoAcaoImovelDebitoContasAntigas() != null
				&& !criterioCobrancaActionForm
						.getOpcaoAcaoImovelDebitoContasAntigas().equals("")) {
			cobrancaCriterio.setIndicadorEmissaoDebitoContaAntiga(new Short(
					criterioCobrancaActionForm
							.getOpcaoAcaoImovelDebitoContasAntigas()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Emissão da Ação para Imóvel com Débito só de Contas Antigas");
		}
				
		// Verificando se houve situacoes de cobranca escolhidas para o criterio
		if (criterioCobrancaActionForm.getIdsCobrancaSituacao() != null && 
				criterioCobrancaActionForm.getIdsCobrancaSituacao().length > 0){
			
			Collection criteriosSituacaoCobranca = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsCobrancaSituacao().length; i++) {
				CriterioSituacaoCobranca csc = new CriterioSituacaoCobranca();
				CriterioSituacaoCobrancaPK cscPK = new CriterioSituacaoCobrancaPK();
				CobrancaSituacao cobSit = new CobrancaSituacao();
				cobSit.setId(new Integer(criterioCobrancaActionForm.getIdsCobrancaSituacao()[i]));
				cscPK.setCobrancaSituacao(cobSit);
				csc.setComp_id(cscPK);
				criteriosSituacaoCobranca.add(csc);				
			}
			cobrancaCriterio.setCriteriosSituacaoCobranca(new HashSet(criteriosSituacaoCobranca));
		}
		
		// verificando se houveram situacoes de ligacao de agua para este criterio
		if (criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua() != null && 
				criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length > 0){
			
			Collection criteriosSituacaoLigacaoAgua = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length; i++) {
				CriterioSituacaoLigacaoAgua csla = new CriterioSituacaoLigacaoAgua();
				CriterioSituacaoLigacaoAguaPK cslaPK = new CriterioSituacaoLigacaoAguaPK();
				LigacaoAguaSituacao ligAguaSit = new LigacaoAguaSituacao();
				ligAguaSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua()[i]));
				cslaPK.setLigacaoAguaSituacao(ligAguaSit);
				csla.setComp_id(cslaPK);
				criteriosSituacaoLigacaoAgua.add(csla);				
			}
			cobrancaCriterio.setCriteriosSituacaoLigacaoAgua(new HashSet(criteriosSituacaoLigacaoAgua));
		}

//		 verificando se houveram situacoes de ligacao de esgoto para este criterio
		if (criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto() != null && 
				criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length > 0){
			
			Collection criteriosSituacaoLigacaoEsgoto = new ArrayList();
			
			for (int i = 0; i < criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length; i++) {
				CriterioSituacaoLigacaoEsgoto csle = new CriterioSituacaoLigacaoEsgoto();
				CriterioSituacaoLigacaoEsgotoPK cslePK = new CriterioSituacaoLigacaoEsgotoPK();
				LigacaoEsgotoSituacao ligEsgotoSit = new LigacaoEsgotoSituacao();
				ligEsgotoSit.setId(new Integer(criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto()[i]));
				cslePK.setLigacaoEsgotoSituacao(ligEsgotoSit);
				csle.setComp_id(cslePK);
				criteriosSituacaoLigacaoEsgoto.add(csle);				
			}
			cobrancaCriterio.setCriteriosSituacaoLigacaoEsgoto(new HashSet(criteriosSituacaoLigacaoEsgoto));
		}
		
		cobrancaCriterio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		cobrancaCriterio.setUltimaAlteracao(new Date());
		Collection colecaoCobrancaCriterioLinha = (Collection) sessao
				.getAttribute("colecaoCobrancaCriterioLinha");

		if (colecaoCobrancaCriterioLinha != null
				&& !colecaoCobrancaCriterioLinha.isEmpty()) {
			cobrancaCriterio.setCobrancaCriterioLinhas(new HashSet(
					colecaoCobrancaCriterioLinha));

			idCobrancaCriterio = fachada
					.inserirCobrancaCriterio(cobrancaCriterio,this.getUsuarioLogado(httpServletRequest));
		} else {
			throw new ActionServletException(
					"atencao.informar.linha.criterio.cobranca");
		}
		sessao.removeAttribute("colecaoCobrancaCriterioLinha");

		montarPaginaSucesso(httpServletRequest, "Critério de Cobrança "
				+ idCobrancaCriterio + " inserido com sucesso.",
				"Inserir outro Critério de Cobrança",
				"exibirInserirCriterioCobrancaAction.do?menu=sim",
				"exibirAtualizarCriterioCobrancaAction.do?idRegistroAtualizacao="
						+ idCobrancaCriterio + "&retornoFiltrar=1",
				"Atualizar Critério de Cobrança inserido");
		return retorno;
	}

}
