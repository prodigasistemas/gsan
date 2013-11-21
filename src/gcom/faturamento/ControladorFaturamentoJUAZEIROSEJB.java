/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/
package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.conta.Conta;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.ejb.SessionBean;

/**
 * Controlador Faturamento Juazeiro
 *
 * @author Rafael Corrêa
 * @date 30/06/2009
 */
public class ControladorFaturamentoJUAZEIROSEJB extends ControladorFaturamento implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DE JUAZEIRO
	//==============================================================================================================
	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 15/09/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContas(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,
			Short contaSemCodigoBarras) throws ControladorException {

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = new ArrayList();

		Iterator iter = idsContaEP.iterator();

		while (iter.hasNext()) {
			Integer idContaEP = (Integer) iter.next();

			Collection colectionConta;
			try {
				colectionConta = this.repositorioFaturamento.pesquisarConta(idContaEP);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) colectionConta.iterator().next();

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			String nomeCliente = "";
			if (emitirContaHelper.getNomeImovel() != null && !emitirContaHelper.getNomeImovel().trim().equals("")) {

				nomeCliente = emitirContaHelper.getNomeImovel();
				emitirContaHelper.setNomeCliente(nomeCliente);
			}

			// Linha 5
			// --------------------------------------------------------------
			// recupera endereco do imóvel
			String enderecoImovel = "";
			try {
				enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatado(emitirContaHelper.getIdImovel());
			} catch (ControladorException e1) {
				e1.printStackTrace();
			}
			emitirContaHelper.setEnderecoImovel(enderecoImovel);

			// Linha 6
			// --------------------------------------------------------------
			// instância um imovel com os dados da conta para recuperar a
			// inscrição que está no objeto imovel
			Imovel imovel = new Imovel();
			Localidade localidade = new Localidade();
			localidade.setId(emitirContaHelper.getIdLocalidade());
			imovel.setLocalidade(localidade);
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setCodigo(emitirContaHelper.getCodigoSetorComercialConta());
			imovel.setSetorComercial(setorComercial);
			Quadra quadra = new Quadra();
			quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());
			imovel.setQuadra(quadra);
			imovel.setLote(emitirContaHelper.getLoteConta());
			imovel.setSubLote(emitirContaHelper.getSubLoteConta());
			// Inscrição do imóvel
			emitirContaHelper.setInscricaoImovel(imovel.getInscricaoFormatada());

			// Linha 7
			// --------------------------------------------------------------
			String idClienteResponsavel = "";
			String enderecoClienteResponsavel = "";
			Integer idImovelContaEnvio = emitirContaHelper.getIdImovelContaEnvio();
			// caso a coleção de contas seja de entrega para o cliente responsável
			if (idImovelContaEnvio != null
				&& (idImovelContaEnvio.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) 
					|| idImovelContaEnvio.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {

				Integer idClienteResponsavelInteger = null;
				idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(
						emitirContaHelper.getIdConta(), false);

				if (idClienteResponsavelInteger != null && !idClienteResponsavelInteger.equals("")) {
					idClienteResponsavel = idClienteResponsavelInteger.toString();
					// [UC0085]Obter Endereco
					enderecoClienteResponsavel = getControladorEndereco()
						.pesquisarEnderecoClienteAbreviado(idClienteResponsavelInteger);
				}

			}
			emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
			emitirContaHelper.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

			// Linha 8
			// --------------------------------------------------------------

			// [SB0002] - Determinar tipo de ligação e tipo de Medição
			Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
			Integer tipoLigacao = parmSituacao[0];
			Integer tipoMedicao = parmSituacao[1];

			// Linha 9
			// --------------------------------------------------------------
			// cria uma stringBuilder para recuperar o resultado do [SB0003]
			// o tamanho da string que vem como resultado é de 20 posições
			StringBuilder obterDadosConsumoMedicaoAnterior = null;

			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 1
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), 
				emitirContaHelper.getAmReferencia(), 1, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes1(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 4
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 4, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes4(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 10
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 2
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 2, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes2(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 5
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 5, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes5(obterDadosConsumoMedicaoAnterior.toString());
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros da medição historico do
			// [SB0004] - Obter Dados da Medição da Conta
			Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
			// Leitura Anterior
			String leituraAnterior = "";
			// Leitura Atual
			String leituraAtual = "";
			// Data Leitura Anterior
			String dataLeituraAnterior = "";
			// Leitura Anterior
			String dataLeituraAtual = "";
			// Leitura Situação Atual
			// String leituraSituacaoAtual = "";
			// Leitura Anormalidade Faturamento
			String leituraAnormalidadeFaturamento = "";
			if (parmsMedicaoHistorico != null) {

				if (parmsMedicaoHistorico[0] != null) {
					leituraAnterior = "" + (Integer) parmsMedicaoHistorico[0];
				}

				if (parmsMedicaoHistorico[1] != null) {
					leituraAtual = "" + (Integer) parmsMedicaoHistorico[1];
				}

				if (parmsMedicaoHistorico[3] != null) {
					dataLeituraAnterior = Util.formatarData((Date) parmsMedicaoHistorico[3]);
				}

				if (parmsMedicaoHistorico[2] != null) {
					dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
				}

				if (parmsMedicaoHistorico[4] != null) {
					// leituraSituacaoAtual = ""
					// + (Integer) parmsMedicaoHistorico[4];
				}

				if (parmsMedicaoHistorico[5] != null) {
					leituraAnormalidadeFaturamento = ""	+ (Integer) parmsMedicaoHistorico[5];
				}
			}
			emitirContaHelper.setDataLeituraAnterior(dataLeituraAnterior);
			emitirContaHelper.setDataLeituraAtual(dataLeituraAtual);
			String diasConsumo = "";
			if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
				// calcula a quantidade de dias de consumo que é a
				// quantidade de dias
				// entre a data de leitura
				// anterior(parmsMedicaoHistorico[2]) e a data de leitura
				// atual(parmsMedicaoHistorico[3])
				diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas(
						(Date) parmsMedicaoHistorico[3],(Date) parmsMedicaoHistorico[2]);
			}
			// recupera os parametros de consumo faturamento e consumo médio diário
			// [SB0005] - Obter Consumo Faturado e Consumo Médio Diário
			String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
					emitirContaHelper, tipoMedicao, diasConsumo);
			String consumoFaturamento = parmsConsumo[0];
			emitirContaHelper.setConsumoFaturamento(consumoFaturamento);

			String consumoMedioDiario = parmsConsumo[1];
			emitirContaHelper.setConsumoMedioDiario(consumoMedioDiario);
			// Fim Chamar Sub-Fluxo
			// Leitura Anterior
			leituraAnterior = Util.completaString(leituraAnterior, 7);
			emitirContaHelper.setLeituraAnterior(leituraAnterior);
			// Leitura Atual
			leituraAtual = Util.completaString(leituraAtual, 7);
			emitirContaHelper.setLeituraAtual(leituraAtual);
			// Dias de consumo
			diasConsumo = Util.completaString(diasConsumo, 2);
			emitirContaHelper.setDiasConsumo(diasConsumo);

			// Linha 11
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 3
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
					emitirContaHelper.getIdImovel(), emitirContaHelper
							.getAmReferencia(), 3, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes3(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 6
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
					emitirContaHelper.getIdImovel(), emitirContaHelper
							.getAmReferencia(), 6, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes6(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 12
			// --------------------------------------------------------------
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros do consumo historico da conta
			// [SB0006] - Obter Dados de Consumo da Conta
			Object[] parmsConsumoHistorico = null;
			String descricaoAbreviadaTipoConsumo = "";
			String descricaoTipoConsumo = "";
			String consumoMedio = "";
			String descricaoAbreviadaAnormalidadeConsumo = "";
			String descricaoAnormalidadeConsumo = "";
			String consumoRateio = "";
			// caso o tipo de ligacao for diferente de nulo
			if (tipoLigacao != null) {
				try {
					parmsConsumoHistorico = getControladorMicromedicao().obterDadosConsumoConta(
						emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),tipoLigacao);
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				if (parmsConsumoHistorico != null) {
					// descrição abreviada tipo de consumo
					if (parmsConsumoHistorico[0] != null) {
						descricaoAbreviadaTipoConsumo = (String) parmsConsumoHistorico[0];
					}
					// descrição tipo de consumo
					if (parmsConsumoHistorico[1] != null) {
						descricaoTipoConsumo = (String) parmsConsumoHistorico[1];
					}
					// Consumo médio
					if (parmsConsumoHistorico[2] != null) {
						consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
					}
					// descrição abreviada anormalidade de consumo
					if (parmsConsumoHistorico[3] != null) {
						descricaoAbreviadaAnormalidadeConsumo = (String) parmsConsumoHistorico[3];
					}
					// descrição anormalidade de consumo
					if (parmsConsumoHistorico[4] != null) {
						descricaoAnormalidadeConsumo = (String) parmsConsumoHistorico[4];
					}
					// Consumo médio
					if (parmsConsumoHistorico[5] != null) {
						consumoRateio = "" + (Integer) parmsConsumoHistorico[5];
					}
				}
			}

			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);

			// Fim Chamar Sub-Fluxo

			// Linha 13
			// --------------------------------------------------------------

			// Inicio Chamar Sub-Fluxo
			// soma a quantidades de economias da tabela contaCategoria
			// [SB0007] - Obter Dados da Medição da Conta
			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(
			emitirContaHelper.getIdConta(), false);
			emitirContaHelper.setQuantidadeEconomiaConta(""	+ quantidadeEconomiaConta);
			// Fim Chamar Sub-Fluxo

			// Consumo por Economia
			// transforma o consumoFaturamento para um bigDecimal
			BigDecimal consumoFaturadoBigDecimal = null;
			if (consumoFaturamento != null && !consumoFaturamento.equals("")) {
				consumoFaturadoBigDecimal = Util.formatarMoedaRealparaBigDecimal(consumoFaturamento);

			}
			// transforma a quantidade de economias da conta para um
			// bigDecimal
			BigDecimal qtdEconomiasBigDecimal = null;
			if (quantidadeEconomiaConta != null && !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util.formatarMoedaRealparaBigDecimal("" + quantidadeEconomiaConta);
			}
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null && qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util.formatarMoedaReal(consumoEconomiaBigDecimal);
				emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(0, (consumoEconomia.length() - 3)));
			}

			// Inicio Chamar Sub-Fluxo
			// concatena os campos dos sub-fluxos anteriores
			// [SB0008] - Obter Dados da Medição da Conta
			StringBuilder codigoAuxiliar = new StringBuilder();
			// leitura situação atual
			// tipo de consumo
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaTipoConsumo, 1));
			// tipo de contrato
			codigoAuxiliar.append(Util.completaString("", 1));
			// anormalidade de leitura
			codigoAuxiliar.append(Util.completaString(leituraAnormalidadeFaturamento, 2));
			// anormalidade de consumo
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaAnormalidadeConsumo, 2));

			// perfil do imóvel
			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString("" + emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}
			// dias do consumo
			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			// Consumo medio do imóvel
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			// Fim Chamar Sub-Fluxo
			emitirContaHelper.setCodigoAuxiliarString(codigoAuxiliar.toString());

			// chama o [SB0009] - Obter Mensagem de Rateio de Consumo Fixo
			// de Esgoto
			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao);
			// mensagem de rateio de consumo ou consumo fixo de esgoto
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo.toString());

			// Linha 16
			// --------------------------------------------------------------
			// chama o [SB0010] - Gerar Linhas da Descrição dos Serviços e Tarifas

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = 
				gerarLinhasDescricaoServicoTarifasRelatorio(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao, false);
			emitirContaHelper.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

			// Linha 17
			// --------------------------------------------------------------
			// cria um objeto conta para calcular o valor da conta
			Conta conta = new Conta();
			conta.setValorAgua(emitirContaHelper.getValorAgua());
			conta.setValorEsgoto(emitirContaHelper.getValorEsgoto());
			conta.setValorCreditos(emitirContaHelper.getValorCreditos());
			conta.setDebitos(emitirContaHelper.getDebitos());
			conta.setValorImposto(emitirContaHelper.getValorImpostos());
			BigDecimal valorConta = conta.getValorTotalContaBigDecimal();

			emitirContaHelper.setValorContaString(Util.formatarMoedaReal(valorConta));
			emitirContaHelper.setValorConta(valorConta);

			if (contaSemCodigoBarras.equals(ConstantesSistema.SIM)
					|| valorConta.compareTo(new BigDecimal("0.00")) == 0) {
				emitirContaHelper.setContaSemCodigoBarras("1");
			} else {
				emitirContaHelper.setContaSemCodigoBarras("2");
			}

			// chama o [SB0016] - Obter Mensagem da Conta em 3 Partes
			String[] parmsPartesConta = obterMensagemConta3Partes(
					emitirContaHelper, sistemaParametro);

			// Linha 18
			// --------------------------------------------------------------
			emitirContaHelper.setPrimeiraParte(parmsPartesConta[0]);

			// Linha 19
			// --------------------------------------------------------------
			emitirContaHelper.setSegundaParte(parmsPartesConta[1]);

			// Linha 20
			// --------------------------------------------------------------
			emitirContaHelper.setTerceiraParte(parmsPartesConta[2]);

			// Linha 21
			// --------------------------------------------------------------
			int anoMesReferenciaSubtraido = Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), 1);
			emitirContaHelper.setMesAnoFormatado(Util.formatarAnoMesParaMesAno(anoMesReferenciaSubtraido));

			// Linha 22
			// --------------------------------------------------------------
			Object[] parmsQualidadeAgua = null;
			parmsQualidadeAgua = pesquisarParmsQualidadeAgua(emitirContaHelper);

			// numero indice turbidez da qualidade agua
			String numeroIndiceTurbidez = "";
			// numero cloro residual da qualidade agua
			String numeroCloroResidual = "";
			if (parmsQualidadeAgua != null) {
				if (parmsQualidadeAgua[0] != null) {
					numeroIndiceTurbidez = Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]);
				}

				if (parmsQualidadeAgua[1] != null) {
					numeroCloroResidual = Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]);
				}
			}
			emitirContaHelper.setNumeroIndiceTurbidez(numeroIndiceTurbidez);
			emitirContaHelper.setNumeroCloroResidual(numeroCloroResidual);

			// Linha 23
			// --------------------------------------------------------------
			// Considerar as contas do tipo débito automático como tipo de conta normal
			// [SB0018 - Gerar Linhas das DemaisContas]
			Integer digitoVerificadorConta = new Integer(""	+ emitirContaHelper.getDigitoVerificadorConta());
			// formata ano mes para mes ano
			String anoMes = "" + emitirContaHelper.getAmReferencia();
			String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

			String representacaoNumericaCodBarra = "";
			
			// Linha28
			Date dataValidade = obterDataValidade2ViaConta(emitirContaHelper);
			emitirContaHelper.setDataValidade(Util.formatarData(dataValidade));
			
			if (contaSemCodigoBarras.equals(ConstantesSistema.NAO)
					|| valorConta.compareTo(new BigDecimal("0.00")) != 0) {

				representacaoNumericaCodBarra = this.getControladorArrecadacao()
						.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
								emitirContaHelper.getIdLocalidade(),
								emitirContaHelper.getIdImovel(), mesAno,
								digitoVerificadorConta, null, null, null, null,
								null, null, null);

				// Linha 24
				// Formata a representação númerica do código de barras
				String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra.substring(0, 11)
						+ "-"
						+ representacaoNumericaCodBarra.substring(11, 12)
						+ " "
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ "-"
						+ representacaoNumericaCodBarra.substring(23, 24)
						+ " "
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ "-"
						+ representacaoNumericaCodBarra.substring(35, 36)
						+ " "
						+ representacaoNumericaCodBarra.substring(36, 47)
						+ "-" + representacaoNumericaCodBarra.substring(47, 48);
				emitirContaHelper.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

				// Linha 25
				String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
						.substring(0, 11)
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ representacaoNumericaCodBarra.substring(36, 47);
				emitirContaHelper.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
				
			}
			
			
			colecaoEmitirContaHelper.add(emitirContaHelper);

			if (cobrarTaxaEmissaoConta) {
				this.gerarDebitoACobrarTaxaEmissaoConta(
					emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia());
			}

		}

		return colecaoEmitirContaHelper;
	}	
	
	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Sávio Luiz
	 * @date 24/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemConta3Partes(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {

		String[] linhasImpostosRetidos = new String[3];
		
		linhasImpostosRetidos = obterMensagemAnormalidadeConsumo(emitirContaHelper);
		
		if(linhasImpostosRetidos == null || linhasImpostosRetidos.equals("")){
			linhasImpostosRetidos = new String[3];
			Integer anoMesReferenciaFinal = sistemaParametro.getAnoMesFaturamento();
			int anoMesSubtraido = Util
					.subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
			Integer dataVencimentoFinalInteger = sistemaParametro
					.getAnoMesArrecadacao();
			String anoMesSubtraidoString = ""
					+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);
			int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
			int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));
	
			// recupera o ultimo dia do anomes e passa a data como parametro
			Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
			dataVencimentoFinal.set(Calendar.YEAR, ano);
			dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
			dataVencimentoFinal.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
					.getActualMaximum(Calendar.DAY_OF_MONTH));
	
			Date dataFinalDate = dataVencimentoFinal.getTime();
	
			// converte String em data
			Date dataVencimento = Util.converteStringParaDate("01/01/1900");
			
			ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = null;
			if(emitirContaHelper.getCategoriaImovel() != null && !emitirContaHelper.getCategoriaImovel().equals(""+Categoria.PUBLICO)){
	
				debitoImovelClienteHelper = getControladorCobranca()
					.obterDebitoImovelOuCliente(1,
							"" + emitirContaHelper.getIdImovel(), null, null,
							"190001", "" + anoMesSubtraido, dataVencimento,
							dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null);
			}
			// se o imovel possua débito(debitoImovelCobrança for diferente de nulo)
			if (debitoImovelClienteHelper != null
					&& ((debitoImovelClienteHelper
							.getColecaoGuiasPagamentoValores() != null && !debitoImovelClienteHelper
							.getColecaoGuiasPagamentoValores().isEmpty()) || (debitoImovelClienteHelper
							.getColecaoContasValores() != null && !debitoImovelClienteHelper
							.getColecaoContasValores().isEmpty()))) {
				String dataVencimentoFinalString = Util.formatarData(dataFinalDate);
				linhasImpostosRetidos[0] = "SR. USUÁRIO: EM  "
						+ dataVencimentoFinalString
						+ ",    REGISTRAMOS QUE V.SA. ESTAVA EM DÉBITO COM A "
						+ sistemaParametro.getNomeAbreviadoEmpresa() + ".";
				linhasImpostosRetidos[1] = "COMPAREÇA A UM DOS NOSSOS POSTOS DE ATENDIMENTO PARA REGULARIZAR SUA SITUACAO.EVITE O CORTE.";
				linhasImpostosRetidos[2] = "CASO O SEU DÉBITO TENHA SIDO PAGO, DESCONSIDERE ESTE AVISO.";
	
			} else {
				Object[] mensagensConta = null;
				// recupera o id do grupo de faturamento da conta
				//Integer idFaturamentoGrupo = emitirContaHelper
				//		.getIdFaturamentoGrupo();
				// recupera o id da gerencia regional da conta
				//Integer idGerenciaRegional = emitirContaHelper
				//		.getIdGerenciaRegional();
				// recupera o id da localidade da conta
				Integer idLocalidade = emitirContaHelper.getIdLocalidade();
				// recupera o id do setor comercial da conta
				//Integer idSetorComercial = emitirContaHelper.getIdSetorComercial();
				// caso entre em alguma condição então não entra mais nas outras
				boolean achou = false;
				try {
					// o sistema obtem a mensagem para a conta
					// Caso seja a condição 1
					// (FaturamentoGrupo =null, GerenciaRegional=null,
					// Localidade=parmConta, SetorComercial=null)
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper, null,
									null, idLocalidade,
									null);
					if (mensagensConta != null) {
						// Conta Mensagem 1
						if (mensagensConta[0] != null) {
							linhasImpostosRetidos[0] = (String) mensagensConta[0];
						} else {
							linhasImpostosRetidos[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							linhasImpostosRetidos[1] = (String) mensagensConta[1];
						} else {
							linhasImpostosRetidos[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							linhasImpostosRetidos[2] = (String) mensagensConta[2];
						} else {
							linhasImpostosRetidos[2] = "";
						}
						achou = true;
					}
	
					if (!achou) {
	
						// Caso seja a condição 2
						// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
						// Localidade=null, SetorComercial=null)
						// Conta Mensagem 1
						mensagensConta = repositorioFaturamento
								.pesquisarParmsContaMensagem(emitirContaHelper,
										null, null, null,
										null);
						if (mensagensConta != null) {
							if (mensagensConta[0] != null) {
								linhasImpostosRetidos[0] = (String) mensagensConta[0];
							} else {
								linhasImpostosRetidos[0] = "";
							}
							// Conta Mensagem 2
							if (mensagensConta[1] != null) {
								linhasImpostosRetidos[1] = (String) mensagensConta[1];
							} else {
								linhasImpostosRetidos[1] = "";
							}
							// Conta Mensagem 3
							if (mensagensConta[2] != null) {
								linhasImpostosRetidos[2] = (String) mensagensConta[2];
							} else {
								linhasImpostosRetidos[2] = "";
							}
							achou = true;
						}
					}
					// caso não tenha entrado em nenhuma das opções acima
					// então completa a string com espaçõs em branco
					if (!achou) {
						linhasImpostosRetidos[0] = "";
						linhasImpostosRetidos[1] = "";
						linhasImpostosRetidos[2] = "";
					}
				} catch (ErroRepositorioException e) {
					sessionContext.setRollbackOnly();
					throw new ControladorException("erro.sistema", e);
				}
			}
		}
		return linhasImpostosRetidos;
	}
	
	

}
