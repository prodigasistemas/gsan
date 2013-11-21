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
package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que filtra os parâmetros inseridos no logradouro_filtrar.jsp para
 * recuperar os logradouros
 * 
 * @author Sávio Luiz
 * @date 28/06/2006
 */
public class FiltrarLogradouroAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("retornarFiltroLogradouro");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Recupera os parâmetros do form
		String idMunicipio = (String) pesquisarActionForm.get("idMunicipioFiltro");
		String idBairro = (String) pesquisarActionForm.get("idBairro");
		
		String nomeLogradouro = (String) pesquisarActionForm
				.get("nomeLogradouro");
		String nomePopularLogradouro = (String) pesquisarActionForm
				.get("nomePopularLogradouro");
		String idLogradouro = (String) pesquisarActionForm.get("idLogradouro");
		String codigoCep = (String) pesquisarActionForm.get("cep");
		Integer idTipoLogradouro = (Integer) pesquisarActionForm.get("idTipo");
		Integer idTituloLogradouro = (Integer) pesquisarActionForm
				.get("idTitulo");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID_MUNICIPIO, idMunicipio));
		}
		if (nomeLogradouro != null
				&& !nomeLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ComparacaoTexto(
			// FiltroLogradouro.NOME, nomeLogradouro));
		}
		if (nomePopularLogradouro != null
				&& !nomePopularLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ComparacaoTexto(
			// FiltroLogradouro.NOME_POPULAR, nomePopularLogradouro));
		}
		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID, idLogradouro));
		}

		if (idTipoLogradouro != null
				&& idTipoLogradouro.intValue() > ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID_LOGRADOUROTIPO, idTipoLogradouro));
		}
		if (idTituloLogradouro != null
				&& idTituloLogradouro.intValue() > ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID_LOGRADOUROTITULO, idTituloLogradouro));
		}

		/*
		 * Caso seja passado como parâmetro o código do Município
		 */
		if (codigoCep != null && !codigoCep.equals("")) {

			if (!peloMenosUmParametroInformado) {
				httpServletRequest.setAttribute("consultaPorCep", "OK");
			}

			peloMenosUmParametroInformado = true;
			httpServletRequest.setAttribute("codigoCep", codigoCep);
		}
		if (idBairro != null && !idBairro.equals("")) {

			if (!peloMenosUmParametroInformado) {
				httpServletRequest.setAttribute("consultaPorBairro", "OK");
			}

			peloMenosUmParametroInformado = true;
			
			sessao.setAttribute("idBairroFiltro","true");
			
			httpServletRequest.setAttribute("idBairro", idBairro);
		}else{
			sessao.setAttribute("idBairroFiltro","false");
		}
		
		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.INDICADORUSO, indicadorUso));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pelo request para o ExibirManterLogradouroAction
		httpServletRequest.setAttribute("filtroLogradouro", filtroLogradouro);
		// sessao.setAttribute("filtroLogradouro", filtroLogradouro);

		String atualizar = null;

		atualizar = (String) httpServletRequest.getParameter("atualizar");

		if (atualizar != null && !atualizar.equalsIgnoreCase("")) {
			
			sessao.setAttribute("atualizar", "true");
		}
		
		FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem(
				FiltroOSProgramaCalibragem.NUMERO_GRAU_IMPORTANCIA);
		filtroOSProgramaCalibragem.setConsultaSemLimites(true);
		filtroOSProgramaCalibragem.adicionarParametro(new ParametroSimples(
				FiltroOSProgramaCalibragem.PRIORIZACAO_TIPO_ID,
				new Integer(1)));
		filtroOSProgramaCalibragem.adicionarCaminhoParaCarregamentoEntidade("priorizacaoTipo");
		Collection osProgramacaoCalibragem = Fachada.getInstancia().pesquisar(
				filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());

		if (osProgramacaoCalibragem != null && !osProgramacaoCalibragem.isEmpty()) {
		
			sessao.setAttribute("osProgramacaoCalibragem", osProgramacaoCalibragem);
		}

		return retorno;		
		
	}
	
	
}
