package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.financeiro.FinanciamentoTipo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.cobranca.parcelamento.ExtratoDebitoRelatorioHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioDocumentoVisitaCobranca extends TarefaRelatorio {

		private static final long serialVersionUID = 1L;
		
		public RelatorioDocumentoVisitaCobranca(Usuario usuario) {
			super(usuario, ConstantesRelatorios.RELATORIO_DOCUMENTO_VISITA_COBRANCA);
		}

		@Deprecated
		public RelatorioDocumentoVisitaCobranca() {
			super(null, "");
		}
		
		public Object executar() throws TarefaException {

			// ------------------------------------
			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
			// ------------------------------------

			Collection<Integer[]> idsImovel = (Collection<Integer[]>) getParametro("idsImovel");
			int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

			// valor de retorno
			byte[] retorno = null;

			Fachada fachada = Fachada.getInstancia();

			// Parâmetros do relatório
			Map parametros = new HashMap();

			// adiciona os parâmetros do relatório
			// adiciona o laudo da análise
			FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
			
			
			Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
			
			SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();

			String nomeEmpresa = sistemaParametro.getNomeEmpresa();
			String nomeAbreviadoEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
			String cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
			String numeroAtendimento =  sistemaParametro.getNumero0800Empresa();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("nomeEmpresa", nomeEmpresa);
			parametros.put("nomeAbreviadoEmpresa", nomeAbreviadoEmpresa);
			parametros.put("cnpjEmpresa", cnpjEmpresa);
			parametros.put("numeroAtendimento", numeroAtendimento);
			parametros.put("dataCorrente", Util.formatarData(new Date()));
			
			if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
					SistemaParametro.EMPRESA_CAERN)){
				parametros.put("agenciaCodigoCedente", "3795-8/9121-9");
			} else if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
					SistemaParametro.EMPRESA_COMPESA)){
				parametros.put("agenciaCodigoCedente", "3234-4/2868-1");
			}
			
			parametros.put("acaoCobranca", "VISITA DE COBRANÇA");
			
			List relatorioBeans = (List) this.gerarExtratoDebito(idsImovel, this.getUsuario());
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_DOCUMENTO_VISITA_COBRANCA,
			parametros, ds, tipoFormatoRelatorio);
			
			
			// ------------------------------------
			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_DOCUMENTO_VISITA_COBRANCA,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
			// ------------------------------------

			// retorna o relatório gerado
			return retorno;
		}
	    
	    public List gerarExtratoDebito(Collection<Integer[]> idsImovel,
	    		Usuario usuarioLogado){

			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			
	    	Collection<CobrancaDocumento> colecaoDocumentoCobranca = new ArrayList();
	    	
	    	Iterator iterator = idsImovel.iterator();
	    	
	    	while(iterator.hasNext()) {
	    		Object[] ids = (Object[]) iterator.next();
	    		
	    		Integer idOrdemServico = (Integer) ids[0];
	    		Integer idImovel = (Integer) ids[1];
	        	
	        	Imovel imovel = Fachada.getInstancia().pesquisarImovel(idImovel);

	    		//Short indicadorGeracaoTaxaCobranca = new Short("2") ;  // no caso do parcelamento sempre 2

	    		// seta valores constantes para chamar o metodo que consulta debitos do
	    		// imovel
	    		Integer tipoImovel = new Integer(1);
	    		Integer indicadorPagamento = new Integer(1);
	    		Integer indicadorConta = new Integer(1);
	    		Integer indicadorDebito = new Integer(1);
	    		Integer indicadorCredito = new Integer(1);
	    		Integer indicadorNotas = new Integer(1);
	    		Integer indicadorGuias = new Integer(1);
	    		Integer indicadorAtualizar = new Integer(1);
	    		Short tipoRelacao = null;
	    		
	    		// Para auxiliar na formatação de uma data
	    		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	    		
	    		String referenciaInicial = "01/0001";
	    		String referenciaFinal = "12/9999";
	    		String mesInicial = referenciaInicial.substring(0, 2);
	    		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
	    		String anoMesInicial = anoInicial + mesInicial;
	    		String mesFinal = referenciaFinal.substring(0, 2);
	    		String anoFinal = referenciaFinal
	    				.substring(3, referenciaFinal.length());
	    		String anoMesFinal = anoFinal + mesFinal;
	    		
	    		String dataVencimentoInicial = "01/01/0001";
	    		String dataVencimentoFinal = "31/12/9999";
	    		Date dataVencimentoDebitoI;
	    		Date dataVencimentoDebitoF;

	    		try {
	    			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
	    		} catch (ParseException ex) {
	    			dataVencimentoDebitoI = null;
	    		}
	    		try {
	    			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
	    		} catch (ParseException ex) {
	    			dataVencimentoDebitoF = null;
	    		}
	    		
	    		// Obtendo os débitos do imovel
	    		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = Fachada.getInstancia()
	    				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
	    						imovel.getId().toString(), null, tipoRelacao, anoMesInicial,
	    						anoMesFinal, dataVencimentoDebitoI,
	    						dataVencimentoDebitoF, indicadorPagamento.intValue(),
	    						indicadorConta.intValue(), indicadorDebito.intValue(),
	    						indicadorCredito.intValue(), indicadorNotas.intValue(),
	    						indicadorGuias.intValue(), indicadorAtualizar
	    								.intValue(), null);
	    		
	    		Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel
					.getColecaoContasValores();

				ContaValoresHelper dadosConta = null;
			
				BigDecimal valorConta = new BigDecimal("0.00");
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				BigDecimal valorAgua = new BigDecimal("0.00");
				BigDecimal valorEsgoto = new BigDecimal("0.00");
				BigDecimal valorDebito = new BigDecimal("0.00");
				BigDecimal valorCredito = new BigDecimal("0.00");
				BigDecimal valorImposto = new BigDecimal("0.00");
				BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
				BigDecimal valorJurosMora = new BigDecimal("0.00");
				BigDecimal valorMulta = new BigDecimal("0.00");
			
				if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
					java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
					// percorre a colecao de conta somando o valor para obter um valor total
					while (colecaoContaValoresIterator.hasNext()) {
			
						dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
						valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
						valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
						valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
						valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
						valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
						valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
						valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
						
						if (dadosConta.getValorAtualizacaoMonetaria() != null && !dadosConta.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosConta.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (dadosConta.getValorJurosMora() != null	&& !dadosConta.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(dadosConta.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (dadosConta.getValorMulta() != null && !dadosConta.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(dadosConta.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
					}
				}
				

				Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();

				BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
				DebitoACobrar dadosDebito = null;
				BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
				int indiceCurtoPrazo = 0;
				int indiceLongoPrazo = 1;
				
				if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
					java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
					// percorre a colecao de debito a cobrar somando o valor para obter um valor total
					while (colecaoDebitoACobrarIterator.hasNext()) {

						dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
						valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
						
						//Debitos A Cobrar - Parcelamento
						if (dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
								|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
								|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
							// [SB0001] Obter Valores de Curto e Longo Prazo
							valorRestanteACobrar = dadosDebito.getValorTotalComBonus();

							BigDecimal[] valoresDeCurtoELongoPrazo = Fachada.getInstancia().obterValorCurtoELongoPrazo(
									dadosDebito.getNumeroPrestacaoDebito(),	
									dadosDebito.getNumeroPrestacaoCobradasMaisBonus(),
									valorRestanteACobrar);
							valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
							valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
						}
					}
					valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
				}
				

				Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();

				BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
				CreditoARealizar dadosCredito = null;

				if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
					java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
					// percorre a colecao de credito a realizar somando o valor para obter um valor total
					while (colecaoCreditoARealizarIterator.hasNext()) {

						dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
						valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
					}
				}

				Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoImovel
						.getColecaoGuiasPagamentoValores();

				BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
				GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

				if (colecaoGuiaPagamentoValores != null
						&& !colecaoGuiaPagamentoValores.isEmpty()) {
					java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores
							.iterator();
					// percorre a colecao de guia de pagamento somando o valor para
					// obter um valor total
					while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

						dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
						valorGuiaPagamento = valorGuiaPagamento
								.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
						
						if (dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (dadosGuiaPagamentoValoresHelper.getValorJurosMora() != null && !dadosGuiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(dadosGuiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (dadosGuiaPagamentoValoresHelper.getValorMulta() != null	&& !dadosGuiaPagamentoValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(dadosGuiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						
					}
				}

				// Soma o valor total dos debitos e subtrai dos creditos
				BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
				valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
				valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);

				BigDecimal valorTotalComAcrescimo = valorTotalSemAcrescimo.add(valorAcrescimo);
				
			
				
				IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = 
					new IndicadoresParcelamentoHelper();
				
				indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer("3"));
				
				BigDecimal valorTotalDescontoPagamentoAVista = null;
				BigDecimal valorPagamentoAVista = null;
				if(sistemaParametro.getResolucaoDiretoria() != null){
				
					ImovelPerfil imovelPerfil = Fachada.getInstancia().obterImovelPerfil(new Integer(imovel.getId())); 
		
					
					Short numeroReparcelamentoConsecutivos = Fachada.getInstancia().consultarNumeroReparcelamentoConsecutivosImovel(imovel.getId());
					if(numeroReparcelamentoConsecutivos == null){
						numeroReparcelamentoConsecutivos = new Short("0");
					}
					
					//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
					ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
							sistemaParametro.getResolucaoDiretoria().getId(), 
							imovel.getId(), 
							new BigDecimal("0.00"), 
							new Integer("3"),
							new Integer("1"),
							imovelPerfil.getId(), 
							"01/0001", 
							new Integer("2"),//indicador de restabelecimento 
							colecaoContaValores, 
							valorTotalComAcrescimo, 
							valorMulta, 
							valorJurosMora, 
							valorAtualizacaoMonetaria, 
							new Integer(numeroReparcelamentoConsecutivos.toString()), 
							colecaoGuiaPagamentoValores, 
							usuarioLogado, 
							valorTotalRestanteParcelamentosACobrar, 
							Util.formatarMesAnoComBarraParaAnoMes("01/0001"),
							Util.formatarMesAnoComBarraParaAnoMes("12/9999"), 
							indicadoresParcelamentoHelper,
							valorCreditoARealizar);
					
					NegociacaoOpcoesParcelamentoHelper negociacaoOpcoesParcelamentoHelper = 
						Fachada.getInstancia().calcularValorDosDescontosPagamentoAVista(helper);
					
					valorTotalDescontoPagamentoAVista = negociacaoOpcoesParcelamentoHelper.getValorTotalDescontoPagamentoAVista();
					valorPagamentoAVista = valorTotalComAcrescimo.subtract(valorTotalDescontoPagamentoAVista);
	    		} else {
	    			valorPagamentoAVista = valorTotalSemAcrescimo;
	    		}
				
				Short indicadorGeracaoTaxaCobranca = new Short("2") ;  // no caso do parcelamento sempre 2

				ResolucaoDiretoria resolucaoDiretoria = null;
				Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento = null;
				Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento = null;
				
	        	ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper = Fachada.getInstancia().gerarEmitirExtratoDebito(
	    				imovel,indicadorGeracaoTaxaCobranca,colecaoContaValores,colecaoGuiaPagamentoValores,colecaoDebitoACobrar,
	    				valorAcrescimo,valorTotalDescontoPagamentoAVista,valorPagamentoAVista, 
	    				colecaoCreditoARealizar, null, resolucaoDiretoria, colecaoAntecipacaoDebitosDeParcelamento,
	    				colecaoAntecipacaoCreditosDeParcelamento);

	    		CobrancaDocumento documentoCobranca = extratoDebitoRelatorioHelper.getDocumentoCobranca();
	    		
	    		Fachada.getInstancia().atualizarDocumentoCobrancaOS(idOrdemServico, documentoCobranca.getId());
	    		
	    		colecaoDocumentoCobranca.add(documentoCobranca);
	    	}
	    	
	    	// tamanho máximo de contas a aparecerem no relatório.
			int tamanhoMaximoDebito = 11;
			
			List relatorioBeans = (List) Fachada.getInstancia().gerarEmitirDocumentoVisitaCobranca(
					tamanhoMaximoDebito, null, colecaoDocumentoCobranca);
	    	
			return relatorioBeans;
	    }

		@Override
		public int calcularTotalRegistrosRelatorio() {
			Collection<Integer[]> idsImovel = (Collection<Integer[]>) getParametro("idsImovel");
			
			return idsImovel.size();
		}

		public void agendarTarefaBatch() {
			AgendadorTarefas.agendarTarefa("RelatorioDocumentoVisitaCobranca",
					this);
		}
}
