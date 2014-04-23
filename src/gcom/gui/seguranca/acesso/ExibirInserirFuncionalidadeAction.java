package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Modulo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
public class ExibirInserirFuncionalidadeAction extends GcomAction {

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

		ActionForward retorno = actionMapping
				.findForward("funcionalidadeInserir");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirFuncionalidadeActionForm inserirFuncionalidadeActionForm = (InserirFuncionalidadeActionForm) actionForm;

		this.pesquisarCampoEnter(httpServletRequest,
				inserirFuncionalidadeActionForm, fachada);

		if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada() == null
				|| inserirFuncionalidadeActionForm.equals("")) {
			inserirFuncionalidadeActionForm
					.setIndicadorPontoEntrada(ConstantesSistema.INDICADOR_USO_ATIVO
							.toString());
		}

		if (inserirFuncionalidadeActionForm.getIndicadorOlap() == null
				|| !inserirFuncionalidadeActionForm.equals("")) {
			inserirFuncionalidadeActionForm
					.setIndicadorOlap(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());
		}

		if (inserirFuncionalidadeActionForm.getIndicadorNovaJanela() == null
				|| !inserirFuncionalidadeActionForm.equals("")) {
			inserirFuncionalidadeActionForm
					.setIndicadorNovaJanela(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());
		}

		// Parte do adicionar

		Collection<Funcionalidade> colecaoFuncionalidade = null;

		if (sessao.getAttribute("colecaoFuncionalidadeTela") != null) {
			colecaoFuncionalidade = (Collection) sessao
					.getAttribute("colecaoFuncionalidadeTela");
		} else {
			colecaoFuncionalidade = new ArrayList();
		}

		if (sessao.getAttribute("colecaoFuncionalidade") != null) {
			Collection colecaoPopUp = (Collection) sessao
					.getAttribute("colecaoFuncionalidade");
			colecaoFuncionalidade.addAll(colecaoPopUp);
			sessao.removeAttribute("colecaoFuncionalidade");
			sessao.setAttribute("colecaoFuncionalidadeTela",
					colecaoFuncionalidade);
		}

		if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada() != null
				&& inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()
						.equalsIgnoreCase(
								ConstantesSistema.INDICADOR_USO_ATIVO
										.toString())) {

			httpServletRequest.setAttribute("funcionalidadeCategoria", "sim");

		} else {
			httpServletRequest.removeAttribute("funcionalidadeCategoria");

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			inserirFuncionalidadeActionForm.setDescricao("");
			inserirFuncionalidadeActionForm.setDescricaoAbreviada("");
			inserirFuncionalidadeActionForm.setCaminhoMenu("");
			inserirFuncionalidadeActionForm.setCaminhoUrl("");
			inserirFuncionalidadeActionForm
					.setIndicadorPontoEntrada(ConstantesSistema.INDICADOR_USO_ATIVO
							.toString());
			inserirFuncionalidadeActionForm.setModulo("-1");

			inserirFuncionalidadeActionForm
					.setIndicadorNovaJanela(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());

			inserirFuncionalidadeActionForm
					.setIndicadorOlap(ConstantesSistema.INDICADOR_USO_DESATIVO
							.toString());

			inserirFuncionalidadeActionForm.setNumeroOrdemMenu("");

			if (inserirFuncionalidadeActionForm.getIndicadorPontoEntrada()
					.equals(ConstantesSistema.INDICADOR_USO_ATIVO.toString())) {

				inserirFuncionalidadeActionForm
						.setIdFuncionalidadeCategoria("");

				inserirFuncionalidadeActionForm
						.setNomeFuncionalidadeCategoria("");

				httpServletRequest.setAttribute("funcionalidadeCategoria",
						"sim");

			} else {
				httpServletRequest.removeAttribute("funcionalidadeCategoria");

			}

			sessao.removeAttribute("colecaoFuncionalidadeTela");

		}

		return retorno;

	}

	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			InserirFuncionalidadeActionForm form, Fachada fachada) {

		String idFuncionalidadeCategoria = form.getIdFuncionalidadeCategoria();

		// Pesquisa a empresa
		if (idFuncionalidadeCategoria != null
				&& !idFuncionalidadeCategoria.trim().equals("")) {

			FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();
			filtroFuncionalidadeCategoria
					.adicionarParametro(new ParametroSimples(
							FiltroFuncionalidadeCategoria.ID,
							idFuncionalidadeCategoria));

			Collection colecaoFuncionalidadeCategoria = fachada.pesquisar(
					filtroFuncionalidadeCategoria,
					FuncionalidadeCategoria.class.getName());

			if (colecaoFuncionalidadeCategoria != null
					&& !colecaoFuncionalidadeCategoria.isEmpty()) {
				FuncionalidadeCategoria funcionalidadeCategoria = (FuncionalidadeCategoria) Util
						.retonarObjetoDeColecao(colecaoFuncionalidadeCategoria);
				
				httpServletRequest.setAttribute("idFuncionalidadeCategoria",
						"true");
				
				form.setIdFuncionalidadeCategoria(funcionalidadeCategoria
						.getId().toString());
				form.setNomeFuncionalidadeCategoria(funcionalidadeCategoria
						.getNome());
			} else {
				form.setIdFuncionalidadeCategoria("");
				form
						.setNomeFuncionalidadeCategoria("FUNCIONALIDADE CATEGORIA INEXISTENTE");
				//httpServletRequest.setAttribute(
				//		"funcionalidadeCategoriaInexistente", true);
				//httpServletRequest.setAttribute("nomeCampo", "idFuncionalidadeCategoria");
			}

		} else {
			form.setNomeFuncionalidadeCategoria("");
		}

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao
		FiltroModulo filtroModulo = new FiltroModulo();

		filtroModulo.setCampoOrderBy(FiltroModulo.DESCRICAO_MODULO);
		Collection<Modulo> colecaoModulo = fachada.pesquisar(filtroModulo,
				Modulo.class.getName());

		if (colecaoModulo == null || colecaoModulo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Modulo");
		}

		httpServletRequest.setAttribute("colecaoModulo", colecaoModulo);

	}
}
