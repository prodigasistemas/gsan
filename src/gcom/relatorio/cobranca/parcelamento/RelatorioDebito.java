package gcom.relatorio.cobranca.parcelamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0444] Gerar e Emitir Simulador de Débito
 * @author Adriana Muniz 
 * @date 26/12/2011
 */

public class RelatorioDebito extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034984685957706140L;
	
	/**
	 * Quantidade máxima de linhas do detail da primeira página do relatorio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA = 19;
	
	/**
	 * Quantidade máxima de linhas do detail a partir da segunda página do relátorio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS = 44;
	
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO = 12;

	public RelatorioDebito(Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_DEBITO);
	}
	
	@Deprecated
	public RelatorioDebito() {
		super(null, "");
	}

	private Collection<RelatorioExtratoDebitoBean> inicializarBeanRelatorio(
			RelatorioDebitoHelper relatorioDebitoHelper, 
			Imovel imovel, SistemaParametro sistemaParametro, 
			Fachada fachada, String empresa) {
		
			
		Collection<RelatorioExtratoDebitoBean> retorno = new ArrayList();
		Collection<RelatorioExtratoDebitoDetailBean> colecaoDetailBean = new ArrayList();
		Collection<RelatorioExtratoDebitoContasDetailBean> colecaoContasDetailBean = new ArrayList();
		Collection<RelatorioExtratoDebitoServicosDetailBean> colecaoServicosDetailBean = new ArrayList();
		
		String dataAtual = Util.formatarData(new Date());
		String dataEmissao = null;
		String usuarioEmissao = null;
		
		//CRC0959 - Vivianne Sousa - 08/09/2010 - analista:Fatima Sampaio
		int numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA;
		/*//Verifica se o extrato vai ser um Boleto Bancário
		if(extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento()!= null 
			&& sistemaParametro.getValorExtratoFichaComp() != null
			&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
			&& extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento().
				compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
			
			numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO;
		}*/
		
		int totalLinhasRelatorio = 0;
		int totalPaginasRelatorio = 1;
		int indicadorPrimeiraPagina = 1;
		int existeContas = 2;
		int existeServicos = 2;
		/*
		 * Selecionar as conta e ordenar por ano/mês de referência da conta
		 */
		if (relatorioDebitoHelper.getColecaoContas() != null 
				&& !relatorioDebitoHelper.getColecaoContas().isEmpty()){
		
			ContaValoresHelper contaValoresHelper = null;
			Collection  colecaoContas = relatorioDebitoHelper.getColecaoContas();	
			
			//Ordena a coleção de contas por ano/mês de referencia da conta
			Collections.sort((List) colecaoContas, new Comparator() {
				public int compare(Object a, Object b) {
					Integer anoMesReferencia1 = new Integer(((ContaValoresHelper) a).getConta().getReferencia() );
					Integer anoMesReferencia2 = new Integer(((ContaValoresHelper) b).getConta().getReferencia()) ;
			
					return anoMesReferencia1.compareTo(anoMesReferencia2);

				}
			});
	
			String faturaAtrasada1 = "";
			String vencimentoFatura1 = "";
			String valorFatura1 = "";
			String faturaAtrasada2 = "";
			String vencimentoFatura2 = "";
			String valorFatura2 = "";
			
			Object[] contasArray =  colecaoContas.toArray();
			
			for (int j = 0; j < contasArray.length; j = j + 1) {
				
				 faturaAtrasada1 = "";
				 vencimentoFatura1 = "";
				 valorFatura1 = "";
				 faturaAtrasada2 = "";
				 vencimentoFatura2 = "";
				 valorFatura2 = "";
				
				if (j < contasArray.length){
					contaValoresHelper = new ContaValoresHelper();
					contaValoresHelper = (ContaValoresHelper)contasArray[j];
					
					//Mês/Ano de referência da conta
					 faturaAtrasada1 = Util.completaString(Util.formatarAnoMesParaMesAno(contaValoresHelper.getConta().getReferencia()),9);
					// Data de vencimento da conta
					 vencimentoFatura1 = Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta());
					// Valor do item
					 valorFatura1 = Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(contaValoresHelper.getValorTotalConta()),16);
					 
					j++;
					totalLinhasRelatorio = totalLinhasRelatorio + 1;
				}
				
				if (j < contasArray.length){
					contaValoresHelper = new ContaValoresHelper();
					contaValoresHelper = (ContaValoresHelper)contasArray[j];
						
					//Mês/Ano de referência da conta
					 faturaAtrasada2 = Util.completaString(Util.formatarAnoMesParaMesAno(contaValoresHelper.getConta().getReferencia()),9);
					// Data de vencimento da conta
					 vencimentoFatura2 = Util.formatarData(contaValoresHelper.getConta().getDataVencimentoConta());
					// Valor do item
					 valorFatura2 = Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(contaValoresHelper.getValorTotalConta()),16);
					 
				}
			
				RelatorioExtratoDebitoContasDetailBean relatorioExtratoDebitoContasDetailBean = 
				new RelatorioExtratoDebitoContasDetailBean(faturaAtrasada1, vencimentoFatura1, 
						valorFatura1, faturaAtrasada2, vencimentoFatura2, valorFatura2);
				colecaoContasDetailBean.add(relatorioExtratoDebitoContasDetailBean);
				
				existeContas = 1;
				 
				if ((totalLinhasRelatorio == numeroMaxLinhasDetailPrimeiraPagina) || 
						(totalLinhasRelatorio - numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					
					RelatorioExtratoDebitoServicosDetailBean relatorioExtratoDebitoServicosDetailBean = null;
					colecaoServicosDetailBean.add(relatorioExtratoDebitoServicosDetailBean);
					
					RelatorioExtratoDebitoDetailBean relatorioExtratoDebitoDetailBean = 
						new RelatorioExtratoDebitoDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoDetailBean);
					
					RelatorioExtratoDebitoBean relatorioExtratoDebitoBean = 
						new RelatorioExtratoDebitoBean(colecaoDetailBean,"" + indicadorPrimeiraPagina);
					relatorioExtratoDebitoBean.setEmpresa(empresa);
					relatorioExtratoDebitoBean.setDataEmissao(dataEmissao);
					relatorioExtratoDebitoBean.setUsuarioEmissao(usuarioEmissao);
					retorno.add(relatorioExtratoDebitoBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
			
				if ((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
				}
				 
			}
		}
		
		//Ordenar os itens da lista de serviços, pela natureza do lançamento 
		//(DÉBITOS A COBRAR, GUIAS DE PAGAMENTO e CRÉDITOS A REALIZAR, nesta ordem),
		//tipo de serviço e ano/mês de referência.
		
		//os debitos e credito relacionados a parcelamento serão 
		//acumulados e exibidos no final dos serviços
		String descricaoServicoParcelamento = "";
		String referenciaServicoParcelamento = "";
		String parcelasServicoParcelamento = "";
		BigDecimal valorServicoParcelamento = new BigDecimal("0.00");
		
		if(relatorioDebitoHelper.getColecaoDebitosACobrar() != null &&
		   !relatorioDebitoHelper.getColecaoDebitosACobrar().isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			
			Collection colecaoDebitosACobrar = 
				relatorioDebitoHelper.getColecaoDebitosACobrar();
			
			//ordenação da coleção de debito a cobrar por tipo de serviço e ano/mês de referência
			Collections.sort((List) colecaoDebitosACobrar, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((DebitoACobrar) a).getDebitoTipo().getId() );
					Integer tipoServico2 = new Integer(((DebitoACobrar) b).getDebitoTipo().getId() );
	
					if (!tipoServico1.equals(tipoServico2)) {
					
						return tipoServico1.compareTo(tipoServico2);
					} else if ( ((DebitoACobrar) a) != null &&
								((DebitoACobrar) a).getAnoMesReferenciaDebito() != null && 
								((DebitoACobrar) b) != null &&
								((DebitoACobrar) b).getAnoMesReferenciaDebito() != null) {
								
							 Integer referencia1 = new Integer(((DebitoACobrar) a).getAnoMesReferenciaDebito());
			 				 Integer referencia2 = new Integer(((DebitoACobrar) b).getAnoMesReferenciaDebito());
			
							 return referencia1.compareTo(referencia2);
					 } else {
						 
						 return 0;
					 }
					
				}
			});
	
			
			Iterator iteratorDebitoACobrar = colecaoDebitosACobrar.iterator();
			
			while (iteratorDebitoACobrar.hasNext()) {
				
				DebitoACobrar debitoACobrar = (DebitoACobrar) iteratorDebitoACobrar.next();
				
				
				if(debitoACobrar.getParcelamento() != null){
					
					descricaoServicoParcelamento = "PARCELAMENTO";
					
					if(debitoACobrar.getAnoMesReferenciaDebito() != null){
						referenciaServicoParcelamento = debitoACobrar.getFormatarAnoMesReferenciaDebito();
					}else{
						referenciaServicoParcelamento = "";
					}
					
					//descomentar quando entrar alteração da antecipação do parcelamento
					/*if(debitoACobrar.getNumeroParcelasAntecipadas() != null){*/
					if(relatorioDebitoHelper.getNumeroParcelasAntecipadasDebitos() != 0){
						
						//parcelasServicoParcelamento = "" + debitoACobrar.getNumeroParcelasAntecipadas();
						parcelasServicoParcelamento = "" + relatorioDebitoHelper.getNumeroParcelasAntecipadasDebitos();
						
						valorServicoParcelamento = valorServicoParcelamento.add(debitoACobrar.getValorAntecipacaoParcela(debitoACobrar.getNumeroParcelasAntecipadas()));
						
					}else{
						parcelasServicoParcelamento = "" + debitoACobrar.getParcelasRestanteComBonus();
						//debitoACobrar.getNumeroPrestacaoCobradasMaisBonus()+ "/" + debitoACobrar.getNumeroPrestacaoDebito();

						valorServicoParcelamento = valorServicoParcelamento.add(debitoACobrar.getValorTotalComBonus());
						
					}
					
				
				}else{
					
					if(debitoACobrar.getDebitoTipo() != null){
						descricaoServico = debitoACobrar.getDebitoTipo().getDescricao();
					}else{
						descricaoServico = "";
					}
					
					if(debitoACobrar.getAnoMesReferenciaDebito() != null){
						referenciaServico = debitoACobrar.getFormatarAnoMesReferenciaDebito();
					}else{
						referenciaServico = "";
					}
					
					parcelasServico = "" + debitoACobrar.getParcelasRestanteComBonus(); 
						//debitoACobrar.getNumeroPrestacaoCobradasMaisBonus()+ "/" + debitoACobrar.getNumeroPrestacaoDebito();
					valorServico = Util.formatarMoedaReal(debitoACobrar.getValorTotalComBonus()) ;
					
					RelatorioExtratoDebitoServicosDetailBean relatorioExtratoDebitoServicosDetailBean = 
						new RelatorioExtratoDebitoServicosDetailBean(descricaoServico,referenciaServico,parcelasServico,valorServico,"");
					colecaoServicosDetailBean.add(relatorioExtratoDebitoServicosDetailBean);
					
					existeServicos = 1;
					totalLinhasRelatorio = totalLinhasRelatorio + 1;
					
					
				}
				
					 
				if ((totalLinhasRelatorio == numeroMaxLinhasDetailPrimeiraPagina) || 
						(totalLinhasRelatorio - numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {

					RelatorioExtratoDebitoDetailBean relatorioExtratoDebitoDetailBean = 
						new RelatorioExtratoDebitoDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoDetailBean);
					
					RelatorioExtratoDebitoBean relatorioExtratoDebitoBean = 
						new RelatorioExtratoDebitoBean(colecaoDetailBean,"" + indicadorPrimeiraPagina);
					relatorioExtratoDebitoBean.setEmpresa(empresa);
					relatorioExtratoDebitoBean.setDataEmissao(dataEmissao);
					relatorioExtratoDebitoBean.setUsuarioEmissao(usuarioEmissao);
					retorno.add(relatorioExtratoDebitoBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
			
				if ((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
				}
				
				
			}
		}
		
		
		if(relatorioDebitoHelper.getColecaoGuiasPagamento() != null &&
		   !relatorioDebitoHelper.getColecaoGuiasPagamento().isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			String vencimentoServico = "";
			
			Collection colecaoGuiasPagamento = 
				relatorioDebitoHelper.getColecaoGuiasPagamento();
			
			//ordenação da coleção de guia de pagamento por tipo de serviço e ano/mês de referência
			Collections.sort((List) colecaoGuiasPagamento, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((GuiaPagamentoValoresHelper) a).getGuiaPagamento().getDebitoTipo().getId() );
					Integer tipoServico2 = new Integer(((GuiaPagamentoValoresHelper) b).getGuiaPagamento().getDebitoTipo().getId() );
	
					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else if ( ((GuiaPagamentoValoresHelper) a).getGuiaPagamento() != null &&
								((GuiaPagamentoValoresHelper) a).getGuiaPagamento().getAnoGuia() != null &&
								((GuiaPagamentoValoresHelper) b).getGuiaPagamento() != null &&
								((GuiaPagamentoValoresHelper) b).getGuiaPagamento().getAnoGuia() != null) {
						
							Integer referencia1 = new Integer(((GuiaPagamentoValoresHelper) a).getGuiaPagamento().getAnoGuia());
							Integer referencia2 = new Integer(((GuiaPagamentoValoresHelper) b).getGuiaPagamento().getAnoGuia());
		
							return referencia1.compareTo(referencia2);
		
						
					} else {
						return 0;
					}
						
					
				}
			});
	
			
			Iterator iteratorGuiaPagamento = colecaoGuiasPagamento.iterator();
			
			while (iteratorGuiaPagamento.hasNext()) {
				GuiaPagamentoValoresHelper guiaPagamentoHelper = (GuiaPagamentoValoresHelper) iteratorGuiaPagamento.next();
				
				GuiaPagamento guiaPagamento = guiaPagamentoHelper.getGuiaPagamento();
				
				if(guiaPagamento.getDebitoTipo() != null){
					descricaoServico = guiaPagamento.getDebitoTipo().getDescricao();
				}else{
					descricaoServico = "";
				}
				
				if(guiaPagamento.getAnoGuia() != null){
					referenciaServico = guiaPagamento.getFormatarAnoMesReferenciaGuia();
				}else{
					referenciaServico = "";
				}
				
				parcelasServico = guiaPagamento.getPrestacaoFormatada();
				valorServico = Util.formatarMoedaReal(guiaPagamento.getValorDebito()) ;
				vencimentoServico = Util.formatarData(guiaPagamento.getDataVencimento());
				
				RelatorioExtratoDebitoServicosDetailBean relatorioExtratoDebitoServicosDetailBean = 
					new RelatorioExtratoDebitoServicosDetailBean(descricaoServico,referenciaServico,parcelasServico,valorServico,vencimentoServico);
				colecaoServicosDetailBean.add(relatorioExtratoDebitoServicosDetailBean);
				
				existeServicos = 1;
				totalLinhasRelatorio = totalLinhasRelatorio + 1;
					 
				if ((totalLinhasRelatorio == numeroMaxLinhasDetailPrimeiraPagina) || 
						(totalLinhasRelatorio - numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {

					RelatorioExtratoDebitoDetailBean relatorioExtratoDebitoDetailBean = 
						new RelatorioExtratoDebitoDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoDetailBean);
					
					RelatorioExtratoDebitoBean relatorioExtratoDebitoBean = 
						new RelatorioExtratoDebitoBean(colecaoDetailBean,"" + indicadorPrimeiraPagina);
					relatorioExtratoDebitoBean.setEmpresa(empresa);
					relatorioExtratoDebitoBean.setDataEmissao(dataEmissao);
					relatorioExtratoDebitoBean.setUsuarioEmissao(usuarioEmissao);
					retorno.add(relatorioExtratoDebitoBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
			
				if ((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
				}
			}
		}
		
		if(relatorioDebitoHelper.getColecaoCreditoARealizar() != null &&
		   !relatorioDebitoHelper.getColecaoCreditoARealizar().isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			
			Collection colecaoCreditoARealizar = relatorioDebitoHelper.getColecaoCreditoARealizar();
			
			//ordenação da coleção de debito a cobrar por tipo de serviço e ano/mês de referência
			Collections.sort((List) colecaoCreditoARealizar, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((CreditoARealizar) a).getCreditoTipo().getId());
					Integer tipoServico2 = new Integer(((CreditoARealizar) b).getCreditoTipo().getId());
	
					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else if ( ((CreditoARealizar) a) != null && 
							((CreditoARealizar) a).getAnoMesReferenciaCredito() != null &&
							((CreditoARealizar) b) != null &&
							((CreditoARealizar) b).getAnoMesReferenciaCredito() != null){
	
						Integer referencia1 = new Integer(((CreditoARealizar) a).getAnoMesReferenciaCredito());
						Integer referencia2 = new Integer(((CreditoARealizar) b).getAnoMesReferenciaCredito());
	
						return referencia1.compareTo(referencia2);
	
					}else {
						return 0;
					}
				}
			});
	
			
			Iterator iteratorCreditoARealizar = colecaoCreditoARealizar.iterator();
			
			while (iteratorCreditoARealizar.hasNext()) {
				CreditoARealizar creditoARealizar = (CreditoARealizar) iteratorCreditoARealizar.next();
				
				/*CreditoARealizar creditoARealizar = cobrancaDocumentoItemDebitoACobrar.getCreditoARealizarGeral().getCreditoARealizar();*/
				
				
				if(creditoARealizar.getParcelamento() != null){
					
					System.out.println("parc " + creditoARealizar.getParcelamento().getId());
					
					if(referenciaServicoParcelamento.equalsIgnoreCase("") && 
							creditoARealizar.getAnoMesReferenciaCredito() != null){
						referenciaServicoParcelamento = creditoARealizar.getFormatarAnoMesCobrancaCredito();
					}else{
						referenciaServicoParcelamento = "";
					}
					
					
					//descomentar quando entrar alteração da antecipação do parcelamento
					//if(creditoARealizar.getNumeroParcelasAntecipadas() != null){
					if(relatorioDebitoHelper.getNumeroParcelasAntecipadasCreditos() != 0){
						
						//parcelasServicoParcelamento = "" + creditoARealizar.getNumeroParcelasAntecipadas();
						parcelasServicoParcelamento = "" + relatorioDebitoHelper.getNumeroParcelasAntecipadasCreditos();
						
						valorServicoParcelamento = valorServicoParcelamento.subtract(creditoARealizar.getValorAntecipacaoParcela(creditoARealizar.getNumeroParcelasAntecipadas()));
						
					}else{
						
						if (parcelasServicoParcelamento.equalsIgnoreCase("")){
							parcelasServicoParcelamento = "" + creditoARealizar.getParcelasRestanteComBonus(); 
						}
						
						valorServicoParcelamento = valorServicoParcelamento.subtract(creditoARealizar.getValorTotalComBonus());
						
					}
					
				}else{
					
					if(creditoARealizar.getCreditoTipo() != null){
						descricaoServico = creditoARealizar.getCreditoTipo().getDescricao();
					}else{
						descricaoServico = "";
					}
					
					if(creditoARealizar.getAnoMesReferenciaCredito() != null){
						referenciaServico = creditoARealizar.getFormatarAnoMesReferenciaCredito();
					}else{
						referenciaServico = "";
					}
					
					parcelasServico =  "" + creditoARealizar.getParcelasRestanteComBonus();  
					valorServico = Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus()) ;
					
					RelatorioExtratoDebitoServicosDetailBean relatorioExtratoDebitoServicosDetailBean = 
						new RelatorioExtratoDebitoServicosDetailBean(descricaoServico,referenciaServico,parcelasServico,"-" + valorServico,"");
					colecaoServicosDetailBean.add(relatorioExtratoDebitoServicosDetailBean);
					
					existeServicos = 1;
					totalLinhasRelatorio = totalLinhasRelatorio + 1;
					
				}
				
				
				
					 
				if ((totalLinhasRelatorio == numeroMaxLinhasDetailPrimeiraPagina) || 
						(totalLinhasRelatorio - numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					
					RelatorioExtratoDebitoDetailBean relatorioExtratoDebitoDetailBean = 
						new RelatorioExtratoDebitoDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoDetailBean);
					
					RelatorioExtratoDebitoBean relatorioExtratoDebitoBean = 
						new RelatorioExtratoDebitoBean(colecaoDetailBean,"" + indicadorPrimeiraPagina);
					relatorioExtratoDebitoBean.setEmpresa(empresa);
					relatorioExtratoDebitoBean.setDataEmissao(dataEmissao);
					relatorioExtratoDebitoBean.setUsuarioEmissao(usuarioEmissao);
					retorno.add(relatorioExtratoDebitoBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
			
				if ((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) % NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS == 0) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
				}
			}
		}
		
		
		if(!descricaoServicoParcelamento.equalsIgnoreCase("")){
			
			RelatorioExtratoDebitoServicosDetailBean relatorioExtratoDebitoServicosDetailBean = 
				new RelatorioExtratoDebitoServicosDetailBean(descricaoServicoParcelamento,
						referenciaServicoParcelamento,parcelasServicoParcelamento,
						Util.formatarMoedaReal(valorServicoParcelamento),"");
			colecaoServicosDetailBean.add(relatorioExtratoDebitoServicosDetailBean);
			
			existeServicos = 1;
			totalLinhasRelatorio = totalLinhasRelatorio + 1;
			
		}
		
		if  (totalLinhasRelatorio  == numeroMaxLinhasDetailPrimeiraPagina ||
				(totalLinhasRelatorio!= numeroMaxLinhasDetailPrimeiraPagina && 
				((totalLinhasRelatorio- numeroMaxLinhasDetailPrimeiraPagina) %
						NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS != 0))) {

			RelatorioExtratoDebitoDetailBean relatorioExtratoDebitoDetailBean = 
				new RelatorioExtratoDebitoDetailBean(colecaoContasDetailBean,
						colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
			colecaoDetailBean.add(relatorioExtratoDebitoDetailBean);
			
			RelatorioExtratoDebitoBean relatorioExtratoDebitoBean = 
				new RelatorioExtratoDebitoBean(colecaoDetailBean,"" + indicadorPrimeiraPagina);
			relatorioExtratoDebitoBean.setEmpresa(empresa);
			relatorioExtratoDebitoBean.setDataEmissao(dataEmissao);
			relatorioExtratoDebitoBean.setUsuarioEmissao(usuarioEmissao);
			retorno.add(relatorioExtratoDebitoBean);
			
		}
		
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String nomeLocalidade = (String) getParametro("nomeLocalidade");
		String inscricao = (String) getParametro("inscricao");
		String nomeUsuario = (String) getParametro("nomeUsuario");
		String cpfCnpj = (String) getParametro("cpfCnpj");
		String matricula = (String) getParametro("matricula");
		String enderecoImovel = (String) getParametro("enderecoImovel");
//		String seqDocCobranca = (String) getParametro("seqDocCobranca");
		String situacaoAgua = (String) getParametro("situacaoAgua");
		String situacaoEsgoto = (String) getParametro("situacaoEsgoto");
		String qtdResidencial = (String) getParametro("qtdResidencial");
		String qtdComercial = (String) getParametro("qtdComercial");
		String qtdIndustrial = (String) getParametro("qtdIndustrial");
		String qtdPublico = (String) getParametro("qtdPublico");
		String descPerfilImovel = (String) getParametro("descPerfilImovel");
		String dataEmissao = (String) getParametro("dataEmissao");
//		String dataValidade = (String) getParametro("dataValidade");
//		String representacaoNumericaCodBarra = (String) getParametro("representacaoNumericaCodBarra");
//		String representacaoNumericaCodBarraSemDigito = (String) getParametro("representacaoNumericaCodBarraSemDigito");
		
		String valorTotalContas = (String) getParametro("valorTotalContas"); 
		String valorServicosAtualizacoes =(String) getParametro("valorServicosAtualizacoes");
		String valorDesconto =  (String) getParametro("valorDesconto");
		String valorTotalComDesconto = (String) getParametro("valorTotalComDesconto");
		String codigoRotaESequencialRota = (String)getParametro("codigoRotaESequencialRota");
		Imovel imovel =  (Imovel) getParametro("imovel");
//		String nossoNumero = (String)getParametro("nossoNumero");
		
		RelatorioDebitoHelper relatorioDebitoHelper = (RelatorioDebitoHelper)getParametro("relatorioDebitoHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String,String> parametros = new HashMap();

		RelatorioDebitoHelper dadosRelatorio = relatorioDebitoHelper;
		

		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String empresa = "\n						"+	sistemaParametro.getNomeAbreviadoEmpresa() +" - "+cnpjEmpresa;

		Collection<RelatorioExtratoDebitoBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio,imovel,sistemaParametro,fachada,empresa);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		
		//Residêncial
		if (!qtdResidencial.equals("")) {
			qtdResidencial = Util.adicionarZerosEsquedaNumero(3,qtdResidencial);
		} 
		// Comercial
		if (!qtdComercial.equals("")) {
			qtdComercial = Util.adicionarZerosEsquedaNumero(3,qtdComercial);
		}
		// Industrial
		if (!qtdIndustrial.equals("")) {
			qtdIndustrial = Util.adicionarZerosEsquedaNumero(3,qtdIndustrial);
		} 
		// Público
		if (!qtdPublico.equals("")) {
			qtdPublico = Util.completaStringComEspacoAEsquerda(qtdPublico, 3);
		} 
		
		
		//Linha 1
		parametros.put("nomeLocalidade",nomeLocalidade);
		
		//Linha 2
		parametros.put("inscricao",inscricao);
		parametros.put("nomeUsuario",nomeUsuario);
		parametros.put("cpfCnpj", cpfCnpj);
		parametros.put("matricula",matricula);
		
		//Linha 3
		parametros.put("enderecoImovel",enderecoImovel);
		/*parametros.put("seqDocCobranca",seqDocCobranca);*/
		
		//Linha 4
		parametros.put("situacaoAgua",situacaoAgua);
		parametros.put("situacaoEsgoto",situacaoEsgoto);
		parametros.put("qtdResidencial",qtdResidencial);
		parametros.put("qtdComercial",qtdComercial);
		parametros.put("qtdIndustrial",qtdIndustrial);
		parametros.put("qtdPublico",qtdPublico);
		parametros.put("descPerfilImovel",descPerfilImovel);
		parametros.put("dataEmissao",dataEmissao);
		/*parametros.put("dataValidade",dataValidade);*/
		
		//Linha 7 
		parametros.put("valorTotalContas",valorTotalContas);
		
		//Linha 8
		parametros.put("valorServicosAtualizacoes",valorServicosAtualizacoes);
		
		//Linha 9 
		parametros.put("valorDesconto",valorDesconto);
		
		//Linha 10
		parametros.put("valorTotalComDesconto",valorTotalComDesconto);
		
		//Linha 11 
	/*	parametros.put("representacaoNumericaCodBarra",representacaoNumericaCodBarra);
		parametros.put("representacaoNumericaCodBarraSemDigito",representacaoNumericaCodBarraSemDigito);
*/
		String idUsuario = "";
		String nameUsuario = "";
		
		Usuario usuario = this.getUsuario();
		
		if (usuario != null) {
			idUsuario = usuario.getId().toString();
			nameUsuario = usuario.getNomeUsuario();
		} else {
			idUsuario = "INTERNET";
		}
		
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("imagemFundo", "./imagens/documento_nao_pagavel.jpg");
		
		//parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("nameUsuario", nameUsuario);
		
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		
		
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
			codigoRotaESequencialRota = null;
		}
		
		parametros.put("codigoRotaESequencialRota", codigoRotaESequencialRota);
		
		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

/*		//CRC0959 - Vivianne Sousa - 08/09/2010 - analista:Fatima Sampaio
		if(extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento()!= null 
				&& sistemaParametro.getValorExtratoFichaComp() != null
				&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
				&& extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento().
					compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
			
			String cedente = sistemaParametro.getNomeAbreviadoEmpresa() + "-" + sistemaParametro.getNomeEmpresa();
			
			parametros.put("cedente", cedente);
			parametros.put("dataAtual", Util.formatarData(new Date()));
			parametros.put("nossoNumero", nossoNumero);
			
			if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_COMPESA)){
				parametros.put("agenciaCodigoCedente","3234-4/2868-1");
			}else if(sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAERN)){
				parametros.put("agenciaCodigoCedente","3795-8/9121-9");
			}
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_EXTRATO_DEBITO_BOLETO_BANCARIO, parametros,
					ds, tipoFormatoRelatorio);
			
		}else{
*/			
			
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_DEBITO, parametros,
					ds, tipoFormatoRelatorio);
		//}
		
		
		
	
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.SIMULADOR_DEBITO,
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
		
		return 0;
	}
	
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioExtratoDebito", this);
	}
}
