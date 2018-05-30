package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarArquivoTextoContasCobrancaEmpresaAction extends GcomAction {

	//private HttpSession sessao;
	//private HttpServletRequest request;
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("exibirGerarArquivoTextoContasCobrancaEmpresaAction");

		GerarArquivoTextoContasCobrancaEmpresaActionForm form = (GerarArquivoTextoContasCobrancaEmpresaActionForm) actionForm;
		HttpSession sessao = request.getSession(false);

		verificarEmpresa(sessao, request, form);

		if (request.getParameter("limpar") != null) {
			sessao.removeAttribute("colecaoGerarArquivoTextoContasCobrancaEmpresaHelper");
		}

		String pagina = request.getParameter("page.offset");
		if (request.getParameter("selecionarComandos") != null || pagina != null) {
			if (pagina == null) {
				pagina = "0";
			}

			retorno = pesquisarComandosContas(sessao, request, pagina, retorno, form);
		}

		return retorno;
	}

	@SuppressWarnings("rawtypes")
	private Collection pesquisarEmpresa(HttpSession sessao, GerarArquivoTextoContasCobrancaEmpresaActionForm form) {
		if (empresaInformada(sessao, form)) {
			Filtro filtro = new FiltroEmpresa();
			
			if (form.getIdEmpresa() != null) {
				filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
				sessao.setAttribute("idEmpresaCobrancaConta", form.getIdEmpresa());
			} else {
				filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, sessao.getAttribute("idEmpresaCobrancaConta")));
			}
			return getFachada().pesquisar(filtro, Empresa.class.getName());
		} else {
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void verificarEmpresa(HttpSession session, HttpServletRequest request, GerarArquivoTextoContasCobrancaEmpresaActionForm form) {
		Collection colecao = pesquisarEmpresa(session, form);
		if (!Util.isVazioOrNulo(colecao)) {
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecao);
			form.setIdEmpresa(empresa.getId().toString());
			form.setNomeEmpresa(empresa.getDescricao());
			request.setAttribute("nomeCampo", "idEmpresa");
		} else if (empresaInformada(session, form)) {
			form.setIdEmpresa("");
			form.setNomeEmpresa("EMPRESA INEXISTENTE");
			request.setAttribute("empresaInexistente", true);
			request.setAttribute("nomeCampo", "idEmpresa");
		}
	}

	@SuppressWarnings("unchecked")
	private ActionForward pesquisarComandosContas(HttpSession sessao, HttpServletRequest request, String pagina, ActionForward retorno, GerarArquivoTextoContasCobrancaEmpresaActionForm form) {
		if (Util.isVazioOrNulo(pesquisarEmpresa(sessao, form))) {
			throw new ActionServletException("atencao.empresa.inexistente");
		}

		String periodoComandoInicial = form.getPeriodoComandoInicial();
		String periodoComandoFinal = form.getPeriodoComandoFinal();
		Date comandoInicial = null;
		Date comandoFinal = null;
		if (periodoComandoFinal != null && !periodoComandoFinal.equals("") && periodoComandoInicial != null && !periodoComandoInicial.equals("")) {

			comandoInicial = Util.converteStringParaDate(periodoComandoInicial);
			comandoFinal = Util.converteStringParaDate(periodoComandoFinal);

			if (comandoInicial.compareTo(comandoFinal) > 0) {
				throw new ActionServletException("atencao.data_inicial_periodo_execucao_comando.posterior.data_final_periodo_execucao_comando");
			}
		}

		Integer totalRegistros = getFachada().pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaCount(new Integer(form.getIdEmpresa()), comandoInicial, comandoFinal);

		ActionForward retorno2 = controlarPaginacao(request, retorno, totalRegistros);

		List<ComandoEmpresaCobrancaConta> comandos = getFachada().pesquisarDadosComandoCobrancaEmpresa(new Integer(form.getIdEmpresa()), 
				comandoInicial, comandoFinal, (Integer) request.getAttribute("numeroPaginasPesquisa"));
		
		if (comandos != null && !comandos.isEmpty()) {
			sessao.setAttribute("dataInicial", comandoInicial);
			sessao.setAttribute("dataFinal", comandoFinal);
			sessao.setAttribute("comandos", comandos);
			
		} else {
			throw new ActionServletException("atencao.nenhum_comando_selecionado_geracao_arquivo");
		}

		return retorno2;
	}

	private boolean empresaInformada(HttpSession sessao, GerarArquivoTextoContasCobrancaEmpresaActionForm form) {
		return form.getIdEmpresa() != null && !form.getIdEmpresa().trim().equals("") || sessao.getAttribute("idEmpresaCobrancaConta") != null ;
	}
}
