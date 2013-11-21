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
package gcom.gui.operacional;


import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FiltroDivisaoEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Este caso de uso permite a inclusão de uma Divisão de Esgoto
 * 
 * [UC0815] Inserir Divisao de Esgoto
 * 
 * 
 * @author Arthur Carvalho
 * @date 09/06/2008
 */
public class InserirDivisaoEsgotoAction extends GcomAction {

		public ActionForward execute(ActionMapping actionMapping,
				ActionForm actionForm, HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");

			InserirDivisaoEsgotoActionForm inserirDivisaoEsgotoActionForm = (InserirDivisaoEsgotoActionForm) actionForm;

			HttpSession sessao = httpServletRequest.getSession(false);

			Fachada fachada = Fachada.getInstancia();

			String descricao = inserirDivisaoEsgotoActionForm.getDescricao();
			String idUnidadeOrganizacional= inserirDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
			
			
			
			DivisaoEsgoto divisaoEsgoto= new DivisaoEsgoto();
			Collection colecaoPesquisa = null;
			

			// Descricao
			if (!"".equals(inserirDivisaoEsgotoActionForm.getDescricao())) {
				divisaoEsgoto.setDescricao(inserirDivisaoEsgotoActionForm
						.getDescricao());
			} else {
				throw new ActionServletException("atencao.required", null,
						"descrição");
			}
			
			/*
			 * Unidade Organizacional é obrigatório 
			 */
			if (idUnidadeOrganizacional == null || idUnidadeOrganizacional.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,  "Unidade Organizacional");
			} else {
				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional= new FiltroUnidadeOrganizacional();
	            
	            filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
	                    FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));

				filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Unidade Organizacional
				colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.distrito_operacional_inexistente");
				} else {
					UnidadeOrganizacional objetoUnidadeOrganizacional = (UnidadeOrganizacional) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					divisaoEsgoto.setUnidadeOrganizacional(objetoUnidadeOrganizacional);
				}
			}
			
			Short iu = 1;
			divisaoEsgoto.setIndicadorUso(iu);
			
			// Ultima alteração
			divisaoEsgoto.setUltimaAlteracao(new Date());

			
			FiltroDivisaoEsgoto filtroDivisaoEsgoto = new FiltroDivisaoEsgoto();

			filtroDivisaoEsgoto.adicionarParametro(new ParametroSimples(
					FiltroDivisaoEsgoto.DESCRICAO, divisaoEsgoto.getDescricao()));

			
			colecaoPesquisa = (Collection) fachada.pesquisar(
					filtroDivisaoEsgoto, DivisaoEsgoto.class.getName());

			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
				throw new ActionServletException(
						"atencao.divisao_esgoto_ja_cadastrada", null,
						descricao);
				
			
			} else {
				divisaoEsgoto.setDescricao(descricao);
				

				Integer idDivisaoEsgoto = (Integer) fachada
						.inserir(divisaoEsgoto);

				montarPaginaSucesso(httpServletRequest,
						"Divisão de Esgoto " + descricao
								+ " inserido com sucesso.",
						"Inserir outra Divisão de Esgoto",
						"exibirInserirDivisaoEsgotoAction.do?menu=sim",
						"exibirAtualizarDivisaoEsgotoAction.do?idRegistroAtualizacao="
								+ idDivisaoEsgoto,
						"Atualizar Divisão de Esgoto Inserida");

				sessao.removeAttribute("InserirDivisaoEsgotoActionForm");

				return retorno;
			}

			}
		}

