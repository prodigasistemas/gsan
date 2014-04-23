package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.indicador.FiltroTabelaAuxiliarIndicador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * @author Rômulo Aurelio
 * 
 */
public class FiltrarTabelaAuxiliarIndicadorAction extends Action {
	/**
	 * < <Descrição do método>>
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroTabelaAuxiliarIndicador");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String indicadorNegocio = (String) pesquisarActionForm
				.get("indicadorNegocio");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
		String atualizar = (String) pesquisarActionForm.get("atualizar");

		// cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarIndicador filtroTabelaAuxiliarIndicador = new FiltroTabelaAuxiliarIndicador();

		// Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			filtroTabelaAuxiliarIndicador
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarIndicador.ID, id));
		}

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			filtroTabelaAuxiliarIndicador
					.adicionarParametro( new ComparacaoTexto(
							FiltroTabelaAuxiliarIndicador.DESCRICAO, descricao));
		}

		if (indicadorNegocio != null && !indicadorNegocio.trim().equalsIgnoreCase("")) {

			filtroTabelaAuxiliarIndicador
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarIndicador.INDICADOR_BAIXA_RENDA,
							indicadorNegocio));
		}

		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {

			filtroTabelaAuxiliarIndicador
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarIndicador.INDICADORUSO,
							indicadorUso));
		}

		if (atualizar != null && atualizar.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}

		// Manda o filtro pelo request para o
		// ExibirManterTabelaAuxiliarAbreviada
		httpServletRequest.setAttribute("filtroTabelaAuxiliarIndicador",
				filtroTabelaAuxiliarIndicador);

		// Devolve o mapeamento de retorno
		return retorno;
	}
}
