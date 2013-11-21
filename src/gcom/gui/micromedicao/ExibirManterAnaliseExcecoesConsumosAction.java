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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Administrador
 * @date 07/03/2006
 * 
 */
public class ExibirManterAnaliseExcecoesConsumosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterAnaliseExcecoesConsumo");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		/*
		 * Colocado por Raphael Rossiter em 04/12/2007 - Analista: Claudio Lira
		 * OBJ: Não perder os registros selecionados na paginação
		 */
		//========================================================================================
		this.capturarSelecao(sessao, httpServletRequest);
		
		this.capturarImoveisJaSelecionados(sessao, httpServletRequest);
		
		this.redirecionarAnalise(httpServletRequest);
		//========================================================================================
		
				
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();

		if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
			filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao
					.getAttribute("filtroMedicaoHistoricoSql");
			
			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)sessao.getAttribute("faturamentoGrupo");
			
			// 1º Passo - Pegar o total de registros através de um count da
			// consulta que aparecerá na tela
			Integer totalRegistros = fachada
				.filtrarExcecoesLeiturasConsumosCount(faturamentoGrupo, filtroMedicaoHistoricoSql, (String)sessao.getAttribute("mesAnoPesquisa"),(String)sessao.getAttribute("valorAguaEsgotoInicial"), (String)sessao.getAttribute("valorAguaEsgotoFinal"));

			// 2º Passo - Chamar a função de Paginação passando o total de
			// registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			String tipoApresentacao = (String)sessao.getAttribute("tipoApresentacao");
			// 3º Passo - Obter a coleção da consulta que aparecerá na tela
			// passando o numero de paginas
			// da pesquisa que está no request
			Collection colecaoImovelMedicao = null;
			colecaoImovelMedicao = fachada
						.filtrarExcecoesLeiturasConsumos(faturamentoGrupo, filtroMedicaoHistoricoSql,
								(Integer) httpServletRequest
										.getAttribute("numeroPaginasPesquisa"), false, 
										(String)sessao.getAttribute("mesAnoPesquisa"),
										(String)sessao.getAttribute("valorAguaEsgotoInicial"), 
										(String)sessao.getAttribute("valorAguaEsgotoFinal"));


			if (colecaoImovelMedicao == null || colecaoImovelMedicao.isEmpty()) {
				// Nenhum cliente cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			sessao.setAttribute("colecaoImovelMedicao", colecaoImovelMedicao);
			sessao.setAttribute("totalRegistros", totalRegistros);
			sessao.setAttribute("numeroPaginasPesquisa",httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));
			
			// se or igual a normal coloca um indicador na sessao para resolver no jsp
			if(tipoApresentacao.trim().equalsIgnoreCase("1")){
				sessao.setAttribute("indicadorTipoApresentacao", true);
			}else{
				sessao.removeAttribute("indicadorTipoApresentacao");
			}

			// Muitos registros encontrados
			// throw new ActionServletException(
			// "atencao.pesquisa.muitosregistros", null);

		}

		return retorno;
	}
	
	/**
	 * Capturar os imóveis selecionados pelo usuário
	 * 
	 * @author Raphael Rossiter
	 * @date 05/12/2007
	 * 
	 */
	public void capturarSelecao(HttpSession sessao, HttpServletRequest httpServletRequest){
		
		//CASO VENHA DA TELA DE ANALISE
		String telaAnalise = httpServletRequest.getParameter("concluir");
		
		if (telaAnalise == null){
		
			String paginaCorrente = httpServletRequest.getParameter("paginaCorrente");
			
			String idsImoveisJuntos = httpServletRequest.getParameter("idRegistrosImovel");
			String[] idsImovel = null;
			
			if (idsImoveisJuntos != null && idsImoveisJuntos.length() > 0){
				idsImovel = idsImoveisJuntos.split(",");
			}
			
			HashMap<String, String[]> imoveisPorPagina = (HashMap) sessao.getAttribute("idsImoveisJaSelecionados");
				
			if (imoveisPorPagina != null && !imoveisPorPagina.isEmpty()){
				
				if (imoveisPorPagina.containsKey(paginaCorrente)){
					
					if (idsImovel != null && idsImovel.length > 0){
						
						//ATUALIZAÇÃO
						imoveisPorPagina.put(paginaCorrente, idsImovel);
					}
					else{
						
						//REMOÇÃO
						imoveisPorPagina.remove(paginaCorrente);
					}
				}
				else if (idsImovel != null && idsImovel.length > 0){
					//PAGINA NAO CADASTRADA 
					imoveisPorPagina.put(paginaCorrente, idsImovel);
				}
				
			}
			else if (idsImovel != null && idsImovel.length > 0){
				
				//PRIMEIRA SELECAO
				imoveisPorPagina = new HashMap<String, String[]>();
				imoveisPorPagina.put(paginaCorrente, idsImovel);
					
				sessao.setAttribute("idsImoveisJaSelecionados", imoveisPorPagina);
			}
		}
		
	}
	
	/**
	 * Capturar os imóveis selecionados pelo usuário
	 * 
	 * @author Raphael Rossiter
	 * @date 05/12/2007
	 * 
	 */
	public void capturarImoveisJaSelecionados(HttpSession sessao, HttpServletRequest httpServletRequest){
		
		String proximaPagina = httpServletRequest.getParameter("page.offset");
		
		if (proximaPagina == null){
			proximaPagina = "1";
		}
			
		HashMap<String, String[]> imoveisPorPagina = (HashMap) sessao.getAttribute("idsImoveisJaSelecionados");
			
		if (imoveisPorPagina != null && !imoveisPorPagina.isEmpty() &&
			imoveisPorPagina.containsKey(proximaPagina)){
				
			String[] idsImovel = imoveisPorPagina.get(proximaPagina);
			Collection<String> selecionados = new ArrayList();
				
			for (int i = 0; i < idsImovel.length; i++) {
				selecionados.add(idsImovel[i]);
			}
				
			httpServletRequest.setAttribute("selecionados", selecionados);
		
		}
	}

	
	/**
	 * Redirecionar para tela de análise
	 * 
	 * @author Raphael Rossiter
	 * @date 05/12/2007
	 * 
	 */
	public void redirecionarAnalise(HttpServletRequest httpServletRequest){
		
		String idRegistroAtualizacao = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if (idRegistroAtualizacao != null){
			
			String linkAnalise = httpServletRequest.getParameter("linkAnalise");
			String medicaoTipo = httpServletRequest.getParameter("medicaoTipo");
			
			linkAnalise = linkAnalise + "?idRegistroAtualizacao=" + idRegistroAtualizacao;
			linkAnalise = linkAnalise + "&medicaoTipo=" + medicaoTipo;
			
			httpServletRequest.setAttribute("telaAnalise", linkAnalise);
		}
	}
}
