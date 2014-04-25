package gcom.arrecadacao;

import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.SessionBean;


/**
 * Controlador Faturamento CAEMA
 *
 * @author Sávio Luiz
 * @date 28/04/2008
 */
public class ControladorArrecadacaoCAEMASEJB extends ControladorArrecadacao implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAEMA
	//==============================================================================================================
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras 
	 *
	 * @author Sávio Luiz, Rafael Corrêa, Raphael Rossiter
	 * @date 15/02/2006, 12/05/2008, 18/11/2008
	 *
	 * @param codigoBarras
	 * @return RegistroHelperCodigoBarras
	 * @throws ControladorException 
	 */
	public RegistroHelperCodigoBarras distribuirDadosCodigoBarras(
			String codigoBarras) throws ControladorException {

		// instancia o objeto de código de barras, setando os valores que são iguais para todas as empresas
		RegistroHelperCodigoBarras registroHelperCodigoBarras = distribuirDadosCodigoBarrasGeral(codigoBarras);
		
		// recupera o id pagamento da string
		String idPagamento = codigoBarras.substring(19, 44);
		
		//TIPO PAGAMENTO GSAN
		String tipoPagamento = idPagamento.substring(24, 25).trim();
		
		//TIPO PAGAMENTO LEGADO
		String tipoPagamentoLegado = idPagamento.substring(21, 23).trim();
		
		String tipoPagamentoLegadoConta = idPagamento.substring(22, 23).trim();
		
		//PARA IDENTIFICAR LEGADO
		boolean eHLegado = false;
		
		String dataVencimentoParaVerificar = idPagamento.substring(0, 8);
		Integer anoVencimentoParaVerificar = Integer.parseInt(dataVencimentoParaVerificar.substring(4, 8));
		
		Integer anoReferenciaParaVerificar = Integer.parseInt(idPagamento.substring(8, 12));
		
		if (tipoPagamento.equals("0")){
			eHLegado = true;
		}
		else if (!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_CLIENTE.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_DOCUMENTO_COBRANCA_NOVO.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_COM_IDENTIFICACAO_MATRICULA.toString()) &&
				!tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_COM_IDENTIFICACAO_CLIENTE.toString())){
				
			eHLegado = true;
		}
			
		else if (!Util.validarDiaMesAnoSemBarra(dataVencimentoParaVerificar) && 
				anoVencimentoParaVerificar > 2000 && anoReferenciaParaVerificar < 2009 &&
				anoReferenciaParaVerificar <= anoVencimentoParaVerificar &&
				(tipoPagamentoLegado.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA) ||
						tipoPagamentoLegadoConta.equals(""+(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA_INTEGER)))){
			
			eHLegado = true;
		}
		
		//IDENTIFICAR TIPO DE PAGAMENTO CAMPANHA - CONTINUAÇÃO LEGADO
		if (!eHLegado){
			
			String mesAnoFaturaParaVerificar = idPagamento.substring(11, 17);
			mesAnoFaturaParaVerificar = Util.formatarMesAnoSemBarraParaMesAnoComBarra(mesAnoFaturaParaVerificar);
			
			if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL.toString()) &&
				(tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString()) &&
				!Util.validarMesAno(mesAnoFaturaParaVerificar)){
				
				eHLegado = true;
			}
			
		}
		
		if (eHLegado){
		
			
			
			if ((tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA_INTEGER.toString())){
				 
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA;
			}
			
			else if ((tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA_INTEGER.toString())){
					 
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA;
			}
			
			else if ((tipoPagamentoLegado.substring(1, 2)).equals(
				ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA_INTEGER.toString())){
						 
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA;
			}
			
			else{
				
				tipoPagamento = ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA;
			}
		}
		
		
		registroHelperCodigoBarras.setTipoPagamento(tipoPagamento);
		
		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = distribuirDadosCodigoBarrasPorTipoPagamento(
		idPagamento, tipoPagamento, registroHelperCodigoBarras.getIdEmpresa());

		registroHelperCodigoBarras
		.setRegistroHelperCodigoBarrasTipoPagamento(registroHelperCodigoBarrasTipoPagamento);

		return registroHelperCodigoBarras;
	}

	
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 19/11/2008
	 *
	 * @param idPagamento
	 * @param tipoPagamento
	 * @param idEmpresa
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 * @throws ControladorException 
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento(
			String idPagamento, String tipoPagamento, String idEmpresa) throws ControladorException {

		RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento = new RegistroHelperCodigoBarrasTipoPagamento();

		registroHelperCodigoBarrasTipoPagamento = 
			super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		
		//LEGADO - CAEMA
		//===============================================================================================================================
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA.toString()) ||
			tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA.toString())){
			
			//Ano de Emissão da Guia
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 4).trim());
			
			// Número da Guia
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(4, 12).trim());
			
			// Matrícula do Imóvel sem o dígito verificador
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(12, 21).trim());
			
			// Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(21, 23).trim());
			
			// Não Utilizado
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(23, 25).trim());
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA.toString())){
			
			//Vencimento da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 8).trim());
			
			// Quantidade de Débitos
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(8, 12).trim());
			
			// Código Orçamentário passou a ser o id do cliente - Raphael Rossiter e Sávio em 11/09/2008
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(12, 21).trim());
			
			// Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(21, 23).trim());
			
			// Mês da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(23, 25).trim());
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA.toString())){
			
			//Vencimento da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
					.substring(0, 8).trim());
			
			// Ano da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
					.substring(8, 12).trim());
			
			// Matrícula do Imóvel sem o dígito verificador
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
					.substring(12, 21).trim());
			
			// Tipo da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
					.substring(21, 22).trim());
			
			// Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
					.substring(22, 23).trim());
			
			// Mês da Fatura
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
					.substring(23, 25).trim());
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString())){
			
			//Aviso de Débitos
			if (idPagamento.substring(23, 25).trim().equals("00")) {
				
				// Vencimento da Fatura
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
						.substring(0, 8).trim());
				
				// Ano da Fatura
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
						.substring(8, 12).trim());
				
				// Matrícula do Imóvel sem o dígito verificador
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
						.substring(12, 21).trim());
				
				// Tipo de Documento
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
						.substring(21, 23).trim());
				
				// Não Utilizado (Zeros)
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
						.substring(23, 25).trim());
				
			} 
			
			// Campanha
			else {
				
				// Número do Lote
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
						.substring(0, 8).trim());
				
				// Ano do Lote
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
						.substring(8, 12).trim());
				
				// Matrícula do Imóvel sem o dígito verificador
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
						.substring(12, 21).trim());
				
				// Tipo de Documento 
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
						.substring(21, 22).trim());
				
				// Tipo de Documento 
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
						.substring(22, 23).trim());
				
				// Plano do Financiamento
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
						.substring(23, 25).trim());
				
			}
		}	
		//===============================================================================================================================
		
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 26/05/2008
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
		
		//LEGADO - CAEMA
		//===============================================================================================================================
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA.toString()) ||
			tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA.toString())){
			
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasGuiaPagamento_CAEMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
					
			
			

			//===============================================================================================================================
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA.toString())){
			
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasClienteResponsavel_CAEMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
					

			//===============================================================================================================================
		}
		
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA.toString())){
			
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_CAEMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
			
			
			//===============================================================================================================================
		}		
	
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString())){
			
			/*
			 * Caso o G05.7.2.6 - PLANO DE FINANCIAMENTO tenha sido informado é CAMPANHA, caso
			 * contrário será AVISO DE DÉBITO.
			 */
			if (registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null){
				
				//CAMPANHA
				pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasCampanha_CAEMA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				//===============================================================================================================================
			}
			else{
				
				//AVISO DE DÉBITO
				pagamentoHelperCodigoBarras = processarPagamentosCodigoBarrasDocumentoCobrancaTipo5_CAEMA_LEGADO(
				registroHelperCodigoBarras, sistemaParametro, dataPagamento, anoMesPagamento, valorPagamento,
				idFormaArrecadacao);
				
				//===============================================================================================================================
			}
			
		}
		else{
			
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
	 * [SB0014] - Processar Pagamento Legado CAEMA - FATURA 
	 *
	 * @author Raphael Rossiter
	 * @date 27/05/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasClienteResponsavel_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();
		
		/*
		 * Alterado por Raphael Rossiter e Sávio em 11/09/2008
		 * O código orçamentário na realizade é o id do cliente responsável 
		 */
		//[FS0011] - Validar Código Orçamentário
		/*Short codigoOrcamentario = null;
		Integer codigoOrcamentarioInteger = null;
		
		boolean codigoOrcamentarioInvalido = Util.validarValorNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());
		
		if (codigoOrcamentarioInvalido) {
			descricaoOcorrencia = "CÓDIGO ORÇAMENTÁRIO NÃO NUMÉRICO";
		}
		else{
			
			codigoOrcamentarioInteger = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());
			
			codigoOrcamentario = new Short(codigoOrcamentarioInteger.toString());
		}*/
		
		//[FS0000] - Validar Cliente
		boolean idClienteInvalido = Util.validarValorNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());
		
		Integer idClienteNaBase = null;

		if (idClienteInvalido) {
			descricaoOcorrencia = "CÓDIGO DO CLIENTE NÃO NUMÉRICO";
		} 
		else {
			
			// Verifica se existe o id do cliente na base
			Integer idCliente = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());

			try {
				idClienteNaBase = repositorioCliente.verificarExistenciaCliente(idCliente);
			} 
			catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			if (idClienteNaBase == null) {
				descricaoOcorrencia = "CLIENTE RESPONSÁVEL NÂO CADASTRADO";
			}
		}
		
		//[FS0012] - Validar Data de Vencimento
		Date dataVencimento = null;
		
		boolean dataVencimentoInvalido = Util.validarDiaMesAnoSemBarra(registroHelperCodigoBarras
				.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
		
		if (dataVencimentoInvalido) {
			descricaoOcorrencia = "DATA DE VENCIMENTO INVÁLIDO";
		}
		else{
			dataVencimento = Util.converteStringSemBarraParaDate(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
		}
		

		if (descricaoOcorrencia.equals("OK")) {

			/*
			 * O anoMesReferencia será formado pelo ano da data de vencimento da fatura com o mês
			 * que vem no código de barras - Raphael Rossiter e Rosana Carvalho
			 */
			String mesFatura = registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
			.getIdPagamento5();
			
			String anoMesReferencia = String.valueOf(Util.getAno(dataVencimento)) + mesFatura; 
			
			// inicializa a coleção de fatura item
			Collection faturaItens = null;

			try {
				
				faturaItens = repositorioFaturamento.pesquisarFaturaItem(idClienteNaBase, 
				new Integer(anoMesReferencia), valorPagamento);
			} 
			catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// verifica se a coleção é diferente de nula
			if (faturaItens != null && !faturaItens.isEmpty()) {
				
				Iterator faturaItensIterator = faturaItens.iterator();
				
				while (faturaItensIterator.hasNext()) {
					
					Object[] faturaItem = (Object[]) faturaItensIterator.next();
					
					// inicializa as variaveis que veio da pesquisa
					Integer idContaPesquisa = null;
					Integer idImovelPesquisa = null;
					Integer idLocalidadePesquisa = null;
					BigDecimal valorConta = null;
					Integer anoMesReferenciaFatura = null;
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
					//verifica o id da conta Historico
					if (faturaItem[6] != null) {
						idContaPesquisa = (Integer) faturaItem[6];
					}
					//verifica o id da conta Historico
					if (faturaItem[7] != null) {
						anoMesReferenciaFatura = (Integer) faturaItem[7];
					}

					// cria o objeto pagamento para setar os dados
					Pagamento pagamento = new Pagamento();
					pagamento.setAnoMesReferenciaPagamento(anoMesReferenciaFatura);
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
					documentoTipo.setId(DocumentoTipo.FATURA_CLIENTE);
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
					arrecadacaoForma.setId(idFormaPagamento);
					pagamento.setArrecadacaoForma(arrecadacaoForma);
					pagamento.setCliente(null);
					pagamento.setUltimaAlteracao(new Date());

					colecaoPagamentos.add(pagamento);
				}
			}
			else{
				
				//atribui o valor 2(NÃO) ao indicador aceitacao registro
				indicadorAceitacaoRegistro = "2";
				
				descricaoOcorrencia = "FATURA DO CÓDIGO ORÇAMENTÁRIO INEXISTENTE";
			}
		} 
		else {
			
			// atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - CONTA ou 2 VIA
	 *
	 * @author Raphael Rossiter
	 * @date 27/05/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasConta_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		//[FS0002] - Validar matrícula do imóvel
		matriculaImovelInvalida = Util.validarValorNaoNumerico(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} 
		else {

			//Calcular Digito Verificador da Matricula (MÓDULO 11)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do imóvel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		
		
		//Mês/Ano de referência da conta = (campo G05.7.2.3 + G05.7.2.6)
		Integer anoMesReferencia = new Integer(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2() + 
		registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6());

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer idConta = null;

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {
				idConta = repositorioFaturamento
				.pesquisarExistenciaContaComSituacaoAtual(imovel, anoMesReferencia);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			
			/*
             * Alterado por Raphael Rossiter em 09/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da conta e NÃO com
             * a localidade do imóvel.
             */
			if (idConta == null) {
				descricaoOcorrencia = "CONTA INEXISTENTE";
			
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}
			else {
				
				try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorConta(idConta);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
			}

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(anoMesReferencia);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
			 * arrecadação da tabela sistema parametro então seta o ano mes da
			 * data de debito.
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

			// Verifica se o id da conta é diferente de nulo
			if (idConta != null) {

				ContaGeral conta = new ContaGeral();
				conta.setId(idConta);
				pagamento.setContaGeral(conta);

			} else {

				pagamento.setContaGeral(null);
			}
			pagamento.setGuiaPagamento(null);

			// verifica se o id da conta é diferente de nulo
			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);
			} else {

				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.CONTA);
			pagamento.setDocumentoTipo(documentoTipo);
			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
				pagamento.setImovel(imovel);
			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());

			colecaoPagamentos.add(pagamento);

		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao registro
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
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - GUIA DE PAGAMENTO
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamento_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {

			//Calcular Digito Verificador da Matricula (MÓDULO 10)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do imóvel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}


		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer dadosGuiaPagamento[] = null;

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);
			
			//ANO DA GUIA
			Integer anoGuia = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
			
			//NÚMERO GUIA
			Integer numeroGuia = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());

			
			try {
				
				dadosGuiaPagamento = repositorioArrecadacao
				.pesquisarExistenciaGuiaPagamento(imovel, numeroGuia, anoGuia);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (dadosGuiaPagamento == null) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}
			
			
			/*
             * Alterado por Raphael Rossiter em 11/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da guia de pagamento e NÃO com
             * a localidade do imóvel.
             */
            if (dadosGuiaPagamento != null) {
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorGuiaPagamento(dadosGuiaPagamento[0]);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
       
            }
            else{
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
            }
            

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
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
			pagamento.setContaGeral(null);

			// Verifica se o id da guia é diferente de nulo
			if (dadosGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(dadosGuiaPagamento[0]);
				pagamento.setGuiaPagamento(guiaPagamento);
				
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(dadosGuiaPagamento[1]);
				pagamento.setDebitoTipo(debitoTipo);

			} else {
				pagamento.setGuiaPagamento(null);
				
				/*
				 * Colocado por Raphael Rossiter em 13/01/2009
				 * Analista: Eduardo Borges
				 */
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(DebitoTipo.SERVICOS_ESPECIAIS);
				
				pagamento.setDebitoTipo(debitoTipo);
			}

			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);

			} else {
				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
				pagamento.setImovel(imovel);
			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());

			colecaoPagamnetos.add(pagamento);

		} else {

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
	 * [SB0014] - Processar Pagamento Legado CAEMA - CAMPANHA
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasCampanha_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamnetos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {

			//Calcular Digito Verificador da Matricula (MÓDULO 10)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do imóvel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}


		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer dadosGuiaPagamento[] = null;

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);
			
			//NÚMERO LOTE
			Integer lotePagamento = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
			
			//ANO DO LOTE
			Integer anoGuia = new Integer(registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2());
			
			try {
				
				dadosGuiaPagamento = repositorioArrecadacao
				.pesquisarExistenciaGuiaPagamentoPorLotePagamento(imovel, lotePagamento, anoGuia);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (dadosGuiaPagamento == null) {
				descricaoOcorrencia = "GUIA PAGAMENTO INEXISTENTE";
			}
			
			
			/*
             * Alterado por Raphael Rossiter em 11/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da guia de pagamento e NÃO com
             * a localidade do imóvel.
             */
            if (dadosGuiaPagamento != null) {
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidadePorGuiaPagamento(dadosGuiaPagamento[0]);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
       
            }
            else{
            	
            	try {
                    idLocalidade = repositorioLocalidade
                    .pesquisarIdLocalidade(idImovelNaBase);

                } catch (ErroRepositorioException e) {
                    throw new ControladorException("erro.sistema", e);
                }
            }
            

			// Cria o objeto pagamento para setar os dados
			Pagamento pagamento = new Pagamento();
			pagamento.setAnoMesReferenciaPagamento(null);

			/*
			 * Caso o ano mes da data de dedito seja maior que o ano mes de
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
			pagamento.setContaGeral(null);

			// Verifica se o id da guia é diferente de nulo
			if (dadosGuiaPagamento != null) {

				GuiaPagamento guiaPagamento = new GuiaPagamento();
				guiaPagamento.setId(dadosGuiaPagamento[0]);
				pagamento.setGuiaPagamento(guiaPagamento);
				
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(dadosGuiaPagamento[1]);
				pagamento.setDebitoTipo(debitoTipo);

			} else {
				pagamento.setGuiaPagamento(null);
				
				/*
				 * Colocado por Raphael Rossiter em 23/01/2009
				 * Analista: Eduardo Borges
				 */
				DebitoTipo debitoTipo = new DebitoTipo();
				debitoTipo.setId(DebitoTipo.SERVICOS_ESPECIAIS);
				
				pagamento.setDebitoTipo(debitoTipo);
			}

			if (idLocalidade != null) {

				Localidade localidade = new Localidade();
				localidade.setId(idLocalidade);
				pagamento.setLocalidade(localidade);

			} else {
				pagamento.setLocalidade(null);
			}

			DocumentoTipo documentoTipo = new DocumentoTipo();
			documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
				pagamento.setImovel(imovel);
			} else {
				pagamento.setImovel(null);
			}

			pagamento.setArrecadadorMovimentoItem(null);

			ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
			arrecadacaoForma.setId(idFormaPagamento);
			pagamento.setArrecadacaoForma(arrecadacaoForma);
			pagamento.setCliente(null);
			pagamento.setUltimaAlteracao(new Date());

			colecaoPagamnetos.add(pagamento);

		} else {

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
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE DÉBITO
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param registroHelperCodigoBarras
	 * @param sistemaParametro
	 * @param dataPagamento
	 * @param anoMesPagamento
	 * @param valorPagamento
	 * @param idFormaPagamento
	 * @return PagamentoHelperCodigoBarras
	 * @throws ControladorException
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasDocumentoCobrancaTipo5_CAEMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaPagamento) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		Collection colecaoDevolucoes = new ArrayList();

		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		String matriculaImovel = null;
		Integer matriculaImovelValidada = null;

		// valida a matricula do imóvel
		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());
		
		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {
			
			//Calcular Digito Verificador da Matricula (MÓDULO 10)
			matriculaImovel = registroHelperCodigoBarras
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelValidada = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelValidada = 0;
				}
			}

			// Verifica se existe a matricula do imóvel na base
			try {
				idImovelNaBase = repositorioImovel
				.recuperarMatriculaImovel(matriculaImovelValidada);
			} 
			catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		//Campo G05.7.2.3 - Data de Emissão
		Date dataEmissao = Util.converteStringSemBarraParaDate(registroHelperCodigoBarras
		.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento1());
		
		if (descricaoOcorrencia.equals("OK")) {
			
			// inicializa o id da localidade
			Integer idLocalidade = null;

			// inicializa a coleção de cobranca documento item
			Collection cobrancaDocumentoItens = null;
			// inicializa a coleção de cobranca documento item
			Object[] parmsDocumentoCobranca = null;

			

			try {

				cobrancaDocumentoItens = repositorioCobranca
				.pesquisarCobrancaDocumentoItem(idImovelNaBase, dataEmissao);
				
				if (cobrancaDocumentoItens == null || cobrancaDocumentoItens.isEmpty()){
					
					cobrancaDocumentoItens = repositorioCobranca
					.pesquisarCobrancaDocumentoItem(idImovelNaBase, valorPagamento);
				}
				
				parmsDocumentoCobranca = repositorioCobranca
				.pesquisarParmsCobrancaDocumento(idImovelNaBase, dataEmissao);
				
				if (parmsDocumentoCobranca == null){
					
					parmsDocumentoCobranca = repositorioCobranca
					.pesquisarParmsCobrancaDocumento(idImovelNaBase, valorPagamento);
				}
				
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			// caso exista documento de cobrança
			if (parmsDocumentoCobranca != null) {
				
				Integer idCobrancaDocumento = null;
				BigDecimal valorAcrescimo = new BigDecimal("0.00");
				BigDecimal valorDesconto = new BigDecimal("0.00");
				BigDecimal valorTaxa = new BigDecimal("0.00");
				Integer idDocumentoTipo = null;
				
				if (parmsDocumentoCobranca[0] != null) {
					valorAcrescimo = ((BigDecimal) parmsDocumentoCobranca[0]);
				}
				if (parmsDocumentoCobranca[1] != null) {
					valorDesconto = ((BigDecimal) parmsDocumentoCobranca[1]);
				}
				if (parmsDocumentoCobranca[2] != null) {
					dataEmissao = ((Date) parmsDocumentoCobranca[2]);
				}
				if (parmsDocumentoCobranca[3] != null) {
					idCobrancaDocumento = ((Integer) parmsDocumentoCobranca[3]);
				}
				if (parmsDocumentoCobranca[4] != null) {
					valorTaxa = ((BigDecimal) parmsDocumentoCobranca[4]);
				}
				
				/*
                 * Alterado por Raphael Rossiter em 10/01/2008 - Analistas: Eduardo e Aryed
                 * OBJ: Gerar os pagamentos associados com a localidade do documento de cobrança e NÃO com
                 * a localidade do imóvel.
                 */
				if (parmsDocumentoCobranca[5] != null) {

					idLocalidade = ((Integer) parmsDocumentoCobranca[5]);
				}
				else{
					
					try {
                        idLocalidade = repositorioLocalidade
                        .pesquisarIdLocalidade(idImovelNaBase);

                    } catch (ErroRepositorioException e) {
                        throw new ControladorException("erro.sistema", e);
                    }
                    
				}
				
				if (parmsDocumentoCobranca[6] != null) {
					idDocumentoTipo = ((Integer) parmsDocumentoCobranca[6]);
				}
				
				// Caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					// [SB0008 - Alterar vencimento dos itens do
					// documento de cobrança]

					alterarVencimentoItensDocumentoCobranca(
							idCobrancaDocumento, dataEmissao);

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

				// Caso o valor de acrescimo for maior que zero
				if (valorAcrescimo.compareTo(new BigDecimal("0.00")) == 1) {
					
					// [SB0005 - Processar Recebimento de Acrescimos por Impontualidade]
					Pagamento pagamento = processarRecebimentoAcrescimosImpontualidade(
							idCobrancaDocumento, dataEmissao, valorAcrescimo,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaPagamento, idDocumentoTipo);

					colecaoPagamentos.add(pagamento);

				}

				// Caso o valor de desconto for maior que zero
				if (valorDesconto.compareTo(new BigDecimal("0.00")) == 1) {
					
					// [SB0006 - Processar Desconto concedido no documento de cobrança]
					Devolucao devolucao = processarDescontoConcedidoDocumentoCobranca(
							idCobrancaDocumento, dataEmissao, valorDesconto,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaPagamento, idDocumentoTipo);

					colecaoDevolucoes.add(devolucao);

				}

				// Caso o valor da taxa referente ao documento de cobrança for maior que zero
				if (valorTaxa.compareTo(new BigDecimal("0.00")) == 1) {
					
					// [SB0006 - Processar Desconto concedido no documento de cobrança]
					Pagamento pagamento = processarTaxaDocumentoCobranca(
							idCobrancaDocumento, dataEmissao, valorTaxa,
							idImovelNaBase, idLocalidade, sistemaParametro,
							idFormaPagamento, idDocumentoTipo);

					colecaoPagamentos.add(pagamento);

				}

				// verifica se a coleção é diferente de nula
				if (cobrancaDocumentoItens != null
						&& !cobrancaDocumentoItens.isEmpty()) {

					Iterator cobrancaDocumentoItensIterator = cobrancaDocumentoItens
							.iterator();

					while (cobrancaDocumentoItensIterator.hasNext()) {

						Object[] cobrancaDocumentoItem = (Object[]) cobrancaDocumentoItensIterator
								.next();

						/*
						 * Colocado por Raphael Rossiter em 31/10/2007 OBJ:
						 * Apenas gerar os pagamentos referentes aos itens que
						 * NAO tenham CreditoARealizar
						 */
						BigDecimal valorItemCobrado = null;

						if (cobrancaDocumentoItem[13] == null) {

							// inicializa as variaveis que veio da
							// pesquisa
							Integer idContaPesquisa = null;
							Integer idContaGeralPesquisa = null;
							Integer idGuiaPagamento = null;
							Integer idDebitoACobrar = null;
							int contaReferencia = 0;
							Integer idDebitoTipo = null;
							Integer idGuiaPagamentoGeralPesquisa = null;
							Integer idDebitoACobrarGeralPesquisa = null;
							// verifica o id da conta
							if (cobrancaDocumentoItem[0] != null) {
								idContaPesquisa = (Integer) cobrancaDocumentoItem[0];
								idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[0];
								// referencia conta
								if (cobrancaDocumentoItem[4] != null) {
									contaReferencia = (Integer) cobrancaDocumentoItem[4];
								}
							} else {
								// caso não exista na conta então
								// pesquisa
								// na conta histórico
								if (cobrancaDocumentoItem[10] != null) {
									idContaGeralPesquisa = (Integer) cobrancaDocumentoItem[10];
								}

								// referencia conta histórico
								if (cobrancaDocumentoItem[5] != null) {
									contaReferencia = (Integer) cobrancaDocumentoItem[5];
								}
							}

							// verifica o id da guia pagamento
							if (cobrancaDocumentoItem[1] != null) {
								idGuiaPagamento = (Integer) cobrancaDocumentoItem[1];
								idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[1];
							} else {
								// caso não exista no guia pagamento
								// então
								// pesquisa no guia pagamento histórico
								if (cobrancaDocumentoItem[11] != null) {
									idGuiaPagamentoGeralPesquisa = (Integer) cobrancaDocumentoItem[11];
								}
							}
							// verifica o id do debito a cobrar
							if (cobrancaDocumentoItem[2] != null) {
								idDebitoACobrar = (Integer) cobrancaDocumentoItem[2];
								idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[2];

								// [SB0012]- Verifica Pagamento de Débito a
								// Cobrar
								// de Parcelamento
								verificaPagamentoDebitoACobrarParcelamento(idDebitoACobrar, null);

							} else {
								// caso não exista no debito a cobrar
								// então
								// pesquisa no debito a cobrar histórico
								if (cobrancaDocumentoItem[12] != null) {
									idDebitoACobrarGeralPesquisa = (Integer) cobrancaDocumentoItem[12];
								}
							}
							// verifica o valor do item cobrado da
							// cobranca
							// documento item
							if (cobrancaDocumentoItem[3] != null) {
								valorItemCobrado = (BigDecimal) cobrancaDocumentoItem[3];
							}

							if (idContaGeralPesquisa == null) {
								// caso exista guia de pagamento
								if (idGuiaPagamentoGeralPesquisa != null) {
									// verifica o id do debito tipo se é
									// da
									// guia
									if (cobrancaDocumentoItem[6] != null) {
										idDebitoTipo = (Integer) cobrancaDocumentoItem[6];
									} else {
										// caso não exista no guia
										// pagamento
										// então
										// pesquisa no guia pagamento
										// histórico
										if (cobrancaDocumentoItem[7] != null) {
											idDebitoTipo = (Integer) cobrancaDocumentoItem[7];
										}
									}
								}
								// caso exista debito a cobrar
								if (idDebitoACobrarGeralPesquisa != null) {
									// verifica o id do debito tipo no
									// debito a cobrar
									if (cobrancaDocumentoItem[8] != null) {
										idDebitoTipo = (Integer) cobrancaDocumentoItem[8];

									} else {
										// caso não exista no debito a
										// cobrar
										// então
										// pesquisa no debito a cobrar
										// histórico
										if (cobrancaDocumentoItem[9] != null) {
											idDebitoTipo = (Integer) cobrancaDocumentoItem[9];
										}
									}
								}
							}

							// cria o objeto pagamento para setar os
							// dados
							Pagamento pagamento = new Pagamento();
							if (contaReferencia != 0) {
								pagamento
										.setAnoMesReferenciaPagamento(contaReferencia);
							} else {
								pagamento.setAnoMesReferenciaPagamento(null);
							}

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
							pagamento.setValorPagamento(valorItemCobrado);
							pagamento.setDataPagamento(dataPagamento);
							pagamento.setPagamentoSituacaoAtual(null);
							pagamento.setPagamentoSituacaoAnterior(null);
							if (idDebitoTipo != null) {
								DebitoTipo debitoTipo = new DebitoTipo();
								debitoTipo.setId(idDebitoTipo);
								pagamento.setDebitoTipo(debitoTipo);
							} else {
								pagamento.setDebitoTipo(null);
							}

							// Verifica se o id da conta é diferente de nulo
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
							
							// Verifica se o id da guia de pagamento é diferente de nulo
							if (idGuiaPagamentoGeralPesquisa != null) {
								
								if (idGuiaPagamento != null) {
									
									GuiaPagamento guiaPagamento = new GuiaPagamento();
									guiaPagamento.setId(idGuiaPagamento);
									pagamento.setGuiaPagamento(guiaPagamento);
									
									/*
									 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
									 * OBJ: Inserir o pagamento com a localidade da própria guia e não
									 * com a localidade do documento de cobrança
									 */
									try {
					                    idLocalidade = repositorioLocalidade
					                    .pesquisarIdLocalidadePorGuiaPagamento(idGuiaPagamento);

					                } catch (ErroRepositorioException e) {
					                    throw new ControladorException("erro.sistema", e);
					                }
								} 
								else {
									
									pagamento.setGuiaPagamento(null);
									
								}
								
								DocumentoTipo documentoTipo = new DocumentoTipo();
								documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
								pagamento.setDocumentoTipo(documentoTipo);

							} 
							else {
								
								pagamento.setGuiaPagamento(null);
								
							}
							
							// Verifica se o id do debito a cobrar é diferente de nulo
							if (idDebitoACobrarGeralPesquisa != null) {
								
								if (idDebitoACobrar != null) {
									
									DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
									debitoACobrarGeral.setId(idDebitoACobrar);
									
									pagamento.setDebitoACobrarGeral(debitoACobrarGeral);
									
									/*
									 * Colocado por Raphael Rossiter em 26/11/2008 - CRC264
									 * OBJ: Inserir o pagamento com a localidade do próprio debito a cobrar e não
									 * com a localidade do documento de cobrança
									 */
									try {
										
										idLocalidade = repositorioLocalidade
					                    .pesquisarIdLocalidadePorDebitoACobrar(idDebitoACobrar);
										
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

							// verifica se o id da conta é diferente de
							// nulo
							if (idLocalidade != null) {
								Localidade localidade = new Localidade();
								localidade.setId(idLocalidade);
								pagamento.setLocalidade(localidade);
							} else {
								pagamento.setLocalidade(null);

							}

							// seta o id do aviso bancario
							pagamento.setAvisoBancario(null);

							// seta o imovel
							if (idImovelNaBase != null) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelNaBase);
								pagamento.setImovel(imovel);
							} else {
								pagamento.setImovel(null);
							}

							// ArrecadadorMovimentoItem
							// arrecadadorMovimentoItem
							// = new ArrecadadorMovimentoItem();

							pagamento.setArrecadadorMovimentoItem(null);

							ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
							arrecadacaoForma.setId(idFormaPagamento);
							pagamento.setArrecadacaoForma(arrecadacaoForma);
							pagamento.setCliente(null);
							pagamento.setUltimaAlteracao(new Date());
							
							pagamento.setFatura(null);
							CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
							cobrancaDocumento.setId(idCobrancaDocumento);
							pagamento.setCobrancaDocumento(cobrancaDocumento);
							
							DocumentoTipo documentoTipoAgregador = new DocumentoTipo();
							documentoTipoAgregador.setId(idDocumentoTipo);
							pagamento.setDocumentoTipoAgregador(documentoTipoAgregador);
							
							pagamento.setDataProcessamento(new Date());
							
							if (pagamento.getDocumentoTipo() != null) {
								colecaoPagamentos.add(pagamento);
							}
						} else {

							// Para os itens que tenham CreditoARealizar gerar
							// suas respectivas devoluções

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
							Integer anoMesDataDevolucao = Util
									.getAnoMesComoInteger(devolucao
											.getDataDevolucao());

							if (anoMesDataDevolucao > sistemaParametro
									.getAnoMesArrecadacao()) {
								devolucao
										.setAnoMesReferenciaArrecadacao(anoMesDataDevolucao);
							} else {
								devolucao
										.setAnoMesReferenciaArrecadacao(sistemaParametro
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
								localidade
										.setId((Integer) cobrancaDocumentoItem[14]);
								devolucao.setLocalidade(localidade);
							}

							// Imovel = Imovel da tabela COBRANCA_DOCUMENTO
							if (cobrancaDocumentoItem[15] != null) {
								Imovel imovel = new Imovel();
								imovel
										.setId((Integer) cobrancaDocumentoItem[15]);
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
							creditoARealizarGeral
									.setId((Integer) cobrancaDocumentoItem[13]);
							devolucao
									.setCreditoARealizarGeral(creditoARealizarGeral);

							// Ultima Alteração
							devolucao.setUltimaAlteracao(new Date());

							// ADICIONANDO A DEVOLUCAO GERADA NA COLECAO DE
							// RETORNO
							colecaoDevolucoes.add(devolucao);
						}
					}
				}

			} else {
				descricaoOcorrencia = "DOCUMENTO DE COBRANÇA INEXISTENTE";
				indicadorAceitacaoRegistro = "2";
			}

		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
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
	 * Obtém a representação númerica do código de barras de um pagamento de
	 * acordo com os parâmetros informados
	 * 
	 * [UC0229] Obter Representação Numérica do Código de Barras
	 * 
	 * Formata a identificação do pagamento de acordo com o tipo de pagamento
	 * informado
	 * 
	 * [SB0001] Obter Identificação do Pagamento
	 * 
	 * @author Pedro Alexandre, Raphael Rossiter
	 * @date 20/04/2006, 04/11/2008
	 * 
	 * @param tipoPagamento
	 * @param idLocalidade
	 * @param matriculaImovel
	 * @param anoMesReferenciaConta
	 * @param digitoVerificadorRefContaModulo10
	 * @param idTipoDebito
	 * @param anoEmissaoGuiaPagamento
	 * @param sequencialDocumentoCobranca
	 * @param idTipoDocumento
	 * @param idCliente
	 * @param seqFaturaClienteResponsavel
	 * @return
	 */
	public String obterIdentificacaoPagamento(Integer tipoPagamento,
			Integer idLocalidade, Integer matriculaImovel,
			String mesAnoReferenciaConta,
			Integer digitoVerificadorRefContaModulo10, Integer idTipoDebito,
			String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca,
			Integer idTipoDocumento, Integer idCliente,
			Integer seqFaturaClienteResponsavel,String idGuiaPagamento) throws ControladorException {

		// Cria a variável que vai armazenar o identificador do pagamento formatado
		String identificacaoPagamento = "";

		// Caso o tipo de pagamento seja referente a conta
		if (tipoPagamento.intValue() == 3) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(9, "" + matriculaImovel);
			
			//FIXO
			identificacaoPagamento = identificacaoPagamento + "0";
			
			//Identifica o tamanho da matrícula do imóvel
			identificacaoPagamento = identificacaoPagamento + "1";
			
			identificacaoPagamento = identificacaoPagamento + mesAnoReferenciaConta;
			identificacaoPagamento = identificacaoPagamento + digitoVerificadorRefContaModulo10;
			
			identificacaoPagamento = identificacaoPagamento + "000";

		} 
		//Caso o tipo de pagamento seja referente a guia de pagamento (Imóvel)
		else if (tipoPagamento.intValue() == 4) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(9, "" + matriculaImovel);
			
			//Identifica o tamanho da matrícula do imóvel
			identificacaoPagamento = identificacaoPagamento + "1";
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(4, idTipoDebito.toString()));
			
			identificacaoPagamento = identificacaoPagamento + anoEmissaoGuiaPagamento;
			
			identificacaoPagamento = identificacaoPagamento + "000";
			
		} 
		//Caso a tipo de pagamento seja referente a documento de cobrança
		else if (tipoPagamento.intValue() == 5) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(9, "" + matriculaImovel);
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(9, sequencialDocumentoCobranca));
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(2, idTipoDocumento.toString()));
			
			//Identifica o tamanho da matrícula do imóvel
			identificacaoPagamento = identificacaoPagamento + "1";

		}
		//Caso o tipo de pagamento seja referente a guia de pagamento (Cliente)
		else if (tipoPagamento.intValue() == 6) {
			
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(3, "" + idLocalidade);
			identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumero(8, "" + idCliente);
			
			identificacaoPagamento = identificacaoPagamento + "00";
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(4, idTipoDebito.toString()));
			identificacaoPagamento = identificacaoPagamento + anoEmissaoGuiaPagamento;
			
			identificacaoPagamento = identificacaoPagamento + "000";

		}
		//Caso o tipo de pagamento seja referente a fatura do cliente responsável
		else if (tipoPagamento.intValue() == 7) {
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(9, idCliente.toString()));
			
			identificacaoPagamento = identificacaoPagamento + "00";
			
			identificacaoPagamento = identificacaoPagamento + mesAnoReferenciaConta;
			identificacaoPagamento = identificacaoPagamento + digitoVerificadorRefContaModulo10;
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(6, seqFaturaClienteResponsavel.toString()));
			
		} 
		//Caso a tipo de pagamento seja referente a documento de cobrança cliente
		else if (tipoPagamento.intValue() == 8) {
			
			identificacaoPagamento = identificacaoPagamento + "000";
			
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(8, idCliente.toString()));
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(9, sequencialDocumentoCobranca));
			identificacaoPagamento = identificacaoPagamento + (Util.adicionarZerosEsquedaNumero(2, idTipoDocumento.toString()));
			
			identificacaoPagamento = identificacaoPagamento + "00";
		} 
		else if(tipoPagamento.intValue() == 1 || tipoPagamento.intValue() == 9){
			
			identificacaoPagamento = Util.adicionarZerosEsquedaNumeroTruncando(3,idLocalidade.toString());
			
			if(tipoPagamento.intValue() == 1){
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,matriculaImovel.toString());
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idGuiaPagamento);
				
				//FIXO
				identificacaoPagamento = identificacaoPagamento + "00";
				
				//Identifica o tamanho da matrícula do imóvel
				identificacaoPagamento = identificacaoPagamento + "1";
			}
			else if(tipoPagamento.intValue() == 9){
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idCliente.toString());
				
				identificacaoPagamento = identificacaoPagamento + Util.adicionarZerosEsquedaNumeroTruncando(9,idGuiaPagamento);
				
				//FIXO
				identificacaoPagamento = identificacaoPagamento + "00";
				
				//Identifica o tamanho da matrícula do imóvel
				identificacaoPagamento = identificacaoPagamento + "1";
			}
				
		}

		// Retorna o identificador do pagamento formatado
		return identificacaoPagamento;
	}
	
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 *
	 * @author Raphael Rossiter
	 * @date 08/11/2008
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		
		super.distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(registroHelperCodigoG,
		arrecadadorMovimentoItemHelper);
		
		//LEGADO - CAEMA
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (MÓDULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_NEGOCIACAO_A_VISTA_LEGADO_CAEMA);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (MÓDULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_FATURA_LEGADO_CAEMA.toString())){
			
			arrecadadorMovimentoItemHelper
			.setIdentificacao(registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3());

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_FATURA_CLIENTE_RESPONSAVEL);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (MÓDULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}

			arrecadadorMovimentoItemHelper
			.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
		}
		
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CAMPANHA_AVISO_DEBITO_LEGADO_CAEMA.toString())){
			
			//Calcular Digito Verificador da Matricula (MÓDULO 11)
			Integer matriculaImovelComDigito = null;
			
			String matriculaImovel = registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3();

			if (matriculaImovel != null) {
				
				int digitoModulo11 = Util.obterDigitoVerificadorModulo11(matriculaImovel);
				
				try {
					
					matriculaImovelComDigito = new Integer(matriculaImovel + digitoModulo11);
					
				} catch (Exception e) {
					matriculaImovelComDigito = 0;
				}
			}

			if (matriculaImovelComDigito == 0){
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovel);
			}
			else{
				arrecadadorMovimentoItemHelper
				.setIdentificacao(matriculaImovelComDigito.toString());
			}
			
			if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento5().equals("00")){
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_AVISO_DEBITO_LEGADO_CAEMA);
			}
			else{
				
				arrecadadorMovimentoItemHelper
				.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CAMPANHA_LEGADO_CAEMA);
			}
			
		}

		
		//===============================================================================================================================
	}
	
	
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras
	 *
	 * @author Raphael Rossiter
	 * @date 06/11/2008
	 *
	 * @param registroHelperCodigoBarrasTipoPagamento
	 * @param idPagamento
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento_GUIA_PAGAMENTO_IMOVEL(
			RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento, String idPagamento) {
		
		//seta o código da localidade
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
				.substring(0, 3).trim());
		// seta a matrícula do imóvel
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
				.substring(3, 11).trim());
		// não está sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
				.substring(11, 13).trim());
		// seta o código do tipo do debito
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
				.substring(13, 17).trim());
		// seta o ano da emissão da guia de pagamento(AAAA)
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
				.substring(17, 21).trim());
		// não está sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
				.substring(21, 24).trim());
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras
	 *
	 * @author Raphael Rossiter
	 * @date 06/11/2008
	 *
	 * @param registroHelperCodigoBarrasTipoPagamento
	 * @param idPagamento
	 * @return RegistroHelperCodigoBarrasTipoPagamento
	 */
	public RegistroHelperCodigoBarrasTipoPagamento distribuirDadosCodigoBarrasPorTipoPagamento_GUIA_PAGAMENTO_CLIENTE(
			RegistroHelperCodigoBarrasTipoPagamento registroHelperCodigoBarrasTipoPagamento, String idPagamento) {
		
		//seta o código da localidade
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento
				.substring(0, 3).trim());
		// seta o id do cliente
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento
				.substring(3, 11).trim());
		// não está sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento
				.substring(11, 13).trim());
		// seta o código do tipo do debito
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(idPagamento
				.substring(13, 17).trim());
		// seta o ano da emissão da guia de pagamento(AAAA)
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(idPagamento
				.substring(17, 21).trim());
		// não está sendo utilizado
		registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(idPagamento
				.substring(21, 24).trim());
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
}
