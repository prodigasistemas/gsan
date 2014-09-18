package gcom.relatorio.cobranca;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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
 * Gerar e Emitir Extrato de D�bito por Cliente
 * @author Ana Maria, Ivan Sergio
 * @date 09/04/2007, 18/02/2009
 * @alteracao 18/02/2009 - CRC1319 - Alterado a constante da Quantidade m�xima
 * 						   de linhas do detail da primeira p�gina do rel�torio;
 */

public class RelatorioExtratoDebitoCliente extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7034984685957706140L;
	
	/**
	 * Quantidade m�xima de linhas do detail da primeira p�gina do relatorio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA = 30;
	
	/**
	 * Quantidade m�xima de linhas do detail a partir da segunda p�gina do rel�torio
	 */
	public final static int NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS = 48;
	
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO = 19;

	public RelatorioExtratoDebitoCliente(Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_EXTRATO_DEBITO_CLIENTE);
	}
	
	@Deprecated
	public RelatorioExtratoDebitoCliente() {
		super(null, "");
	}

	private Collection<RelatorioExtratoDebitoClienteBean> inicializarBeanRelatorio(
			Collection<ContaValoresHelper> colecaoContas,
			Collection<DebitoACobrar> colecaoDebitoACobrar,
			Collection<CreditoARealizar> colecaoCreditoARealizar,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores,
			SistemaParametro sistemaParametro,
			BigDecimal valorTotalContasBigDecimal) {

		Collection<RelatorioExtratoDebitoClienteBean> retorno = new ArrayList();
		Collection<RelatorioExtratoDebitoClienteDetailBean> colecaoDetailBean = new ArrayList();
		Collection<RelatorioExtratoDebitoClienteContasDetailBean> colecaoContasDetailBean = new ArrayList();
		Collection<RelatorioExtratoDebitoClienteServicosDetailBean> colecaoServicosDetailBean = new ArrayList();
		
		
		int totalLinhasRelatorio = 0;
		int totalPaginasRelatorio = 1;
		int indicadorPrimeiraPagina = 1;
		int existeContas = 2;
		int existeServicos = 2;
		//variavel responsavel por conter a quantidade de linhas exibidas na pagina.
		int quantidadeLinhas = 0;
		
		
		//CRC0959 - Vivianne Sousa - 08/09/2010 - analista:Fatima Sampaio
		boolean ehBoletoBancario = false;
		//Verifica se o extrato vai ser um Boleto Banc�rio
		if( valorTotalContasBigDecimal != null 
			&& sistemaParametro.getValorExtratoFichaComp() != null
			&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
			&& valorTotalContasBigDecimal.compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
			
			ehBoletoBancario = true;
		}
		
		/*
		 * Selecionar os itens do documento de cobran�a
		 * correspondentes a conta e ordenar por ano/m�s de
		 * refer�ncia da conta
		 */		
		if (colecaoContas != null && !colecaoContas.isEmpty()){
			
			String faturaAtrasada1 = "";
			String vencimentoFatura1 = "";
			String matricula1 = "";
			String valorFatura1 = "";
			String faturaAtrasada2 = "";
			String vencimentoFatura2 = "";
			String matricula2 = "";
			String valorFatura2 = "";
			
			Object[] cobrancaDocumentoItemContasArray =  colecaoContas.toArray();
			
			//Percorre todas as contas.
			for (int j = 0; j < cobrancaDocumentoItemContasArray.length; j = j + 1) {
				
				 faturaAtrasada1 = "";
				 vencimentoFatura1 = "";
				 valorFatura1 = "";
				 faturaAtrasada2 = "";
				 vencimentoFatura2 = "";
				 valorFatura2 = "";
	
				 quantidadeLinhas = controlePaginacaoRelatorio(indicadorPrimeiraPagina,ehBoletoBancario);
				 
				 ContaValoresHelper contaHelper = new ContaValoresHelper();
				 //Monta a primeira coluna das contas
				if (j < cobrancaDocumentoItemContasArray.length){
					
					contaHelper = (ContaValoresHelper) cobrancaDocumentoItemContasArray[j];
					
					//M�s/Ano de refer�ncia da conta
					faturaAtrasada1 = Util.completaString(Util.formatarAnoMesParaMesAno(contaHelper.getConta().getReferencia()), 9);
					// Matricula
					matricula1 = Util.retornaMatriculaImovelFormatada(contaHelper.getConta().getImovel().getId());
					// Data de vencimento da conta
					vencimentoFatura1 = Util.formatarData(contaHelper.getConta().getDataVencimentoConta());
					// Valor do item
					valorFatura1 = Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(contaHelper.getConta().getValorTotal()), 16);
					
					j++;
					totalLinhasRelatorio = totalLinhasRelatorio + 1;
				}
				
				//Monta a segunda coluna das contas.
				if (j < cobrancaDocumentoItemContasArray.length){
					contaHelper = (ContaValoresHelper) cobrancaDocumentoItemContasArray[j];
						
					//M�s/Ano de refer�ncia da conta
					faturaAtrasada2 = Util.completaString(Util.formatarAnoMesParaMesAno(contaHelper.getConta().getReferencia()), 9);
					// Matricula
					matricula2 = Util.retornaMatriculaImovelFormatada(contaHelper.getConta().getImovel().getId());
					// Data de vencimento da conta
					vencimentoFatura2 = Util.formatarData(contaHelper.getConta().getDataVencimentoConta());
					// Valor do item
					valorFatura2 = Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(contaHelper.getConta().getValorTotal()), 16);
					
				}
			
				RelatorioExtratoDebitoClienteContasDetailBean relatorioExtratoDebitoClienteContasDetailBean = 
				new RelatorioExtratoDebitoClienteContasDetailBean(faturaAtrasada1, vencimentoFatura1, 
						valorFatura1, faturaAtrasada2, vencimentoFatura2, valorFatura2,matricula1,matricula2);
				colecaoContasDetailBean.add(relatorioExtratoDebitoClienteContasDetailBean);
				existeContas = 1;
				
				if (totalLinhasRelatorio == quantidadeLinhas) {
					
					RelatorioExtratoDebitoClienteServicosDetailBean relatorioExtratoDebitoClienteServicosDetailBean = null;
					colecaoServicosDetailBean.add(relatorioExtratoDebitoClienteServicosDetailBean);
					
					RelatorioExtratoDebitoClienteDetailBean relatorioExtratoDebitoClienteDetailBean = 
						new RelatorioExtratoDebitoClienteDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoClienteDetailBean);
					
					RelatorioExtratoDebitoClienteBean relatorioExtratoDebitoClienteBean = 
						new RelatorioExtratoDebitoClienteBean("" + indicadorPrimeiraPagina,colecaoDetailBean);
					
					retorno.add(relatorioExtratoDebitoClienteBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
				
				if (totalLinhasRelatorio == quantidadeLinhas) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
					totalLinhasRelatorio = 0;
				}


				 
			}
		}
		
		
		
		//Ordenar os itens da lista de servi�os, pela natureza do lan�amento 
		//(D�BITOS A COBRAR, GUIAS DE PAGAMENTO e CR�DITOS A REALIZAR, nesta ordem),
		//tipo de servi�o e ano/m�s de refer�ncia.
		
		String descricaoServicoParcelamento = "";
		String referenciaServicoParcelamento = "";
		String parcelasServicoParcelamento = "";
		BigDecimal valorServicoParcelamento = new BigDecimal("0.00");
		
		if(colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
	
			//ordena��o da cole��o de debito a cobrar por tipo de servi�o e ano/m�s de refer�ncia
			Collections.sort((List) colecaoDebitoACobrar, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((DebitoACobrar) a).getDebitoTipo().getId() );
					Integer tipoServico2 = new Integer(((DebitoACobrar) b).getDebitoTipo().getId() );
	
					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else {
	
						if ( ((DebitoACobrar) a).getAnoMesReferenciaDebito() != null && 
								((DebitoACobrar) b).getAnoMesReferenciaDebito() != null ) {
						
							Integer referencia1 = new Integer(((DebitoACobrar) a).getAnoMesReferenciaDebito());
							Integer referencia2 = new Integer(((DebitoACobrar) b).getAnoMesReferenciaDebito());
	
						return referencia1.compareTo(referencia2);
						} else {
							return 0;
						}
					}
				}
			});
	
			
			Iterator iteratorDebitoACobrar = colecaoDebitoACobrar.iterator();
			
			while (iteratorDebitoACobrar.hasNext()) {
				quantidadeLinhas = controlePaginacaoRelatorio(indicadorPrimeiraPagina,ehBoletoBancario);
				
				DebitoACobrar debitoACobrar = (DebitoACobrar) iteratorDebitoACobrar.next();
				
				if(debitoACobrar.getParcelamento() != null){
					
					descricaoServicoParcelamento = "PARCELAMENTO";
					
					if(debitoACobrar.getAnoMesReferenciaDebito() != null){
						referenciaServicoParcelamento = debitoACobrar.getFormatarAnoMesReferenciaDebito();
					}else{
						referenciaServicoParcelamento = "";
					}
					
					//parcelasServicoParcelamento = debitoACobrar.getNumeroPrestacaoCobradasMaisBonus()+ "/" + debitoACobrar.getNumeroPrestacaoDebito();
					
					parcelasServicoParcelamento = "" + debitoACobrar.getParcelasRestanteComBonus();

					valorServicoParcelamento = valorServicoParcelamento.add(debitoACobrar.getValorTotalComBonus());
					
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
					
					RelatorioExtratoDebitoClienteServicosDetailBean relatorioExtratoDebitoClienteServicosDetailBean = 
						new RelatorioExtratoDebitoClienteServicosDetailBean(descricaoServico,referenciaServico,parcelasServico,valorServico, "");
					colecaoServicosDetailBean.add(relatorioExtratoDebitoClienteServicosDetailBean);
					
					existeServicos = 1;
					totalLinhasRelatorio = totalLinhasRelatorio + 1;
					
				}
				
				if (totalLinhasRelatorio == quantidadeLinhas) {

					RelatorioExtratoDebitoClienteDetailBean relatorioExtratoDebitoClienteDetailBean = 
						new RelatorioExtratoDebitoClienteDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoClienteDetailBean);
					
					RelatorioExtratoDebitoClienteBean relatorioExtratoDebitoClienteBean = 
						new RelatorioExtratoDebitoClienteBean("" + indicadorPrimeiraPagina,colecaoDetailBean);
					
					retorno.add(relatorioExtratoDebitoClienteBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
			
				
				
				if (totalLinhasRelatorio== quantidadeLinhas) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
					totalLinhasRelatorio = 0;
				}
			}
		}
		
		
		if(colecaoGuiaPagamentoValores != null && !colecaoGuiaPagamentoValores.isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			String vencimentoServico = "";
			
			
			//ordena��o da cole��o de guia de pagamento por tipo de servi�o e ano/m�s de refer�ncia
			Collections.sort((List) colecaoGuiaPagamentoValores, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((GuiaPagamentoValoresHelper) a).getGuiaPagamento().getDebitoTipo().getId() );
					Integer tipoServico2 = new Integer(((GuiaPagamentoValoresHelper) b).getGuiaPagamento().getDebitoTipo().getId() );
	
					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else {
	
						Date dataEmissao1 = ((GuiaPagamentoValoresHelper) a).getGuiaPagamento().getDataEmissao();
						Date dataEmissao2 = ((GuiaPagamentoValoresHelper) b).getGuiaPagamento().getDataEmissao();
	
						return dataEmissao1.compareTo(dataEmissao2);
	
					}
				}
			});
	
			
			Iterator iteratorGuiaPagamento = colecaoGuiaPagamentoValores.iterator();
			
			while (iteratorGuiaPagamento.hasNext()) {
				GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) iteratorGuiaPagamento.next();
				
				GuiaPagamento guiaPagamento = guiaPagamentoValoresHelper.getGuiaPagamento();
				
				if(guiaPagamento.getDebitoTipo() != null){
					descricaoServico = guiaPagamento.getDebitoTipo().getDescricao();
				}else{
					descricaoServico = "";
				}
				
				/**
				 * @autor Wellington Rocha
				 * @date 01/02/2012
				 * 
				 * Mudan�a para ano/referencia cont�bil da guia
				 * */
				if(guiaPagamento.getAnoMesReferenciaContabil() != null){
					referenciaServico = Util.formatarAnoMesParaMesAno(guiaPagamento.getAnoMesReferenciaContabil());
				}else{
					referenciaServico = "";
				}
				
				parcelasServico = guiaPagamento.getPrestacaoFormatada();
				valorServico = Util.formatarMoedaReal(guiaPagamento.getValorDebito()) ;
				
				vencimentoServico = Util.formatarData(guiaPagamento.getDataVencimento());
				
				RelatorioExtratoDebitoClienteServicosDetailBean relatorioExtratoDebitoClienteServicosDetailBean = 
					new RelatorioExtratoDebitoClienteServicosDetailBean(descricaoServico,
							referenciaServico,parcelasServico,valorServico,vencimentoServico);
				colecaoServicosDetailBean.add(relatorioExtratoDebitoClienteServicosDetailBean);
				
				existeServicos = 1;
				totalLinhasRelatorio = totalLinhasRelatorio + 1;
					 
				if (totalLinhasRelatorio == quantidadeLinhas) {

					RelatorioExtratoDebitoClienteDetailBean relatorioExtratoDebitoClienteDetailBean = 
						new RelatorioExtratoDebitoClienteDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoClienteDetailBean);
					
					RelatorioExtratoDebitoClienteBean relatorioExtratoDebitoClienteBean = 
						new RelatorioExtratoDebitoClienteBean("" + indicadorPrimeiraPagina,colecaoDetailBean);
					
					retorno.add(relatorioExtratoDebitoClienteBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
			
			
					if (totalLinhasRelatorio == quantidadeLinhas) {
						totalPaginasRelatorio = totalPaginasRelatorio + 1;
						indicadorPrimeiraPagina = totalPaginasRelatorio;
						totalLinhasRelatorio = 0;
					}
			}
		}
		
		if(colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			
			//ordena��o da cole��o de debito a cobrar por tipo de servi�o e ano/m�s de refer�ncia
			Collections.sort((List) colecaoCreditoARealizar, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((CreditoARealizar) a).getCreditoTipo().getId());
					Integer tipoServico2 = new Integer(((CreditoARealizar) b).getCreditoTipo().getId());
	
					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else {
	
						Integer referencia1 = new Integer(((CreditoARealizar) a).getAnoMesReferenciaCredito());
						Integer referencia2 = new Integer(((CreditoARealizar) b).getAnoMesReferenciaCredito());
	
						return referencia1.compareTo(referencia2);
	
					}
				}
			});
	
			
			Iterator iteratorCreditoARealizar = colecaoCreditoARealizar.iterator();
			
			while (iteratorCreditoARealizar.hasNext()) {
				
				quantidadeLinhas = controlePaginacaoRelatorio(indicadorPrimeiraPagina,ehBoletoBancario);
				
				CreditoARealizar creditoARealizar = (CreditoARealizar)iteratorCreditoARealizar.next();
				
				
				if(creditoARealizar.getParcelamento() != null){
					
					if(referenciaServicoParcelamento.equalsIgnoreCase("") && 
							creditoARealizar.getAnoMesReferenciaCredito() != null){
						referenciaServicoParcelamento = creditoARealizar.getFormatarAnoMesCobrancaCredito();
					}else{
						referenciaServicoParcelamento = "";
					}
					
					if (parcelasServicoParcelamento.equalsIgnoreCase("")){
						parcelasServicoParcelamento = "" + creditoARealizar.getParcelasRestanteComBonus();  
					}
					
					valorServicoParcelamento = valorServicoParcelamento.subtract(creditoARealizar.getValorTotalComBonus());
					
				}else{
					
					if(creditoARealizar.getCreditoTipo() != null){
						descricaoServico = creditoARealizar.getCreditoTipo().getDescricao();
					}else{
						descricaoServico = "";
					}
					
					if(creditoARealizar.getAnoMesReferenciaCredito() != null){
						referenciaServico = creditoARealizar.getFormatarAnoMesCobrancaCredito();
					}else{
						referenciaServico = "";
					}
					
					parcelasServico = "" + creditoARealizar.getParcelasRestanteComBonus();  
					valorServico = Util.formatarMoedaReal(creditoARealizar.getValorTotalComBonus()) ;
					
					RelatorioExtratoDebitoClienteServicosDetailBean relatorioExtratoDebitoClienteServicosDetailBean = 
						new RelatorioExtratoDebitoClienteServicosDetailBean(descricaoServico,referenciaServico,parcelasServico,"-" + valorServico,"");
					colecaoServicosDetailBean.add(relatorioExtratoDebitoClienteServicosDetailBean);
					
					existeServicos = 1;
					totalLinhasRelatorio = totalLinhasRelatorio + 1;
					
				}
				
				if (totalLinhasRelatorio == quantidadeLinhas) {
					
					RelatorioExtratoDebitoClienteDetailBean relatorioExtratoDebitoClienteDetailBean = 
						new RelatorioExtratoDebitoClienteDetailBean(colecaoContasDetailBean,
								colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
					colecaoDetailBean.add(relatorioExtratoDebitoClienteDetailBean);
					
					RelatorioExtratoDebitoClienteBean relatorioExtratoDebitoClienteBean = 
						new RelatorioExtratoDebitoClienteBean("" + indicadorPrimeiraPagina,colecaoDetailBean);
					
					retorno.add(relatorioExtratoDebitoClienteBean);
					
					colecaoDetailBean.clear();
					colecaoContasDetailBean.clear();
					colecaoServicosDetailBean.clear();
					existeContas = 2;
					existeServicos = 2;
					
				}
				
				
				if (totalLinhasRelatorio == quantidadeLinhas) {
					totalPaginasRelatorio = totalPaginasRelatorio + 1;
					indicadorPrimeiraPagina = totalPaginasRelatorio;
					totalLinhasRelatorio = 0;
				}
				
			}
		}
		
		
		if(!descricaoServicoParcelamento.equalsIgnoreCase("")){
			
			RelatorioExtratoDebitoClienteServicosDetailBean relatorioExtratoDebitoClienteServicosDetailBean = 
				new RelatorioExtratoDebitoClienteServicosDetailBean(descricaoServicoParcelamento,
						referenciaServicoParcelamento,parcelasServicoParcelamento,
						Util.formatarMoedaReal(valorServicoParcelamento),"");
			colecaoServicosDetailBean.add(relatorioExtratoDebitoClienteServicosDetailBean);

			
			existeServicos = 1;
			totalLinhasRelatorio = totalLinhasRelatorio + 1;
			
		}

		
		if  (totalLinhasRelatorio!= NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA && 
				((totalLinhasRelatorio- NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA) %
						NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS != 0)
						|| totalLinhasRelatorio == NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA) {

			RelatorioExtratoDebitoClienteDetailBean relatorioExtratoDebitoClienteDetailBean = 
				new RelatorioExtratoDebitoClienteDetailBean(colecaoContasDetailBean,
						colecaoServicosDetailBean,"" + existeContas, "" + existeServicos);
			colecaoDetailBean.add(relatorioExtratoDebitoClienteDetailBean);
			
			RelatorioExtratoDebitoClienteBean relatorioExtratoDebitoClienteBean = 
				new RelatorioExtratoDebitoClienteBean("" + indicadorPrimeiraPagina,colecaoDetailBean);
			
			retorno.add(relatorioExtratoDebitoClienteBean);
			
		}
		
		
		return retorno;
	}
	
	//Metodo responsavel por definir quantas linhas seram exibidas no relatorio.
	public int controlePaginacaoRelatorio(int indicadorPagina,boolean ehBoletoBancario) {
		int quantidadeLinhas = 0;
		if(indicadorPagina > 1 ) {
			quantidadeLinhas = NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS;
		} else {
			
			if(ehBoletoBancario){
				quantidadeLinhas = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO;
			}else{
				quantidadeLinhas = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA;
			}
			
	
		}
		return quantidadeLinhas;
	}


	/**
	 * M�todo que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();
		
		String nomeCliente = (String) getParametro("nomeCliente");
		String codigoClienteResponsavel = (String) getParametro("codigoClienteResponsavel");
		String enderecoCliente = (String) getParametro("enderecoCliente");
		String seqDocCobranca = (String) getParametro("seqDocCobranca");
		String tipoResponsavel = (String) getParametro("tipoResponsavel");
		String dataEmissao = (String) getParametro("dataEmissao");
		String dataValidade = (String) getParametro("dataValidade");
		String valorContas = (String) getParametro("valorContas");
		String debitosACobrar = (String) getParametro("debitosACobrar");
		String acrescimoImpontualidade = (String) getParametro("acrescimoImpontualidade");
		String valorGuiasPagamento = (String) getParametro("valorGuiasPagamento");
		String valorDescontosCreditos = (String) getParametro("valorDescontosCreditos");	
		String valorTotalContas = (String) getParametro("valorTotalContas");
		String valorTotalImpostos = (String) getParametro("valorTotalImpostos");
		String cpfCnpj = (String) getParametro("cpfCnpj");
		String tipoFederal = (String) getParametro("tipoFederal");		

		String representacaoNumericaCodBarra = (String) getParametro("representacaoNumericaCodBarra");
		String representacaoNumericaCodBarraSemDigito = (String) getParametro("representacaoNumericaCodBarraSemDigito");
		
		Collection<ContaValoresHelper> colecaoContas = (Collection)getParametro("colecaoContas");
		
		Collection<DebitoACobrar> colecaoDebitoACobrar = (Collection)getParametro("colecaoDebitoACobrar");
		Collection<CreditoARealizar> colecaoCreditoARealizar = (Collection)getParametro("colecaoCreditoARealizar");
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = (Collection)getParametro("colecaoGuiaPagamentoValores");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String nossoNumero = (String)getParametro("nossoNumero");
		BigDecimal valorTotalContasBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorTotalContas);
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String,String> parametros = new HashMap();

		Collection<RelatorioExtratoDebitoClienteBean> colecaoBean = this
				.inicializarBeanRelatorio(colecaoContas,colecaoDebitoACobrar,
				colecaoCreditoARealizar,colecaoGuiaPagamentoValores,
				sistemaParametro,valorTotalContasBigDecimal);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		parametros.put("tipoFederal", tipoFederal);
		//Linha 2
		parametros.put("nomeCliente",nomeCliente);
		parametros.put("codigoClienteResponsavel",codigoClienteResponsavel);
		parametros.put("cpfCnpj", cpfCnpj);
		
		//Linha 3
		parametros.put("enderecoCliente",enderecoCliente);
		parametros.put("seqDocCobranca",seqDocCobranca);
		
		//Linha 4
		parametros.put("tipoResponsavel", tipoResponsavel);
		parametros.put("quantidadeFaturas", ""+colecaoContas.size());
		 
		//Linha 11
		parametros.put("dataEmissao",dataEmissao);
		parametros.put("valorContas",valorContas);
		parametros.put("debitosACobrar",debitosACobrar);
		parametros.put("acrescimoImpontualidade",acrescimoImpontualidade);
		parametros.put("valorGuiasPagamento",valorGuiasPagamento);
		parametros.put("valorDescontosCreditos",valorDescontosCreditos);
		parametros.put("valorTotalContas",valorTotalContas);
		parametros.put("valorTotalImpostos",valorTotalImpostos);
		
		//Linha 13 
		parametros.put("representacaoNumericaCodBarra",representacaoNumericaCodBarra);
		parametros.put("representacaoNumericaCodBarraSemDigito",representacaoNumericaCodBarraSemDigito);
		
		parametros.put("dataValidade",dataValidade);
		
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String idUsuario = "";
		String nomeUsuario = "";
		
		Usuario usuario = this.getUsuario();
		
		if (usuario != null) {
			idUsuario = usuario.getId().toString();
			nomeUsuario = usuario.getNomeUsuario();
		} else {
			idUsuario = "INTERNET";
		}
		
		parametros.put("nomeUsuario" ,nomeUsuario);
		
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());
		
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		
		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		//CRC0959 - Vivianne Sousa - 08/09/2010 - analista:Fatima Sampaio
		if( valorTotalContasBigDecimal!= null && sistemaParametro.getValorExtratoFichaComp() != null
			&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
			&& valorTotalContasBigDecimal.compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
		
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
					ConstantesRelatorios.RELATORIO_EXTRATO_DEBITO_CLIENTE_BOLETO_BANCARIO, parametros,
					ds, tipoFormatoRelatorio);
			
		}else{
		
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_EXTRATO_DEBITO_CLIENTE, parametros,
					ds, tipoFormatoRelatorio);
		}
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.EXTRATO_DEBITO_CLIENTE,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;

	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 0;
	}
	
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioExtratoDebitoCliente", this);
	}
}
