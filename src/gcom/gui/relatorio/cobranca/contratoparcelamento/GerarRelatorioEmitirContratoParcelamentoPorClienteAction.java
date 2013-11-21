package gcom.gui.relatorio.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioEmitirContratoParcelamentoPorCliente;
import gcom.relatorio.cobranca.contratoparcelamento.FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioEmitirContratoParcelamentoPorClienteAction  extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		ContratoParcelamento contratoParcelamento = null;
		
		if (httpServletRequest.getParameter("inserirContrato") != null
				&& httpServletRequest.getParameter("inserirContrato").toString().trim().equalsIgnoreCase("sim")
				&& httpServletRequest.getParameter("contratoNumero") != null
				&& !httpServletRequest.getParameter("contratoNumero").toString().trim().equals("")) {
			
			String numeroContrato = httpServletRequest.getParameter("contratoNumero");
			contratoParcelamento = new ContratoParcelamento();
			contratoParcelamento.setNumero(numeroContrato);
			
		} else {
			contratoParcelamento = (ContratoParcelamento) sessao.getAttribute("contratoManter");
		}
		
		//[FS0001] – Verificar existência de contrato com o número recebido
		ContratoParcelamento contratoParcelamentoBase = Fachada.getInstancia()
			.pesquisarContratoParcelamento(contratoParcelamento.getNumero());
		
		if (contratoParcelamentoBase == null || contratoParcelamentoBase.getId() == null) {
			throw new ActionServletException(
					"atencao.contrato_parcelamento_por_cliente.inexistente", 
					new String[] { contratoParcelamento.getNumero() } );
		}
		
		Collection<PrestacaoContratoParcelamento> colecaoParcelas = (Collection<PrestacaoContratoParcelamento>)
			sessao.getAttribute("collParcelas");
		
		if (colecaoParcelas == null || colecaoParcelas.isEmpty()) {
			colecaoParcelas = fachada.obterDadosPrestacoesContratoParcelamento(contratoParcelamentoBase.getId());
		}
		
		Collection<ContratoParcelamentoItem> colecaoContaItens = (Collection<ContratoParcelamentoItem>)
			sessao.getAttribute("colecaoContaItens");

		if (colecaoContaItens == null || colecaoContaItens.isEmpty()) {
			
			colecaoContaItens = fachada.pesquisarContratoParcelamentoItem(
					contratoParcelamentoBase.getId(), DocumentoTipo.CONTA);
			
		}
		
		Collection<ContratoParcelamentoItem> colecaoItensDebitoACobrar = (Collection<ContratoParcelamentoItem>)
			sessao.getAttribute("colecaoItensDebitoACobrar");

		if (colecaoItensDebitoACobrar == null || colecaoItensDebitoACobrar.isEmpty()) {
			colecaoItensDebitoACobrar = fachada.pesquisarContratoParcelamentoItem(
					contratoParcelamentoBase.getId(), DocumentoTipo.DEBITO_A_COBRAR);
		}
		
		Cliente cliente = null;
		
		if (sessao.getAttribute("clienteContrato") != null
				&& !sessao.getAttribute("clienteContrato").equals("")) {
			
			cliente = (Cliente) sessao.getAttribute("clienteContrato");
			
		} else {
			ContratoParcelamentoCliente contratoParcelamentoCliente = fachada.pesquisarClienteContrato(
					contratoParcelamentoBase.getId());
			cliente = contratoParcelamentoCliente.getCliente();
		}
		
		FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper helper = this.criarHelper(contratoParcelamentoBase, 
				cliente, colecaoParcelas, colecaoContaItens, colecaoItensDebitoACobrar);
		
		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
		RelatorioEmitirContratoParcelamentoPorCliente relatorioEmitirContratoParcelamentoPorCliente = new RelatorioEmitirContratoParcelamentoPorCliente(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		//Adiciona filtro escolhido pelo usuario ao relatorio
		relatorioEmitirContratoParcelamentoPorCliente.addParametro("filtrarRelatorioEmitirContratoParcelamentoPorClienteHelper", helper);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioEmitirContratoParcelamentoPorCliente.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioEmitirContratoParcelamentoPorCliente,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

	/**
	 * Recupera os dados usados no formulário que chamou o relatório
	 * */
	private FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper criarHelper(
			ContratoParcelamento contratoParcelamento, Cliente cliente,
			Collection<PrestacaoContratoParcelamento> colecaoParcelas,
			Collection<ContratoParcelamentoItem> colecaoContaItens, Collection<ContratoParcelamentoItem> colecaoItensDebitoACobrar) {
		
		FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper retorno = new FiltrarRelatorioEmitirContratoParcelamentoPorClienteHelper();
		
		if (contratoParcelamento != null) {
			retorno.setContratoParcelamento(
					contratoParcelamento);
		}

		if (contratoParcelamento.getUsuarioResponsavel() != null
				&& contratoParcelamento.getUsuarioResponsavel().getId() != null
				&& contratoParcelamento.getUsuarioResponsavel().getNomeUsuario() != null
				&& !contratoParcelamento.getUsuarioResponsavel().getNomeUsuario().equals("")) {
			
			retorno.setUsuarioResponsavel(contratoParcelamento.getUsuarioResponsavel().getLogin()
					+ " - " + contratoParcelamento.getUsuarioResponsavel().getNomeUsuario());
			
		}

		if (cliente != null) {
			retorno.setCliente(cliente);
		}
		
		if (colecaoParcelas != null && !colecaoParcelas.isEmpty()) {
			retorno.setColecaoParcelas(colecaoParcelas);
		}
		
		if (colecaoContaItens != null && !colecaoContaItens.isEmpty()) {
			retorno.setColecaoContaItens(colecaoContaItens);
		}
		
		if (colecaoItensDebitoACobrar != null && !colecaoItensDebitoACobrar.isEmpty()) {
			retorno.setColecaoItensDebitoACobrar(colecaoItensDebitoACobrar);
		}
		
		return retorno;
	}

}
