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
 * Action responsável por exibir a página de comparação entre os resumos 
 * do faturamento,arrecadação e da pendência.
 * Exibi também os percentuais do valor e da quantidade de contas entre 
 * o faturamenro e a arrecadação.
 * 
 * @author Pedro Alexandre
 * @date 07/06/2006
 * 
 */
public class ExibirResultadoConsultarComparativoResumoArrecadacaoFaturamentoPendenciaAction extends GcomAction {

	/**
	 * Action responsável por exibir a página de comparação entre os resumos 
	 * do faturamento,arrecadação e da pendência.
	 * Exibi também os percentuais do valor e da quantidade de contas entre 
	 * o faturamenro e a arrecadação.
	 * 
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecadação e da Pendência.
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

		//Cria uma instância da fachada.
		Fachada fachada = Fachada.getInstancia();

		// Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Recupera os dados do filtro do resumo da sessão.
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");

		//Instância as variáveis que iram armazenar os dados da exibição
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

		
		
		//Cria o objeto de consulta dos resumos só com o ano/mês de referência informado 
		//para verificar se existe resumo de faturamento, arrecadação e pendência
		//para o ano/mês de referência informado.
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
		
		//Pesquisa os resumos para o ano/mês de referência informado
		List resultadoConsultaResumoFaturamentoParaAnoMesReferencia = fachada.consultarResumoFaturamento(verificarExistenciaResumosParaAnoMesReferencia);
		List resultadoConsultaResumoArrecadacaoParaAnoMesReferencia = fachada.consultarResumoArrecadacao(verificarExistenciaResumosParaAnoMesReferencia);
		List resultadoConsultaResumoPendenciaParaAnoMesReferencia = fachada.consultarResumoComparativoPendencia(verificarExistenciaResumosParaAnoMesReferencia);
		
		//Recupera o ano/mês de referência e formato para apresentar na menssagem de erro
		String mesAnoReferencia = Util.formatarAnoMesParaMesAno(verificarExistenciaResumosParaAnoMesReferencia.getAnoMesReferencia());
		
		//[FS0006] - Verificar existência de dados de simulação do faturamento para o ano/mês de referência retornado.
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
		
		//[FS0007] - Verificar existência de dados de arrecadação dados diários para o ano/mês de referência retornado.
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
		
		//[FS0008] - Verificar existência de dados de pendência para o ano/mês de referência retornado.
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
		
		
		//Pesquisa na base de dados os resumos do faturamento, arrecadação e pendência para 
		//todos os filtros selecionados na página.
		List resultadoConsultaResumoFaturamento = fachada.consultarResumoFaturamento(informarDadosGeracaoRelatorioConsultaHelper);
		List resultadoConsultaResumoArrecadacao = fachada.consultarResumoArrecadacao(informarDadosGeracaoRelatorioConsultaHelper);
		List resultadoConsultaResumoPendencia = fachada.consultarResumoComparativoPendencia(informarDadosGeracaoRelatorioConsultaHelper);

		//Recupera o form 
		ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm consultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm = (ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaActionForm) actionForm;
		
		//Pesquisa a coleção de argumentos de agrupamento da sessão.
		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumos(informarDadosGeracaoRelatorioConsultaHelper);
		
		//Seta a coleção de agrupmento na sessão para ser utilizado na jsp deo comparativo 
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);

		//Caso exista resumo de faturamento com os parâmetros de pesquisa informado 
		//recupera o valor do faturamento e a quantidade de contas
		//Caso contrário seta os valores para (0)zero
		if(resultadoConsultaResumoFaturamento != null){
			Iterator iteratorResultadoResumoFaturamento = resultadoConsultaResumoFaturamento.iterator();
			Object[] arrayResultadoConsultaResumoFaturamento = (Object[])iteratorResultadoResumoFaturamento.next();
			contasFaturamento = (Integer)arrayResultadoConsultaResumoFaturamento[0];
			valorFaturamento = (BigDecimal)arrayResultadoConsultaResumoFaturamento[1];
		}else{
			contasFaturamento = 0;
			valorFaturamento = new BigDecimal("0.00");
		}

		//Caso exista resumo de arrecadação com os parâmetros de pesquisa informado 
		//recupera o valor da arrecadação e a quantidade de contas
		//Caso contrário seta os valores para (0)zero
		if(resultadoConsultaResumoArrecadacao != null){
			Iterator iteratorResultadoResumoArrecadacao = resultadoConsultaResumoArrecadacao.iterator();
			Object[] arrayResultadoConsultaResumoArrecadacao = (Object[])iteratorResultadoResumoArrecadacao.next();
			contasArrecadacao = (Integer)arrayResultadoConsultaResumoArrecadacao[0];
			valorArrecadacao = (BigDecimal)arrayResultadoConsultaResumoArrecadacao[1];
		}else{
			contasArrecadacao = 0;
			valorArrecadacao = new BigDecimal("0.00");
		}

		
		//Caso exista resumo de pendência com os parâmetros de pesquisa informado 
		//recupera o valor da pendência e a quantidade de contas
		//Caso contrário seta os valores para (0)zero		
		if(resultadoConsultaResumoPendencia != null){
			Iterator iteratorResultadoResumoPendencia = resultadoConsultaResumoPendencia.iterator();
			Object[] arrayResultadoConsultaResumoPendencia = (Object[])iteratorResultadoResumoPendencia.next();
			contasPendencia = (Integer)arrayResultadoConsultaResumoPendencia[0];
			valorPendencia = (BigDecimal)arrayResultadoConsultaResumoPendencia[1];
		}else{
			contasPendencia = 0;
			valorPendencia = new BigDecimal("0.00");
		}

		//Caso exista contas do faturamento calcula o percentual das contas - ((nºcontasArrecadacao/nºcontasFaturamento) * 100) 
		if(contasFaturamento != null && contasFaturamento > 0 && contasArrecadacao != null){
			contasPercentual =Double.parseDouble(Util.calcularPercentual(""+contasArrecadacao, ""+contasFaturamento));
		}

		//Caso exista contas do faturamento calcula o nº de vezes do faturamento - (contasPendencia/contasFaturamento)
		if(contasFaturamento != null && contasFaturamento > 0 && contasPendencia != null){
			contasNumeroVezesFaturamento = contasPendencia.doubleValue()/contasFaturamento.doubleValue();
		}
		
		//Caso exista contas do faturamento calcula os percentuais do valor do faturamento - ((valorArrecadacao/valorFaturamento) * 100)
		if(valorFaturamento != null && valorFaturamento.doubleValue() > 0 && valorArrecadacao != null){
			valorPercentual = ((valorArrecadacao.divide(valorFaturamento, BigDecimal.ROUND_UNNECESSARY,2)).multiply(new BigDecimal(100))).doubleValue(); 
		}
		
		//Caso exista valor do faturamento e seja maior que 0(zero), calcula o nº de vezes do valor do faturamento - (valorPendencia/valorFaturamento)
		if(valorFaturamento != null && valorFaturamento.doubleValue() > 0 && valorPendencia != null){
			valorNumeroVezesFaturamento = (valorPendencia.divide(valorFaturamento, BigDecimal.ROUND_UNNECESSARY,2)).doubleValue();
		}


		/*
		 * Cria as variáveis que iram armazenar os valores das percentuais com a formatação certa 
		 * e o nº de vezes do faturamento, caso não exista valor ou não exista contas para o faturamento 
		 * exibe uma string vazia. 
		 */
		String percentualContasString = null;
		String percentualValorString = null;
		String nContasFaturamentoContasString = null;
		String nValorFaturamentoContasString = null;
		
		/*
		 * Bloco para verificar se alguma valor está null,
		 * caso ocorra seta o valor para 0(zero),para não ser 
		 * exibida a descrição "NULL" 
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

		//Seta os valores no form para exibição na página
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
