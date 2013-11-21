package gcom.gui.cobranca.contratoparcelamento;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.contratoparcelamento.InformarPagamentoContratoParcelamentoPorClienteHelper;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 * @since 27/05/2011
 */
public class ExibirInformarPagamentoContratoParcelamentoPorClienteAction extends GcomAction {

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
				.findForward("exibirInformarPagamentoContratoParcelamentoPorCliente");
		
		//String pagina = httpServletRequest.getParameter("page.offset");
	
		InformarPagamentoContratoParcelamentoPorClienteActionForm form = (InformarPagamentoContratoParcelamentoPorClienteActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
	
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o usuario que está logado na aplicação
		//Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// Limpar Cliente
		if (httpServletRequest.getParameter("limpar") != null
			&& httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim")) {
			
			form.setIdCliente("");
			form.setNomeCliente("");
			form.setTotalSelecionado("");
			form.setIdRegistro("");
			form.setIdArrecadador("");
			form.setNomeArrecadador("");
			form.setNumeroParcela("");
			form.setValorParcelado("");
			form.setDataPagamento("");
			sessao.removeAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper");
			sessao.removeAttribute("confirmarPagamento");

        // Limpar Lista de Contratos
		} else if (httpServletRequest.getParameter("limparContratos") != null
			&& httpServletRequest.getParameter("limparContratos").equalsIgnoreCase("sim")) {
			
			form.setTotalSelecionado("");
			form.setIdRegistro("");
			form.setIdArrecadador("");
			form.setNomeArrecadador("");
			form.setNumeroParcela("");
			form.setValorParcelado("");
			form.setDataPagamento("");
			sessao.removeAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper");
			sessao.removeAttribute("confirmarPagamento");
			
		// Limpar Cliente
		} else if (httpServletRequest.getParameter("limpar") != null
			&& httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim")) {
			
		// Pesquisar Dados do Contrato Selecionado
		} else if (httpServletRequest.getParameter("selecionarDadosContrato") != null
				&& httpServletRequest.getParameter("selecionarDadosContrato").equalsIgnoreCase("sim")
				&& httpServletRequest.getParameter("idContrato") != null
				&& !httpServletRequest.getParameter("idContrato").equalsIgnoreCase("")) {
			
			Integer idContrato = new Integer(httpServletRequest.getParameter("idContrato"));
			
			Object[] dadosContrato = fachada.pesquisarDadosContratoParcelamentoPorClienteSelecionado(idContrato);
			
			if (dadosContrato != null){
				if (dadosContrato[0] != null) {
					Integer numeroParcela = (Integer) dadosContrato[0];
					form.setNumeroParcela(numeroParcela.toString());
				}
				
				if (dadosContrato[1] != null) {
					BigDecimal valorParcela = (BigDecimal) dadosContrato[1];
					form.setValorParcelado(Util.formatarMoedaReal(valorParcela));
				}
				
				if (dadosContrato[2] != null) {
					Date dataVencimento = (Date) dadosContrato[2];
					form.setDataPagamento(Util.formatarData(dataVencimento));
				}
			}
			
		} else {
		
			this.pesquisarCamposEnter(httpServletRequest,form, this.getFachada(), sessao);
		}

		if(form.getIdCliente() != null 
			&& !form.getIdCliente().equals("")) {
			this.selecionarContratos(httpServletRequest,
					form, fachada, sessao);
		}
		
		return retorno;
	}


