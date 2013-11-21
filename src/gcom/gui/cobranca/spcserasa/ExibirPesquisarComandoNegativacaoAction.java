/**
 * 
 */
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
package gcom.gui.cobranca.spcserasa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/10/2007
 */


public class ExibirPesquisarComandoNegativacaoAction extends GcomAction {

	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("pesquisarComandoNegativacao");	
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			PesquisarComandoNegativacaoActionForm form = (PesquisarComandoNegativacaoActionForm) actionForm;
					
				
			// SETANDO OS CAMPOS QUE VÊM MARCADO INICIALMENTE NO FORM	
			if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").equals("")) {
				
				form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
				form.setIndicadorComandoSimulado(ConstantesSistema.TODOS.toString());
				
				if (httpServletRequest.getParameter("menu").equals("sim")) {
					form.setPopup(ConstantesSistema.NAO.toString());
					sessao.setAttribute("popup", ConstantesSistema.NAO.toString());
				} else if (httpServletRequest.getParameter("menu").equals("ok")) {
					form.setPopup(ConstantesSistema.SIM.toString());
					sessao.setAttribute("popup", ConstantesSistema.SIM.toString());
				}
			}				
			
			if (httpServletRequest.getParameter("APAGAR")!= null){
				
				sessao.removeAttribute("collectionComandoNegativacao");
				sessao.removeAttribute("totalRegistrosPrimeiraPaginacao");
				sessao.removeAttribute("numeroPaginasPesquisaPrimeiraPaginacao");
			}
			
			
			// FILTRAR USUARIO RESPONSAVEL
			
			if (form.getUsuarioResponsavelId()!= null && !form.getUsuarioResponsavelId().equals("")){
				
				FiltroUsuario filtroUsuarioResponsavel = new FiltroUsuario();
				filtroUsuarioResponsavel.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getUsuarioResponsavelId()));
				Collection colecaoUsuarioResponsavel = fachada.pesquisar(filtroUsuarioResponsavel, Usuario.class.getName());

				if (colecaoUsuarioResponsavel != null && !colecaoUsuarioResponsavel.isEmpty()) {
					
					Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuarioResponsavel);
					form.setUsuarioResponsavelNome(usuario.getNomeUsuario());
					sessao.setAttribute("usuarioResponsavelEncontrada", "true");
					
				} else {
					
					sessao.removeAttribute("usuarioResponsavelEncontrada");
					form.setUsuarioResponsavelNome("Usuário inexistente");
					form.setUsuarioResponsavelId("");
				}
			}
			
			//Recupera os dados do popup de usuário responsável
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
				//Recupera os dados do popup de usuário		
				if (httpServletRequest.getParameter("tipoConsulta").equals(
							"usuario")) {

					form.setUsuarioResponsavelId(httpServletRequest.getParameter("idCampoEnviarDados"));
					form.setUsuarioResponsavelNome(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));	
					sessao.setAttribute("usuarioResponsavelEncontrada", "true");

				}				
			}

	
			return retorno;
	}

}
