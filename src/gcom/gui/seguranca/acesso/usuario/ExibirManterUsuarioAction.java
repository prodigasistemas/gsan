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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterUsuarioAction extends GcomAction {
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

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("manterUsuario");

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection collectionUsuariosGrupos = null;
		
		Short indicadorUsuarioBatch = (Short) sessao.getAttribute("indicadorUsuarioBatch");
		Short indicadorUsuarioInternet = (Short) sessao.getAttribute("indicadorUsuarioInternet");

		// Parte da verificação do filtro
		FiltroUsuarioGrupo filtroUsuarioGrupo = null;

		// Verifica se o filtro foi informado pela página de filtragem de
		// cobranca critério
		if (sessao.getAttribute("filtroUsuarioGrupo") != null) {
			filtroUsuarioGrupo = (FiltroUsuarioGrupo) sessao
					.getAttribute("filtroUsuarioGrupo");
		}

		//Caso seja selecionado o indicador de Usuario batch ou internet não utilizar o FiltroUsuarioGrupo para fazer a busca
		if(indicadorUsuarioBatch == 1 || indicadorUsuarioInternet == 1){
			
			String identificadorAtualizar = (String) sessao.getAttribute("indicadorAtualizar");
		
			// 1º Passo - Pegar o total de registros através de um count da
			// consulta que aparecerá na tela
			Integer totalRegistros = 0;
			
			// 2º Passo - Obter a coleção da consulta que aparecerá na tela
			// passando o numero de paginas
			// da pesquisa que está no request
			collectionUsuariosGrupos = new ArrayList<Usuario>();
			
			if(indicadorUsuarioBatch == 1){
				Usuario userBatch = Fachada.getInstancia().pesquisarUsuarioRotinaBatch();
				
				if(indicadorUsuarioInternet == 1){
					Usuario userInternet = Fachada.getInstancia().pesquisarUsuarioInternet();
					if(userBatch != null && userInternet != null && userBatch.getId().compareTo(userInternet.getId()) == 0){
						collectionUsuariosGrupos.add(userBatch);
					}
				}else{
					if(userBatch != null){
						collectionUsuariosGrupos.add(userBatch);
					}
				}
				
			}else{
				Usuario userInternet = Fachada.getInstancia().pesquisarUsuarioInternet();
				if(userInternet != null ){
					collectionUsuariosGrupos.add(userInternet);
				}
			}
			
			// [FS0003] Nenhum registro encontrado
			if (collectionUsuariosGrupos == null
					|| collectionUsuariosGrupos.isEmpty()) {
				// Nenhum criterio de cobrança cadastrada
				throw new ActionServletException(
				"atencao.pesquisa.nenhumresultado");
			}
			
			totalRegistros = collectionUsuariosGrupos.size();
			
			//3º Passo - Chamar a função de Paginação passando o total de
			// registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);
			
			if (totalRegistros == 1 && identificadorAtualizar != null) {
				// caso o resultado do filtro só retorne um registro
				// e o check box Atualizar estiver selecionado
				// o sistema não exibe a tela de manter, exibe a de atualizar
				retorno = actionMapping.findForward("atualizarUsuario");
				Usuario usuarioParaAtualizar = (Usuario) Util
				.retonarObjetoDeColecao(collectionUsuariosGrupos);
				httpServletRequest.setAttribute("idRegistroAtualizacao",
						new Integer(usuarioParaAtualizar.getId()).toString());
				httpServletRequest.setAttribute("atualizar", "1");
			} else {
				sessao.setAttribute("collectionUsuariosGrupos",
						collectionUsuariosGrupos);
			}
			
		}else{
			
			if ((retorno != null)
					&& (retorno.getName().equalsIgnoreCase("manterUsuario"))) {
				
				String identificadorAtualizar = (String) sessao
				.getAttribute("indicadorAtualizar");
				// 1º Passo - Pegar o total de registros através de um count da
				// consulta que aparecerá na tela
				Integer totalRegistros = Fachada.getInstancia()
				.totalRegistrosPesquisaUsuarioGrupo(filtroUsuarioGrupo);
				
				// 2º Passo - Chamar a função de Paginação passando o total de
				// registros
				retorno = this.controlarPaginacao(httpServletRequest, retorno,
						totalRegistros);
				
				// 3º Passo - Obter a coleção da consulta que aparecerá na tela
				// passando o numero de paginas
				// da pesquisa que está no request
				collectionUsuariosGrupos = Fachada.getInstancia()
				.pesquisarUsuariosDosGruposUsuarios(
						filtroUsuarioGrupo,
						((Integer) httpServletRequest
								.getAttribute("numeroPaginasPesquisa")));
				// [FS0003] Nenhum registro encontrado
				if (collectionUsuariosGrupos == null
						|| collectionUsuariosGrupos.isEmpty()) {
					// Nenhum criterio de cobrança cadastrada
					throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado");
				}
				
				if (totalRegistros == 1 && identificadorAtualizar != null) {
					// caso o resultado do filtro só retorne um registro
					// e o check box Atualizar estiver selecionado
					// o sistema não exibe a tela de manter, exibe a de atualizar
					retorno = actionMapping.findForward("atualizarUsuario");
					Usuario usuarioParaAtualizar = (Usuario) Util
					.retonarObjetoDeColecao(collectionUsuariosGrupos);
					httpServletRequest.setAttribute("idRegistroAtualizacao",
							new Integer(usuarioParaAtualizar.getId()).toString());
					httpServletRequest.setAttribute("atualizar", "1");
				} else {
					sessao.setAttribute("collectionUsuariosGrupos",
							collectionUsuariosGrupos);
				}
				
			}
		}

		sessao.removeAttribute("desabilita");
		return retorno;
	}
}