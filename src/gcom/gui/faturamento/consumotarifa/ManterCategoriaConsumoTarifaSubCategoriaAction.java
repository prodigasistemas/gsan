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
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ManterCategoriaConsumoTarifaSubCategoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCategoriaConsumoTarifaSubCategoria");

		Fachada fachada = Fachada.getInstancia();

		InserirCategoriaConsumoTarifaActionForm inserirCategoriaConsumoTarifaActionForm = (InserirCategoriaConsumoTarifaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoFaixa = (Collection) sessao
				.getAttribute("colecaoFaixa");

		if (colecaoFaixa != null) {

			if (colecaoFaixa == null || colecaoFaixa.isEmpty()) {
				throw new ActionServletException(
						"atencao.faixa_categoria_consumo_tarifa");
			}

		} else {
			throw new ActionServletException(
					"atencao.faixa_categoria_consumo_tarifa");
		}

		Iterator iteratorColecaoFaixa = colecaoFaixa.iterator();

		while (iteratorColecaoFaixa.hasNext()) {
			ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) iteratorColecaoFaixa
					.next();
			String parametroConsumoTarifaFaixa = "valorConsumoTarifa"
					+ consumoTarifaFaixa.getUltimaAlteracao().getTime();
			consumoTarifaFaixa.setValorConsumoTarifa(Util
					.formatarMoedaRealparaBigDecimal(httpServletRequest
							.getParameter(parametroConsumoTarifaFaixa)));

		}

		// ######## Colocando os dados dos Forms na SessaoCategoria

		String idCategoria = inserirCategoriaConsumoTarifaActionForm
				.getSlcCategoria();
		
		String idSubCategoria = inserirCategoriaConsumoTarifaActionForm
		.getSlcSubCategoria();

		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.CODIGO, idCategoria));

		Collection colecaoCategoriaPesquisadas = fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());

		Categoria categoriaSelected = (Categoria) Util
				.retonarObjetoDeColecao(colecaoCategoriaPesquisadas);
		
		FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

		filtroSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.ID, idSubCategoria));

		Collection colecaoSubCategoriaPesquisadas = fachada.pesquisar(
				filtroSubCategoria, Subcategoria.class.getName());

		Subcategoria subcategoriaSelected = (Subcategoria) Util
				.retonarObjetoDeColecao(colecaoSubCategoriaPesquisadas);
		

		ConsumoTarifaCategoria consumoTarifaCategoria = new ConsumoTarifaCategoria();

		// Categoria
		consumoTarifaCategoria.setCategoria(categoriaSelected);
		
		//SubCategoria
		consumoTarifaCategoria.setSubCategoria(subcategoriaSelected);

		// Consumo mínimo
		consumoTarifaCategoria.setNumeroConsumoMinimo(new Integer(
				inserirCategoriaConsumoTarifaActionForm.getConsumoMinimo()));

		// Tarifa mínima
		consumoTarifaCategoria
				.setValorTarifaMinima(Util
						.formatarMoedaRealparaBigDecimal(inserirCategoriaConsumoTarifaActionForm
								.getValorTarifaMinima()));

		// Ultima alteração
		consumoTarifaCategoria.setUltimaAlteracao(new Date());

		Collection colecaoCategoria = new ArrayList();
		int numeroFaixasCategoria = 0;

		if (colecaoFaixa != null) {
			numeroFaixasCategoria = colecaoFaixa.size();
		}

		// Atribuindo a colecao faixa valores da categoria
		colecaoFaixa = (Collection) sessao.getAttribute("colecaoFaixa");

		Iterator colecaoFaixaIterator = colecaoFaixa.iterator();

		if ((colecaoFaixa != null) && (!colecaoFaixa.isEmpty())) {
			Iterator colecaoFaixaIt = colecaoFaixa.iterator();
			boolean i = false;
			while (colecaoFaixaIt.hasNext()) {
				ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt
						.next();
				if(new Integer(inserirCategoriaConsumoTarifaActionForm.getConsumoMinimo()).intValue() > consumoTarifaFaixa.getNumeroConsumoFaixaFim().intValue() ){
					throw new ActionServletException(
					"atencao.consumo_minimo.maior.faixa_limite_superior_menor_existe");
				}
				
				
				if (consumoTarifaFaixa.getNumeroConsumoFaixaFim().toString()
						.equals("999999")) {
					i = true;
				}

			}
			if (!i) {
				throw new ActionServletException(
						"atencao.faixa_limite_superior");
			}
		}

		while (colecaoFaixaIterator.hasNext()) {
			ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIterator
					.next();
			consumoTarifaFaixa
					.setConsumoTarifaCategoria(consumoTarifaCategoria);
		}

		if (sessao.getAttribute("colecaoCategoria") != null) {

			colecaoCategoria = (Collection) sessao
					.getAttribute("colecaoCategoria");

			CategoriaFaixaConsumoTarifaHelper consumoTarifaHelper = new CategoriaFaixaConsumoTarifaHelper(
					numeroFaixasCategoria, consumoTarifaCategoria,
					(Collection) sessao.getAttribute("colecaoFaixa"));
			
			String trava = (String) sessao.getAttribute("trava");
			if (this.existeNaColecao(consumoTarifaHelper,httpServletRequest)) {
				
				String novaCategoria = (String) sessao
						.getAttribute("novaCategoria");
				
				if (novaCategoria != null && novaCategoria.equals("sim")) {
					throw new ActionServletException(
							"atencao.consumotaria.categoria_existente");
				}
				
				if (novaCategoria != null && novaCategoria.equals("sim") && !"sim".equals(trava)) {
					throw new ActionServletException(
							"atencao.consumotaria.categoria_existente");
				} else {
					Iterator iteratorColecaoCategoria = colecaoCategoria
							.iterator();
					while (iteratorColecaoCategoria.hasNext()) {
						CategoriaFaixaConsumoTarifaHelper helper = (CategoriaFaixaConsumoTarifaHelper) iteratorColecaoCategoria
								.next();
						if (helper.getConsumoTarifaCategoria().getSubCategoria()
								.getId().equals(
										consumoTarifaHelper
												.getConsumoTarifaCategoria()
												.getSubCategoria().getId())) {
							iteratorColecaoCategoria.remove();
						}

					}

					colecaoCategoria.add(consumoTarifaHelper);

				}
			} else {

				colecaoCategoria.add(consumoTarifaHelper);
			}
		} else {
			colecaoCategoria.add(new CategoriaFaixaConsumoTarifaHelper(
					numeroFaixasCategoria, consumoTarifaCategoria,
					(Collection) sessao.getAttribute("colecaoFaixa")));

			sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		}
		
		// fim.
		sessao.removeAttribute("categoriaNova");
		sessao.removeAttribute("trava");
		if (httpServletRequest.getParameter("testeInserir").equalsIgnoreCase(
				"true")) {
			httpServletRequest.setAttribute("testeInserir", "true");
		}

		return retorno;
	}
	
	
	private boolean existeNaColecao(CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelperForm,
		HttpServletRequest httpServletRequest){
		
		boolean retorno = false;
		
		Collection colecaoCategoria = (Collection) this.getSessao(httpServletRequest).getAttribute("colecaoCategoria");
		
		Iterator itera = colecaoCategoria.iterator();
		if (colecaoCategoria!= null){
			while(itera.hasNext()){
				CategoriaFaixaConsumoTarifaHelper categoriaHelper = (CategoriaFaixaConsumoTarifaHelper) itera.next();
				
				if(categoriaHelper.getConsumoTarifaCategoria().getCategoria().getId().intValue() == 
					categoriaFaixaConsumoTarifaHelperForm.getConsumoTarifaCategoria().getCategoria().getId().intValue() &&
					categoriaHelper.getConsumoTarifaCategoria().getSubCategoria().getId().intValue() == 
						categoriaFaixaConsumoTarifaHelperForm.getConsumoTarifaCategoria().getSubCategoria().getId().intValue()){
					
					retorno = true;
					
				}
			}
		}
		return retorno;
	}
}