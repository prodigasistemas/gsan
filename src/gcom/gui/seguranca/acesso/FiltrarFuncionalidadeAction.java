package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
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
 * [UC0283] Filtrar Funcionalidade
 * 
 * @author Rômulo Aurélio
 * @date 12/05/2006
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */

public class FiltrarFuncionalidadeAction extends GcomAction {

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

		ActionForward retorno = actionMapping
				.findForward("exibirManterFuncionalidadeAction");

		FiltrarFuncionalidadeActionForm filtrarFuncionalidadeActionForm = (FiltrarFuncionalidadeActionForm) actionForm;

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		boolean peloMenosUmParametroInformado = false;

		String codigo = filtrarFuncionalidadeActionForm.getCodigo();

		String descricao = filtrarFuncionalidadeActionForm.getDescricao();

		String idModulo = filtrarFuncionalidadeActionForm.getModulo();

		String Pentrada = filtrarFuncionalidadeActionForm
				.getIndicadorPontoEntrada();

		// Verifica se o campo codigo foi informado

		if (codigo != null && !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.ID, codigo));

		}

		// Verifica se o campo descricao foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
					FiltroFuncionalidade.DESCRICAO, descricao));

		}

		// Verifica se o campo modulo foi informado

		if (idModulo != null
				&& !idModulo.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.MODULO_ID, idModulo));

		}

		// Verifica se o campo Ponto de Entrada foi informado

		if (Pentrada != null
				&& !Pentrada.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			peloMenosUmParametroInformado = true;

			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA, Pentrada));

		}
		

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		
		
//		filtroFuncionalidade
//				.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.ID);
//
//		filtroFuncionalidade
//				.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.DESCRICAO);

		//filtroFuncionalidade
			//	.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.MODULO);

//		filtroFuncionalidade
//				.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA);
		
		
		
		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterFuncionalidadeAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (filtrarFuncionalidadeActionForm.getAtualizar() != null
				&& filtrarFuncionalidadeActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarFuncionalidadeActionForm.getAtualizar());
			
			
		}
		
		
		// Manda o filtro pelo sessao para o
		// ExibirFuncionalidadeAction

		sessao.setAttribute("filtroFuncionalidade", filtroFuncionalidade);

		httpServletRequest.setAttribute("filtroFuncionalidade", filtroFuncionalidade);
		

		return retorno;

	}

}
