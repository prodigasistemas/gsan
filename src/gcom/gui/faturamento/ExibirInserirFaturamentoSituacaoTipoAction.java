package gcom.gui.faturamento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


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
import gcom.util.filtro.ParametroSimples;

/**
 * @author Arthur Carvalho
 * @created 19 de agosto de 2008
 */
public class ExibirInserirFaturamentoSituacaoTipoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirFaturamentoSituacaoTipo");

		InserirFaturamentoSituacaoTipoActionForm inserirFaturamentoSituacaoTipoActionForm = (InserirFaturamentoSituacaoTipoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoPesquisa = null;

		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFaturamentoSituacaoTipoActionForm.setIndicadorInformarVolume(new Short("2"));
		}
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFaturamentoSituacaoTipoActionForm.setIndicadorInformarConsumo(new Short("2"));
		}
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFaturamentoSituacaoTipoActionForm.setIndicadorParalisacaoFaturamento(new Short("2"));
		}
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFaturamentoSituacaoTipoActionForm.setIndicadorParalisacaoLeitura(new Short("2"));
		}
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFaturamentoSituacaoTipoActionForm.setIndicadorUso(new Short("2"));
		}
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFaturamentoSituacaoTipoActionForm.setIndicadorValidoAgua(new Short("2"));
		}
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFaturamentoSituacaoTipoActionForm.setIndicadorValidoEsgoto(new Short("2"));
		}
		
		
		
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
		
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirFaturamentoSituacaoTipoActionForm.setDescricao("");

			if (inserirFaturamentoSituacaoTipoActionForm.getDescricao() == null
					|| inserirFaturamentoSituacaoTipoActionForm
							.getDescricao().equals("")) {


				FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

				filtroFaturamentoSituacaoTipo
						.setCampoOrderBy(FiltroFaturamentoSituacaoTipo.ID);

				colecaoPesquisa = fachada.pesquisar(
						filtroFaturamentoSituacaoTipo,
						FaturamentoSituacaoTipo.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Tipo de Situação de Faturamento");
				} else {
					sessao.setAttribute("colecaoFaturamentoSituacaoTipo",
							colecaoPesquisa);
				}

				// Coleção do tipo de situacao do faturamento
				filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();
				
				filtroFaturamentoSituacaoTipo
						.setCampoOrderBy(FiltroFaturamentoSituacaoTipo.ID);

				Collection colecaoFaturamentoSituacaoTipo = fachada
						.pesquisar(filtroFaturamentoSituacaoTipo,
								FaturamentoSituacaoTipo.class.getName());
				sessao.setAttribute("colecaoFaturamentoSituacaoTipo",
						colecaoFaturamentoSituacaoTipo);

			}

		}
		return retorno;
	}
}
