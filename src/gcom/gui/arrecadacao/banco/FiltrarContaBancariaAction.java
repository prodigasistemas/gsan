package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class FiltrarContaBancariaAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
	 * 
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
				.findForward("exibirManterContaBancaria");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarContaBancariaActionForm filtrarContaBancariaActionForm = (FiltrarContaBancariaActionForm) actionForm;

		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();

		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String banco = filtrarContaBancariaActionForm.getBanco();

		String agenciaBancaria = filtrarContaBancariaActionForm.getAgenciaBancaria();

		String contaBanco = filtrarContaBancariaActionForm.getContaBanco();

		
		// Verifica se o campo Referência do Tipo de Serviço foi informado

		if (banco != null
				&& !banco.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.AGENCIA_BANCO_ID, banco));

		}
		
		
		// Verifica se o campo Referência do Tipo de Serviço foi informado

		if (agenciaBancaria != null
				&& !agenciaBancaria.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroContaBancaria.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.AGENCIA_ID, agenciaBancaria));

		}
		
		
		// Verifica se o campo Código foi informado

		if (contaBanco != null && !contaBanco.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroContaBancaria.adicionarParametro(new ComparacaoTexto(
					FiltroContaBancaria.NUMERO_CONTA, contaBanco));

		}


		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		sessao.setAttribute("filtroContaBancaria", filtroContaBancaria);

		return retorno;
	}

}
