package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtaualizarDadosCartaoCreditoDebitoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Sera o caminho de retorno
		ActionForward retorno = actionMapping.findForward("consultarDadosParcelamentoCartaoCredito");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm = (ParcelamentoCartaoConfirmarForm) actionForm;

		//ATUALIZANDO A TRANSAÇÃO SELECIONADA PELO USUÁRIO
		this.atualizarParcelamentoPagamentoCartaoCreditoNaSessao(httpServletRequest, sessao, 
		parcelamentoCartaoConfirmarForm);
		
		
		String concluir = (String) httpServletRequest.getParameter("concluir");
		
		if (concluir != null && concluir.equals("ok")){
			
			Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
			Collection colecaoParcelamentoPagamentoCartaoCredito = (Collection) sessao.getAttribute("colecaoParcelamentos");
			
			fachada.atualizarParcelamentoPagamentoCartaoCredito(colecaoParcelamentoPagamentoCartaoCredito, usuarioLogado);
			
			httpServletRequest.setAttribute("fechaPopup", "SIM");
		}
		

		return retorno;
	}
	
	
	public void atualizarParcelamentoPagamentoCartaoCreditoNaSessao(HttpServletRequest httpServletRequest, HttpSession sessao,
			ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm){
		
		String atualizar = (String) httpServletRequest.getParameter("atualizar");
		
		if (atualizar != null && atualizar.equalsIgnoreCase("ok")){
			
			//PARCELAMENTO PAGAMENTO CARTÃO CRÉDITO SELECIONADO
			ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCredito = 
			(ParcelamentoPagamentoCartaoCredito) sessao.getAttribute("parcelamentoAtualizacao");
			
			if (parcelamentoPagamentoCartaoCredito != null){
				
				//COLEÇÃO COM TODAS AS TRANSAÇÕES DE CARTÃO DE CRÉDITO ATIVAS PARA O PARCELAMENTO
				//Collection colecaoParcelamentoPagamentoCartaoCreditoNew = new ArrayList();
				Collection colecaoParcelamentoPagamentoCartaoCredito = (Collection) sessao.getAttribute("colecaoParcelamentos");
				
				if (colecaoParcelamentoPagamentoCartaoCredito != null && !colecaoParcelamentoPagamentoCartaoCredito.isEmpty()){
					
					Iterator iterator = colecaoParcelamentoPagamentoCartaoCredito.iterator();
					
					while(iterator.hasNext()){
						
						ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCreditoColecao = (ParcelamentoPagamentoCartaoCredito)
						iterator.next();
						
						//IDENTIFICANDO A TRANSAÇÃO QUE SERÁ ALTERADA
						if (parcelamentoPagamentoCartaoCreditoColecao.getId().equals(parcelamentoPagamentoCartaoCredito.getId())){
							
							if(parcelamentoCartaoConfirmarForm.getNumeroCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getNumeroCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setNumeroCartaoCredito(
								Util.encrypt(parcelamentoCartaoConfirmarForm.getNumeroCartao()));
							}
							
							if(parcelamentoCartaoConfirmarForm.getNumeroIdentificacaoTransacao() != null && 
								!parcelamentoCartaoConfirmarForm.getNumeroIdentificacaoTransacao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setIdentificacaoTransacao(
								parcelamentoCartaoConfirmarForm.getNumeroIdentificacaoTransacao());
							}
							
							if(parcelamentoCartaoConfirmarForm.getValidadeCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getValidadeCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setAnoMesValidade(
								Util.formatarMesAnoComBarraParaAnoMes(parcelamentoCartaoConfirmarForm.getValidadeCartao()));
							}
							
							if(parcelamentoCartaoConfirmarForm.getNomeCliente() != null && 
								!parcelamentoCartaoConfirmarForm.getNomeCliente().equals("")){
								
								Cliente cliente = new Cliente();
								cliente.setId(new Integer(parcelamentoCartaoConfirmarForm.getIdCliente()));
								cliente.setNome(parcelamentoCartaoConfirmarForm.getNomeCliente());
								parcelamentoPagamentoCartaoCreditoColecao.setCliente(cliente);
							}
							
							if(parcelamentoCartaoConfirmarForm.getDocumentoCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getDocumentoCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setDocumentoCartaoCredito(parcelamentoCartaoConfirmarForm.getDocumentoCartao());
							}
							
							if(parcelamentoCartaoConfirmarForm.getAutorizacaoCartao() != null && 
								!parcelamentoCartaoConfirmarForm.getAutorizacaoCartao().equals("")){
								
								parcelamentoPagamentoCartaoCreditoColecao.setNumeroAutorizacao(parcelamentoCartaoConfirmarForm.getAutorizacaoCartao());
							}
							
							if(parcelamentoCartaoConfirmarForm.getValorTransacao() != null && 
								!parcelamentoCartaoConfirmarForm.getValorTransacao().equals("")){
									
								parcelamentoPagamentoCartaoCreditoColecao.setValorParcelado(Util.formatarMoedaRealparaBigDecimal(
								parcelamentoCartaoConfirmarForm.getValorTransacao()));
							}
							
							break;
						}
					}
				}
			}
		}
	}
}
