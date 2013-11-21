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
package gcom.gui.cadastro;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
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
 * [UC1162] AUTORIZAR ALTERACAO INSCRICAO IMOVEL
 * 
 * @author Rodrigo Cabral
 * @date 04/04/2011
 */

public class ExibirFiltrarAlteracaoInscricaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarAlteracaoInscricaoImovel");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarAlteracaoInscricaoImovelActionForm form = (FiltrarAlteracaoInscricaoImovelActionForm) actionForm;

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {
		
			switch (Integer.parseInt(objetoConsulta)) {
				// Localidade
				case 1:
					pesquisarLocalidade(form, fachada, httpServletRequest);
					break;
	
				// Setor Comercial
				case 2:
					pesquisarLocalidade(form, fachada, httpServletRequest);
					pesquisarSetorComercial(form, fachada, httpServletRequest);
					break;
				
				default:
					break;
			}
		} else {
			sessao.removeAttribute("form");
		}

		return retorno;
	}
	
	private void pesquisarLocalidade(
			FiltrarAlteracaoInscricaoImovelActionForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		
			// Recebe o valor do campo localidadeOrigemID do formulário.
			String idLocalidade = (String) form.getIdLocalidade();
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));
			
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			// Retorna localidade
			Collection colecaoPesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				form.setIdLocalidade("");
				form.setDesLocalidade("Localidade inexistente");
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
				
			} else {
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				form.setIdLocalidade(String.valueOf(objetoLocalidade.getId()));
				form.setDesLocalidade(objetoLocalidade.getDescricao());
				
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
				
				}
		

	}
	
	private void pesquisarSetorComercial(
			FiltrarAlteracaoInscricaoImovelActionForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		
			// Recebe o valor do campo localidadeOrigemID do formulário.
			String idLocalidade = (String) form.getIdLocalidade();

			// O campo idLocalidade será obrigatório
			if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
				String codigoSetorComercial = (String) form.getCodigoSetorComercial();
				
				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
				
				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
				
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
				
				// Retorna setorComercial
				Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					form.setCodigoSetorComercial("");
					form.setIdSetorComercial("");
					form.setDesSetorComercial("Setor comercial inexistente.");
					
					httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					//setorComercialOrigem
					form.setCodigoSetorComercial(
							String.valueOf(objetoSetorComercial.getCodigo()));
					form.setIdSetorComercial(
							String.valueOf(objetoSetorComercial.getId()));
					form.setDesSetorComercial(
							objetoSetorComercial.getDescricao());
					

				}
			} else {
				// Limpa o campo codigoSetorComercial do formulário
				form.setCodigoSetorComercial("");
				form.setDesSetorComercial(
						"Informe a localidade da inscrição de origem.");
				
				httpServletRequest.setAttribute("corSetorComercialOrigem", "exception");
			}
		}
}
