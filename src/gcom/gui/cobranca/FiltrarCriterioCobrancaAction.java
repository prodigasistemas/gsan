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

import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * processamento para filtrar o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 05/05/2006
 */
public class FiltrarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCriterioCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		CriterioCobrancaFiltrarActionForm criterioCobrancaFiltrarActionForm = (CriterioCobrancaFiltrarActionForm) actionForm;

		// Recupera os parâmetros do form
		String descricaoCriterioCobranca = criterioCobrancaFiltrarActionForm
				.getDescricaoCriterio();
		String dataInicioVigencia = criterioCobrancaFiltrarActionForm
				.getDataInicioVigencia();
		String numeroAnosContaAntiga = criterioCobrancaFiltrarActionForm
				.getNumeroAnoContaAntiga();
		String opcaoAcaoImovelSitEspecial = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSitEspecial();
		String opcaoAcaoImovelSit = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelSit();
		String opcaoContasRevisao = criterioCobrancaFiltrarActionForm
				.getOpcaoContasRevisao();
		String opcaoAcaoImovelDebitoMesConta = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoMesConta();
		String opcaoAcaoInquilinoDebitoMesConta = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoInquilinoDebitoMesConta();
		String opcaoAcaoImovelDebitoContasAntigas = criterioCobrancaFiltrarActionForm
				.getOpcaoAcaoImovelDebitoContasAntigas();
		String indicadorUso = criterioCobrancaFiltrarActionForm
				.getIndicadorUso();
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar == null) {
			criterioCobrancaFiltrarActionForm.setIndicadorAtualizar("2");
		} else {
			criterioCobrancaFiltrarActionForm
					.setIndicadorAtualizar(indicadorAtualizar);
		}

		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio(
				FiltroCobrancaCriterio.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (descricaoCriterioCobranca != null
				&& !descricaoCriterioCobranca.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ComparacaoTexto(
					FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO,
					descricaoCriterioCobranca));
		}
		if (dataInicioVigencia != null
				&& !dataInicioVigencia.trim().equalsIgnoreCase("")) {
			Date dataVigencia = Util.converteStringParaDate(dataInicioVigencia);
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.DATA_INICIO_VIGENCIA, dataVigencia));
		}
		if (numeroAnosContaAntiga != null
				&& !numeroAnosContaAntiga.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.NUMERO_ANOS_CONTA_ANTIGA,
					numeroAnosContaAntiga));
		}

		if (opcaoAcaoImovelSitEspecial != null
				&& !opcaoAcaoImovelSitEspecial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelSitEspecial.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_IMOVEL_PARALISACAO,
						opcaoAcaoImovelSitEspecial));
			}
		}
		if (opcaoAcaoImovelSit != null
				&& !opcaoAcaoImovelSit.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelSit.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaCriterio.INDICADOR_IMOVEL_SITUACAO_COBRANCA,
								opcaoAcaoImovelSit));
			}
		}
		if (opcaoContasRevisao != null
				&& !opcaoContasRevisao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoContasRevisao.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_CONTA_REVISAO,
						opcaoContasRevisao));
			}
		}
		if (opcaoAcaoImovelDebitoMesConta != null
				&& !opcaoAcaoImovelDebitoMesConta.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelDebitoMesConta.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_MES,
						opcaoAcaoImovelDebitoMesConta));
			}
		}
		if (opcaoAcaoInquilinoDebitoMesConta != null
				&& !opcaoAcaoInquilinoDebitoMesConta.trim()
						.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoInquilinoDebitoMesConta.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaCriterio.INDICADOR_INQUILINO_DEBITO_CONTA_MES,
								opcaoAcaoInquilinoDebitoMesConta));
			}
		}
		if (opcaoAcaoImovelDebitoContasAntigas != null
				&& !opcaoAcaoImovelDebitoContasAntigas.trim().equalsIgnoreCase(
						"")) {
			peloMenosUmParametroInformado = true;
			if (!opcaoAcaoImovelDebitoContasAntigas.equalsIgnoreCase("3")) {
				filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
						FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_ANTIGA,
						opcaoAcaoImovelDebitoContasAntigas));
			}
		}

		if ((indicadorUso != null && !indicadorUso.equals(""
				+ ConstantesSistema.NUMERO_NAO_INFORMADO))
				&& (!indicadorUso.equals("3"))) {

			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_USO, indicadorUso));

			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pelo request para o ExibirManterClienteAction
		sessao.setAttribute("filtroCobrancaCriterio", filtroCobrancaCriterio);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		return retorno;

	}
}
