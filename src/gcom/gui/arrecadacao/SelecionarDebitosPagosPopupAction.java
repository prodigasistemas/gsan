package gcom.gui.arrecadacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoACobrarValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1214] Informar Acerto Documentos Não Aceitos
 *
 * @author Mariana Victor
 * @date 23/08/2011
 */
public class SelecionarDebitosPagosPopupAction extends GcomAction {
	
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping
			.findForward("exibirSelecionarDebitosPagosPopup");
	
		SelecionarDebitosPagosPopupActionForm form = (SelecionarDebitosPagosPopupActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		Conta contaEncontrada = null;
		GuiaPagamentoValoresHelper guiaPagamentoEncontradaHelper = null;
		DebitoACobrarValoresHelper debitoACobrarEncontradoHelper = null;

		boolean contaHist = false;

		sessao.removeAttribute("valorAdicionado");
		
		// [FS0004] - Verificar preenchimento dos campos obrigatórios
		if (form.getIdTipoDocumento() == null
				|| form.getIdTipoDocumento().trim().equals("")
				|| form.getIdTipoDocumento().trim().equals("-1")) {
	
			throw new ActionServletException("atencao.required", 
					"Tipo de Documento");
		}
		
		
		// [FS0011] - Verificar preenchimento dos campos necessários.
		if (form.getIdTipoDocumento().trim().compareTo(DocumentoTipo.CONTA.toString()) == 0
				&& (form.getReferenciaConta() == null
					|| form.getReferenciaConta().trim().equals(""))) {
	
			throw new ActionServletException("atencao.necessario_informar", 
					"Referência da Conta");
			
		} else if (form.getIdTipoDocumento().trim().compareTo(DocumentoTipo.GUIA_PAGAMENTO.toString()) == 0
				&& (form.getIdGuiaPagamento() == null
						|| form.getIdGuiaPagamento().trim().equals(""))) {
	
				throw new ActionServletException("atencao.necessario_informar", 
						"Guia de Pagamento");
				
		} else if (form.getIdTipoDocumento().trim().compareTo(DocumentoTipo.DEBITO_A_COBRAR.toString()) == 0
				&& (form.getIdDebitoACobrar() == null
						|| form.getIdDebitoACobrar().trim().equals(""))) {
	
				throw new ActionServletException("atencao.necessario_informar", 
						"Débito a Cobrar");
				
		}
		
		
		// [FS0012] - Verificar existência do débito.
		if (form.getIdTipoDocumento().trim().compareTo(DocumentoTipo.CONTA.toString()) == 0) {
			
			// Recupera a conta do imóvel com a referência informada
			contaEncontrada = fachada.pesquisarContaDigitada(
					form.getIdImovel(), form.getReferenciaConta());
	
			// Caso não exista o débito
			if (contaEncontrada == null) {
				
				ContaHistorico contaHistorico = fachada.pesquisarContaHistoricoDigitada(
						form.getIdImovel(), form.getReferenciaConta());
	
				if (contaHistorico == null) {
	
					throw new ActionServletException("atencao.pesquisa_inexistente", 
							"Conta");
				}
				contaHist = true;
				
				contaEncontrada = new Conta();
				contaEncontrada.setId(contaHistorico.getId());
				contaEncontrada.setReferencia(contaHistorico.getAnoMesReferenciaConta());
				contaEncontrada.setDataVencimentoConta(contaHistorico.getDataVencimentoConta());
				contaEncontrada.setDebitoCreditoSituacaoAtual(contaHistorico.getDebitoCreditoSituacaoAtual());
				contaEncontrada.setValorAgua(contaHistorico.getValorAgua());
				contaEncontrada.setValorEsgoto(contaHistorico.getValorEsgoto());
				contaEncontrada.setValorCreditos(contaHistorico.getValorCreditos());
				contaEncontrada.setDebitos(contaHistorico.getValorDebitos());
				contaEncontrada.setValorImposto(contaHistorico.getValorImposto());
				contaEncontrada.setLocalidade(contaHistorico.getLocalidade());
			}
			
		} else if (form.getIdTipoDocumento().trim().compareTo(DocumentoTipo.GUIA_PAGAMENTO.toString()) == 0) {
	
			// Pesquisa a guia de pagamento para o imóvel informado
			guiaPagamentoEncontradaHelper = fachada
				.pesquisarGuiaPagamentoDigitada(form.getIdImovel(), 
						form.getIdGuiaPagamento());
			
			// Caso não exista o débito
			if (guiaPagamentoEncontradaHelper == null) {
				
				throw new ActionServletException("atencao.pesquisa_inexistente", 
						"Guia de Pagamento");
			} 
			
		} else if (form.getIdTipoDocumento().trim().compareTo(DocumentoTipo.DEBITO_A_COBRAR.toString()) == 0) {
	
			// Pesquisa o débito a cobrar para o imóvel informado
			debitoACobrarEncontradoHelper = fachada
					.pesquisarDebitoACobrarImovelDigitado(form.getIdImovel(),
							form.getIdDebitoACobrar());
			
			// Caso não exista o débito
			if (debitoACobrarEncontradoHelper == null) {
	
				throw new ActionServletException("atencao.pesquisa_inexistente", 
						"Débito a Cobrar");
			}
			
		}
		
		
		// O sistema retorna para a tela principal para inserir na coleção do tipo de débito 
		//  pesquisado na cor vermelha e atribui o valor 1 (um) ao indicador de débito pago. 
		if (contaEncontrada != null) {
			
			Collection<ContaValoresHelper> colecaoContaValores = 
				(Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores"); 
			
			if (colecaoContaValores == null) {
				colecaoContaValores = new ArrayList<ContaValoresHelper>();
			}
			
			if (!this.contaAdicionada(colecaoContaValores, contaEncontrada.getId())) {
				ContaValoresHelper contaValoresHelper = new ContaValoresHelper();
				
				contaValoresHelper.setConta(contaEncontrada);
				
				if (contaHist) {
					contaValoresHelper.setIndicadorDebitoPago(ConstantesSistema.SIM);
				} else {
					contaValoresHelper.setIndicadorDebitoPago(ConstantesSistema.NAO);
				}
				
				colecaoContaValores.add(contaValoresHelper);
				
				String valorContaSessao = "0,00";
				BigDecimal valorConta = BigDecimal.ZERO;
				
				if (sessao.getAttribute("valorConta") != null
						&& !sessao.getAttribute("valorConta").toString().trim().equals("")) {
					valorContaSessao = (String) sessao.getAttribute("valorConta");
					valorConta = Util.formatarMoedaRealparaBigDecimal(valorContaSessao);
					valorConta = valorConta.add(contaEncontrada.getValorTotal());
				}
		
				sessao.setAttribute("colecaoContaValores", colecaoContaValores);
				sessao.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));
				sessao.setAttribute("valorAdicionado", contaEncontrada.getValorTotal());
			} else {

				throw new ActionServletException("atencao.objeto_ja_informado", 
						"Conta");
				
			}
	
		} else if (guiaPagamentoEncontradaHelper != null 
				&& guiaPagamentoEncontradaHelper.getGuiaPagamento() != null) {
			
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = 
				(Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores"); 
			
			if (colecaoGuiaPagamentoValores == null) {
				colecaoGuiaPagamentoValores = new ArrayList<GuiaPagamentoValoresHelper>();
			}
			
			if (!this.guiaAdicionada(colecaoGuiaPagamentoValores, guiaPagamentoEncontradaHelper.getGuiaPagamento().getId())) {
				GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = new GuiaPagamentoValoresHelper();
				
				guiaPagamentoValoresHelper.setGuiaPagamento(guiaPagamentoEncontradaHelper.getGuiaPagamento());
				guiaPagamentoValoresHelper.setIndicadorDebitoPago(guiaPagamentoEncontradaHelper.getIndicadorDebitoPago());
		
				colecaoGuiaPagamentoValores.add(guiaPagamentoValoresHelper);
		
				String valorGuiaPagamentoSessao = "0,00";
				BigDecimal valorGuiaPagamento = BigDecimal.ZERO;
				
				if (sessao.getAttribute("valorGuiaPagamento") != null
						&& !sessao.getAttribute("valorGuiaPagamento").toString().trim().equals("")) {
					valorGuiaPagamentoSessao = (String) sessao.getAttribute("valorGuiaPagamento");
					valorGuiaPagamento = Util.formatarMoedaRealparaBigDecimal(valorGuiaPagamentoSessao);
					valorGuiaPagamento = valorGuiaPagamento.add(guiaPagamentoEncontradaHelper.getGuiaPagamento().getValorDebito());
				}
		
				sessao.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);
				sessao.setAttribute("valorGuiaPagamento", Util.formatarMoedaReal(valorGuiaPagamento));
				sessao.setAttribute("valorAdicionado", guiaPagamentoEncontradaHelper.getGuiaPagamento().getValorDebito());
			} else {

				throw new ActionServletException("atencao.objeto_ja_informado", 
						"Guia de Pagamento");
				
			}
			
		} else if (debitoACobrarEncontradoHelper != null 
				&& debitoACobrarEncontradoHelper.getDebitoACobrar() != null) {
			
			Collection<DebitoACobrarValoresHelper>  colecaoDebitoACobrar = 
				(Collection<DebitoACobrarValoresHelper>)sessao.getAttribute("colecaoDebitoACobrar"); 

			if (colecaoDebitoACobrar == null) {
				colecaoDebitoACobrar = new ArrayList<DebitoACobrarValoresHelper>();
			}
			
			if (!this.debitoACobrarAdicionado(colecaoDebitoACobrar, debitoACobrarEncontradoHelper.getDebitoACobrar().getId())) {
				DebitoACobrarValoresHelper debitoACobrarValoresHelper = new DebitoACobrarValoresHelper();
				debitoACobrarValoresHelper.setDebitoACobrar(debitoACobrarEncontradoHelper.getDebitoACobrar());
				debitoACobrarValoresHelper.setIndicadorDebitoPago(debitoACobrarEncontradoHelper.getIndicadorDebitoPago());
		
				colecaoDebitoACobrar.add(debitoACobrarValoresHelper);
		
				String valorDebitoACobrarSessao = "0,00";
				BigDecimal valorDebitoACobrar = BigDecimal.ZERO;
				
				if (sessao.getAttribute("valorDebitoACobrar") != null
						&& !sessao.getAttribute("valorDebitoACobrar").toString().trim().equals("")) {
					valorDebitoACobrarSessao = (String) sessao.getAttribute("valorDebitoACobrar");
					valorDebitoACobrar = Util.formatarMoedaRealparaBigDecimal(valorDebitoACobrarSessao);
					valorDebitoACobrar = valorDebitoACobrar.add(debitoACobrarEncontradoHelper.getDebitoACobrar().getValorTotalComBonus());
				}
		
				sessao.setAttribute("colecaoDebitoACobrar", colecaoDebitoACobrar);
				sessao.setAttribute("valorDebitoACobrar", Util.formatarMoedaReal(valorDebitoACobrar));
				sessao.setAttribute("valorAdicionado", debitoACobrarEncontradoHelper.getDebitoACobrar().getValorTotalComBonus());
			} else {

				throw new ActionServletException("atencao.objeto_ja_informado", 
						"Débito a Cobrar");
				
			}
		}
	
