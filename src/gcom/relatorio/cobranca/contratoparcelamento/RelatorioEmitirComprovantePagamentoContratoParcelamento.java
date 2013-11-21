package gcom.relatorio.cobranca.contratoparcelamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroPrestacaoItemContratoParcelamento;
import gcom.cobranca.contratoparcelamento.PrestacaoItemContratoParcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.faturamento.debito.FiltroDebitoACobrarHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




public class RelatorioEmitirComprovantePagamentoContratoParcelamento extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioEmitirComprovantePagamentoContratoParcelamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_COMPROVANTE_CONTRATO_PARCELAMENTO);
	}
	public Object executar() throws TarefaException {

		
		//Cliente
		ContratoParcelamentoCliente contratoParcelamentoCliente = (ContratoParcelamentoCliente)getParametro("contratoParcelamentoCliente");
		
		//Tipo do Relatório
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		//Ocultar Parcela
		String ocultarParcela = (String) getParametro("ocultarParcela");
		
		//Parcela para Emissao
		String parcelaInicial = (String) getParametro("inicioParcelas");
		
		//Parcela Emissão
		String parcelaEmissao = (String) getParametro("parcelaEmissao");

		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();


		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		
		
		//[SB0001] Emitir Dados do Cabeçalho
		//[SB0002] Emitir Dados Gerais do Contrato
		parametros = emitirCabecalho(contratoParcelamentoCliente, parametros, sistemaParametro, ocultarParcela);
		
		
		//[SB0003] Emitir Dados do Pagamento
		//Itens pagos
		ArrayList<PrestacaoItemContratoParcelamento> prestacoes = getItensPrestacoes(contratoParcelamentoCliente,ocultarParcela,parcelaEmissao,fachada);
		
		
	
		
		//[SB0004] Emitir Dados do Item de Débito		
		RelatorioDataSource ds = montarBeans(parcelaInicial, fachada, prestacoes);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_COMPROVANTE_CONTRATO_PARCELAMENTO,
		parametros, ds, tipoFormatoRelatorio);
		
		
		// retorna o relatório gerado
		return retorno;
	}
	
	
	
	
	private RelatorioDataSource montarBeans(String parcelaInicial, Fachada fachada, ArrayList<PrestacaoItemContratoParcelamento> prestacoes) {
		ArrayList<RelatorioEmitirComprovantePagamentoContratoParcelamentoBean> beans = new ArrayList();
		Iterator it = prestacoes.iterator();
		while(it.hasNext()){
			PrestacaoItemContratoParcelamento prestacao = (PrestacaoItemContratoParcelamento) it.next();
			
			//Caso o campo "Iniciar Numeração das parcelas a partir de" esteja preenchido
			Integer parcela,parcelaTotal;
			if(parcelaInicial != null && !parcelaInicial.equals("")){
				parcela = prestacao.getPrestacao().getNumero() + new Integer(parcelaInicial) - new Integer("1");
				parcelaTotal = prestacao.getPrestacao().getContratoParcelamento().getNumeroPrestacoes() + new Integer(parcelaInicial) - new Integer("1");
			}
			else{
				parcela = prestacao.getPrestacao().getNumero();
				parcelaTotal = prestacao.getPrestacao().getContratoParcelamento().getNumeroPrestacoes();
			}
			
			
			//Tipo Conta
			if(prestacao.getItem().getDocumentoTipo().getId().compareTo(DocumentoTipo.CONTA) == 0){
				//Conta não está no histórico
				if(prestacao.getItem().getContaGeral().getIndicadorHistorico() == 2){
					FiltroConta filtroConta = new FiltroConta();
					filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.ID,prestacao.getItem().getContaGeral().getId()));
					filtroConta.adicionarCaminhoParaCarregamentoEntidade("imovel");
					ArrayList<Conta> collectionConta = new ArrayList<Conta>(fachada.pesquisar(filtroConta,Conta.class.getName()));
					if(collectionConta.size() != 0){
						Conta conta = collectionConta.get(0);
						RelatorioEmitirComprovantePagamentoContratoParcelamentoBean bean = 
								new RelatorioEmitirComprovantePagamentoContratoParcelamentoBean(
										parcela.toString(),
										parcelaTotal.toString(),
										prestacao.getItem().getDocumentoTipo().getDescricaoDocumentoTipo().toString(),
										"Matrícula",
										"Mês/Ano",
										"Vencimento",
										"Valor da Conta",
										"Acrés. Impont.",
										"Valor Pago",
										conta.getImovel().getMatriculaFormatada(),
										Util.formatarAnoMesParaMesAno(conta.getReferencia())+"",
										Util.formatarData(conta.getDataVencimentoConta()),
										Util.formatarBigDecimalParaStringComVirgula(prestacao.getItem().getValorItem()),
										Util.formatarBigDecimalParaStringComVirgula(prestacao.getItem().getValarAcrescImpont()),
										prestacao.getValorPago()
								);
						
						beans.add(bean);
						
					}
				}
				//Conta está no histórico
				else if(prestacao.getItem().getContaGeral().getIndicadorHistorico() == 1){
					FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
					filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
					filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.ID,prestacao.getItem().getContaGeral().getId()));
					ArrayList<ContaHistorico> collectionHistorico = new ArrayList<ContaHistorico>(fachada.pesquisar(filtroContaHistorico,ContaHistorico.class.getName()));
					if(collectionHistorico.size() > 0 ){
						ContaHistorico contaHistorico = collectionHistorico.get(0);
						RelatorioEmitirComprovantePagamentoContratoParcelamentoBean bean = 
							new RelatorioEmitirComprovantePagamentoContratoParcelamentoBean(
									parcela.toString(),
									parcelaTotal.toString(),
									prestacao.getItem().getDocumentoTipo().getDescricaoDocumentoTipo().toString(),
									"Matrícula",
									"Mês/Ano",
									"Vencimento",
									"Valor da Conta",
									"Acrés. Impont.",
									"Valor Pago",
									contaHistorico.getImovel().getMatriculaFormatada(),
									contaHistorico.getFormatarAnoMesParaMesAno(),
									Util.formatarData(contaHistorico.getDataVencimentoConta()),
									Util.formatarBigDecimalParaStringComVirgula(prestacao.getItem().getValorItem()),
									Util.formatarBigDecimalParaStringComVirgula(prestacao.getItem().getValarAcrescImpont()),
									prestacao.getValorPago()							
							);
						beans.add(bean);
					}
				}
			}
			
			//Tipo Guia de Pagamento
			else if(prestacao.getItem().getDocumentoTipo().getId().compareTo(DocumentoTipo.GUIA_PAGAMENTO) == 0){
				
				//Guia de pagamento não está no histórico
				if(prestacao.getItem().getGuiaPagamentoGeral().getIndicadorHistorico() == 2){
					FiltroGuiaPagamento filtroGuia = new FiltroGuiaPagamento();
					filtroGuia.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroGuia.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.ID,prestacao.getItem().getGuiaPagamentoGeral().getId()));
					ArrayList<GuiaPagamento> collectionGuia = new ArrayList<GuiaPagamento>(fachada.pesquisar(filtroGuia,GuiaPagamento.class.getName()));
					if(collectionGuia.size() > 0){
						GuiaPagamento guia = collectionGuia.get(0);
						RelatorioEmitirComprovantePagamentoContratoParcelamentoBean bean = 
						new RelatorioEmitirComprovantePagamentoContratoParcelamentoBean(
								parcela.toString(),
								parcelaTotal.toString().toString(),
								prestacao.getItem().getDocumentoTipo().getDescricaoDocumentoTipo().toString(),
								"Prestação",
								"Tipo do Débito",
								"Data de Emissão",
								"Data de Vencimento",
								"Valor da Guia",
								"Valor Pago",
								guia.getNumeroPrestacaoDebito().toString(),
								guia.getDebitoTipo().getDescricaoAbreviada(),
								Util.formatarData(guia.getDataEmissao()),
								Util.formatarData(guia.getDataVencimento()),
								Util.formatarBigDecimalParaStringComVirgula(prestacao.getItem().getValorItem()),
								prestacao.getValorPago()						
						);
					beans.add(bean);
					}
				}
				
				else if(prestacao.getItem().getGuiaPagamentoGeral().getIndicadorHistorico() == 1){
					FiltroGuiaPagamentoHistorico filtroGuiaHistorico = new FiltroGuiaPagamentoHistorico();
					filtroGuiaHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroGuiaHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoHistorico.ID,prestacao.getItem().getGuiaPagamentoGeral().getId()));
					ArrayList<GuiaPagamentoHistorico> collectionGuiaHistorico = new ArrayList<GuiaPagamentoHistorico>(fachada.pesquisar(filtroGuiaHistorico,GuiaPagamentoHistorico.class.getName()));
					if(collectionGuiaHistorico.size() > 0){
						GuiaPagamentoHistorico guiaHistorico = collectionGuiaHistorico.get(0);
						RelatorioEmitirComprovantePagamentoContratoParcelamentoBean bean = 
							new RelatorioEmitirComprovantePagamentoContratoParcelamentoBean(
									parcela.toString(),
									parcelaTotal.toString(),
									prestacao.getItem().getDocumentoTipo().getDescricaoDocumentoTipo().toString(),
									"Prestação",
									"Tipo do Débito",
									"Data de Emissão",
									"Data de Vencimento",
									"Valor da Guia",
									"Valor Pago",
									guiaHistorico.getNumeroPrestacaoDebito()+"",
									guiaHistorico.getDebitoTipo().getDescricaoAbreviada(),
									Util.formatarData(guiaHistorico.getDataEmissao()),
									Util.formatarData(guiaHistorico.getDataVencimento()),
									Util.formatarBigDecimalParaStringComVirgula(prestacao.getItem().getValorItem()),
									prestacao.getValorPago()					
							);
						beans.add(bean);
					}
				}
			}
			
			//Tipo Debito a Cobrar
			if(prestacao.getItem().getDocumentoTipo().getId().compareTo(DocumentoTipo.DEBITO_A_COBRAR) == 0){
				
				//Debito a Cobrar não está no histórico
				if(prestacao.getItem().getDebitoACobrarGeral().getIndicadorHistorico() == 2){
					FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
					filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.ID,prestacao.getItem().getDebitoACobrarGeral().getId()));
					filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade("imovel");
					filtroDebitoACobrar.adicionarCaminhoParaCarregamentoEntidade(FiltroDebitoACobrar.DEBITO_TIPO);
					ArrayList<DebitoACobrar> collectionDebitoACobrar = new ArrayList<DebitoACobrar>(fachada.pesquisar(filtroDebitoACobrar,DebitoACobrar.class.getName()));
					if(collectionDebitoACobrar.size() != 0){
						DebitoACobrar debitoACobrar = collectionDebitoACobrar.get(0);
						RelatorioEmitirComprovantePagamentoContratoParcelamentoBean bean = 
								new RelatorioEmitirComprovantePagamentoContratoParcelamentoBean(
										parcela.toString(),
										parcelaTotal.toString(),
										prestacao.getItem().getDocumentoTipo().getDescricaoDocumentoTipo().toString(),
										"Matrícula",
										"Tipo do Débito",
										"Mês/Ano Referência",
										"Mês/Ano Cobrança",
										"Valor a Cobrar",
										"Valor Pago",
										debitoACobrar.getImovel().getMatriculaFormatada()+"",
										debitoACobrar.getDebitoTipo().getDescricao(),
										debitoACobrar.getFormatarAnoMesReferenciaDebito(),
										debitoACobrar.getFormatarAnoMesCobrancaDebito(),
										Util.formatarBigDecimalParaStringComVirgula(debitoACobrar.getValorTotalComBonus()),
										prestacao.getValorPago()
								);
						
						beans.add(bean);
						
					}
				}
				//Debito a Cobrar está no histórico
				else if(prestacao.getItem().getDebitoACobrarGeral().getIndicadorHistorico() == 1){
					FiltroDebitoACobrarHistorico filtroDebitoACobrarHistorico = new FiltroDebitoACobrarHistorico();
					filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
					filtroDebitoACobrarHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDebitoACobrarHistorico.adicionarParametro(new ParametroSimples(FiltroDebitoACobrarHistorico.ID,prestacao.getItem().getDebitoACobrarGeral().getId()));
					
					ArrayList<DebitoACobrarHistorico> collectionDebitoACobrarHistorico = new ArrayList<DebitoACobrarHistorico>(fachada.pesquisar(filtroDebitoACobrarHistorico,DebitoACobrarHistorico.class.getName()));
					if(collectionDebitoACobrarHistorico.size() != 0){
						DebitoACobrarHistorico debitoACobrarHistorico = collectionDebitoACobrarHistorico.get(0);
						RelatorioEmitirComprovantePagamentoContratoParcelamentoBean bean = 
								new RelatorioEmitirComprovantePagamentoContratoParcelamentoBean(
										parcela.toString(),
										parcelaTotal.toString(),
										prestacao.getItem().getDocumentoTipo().getDescricaoDocumentoTipo().toString(),
										"Matrícula",
										"Tipo do Débito",
										"Mês/Ano Referência",
										"Mês/Ano Cobrança",
										"Valor a Cobrar",
										"Valor Pago",
										debitoACobrarHistorico.getImovel().getMatriculaFormatada()+"",
										debitoACobrarHistorico.getDebitoTipo().getDescricaoAbreviada(),
										Util.formatarAnoMesParaMesAno(debitoACobrarHistorico.getAnoMesReferenciaDebito()),
										Util.formatarAnoMesParaMesAno(debitoACobrarHistorico.getAnoMesCobrancaDebito()),
										Util.formatarBigDecimalParaStringComVirgula(debitoACobrarHistorico.getValorDebito()),
										prestacao.getValorPago()
								);
						beans.add(bean);
					}
				}
			}
		}
			
		//this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		return ds;
	}
	
	
	
	private Map emitirCabecalho(ContratoParcelamentoCliente contratoParcelamentoCliente, Map parametros, SistemaParametro sistemaParametro, String ocultarParcela) {
		
		//[SB0002] Emitir Dados Gerais do Contrato
		//Indicador de parcela oculta
		parametros.put("ocultarParcela",ocultarParcela);
		
		//Linha 1
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Linha 2
		parametros.put("nomeEmpresa",sistemaParametro.getNomeEmpresa());
		//parametros.put("dataHoraEmissao",dataAtual);
		
		//Linha 2
		parametros.put("numeroContrato",contratoParcelamentoCliente.getContrato().getNumero());
		if(contratoParcelamentoCliente.getContrato().getContratoAnterior() != null){
			parametros.put("numeroContratoAnterior",contratoParcelamentoCliente.getContrato().getContratoAnterior().getNumero());
			parametros.put("tipoRelacao",contratoParcelamentoCliente.getContrato().getRelacaoAnterior().getDescricao());
		}
		
		//Linha 3
		parametros.put("dataContrato",contratoParcelamentoCliente.getContrato().getDataContratoFormatada());
		parametros.put("dataImplantacao",Util.formatarDataComHora(contratoParcelamentoCliente.getContrato().getDataImplantacao()));
		
		//Linha 4
		parametros.put("codigoCliente",contratoParcelamentoCliente.getCliente().getId().toString());
		parametros.put("nomeCliente",contratoParcelamentoCliente.getCliente().getNome());
		parametros.put("cnpjCliente",contratoParcelamentoCliente.getCliente().getCnpjFormatado());
		if(contratoParcelamentoCliente.getIndicadorClienteSuperior() == 2){
			parametros.put("tituloCliente","Cliente");
			if(contratoParcelamentoCliente.getContrato().getRelacaoCliente() != null)
				parametros.put("tipoRelacaoCliente",contratoParcelamentoCliente.getContrato().getRelacaoCliente().getDescricao());
		}
		else{
			parametros.put("tituloCliente","Cliente Superior");
		}
		//Linha 5
		switch(contratoParcelamentoCliente.getContrato().getIndicadorResponsavel().intValue()){
			case 1:
				parametros.put("debitoSelecionado","Indicado na Conta");
				break;
			case 2:
				parametros.put("debitoSelecionado","Atual do Imóvel");
				break;
			case 3:
				parametros.put("debitoSelecionado","Indicado na Conta e Atual do Imóvel");
				break;
		}
			
		
		//Linha 6
		parametros.put("periodoReferenciaInicio",contratoParcelamentoCliente.getContrato().getAnoMesDebitoInicioFormatado());
		parametros.put("periodoReferenciaFim",contratoParcelamentoCliente.getContrato().getAnoMesDebitoFinalFormatado());
		parametros.put("periodoVencimentoInicio",contratoParcelamentoCliente.getContrato().getDataVencimentoInicioFormatada());
		parametros.put("periodoVencimentoFim",contratoParcelamentoCliente.getContrato().getDataVencimentoFinalFormatada());
		
		//Linha 7
		//Situação de Cancelamento
		if(contratoParcelamentoCliente.getContrato().getMotivoDesfazer() != null)
			parametros.put("situacaoCancelamento","Cancelado");
		else
			parametros.put("situacaoCancelamento","Não Cancelado");
		
		//Situação de Pagamento
		if(contratoParcelamentoCliente.getContrato().getValorParcelamentoACobrar() != null && contratoParcelamentoCliente.getContrato().getValorParcelamentoACobrar().compareTo(BigDecimal.ZERO) > 0)
			parametros.put("situacaoPagamento","Pendente");
		else
			parametros.put("situacaoPagamento","Pago");
		
		//Linha 8
		parametros.put("formaPagamento",contratoParcelamentoCliente.getContrato().getCobrancaForma().getDescricao());
		
		//Linha 9
		if(contratoParcelamentoCliente.getContrato().getValorTotalAcrescImpontualidade() == null)
			parametros.put("valorDebito",contratoParcelamentoCliente.getContrato().getValorDebitoAtualizado().toString());
		else{
			parametros.put("valorDebito",Util.formatarBigDecimalParaStringComVirgula(contratoParcelamentoCliente.getContrato().getValorDebitoAtualizado().subtract(contratoParcelamentoCliente.getContrato().getValorTotalAcrescImpontualidade())));
			parametros.put("valorAcrescimosImpontualidade",Util.formatarBigDecimalParaStringComVirgula(contratoParcelamentoCliente.getContrato().getValorTotalAcrescImpontualidade()));
		}
		
		
		//Linha 10
		if(contratoParcelamentoCliente.getContrato().getTaxaJuros() != null){
			String taxaJuros = Util.formatarBigDecimalParaStringComVirgula(contratoParcelamentoCliente.getContrato().getTaxaJuros());
			if(!taxaJuros.contains(",")){
				taxaJuros += ",00";
			}
			parametros.put("taxaJuros",taxaJuros);
		}
		if(contratoParcelamentoCliente.getContrato().getValorJurosParcelamento() != null)
			parametros.put("valorJurosParcelamento",Util.formatarBigDecimalParaStringComVirgula(contratoParcelamentoCliente.getContrato().getValorJurosParcelamento()));
		
		
		//Linha 11
		if(contratoParcelamentoCliente.getContrato().getValorTotalParcelado() != null)
			parametros.put("valorParcelado",Util.formatarBigDecimalParaStringComVirgula(contratoParcelamentoCliente.getContrato().getValorTotalParcelado()));
		if(contratoParcelamentoCliente.getContrato().getValorParcelamentoACobrar() != null)
			parametros.put("valorParceladoACobrar",Util.formatarBigDecimalParaStringComVirgula(contratoParcelamentoCliente.getContrato().getValorParcelamentoACobrar()));
		
		return parametros;
	}

	
	
	
	private ArrayList<PrestacaoItemContratoParcelamento> getItensPrestacoes(ContratoParcelamentoCliente contratoParcelamentoCliente,String ocultarParcela,String parcelasEmissao,Fachada fachada){
		
		String parcelaInicial,parcelaFinal;
		ArrayList<PrestacaoItemContratoParcelamento> listaPrestacaoItem;
		
		//Parcelas para emissão
		if(parcelasEmissao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			parcelaInicial = "1";
			parcelaFinal = "9999";
		}
		else{
			parcelaInicial = parcelasEmissao;
			parcelaFinal = parcelasEmissao;
		}
		
			
			FiltroPrestacaoItemContratoParcelamento filtroPrestacaoItem = new FiltroPrestacaoItemContratoParcelamento();
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("prestacao");
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("prestacao.contratoParcelamento");
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item");
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item.documentoTipo");
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item.contaGeral");
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item.guiaPagamentoGeral");
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item.creditoARealizarGeral");
			filtroPrestacaoItem.adicionarCaminhoParaCarregamentoEntidade("item.debitoACobrarGeral");
			filtroPrestacaoItem.adicionarParametro(new ParametroNaoNulo(FiltroPrestacaoItemContratoParcelamento.VALOR_PRESTACAO,ConectorAnd.CONECTOR_AND,2));
			filtroPrestacaoItem.adicionarParametro(new ParametroSimples(FiltroPrestacaoItemContratoParcelamento.CONTRATO_PRESTACAO_ID,contratoParcelamentoCliente.getContrato().getId()));
			filtroPrestacaoItem.adicionarParametro(new MenorQue(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_NUMERO,parcelaFinal,ConectorOr.CONECTOR_OR,2));
			filtroPrestacaoItem.adicionarParametro(new ParametroSimples(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_NUMERO,parcelaFinal));
			filtroPrestacaoItem.adicionarParametro(new MaiorQue(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_NUMERO,parcelaInicial,ConectorOr.CONECTOR_OR,2));
			filtroPrestacaoItem.adicionarParametro(new ParametroSimples(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_NUMERO,parcelaInicial));
			
			//Ocultar número de parcelas
			if(ocultarParcela.equals("0")){
				filtroPrestacaoItem.setCampoOrderBy(FiltroPrestacaoItemContratoParcelamento.PRESTACAO_NUMERO);				
			}
			
			filtroPrestacaoItem.setCampoOrderBy(FiltroPrestacaoItemContratoParcelamento.DOCUMENTO_TIPO_ID,
												FiltroPrestacaoItemContratoParcelamento.CONTA_ID,
												FiltroPrestacaoItemContratoParcelamento.CREDITO_REALIZAR_ID,
												FiltroPrestacaoItemContratoParcelamento.DEBITO_COBRAR_ID,
												FiltroPrestacaoItemContratoParcelamento.GUIA_PAGAMENTO_ID);
			listaPrestacaoItem = new ArrayList<PrestacaoItemContratoParcelamento>(fachada.pesquisar(filtroPrestacaoItem,PrestacaoItemContratoParcelamento.class.getName()));
			
		
		return listaPrestacaoItem;
	}
	
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}
	
	public void agendarTarefaBatch() {}
	
}
