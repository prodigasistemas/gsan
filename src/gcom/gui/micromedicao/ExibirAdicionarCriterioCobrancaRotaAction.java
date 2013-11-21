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
package gcom.gui.micromedicao;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.RotaAcaoCriterio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action de exibir adicionar critério de cobrança da rota
 * 
 * @author Vivianne Sousa
 * @created 25/04/2006
 */

public class ExibirAdicionarCriterioCobrancaRotaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		// Inicializando Variaveis
		ActionForward retorno = actionMapping
				.findForward("adicionarCriterioCobrancaRota");
		
		Fachada fachada = Fachada.getInstancia();
		
		AdicionarCriterioCobrancaRotaActionForm adicionarCriterioCobrancaRotaActionForm = (AdicionarCriterioCobrancaRotaActionForm) actionForm;

		//Pesquisando grupo de cobrança
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaAcao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<CobrancaAcao> collectionCobrancaAcao = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		
		//Coleção de Ação Critérios já escolhido na seção
		Collection<RotaAcaoCriterio> collectionRotaAcaoCriterio = null;
        if (sessao.getAttribute("collectionRotaAcaoCriterio") != null) {
        	collectionRotaAcaoCriterio = (Collection) sessao
                    .getAttribute("collectionRotaAcaoCriterio");
        } else {
        	collectionRotaAcaoCriterio = new ArrayList<RotaAcaoCriterio>();
        }
        
        List<CobrancaAcao> acoesParaRemover = new ArrayList<CobrancaAcao>();
        
        //Remove dos criterios da pesquisa os ja escolhidos que estão na colecao da sessao
        for (CobrancaAcao acao : collectionCobrancaAcao) {
        	for (RotaAcaoCriterio criterio : collectionRotaAcaoCriterio) {
        		if (criterio.getCobrancaAcao().getId().equals(acao.getId())) {
        			acoesParaRemover.add(acao);
        			break;
        		}
        	}
        }
		
        collectionCobrancaAcao.removeAll(acoesParaRemover);
		
		sessao.setAttribute("collectionCobrancaAcao", collectionCobrancaAcao);
		// Fim de pesquisando grupo de cobrança/

		if (sessao.getAttribute("idCobrancaAcao") != null && !sessao.getAttribute("idCobrancaAcao").equals("")){
			adicionarCriterioCobrancaRotaActionForm.setCobrancaAcao((String)sessao.getAttribute("idCobrancaAcao"));
		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {
			// limpar tela
			adicionarCriterioCobrancaRotaActionForm.setCobrancaAcao(""
					+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			adicionarCriterioCobrancaRotaActionForm.setIdCobrancaCriterio("");
			adicionarCriterioCobrancaRotaActionForm
					.setDescricaoCobrancaCriterio("");

		}

		// -------Parte que trata do código quando o usuário tecla enter
		if (adicionarCriterioCobrancaRotaActionForm != null){
			String idDigitadoEnterCobrancaCriterio = adicionarCriterioCobrancaRotaActionForm.getIdCobrancaCriterio();
			verificaExistenciaCodCobrancaCriterio(idDigitadoEnterCobrancaCriterio,
					adicionarCriterioCobrancaRotaActionForm, httpServletRequest,
					fachada);
		}
		// -------Fim de parte que trata do código quando o usuário tecla enter

		sessao.removeAttribute("caminhoRetornoTelaPesquisa");

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina criterio_cobranca_rota_adicionar_popup.jsp
			adicionarCriterioCobrancaRotaActionForm
					.setIdCobrancaCriterio(httpServletRequest
							.getParameter("idCampoEnviarDados"));
			adicionarCriterioCobrancaRotaActionForm
					.setDescricaoCobrancaCriterio(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));
			//sessao.setAttribute("AdicionarCriterioCobrancaRotaActionForm",adicionarCriterioCobrancaRotaActionForm);
		}

		return retorno;

	}

	private void verificaExistenciaCodCobrancaCriterio(
			String idDigitadoEnterCobrancaCriterio,
			AdicionarCriterioCobrancaRotaActionForm adicionarCriterioCobrancaRotaActionForm,
			HttpServletRequest httpServletRequest, Fachada fachada) {

		// Verifica se o código da CobrancaCriterio foi digitado
		if (idDigitadoEnterCobrancaCriterio != null
				&& !idDigitadoEnterCobrancaCriterio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterCobrancaCriterio) > 0) {

			// Recupera a CobrancaCriterio informada pelo usuário
			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();

			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.ID, new Integer(
							idDigitadoEnterCobrancaCriterio)));

			Collection<CobrancaCriterio> cobrancaCriterios = fachada.pesquisar(
					filtroCobrancaCriterio, CobrancaCriterio.class.getName());

			if (cobrancaCriterios != null && !cobrancaCriterios.isEmpty()) {
				// a CobrancaCriterio foi encontrada
				CobrancaCriterio cobrancaCriterioEncontrada = (CobrancaCriterio) Util
						.retonarObjetoDeColecao(cobrancaCriterios);
				adicionarCriterioCobrancaRotaActionForm
						.setIdCobrancaCriterio(""
								+ (cobrancaCriterioEncontrada.getId()));
				adicionarCriterioCobrancaRotaActionForm
						.setDescricaoCobrancaCriterio(cobrancaCriterioEncontrada
								.getDescricaoCobrancaCriterio());
				httpServletRequest.setAttribute(
						"idCobrancaCriterioNaoEncontrado", "true");

			} else {
				// a CobrancaCriterio não foi encontrada
				adicionarCriterioCobrancaRotaActionForm
						.setIdCobrancaCriterio("");
				httpServletRequest.setAttribute(
						"idCobrancaCriterioNaoEncontrado", "exception");
				adicionarCriterioCobrancaRotaActionForm
						.setDescricaoCobrancaCriterio("CRITÉRIO DE COBRANÇA INEXISTENTE");

			}
		}

	}

}