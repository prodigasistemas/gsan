package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.gui.arrecadacao.pagamento.ConsultarPagamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioPagamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

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
public class GerarRelatorioPagamentoAction extends
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

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarPagamentoActionForm consultarPagamentoActionForm = (ConsultarPagamentoActionForm) actionForm;

//		Collection colecaoPagamentos = (Collection) sessao
//				.getAttribute("colecaoPagamentosLocalidade");

		// Inicio da parte que vai mandar os parametros para o relatório

		Pagamento pagamentoParametrosInicial = new Pagamento();
		Pagamento pagamentoParametrosFinal = new Pagamento();

		String idImovel = null;
		String idCliente = null;
		String clienteRelacaoTipo = null;
		String idAvisoBancario = null;
		String idMovimentoArrecadador = null;
		String localidadeInicial = null;
		String localidadeFinal = null;
		String periodoArrecadacaoInicial = null;
		String periodoArrecadacaoFinal = null;
		String periodoPagamentoInicial = null;
		String periodoPagamentoFinal = null;
		String dataPagamentoInicial = null;
		String dataPagamentoFinal = null;
		String[] idsPagamentoSituacao = null;
		String[] idsArrecadacaoForma = null;
		String[] idsDocumentoTipo = null;
		String[] idsDebitoTipo = null;
		String opcaoPagamento = null;
        String valorPagamentoInicial = 
            consultarPagamentoActionForm.getValorPagamentoInicial();
        
        String valorPagamentoFinal = 
            consultarPagamentoActionForm.getValorPagamentoFinal();
		
		if (sessao.getAttribute("idImovelPrincipalAba") != null && !sessao.getAttribute("idImovelPrincipalAba").equals("")) {
			idImovel = (String) sessao.getAttribute("idImovelPrincipalAba");
			opcaoPagamento = "ambos";
		} else {
			idImovel = consultarPagamentoActionForm.getIdImovel();
			idCliente = consultarPagamentoActionForm.getIdCliente();
			clienteRelacaoTipo = consultarPagamentoActionForm
					.getClienteRelacaoTipo();
			idAvisoBancario = consultarPagamentoActionForm
					.getIdAvisoBancario();
			idMovimentoArrecadador = consultarPagamentoActionForm
					.getIdArrecadador();
			localidadeInicial = consultarPagamentoActionForm
					.getLocalidadeInicial();
			localidadeFinal = consultarPagamentoActionForm
					.getLocalidadeFinal();
			periodoArrecadacaoInicial = consultarPagamentoActionForm
					.getPeriodoArrecadacaoInicio();
			periodoArrecadacaoFinal = consultarPagamentoActionForm
					.getPeriodoArrecadacaoFim();
			periodoPagamentoInicial = consultarPagamentoActionForm
					.getPeriodoPagamentoInicio();
			periodoPagamentoFinal = consultarPagamentoActionForm
					.getPeriodoPagamentoFim();
			dataPagamentoInicial = consultarPagamentoActionForm
					.getDataPagamentoInicio();
			dataPagamentoFinal = consultarPagamentoActionForm
					.getDataPagamentoFim();
			idsPagamentoSituacao = consultarPagamentoActionForm
					.getPagamentoSituacao();
			idsArrecadacaoForma = consultarPagamentoActionForm
					.getArrecadacaoForma();
			idsDocumentoTipo = consultarPagamentoActionForm
					.getDocumentoTipo();
			idsDebitoTipo = consultarPagamentoActionForm.getDebitoTipoSelecionados();
			opcaoPagamento = consultarPagamentoActionForm.getOpcaoPagamento();
		}
		
		
		//caso venha da tela de manter pagamento o padrão será consultar o atual
		if(opcaoPagamento == null){
			opcaoPagamento = "atual";
		}

		// seta os parametros que serão mostrados no relatório
		if (periodoArrecadacaoInicial != null
				&& !periodoArrecadacaoInicial.equals("")) {
			pagamentoParametrosInicial.setAnoMesReferenciaPagamento(Util
					.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial));
			pagamentoParametrosFinal.setAnoMesReferenciaPagamento(Util
					.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal));
		}
		if (dataPagamentoInicial != null && !dataPagamentoInicial.equals("")) {
			pagamentoParametrosInicial.setDataPagamento(Util
					.converteStringParaDate(dataPagamentoInicial));
			pagamentoParametrosFinal.setDataPagamento(Util
					.converteStringParaDate(dataPagamentoFinal));
		}

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioPagamento relatorioPagamento = new RelatorioPagamento((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (sessao.getAttribute("idImovelPrincipalAba") != null && !sessao.getAttribute("idImovelPrincipalAba").equals("")) {
			relatorioPagamento.addParametro("relatorioConsultarImovel", true);
		}
		
		if (sessao.getAttribute("avisoBancarioHelper") != null) {
			relatorioPagamento.addParametro("relatorioConsultarAvisoBancario", true);
			relatorioPagamento.addParametro("avisoBancarioHelper", sessao.getAttribute("avisoBancarioHelper"));
			idAvisoBancario = (String) sessao.getAttribute("idAvisoBancario"); 
		}
		relatorioPagamento.addParametro("pagamentoParametrosInicial",
				pagamentoParametrosInicial);
		relatorioPagamento.addParametro("pagamentoParametrosFinal",
				pagamentoParametrosFinal);

		// Parâmetros de Pesquisa
		relatorioPagamento.addParametro("idImovel", idImovel);
		relatorioPagamento.addParametro("idCliente", idCliente);
		relatorioPagamento.addParametro("clienteRelacaoTipo",
				clienteRelacaoTipo);
		relatorioPagamento.addParametro("idAvisoBancario", idAvisoBancario);
		relatorioPagamento.addParametro("idMovimentoArrecadador",
				idMovimentoArrecadador);
		relatorioPagamento.addParametro("localidadeInicial", localidadeInicial);
		relatorioPagamento.addParametro("localidadeFinal", localidadeFinal);
		relatorioPagamento.addParametro("periodoArrecadacaoInicial",
				periodoArrecadacaoInicial);
		relatorioPagamento.addParametro("periodoArrecadacaoFinal",
				periodoArrecadacaoFinal);
		relatorioPagamento.addParametro("periodoPagamentoInicial",
				periodoPagamentoInicial);
		relatorioPagamento.addParametro("periodoPagamentoFinal",
				periodoPagamentoFinal);
		relatorioPagamento.addParametro("dataPagamentoInicial",
				Util.converteStringParaDate(dataPagamentoInicial));
		relatorioPagamento.addParametro("dataPagamentoFinal",
				Util.converteStringParaDate(dataPagamentoFinal));
		relatorioPagamento.addParametro("idsPagamentoSituacao",
				idsPagamentoSituacao);
		relatorioPagamento.addParametro("idsArrecadacaoForma",
				idsArrecadacaoForma);
		relatorioPagamento.addParametro("idsDocumentoTipo", idsDocumentoTipo);
		relatorioPagamento.addParametro("idsDebitoTipo", idsDebitoTipo);
		relatorioPagamento.addParametro("opcaoPagamento",opcaoPagamento);
        
        relatorioPagamento.addParametro( "valorPagamentoInicial", valorPagamentoInicial );
        relatorioPagamento.addParametro( "valorPagamentoFinal", valorPagamentoFinal );

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioPagamento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioPagamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}

