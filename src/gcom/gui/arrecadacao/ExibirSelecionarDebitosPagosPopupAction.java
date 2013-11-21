package gcom.gui.arrecadacao;

import java.util.Collection;

import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.cobranca.bean.DebitoACobrarValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
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

public class ExibirSelecionarDebitosPagosPopupAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping.findForward("selecionarDebitosPagosPopup");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

		SelecionarDebitosPagosPopupActionForm form = (SelecionarDebitosPagosPopupActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		

		if (httpServletRequest.getParameter("abrirPopup") != null && 
				httpServletRequest.getParameter("abrirPopup").trim().equalsIgnoreCase("SIM") &&
				httpServletRequest.getParameter("idImovel") != null && 
				!httpServletRequest.getParameter("idImovel").trim().equals("")) {

			String idImovel = httpServletRequest.getParameter("idImovel");
			String descricaoImovel = httpServletRequest.getParameter("descricaoImovel");

			form.setIdImovel(idImovel);
			form.setDescricaoImovel(descricaoImovel);

			// 1.1.	Tipo do Documento (obrigatório) 
			//   seleciona a partir da tabela DOCUMENTO_TIPO os tipos de documento 
			//   correspondentes a Conta, Guia de Pagamento e Débito A Cobrar
			FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
			filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
					FiltroDocumentoTipo.ID, DocumentoTipo.CONTA,
					ParametroSimples.CONECTOR_OR));
			filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
					FiltroDocumentoTipo.ID, DocumentoTipo.GUIA_PAGAMENTO,
					ParametroSimples.CONECTOR_OR));
			filtroDocumentoTipo.adicionarParametro(new ParametroSimples(
					FiltroDocumentoTipo.ID, DocumentoTipo.DEBITO_A_COBRAR));
			
			Collection<DocumentoTipo> colecaoDocumentoTipo = fachada.pesquisar(
					filtroDocumentoTipo, DocumentoTipo.class.getName());

			if (colecaoDocumentoTipo == null || colecaoDocumentoTipo.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Tipo de Documento");
			} else {
				sessao.setAttribute("colecaoDocumentoTipo", colecaoDocumentoTipo);
				form.setIdTipoDocumento("-1");
				form.setReferenciaConta("");
				form.setDescricaoReferenciaConta("");
				form.setIdGuiaPagamento("");
				form.setDescricaoGuiaPagamento("");
				form.setIdDebitoACobrar("");
				form.setDescricaoDebitoACobrar("");
			}
			
		}
     

		// [FS0012] - Verificar existência da conta
		String referenciaContaDigitadoEnter = form.getReferenciaConta();
		String codigoImovel = form.getIdImovel();

		// Caso o usuário tenha informado a referência da conta
		if (referenciaContaDigitadoEnter != null
				&& !referenciaContaDigitadoEnter.trim().equalsIgnoreCase("")) {

			if (codigoImovel == null
					|| codigoImovel.trim().equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,
						"Matrícula do Imóvel");
			}

			// Recupera a conta do imóvel com a referência informada
			Conta contaEncontrada = fachada.pesquisarContaDigitada(
					codigoImovel, referenciaContaDigitadoEnter);

			
			// [FS0012] - Verificar existência do débito.
			if (contaEncontrada != null) {				
				form.setReferenciaConta(referenciaContaDigitadoEnter);
				form.setDescricaoReferenciaConta(Util.formatarMoedaReal(contaEncontrada
								.getValorTotal()));
				
				httpServletRequest.setAttribute("referenciaContaNaoEncontrada",
						"true");

			} else {

				ContaHistorico contaHistorico = fachada.pesquisarContaHistoricoDigitada(
						form.getIdImovel(), form.getReferenciaConta());
				
				if (contaHistorico != null) {
					form.setReferenciaConta(referenciaContaDigitadoEnter);
					form.setDescricaoReferenciaConta(Util.formatarMoedaReal(contaHistorico
									.getValorTotal()));
					
					httpServletRequest.setAttribute("referenciaContaNaoEncontrada",
							"true");
				} else {
					
					form.setReferenciaConta("");
					
					form.setDescricaoReferenciaConta("Conta inexistente");
					httpServletRequest.setAttribute("referenciaContaNaoEncontrada",
							"exception");
					
				}
			}
		}

		// [FS0009] ? Verificar existência da guia de pagamento
		String codigoGuiaPagamentoDigitadoEnter = form.getIdGuiaPagamento();

		// Caso o usuário tenha informado o código da guia de pagamento
		if (codigoGuiaPagamentoDigitadoEnter != null
				&& !codigoGuiaPagamentoDigitadoEnter.trim()
						.equalsIgnoreCase("")) {

			if (codigoImovel == null
					|| codigoImovel.trim().equalsIgnoreCase("")) {
					throw new ActionServletException("atencao.required",
							null, "Matrícula do Imóvel");
			}

			// Pesquisa a guia de pagamento para o imóvel informado
			GuiaPagamentoValoresHelper guiaPagamentoEncontradaHelper = fachada
					.pesquisarGuiaPagamentoDigitada(codigoImovel, 
							codigoGuiaPagamentoDigitadoEnter);
			
			
			if (guiaPagamentoEncontradaHelper != null) {

				form.setIdGuiaPagamento(codigoGuiaPagamentoDigitadoEnter);
				form.setDescricaoGuiaPagamento(guiaPagamentoEncontradaHelper.getGuiaPagamento().getDebitoTipo()
								.getDescricao());
				httpServletRequest.setAttribute("idGuiaPagamentoNaoEncontrado",
						"true");

			} else {
				form.setIdGuiaPagamento("");
				form.setDescricaoGuiaPagamento("Guia de Pagamento inexistente");
				httpServletRequest.setAttribute("idGuiaPagamentoNaoEncontrado",
						"exception");
			}
		}

		// [FS0012] - Verificar existência do débito.
		String codigoDebitoACobrarDigitadoEnter = form.getIdDebitoACobrar();
	
		// Caso o usuário tenha informado o código do débito a cobrar
		if (codigoDebitoACobrarDigitadoEnter != null
				&& !codigoDebitoACobrarDigitadoEnter.trim()
						.equalsIgnoreCase("")) {
	
			if (codigoImovel == null
					|| codigoImovel.trim().equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,
						"Matrícula do Imóvel");
				
			}
	
			// Pesquisa o débito a cobrar para o imóvel informado
			DebitoACobrarValoresHelper debitoACobrarEncontradoHelper = fachada
					.pesquisarDebitoACobrarImovelDigitado(codigoImovel,
							codigoDebitoACobrarDigitadoEnter);
			
			if (debitoACobrarEncontradoHelper != null) {
				form.setIdDebitoACobrar(codigoDebitoACobrarDigitadoEnter);
				form.setDescricaoDebitoACobrar(debitoACobrarEncontradoHelper.getDebitoACobrar()
						.getDebitoTipo().getDescricao());
				httpServletRequest.setAttribute("idDebitoACobrarNaoEncontrado",
						"true");
	
			} else {
				form.setIdDebitoACobrar("");
				form.setDescricaoDebitoACobrar("Débito a Cobrar inexistente");
				httpServletRequest.setAttribute("idDebitoACobrarNaoEncontrado",
						"exception");
	
			}
		}
		
		if ((httpServletRequest.getParameter("tipoConsulta") != null 
				&& !httpServletRequest.getParameter("tipoConsulta").equals(""))) {
			
			if (httpServletRequest.getParameter("tipoConsulta").equals("conta")) {

				form.setIdTipoDocumento(DocumentoTipo.CONTA.toString());
				
				form.setReferenciaConta(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
	
				form.setDescricaoReferenciaConta(httpServletRequest.getParameter("codigoAuxiliarEnviarDados"));
				
			} else if (httpServletRequest.getParameter("tipoConsulta").equals("debitoACobrar")) {

				form.setIdTipoDocumento(DocumentoTipo.DEBITO_A_COBRAR.toString());
				
				form.setIdDebitoACobrar(httpServletRequest.getParameter("idCampoEnviarDados"));
	
				form.setDescricaoDebitoACobrar(httpServletRequest.getParameter("descricaoCampoEnviarDados3"));
				
			} else if (httpServletRequest.getParameter("tipoConsulta").equals("guiaPagamento")) {

				form.setIdTipoDocumento(DocumentoTipo.GUIA_PAGAMENTO.toString());
				
				form.setIdGuiaPagamento(httpServletRequest.getParameter("idCampoEnviarDados"));
	
				form.setDescricaoGuiaPagamento(httpServletRequest.getParameter("descricaoCampoEnviarDados3"));
				
			}

		}

		
        return retorno;
        
    }

}