		form.setIdImovel("");
		form.setDescricaoImovel("");
		form.setReferenciaConta("");
		form.setDescricaoReferenciaConta("");
		form.setIdGuiaPagamento("");
		form.setDescricaoGuiaPagamento("");
		form.setIdDebitoACobrar("");
		form.setDescricaoDebitoACobrar("");
		httpServletRequest.setAttribute("fecharPopup", "OK");
		sessao.setAttribute("fecharPopup", "OK");
		
		return retorno;
	}
    
    public boolean contaAdicionada(Collection<ContaValoresHelper> colecaoContaValores,
    		Integer idConta) {
    	
    	boolean contaAdicionada = false;
    	
    	if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
	    	Iterator iterator = colecaoContaValores.iterator();
	    	
			while(iterator.hasNext()) {
				ContaValoresHelper helper = (ContaValoresHelper) iterator.next();
				if(helper.getConta().getId().compareTo(idConta) == 0) {
					contaAdicionada = true;
					break;
				}
			}
    	}
		
		return contaAdicionada;
		
    }
    
    public boolean guiaAdicionada(Collection<GuiaPagamentoValoresHelper> colecaoContaValores,
    		Integer idGuia) {
    	
    	boolean guiaAdicionada = false;
    	
    	if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
	    	Iterator iterator = colecaoContaValores.iterator();
	    	
			while(iterator.hasNext()) {
				GuiaPagamentoValoresHelper helper = (GuiaPagamentoValoresHelper) iterator.next();
				if(helper.getGuiaPagamento().getId().compareTo(idGuia) == 0) {
					guiaAdicionada = true;
					break;
				}
			}
    	}
		
		return guiaAdicionada;
		
    }
    
    public boolean debitoACobrarAdicionado(Collection<DebitoACobrarValoresHelper> colecaoContaValores,
    		Integer idDebitoACobrar) {
    	
    	boolean debitoACobrarAdicionado = false;
    	
    	if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
	    	Iterator iterator = colecaoContaValores.iterator();
	    	
			while(iterator.hasNext()) {
				DebitoACobrarValoresHelper helper = (DebitoACobrarValoresHelper) iterator.next();
				if(helper.getDebitoACobrar().getId().compareTo(idDebitoACobrar) == 0) {
					debitoACobrarAdicionado = true;
					break;
				}
			}
    	}
		
		return debitoACobrarAdicionado;
		
    }
}
