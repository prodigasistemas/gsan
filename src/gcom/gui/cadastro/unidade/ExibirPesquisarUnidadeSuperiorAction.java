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
package gcom.gui.cadastro.unidade;


import gcom.atendimentopublico.registroatendimento.FiltroMeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Action que define o pré-processamento da página de pesquisa unidade organizacional
 * 
 * @author Rafael Pinto
 * @created 25/07/2006
 */
public class ExibirPesquisarUnidadeSuperiorAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirPesquisarUnidadeSuperior");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm = 
				(PesquisarUnidadeSuperiorActionForm) actionForm;

		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null && 
			httpServletRequest.getParameter("caminhoRetornoTelaPesquisa").equals("exibirPesquisarUnidadeOrganizacionalAction") ) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisa",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisa"));
			
		}else {
		
			// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
	
	
			if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) {
	
				// Faz a consulta de Localidade
				pesquisarLocalidade(httpServletRequest, retorno,pesquisarUnidadeSuperiorActionForm);
	
			}
			
			// Parte que passa as coleções necessárias no jsp
			Collection colecaoUnidadeTipo = (Collection) sessao.getAttribute("colecaoUnidadeTipo");
			
			if(colecaoUnidadeTipo == null){
				
				FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();
				
				filtroUnidadeTipo.adicionarParametro(new ParametroSimples(
						FiltroUnidadeTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroUnidadeTipo.setCampoOrderBy(FiltroUnidadeTipo.DESCRICAO);
	
				colecaoUnidadeTipo = 
					fachada.pesquisar(filtroUnidadeTipo, UnidadeTipo.class.getName());
	
				if (colecaoUnidadeTipo != null && !colecaoUnidadeTipo.isEmpty()) {
					sessao.setAttribute("colecaoUnidadeTipo", colecaoUnidadeTipo);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Unidade Tipo");
				}
			}
			
			Collection colecaoGerenciaRegional = (Collection) sessao.getAttribute("colecaoGerenciaRegional");
			
			if(colecaoGerenciaRegional == null){
				
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
				
				colecaoGerenciaRegional = 
					fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
	
				if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
					sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Gerêncial Regional");
				}
			}
	
			Collection colecaoMeioSolicitacao = (Collection) sessao.getAttribute("colecaoMeioSolicitacao");
			
			if(colecaoMeioSolicitacao == null){
				
				FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
				
				filtroMeioSolicitacao.adicionarParametro(new ParametroSimples(
						FiltroMeioSolicitacao.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
				
				colecaoMeioSolicitacao = 
					fachada.pesquisar(filtroMeioSolicitacao, MeioSolicitacao.class.getName());
	
				if (colecaoMeioSolicitacao != null && !colecaoMeioSolicitacao.isEmpty()) {
					sessao.setAttribute("colecaoMeioSolicitacao", colecaoMeioSolicitacao);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Meio Solicitação");
				}
			}
	
			Collection colecaoEmpresa = 
				(Collection) sessao.getAttribute("colecaoEmpresa");
			
			if(colecaoEmpresa == null){
	
				// Filtro para obter empresa ativo de id informado
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
	
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
	
				filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
				
				colecaoEmpresa = 
					fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
	
				if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
					sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
				} else {
					throw new ActionServletException("atencao.naocadastrado", null,
							"Empresa");
				}
			}
		}
		
		this.setaRequest(httpServletRequest,pesquisarUnidadeSuperiorActionForm);
		
		return retorno;
	}
	
	private void pesquisarLocalidade(
			HttpServletRequest httpServletRequest, ActionForward retorno,
			PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm) {
		
		// Filtro para obter o localidade ativo de id informado
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		filtroLocalidade.adicionarParametro(
			new ParametroSimples(FiltroLocalidade.ID, 
				new Integer(pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho() )));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoLocalidade = Fachada.getInstancia()
				.pesquisar(filtroLocalidade,Localidade.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			// Exibe o código e a descrição pesquisa na página
			httpServletRequest.setAttribute("idLocalidadeEncontradaFilho","true");
			
			pesquisarUnidadeSuperiorActionForm.setIdLocalidadeFilho(localidade.getId().toString());
			pesquisarUnidadeSuperiorActionForm.setDescricaoLocalidadeFilho(localidade.getDescricao());

		} else {

			pesquisarUnidadeSuperiorActionForm.setDescricaoLocalidadeFilho("Localidade inexistente");
			pesquisarUnidadeSuperiorActionForm.setIdLocalidadeFilho("");

		}
	}

	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm){
		
		// Localidade
		if(pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho() != null && 
			!pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho().equals("") && 
			pesquisarUnidadeSuperiorActionForm.getDescricaoLocalidadeFilho() != null && 
			!pesquisarUnidadeSuperiorActionForm.getDescricaoLocalidadeFilho().equals("")){
					
			httpServletRequest.setAttribute("idLocalidadeEncontradaFilho","true");
		}
		
	}
	
	
}
