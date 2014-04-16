package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 * @date 09/11/2006
 * 
 */
public class ExibirConsultarResumoNegativacaoPopupAction extends
		GcomAction {
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

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarResumoNegativacaoPopup");

		HttpSession sessao = getSessao(httpServletRequest);

		BigDecimal somatorioValorDebito = new BigDecimal(0);
		if(httpServletRequest.getParameter("valorTotal") != null){
			somatorioValorDebito = new BigDecimal(httpServletRequest.getParameter("valorTotal"));
		}
		sessao.setAttribute("somatorioValorDebito",somatorioValorDebito);
		
		String descricaoIncluidas = (String)httpServletRequest.getParameter("descricao"); 
		sessao.setAttribute("descricaoIncluidas",descricaoIncluidas);
		
		String stNegativacao = (String)httpServletRequest.getParameter("stNegociacao");
		String situacaoNegativacao = ""; 
		if(stNegativacao.equals("1")){
			situacaoNegativacao = "NEGATIVAÇÕES INCLUÍDAS";
			sessao.setAttribute("situacaoNegativacao",situacaoNegativacao);
			
		}else if(stNegativacao.equals("2")){
			situacaoNegativacao = "NEGATIVAÇÕES INCLUÍDAS E CONFORMADAS";
			sessao.setAttribute("situacaoNegativacao",situacaoNegativacao);
			
		}else if(stNegativacao.equals("3")){
			situacaoNegativacao = "NEGATIVAÇÕES INCLUÍDAS E NÃO CONFORMADAS";
			sessao.setAttribute("situacaoNegativacao",situacaoNegativacao);
			
		}
		// Montagem Valores Detalhe PopUp 
		////////////////  ***  VALOR PENDENTE  ***  //////////////// 
		Collection<NegativacaoHelper>colecaoResumoNegativacaoDetalhePopUp = new ArrayList();
		NegativacaoHelper retumoTotaisDetalhePopUp = new NegativacaoHelper();
		retumoTotaisDetalhePopUp.setDescricao("PENDENTE");
		BigDecimal valorPendente = new BigDecimal(0);
		if(httpServletRequest.getParameter("valorPendente") != null){
			valorPendente = new BigDecimal(httpServletRequest.getParameter("valorPendente"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorPendente);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorPendente.doubleValue() * 100)/somatorioValorDebito.doubleValue()));	
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);

		////////////////***  VALOR PAGO  ***  ////////////////			
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("PAGO");
		BigDecimal valorPago = new BigDecimal(0);		
		if(httpServletRequest.getParameter("valorPago") != null){
			valorPago = new BigDecimal(httpServletRequest.getParameter("valorPago"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorPago);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorPago.doubleValue() * 100)/somatorioValorDebito.doubleValue()));		
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);		

		////////////////***  VALOR PARCELADO  ***  ////////////////			
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("PARCELADO");
		BigDecimal valorParcelado = new BigDecimal(0);		
		if(httpServletRequest.getParameter("valorParcelado") != null){
			valorParcelado = new BigDecimal(httpServletRequest.getParameter("valorParcelado"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorParcelado);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorParcelado.doubleValue() * 100)/somatorioValorDebito.doubleValue()));		
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);		
		
		////////////////***  VALOR CANCELADO  ***  ////////////////			
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("CANCELADO");
		BigDecimal valorCancelado = new BigDecimal(0);		
		if(httpServletRequest.getParameter("valorCancelado") != null){
			valorCancelado = new BigDecimal(httpServletRequest.getParameter("valorCancelado"));
		}
		// Valor
		retumoTotaisDetalhePopUp.setValorDinamico(valorCancelado);
		// Percentual
		retumoTotaisDetalhePopUp.setPercentualValor(new BigDecimal((valorCancelado.doubleValue() * 100)/somatorioValorDebito.doubleValue()));		
		colecaoResumoNegativacaoDetalhePopUp.add(retumoTotaisDetalhePopUp);		
		
        sessao.setAttribute("colecaoResumoNegativacaoDetalhePopUp", colecaoResumoNegativacaoDetalhePopUp);				
				
		
        //**************************************************************************************
        // Pesquisa os dados do resumo da Negociacao agrupando por ligacaoAguaSituacao
        Collection<NegativacaoHelper>colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp = new ArrayList();
        Integer idSituacaoDebito = new Integer(httpServletRequest.getParameter("idSituacaoCobranca"));
        DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper = 
			(DadosConsultaNegativacaoHelper) sessao.getAttribute("dadosConsultaNegativacaoHelper");
        
		Collection<NegativacaoHelper> colecaoResumoNegativacaoLigacaoAguaSituacao = 
			Fachada.getInstancia().consultarResumoNegativacaoLigacaoAguaPorSituacaoDebito(
					dadosConsultaNegativacaoHelper, idSituacaoDebito);
		
		Iterator itcolecaoResumoNegativacao = colecaoResumoNegativacaoLigacaoAguaSituacao.iterator();
		BigDecimal valorDebito = new BigDecimal(0);
		BigDecimal percentualValorDebito = new BigDecimal(0);
		BigDecimal valorPotencial = new BigDecimal(0);
		Integer quantidadePotencial = new Integer(0);
		BigDecimal valorFactivel = new BigDecimal(0);
		Integer quantidadeFactivel = new Integer(0);
		BigDecimal valorLigado = new BigDecimal(0);
		Integer quantidadeLigado = new Integer(0);
		BigDecimal valorEmAnalise = new BigDecimal(0);
		Integer quantidadeEmAnalise = new Integer(0);
		BigDecimal valorCortado = new BigDecimal(0);
		Integer quantidadeCortado = new Integer(0);
		BigDecimal valorSuprimido = new BigDecimal(0);
		Integer quantidadeSuprimido = new Integer(0);
		
		while(itcolecaoResumoNegativacao.hasNext()){
			NegativacaoHelper retumoTotais = (NegativacaoHelper)itcolecaoResumoNegativacao.next();
			valorDebito = retumoTotais.getSomatorioValorDebito();
			
			// o getIdSituacaoCobranca sera reaproveitado para armazenar o id da situacao da agua;
			if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(1)){ // POTENCIAL
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					
					valorPotencial = valorPotencial.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Quantidade
				quantidadePotencial = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(2)){ // FACTIVEL
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					
					valorFactivel = valorFactivel.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeFactivel =  retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(3)){ // LIGADO
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					
					valorLigado = valorLigado.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeLigado = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(4)){ // EM ANALISE
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					
					valorEmAnalise = valorEmAnalise.add(retumoTotais.getSomatorioValorCancelado());
				}
				// Calculo dos Percentuais
				quantidadeEmAnalise = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(5)){ // CORTADO
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					
					valorCortado = valorCortado.add(retumoTotais.getSomatorioValorCancelado());
				}
				// Calculo dos Percentuais
				quantidadeCortado = retumoTotais.getSomatorioQuantidadeInclusoes();
			}else if(retumoTotais.getIdSituacaoCobranca() != null && retumoTotais.getIdSituacaoCobranca().equals(6)){ // SUPRIMIDO
				// Valor
				
				if (idSituacaoDebito.equals(ConstantesSistema.PENDENTE)){
					
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorPendente());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PAGO)){
					
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorPago());
				}
				else if (idSituacaoDebito.equals(ConstantesSistema.PARCELADO)){
					
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorParcelado());
				}
				else{
					
					valorSuprimido = valorSuprimido.add(retumoTotais.getSomatorioValorCancelado());
				}
				
				// Calculo dos Percentuais
				quantidadeSuprimido = retumoTotais.getSomatorioQuantidadeInclusoes();
			}
		}
		
		Integer somatorioTotalQuantidade = 0;
		BigDecimal somatorioValor = new BigDecimal(0);
		
		// Potencial
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("POTENCIAL");
		retumoTotaisDetalhePopUp.setValorDinamico(valorPotencial);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadePotencial);
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorPotencial);
		somatorioTotalQuantidade += quantidadePotencial;
		
		// Factivel
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("FACTIVEL");
		retumoTotaisDetalhePopUp.setValorDinamico(valorFactivel);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeFactivel);
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorFactivel);
		somatorioTotalQuantidade += quantidadeFactivel;
		
		// Ligado
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("LIGADO");
		retumoTotaisDetalhePopUp.setValorDinamico(valorLigado);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeLigado);
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorLigado);
		somatorioTotalQuantidade += quantidadeLigado;
		
		// Em Analise
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("EM ANALISE");
		retumoTotaisDetalhePopUp.setValorDinamico(valorEmAnalise);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeEmAnalise);
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorEmAnalise);
		somatorioTotalQuantidade += quantidadeEmAnalise;
		
		// Cortado
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("CORTADO");
		retumoTotaisDetalhePopUp.setValorDinamico(valorCortado);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeCortado);
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorCortado);
		somatorioTotalQuantidade += quantidadeCortado;
		
		// Suprimido
		retumoTotaisDetalhePopUp = new NegativacaoHelper();		
		retumoTotaisDetalhePopUp.setDescricao("SUPRIMIDO");
		retumoTotaisDetalhePopUp.setValorDinamico(valorSuprimido);
		retumoTotaisDetalhePopUp.setQuantidadeInclusao(quantidadeSuprimido);
		colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp.add(retumoTotaisDetalhePopUp);
		somatorioValor.add(valorSuprimido);
		somatorioTotalQuantidade += quantidadeSuprimido;
		
		sessao.setAttribute("colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp", colecaoResumoNegativacaoDetalheLigacaoAguaSituacaoPopUp);
		sessao.setAttribute("somatorioValor", somatorioValor);
		sessao.setAttribute("somatorioTotalQuantidade", somatorioTotalQuantidade);
		
		return retorno;
	}
}
