package gcom.gui.atendimentopublico;


import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoMotivo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
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
 * 
 * @author Arthur Carvalho
 * @date 14/08/2008
 */
public class ExibirAtualizarLigacaoEsgotoEsgotamentoAction extends GcomAction {

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
				.findForward("ligacaoEsgotoEsgotamentoAtualizar");

		AtualizarLigacaoEsgotoEsgotamentoActionForm atualizarLigacaoEsgotoEsgotamentoActionForm = (AtualizarLigacaoEsgotoEsgotamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoPesquisa = null;
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((LigacaoEsgotoEsgotamento) sessao.getAttribute("ligacaoEsgotoEsgotamento")).getId().toString();
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

		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = new LigacaoEsgotoEsgotamento();
		
		
		if (id != null && !id.trim().equals("")) {

			FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
			filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.ID, id));
			
			filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
			filtroLigacaoEsgotoEsgotamento.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
			
			Collection colecaoLigacaoEsgotoEsgotamento = fachada.pesquisar(
					filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
			
			if (colecaoLigacaoEsgotoEsgotamento != null && !colecaoLigacaoEsgotoEsgotamento.isEmpty()) {
				ligacaoEsgotoEsgotamento= (LigacaoEsgotoEsgotamento) Util.retonarObjetoDeColecao(colecaoLigacaoEsgotoEsgotamento);
			}

		
			
			if (id != null && !id.trim().equals("")) {

				atualizarLigacaoEsgotoEsgotamentoActionForm
					.setCodigo(ligacaoEsgotoEsgotamento
						.getId().toString());

				atualizarLigacaoEsgotoEsgotamentoActionForm
					.setDescricao(ligacaoEsgotoEsgotamento
						.getDescricao());
				
				if (ligacaoEsgotoEsgotamento.getFaturamentoSituacaoTipo() != null){
					
					atualizarLigacaoEsgotoEsgotamentoActionForm
					.setFaturamentoSituacaoTipo(ligacaoEsgotoEsgotamento
							.getFaturamentoSituacaoTipo().getId().toString());
			
				} 
					
				if(ligacaoEsgotoEsgotamento.getFaturamentoSituacaoMotivo() != null){
						
					atualizarLigacaoEsgotoEsgotamentoActionForm
					.setFaturamentoSituacaoMotivo(ligacaoEsgotoEsgotamento
						.getFaturamentoSituacaoMotivo().getId().toString());
				
				} 
				
				atualizarLigacaoEsgotoEsgotamentoActionForm
					.setIndicadorUso(ligacaoEsgotoEsgotamento
						.getIndicadorUso());
				
				if (ligacaoEsgotoEsgotamento.getQuantidadeMesesSituacaoEspecial() != null){
				
					atualizarLigacaoEsgotoEsgotamentoActionForm
					.setQuantidadeMesesSituacaoEspecial(ligacaoEsgotoEsgotamento
						.getQuantidadeMesesSituacaoEspecial().toString());
				}
				
				//Tipo de Situação Especial de Faturamento
		        FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
		        
		        filtroFaturamentoSituacaoTipo.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.ID);
		        
		        filtroFaturamentoSituacaoTipo.adicionarParametro(new ParametroSimples(
		        		FiltroFaturamentoSituacaoTipo.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna tipo de situacao especial de faturamento
		        colecaoPesquisa = this.getFachada().pesquisar(filtroFaturamentoSituacaoTipo,
		        		FaturamentoSituacaoTipo.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Tipo de Situação Especial de Faturamento");
		        } else {
		            httpServletRequest.setAttribute("colecaoFaturamentoSituacaoTipo", colecaoPesquisa);
		        }
		        
		        //Motivo de Situação Especial de Faturamento
		        FiltroFaturamentoSituacaoMotivo filtroFaturamentoSituacaoMotivo = new FiltroFaturamentoSituacaoMotivo();
		        
		        filtroFaturamentoSituacaoMotivo.setCampoOrderBy(FiltroLigacaoEsgotoEsgotamento.ID);
		        
		        filtroFaturamentoSituacaoMotivo.adicionarParametro(new ParametroSimples(
		        		FiltroFaturamentoSituacaoMotivo.INDICADOR_USO,
		        		ConstantesSistema.INDICADOR_USO_ATIVO));
		       
		        //Retorna Motivo de situacao especial de faturamento
		        colecaoPesquisa = this.getFachada().pesquisar(filtroFaturamentoSituacaoMotivo,
		        		FaturamentoSituacaoMotivo.class.getName());
		        
		        if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            //Nenhum registro na tabela leitura_anormalidade_consumo foi encontrado
		            throw new ActionServletException(
		                    "atencao.pesquisa.nenhum_registro_tabela", null,
		                    "Motivo da Situação Especial de Faturamento");
		        } else {
		            httpServletRequest.setAttribute("colecaoFaturamentoSituacaoMotivo", colecaoPesquisa);
		        }
		        
			}
			
			
			
			
			sessao.setAttribute("atualizarLigacaoEsgotoEsgotamento", ligacaoEsgotoEsgotamento);

			if (sessao.getAttribute("colecaoLigacaoEsgotoEsgotamento") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarLigacaoEsgotoEsgotamentoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarLigacaoEsgotoEsgotamentoAction.do");
			}

		}
		

		return retorno;
	}
	
	
}
