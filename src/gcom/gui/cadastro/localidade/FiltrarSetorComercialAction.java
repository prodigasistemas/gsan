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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
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

public class FiltrarSetorComercialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém a instância da fachada
		//Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping
				.findForward("retornarFiltroSetorComercial");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarFiltrarSetorComercialActionForm pesquisarFiltrarSetorComercialActionForm = (PesquisarFiltrarSetorComercialActionForm) actionForm;

		//String atualizar = pesquisarFiltrarSetorComercialActionForm
		//		.getAtualizar();

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		// Objetos que serã retornados pelo hibernate
		filtroSetorComercial.setCampoOrderBy(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL
				,FiltroSetorComercial.DESCRICAO_LOCALIDADE);
		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroSetorComercial
				.adicionarCaminhoParaCarregamentoEntidade("municipio");

		String localidadeID = pesquisarFiltrarSetorComercialActionForm
				.getLocalidadeID();
		String setorComercialCD = pesquisarFiltrarSetorComercialActionForm
				.getSetorComercialCD();
		String setorComercialNome = pesquisarFiltrarSetorComercialActionForm
				.getSetorComercialNome();
		String municipioID = pesquisarFiltrarSetorComercialActionForm
				.getMunicipioID();
		String indicadorUso = pesquisarFiltrarSetorComercialActionForm
				.getIndicadorUso();
		String tipoPesquisa = pesquisarFiltrarSetorComercialActionForm
				.getTipoPesquisa();
		String indicadorSetorAlternativo = pesquisarFiltrarSetorComercialActionForm.getIndicadorSetorAlternativo();

		// 1 check --- null uncheck
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		boolean peloMenosUmParametroInformado = false;

		if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(localidadeID)));
		}

		if (setorComercialCD != null && !setorComercialCD.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					setorComercialCD));
		}

		if (setorComercialNome != null
				&& !setorComercialNome.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
				filtroSetorComercial
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroSetorComercial.DESCRICAO,
								setorComercialNome));
			} else {
				filtroSetorComercial.adicionarParametro(new ComparacaoTexto(
						FiltroSetorComercial.DESCRICAO, setorComercialNome));
			}
		}

		if (municipioID != null && !municipioID.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_MUNICIPIO, municipioID));
		}

		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")
				&& !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		if ( indicadorSetorAlternativo != null && !indicadorSetorAlternativo.equalsIgnoreCase("") &&
				!indicadorSetorAlternativo.equalsIgnoreCase("3") ) {
			peloMenosUmParametroInformado = true;
			if ( indicadorSetorAlternativo.equalsIgnoreCase( String
					.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroSetorComercial.adicionarParametro( new ParametroSimples( 
						FiltroSetorComercial.INDICADOR_SETOR_ALTERNATIVO, 
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroSetorComercial.adicionarParametro( new ParametroSimples(
						FiltroSetorComercial.INDICADOR_SETOR_ALTERNATIVO,
						ConstantesSistema.INDICADOR_USO_DESATIVO ) );
			}
		}
		// Está consulta irá retornar todos os setores comerciais, inclusive os
		// inativos

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		sessao.setAttribute("filtroSetorComercial", filtroSetorComercial);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		sessao.setAttribute("voltar", "manter");
		
		// devolve o mapeamento de retorno
		return retorno;
	}

}
