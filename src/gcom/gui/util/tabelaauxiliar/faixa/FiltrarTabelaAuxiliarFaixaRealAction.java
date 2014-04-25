package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.gui.ActionServletException;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaReal;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * @author Romulo Aurelio
 *
 */
public class FiltrarTabelaAuxiliarFaixaRealAction extends Action {
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

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroTabelaAuxiliarFaixaReal");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		//Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String volumeMenorFaixa = (String) pesquisarActionForm
				.get("volumeMenorFaixa");
		String volumeMaiorFaixa = (String) pesquisarActionForm
				.get("volumeMaiorFaixa");
		String atualizar = (String) pesquisarActionForm.get("atualizar");

		//cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarFaixaReal filtroTabelaAuxiliarFaixaReal = new FiltroTabelaAuxiliarFaixaReal();

		//Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			filtroTabelaAuxiliarFaixaReal
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarFaixaReal.ID, id));
		}

		BigDecimal volumeMenorFaixaReal = new BigDecimal(0);
		
		if (volumeMenorFaixa != null
				&& !volumeMenorFaixa.trim().equalsIgnoreCase("")) {


			String valorAux = volumeMenorFaixa.toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			volumeMenorFaixaReal = new BigDecimal(valorAux);

			filtroTabelaAuxiliarFaixaReal.adicionarParametro(new MaiorQue(
					FiltroTabelaAuxiliarFaixaReal.VOLUME_MENOR_FAIXA,
					volumeMenorFaixaReal));
		}

		if (volumeMaiorFaixa != null
				&& !volumeMaiorFaixa.trim().equalsIgnoreCase("")) {

			BigDecimal volumeMaiorFaixaReal = new BigDecimal(0);

			String valorAux = volumeMaiorFaixa.toString().replace(".", "");

			valorAux = valorAux.replace(",", ".");

			volumeMaiorFaixaReal = new BigDecimal(valorAux);
			
			
			if (volumeMaiorFaixaReal != null					
					&& volumeMenorFaixaReal != null) {
				if (volumeMenorFaixaReal.compareTo(volumeMaiorFaixaReal) > 0) {
					throw new ActionServletException("atencao.volume_menor_faixa_superior_maior_faixa");
				}
			}

			filtroTabelaAuxiliarFaixaReal.adicionarParametro(new MenorQue(
					FiltroTabelaAuxiliarFaixaReal.VOLUME_MAIOR_FAIXA,
					volumeMaiorFaixaReal));
		}

		

		if (atualizar != null && atualizar.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}

		//Manda o filtro pelo request para o
		// ExibirManterTabelaAuxiliarAbreviada
		httpServletRequest.setAttribute("filtroTabelaAuxiliarFaixaReal",
				filtroTabelaAuxiliarFaixaReal);

		//Devolve o mapeamento de retorno
		return retorno;
	}
}
