package gcom.gui.atendimentopublico;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FiltroFaturamentoSituacaoMotivo;
import gcom.faturamento.FiltroFaturamentoSituacaoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;


import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;


/**			
 * @date 25/08/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarLigacaoEsgotoEsgotamentoAction extends GcomAction {
	
	/*
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarLigacaoEsgotoEsgotamento");

		Collection colecaoPesquisa = null;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarLigacaoEsgotoEsgotamentoActionForm filtrarLigacaoEsgotoEsgotamentoActionForm = (FiltrarLigacaoEsgotoEsgotamentoActionForm) actionForm;
		
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
        
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarLigacaoEsgotoEsgotamentoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		
		if(filtrarLigacaoEsgotoEsgotamentoActionForm.getIndicadorAtualizar()==null){
			filtrarLigacaoEsgotoEsgotamentoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarLigacaoEsgotoEsgotamentoActionForm.setId("");
        	filtrarLigacaoEsgotoEsgotamentoActionForm.setFaturamentoSituacaoMotivo("");
        	filtrarLigacaoEsgotoEsgotamentoActionForm.setDescricao("");
        	filtrarLigacaoEsgotoEsgotamentoActionForm.setIndicadorUso("");
        	filtrarLigacaoEsgotoEsgotamentoActionForm.setQuantidadeMesesSituacaoEspecial("");
        	filtrarLigacaoEsgotoEsgotamentoActionForm.setFaturamentoSituacaoTipo("");
        	
        }
       return retorno;

	}

}
