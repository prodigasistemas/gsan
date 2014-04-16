package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper;
import gcom.batch.Processo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
 * 
 * @author Vivianne Sousa
 * @since 11/07/2011
 */
public class ExibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction extends GcomAction {

	/**
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
	
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarComandosOSSeletivaInspecaoAnormalidade");
		
		String pagina = httpServletRequest.getParameter("page.offset");
	
		ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm form = (ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
	
		HttpSession sessao = httpServletRequest.getSession(false);
		
		this.pesquisarCampoEnter(httpServletRequest, form, fachada);
	
		if (httpServletRequest.getParameter("limpar") != null 
				&& httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim")) {
	
			sessao.removeAttribute("colecaoConsultarComandosOSHelper");
			
		}
	
		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			if(pagina==null){
				pagina = "0";
			}
			
			retorno = this.pesquisarComandosOS(httpServletRequest, form, fachada,
					sessao,pagina,retorno);
		}
	
		if (httpServletRequest.getParameter("acao") != null 
				&& httpServletRequest.getParameter("acao").equalsIgnoreCase("gerarTxtComando")) {
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			ComandoOrdemSeletiva  comandoOrdemSeletiva = fachada.pesquisarComandoOSSeletiva(new Integer(form.getIdRegistro()));
			if (comandoOrdemSeletiva.getDataEncerramento() != null) {
				throw new ActionServletException("atencao.comando.ja_encerrado", 
						null, "Comandos de OS Seletiva de Inspeção de Anormalidade");
			} 
			
			Integer qtdeOsNaoPendenteFazParteComando = fachada.pesquisaOrdemServicoNaoPendenteFazParteComando(new Integer(form.getIdRegistro()));
			if(qtdeOsNaoPendenteFazParteComando != null && qtdeOsNaoPendenteFazParteComando.intValue() > 0){
				throw new ActionServletException("atencao.nao_e_possivel_gerar_txt_comando");
			}
			
			Map parametros = new HashMap();
			parametros.put("idComandoOrdemSeletiva",new Integer(form.getIdRegistro()));
			parametros.put("qtdAnormalidadesConsecutivas",comandoOrdemSeletiva.getQuantidadeConsecutivaAnormalidade());
			Fachada.getInstancia().inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(
					parametros, 
	         		Processo.GERAR_TXT_OS_INSPECAO_ANORMALIDADE ,
	         		usuarioLogado);
				
			retorno = actionMapping.findForward("telaSucesso");
			//montando página de sucesso
			montarPaginaSucesso(httpServletRequest,
				"Geração de Txt do Comando  de OS Seletiva de Inspeção de Anormalidade Enviado para Processamento", 
				"Voltar",
				"exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?menu=sim");
		}
		
		return retorno;
	}
	
	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm form,
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
	
	private ActionForward pesquisarComandosOS(HttpServletRequest httpServletRequest,
			ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm form,
			Fachada fachada, HttpSession sessao, String pagina, ActionForward retorno) {

		String idEmpresa = form.getIdEmpresa();
		
		ActionForward retorno2 = new ActionForward();

		String periodoExecucaoInicial = form.getPeriodoExecucaoInicial();

		String periodoExecucaoFinal = form.getPeriodoExecucaoFinal();

		Date execucaoInicial = null;

		Date execucaoFinal = null;

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,Empresa.class.getName());

			if (Util.isVazioOrNulo(colecaoEmpresa)) {
				throw new ActionServletException("atencao.empresa.inexistente");
			}
		}else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Empresa");
		}

		if (periodoExecucaoFinal != null && !periodoExecucaoFinal.equals("")
			&& periodoExecucaoInicial != null && !periodoExecucaoInicial.equals("")) {

			execucaoInicial = Util.converteStringParaDate(periodoExecucaoInicial);
			execucaoFinal = Util.converteStringParaDate(periodoExecucaoFinal);

			if (execucaoInicial.compareTo(execucaoFinal) > 0) {
				throw new ActionServletException("atencao.data_inicial_periodo_execucao.posterior.data_final_periodo_execucao");
			}

		}
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		int quantidadeRegistros = 10;
		Integer totalRegistros = fachada.pesquisarDadosComandoOSSeletivaCount(
				new Integer(idEmpresa), execucaoInicial, execucaoFinal);

		retorno2 = this.controlarPaginacao(httpServletRequest, retorno,	totalRegistros);
		
		Collection<ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper> colecaoConsultarComandosOSHelper = fachada
		.pesquisarDadosComandoOSSeletivaResumido(new Integer(idEmpresa),execucaoInicial, execucaoFinal,
				(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"),quantidadeRegistros,
				sistemaParametro.getQtdeDiasValidadeOSAnormalidadeFiscalizacao());
		
		if(colecaoConsultarComandosOSHelper != null && !colecaoConsultarComandosOSHelper.isEmpty()){
			sessao.setAttribute("dataInicial",execucaoInicial);	
			sessao.setAttribute("dataFinal",execucaoFinal);	
			sessao.setAttribute("colecaoConsultarComandosOSHelper",	colecaoConsultarComandosOSHelper);
		}else{
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno2;
	}
}
