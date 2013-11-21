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
package gcom.gui.faturamento;




import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
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
 * 
 * @author Arthur Carvalho
 * @date 21/08/2008
 */
public class ExibirAtualizarFaturamentoSituacaoTipoAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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

		ActionForward retorno = actionMapping
				.findForward("faturamentoSituacaoTipoAtualizar");

		AtualizarFaturamentoSituacaoTipoActionForm atualizarFaturamentoSituacaoTipoActionForm = (AtualizarFaturamentoSituacaoTipoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoPesquisa = null;
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((FaturamentoSituacaoTipo) sessao.getAttribute("faturamentoSituacaoTipo")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		FaturamentoSituacaoTipo faturamentoSituacaoTipo= new FaturamentoSituacaoTipo();
						
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo= new FiltroFaturamentoSituacaoTipo();
			filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoSituacaoTipo.ID, id));
			Collection colecaoFaturamentoSituacaoTipo = fachada.pesquisar(
					filtroFaturamentoSituacaoTipo, FaturamentoSituacaoTipo.class.getName());
			if (colecaoFaturamentoSituacaoTipo != null
					&& !colecaoFaturamentoSituacaoTipo.isEmpty()) {
				faturamentoSituacaoTipo= (FaturamentoSituacaoTipo) Util
						.retonarObjetoDeColecao(colecaoFaturamentoSituacaoTipo);
			}
			
			
			if (id != null && !id.trim().equals("")) {

				atualizarFaturamentoSituacaoTipoActionForm.setCodigo(faturamentoSituacaoTipo
						.getId().toString());

				atualizarFaturamentoSituacaoTipoActionForm
						.setDescricao(faturamentoSituacaoTipo.getDescricao());

				atualizarFaturamentoSituacaoTipoActionForm
						.setIndicadorInformarConsumo(faturamentoSituacaoTipo
								.getIndicadorInformarConsumo().toString());

				atualizarFaturamentoSituacaoTipoActionForm
						.setIndicadorInformarVolume(faturamentoSituacaoTipo
								.getIndicadorInformarVolume().toString());

				atualizarFaturamentoSituacaoTipoActionForm
						.setIndicadorParalisacaoFaturamento(faturamentoSituacaoTipo
								.getIndicadorParalisacaoFaturamento().toString());

				atualizarFaturamentoSituacaoTipoActionForm
						.setIndicadorParalisacaoLeitura(faturamentoSituacaoTipo
								.getIndicadorParalisacaoLeitura().toString());
				
				atualizarFaturamentoSituacaoTipoActionForm
						.setIndicadorValidoAgua(faturamentoSituacaoTipo
								.getIndicadorValidoAgua().toString());
				
				atualizarFaturamentoSituacaoTipoActionForm
						.setIndicadorValidoEsgoto(faturamentoSituacaoTipo
								.getIndicadorValidoEsgoto().toString());
				
				atualizarFaturamentoSituacaoTipoActionForm
						.setLeituraAnormalidadeConsumoComLeitura( faturamentoSituacaoTipo
								.getLeituraAnormalidadeConsumoComLeitura().getId()+"" );
				
				atualizarFaturamentoSituacaoTipoActionForm
						.setLeituraAnormalidadeConsumoSemLeitura( faturamentoSituacaoTipo
								.getLeituraAnormalidadeConsumoSemLeitura().getId()+"" );
				
				atualizarFaturamentoSituacaoTipoActionForm
						.setLeituraAnormalidadeLeituraComLeitura( faturamentoSituacaoTipo
								.getLeituraAnormalidadeLeituraComLeitura().getId()+"" );
				
				atualizarFaturamentoSituacaoTipoActionForm
						.setLeituraAnormalidadeLeituraSemLeitura( faturamentoSituacaoTipo
								.getLeituraAnormalidadeLeituraSemLeitura().getId()+"" );
				
				//Anormalidade de Consumo Cobrar Sem Leitura
		        FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		        
		        filtroLeituraAnormalidadeConsumo.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);
		        
		        filtroLeituraAnormalidadeConsumo.adicionarParametro(new ParametroSimples(
		        		FiltroLeituraAnormalidadeConsumo.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna local anormalidade de consumo cobrar sem leitura
		        colecaoPesquisa = this.getFachada().pesquisar(filtroLeituraAnormalidadeConsumo,
		                LeituraAnormalidadeConsumo.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Anormalidade de Consumo Cobrar Sem Leitura");
		        } else {
		            httpServletRequest.setAttribute("colecaoAnormalidadeConsumoSemLeitura", colecaoPesquisa);
		        }

		        //Anormalidade de Consumo Cobrar Com Leitura
		        FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumoComLeitura = new FiltroLeituraAnormalidadeConsumo();
		        
		        filtroLeituraAnormalidadeConsumoComLeitura.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);
		        
		        filtroLeituraAnormalidadeConsumoComLeitura.adicionarParametro(new ParametroSimples(
		        		FiltroLeituraAnormalidadeConsumo.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna local anormalidade de consumo cobrar com leitura
		        colecaoPesquisa = this.getFachada().pesquisar(filtroLeituraAnormalidadeConsumoComLeitura,
		                LeituraAnormalidadeConsumo.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Anormalidade de Consumo Cobrar Com Leitura");
		        } else {
		            httpServletRequest.setAttribute("colecaoAnormalidadeConsumoComLeitura", colecaoPesquisa);
		        }

		        //Anormalidade de leitura faturar sem leitura
		        FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeSemLeitura = new FiltroLeituraAnormalidadeLeitura();
		        
		        filtroLeituraAnormalidadeSemLeitura.setCampoOrderBy(FiltroLeituraAnormalidadeLeitura.ID);
		        
		        filtroLeituraAnormalidadeSemLeitura.adicionarParametro(new ParametroSimples(
		        		FiltroLeituraAnormalidadeLeitura.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna local anormalidade de leitura faturar sem leitura
		        colecaoPesquisa = this.getFachada().pesquisar(filtroLeituraAnormalidadeSemLeitura,
		                LeituraAnormalidadeLeitura.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_leitura foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Anormalidade de Leitura Faturar Sem Leitura");
		        } else {
		            httpServletRequest.setAttribute("colecaoAnormalidadeLeituraSemLeitura", colecaoPesquisa);
		        }
		        
		        //Anormalidade de leitura faturar com leitura
		        FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeComLeitura = new FiltroLeituraAnormalidadeLeitura();
		        
		        filtroLeituraAnormalidadeComLeitura.setCampoOrderBy(FiltroLeituraAnormalidadeLeitura.ID);
		        
		        filtroLeituraAnormalidadeSemLeitura.adicionarParametro(new ParametroSimples(
		        		FiltroLeituraAnormalidadeLeitura.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna local anormalidade de leitura faturar sem leitura
		        colecaoPesquisa = this.getFachada().pesquisar(filtroLeituraAnormalidadeComLeitura,
		                LeituraAnormalidadeLeitura.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_leitura foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Anormalidade de Leitura Faturar Com Leitura");
		        } else {
		            httpServletRequest.setAttribute("colecaoAnormalidadeLeituraComLeitura", colecaoPesquisa);
		        }


			}
			
			atualizarFaturamentoSituacaoTipoActionForm.setCodigo(faturamentoSituacaoTipo.getId().toString());
			
			atualizarFaturamentoSituacaoTipoActionForm.setDescricao(faturamentoSituacaoTipo.getDescricao());
			
			atualizarFaturamentoSituacaoTipoActionForm.setIndicadorInformarConsumo(faturamentoSituacaoTipo.getIndicadorInformarConsumo().toString());
			
			atualizarFaturamentoSituacaoTipoActionForm.setIndicadorInformarVolume(faturamentoSituacaoTipo.getIndicadorInformarVolume().toString());
			
			atualizarFaturamentoSituacaoTipoActionForm.setIndicadorParalisacaoFaturamento(faturamentoSituacaoTipo.getIndicadorParalisacaoFaturamento().toString());
			
			atualizarFaturamentoSituacaoTipoActionForm.setIndicadorUso(faturamentoSituacaoTipo.getIndicadorUso().toString());

			atualizarFaturamentoSituacaoTipoActionForm.setIndicadorParalisacaoLeitura(faturamentoSituacaoTipo.getIndicadorParalisacaoLeitura().toString());
			
			atualizarFaturamentoSituacaoTipoActionForm.setIndicadorValidoAgua(faturamentoSituacaoTipo.getIndicadorValidoEsgoto().toString());
			
			atualizarFaturamentoSituacaoTipoActionForm.setIndicadorValidoEsgoto(faturamentoSituacaoTipo.getIndicadorValidoEsgoto().toString());
			
			sessao.setAttribute("atualizarFaturamentoSituacaoTipo", faturamentoSituacaoTipo);

			if (sessao.getAttribute("colecaoFaturamentoSituacaoTipo") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarFaturamentoSituacaoTipoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarFaturamentoSituacaoTipoAction.do");
			}

		}

		return retorno;
	}
}