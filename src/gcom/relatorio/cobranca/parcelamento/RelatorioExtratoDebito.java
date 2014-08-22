package gcom.relatorio.cobranca.parcelamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.batch.Relatorio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumentoItem;
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
import gcom.util.filtro.ParametroSimples;

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
 * [UC0444] Gerar e Emitir Extrato de D�bito
 */
public class RelatorioExtratoDebito extends TarefaRelatorio {

	private static final long serialVersionUID = -7034984685957706140L;
	
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA = 19;
	public final static int NUMERO_MAX_lINHAS_DETAIL_OUTRAS_PAGINAS = 44;
	public final static int NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO = 12;

	public RelatorioExtratoDebito(Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_EXTRATO_DEBITO);
	}
	
	@Deprecated
	public RelatorioExtratoDebito() {
		super(null, "");
	}

	private Collection<RelatorioExtratoDebitoBean> inicializarBeanRelatorio(
			ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper, 
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
		
		if(extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento()!= null 
			&& sistemaParametro.getValorExtratoFichaComp() != null
			&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
			&& extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento().
				compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
			
			numeroMaxLinhasDetailPrimeiraPagina = NUMERO_MAX_lINHAS_DETAIL_PRIMEIRA_PAGINA_BOLETO;
		}
		
		
		if(sistemaParametro.getNomeAbreviadoEmpresa().toString().equals("CAEMA")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(
				new ParametroSimples(
					FiltroLocalidade.ID,
					imovel.getLocalidade().getId()));

			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

			Collection cLocalidade = 
				(Collection) fachada.pesquisar(
					filtroLocalidade,Localidade.class.getName());
			
			Localidade localidade = (Localidade) cLocalidade.iterator().next();
			
			String endereco = localidade.getEnderecoFormatadoTituloAbreviado();
			String telefone = Util.completaString(localidade.getFone(), 9);
			String cnpj = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
			String inscricaoEstadual = Util.formatarInscricaoEstadualCaema(sistemaParametro.getInscricaoEstadual());
			/*
			empresa = "N� Fatura:"+numeroFatura+"    "+endereco+"\n"+
				"Emitida em:"+dataEmissao+"   Cnpj:"+cnpj+"    Fone:"+telefone+"\n" +
				" Insc. Estadual:"+inscricaoEstadual;
			*/
			
			empresa = endereco+"\n"+
				"Cnpj:"+cnpj+"  Fone:"+telefone+"\n" +
				"Insc. Estadual:"+inscricaoEstadual+"\n" +
				"Data emiss�o:"+dataAtual;
			
			dataEmissao = "Emitido em: "+dataAtual;
			usuarioEmissao = "Emitido por: "+this.getUsuario().getNomeUsuario();
			
		}
		
		
		int totalLinhasRelatorio = 0;
		int totalPaginasRelatorio = 1;
		int indicadorPrimeiraPagina = 1;
		int existeContas = 2;
		int existeServicos = 2;
		/*
		 * Selecionar os itens do documento de cobran�a
		 * correspondentes a conta e ordenar por ano/m�s de refer�ncia da conta
		 */
		if (extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemContas() != null 
				&& !extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemContas().isEmpty()){
		
			CobrancaDocumentoItem cobrancaDocumentoItem = null;
			Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemContas = extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemContas();	
			
			//Ordena a cole��o de CobrancaDocumentoItem por ano/m�s de referencia da conta
			Collections.sort((List) colecaoCobrancaDocumentoItemContas, new Comparator() {
				public int compare(Object a, Object b) {
					Integer anoMesReferencia1 = new Integer(((CobrancaDocumentoItem) a).getContaGeral().getConta().getReferencia() );
					Integer anoMesReferencia2 = new Integer(((CobrancaDocumentoItem) b).getContaGeral().getConta().getReferencia()) ;
			
					return anoMesReferencia1.compareTo(anoMesReferencia2);

				}
			});
	
			String faturaAtrasada1 = "";
			String vencimentoFatura1 = "";
			String valorFatura1 = "";
			String faturaAtrasada2 = "";
			String vencimentoFatura2 = "";
			String valorFatura2 = "";
			
			Object[] cobrancaDocumentoItemContasArray =  colecaoCobrancaDocumentoItemContas.toArray();
			
			for (int j = 0; j < cobrancaDocumentoItemContasArray.length; j = j + 1) {
				
				 faturaAtrasada1 = "";
				 vencimentoFatura1 = "";
				 valorFatura1 = "";
				 faturaAtrasada2 = "";
				 vencimentoFatura2 = "";
				 valorFatura2 = "";
				
				if (j < cobrancaDocumentoItemContasArray.length){
					cobrancaDocumentoItem = new CobrancaDocumentoItem();
					cobrancaDocumentoItem = (CobrancaDocumentoItem)cobrancaDocumentoItemContasArray[j];
					
					//M�s/Ano de refer�ncia da conta
					 faturaAtrasada1 = Util.completaString(Util.formatarAnoMesParaMesAno(cobrancaDocumentoItem.getContaGeral().getConta().getReferencia()),9);
					// Data de vencimento da conta
					 vencimentoFatura1 = Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta());
					// Valor do item
					 valorFatura1 = Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(cobrancaDocumentoItem.getValorItemCobrado()),16);
					 
					j++;
					totalLinhasRelatorio = totalLinhasRelatorio + 1;
				}
				
				if (j < cobrancaDocumentoItemContasArray.length){
					 cobrancaDocumentoItem = new CobrancaDocumentoItem();
					 cobrancaDocumentoItem = (CobrancaDocumentoItem)cobrancaDocumentoItemContasArray[j];
						
					//M�s/Ano de refer�ncia da conta
					 faturaAtrasada2 = Util.completaString(Util.formatarAnoMesParaMesAno(cobrancaDocumentoItem.getContaGeral().getConta().getReferencia()),9);
					// Data de vencimento da conta
					 vencimentoFatura2 = Util.formatarData(cobrancaDocumentoItem.getContaGeral().getConta().getDataVencimentoConta());
					// Valor do item
					 valorFatura2 = Util.completaStringComEspacoAEsquerda(Util.formatarMoedaReal(cobrancaDocumentoItem.getValorItemCobrado()),16);
					 
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
		
		//Ordenar os itens da lista de servi�os, pela natureza do lan�amento 
		//(D�BITOS A COBRAR, GUIAS DE PAGAMENTO e CR�DITOS A REALIZAR, nesta ordem),
		//tipo de servi�o e ano/m�s de refer�ncia.
		
		//os debitos e credito relacionados a parcelamento ser�o 
		//acumulados e exibidos no final dos servi�os
		String descricaoServicoParcelamento = "";
		String referenciaServicoParcelamento = "";
		String parcelasServicoParcelamento = "";
		BigDecimal valorServicoParcelamento = new BigDecimal("0.00");
		
		if(extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemDebitosACobrar() != null &&
		   !extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemDebitosACobrar().isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			
			Collection colecaoCobrancaDocumentoItemDebitosACobrar = 
				extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemDebitosACobrar();
			
			//ordena��o da cole��o de debito a cobrar por tipo de servi�o e ano/m�s de refer�ncia
			Collections.sort((List) colecaoCobrancaDocumentoItemDebitosACobrar, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((CobrancaDocumentoItem) a).getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() );
					Integer tipoServico2 = new Integer(((CobrancaDocumentoItem) b).getDebitoACobrarGeral().getDebitoACobrar().getDebitoTipo().getId() );
	
					if (!tipoServico1.equals(tipoServico2)) {
					
						return tipoServico1.compareTo(tipoServico2);
					} else if ( ((CobrancaDocumentoItem) a).getDebitoACobrarGeral().getDebitoACobrar() != null &&
								((CobrancaDocumentoItem) a).getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaDebito() != null && 
								((CobrancaDocumentoItem) b).getDebitoACobrarGeral().getDebitoACobrar() != null &&
								((CobrancaDocumentoItem) b).getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaDebito() != null) {
								
							 Integer referencia1 = new Integer(((CobrancaDocumentoItem) a).getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaDebito());
			 				 Integer referencia2 = new Integer(((CobrancaDocumentoItem) b).getDebitoACobrarGeral().getDebitoACobrar().getAnoMesReferenciaDebito());
			
							 return referencia1.compareTo(referencia2);
					 } else {
						 
						 return 0;
					 }
					
				}
			});
	
			
			Iterator iteratorDebitoACobrar = colecaoCobrancaDocumentoItemDebitosACobrar.iterator();
			
			while (iteratorDebitoACobrar.hasNext()) {
				CobrancaDocumentoItem cobrancaDocumentoItemDebitoACobrar = (CobrancaDocumentoItem) iteratorDebitoACobrar.next();
				
				DebitoACobrar debitoACobrar = cobrancaDocumentoItemDebitoACobrar.getDebitoACobrarGeral().getDebitoACobrar();
				
				
				if(debitoACobrar.getParcelamento() != null){
					
					descricaoServicoParcelamento = "PARCELAMENTO";
					
					if(debitoACobrar.getAnoMesReferenciaDebito() != null){
						referenciaServicoParcelamento = debitoACobrar.getFormatarAnoMesReferenciaDebito();
					}else{
						referenciaServicoParcelamento = "";
					}
					
					//descomentar quando entrar altera��o da antecipa��o do parcelamento
					if(cobrancaDocumentoItemDebitoACobrar.getNumeroParcelasAntecipadas() != null){
						
						parcelasServicoParcelamento = "" + cobrancaDocumentoItemDebitoACobrar.getNumeroParcelasAntecipadas();
						
						valorServicoParcelamento = valorServicoParcelamento.add(cobrancaDocumentoItemDebitoACobrar.getValorItemCobrado());
						
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
		
		
		if(extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemGuiasPagamento() != null &&
		   !extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemGuiasPagamento().isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			String vencimentoServico = "";
			
			Collection colecaoCobrancaDocumentoItemGuiasPagamento = 
				extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemGuiasPagamento();
			
			//ordena��o da cole��o de guia de pagamento por tipo de servi�o e ano/m�s de refer�ncia
			Collections.sort((List) colecaoCobrancaDocumentoItemGuiasPagamento, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((CobrancaDocumentoItem) a).getGuiaPagamentoGeral().getGuiaPagamento().getDebitoTipo().getId() );
					Integer tipoServico2 = new Integer(((CobrancaDocumentoItem) b).getGuiaPagamentoGeral().getGuiaPagamento().getDebitoTipo().getId() );
	
					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else if ( ((CobrancaDocumentoItem) a).getGuiaPagamentoGeral().getGuiaPagamento() != null &&
								((CobrancaDocumentoItem) a).getGuiaPagamentoGeral().getGuiaPagamento().getAnoGuia() != null &&
								((CobrancaDocumentoItem) b).getGuiaPagamentoGeral().getGuiaPagamento() != null &&
								((CobrancaDocumentoItem) b).getGuiaPagamentoGeral().getGuiaPagamento().getAnoGuia() != null) {
						
							Integer referencia1 = new Integer(((CobrancaDocumentoItem) a).getGuiaPagamentoGeral().getGuiaPagamento().getAnoGuia());
							Integer referencia2 = new Integer(((CobrancaDocumentoItem) b).getGuiaPagamentoGeral().getGuiaPagamento().getAnoGuia());
		
							return referencia1.compareTo(referencia2);
		
						
					} else {
						return 0;
					}
						
					
				}
			});
	
			
			Iterator iteratorGuiaPagamento = colecaoCobrancaDocumentoItemGuiasPagamento.iterator();
			
			while (iteratorGuiaPagamento.hasNext()) {
				CobrancaDocumentoItem cobrancaDocumentoItemGuiaPagamento = (CobrancaDocumentoItem) iteratorGuiaPagamento.next();
				
				GuiaPagamento guiaPagamento = cobrancaDocumentoItemGuiaPagamento.getGuiaPagamentoGeral().getGuiaPagamento();
				
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
		
		if(extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemCreditoARealizar() != null &&
		   !extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemCreditoARealizar().isEmpty()){
			
			String descricaoServico = "";
			String referenciaServico = "";
			String parcelasServico = "";
			String valorServico = "";
			
			Collection colecaoCobrancaDocumentoItemCreditoARealizar = 
				extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemCreditoARealizar();
			
			//ordena��o da cole��o de debito a cobrar por tipo de servi�o e ano/m�s de refer�ncia
			Collections.sort((List) colecaoCobrancaDocumentoItemCreditoARealizar, new Comparator() {
				public int compare(Object a, Object b) {
					Integer tipoServico1 = new Integer(((CobrancaDocumentoItem) a).getCreditoARealizarGeral().getCreditoARealizar().getCreditoTipo().getId());
					Integer tipoServico2 = new Integer(((CobrancaDocumentoItem) b).getCreditoARealizarGeral().getCreditoARealizar().getCreditoTipo().getId());
	
					if (!tipoServico1.equals(tipoServico2)) {
						return tipoServico1.compareTo(tipoServico2);
					} else if ( ((CobrancaDocumentoItem) a).getCreditoARealizarGeral().getCreditoARealizar() != null && 
							((CobrancaDocumentoItem) a).getCreditoARealizarGeral().getCreditoARealizar().getAnoMesReferenciaCredito() != null &&
							((CobrancaDocumentoItem) b).getCreditoARealizarGeral().getCreditoARealizar() != null &&
							((CobrancaDocumentoItem) b).getCreditoARealizarGeral().getCreditoARealizar().getAnoMesReferenciaCredito() != null){
	
						Integer referencia1 = new Integer(((CobrancaDocumentoItem) a).getCreditoARealizarGeral().getCreditoARealizar().getAnoMesReferenciaCredito());
						Integer referencia2 = new Integer(((CobrancaDocumentoItem) b).getCreditoARealizarGeral().getCreditoARealizar().getAnoMesReferenciaCredito());
	
						return referencia1.compareTo(referencia2);
	
					}else {
						return 0;
					}
				}
			});
	
			
			Iterator iteratorCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar.iterator();
			
			while (iteratorCreditoARealizar.hasNext()) {
				CobrancaDocumentoItem cobrancaDocumentoItemDebitoACobrar = (CobrancaDocumentoItem) iteratorCreditoARealizar.next();
				
				CreditoARealizar creditoARealizar = cobrancaDocumentoItemDebitoACobrar.getCreditoARealizarGeral().getCreditoARealizar();
				
				
				if(creditoARealizar.getParcelamento() != null){
					
					System.out.println("parc " + creditoARealizar.getParcelamento().getId());
					
					if(referenciaServicoParcelamento.equalsIgnoreCase("") && 
							creditoARealizar.getAnoMesReferenciaCredito() != null){
						referenciaServicoParcelamento = creditoARealizar.getFormatarAnoMesCobrancaCredito();
					}else{
						referenciaServicoParcelamento = "";
					}
					
					
					//descomentar quando entrar altera��o da antecipa��o do parcelamento
					if(cobrancaDocumentoItemDebitoACobrar.getNumeroParcelasAntecipadas() != null){
						
						parcelasServicoParcelamento = "" + cobrancaDocumentoItemDebitoACobrar.getNumeroParcelasAntecipadas();
						
						valorServicoParcelamento = valorServicoParcelamento.subtract(cobrancaDocumentoItemDebitoACobrar.getValorItemCobrado());
						
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
	 * M�todo que executa a tarefa
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
		String seqDocCobranca = (String) getParametro("seqDocCobranca");
		String situacaoAgua = (String) getParametro("situacaoAgua");
		String situacaoEsgoto = (String) getParametro("situacaoEsgoto");
		String qtdResidencial = (String) getParametro("qtdResidencial");
		String qtdComercial = (String) getParametro("qtdComercial");
		String qtdIndustrial = (String) getParametro("qtdIndustrial");
		String qtdPublico = (String) getParametro("qtdPublico");
		String descPerfilImovel = (String) getParametro("descPerfilImovel");
		String dataEmissao = (String) getParametro("dataEmissao");
		String dataValidade = (String) getParametro("dataValidade");
		String representacaoNumericaCodBarra = (String) getParametro("representacaoNumericaCodBarra");
		String representacaoNumericaCodBarraSemDigito = (String) getParametro("representacaoNumericaCodBarraSemDigito");
		
		String valorTotalContas = (String) getParametro("valorTotalContas"); 
		String valorServicosAtualizacoes =(String) getParametro("valorServicosAtualizacoes");
		String valorDesconto =  (String) getParametro("valorDesconto");
		String valorTotalComDesconto = (String) getParametro("valorTotalComDesconto");
		String codigoRotaESequencialRota = (String)getParametro("codigoRotaESequencialRota");
		Imovel imovel =  (Imovel) getParametro("imovel");
		String nossoNumero = (String)getParametro("nossoNumero");
		
		ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper = (ExtratoDebitoRelatorioHelper)getParametro("extratoDebitoRelatorioHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Par�metros do relat�rio
		Map<String,String> parametros = new HashMap();

		ExtratoDebitoRelatorioHelper dadosRelatorio = extratoDebitoRelatorioHelper;
		

		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		String empresa = "\n						"+	sistemaParametro.getNomeAbreviadoEmpresa() +" - "+cnpjEmpresa;

		Collection<RelatorioExtratoDebitoBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio,imovel,sistemaParametro,fachada,empresa);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// N�o existem dados para a exibi��o do relat�rio.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		
		//Resid�ncial
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
		// P�blico
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
		parametros.put("seqDocCobranca",seqDocCobranca);
		
		//Linha 4
		parametros.put("situacaoAgua",situacaoAgua);
		parametros.put("situacaoEsgoto",situacaoEsgoto);
		parametros.put("qtdResidencial",qtdResidencial);
		parametros.put("qtdComercial",qtdComercial);
		parametros.put("qtdIndustrial",qtdIndustrial);
		parametros.put("qtdPublico",qtdPublico);
		parametros.put("descPerfilImovel",descPerfilImovel);
		parametros.put("dataEmissao",dataEmissao);
		parametros.put("dataValidade",dataValidade);
		
		//Linha 7 
		parametros.put("valorTotalContas",valorTotalContas);
		
		//Linha 8
		parametros.put("valorServicosAtualizacoes",valorServicosAtualizacoes);
		
		//Linha 9 
		parametros.put("valorDesconto",valorDesconto);
		
		//Linha 10
		parametros.put("valorTotalComDesconto",valorTotalComDesconto);
		
		//Linha 11 
		parametros.put("representacaoNumericaCodBarra",representacaoNumericaCodBarra);
		parametros.put("representacaoNumericaCodBarraSemDigito",representacaoNumericaCodBarraSemDigito);

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
		
		parametros.put("nomeEmpresa", sistemaParametro.getNomeAbreviadoEmpresa());
		
		//parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("idUsuario", idUsuario);
		parametros.put("nameUsuario", nameUsuario);
		
		parametros.put("indicadorExibirMsgNaoReceberPagamento", sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		
		
		if (sistemaParametro.getCodigoEmpresaFebraban().equals(SistemaParametro.CODIGO_EMPRESA_FEBRABAN_CAER)){
			codigoRotaESequencialRota = null;
		}
		
		parametros.put("codigoRotaESequencialRota", codigoRotaESequencialRota);
		
		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		//CRC0959 - Vivianne Sousa - 08/09/2010 - analista:Fatima Sampaio
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
			
			
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_EXTRATO_DEBITO, parametros,
					ds, tipoFormatoRelatorio);
		}
		
		
		
	
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.EXTRATO_DEBITO,
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
		AgendadorTarefas.agendarTarefa("RelatorioExtratoDebito", this);
	}
}
