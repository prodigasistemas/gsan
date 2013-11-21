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
package gcom.gui;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Responsável por executar a ação desejada
 * 
 * @author thiago toscano
 * @date 21/12/2005
 */
public abstract class ControladorGcomAction extends GcomAction {

	public static final String PARAMETRO_ACAO = "acao";

	public static final String PARAMETRO_ACAO_EXIBIR = "exibir";

	public static final String PARAMETRO_ACAO_PROCESSAR = "processar";
	
	public static final String FORWARD_EXIBIR = "exibir";
	
	public static final String FORWARD_EXIBIR_REMOVER = "exibirRemover";
	
	public static final String FORWARD_REMOVER = "remover";

	public static final String FORWARD_PROCESSAR = "processar";

	public static final String FORWARD_CONCLUIR = "concluir";

	public static final String FORWARD_TELA_SUCESSO = "telaSucesso";

	public static final String FORWARD_POPUP = "popup";
	
	public static final String PARAMETRO_COLL_OBJETO = "collObjeto";
	
	public static final String PARAMETRO_FORMULARIO = "formulario";
	
	public static final String PARAMETRO_SEPARADO_CHAVE_PRIMARIA = ";";

	/**
	 * Reemplementação do Método para validar a passagem do parametro acao
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return selecionarExibir(actionMapping, actionForm, request, response);
	}

	/**
	 * Redireciona pra tela de exibicao
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, ServletRequest request, ServletResponse response) throws Exception {
		return selecionarExibir(actionMapping, actionForm, request, response);
	}

	/**
	 * Redireciona pra tela de exibicao
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	private ActionForward selecionarExibir(ActionMapping actionMapping, ActionForm actionForm, ServletRequest request, ServletResponse response) throws Exception {
		String acao = request.getParameter(PARAMETRO_ACAO);
		ActionForward retorno = null;
		if (acao == null || "".equals(acao)) {
			retorno = this.exibir(actionMapping, actionForm, (HttpServletRequest) request, (HttpServletResponse) response);
		} else {
			retorno = super.execute(actionMapping, actionForm, (HttpServletRequest) request, (HttpServletResponse) response);
		}
		return retorno;
	}

	/**
	 * Encaminha pra tela de sucesso
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	public ActionForward telaSucesso(ActionMapping actionMapping, HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade) throws Exception {

		request.setAttribute("caminhoFuncionalidade",caminhoFuncionalidade);
		request.setAttribute("labelPaginaSucesso",labelPaginaSucesso);
		request.setAttribute("mensagemPaginaSucesso",mensagemPaginaSucesso);

		return actionMapping.findForward(ControladorGcomAction.FORWARD_TELA_SUCESSO);
	}

	
	public ControladorGcomActionForm getControladorGcomActionForm(HttpServletRequest request, String nomeForm){
		return (ControladorGcomActionForm) getSessao(request).getAttribute(nomeForm);
	}

	/**
	 * Método que responde pela ação de exibição
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract ActionForward exibir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * Método que responde pela ação de processamento 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract ActionForward processar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception;

	
	
	
	
//	/**
//	 * Método responsável por preencher o formulario de apresentação a partir do objeto selecionado  
//	 * 
//	 * @param obj
//	 * @param actionForm
//	 */
//	public abstract void montarFormulario(Object obj, ControladorGcomActionForm actionForm) ;
//
//	/**
//	 * Método responsável por montar o filtro que ser utilizado pela consulta 
//	 * 
//	 * @param actionForm
//	 * @return
//	 */
//	public abstract Filtro gerarFiltro(ControladorGcomActionForm actionForm);
//	
//	/**
//	 * Método que gera o objeto para a manipulacao no sistema 
//	 *  
//	 * @param actionForm
//	 * @return
//	 */
//	public abstract Object gerarObject(ControladorGcomActionForm actionForm);
//	
//	/**
//	 * Método que carrega a colecao para a apresentação dos dados.
//	 * 
//	 * @param actionForm
//	 */
//	public abstract void carregarColecao(ControladorGcomActionForm actionForm);
}
