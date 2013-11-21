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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action responsável por alterar a senha do usuário 
 *
 * @author Pedro Alexandre
 * @date 12/07/2006
 */
public class EfetuarAlteracaoSenhaAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Pedro Alexandre
	 * @date 04/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								ActionForm actionForm, 
								HttpServletRequest httpServletRequest,
								HttpServletResponse httpServletResponse) {

		// Prepara o retorno da ação para a tela principal
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		//Recupera uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usuário que está solicitando o lembrete da senha
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		// Recupera o ActionForm
		EfetuarAlteracaoSenhaActionForm efetuarAlteracaoSenhaActionForm = (EfetuarAlteracaoSenhaActionForm) actionForm;

		//Recupera todos os dados necessários para verificar se o usuário 
		//pode ser lembrada sua senha
		String dataNascimentoString = efetuarAlteracaoSenhaActionForm.getDataNascimento();
		String cpf = efetuarAlteracaoSenhaActionForm.getCpf();
		String lembreteSenha = efetuarAlteracaoSenhaActionForm.getLembreteSenha();
		String novaSenha = efetuarAlteracaoSenhaActionForm.getNovaSenha();
		String confirmacaoNovaSenha = efetuarAlteracaoSenhaActionForm.getConfirmacaoNovaSenha();
		
		fachada.efetuarAlteracaoSenha(usuarioLogado, dataNascimentoString, cpf, lembreteSenha, novaSenha, confirmacaoNovaSenha);
			
		//Caso o mapeamento seja para a tela de sucesso do popup
		//monta a tela de sucesso indicando que o email foi enviado 
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
		  montarPaginaSucesso(httpServletRequest,"Senha alterada com sucesso.", "", "");
	    }
		 
		return retorno;
	}
}
