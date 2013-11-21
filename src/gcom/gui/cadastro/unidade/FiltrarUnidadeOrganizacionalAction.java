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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

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
public class FiltrarUnidadeOrganizacionalAction extends GcomAction {
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

		HttpSession sessao = httpServletRequest.getSession(false);
		
		UnidadeOrganizacionalActionForm form = (UnidadeOrganizacionalActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("retornarFiltroUnidadeOrganizacional");

		// Recupera os parâmetros do form
		String idUnidade = form.getIdUnidade();
		String unidadeTipo 	=  form.getIdTipoUnidade();
		String nivel 		=  form.getNivelHierarquico();
		String localidade	=  form.getIdLocalidade();
		
		String gerenciaRegional	=  form.getIdGerenciaRegional();
		String descricao		=  form.getDescricao();
		
		String sigla	=  form.getSigla();
		String empresa	=  form.getIdEmpresa();
		
		String unidadeSuperior	=  form.getIdUnidadeSuperior();
		String unidadeCentralizadora	=  form.getIdUnidadeCentralizadora();
		String unidadeRepavimentadora	=  form.getIdUnidadeRepavimentadora();
		String meioSolicitacao	=  form.getIdMeioSolicitacao();
		
		String unidadeEsgoto = form.getUnidadeEsgoto();
		String unidadeAbreRegistro = form.getUnidadeAbreRA();
		String unidadeAceita = form.getUnidadeTramitacao();
		String indicadorAtualizar = httpServletRequest.getParameter("atualizar");
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();
		String ordernarPor = form.getOrdernarPor();
		
		// filtro para a pesquisa da unidade organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		if (ordernarPor != null
				&& ordernarPor.equals(ConstantesSistema.ORDENAR_POR_CODIGO)) {
			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.ID);
		} else {
			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);
		}
		

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (idUnidade != null && !idUnidade.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidade));
		}
		
		if (unidadeTipo != null && !unidadeTipo.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO, unidadeTipo));
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

		if (gerenciaRegional != null && !gerenciaRegional.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.GERENCIAL_REGIONAL, gerenciaRegional));
		}

		if (descricao != null && !descricao.equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				
				filtroUnidadeOrganizacional.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroUnidadeOrganizacional.DESCRICAO, descricao));
			} else {
				filtroUnidadeOrganizacional.adicionarParametro(
					new ComparacaoTexto(FiltroUnidadeOrganizacional.DESCRICAO, descricao));
			}
		}
		

		if (sigla != null && !sigla.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ComparacaoTexto(FiltroUnidadeOrganizacional.SIGLA, sigla));
		}

		if (empresa != null && !empresa.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.EMPRESA, empresa));
		}

		if (unidadeSuperior != null && !unidadeSuperior.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, unidadeSuperior));
		}

		if (unidadeCentralizadora != null && 
			!unidadeCentralizadora.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_CENTRALIZADORA, 
					unidadeCentralizadora));
		}
		
		if (unidadeRepavimentadora != null && 
				!unidadeRepavimentadora.trim().equals("")) {
				
				peloMenosUmParametroInformado = true;
				
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.ID_UNIDADE_REPAVIMENTADORA, 
							unidadeRepavimentadora));
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
				!meioSolicitacao.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.MEIO_SOLICITACAO, meioSolicitacao));
		}
		
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			if(!indicadorUso.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){			
				filtroUnidadeOrganizacional.adicionarParametro(
					new ParametroSimples(FiltroUnidadeOrganizacional.INDICADOR_USO, indicadorUso));
			}
		}		

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pela sessão para o ExibirManterUnidadeOrganizacionalAction
		sessao.setAttribute("filtroUnidadeOrganizacional", filtroUnidadeOrganizacional);
		if(indicadorAtualizar != null && !indicadorAtualizar.trim().equals("")){
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		}else{
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		
		return retorno;
	}
		
}
