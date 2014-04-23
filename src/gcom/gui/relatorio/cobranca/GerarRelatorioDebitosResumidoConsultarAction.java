package gcom.gui.relatorio.cobranca;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.gui.cobranca.ConsultarDebitoClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioConsultarDebitosResumido;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioDebitosResumidoConsultarAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;
		
		ConsultarDebitoClienteActionForm consultarDebitoClienteActionForm = (ConsultarDebitoClienteActionForm) actionForm;

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoContaValores = new ArrayList();
		Collection colecaoDebitoACobrar = new ArrayList();
		Collection colecaoCreditoARealizar = new ArrayList();
		Collection colecaoGuiasPagamento = new ArrayList();
		String valorTotalDebitos = null;
		String valorTotalDebitosAtualizado = null;

		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValores = (Collection) sessao
					.getAttribute("colecaoContaValores");
		}

		if (sessao.getAttribute("colecaoDebitoACobrar") != null) {
			colecaoDebitoACobrar = (Collection) sessao
					.getAttribute("colecaoDebitoACobrar");
		}

		if (sessao.getAttribute("colecaoCreditoARealizar") != null) {
			colecaoCreditoARealizar = (Collection) sessao
					.getAttribute("colecaoCreditoARealizar");
		}

		if (sessao.getAttribute("colecaoGuiaPagamentoValores") != null) {
			colecaoGuiasPagamento = (Collection) sessao
					.getAttribute("colecaoGuiaPagamentoValores");
		}

		if (sessao.getAttribute("valorTotalSemAcrescimo") != null) {
			valorTotalDebitos = (String) sessao
					.getAttribute("valorTotalSemAcrescimo");
		}

		if (sessao.getAttribute("valorTotalComAcrescimo") != null) {
			valorTotalDebitosAtualizado = (String) sessao
					.getAttribute("valorTotalComAcrescimo");
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioConsultarDebitosResumido relatorioConsultarDebitosResumido = new RelatorioConsultarDebitosResumido(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		relatorioConsultarDebitosResumido.addParametro("colecaoContaValores",
				colecaoContaValores);
		relatorioConsultarDebitosResumido.addParametro("colecaoDebitoACobrar",
				colecaoDebitoACobrar);
		relatorioConsultarDebitosResumido.addParametro(
				"colecaoCreditoARealizar", colecaoCreditoARealizar);
		relatorioConsultarDebitosResumido.addParametro("colecaoGuiasPagamento",
				colecaoGuiasPagamento);

		ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) sessao.getAttribute("tipoRelacao");
		
		if (consultarDebitoClienteActionForm.getCodigoCliente() != null && !consultarDebitoClienteActionForm.getCodigoCliente().trim().equals("")) {
			relatorioConsultarDebitosResumido.addParametro("codigoCliente",
					consultarDebitoClienteActionForm.getCodigoCliente());
			relatorioConsultarDebitosResumido.addParametro("nomeCliente",
					consultarDebitoClienteActionForm.getNomeCliente());
			relatorioConsultarDebitosResumido.addParametro("cpfCnpj",
					consultarDebitoClienteActionForm.getCpfCnpj());
			relatorioConsultarDebitosResumido.addParametro("tipoRelacao", tipoRelacao);
		} else {
			relatorioConsultarDebitosResumido.addParametro("codigoCliente",
					consultarDebitoClienteActionForm.getCodigoClienteSuperior());
			relatorioConsultarDebitosResumido.addParametro("nomeCliente",
					consultarDebitoClienteActionForm.getNomeClienteSuperior());
			relatorioConsultarDebitosResumido.addParametro("cpfCnpj",
					consultarDebitoClienteActionForm.getCpfCnpj());
			relatorioConsultarDebitosResumido.addParametro("tipoRelacao", null);
		}
		relatorioConsultarDebitosResumido.addParametro(
				"periodoReferenciaInicial", consultarDebitoClienteActionForm
						.getReferenciaInicial());
		relatorioConsultarDebitosResumido.addParametro(
				"periodoReferenciaFinal", consultarDebitoClienteActionForm
						.getReferenciaFinal());
		relatorioConsultarDebitosResumido.addParametro("dataVencimentoInicial",
				consultarDebitoClienteActionForm.getDataVencimentoInicial());
		relatorioConsultarDebitosResumido.addParametro("dataVencimentoFinal",
				consultarDebitoClienteActionForm.getDataVencimentoFinal());
		relatorioConsultarDebitosResumido.addParametro("tipoRelacao",
				tipoRelacao);

		relatorioConsultarDebitosResumido.addParametro("valorTotalDebitos",
				valorTotalDebitos);
		relatorioConsultarDebitosResumido.addParametro(
				"valorTotalDebitosAtualizado", valorTotalDebitosAtualizado);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioConsultarDebitosResumido.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorioConsultarDebitosResumido,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
