package gcom.gui.util.tabelaauxiliar.faixa;

import gcom.gui.ActionServletException;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.faixa.FiltroTabelaAuxiliarFaixaInteiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * @author Rômulo Aurélio
 *
 */
public class FiltrarTabelaAuxiliarFaixaInteiroAction extends Action {
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
				.findForward("retornarFiltroTabelaAuxiliarFaixaInteiro");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		//Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String menorFaixa = (String) pesquisarActionForm.get("menorFaixa");
		String maiorFaixa = (String) pesquisarActionForm
				.get("maiorFaixa");
		String atualizar = (String) pesquisarActionForm.get("atualizar");

		//cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarFaixaInteiro filtroTabelaAuxiliarFaixaInteiro = new FiltroTabelaAuxiliarFaixaInteiro();

		//Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			filtroTabelaAuxiliarFaixaInteiro
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarFaixaInteiro.ID, id));
		}
		
		
		if (menorFaixa != null
				&& !menorFaixa.trim().equalsIgnoreCase("")) {
			
			filtroTabelaAuxiliarFaixaInteiro
			.adicionarParametro(new MaiorQue(
					FiltroTabelaAuxiliarFaixaInteiro.MENOR_FAIXA, menorFaixa));
		}
		
		if (maiorFaixa != null
				&& !maiorFaixa.trim().equalsIgnoreCase("")) {
			
			filtroTabelaAuxiliarFaixaInteiro
			.adicionarParametro(new MenorQue(
					FiltroTabelaAuxiliarFaixaInteiro.MAIOR_FAIXA, maiorFaixa));
		}
		
		if (menorFaixa != null
				&& !menorFaixa.trim().equalsIgnoreCase("")
				&& maiorFaixa != null
				&& !maiorFaixa.trim().equalsIgnoreCase("")) {
			if (new Integer(menorFaixa).intValue() > new Integer(
					maiorFaixa)) {
				throw new ActionServletException("atencao.menor_faixa_superior_maior_faixa");
			}
		}
		
		
		if (atualizar != null && atualizar.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}

		//Manda o filtro pelo request para o
		// ExibirManterTabelaAuxiliarAbreviada
		httpServletRequest.setAttribute("filtroTabelaAuxiliarFaixaInteiro",
				filtroTabelaAuxiliarFaixaInteiro);
		

		//Devolve o mapeamento de retorno
		return retorno;
	}
}
