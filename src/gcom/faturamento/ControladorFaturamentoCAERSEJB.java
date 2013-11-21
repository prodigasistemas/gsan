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

import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.interceptor.RegistradorOperacao;
import gcom.relatorio.faturamento.conta.ContaLinhasDescricaoServicosTarifasTotalHelper;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;

import br.com.danhil.BarCode.Interleaved2of5;


/**
 * Controlador Faturamento CAER
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class ControladorFaturamentoCAERSEJB extends ControladorFaturamento implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAER
	//==============================================================================================================
	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 03/03/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContas(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,Short contaSemCodigoBarras)
			throws ControladorException {

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = new ArrayList();
		Collection colecaoNacionalFeriado = getControladorUtil().pesquisarFeriadosNacionais();

		Iterator iter = idsContaEP.iterator();
		while (iter.hasNext()) {
			Integer idContaEP = (Integer) iter.next();

			Collection colectionConta;
			try {
				colectionConta = this.repositorioFaturamento.pesquisarContaERota(idContaEP);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) colectionConta.iterator().next();

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			String nomeCliente = "";
			if (emitirContaHelper.getNomeImovel() != null && !emitirContaHelper.getNomeImovel().equals("")) {
				
				nomeCliente = emitirContaHelper.getNomeImovel();
				emitirContaHelper.setNomeCliente(nomeCliente);
			}
			
			// Linha 5
			// --------------------------------------------------------------
			// recupera endereco do imóvel
			String[] enderecoArray = 
				this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(emitirContaHelper.getIdImovel());
			
			if(enderecoArray != null){
				// 3 - Endereço parte 1 
				emitirContaHelper.setEnderecoImovel(Util.completaString(enderecoArray[0],100));

				// 4 - Endereço parte 2 (Bairro) 
				emitirContaHelper.setEnderecoLinha2(Util.completaString(enderecoArray[3],30));
				
				// 5 - Endereço parte 3(Municipio,Uf,Cep) 
				String restoEndereco = "";
				
				if(enderecoArray[1] != null){
					restoEndereco = enderecoArray[1];
				}
				
				if(enderecoArray[2] != null){
					if(restoEndereco.equals("")){
						restoEndereco = enderecoArray[2];
					}else{
						restoEndereco = restoEndereco+" "+enderecoArray[2];
					}
				}
				
				if(enderecoArray[4] != null){
					Cep cep = new Cep();
					cep.setCodigo(new Integer(enderecoArray[4]));
					
					if(restoEndereco.equals("")){
						restoEndereco = cep.getCepFormatado();
					}else{
						restoEndereco = restoEndereco+" "+cep.getCepFormatado();
					}
				}
				
				emitirContaHelper.setEnderecoLinha3(Util.completaString(restoEndereco,41));
				
			}else{
				// 3 - Endereço parte 1 
				// 4 - Endereço parte 2 
				// 5 - Endereço parte 3 
				emitirContaHelper.setEnderecoImovel("");
				emitirContaHelper.setEnderecoLinha2("");
				emitirContaHelper.setEnderecoLinha3("");
			}

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
//			String idClienteResponsavel = "";
//			String enderecoClienteResponsavel = "";
//			Integer idImovelContaEnvio = emitirContaHelper.getIdImovelContaEnvio();
//			// caso a coleção de contas seja de entrega para o cliente
//			// responsável
//			if (idImovelContaEnvio != null
//					&& (idImovelContaEnvio.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) 
//						|| idImovelContaEnvio.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {
//				Integer idClienteResponsavelInteger = null;
//				idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(emitirContaHelper.getIdConta(),false);
//
//				if (idClienteResponsavel != null && !idClienteResponsavel.equals("")) {
//					idClienteResponsavel = idClienteResponsavelInteger.toString();
//					// [UC0085]Obter Endereco
//					enderecoClienteResponsavel = getControladorEndereco()
//							.pesquisarEnderecoClienteAbreviado(idClienteResponsavelInteger);
//				}
//
//			}
//			emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
//			emitirContaHelper.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

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
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 1, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes1(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 4
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 4, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes4(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 10
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 2
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 2, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes2(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 5
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 5, tipoLigacao, tipoMedicao);
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
					leituraAnormalidadeFaturamento = "" + (Integer) parmsMedicaoHistorico[5];
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
			// recupera os parametros de consumo faturamento e consumo médio
			// diário
			// [SB0005] - Obter Consumo Faturado e Consumo Médio Diário
			String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(emitirContaHelper, tipoMedicao, diasConsumo);
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
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 3, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes3(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 6
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 6, tipoLigacao, tipoMedicao);
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
							emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(),tipoLigacao);
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
			emitirContaHelper.setConsumoMedio(consumoMedio);
			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);

			// Fim Chamar Sub-Fluxo

			// Linha 13
			// --------------------------------------------------------------

			// Inicio Chamar Sub-Fluxo
			// soma a quantidades de economias da tabela contaCategoria
			// [SB0007] - Obter Dados da Medição da Conta
			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(emitirContaHelper.getIdConta(), false);
			emitirContaHelper.setQuantidadeEconomiaConta("" + quantidadeEconomiaConta);
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
			if (quantidadeEconomiaConta != null
					&& !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util
						.formatarMoedaRealparaBigDecimal(""
								+ quantidadeEconomiaConta);
			}
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null
					&& qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal
						.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util
						.formatarMoedaReal(consumoEconomiaBigDecimal);
				
				Categoria categoria = 
					this.getControladorImovel().obterPrincipalCategoriaImovel(emitirContaHelper.getIdImovel());
				Collection<Categoria> colecaoCategoria = new ArrayList();
				colecaoCategoria.add(categoria);
				Imovel imovelNaBase = getControladorImovel().pesquisarImovel(emitirContaHelper.getIdImovel());
				int consumoMinimo = this.getControladorMicromedicao().obterConsumoMinimoLigacao(imovelNaBase, colecaoCategoria);
				int consumoAtual = Integer.parseInt((consumoEconomia.substring(
						0, (consumoEconomia.length() - 3))).replace(".",""));
				
				if (consumoMinimo > consumoAtual) {
					emitirContaHelper.setConsumoEconomia(consumoMinimo + "");
				} else{
					emitirContaHelper.setConsumoEconomia(consumoAtual + "");
				}
				
				/*emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(
						0, (consumoEconomia.length() - 3)));*/
			}

			// Inicio Chamar Sub-Fluxo
			// concatena os campos dos sub-fluxos anteriores
			// [SB0008] - Obter Dados da Medição da Conta
			StringBuilder codigoAuxiliar = new StringBuilder();
			// leitura situação atual
			// tipo de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaTipoConsumo, 1));
			// tipo de contrato
			codigoAuxiliar.append(Util.completaString("", 1));
			// anormalidade de leitura
			codigoAuxiliar.append(Util.completaString(
					leituraAnormalidadeFaturamento, 2));
			// anormalidade de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaAnormalidadeConsumo, 2));

			// perfil do imóvel
			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString(""
						+ emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}
			// dias do consumo
			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			// Consumo medio do imóvel
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			// Fim Chamar Sub-Fluxo
			emitirContaHelper
					.setCodigoAuxiliarString(codigoAuxiliar.toString());

			// chama o [SB0009] - Obter Mensagem de Rateio de Consumo Fixo
			// de Esgoto
			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao);
			// mensagem de rateio de consumo ou consumo fixo de esgoto
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo
					.toString());

			// Linha 16
			// --------------------------------------------------------------
			// chama o [SB0010] - Gerar Linhas da Descrição dos Serviços e
			// Tarifas

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = gerarLinhasDescricaoServicoTarifasRelatorio(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao, false);
			emitirContaHelper
					.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

			// StringBuilder linhasDescricaoServicosTarifasTotal =
			// gerarLinhasDescricaoServicoTarifas(emitirContaHelper,
			// consumoRateio, parmsMedicaoHistorico, tipoMedicao);
			// emitirContaHelper.setLinhasDescricaoServicosTarifasTotal(linhasDescricaoServicosTarifasTotal);
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

			emitirContaHelper.setValorContaString(Util
					.formatarMoedaReal(valorConta));
			
			if (contaSemCodigoBarras.equals(ConstantesSistema.SIM) || valorConta.compareTo(new BigDecimal("0.00")) == 0){
				emitirContaHelper.setContaSemCodigoBarras("1");
			}else{
				emitirContaHelper.setContaSemCodigoBarras("2");
			}
			
			if (contaSemCodigoBarras.equals(ConstantesSistema.NAO)){
				
				FiltroPagamento filtroPagamento = new FiltroPagamento();
				filtroPagamento.adicionarParametro(new ParametroSimples(
						FiltroPagamento.CONTA_ID, idContaEP));
				Collection colecaoPagamentos = Fachada.getInstancia().pesquisar(filtroPagamento, Pagamento.class.getName());
				
				if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()){
					
					emitirContaHelper.setContaSemCodigoBarras("1");
				}
				
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
			int anoMesReferenciaSubtraido = Util.subtrairMesDoAnoMes(
					emitirContaHelper.getAmReferencia(), 1);
			emitirContaHelper.setMesAnoFormatado(Util
					.formatarAnoMesParaMesAno(anoMesReferenciaSubtraido));

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
					numeroIndiceTurbidez = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]);
				}

				if (parmsQualidadeAgua[1] != null) {
					numeroCloroResidual = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]);
				}
			}
			emitirContaHelper.setNumeroIndiceTurbidez(numeroIndiceTurbidez);
			emitirContaHelper.setNumeroCloroResidual(numeroCloroResidual);

			// Linha 23
			// --------------------------------------------------------------
			// Considerar as contas do tipo débito automático como tipo de conta
			// normal
			// [SB0018 - Gerar Linhas das DemaisContas]
			Integer digitoVerificadorConta = new Integer(""
					+ emitirContaHelper.getDigitoVerificadorConta());
			// formata ano mes para mes ano
			String anoMes = "" + emitirContaHelper.getAmReferencia();
			String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

			String representacaoNumericaCodBarra = "";
			
			representacaoNumericaCodBarra = this.getControladorArrecadacao()
						.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
								emitirContaHelper.getIdLocalidade(),
								emitirContaHelper.getIdImovel(), mesAno,
								digitoVerificadorConta, null, null, null, null,
								null, null, null);

			

			// Linha 24
			// Formata a representação númerica do código de barras
			String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
					.substring(0, 11)
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
			emitirContaHelper
					.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

			// Linha 25
			String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
					.substring(0, 11)
					+ representacaoNumericaCodBarra.substring(12, 23)
					+ representacaoNumericaCodBarra.substring(24, 35)
					+ representacaoNumericaCodBarra.substring(36, 47);
			emitirContaHelper
					.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
			// Linha28

			if (emitirContaHelper.getDataValidadeConta().compareTo(new Date()) == 1) {
				emitirContaHelper
						.setDataValidade(Util.formatarData(emitirContaHelper
								.getDataValidadeConta()));

			} else {
				// soma 60 dias a data atual
				Date dataValidadeConta = Util.adicionarNumeroDiasDeUmaData(
						new Date(), 60);

				int ano = Util.getAno(dataValidadeConta);
				int mes = Util.getMes(dataValidadeConta);
				Calendar calendar = new GregorianCalendar();
				calendar.set(Calendar.MONTH, mes - 1);
				calendar.set(Calendar.YEAR, ano);


				Collection colecaoDatasFeriados = new ArrayList();
				Iterator iterNacionalFeriado = colecaoNacionalFeriado
						.iterator();
				while (iterNacionalFeriado.hasNext()) {
					NacionalFeriado nacionalFeriado = (NacionalFeriado) iterNacionalFeriado
							.next();
					colecaoDatasFeriados.add(nacionalFeriado.getData());
				}

				calendar.set(Calendar.DAY_OF_MONTH, Util.obterUltimoDiaUtilMes(
						mes, ano, colecaoDatasFeriados));

				dataValidadeConta = calendar.getTime();

				emitirContaHelper.setDataValidade(Util
						.formatarData(dataValidadeConta));

			}

			// emitirContaHelper.setDataValidade(Util
			// .formatarData(emitirContaHelper.getDataValidadeConta()));
			
			emitirContaHelper.setCategoriaImovel(obterCategoriaImovelConta(idContaEP));
			
			
			FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
			
			filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
					FiltroQualidadeAgua.LOCALIDADE_ID));
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA, 
					emitirContaHelper.getAmReferencia()));
			
			Collection colecaoQualidadeAgua = 
				getControladorUtil().pesquisar(filtroQualidadeAgua, 
					QualidadeAgua.class.getName());
			
			QualidadeAgua qualidadeAgua = null;
			if(colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()){
				qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();
			}
			
			FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
			
			Collection colecaoQualidadeAguaPadrao = 
				getControladorUtil().pesquisar(filtroQualidadeAguaPadrao, 
					QualidadeAguaPadrao.class.getName());
			
			QualidadeAguaPadrao qualidadeAguaPadrao = null;
			if(colecaoQualidadeAguaPadrao != null && !colecaoQualidadeAguaPadrao.isEmpty()){
				qualidadeAguaPadrao = (QualidadeAguaPadrao) colecaoQualidadeAguaPadrao.iterator().next();
			}

			//Turbidez Valor Medio 
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioTurbidez(Util.completaString(
						Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceTurbidez(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioTurbidez("");
			}
			
			//Turbidez Valor Padrao 
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoTurbidez(qualidadeAguaPadrao.getDescricaoPadraoTurbidez());
			}else{
				emitirContaHelper.setPadraoTurbidez("");
			}
			
			//Ph Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioPh(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndicePh(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioPh("");
			}
			
			//Ph Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoPh(qualidadeAguaPadrao.getDescricaoPadraoPh());
			}else{
				emitirContaHelper.setPadraoPh("");
			}
			
			//Cor Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioCor(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceCor(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioCor("");
			}
			
			//Cor Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoCor(qualidadeAguaPadrao.getDescricaoPadraoCor());
			}else{
				emitirContaHelper.setPadraoCor("");
			}
			
			//Cloro Valor Medio 
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioCloro(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroCloroResidual(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioCloro("");
			}
			
			//Cloro Valor Padrao 
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoCloro(qualidadeAguaPadrao.getDescricaoPadraoCloro());
			}else{
				emitirContaHelper.setPadraoCloro("");
			}
			
			//Fluor Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioFluor(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFluor(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioFluor("");
			}
			
			//Fluor Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoFluor(qualidadeAguaPadrao.getDescricaoPadraoFluor());
			}else{
				emitirContaHelper.setPadraoFluor("");
			}
			
			//Ferro Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioFerro(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFerro(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioFerro("");
			}
			
			//Ferro Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoFerro(qualidadeAguaPadrao.getDescricaoPadraoFerro());
			}else{
				emitirContaHelper.setPadraoFerro("");
			}
			
			//Coliformes Totais Valor Medio
			if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesTotais() == null || 
				qualidadeAgua.getNumeroIndiceColiformesTotais().compareTo(new BigDecimal("0.00")) == 0 ) ){
				emitirContaHelper.setValorMedioColiformesTotais("Ausente");															
			}else{
				emitirContaHelper.setValorMedioColiformesTotais(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesTotais(),2,true),10));
			}
			
			//Coliformes Totais Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoColiformesTotais(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais());
			}else{
				emitirContaHelper.setPadraoColiformesTotais("");
			}
			
			//Coliformes Fecais Valor Medio
			if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesFecais() == null || 
				qualidadeAgua.getNumeroIndiceColiformesFecais().compareTo(new BigDecimal("0.00")) == 0 ) ){

				emitirContaHelper.setValorMedioColiformesfecais("Ausente");															
				
			}else{
				emitirContaHelper.setValorMedioColiformesfecais(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesFecais(),2,true),10));
			}
			
			//Coliformes Fecais Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoColiformesfecais(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais());
			}else{
				emitirContaHelper.setPadraoColiformesfecais("");
			}

			Integer tipoConta = null;
			try {
				tipoConta = repositorioFaturamento.pesquisarTipoConta(idContaEP);
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}
			
			if (tipoConta != null){

				String[] parmsMensagemConta = 
					obterMensagemConta(emitirContaHelper,sistemaParametro,tipoConta,colecaoNacionalFeriado);
				
				//Mensagem 1
				emitirContaHelper.setMsgConta(Util.completaString(parmsMensagemConta[0],40));
				
				//Mensagem 2
				emitirContaHelper.setMsgLinha1Conta(Util.completaString(parmsMensagemConta[1],65));
				
				//Mensagem 3
				emitirContaHelper.setMsgLinha2Conta(Util.completaString(parmsMensagemConta[2],65));
				
				//Mensagem 4
				//São as data de vencimento
				//emitirContaHelper.setDatasVencimento(Util.completaString(parmsMensagemConta[3],65));
				
				//Mensagem 5
				emitirContaHelper.setMsgLinha3Conta(Util.completaString(parmsMensagemConta[3],65));
				
				//Mensagem 6
				emitirContaHelper.setMsgLinha4Conta(Util.completaString(parmsMensagemConta[4],65));
	
				//Mensagem 7
				emitirContaHelper.setMsgLinha5Conta(Util.completaString(parmsMensagemConta[5],65));
				
//				Mensagem 7
				emitirContaHelper.setMsgLinha5Conta(Util.completaString(parmsMensagemConta[6],65));
				
			}
			
			String numeroHidrometro = "";
			if(getControladorAtendimentoPublico().obterNumeroHidrometroEmLigacaoAgua(emitirContaHelper.getIdImovel()) != null){
				numeroHidrometro = getControladorAtendimentoPublico().obterNumeroHidrometroEmLigacaoAgua(emitirContaHelper.getIdImovel());
			}
			emitirContaHelper.setNumeroHidrometro(numeroHidrometro);	

			
			colecaoEmitirContaHelper.add(emitirContaHelper);

			if (cobrarTaxaEmissaoConta) {
				this.gerarDebitoACobrarTaxaEmissaoConta(emitirContaHelper
						.getIdImovel(), emitirContaHelper.getAmReferencia());
			}

		}

		return colecaoEmitirContaHelper;
	}

	
	
	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0003] Obter Dados do Consumo e Medicao Anterior
	 * 
	 * @author Vivianne Sousa
	 * @date 03/03/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public StringBuilder obterDadosConsumoAnterior(Integer idImovel,
			int anoMes, int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException {

		StringBuilder dadosConsumoAnterior = new StringBuilder();

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);
		String mesAnoFormatado = Util.formatarAnoMesParaMesAno(anoMesSubtraido)
				+ " -";

		// adiciona o mes/ano formatado com o traço
		dadosConsumoAnterior.append(Util.completaString(mesAnoFormatado, 9));
		// caso o tipo de ligação e medição seja diferente de nulo
		if (tipoLigacao != null && tipoMedicao != null) {
			Object[] parmsConsumoHistorico = null;
//			Integer idLeituraAnormalidade = null;
			parmsConsumoHistorico = getControladorMicromedicao()
					.obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
							anoMesSubtraido, tipoLigacao);
			Integer numeroConsumoFaturadoMes = null;
//			String descricaoAbreviadaAnormalidadeAnterior = null;
			if (parmsConsumoHistorico != null) {
				if (parmsConsumoHistorico[0] != null) {
					numeroConsumoFaturadoMes = (Integer) parmsConsumoHistorico[0];
				}
//				if (parmsConsumoHistorico[1] != null) {
//					descricaoAbreviadaAnormalidadeAnterior = (String) parmsConsumoHistorico[1];
//				}
			}
//			try {
//
//				// caso o tipo de medição seja agua
//				if (tipoLigacao.equals(MedicaoTipo.LIGACAO_AGUA)) {
//					idLeituraAnormalidade = repositorioMicromedicao.pesquisarIdLeituraAnormalidadeTipoAgua(idImovel,anoMesSubtraido);
//				} else {
//					// senão caso o tipo de medição seja poco
//					if (tipoMedicao.equals(MedicaoTipo.POCO)) {
//						idLeituraAnormalidade = repositorioMicromedicao.pesquisarIdLeituraAnormalidadeTipoEsgoto(idImovel, anoMesSubtraido);
//					}
//				}
//			} catch (ErroRepositorioException e) {
//				throw new ControladorException("erro.sistema", e);
//			}
			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				dadosConsumoAnterior.append(Util.completaStringComEspacoAEsquerda("" + numeroConsumoFaturadoMes, 6));
			} else {
				dadosConsumoAnterior.append(Util.completaStringComEspacoAEsquerda("", 6));
			}
			
		} else {
			// senão completa com espaços em branco
			dadosConsumoAnterior.append(Util.completaString("", 11));
		}
		
		return dadosConsumoAnterior;
	}

	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0352] Emitir Contas
	 * 
	 * [SB0023] Obter Dados do Consumo Anterior CAER
	 * 
	 * @author Rafael Pinto
	 * @date 24/07/2007
	 * 
	 * @throws ControladorException
	 */
	private String[] obterDadosConsumoAnteriorEmitirTXTContas(Integer idImovel,
			int anoMes, int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException {

		String[] dadosConsumoAnterior = new String[2];

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);
		String mesAnoFormatado = Util.formatarAnoMesParaMesAno(anoMesSubtraido);

		// adiciona o mes/ano formatado com o traço
		dadosConsumoAnterior[0] = mesAnoFormatado;
		
		// caso o tipo de ligação e medição seja diferente de nulo
		if (tipoLigacao != null && tipoMedicao != null) {
			
			Object[] parmsConsumoHistorico = null;

			parmsConsumoHistorico = getControladorMicromedicao()
					.obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
							anoMesSubtraido, tipoLigacao);
			Integer numeroConsumoFaturadoMes = null;

			if (parmsConsumoHistorico != null) {
				if (parmsConsumoHistorico[0] != null) {
					numeroConsumoFaturadoMes = (Integer) parmsConsumoHistorico[0];
				}
			}

			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				dadosConsumoAnterior[1] = ""+numeroConsumoFaturadoMes;
			} else {
				dadosConsumoAnterior[1] = "";
			}
			
		} else {
			dadosConsumoAnterior = null;
		}
		
		return dadosConsumoAnterior;
	}
	
	
	public String obterCategoriaImovelConta( Integer idConta)throws ControladorException{
		Collection colecaoContaCategoriaComFaixas = null;
		try{
		colecaoContaCategoriaComFaixas = repositorioFaturamento
		.pesquisarContaCategoria(idConta);
		}catch (ErroRepositorioException e) {
		sessionContext.setRollbackOnly();
		throw new ControladorException("erro.sistema", e);
		}
		
		String categoria = "";
		if (colecaoContaCategoriaComFaixas != null
			&& !colecaoContaCategoriaComFaixas.isEmpty()) {
			Iterator iteratorContaCategoriaComFaixas = colecaoContaCategoriaComFaixas.iterator();
			while (iteratorContaCategoriaComFaixas.hasNext()) {
				ContaCategoria contaCategoria = (ContaCategoria) iteratorContaCategoriaComFaixas
						.next();
				categoria = contaCategoria.getComp_id().getCategoria().getDescricao();
				
			}
		}

		return categoria;
		
	}
	
	
	/**
	 * [UC0482] Emitir 2 Via de Contas
	 * 
	 * [SB00011] Gerar Linhas da Tarifa de Água
	 * 
	 * @author Vivianne Sousa
	 * @date 24/04/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	protected Collection gerarLinhasTarifaAguaRelatorio(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao,
			Collection colecaoLinhasDescricaoServicosTarifasTotal)
			throws ControladorException {

		Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = colecaoLinhasDescricaoServicosTarifasTotal;
		ContaLinhasDescricaoServicosTarifasTotalHelper contaLinhasDescricaoServicosTarifasTotalHelper = null;

		String descricaoServicosTarifas1 = "";

		// -- Linha 1 --//
		descricaoServicosTarifas1 = "AGUA";
		contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
		contaLinhasDescricaoServicosTarifasTotalHelper.setDescricaoServicosTarifas(descricaoServicosTarifas1);
		contaLinhasDescricaoServicosTarifasTotalHelper.setConsumoFaixa("");
		contaLinhasDescricaoServicosTarifasTotalHelper.setValor("");
		colecaoContaLinhasDescricaoServicosTarifasTotalHelper.add(contaLinhasDescricaoServicosTarifasTotalHelper);

		Collection colecaoContaCategoriaComFaixas = null;
		try {
			colecaoContaCategoriaComFaixas = repositorioFaturamento
					.pesquisarContaCategoria(emitirContaHelper.getIdConta());
		} catch (ErroRepositorioException e) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", e);
		}

		if (colecaoContaCategoriaComFaixas != null && !colecaoContaCategoriaComFaixas.isEmpty()) {
			Iterator iteratorContaCategoriaComFaixas = colecaoContaCategoriaComFaixas.iterator();
			while (iteratorContaCategoriaComFaixas.hasNext()) {
				ContaCategoria contaCategoria = (ContaCategoria) iteratorContaCategoriaComFaixas.next();

				String descricaoServicosTarifas2 = "";

				// -- Linha 2 --//
				descricaoServicosTarifas2 = " ";
				// descricao da categoria
				descricaoServicosTarifas2 = descricaoServicosTarifas2
						+ Util.completaString(contaCategoria.getComp_id().getCategoria().getDescricao(), 13);
				// quantidade de economias
				descricaoServicosTarifas2 = descricaoServicosTarifas2
						+ Util.adicionarZerosEsquedaNumero(3, "" + contaCategoria.getQuantidadeEconomia());

				if (contaCategoria.getQuantidadeEconomia() == 1) {
					descricaoServicosTarifas2 = descricaoServicosTarifas2 + "  UNIDADE ";
				} else {
					descricaoServicosTarifas2 = descricaoServicosTarifas2 + "  UNIDADES ";
				}

				contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
				contaLinhasDescricaoServicosTarifasTotalHelper.setDescricaoServicosTarifas(descricaoServicosTarifas2);
				contaLinhasDescricaoServicosTarifasTotalHelper.setConsumoFaixa("");
				contaLinhasDescricaoServicosTarifasTotalHelper.setValor("");
				colecaoContaLinhasDescricaoServicosTarifasTotalHelper.add(contaLinhasDescricaoServicosTarifasTotalHelper);

				// -- Linha 3 --//
				contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
				String descricaoServicosTarifas3 = "";
				String consumoFaixa3 = "";
				String valor3 = "";

				descricaoServicosTarifas3 = " CONSUMO DE ÁGUA";
				consumoFaixa3 = Util.completaStringComEspacoAEsquerda("" + contaCategoria.getConsumoAgua(), 6) + " M3";
				valor3 = Util.formatarMoedaReal(contaCategoria.getValorAgua());

				contaLinhasDescricaoServicosTarifasTotalHelper = new ContaLinhasDescricaoServicosTarifasTotalHelper();
				contaLinhasDescricaoServicosTarifasTotalHelper.setDescricaoServicosTarifas(descricaoServicosTarifas3);
				contaLinhasDescricaoServicosTarifasTotalHelper.setConsumoFaixa(consumoFaixa3.trim());
				contaLinhasDescricaoServicosTarifasTotalHelper.setValor(valor3);
				colecaoContaLinhasDescricaoServicosTarifasTotalHelper.add(contaLinhasDescricaoServicosTarifasTotalHelper);

			}
		}

		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}
	

	
	
	public StringBuilder obterConsumoAnterior(Integer idImovel,
			int anoMes, int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException {

		StringBuilder dadosConsumoAnterior = new StringBuilder();

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);
		String mesAnoFormatado = Util.formatarAnoMesParaMesAno(anoMesSubtraido)
				+ " -";

		// adiciona o mes/ano formatado com o traço
		
		// caso o tipo de ligação e medição seja diferente de nulo
		if (tipoLigacao != null && tipoMedicao != null) {
			dadosConsumoAnterior.append(Util.completaString(mesAnoFormatado, 7));
			Object[] parmsConsumoHistorico = null;
			parmsConsumoHistorico = getControladorMicromedicao()
					.obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
							anoMesSubtraido, tipoLigacao);
			Integer numeroConsumoFaturadoMes = null;
			if (parmsConsumoHistorico != null) {
				if (parmsConsumoHistorico[0] != null) {
					numeroConsumoFaturadoMes = (Integer) parmsConsumoHistorico[0];
				}
			}
			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				dadosConsumoAnterior.append(Util
						.completaStringComEspacoAEsquerda(""
								+ numeroConsumoFaturadoMes, 5));
			} else {
				dadosConsumoAnterior.append(Util
						.completaStringComEspacoAEsquerda("", 5));
			}
			// caso o id dos dados do consumo anterior for diferente de nulo
			
		} else {
			// senão completa com espaços em branco
			dadosConsumoAnterior.append(Util.completaString("", 12));
		}

		return dadosConsumoAnterior;
	}
	
	
	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {

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

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
				.obterDebitoImovelOuCliente(1,
						"" + emitirContaHelper.getIdImovel(), null, null,
						"190001", "" + anoMesSubtraido, dataVencimento,
						dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null);
		
		return debitoImovelClienteHelper;
	}
	
	
	
	/**
	 * Metódo responsável por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas - CAER
	 * 
	 * @author Rafael Pinto
	 * @date 24/07/2007
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param faturamentoGrupo
	 * @param idFuncionalidadeIniciada
	 * @param tipoConta
	 * @param idEmpresa
	 * @param indicadorEmissaoExtratoFaturamento
	 * 
	 * @throws ControladorException
	 */
	public void emitirContas(Integer anoMesReferenciaFaturamento,FaturamentoGrupo faturamentoGrupo, 
		int idFuncionalidadeIniciada,int tipoConta, Integer idEmpresa, 
		Short indicadorEmissaoExtratoFaturamento)
		throws ControladorException {

		int idUnidadeIniciada = 0;
		
		// -------------------------
		// Registrar o início do processamento da Unidade de
		// Processamento do Batch
		// -------------------------

		idUnidadeIniciada = 
			getControladorBatch().iniciarUnidadeProcessamentoBatch(
				idFuncionalidadeIniciada,
				UnidadeProcessamento.FUNCIONALIDADE,
				(idEmpresa == null ? 0 : idEmpresa));

		try {

			SistemaParametro sistemaParametro = null;
			Collection<NacionalFeriado> colecaoNacionalFeriado = null;

			int quantidadeContas = 0;

			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;
			int numeroIndiceAntecipado = 0;

			try {
				
				String mesReferencia = "_Fat" + anoMesReferenciaFaturamento.toString().substring(4, 6);				

				sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

				colecaoNacionalFeriado = getControladorUtil().pesquisarFeriadosNacionais(); 
				
				// recebe todos as contas da lista
				StringBuilder contasTxtLista = null;
				Map<Integer, Integer> mapAtualizaSequencial = null;
				
				// Alterado por Sávio Luiz Data:12/11/2007 Analista:Aryed Lins
				// Se o mês de faturamento for igual a 11 e o indicador faturamento
				// antecipado for igual a 1, então seta o mes de faturamento igual a
				// 12 em conta impressão,e imprime as contas no mes 12 também.
				boolean ehFaturamentoAntecipado = false;
				Integer anoMesReferenciaFaturamentoAntecipado = null;
				
				if (Util.obterMes(anoMesReferenciaFaturamento) == 11) {
					if(sistemaParametro.getIndicadorFaturamentoAntecipado().equals(ConstantesSistema.SIM)){
						ehFaturamentoAntecipado = true;
						anoMesReferenciaFaturamentoAntecipado = 
							Util.somarData(anoMesReferenciaFaturamento);
					}
				}

				try {

					boolean flagTerminou = false;
					numeroIndice = 0;
					numeroIndiceAntecipado = 0;
					Integer sequencialImpressao = 0;

					Collection colecaoConta = null;
					Collection<Integer> colecaoContaTipo = null;
					contasTxtLista = new StringBuilder();

					while (!flagTerminou) {

						// map que armazena o sequencial e o numero da
						// conta para no final atualizar todos os
						// sequencias
						mapAtualizaSequencial = new HashMap();
						Collection colecaoContaParms = null;
						
						if(anoMesReferenciaFaturamentoAntecipado != null &&
							anoMesReferenciaFaturamento.intValue() == anoMesReferenciaFaturamentoAntecipado.intValue()){
								
							System.out.println("INDICE_ANTECIPADO_PESQUISA:"+numeroIndiceAntecipado);
								
							numeroIndice = numeroIndiceAntecipado;
						}						
						
						colecaoContaTipo = new ArrayList<Integer>();
						
						if(tipoConta == 0){
							
							System.out.println("***************************************************");
							System.out.println("PESQUISA AS CONTAS COM ENDEREÇO NORMAL (ÍNDICE-"+numeroIndice+")");
							System.out.println("**************************************************");
							
							colecaoContaTipo.add(ContaTipo.CONTA_RETIDA_POR_EC);
							colecaoContaTipo.add(ContaTipo.CONTA_RETIDA_POR_BC);
							colecaoContaTipo.add(ContaTipo.CONTA_DEBITO_AUTOMATICO);
							colecaoContaTipo.add(ContaTipo.CONTA_NORMAL);
							
							colecaoContaParms = 
								repositorioFaturamento.pesquisarContasEmitirCAER(
									colecaoContaTipo,
									idEmpresa, 
									numeroIndice,
									anoMesReferenciaFaturamento,
									faturamentoGrupo.getId());
							
							colecaoConta = formatarEmitirContasHelper(colecaoContaParms, 0);
							colecaoContaParms = null;
							
						} else {
							
							System.out.println("***********************************************");
							System.out.println("PESQUISA CONTAS COM ENDEREÇO RESP. (ÍNDICE-"+numeroIndice+")");
							System.out.println("**********************************************");
							
							colecaoContaTipo.add(ContaTipo.CONTA_CLIENTE_RESPONSAVEL);
							colecaoContaTipo.add(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP);

							if (faturamentoGrupo != null) {
								
								colecaoContaParms = 
									repositorioFaturamento.pesquisarContasClienteResponsavelCAER(
										colecaoContaTipo,
										numeroIndice,
										anoMesReferenciaFaturamento,
										faturamentoGrupo.getId(),
										indicadorEmissaoExtratoFaturamento);
							} else {
								
								colecaoContaParms = 
									repositorioFaturamento.pesquisarContasClienteResponsavelCAER(
										colecaoContaTipo,
										numeroIndice,
										anoMesReferenciaFaturamento,
										indicadorEmissaoExtratoFaturamento);
							}
							
							
							colecaoConta = formatarEmitirContasHelper(colecaoContaParms, 3);
							colecaoContaParms = null;
						}
						
						if (colecaoConta != null && !colecaoConta.isEmpty()) {

							if (colecaoConta.size() < quantidadeRegistros) {
								flagTerminou = true;
							}else if(ehFaturamentoAntecipado){
								numeroIndiceAntecipado = numeroIndiceAntecipado + 1000;	
							}							

							int metadeColecao = 0;
							if (colecaoConta.size() % 2 == 0) {
								metadeColecao = colecaoConta.size() / 2;
							} else {
								metadeColecao = (colecaoConta.size() / 2) + 1;
							}

							Map<Integer, Map<EmitirContaHelper, EmitirContaHelper>> mapContasDivididasOrdenada = 
								dividirColecao(colecaoConta);

							colecaoConta = null;

							EmitirContaHelper emitirContaHelper = null;

							if (mapContasDivididasOrdenada != null) {
								
								int countOrdem = 0;
								
								while (countOrdem < mapContasDivididasOrdenada.size()) {
									
									Map<EmitirContaHelper, EmitirContaHelper> mapContasDivididas = 
										mapContasDivididasOrdenada.get(countOrdem);

									Iterator iteratorConta = mapContasDivididas.keySet().iterator();

									while (iteratorConta.hasNext()) {

										emitirContaHelper = null;

										int situacao = 0;

										emitirContaHelper = (EmitirContaHelper) iteratorConta.next();
										
										while (situacao < 2) {
											
											if (situacao == 0) {
												
												situacao = 1;
												
												sequencialImpressao += 1;
												
											} else {
												
												emitirContaHelper = mapContasDivididas.get(emitirContaHelper);
												situacao = 2;
											}

											quantidadeContas++;
											
											if (emitirContaHelper != null) {

												int contaTipo = emitirContaHelper.getContaTipo();
												
												StringBuilder contaTxt = new StringBuilder();
												
												// 1 - Numero da Conta - Tam 10
												contaTxt.append(Util.completaString(emitirContaHelper.getIdConta().toString(),10));
												
												// 2 - Nome do Cliente - Tam 50
												if (contaTipo == 3 || contaTipo == 6) {
													
													String nomeClienteUsuario = null;
													
													if (emitirContaHelper.getNomeImovel() != null && 
														!emitirContaHelper.getNomeImovel().equals("")) {
														
														nomeClienteUsuario = emitirContaHelper.getNomeImovel();

													} else {
														
														try {
															
															nomeClienteUsuario = 
																repositorioFaturamento
																	.pesquisarNomeClienteUsuarioConta(emitirContaHelper
																			.getIdConta());

														} catch (ErroRepositorioException e) {
															throw new ControladorException("erro.sistema",e);
														}
													}
													contaTxt.append(
														Util.completaString(nomeClienteUsuario,50));
												} else {
													contaTxt.append(Util.completaString(
														emitirContaHelper.getNomeCliente(),50));
												}
												
												// 0 - Endereço sem municipio e UF
												// 1 - municipio
												// 2 - unidade federeção
												// 3 - bairro
												// 4 - CEP
												String[] enderecoArray = 
													this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(emitirContaHelper.getIdImovel());
												
												if(enderecoArray != null){
													
													// 3 - Endereço parte 1 - Tam 100
													contaTxt.append(Util.completaString(enderecoArray[0],100));

													// 4 - Endereço parte 2 (Bairro) - Tam 30
													contaTxt.append(Util.completaString(enderecoArray[3],30));
													
													// 5 - Endereço parte 3(Municipio,Uf,Cep) - Tam 41
													String restoEndereco = "";
													
													if(enderecoArray[1] != null){
														restoEndereco = enderecoArray[1];
													}
													
													if(enderecoArray[2] != null){
														if(restoEndereco.equals("")){
															restoEndereco = enderecoArray[2];
														}else{
															restoEndereco = restoEndereco+" "+enderecoArray[2];
														}
													}
													
													if(enderecoArray[4] != null){
														Cep cep = new Cep();
														cep.setCodigo(new Integer(enderecoArray[4]));
														
														if(restoEndereco.equals("")){
															restoEndereco = cep.getCepFormatado();
														}else{
															restoEndereco = restoEndereco+" "+cep.getCepFormatado();
														}
													}
													
													contaTxt.append(Util.completaString(restoEndereco,41));
													
												}else{
													// 3 - Endereço parte 1 - Tam 100
													// 4 - Endereço parte 2 - Tam 30
													// 5 - Endereço parte 3 - Tam 41
													contaTxt.append(Util.completaString(" ",171));
												}
												
												// 8 - Mês/Ano de Referencia - Tam 7
												String mesAnoReferencia = 
													Util.formatarAnoMesParaMesAno(emitirContaHelper.getAmReferencia());
												
												contaTxt.append(Util.completaString(mesAnoReferencia,7));

												// 9 - Data Vencimento - Tam 10
												String dataVencimento =
													Util.formatarData(emitirContaHelper.getDataVencimentoConta());
										
												contaTxt.append(Util.completaString(dataVencimento,10));
												
												// 10 - Matricula do Imovel - Tam 8
												String matriculaImovelFormatada = 
													Util.retornaMatriculaImovelFormatada(emitirContaHelper.getIdImovel());
												
												contaTxt.append(Util.completaString(matriculaImovelFormatada,8));

												// 11 - Inscrição do Imovel - Tam 20
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
												
												String inscricao = imovel.getInscricaoFormatada();
												
												contaTxt.append(Util.completaString(inscricao,20));

												// 12 - Rota - Tam 8
												contaTxt.append(Util.adicionarZerosEsquedaNumero(3,emitirContaHelper.getCodigoRota().toString()));
												contaTxt.append(".");
												
												if(emitirContaHelper.getNumeroSequencialRota() != null){
													contaTxt.append(Util.adicionarZerosEsquedaNumero(4,emitirContaHelper.getNumeroSequencialRota().toString()));
												}else{
													contaTxt.append(Util.adicionarZerosEsquedaNumero(4,"0"));
												}

												// 13 - Descrição da Categoria - Tam 13
												Categoria categoria = 
													this.getControladorImovel().obterPrincipalCategoriaImovel(emitirContaHelper.getIdImovel());
												contaTxt.append(Util.completaString(categoria.getDescricao(),13));
												
												// 14 - Numero de Economias - Tam 4
												Short quantidadeEconomiaConta = 0;
												try {
													quantidadeEconomiaConta = repositorioFaturamento
															.obterQuantidadeEconomiasConta(emitirContaHelper
																	.getIdConta());
												} catch (ErroRepositorioException e) {
													throw new ControladorException("erro.sistema", e);
												}

												contaTxt.append(Util.completaString(""+quantidadeEconomiaConta,4));
												
												Imovel imovelEmitido = 
													getControladorImovel().pesquisarImovel(emitirContaHelper.getIdImovel());
												
												// 15 - Numero do Hidrometro - Tam 10
												if (imovelEmitido.getLigacaoAgua() != null) {
													
													if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null){
														
														if (imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro() != null){
															
															contaTxt.append(Util.completaString(
																imovelEmitido.getLigacaoAgua().getHidrometroInstalacaoHistorico()
																	.getHidrometro().getNumero(), 10));
														} else{
															contaTxt.append(Util.completaString(" ", 10));
														}
															
													} else {
														contaTxt.append(Util.completaString(" ", 10));
													}
													
												} else {
													contaTxt.append(Util.completaString(" ", 10));
												}
												
												// [SB0003] - Determinar tipo de ligação e tipo de Medição
												Integer[] parmSituacao = 
													determinarTipoLigacaoMedicao(emitirContaHelper);
												
												Integer tipoLigacao = parmSituacao[0];
												Integer tipoMedicao = parmSituacao[1];
												
												// 16 - Descrição do tipo de consumo - Tam 20
												Object[] parmsConsumoHistorico = null;
												
												String descricaoTipoConsumo = "";
												String consumoMedio = "";
												//String descricaoAnormalidadeConsumo = null;
												
												if (tipoLigacao != null) {
													
													try {
														parmsConsumoHistorico = 
															repositorioMicromedicao.obterDadosConsumoConta(
																emitirContaHelper.getIdImovel(),
																emitirContaHelper.getAmReferencia(),
																tipoLigacao);

													} catch (ErroRepositorioException e) {
														throw new ControladorException("erro.sistema",e);
													}

													if (parmsConsumoHistorico != null) {

														// descrição tipo de consumo
														if (parmsConsumoHistorico[1] != null) {
															descricaoTipoConsumo = (String) parmsConsumoHistorico[1];
														}
														
														// Consumo médio
														if (parmsConsumoHistorico[2] != null) {
															consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
														}

														// descrição anormalidade de consumo
                                                        /*if (parmsConsumoHistorico[4] != null) {
                                                            descricaoAnormalidadeConsumo = (String) parmsConsumoHistorico[4];
                                                        }*/
													}
												}

												contaTxt.append(Util.completaString(descricaoTipoConsumo,20));
												

												// 17 - Consumo Faturado - Tam 4
												Object[] parmsMedicaoHistorico = 
													obterDadosMedicaoConta(emitirContaHelper,tipoMedicao);
												
												// Leitura Anterior
												String leituraAnterior = "";
												
												// Leitura Atual
												String leituraAtual = "";
												
												// Data Leitura Anterior
												String dataLeituraAnterior = "";
												
												// Leitura Anterior
												String dataLeituraAtual = "";
												
												if (parmsMedicaoHistorico != null) {

													if (parmsMedicaoHistorico[0] != null) {
														leituraAnterior = ""
																+ (Integer) parmsMedicaoHistorico[0];
													}

													if (parmsMedicaoHistorico[1] != null) {
														leituraAtual = ""
																+ (Integer) parmsMedicaoHistorico[1];
													}

													if (parmsMedicaoHistorico[3] != null) {
														dataLeituraAnterior = Util
																.formatarData((Date) parmsMedicaoHistorico[3]);
													}

													if (parmsMedicaoHistorico[2] != null) {
														dataLeituraAtual = Util
																.formatarData((Date) parmsMedicaoHistorico[2]);
													}

												}
												
												String diasConsumo = "";
												if (!dataLeituraAnterior.equals("") && 
													!dataLeituraAtual.equals("")) {
													
													diasConsumo = ""+Util.obterQuantidadeDiasEntreDuasDatas(
														(Date) parmsMedicaoHistorico[3],
														(Date) parmsMedicaoHistorico[2]);
												}
												
												String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
														emitirContaHelper,
														tipoMedicao,
														diasConsumo);
												
												String consumoFaturamento = parmsConsumo[0];
												
												Collection<Categoria> colecaoCategoria = new ArrayList();
												colecaoCategoria.add(categoria);
												Imovel imovelNaBase = getControladorImovel().pesquisarImovel(emitirContaHelper.getIdImovel());
												int consumoMinimo = 
													this.getControladorMicromedicao().obterConsumoMinimoLigacao(imovelNaBase, colecaoCategoria);
												
												int consumoAtual = 0;
												if(consumoFaturamento != null && !consumoFaturamento.equals("")){
													consumoAtual = Integer.parseInt(consumoFaturamento);
												}
												
												if (consumoMinimo > consumoAtual) {
													contaTxt.append(Util.completaString(consumoMinimo+"",4));
												} else{
													contaTxt.append(Util.completaString(consumoAtual+"",4));
												}
												/*contaTxt.append(Util.completaString(consumoFaturamento,4));*/

												// 18 - Consumo Estimado - Tam 4
												contaTxt.append(Util.completaString(" ",4));
												
												// 19 - Data da Leitura Anterior - Tam 10
												contaTxt.append(Util.completaString(dataLeituraAnterior,10));

												// 20 - Data da Leitura Atual - Tam 10
												contaTxt.append(Util.completaString(dataLeituraAtual,10));
												
												// 21 - Dias de consumo - Tam 2
												contaTxt.append(Util.completaString(diasConsumo,2));
												
												// 22 - Leitura Anterior - Tam 7
												contaTxt.append(Util.completaString(leituraAnterior,7));
												
												// 23 - Leitura Atual - Tam 7
												contaTxt.append(Util.completaString(leituraAtual,7));
												
												// 24 - Média de consumo - Tam 6
												contaTxt.append(Util.completaString(consumoMedio,6));
												
												// 25 - Referencia 1 - Tam 7
												// 26 - Consumo 1 - Tam 4
												String[] obterDadosConsumoMedicaoAnterior = 
													obterDadosConsumoAnteriorEmitirTXTContas(
														emitirContaHelper.getIdImovel(),
														emitirContaHelper.getAmReferencia(),
														1, tipoLigacao,
														tipoMedicao);
												
												if(obterDadosConsumoMedicaoAnterior != null){
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[0],7));
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[1],4));
												}else{
													contaTxt.append(Util.completaString(" ",11));
												}
												
												// 27 - Referencia 2 - Tam 7
												// 28 - Consumo 2 - Tam 4
												obterDadosConsumoMedicaoAnterior = 
													obterDadosConsumoAnteriorEmitirTXTContas(
														emitirContaHelper.getIdImovel(),
														emitirContaHelper.getAmReferencia(),
														2, 
														tipoLigacao,
														tipoMedicao);
												
												if(obterDadosConsumoMedicaoAnterior != null){
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[0],7));
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[1],4));
												}else{
													contaTxt.append(Util.completaString(" ",11));
												}
												
												// 29 - Referencia 3 - Tam 7
												// 30 - Consumo 3 - Tam 4
												obterDadosConsumoMedicaoAnterior = 
													obterDadosConsumoAnteriorEmitirTXTContas(
														emitirContaHelper.getIdImovel(),
														emitirContaHelper.getAmReferencia(),
														3, 
														tipoLigacao,
														tipoMedicao);
												
												if(obterDadosConsumoMedicaoAnterior != null){
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[0],7));
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[1],4));
												}else{
													contaTxt.append(Util.completaString(" ",11));
												}
												
												// 31 - Referencia 4 - Tam 7
												// 32 - Consumo 4 - Tam 4
												obterDadosConsumoMedicaoAnterior = 
													obterDadosConsumoAnteriorEmitirTXTContas(
														emitirContaHelper.getIdImovel(),
														emitirContaHelper.getAmReferencia(),
														4, 
														tipoLigacao,
														tipoMedicao);
												
												if(obterDadosConsumoMedicaoAnterior != null){
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[0],7));
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[1],4));
												}else{
													contaTxt.append(Util.completaString(" ",11));
												}
												
												// 33 - Referencia 5 - Tam 7
												// 34 - Consumo 5 - Tam 4
												obterDadosConsumoMedicaoAnterior = 
													obterDadosConsumoAnteriorEmitirTXTContas(
														emitirContaHelper.getIdImovel(),
														emitirContaHelper.getAmReferencia(),
														5, 
														tipoLigacao,
														tipoMedicao);
												
												if(obterDadosConsumoMedicaoAnterior != null){
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[0],7));
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[1],4));
												}else{
													contaTxt.append(Util.completaString(" ",11));
												}
												
												// 35 - Referencia 6 - Tam 7
												// 36 - Consumo 6 - Tam 4
												obterDadosConsumoMedicaoAnterior = 
													obterDadosConsumoAnteriorEmitirTXTContas(
														emitirContaHelper.getIdImovel(),
														emitirContaHelper.getAmReferencia(),
														6, 
														tipoLigacao,
														tipoMedicao);
												
												if(obterDadosConsumoMedicaoAnterior != null){
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[0],7));
													contaTxt.append(Util.completaString(obterDadosConsumoMedicaoAnterior[1],4));
												}else{
													contaTxt.append(Util.completaString(" ",11));
												}
												
												StringBuilder linhasTarifa = new StringBuilder();
												int quantidadeLinhaTarifa = 0;
												
												// 37 - Descricao Item 1 - Tam 55
												// 38 - Valor Item 1 - Tam 14
												if (emitirContaHelper.getValorAgua() != null && 
													emitirContaHelper.getValorAgua().compareTo(new BigDecimal("0.00")) == 1) {

													linhasTarifa.append(Util.completaString("TARIFA DE AGUA", 55));
													linhasTarifa.append(Util.completaString(
														Util.formatarMoedaReal(emitirContaHelper.getValorAgua()), 14));
													
													quantidadeLinhaTarifa++;
												}

												// 39 - Descricao Item 2 - Tam 55
												// 40 - Valor Item 2 - Tam 14
												if (emitirContaHelper.getValorEsgoto() != null && 
													emitirContaHelper.getValorEsgoto().compareTo(new BigDecimal("0.00")) == 1) {
													
													StringBuilder descricaoEsgoto = new StringBuilder();
													
													// caso o consumo de agua seja igual ao volume coletado de esgoto e o
													// valor de agua seja diferente de 0
													if (emitirContaHelper.getConsumoAgua().equals(emitirContaHelper.getConsumoEsgoto()) && 
														emitirContaHelper.getValorAgua() != null && 
														!emitirContaHelper.getValorAgua().equals("0.00")) {
														
														descricaoEsgoto.append("TARIFA DE ESGOTO=");
														
														// Percentual esgoto
														String percentualEsgoto = 
															Util.formatarMoedaReal(emitirContaHelper.getPercentualEsgotoConta());
														
														descricaoEsgoto.append(percentualEsgoto);
														// Constante
														descricaoEsgoto.append("% DA TARIFA DE AGUA");

													} else {
														
														descricaoEsgoto.append("TARIFA DE ESGOTO REF.A ");
														
														// Volume coletado de esgoto
														descricaoEsgoto.append(emitirContaHelper.getConsumoEsgoto());
														// Constante
														descricaoEsgoto.append(" M3");
													}
													
													linhasTarifa.append(Util.completaString(descricaoEsgoto.toString(), 55));
													linhasTarifa.append(Util.completaString(
														Util.formatarMoedaReal(emitirContaHelper.getValorEsgoto()), 14));
													
													quantidadeLinhaTarifa++;
												}

												
												// 41 - Descricao Item 3 - Tam 55
												// 42 - Valor Item 3 - Tam 14
												BigDecimal valorAcumuladoFinanciamento = new BigDecimal("0.00");
												Collection<DebitoCobrado> servicosCobrados = new ArrayList<DebitoCobrado>();
												
												if (emitirContaHelper.getDebitos() != null && 
													emitirContaHelper.getDebitos().compareTo(new BigDecimal("0.00")) == 1) {
													
													Conta contaId = new Conta();
													contaId.setId(emitirContaHelper.getIdConta());
													
													Collection<DebitoCobrado> cDebitoCobrado = 
														this.obterDebitosCobradosConta(contaId);
													
													if (cDebitoCobrado != null && !cDebitoCobrado.isEmpty()){
														
														BigDecimal valorAcumuladoParcelamento = new BigDecimal("0.00");
														StringBuilder descricaoDebitosParcelamento = null;
															
														for (Iterator iter = cDebitoCobrado.iterator(); iter.hasNext();) {
																
															DebitoCobrado debitoCobrado = (DebitoCobrado) iter.next();

															if(debitoCobrado.getDebitoTipo().getFinanciamentoTipo().getId().intValue() == 
																FinanciamentoTipo.PARCELAMENTO_AGUA || 
																debitoCobrado.getDebitoTipo().getFinanciamentoTipo().getId().intValue() == 
																FinanciamentoTipo.PARCELAMENTO_ESGOTO ||
																debitoCobrado.getDebitoTipo().getFinanciamentoTipo().getId().intValue() == 
																FinanciamentoTipo.PARCELAMENTO_SERVICO){
															
																if(descricaoDebitosParcelamento == null){
																	descricaoDebitosParcelamento = new StringBuilder();
																	
																	descricaoDebitosParcelamento.append("PARCELAMENTO DE DEBITOS");
																	descricaoDebitosParcelamento.append(" PARCELA ");
																	descricaoDebitosParcelamento.append(Util.completaString(
																			debitoCobrado.getNumeroPrestacaoDebito() + "/" +
																			debitoCobrado.getNumeroPrestacao(), 10));
																}
															
																valorAcumuladoParcelamento = 
																	valorAcumuladoParcelamento.add(debitoCobrado.getValorPrestacao());

															}else{
															
																servicosCobrados.add(debitoCobrado);
															
																valorAcumuladoFinanciamento = 
																	valorAcumuladoFinanciamento.add(debitoCobrado.getValorPrestacao());
															
															}
														}
														
														if(valorAcumuladoParcelamento.compareTo(new BigDecimal("0.00")) == 1){
															
															linhasTarifa.append(Util.completaString(descricaoDebitosParcelamento.toString(), 55));
															linhasTarifa.append(Util.completaString(
																Util.formatarMoedaReal(valorAcumuladoParcelamento), 14));
															
															quantidadeLinhaTarifa++;
														}
													}													
												}
												
												if (emitirContaHelper.getValorCreditos() != null && 
													emitirContaHelper.getValorCreditos().compareTo(new BigDecimal("0.00")) == 1) {

													linhasTarifa.append(Util.completaString("CREDITOS REALIZADOS", 55));
													linhasTarifa.append(Util.completaString(
														Util.formatarMoedaReal(emitirContaHelper.getValorCreditos()), 14));
													
													quantidadeLinhaTarifa++;
												}
												
												// 47 - Descricao Item 6 - Tam 55
												// 48 - Valor Item 6 - Tam 14
												if (emitirContaHelper.getValorImpostos() != null && 
													emitirContaHelper.getValorImpostos().compareTo(new BigDecimal("0.00")) == 1) {

													linhasTarifa.append(Util.completaString("IMPOSTOS RETIDOS", 55));
													linhasTarifa.append(Util.completaString(
														Util.formatarMoedaReal(emitirContaHelper.getValorImpostos()), 14));
													
													quantidadeLinhaTarifa++;
												}
												
												//[SB0033] - Gerar Linhas dos Débitos Cobrados de Serviços
												//Caso tenha algum espaço sobrando e colocado a descrição dos serviços
												//ao invés do acumulado
												if (servicosCobrados != null && !servicosCobrados.isEmpty()){

													for (Iterator iter = servicosCobrados.iterator(); iter.hasNext();) {
														
														quantidadeLinhaTarifa++;
														
														if(quantidadeLinhaTarifa < 6){
															
															DebitoCobrado debitoCobrado = (DebitoCobrado) iter.next();
															
															valorAcumuladoFinanciamento = 
																valorAcumuladoFinanciamento.subtract(debitoCobrado.getValorPrestacao());
															
															String descricaoDebitoTipo = 
																debitoCobrado.getDebitoTipo().getDescricao();
															
															if(descricaoDebitoTipo.length() < 48){
																
																if(debitoCobrado.getAnoMesReferenciaDebito() != null){

																	String mesAnoDebito = Util.formatarAnoMesParaMesAno(
																		debitoCobrado.getAnoMesReferenciaDebito());

																	descricaoDebitoTipo = descricaoDebitoTipo+" "+mesAnoDebito;
																}
																
																if(descricaoDebitoTipo.length() < 50){
																	descricaoDebitoTipo = descricaoDebitoTipo
																		+" "+debitoCobrado.getNumeroPrestacaoDebito()
																		+"/"+debitoCobrado.getNumeroPrestacao();
																}
															}
															
															linhasTarifa.append(Util.completaString(descricaoDebitoTipo, 55));
															linhasTarifa.append(Util.completaString(
																Util.formatarMoedaReal(debitoCobrado.getValorPrestacao()), 14));
															
															iter.remove();
															
														}else{
															break;
														}
													}
															
													if(servicosCobrados != null && servicosCobrados.size() > 1){
														
														linhasTarifa.append(Util.completaString("OUTROS DÉBITOS DE SERVIÇOS", 55));
														linhasTarifa.append(Util.completaString(
																Util.formatarMoedaReal(valorAcumuladoFinanciamento), 14));

													}else if (servicosCobrados != null && servicosCobrados.size() == 1){
														
														DebitoCobrado debitoCobrado = 
															(DebitoCobrado) Util.retonarObjetoDeColecao(servicosCobrados);
														
														String descricaoDebitoCobrado = 
															debitoCobrado.getDebitoTipo().getDescricao();
														
														if(descricaoDebitoCobrado.length() < 48){
															
															if(debitoCobrado.getAnoMesReferenciaDebito() != null){

																String mesAnoDebito = Util.formatarAnoMesParaMesAno(
																	debitoCobrado.getAnoMesReferenciaDebito());

																descricaoDebitoCobrado = descricaoDebitoCobrado+" "+mesAnoDebito;
															}
															
															if(descricaoDebitoCobrado.length() < 50){
																descricaoDebitoCobrado = descricaoDebitoCobrado
																	+" "+debitoCobrado.getNumeroPrestacaoDebito()
																	+"/"+debitoCobrado.getNumeroPrestacao();
															}
														}
														
														linhasTarifa.append(Util.completaString(
															descricaoDebitoCobrado, 55));
														
														linhasTarifa.append(Util.completaString(
															Util.formatarMoedaReal(debitoCobrado.getValorPrestacao()), 14));

													}
												}
												
												contaTxt.append(Util.completaString(linhasTarifa.toString(),414));
												
												// 49 - Valor Total - Tam 14
												Conta conta = new Conta();
												conta.setValorAgua(emitirContaHelper.getValorAgua());
												conta.setValorEsgoto(emitirContaHelper.getValorEsgoto());
												conta.setValorCreditos(emitirContaHelper.getValorCreditos());
												conta.setDebitos(emitirContaHelper.getDebitos());
												conta.setValorImposto(emitirContaHelper.getValorImpostos());
												
												BigDecimal valorConta = conta.getValorTotalContaBigDecimal();
												conta = null;

												String valorContaString = Util.formatarMoedaReal(valorConta);
												
												contaTxt.append(Util.completaString(valorContaString,14));
												
												// 50 - Mensagem 1 - Tam 70
												String[] parmsMensagemConta = 
													obterMensagemConta(emitirContaHelper,sistemaParametro,
														contaTipo,colecaoNacionalFeriado);
												
												//[SB0034] - Formatar mensagem com anormalidade de consumo
												/*if(descricaoAnormalidadeConsumo != null && !descricaoAnormalidadeConsumo.equals("")){
													for (int i = 0; i < parmsMensagemConta.length; i++) {
														String msg = parmsMensagemConta[i];
														
														if(msg == null || msg.equals("")){
															parmsMensagemConta[i] = 
																"ANORMALIDADE DE CONSUMO - "+descricaoAnormalidadeConsumo.trim();
															break;
														}
													}
												}*/
												
												contaTxt.append(Util.completaString(parmsMensagemConta[0],70));
												
												// 51 - Mensagem 2 - Tam 65
												contaTxt.append(Util.completaString(parmsMensagemConta[1],65));
												
												// 52 - Mensagem 3 - Tam 65
												contaTxt.append(Util.completaString(parmsMensagemConta[2],65));
												
												// 53 - Mensagem 4 - Tam 65
												contaTxt.append(Util.completaString(parmsMensagemConta[3],65));
												
												// 54 - Mensagem 5 - Tam 65
												contaTxt.append(Util.completaString(parmsMensagemConta[4],65));
												
												// 55 - Mensagem 6 - Tam 65
												//contaTxt.append(Util.completaString(parmsMensagemConta[5],65));
												contaTxt.append(Util.completaString("",65));
												
												// 56 - Mensagem 7 - Tam 65
												//contaTxt.append(Util.completaString(parmsMensagemConta[6],65));
												contaTxt.append(Util.completaString("",65));
												

												FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();

												Collection colecaoQualidadeAgua = null;

												filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
														FiltroQualidadeAgua.LOCALIDADE_ID,localidade.getId()));
												
												filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
														FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
														emitirContaHelper.getIdSetorComercial()));
												
												filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
														FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
												
												filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
												
												colecaoQualidadeAgua = getControladorUtil().pesquisar(
														filtroQualidadeAgua,QualidadeAgua.class.getName());

												if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
													
													filtroQualidadeAgua.limparListaParametros();
													
													colecaoQualidadeAgua = null;
													filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
															FiltroQualidadeAgua.LOCALIDADE_ID,localidade.getId()));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
															FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
													
													filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
													
													colecaoQualidadeAgua = getControladorUtil().pesquisar(
															filtroQualidadeAgua, QualidadeAgua.class.getName());
												}
												
												if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
													
													filtroQualidadeAgua.limparListaParametros();
													
													colecaoQualidadeAgua = null;
													filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.LOCALIDADE_ID));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
															FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
													
													filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
															FiltroQualidadeAgua.ANO_MES_REFERENCIA,emitirContaHelper.getAmReferencia()));
													
													filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
													
													colecaoQualidadeAgua = getControladorUtil().pesquisar(
															filtroQualidadeAgua, QualidadeAgua.class.getName());
												}
												
												QualidadeAgua qualidadeAgua = null;
												if(colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()){

													qualidadeAgua = 
														(QualidadeAgua) colecaoQualidadeAgua.iterator().next();
												}
												
												FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
												
												Collection colecaoQualidadeAguaPadrao = 
													getControladorUtil().pesquisar(filtroQualidadeAguaPadrao,
															QualidadeAguaPadrao.class.getName());
												
												QualidadeAguaPadrao qualidadeAguaPadrao = null;
												if(colecaoQualidadeAguaPadrao != null && !colecaoQualidadeAguaPadrao.isEmpty()){

													qualidadeAguaPadrao = 
														(QualidadeAguaPadrao) colecaoQualidadeAguaPadrao.iterator().next();
												}
												
												// 57 - Turbidez Valor Medio - Tam 5
												if(qualidadeAgua != null){
													contaTxt.append(Util.completaString(
															Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceTurbidez(),2,true),5));
													
												}else{
													contaTxt.append(Util.completaString("",5));
												}
												
												// 58 - Turbidez Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(
														qualidadeAguaPadrao.getDescricaoPadraoTurbidez(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 59 - Ph Valor Medio - Tam 5
												if(qualidadeAgua != null){
													contaTxt.append(Util.completaString(
														Util.formataBigDecimal(qualidadeAgua.getNumeroIndicePh(),2,true),5));
												}else{
													contaTxt.append(Util.completaString("",5));
												}
												
												// 60 - Ph Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoPh(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 61 - Cor Valor Medio - Tam 5
												if(qualidadeAgua != null){
													contaTxt.append(Util.completaString(
														Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceCor(),2,true),5));
												}else{
													contaTxt.append(Util.completaString("",5));
												}
												
												// 62 - Cor Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoCor(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 63 - Cloro Valor Medio - Tam 5
												if(qualidadeAgua != null){
													contaTxt.append(Util.completaString(
														Util.formataBigDecimal(qualidadeAgua.getNumeroCloroResidual(),2,true),5));
												}else{
													contaTxt.append(Util.completaString("",5));
												}
												
												// 64 - Cloro Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoCloro(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 65 - Fluor Valor Medio - Tam 5
												if(qualidadeAgua != null){
													contaTxt.append(Util.completaString(
														Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFluor(),2,true),5));
												}else{
													contaTxt.append(Util.completaString("",5));
												}
												
												// 66 - Fluor Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoFluor(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 67 - Ferro Valor Medio - Tam 5
												if(qualidadeAgua != null){
													contaTxt.append(Util.completaString(
														Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFerro(),2,true),5));
												}else{
													contaTxt.append(Util.completaString("",5));
												}
												
												// 68 - Ferro Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoFerro(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 69 - Coliformes Totais Valor Medio - Tam 10
												if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesTotais() == null || 
													qualidadeAgua.getNumeroIndiceColiformesTotais().compareTo(new BigDecimal("0.00")) == 0 ) ){

													contaTxt.append(Util.completaString("Ausente",10));															
													
												}else{
													contaTxt.append(Util.completaString(
														Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesTotais(),2,true),10));
												}
												
												// 70 - Coliformes Totais Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 71 - Coliformes Fecais Valor Medio - Tam 10
												if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesFecais() == null || 
													qualidadeAgua.getNumeroIndiceColiformesFecais().compareTo(new BigDecimal("0.00")) == 0 ) ){

													contaTxt.append(Util.completaString("Ausente",10));															
													
												}else{
													contaTxt.append(Util.completaString(
														Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesFecais(),2,true),10));
												}
												
												// 72 - Coliformes Fecais Valor Padrao - Tam 20
												if(qualidadeAguaPadrao != null){
													contaTxt.append(Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais(),20));
												}else{
													contaTxt.append(Util.completaString("",20));
												}
												
												// 73 - Representação Numerica Codigo de Barras - Tam 55

												//Caso seja debito automatico não gera codigo de barras
												if(contaTipo == 4 || contaTipo == 6){
													
													contaTxt.append(Util.completaString("", 55));
													contaTxt.append(Util.completaString("", 40));

												}else{

													String anoMesString = "" + emitirContaHelper.getAmReferencia();

													String mesAnoFormatado = 
														anoMesString.substring(4, 6) + anoMesString.substring(0, 4);
													
													Integer digitoVerificadorConta = 
														new Integer(""+ emitirContaHelper.getDigitoVerificadorConta());

													String representacaoNumericaCodBarra = null;
														
													representacaoNumericaCodBarra = this.getControladorArrecadacao()
																.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
																		emitirContaHelper.getIdLocalidade(),
																		emitirContaHelper.getIdImovel(), mesAnoFormatado,
																		digitoVerificadorConta, null, null, null, null,
																		null, null, null);
														
													contaTxt.append(Util.completaString(representacaoNumericaCodBarra, 55));
													
													// 74 - Codigo de Barras - Tam 40
													String representacaoNumericaCodBarraSemDigito = "";
													if (representacaoNumericaCodBarra != null) {
														representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
																.substring(0, 11)
																+ representacaoNumericaCodBarra.substring(12, 23)
																+ representacaoNumericaCodBarra.substring(24, 35)
																+ representacaoNumericaCodBarra.substring(36, 47);
													}
													
													// Cria o objeto para gerar o códigode barras no
													// padrão intercalado 2 de 5
													Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();
													
													// Recupera a representação númerica do código de
													// barras sem os dígitos verificadores
													if (representacaoNumericaCodBarraSemDigito != null && 
														!representacaoNumericaCodBarraSemDigito.equals("")) {
														
														contaTxt.append(
															Util.completaString(
																codigoBarraIntercalado2de5.encodeValue(
																	representacaoNumericaCodBarraSemDigito),40));
													} else {
														contaTxt.append(Util.completaString("", 40));
													}
												}
												
												
												// 75 - Origem - Tam 3
												if(emitirContaHelper.getDebitoCreditoSituacaoAtualConta() != null){
													
													if(emitirContaHelper.getDebitoCreditoSituacaoAtualConta().intValue() == 0){
														contaTxt.append(Util.completaString("1-9", 3));
													}else if(emitirContaHelper.getDebitoCreditoSituacaoAtualConta().intValue() == 1){
														contaTxt.append(Util.completaString("2-7", 3));
													}else if(emitirContaHelper.getDebitoCreditoSituacaoAtualConta().intValue() == 2){
														contaTxt.append(Util.completaString("3-5", 3));
													}else{
														contaTxt.append(Util.completaString("", 3));
													}
													
												}else{
													contaTxt.append(Util.completaString("", 3));	
												}
												
												
												// 76 - Data Limite para Pagamento - Tam 10
												String dataLimite =
													Util.formatarData(emitirContaHelper.getDataValidadeConta());
										
												contaTxt.append(Util.completaString(dataLimite,10));
												
												// 77 - Grupo Faturamento - Tam 3
												String grupo = "";
												if(faturamentoGrupo != null){
													grupo = faturamentoGrupo.getId().toString();
												}
												contaTxt.append(Util.completaString(grupo,3));
												
												// incrementa o sequencial de impressao
												sequencialImpressao = 
													atualizaSequencial(sequencialImpressao,situacao, metadeColecao);
												
												//78 - Sequencial de Impressao - Tam 7
												String sequencialImpressaoFormatada = 
													Util.adicionarZerosEsquedaNumero(6,""+ sequencialImpressao);
												
												sequencialImpressaoFormatada = 
													sequencialImpressaoFormatada.substring(0, 3)
													+ "."
													+ sequencialImpressaoFormatada.substring(3, 6);
												
												contaTxt.append(Util.completaString(""+ sequencialImpressaoFormatada,7));
												
												
												//79 - Informação para entrega do usuario responsavel - Tam 16
												Object[] arrayRota = null;
												if(contaTipo == 3 || contaTipo == 6){
													
													if(emitirContaHelper.getIdRotaEntrega() != null){

														Object[] arrayPesquisaRota = 
															this.repositorioCadastro.
																pesquisarDadosRotaEntregaContaPorRota(
																	new Integer(emitirContaHelper.getIdRotaEntrega()));
														
														arrayRota = new Object[4];
														
														arrayRota[0] = arrayPesquisaRota[0];
														arrayRota[1] = arrayPesquisaRota[1];
														arrayRota[2] = arrayPesquisaRota[2];
														arrayRota[3] = emitirContaHelper.getNumeroSequencialRotaEntrega();
														
													}else if (emitirContaHelper.getIdClienteResponsavel() != null && 
														!emitirContaHelper.getIdClienteResponsavel().equals("")) {
														
														arrayRota = 
															this.repositorioClienteImovel.
																pesquisarCodigoSequencialRotaPorUsuario(
																	new Integer(emitirContaHelper.getIdClienteResponsavel()));
													}
													
												}
												
												if(arrayRota != null){
													
													Integer idLocalidade = (Integer) arrayRota[0];
													Integer codigoSetor = (Integer) arrayRota[1];
													Short codigoRota = (Short) arrayRota[2];
													Integer sequencialRota = (Integer) arrayRota[3];
													String numeroQuadraEntrega = null;
													
													if(emitirContaHelper.getNumeroQuadraEntrega() != null){
														numeroQuadraEntrega = emitirContaHelper.getNumeroQuadraEntrega().toString();
													}

													contaTxt.append(Util.adicionarZerosEsquedaNumero(3,idLocalidade.toString()));
													contaTxt.append(".");
													contaTxt.append(Util.adicionarZerosEsquedaNumero(3,codigoSetor.toString()));
													contaTxt.append(".");
													contaTxt.append(Util.adicionarZerosEsquedaNumero(4,numeroQuadraEntrega));
													contaTxt.append(".");
													contaTxt.append(Util.adicionarZerosEsquedaNumero(3,codigoRota.toString()));
													contaTxt.append(".");
													contaTxt.append(Util.adicionarZerosEsquedaNumero(4,sequencialRota.toString()));
													
												}else{
													contaTxt.append(Util.completaString("", 21));
												}
												
												//80 - Matricula do Funcionario para desc. em folha - Tam 8
												if(emitirContaHelper.getIdFuncionario() != null){
													contaTxt.append(Util.completaString(emitirContaHelper.getIdFuncionario().toString(), 8));
												}else{
													contaTxt.append(Util.completaString("", 8));
												}
												
												//81 - Nome do Funcionario para desc. em folha - Tam 70
												if(emitirContaHelper.getNomeFuncionario() != null){
													contaTxt.append(Util.completaString(emitirContaHelper.getNomeFuncionario(), 70));
												}else{
													contaTxt.append(Util.completaString("", 70));
												}
												
												// txt recupera a string
												// builder
												// da
												// conta da
												// lista
												// de
												// contas
												contasTxtLista.append(contaTxt.toString());
												contasTxtLista.append(System.getProperty("line.separator"));
												contaTxt = null;

												// adiciona o id da
												// conta e o sequencial
												// no para serem
												// atualizados
												mapAtualizaSequencial.put(emitirContaHelper.getIdConta(),sequencialImpressao);
												
												if(flagTerminou && ehFaturamentoAntecipado){
													if(anoMesReferenciaFaturamentoAntecipado != null && 
														anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()){
														
														anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
														flagTerminou = false;
														numeroIndiceAntecipado = 0;
													}
												}
												

											}// fim do laço que
											// verifica
											// se o
											// helper é diferente de
											// nulo
											
										}// fim do laço que verifica
										// as 2
										// contas
										
									}// fim laço while do iterator do
									// objeto
									// helper
									
									countOrdem++;
									mapContasDivididas = null;
									
								}// fim do while que pega os Map
								// ordenados
								
							}// fim do laço que verifica se o Map
							// está
							// nula
							// fim do laço que verifica se a coleção é
							// nula
							
						} else {
							flagTerminou = true;
							if(ehFaturamentoAntecipado){
								if(anoMesReferenciaFaturamentoAntecipado != null && 
									anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()){
									
									anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
									flagTerminou = false;
									numeroIndiceAntecipado = 0;
								}
							}							
						}
						
						numeroIndice = numeroIndice + 1000;
						
						if(mapAtualizaSequencial != null){
							
							System.out.println("NUMERO_INDICE_ANTECIPADO:"+numeroIndiceAntecipado);
							System.out.println("NUMERO_INDICE:"+numeroIndice);
							System.out.println("QTD_CONTAS:"+quantidadeContas);
							
							repositorioFaturamento
									.atualizarSequencialContaImpressao(mapAtualizaSequencial);
						}						

						mapAtualizaSequencial = null;

					}// fim laço if da paginação

					String idGrupoFaturamento = null;

					if (faturamentoGrupo == null) {
						idGrupoFaturamento = "G";
					} else {
						idGrupoFaturamento = "G" + faturamentoGrupo.getId();
					}

					String nomeZip = null;

					switch (tipoConta) {
						case 0:
							nomeZip = "CONTA_NORMAL"+"_"+idGrupoFaturamento+mesReferencia;
							break;
						case 1:
							nomeZip = "CONTA_OUTRO_ENDERECO"+"_"+idGrupoFaturamento+mesReferencia;
							break;
					}

					BufferedWriter out = null;
					ZipOutputStream zos = null;

					File compactadoTipo = new File(nomeZip + ".zip");
					File leituraTipo = new File(nomeZip + ".txt");

					if (contasTxtLista != null && contasTxtLista.length() != 0) {

						// fim de arquivo
						//contasTxtLista.append("\u0004");
						
						// ************ TIPO E *************
						System.out.println("CRIANDO ZIP");
						zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
						out = new BufferedWriter(
							new OutputStreamWriter(
								new FileOutputStream(leituraTipo.getAbsolutePath())));
						
						out.write(contasTxtLista.toString());
						out.flush();
						
						ZipUtil.adicionarArquivo(zos, leituraTipo);
						zos.close();
						
						leituraTipo.delete();
						out.close();
					}

					// limpa todos os campos
					nomeZip = null;
					out = null;
					zos = null;
					compactadoTipo = null;
					leituraTipo = null;
					contasTxtLista = null;

				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

			} catch (IOException e) {
				String mensagem = e.getMessage();
				String[] inicioMensagem = mensagem.split("\\.");
				if (inicioMensagem != null
						&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
								.equals("atencao"))) {
					throw new ControladorException(mensagem);
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String mensagem = e.getMessage();
				if (mensagem != null) {
					String[] inicioMensagem = mensagem.split("\\.");
					if (inicioMensagem != null
							&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
									.equals("atencao"))) {
						throw new ControladorException(mensagem);
					} else {
						throw new ControladorException("erro.sistema", e);
					}
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			}

			// --------------------------------------------------------
			// Registrar o fim da execução da Unidade de Processamento
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido

			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);

			throw new EJBException(e);
		}

	}	
	
	
	/**
	 * [UC0099] Emitir TXT de Fatura de Cliente Responsável
	 * 
	 * @author Rafael Pinto
	 * @date 13/08/2007
	 * 
	 * @param colecaoFatura,anoMesFaturamentoCorrente
	 * @throws ControladorException
	 */
	public void emitirFaturaClienteResponsavel(Collection<Fatura> colecaoFatura, 
		Integer anoMesFaturamentoCorrente) throws ControladorException, IOException {

		// Ordena a coleção de faturas pelo código do cliente responsável
		Collections.sort((List) colecaoFatura, new Comparator() {
			public int compare(Object a, Object b) {
				Integer cliente1 = ((Fatura) a).getCliente().getId();
				Integer cliente2 = ((Fatura) b).getCliente().getId();

				return cliente1.compareTo(cliente2);
			}
		});

		// Cria a variável que vai armazenar o txt de fatura
		StringBuilder arquivoFaturaTXT = new StringBuilder();

		// Caso a coleção de faturas esteja preenchida
		// Emiti todas as faturas informadas
		if (colecaoFatura != null) {

			// Cria o sequêncial da fatura
			int quantidadeFaturas = 0;
			
			Integer quantidadeRegistros = FaturaItem.QTD_ITENS_RELATORIO_FATURA;
			Collection<NacionalFeriado> colecaoNacionalFeriado = 
				getControladorUtil().pesquisarFeriadosNacionais(); 
			// Laço para emitir todas as faturas
			for (Fatura fatura : colecaoFatura) {
				
				try {

					quantidadeFaturas++;
					System.out.println("QUANTIDADE FATURAS CLIENTE RESPONSAVEL :"+ quantidadeFaturas);
					
					// 1 - Numero da Fatura - Tam 9
					String numeroFatura = Util.completaString(fatura.getId().toString(),9);
					
					// 2 - Responsavel pelo Pagamento - Tam 50
					Cliente cliente = getControladorCliente().pesquisarCliente(fatura.getCliente().getId());

					String responsavelPagamento = Util.completaString(cliente.getNome(),50);
					
					// 3.1 - Endereço de Correspondencia (Parte 1) - Tam 100
					// 3.2 - Endereço de Correspondencia (Bairro) (Parte 2) - Tam 30
					// 3.3 - Endereço de Correspondencia (Municipio,Uf,Cep) (Parte 3) - Tam 41
					String enderecoParte1 = "";
					String bairro = "";
					String municipio = "";
					String uf = "";
					String cep = "";
					
					String[] endereco = 
						getControladorEndereco().pesquisarEnderecoClienteAbreviadoDividido(fatura.getCliente().getId());
					
					// endereço sem municipio e unidade federação
					enderecoParte1 = Util.completaString(endereco[0], 100);
					
					// nome do bairro
					bairro = Util.completaString(endereco[3], 30);
					
					// nome do municipio
					municipio = Util.completaString(endereco[1], 30);

					// sigla da unidade federação
					uf = Util.completaString(endereco[2], 2); 
					
					if(endereco[4] != null && !endereco[4].equals("")){
						Cep objetoCep = new Cep();
						objetoCep.setCodigo(new Integer(endereco[4]));

						cep = Util.completaString(objetoCep.getCepFormatado(), 9);
					}else{
						cep = Util.completaString("", 9);
					}
					
					// 4 - Mês/Ano - Tam 7
					String mesAno = 
						Util.completaString(Util.formatarAnoMesParaMesAno(fatura.getAnoMesReferencia()),7);
					
					// 5 - Tipo do Cliente Responsável - Tam 50
					String tipoCliente = Util.completaString(cliente.getClienteTipo().getDescricao(), 50);
					
					// 7 - Data de Emissão - Tam 10
					String dataEmissao = Util.completaString(Util.formatarData(fatura.getEmissao()), 10);
					
					// 8 - Data de Vencimento - Tam 10
					String dataVencimento = Util.completaString(Util.formatarData(fatura.getVencimento()), 10);
					
					// 9 - Valor Total - Tam 14
					String valorTotal = Util.completaString(Util.formatarMoedaReal(fatura.getDebito()),14);
					
					// 10 - Sequencia da Impressao
					String sequencialImpressao = Util.completaString(""+quantidadeFaturas,7);
					
					// 11 - Representação Numerica do código de Barras - Tam 55

					// Formata o ano/mês em mês/ano de referência da
					// conta no formato MM/AAAA
					String mesAnoReferenciaConta = Util
							.formatarAnoMesParaMesAno(fatura
									.getAnoMesReferencia());

					// Formata o ano/mês em mês/ano de referência da
					// conta no formato MMAAAA(sem a barra)
					String mesAnoReferenciaContaSemABarra = mesAnoReferenciaConta
							.substring(0, 2)
							+ mesAnoReferenciaConta.substring(3, 7);
					
					// Obtémo dígito verificador da referência da conta
					// no modulo 10(dez)
					Integer digitoVerificadorReferenciaContaModulo10 = Util
							.obterDigitoVerificadorModulo10(new Long(
									mesAnoReferenciaContaSemABarra));

					
					// Variável que vai armazenar a representação
					// numérica do código de barras
					String representacaoNumericaCodBarra = "";

					
					// Obtém a representação numérica do códigode
					// barra
					representacaoNumericaCodBarra =
							Util.completaString(
								this.getControladorArrecadacao().obterRepresentacaoNumericaCodigoBarra(
									7,
									fatura.getDebito(),
									null,
									null,
									mesAnoReferenciaContaSemABarra,
									digitoVerificadorReferenciaContaModulo10,
									null, null, null, null, 
									cliente.getId(), 
									fatura.getSequencial(), null
								),55
							);
						
										
					

					// 12 - Código de Barras da Fatura - Tam 112
					Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();

					// Recupera a representação númerica do código de
					// barras sem os dígitos verificadores
					String representacaoCodigoBarrasSemDigitoVerificador = 
						representacaoNumericaCodBarra.substring(0, 11) + 
						representacaoNumericaCodBarra.substring(12, 23) + 
						representacaoNumericaCodBarra.substring(24, 35) + 
						representacaoNumericaCodBarra.substring(36, 47);
					
					String codigoBarrasFatura = 
						Util.completaString(codigoBarraIntercalado2de5.encodeValue(
							representacaoCodigoBarrasSemDigitoVerificador),112);					
					
					// Formata a representação númerica do
					// código de
					// barras
					String representacaoNumericaCodBarraFormatada = 
						representacaoNumericaCodBarra.substring(0, 11) + " " + 
						representacaoNumericaCodBarra.substring(11, 12) + " " + 
						representacaoNumericaCodBarra.substring(12, 23) + " " + 
						representacaoNumericaCodBarra.substring(23, 24) + " " + 
						representacaoNumericaCodBarra.substring(24, 35) + " " + 
						representacaoNumericaCodBarra.substring(35, 36) + " " + 
						representacaoNumericaCodBarra.substring(36, 47) + " " + 
						representacaoNumericaCodBarra.substring(47, 48);
					
					FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
					
					Collection colecaoQualidadeAguaPadrao = 
						getControladorUtil().pesquisar(filtroQualidadeAguaPadrao,
								QualidadeAguaPadrao.class.getName());
					
					QualidadeAguaPadrao qualidadeAguaPadrao = null;

					if(colecaoQualidadeAguaPadrao != null && !colecaoQualidadeAguaPadrao.isEmpty()){

						qualidadeAguaPadrao = 
							(QualidadeAguaPadrao) colecaoQualidadeAguaPadrao.iterator().next();
					}
					
					FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
					
					filtroQualidadeAgua.adicionarParametro(new ParametroNulo(
						FiltroQualidadeAgua.LOCALIDADE_ID));

					filtroQualidadeAgua.adicionarParametro(new ParametroSimples(
						FiltroQualidadeAgua.ANO_MES_REFERENCIA, 
							fatura.getAnoMesReferencia()));
					
					Collection colecaoQualidadeAgua = 
						getControladorUtil().pesquisar(filtroQualidadeAgua,
								QualidadeAgua.class.getName());
					
					QualidadeAgua qualidadeAgua = null;
					if(colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()){

						qualidadeAgua = 
							(QualidadeAgua) colecaoQualidadeAgua.iterator().next();
					}
					
					
					// 13 - Turbidez Valor Medio - Tam 5
					String turbidezValorMedio = "";
					if(qualidadeAgua != null){
						turbidezValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceTurbidez(),2,true),5);
						
					}else{
						turbidezValorMedio = Util.completaString("",5);
					}
					
					// 14 - Turbidez Valor Padrao - Tam 20
					String turbidezValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						turbidezValorPadrao = Util.completaString(
							qualidadeAguaPadrao.getDescricaoPadraoTurbidez(),20);
					}else{
						turbidezValorPadrao = Util.completaString("",20);
					}
					
					// 15 - Ph Valor Medio - Tam 5
					String phValorMedio = "";
					if(qualidadeAgua != null){
						phValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroIndicePh(),2,true),5);
					}else{
						phValorMedio = Util.completaString("",5);
					}
					
					// 16 - Ph Valor Padrao - Tam 20
					String phValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						phValorPadrao = Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoPh(),20);
					}else{
						phValorPadrao = Util.completaString("",20);
					}
					
					// 17 - Cor Valor Medio - Tam 5
					String corValorMedio = "";
					if(qualidadeAgua != null){
						corValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceCor(),2,true),5);
					}else{
						corValorMedio = Util.completaString("",5);
					}
					
					// 18 - Cor Valor Padrao - Tam 20
					String corValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						corValorPadrao = Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoCor(),20);
					}else{
						corValorPadrao = Util.completaString("",20);
					}
					
					
					// 19 - Cloro Valor Medio - Tam 5
					String cloroValorMedio = "";
					if(qualidadeAgua != null){
						cloroValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroCloroResidual(),2,true),5);
					}else{
						cloroValorMedio = Util.completaString("",5);
					}
					
					// 20 - Cloro Valor Padrao - Tam 20
					String cloroValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						cloroValorPadrao = Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoCloro(),20);
					}else{
						cloroValorPadrao = Util.completaString("",20);
					}
					
					// 21 - Fluor Valor Medio - Tam 5
					String fluorValorMedio = "";
					if(qualidadeAgua != null){
						fluorValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFluor(),2,true),5);
					}else{
						fluorValorMedio = Util.completaString("",5);
					}
					
					// 22 - Fluor Valor Padrao - Tam 20
					String fluorValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						fluorValorPadrao = Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoFluor(),20);
					}else{
						fluorValorPadrao = Util.completaString("",20);
					}

					// 23 - Ferro Valor Medio - Tam 5
					String ferroValorMedio = "";
					if(qualidadeAgua != null){
						ferroValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFerro(),2,true),5);
					}else{
						ferroValorMedio = Util.completaString("",5);
					}
					
					// 24 - Ferro Valor Padrao - Tam 20
					String ferroValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						ferroValorPadrao = Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoFerro(),20);
					}else{
						ferroValorPadrao = Util.completaString("",20);
					}
					
					
					// 25 - Coliformes Totais Valor Medio - Tam 10
					String coliformesTotaisValorMedio = "";
					if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesTotais() == null || 
						qualidadeAgua.getNumeroIndiceColiformesTotais().compareTo(new BigDecimal("0.00")) == 0 ) ){

						coliformesTotaisValorMedio = Util.completaString("Ausente",10);															
						
					}else{
						coliformesTotaisValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesTotais(),2,true),10);
					}
					
					// 26 - Coliformes Totais Valor Padrao - Tam 20
					String coliformesTotaisValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						coliformesTotaisValorPadrao = 
							Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais(),20);
					}else{
						coliformesTotaisValorPadrao = Util.completaString("",20);
					}
					
					// 27 - Coliformes Fecais Valor Medio - Tam 10
					String coliformesFecaisValorMedio = "";
					if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesFecais() == null || 
						qualidadeAgua.getNumeroIndiceColiformesFecais().compareTo(new BigDecimal("0.00")) == 0 ) ){

						coliformesFecaisValorMedio = Util.completaString("Ausente",10);															
						
					}else{
						coliformesFecaisValorMedio = Util.completaString(
							Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesFecais(),2,true),10);
					}
					
					// 28 - Coliformes Fecais Valor Padrao - Tam 20
					String coliformesFecaisValorPadrao = "";
					if(qualidadeAguaPadrao != null){
						coliformesFecaisValorPadrao = 
							Util.completaString(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais(),20);
					}else{
						coliformesFecaisValorPadrao = Util.completaString("",20);
					}

					
					// 29 - Vencimento Alternativo 1 - Tam 10
					// 30 - Vencimento Alternativo 2 - Tam 10
					// 31 - Vencimento Alternativo 3 - Tam 10
					// 32 - Vencimento Alternativo 4 - Tam 10
					// 33 - Vencimento Alternativo 5 - Tam 10
					// 34 - Vencimento Alternativo 6 - Tam 10
					Date dataVencimentoFatura = fatura.getVencimento();
					Date dataSubtraida = null;
					
					String datasVencimentos = Util.formatarData(dataVencimentoFatura);
					
					for (int i = 0; i < 5; i++) {
						
						boolean naoEhValida = true;
						
						while(naoEhValida){
							
							if(dataSubtraida == null){
								dataSubtraida = Util.adicionarNumeroDiasDeUmaData(dataVencimentoFatura,-2);
							}else{
								dataSubtraida = Util.adicionarNumeroDiasDeUmaData(dataSubtraida,-2);
							}
							
							// Não considera os feriados municipais
							if(Util.ehDiaUtil(dataSubtraida,colecaoNacionalFeriado,null)){
								datasVencimentos = Util.formatarData(dataSubtraida)+datasVencimentos;
								naoEhValida = false;
							}else{
								dataSubtraida = Util.adicionarNumeroDiasDeUmaData(dataSubtraida,+1);
							}
						}
					}
					
					

					
					// Obtém a coleção dos itens da fatura atual
					List<FaturaItem> colecaoFaturaItem = 
						(ArrayList) repositorioFaturamento.pesquisarItemsFatura(fatura.getId());
					
					BigDecimal valorTotalImpostos = new BigDecimal("0.00");

					// Caso a coleção de itens da fatura estejam preenchidos
					if (colecaoFaturaItem != null && colecaoFaturaItem.size() > 0) {

						// 6 - Quantidade Itens da Fatura - Tam 4
						String quantidadeItemFatura = Util.completaString(""+colecaoFaturaItem.size(),4);

						// Calcula a quantidade de páginas
						Integer quantidadePaginas = 
							((Double) Math.ceil(Double.parseDouble(quantidadeItemFatura) / quantidadeRegistros)).intValue();
						
						int quantidadeItens = 0;
						for (int i = 1; i <= quantidadePaginas; i++) {
							
							//1
							arquivoFaturaTXT.append(numeroFatura);
							
							//2
							arquivoFaturaTXT.append(responsavelPagamento);
							
							//3
							arquivoFaturaTXT.append(enderecoParte1);
							arquivoFaturaTXT.append(bairro);
							arquivoFaturaTXT.append(municipio);
							arquivoFaturaTXT.append(uf);
							arquivoFaturaTXT.append(cep);
							
							//4
							arquivoFaturaTXT.append(mesAno);
							
							//5
							arquivoFaturaTXT.append(tipoCliente);
							
							//6
							arquivoFaturaTXT.append(quantidadeItemFatura);
							
							//7
							arquivoFaturaTXT.append(dataEmissao);
							
							//8
							arquivoFaturaTXT.append(dataVencimento);
							
							//9
							arquivoFaturaTXT.append(valorTotal);
							
							//10
							arquivoFaturaTXT.append(sequencialImpressao);
							
							//11
							arquivoFaturaTXT.append(representacaoNumericaCodBarraFormatada);
							
							//Só deve ser gerado na última Página de cada Fatura
							if(i == quantidadePaginas){
								//12
								arquivoFaturaTXT.append(codigoBarrasFatura);
							}else{
								arquivoFaturaTXT.append(Util.completaString("",112));
							}
							
							//13
							arquivoFaturaTXT.append(turbidezValorMedio);
							
							//14
							arquivoFaturaTXT.append(turbidezValorPadrao);
							
							//15
							arquivoFaturaTXT.append(phValorMedio);
							
							//16
							arquivoFaturaTXT.append(phValorPadrao);
							
							//17
							arquivoFaturaTXT.append(corValorMedio);
							
							//18
							arquivoFaturaTXT.append(corValorPadrao);
							
							//19
							arquivoFaturaTXT.append(cloroValorMedio);
							
							//20
							arquivoFaturaTXT.append(cloroValorPadrao);
							
							//21
							arquivoFaturaTXT.append(fluorValorMedio);
							
							//22
							arquivoFaturaTXT.append(fluorValorPadrao);
							
							//23
							arquivoFaturaTXT.append(ferroValorMedio);
							
							//24
							arquivoFaturaTXT.append(ferroValorPadrao);
							
							//25
							arquivoFaturaTXT.append(coliformesTotaisValorMedio);
							
							//26
							arquivoFaturaTXT.append(coliformesTotaisValorPadrao);
							
							//27
							arquivoFaturaTXT.append(coliformesFecaisValorMedio);
							
							//28
							arquivoFaturaTXT.append(coliformesFecaisValorPadrao);
							
							//29,30,31,32,33,34
							arquivoFaturaTXT.append(datasVencimentos);

							for (int j = 0; j < quantidadeRegistros; j++) {
								
								if(quantidadeItens >= colecaoFaturaItem.size()){
									arquivoFaturaTXT.append(Util.completaString("",82));
								}else{
									
									FaturaItem faturaItem = 
										(FaturaItem) colecaoFaturaItem.get(quantidadeItens);
									
									// 35.1 - Nome do Usuario - Tam 50
									String nomeUsuario = 
										Util.completaString(
											this.getControladorCliente().pesquisarNomeClientePorImovel(
												faturaItem.getImovel().getId()),50);
									
									// 35.2 - Matricula do Imovel - Tam 9
									String matriculaImovel = 
										Util.adicionarZerosEsquedaNumero(9,
											Util.retornaMatriculaImovelFormatada(faturaItem.getImovel().getId()));
									
									valorTotalImpostos = valorTotalImpostos.add(faturaItem.getValorImposto());
									
									// 35.3 - Consumo - Tam 9
									String consumo = Util.completaString(faturaItem.getNumeroConsumo().toString(),9);
									
									// 35.4 - Valor da Conta - Tam 14
									BigDecimal valorContaBruto = faturaItem.getValorConta().add(faturaItem.getValorImposto());
									String valorConta = 
										Util.completaString(Util.formatarMoedaReal(valorContaBruto),14);
									

									//35.1
									arquivoFaturaTXT.append(nomeUsuario);
									
									//35.2
									arquivoFaturaTXT.append(matriculaImovel);
									
									//35.3
									arquivoFaturaTXT.append(consumo);
									
									//35.4
									arquivoFaturaTXT.append(valorConta);
									
									
									quantidadeItens++;
								}

							}
							//36 Valor Bruto - Tam 14
							BigDecimal valTotalBruto = fatura.getDebito().add(valorTotalImpostos);
							String valorTotalBruto = Util.completaString(Util.formatarMoedaReal(valTotalBruto),14);
							arquivoFaturaTXT.append(valorTotalBruto);
							
							//37 Valor Impostos
							String valorTotImpostos = Util.completaString(Util.formatarMoedaReal(valorTotalImpostos),14);
							arquivoFaturaTXT.append(valorTotImpostos);
							
							arquivoFaturaTXT.append(System.getProperty("line.separator"));
						
						}
					}
					
					// Erro no repositório
				} catch (ErroRepositorioException ex) {
					// Seta o roolback na base
					// sessionContext.setRollbackOnly();

					// Levanta a exceção para o usuário
					throw new ControladorException("erro.sistema", ex);
				}
			}

			String mesReferencia = anoMesFaturamentoCorrente.toString().substring(4, 6);
			String anoReferencia = anoMesFaturamentoCorrente.toString().substring(0, 4);

			String nomeZip = "FATURAS_CLIENTE_RESPONSAVEL_" + mesReferencia+ "_" + anoReferencia;

			BufferedWriter out = null;
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeZip + ".zip");

			File leituraTipo = new File(nomeZip + ".txt");

			if (arquivoFaturaTXT != null && arquivoFaturaTXT.length() != 0) {
				try {
					System.out.println("CRIANDO ZIP");
					
					zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

					out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leituraTipo.getAbsolutePath())));
					
					out.write(arquivoFaturaTXT.toString());
					out.flush();
					
					ZipUtil.adicionarArquivo(zos, leituraTipo);
					zos.close();
					
					leituraTipo.delete();
					out.close();
					
				} catch (IOException ex) {
					throw new ControladorException("erro.sistema", ex);
				} catch (Exception e) {
					throw new ControladorException("erro.sistema", e);

				}

				nomeZip = null;
				out = null;
				zos = null;
				compactadoTipo = null;
				leituraTipo = null;
			}
			
			this.emitirRelacaoAnaliticaFaturas(colecaoFatura,anoMesFaturamentoCorrente);
			this.getControladorRelatorioFaturamento().emitirRelacaoSinteticaFaturas(colecaoFatura,anoMesFaturamentoCorrente);
		}
	}
	
	
	
	/**
	 * [UC0482]Emitir 2ª Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/2007
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContasHistorico(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,Short contaSemCodigoBarras)
			throws ControladorException {

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = new ArrayList();
		Collection colecaoNacionalFeriado = getControladorUtil().pesquisarFeriadosNacionais();

		Iterator iter = idsContaEP.iterator();

		while (iter.hasNext()) {
			Integer idContaEP = (Integer) iter.next();

			Collection colectionConta;
			try {
				colectionConta = this.repositorioFaturamento.pesquisarContaHistoricoERota(idContaEP);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) colectionConta.iterator().next();

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			String nomeCliente = "";
			if (emitirContaHelper.getNomeImovel() != null && !emitirContaHelper.getNomeImovel().equals("")) {
				
				nomeCliente = emitirContaHelper.getNomeImovel();
				emitirContaHelper.setNomeCliente(nomeCliente);
			}
			
			// Linha 5
			// --------------------------------------------------------------
			// recupera endereco do imóvel
			String[] enderecoArray = 
				this.getControladorEndereco().pesquisarEnderecoFormatadoDividido(emitirContaHelper.getIdImovel());
			
			if(enderecoArray != null){
				// 3 - Endereço parte 1 
				emitirContaHelper.setEnderecoImovel(Util.completaString(enderecoArray[0],100));

				// 4 - Endereço parte 2 (Bairro) 
				emitirContaHelper.setEnderecoLinha2(Util.completaString(enderecoArray[3],30));
				
				// 5 - Endereço parte 3(Municipio,Uf,Cep) 
				String restoEndereco = "";
				
				if(enderecoArray[1] != null){
					restoEndereco = enderecoArray[1];
				}
				
				if(enderecoArray[2] != null){
					if(restoEndereco.equals("")){
						restoEndereco = enderecoArray[2];
					}else{
						restoEndereco = restoEndereco+" "+enderecoArray[2];
					}
				}
				
				if(enderecoArray[4] != null){
					Cep cep = new Cep();
					cep.setCodigo(new Integer(enderecoArray[4]));
					
					if(restoEndereco.equals("")){
						restoEndereco = cep.getCepFormatado();
					}else{
						restoEndereco = restoEndereco+" "+cep.getCepFormatado();
					}
				}
				
				emitirContaHelper.setEnderecoLinha3(Util.completaString(restoEndereco,41));
				
			}else{
				// 3 - Endereço parte 1 
				// 4 - Endereço parte 2 
				// 5 - Endereço parte 3 
				emitirContaHelper.setEnderecoImovel("");
				emitirContaHelper.setEnderecoLinha2("");
				emitirContaHelper.setEnderecoLinha3("");
			}

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
//			String idClienteResponsavel = "";
//			String enderecoClienteResponsavel = "";
//			Integer idImovelContaEnvio = emitirContaHelper.getIdImovelContaEnvio();
//			// caso a coleção de contas seja de entrega para o cliente
//			// responsável
//			if (idImovelContaEnvio != null
//					&& (idImovelContaEnvio.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) 
//						|| idImovelContaEnvio.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {
//				Integer idClienteResponsavelInteger = null;
//				idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(emitirContaHelper.getIdConta(),false);
//
//				if (idClienteResponsavel != null && !idClienteResponsavel.equals("")) {
//					idClienteResponsavel = idClienteResponsavelInteger.toString();
//					// [UC0085]Obter Endereco
//					enderecoClienteResponsavel = getControladorEndereco()
//							.pesquisarEnderecoClienteAbreviado(idClienteResponsavelInteger);
//				}
//
//			}
//			emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
//			emitirContaHelper.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

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
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 1, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes1(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 4
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 4, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes4(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 10
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 2
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 2, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes2(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 5
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 5, tipoLigacao, tipoMedicao);
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
					leituraAnormalidadeFaturamento = "" + (Integer) parmsMedicaoHistorico[5];
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
			// recupera os parametros de consumo faturamento e consumo médio
			// diário
			// [SB0005] - Obter Consumo Faturado e Consumo Médio Diário
			String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(emitirContaHelper, tipoMedicao, diasConsumo);
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
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 3, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes3(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medição Anterior
			// passando a quantidade de Meses Igual a 6
			// e o tipo de ligação e medição recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 6, tipoLigacao, tipoMedicao);
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
							emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(),tipoLigacao);
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
			emitirContaHelper.setConsumoMedio(consumoMedio);
			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);

			// Fim Chamar Sub-Fluxo

			// Linha 13
			// --------------------------------------------------------------

			// Inicio Chamar Sub-Fluxo
			// soma a quantidades de economias da tabela contaCategoria
			// [SB0007] - Obter Dados da Medição da Conta
			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(emitirContaHelper.getIdConta(), false);
			emitirContaHelper.setQuantidadeEconomiaConta("" + quantidadeEconomiaConta);
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
			if (quantidadeEconomiaConta != null
					&& !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util
						.formatarMoedaRealparaBigDecimal(""
								+ quantidadeEconomiaConta);
			}
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null
					&& qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal
						.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util
						.formatarMoedaReal(consumoEconomiaBigDecimal);
				emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(
						0, (consumoEconomia.length() - 3)));
			}

			// Inicio Chamar Sub-Fluxo
			// concatena os campos dos sub-fluxos anteriores
			// [SB0008] - Obter Dados da Medição da Conta
			StringBuilder codigoAuxiliar = new StringBuilder();
			// leitura situação atual
			// tipo de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaTipoConsumo, 1));
			// tipo de contrato
			codigoAuxiliar.append(Util.completaString("", 1));
			// anormalidade de leitura
			codigoAuxiliar.append(Util.completaString(
					leituraAnormalidadeFaturamento, 2));
			// anormalidade de consumo
			codigoAuxiliar.append(Util.completaString(
					descricaoAbreviadaAnormalidadeConsumo, 2));

			// perfil do imóvel
			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString(""
						+ emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}
			// dias do consumo
			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			// Consumo medio do imóvel
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			// Fim Chamar Sub-Fluxo
			emitirContaHelper
					.setCodigoAuxiliarString(codigoAuxiliar.toString());

			// chama o [SB0009] - Obter Mensagem de Rateio de Consumo Fixo
			// de Esgoto
			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico,
					tipoMedicao);
			// mensagem de rateio de consumo ou consumo fixo de esgoto
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo
					.toString());

			// Linha 16
			// --------------------------------------------------------------
			// chama o [SB0010] - Gerar Linhas da Descrição dos Serviços e
			// Tarifas

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = gerarLinhasDescricaoServicoTarifasRelatorio(
					emitirContaHelper, consumoRateio, parmsMedicaoHistorico, tipoMedicao, true);
			emitirContaHelper
					.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

			// StringBuilder linhasDescricaoServicosTarifasTotal =
			// gerarLinhasDescricaoServicoTarifas(emitirContaHelper,
			// consumoRateio, parmsMedicaoHistorico, tipoMedicao);
			// emitirContaHelper.setLinhasDescricaoServicosTarifasTotal(linhasDescricaoServicosTarifasTotal);
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

			emitirContaHelper.setValorContaString(Util
					.formatarMoedaReal(valorConta));
			
			if (contaSemCodigoBarras.equals(ConstantesSistema.SIM) || valorConta.compareTo(new BigDecimal("0.00")) == 0){
				emitirContaHelper.setContaSemCodigoBarras("1");
			}else{
				emitirContaHelper.setContaSemCodigoBarras("2");
			}

			// chama o [SB0016] - Obter Mensagem da Conta em 3 Partes
//			String[] parmsPartesConta = obterMensagemConta3Partes(
//					emitirContaHelper, sistemaParametro);
//
//			// Linha 18
//			// --------------------------------------------------------------
//			emitirContaHelper.setPrimeiraParte(parmsPartesConta[0]);
//
//			// Linha 19
//			// --------------------------------------------------------------
//			emitirContaHelper.setSegundaParte(parmsPartesConta[1]);
//
//			// Linha 20
//			// --------------------------------------------------------------
//			emitirContaHelper.setTerceiraParte(parmsPartesConta[2]);

			// Linha 21
			// --------------------------------------------------------------
			int anoMesReferenciaSubtraido = Util.subtrairMesDoAnoMes(
					emitirContaHelper.getAmReferencia(), 1);
			emitirContaHelper.setMesAnoFormatado(Util
					.formatarAnoMesParaMesAno(anoMesReferenciaSubtraido));

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
					numeroIndiceTurbidez = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]);
				}

				if (parmsQualidadeAgua[1] != null) {
					numeroCloroResidual = Util
							.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]);
				}
			}
			emitirContaHelper.setNumeroIndiceTurbidez(numeroIndiceTurbidez);
			emitirContaHelper.setNumeroCloroResidual(numeroCloroResidual);

			// Linha 23
			// --------------------------------------------------------------
			// Considerar as contas do tipo débito automático como tipo de conta
			// normal
			// [SB0018 - Gerar Linhas das DemaisContas]
			Integer digitoVerificadorConta = new Integer(""
					+ emitirContaHelper.getDigitoVerificadorConta());
			// formata ano mes para mes ano
			String anoMes = "" + emitirContaHelper.getAmReferencia();
			String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

			String representacaoNumericaCodBarra = "";
			
			representacaoNumericaCodBarra = this.getControladorArrecadacao()
						.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
								emitirContaHelper.getIdLocalidade(),
								emitirContaHelper.getIdImovel(), mesAno,
								digitoVerificadorConta, null, null, null, null,
								null, null, null);

			

			// Linha 24
			// Formata a representação númerica do código de barras
			String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
					.substring(0, 11)
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
			emitirContaHelper
					.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

			// Linha 25
			String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
					.substring(0, 11)
					+ representacaoNumericaCodBarra.substring(12, 23)
					+ representacaoNumericaCodBarra.substring(24, 35)
					+ representacaoNumericaCodBarra.substring(36, 47);
			emitirContaHelper
					.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
			// Linha28

			if (emitirContaHelper.getDataValidadeConta().compareTo(new Date()) == 1) {
				emitirContaHelper
						.setDataValidade(Util.formatarData(emitirContaHelper
								.getDataValidadeConta()));

			} else {
				// soma 60 dias a data atual
				Date dataValidadeConta = Util.adicionarNumeroDiasDeUmaData(
						new Date(), 60);

				int ano = Util.getAno(dataValidadeConta);
				int mes = Util.getMes(dataValidadeConta);
				Calendar calendar = new GregorianCalendar();
				calendar.set(Calendar.MONTH, mes - 1);
				calendar.set(Calendar.YEAR, ano);

				Collection colecaoDatasFeriados = new ArrayList();
				Iterator iterNacionalFeriado = colecaoNacionalFeriado
						.iterator();
				while (iterNacionalFeriado.hasNext()) {
					NacionalFeriado nacionalFeriado = (NacionalFeriado) iterNacionalFeriado
							.next();
					colecaoDatasFeriados.add(nacionalFeriado.getData());
				}

				calendar.set(Calendar.DAY_OF_MONTH, Util.obterUltimoDiaUtilMes(
						mes, ano, colecaoDatasFeriados));

				dataValidadeConta = calendar.getTime();

				emitirContaHelper.setDataValidade(Util
						.formatarData(dataValidadeConta));

			}

			// emitirContaHelper.setDataValidade(Util
			// .formatarData(emitirContaHelper.getDataValidadeConta()));
			
			emitirContaHelper.setCategoriaImovel(obterCategoriaImovelConta(idContaEP));
			
			
			FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.LOCALIDADE_ID, localidade.getId()));
			filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA, emitirContaHelper.getAmReferencia()));
			
			Collection colecaoQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua, QualidadeAgua.class.getName());
			
			QualidadeAgua qualidadeAgua = null;
			if(colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()){
				qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();
			}
			
			FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
			Collection colecaoQualidadeAguaPadrao = getControladorUtil().pesquisar(filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class.getName());
			
			QualidadeAguaPadrao qualidadeAguaPadrao = null;
			if(colecaoQualidadeAguaPadrao != null && !colecaoQualidadeAguaPadrao.isEmpty()){
				qualidadeAguaPadrao = (QualidadeAguaPadrao) colecaoQualidadeAguaPadrao.iterator().next();
			}

			//Turbidez Valor Medio 
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioTurbidez(Util.completaString(
						Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceTurbidez(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioTurbidez("");
			}
			
			//Turbidez Valor Padrao 
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoTurbidez(qualidadeAguaPadrao.getDescricaoPadraoTurbidez());
			}else{
				emitirContaHelper.setPadraoTurbidez("");
			}
			
			//Ph Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioPh(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndicePh(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioPh("");
			}
			
			//Ph Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoPh(qualidadeAguaPadrao.getDescricaoPadraoPh());
			}else{
				emitirContaHelper.setPadraoPh("");
			}
			
			//Cor Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioCor(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceCor(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioCor("");
			}
			
			//Cor Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoCor(qualidadeAguaPadrao.getDescricaoPadraoCor());
			}else{
				emitirContaHelper.setPadraoCor("");
			}
			
			//Cloro Valor Medio 
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioCloro(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroCloroResidual(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioCloro("");
			}
			
			//Cloro Valor Padrao 
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoCloro(qualidadeAguaPadrao.getDescricaoPadraoCloro());
			}else{
				emitirContaHelper.setPadraoCloro("");
			}
			
			//Fluor Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioFluor(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFluor(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioFluor("");
			}
			
			//Fluor Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoFluor(qualidadeAguaPadrao.getDescricaoPadraoFluor());
			}else{
				emitirContaHelper.setPadraoFluor("");
			}
			
			//Ferro Valor Medio
			if(qualidadeAgua != null){
				emitirContaHelper.setValorMedioFerro(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceFerro(),2,true),5));
			}else{
				emitirContaHelper.setValorMedioFerro("");
			}
			
			//Ferro Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoFerro(qualidadeAguaPadrao.getDescricaoPadraoFerro());
			}else{
				emitirContaHelper.setPadraoFerro("");
			}
			
			//Coliformes Totais Valor Medio
			if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesTotais() == null || 
				qualidadeAgua.getNumeroIndiceColiformesTotais().compareTo(new BigDecimal("0.00")) == 0 ) ){
				emitirContaHelper.setValorMedioColiformesTotais("Ausente");															
			}else{
				emitirContaHelper.setValorMedioColiformesTotais(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesTotais(),2,true),10));
			}
			
			//Coliformes Totais Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoColiformesTotais(qualidadeAguaPadrao.getDescricaoPadraoColiformesTotais());
			}else{
				emitirContaHelper.setPadraoColiformesTotais("");
			}
			
			//Coliformes Fecais Valor Medio
			if(qualidadeAgua == null || (qualidadeAgua.getNumeroIndiceColiformesFecais() == null || 
				qualidadeAgua.getNumeroIndiceColiformesFecais().compareTo(new BigDecimal("0.00")) == 0 ) ){

				emitirContaHelper.setValorMedioColiformesfecais("Ausente");															
				
			}else{
				emitirContaHelper.setValorMedioColiformesfecais(Util.completaString(
					Util.formataBigDecimal(qualidadeAgua.getNumeroIndiceColiformesFecais(),2,true),10));
			}
			
			//Coliformes Fecais Valor Padrao
			if(qualidadeAguaPadrao != null){
				emitirContaHelper.setPadraoColiformesfecais(qualidadeAguaPadrao.getDescricaoPadraoColiformesFecais());
			}else{
				emitirContaHelper.setPadraoColiformesfecais("");
			}

			Integer tipoConta = null;
			try {
				tipoConta = repositorioFaturamento.pesquisarTipoConta(idContaEP);
			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}
			
			if (tipoConta != null){

				String[] parmsMensagemConta = 
					obterMensagemConta(emitirContaHelper,sistemaParametro,tipoConta,colecaoNacionalFeriado);
				
				//Mensagem 1
				emitirContaHelper.setMsgConta(Util.completaString(parmsMensagemConta[0],40));
				
				//Mensagem 2
				emitirContaHelper.setMsgLinha1Conta(Util.completaString(parmsMensagemConta[1],65));
				
				//Mensagem 3
				emitirContaHelper.setMsgLinha2Conta(Util.completaString(parmsMensagemConta[2],65));
				
				//Mensagem 4
				emitirContaHelper.setDatasVencimento(Util.completaString(parmsMensagemConta[3],65));
				
				//Mensagem 5
				emitirContaHelper.setMsgLinha3Conta(Util.completaString(parmsMensagemConta[4],65));
				
				//Mensagem 6
				emitirContaHelper.setMsgLinha4Conta(Util.completaString(parmsMensagemConta[5],65));
	
				//Mensagem 7
				emitirContaHelper.setMsgLinha5Conta(Util.completaString(parmsMensagemConta[6],65));
				
			}			
			
			colecaoEmitirContaHelper.add(emitirContaHelper);

			if (cobrarTaxaEmissaoConta) {
				this.gerarDebitoACobrarTaxaEmissaoConta(emitirContaHelper
						.getIdImovel(), emitirContaHelper.getAmReferencia());
			}

		}

		return colecaoEmitirContaHelper;
	}

	
	/**
	 * [UC0482] Emitir Segunda Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 04/09/2007
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarTaxaEmissaoConta(Integer idImovel,
			int anoMesReferencia) throws ControladorException {

		try {

			Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

			// Recupera os parametros do sistema
			SistemaParametro sistema = getControladorUtil().pesquisarParametrosDoSistema();

			// Instância a forma de cobrança para cobrança em conta
			CobrancaForma cobrancaForma = new CobrancaForma();
			cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);

			// Instância a situação do débito para normal
			DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
			debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);

			// Recupera o tipo de débito referente a despesa postal
			DebitoTipo debitoTipo = this.getDebitoTipoHql(DebitoTipo.TAXA_COBRANCA);

			// Recupera a data atual
			Date dataAtual = new Date(System.currentTimeMillis());

			// inclui Débito A Cobrar Geral
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral.setIndicadorHistorico(DebitoACobrarGeral.INDICADOR_NAO_POSSUI_HISTORICO);
			debitoACobrarGeral.setUltimaAlteracao(new Date());
			Integer idDebitoACobrarGeral = (Integer) this.getControladorUtil().inserir(debitoACobrarGeral);
			debitoACobrarGeral.setId(idDebitoACobrarGeral);

			// Cria uma instância de débito a cobrar
			DebitoACobrar debitoACobrar = new DebitoACobrar();
			debitoACobrar.setId(debitoACobrarGeral.getId());
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

			// Seta o Imóvel
			debitoACobrar.setImovel(imovel);
			// Seta o Débito Tipo
			debitoACobrar.setDebitoTipo(debitoTipo);
			// Seta Data e Hora Atual
			debitoACobrar.setGeracaoDebito(dataAtual);
			// Seta ano/mês da conta emitida como 2 via
			debitoACobrar.setAnoMesReferenciaDebito(anoMesReferencia);
			// Seta Ano/Mês de Cobrança
			debitoACobrar.setAnoMesCobrancaDebito(sistema.getAnoMesArrecadacao());
			
			
			
			// Seta Ano/Mês Referência do Faturamento
			//Alteracao CRC1389 Data:09/03/2009 
			//Author: Rômulo Aurélio 
			//Analista: Rosana Carvalho

			int anoMesAtual =  Util.getAnoMesComoInt(new Date());
			
			if(sistema
					.getAnoMesFaturamento().compareTo(anoMesAtual) < 0){
			
				debitoACobrar.setAnoMesReferenciaContabil(anoMesAtual);
			
			}else{
				debitoACobrar.setAnoMesReferenciaContabil(sistema
						.getAnoMesFaturamento());
			}
			//Fim Alteracao CRC1389 Data:09/03/2009
			
			
			// Seta Valor do Débito
			BigDecimal valorFinal = sistema.getValorSegundaVia();
			debitoACobrar.setValorDebito(valorFinal);
			// Seta Número de Prestações do Débito
			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));
			// Seta Número de Prestações Cobradas
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));
			// Seta Localidade
			debitoACobrar.setLocalidade(imovel.getLocalidade());
			// Seta Quadra
			debitoACobrar.setQuadra(imovel.getQuadra());
			// Seta Código do Setor Comercial
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial().getCodigo());
			// Seta Número Quadra
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());
			// Seta Lote
			debitoACobrar.setNumeroLote(imovel.getLote());
			// Seta SubLote
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());
			// Seta Taxa de Juros do Financiamento
			debitoACobrar.setPercentualTaxaJurosFinanciamento(new BigDecimal("0"));
			// Seta Financiamento Tipo
			debitoACobrar.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo());
			// Seta Lançamento Item Contábil
			debitoACobrar.setLancamentoItemContabil(debitoTipo.getLancamentoItemContabil());
			// Seta Débito Crédito Situação
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);
			// Seta Cobrança Forma
			debitoACobrar.setCobrancaForma(cobrancaForma);
			// Seta a data de ultima alteração
			debitoACobrar.setUltimaAlteracao(new Date());

			Integer idDebitoACobrar = (Integer) this.getControladorUtil().inserir(debitoACobrar);

			debitoACobrar.setId(idDebitoACobrar);

			// Recupera Categorias por Imóvel
			Collection<Categoria> colecaoCategoria = this.getControladorImovel().obterQuantidadeEconomiasCategoria(imovel);
			// Recupera Valores por Categorias
			Collection<BigDecimal> colecaoValoresCategorias = this.getControladorImovel().obterValorPorCategoria(colecaoCategoria, valorFinal);
			// Insere débito a cobrar por categoria
			inserirDebitoACobrarCategoria(colecaoCategoria,	colecaoValoresCategorias, debitoACobrar);

		} catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	
	
	
	
	/**
	 * [UC0596] Inserir Qualidade de Agua
	 *
	 * @author Kássia Albuquerque
	 * @date 06/08/2007
	 *
	 * @return
	 * @throws ControladorException
	 */
	
	
	public void inserirQualidadeAgua(QualidadeAgua qualidadeAgua,Collection colecaoQualidadeAgua,
			Usuario usuarioLogado,QualidadeAguaPadrao qualidadeAguaPadrao)  throws ControladorException{
		

		if (colecaoQualidadeAgua!= null && !colecaoQualidadeAgua.isEmpty()){
			
			Iterator IteratorColecaoQualidadeAgua = colecaoQualidadeAgua.iterator();
			
			while (IteratorColecaoQualidadeAgua.hasNext()){
				
				QualidadeAgua qualidadeAguaColecao = (QualidadeAgua)IteratorColecaoQualidadeAgua.next();
				
				//  -------- REGISTRAR TRANSAÇÃO------------------
				
				RegistradorOperacao registradorOperacao = new RegistradorOperacao( Operacao.OPERACAO_QUALIDADE_AGUA_INSERIR, 
						new UsuarioAcaoUsuarioHelper(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

				Operacao operacao = new Operacao();
				operacao.setId(Operacao.OPERACAO_QUALIDADE_AGUA_INSERIR);

				OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
				operacaoEfetuada.setOperacao(operacao);

				qualidadeAguaColecao.setOperacaoEfetuada(operacaoEfetuada);
				qualidadeAguaColecao.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(qualidadeAguaColecao);
				// ------------ REGISTRAR TRANSAÇÃO----------------------------
				
				getControladorUtil().inserir(qualidadeAguaColecao);
				
			}
			
		}else{
			
			//	 [FS0005 - VERIFICAR EXISTÊNCIA DA QUALIDADE DE ÁGUA PARA LOCALIDADE]
			
			if ((qualidadeAgua.getSetorComercial()== null || qualidadeAgua.getSetorComercial().equals("")) 
					&& (qualidadeAgua.getLocalidade()!= null && !qualidadeAgua.getLocalidade().equals(""))){
				
				FiltroQualidadeAgua filtroQualidadeAgua= new FiltroQualidadeAgua();
				
				filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("localidade");
				
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA,
						qualidadeAgua.getAnoMesReferencia().toString()));
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.LOCALIDADE_ID,
						qualidadeAgua.getLocalidade().getId().toString()));
				filtroQualidadeAgua.adicionarParametro(new ParametroNulo(FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
				
				Collection colecaoExistenciaQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua,
						QualidadeAgua.class.getName());
				
				if (colecaoExistenciaQualidadeAgua != null && !colecaoExistenciaQualidadeAgua.isEmpty()){
					
					throw new ControladorException( "atencao.faturamento.qualidade_agua_existente", null,
							qualidadeAgua.getLocalidade().getId().toString());
				}
				
			}else if ((qualidadeAgua.getSetorComercial()!= null && !qualidadeAgua.getSetorComercial().equals("")) 
					&& (qualidadeAgua.getLocalidade()!= null && !qualidadeAgua.getLocalidade().equals(""))){
				
				//  [FS0006	- VERIFICAR EXISTÊNCIA DA QUALIDADE DE ÁGUA PARA SETOR COMERCIAL]
				
				FiltroQualidadeAgua filtroQualidadeAgua= new FiltroQualidadeAgua();
				
				filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ANO_MES_REFERENCIA,
						qualidadeAgua.getAnoMesReferencia().toString()));
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.LOCALIDADE_ID,
						qualidadeAgua.getLocalidade().getId().toString()));
				filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
						qualidadeAgua.getSetorComercial().getId().toString()));
				
				Collection colecaoExistenciaQualidadeAgua = getControladorUtil().pesquisar(filtroQualidadeAgua,
						QualidadeAgua.class.getName());
				
				if (colecaoExistenciaQualidadeAgua != null && !colecaoExistenciaQualidadeAgua.isEmpty()){
					
					QualidadeAgua qualidadeAguaExistente = (QualidadeAgua)colecaoExistenciaQualidadeAgua.iterator().next();
					throw new ControladorException( "atencao.faturamento.qualidade_agua_existente_setor_comercial", null,
							""+qualidadeAguaExistente.getLocalidade().getId()+"/" + qualidadeAguaExistente.getSetorComercial().getCodigo());
				}
				
			}
			
			qualidadeAgua.setUltimaAlteracao(new Date());
			
			// -------- REGISTRAR TRANSAÇÃO------------------
			
			RegistradorOperacao registradorOperacao = new RegistradorOperacao( Operacao.OPERACAO_QUALIDADE_AGUA_INSERIR, 
					new UsuarioAcaoUsuarioHelper(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_QUALIDADE_AGUA_INSERIR);

			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);

			qualidadeAgua.setOperacaoEfetuada(operacaoEfetuada);
			qualidadeAgua.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(qualidadeAgua);
			
			// ------------ REGISTRAR TRANSAÇÃO-----------------
			
			getControladorUtil().inserir(qualidadeAgua);
			
			
			
			// ----------- INSERINDO QUALIDADE DE AGUA PADRAO -----------------

			qualidadeAguaPadrao.setUltimaAlteracao(new Date());
			
			
			
			getControladorUtil().atualizar(qualidadeAguaPadrao);
			
			
			
		}	
	}
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * Determina o tipo de conta que será associado na impressão da conta, para
	 * caer não tem o tipo de conta retida por BC ou retida por EC.
	 * 
	 * [SB0006 - ]
	 * 
	 * @author Raphael Rossiter,Sávio Luiz
	 * @date 09/12/2005,14/11/2007
	 * 
	 * @param imovel
	 * @throws ControladorException
	 */
	protected ContaTipo obterContaTipoParaContaImpressao(Conta conta,
			Integer idClienteResponsavel, Imovel imovel) {

		ContaTipo contaTipo = new ContaTipo();

		contaTipo.setId(ContaTipo.CONTA_NORMAL);

		// Comentado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)

		/*
		 * else if (idClienteResponsavel != null) {
		 * 
		 * contaTipo.setId(ContaTipo.CONTA_CLIENTE_RESPONSAVEL); }
		 */

		// Colocado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)
		if (idClienteResponsavel != null
				&& imovel.getIndicadorDebitoConta().shortValue() == ConstantesSistema.NAO
						.shortValue()) {

			contaTipo.setId(ContaTipo.CONTA_CLIENTE_RESPONSAVEL);
		}

		else if (idClienteResponsavel != null
				&& imovel.getIndicadorDebitoConta().shortValue() == ConstantesSistema.SIM
						.shortValue()) {

			contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTO_COM_CLIENTE_RESP);
		}

		// Comentado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)

		/*
		 * else if (imovel.getIndicadorDebitoConta() != null &&
		 * imovel.getIndicadorDebitoConta().equals( ConstantesSistema.SIM)) {
		 * 
		 * contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTOMATICO); }
		 */

		// Colocado por Raphael Rossiter em 01/03/2007 (Analista Responsável:
		// Aryed)
		else if (idClienteResponsavel == null
				&& imovel.getIndicadorDebitoConta().shortValue() == ConstantesSistema.SIM
						.shortValue()) {

			contaTipo.setId(ContaTipo.CONTA_DEBITO_AUTOMATICO);
		}

		return contaTipo;
	}
}
