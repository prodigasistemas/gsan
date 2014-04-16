package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Pagamento - Exibir
 * 
 * @author TIAGO MORENO - 31/01/2006
 */
public class ExibirPesquisarAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarAvisoBancario");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarAvisoBancarioActionForm pesquisarAvisoBancarioActionForm = (PesquisarAvisoBancarioActionForm) actionForm;

		sessao.removeAttribute("tipoPesquisa");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaArrecadador");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaContaBancaria");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaMovimentoArrecadador");

		if (httpServletRequest.getParameter("limparForm") != null) {
//			if (httpServletRequest.getParameter("limparForm").equals("1")) {
				
				pesquisarAvisoBancarioActionForm.setIdArrecadador("");
				pesquisarAvisoBancarioActionForm.setDataLancamentoInicio("");
				pesquisarAvisoBancarioActionForm.setDataLancamentoFim("");
				pesquisarAvisoBancarioActionForm.setTipoAviso("");
				pesquisarAvisoBancarioActionForm.setPeriodoArrecadacaoInicio("");
				pesquisarAvisoBancarioActionForm.setPeriodoArrecadacaoFim("");
				pesquisarAvisoBancarioActionForm.setDataPrevisaoCreditoDebitoFim("");
				pesquisarAvisoBancarioActionForm.setDataPrevisaoCreditoDebitoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorPrevistoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorPrevistoFim("");
				pesquisarAvisoBancarioActionForm.setDataRealizacaoCreditoDebitoFim("");
				pesquisarAvisoBancarioActionForm.setDataRealizacaoCreditoDebitoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorRealizadoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorRealizadoFim("");
				pesquisarAvisoBancarioActionForm.setArrecadadorNome("");
				pesquisarAvisoBancarioActionForm.setIdConta("");
				pesquisarAvisoBancarioActionForm.setIdBancoConta("");
				pesquisarAvisoBancarioActionForm.setNumeroConta("");
				pesquisarAvisoBancarioActionForm.setCodigoBanco("");
				pesquisarAvisoBancarioActionForm.setCodigoRemessa("");
				pesquisarAvisoBancarioActionForm.setIdentificacaoServico("");
				pesquisarAvisoBancarioActionForm.setNumeroSequencial("");
				pesquisarAvisoBancarioActionForm.setIdMovimento("");
				
//				pesquisarAvisoBancarioActionForm.reset(actionMapping,
//						httpServletRequest);
				sessao.removeAttribute("PesquisarAvisoBancarioActionForm");
//			}
		}

		// -------Inicio da Parte que trata do código quando o usuário tecla enter
		String idDigitadoEnterArrecadador = (String) pesquisarAvisoBancarioActionForm
			.getIdArrecadador();
		
		/*Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		if(requestMap.get("idArrecadador") != null){
			sessao.setAttribute("idArrecadador","idArrecadador");
		}else if ((httpServletRequest.getParameter("tipoConsulta") != null)
				&& (!httpServletRequest.getParameter("tipoConsulta")
						.equals("arrecadador"))) {

			sessao.setAttribute("idArrecadador","idArrecadador");
		}

		if(requestMap.get("idArrecadador") == null && sessao.getAttribute("idArrecadador") == null && idDigitadoEnterArrecadador != null){
			pesquisarAvisoBancarioActionForm.setIdArrecadador("");
			pesquisarAvisoBancarioActionForm.setArrecadadorNome("");
		}else*/if (idDigitadoEnterArrecadador != null
				&& !idDigitadoEnterArrecadador.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterArrecadador) > 0) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE,
					idDigitadoEnterArrecadador));

			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection arrecadadorEncontrado = fachada.pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			if (arrecadadorEncontrado != null
					&& !arrecadadorEncontrado.isEmpty()) {
				// O arrecadador foi encontrado
				pesquisarAvisoBancarioActionForm.setIdArrecadador(""
						+ ((Arrecadador) ((List) arrecadadorEncontrado)
								.get(0)).getCodigoAgente());
				pesquisarAvisoBancarioActionForm
						.setArrecadadorNome(""
								+ ((Arrecadador) ((List) arrecadadorEncontrado)
										.get(0)).getCliente().getNome());
				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "true");
				httpServletRequest.setAttribute("nomeCampo", "tipoRemessa");

			} else {

				pesquisarAvisoBancarioActionForm.setIdArrecadador("");
				pesquisarAvisoBancarioActionForm
				.setArrecadadorNome("ARRECADADOR INEXISTENTE");

				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "exception");

			}
		}
	 	// -------Fim da Parte que trata do código quando o usuário tecla enter
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"contaBancaria")) {

				pesquisarAvisoBancarioActionForm.setIdConta(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarAvisoBancarioActionForm
						.setIdBancoConta(httpServletRequest
								.getParameter("descricaoCampoEnviarDados1"));

				pesquisarAvisoBancarioActionForm
						.setIdAgenciaConta(httpServletRequest
								.getParameter("descricaoCampoEnviarDados2"));

				pesquisarAvisoBancarioActionForm
						.setNumeroConta(httpServletRequest
								.getParameter("descricaoCampoEnviarDados3"));

			}

			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"movimentoArrecadador")) {

				pesquisarAvisoBancarioActionForm
						.setIdMovimento(httpServletRequest
								.getParameter("idCampoEnviarDados"));

				pesquisarAvisoBancarioActionForm
						.setCodigoBanco(httpServletRequest
								.getParameter("descricaoCampoEnviarDados1"));

				pesquisarAvisoBancarioActionForm
						.setCodigoRemessa(httpServletRequest
								.getParameter("descricaoCampoEnviarDados2"));

				pesquisarAvisoBancarioActionForm
						.setIdentificacaoServico(httpServletRequest
								.getParameter("descricaoCampoEnviarDados3"));

				pesquisarAvisoBancarioActionForm
						.setNumeroSequencial(httpServletRequest
								.getParameter("descricaoCampoEnviarDados4"));

			}
			if ((httpServletRequest.getParameter("tipoConsulta") != null)
					&& (httpServletRequest.getParameter("tipoConsulta")
							.equals("arrecadador"))) {

				pesquisarAvisoBancarioActionForm
						.setArrecadadorNome(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				pesquisarAvisoBancarioActionForm
						.setIdArrecadador(httpServletRequest
								.getParameter("idCampoEnviarDados"));

			}
	
		}
		
		return retorno;

	}
}
