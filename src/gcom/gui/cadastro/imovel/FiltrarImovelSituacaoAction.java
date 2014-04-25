package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelSituacao;
import gcom.cadastro.imovel.ImovelSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0037] Filtrar Situação do Imóvel Tem o objetivo de filtrar as situacoes de
 * imovel existentes
 * 
 * @author Rômulo Aurélio
 * @date 31/03/2006
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */
public class FiltrarImovelSituacaoAction extends GcomAction {

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
				.findForward("exibirManterImovelSituacaoAction");

		// Recebe a sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarImovelSituacaoActionForm filtrarImovelSituacaoActionForm = (FiltrarImovelSituacaoActionForm) actionForm;

		FiltroImovelSituacao filtroImovelSituacao = new FiltroImovelSituacao();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		// Recebe os dados do ActionForm

		String idImovelSituacaoTipo = filtrarImovelSituacaoActionForm
				.getImovelSituacaoTipo();
		String idLigacaoAguaSituacao = filtrarImovelSituacaoActionForm
				.getLigacaoAguaSituacao();
		String idLigacaoEsgotoSituacao = filtrarImovelSituacaoActionForm
				.getLigacaoEsgotoSituacao();

		// [FS0002] Verificar preenchimento dos campos

		if (idImovelSituacaoTipo != null && !idImovelSituacaoTipo.equals("-1")) {
			peloMenosUmParametroInformado = true;

			filtroImovelSituacao.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacao.SITUACAO_TIPO, idImovelSituacaoTipo));

		}
		if (idLigacaoAguaSituacao != null
				&& !idLigacaoAguaSituacao.equals("-1")) {
			peloMenosUmParametroInformado = true;
			filtroImovelSituacao.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacao.SITUACAO_AGUA_ID,
					idLigacaoAguaSituacao));
		}
		if (idLigacaoEsgotoSituacao != null
				&& !idLigacaoEsgotoSituacao.equals("-1")) {
			peloMenosUmParametroInformado = true;
			filtroImovelSituacao.adicionarParametro(new ParametroSimples(
					FiltroImovelSituacao.SITUACAO_ESCGOTO_ID,
					idLigacaoEsgotoSituacao));

		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro

		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Coloca os dados do filtro na colecao

		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovelSituacao
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

		Collection colecaoImovelSituacao = (Collection) fachada.pesquisar(
				filtroImovelSituacao, ImovelSituacao.class.getName());

		/*if (colecaoImovelSituacao == null || colecaoImovelSituacao.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"situação do imóvel");
		}*/

		if (colecaoImovelSituacao == null || colecaoImovelSituacao.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		
		// Coloca os dados da colecao na sessao

		sessao.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);

		// Manda o filtro pelo sessao para o
		// ExibirManterImovelSItuacaoAction

		sessao.setAttribute("filtroImovelSituacao", filtroImovelSituacao);

		httpServletRequest.setAttribute("filtroImovelSituacao",
				filtroImovelSituacao);

		return retorno;

	}
}
