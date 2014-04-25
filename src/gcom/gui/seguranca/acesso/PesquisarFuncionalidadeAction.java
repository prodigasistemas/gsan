package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.ConstantesSistema;
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
 * [UC0282] Pesquisar Funcionalidade
 * 
 * @author Rômulo Aurélio
 * @date 10/05/2006
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class PesquisarFuncionalidadeAction extends GcomAction {

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
				.findForward("exibirResultadoPesquisaFuncionalidadeAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarFuncionalidadeActionForm pesquisarFuncionalidadeActionForm = (PesquisarFuncionalidadeActionForm) actionForm;

		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		
		boolean peloMenosUmParametroInformado = false;

		String codigo = pesquisarFuncionalidadeActionForm.getCodigo();
		String descricao = pesquisarFuncionalidadeActionForm.getDescricao();
		String idModulo = pesquisarFuncionalidadeActionForm.getModulo();
		String Pentrada = pesquisarFuncionalidadeActionForm.getIndicadorPontoEntrada();
		String indicadorRegistraTransacao = pesquisarFuncionalidadeActionForm.getIndicadorRegistraTransacao();

		
		String tipoPesquisa = pesquisarFuncionalidadeActionForm.getTipoPesquisa();
		// Verifica se o campo codigo foi informado

		if (codigo != null
				&& !codigo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroFuncionalidade.adicionarParametro(new ParametroSimples(
					FiltroFuncionalidade.ID, codigo));

		}

		// Verifica se o campo descricao foi informado

				
		
		if (descricao != null
				&& !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			 if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {

					filtroFuncionalidade
							.adicionarParametro(new ComparacaoTextoCompleto(
									FiltroFuncionalidade.DESCRICAO, descricao));
				} else {

					filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
							FiltroFuncionalidade.DESCRICAO, descricao));
				}

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
		
		
		if (indicadorRegistraTransacao != null && 
			!indicadorRegistraTransacao.trim().equals("")) {

			filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(
					FiltroFuncionalidade.OPERACOES_INDICADOR_REGISTRA_TRANSACAO, ConstantesSistema.SIM));
		}

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("modulo");
		
		Collection colecaoFuncionalidade = 
			(Collection) this.getFachada().pesquisar(
				filtroFuncionalidade, Funcionalidade.class.getName());

		if (colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Funcionalidade");
		}

		sessao.setAttribute("colecaoFuncionalidade", colecaoFuncionalidade);
		
		return retorno;

	}

}
