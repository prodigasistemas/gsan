package gcom.arrecadacao;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.DadosConteudoCodigoBarrasHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoA;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gcom.arrecadacao.bean.RegistroHelperCodigoC;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.bean.RegistroHelperCodigoZ;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.SessionBean;

/**
 * Controlador Arrecadacao COSANPA
 *
 * @author Raphael Rossiter
 * @date 24/04/2009
 */
public class ControladorArrecadacaoCOSANPASEJB extends ControladorArrecadacao implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA COSANPA
	//==============================================================================================================
	
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param idPagamento
	 * @param tipoPagamento
	 * @param idEmpresa
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();

		registroHelperCodigoBarrasTipoPagamento = 
			super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		
		//LEGADO - COSANPA
		//===============================================================================================================================
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_LEGADO_COSANPA.toString())){
			
			//Tipo do documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
			.substring(0, 1).trim());
			
			//Identificação
			Long identificacaoConta = Long.parseLong(idPagamento
			.substring(1, 15).trim());
			
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(identificacaoConta.toString());
			
			//Localidade
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
			.substring(15, 24).trim());
			
			// Tipo de pagamento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(tipoPagamento);
			
			/*
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 1 (Conta_Legado OU Guia_Legado):
			 */
			if (registroHelperCodigoBarrasTipoPagamento.getIdPagamento1()
				.equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSANPA.toString())){
				
				Long identificacao = Long.valueOf(registroHelperCodigoBarrasTipoPagamento.getIdPagamento2());
				
				/*
				 * Verificar a matricula do imóvel através da identificação da fatura (G.05.7.2) 
				 * com valor igual à CNTA_NNCONTAFATURA.
				 * 
				 * Caso não exista, verificar a matrícula através da identificação da guia de pagamento 
				 * (G.05.7.2) com valor igual à GPAG_NNGUIAFATURA e retornar tipo de documento com valor = 3.
				 * 
				 * Caso não exista, verificar a matrícula através da identificação do documento de cobrança 
				 * (G.05.7.2) com valor igual à CBDO_NNDOCUMENTOFATURA e retornar tipo de documento com valor = 4);
				 * 
				 * Caso não exista, verificar o cliente através da identificação da fatura de cliente 
				 * (G.05.7.2) com valor igual à CBDO_NNNUMEROFATURA e retornar tipo de documento com valor = 5);
				 */
				Conta conta = this.pesquisarExistenciaContaPorNumeroFatura(identificacao.toString());
				
				if (conta == null){
					
					GuiaPagamento guiaPagamento = this.pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(
					identificacao.toString());
					
					if (guiaPagamento != null){
						
						//GUIA DE PAGAMENTO
						registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(
						ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSANPA.toString());
					}
					else{
						
						CobrancaDocumento cobrancaDocumento = this.pesquisarParmsCobrancaDocumentoPorNumeroDocumentoFatura(
						identificacao.toString());
						
						if (cobrancaDocumento != null){
						
							//DOCUMENTO DE COBRAÇA
							registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(
							ConstantesSistema.CODIGO_TIPO_DOCUMENTO_DOCUMENTO_COBRANCA_LEGADO_COSANPA.toString());
						}
						else{
							
							Fatura fatura = this.pesquisarFaturaPorNumeroFatura(identificacao.toString());
							
							if (fatura != null){
								
								//FATURA
								registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(
								ConstantesSistema.CODIGO_TIPO_DOCUMENTO_FATURA_LEGADO_COSANPA.toString());
							}
						}
					}
				}
			}
			
			
		}
		
		//===============================================================================================================================
		
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param registroHelperCodigoBarras
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @param sistemaParametro
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasPorTipoPagamento(
			RegistroHelperCodigoBarras registroHelperCodigoBarras, Date dataPagamento, Integer anoMesPagamento,
			BigDecimal valorPagamento, Integer idFormaArrecadacao, SistemaParametro sistemaParametro, Usuario usuarioLogado) 
			throws ControladorException {
		
		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = null;
		
		String tipoPagamento = registroHelperCodigoBarras.getTipoPagamento();

		
		
		//LEGADO - COSANPA
		//===============================================================================================================================
		
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_LEGADO_COSANPA.toString())){
			
			/*
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 1 (Conta_Legado).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 2 (Extrato).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 3 (Guia_Pagamento).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 4 (Documento_Cobranca).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 5 (Fatura).
			 * 
			 */
			if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSANPA.toString())){
				
				//CONTA
				pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_COSANPA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				System.out.println("CONTA");
				
				//===============================================================================================================================
			}
			else if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSANPA.toString())){
				
				//EXTRATO
				pagamentoHelperCodigoBarras = processarPagamentosCodigoBarrasDocumentoCobranca_COSANPA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro, dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				System.out.println("EXTRATO");
				
				//===============================================================================================================================
			}
			else if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSANPA.toString())){
				
				//GUIA DE PAGAMENTO
				pagamentoHelperCodigoBarras = processarPagamentosCodigoBarrasGuiaPagamento_COSANPA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro, dataPagamento,anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				System.out.println("GUIA DE PAGAMENTO");
				
				//===============================================================================================================================
			}
			else if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_DOCUMENTO_COBRANCA_LEGADO_COSANPA.toString())){
					
				//DOCUMENTO COBRANCA
				pagamentoHelperCodigoBarras = processarPagamentosCodigoBarrasDocumentoCobranca_COSANPA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro, dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
					
				System.out.println("DOCUMENTO COBRANCA");
					
				//===============================================================================================================================
			}
			else if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_FATURA_LEGADO_COSANPA.toString())){
					
				//FATURA
				pagamentoHelperCodigoBarras = processarPagamentosCodigoBarrasFatura_COSANPA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro, dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
					
				System.out.println("FATURA");
					
				//===============================================================================================================================
			}
			else{
				
				//atribui o valor 2(NÃO) ao indicador aceitacao registro
				Collection colecaoPagamentos = new ArrayList();
				pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
			
				pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
				pagamentoHelperCodigoBarras.setDescricaoOcorrencia("CÓDIGO DE BARRAS COM TIPO DE PAGAMENTO INVÁLIDO");
				pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro("2");
			}
		}else{
		
			pagamentoHelperCodigoBarras = super.processarPagamentosCodigoBarrasPorTipoPagamento(
				registroHelperCodigoBarras, dataPagamento,
				anoMesPagamento, valorPagamento,
				idFormaArrecadacao, sistemaParametro, usuarioLogado);
		}

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - CONTA
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaArrecadacao
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasConta_COSANPA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean numeroFaturaInvalida = false;
		Long identificacao = null;
		Conta conta = null;
		
		numeroFaturaInvalida = Util.validarValorLongoNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());

		if (numeroFaturaInvalida) {
			descricaoOcorrencia = "NÚMERO DA FATURA NÃO NUMÉRICO";
		}
		else {
			
			identificacao = Long.valueOf(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			conta = this.pesquisarExistenciaContaPorNumeroFatura(identificacao.toString());
					
			if (conta == null) {
						
				descricaoOcorrencia = "CONTA INEXISTENTE";
						
			}
		}


		if (descricaoOcorrencia.equals("OK")) {

			// GERAÇÃO DO PAGAMENTO
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(conta.getReferencia());

			/*
			 * Caso o ano mes da data de debito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito
			 */
			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {

				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);

			} else {

				/*
				 * caso contrario seta o o ano mes arrecadação da tabela sistema
				 * parametro
				 */
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
				.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setDebitoTipo(null);

			// ASSOCIANDO O PAGAMENTO COM A CONTA 
			ContaGeral contaGeral = new ContaGeral();
			contaGeral.setId(conta.getId());
			contaGeral.setConta(conta);
			
			pagamento.setContaGeral(contaGeral);
			pagamento.setImovel(conta.getImovel());
			
			pagamento.setGuiaPagamento(null);

			// ASSOCIANDO O PAGAMENTO COM A LOCALIDADE DA CONTA
			pagamento.setLocalidade(conta.getLocalidade());

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.CONTA);
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
			pagamento.setDocumentoTipo(documentoTipo);
			pagamento.setAvisoBancario(null);

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaArrecadacao);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());
			
			/*
			 * Alteracao referente ao relatorio de Float - Francisco : 14/07/08
			 */
			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.CONTA);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			pagamento.setDataProcessamento(new Date());

			colecaoPagamnetos.add(pagamento);

		} 
		else {
			
			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - GUIA DE PAGAMENTO
	 *
	 * @author Raphael Rossiter
	 * @date 30/06/2009
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaArrecadacao
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamento_COSANPA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean numeroFaturaInvalida = false;
		Long identificacao = null;
		GuiaPagamento guiaPagamento = null;

		numeroFaturaInvalida = Util.validarValorLongoNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());

		if (numeroFaturaInvalida) {
			descricaoOcorrencia = "NÚMERO DA FATURA NÃO NUMÉRICO";
		}
		else {
			
			identificacao = Long.valueOf(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			guiaPagamento = this.pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(identificacao.toString());
					
			if (guiaPagamento == null) {
						
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
						
			}
		}


		if (descricaoOcorrencia.equals("OK")) {

			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {
				pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);
			} else {
				pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro.getAnoMesArrecadacao());
			}

			pagamento.setValorPagamento(valorPagamento);
			pagamento.setDataPagamento(dataPagamento);
			pagamento.setPagamentoSituacaoAtual(null);
			pagamento.setPagamentoSituacaoAnterior(null);
			pagamento.setDebitoTipo(guiaPagamento.getDebitoTipo());
			pagamento.setContaGeral(null);
			pagamento.setGuiaPagamento(guiaPagamento.getGuiaPagamentoGeral());
			pagamento.setLocalidade(guiaPagamento.getLocalidade());
			
			DocumentoTipo documentoTipo = new DocumentoTipo();
			if (guiaPagamento.getDebitoTipo().getId().equals(DebitoTipo.ENTRADA_PARCELAMENTO)){
				documentoTipo.setId(DocumentoTipo.ENTRADA_DE_PARCELAMENTO);	
			} else {
				documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			}
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);
			
			pagamento.setImovel(guiaPagamento.getImovel());			

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaArrecadacao);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());

			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			/*
			 * Alteracao referente ao Relatorio do Float - Francisco: 14/07/08
			 */
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			pagamento.setDataProcessamento(new Date());
			
			colecaoPagamnetos.add(pagamento);

		} 
		else {
			
			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamnetos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - EXTRATO DE DÉBITOS (IMÓVEL)
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaArrecadacao
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasDocumentoCobranca_COSANPA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		Collection colecaoDevolucoes = new ArrayList();

		boolean numeroDocumentoFaturaInvalido = false;
		Long identificacao = null;
		CobrancaDocumento cobrancaDocumento = null;

		numeroDocumentoFaturaInvalido = Util.validarValorLongoNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
		
		if (numeroDocumentoFaturaInvalido) {
			descricaoOcorrencia = "NÚMERO DO DOCUMENTO NÃO NUMÉRICO";
		}
		else {
			
			identificacao = Long.valueOf(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			cobrancaDocumento = this.pesquisarParmsCobrancaDocumentoPorNumeroDocumentoFatura(
			identificacao.toString());
			
					
			if (cobrancaDocumento == null) {
						
				descricaoOcorrencia = "DOCUMENTO DE COBRANÇA INEXISTENTE";
						
			}
		}
		
		
		if (descricaoOcorrencia.equals("OK")) {
			
			Integer idLocalidade = null;

			Collection cobrancaDocumentoItens = null;

			try {

				cobrancaDocumentoItens = repositorioArrecadacao
				.pesquisarCobrancaDocumentoItem(cobrancaDocumento.getId());
				
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			Integer idCobrancaDocumento = null;
			BigDecimal valorAcrescimo = new BigDecimal("0.00");
			BigDecimal valorDesconto = new BigDecimal("0.00");
			Date dataEmissao = null;
			BigDecimal valorTaxa = new BigDecimal("0.00");
			Integer idDocumentoTipo = null;
			
			idCobrancaDocumento = cobrancaDocumento.getId();
			idDocumentoTipo = cobrancaDocumento.getDocumentoTipo().getId();
			
			if (cobrancaDocumento.getValorAcrescimos() != null) {
				valorAcrescimo = cobrancaDocumento.getValorAcrescimos();
			}
			if (cobrancaDocumento.getValorDesconto() != null) {
				valorDesconto = cobrancaDocumento.getValorDesconto();
			}
			if (cobrancaDocumento.getEmissao() != null) {
				dataEmissao = cobrancaDocumento.getEmissao();
			}
			if (cobrancaDocumento.getValorTaxa() != null) {
				valorTaxa = cobrancaDocumento.getValorTaxa();
			}
				
			/*
             * Gerar os pagamentos associados com a localidade do documento de cobrança e NÃO com
             * a localidade do imóvel.
             */
			if (cobrancaDocumento.getLocalidade() != null) {

				idLocalidade = cobrancaDocumento.getLocalidade().getId();
			}
			else{
					
				idLocalidade = cobrancaDocumento.getImovel().getLocalidade().getId();
                    
			}
			
			// Caso o valor de acréscimo for maior que zero
			if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
				
				// [SB0008 - Alterar vencimento dos itens do documento de cobrança]
				alterarVencimentoItensDocumentoCobranca(idCobrancaDocumento, dataEmissao);

			}
			
			// Caso o valor de acrescimos seja maior que o valor de descontos
			if (valorAcrescimo.compareTo(valorDesconto) == 1) {
				valorAcrescimo = valorAcrescimo.subtract(valorDesconto);
				
				valorDesconto = new BigDecimal("0.00");
			} 
			else {
				
				valorDesconto = valorDesconto.subtract(valorAcrescimo);
				valorAcrescimo = new BigDecimal("0.00");
			}

			// Caso o valor de acréscimo for maior que zero
			if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
				
				// [SB0005 - Processar Recebimento de Acrescimos por Impontualidade]

				Pagamento pagamento = processarRecebimentoAcrescimosImpontualidade(
				idCobrancaDocumento, dataEmissao, valorAcrescimo, cobrancaDocumento.getImovel().getId(), 
				idLocalidade, sistemaParametro, idFormaArrecadacao, idDocumentoTipo);

				colecaoPagamentos.add(pagamento);

			}

			// Caso o valor de desconto for maior que zero
				if (valorDesconto.compareTo(new BigDecimal("0.00")) == 1) {
				
				// [SB0006 - Processar Desconto concedido no documento de cobrança]
				Devolucao devolucao = processarDescontoConcedidoDocumentoCobranca(
				idCobrancaDocumento, dataEmissao, valorDesconto, cobrancaDocumento.getImovel().getId(), 
				idLocalidade, sistemaParametro, idFormaArrecadacao, idDocumentoTipo);

				colecaoDevolucoes.add(devolucao);

			}

			// Caso o valor da taxa referente ao documento de cobrança for maior que zero
			if (valorTaxa.compareTo(new BigDecimal("0.00")) == 1) {
				
				// [SB0006 - Processar Desconto concedido no documento de cobrança]
				Pagamento pagamento = processarTaxaDocumentoCobranca(
				idCobrancaDocumento, dataEmissao, valorTaxa,
				cobrancaDocumento.getImovel().getId(), idLocalidade, sistemaParametro,
				idFormaArrecadacao, idDocumentoTipo);

				colecaoPagamentos.add(pagamento);

			}

			// verifica se a coleção é diferente de nula
			if (cobrancaDocumentoItens != null && !cobrancaDocumentoItens.isEmpty()) {

				Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens
				.iterator();

				while (cobrancaDocumentoItensIterator.hasNext()) {

					Object[] cobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator.next();

					/*
					 * Colocado por Raphael Rossiter em 31/10/2007 OBJ:
					 * Apenas gerar os pagamentos referentes aos itens que
					 * NAO tenham CreditoARealizar
					 */
					BigDecimal valorItemCobrado = null;

					if (cobrancaDocumentoItem[13] == null) {

						Integer idContaPesquisa = null;
						Integer idContaGeralPesquisa = null;
						Integer idGuiaPagamento = null;
						Integer idDebitoACobrar = null;
						int contaReferencia = 0;
						Integer idDebitoTipo = null;
						Integer idGuiaPagamentoGeralPesquisa = null;
						Integer idDebitoACobrarGeralPesquisa = null;
						Short numeroPrestacaoCobradas = null;
						Short numeroPrestacaoDebito = null;
							
						// verifica o id da conta
						if (cobrancaDocumentoItem[0] != null) {
							idContaPesquisa = (Integer) cobrancaDocumentoItem[0];
							idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[0];
							// referencia conta
							if (cobrancaDocumentoItem[4] != null) {
								contaReferencia = (Integer) cobrancaDocumentoItem[4];
							}
						} 
						else {
							
							// Caso não exista na conta então pesquisa na conta histórico
							if (cobrancaDocumentoItem[10] != null) {
								idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[10];
							}

							// referencia conta histórico
							if (cobrancaDocumentoItem[5] != null) {
								contaReferencia = (Integer) cobrancaDocumentoItem[5];
							}
						}

						// Verifica o id da guia pagamento
						if (cobrancaDocumentoItem[1] != null) {
							idGuiaPagamento = (Integer) cobrancaDocumentoItem[1];
							idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[1];
						} 
						else {
							
							// Caso não exista no guia pagamento então pesquisa no guia pagamento histórico
							if (cobrancaDocumentoItem[11] != null) {
								idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[11];
							}
						}
							
						// verifica o id do debito a cobrar
						if (cobrancaDocumentoItem[2] != null) {
							idDebitoACobrar = (Integer) cobrancaDocumentoItem[2];
							idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[2];
							numeroPrestacaoDebito = (Short) cobrancaDocumentoItem[16];
							numeroPrestacaoCobradas = (Short) cobrancaDocumentoItem[17];

							// [SB0012]- Verifica Pagamento de Débito a Cobrar de Parcelamento
							verificaPagamentoDebitoACobrarParcelamento(idDebitoACobrar, null);

						} 
						else {
							
							// Caso não exista no debito a cobrar então pesquisa no debito a cobrar histórico
							if (cobrancaDocumentoItem[12] != null) {
								idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[12];
							}
						}
						
						// Verifica o valor do item cobrado da cobranca documento item
						if (cobrancaDocumentoItem[3] != null) {
							valorItemCobrado = (BigDecimal) cobrancaDocumentoItem[3];
						}

						if (idContaGeralPesquisa == null) {
						
							// caso exista guia de pagamento
							if (idGuiaPagamentoGeralPesquisa != null) {
								
								// Verifica o id do debito tipo se é da guia
								if (cobrancaDocumentoItem[6] != null) {
									idDebitoTipo = (Integer) cobrancaDocumentoItem[6];
								} 
								else {
									
									/*
									 * Caso não exista no guia pagamento então pesquisa no guia pagamento
									 * histórico
									 */ 
									if (cobrancaDocumentoItem[7] != null) {
										idDebitoTipo = (Integer) cobrancaDocumentoItem[7];
									}
								}
							}
							
							// Caso exista debito a cobrar
							if (idDebitoACobrarGeralPesquisa != null) {
								
								// Verifica o id do debito tipo no debito a cobrar
								if (cobrancaDocumentoItem[8] != null) {
									idDebitoTipo = (Integer) cobrancaDocumentoItem[8];
								} 
								else {
									
									/*
									 * caso não exista no debito a cobrar então pesquisa no debito a cobrar
									 * histórico
									 */ 
									if (cobrancaDocumentoItem[9] != null) {
										idDebitoTipo = (Integer) cobrancaDocumentoItem[9];
									}
								}
							}
						}

						// GERANDO O PAGAMENTO
						Pagamento pagamento = new Pagamento();
						
						if (contaReferencia != 0) {
							
							pagamento.setAnoMesReferenciaPagamento(contaReferencia);
						} 
						else {
							pagamento.setAnoMesReferenciaPagamento(null);
						}

						/*
						 * caso o ano mes da data de dedito seja maior que o ano mes de arrecadação da
						 * tabela sistema parametro então seta o ano mes da data de debito.
						 */ 
						if (anoMesPagamento > sistemaParametro.getAnoMesArrecadacao()) {
								
							pagamento.setAnoMesReferenciaArrecadacao(anoMesPagamento);
						} 
						else {
							
							// caso contrario seta o o ano mes arrecadação da tabela sistema parametro
							pagamento.setAnoMesReferenciaArrecadacao(sistemaParametro
							.getAnoMesArrecadacao());
						}
						
						pagamento.setValorPagamento(valorItemCobrado);
						pagamento.setDataPagamento(dataPagamento);
						pagamento.setPagamentoSituacaoAtual(null);
						pagamento.setPagamentoSituacaoAnterior(null);
						
						if (idDebitoTipo != null) {
							
							DebitoTipo debitoTipo = new DebitoTipo();
							debitoTipo.setId(idDebitoTipo);
							pagamento.setDebitoTipo(debitoTipo);
						} 
						else {
							
							pagamento.setDebitoTipo(null);
						}

						// CONTA
						if (idContaGeralPesquisa != null) {
								
							if (idContaPesquisa != null) {
									
								ContaGeral conta = new ContaGeral();
								conta.setId(idContaPesquisa);
									
								pagamento.setContaGeral(conta);
									
								/*
								 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
								 * OBJ: Inserir o pagamento com a localidade da própria conta e não
								 * com a localidade do documento de cobrança
								 */
								try {
									idLocalidade = repositorioLocalidade
					                .pesquisarIdLocalidadePorConta(idContaPesquisa);

					            } catch (ErroRepositorioException e) {
					            	throw new ControladorException("erro.sistema", e);
					            }
							} 
							else {
									
								pagamento.setContaGeral(null);
									
							}

							DocumentoTipo documentoTipo = new DocumentoTipo();
							documentoTipo.setId(DocumentoTipo.CONTA);
							pagamento.setDocumentoTipo(documentoTipo);
						} 
						else {
								
							pagamento.setContaGeral(null);
								
						}
							
						// GUIA DE PAGAMENTO
						if (idGuiaPagamentoGeralPesquisa != null) {
								
							if (idGuiaPagamento != null) {
									
								pagamento.setGuiaPagamento(new GuiaPagamentoGeral(idGuiaPagamento));
									
								try {
									idLocalidade = repositorioLocalidade.pesquisarIdLocalidadePorGuiaPagamento(idGuiaPagamento);
					            } catch (ErroRepositorioException e) {
					                    throw new ControladorException("erro.sistema", e);
					            }
							} 
							else {
									
								pagamento.setGuiaPagamento(null);
									
							}
								
							DocumentoTipo documentoTipo = new DocumentoTipo();
								
							/*
							 * verificar se o tipo de debito eh 'entrada de parcelamento', e preencher o documentotipo
							 * com o 'entrada de parcelamento'
							 */
							if (idDebitoTipo != null && idDebitoTipo.equals(DebitoTipo.ENTRADA_PARCELAMENTO)){
								documentoTipo.setId(DocumentoTipo.ENTRADA_DE_PARCELAMENTO);									
							} 
							else {
								documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);	
							}
							
							documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA);
							pagamento.setDocumentoTipo(documentoTipo);

						} 
						else {
								
							pagamento.setGuiaPagamento(null);
								
						}

						// DÉBITO A COBRAR
						if (idDebitoACobrarGeralPesquisa != null) {
								
							if (idDebitoACobrar != null) {
									
								try {
										
									if (numeroPrestacaoCobradas.intValue() != numeroPrestacaoDebito.intValue()) {
							
										DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
										debitoACobrarGeral.setId(idDebitoACobrar);
										
										pagamento.setDebitoACobrarGeral(debitoACobrarGeral);
											
										/*
										 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
										 * OBJ: Inserir o pagamento com a localidade do próprio debito a cobrar e não
										 * com a localidade do documento de cobrança
										 */
										idLocalidade = repositorioLocalidade
							            .pesquisarIdLocalidadePorDebitoACobrar(idDebitoACobrar);
									}
									
									// atualiza a situação atual para cancelada
									repositorioFaturamento
									.atualizarSituacaoAtualDebitoACobrar(idDebitoACobrar);
										
								} catch (ErroRepositorioException e) {
									throw new ControladorException("erro.sistema", e);
								}

							} 
							else {
									
								pagamento.setDebitoACobrarGeral(null);
									
							}
								
							DocumentoTipo documentoTipo = new DocumentoTipo();
							documentoTipo.setId(DocumentoTipo.DEBITO_A_COBRAR);
							pagamento.setDocumentoTipo(documentoTipo);

						} 
						else {
								
							pagamento.setDebitoACobrarGeral(null);
								
						}

						// Verifica se o id da conta é diferente de nulo
						if (idLocalidade != null) {
							Localidade localidade = new Localidade();
							localidade.setId(idLocalidade);
							pagamento.setLocalidade(localidade);
						} 
						else {
						
							pagamento.setLocalidade(null);

						}

						// AVISO BANCÁRIO
						pagamento.setAvisoBancario(null);

						// IMÓVEL
						pagamento.setImovel(cobrancaDocumento.getImovel());
						
						pagamento.setArrecadadorMovimentoItem(null);

						ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
						arrecadacaoForma.setId(idFormaArrecadacao);
						pagamento.setArrecadacaoForma(arrecadacaoForma);
						pagamento.setCliente(null);
						pagamento.setUltimaAlteracao(new Date());
							
						pagamento.setFatura(null);
							
						//DOCUMENTO DE COBRANÇA
						pagamento.setCobrancaDocumento(cobrancaDocumento);							
							
						// DOCUMENTO TIPO
						if(idDocumentoTipo != null){
							DocumentoTipo documentoAgregador = new DocumentoTipo();
							documentoAgregador.setId(idDocumentoTipo);
							pagamento.setDocumentoTipoAgregador(documentoAgregador);
						}
							
						pagamento.setDataProcessamento(new Date());
							
						if (pagamento.getDocumentoTipo() != null) {
							colecaoPagamentos.add(pagamento);
						}
					} 
					else {

						// Para os itens que tenham CreditoARealizar gerar suas respectivas devoluções

						Devolucao devolucao = new Devolucao();

						// DataDevolucao = DataPagamento
						devolucao.setDataDevolucao(dataPagamento);

						/*
						 * AnoMesReferenciaDevolucao Caso o anoMes da data
						 * de devolução seja MAIOR que a
						 * PARM_AMREFERENCIAARRECADACAO da tabela
						 * SISTEMA_PARAMETROS atribuir o anoMes da data da
						 * devolução, caso contrário atribuir o
						 * PARM_AMREFERENCIAARRECADACAO.
						 */
						Integer anoMesDataDevolucao = Util.getAnoMesComoInteger(devolucao
						.getDataDevolucao());

						if (anoMesDataDevolucao > sistemaParametro.getAnoMesArrecadacao()) {
							
							devolucao.setAnoMesReferenciaArrecadacao(anoMesDataDevolucao);
						} 
						else {
								
							devolucao.setAnoMesReferenciaArrecadacao(sistemaParametro
							.getAnoMesArrecadacao());
						}

						// ValorDevolucao = ValorItemCobrado
						if (cobrancaDocumentoItem[3] != null) {
							valorItemCobrado = (BigDecimal) cobrancaDocumentoItem[3];
							devolucao.setValorDevolucao(valorItemCobrado);
						}

						// Localidade = Localidade da tabela
						// COBRANCA_DOCUMENTO
						if (cobrancaDocumentoItem[14] != null) {
							Localidade localidade = new Localidade();
							localidade.setId((Integer) cobrancaDocumentoItem[14]);
							
							devolucao.setLocalidade(localidade);
						}

						// Imovel = Imovel da tabela COBRANCA_DOCUMENTO
						if (cobrancaDocumentoItem[15] != null) {
							Imovel imovel = new Imovel();
							imovel.setId((Integer) cobrancaDocumentoItem[15]);
							
							devolucao.setImovel(imovel);
						}

						// DebitoTipo = DebitoTipo com o valor
						// correspondente a outros
						DebitoTipo debitoTipo = new DebitoTipo();
						debitoTipo.setId(DebitoTipo.OUTROS);
						devolucao.setDebitoTipo(debitoTipo);

						// CreditoARealizarGeral = CreditoARealizarGeral da
						// tabela COBRANCA_DOCUMENTO_ITEM
						CreditoARealizarGeral creditoARealizarGeral = new CreditoARealizarGeral();
						creditoARealizarGeral.setId((Integer) cobrancaDocumentoItem[13]);
						
						devolucao.setCreditoARealizarGeral(creditoARealizarGeral);

						// Ultima Alteração
						devolucao.setUltimaAlteracao(new Date());

						//DOCUMENTO DE COBRANÇA
						devolucao.setCobrancaDocumento(cobrancaDocumento);							
							
						// documento tipo do documento de cobranca
						if(idDocumentoTipo != null){
							DocumentoTipo documentoAgregador = new DocumentoTipo();
							documentoAgregador.setId(idDocumentoTipo);
							devolucao.setDocumentoTipoAgregador(documentoAgregador);
						}
							
						// ADICIONANDO A DEVOLUCAO GERADA NA COLECAO DE RETORNO
						colecaoDevolucoes.add(devolucao);
					}
				}
			}
		} 
		else {
			
			// atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setColecaoDevolucao(colecaoDevolucoes);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;

	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - FATURA (IMÓVEL)
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 * 
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaArrecadacao
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasFatura_COSANPA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		boolean numeroFaturaInvalido = false;
		Long identificacao = null;
		Fatura fatura = null;

		numeroFaturaInvalido = Util.validarValorLongoNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
		
		if (numeroFaturaInvalido) {
			descricaoOcorrencia = "NÚMERO DA FATURA NÃO NUMÉRICO";
		}
		else {
			
			identificacao = Long.valueOf(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			fatura = this.pesquisarFaturaPorNumeroFaturaObjetoCompleto(identificacao.toString());
					
			if (fatura == null) {
						
				descricaoOcorrencia = "FATURA INEXISTENTE";
						
			}
		}
		
		if (descricaoOcorrencia.equals("OK")) {

			// COLEÇÃO PARA OS ÍTENS DA FATURA
			Collection faturaItens = null;

			
			try {
				
				faturaItens = repositorioFaturamento.pesquisarFaturaItem(fatura.getId());
				
			} 
			catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (faturaItens != null && !faturaItens.isEmpty()) {
				
				Iterator faturaItensIterator = faturaItens.iterator();
				
				while (faturaItensIterator.hasNext()) {
					
					Object[] faturaItem = (Object[]) faturaItensIterator.next();
					
					// inicializa as variaveis que veio da pesquisa
					Integer idContaPesquisa = null;
					Integer idImovelPesquisa = null;
					Integer idLocalidadePesquisa = null;
					BigDecimal valorConta = null;
					
					
					// verifica o valor da conta
					if (faturaItem[0] != null) {
						valorConta = (BigDecimal) faturaItem[0];
					}
					// verifica o id da conta
					if (faturaItem[1] != null) {
						idContaPesquisa = (Integer) faturaItem[1];
					}
					// verifica o id da localidade
					if (faturaItem[2] != null) {
						idLocalidadePesquisa = (Integer) faturaItem[2];
					}
					// verifica o id do imovel
					if (faturaItem[3] != null) {
						idImovelPesquisa = (Integer) faturaItem[3];
					}
					// verifica o id da localidade de Conta
					// Histórico
					if (faturaItem[4] != null) {
						idLocalidadePesquisa = (Integer) faturaItem[4];
					}
					
					
					// verifica o id do imovel de Conta Histórico
					if (faturaItem[5] != null) {
						idImovelPesquisa = (Integer) faturaItem[5];
					}

					// cria o objeto pagamento para setar os
					// dados
					Pagamento pagamento = new Pagamento();
					pagamento.setAnoMesReferenciaPagamento(fatura.getAnoMesReferencia());
					
					// caso o ano mes da data de dedito seja
					// maior que o ano mes de arrecadação da
					// tabela sistema parametro então seta o ano
					// mes da data de debito
					if (anoMesPagamento > sistemaParametro
							.getAnoMesArrecadacao()) {
						pagamento
								.setAnoMesReferenciaArrecadacao(anoMesPagamento);
					} else {
						// caso contrario seta o o ano mes
						// arrecadação da tabela sistema
						// parametro
						pagamento
								.setAnoMesReferenciaArrecadacao(sistemaParametro
										.getAnoMesArrecadacao());
					}
					pagamento.setValorPagamento(valorConta);
					pagamento.setDataPagamento(dataPagamento);
					pagamento.setPagamentoSituacaoAtual(null);
					pagamento.setPagamentoSituacaoAnterior(null);
					pagamento.setDebitoTipo(null);
					// verifica se o id da conta é diferente de
					// nulo
					if (idContaPesquisa != null) {
						ContaGeral conta = new ContaGeral();
						conta.setId(idContaPesquisa);
						pagamento.setContaGeral(conta);
					} else {
						pagamento.setContaGeral(null);
					}
					pagamento.setGuiaPagamento(null);

					pagamento.setDebitoACobrarGeral(null);

					// verifica se o id da conta é diferente de
					// nulo
					if (idLocalidadePesquisa != null) {
						Localidade localidade = new Localidade();
						localidade.setId(idLocalidadePesquisa);
						pagamento.setLocalidade(localidade);
					} else {
						pagamento.setLocalidade(null);
					}
					DocumentoTipo documentoTipo = new DocumentoTipo();
					documentoTipo.setId(DocumentoTipo.CONTA);
					documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL);
					pagamento.setDocumentoTipo(documentoTipo);

					// seta o id do aviso bancario
					pagamento.setAvisoBancario(null);

					// seta o imovel
					if (idImovelPesquisa != null) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelPesquisa);
						pagamento.setImovel(imovel);
					} else {
						pagamento.setImovel(null);
					}

					pagamento.setArrecadadorMovimentoItem(null);

					ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
					arrecadacaoForma.setId(idFormaArrecadacao);
					pagamento.setArrecadacaoForma(arrecadacaoForma);
					pagamento.setCliente(null);
					pagamento.setUltimaAlteracao(new Date());

					pagamento.setFatura(fatura);
					
					pagamento.setCobrancaDocumento(null);
					
					DocumentoTipo documentoAgregador = new DocumentoTipo();
					documentoAgregador.setId(DocumentoTipo.FATURA_CLIENTE);
					pagamento.setDocumentoTipoAgregador(documentoAgregador);
					
					pagamento.setDataProcessamento(new Date());
					
					colecaoPagamentos.add(pagamento);
				}
			}
		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras
				.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2009
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		
		super.distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(registroHelperCodigoG,
		arrecadadorMovimentoItemHelper);
		
		//LEGADO - COSANPA
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_LEGADO_COSANPA.toString())){
			
			Long identificacao = Long.valueOf(registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			/*
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 1 (Conta_Legado).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 2 (Extrato).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 3 (Guia_Pagamento).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 4 (Documento_Cobranca).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 5 (Fatura).
			 * 
			 */
			if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSANPA.toString())){
				
				//CONTA
				Conta conta = this.pesquisarExistenciaContaPorNumeroFatura(identificacao.toString());
				
				if (conta != null){
					
					arrecadadorMovimentoItemHelper
					.setIdentificacao(conta.getImovel().getId().toString());
				}
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA_LEGADO);
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSANPA.toString())){
				
				//EXTRATO
				CobrancaDocumento cobrancaDocumento = this.pesquisarParmsCobrancaDocumentoPorNumeroDocumentoFatura(
				identificacao.toString());
				
				if (cobrancaDocumento != null){
					
					arrecadadorMovimentoItemHelper
					.setIdentificacao(cobrancaDocumento.getImovel().getId().toString());
				}
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_EXTRATO_LEGADO);
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSANPA.toString())){
				
				//GUIA DE PAGAMENTO
				GuiaPagamento guiaPagamento = this.pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(
				identificacao.toString());
				
				if (guiaPagamento != null){
					
					arrecadadorMovimentoItemHelper
					.setIdentificacao(guiaPagamento.getImovel().getId().toString());
				}
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO);
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_DOCUMENTO_COBRANCA_LEGADO_COSANPA.toString())){
				
				//DOCUMENTO DE COBRANÇA
				CobrancaDocumento cobrancaDocumento = this.pesquisarParmsCobrancaDocumentoPorNumeroDocumentoFatura(
				identificacao.toString());
				
				if (cobrancaDocumento != null && cobrancaDocumento.getImovel() != null){
					
					arrecadadorMovimentoItemHelper
					.setIdentificacao(cobrancaDocumento.getImovel().getId().toString());
				}
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_LEGADO);
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_FATURA_LEGADO_COSANPA.toString())){
				
				//FATURA
				Fatura fatura = this.pesquisarFaturaPorNumeroFaturaObjetoCompleto(identificacao.toString());
				
				if (fatura != null){
					
					arrecadadorMovimentoItemHelper
					.setIdentificacao(fatura.getCliente().getId().toString());
				}
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_LEGADO);
			}
		}
	}
	
	/**
     * [UC0242] - Registrar Movimento dos Arrecadadores
     *
     * @author Raphael Rossiter
     * @date 18/09/2009
     *
     * @param idClienteEmpresa
     * @return Integer
     * @throws ControladorException
     */
    public Integer recuperarMatriculaImovel(String idClienteEmpresa) throws ControladorException{
    	
    	Integer matriculaImovelInteger = null;
    	String matriculaImovel = null;
    	
    	if (idClienteEmpresa != null) {
			
    		int max = 11;
    		
    		if (idClienteEmpresa.length() < 11){
    			max = idClienteEmpresa.length();
    		}
    		
    		matriculaImovel = idClienteEmpresa.substring(0, max);
			
			try {
				
				matriculaImovelInteger = new Integer(matriculaImovel);
				
			} catch (Exception e) {
				matriculaImovelInteger = 0;
			}
		}
        
        return matriculaImovelInteger;
    }
    
    
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conteúdo do do código de barras
	 * 
	 * [SF0003] Apresentar Dados do Conteúdo do Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @data 22/03/2006
	 * 
	 * @param registroHelperCodigoG
	 * @return DadosConteudoCodigoBarrasHelper
	 */
	public DadosConteudoCodigoBarrasHelper apresentarDadosConteudoCodigoBarras(
			RegistroHelperCodigoG registroHelperCodigoG)
			throws ControladorException {

		//GERADO PELO GSAN
		DadosConteudoCodigoBarrasHelper retorno = 
		super.apresentarDadosConteudoCodigoBarras(registroHelperCodigoG);

		
		//LEGADO
		String tipoPagamento = registroHelperCodigoG
		.getRegistroHelperCodigoBarras().getTipoPagamento();


		if (tipoPagamento != null && 
			tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_LEGADO_COSANPA.toString())) {
			
			//LOCALIDADE
			retorno.setCodigoLocalidade(new Integer(registroHelperCodigoG
			.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
			.getIdPagamento3()).toString());
			
			Long identificacao = Long.valueOf(registroHelperCodigoG
			.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
			.getIdPagamento2());
			
			//IDENTIFICAÇÃO DO DOCUMENTO
			retorno.setIdentificacaoDocumento(identificacao.toString());

			/*
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 1 (Conta_Legado).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 2 (Extrato).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 3 (Guia_Pagamento).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 4 (Documento_Cobranca).
			 * 
			 * Caso o tipo de documento (G.05.7.1) tenha valor igual a 5 (Fatura).
			 * 
			 */
			if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSANPA.toString())){
						
				//CONTA
				retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA_LEGADO);
				
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSANPA.toString())){
						
				//EXTRATO
				retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_EXTRATO_LEGADO);
				
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSANPA.toString())){
						
				//GUIA DE PAGAMENTO
				retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO);
				
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_DOCUMENTO_COBRANCA_LEGADO_COSANPA.toString())){
						
				//DOCUMENTO DE COBRANÇA
				retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_LEGADO);
				
			}
			else if (registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1().equals(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_FATURA_LEGADO_COSANPA.toString())){
						
				//FATURA
				retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL_LEGADO);
				
			}
		}

		return retorno;

	}
	
	
	/**
	 * [UC0242] - Registrar Movimento dos Arrecadadores 
	 * 
	 * [SF0010] - Gerar o arquivo de envio para o arrecadador com registros do Movimento 
	 * 
	 * Autor: Sávio Luiz Data: 15/02/2006
	 */
	public void gerarArquivoRegistrosTipoC(
			RegistroHelperCodigoA registroHelperCodigoA,
			RegistroHelperCodigoZ registroHelperCodigoZ,
			Collection colecaoRegistrosC,
			Integer numeroSequencialArquivoEnvioDebitoAutomatico,
			String descricaoOcorrencia, Integer indicadorAceitacaoRegistro,
			String idTipoMovimento) throws ControladorException {
		StringBuilder registrosC = new StringBuilder();
		// cria o header do código C para inserir o movimento arrecadador
		RegistroHelperCodigoA registroHelperHeaderC = new RegistroHelperCodigoA();
		// cria o trailler do código C para inserir o móvimento arrecadador
		RegistroHelperCodigoZ registroHelperTreillerC = new RegistroHelperCodigoZ();

		// inicializa com 1, pois já está contando com a linha do registro A
		int quantidadeRegistros = 1;

		registrosC.append("A");
		registroHelperHeaderC.setCodigoRegistro("A");
		registrosC.append("1");
		registroHelperHeaderC.setCodigoRemessa("1");
		// seta o código do convênio
		registrosC.append(Util.completaString(registroHelperCodigoA
				.getCodigoConvenio(), 20));
		registroHelperHeaderC.setCodigoConvenio(registroHelperCodigoA
				.getCodigoConvenio());
		// seta o nome da empresa
		registrosC.append(Util.completaString(registroHelperCodigoA
				.getNomeEmpresa(), 20));
		registroHelperHeaderC.setNomeEmpresa(registroHelperCodigoA
				.getNomeEmpresa());
		// seta o código do banco
		registrosC.append(Util.adicionarZerosEsquedaNumero(3,
				registroHelperCodigoA.getCodigoBanco()));
		registroHelperHeaderC.setCodigoBanco(registroHelperCodigoA
				.getCodigoBanco());
		// seta o nome do banco
		registrosC.append(Util.completaString(registroHelperCodigoA
				.getNomeBanco(), 20));
		registroHelperHeaderC
				.setNomeBanco(registroHelperCodigoA.getNomeBanco());
		// seta a data corrente
		String dataAtualString = Util.recuperaDataInvertida(new Date());
		registrosC.append(Util.completaString(dataAtualString, 8));
		registroHelperHeaderC.setDataGeracaoArquivo(dataAtualString);

		// seta numero sequencial da tabela Arrecadador_Contrato o campo
		// ARCT_NNNSAENVIODEBAUT + 1
		numeroSequencialArquivoEnvioDebitoAutomatico += 1;
		registrosC.append(Util.adicionarZerosEsquedaNumero(6, ""
				+ numeroSequencialArquivoEnvioDebitoAutomatico));
		registroHelperHeaderC.setNumeroSequencialArquivo(""
				+ numeroSequencialArquivoEnvioDebitoAutomatico);
		// seta a versão de layout
		registrosC.append(Util.adicionarZerosEsquedaNumero(2, ""
				+ registroHelperCodigoA.getVersaoLayout()));
		registroHelperHeaderC.setVersaoLayout(registroHelperCodigoA
				.getVersaoLayout());
		// tipo do movimento
		registrosC.append(Util.completaString(registroHelperCodigoA
				.getTipoMovimento(), 17));
		registroHelperHeaderC.setTipoMovimento(registroHelperCodigoA
				.getTipoMovimento());

		// reservado para o futuro
		registrosC.append(Util.completaString("", 51));
		// reservado para o futuro
		registrosC.append(Util.completaString("*", 1));
		registroHelperHeaderC.setReservadoFuturo(registroHelperCodigoA
				.getReservadoFuturo());

		// parte do treiller criada para inserir no movimento arrecadador
		registroHelperTreillerC.setCodigoRegistro("Z");
		// o total de registros é a quantidade da coleção mais a header e o
		// treiller (1+1)
		registroHelperTreillerC.setTotalRegistrosArquivo(""
				+ (colecaoRegistrosC.size() + 2));

		registroHelperTreillerC.setValorTotalRegistrosArquivo("000");

		ArrecadadorMovimento arrecadadorMovimento = inserirMovimentoArrecadador(
				registroHelperHeaderC, registroHelperTreillerC, idTipoMovimento);

		// pula uma linha
		registrosC.append(System.getProperty("line.separator"));

		Iterator colecaoRegistrosCIterator = colecaoRegistrosC.iterator();
		while (colecaoRegistrosCIterator.hasNext()) {
			// cria uma string builder para pegar linha a linha da coleção
			// inserir o movimento arrecadador e depois inserir na string
			// builder do registrosC
			// para ser mandado para o banco.
			StringBuilder linhaRegistroC = new StringBuilder();
			// incrementa a quantidade de registros para cada registro da
			// coleção
			quantidadeRegistros = quantidadeRegistros + 1;
			// começa a criar a string com o registro do tipo c
			RegistroHelperCodigoC registroHelperCodigoC = (RegistroHelperCodigoC) colecaoRegistrosCIterator
					.next();
			linhaRegistroC.append("C");
			// Identificação do cliente na empresa
			linhaRegistroC.append(Util.completaString(registroHelperCodigoC
					.getIdClienteEmpresa(), 25));
			// agencia para debito
			linhaRegistroC.append(Util.completaString(registroHelperCodigoC
					.getAgenciaDebito(), 4));
			// Identificacao cliente banco
			linhaRegistroC.append(Util.completaString(registroHelperCodigoC
					.getIdClienteBanco(), 14));
			// descricao ocorrencia movimento
			linhaRegistroC.append(Util.completaString(registroHelperCodigoC
					.getDescricaoOcorrenciaMovimento(), 40));
			// brancos
			linhaRegistroC.append(Util.completaString("", 40));
			// reservado para o futuro
			linhaRegistroC.append(Util.completaString("", 25));
			// agencia para debito
			linhaRegistroC.append(Util.adicionarZerosEsquedaNumero(1,
					registroHelperCodigoC.getCodigoMovimento()));

			// inseri o item do movimento arrecadador
			inserirItemMovimentoArrecadador(linhaRegistroC.toString(),
					arrecadadorMovimento.getId(), registroHelperCodigoC
							.getDescricaoOcorrenciaMovimento(),
					indicadorAceitacaoRegistro, null);
			registrosC.append(linhaRegistroC);
			registrosC.append(System.getProperty("line.separator"));
		}

		// incrementa a quantidade de registros para registro Z
		quantidadeRegistros = quantidadeRegistros + 1;

		registrosC.append("Z");
		// total de registros do arquivo
		registrosC.append(Util.adicionarZerosEsquedaNumero(6, ""
				+ quantidadeRegistros));
		// valor zero
		registrosC.append(Util.adicionarZerosEsquedaNumero(17, ""));
		// reservado para o futuro
		registrosC.append(Util.completaString("", 125));
		// reservado para o futuro
		registrosC.append(Util.completaString("*", 1));
		registrosC.append(System.getProperty("line.separator"));
		
		//NÃO UTILIZADO PELA COSANPA
		//registrosC.append("\u0004");

		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(
				EnvioEmail.REGISTRAR_MOVIMENTO_ARRECADADORES);

		String emailRemetente = envioEmail.getEmailRemetente();
		String tituloMensagem = envioEmail.getTituloMensagem();
		String corpoMensagem = envioEmail.getCorpoMensagem();
		String emailReceptor = envioEmail.getEmailReceptor();

		mandaArquivoLeituraEmail(registrosC, emailReceptor, emailRemetente,
				tituloMensagem, corpoMensagem,numeroSequencialArquivoEnvioDebitoAutomatico);

	}
	
	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * Gera o arquivo TXT para envio.
	 * 
	 * [SB0004] - Gerar Arquivo TXT para Envio do Banco
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2006
	 * 
	 * @param arrecadadorMovimento,registrosTipoE
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	protected StringBuilder gerarArquivoTxt(
			ArrecadadorMovimento arrecadadorMovimento,
			StringBuilder registrosTipoE) {
		
		StringBuilder arquivoTXTEnvio = new StringBuilder();

		// gera o header do arquivo(registroCódigoA)
		arquivoTXTEnvio.append("A");
		arquivoTXTEnvio.append(Util.completaString(""+ arrecadadorMovimento.getCodigoRemessa(), 1));
		arquivoTXTEnvio.append(Util.completaString(arrecadadorMovimento.getCodigoConvenio(), 20));
		arquivoTXTEnvio.append(Util.completaString(arrecadadorMovimento.getNomeEmpresa(), 20));
		
		//codigo agente
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		filtroArrecadador.adicionarParametro(
			new ParametroSimples(
				FiltroArrecadador.ID, 
				arrecadadorMovimento.getCodigoBanco()));
		
		Collection colecaoArrecadador = 
			Fachada.getInstancia().pesquisar(filtroArrecadador, Arrecadador.class.getName());
		
		String codigoAgente = "";
		if(!colecaoArrecadador.isEmpty()){
			Arrecadador arrecadador = (Arrecadador)Util.retonarObjetoDeColecao(colecaoArrecadador);
			codigoAgente = arrecadador.getCodigoAgente().toString();
		}
		
		arquivoTXTEnvio.append(Util.adicionarZerosEsquedaNumero(3, ""+ codigoAgente));
		arquivoTXTEnvio.append(Util.completaString(arrecadadorMovimento.getNomeBanco(), 20));
		String dataGeracaoArquivo = Util.recuperaAnoMesDiaDaData(arrecadadorMovimento.getDataGeracao());		
		arquivoTXTEnvio.append(dataGeracaoArquivo);
		arquivoTXTEnvio.append(Util.adicionarZerosEsquedaNumero(6, ""+ arrecadadorMovimento.getNumeroSequencialArquivo()));
		arquivoTXTEnvio.append(Util.adicionarZerosEsquedaNumero(2, ""+ arrecadadorMovimento.getNumeroVersaoLayout()));
		arquivoTXTEnvio.append(Util.completaString(arrecadadorMovimento.getDescricaoIdentificacaoServico(), 17));
		arquivoTXTEnvio.append(Util.completaString("", 51));
		arquivoTXTEnvio.append("*");

		arquivoTXTEnvio.append(System.getProperty("line.separator"));
		
		// recupera todos os registros do tipo E
		arquivoTXTEnvio.append(registrosTipoE);
		
		// gera o trailler(registro código "Z") do arquivo de envio
		arquivoTXTEnvio.append("Z");
		arquivoTXTEnvio.append(Util.adicionarZerosEsquedaNumero(6, ""+ arrecadadorMovimento.getNumeroRegistrosMovimento()));
		
		String valorSemVirgula = ("" + arrecadadorMovimento.getValorTotalMovimento()).replace(".", "");
		arquivoTXTEnvio.append(Util.adicionarZerosEsquedaNumero(17,valorSemVirgula));
		arquivoTXTEnvio.append(Util.completaString("", 125));
		arquivoTXTEnvio.append("*");
		arquivoTXTEnvio.append(System.getProperty("line.separator"));
		
		// fim de arquivo - NÃO UTILIZADO PELA COSANPA
		//arquivoTXTEnvio.append("\u0004");

		return arquivoTXTEnvio;

	}
	
	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * Cria uma linha de 150 posições com o registro tipo E.
	 * 
	 * 
	 * [SB0001] - Gerar Movimento para Debito Automático
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2006
	 * 
	 * @param banco,debitoAutomaticoMovimento
	 * @return StringBuilder
	 * @throws ControladorException
	 */
	protected StringBuilder criarRegistroTipoE(Banco banco,
			DebitoAutomaticoMovimento debitoAutomaticoMovimento, Short tamanhoMaximoIdentificacaoImovel)
			throws ControladorException {
		StringBuilder registroTipoE = new StringBuilder();
		
		Conta conta = debitoAutomaticoMovimento.getContaGeral().getConta();
		
		registroTipoE.append("E");
		
		// identificação do cliente na empresa
		String identificacaoCliente = Util.adicionarZerosEsquedaNumeroTruncando(tamanhoMaximoIdentificacaoImovel.intValue(), ""
				+ conta.getImovel().getId());
		registroTipoE.append(Util.completaString(identificacaoCliente, 25));

		String codigoAgencia = debitoAutomaticoMovimento.getDebitoAutomatico()
				.getAgencia().getCodigoAgencia();
		// agencia para débito
		registroTipoE
				.append(Util.adicionarZerosEsquedaNumero(4, codigoAgencia));

		// Identificação do cliente no Banco
		registroTipoE.append(Util.completaString(debitoAutomaticoMovimento
				.getDebitoAutomatico().getIdentificacaoClienteBanco(), 14));
		// data de vencimento(AAAAMMDD)
		String dataVencimento = Util.recuperaAnoMesDiaDaData(conta
				.getDataVencimentoConta());
		registroTipoE.append(dataVencimento);
		// Valor do débito
		BigDecimal valorDebito = new BigDecimal("0.00");
		valorDebito = valorDebito.add(conta.getValorAgua());
		valorDebito = valorDebito.add(conta.getValorEsgoto());
		valorDebito = valorDebito.add(conta.getDebitos());
		valorDebito = valorDebito.subtract(conta.getValorCreditos());

		/*
		 * Colocado por Sávio Luiz em 31/08/2007 (Analista: Rosana Carvalho)
		 * OBJETIVO: Retirar o valor dos impostos do valor total da conta
		 */
		if (conta.getValorImposto() != null) {
			valorDebito = valorDebito.subtract(conta.getValorImposto());
		}

		String valorDebitoString = ("" + valorDebito).replace(".", "");
		registroTipoE.append(Util.adicionarZerosEsquedaNumero(15,
				valorDebitoString));
		// Código da moeda
		registroTipoE.append("03");
		// inicio preenchido conforme segue abaixo(E.07)
		// Ano/Mês de referência da conta no formato AAAAMM
		registroTipoE.append(conta.getReferencia());
		// Digito verificador da conta
		registroTipoE.append(conta.getDigitoVerificadorConta());
		// Id da conta
		registroTipoE.append(Util.completaString("" + conta.getId(), 9));
		// id do grupo de faturamento
		registroTipoE.append(Util.completaString(""
				+ debitoAutomaticoMovimento.getFaturamentoGrupo().getId(), 3));
		// reservado para o futuro
		registroTipoE.append(Util.completaString("", 42));
		// fim preenchido conforme segue abaixo(E.07)

		// reservado para o futuro
		registroTipoE.append(Util.completaString("", 19));
		// Tipo do Movimento
		registroTipoE.append("0");

		return registroTipoE;
	}
}
