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

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * @author Rafael Pinto
 * @created 26/07/2006
 */
public class PesquisarUnidadeSuperiorAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaUnidadeSuperiorResultado");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarUnidadeSuperiorActionForm pesquisarUnidadeSuperiorActionForm 
			= (PesquisarUnidadeSuperiorActionForm) actionForm;

		// Recupera os parâmetros do form
		String unidadeTipo 	= pesquisarUnidadeSuperiorActionForm.getUnidadeTipoFilho();
		String nivel 		=  pesquisarUnidadeSuperiorActionForm.getNivelHierarquicoFilho();
		String localidade	=  pesquisarUnidadeSuperiorActionForm.getIdLocalidadeFilho();
		
		String gerenciaRegional	=  pesquisarUnidadeSuperiorActionForm.getGerenciaRegionalFilho();
		String descricao		=  pesquisarUnidadeSuperiorActionForm.getDescricaoFilho();
		
		String sigla	=  pesquisarUnidadeSuperiorActionForm.getSiglaFilho();
		String empresa	=  pesquisarUnidadeSuperiorActionForm.getIdEmpresaFilho();

		String meioSolicitacao	=  pesquisarUnidadeSuperiorActionForm.getMeioSolicitacaoFilho();
		
		String unidadeEsgoto = pesquisarUnidadeSuperiorActionForm.getUnidadeEsgotoFilho();
		String unidadeAbreRegistro = pesquisarUnidadeSuperiorActionForm.getUnidadeAbreRegistroFilho();
		String unidadeAceita = pesquisarUnidadeSuperiorActionForm.getUnidadeAceitaFilho();
		
		// filtro para a pesquisa da unidade organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (unidadeTipo != null && !unidadeTipo.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeTipo));
		}

		if (nivel != null && !nivel.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.UNIDADE_TIPO_NIVEL, nivel));
		}

		if (localidade != null && !localidade.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_LOCALIDADE, localidade));
		}

		if (gerenciaRegional != null && 
			!gerenciaRegional.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.GERENCIAL_REGIONAL, gerenciaRegional));
		}

				
		if (descricao != null && !descricao.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ComparacaoTexto(FiltroUnidadeOrganizacional.DESCRICAO, descricao));
		}

		if (sigla != null && !sigla.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.SIGLA, sigla));
		}

		if (empresa != null && !empresa.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.EMPRESA, empresa));
		}

		if (unidadeEsgoto != null && !unidadeEsgoto.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;

			if(!unidadeEsgoto.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_ESGOTO,unidadeEsgoto));
			}
			
		}

		if (unidadeAbreRegistro != null && !unidadeAbreRegistro.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			if(!unidadeAbreRegistro.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_ABERTURA_RA,unidadeAbreRegistro));
			}			

		}

		if (unidadeAceita != null && !unidadeAceita.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			if(!unidadeAceita.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){			
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_TRAMITE,unidadeAceita));
			}
			
		}

		if (meioSolicitacao != null && 
				!meioSolicitacao.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.MEIO_SOLICITACAO, meioSolicitacao));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// adiciona as dependências para serem mostradas na página
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		
		Collection colecaoUnidadeOrganizacionalSuperior = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		colecaoUnidadeOrganizacionalSuperior = 
			fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());


		if (colecaoUnidadeOrganizacionalSuperior == null || colecaoUnidadeOrganizacionalSuperior.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "unidade organizacional");
		} else if (colecaoUnidadeOrganizacionalSuperior.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("colecaoUnidadeOrganizacionalSuperior",colecaoUnidadeOrganizacionalSuperior);
		}

		return retorno;
	}

}
