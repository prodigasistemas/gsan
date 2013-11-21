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
package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por exibir a página de definir o acesso inicial que o
 * usuario vai possuir
 * 
 * @author Sávio Luiz
 * @date 28/06/2006
 */
public class ExibirControlarRestrincoesAcessoUsuarioAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;

		// cria a variável de retorno e seta o mapeamento para a página de
		// controlar
		ActionForward retorno = actionMapping.findForward("controlarRestrincoesAcessoUsuario");

		// verifica se foi clicado no botão para salvar as operações alteradas
		String botaoSalvar = httpServletRequest.getParameter("botaoSalvar");

		Usuario usuarioAtualizar = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioParaAtualizar");

		Integer[] idsGrupos = (Integer[]) this.getSessao(httpServletRequest).getAttribute("grupo");

		controlarAcessoUsuarioActionForm.setNomeUsuario(usuarioAtualizar.getNomeUsuario());
		controlarAcessoUsuarioActionForm.setLoginUsuario(usuarioAtualizar.getLogin());

		// cria o filtro de funcionalidade
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();

		// seta o filtro para retornar todas as funcionalidades que são ponto de
		// entrada
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA, 
				new Integer(1)));
		
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS_BY_ID_INDEPENDENCIA);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS);

		// cria a coleção de funcionalidades
		Collection<Funcionalidade> funcionalidades = null;

		Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap = 
			(Map<Integer, Map<Integer, Collection<Operacao>>>) this.getSessao(httpServletRequest).getAttribute("funcionalidadesMap");
		
		if (funcionalidadesMap == null) {
			funcionalidadesMap = new HashMap<Integer, Map<Integer, Collection<Operacao>>>();
		}

		// pega o código da funcionalidade selecionada na página
		String codigoFuncionalidade = httpServletRequest.getParameter("codigoFuncionalidade");

		// cadastrando as operacoes selecionadas
		// String codigoFuncionalidade = form.getFuncionalidade();
		if (codigoFuncionalidade != null && !"".equals(codigoFuncionalidade) && botaoSalvar != null) {

			// pegando as operacoes escolhidas
			String[] operacoesAInserir = controlarAcessoUsuarioActionForm.getOperacoes();
			
			funcionalidadesMap = 
				this.getFachada().recuperaFuncionalidadeOperacaoRestrincao(
					new Integer(codigoFuncionalidade),
					operacoesAInserir, 
					funcionalidadesMap);

			// manda o map de operações para a sessão
			Map<Integer, Collection<Operacao>> operacoesMap = 
				funcionalidadesMap.get(new Integer(codigoFuncionalidade));
			httpServletRequest.setAttribute("operacoesMap", operacoesMap);

			Collection<Operacao> operacoesMarcadas = operacoesMap.get(1);
			Collection<Operacao> operacoesDesmarcadas = operacoesMap.get(2);
			Collection<Operacao> operacoesDesabilitadas = operacoesMap.get(3);
			httpServletRequest.setAttribute("operacoesMarcadas",operacoesMarcadas);
			httpServletRequest.setAttribute("operacoesDesmarcadas",operacoesDesmarcadas);
			httpServletRequest.setAttribute("operacoesDesabilitadas",operacoesDesabilitadas);

		} else {
			if (codigoFuncionalidade != null && !codigoFuncionalidade.equals("")) {

				funcionalidadesMap = 
					this.getFachada().organizarOperacoesComValor(
						new Integer(codigoFuncionalidade),
						funcionalidadesMap, 
						idsGrupos, 
						usuarioAtualizar);

				// manda o map de operações para a sessão
				Map<Integer, Collection<Operacao>> operacoesMap = 
					funcionalidadesMap.get(new Integer(codigoFuncionalidade));
				httpServletRequest.setAttribute("operacoesMap", operacoesMap);

				Collection<Operacao> operacoesMarcadas = operacoesMap.get(1);
				Collection<Operacao> operacoesDesmarcadas = operacoesMap.get(2);
				Collection<Operacao> operacoesDesabilitadas = operacoesMap.get(3);

				httpServletRequest.setAttribute("operacoesMarcadas",operacoesMarcadas);
				httpServletRequest.setAttribute("operacoesDesmarcadas",operacoesDesmarcadas);
				httpServletRequest.setAttribute("operacoesDesabilitadas",operacoesDesabilitadas);

			} else {

				String linkRetorno = 
					"controlarAcessosUsuarioWizardAction.do?action=exibirControlarRestrincoesAcessoUsuarioAction";

				// chama o metódo para criar a árvore das funcionalidades
				String arvoreFuncionalidades = 
					this.getFachada().construirMenuAcesso(
						usuarioAtualizar, 
						linkRetorno,
						null);

				// manda o javascript da árvore para a página no request
				this.getSessao(httpServletRequest).setAttribute("arvoreFuncionalidades",arvoreFuncionalidades);
			}

		}

		/**
		 * Com a funcionalidade passada pesquisa todas as funcionalidade Ocultas
		 * com as funcionalidade Ocultas retira as operacoes
		 */

		// pesquisa a coleção de funcionalidades cadastradas no sistema
		funcionalidades = 
			this.getFachada().pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());

		// se nenhuma funcionalidade cadastrada no sistema
		if (funcionalidades == null || funcionalidades.isEmpty()) {
			// manda o erro no request atual
			throw new ActionServletException("atencao.naocadastrado.funcionalidade");
		}

		// se for informado o código da funcionalidade no request
		if (codigoFuncionalidade != null && !codigoFuncionalidade.equalsIgnoreCase("")) {

			// cria a variável que vai conter a descrição da funcionalidade
			String descricaoFuncionalidade = null;

			// coloca a coleção de funcionalidades no iterator
			Iterator<Funcionalidade> iteratorFuncionalidades = funcionalidades.iterator();

			// cria a coleção de funcionalidades ocultas(dependentes)
			// Collection funcionalidadesOcultas = new ArrayList();

			// laço para encontrar a funcionalidade informada
			while (iteratorFuncionalidades.hasNext()) {
				// recupera a funcionalidade do iterator
				Funcionalidade funcionalidade = iteratorFuncionalidades.next();

				// se o código da funcionalidade for igual ao código da
				// funcionalidade informada
				if (codigoFuncionalidade.equalsIgnoreCase(funcionalidade.getId().toString())) {

					// recupera a descrição da funcionalidade
					descricaoFuncionalidade = funcionalidade.getDescricao();
					break;
				}
			}

			// manda a descrição da funcionalidade no request
			httpServletRequest.setAttribute("descricaoFuncionalidade",
					descricaoFuncionalidade);

			// manda a código da funcionalidade no request
			httpServletRequest.setAttribute("idFuncionalidade",
					codigoFuncionalidade);

		}

		this.getSessao(httpServletRequest).setAttribute("funcionalidadesMap", funcionalidadesMap);

		// retorna o mapeamento contido na variável retorno
		return retorno;
	}
}