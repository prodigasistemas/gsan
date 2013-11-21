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
package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirSubCategoriaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirSubCategoriaConsumoTarifa");
	
		Fachada fachada = Fachada.getInstancia();
	
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
	
		// ----------------FILTRO CATEGORIAS DE ESTABELECIMENTO - PROPULAR
		// DROPDOWN ------
		
		if (httpServletRequest.getParameter("parametroVigencia") != null){
			String vigencia = (String) httpServletRequest.getParameter("parametroVigencia");
			sessao.setAttribute("vigencia", vigencia);
		}
		if (httpServletRequest.getParameter("parametroDescricao") != null){
			String descricao = (String) httpServletRequest.getParameter("parametroDescricao");
			sessao.setAttribute("descricao", descricao);
		}
		if (httpServletRequest.getParameter("parametroSelect") != null){
			String select = (String) httpServletRequest.getParameter("parametroSelect");
			sessao.setAttribute("select", select);
		}
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
	
		
		if ((httpServletRequest.getParameter("limpa") != null)
				&& (httpServletRequest.getParameter("limpa").equals("1"))) {
			sessao.removeAttribute("InserirCategoriaConsumoTarifaActionForm");
			sessao.removeAttribute("valorMinimo");
			sessao.removeAttribute("colecaoFaixa");
			sessao.removeAttribute("consumoMinimo");
			sessao.setAttribute("categoriaSelected", ConstantesSistema.NUMERO_NAO_INFORMADO);
			sessao.setAttribute("subCategoriaSelected", ConstantesSistema.NUMERO_NAO_INFORMADO);
		}
		
		InserirSubCategoriaConsumoTarifaActionForm inserirSubCategoriaConsumoTarifaActionForm = new InserirSubCategoriaConsumoTarifaActionForm();
		
		if ((sessao.getAttribute("valorMinimo") != null)
				&& (! sessao.getAttribute("valorMinimo").equals(""))) {
			inserirSubCategoriaConsumoTarifaActionForm.setValorTarifaMinima((String) sessao.getAttribute("valorMinimo").toString());
		}
		
		if ((sessao.getAttribute("consumoMinimo") != null)
				&& (! sessao.getAttribute("consumoMinimo").equals(""))) {
			inserirSubCategoriaConsumoTarifaActionForm.setConsumoMinimo((String) sessao.getAttribute("consumoMinimo"));
		}
		
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		
		sessao.setAttribute("inserirSubCategoriaConsumoTarifaActionForm", inserirSubCategoriaConsumoTarifaActionForm);
		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		//preenchendo o combo de subcategoria
		if (httpServletRequest.getParameter("pesquisarSubCategoria") != null){
			String idCategoria = httpServletRequest.getParameter("categoria");
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, idCategoria));
			filtroSubCategoria.adicionarParametro(new ParametroNaoNulo(FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
			if (!idCategoria.equalsIgnoreCase("-1")){
				Collection colecaoSubCategoria = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				if (colecaoSubCategoria != null && !colecaoSubCategoria.isEmpty()){
					sessao.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
				} else {
					sessao.removeAttribute("colecaoSubCategoria");
				}
			} else {
				sessao.removeAttribute("colecaoSubCategoria");
			}
			sessao.setAttribute("categoriaSelected", idCategoria);
		}
		
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
	
		return retorno;
	}

}
