package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltrarItemServicoActionForm;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * FILTRAR 
 * 
 * @author Rodrigo Cabral
 * @date 04/08/2010
 */

public class FiltrarItemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterItemServico");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarItemServicoActionForm form = (FiltrarItemServicoActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente

		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String indicadorUso = form.getIndicadorUso();
		String codigoConstanteCalculo = form.getCodigoConstanteCalculo();
		String codigoItem = form.getCodigoItem();
		String tipoPesquisa = form.getTipoPesquisa();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {

			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			
				sessao.removeAttribute("indicadorAtualizar");
			
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroItemServico filtroItemServico = new FiltroItemServico();
		
		
		//Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroItemServico
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroItemServico.DESCRICAO, descricao));
			} else {

				filtroItemServico.adicionarParametro(new ComparacaoTexto(
						FiltroItemServico.DESCRICAO, descricao));
			}
		}
		
		//Descrição Abreviada
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroItemServico
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroItemServico.DESCRICAO_ABREVIADA, descricaoAbreviada));
			} else {

				filtroItemServico.adicionarParametro(new ComparacaoTexto(
						FiltroItemServico.DESCRICAO_ABREVIADA, descricaoAbreviada));
			}
		}
		
		//Indicador de Uso
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            if (indicadorUso.equalsIgnoreCase(String
                    .valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
            	filtroItemServico.adicionarParametro(new ParametroSimples(
                		FiltroItemServico.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
            } else {
            	filtroItemServico.adicionarParametro(new ParametroSimples(
            			FiltroItemServico.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_DESATIVO));
            }
        }
		
		//Codigo Constante de Calculo
		if (codigoConstanteCalculo != null && !codigoConstanteCalculo.equals("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroItemServico
						.adicionarParametro(new ParametroSimples(
								FiltroItemServico.CODIGO_CONSTANTE_CALCULO, codigoConstanteCalculo));
			} else {

				filtroItemServico.adicionarParametro(new ParametroSimples(
						FiltroItemServico.CODIGO_CONSTANTE_CALCULO, codigoConstanteCalculo));
			}
		}
		
		//Codigo Item
		if (codigoItem != null && !codigoItem.equals("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroItemServico
						.adicionarParametro(new ParametroSimples(
								FiltroItemServico.CODIGO_ITEM, codigoItem));
			} else {

				filtroItemServico.adicionarParametro(new ParametroSimples(
						FiltroItemServico.CODIGO_ITEM, codigoItem));
			}
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		filtroItemServico.setCampoOrderBy(FiltroItemServico.ID);

		Collection<ItemServico> colecaoItemServico = fachada.pesquisar(
				filtroItemServico, ItemServico.class.getName());

		if (colecaoItemServico == null || colecaoItemServico.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Item de Contrato");
		} else {
			httpServletRequest.setAttribute("colecaoItemServico",
					colecaoItemServico);
			ItemServico itemServico = new ItemServico();
			itemServico = (ItemServico) Util
					.retonarObjetoDeColecao(colecaoItemServico);
			String idRegistroAtualizacao = ""+itemServico.getDescricao();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroItemServico", filtroItemServico);

		httpServletRequest.setAttribute("filtroItemServico", filtroItemServico);

		return retorno;

	}
}
