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
