package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
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
public class ExibirPesquisarMovimentoArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarMovimentoArrecadador");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarMovimentoArrecadadorActionForm pesquisarMovimentoArrecadadorActionForm = (PesquisarMovimentoArrecadadorActionForm) actionForm;
		
		if (httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null) {
			
			pesquisarMovimentoArrecadadorActionForm.setIdBanco("");
			//pesquisarMovimentoArrecadadorActionForm.setTipoRemessa("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			//pesquisarMovimentoArrecadadorActionForm.setIdentificacaoServico("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
			pesquisarMovimentoArrecadadorActionForm.setNumeroSequencialArquivo("");
			pesquisarMovimentoArrecadadorActionForm.setDataMovimentoInicio("");
			pesquisarMovimentoArrecadadorActionForm.setDataMovimentoFim("");
			pesquisarMovimentoArrecadadorActionForm.setArrecadadorNome("");
		}
		
		// Carregar a data corrente do sistema
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));
		
		String idDigitadoEnterArrecadador = (String) pesquisarMovimentoArrecadadorActionForm
		.getIdBanco();
		
		// Verifica se o código foi digitado
		if ((httpServletRequest.getParameter("tipoConsulta") != null)
				&& (httpServletRequest.getParameter("tipoConsulta")
						.equals("arrecadador"))) {

			pesquisarMovimentoArrecadadorActionForm
					.setArrecadadorNome(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));
			pesquisarMovimentoArrecadadorActionForm
					.setIdBanco(httpServletRequest
							.getParameter("idCampoEnviarDados"));

		} else {
			if (idDigitadoEnterArrecadador != null
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
					pesquisarMovimentoArrecadadorActionForm.setIdBanco(""
							+ ((Arrecadador) ((List) arrecadadorEncontrado)
									.get(0)).getCodigoAgente());
					pesquisarMovimentoArrecadadorActionForm
							.setArrecadadorNome(""
									+ ((Arrecadador) ((List) arrecadadorEncontrado)
											.get(0)).getCliente().getNome());
					httpServletRequest.setAttribute(
							"idArrecadadorNaoEncontrado", "true");
					httpServletRequest.setAttribute("nomeCampo", "tipoRemessa");

				} else {

					pesquisarMovimentoArrecadadorActionForm.setIdBanco("");
					pesquisarMovimentoArrecadadorActionForm
							.setArrecadadorNome("ARRECADADOR INEXISTENTE");

					httpServletRequest.setAttribute(
							"idArrecadadorNaoEncontrado", "exception");

				}
			}
		}

		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaMovimentoArrecadador") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaMovimentoArrecadador",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaMovimentoArrecadador"));
		}

		// limpa o parametro passado no movimento_arrecadador_pesquisar.jsp da
		// sessao
		sessao.removeAttribute("caminhoRetornoTelaPesquisaArrecadador");

		return retorno;

	}

}
