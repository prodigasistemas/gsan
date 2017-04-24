package gcom.gui;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GcomAction extends DispatchAction {

	private SistemaParametro sistemaParametro;
	private Fachada fachada;

	protected void reportarErros(HttpServletRequest request, String chave) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chave));

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	public static long obterTimestampIdObjeto(Object objeto) {
		long retorno = 0L;

		try {
			Integer idObjeto = (Integer) objeto.getClass().getMethod("getId", (Class[]) null).invoke(objeto, (Object[]) null);

			Object ultimaAlteracao = objeto.getClass().getMethod("getUltimaAlteracao", (Class[]) null).invoke(objeto, (Object[]) null);
			if (ultimaAlteracao != null) {
				retorno = (Long) ultimaAlteracao.getClass().getMethod("getTime", (Class[]) null).invoke(ultimaAlteracao, (Object[]) null);
			} else {
				throw new ActionServletException("atencao.registro.sem.timestamp");
			}

			if (idObjeto != null) {
				retorno = retorno + idObjeto;
			}
		} catch (IllegalArgumentException e) {
			throw new ActionServletException("erro.sistema");
		} catch (SecurityException e) {
			throw new ActionServletException("erro.sistema");
		} catch (IllegalAccessException e) {
			throw new ActionServletException("erro.sistema");
		} catch (InvocationTargetException e) {
			throw new ActionServletException("erro.sistema");
		} catch (NoSuchMethodException e) {
			try {
				Object compId = (Object) objeto.getClass().getMethod("getComp_id", (Class[]) null).invoke(objeto, (Object[]) null);

				Object ultimaAlteracao = objeto.getClass().getMethod("getUltimaAlteracao", (Class[]) null).invoke(objeto, (Object[]) null);
				retorno = (Long) ultimaAlteracao.getClass().getMethod("getTime", (Class[]) null).invoke(ultimaAlteracao, (Object[]) null);

				if (compId != null) {
					retorno = retorno + compId.hashCode();
				}
			} catch (IllegalArgumentException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (SecurityException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (IllegalAccessException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (InvocationTargetException ex) {
				throw new ActionServletException("erro.sistema");
			} catch (NoSuchMethodException ex) {
				throw new ActionServletException("erro.sistema");
			}
		}

		return retorno;
	}

	protected void reportarErrosMensagem(HttpServletRequest request, String chave, String mensagem) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chave, mensagem));

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	protected void reportarErros(HttpServletRequest request, String chaveMensagem, Exception exception) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chaveMensagem));

		HttpSession sessao = request.getSession(false);

		if (sessao == null) {
			try {
				ServicosEmail.enviarMensagem("gcom@compesa.com.br", ServicosEmail.EMAIL_ADMINISTRADOR, "Urgente: Erro no Sistema", ServicosEmail.processarExceptionParaEnvio(exception));
			} catch (ErroEmailException ex) {
				ex.printStackTrace();
			}
		} else {
			sessao.setAttribute("excecaoPaginaErro", exception);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	protected void reportarErrosMensagem(HttpServletRequest request, String chaveMensagem, String mensagem, Exception exception) {
		ActionErrors errors = new ActionErrors();

		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(chaveMensagem, mensagem));

		HttpSession sessao = request.getSession(false);

		if (sessao == null) {
			try {
				ServicosEmail.enviarMensagem("gcom@compesa.com.br", ServicosEmail.EMAIL_ADMINISTRADOR, "Urgente: Erro no Sistema", ServicosEmail.processarExceptionParaEnvio(exception));
			} catch (ErroEmailException ex) {
				ex.printStackTrace();
			}
		} else {
			sessao.setAttribute("excecaoPaginaErro", exception);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
	}

	protected boolean verificarUsuarioLogado(HttpSession sessao, String parametroSessao) {
		return (sessao != null && sessao.getAttribute(parametroSessao) != null);
	}

	protected int converterStringToInt(String target) {
		try {
			return Integer.parseInt(target);
		} catch (NumberFormatException e) {
			return ConstantesSistema.NUMERO_NAO_INFORMADO;
		}
	}

	protected void montarPaginaSucesso(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade) {
		request.setAttribute("labelPaginaSucesso", labelPaginaSucesso);
		request.setAttribute("mensagemPaginaSucesso", mensagemPaginaSucesso);
		request.setAttribute("caminhoFuncionalidade", caminhoFuncionalidade);

	}

	protected void montarPaginaSucesso(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade, 
			String caminhoAtualizarRegistro, String labelPaginaAtualizacao) {
		
		request.setAttribute("labelPaginaAtualizacao", labelPaginaAtualizacao);
		request.setAttribute("caminhoAtualizarRegistro", caminhoAtualizarRegistro);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade);

	}

	protected void montarPaginaSucesso(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade, 
			String caminhoAtualizarRegistro, String labelPaginaAtualizacao, String labelGerarOrdemServico, String caminhoGerarOrdemServico) {

		request.setAttribute("labelGerarOrdemServico", labelGerarOrdemServico);
		request.setAttribute("caminhoGerarOrdemServico", caminhoGerarOrdemServico);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade, caminhoAtualizarRegistro, labelPaginaAtualizacao);

	}

	protected void montarPaginaSucessoComVoltarJavascript(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade,
			String caminhoAtualizarRegistro, String labelPaginaAtualizacao, String labelVoltar, String caminhoVoltar) {

		request.setAttribute("labelVoltarJavascript", labelVoltar);
		request.setAttribute("caminhoVoltarJavascript", caminhoVoltar);

		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade, caminhoAtualizarRegistro, labelPaginaAtualizacao);
	}

	protected void montarPaginaSucesso(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade,String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String labelGerarOrdemServico, String caminhoGerarOrdemServico, String labelVoltar, String caminhoVoltar) {

		request.setAttribute("labelVoltar", labelVoltar);
		request.setAttribute("caminhoVoltar", caminhoVoltar);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade, caminhoAtualizarRegistro, 
				labelPaginaAtualizacao, labelGerarOrdemServico, caminhoGerarOrdemServico);

	}

	protected void montarPaginaSucessoComImpressora(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, 
			String caminhoFuncionalidade, String caminhoAtualizarRegistro, String labelPaginaAtualizacao, String caminhoRelatorio) {

		request.setAttribute("labelPaginaAtualizacao", labelPaginaAtualizacao);
		request.setAttribute("caminhoAtualizarRegistro", caminhoAtualizarRegistro);
		request.setAttribute("caminhoRelatorio", caminhoRelatorio);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade);

	}

	protected void montarPaginaSucessoComImpressora(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade, 
			String caminhoAtualizarRegistro, String labelPaginaAtualizacao, String labelGerarOrdemServico, String caminhoGerarOrdemServico, String caminhoRelatorio) {

		request.setAttribute("labelGerarOrdemServico", labelGerarOrdemServico);
		request.setAttribute("caminhoGerarOrdemServico", caminhoGerarOrdemServico);
		request.setAttribute("caminhoRelatorio", caminhoRelatorio);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade, caminhoAtualizarRegistro, labelPaginaAtualizacao);

	}

	protected void montarPaginaSucessoUmRelatorio(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade, String caminhoAtualizarRegistro,
			String labelPaginaAtualizacao, String labelGerarOrdemServico, String caminhoGerarOrdemServico, String mensagemRelatorioLink1, String caminhoRelatorioLink1) {

		request.setAttribute("labelGerarOrdemServico", labelGerarOrdemServico);
		request.setAttribute("caminhoGerarOrdemServico", caminhoGerarOrdemServico);
		request.setAttribute("mensagemRelatorioLink1", mensagemRelatorioLink1);
		request.setAttribute("caminhoRelatorioLink1", caminhoRelatorioLink1);
		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade, caminhoAtualizarRegistro, labelPaginaAtualizacao);
	}

	protected void montarPaginaSucessoUmRelatorio(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade, 
			String caminhoAtualizarRegistro, String labelPaginaAtualizacao, String mensagemRelatorioLink1, String caminhoRelatorioLink1) {

		request.setAttribute("mensagemRelatorioLink1", mensagemRelatorioLink1);
		request.setAttribute("caminhoRelatorioLink1", caminhoRelatorioLink1);

		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade, caminhoAtualizarRegistro, labelPaginaAtualizacao);
	}

	protected void montarPaginaSucessoDoisRelatorios(HttpServletRequest request, String mensagemPaginaSucesso, String labelPaginaSucesso, String caminhoFuncionalidade,
			String caminhoAtualizarRegistro, String labelPaginaAtualizacao, String mensagemRelatorioLink1, String caminhoRelatorioLink1, String mensagemRelatorioLink2, String caminhoRelatorioLink2) {

		request.setAttribute("mensagemRelatorioLink1", mensagemRelatorioLink1);
		request.setAttribute("caminhoRelatorioLink1", caminhoRelatorioLink1);
		request.setAttribute("mensagemRelatorioLink2", mensagemRelatorioLink2);
		request.setAttribute("caminhoRelatorioLink2", caminhoRelatorioLink2);

		montarPaginaSucesso(request, mensagemPaginaSucesso, labelPaginaSucesso, caminhoFuncionalidade, caminhoAtualizarRegistro, labelPaginaAtualizacao);

	}

	protected void montarPaginaConfirmacao(HttpServletRequest request, String labelPaginaConfirmacao, String mensagemSuperiorConfirmacao, String mensagemInferiorConfirmacao, String caminhoFuncionalidade) {
		request.setAttribute("labelPaginaConfirmacao", labelPaginaConfirmacao);
		request.setAttribute("mensagemSuperiorConfirmacao", mensagemSuperiorConfirmacao);
		request.setAttribute("mensagemInferiorConfirmacao", mensagemInferiorConfirmacao);
		request.setAttribute("caminhoFuncionalidade", caminhoFuncionalidade);
	}

	protected String getNomeClasse(Object objeto) {
		String nomeClasse = null;
		String nomePacoteObjeto = objeto.getClass().getName();
		String nomeApenasPacote = (objeto.getClass().getPackage().toString()) + ".";

		int tamanhoNomePacoteObjeto = nomePacoteObjeto.length();
		int tamanhoNomePacote = nomeApenasPacote.length();

		nomeClasse = nomePacoteObjeto.substring((tamanhoNomePacote - 8), tamanhoNomePacoteObjeto);
		nomeClasse = (nomeClasse.substring(0, 1)).toLowerCase() + (nomeClasse.substring(1, nomeClasse.length()));

		return nomeClasse;
	}

	protected boolean verificarDataMenorQueDataCorrente(Date dataInformada) {
		return (new Date().after(dataInformada));
	}

	protected ActionForward montarPaginaConfirmacaoWizard(String chaveMensagem, HttpServletRequest request, ActionMapping actionMapping) {
		return montarPaginaConfirmacaoWizard(chaveMensagem, request, actionMapping, (String[]) null);
	}

	protected ActionForward montarPaginaConfirmacaoWizard(String chaveMensagem, HttpServletRequest request, ActionMapping actionMapping, String... parametrosMensagem) {
		String destino = request.getParameter("destino");

		if (destino == null) {
			if (request.getAttribute("destino") != null && !request.getAttribute("destino").equals("")) {
				destino = "" + request.getAttribute("destino");
			}
		}

		HttpSession sessao = getSessao(request);
		StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");

		String caminhoActionInicial = null;

		if (destino == null || destino.trim().equalsIgnoreCase("")) {
			caminhoActionInicial = (statusWizard.getCaminhoActionConclusao());
		} else {
			caminhoActionInicial = (statusWizard.retornarItemWizard(Integer.parseInt(destino))).getCaminhoActionInicial();
		}
		request.setAttribute("confirmacao", "true");
		request.setAttribute("caminhoConfirmacao", caminhoActionInicial);
		request.setAttribute("chaveMensagem", chaveMensagem);
		request.setAttribute("parametrosMensagem", parametrosMensagem);

		return actionMapping.findForward("telaConfirmacao");
	}

	protected ActionForward montarPaginaConfirmacao(String chaveMensagem, HttpServletRequest request, ActionMapping actionMapping, String... parametrosMensagem) {
		String caminhoActionConclusao = (String) request.getAttribute("caminhoActionConclusao");
		String tipoRelatorio = (String) request.getAttribute("tipoRelatorio");

		request.setAttribute("confirmacao", "true");
		request.setAttribute("confirmacaoNormal", "true");
		request.setAttribute("caminhoConfirmacao", caminhoActionConclusao);
		request.setAttribute("chaveMensagem", chaveMensagem);
		request.setAttribute("parametrosMensagem", parametrosMensagem);
		request.setAttribute("tipoRelatorio", tipoRelatorio);

		return actionMapping.findForward("telaConfirmacao");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map controlarPaginacao(HttpServletRequest request, ActionForward actionForward, Filtro filtro, String nomePacoteObjeto) {

		String totalRegistros = "" + (Integer) this.getSessao(request).getAttribute("totalRegistros");

		String pageOffsetRequest = request.getParameter("page.offset");

		if (pageOffsetRequest == null) {
			pageOffsetRequest = "1";
			totalRegistros = null;
		}

		Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

		Collection colecaoResultado = this.getFachada().pesquisar(filtro, pageOffset, nomePacoteObjeto);

		if ((totalRegistros == null || totalRegistros.trim().equalsIgnoreCase("") || totalRegistros.trim().equalsIgnoreCase("0") || totalRegistros.trim().equalsIgnoreCase("null"))
				|| (pageOffsetRequest == null || pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest.trim().equalsIgnoreCase("null"))) {

			int totalPesquisa = this.getFachada().totalRegistrosPesquisa(filtro, nomePacoteObjeto);
			totalRegistros = "" + totalPesquisa;

			this.getSessao(request).setAttribute("totalRegistros", totalPesquisa);

		}

		request.setAttribute("page.offset", pageOffset + 1);
		request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double.parseDouble(totalRegistros) / 10)).intValue());

		actionForward = new ActionForward(actionForward.getName(), actionForward.getPath() + "?pager.offset=" + (((pageOffset + 1) * 10) - 10), false);

		HashMap retorno = new HashMap();
		retorno.put("colecaoRetorno", colecaoResultado);
		retorno.put("destinoActionForward", actionForward);

		return retorno;
	}

	protected ActionForward controlarPaginacao(HttpServletRequest request, ActionForward actionForward, int totalRegistrosPesquisa) {
		HttpSession sessao = request.getSession(false);

		String totalRegistros = "" + (Integer) sessao.getAttribute("totalRegistros");

		String pageOffsetRequest = request.getParameter("page.offset");

		if (pageOffsetRequest == null) {
			pageOffsetRequest = "1";
			totalRegistros = null;
		}

		if ((totalRegistros == null || totalRegistros.trim().equalsIgnoreCase("") || totalRegistros.trim().equalsIgnoreCase("0") || totalRegistros.trim().equalsIgnoreCase("null"))
				|| (pageOffsetRequest == null || pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest.trim().equalsIgnoreCase("null"))) {

			int totalPesquisa = totalRegistrosPesquisa;
			totalRegistros = "" + totalPesquisa;
			sessao.setAttribute("totalRegistros", totalPesquisa);

		}

		Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

		request.setAttribute("page.offset", pageOffset + 1);
		request.setAttribute("numeroPaginasPesquisa", pageOffset);
		request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double.parseDouble(totalRegistros) / 10)).intValue());

		actionForward = new ActionForward(actionForward.getName(), actionForward.getPath() + "?pager.offset=" + (((pageOffset + 1) * 10) - 10), false);

		return actionForward;
	}

	protected Fachada getFachada() {
		if (fachada == null) {
			fachada = Fachada.getInstancia();
		}

		return fachada;
	}

	protected HttpSession getSessao(HttpServletRequest request) {
		return request.getSession(false);
	}

	protected Usuario getUsuarioLogado(HttpServletRequest request) {

		Usuario usuario = (Usuario) this.getSessao(request).getAttribute("usuarioLogado");

		return usuario;
	}

	protected SistemaParametro getSistemaParametro() {
		if (sistemaParametro == null) {
			sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		}

		return sistemaParametro;
	}

	protected ActionForward controlarPaginacao(HttpServletRequest request, ActionForward actionForward, Integer totalRegistrosPesquisa, Boolean primeiraPaginacao) {
		if (primeiraPaginacao) {

			String registrosPrimeiraPaginacao = "" + (Integer) this.getSessao(request).getAttribute("totalRegistrosPrimeiraPaginacao");
			String pageOffsetRequest = request.getParameter("page.offset");

			if (pageOffsetRequest == null) {
				pageOffsetRequest = "1";
				registrosPrimeiraPaginacao = null;
			}

			if ((registrosPrimeiraPaginacao == null || registrosPrimeiraPaginacao.trim().equalsIgnoreCase("") || registrosPrimeiraPaginacao.trim().equalsIgnoreCase("0") || registrosPrimeiraPaginacao
					.trim().equalsIgnoreCase("null")) || (pageOffsetRequest == null || pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest.trim().equalsIgnoreCase("null"))) {

				Integer totalPesquisa = totalRegistrosPesquisa;
				registrosPrimeiraPaginacao = "" + totalPesquisa;
				this.getSessao(request).setAttribute("registrosPrimeiraPaginacao", totalPesquisa);

			}

			Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

			request.setAttribute("page.offset", pageOffset + 1);
			request.setAttribute("numeroPaginasPesquisaPrimeiraPaginacao", pageOffset);
			request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double.parseDouble(registrosPrimeiraPaginacao) / 10)).intValue());
			actionForward = new ActionForward(actionForward.getName(), actionForward.getPath() + "?pager.offset=" + (((pageOffset + 1) * 10) - 10), false);

		} else {

			String registrosSegundaPaginacao = "" + (Integer) this.getSessao(request).getAttribute("totalRegistrosSegundaPaginacao");
			String pageOffsetRequest = request.getParameter("page.offset");

			if (pageOffsetRequest == null) {
				pageOffsetRequest = "1";
				registrosSegundaPaginacao = null;
			}

			if ((registrosSegundaPaginacao == null || registrosSegundaPaginacao.trim().equalsIgnoreCase("") || registrosSegundaPaginacao.trim().equalsIgnoreCase("0") || registrosSegundaPaginacao
					.trim().equalsIgnoreCase("null")) || (pageOffsetRequest == null || pageOffsetRequest.trim().equalsIgnoreCase("") || pageOffsetRequest.trim().equalsIgnoreCase("null"))) {

				Integer totalPesquisa = totalRegistrosPesquisa;
				registrosSegundaPaginacao = "" + totalPesquisa;
				this.getSessao(request).setAttribute("registrosSegundaPaginacao", totalPesquisa);

			}

			Integer pageOffset = Integer.parseInt(pageOffsetRequest) - 1;

			request.setAttribute("page.offset", pageOffset + 1);
			request.setAttribute("numeroPaginasPesquisaSegundaPaginacao", pageOffset);
			request.setAttribute("maximoPaginas", ((Double) Math.ceil(Double.parseDouble(registrosSegundaPaginacao) / 10)).intValue());
			actionForward = new ActionForward(actionForward.getName(), actionForward.getPath() + "?pager.offset=" + (((pageOffset + 1) * 10) - 10), false);
		}

		return actionForward;

	}

	protected boolean verificaReferenciaIgualReferencialFaturamento(Integer referencia) {
		if (referencia.equals(getSistemaParametro().getAnoMesFaturamento()))
			return true;
		return false;
	}
}
