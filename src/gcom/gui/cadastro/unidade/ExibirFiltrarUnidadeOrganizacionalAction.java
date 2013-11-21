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
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.FiltroUnidadeTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
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
 * < <Descrição da Classe>>
 * 
 * @author Ana Maria		
 * @date 20/11/2006
 */
public class ExibirFiltrarUnidadeOrganizacionalAction extends GcomAction {
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

		// Obtém o action form
		UnidadeOrganizacionalActionForm form = (UnidadeOrganizacionalActionForm) actionForm;

		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("filtrarUnidadeOrganizacional");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();

		// Obtém o objetoCosulta vindo na sessão
		String consultaLocalidade = (String) httpServletRequest.getParameter("consultaLocalidade");

		if (consultaLocalidade != null
				&& !consultaLocalidade.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(consultaLocalidade)) == 1) {

			// Filtro para obter o localidade ativo de id informado
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, new Integer(form.getIdLocalidade()),
							ParametroSimples.CONECTOR_AND));

			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoLocalidade = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

				// Obtém o objeto da coleção pesquisada
				Localidade localidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidade);

				// Exibe o código e a descrição pesquisa na página
				httpServletRequest.setAttribute("corLocalidade", "valor");
				form.setIdLocalidade(localidade.getId().toString());
				form.setDescricaoLocalidade(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "fixo");
			} else {
				// Exibe mensagem de código inexiste e limpa o campo de código
				httpServletRequest.setAttribute("corLocalidade",
						"exception");
				form.setIdLocalidade("");
				form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
			}
		}
		
		String consultaUnidadeSuperior = (String) httpServletRequest.getParameter("consultaUnidadeSuperior");
		
		if (consultaUnidadeSuperior != null
				&& !consultaUnidadeSuperior.trim().equalsIgnoreCase("")
				&& (Integer.parseInt(consultaUnidadeSuperior)) == 1) {
			
			// Filtro para obter o Unidade Superior ativo de id informado
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, new Integer(form.getIdUnidadeSuperior()),
							ParametroSimples.CONECTOR_AND));

			// Pesquisa de acordo com os parâmetros informados no filtro
			Collection colecaoUnidadeSuperior = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoUnidadeSuperior != null && !colecaoUnidadeSuperior.isEmpty()) {

				// Obtém o objeto da coleção pesquisada
				UnidadeOrganizacional unidadeSuperior = (UnidadeOrganizacional) Util
						.retonarObjetoDeColecao(colecaoUnidadeSuperior);

				// Exibe o código e a descrição pesquisa na página
				httpServletRequest.setAttribute("corUnidadeSuperior", "valor");
				form.setIdUnidadeSuperior(unidadeSuperior.getId().toString());
				form.setDescricaoUnidadeSuperior(unidadeSuperior.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "fixo");
			} else {
				// Exibe mensagem de código inexiste e limpa o campo de código
				httpServletRequest.setAttribute("corUnidadeSuperior","exception");
				form.setIdUnidadeSuperior("");
				form.setDescricaoUnidadeSuperior("UNIDADE ORGANIZACIONAL INEXISTENTE");

				httpServletRequest.setAttribute("nomeCampo", "idUnidadeSuperior");	
			}
		}
		if (sessao.getAttribute("colecaoTipoUnidade") == null
				&& sessao.getAttribute("colecaoGerenciaRegional") == null
				&& sessao.getAttribute("colecaoEmpresa") == null
				&& sessao.getAttribute("colecaoUnidadeCentralizadora") == null
				&& sessao.getAttribute("colecaoUnidadeRepavimentadora") == null
				&& sessao.getAttribute("colecaoMeioSolicitacao") == null) {

        	//Pesquisando Tipo da Unidade 
			FiltroUnidadeTipo filtroUnidadeTipo = new FiltroUnidadeTipo();
			filtroUnidadeTipo.adicionarParametro(new ParametroSimples(
					FiltroUnidadeTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeTipo.setCampoOrderBy(FiltroUnidadeTipo.DESCRICAO);
			Collection colecaoTipoUnidade = fachada.pesquisar(
					filtroUnidadeTipo, UnidadeTipo.class.getName());
			sessao.setAttribute("colecaoTipoUnidade", colecaoTipoUnidade);
			
        	//Pesquisando Gerência regional
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);


			// Pesquisando Empresas
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			
			// Pesquisando Unidade Centralizadora
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, UnidadeTipo.UNIDADE_TIPO_CENTRALIZADORA));
			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);
			Collection colecaoUnidadeCentralizadora = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			sessao.setAttribute("colecaoUnidadeCentralizadora", colecaoUnidadeCentralizadora);	
			
			
			//...........................................................................................
			// 06/03/2008 - Alteração solicitada por Fabíola Araújo. 
			// Yara Taciane de Souza.
			//8.0 -  Inclusão de opção de tratamento pra Unidade Repavimentadora.
			FiltroUnidadeOrganizacional filtroUnidadeRepavimentadora = new FiltroUnidadeOrganizacional();
			filtroUnidadeRepavimentadora.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));			
			filtroUnidadeRepavimentadora.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA));			
			filtroUnidadeRepavimentadora.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);			
			Collection colecaoUnidadeRepavimentadora = fachada.pesquisar(
					filtroUnidadeRepavimentadora, UnidadeOrganizacional.class.getName());
			sessao.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);				
			//.........................................................................................
			
			
			//Pesquisando Meio de Solicitação
			FiltroMeioSolicitacao filtroMeioSolicitacao = new FiltroMeioSolicitacao();
			filtroMeioSolicitacao.setCampoOrderBy(FiltroMeioSolicitacao.DESCRICAO);
			Collection colecaoMeioSolicitacao = fachada.pesquisar(
					filtroMeioSolicitacao, MeioSolicitacao.class.getName());
			sessao.setAttribute("colecaoMeioSolicitacao", colecaoMeioSolicitacao);	
			
			form.setOrdernarPor("1");
		}
		
		if(httpServletRequest.getParameter("menu") != null){
			
			form.setIndicadorAtualizar("1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

			sessao.setAttribute("indicadorAtualizar", form.getIndicadorAtualizar());
		}
		
		return retorno;
	}

}
