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

import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Tiago Moreno
 * @create 16/02/2006
 * 
 */
public class PesquisarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarCriterioCobrancaResultado");

		// Obtém a instância da fachada
		//Fachada fachada = Fachada.getInstancia();

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarCriterioCobrancaActionForm pesquisarCriterioCobrancaActionForm = (PesquisarCriterioCobrancaActionForm) actionForm;

		String descricaoCriterio = pesquisarCriterioCobrancaActionForm
				.getDescricaoCriterio();
		String dataInicio = pesquisarCriterioCobrancaActionForm.getDataInicio();
		String dataFim = pesquisarCriterioCobrancaActionForm.getDataFim();
		String numeroAnos = pesquisarCriterioCobrancaActionForm.getNumeroAnos();
		String opcaoContaRevisao = pesquisarCriterioCobrancaActionForm
				.getOpcaoContaRevisao();
		String opcaoImovelDebito = pesquisarCriterioCobrancaActionForm
				.getOpcaoImovelDebito();
		String opcaoImovelSitCobranca = pesquisarCriterioCobrancaActionForm
				.getOpcaoImovelSitCobranca();
		String opcaoImovelSitEspecial = pesquisarCriterioCobrancaActionForm
				.getOpcaoImovelSitEspecial();
		String opcaoInqDebitoConta = pesquisarCriterioCobrancaActionForm
				.getOpcaoInqDebitoConta();
		String opcaoInqDebitoContaAntiga = pesquisarCriterioCobrancaActionForm
				.getOpcaoInqDebitoContaAntiga();

		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();

		if ((descricaoCriterio == null || descricaoCriterio
				.equalsIgnoreCase(""))
				&& (dataInicio == null || dataInicio.equalsIgnoreCase(""))
				&& (numeroAnos == null || numeroAnos.equalsIgnoreCase(""))
				&& (opcaoContaRevisao == null || opcaoContaRevisao
						.equalsIgnoreCase(""))
				&& (opcaoImovelSitCobranca == null || opcaoImovelSitCobranca
						.equalsIgnoreCase(""))
				&& (opcaoInqDebitoContaAntiga == null || opcaoInqDebitoContaAntiga
						.equalsIgnoreCase(""))
				&& (opcaoInqDebitoConta == null || opcaoInqDebitoConta
						.equalsIgnoreCase(""))
				&& (opcaoImovelDebito == null || opcaoImovelDebito
						.equalsIgnoreCase(""))
				&& (opcaoImovelSitEspecial == null || opcaoImovelSitEspecial
						.equalsIgnoreCase(""))) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		if ((dataInicio.trim().length() == 10)
				&& (dataFim.trim().length() == 10) && dataFim != null && !dataFim.equalsIgnoreCase("")) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(dataInicio
					.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(dataInicio
					.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(dataInicio.substring(
					6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(dataFim
					.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH,
					new Integer(dataFim.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR,
					new Integer(dataFim.substring(6, 10)).intValue());
			// joga exessão
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
		}

		if (descricaoCriterio != null
				&& !descricaoCriterio.equalsIgnoreCase("")) {
			filtroCobrancaCriterio.adicionarParametro(new ComparacaoTexto(
					FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO,
					descricaoCriterio));
		}

		if (dataInicio != null && !dataInicio.equalsIgnoreCase("")) {
			if (dataFim == null || dataFim.equalsIgnoreCase("")) {
				filtroCobrancaCriterio.adicionarParametro(new Intervalo(FiltroCobrancaCriterio.DATA_INICIO_VIGENCIA, Util
						.converteStringParaDate(dataInicio), Util.converteStringParaDate(ConstantesSistema.DATA_LIMITE)));
			}else{
				filtroCobrancaCriterio.adicionarParametro(new Intervalo(
						FiltroCobrancaCriterio.DATA_INICIO_VIGENCIA, Util
								.converteStringParaDate(dataInicio), Util
								.converteStringParaDate(dataFim)));
			}
		}
		if (numeroAnos != null && !numeroAnos.equalsIgnoreCase("")) {

			Integer numeroAnosFormatado = new Integer(numeroAnos);
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.NUMERO_ANOS_CONTA_ANTIGA,
					numeroAnosFormatado));
		}
		if (opcaoContaRevisao != null
				&& !opcaoContaRevisao.equalsIgnoreCase("")
				&& !opcaoContaRevisao.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_CONTA_REVISAO,
					opcaoContaRevisao));
		}
		if (opcaoImovelSitCobranca != null
				&& !opcaoImovelSitCobranca.equalsIgnoreCase("")
				&& !opcaoImovelSitCobranca.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_IMOVEL_SITUACAO_COBRANCA,
					opcaoImovelSitCobranca));
		}
		if (opcaoInqDebitoContaAntiga != null
				&& !opcaoInqDebitoContaAntiga.equalsIgnoreCase("")
				&& !opcaoInqDebitoContaAntiga.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_ANTIGA,
					opcaoInqDebitoContaAntiga));
		}
		if (opcaoInqDebitoConta != null
				&& !opcaoInqDebitoConta.equalsIgnoreCase("")
				&& !opcaoInqDebitoConta.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaCriterio.INDICADOR_INQUILINO_DEBITO_CONTA_MES,
							opcaoInqDebitoConta));
		}
		if (opcaoImovelDebito != null
				&& !opcaoImovelDebito.equalsIgnoreCase("")
				&& !opcaoImovelDebito.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_MES,
					opcaoImovelDebito));
		}
		if (opcaoImovelSitEspecial != null
				&& !opcaoImovelSitEspecial.equalsIgnoreCase("")
				&& !opcaoImovelSitEspecial.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_IMOVEL_PARALISACAO,
					opcaoImovelSitEspecial));
		}

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroCobrancaCriterio, CobrancaCriterio.class.getName());

		Collection colecaoCriterioCobranca = (Collection) resultado
				.get("colecaoRetorno");

		retorno = (ActionForward) resultado.get("destinoActionForward");

		if (colecaoCriterioCobranca == null
				|| colecaoCriterioCobranca.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		} else {
			sessao.setAttribute("colecaoCriterioCobranca",
					colecaoCriterioCobranca);
		}

		return retorno;
	}
}