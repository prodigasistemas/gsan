package gcom.gui.gerencial;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel por exibir a p�gina de compara��o entre os resumos 
 * do faturamento,arrecada��o e da pend�ncia.
 * Exibi tamb�m os percentuais do valor e da quantidade de contas entre 
 * o faturamenro e a arrecada��o.
 * 
 * @author Pedro Alexandre
 * @date 07/06/2006
 * 
 */
public class ExibirResultadoConsultarComparativoResumoArrecadacaoFaturamentoPendenciaAction extends GcomAction {

	/**
	 * Action respons�vel por exibir a p�gina de compara��o entre os resumos 
	 * do faturamento,arrecada��o e da pend�ncia.
	 * Exibi tamb�m os percentuais do valor e da quantidade de contas entre 
	 * o faturamenro e a arrecada��o.
	 * 
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecada��o e da Pend�ncia.
	 * 
	 * @author Pedro Alexandre
	 * @date 07/03/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//Seta o amapeamento de retorno para a tela do comparativo dos resumos 
		ActionForward retorno = actionMapping.findForward("resultadoComparativoResumosFaturamentoArrecadacaoPendencia");

		//Cria uma inst�ncia da fachada.
		Fachada fachada = Fachada.getInstancia();

		// Cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera os dados do filtro do resumo da sess�o.
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");

		//Inst�ncia as vari�veis que iram armazenar os dados da exibi��o
		Integer contasFaturamento = 0;
		BigDecimal valorFaturamento = BigDecimal.ZERO;
		Integer contasArrecadacao = 0;
		BigDecimal valorArrecadacao = BigDecimal.ZERO;
		Integer contasPendencia = 0;
		BigDecimal valorPendencia = BigDecimal.ZERO;
		double contasPercentual = 0.0;
		double valorPercentual = 0.0;
		double contasNumeroVezesFaturamento = 0.0;
		double valorNumeroVezesFaturamento = 0.0;

		
		
		//Cria o objeto de consulta dos resumos s� com o ano/m�s de refer�ncia informado 
		//para verificar se existe resumo de faturamento, arrecada��o e pend�ncia
		//para o ano/m�s de refer�ncia informado.
		InformarDadosGeracaoRelatorioConsultaHelper verificarExistenciaResumosParaAnoMesReferencia = new InformarDadosGeracaoRelatorioConsultaHelper();
		verificarExistenciaResumosParaAnoMesReferencia.setAnoMesReferencia(informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia());
		verificarExistenciaResumosParaAnoMesReferencia.setColecaoCategoria(null);
		verificarExistenciaResumosParaAnoMesReferencia.setColecaoEsferaPoder(null);
		verificarExistenciaResumosParaAnoMesReferencia.setColecaoImovelPerfil(null);
		verificarExistenciaResumosParaAnoMesReferencia.setColecaoLigacaoAguaSituacao(null);
		verificarExistenciaResumosParaAnoMesReferencia.setColecaoLigacaoEsgotoSituacao(null);
		verificarExistenciaResumosParaAnoMesReferencia.setDescricaoOpcaoTotalizacao("ESTADO");
		verificarExistenciaResumosParaAnoMesReferencia.setEloPolo(null);
		verificarExistenciaResumosParaAnoMesReferencia.setFaturamentoGrupo(null);
		verificarExistenciaResumosParaAnoMesReferencia.setGerarRelatorio(false);
		verificarExistenciaResumosParaAnoMesReferencia.setQuadra(null);
		verificarExistenciaResumosParaAnoMesReferencia.setSetorComercial(null);
		verificarExistenciaResumosParaAnoMesReferencia.setTipoAnaliseFaturamento(null);
		verificarExistenciaResumosParaAnoMesReferencia.setTipoRelatorio(null);
		
		//Pesquisa os resumos para o ano/m�s de refer�ncia informado
		List resultadoConsultaResumoFaturamentoParaAnoMesReferencia = fachada.consultarResumoFaturamento(verificarExistenciaResumosParaAnoMesReferencia);
		List resultadoConsultaResumoArrecadacaoParaAnoMesReferencia = fachada.consultarResumoArrecadacao(verificarExistenciaResumosParaAnoMesReferencia);
		List resultadoConsultaResumoPendenciaParaAnoMesReferencia = fachada.consultarResumoComparativoPendencia(verificarExistenciaResumosParaAnoMesReferencia);
		
		//Recupera o ano/m�s de refer�ncia e formato para apresentar na menssagem de erro
		String mesAnoReferencia = Util.formatarAnoMesParaMesAno(verificarExistenciaResumosParaAnoMesReferencia.getAnoMesReferencia());
		
		//[FS0006] - Verificar exist�ncia de dados de simula��o do faturamento para o ano/m�s de refer�ncia retornado.
		if(resultadoConsultaResumoFaturamentoParaAnoMesReferencia != null){
			Iterator iteratorResultadoResumoFaturamentoParaAnoMesReferencia = resultadoConsultaResumoFaturamentoParaAnoMesReferencia.iterator();
			Object[] arrayResultadoConsultaResumoFaturamentoParaAnoMesReferencia = (Object[])iteratorResultadoResumoFaturamentoParaAnoMesReferencia.next();
			contasFaturamento = (Integer)arrayResultadoConsultaResumoFaturamentoParaAnoMesReferencia[0];
			valorFaturamento = (BigDecimal)arrayResultadoConsultaResumoFaturamentoParaAnoMesReferencia[1];
			
			if(contasFaturamento == null && valorFaturamento == null){
				throw new ActionServletException("atencao.resumo.faturamento.inexistente",null,mesAnoReferencia);
			}
		}else{
			throw new ActionServletException("atencao.resumo.faturamento.inexistente",null,mesAnoReferencia);
		}
		
		//[FS0007] - Verificar exist�ncia de dados de arrecada��o dados di�rios para o ano/m�s de refer�ncia retornado.
		if(resultadoConsultaResumoArrecadacaoParaAnoMesReferencia != null){
			Iterator iteratorResultadoResumoArrecadacaoParaAnoMesReferencia = resultadoConsultaResumoArrecadacaoParaAnoMesReferencia.iterator();
			Object[] arrayResultadoConsultaResumoArrecadacaoParaAnoMesReferencia = (Object[])iteratorResultadoResumoArrecadacaoParaAnoMesReferencia.next();
			contasFaturamento = (Integer)arrayResultadoConsultaResumoArrecadacaoParaAnoMesReferencia[0];
			valorFaturamento = (BigDecimal)arrayResultadoConsultaResumoArrecadacaoParaAnoMesReferencia[1];
			
			if(contasFaturamento == null && valorFaturamento == null){
				throw new ActionServletException("atencao.resumo.arrecadacao.inexistente",null,mesAnoReferencia);
			}
		}else{
			throw new ActionServletException("atencao.resumo.arrecadacao.inexistente",null,mesAnoReferencia);
		}
		
		//[FS0008] - Verificar exist�ncia de dados de pend�ncia para o ano/m�s de refer�ncia retornado.
		if(resultadoConsultaResumoPendenciaParaAnoMesReferencia != null){
			Iterator iteratorResultadoResumoPendenciaParaAnoMesReferencia = resultadoConsultaResumoPendenciaParaAnoMesReferencia.iterator();
			Object[] arrayResultadoConsultaResumoPendenciaParaAnoMesReferencia = (Object[])iteratorResultadoResumoPendenciaParaAnoMesReferencia.next();
			contasFaturamento = (Integer)arrayResultadoConsultaResumoPendenciaParaAnoMesReferencia[0];
			valorFaturamento = (BigDecimal)arrayResultadoConsultaResumoPendenciaParaAnoMesReferencia[1];
			
			if(contasFaturamento == null && valorFaturamento == null){
				throw new ActionServletException("atencao.resumo.pendencia.inexistente",null,mesAnoReferencia);
			}
		}else{
			throw new ActionServletException("atencao.resumo.pendencia.inexistente",null,mesAnoReferencia);
		}
		
		
		//Pesquisa na base de dados os resumos do faturamento, arrecada��o e pend�ncia para 
		
		List resultadoConsultaResumoFaturamento = fachada.consultarResumoFaturamento(informarDadosGeracaoRelatorioConsultaHelper);
		List resultadoConsultaResumoArrecadacao = fachada.consultarResumoArrecadacao(informarDadosGeracaoRelatorioConsultaHelper);
		List resultadoConsultaResumoPendencia = fachada.consultarResumoComparativoPendencia(informarDadosGeracaoRelatorioConsultaHelper);

		//Recupera o form 
		ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm = (ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm) actionForm;
		
		//Pesquisa a cole��o de argumentos de agrupamento da sess�o.
		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumos(informarDadosGeracaoRelatorioConsultaHelper);
		
		//Seta a cole��o de agrupmento na sess�o para ser utilizado na jsp deo comparativo 
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);

		//Caso exista resumo de faturamento com os par�metros de pesquisa informado 
		//recupera o valor do faturamento e a quantidade de contas
		//Caso contr�rio seta os valores para (0)zero
		if(resultadoConsultaResumoFaturamento != null){
			Iterator iteratorResultadoResumoFaturamento = resultadoConsultaResumoFaturamento.iterator();
			Object[] arrayResultadoConsultaResumoFaturamento = (Object[])iteratorResultadoResumoFaturamento.next();
			contasFaturamento = (Integer)arrayResultadoConsultaResumoFaturamento[0];
			valorFaturamento = (BigDecimal)arrayResultadoConsultaResumoFaturamento[1];
		}else{
			contasFaturamento = 0;
			valorFaturamento = new BigDecimal("0.00");
		}

		//Caso exista resumo de arrecada��o com os par�metros de pesquisa informado 
		//recupera o valor da arrecada��o e a quantidade de contas
		//Caso contr�rio seta os valores para (0)zero
		if(resultadoConsultaResumoArrecadacao != null){
			Iterator iteratorResultadoResumoArrecadacao = resultadoConsultaResumoArrecadacao.iterator();
			Object[] arrayResultadoConsultaResumoArrecadacao = (Object[])iteratorResultadoResumoArrecadacao.next();
			contasArrecadacao = (Integer)arrayResultadoConsultaResumoArrecadacao[0];
			valorArrecadacao = (BigDecimal)arrayResultadoConsultaResumoArrecadacao[1];
		}else{
			contasArrecadacao = 0;
			valorArrecadacao = new BigDecimal("0.00");
		}

		
		//Caso exista resumo de pend�ncia com os par�metros de pesquisa informado 
		//recupera o valor da pend�ncia e a quantidade de contas
		//Caso contr�rio seta os valores para (0)zero		
		if(resultadoConsultaResumoPendencia != null){
			Iterator iteratorResultadoResumoPendencia = resultadoConsultaResumoPendencia.iterator();
			Object[] arrayResultadoConsultaResumoPendencia = (Object[])iteratorResultadoResumoPendencia.next();
			contasPendencia = (Integer)arrayResultadoConsultaResumoPendencia[0];
			valorPendencia = (BigDecimal)arrayResultadoConsultaResumoPendencia[1];
		}else{
			contasPendencia = 0;
			valorPendencia = new BigDecimal("0.00");
		}

		//Caso exista contas do faturamento calcula o percentual das contas - ((n�contasArrecadacao/n�contasFaturamento) * 100) 
		if(contasFaturamento != null && contasFaturamento > 0 && contasArrecadacao != null){
			contasPercentual =Double.parseDouble(Util.calcularPercentual(""+contasArrecadacao, ""+contasFaturamento));
		}

		//Caso exista contas do faturamento calcula o n� de vezes do faturamento - (contasPendencia/contasFaturamento)
		if(contasFaturamento != null && contasFaturamento > 0 && contasPendencia != null){
			contasNumeroVezesFaturamento = contasPendencia.doubleValue()/contasFaturamento.doubleValue();
		}
		
		//Caso exista contas do faturamento calcula os percentuais do valor do faturamento - ((valorArrecadacao/valorFaturamento) * 100)
		if(valorFaturamento != null && valorFaturamento.doubleValue() > 0 && valorArrecadacao != null){
			valorPercentual = ((valorArrecadacao.divide(valorFaturamento, BigDecimal.ROUND_UNNECESSARY,2)).multiply(new BigDecimal(100))).doubleValue(); 
		}
		
		//Caso exista valor do faturamento e seja maior que 0(zero), calcula o n� de vezes do valor do faturamento - (valorPendencia/valorFaturamento)
		if(valorFaturamento != null && valorFaturamento.doubleValue() > 0 && valorPendencia != null){
			valorNumeroVezesFaturamento = (valorPendencia.divide(valorFaturamento, BigDecimal.ROUND_UNNECESSARY,2)).doubleValue();
		}


		/*
		 * Cria as vari�veis que iram armazenar os valores das percentuais com a formata��o certa 
		 * e o n� de vezes do faturamento, caso n�o exista valor ou n�o exista contas para o faturamento 
		 * exibe uma string vazia. 
		 */
		String percentualContasString = null;
		String percentualValorString = null;
		String nContasFaturamentoContasString = null;
		String nValorFaturamentoContasString = null;
		
