package gcom.gui.relatorio.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.cobrancaporresultado.ConsultarComandosContasCobrancaEmpresaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * 
 * 
 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
 * 
 * @author Hugo Azevedo 
 * @date 02/07/2011
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */

public class ExibirConsultarComandosAcompanhamentoCobrancaResultadoAction extends GcomAction {

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
				.findForward("exibirConsultarComandos");
		
		String pagina = httpServletRequest.getParameter("page.offset");
	
		ConsultarComandosAcompanhamentoCobrancaResultadoActionForm form = (ConsultarComandosAcompanhamentoCobrancaResultadoActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
	
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o usuario que está logado na aplicação
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
	
		if (fachada.verificarPermissaoEspecial(PermissaoEspecial.ENCERRAR_COMANDO_COBRANCA_EMPRESA, usuarioLogado)) {
			sessao.setAttribute("permissaoEspecialEncerrarComando", true);
		} else {
			sessao.removeAttribute("permissaoEspecialEncerrarComando");
		}
		
		this.pesquisarCampoEnter(httpServletRequest, form, fachada);
	
		if (httpServletRequest.getParameter("limpar") != null 
				&& httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim")) {
	
			sessao.removeAttribute("colecaoConsultarComandosContasCobrancaEmpresaHelper");
			
		}
	
		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			if(pagina==null){
				pagina = "0";
			}
			
			retorno = this.pesquisarComandosContas(httpServletRequest, form, fachada,
					sessao,pagina,retorno);
		}
	
		return retorno;
	}
	
	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest,
			ConsultarComandosAcompanhamentoCobrancaResultadoActionForm form,
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
	
	private ActionForward pesquisarComandosContas(HttpServletRequest httpServletRequest,
			ConsultarComandosAcompanhamentoCobrancaResultadoActionForm form,
			Fachada fachada, HttpSession sessao, String pagina, ActionForward retorno) {

		String idEmpresa = form.getIdEmpresa();
		
		ActionForward retorno2 = new ActionForward();

		
		String periodoCicloInicial = form.getPeriodoCicloInicial();

		String periodoCicloFinal = form.getPeriodoCicloFinal();

		Date cicloInicial = null;

		Date cicloFinal = null;

		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (Util.isVazioOrNulo(colecaoEmpresa)) {

				throw new ActionServletException("atencao.empresa.inexistente");

			}
		}

		if (periodoCicloFinal != null && !periodoCicloFinal.equals("")
				&& periodoCicloInicial != null
				&& !periodoCicloInicial.equals("")) {

			cicloInicial = Util.converteStringParaDate(periodoCicloInicial);
			cicloFinal = Util.converteStringParaDate(periodoCicloFinal);

			if (cicloInicial.compareTo(cicloFinal) > 0) {
				throw new ActionServletException(
						"atencao.data_inicial_periodo_ciclo.posterior.data_final_periodo_ciclo");
			}

		}

		Integer totalRegistros = fachada
				.pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaCount(
						new Integer(idEmpresa), cicloInicial, cicloFinal);

		retorno2 = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		Collection<ConsultarComandosContasCobrancaEmpresaHelper> colecaoConsultarComandosContasCobrancaEmpresaHelper = fachada
				.pesquisarDadosConsultarComandosContasCobrancaEmpresa(
						new Integer(idEmpresa), cicloInicial, cicloFinal,(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		if(colecaoConsultarComandosContasCobrancaEmpresaHelper !=null 
				&& !colecaoConsultarComandosContasCobrancaEmpresaHelper.isEmpty()){
		
			sessao.setAttribute("dataInicial",cicloInicial);	
			sessao.setAttribute("dataFinal",cicloFinal);	
			sessao.setAttribute("colecaoConsultarComandosContasCobrancaEmpresaHelper",
				colecaoConsultarComandosContasCobrancaEmpresaHelper);
		}else{
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno2;
	}
}
