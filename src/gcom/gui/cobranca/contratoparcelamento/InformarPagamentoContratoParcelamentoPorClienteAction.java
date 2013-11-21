package gcom.gui.cobranca.contratoparcelamento;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoPrestacao;
import gcom.cobranca.contratoparcelamento.InformarPagamentoContratoParcelamentoHelper;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

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
public class InformarPagamentoContratoParcelamentoPorClienteAction extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {

		
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InformarPagamentoContratoParcelamentoPorClienteActionForm form = (InformarPagamentoContratoParcelamentoPorClienteActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//[FS0001] - Verificar preenchimento dos campos
		this.verificarPreenchimentoCampos(form);
		
		InformarPagamentoContratoParcelamentoHelper helper = new InformarPagamentoContratoParcelamentoHelper(form);
		
		//[SB0003] – Efetuar Pagamento da Parcela do Contrato de Parcelamento Por Cliente
		Object[] dadosPagamento = fachada.efetuarPagamentoParcelaContratoParcelamentoPorCliente(helper, usuarioLogado);
		AvisoBancario avisoBancario = (AvisoBancario) dadosPagamento[0];
		
		//10. O sistema apresenta a tela de sucesso de acordo com as seguintes regras:
		//10.1.	Caso o valor do pagamento tenha sido superior ao valor efetivamente descontado do contrato
		if (avisoBancario.getValorArrecadacaoInformado().compareTo(avisoBancario.getValorArrecadacaoCalculado()) > 0) {

			montarPaginaSucesso(httpServletRequest, "Não há mais contas ou débitos a cobrar sem pagamento." + 
					" Em razão disso, o contrato foi concluído e um pagamento foi gerado com o valor pago a maior.",
					"Informar pagamento para outro Contrato de Parcel. por Cliente",
					"exibirInformarPagamentoContratoParcelamentoPorClienteAction.do?menu=sim",
					"gerarRelatorioEmitirComprovantePagContratoParcelamentoAction.do?tipoRelatorio=" 
						+ TarefaRelatorio.TIPO_PDF + "&numeroParcela=" + helper.getNumeroParcela()
						+ "&numeroContrato=" + helper.getNumeroContrato(),
					"Emitir Comprovante de Pagamento da Parcela");
		} else {

			String numeroPagamento = "";
			
			if (form.getNumeroContrato() == null
					|| form.getNumeroContrato().equals("")) {
				FiltroContratoParcelamentoPrestacao filtroContratoParcelamentoPrestacao = new FiltroContratoParcelamentoPrestacao();
				filtroContratoParcelamentoPrestacao.adicionarParametro(new ParametroSimples(FiltroContratoParcelamentoPrestacao.ID, avisoBancario.getNumeroDocumento()));
				filtroContratoParcelamentoPrestacao.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoPrestacao.CONTRATO_PARCEL);
				
				Collection colecaoContratoParcelamentoPrestacao = fachada.pesquisar(filtroContratoParcelamentoPrestacao, PrestacaoContratoParcelamento.class.getName());
				
				if (colecaoContratoParcelamentoPrestacao != null 
						&& !colecaoContratoParcelamentoPrestacao.isEmpty()) {
					PrestacaoContratoParcelamento prestacaoContratoParcelamento = (PrestacaoContratoParcelamento) 
						Util.retonarObjetoDeColecao(colecaoContratoParcelamentoPrestacao);
					numeroPagamento = prestacaoContratoParcelamento.getContratoParcelamento().getNumero();
				}
				
			} else {
				numeroPagamento = form.getNumeroContrato();
			}
			
			montarPaginaSucesso(httpServletRequest, "Pagamento da Parcela " + form.getNumeroParcela() +
					" do Contrato de Parcelamento por Cliente - " + numeroPagamento +
					" - efetuado com sucesso. ",
					"Informar pagamento para outro Contrato de Parcel. por Cliente",
					"exibirInformarPagamentoContratoParcelamentoPorClienteAction.do?menu=sim",
					"gerarRelatorioEmitirComprovantePagContratoParcelamentoAction.do?tipoRelatorio=" 
						+ TarefaRelatorio.TIPO_PDF + "&numeroParcela=" + helper.getNumeroParcela()
						+ "&numeroContrato=" + helper.getNumeroContrato(),
					"Emitir Comprovante de Pagamento da Parcela");
		}
		
		return retorno;
	}

	/**
	 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
	 * [FS0001] - Verificar preenchimento dos campos
	 * 
	 * @author Mariana Victor
	 * @since 06/06/2011
	 * */
	private void verificarPreenchimentoCampos(InformarPagamentoContratoParcelamentoPorClienteActionForm form) {
		
		if ((form.getIdCliente() == null || form.getIdCliente().trim().equals(""))
			&& (form.getNumeroContrato() == null || form.getNumeroContrato().trim().equals(""))) {

			throw new ActionServletException("atencao.contrato_parcelamento_por_cliente.informe.contrato_ou_cliente");
			
		}
		
		if (form.getIdRegistro() == null || form.getIdRegistro().trim().equals("")) {
			
			throw new ActionServletException("atencao.contrato_parcelamento_por_cliente.selecione.pelo_menos_um.contrato");
			
		}
		
		if (form.getIdArrecadador() == null || form.getIdArrecadador().trim().equals("")) {
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", new String[] {"Arrecadador"});
			
		}
		
		if (form.getDataPagamento() == null || form.getDataPagamento().trim().equals("")) {
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", new String[] {"Data de Pagamento"});
			
		} else if (Util.converteStringParaDate(form.getDataPagamento()).compareTo(new Date()) > 0) {
			
			throw new ActionServletException("atencao.contrato.superio.data.corrente", new String[]{Util.formatarData(new Date())});
			
		}
	}
}
