package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.GerarExtensaoComandoContasCobrancaEmpresaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0879] Gerar Arquivo Texto das Contas em Cobrança por Empresa
 * 
 * @author Rômulo Aurélio
 * @since 02/02/2009
 */

public class ExibirGerarExtensaoComandoContasCobrancaEmpresaAction extends
		GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarExtensaoComandoContasCobrancaEmpresaAction");
		
		String pagina = httpServletRequest.getParameter("page.offset");

		GerarExtensaoComandoContasCobrancaEmpresaActionForm form = (GerarExtensaoComandoContasCobrancaEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		this.pesquisarCampoEnter(httpServletRequest, form, fachada);

		if (httpServletRequest.getParameter("limpar") != null) {

			sessao
					.removeAttribute("colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper");

		}

		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			
			if(pagina==null){
				pagina = "0";
			}
			
			retorno = this.pesquisarExtensaoComandosContas(httpServletRequest, form,
					sessao, retorno);
		}

		return retorno;
	}

	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			GerarExtensaoComandoContasCobrancaEmpresaActionForm form,
			Fachada fachada) {

		String idEmpresa = form.getIdEmpresa();

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util
						.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId().toString());
				form.setNomeEmpresa(empresa.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			} else {
				form.setIdEmpresa("");
				form.setNomeEmpresa("EMPRESA INEXISTENTE");

				httpServletRequest.setAttribute("empresaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}

		} else {
			form.setNomeEmpresa("");
		}

	}

	private ActionForward pesquisarExtensaoComandosContas(
			HttpServletRequest httpServletRequest,
			GerarExtensaoComandoContasCobrancaEmpresaActionForm form,
			HttpSession sessao, ActionForward retorno) {

		String idEmpresa = form.getIdEmpresa();
		
		ActionForward retorno2 = new ActionForward();
		
		String periodoComandoInicial = form.getPeriodoComandoInicial();

		String periodoComandoFinal = form.getPeriodoComandoFinal();

		Date comandoInicial = null;

		Date comandoFinal = null;

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = this.getFachada().pesquisar(
					filtroEmpresa, Empresa.class.getName());

			if (Util.isVazioOrNulo(colecaoEmpresa)) {

				throw new ActionServletException("atencao.empresa.inexistente");

			}
		} else {
			throw new ActionServletException("atencao.empresa.obrigatoria");
		}

		if (periodoComandoFinal != null && !periodoComandoFinal.equals("")
				&& periodoComandoInicial != null
				&& !periodoComandoInicial.equals("")) {

			comandoInicial = Util.converteStringParaDate(periodoComandoInicial);
			comandoFinal = Util.converteStringParaDate(periodoComandoFinal);

			if (comandoInicial.compareTo(comandoFinal) > 0) {
				throw new ActionServletException(
						"atencao.data_inicial_periodo_execucao_comando.posterior.data_final_periodo_execucao_comando");
			}

		}
		
		Integer totalRegistros = this.getFachada()
			.pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaCount(
				new Integer(idEmpresa), comandoInicial, comandoFinal);

		retorno2 = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		Collection<GerarExtensaoComandoContasCobrancaEmpresaHelper> colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper = this
				.getFachada()
				.pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresa(
						new Integer(idEmpresa), comandoInicial, comandoFinal,(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		
		

		if (colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper != null
				&& !colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper
						.isEmpty()) {

			sessao.setAttribute(
					"colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper",
					colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper);

		} else {
			throw new ActionServletException(
					"atencao.nenhum_comando_selecionado_geracao_arquivo");
		}
	
		return retorno2;
		
	}

}
