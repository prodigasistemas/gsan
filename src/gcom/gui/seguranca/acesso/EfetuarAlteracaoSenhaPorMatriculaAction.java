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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Criptografia;
import gcom.util.ErroCriptografiaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Action responsável pela pré-exibição da tela de alterar a senha do usuário
 *
 * @author Flávio Cordeiro
 * @date 24/02/2006
 */
public class EfetuarAlteracaoSenhaPorMatriculaAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * [UC0287] - Efetuar Login
	 * 
	 * @author Flavio Cordeiro
	 * @date 24/02/2007
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

		// Prepara o retorno da ação para a tela de lembrar senha
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		//Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
		
		EfetuarAlteracaoSenhaPorMatriculaActionForm form = (EfetuarAlteracaoSenhaPorMatriculaActionForm) actionForm;
		
		//Recupera uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera o usuário que está solicitando o lembrete da senha
		Usuario usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
		
		if(form.getLogin() != null){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLogin()));
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(!colecaoUsuario.isEmpty()){
				Usuario usuario = (Usuario)colecaoUsuario.iterator().next();
				try {
					//if(usuario.getDataNascimento() == null || form.getDataNascimento().trim().equals(Util.formatarData(usuario.getDataNascimento()))){
						usuario.setSenha(Criptografia.encriptarSenha("gcom"));
					// }else{
					// throw new
					// ActionServletException("atencao.data_nascimento.incorreta.login",
					// null, form.getLogin());
					//					}
				} catch (ErroCriptografiaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				//------------ REGISTRAR TRANSAÇÃO ----------------
//		        RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//					Operacao.OPERACAO_USUARIO_ALTERAR_SENHA,
//					new UsuarioAcaoUsuarioHelper(usuarioLogado,
//				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//			        
//			    Operacao operacao = new Operacao();
//			    operacao.setId(Operacao.OPERACAO_USUARIO_ALTERAR_SENHA);
//			
//			    OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//			    operacaoEfetuada.setOperacao(operacao);
//			    registradorOperacao.registrarOperacao(usuarioLogado);
//		    	//------------ REGISTRAR TRANSAÇÃO ----------------
				
				// ------------ REGISTRAR TRANSAÇÃO ----------------
				
				usuario.setUltimaAlteracao(new Date());
				
				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				    Operacao.OPERACAO_USUARIO_ALTERAR_SENHA_LOGIN,
				    usuario.getId(), usuario.getId(),
				    new UsuarioAcaoUsuarioHelper(usuarioLogado,
				    UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				registradorOperacao.registrarOperacao(usuario);

				// ------------ REGISTRAR TRANSAÇÃO ----------------

				fachada.atualizar(usuario);
			}else{
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + form.getLogin() + "");
			}
		}
		
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Senha padrão para o login: "
				+ form.getLogin() + " gerada com sucesso.",
				"Alterar outra senha", "exibirEfetuarAlteracaoSenhaPorMatriculaAction.do?limparForm=ok");
		
		
		//Retornao mapeamento contido na variável retorno
		return retorno;
	}
}
