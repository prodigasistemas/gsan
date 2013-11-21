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

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

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
 * 
 * @author Thiago Tenório
 * @created 21 de Julho de 2005
 */
public class PesquisarUnidadeEmpresaAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("listaUnidadeEmpresaResultado");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		UnidadeEmpresaActionForm unidadeEmpresaActionForm = (UnidadeEmpresaActionForm) actionForm;

		// Recupera os parâmetros do form
		String idUnidade = unidadeEmpresaActionForm.getCodigoUnidade();
		String nomeUnidade = unidadeEmpresaActionForm.getNomeUnidade();
		String siglaUnidade =  unidadeEmpresaActionForm.getSiglaUnidade();
		String nivelHiearquia =  unidadeEmpresaActionForm.getNivelHiearquia();
		String idUnidadeSuperior = unidadeEmpresaActionForm.getIdUnidadeSuperior();
		
		// filtro para a pesquisa de endereco do cliente
		FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

		filtroUnidadeEmpresa.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (idUnidade != null
				&& !idUnidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, idUnidade));
		}

		if (nomeUnidade != null && !nomeUnidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ComparacaoTexto(
					FiltroUnidadeOrganizacional.DESCRICAO, nomeUnidade));
		}

		if (siglaUnidade != null && !siglaUnidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.SIGLA, siglaUnidade));
		}

		if (nivelHiearquia != null && !nivelHiearquia.equals("" +(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, nivelHiearquia));
		}

		if (idUnidadeSuperior != null && !idUnidadeSuperior.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idUnidadeSuperior));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroUnidadeEmpresa
				.adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtroUnidadeEmpresa
				.adicionarCaminhoParaCarregamentoEntidade("unidadeNivel");

		Collection colecaoUnidadesEmpresas = null;

		// Obtém a instância da Fachada
		//Fachada fachada = Fachada.getInstancia();

		// pesquisa os endereços do cliente
//		colecaoUnidadesEmpresas = fachada
//				.pesquisar(filtroUnidadeEmpresa, UnidadeOrganizacional.class.getName());

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroUnidadeEmpresa, UnidadeOrganizacional.class.getName());

		colecaoUnidadesEmpresas = (Collection) resultado
				.get("colecaoRetorno");

		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		

		if (colecaoUnidadesEmpresas == null || colecaoUnidadesEmpresas.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "unidade empresa");
		} else if (colecaoUnidadesEmpresas.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("colecaoUnidadesEmpresas",
					colecaoUnidadesEmpresas);

		}

		return retorno;
	}

}
