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

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com as resoluções de diretoria retornadas do
 * FiltrarResolucaoDiretoriaAction ou ir para o
 * ExibirAtualizarResolucaoDiretoriaAction
 * 
 * @author Rafael Corrêa
 * @since 31/03/2006
 */
public class ExibirManterEquipeAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterEquipe");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("equipeAtualizar");

		// Recupera os parâmetros da sessão para ser efetuada a pesquisa
		String idEquipe = (String) sessao.getAttribute("idEquipe");
		String nome = (String) sessao.getAttribute("nome");
		String placa = (String) sessao.getAttribute("placa");
		String cargaTrabalho = (String) sessao.getAttribute("cargaTrabalho");
		String idUnidade = (String) sessao.getAttribute("idUnidade");
		String idFuncionario = (String) sessao.getAttribute("idFuncionario");
		String idPerfilServico = (String) sessao
				.getAttribute("idPerfilServico");
		String indicadorUso = (String) sessao.getAttribute("indicadorUso");
		String indicadorProgramacaoAutomatica = (String) sessao.getAttribute("indicadorProgramacaoAutomatica");
		String tipoPesquisa = (String) sessao.getAttribute("tipoPesquisa");
		String codigoDdd = (String) sessao.getAttribute("codigoDdd");
		String numeroTelefone = (String) sessao.getAttribute("numeroTelefone");
		String numeroImei = (String) sessao.getAttribute("numeroImei");
		String equipamentosEspeciasId = (String) sessao.getAttribute("equipamentosEspeciasId");
		String cdUsuarioRespExcServico = (String) sessao.getAttribute("cdUsuarioRespExcServico");

		
		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Integer totalRegistros = fachada.pesquisarEquipesCount(idEquipe, nome,
				placa, cargaTrabalho, codigoDdd, numeroTelefone, numeroImei, idUnidade, idFuncionario,
				idPerfilServico, indicadorUso, tipoPesquisa,equipamentosEspeciasId, cdUsuarioRespExcServico, indicadorProgramacaoAutomatica);

		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		Collection colecaoEquipes = fachada.pesquisarEquipes(idEquipe, nome,
				placa, cargaTrabalho, codigoDdd, numeroTelefone, numeroImei, idUnidade, idFuncionario,
				idPerfilServico, indicadorUso, tipoPesquisa, (Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"),equipamentosEspeciasId, cdUsuarioRespExcServico, indicadorProgramacaoAutomatica);

		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhuma equipe
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarEquipeAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoEquipes != null && !colecaoEquipes.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			if (colecaoEquipes.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// equipe_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarEquipeAction e em caso negativo
				// manda a coleção pelo request.
				if (sessao.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarEquipe");
					Equipe equipe = (Equipe) colecaoEquipes.iterator().next();
					sessao.setAttribute("equipe", equipe);
				} else {
					httpServletRequest.setAttribute("colecaoEquipes",
							colecaoEquipes);
				}
			} else {
				httpServletRequest.setAttribute("colecaoEquipes",
						colecaoEquipes);
			}
		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