		/*
		 * Bloco para verificar se alguma valor est� null,
		 * caso ocorra seta o valor para 0(zero),para n�o ser 
		 * exibida a descri��o "NULL" 
		 */
		if(contasFaturamento == null){
			contasFaturamento = 0;
			percentualContasString = "";
			nContasFaturamentoContasString = "";
		}else{
			percentualContasString = Util.formatarMoedaReal(new BigDecimal(contasPercentual));
			nContasFaturamentoContasString = Util.formatarMoedaReal(new BigDecimal(contasNumeroVezesFaturamento));
		}
		
		if(valorFaturamento == null){
			valorFaturamento = BigDecimal.ZERO;
			percentualValorString = "";
			nValorFaturamentoContasString = "";
		}else{
			percentualValorString = Util.formatarMoedaReal(new BigDecimal(valorPercentual));
			nValorFaturamentoContasString =Util.formatarMoedaReal(new BigDecimal(valorNumeroVezesFaturamento));
		}
		
		if(contasArrecadacao == null){
			contasArrecadacao = 0;
		}
		if(valorArrecadacao == null){
			valorArrecadacao = BigDecimal.ZERO;
		}
		if(contasPendencia == null){
			contasPendencia = 0;
		}
		if(valorPendencia == null){
			valorPendencia = BigDecimal.ZERO;
		}

		//Seta os valores no form para exibi��o na p�gina
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setQuantidadeContasFaturamento(contasFaturamento);
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setValorFaturamento(Util.formatarMoedaReal(valorFaturamento));
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setQuantidadeContasArrecadacao(contasArrecadacao);
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setValorArrecadacao(Util.formatarMoedaReal(valorArrecadacao));
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setQuantidadeContasPendencia(contasPendencia);
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setValorPendencia(Util.formatarMoedaReal(valorPendencia));
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setQuantidadeContasPercentual(percentualContasString);
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setValorPercentual(percentualValorString);
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setQuantidadeContasNumeroVezesFaturamento(nContasFaturamentoContasString);
		consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm.setValorNumeroVezesFaturamento(nValorFaturamentoContasString);
		
		return retorno;
	}

}
