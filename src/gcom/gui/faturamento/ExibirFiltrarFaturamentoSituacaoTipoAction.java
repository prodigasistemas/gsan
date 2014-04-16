package gcom.gui.faturamento;



import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**			
 * @date 21/08/08
 * @author Arthur Carvalho
 */

public class ExibirFiltrarFaturamentoSituacaoTipoAction extends GcomAction {
	
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
				.findForward("filtrarFaturamentoSituacaoTipo");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarFaturamentoSituacaoTipoActionForm filtrarFaturamentoSituacaoTipoActionForm = (FiltrarFaturamentoSituacaoTipoActionForm) actionForm;

		Collection colecaoPesquisa = null;
		
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
            httpServletRequest.setAttribute("colecaoAnormalidadeLeituraFaturarSemLeitura", colecaoPesquisa);
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
            httpServletRequest.setAttribute("colecaoAnormalidadeLeituraFaturarComLeitura", colecaoPesquisa);
        }
		
		
		
		String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarFaturamentoSituacaoTipoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}			

		if(filtrarFaturamentoSituacaoTipoActionForm.getIndicadorAtualizar()==null){
			filtrarFaturamentoSituacaoTipoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	
        	filtrarFaturamentoSituacaoTipoActionForm.setId("");
        	filtrarFaturamentoSituacaoTipoActionForm.setDescricao("");
        	filtrarFaturamentoSituacaoTipoActionForm.setIndicadorUso("");
        	filtrarFaturamentoSituacaoTipoActionForm.setIndicadorInformarConsumo("");
        	filtrarFaturamentoSituacaoTipoActionForm.setIndicadorInformarVolume("");
        	filtrarFaturamentoSituacaoTipoActionForm.setIndicadorParalisacaoFaturamento("");
        	filtrarFaturamentoSituacaoTipoActionForm.setIndicadorParalisacaoLeitura("");
        	filtrarFaturamentoSituacaoTipoActionForm.setIndicadorValidoAgua("");
        	filtrarFaturamentoSituacaoTipoActionForm.setIndicadorValidoEsgoto("");
        	filtrarFaturamentoSituacaoTipoActionForm.setLeituraAnormalidadeConsumoComLeitura("-1");
        	filtrarFaturamentoSituacaoTipoActionForm.setLeituraAnormalidadeConsumoSemLeitura("-1");
        	filtrarFaturamentoSituacaoTipoActionForm.setLeituraAnormalidadeLeituraComLeitura("-1");
        	filtrarFaturamentoSituacaoTipoActionForm.setLeituraAnormalidadeLeituraSemLeitura("-1");
        }
       return retorno;

	}

}
