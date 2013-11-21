package gcom.gui.cobranca.contratoparcelamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamentoHelper;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
 * 
 * Este caso de uso permite emitir o extrato de uma ou todas as prestações 
 *  do contrato de parcelamento por cliente.
 * 
 * @author Mariana Victor
 * @since 29/07/2011
 */
public class ExibirEmitirExtratoContratoParcelamentoPorClienteAction extends GcomAction {

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
				.findForward("exibirEmitirExtratoContratoParcelamentoPorCliente");
		
		EmitirExtratoContratoParcelamentoPorClienteActionForm form = (EmitirExtratoContratoParcelamentoPorClienteActionForm) actionForm;
	
		Fachada fachada = Fachada.getInstancia();
	
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("inserirContrato") != null
				&& httpServletRequest.getParameter("inserirContrato").toString().trim().equalsIgnoreCase("sim")) {
			sessao.removeAttribute("exibirBotaoVoltar");
		} else {
			sessao.setAttribute("exibirBotaoVoltar", true);
		}
		
		if (httpServletRequest.getParameter("contratoAtualizarNumero") != null
				&& !httpServletRequest.getParameter("contratoAtualizarNumero").toString().trim().equals("")) {
			
			String numeroContrato = httpServletRequest.getParameter("contratoAtualizarNumero").toString();
			
			// 1. O sistema obtém os dados do contrato de parcelamento por cliente
			ContratoParcelamento contratoParcelamento = fachada.obterDadosContratoParcelamento(numeroContrato);
			
			// [FS0002] ? Verificar existência de contrato com o número recebido
			// Caso não exista o contrato de parcelamento por cliente com o número recebido 
			if (contratoParcelamento == null
					|| contratoParcelamento.getId() == null) {

				throw new ActionServletException(
					"atencao.numero.contrato.parcelamento.nao.existe", numeroContrato.toString());
			}
			
			// [FS0003] - Verificar forma de pagamento
			// Caso a forma de pagamento do contrato não seja COBRANCA POR EXTRATO 
			if (contratoParcelamento.getCobrancaForma() == null
					|| contratoParcelamento.getCobrancaForma().getId() == null
					|| contratoParcelamento.getCobrancaForma().getId().compareTo(
							CobrancaForma.COBRANCA_POR_EXTRATO) != 0) {

				throw new ActionServletException(
					"atencao.nao_possivel.emitir_extrato.cobranca_form.nao_permitida", 
						new String[] {
							numeroContrato.toString(),
							contratoParcelamento.getCobrancaForma().getDescricao()
							});
			}
			
			//[FS0004] - Verificar contrato pago ou encerrado.
			// Caso o contrato esteja pago
			if (contratoParcelamento.getValorParcelamentoACobrar() == null
					|| contratoParcelamento.getValorParcelamentoACobrar().compareTo(BigDecimal.ZERO) == 0) {

				throw new ActionServletException(
					"atencao.contrato_parcelamento.ja_pago", numeroContrato.toString());
			}
			//Caso o contrato esteja encerrado 
			if (contratoParcelamento.getParcelamentoSituacao() == null
					|| contratoParcelamento.getParcelamentoSituacao().getId() == null
					|| contratoParcelamento.getParcelamentoSituacao().getId().compareTo(ParcelamentoSituacao.NORMAL) != 0) {

				throw new ActionServletException(
					"atencao.contrato_parcelamento.nao_possivel.emitir_extrato", 
						new String[] {
							numeroContrato.toString(),
							contratoParcelamento.getParcelamentoSituacao().getDescricao()
							});
			}
			
			contratoParcelamento.setNumero(numeroContrato);

			Integer idClienteContrato = fachada.pesquisarIdClientecontrato(contratoParcelamento.getId());
			
			sessao.setAttribute("idClienteContratoConsultar", idClienteContrato);
			
			this.carregarCamposForm(contratoParcelamento, form, sessao);
			
		} else {
			sessao.removeAttribute("idClienteContratoConsultar");
			
			throw new ActionServletException(
				"atencao.naoinformado", "Número do Contrato de Parcelamento");
		}
		
		return retorno;
	}

	private void carregarCamposForm(ContratoParcelamento contratoParcelamento, 
			EmitirExtratoContratoParcelamentoPorClienteActionForm form, HttpSession sessao){
		
		this.carregarDadosContrato(contratoParcelamento, form, sessao);
		
		this.carregarDadosParcelamento(contratoParcelamento, form, sessao);
		
	}
	
	private void carregarDadosContrato(ContratoParcelamento contratoParcelamento, 
			EmitirExtratoContratoParcelamentoPorClienteActionForm form, HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();

		// 2.1.1. Número do Contrato
		form.setNumeroContrato(contratoParcelamento.getNumero());
		
		FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = new FiltroContratoParcelamentoCliente();
		filtroContratoParcelamentoCliente.adicionarParametro(new ParametroSimples(
				FiltroContratoParcelamentoCliente.ID_CONTRATO, contratoParcelamento.getId()));
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoCliente.CLIENTE);
		filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamentoCliente.CLIENTE_SUPERIOR);
		
		Collection colecaoContratoParcelamentoCliente = fachada
			.pesquisar(filtroContratoParcelamentoCliente, ContratoParcelamentoCliente.class.getName());
		
		// 2.1.2. Cliente
		if (colecaoContratoParcelamentoCliente != null
				&& !colecaoContratoParcelamentoCliente.isEmpty()) {
			
			ContratoParcelamentoCliente contratoParcelamentoCliente = this.retornaClienteSuperior(colecaoContratoParcelamentoCliente);
			
			// caso o contrato tenha sido implantado para um cliente superior,
			if (contratoParcelamentoCliente != null) {
				//código 
				form.setCodigoCliente(contratoParcelamentoCliente
						.getCliente().getId().toString());
				//nome 
				form.setNomeCliente(contratoParcelamentoCliente
						.getCliente().getNome());
				//CNPJ 
				form.setCnpjCliente(contratoParcelamentoCliente
						.getCliente().getCnpj());
				
				sessao.removeAttribute("exibirTipoRelacaoCliente");
			} else {
				// caso contrário, ou seja, o contrato não foi implantado para um cliente superior
				contratoParcelamentoCliente = (ContratoParcelamentoCliente) 
					Util.retonarObjetoDeColecao(colecaoContratoParcelamentoCliente);
				
				// código 
				form.setCodigoCliente(contratoParcelamentoCliente
						.getCliente().getId().toString());
				//nome 
				form.setNomeCliente(contratoParcelamentoCliente
						.getCliente().getNome());
				//CNPJ 
				form.setCnpjCliente(contratoParcelamentoCliente
						.getCliente().getCnpj());
				//tipo da relação 
				form.setTipoRelacao(contratoParcelamento.getRelacaoCliente().getDescricao());
				
				sessao.setAttribute("exibirTipoRelacaoCliente", true);
			}
		}
		
		// 2.1.3. Data Implantação do Contrato
		form.setDataImplantacao(Util.formatarData(contratoParcelamento.getDataImplantacao()));
		
	}
	
	private void carregarDadosParcelamento(ContratoParcelamento contratoParcelamento, 
			EmitirExtratoContratoParcelamentoPorClienteActionForm form, HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		// 2.2.	Dados do Parcelamento 
		// 2.2.1. Parcelas 
		Collection<PrestacaoContratoParcelamento> colecaoPrestacoes = fachada.
			obterDadosPrestacoesContratoParcelamento(contratoParcelamento.getId());
		
		Collection<PrestacaoContratoParcelamentoHelper> colecaoHelper = new ArrayList<PrestacaoContratoParcelamentoHelper>();
		
		if (colecaoPrestacoes != null && !colecaoPrestacoes.isEmpty()) {
			Iterator iterator = colecaoPrestacoes.iterator();
			
			while(iterator.hasNext()) {
				PrestacaoContratoParcelamento prestacaoContratoParcelamento = (PrestacaoContratoParcelamento) iterator.next();
				
				PrestacaoContratoParcelamentoHelper helper = new PrestacaoContratoParcelamentoHelper();

				helper.setIdParcela(prestacaoContratoParcelamento.getId());
				
				// 2.2.1.1.	Parcela/No. Prestações 
				helper.setNumeroParcela(prestacaoContratoParcelamento.getNumero().toString());
				helper.setNumeroPrestacoes("" + colecaoPrestacoes.size());
				
				// 2.2.1.2.	Data de Vencimento 
				helper.setDataVencimento(Util.formatarData(prestacaoContratoParcelamento.getDataVencimento()));
				
				// 2.2.1.3.	Valor da Parcela 
				helper.setValorParcela(Util.formatarMoedaReal(prestacaoContratoParcelamento.getValor()));
				
				// 2.2.1.4.	Situação
				// caso a parcela já tenha sido paga 
				if (fachada.verificaPrestacaoPaga(prestacaoContratoParcelamento.getId())) {
					helper.setSituacao("Paga");
				}
				
				colecaoHelper.add(helper);
			}
			
			sessao.setAttribute("colecaoPrestacaoContratoParcelamentoHelper", colecaoHelper);
		}
		
	}
	
	public ContratoParcelamentoCliente retornaClienteSuperior(Collection<ContratoParcelamentoCliente> colecaoContratoParcelamentoCliente) {
		Iterator iterator = colecaoContratoParcelamentoCliente.iterator();
		
		while(iterator.hasNext()) {
			ContratoParcelamentoCliente contratoParcelamentoCliente = (ContratoParcelamentoCliente) iterator.next();
			if (contratoParcelamentoCliente.getIndicadorClienteSuperior() != null
					&& contratoParcelamentoCliente.getIndicadorClienteSuperior().compareTo(ConstantesSistema.SIM) == 0) {
				return contratoParcelamentoCliente;
			}
		}
		return null;
	}
}
