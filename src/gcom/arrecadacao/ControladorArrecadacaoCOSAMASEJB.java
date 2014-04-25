package gcom.arrecadacao;

import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.DadosConteudoCodigoBarrasHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarrasTipoPagamento;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
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

import javax.ejb.SessionBean;

/**
 * Controlador Arrecadacao Juazeiro
 *
 * @author Rafael Corrêa
 * @date 30/06/2009
 */
public class ControladorArrecadacaoCOSAMASEJB extends ControladorArrecadacao implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA COSAMA
	//==============================================================================================================
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 20/05/2010
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
		
		//===============================================================================================================================
		//LEGADO - COSAMA
		//===============================================================================================================================
		String filler = "";
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString())){
			filler = idPagamento.substring(15, 24).trim();
		}
		else{
			filler = idPagamento.substring(13, 24).trim();
		}
		
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			filler.equals("000000000")){
			
			//Imóvel
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 8).trim());
				
			//Ano e Mês
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(8, 14).trim());
			
			//Dígito verificador
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(14, 15).trim());
				
			//Tipo de Documento (MOVER PARA 0 - ZERO)
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString());

		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString())){
			
			//Imóvel
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 8).trim());
				
			//Ano e Mês 1
			String meAno1 = idPagamento.substring(8, 10).trim() + "20" + idPagamento.substring(10, 12).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(meAno1);
			
			//Ano e Mês 2
			String meAno2 = idPagamento.substring(12, 14).trim() + "20" + idPagamento.substring(14, 16).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(meAno2);
			
			//Ano e Mês 3
			String meAno3 = idPagamento.substring(16, 18).trim() + "20" + idPagamento.substring(18, 20).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(meAno3);
			
			//Ano e Mês 4
			String meAno4 = idPagamento.substring(20, 22).trim() + "20" + idPagamento.substring(22, 24).trim();
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento5(meAno4);
				
			//Tipo de Documento
			registroHelperCodigoBarrasTipoPagamento.setIdPagamento6(ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString());
			
		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				filler.equals("00000000000")){
				
				//Imóvel
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento1(idPagamento.substring(0, 8).trim());
					
				//Localidade
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento2(idPagamento.substring(8, 11).trim());
				
				//tipo do débito
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento3(idPagamento.substring(11, 13).trim());
					
				//Tipo de Documento (MOVER PARA 30 - trinta)
				registroHelperCodigoBarrasTipoPagamento.setIdPagamento4(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString());
				
		}
		else{
			
			registroHelperCodigoBarrasTipoPagamento = super.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, tipoPagamento, idEmpresa);
		}
		//===============================================================================================================================
		
		
		return registroHelperCodigoBarrasTipoPagamento;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 20/05/2010
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
		
		if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			(registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
			registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
			ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString()))){
			
			//CONTA
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_COSAMA_LEGADO(
			registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
			idFormaArrecadacao);
			
			System.out.println("CONTA LEGADO");
			
		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null &&
				registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString()))){
						
			//EXTRATO
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasExtrato_COSAMA_LEGADO(
					registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
					idFormaArrecadacao);
			
			System.out.println("EXTRATO LEGADO");
						
		}
		else if (tipoPagamento.equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
				registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()))){
						
			//GUIA DE PAGAMENTO
			pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasGuiaPagamento_COSAMA_LEGADO(
					registroHelperCodigoBarras, sistemaParametro,dataPagamento, anoMesPagamento, valorPagamento,
					idFormaArrecadacao);
			
			System.out.println("GUIA DE PAGAMENTO LEGADO");
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
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * LEGADO CONTA
	 * 
	 * @autor: Raphael Rossiter 
	 * @data: 20/05/2010
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasConta_COSAMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();

		boolean matriculaImovelInvalida = false;

		int anoMes = 0;
		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;

		boolean anoMesReferencia = false;

		String idImovel = registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1();
		
		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(idImovel);

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {

			/*
			 * Verifica se existe a matricula do imóvel na base
			 */
			matriculaImovel = new Integer(idImovel);

			idImovelNaBase = null;

			try {
				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(matriculaImovel);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}

			/*
			 * Se o id do imovel pesquisado na base for diferente de nulo
			 */
			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		anoMesReferencia = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (!anoMesReferencia) {

			// valida o ano mes de referencia da conta
			anoMes = Util.formatarMesAnoParaAnoMes(Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()));

			anoMesReferencia = Util.validarAnoMesSemBarra("" + anoMes);

			if (anoMesReferencia) {
				descricaoOcorrencia = "ANO/MÊS DE REFERÊNCIA DA CONTA INVÁLIDA";
			}

		} else {
			descricaoOcorrencia = "ANO/MÊS DE REFERÊNCIA DA CONTA INVÁLIDA";
		}

		if (descricaoOcorrencia.equals("OK")) {

			Integer idLocalidade = null;

			Integer idConta = null;

			// Valida o amo mes de referencia da conta
			int anoMesReferenciaInt = Util.formatarMesAnoParaAnoMes(Integer
					.parseInt(registroHelperCodigoBarras
							.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2()));

			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);

			try {
				idConta = repositorioFaturamento
						.pesquisarExistenciaContaComSituacaoAtual(imovel,
								anoMesReferenciaInt);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}
			
			/*
             * Alterado por Raphael Rossiter em 09/01/2008 - Analistas: Eduardo e Aryed
             * OBJ: Gerar os pagamentos associados com a localidade da conta e NÃO com
             * a localidade do imóvel.
             */
			if (idConta == null || idConta.equals("")) {
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
			pagamento.setAnoMesReferenciaPagamento(anoMes);

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
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_CONTA);
			pagamento.setDocumentoTipo(documentoTipo);
			pagamento.setAvisoBancario(null);

			if (idImovelNaBase != null) {
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
			
			/*
			 * Alteracao referente ao relatorio de Float - Francisco : 14/07/08
			 */
			pagamento.setFatura(null);
			pagamento.setCobrancaDocumento(null);
			
			DocumentoTipo documentoAgregador = new DocumentoTipo();
			documentoAgregador.setId(DocumentoTipo.CONTA);
			pagamento.setDocumentoTipoAgregador(documentoAgregador);
			
			pagamento.setDataProcessamento(new Date());

			colecaoPagamentos.add(pagamento);

		} else {
			// atribui o valor 2(NÃO) ao indicador aceitacao
			// registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 *
	 * @author Raphael Rossiter
	 * @date 20/05/2011
	 *
	 * @param registroHelperCodigoG
	 * @param arrecadadorMovimentoItemHelper
	 * @throws ControladorException
	 */
	public void distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(RegistroHelperCodigoG registroHelperCodigoG,
			ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper) throws ControladorException {
		
		//LEGADO - COSAMA
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
			registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
			ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString()))){
			
			//CONTA
			String identificacao = registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1();
				
			arrecadadorMovimentoItemHelper.setIdentificacao(identificacao);
			arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA_LEGADO);
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString()))){
						
			//EXTRATO
			String identificacao = registroHelperCodigoG
				.getRegistroHelperCodigoBarras()
				.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento1();
				
			arrecadadorMovimentoItemHelper.setIdentificacao(identificacao);
			arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_EXTRATO_LEGADO);
						
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()))){
						
			//GUIA DE PAGAMENTO
			String identificacao = registroHelperCodigoG
			.getRegistroHelperCodigoBarras()
			.getRegistroHelperCodigoBarrasTipoPagamento()
			.getIdPagamento1();
			
			arrecadadorMovimentoItemHelper.setIdentificacao(identificacao);
			arrecadadorMovimentoItemHelper.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO);
		}
		else{
			
			super.distribuirDadosRegistroMovimentoArrecadadorPorTipoPagamento(registroHelperCodigoG,
					arrecadadorMovimentoItemHelper);
		}
	}
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema captura os dados referentes ao conteúdo do do código de barras
	 * 
	 * [SF0003] Apresentar Dados do Conteúdo do Código de Barras
	 * 
	 * @author Raphael Rossiter
	 * @data 20/05/2011
	 * 
	 * @param registroHelperCodigoG
	 * @return DadosConteudoCodigoBarrasHelper
	 */
	public DadosConteudoCodigoBarrasHelper apresentarDadosConteudoCodigoBarras(
			RegistroHelperCodigoG registroHelperCodigoG)
			throws ControladorException {

		DadosConteudoCodigoBarrasHelper retorno = new DadosConteudoCodigoBarrasHelper();
		
		
		//LEGADO - COSAMA
		//===============================================================================================================================
		if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
			.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_CONTA_LEGADO_COSAMA.toString()) &&
			(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
			registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
			ConstantesSistema.CODIGO_TIPO_DOCUMENTO_CONTA_LEGADO_COSAMA.toString()))){
			
			//CONTA - LEGADO
			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_CONTA_LEGADO);
			
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_EXTRATO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento6().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_EXTRATO_LEGADO_COSAMA.toString()))){
						
			//EXTRATO - LEGADO
			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_EXTRATO_LEGADO);
						
		}
		else if (registroHelperCodigoG.getRegistroHelperCodigoBarras()
				.getTipoPagamento().equals(ConstantesSistema.CODIGO_TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()) &&
				(registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4() != null &&
				registroHelperCodigoG.getRegistroHelperCodigoBarras().getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals(
				ConstantesSistema.CODIGO_TIPO_DOCUMENTO_GUIA_PAGAMENTO_LEGADO_COSAMA.toString()))){
						
			//GUIA DE PAGAMENTO - LEGADO
			retorno.setTipoPagamento(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO_LEGADO);
			
		}
		else{
			
			//GERADO PELO GSAN
			retorno = super.apresentarDadosConteudoCodigoBarras(registroHelperCodigoG);
		}
		

		return retorno;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras - LEGADO
	 *
	 * @author Raphael Rossiter
	 * @date 03/06/2011
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
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasGuiaPagamento_COSAMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();

		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentos = new ArrayList();
		
		boolean idLocalidadeInvalida = false;
		boolean matriculaImovelInvalida = false;

		Integer idImovelNaBase = null;
		Integer matriculaImovel = null;
		
		Integer idLocalidade = null;
		Integer idDebitoTipoNaBase = null;

		idLocalidadeInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento2());

		if (idLocalidadeInvalida) {
			descricaoOcorrencia = "CÓDIGO DA LOCALIDADE NÃO NUMÉRICA";
		}
		
		idLocalidade = new Integer(registroHelperCodigoBarras
				.getRegistroHelperCodigoBarrasTipoPagamento()
				.getIdPagamento2());

		matriculaImovelInvalida = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento1());

		if (matriculaImovelInvalida) {
			descricaoOcorrencia = "MÁTRICULA DO IMÓVEL INVÁLIDA";
		} else {

			// Verifica se existe a matricula do imóvel na base
			matriculaImovel = new Integer(registroHelperCodigoBarras
					.getRegistroHelperCodigoBarrasTipoPagamento()
					.getIdPagamento1());

			try {
				idImovelNaBase = repositorioImovel
						.recuperarMatriculaImovel(new Integer(matriculaImovel));
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
				throw new ControladorException("erro.sistema", e);
			}

			if (idImovelNaBase == null) {
				descricaoOcorrencia = "MATRÍCULA DO IMÓVEL NÃO CADASTRADA";
			}
		}

		boolean codigoTipoDebito = Util
				.validarValorNaoNumerico(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento3());

		
		
		if (codigoTipoDebito) {
			descricaoOcorrencia = "TIPO DO DÉBITO NÃO NUMÉRICO";
		} else {

			idDebitoTipoNaBase = getControladorFaturamento()
					.verificarExistenciaDebitoTipo(
							Util
									.converterStringParaInteger(registroHelperCodigoBarras
											.getRegistroHelperCodigoBarrasTipoPagamento()
											.getIdPagamento3()));

			if (idDebitoTipoNaBase == null) {
				descricaoOcorrencia = "TIPO DO DÉBITO INEXISTENTE";
			}
		}

		if (descricaoOcorrencia.equals("OK")) {

			// GERAÇÃO DO PAGAMENTO
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
			
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(idDebitoTipoNaBase);
			pagamento.setDebitoTipo(debitoTipo);

			pagamento.setContaGeral(null);
			
			pagamento.setGuiaPagamento(null);
			
			Localidade localidade = new Localidade();
			localidade.setId(idLocalidade);
			pagamento.setLocalidade(localidade);
			
			DocumentoTipo documentoTipo = new DocumentoTipo();
			if (debitoTipo.getId().equals(DebitoTipo.ENTRADA_PARCELAMENTO)){
				documentoTipo.setId(DocumentoTipo.ENTRADA_DE_PARCELAMENTO);	
			} else {
				documentoTipo.setId(DocumentoTipo.GUIA_PAGAMENTO);
			}
			documentoTipo.setDescricaoDocumentoTipo(ConstantesSistema.TIPO_PAGAMENTO_GUIA_PAGAMENTO);
			pagamento.setDocumentoTipo(documentoTipo);

			pagamento.setAvisoBancario(null);
			
			Imovel imovel = new Imovel();
			imovel.setId(idImovelNaBase);
			pagamento.setImovel(imovel);			

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
			
			colecaoPagamentos.add(pagamento);

		} 
		else {
			
			// Atribui o valor 2(NÃO) ao indicador aceitacao registro
			indicadorAceitacaoRegistro = "2";
		}

		// Seta os parametros que serão retornados
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentos);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * EXTRATO LEGADO
	 * 
	 * @autor: Raphael Rossiter 
	 * @data: 03/06/2010
	 */
	protected PagamentoHelperCodigoBarras processarPagamentosCodigoBarrasExtrato_COSAMA_LEGADO(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento,
			Integer anoMesPagamento, BigDecimal valorPagamento,
			Integer idFormaArrecadacao) throws ControladorException {

		PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = new PagamentoHelperCodigoBarras();
		
		String descricaoOcorrencia = "OK";

		String indicadorAceitacaoRegistro = "1";

		Collection colecaoPagamentosRetorno = new ArrayList();
		
		RegistroHelperCodigoBarras codigoBarras = new RegistroHelperCodigoBarras();
		codigoBarras.setIdProduto(registroHelperCodigoBarras.getIdProduto());
		codigoBarras.setIdSegmento(registroHelperCodigoBarras.getIdSegmento());
		codigoBarras.setIdValorReal(registroHelperCodigoBarras.getIdValorReal());
		codigoBarras.setDigitoVerificadorGeral(registroHelperCodigoBarras.getDigitoVerificadorGeral());
		codigoBarras.setIdEmpresa(registroHelperCodigoBarras.getIdEmpresa());
		
		
		for (int i = 1; i <= 4; i++) {
			
			switch (i) {
			case 1:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento2().equals("002000")){
					
					//CONTA 01
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento2(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;

			case 2:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento3().equals("002000")){
					
					//CONTA 02
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento3(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;
				
			case 3:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento4().equals("002000")){
					
					//CONTA 03
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento4(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;
				
			case 4:
				
				if (!registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento().getIdPagamento5().equals("002000")){
					
					//CONTA 04
					Pagamento pagamentoGerado = this.processarPagamentosCodigoBarrasExtratoPorConta(codigoBarras, 
							sistemaParametro, dataPagamento, registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento1(), registroHelperCodigoBarras.getRegistroHelperCodigoBarrasTipoPagamento()
							.getIdPagamento5(), anoMesPagamento, idFormaArrecadacao);
					
					if (pagamentoGerado != null){
						
						colecaoPagamentosRetorno.add(pagamentoGerado);
					}
				}
				
				break;
			default:
				break;
			}
		}

		if (colecaoPagamentosRetorno == null || colecaoPagamentosRetorno.isEmpty()){
			
			descricaoOcorrencia = "EXTRATO INEXISTENTE";
			indicadorAceitacaoRegistro = "2";
		}
		
		pagamentoHelperCodigoBarras.setColecaoPagamentos(colecaoPagamentosRetorno);
		pagamentoHelperCodigoBarras.setDescricaoOcorrencia(descricaoOcorrencia);
		pagamentoHelperCodigoBarras.setIndicadorAceitacaoRegistro(indicadorAceitacaoRegistro);

		return pagamentoHelperCodigoBarras;
	}
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * EXTRATO LEGADO
	 * 
	 * @autor: Raphael Rossiter 
	 * @data: 03/06/2010
	 */
	protected Pagamento processarPagamentosCodigoBarrasExtratoPorConta(
			RegistroHelperCodigoBarras registroHelperCodigoBarras,
			SistemaParametro sistemaParametro, Date dataPagamento, String idImovel, String anoMesReferenciaConta,
			Integer anoMesPagamento, Integer idFormaArrecadacao) throws ControladorException {
		
		Pagamento pagamentoGerado = null;
		
		/*
		 * FORMATANDO A IDENTIFICAÇÃO DO PAGAMENTO PARA PROCESSAR CONTA POR CONTA
		 * (imovel + referencia + digito verificador + filler
		 */ 
		String idPagamento = idImovel + anoMesReferenciaConta + "1000000000";
		
		//DISTRIBUINDO O CÓDIGO DE BARRAS NO FORMATO DE CONTA LEGADO
		RegistroHelperCodigoBarrasTipoPagamento codigoBarrasTipoPagamento = 
		this.distribuirDadosCodigoBarrasPorTipoPagamento(idPagamento, "1", null);
		
		//ATRIBUINDO O NOVO FORMATO
		registroHelperCodigoBarras.setRegistroHelperCodigoBarrasTipoPagamento(codigoBarrasTipoPagamento);
		
		//PESQUISANDO O VALOR DA CONTA QUE SERÁ ATRIBUIDO AO VALOR DO PAGAMENTO
		BigDecimal valorPagamentoConta = null;
		
		try {
			
			valorPagamentoConta = repositorioFaturamento.pesquisarValorContaComSituacaoAtual(Integer.parseInt(registroHelperCodigoBarras
						.getRegistroHelperCodigoBarrasTipoPagamento()
						.getIdPagamento1()), Util.formatarMesAnoParaAnoMes(Integer.parseInt(anoMesReferenciaConta)));

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		
		//CASO O VALOR DA CONTA NÃO SEJA IDENTIFICADO O PAGAMENTO NÃO SERÁ GERADO
		if (valorPagamentoConta != null){
			
			//PROCESSANDO O PAGAMENTO POR CONTA
			PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = this.processarPagamentosCodigoBarrasConta_COSAMA_LEGADO(
					registroHelperCodigoBarras, sistemaParametro, dataPagamento,
					anoMesPagamento, valorPagamentoConta, idFormaArrecadacao);
			
			if (pagamentoHelperCodigoBarras.getColecaoPagamentos() != null &&
				!pagamentoHelperCodigoBarras.getColecaoPagamentos().isEmpty()){
				
				pagamentoGerado = (Pagamento) Util.retonarObjetoDeColecao(pagamentoHelperCodigoBarras.getColecaoPagamentos());
			}
		}
		
		
		return pagamentoGerado;
	}

}