	private void pesquisarCamposEnter(
			HttpServletRequest httpServletRequest,
			InformarPagamentoContratoParcelamentoPorClienteActionForm form,
			Fachada fachada,
			HttpSession sessao) {
		
		String idCliente = form.getIdCliente();

		// Pesquisa o cliente
		if (idCliente != null && !idCliente.trim().equals("")) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			//[FS0004] - Validar cliente
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);
				form.setIdCliente(cliente.getId()
						.toString());
				form.setNomeCliente(cliente
						.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
				
				Collection<Object[]> colecaoDadosContrato = fachada.pesquisarDadosContratoParcelamentoPorCliente(new Integer(idCliente));
				
				//[FS0005] – Verificar existência de contratos para o cliente
				//Caso não existam contratos para o código do cliente informado 
				if (colecaoDadosContrato == null || colecaoDadosContrato.isEmpty()) {

					form.setTotalSelecionado("");
					sessao.removeAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper");
					sessao.removeAttribute("confirmarPagamento");
					throw new ActionServletException(
							"atencao.contrato_parcelamento_por_cliente.inexistente.selecione_outro_cliente",
								new String[] {cliente.getNome()});
				}
				
				Collection<InformarPagamentoContratoParcelamentoPorClienteHelper> colecaoHelper = new ArrayList();
				
				Iterator iterator = colecaoDadosContrato.iterator();
				
				while (iterator.hasNext()) {
					InformarPagamentoContratoParcelamentoPorClienteHelper helper = new InformarPagamentoContratoParcelamentoPorClienteHelper();
					Object[] dadosContrato = (Object[]) iterator.next();
					
					helper.setIdContrato(((Integer) dadosContrato[0]).toString());
					helper.setNumeroContrato(((Integer) dadosContrato[1]).toString());
					helper.setValorParcelado(Util.formatarMoedaReal((BigDecimal) dadosContrato[2]));
					helper.setNumeroParcelas(((Integer) dadosContrato[3]).toString());
					helper.setValorPago(Util.formatarMoedaReal((BigDecimal) dadosContrato[4]));
					
					Integer numeroParcelasPagas = fachada.pesquisarDadosContratoParcelamentoNumeroParcelasPagas((Integer) dadosContrato[0]);
					
					if (numeroParcelasPagas != null) {
						helper.setNumeroParcelasPagas(numeroParcelasPagas.toString());
					} else {
						helper.setNumeroParcelasPagas("");
					}
					
					colecaoHelper.add(helper);					
				}
				
				form.setTotalSelecionado("sim");
				sessao.setAttribute("confirmarPagamento", true);
				sessao.setAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper",colecaoHelper);
				
			} else {
				form.setIdCliente("");
				form.setNomeCliente("Cliente inexistente. Informe ou pesquise outro Cliente.");
			}

		} else {
			form.setIdCliente("");
			form.setNomeCliente("");
		}

		String numeroContrato = form.getNumeroContrato();
		
		// Pesquisa o contrato
		if (numeroContrato != null && !numeroContrato.trim().equals("")
				&& (httpServletRequest.getParameter("limpar") == null
				|| !httpServletRequest.getParameter("limpar").equalsIgnoreCase("sim"))) {

			Object[] dadosContrato = fachada.pesquisarDadosContratoParcelamento(numeroContrato);

			//[FS0003] – Validar contrato
			//Caso não exista o contrato de parcelamento por cliente com o número informado 
			if (dadosContrato == null || dadosContrato[0] == null) {

				form.setTotalSelecionado("");
				sessao.removeAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper");
				sessao.removeAttribute("confirmarPagamento");
				throw new ActionServletException(
						"atencao.contrato_parcelamento_por_cliente.inexistente", new String[] {numeroContrato});
				
			}
			
			//Caso a forma de pagamento do contrato não seja “Cobrança por ICMS” 
			if (((Integer)dadosContrato[2]).compareTo(CobrancaForma.COBRANCA_POR_ICMS) != 0) {

				form.setTotalSelecionado("");
				sessao.removeAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper");
				sessao.removeAttribute("confirmarPagamento");
				throw new ActionServletException(
						"atencao.contrato_parcelamento_por_cliente.nao_possivel.informar_pagamento",
							new String[] {numeroContrato, (String) dadosContrato[3]});
			}

			//Caso o contrato esteja pago
			if (dadosContrato[4] == null || ((BigDecimal)dadosContrato[4]).compareTo(BigDecimal.ZERO) == 0) {

				form.setTotalSelecionado("");
				sessao.removeAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper");
				sessao.removeAttribute("confirmarPagamento");
				throw new ActionServletException(
						"atencao.contrato_parcelamento_por_cliente.ja_pago",
							new String[] {numeroContrato});
				
			}
			
			//Caso o contrato esteja encerrado
			if (((Integer)dadosContrato[5]).compareTo(ParcelamentoSituacao.NORMAL) != 0) {

				form.setTotalSelecionado("");
				sessao.removeAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper");
				sessao.removeAttribute("confirmarPagamento");
				throw new ActionServletException(
						"atencao.contrato_parcelamento_por_cliente.encerrado",
							new String[] {numeroContrato, (String)dadosContrato[6]});
				
			}
			
			Integer numeroParcelasPagas = fachada.pesquisarDadosContratoParcelamentoNumeroParcelasPagas((Integer) dadosContrato[0]);
			
			Collection<InformarPagamentoContratoParcelamentoPorClienteHelper> colecaoHelper = new ArrayList();
			
			InformarPagamentoContratoParcelamentoPorClienteHelper helper = new InformarPagamentoContratoParcelamentoPorClienteHelper();
			helper.setIdContrato(((Integer) dadosContrato[0]).toString());
			helper.setNumeroContrato((dadosContrato[1]).toString());
			helper.setValorParcelado(Util.formatarMoedaReal((BigDecimal) dadosContrato[7]));
			helper.setNumeroParcelas(((Integer) dadosContrato[8]).toString());
			helper.setValorPago(Util.formatarMoedaReal((BigDecimal) dadosContrato[9]));
			if (numeroParcelasPagas != null) {
				helper.setNumeroParcelasPagas(numeroParcelasPagas.toString());
			} else {
				helper.setNumeroParcelasPagas("");
			}
			
			colecaoHelper.add(helper);

			form.setTotalSelecionado("sim");
			sessao.setAttribute("confirmarPagamento", true);
			sessao.setAttribute("colecaoInformarPagamentoContratoParcelamentoPorClienteHelper",colecaoHelper);
			
		}
		
		String idArrecadador = form.getIdArrecadador();
		// Pesquisa o arrecadador
		if (idArrecadador != null
				&& !idArrecadador.trim().equals("")) {

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE,idArrecadador));
			
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection colecaoArrecadador = this.getFachada().pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			//[FS0007] - Verificar existência do arrecadador
			if (colecaoArrecadador != null
					&& !colecaoArrecadador.isEmpty()) {

				Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);

				form.setIdArrecadador(arrecadador.getCodigoAgente().toString());
				form.setNomeArrecadador(arrecadador.getCliente().getNome());
				
				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "true");
				
			} else {

				form.setIdArrecadador("");
				
				form.setNomeArrecadador("Arrecadador inexistente. Informe ou pesquise o arrecadador");
				
				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "exception");

			}
		}
	}
	

	private void selecionarContratos(
			HttpServletRequest httpServletRequest,
			InformarPagamentoContratoParcelamentoPorClienteActionForm form,
			Fachada fachada,
			HttpSession sessao) {
		
	}
}
