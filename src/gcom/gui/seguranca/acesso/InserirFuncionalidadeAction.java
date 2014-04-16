package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Modulo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * @author Rômulo Aurélio
 * @date 25/04/2006
 */
public class InserirFuncionalidadeAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma nova Funcionalidade
	 * 
	 * [UC0280] Inserir Funcionalidade
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 25/04/2006
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirFuncionalidadeActionForm inserirFuncionalidadeActionForm = (InserirFuncionalidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtendo dados do form e setando no Objeto Funcionalidade

		Funcionalidade funcionalidade = new Funcionalidade();

		String idModulo = inserirFuncionalidadeActionForm.getModulo();

		funcionalidade.setDescricao(inserirFuncionalidadeActionForm
				.getDescricao());

		funcionalidade.setDescricaoAbreviada(inserirFuncionalidadeActionForm
				.getDescricaoAbreviada());

		funcionalidade.setCaminhoMenu(inserirFuncionalidadeActionForm
				.getCaminhoMenu());

		funcionalidade.setCaminhoUrl(inserirFuncionalidadeActionForm
				.getCaminhoUrl());

		funcionalidade.setIndicadorPontoEntrada(new Short(
				inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()));

		funcionalidade.setNumeroOrdemMenu(new Long(
				inserirFuncionalidadeActionForm.getNumeroOrdemMenu()));

		if (idModulo != null
				&& !idModulo.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Modulo modulo = new Modulo();

			modulo.setId(new Integer(idModulo));

			funcionalidade.setModulo(modulo);

		}

		funcionalidade.setIndicadorNovaJanela(new Short(
				inserirFuncionalidadeActionForm.getIndicadorNovaJanela()));

		funcionalidade.setIndicadorOlap(new Short(
				inserirFuncionalidadeActionForm.getIndicadorOlap()));

		if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada() != null
				&& inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()
						.equalsIgnoreCase(
								ConstantesSistema.INDICADOR_USO_ATIVO
										.toString())
				&& inserirFuncionalidadeActionForm
						.getIdFuncionalidadeCategoria() != null
				&& !inserirFuncionalidadeActionForm
						.getIdFuncionalidadeCategoria().equalsIgnoreCase("")) {

			FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();
			filtroFuncionalidadeCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeCategoria.ID,
							inserirFuncionalidadeActionForm
									.getIdFuncionalidadeCategoria()));

			Collection colecaoFuncionalidadeCategoria = fachada.pesquisar(
					filtroFuncionalidadeCategoria,
					FuncionalidadeCategoria.class.getName());

			if (colecaoFuncionalidadeCategoria != null
					&& !colecaoFuncionalidadeCategoria.isEmpty()) {
				FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeCategoria);
				funcionalidade
						.setFuncionalidadeCategoria(funcionalidadeCategoria);
			} else {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Funcionalidade Categoria");
			}

		}

		Collection<Funcionalidade> colecaoFuncionalidade = null;

		if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
			colecaoFuncionalidade = (Collection) sessao
					.getAttribute("colecaoFuncionalidadeTela");
		}

		Integer idFuncionalidade = (Integer) fachada.inserirFuncionalidade(
				funcionalidade, colecaoFuncionalidade);

		sessao.removeAttribute("colecaoFuncionalidadeTela");

		montarPaginaSucesso(httpServletRequest, "Funcionalidade "
				+ idFuncionalidade + " inserida com sucesso.",
				"Inserir outra Funcionalidade",
				"exibirInserirFuncionalidadeAction.do?menu=sim",
				"exibirAtualizarFuncionalidadeAction.do?idFuncionalidade="
						+ idFuncionalidade, "Atualizar Funcionalidade inserida");

		return retorno;
	}
}
