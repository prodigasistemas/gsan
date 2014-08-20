package gcom.financeiro;

import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import javax.ejb.SessionBean;

/**
 * Controlador Financeiro COSANPA
 *
 * @author Raphael Rossiter
 * @date 24/04/2009
 */
public class ControladorFinanceiroCOSANPASEJB extends ControladorFinanceiro implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA COSANPA
	//==============================================================================================================

	/**
	 * este caso de uso gera a integra��o para a contabilidade
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Pedro Alexandre
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	public void gerarIntegracaoContabilidade(
			String idLancamentoOrigem, String anoMes, String data) throws ControladorException {
		
		/*
		 * Pesquisa os dados para gerar a integra��o para a contabilidade.
		 * 
		 * 0 - cnct_nnconta
		 * 1 - loca_cdcentrocusto
		 * 2 - lcti_icdebitocredito
		 * 3 - lcti_vllancamento
		 * 4 - lcor_cdreferencia
		 */
		Collection<Object[]> colecaoDadosGerarIntegracao = null;
		
		try {
			colecaoDadosGerarIntegracao = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeCOSANPA(
			idLancamentoOrigem, anoMes);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		StringBuilder gerarIntegracaoTxt = new StringBuilder();
		Date dataInformada = Util.converteStringParaDate(data);
		
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
			int sequencialLancamento = 1;
			
			String numeroConta = "";
			String codigoCentroCusto = "";
			Short indicadorDebitoCredito = null;
			BigDecimal valorLancamento = new BigDecimal("0.00");
			String prefixoContabil = "";
			String nomeConta = "";
			String nomeLote = "";
			String descricaoLancamento = "";
			Date dataLancamento = null;
			String descricaoHistorico = "";
			Integer codigoTerceiro = null;
			Short indicadorCentroCusto = null;
			
			// Determina se o arquivo � de faturamento ou arrecada��o 
			int mesLancamento = 0;
			Integer anoMesInteger = Integer.valueOf(anoMes);
			
			if (idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")) {
				descricaoLancamento = "FATURAMENTO";
				nomeLote = "FT" + Util.formatarAnoMesParaMesAnoCom2Digitos(anoMesInteger);
				mesLancamento = Util.obterMes(anoMesInteger);
			} else if (idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")) {
				nomeLote = "AD" + Util.formatarAnoMesParaMesAnoCom2Digitos(anoMesInteger);
				descricaoLancamento = "ARRECADACAO";
				mesLancamento = Util.obterMes(anoMesInteger);
			} else if(idLancamentoOrigem.equals(LancamentoOrigem.AVISO_BANCARIO + "")) {
				nomeLote = "AB" + Util.formatarAnoMesParaMesAnoCom2Digitos(anoMesInteger);
				descricaoLancamento = "AVISO_BANCARIO";
				mesLancamento = Util.obterMes(anoMesInteger);
			}
			
			// La�o para gerar o txt 
			for(Object[] dadosGerarIntegracao : colecaoDadosGerarIntegracao) {
				//CONTA CONTABIL - NUMERO CONTA
				if (dadosGerarIntegracao[0] != null) {
					numeroConta = (String) dadosGerarIntegracao[0];
				} else {
					numeroConta = "";
				}

				// LOCALIDADE - CODIGO DO CENTRO DE CUSTO
				if (dadosGerarIntegracao[1] != null) {
					codigoCentroCusto = (String) dadosGerarIntegracao[1];
				} else {
					codigoCentroCusto = "";
				}

				// LANCAMENTO CONTABIL ITEM - INDICADOR DEBITO CREDTIO
				if (dadosGerarIntegracao[2] != null) {
					indicadorDebitoCredito = (Short) dadosGerarIntegracao[2];
				}

				// LANCAMENTO CONTABIL ITEM - VALOR LANCAMENTO
				if (dadosGerarIntegracao[4] != null) {
					valorLancamento = (BigDecimal) dadosGerarIntegracao[4];
				} else {
					valorLancamento = new BigDecimal("0.00");
				}

				// CONTA CONTABIL - PREFIXO CONTABIL
				if (dadosGerarIntegracao[5] != null) {
					prefixoContabil = (String) dadosGerarIntegracao[5];
				} else {
					prefixoContabil = "";
				}

				// CONTA CONTABIL - NOME CONTA
				if (dadosGerarIntegracao[6] != null) {
					nomeConta = (String) dadosGerarIntegracao[6];
				} else {
					nomeConta = "";
				}

				// DATA CONTABILIZA��O - DATA DO LAN�AMENTO
				if (dadosGerarIntegracao[7] != null) {
					dataLancamento = (Date) dadosGerarIntegracao[7];
				} else {
					dataLancamento = null;
				}

				// DESCRITIVO DO HISTORICO PADRAO 2 - DESCRI��O HISTORICO
				if (dadosGerarIntegracao[8] != null) {
					descricaoHistorico = (String) dadosGerarIntegracao[8];
				} else {
					descricaoHistorico = "";
				}

				// SUBCONTA - C�DIGO TERCEIRO
				if (dadosGerarIntegracao[9] != null) {
					codigoTerceiro = (Integer) dadosGerarIntegracao[9];
				} else {
					codigoTerceiro = null;
				}

				// CONTA CONTABIL - INDICADOR CENTRO DE CUSTO
				if (dadosGerarIntegracao[10] != null) {
					indicadorCentroCusto = (Short) dadosGerarIntegracao[10];
				} else {
					indicadorCentroCusto = null;
				}
				
				// 1 - C�DIGO COMPANHIA
				gerarIntegracaoTxt.append("130");

				// 2 - PLANO DE CONTAS
				gerarIntegracaoTxt.append(" ");

				/*
				 * 3 - NOME DO LOTE
				 * 
				 * Caso a origem do lan�amento (LCOR_ID) corresponda a
				 * FATURAMENTO, concatenar FT com o m�s e ano do faturamento no
				 * formato MMAA (PARM_AMREFERENCIAFATURAMENTO da tabela
				 * SISTEMA_PARAMETROS.
				 * 
				 * Caso a origem do lan�amento (LCOR_ID) corresponda a
				 * ARRECADACAO, concatenar AD com o m�s e ano da arrecada��o no
				 * formato MMAA (PARM_AMREFERENCIAARRECADACAO da tabela
				 * SISTEMA_PARAMETROS.
				 */
				gerarIntegracaoTxt.append(nomeLote);

				// 4 - SEQU�NCIA DO LAN�AMENTO
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(6, "" + sequencialLancamento));

				/*
				 * 5 - M�S DO LAN�AMENTO Preencher com o m�s (MM) do campo 3
				 */
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(2, "" + mesLancamento));

				// 6 - ORIGEM TRANSA��O
				gerarIntegracaoTxt.append("02");

				/*
				 * 7 - DATA DA CONTABILIZA��O
				 * 
				 * Preencher com LCTI_DTLANCAMENTO da tabela
				 * LANCAMENTO_CONTABIL_ITEM caso seja diferente de nulo, caso
				 * contr�rio preencher com a Data do Lan�amento informada no
				 * in�cio do caso de uso no formato AAAAMMDD.
				 */
				if (dataLancamento != null) {
					gerarIntegracaoTxt.append(Util.formatarDataAAAAMMDD(dataLancamento));
				} else {
					gerarIntegracaoTxt.append(Util.formatarDataAAAAMMDD(dataInformada));
				}

				/*
				 * 8 - PER�ODO DA TRANSA��O Preencher com o m�s (MM) do campo 3
				 */
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(2, "" + mesLancamento));

				// 9 - C�DIGO DA COMPANHIA DA TRANSA��O
				gerarIntegracaoTxt.append("130");

				// 10 - PLANO DE CONTAS DA TRANSA��O
				gerarIntegracaoTxt.append(" ");

				/*
				 * 11 - CONTA CONT�BIL
				 * 
				 * CNCT_NNCONTA da tabela CONTA_CONTABIL para CNCT_ID = CNCT_ID
				 * da tabela LANCAMENTO_CONTABIL_ITEM
				 */
				if (numeroConta.length() >= 9) {
					gerarIntegracaoTxt.append(Util.completaString(numeroConta.substring(0, 9), 16));
				} else {
					gerarIntegracaoTxt.append(Util.completaString(numeroConta, 16));
				}

				/*
				 * 12 - UNIDADE DE NEG�CIO
				 * 
				 * Caso CNCT_ICCUSTO da tabela CONTA_CONTABIL para CNCT_ID =
				 * CNCT_ID da tabela LANCAMENTO_CONTABIL_ITEM corresponda a n�o
				 * (2) preencher fixo 10000, caso contr�rio, cinco primeiros
				 * caracteres da coluna LOCA_CDCENTROCUSTO da tabela LOCALIDADE
				 * para LOCA_ID = LOCA_ID da tabela LANCAMENTO_CONTABIL
				 */
				if (indicadorCentroCusto != null && indicadorCentroCusto.equals(ConstantesSistema.NAO)) {
					gerarIntegracaoTxt.append("10000");
				} else if (codigoCentroCusto.length() >= 5) {
					gerarIntegracaoTxt.append(codigoCentroCusto.substring(0, 5));
				} else {
					gerarIntegracaoTxt.append(Util.completaString("", 5));
				}

				/*
				 * 13 - CENTRO DE RESPONSABILIDADE
				 * 
				 * Caso CNCT_ICCUSTO da tabela CONTA_CONTABIL para CNCT_ID =
				 * CNCT_ID da tabela LANCAMENTO_CONTABIL_ITEM corresponda a n�o
				 * (2) preencher fixo 0000, caso contr�rio, quatro caracteres a
				 * partir da sexta posi��o da coluna LOCA_CDCENTROCUSTO da
				 * tabela LOCALIDADE para LOCA_ID = LOCA_ID da tabela
				 * LANCAMENTO_CONTABIL
				 */
				if (indicadorCentroCusto != null && indicadorCentroCusto.equals(ConstantesSistema.NAO)) {
					gerarIntegracaoTxt.append("0000");
				} else if (codigoCentroCusto.length() >= 9) {
					gerarIntegracaoTxt.append(codigoCentroCusto.substring(5, 9));
				} else {
					gerarIntegracaoTxt.append(Util.completaString("", 4));
				}

				// 14 - PRODUTO E SERVI�O
				gerarIntegracaoTxt.append(Util.completaString(prefixoContabil, 3));
				
				/*
				 *15 - INDICADOR DEB/CRED
				 */
				if (indicadorDebitoCredito.equals(ConstantesSistema.SIM)) {
					gerarIntegracaoTxt.append("C");
				} else {
					gerarIntegracaoTxt.append("D");
				}
				
				/*
				 *16 - HISTORICO PADR�O
				 * 
				 * LCOR_CDREFERENCIA da tabela LANCAMENTO_ORIGEM
				 */
				gerarIntegracaoTxt.append(Util.completaString("", 3));
				
				/*
				 * 17 - DESCRITIVO DO HISTORICO PADRAO 1
				 * 
				 * Caso a origem do lan�amento  (LCOR_ID) corresponda a AVISO BANC�RIO, LCTI_DSHISTORICO da tabela 
				 * LANCAMENTO_CONTABIL_ITEM, caso contr�rio CNCT_NMCONTA da tabela CONTA_CONTABIL para 
				 * CNCT_ID = CNCT_ID da tabela 
				 */
				if (idLancamentoOrigem.equals(LancamentoOrigem.AVISO_BANCARIO + "")) {
					gerarIntegracaoTxt.append(Util.completaString(descricaoHistorico, 50));
				} else {
					gerarIntegracaoTxt.append(Util.completaString(nomeConta, 50));
				}
				
				/*
				 * 18 - DESCRITIVO DO HISTORICO PADRAO 2
				 * 
				 * N�O PREENCHER
				 */
				gerarIntegracaoTxt.append(Util.completaString("", 50));
				
				/*
				 *19 - VALOR DO LANCAMENTO
				 * 
				 * LCTI_VLLANCAMENTO da tabela LANCAMENTO_CONTABIL_ITEM, completar com zeros a esquerda
				 */
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(15,(valorLancamento + "").replace(".","")));
				
				//20 - CLASSE DE VALOR
				gerarIntegracaoTxt.append("SAC");
				
				//21 - C�DIGO DE LAN�AMENTO DA FATURA
				gerarIntegracaoTxt.append(Util.completaString(prefixoContabil, 6));
				
				/*
				 *22 - GRUPO DE SUBCONTA
				 * 
				 * Dois caracteres a partir da d�cima posi��o da coluna CNCT_NNCONTA da tabela 
				 * CONTA_CONTABIL para CNCT_ID = CNCT_ID da tabela 
				 */
				if (numeroConta.length() >= 11) {
					gerarIntegracaoTxt.append(numeroConta.substring(9, 11));
				} else {

					gerarIntegracaoTxt.append(Util.completaString("", 2));
				}
				
				/*
				 *23 - SUBCONTA
				 * 
				 * Caso a origem do lan�amento  (LCOR_ID) corresponda a AVISO BANC�RIO LCTI_NNCODIGOTERCEIRO 
				 * da tabela LANCAMENTO_CONTABIL_ITEM, caso contr�rio seis caracteres a partir da d�cima segunda 
				 * posi��o da coluna CNCT_NNCONTA da tabela CONTA_CONTABIL para CNCT_ID = CNCT_ID da tabela 
				 * LANCAMENTO_CONTABIL_ITEM
				 */
				if (idLancamentoOrigem.equals(LancamentoOrigem.AVISO_BANCARIO + "")) {
					if (codigoTerceiro != null) {
						gerarIntegracaoTxt.append(Util.completaString(codigoTerceiro.toString(), 12));
					} else {
						gerarIntegracaoTxt.append(Util.completaString("", 12));
					}
				} else if (numeroConta.length() >= 17) {
					gerarIntegracaoTxt.append(Util.completaString(numeroConta.substring(11, 17), 12));
				} else {
					gerarIntegracaoTxt.append(Util.completaString("", 12));
				}
				
				//24 - CRIT�RIO DE RATEIO
				gerarIntegracaoTxt.append(Util.completaString("", 3));
				
				//25 - VALOR RATEIO
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(15, ""));
				
				//26 - CLASSE RATEIO
				gerarIntegracaoTxt.append(Util.completaString("", 3));
				
				//27 - CAMPO DE USUARIO
				gerarIntegracaoTxt.append(Util.completaString("", 20));
				
				//28 - SENHA DE PARTIDA
				gerarIntegracaoTxt.append(Util.completaString("", 8));
				
				//29 - INDICADOR DE VALIDA��O
				gerarIntegracaoTxt.append("1");
				
				//30 - INDICADOR DE TOTAL DE LOTE
				gerarIntegracaoTxt.append("1");
				
				//31 - TIPO DE ENTRADA
				gerarIntegracaoTxt.append(" ");
				
				//32 - C�DIGO DO SISTEMA
				gerarIntegracaoTxt.append(Util.completaString("", 8));
				
				//33 - DATA DE INCLUS�O-ALTERA��O
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8, ""));
				
				//34 - HORA DE INCLUS�O-ALTERA��O
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(7, ""));
				
				//35 - COMPANHIA CONTRA PARTIDA
				gerarIntegracaoTxt.append(Util.completaString("", 3));
				
				//36 - PLANO DE CONTAS CONTRA PARTIDA
				gerarIntegracaoTxt.append(" ");
				
				//37 - CONTA CONT�BIL CONTRA PARTIDA
				gerarIntegracaoTxt.append(Util.completaString("", 16));
				
				//38 - CENTRO CONT�BIL CONTRA PARTIDA
				gerarIntegracaoTxt.append(Util.completaString("", 12));
				
				//39 - GRUPO DE SUBCONTA CONTRA PARTIDA
				gerarIntegracaoTxt.append(Util.completaString("", 2));
				
				//40 - SUBCONTA CONTRA PARTIDA
				gerarIntegracaoTxt.append(Util.completaString("", 12));
				
				//41 - SENHA DA CONTA
				gerarIntegracaoTxt.append(Util.completaString("", 8));
				
				//42 - INDICADOR DE INTERRUP��O
				gerarIntegracaoTxt.append(" ");
				
				//43 - INDICADOR DE STATUS DE REGISTRO
				gerarIntegracaoTxt.append("0");
				
				//44 - INDICADOR DE ENTRADA DUPLICADA
				gerarIntegracaoTxt.append(" ");
				
				//45 - DATA DE REVERS�O 1
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8, ""));
				
				//46 - DATA DE REVERS�O 2
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(8, ""));
				
				//47 - INDICADOR DE REVERS�O
				gerarIntegracaoTxt.append("0");
				
				//48 - SINAL DE TRANSA��O 1
				gerarIntegracaoTxt.append(" ");
				
				//49 - SINAL DE TRANSA��O 2
				gerarIntegracaoTxt.append(" ");
				
				//50 - INDICADOR DE CONVERS�O DE TRANSA��O
				gerarIntegracaoTxt.append(" ");
				
				gerarIntegracaoTxt.append(System.getProperty("line.separator"));
				
				//Iniciar com o valor 1 e incrementar com 1 a cada registro gerado.
				sequencialLancamento++;
			}
			
			//Gerando o arquivo zip
			String nomeZip = "CONTABILIDADE_" + descricaoLancamento + "_" + (data.replace("/","_"));
			BufferedWriter out = null;
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeZip + ".zip");
			File leituraTipo = new File(nomeZip + ".txt");

			if (gerarIntegracaoTxt != null && gerarIntegracaoTxt.length() != 0) {
				try {
					System.out.println("CRIANDO ZIP");
					zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
					out.write(gerarIntegracaoTxt.toString());
					out.flush();
					ZipUtil.adicionarArquivo(zos, leituraTipo);
					zos.close();
					leituraTipo.delete();
					out.close();
				} catch (IOException ex) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", ex);
				} catch (Exception e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
			}
		} else{
			if (idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")) {
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null, "Resumo Faturamento");
			} else if (idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")) {
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null, "Resumo Arrecada��o");
			}
		}
	}
}
