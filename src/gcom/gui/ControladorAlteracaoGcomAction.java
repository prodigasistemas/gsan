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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Responsável por executar a ação de alteraçao
 * 
 * @author thiago toscano
 * @date 21/12/2005
 */
public abstract class ControladorAlteracaoGcomAction extends ControladorGcomAction {

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
	public final ActionForward exibir(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ControladorAlteracaoGcomActionForm form = (ControladorAlteracaoGcomActionForm) actionForm; 

		String[] chavesPrimarias = form.getChavePrimaria().split(ControladorGcomAction.PARAMETRO_SEPARADO_CHAVE_PRIMARIA);
		
		Object obj = this.consultarObjetoSistema(chavesPrimarias, request, form);
		
		this.montarFormulario(obj, form);
		
		ActionForward forward = this.exibirAuxiliar(actionMapping, actionForm, request, response);

		this.carregarColecao(form);

		form.setAcao(ControladorGcomAction.PARAMETRO_ACAO_PROCESSAR);

		if (forward != null) {
			return forward;
		}
		return actionMapping.findForward(ControladorGcomAction.FORWARD_EXIBIR);
	}

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
	public final ActionForward processar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception{

		ControladorAlteracaoGcomActionForm form = (ControladorAlteracaoGcomActionForm) actionForm; 

		Object obj = gerarObject(form,request);

		this.atualizarObjeto(obj, request, form);
		
		ActionForward forward = this.processarAuxiliar(actionMapping, actionForm, request, response);
		
		if (forward != null) {
			return forward;
		}
		return actionMapping.findForward(ControladorGcomAction.FORWARD_PROCESSAR);
	}

	/**
	 * Método que auxiliar ao método exibir 
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exibirAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * Método que auxiliar ao método processar 
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward processarAuxiliar(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 * Método que consulta o Objeto no sistema com o array de chaves necessaria
	 * 
	 * @param chavesPrimarias
	 * @return
	 * @throws Exception
	 */	
	public abstract Object consultarObjetoSistema(String[] chavesPrimarias, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception;

	/**
	 * Método que atualiza o objeto no sistema
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public abstract void atualizarObjeto(Object obj, HttpServletRequest request, ControladorAlteracaoGcomActionForm actionForm) throws Exception;

	/**
	 * Método responsável por preencher o formulario de apresentação a partir do objeto selecionado  
	 * 
	 * @param obj
	 * @param actionForm
	 */
	public abstract void montarFormulario(Object obj, ControladorAlteracaoGcomActionForm actionForm) ;
	
	/**
	 * Método que gera o objeto para a manipulacao no sistema 
	 *  
	 * @param actionForm
	 * @return
	 */
	public abstract Object gerarObject(ControladorAlteracaoGcomActionForm actionForm, HttpServletRequest request);
	
	/**
	 * Método que carrega a colecao para a apresentação dos dados.
	 * 
	 * @param actionForm
	 */
	public abstract void carregarColecao(ControladorAlteracaoGcomActionForm actionForm);
}