package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.DevolucaoHistorico;
import gcom.arrecadacao.DevolucaoSituacao;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.FiltroDevolucaoHistorico;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioDevolucao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioDevolucao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DEVOLUCAO);
	}
	
	@Deprecated
	public RelatorioDevolucao() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroDevolucao filtroDevolucao = (FiltroDevolucao) getParametro("filtroDevolucao");
		FiltroDevolucaoHistorico filtroDevolucaoHistorico = (FiltroDevolucaoHistorico) getParametro("filtroDevolucaoHistorico");

		Devolucao devolucaoParametrosInicial = (Devolucao) getParametro("devolucaoParametrosInicial");
		Devolucao devolucaoParametrosFinal = (Devolucao) getParametro("devolucaoParametrosFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection<Devolucao> colecaoDevolucoes = new ArrayList<Devolucao>();
		if (!filtroDevolucao.getParametros().isEmpty()){
			
			filtroDevolucao.setConsultaSemLimites(true);
			colecaoDevolucoes = fachada.pesquisarDevolucao(filtroDevolucao);
		}
		
		if (filtroDevolucaoHistorico != null && !filtroDevolucaoHistorico.getParametros().isEmpty()){
			
			filtroDevolucaoHistorico.setConsultaSemLimites(true);
			
			Collection colecaoDevolucoesHistorico = fachada
				.pesquisarDevolucaoHistorico(filtroDevolucaoHistorico);
			
			if (colecaoDevolucoesHistorico != null) {
				
				Iterator colecaoDevolucoesHistoricoIterator = colecaoDevolucoesHistorico.iterator();
				
				DevolucaoHistorico devolucaoHistorico = new DevolucaoHistorico();
				while (colecaoDevolucoesHistoricoIterator.hasNext()) {
					
					devolucaoHistorico = (DevolucaoHistorico) colecaoDevolucoesHistoricoIterator.next();
					
					Devolucao devolucao = new Devolucao();
					devolucao.setAnoMesReferenciaArrecadacao(devolucaoHistorico.getAnoMesReferenciaArrecadacao());
					devolucao.setAnoMesReferenciaDevolucao(devolucaoHistorico.getAnoMesReferenciaDevolucao());
					devolucao.setAvisoBancario(devolucaoHistorico.getAvisoBancario());
					devolucao.setCliente(devolucaoHistorico.getCliente());
					devolucao.setDataDevolucao(devolucaoHistorico.getDataDevolucao());
					devolucao.setDebitoTipo(devolucaoHistorico.getDebitoTipo());
					devolucao.setDevolucaoSituacaoAnterior(devolucaoHistorico.getDevolucaoSituacaoAnterior());
					devolucao.setDevolucaoSituacaoAtual(devolucaoHistorico.getDevolucaoSituacaoAtual());
					devolucao.setGuiaDevolucao(devolucaoHistorico.getGuiaDevolucao());
					devolucao.setId(devolucaoHistorico.getId());
					devolucao.setImovel(devolucaoHistorico.getImovel());
					devolucao.setLocalidade(devolucaoHistorico.getLocalidade());
					devolucao.setUltimaAlteracao(devolucaoHistorico.getUltimaAlteracao());
					devolucao.setValorDevolucao(devolucaoHistorico.getValorDevolucao());
					
					colecaoDevolucoes.add(devolucao);
				}
			}
			
		}
		
		RelatorioDevolucaoBean relatorioBean = null;

		// Cria as variáveis que serão usadas futuramente no somatório de alguns
		// valores e para contar o número de devoluções de cada tipo
		int qtdeDevolucoesClassificadaContas = 0;
		BigDecimal valorDevolucoesClassificadaContas = new BigDecimal("0.00");
		
		int qtdeDevolucoesOutrosValoresContas = 0;
		BigDecimal valorDevolucoesOutrosValoresContas = new BigDecimal("0.00");
		
		int qtdeDevolucoesPgtoDuploNaoEncontradoContas = 0;
		BigDecimal valorDevolucoesPgtoDuploNaoEncontradoContas = new BigDecimal("0.00");
		
		int qtdeDevolucoesGuiaDevolucaoNaoInformadaContas = 0;
		BigDecimal valorDevolucoesGuiaDevolucaoNaoInformadaContas = new BigDecimal("0.00");
		
		int qtdeDevolucoesValorNaoConfereContas = 0;
		BigDecimal valorDevolucoesValorNaoConfereContas = new BigDecimal("0.00");
		
		int qtdeDevolucoesClassificadaGuiasPagamento = 0;
		BigDecimal valorDevolucoesClassificadaGuiasPagamento = new BigDecimal("0.00");
		
		int qtdeDevolucoesOutrosValoresGuiasPagamento = 0;
		BigDecimal valorDevolucoesOutrosValoresGuiasPagamento = new BigDecimal("0.00");
		
		int qtdeDevolucoesPgtoDuploNaoEncontradoGuiasPagamento = 0;
		BigDecimal valorDevolucoesPgtoDuploNaoEncontradoGuiasPagamento = new BigDecimal("0.00");
		
		int qtdeDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento = 0;
		BigDecimal valorDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento = new BigDecimal("0.00");
		
		int qtdeDevolucoesValorNaoConfereGuiasPagamento = 0;
		BigDecimal valorDevolucoesValorNaoConfereGuiasPagamento = new BigDecimal("0.00");
		
		int qtdeDevolucoesClassificadaDebitosACobrar = 0;
		BigDecimal valorDevolucoesClassificadaDebitosACobrar = new BigDecimal("0.00");
		
		int qtdeDevolucoesOutrosValoresDebitosACobrar = 0;
		BigDecimal valorDevolucoesOutrosValoresDebitosACobrar = new BigDecimal("0.00");
		
		int qtdeDevolucoesPgtoDuploNaoEncontradoDebitosACobrar = 0;
		BigDecimal valorDevolucoesPgtoDuploNaoEncontradoDebitosACobrar = new BigDecimal("0.00");
		
		int qtdeDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar = 0;
		BigDecimal valorDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar = new BigDecimal("0.00");
		
		int qtdeDevolucoesValorNaoConfereDebitosACobrar = 0;
		BigDecimal valorDevolucoesValorNaoConfereDebitosACobrar = new BigDecimal("0.00");
		
		int qtdeDevolucoesClassificadaOutrosValores = 0;
		BigDecimal valorDevolucoesClassificadaOutrosValores = new BigDecimal("0.00");
		
		int qtdeDevolucoesOutrosValoresOutrosValores = 0;
		BigDecimal valorDevolucoesOutrosValoresOutrosValores = new BigDecimal("0.00");
		
		int qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores = 0;
		BigDecimal valorDevolucoesPgtoDuploNaoEncontradoOutrosValores = new BigDecimal("0.00");
		
		int qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores = 0;
		BigDecimal valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores = new BigDecimal("0.00");
		
		int qtdeDevolucoesValorNaoConfereOutrosValores = 0;
		BigDecimal valorDevolucoesValorNaoConfereOutrosValores = new BigDecimal("0.00");
		
		int qtdeDevolucoesClassificada = 0;
		BigDecimal valorDevolucoesClassificada = new BigDecimal("0.00");
		
		int qtdeDevolucoesOutrosValores = 0;
		BigDecimal valorDevolucoesOutrosValores = new BigDecimal("0.00");
		
		int qtdeDevolucoesPgtoDuploNaoEncontrado = 0;
		BigDecimal valorDevolucoesPgtoDuploNaoEncontrado = new BigDecimal("0.00");
		
		int qtdeDevolucoesGuiaDevolucaoNaoInformada = 0;
		BigDecimal valorDevolucoesGuiaDevolucaoNaoInformada = new BigDecimal("0.00");
		
		int qtdeDevolucoesValorNaoConfere = 0;
		BigDecimal valorDevolucoesValorNaoConfere = new BigDecimal("0.00");

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoDevolucoes != null && !colecaoDevolucoes.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoDevolucoesIterator = colecaoDevolucoes.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoDevolucoesIterator.hasNext()) {

				Devolucao devolucao = (Devolucao) colecaoDevolucoesIterator.next();
				if (devolucao.getGuiaDevolucao() != null) {

					if (devolucao.getGuiaDevolucao().getDocumentoTipo().getId().equals(DocumentoTipo.CONTA)) {
						
						if (devolucao.getDevolucaoSituacaoAtual() == null || 
							devolucao.getDevolucaoSituacaoAtual().getId().equals(DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)) {
							
							qtdeDevolucoesClassificadaContas = qtdeDevolucoesClassificadaContas + 1;
							
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesClassificadaContas = 
									valorDevolucoesClassificadaContas.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao.getDevolucaoSituacaoAtual().getId().equals(DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)) {
							
							qtdeDevolucoesOutrosValoresContas = qtdeDevolucoesOutrosValoresContas + 1;
							
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesOutrosValoresContas = 
									valorDevolucoesOutrosValoresContas.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.PAGAMENTO_DUPLICIDADE_NAO_ENCONTRADO)) {
							qtdeDevolucoesPgtoDuploNaoEncontradoContas = qtdeDevolucoesPgtoDuploNaoEncontradoContas + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesPgtoDuploNaoEncontradoContas = valorDevolucoesPgtoDuploNaoEncontradoContas
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.GUIA_DEVOLUCAO_NAO_INFORMADA)) {
							qtdeDevolucoesGuiaDevolucaoNaoInformadaContas = qtdeDevolucoesGuiaDevolucaoNaoInformadaContas + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesGuiaDevolucaoNaoInformadaContas = valorDevolucoesGuiaDevolucaoNaoInformadaContas
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao.getDevolucaoSituacaoAtual()
								.getId().equals(
										DevolucaoSituacao.VALOR_NAO_CONFERE)) {
							qtdeDevolucoesValorNaoConfereContas = qtdeDevolucoesValorNaoConfereContas + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesValorNaoConfereContas = valorDevolucoesValorNaoConfereContas
										.add(devolucao.getValorDevolucao());
							}
						}
					} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
							.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
						if (devolucao.getDevolucaoSituacaoAtual() == null
								|| devolucao
										.getDevolucaoSituacaoAtual()
										.getId()
										.equals(
												DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)) {
							qtdeDevolucoesClassificadaDebitosACobrar = qtdeDevolucoesClassificadaDebitosACobrar + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesClassificadaDebitosACobrar = valorDevolucoesClassificadaDebitosACobrar
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)) {
							qtdeDevolucoesOutrosValoresDebitosACobrar = qtdeDevolucoesOutrosValoresDebitosACobrar + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesOutrosValoresDebitosACobrar = valorDevolucoesOutrosValoresDebitosACobrar
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.PAGAMENTO_DUPLICIDADE_NAO_ENCONTRADO)) {
							qtdeDevolucoesPgtoDuploNaoEncontradoDebitosACobrar = qtdeDevolucoesPgtoDuploNaoEncontradoDebitosACobrar + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesPgtoDuploNaoEncontradoDebitosACobrar = valorDevolucoesPgtoDuploNaoEncontradoDebitosACobrar
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.GUIA_DEVOLUCAO_NAO_INFORMADA)) {
							qtdeDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar = qtdeDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar = valorDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao.getDevolucaoSituacaoAtual()
								.getId().equals(
										DevolucaoSituacao.VALOR_NAO_CONFERE)) {
							qtdeDevolucoesValorNaoConfereDebitosACobrar = qtdeDevolucoesValorNaoConfereDebitosACobrar + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesValorNaoConfereDebitosACobrar = valorDevolucoesValorNaoConfereDebitosACobrar
										.add(devolucao.getValorDevolucao());
							}
						}
					} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
							.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
						if (devolucao.getDevolucaoSituacaoAtual() == null
								|| devolucao
										.getDevolucaoSituacaoAtual()
										.getId()
										.equals(
												DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)) {
							qtdeDevolucoesClassificadaGuiasPagamento = qtdeDevolucoesClassificadaGuiasPagamento + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesClassificadaGuiasPagamento = valorDevolucoesClassificadaGuiasPagamento
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)) {
							qtdeDevolucoesOutrosValoresGuiasPagamento = qtdeDevolucoesOutrosValoresGuiasPagamento + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesOutrosValoresGuiasPagamento = valorDevolucoesOutrosValoresGuiasPagamento
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.PAGAMENTO_DUPLICIDADE_NAO_ENCONTRADO)) {
							qtdeDevolucoesPgtoDuploNaoEncontradoGuiasPagamento = qtdeDevolucoesPgtoDuploNaoEncontradoGuiasPagamento + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesPgtoDuploNaoEncontradoGuiasPagamento = valorDevolucoesPgtoDuploNaoEncontradoGuiasPagamento
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.GUIA_DEVOLUCAO_NAO_INFORMADA)) {
							qtdeDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento = qtdeDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento = valorDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao.getDevolucaoSituacaoAtual()
								.getId().equals(
										DevolucaoSituacao.VALOR_NAO_CONFERE)) {
							qtdeDevolucoesValorNaoConfereGuiasPagamento = qtdeDevolucoesValorNaoConfereGuiasPagamento + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesValorNaoConfereGuiasPagamento = valorDevolucoesValorNaoConfereGuiasPagamento
										.add(devolucao.getValorDevolucao());
							}
						}
					} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
							.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
						if (devolucao.getDevolucaoSituacaoAtual() == null
								|| devolucao
										.getDevolucaoSituacaoAtual()
										.getId()
										.equals(
												DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)) {
							qtdeDevolucoesClassificadaOutrosValores = qtdeDevolucoesClassificadaOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesClassificadaOutrosValores = valorDevolucoesClassificadaOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)) {
							qtdeDevolucoesOutrosValoresOutrosValores = qtdeDevolucoesOutrosValoresOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesOutrosValoresOutrosValores = valorDevolucoesOutrosValoresOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.PAGAMENTO_DUPLICIDADE_NAO_ENCONTRADO)) {
							qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores = qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesPgtoDuploNaoEncontradoOutrosValores = valorDevolucoesPgtoDuploNaoEncontradoOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.GUIA_DEVOLUCAO_NAO_INFORMADA)) {
							qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores = qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores = valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao.getDevolucaoSituacaoAtual()
								.getId().equals(
										DevolucaoSituacao.VALOR_NAO_CONFERE)) {
							qtdeDevolucoesValorNaoConfereOutrosValores = qtdeDevolucoesValorNaoConfereOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesValorNaoConfereOutrosValores = valorDevolucoesValorNaoConfereOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						}
					}
				} else {
					if (devolucao.getAnoMesReferenciaDevolucao() != null) {
						if (devolucao.getDevolucaoSituacaoAtual() == null
								|| devolucao
										.getDevolucaoSituacaoAtual()
										.getId()
										.equals(
												DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)) {
							qtdeDevolucoesClassificadaContas = qtdeDevolucoesClassificadaContas + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesClassificadaContas = valorDevolucoesClassificadaContas
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)) {
							qtdeDevolucoesOutrosValoresContas = qtdeDevolucoesOutrosValoresContas + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesOutrosValoresContas = valorDevolucoesOutrosValoresContas
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.PAGAMENTO_DUPLICIDADE_NAO_ENCONTRADO)) {
							qtdeDevolucoesPgtoDuploNaoEncontradoContas = qtdeDevolucoesPgtoDuploNaoEncontradoContas + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesPgtoDuploNaoEncontradoContas = valorDevolucoesPgtoDuploNaoEncontradoContas
										.add(devolucao.getValorDevolucao());
							}
						}
					} else {
						if (devolucao.getDevolucaoSituacaoAtual() == null
								|| devolucao
										.getDevolucaoSituacaoAtual()
										.getId()
										.equals(
												DevolucaoSituacao.DEVOLUCAO_CLASSIFICADA)) {
							qtdeDevolucoesClassificadaOutrosValores = qtdeDevolucoesClassificadaOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesClassificadaOutrosValores = valorDevolucoesClassificadaOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.DEVOLUCAO_OUTROS_VALORES)) {
							qtdeDevolucoesOutrosValoresOutrosValores = qtdeDevolucoesOutrosValoresOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesOutrosValoresOutrosValores = valorDevolucoesOutrosValoresOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.PAGAMENTO_DUPLICIDADE_NAO_ENCONTRADO)) {
							qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores = qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesPgtoDuploNaoEncontradoOutrosValores = valorDevolucoesPgtoDuploNaoEncontradoOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao
								.getDevolucaoSituacaoAtual()
								.getId()
								.equals(
										DevolucaoSituacao.GUIA_DEVOLUCAO_NAO_INFORMADA)) {
							qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores = qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores = valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						} else if (devolucao.getDevolucaoSituacaoAtual()
								.getId().equals(
										DevolucaoSituacao.VALOR_NAO_CONFERE)) {
							qtdeDevolucoesValorNaoConfereOutrosValores = qtdeDevolucoesValorNaoConfereOutrosValores + 1;
							if (devolucao.getValorDevolucao() != null) {
								valorDevolucoesValorNaoConfereOutrosValores = valorDevolucoesValorNaoConfereOutrosValores
										.add(devolucao.getValorDevolucao());
							}
						}
					}
				}

				// Cria o objeto que será impresso no relatório setando os
				// campos que serão mostrados e fazendo as verificações para
				// evitar NullPointerException
				relatorioBean = new RelatorioDevolucaoBean(
						
						// Gerência Regional
						devolucao.getLocalidade().getGerenciaRegional() == null ? ""
								: devolucao.getLocalidade()
										.getGerenciaRegional().getId()
										+ " - "
										+ devolucao.getLocalidade()
												.getGerenciaRegional()
												.getNome(),
												
						// Localidade
						devolucao.getLocalidade().getId().toString() + " - "
								+ devolucao.getLocalidade().getDescricao(),

						// Matrícula do Imóvel ou Código do Cliente
						devolucao.getImovel() == null ? devolucao.getCliente().getId().toString() : devolucao
								.getImovel().getId().toString(),
								
						// Inscrição do Imóvel ou Nome do Cliente
						devolucao.getImovel() == null ? devolucao.getCliente().getNome() : devolucao
								.getImovel().getInscricaoFormatada(),
								
						// Arrecadador
						devolucao.getAvisoBancario() == null ? ""
								: devolucao.getAvisoBancario().getArrecadador() == null ? ""
										: devolucao.getAvisoBancario()
												.getArrecadador().getCliente()
												.getNome(), 
												
						// Data Devolução
												devolucao
								.getDataDevolucao() == null ? "" : Util
								.formatarData(devolucao.getDataDevolucao()),
						
						// Mês/Ano
						devolucao.getAnoMesReferenciaDevolucao() == null ? ""
								: Util.formatarAnoMesParaMesAno(devolucao
										.getAnoMesReferenciaDevolucao()),
										
						// Tipo de Débito
						devolucao.getDebitoTipo() == null ? "" : devolucao
								.getDebitoTipo().getDescricao(), 
								
						// Valor da Guia de Devolução
								devolucao
								.getGuiaDevolucao() == null ? ""
								: devolucao.getGuiaDevolucao()
										.getValorDevolucao() == null ? ""
										: Util.formatarMoedaReal(devolucao
												.getGuiaDevolucao()
												.getValorDevolucao()),
												
						// Valor da Devolução
						devolucao.getValorDevolucao() == null ? "" : Util
								.formatarMoedaReal(devolucao
										.getValorDevolucao()), devolucao
								.getDevolucaoSituacaoAtual() == null ? ""
								: devolucao.getDevolucaoSituacaoAtual()
										.getDescricaoDevolucaoSituacao());

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// Seta os parâmetros de acordo com o que o usuário digitou ou de acordo
		// com a qtde de devoluções de cada tipo e seus respectivos valores
		if (devolucaoParametrosInicial.getAnoMesReferenciaDevolucao() != null) {
			parametros.put("periodoAnoMes", Util
					.formatarAnoMesParaMesAno(devolucaoParametrosInicial
							.getAnoMesReferenciaDevolucao())
					+ " a "
					+ Util.formatarAnoMesParaMesAno(devolucaoParametrosFinal
							.getAnoMesReferenciaDevolucao()));

		} else {
			parametros.put("periodoAnoMes", "");
		}

		if (devolucaoParametrosInicial.getDataDevolucao() != null) {
			parametros.put("periodoDevolucao",
					Util.formatarData(devolucaoParametrosInicial
							.getDataDevolucao())
							+ " a "
							+ Util.formatarData(devolucaoParametrosFinal
									.getDataDevolucao()));
		} else {
			parametros.put("periodoDevolucao", "");
		}

		// o valor total das devoluções de cada tipo será o valor de todas as
		// devoluções desse tipo presente em cada tipo de documento, assim,
		// adiciona-se os valores a ele para achar o resultado
		qtdeDevolucoesClassificada = qtdeDevolucoesClassificada
				+ qtdeDevolucoesClassificadaContas
				+ qtdeDevolucoesClassificadaDebitosACobrar
				+ qtdeDevolucoesClassificadaGuiasPagamento
				+ qtdeDevolucoesClassificadaOutrosValores;

		valorDevolucoesClassificada = valorDevolucoesClassificada
				.add(valorDevolucoesClassificadaContas
						.add(valorDevolucoesClassificadaDebitosACobrar
								.add(valorDevolucoesClassificadaGuiasPagamento
										.add(valorDevolucoesClassificadaOutrosValores))));

		qtdeDevolucoesOutrosValores = qtdeDevolucoesOutrosValores
				+ qtdeDevolucoesOutrosValoresContas
				+ qtdeDevolucoesOutrosValoresDebitosACobrar
				+ qtdeDevolucoesOutrosValoresGuiasPagamento
				+ qtdeDevolucoesOutrosValoresOutrosValores;

		valorDevolucoesOutrosValores = valorDevolucoesOutrosValores
				.add(valorDevolucoesOutrosValoresContas
						.add(valorDevolucoesOutrosValoresDebitosACobrar
								.add(valorDevolucoesOutrosValoresGuiasPagamento
										.add(valorDevolucoesOutrosValoresOutrosValores))));

		qtdeDevolucoesPgtoDuploNaoEncontrado = qtdeDevolucoesPgtoDuploNaoEncontrado
				+ qtdeDevolucoesPgtoDuploNaoEncontradoContas
				+ qtdeDevolucoesPgtoDuploNaoEncontradoDebitosACobrar
				+ qtdeDevolucoesPgtoDuploNaoEncontradoGuiasPagamento
				+ qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores;

		valorDevolucoesPgtoDuploNaoEncontrado = valorDevolucoesPgtoDuploNaoEncontrado
				.add(valorDevolucoesPgtoDuploNaoEncontradoContas
						.add(valorDevolucoesPgtoDuploNaoEncontradoDebitosACobrar
								.add(valorDevolucoesPgtoDuploNaoEncontradoGuiasPagamento
										.add(valorDevolucoesPgtoDuploNaoEncontradoOutrosValores))));

		qtdeDevolucoesGuiaDevolucaoNaoInformada = qtdeDevolucoesGuiaDevolucaoNaoInformada
				+ qtdeDevolucoesGuiaDevolucaoNaoInformadaContas
				+ qtdeDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar
				+ qtdeDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento
				+ qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores;

		valorDevolucoesGuiaDevolucaoNaoInformada = valorDevolucoesGuiaDevolucaoNaoInformada
				.add(valorDevolucoesGuiaDevolucaoNaoInformadaContas
						.add(valorDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar
								.add(valorDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento
										.add(valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores))));

		qtdeDevolucoesValorNaoConfere = qtdeDevolucoesValorNaoConfere
				+ qtdeDevolucoesValorNaoConfereContas
				+ qtdeDevolucoesValorNaoConfereDebitosACobrar
				+ qtdeDevolucoesValorNaoConfereGuiasPagamento
				+ qtdeDevolucoesValorNaoConfereOutrosValores;

		valorDevolucoesValorNaoConfere = valorDevolucoesValorNaoConfere
				.add(valorDevolucoesValorNaoConfereContas
						.add(valorDevolucoesValorNaoConfereDebitosACobrar
								.add(valorDevolucoesValorNaoConfereGuiasPagamento
										.add(valorDevolucoesValorNaoConfereOutrosValores))));

		parametros.put("qtdeDevolucoesClassificadaContas", ""
				+ qtdeDevolucoesClassificadaContas);
		parametros.put("valorDevolucoesClassificadaContas", Util
				.formatarMoedaReal(valorDevolucoesClassificadaContas));
		parametros.put("qtdeDevolucoesOutrosValoresContas", ""
				+ qtdeDevolucoesOutrosValoresContas);
		parametros.put("valorDevolucoesOutrosValoresContas", Util
				.formatarMoedaReal(valorDevolucoesOutrosValoresContas));
		parametros.put("qtdeDevolucoesPgtoDuploNaoEncontradoContas", ""
				+ qtdeDevolucoesPgtoDuploNaoEncontradoContas);
		parametros
				.put(
						"valorDevolucoesPgtoDuploNaoEncontradoContas",
						Util
								.formatarMoedaReal(valorDevolucoesPgtoDuploNaoEncontradoContas));
		parametros.put("qtdeDevolucoesGuiaDevolucaoNaoInformadaContas", ""
				+ qtdeDevolucoesGuiaDevolucaoNaoInformadaContas);
		parametros
				.put(
						"valorDevolucoesGuiaDevolucaoNaoInformadaContas",
						Util
								.formatarMoedaReal(valorDevolucoesGuiaDevolucaoNaoInformadaContas));
		parametros.put("qtdeDevolucoesValorNaoConfereContas", ""
				+ qtdeDevolucoesValorNaoConfereContas);
		parametros.put("valorDevolucoesValorNaoConfereContas", Util
				.formatarMoedaReal(valorDevolucoesValorNaoConfereContas));
		parametros.put("qtdeDevolucoesClassificadaGuiasPagamento", ""
				+ qtdeDevolucoesClassificadaGuiasPagamento);
		parametros.put("valorDevolucoesClassificadaGuiasPagamento", Util
				.formatarMoedaReal(valorDevolucoesClassificadaGuiasPagamento));
		parametros.put("qtdeDevolucoesOutrosValoresGuiasPagamento", ""
				+ qtdeDevolucoesOutrosValoresGuiasPagamento);
		parametros.put("valorDevolucoesOutrosValoresGuiasPagamento", Util
				.formatarMoedaReal(valorDevolucoesOutrosValoresGuiasPagamento));
		parametros.put("qtdeDevolucoesPgtoDuploNaoEncontradoGuiasPagamento", ""
				+ qtdeDevolucoesPgtoDuploNaoEncontradoGuiasPagamento);
		parametros
				.put(
						"valorDevolucoesPgtoDuploNaoEncontradoGuiasPagamento",
						Util
								.formatarMoedaReal(valorDevolucoesPgtoDuploNaoEncontradoGuiasPagamento));
		parametros.put("qtdeDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento",
				"" + qtdeDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento);
		parametros
				.put(
						"valorDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento",
						Util
								.formatarMoedaReal(valorDevolucoesGuiaDevolucaoNaoInformadaGuiasPagamento));
		parametros.put("qtdeDevolucoesValorNaoConfereGuiasPagamento", ""
				+ qtdeDevolucoesValorNaoConfereGuiasPagamento);
		parametros
				.put(
						"valorDevolucoesValorNaoConfereGuiasPagamento",
						Util
								.formatarMoedaReal(valorDevolucoesValorNaoConfereGuiasPagamento));
		parametros.put("qtdeDevolucoesClassificadaDebitosACobrar", ""
				+ qtdeDevolucoesClassificadaDebitosACobrar);
		parametros.put("valorDevolucoesClassificadaDebitosACobrar", Util
				.formatarMoedaReal(valorDevolucoesClassificadaDebitosACobrar));
		parametros.put("qtdeDevolucoesOutrosValoresDebitosACobrar", ""
				+ qtdeDevolucoesOutrosValoresDebitosACobrar);
		parametros.put("valorDevolucoesOutrosValoresDebitosACobrar", Util
				.formatarMoedaReal(valorDevolucoesOutrosValoresDebitosACobrar));
		parametros.put("qtdeDevolucoesPgtoDuploNaoEncontradoDebitosACobrar", ""
				+ qtdeDevolucoesPgtoDuploNaoEncontradoDebitosACobrar);
		parametros
				.put(
						"valorDevolucoesPgtoDuploNaoEncontradoDebitosACobrar",
						Util
								.formatarMoedaReal(valorDevolucoesPgtoDuploNaoEncontradoDebitosACobrar));
		parametros.put("qtdeDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar",
				"" + qtdeDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar);
		parametros
				.put(
						"valorDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar",
						Util
								.formatarMoedaReal(valorDevolucoesGuiaDevolucaoNaoInformadaDebitosACobrar));
		parametros.put("qtdeDevolucoesValorNaoConfereDebitosACobrar", ""
				+ qtdeDevolucoesValorNaoConfereDebitosACobrar);
		parametros
				.put(
						"valorDevolucoesValorNaoConfereDebitosACobrar",
						Util
								.formatarMoedaReal(valorDevolucoesValorNaoConfereDebitosACobrar));

		parametros.put("qtdeDevolucoesClassificadaOutrosValores", ""
				+ qtdeDevolucoesClassificadaOutrosValores);
		parametros.put("valorDevolucoesClassificadaOutrosValores", Util
				.formatarMoedaReal(valorDevolucoesClassificadaOutrosValores));
		parametros.put("qtdeDevolucoesOutrosValoresOutrosValores", ""
				+ qtdeDevolucoesOutrosValoresOutrosValores);
		parametros.put("valorDevolucoesOutrosValoresOutrosValores", Util
				.formatarMoedaReal(valorDevolucoesOutrosValoresOutrosValores));
		parametros.put("qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores", ""
				+ qtdeDevolucoesPgtoDuploNaoEncontradoOutrosValores);
		parametros
				.put(
						"valorDevolucoesPgtoDuploNaoEncontradoOutrosValores",
						Util
								.formatarMoedaReal(valorDevolucoesPgtoDuploNaoEncontradoOutrosValores));
		parametros.put("qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores",
				"" + qtdeDevolucoesGuiaDevolucaoNaoInformadaOutrosValores);
		parametros
				.put(
						"valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores",
						Util
								.formatarMoedaReal(valorDevolucoesGuiaDevolucaoNaoInformadaOutrosValores));
		parametros.put("qtdeDevolucoesValorNaoConfereOutrosValores", ""
				+ qtdeDevolucoesValorNaoConfereOutrosValores);
		parametros
				.put(
						"valorDevolucoesValorNaoConfereOutrosValores",
						Util
								.formatarMoedaReal(valorDevolucoesValorNaoConfereOutrosValores));
		parametros.put("qtdeDevolucoesClassificada", ""
				+ qtdeDevolucoesClassificada);
		parametros.put("valorDevolucoesClassificada", Util
				.formatarMoedaReal(valorDevolucoesClassificada));
		parametros.put("qtdeDevolucoesOutrosValores", ""
				+ qtdeDevolucoesOutrosValores);
		parametros.put("valorDevolucoesOutrosValores", Util
				.formatarMoedaReal(valorDevolucoesOutrosValores));
		parametros.put("qtdeDevolucoesPgtoDuploNaoEncontrado", ""
				+ qtdeDevolucoesPgtoDuploNaoEncontrado);
		parametros.put("valorDevolucoesPgtoDuploNaoEncontrado", Util
				.formatarMoedaReal(valorDevolucoesPgtoDuploNaoEncontrado));
		parametros.put("qtdeDevolucoesGuiaDevolucaoNaoInformada", ""
				+ qtdeDevolucoesGuiaDevolucaoNaoInformada);
		parametros.put("valorDevolucoesGuiaDevolucaoNaoInformada", Util
				.formatarMoedaReal(valorDevolucoesGuiaDevolucaoNaoInformada));
		parametros.put("qtdeDevolucoesValorNaoConfere", ""
				+ qtdeDevolucoesValorNaoConfere);
		parametros.put("valorDevolucoesValorNaoConfere", Util
				.formatarMoedaReal(valorDevolucoesValorNaoConfere));

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DEVOLUCAO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.DEVOLUCAO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroDevolucao) getParametro("filtroDevolucao"),
				Devolucao.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDevolucao", this);
	}

}
