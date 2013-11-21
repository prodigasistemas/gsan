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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de atualizar subcategoria
 * 
 * [UC0059] Atualizar Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 04/01/2005
 */
public class ExibirAtualizarSubcategoriaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarSubcategoria");

		FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String codigoSubcategoria = httpServletRequest
				.getParameter("idRegistroAtualizacao");
		
		if (codigoSubcategoria == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				codigoSubcategoria = (String) sessao.getAttribute("codigoSubcategoria");
			}else{
				codigoSubcategoria = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
			
		sessao.setAttribute("codigoSubcategoria", codigoSubcategoria);

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Collection<Categoria> collectionImovelCategoria = fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());

		httpServletRequest.setAttribute("collectionImovelCategoria",
				collectionImovelCategoria);

		// ------Inicio da parte que verifica se vem da página de manter_subcategoria.jsp
		if (codigoSubcategoria != null && !codigoSubcategoria.equals("")) {

			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.ID, codigoSubcategoria));

			// Informa ao filtro para ele buscar objetos associados a Subcategoria
			filtroSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("categoria");

			Collection subcategorias = fachada.pesquisar(filtroSubcategoria,
					Subcategoria.class.getName());

			if (subcategorias != null && !subcategorias.isEmpty()) {
				// A subcategoria foi encontrada
				filtrarSubcategoriaActionForm
						.setIdCategoria(formatarResultado(((Subcategoria) ((List) subcategorias)
								.get(0)).getCategoria().getId().toString()));

				filtrarSubcategoriaActionForm
						.setCodigoSubcategoria(formatarResultado(""
								+ ((Subcategoria) ((List) subcategorias).get(0))
										.getCodigo()));

				filtrarSubcategoriaActionForm
						.setDescricaoSubcategoria(formatarResultado(((Subcategoria) ((List) subcategorias)
								.get(0)).getDescricao()));
				
				filtrarSubcategoriaActionForm
				.setDescricaoAbreviada(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getDescricaoAbreviada()));
				
				filtrarSubcategoriaActionForm
				.setCodigoTarifaSocial(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getCodigoTarifaSocial()));
				
				filtrarSubcategoriaActionForm
				.setCodigoGrupoSubcategoria(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getCodigoGrupoSubcategoria()));

				filtrarSubcategoriaActionForm
				.setNumeroFatorFiscalizacao(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getNumeroFatorFiscalizacao()));
				
				filtrarSubcategoriaActionForm
				.setIndicadorTarifaConsumo(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getIndicadorTarifaConsumo()));
				
				filtrarSubcategoriaActionForm
				.setIndicadorSazonalidade(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getIndicadorSazonalidade()));
				
				filtrarSubcategoriaActionForm
						.setIndicadorUso(formatarResultado(""
								+ ((Subcategoria) ((List) subcategorias).get(0))
										.getIndicadorUso()));

				filtrarSubcategoriaActionForm.setIdSubcategoria(codigoSubcategoria);
				
				Subcategoria subcategoria = ((Subcategoria) ((List) subcategorias)
						.get(0));

				sessao.setAttribute("subcategoria", subcategoria);
				sessao.setAttribute("filtrarSubcategoriaActionForm",
						filtrarSubcategoriaActionForm);

			}

		}
		// ------Fim da parte que verifica se vem da página de
		// manter_subcategoria.jsp
		
		// caso ainda não tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}
		return retorno;
	}

	/**
	 * Formata o resultado 
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}
}