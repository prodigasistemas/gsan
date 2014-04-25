package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroPesquisarOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe 
 *
 * @author Administrador
 * @date 05/05/2006
 */
public class PesquisarOperacaoAction extends GcomAction {
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
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("listaPesquisarOperacaoResultado");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarOperacaoActionForm pesquisarOperacaoActionForm = (PesquisarOperacaoActionForm) actionForm;

		// Recupera os parâmetros do form
		String idOperacao = pesquisarOperacaoActionForm.getCodigoOperacao();
		String nomeOperacao = pesquisarOperacaoActionForm.getNomeOperacao();
		String tipoOperacao = pesquisarOperacaoActionForm.getTipoOperacao();
		String idFuncionalidade = pesquisarOperacaoActionForm
				.getIdFuncionalidade();

		// filtro para a pesquisa de endereco do cliente
		FiltroPesquisarOperacao filtroPesquisarOperacao = new FiltroPesquisarOperacao();

		filtroPesquisarOperacao.setCampoOrderBy(FiltroOperacao.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro
		if (idOperacao != null && !idOperacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
					FiltroPesquisarOperacao.ID, idOperacao));
		}

		if (nomeOperacao != null && !nomeOperacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ComparacaoTexto(
					FiltroPesquisarOperacao.DESCRICAO, nomeOperacao));
		}

		if (tipoOperacao != null
				&& !tipoOperacao.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
					FiltroPesquisarOperacao.OPERACAO_TIPO, tipoOperacao));
		}

		if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
					FiltroPesquisarOperacao.FUNCIONALIDADE, idFuncionalidade));
		}

		// if (idfuncionalidade != null && !idfuncionalidade.trim().equals(""))
		// {
		// peloMenosUmParametroInformado = true;
		// filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
		// FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idfuncionalidade));
		// }

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroPesquisarOperacao
				.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroPesquisarOperacao
				.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroPesquisarOperacao
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");

		Collection colecaoPesquisarOperacao = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// pesquisa os endereços do cliente
		colecaoPesquisarOperacao = fachada.pesquisar(filtroPesquisarOperacao,
				Operacao.class.getName());

		if (colecaoPesquisarOperacao == null
				|| colecaoPesquisarOperacao.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Operação");
		} else if (colecaoPesquisarOperacao.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("colecaoPesquisarOperacao",
					colecaoPesquisarOperacao);

		}

		return retorno;
	}

}
