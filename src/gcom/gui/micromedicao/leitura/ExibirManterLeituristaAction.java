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
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com os leituristas retornados do
 * FiltrarManterLeituristaAction ou ir para o
 * ExibirManterLeituristaAction
 * 
 * @author Thiago Tenório
 * @since 22/07/2007
 */
public class ExibirManterLeituristaAction extends GcomAction {

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
		ActionForward retorno = actionMapping
				.findForward("exibirManterLeiturista");

		// Obtém a instância da fachada
		// Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera o filtro passado pelo FiltrarLeituristaAction para
		// ser efetuada pesquisa
		FiltroLeiturista filtroLeiturista = (FiltroLeiturista) sessao
				.getAttribute("filtroLeiturista");
		
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.EMPRESA);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
		
		// Bloquear a opcao de remover quando a empresa do usuario nao seja a empresa do sistema
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usuário logado.");
		}
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			sessao.setAttribute("bloquearRemover", true); 
		}

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Collection colecaoLeiturista = new ArrayList();
		if(filtroLeiturista != null && !filtroLeiturista.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroLeiturista, Leiturista.class.getName());
			colecaoLeiturista = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		// Verifica se a coleção retornada pela pesquisa é nula, em caso
		// afirmativo comunica ao usuário que não existe nenhum leiturista
		// cadastrado para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condições seta o retorno para o
		// ExibirAtualizarLeituristaAction, se não atender manda a
		// coleção pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoLeiturista != null
				&& !colecaoLeiturista.isEmpty()) {

			// Verifica se a coleção contém apenas um objeto, se está retornando
			// da paginação (devido ao esquema de paginação de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usuário selecione o link para ir para a
			// segunda página ele não vá para tela de atualizar.
			if (colecaoLeiturista.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usuário marcou o checkbox de atualizar no jsp
				// leiturista_filtrar. Caso todas as condições sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarLeituristaAction e em caso negativo
				// manda a coleção pelo request.
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarLeiturista");
					Leiturista leiturista = (Leiturista) colecaoLeiturista
							.iterator().next();
					sessao.setAttribute("leiturista",
							leiturista);
				} else {
					httpServletRequest.setAttribute(
							"colecaoLeiturista",
							colecaoLeiturista);
					Leiturista leiturista = (Leiturista) colecaoLeiturista
							.iterator().next();
					sessao.setAttribute("leiturista",
							leiturista);
				}
			} else {
				httpServletRequest.setAttribute("colecaoLeiturista",
						colecaoLeiturista);
			}
		} else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
