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
package gcom.cobranca;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.bean.CalcularValorDataVencimentoAnteriorHelper;
import gcom.cobranca.bean.EmissaoDocumentoCobrancaHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.SessionBean;

import br.com.danhil.BarCode.Interleaved2of5;

/**
 * Controlador Cobranca CAERN
 *
 * @author Raphael Rossiter
 * @date 26/06/2006
 */
public class ControladorCobrancaCAERNSEJB extends ControladorCobranca implements SessionBean {

	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAERN
	//==============================================================================================================
	
	
	/**
	 * 
	 * Este caso de uso permite a emissão de um ou mais documentos de cobrança
	 * 
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * @author Raphael Rossiter
	 * @data 26/05/2006
	 * 
	 * @param
	 * @return void
	 */
	public void emitirDocumentoCobranca(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {

		boolean flagFimPesquisa = false;
		final int quantidadeCobrancaDocumento = 1000;
		int quantidadeCobrancaDocumentoInicio = 0;

		StringBuilder cobrancaDocumentoTxt = new StringBuilder();
		int sequencialImpressao = 0;

		Collection colecaoCobrancaDocumento = null;

		Map<Integer, Integer> mapAtualizaSequencial = null;

		Integer idCronogramaAtividadeAcaoCobranca = null;
		Integer idComandoAtividadeAcaoCobranca = null;
		Integer idAcaoCobranca = null;
		if (cobrancaAcaoAtividadeCronograma != null
				&& cobrancaAcaoAtividadeCronograma.getId() != null) {
			idCronogramaAtividadeAcaoCobranca = cobrancaAcaoAtividadeCronograma
					.getId();
		}
		if (cobrancaAcaoAtividadeComando != null
				&& cobrancaAcaoAtividadeComando.getId() != null) {
			idComandoAtividadeAcaoCobranca = cobrancaAcaoAtividadeComando
					.getId();
		}
		if (acaoCobranca != null && acaoCobranca.getId() != null) {
			idAcaoCobranca = acaoCobranca.getId();
		}

		while (!flagFimPesquisa) {
			// map que armazena o sequencial e o numero da
			// conta para no final atualizar todos os
			// sequencias
			mapAtualizaSequencial = new HashMap();

			try {

				System.out.println("***************************************");
				System.out.println("ENTROU NO AVISO DE CORTE");
				System.out.println("***************************************");
				colecaoCobrancaDocumento = repositorioCobranca
						.pesquisarCobrancaDocumentoParaEmitirPorRota(
								idCronogramaAtividadeAcaoCobranca,
								idComandoAtividadeAcaoCobranca,
								dataAtualPesquisa, idAcaoCobranca,
								quantidadeCobrancaDocumentoInicio);
				System.out.println("***************************************");
				System.out.println("QTD DE COBRANCA DOCUMENTO:"
						+ colecaoCobrancaDocumento.size());
				System.out.println("***************************************");
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}

			if (colecaoCobrancaDocumento != null
					&& !colecaoCobrancaDocumento.isEmpty()) {

				System.out.println("***************************************");
				System.out.println("QUANTIDADE COBRANÇA:"
						+ colecaoCobrancaDocumento.size());
				System.out.println("***************************************");

				if (colecaoCobrancaDocumento.size() < quantidadeCobrancaDocumento) {
					flagFimPesquisa = true;
				} else {
					quantidadeCobrancaDocumentoInicio = quantidadeCobrancaDocumentoInicio + 1000;
				}

				int metadeColecao = 0;
				if (colecaoCobrancaDocumento.size() % 2 == 0) {
					metadeColecao = colecaoCobrancaDocumento.size() / 2;
				} else {
					metadeColecao = (colecaoCobrancaDocumento.size() / 2) + 1;
				}

				Map<Integer, Map<Object, Object>> mapCobrancaDocumentoOrdenada = dividirColecao(colecaoCobrancaDocumento);

				if (mapCobrancaDocumentoOrdenada != null) {
					int countOrdem = 0;

					while (countOrdem < mapCobrancaDocumentoOrdenada.size()) {
						Map<Object, Object> mapCobrancaoDocumentoDivididas = mapCobrancaDocumentoOrdenada
								.get(countOrdem);

						Iterator iteratorCobrancaDocumento = mapCobrancaoDocumentoDivididas
								.keySet().iterator();

						while (iteratorCobrancaDocumento.hasNext()) {

							CobrancaDocumento cobrancaDocumento = null;
							/*
							 * if(quantidadeContas == 48){
							 * System.out.println(""); }
							 */

							int situacao = 0;

							cobrancaDocumento = (CobrancaDocumento) iteratorCobrancaDocumento
									.next();

							String nomeCliente = null;
							Collection colecaoCobrancaDocumentoItem = null;
							Iterator iteratorColecaoCobrancaDocumento = colecaoCobrancaDocumento
									.iterator();

							/*
							 * Estes objetos auxiliarão na formatação da
							 * inscrição que será composta por informações do
							 * documento de cobrança e do imóvel a ele associado
							 */
							Imovel inscricao = null;
							SetorComercial setorComercialInscricao = null;
							Quadra quadraInscricao = null;

							/*
							 * Objeto que será utilizado para armazenar as
							 * informações do documento de cobrança de acordo
							 * com o layout definido no caso de uso
							 */

							sequencialImpressao++;

							while (situacao < 2) {
								if (situacao == 0) {
									situacao = 1;
									sequencialImpressao = atualizaSequencial(
											sequencialImpressao, situacao,
											metadeColecao);

								} else {
									cobrancaDocumento = (CobrancaDocumento) mapCobrancaoDocumentoDivididas
											.get(cobrancaDocumento);
									situacao = 2;
									sequencialImpressao = atualizaSequencial(
											sequencialImpressao, situacao,
											metadeColecao);
								}

								if (cobrancaDocumento != null) {

									try {

										nomeCliente = this.repositorioClienteImovel
												.pesquisarNomeClientePorImovel(cobrancaDocumento
														.getImovel().getId());

										colecaoCobrancaDocumentoItem = this.repositorioCobranca
												.selecionarCobrancaDocumentoItemReferenteConta(cobrancaDocumento);

									} catch (ErroRepositorioException ex) {
										ex.printStackTrace();
										throw new ControladorException(
												"erro.sistema", ex);
									}

									if (colecaoCobrancaDocumentoItem != null
											&& !colecaoCobrancaDocumentoItem
													.isEmpty()) {

										// Início do processo de geração do
										// arquivo
										// txt

										// LINHA 01
										// ==================================

										/*
										 * Canal ("1") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 35));

										// Nome da Localidade
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																cobrancaDocumento
																		.getLocalidade()
																		.getDescricao(),
																30));
										
										//Rota
										if (cobrancaDocumento.getQuadra() != null){
										
											cobrancaDocumentoTxt.append(Util
											.completaString("", 5));
													
											cobrancaDocumentoTxt.append("Rota: ");
													
											cobrancaDocumentoTxt
											.append(Util.adicionarZerosEsquedaNumero(5, 
											cobrancaDocumento.getQuadra().getRota().getCodigo().toString()));
										
											if (cobrancaDocumento.getImovel() != null &&
												cobrancaDocumento.getImovel().getNumeroSequencialRota() != null){
										
												cobrancaDocumentoTxt.append("/");
												
												cobrancaDocumentoTxt
												.append(Util.adicionarZerosEsquedaNumero(5, 
												cobrancaDocumento.getImovel().getNumeroSequencialRota().toString()));
											
												cobrancaDocumentoTxt.append(Util
												.completaString("", 63));
											}
											else{
											
												cobrancaDocumentoTxt.append(Util
												.completaString("", 69));
											}
										}
										else{
											
											cobrancaDocumentoTxt.append(Util
													.completaString("", 85));
										}
										
										
										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 02
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Inscrição
										quadraInscricao = new Quadra();
										setorComercialInscricao = new SetorComercial();
										inscricao = new Imovel();

										quadraInscricao
												.setNumeroQuadra(cobrancaDocumento
														.getNumeroQuadra());
										setorComercialInscricao
												.setCodigo(cobrancaDocumento
														.getCodigoSetorComercial());
										inscricao
												.setLocalidade(cobrancaDocumento
														.getLocalidade());
										inscricao
												.setSetorComercial(setorComercialInscricao);
										inscricao.setQuadra(quadraInscricao);
										inscricao.setLote(cobrancaDocumento
												.getImovel().getLote());
										inscricao.setSubLote(cobrancaDocumento
												.getImovel().getSubLote());

										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																inscricao
																		.getInscricaoFormatada(),
																20));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 14));

										// Nome do Cliente
										cobrancaDocumentoTxt
												.append(Util.completaString(
														nomeCliente, 50));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 65));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 03
										// ==================================

										/*
										 * Canal ("+") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("+");
										cobrancaDocumentoTxt.append("2");
										cobrancaDocumentoTxt.append(Util
												.completaString("", 69));

										// Matrícula do imóvel
										String matriculaImovelFormatada = Util
												.retornaMatriculaImovelFormatada(cobrancaDocumento
														.getImovel().getId());

										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																matriculaImovelFormatada,
																10));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 71));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 04
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Endereço Formatado
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																cobrancaDocumento
																		.getImovel()
																		.getEnderecoFormatadoAbreviado(),
																72));
										cobrancaDocumentoTxt.append(Util
												.completaString("", 77));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 05
										// ==================================

										/*
										 * Canal ("+") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("+");
										cobrancaDocumentoTxt.append("2");
										cobrancaDocumentoTxt.append(Util
												.completaString("", 66));

										// Grupo de Cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																cobrancaDocumento
																		.getQuadra()
																		.getRota()
																		.getCobrancaGrupo()
																		.getId()
																		.toString(),
																2));

										cobrancaDocumentoTxt.append(" ");

										// Sequencial do Documento de Cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																""
																		+ cobrancaDocumento
																				.getNumeroSequenciaDocumento(),
																9));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 72));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 06
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Código da situação da ligação de água
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																cobrancaDocumento
																		.getImovel()
																		.getLigacaoAguaSituacao()
																		.getId()
																		.toString(),
																1));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 2));

										// Código da situação da ligação de
										// esgoto
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																cobrancaDocumento
																		.getImovel()
																		.getLigacaoEsgotoSituacao()
																		.getId()
																		.toString(),
																1));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 6));

										/*
										 * Quantidades de economias por
										 * categoria: 1º RESIDÊNCIAL 2º
										 * COMERCIAL 3º INDUSTRIAL 4º PÚBLICA
										 */
										Collection colecaoCategorias = this
												.getControladorImovel()
												.obterQuantidadeEconomiasCategoria(
														cobrancaDocumento
																.getImovel());
										String qtdResidencial = "";
										String qtdComercial = "";
										String qtdIndustrial = "";
										String qtdPublico = "";

										if (colecaoCategorias != null
												&& !colecaoCategorias.isEmpty()) {
											Iterator iteratorColecaoCategorias = colecaoCategorias
													.iterator();
											Categoria categoria = null;

											while (iteratorColecaoCategorias
													.hasNext()) {
												categoria = (Categoria) iteratorColecaoCategorias
														.next();

												if (categoria.getId().equals(
														Categoria.RESIDENCIAL)) {
													qtdResidencial = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												} else if (categoria
														.getId()
														.equals(
																Categoria.COMERCIAL)) {
													qtdComercial = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												} else if (categoria
														.getId()
														.equals(
																Categoria.INDUSTRIAL)) {
													qtdIndustrial = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												} else if (categoria
														.getId()
														.equals(
																Categoria.PUBLICO)) {
													qtdPublico = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												}
											}
										}
										colecaoCategorias = null;

										// Residêncial
										if (!qtdResidencial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdResidencial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 5));

										// Comercial
										if (!qtdComercial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdComercial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 8));

										// Industrial
										if (!qtdIndustrial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdIndustrial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 5));

										// Público
										if (!qtdPublico.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdPublico));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 11));

										// Perfil do Imóvel
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																cobrancaDocumento
																		.getImovelPerfil()
																		.getDescricao(),
																8));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 6));

										// Data de Emissão
										if (cobrancaDocumento.getEmissao() != null) {
											cobrancaDocumentoTxt
													.append(Util
															.formatarData(cobrancaDocumento
																	.getEmissao()));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 10));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 4));

										// Data de Validade
//										if (cobrancaDocumento.getDataValidade() != null) {
//											cobrancaDocumentoTxt.append(Util
//											.formatarData(cobrancaDocumento.getDataValidade()));
//										} else {
//											cobrancaDocumentoTxt.append(Util.completaString("", 10));
//										}
										
										//alterado por Vivianne Sousa 15/09/2008
										//Data de Validade
										Date dataValidade = this.
										obterDataValidadeDocumentoCobranca(cobrancaDocumento,null,null);
																				
										if (dataValidade != null) {
											cobrancaDocumentoTxt.append(Util.formatarData(dataValidade));
										} else {
											cobrancaDocumentoTxt.append(Util.completaString("", 10));
										}

										
										cobrancaDocumentoTxt.append(Util
												.completaString("", 60));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 07
										// ==================================

										/*
										 * Canal ("1") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("1");

										/*
										 * Selecionar os itens do documento de
										 * cobrança correspondentes a conta e
										 * ordenar por ano/mês de referência da
										 * conta
										 */
										if (colecaoCobrancaDocumentoItem != null
												&& !colecaoCobrancaDocumentoItem
														.isEmpty()) {

											int countImpressao = colecaoCobrancaDocumentoItem
													.size() - 26;

											Iterator iteratorColecaoCobrancaDocumentoItem = null;
											int contRegistros = 0;
											CobrancaDocumentoItem cobrancaDocumentoItem = null;

											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));

											/*
											 * Caso a quantidade de itens
											 * selecionados seja superior a 28
											 * [SB0001 - Calcular Valor e Data
											 * de Vencimento Anterior]
											 * 
											 * Caso contrário: Dados do primeiro
											 * e segundo itens selecionados
											 */
											if (colecaoCobrancaDocumentoItem
													.size() > 28) {

												CalcularValorDataVencimentoAnteriorHelper calcularValorDataVencimentoAnteriorHelper = this
														.calcularValorDataVencimentoAnterior(
																colecaoCobrancaDocumentoItem,
																28);

												// Constante "DEBTO.ATE"
												cobrancaDocumentoTxt
														.append("DEBTO.ATE");

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 5));

												// Data de Vencimento anterior
												// retornado
												// pelo
												// [SB0001]
												cobrancaDocumentoTxt
														.append(Util
																.formatarData(calcularValorDataVencimentoAnteriorHelper
																		.getDataVencimentoAnterior()));

												cobrancaDocumentoTxt
														.append(" ");

												// Valor anterior retornado pelo
												// [SB0001]
												cobrancaDocumentoTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		Util
																				.formatarMoedaReal(calcularValorDataVencimentoAnteriorHelper
																						.getValorAnterior()),
																		16));

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 5));

												/*
												 * Dados do primeiro ítem que
												 * não foi considerado anterior:
												 */

												// Mês/Ano de referência da
												// conta
												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		Util
																				.formatarAnoMesParaMesAno(calcularValorDataVencimentoAnteriorHelper
																						.getCobrancaDocumentoItemNaoAnterior()
																						.getContaGeral()
																						.getConta()
																						.getReferencia()),
																		9));

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 5));

												// Data de vencimento da conta
												cobrancaDocumentoTxt
														.append(Util
																.formatarData(calcularValorDataVencimentoAnteriorHelper
																		.getCobrancaDocumentoItemNaoAnterior()
																		.getContaGeral()
																		.getConta()
																		.getDataVencimentoConta()));

												cobrancaDocumentoTxt
														.append(" ");

												// Valor do item
												cobrancaDocumentoTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		Util
																				.formatarMoedaReal(calcularValorDataVencimentoAnteriorHelper
																						.getCobrancaDocumentoItemNaoAnterior()
																						.getValorItemCobrado()),
																		16));

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 60));

											} else {

												iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem
														.iterator();
												contRegistros = 0;
												cobrancaDocumentoItem = null;

												while (iteratorColecaoCobrancaDocumentoItem
														.hasNext()) {
													cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem
															.next();

													if (contRegistros == 2) {
														break;
													}

													// Mês/Ano de referência da
													// conta
													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			Util
																					.formatarAnoMesParaMesAno(cobrancaDocumentoItem
																							.getContaGeral()
																							.getConta()
																							.getReferencia()),
																			9));

													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			5));

													// Data de vencimento da
													// conta
													cobrancaDocumentoTxt
															.append(Util
																	.formatarData(cobrancaDocumentoItem
																			.getContaGeral()
																			.getConta()
																			.getDataVencimentoConta()));

													cobrancaDocumentoTxt
															.append(" ");

													// Valor do item
													cobrancaDocumentoTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(cobrancaDocumentoItem
																							.getValorItemCobrado()),
																			16));

													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			5));

													contRegistros++;
												}

												if (contRegistros < 2) {
													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			101));
												} else {
													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			55));
												}

											}

											cobrancaDocumentoTxt
													.append(System
															.getProperty("line.separator"));

											// LINHA 08
											// ==================================

											/*
											 * Canal ("") Fonte ("1")
											 */
											if (countImpressao <= 0) {
												iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem
														.iterator();
												contRegistros = 0;
												cobrancaDocumentoItem = null;

												while (iteratorColecaoCobrancaDocumentoItem
														.hasNext()) {
													cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem
															.next();

													if (contRegistros >= 2) {

														if (contRegistros % 2 == 0) {
															cobrancaDocumentoTxt
																	.append(" ");
															cobrancaDocumentoTxt
																	.append("1");
															cobrancaDocumentoTxt
																	.append(Util
																			.completaString(
																					"",
																					3));
														}

														// Mês/Ano de referência
														// da
														// conta
														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				Util
																						.formatarAnoMesParaMesAno(cobrancaDocumentoItem
																								.getContaGeral()
																								.getConta()
																								.getReferencia()),
																				9));

														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				"",
																				5));

														// Data de vencimento da
														// conta
														cobrancaDocumentoTxt
																.append(Util
																		.formatarData(cobrancaDocumentoItem
																				.getContaGeral()
																				.getConta()
																				.getDataVencimentoConta()));

														cobrancaDocumentoTxt
																.append(" ");

														// Valor do item
														cobrancaDocumentoTxt
																.append(Util
																		.completaStringComEspacoAEsquerda(
																				Util
																						.formatarMoedaReal(cobrancaDocumentoItem
																								.getValorItemCobrado()),
																				16));

														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				"",
																				5));

														if (contRegistros % 2 != 0) {
															cobrancaDocumentoTxt
																	.append(Util
																			.completaString(
																					"",
																					55));
															cobrancaDocumentoTxt
																	.append(System
																			.getProperty("line.separator"));
														}
													}

													contRegistros++;
												}
											} else {
												while (countImpressao < colecaoCobrancaDocumentoItem
														.size()) {
													cobrancaDocumentoItem = (CobrancaDocumentoItem) ((List) colecaoCobrancaDocumentoItem)
															.get(countImpressao);

													if (contRegistros % 2 == 0) {
														cobrancaDocumentoTxt
																.append(" ");
														cobrancaDocumentoTxt
																.append("1");
														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				"",
																				3));
													}

													// Mês/Ano de referência
													// da
													// conta
													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			Util
																					.formatarAnoMesParaMesAno(cobrancaDocumentoItem
																							.getContaGeral()
																							.getConta()
																							.getReferencia()),
																			9));

													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			5));

													// Data de vencimento da
													// conta
													cobrancaDocumentoTxt
															.append(Util
																	.formatarData(cobrancaDocumentoItem
																			.getContaGeral()
																			.getConta()
																			.getDataVencimentoConta()));

													cobrancaDocumentoTxt
															.append(" ");

													// Valor do item
													cobrancaDocumentoTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(cobrancaDocumentoItem
																							.getValorItemCobrado()),
																			16));

													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			5));

													if (contRegistros % 2 != 0) {
														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				"",
																				55));
														cobrancaDocumentoTxt
																.append(System
																		.getProperty("line.separator"));
													}

													countImpressao++;

													contRegistros++;
												}
											}

											if (contRegistros > 2) {
												if (contRegistros % 2 != 0) {
													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			101));
													cobrancaDocumentoTxt
															.append(System
																	.getProperty("line.separator"));
												}
											}
										}

										// LINHA 09
										// ==================================

										/*
										 * Canal ("1") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 10
										// ==================================

										/*
										 * Canal ("0") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("0");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 61));

										// Valor total do documento de cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.formatarMoedaReal(cobrancaDocumento
																				.getValorDocumento()),
																16));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 73));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 11
										// ==================================

										/*
										 * Canal ("1") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(" ");

										// Constante "GR- "
										cobrancaDocumentoTxt.append("GR- ");

										// Grupo de Cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																cobrancaDocumento
																		.getQuadra()
																		.getRota()
																		.getCobrancaGrupo()
																		.getId()
																		.toString(),
																2));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 2));

										// Código e descrição da empresa
										if (cobrancaDocumento.getEmpresa() != null) {
											cobrancaDocumentoTxt
													.append(Util
															.completaStringComEspacoAEsquerda(
																	cobrancaDocumento
																			.getEmpresa()
																			.getId()
																			.toString(),
																	2));

											cobrancaDocumentoTxt.append("- ");

											cobrancaDocumentoTxt
													.append(Util
															.completaString(
																	cobrancaDocumento
																			.getEmpresa()
																			.getDescricaoAbreviada(),
																	10));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 14));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 11));

										// Sigla e descriçao da gerência
										// regional
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																cobrancaDocumento
																		.getLocalidade()
																		.getGerenciaRegional()
																		.getNomeAbreviado(),
																3));

										cobrancaDocumentoTxt.append("-");

										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																cobrancaDocumento
																		.getLocalidade()
																		.getGerenciaRegional()
																		.getNome(),
																8));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 25));

										// Sequencial de impressão
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.retornaSequencialFormatado(sequencialImpressao),
																9));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 70));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 12
										// ==================================

										/*
										 * Canal ("") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append(" ");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 13
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 18));

										String representacaoNumericaCodBarra = "";

										
											// Obtém a representação numérica do
											// códigode
											// barra
										representacaoNumericaCodBarra = 
												
												this.getControladorArrecadacao().
												obterRepresentacaoNumericaCodigoBarra(
															5,
															cobrancaDocumento
																	.getValorDocumento(),
															cobrancaDocumento
																	.getLocalidade()
																	.getId(),
															cobrancaDocumento
																	.getImovel()
																	.getId(),
															null,
															null,
															null,
															null,
															String
																	.valueOf(cobrancaDocumento
																			.getNumeroSequenciaDocumento()),
															cobrancaDocumento
																	.getDocumentoTipo()
																	.getId(),
															null, null, null);

										

										// Formata a representação númerica do
										// código de
										// barras
										String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
												.substring(0, 11)
												+ " "
												+ representacaoNumericaCodBarra
														.substring(11, 12)
												+ " "
												+ representacaoNumericaCodBarra
														.substring(12, 23)
												+ " "
												+ representacaoNumericaCodBarra
														.substring(23, 24)
												+ " "
												+ representacaoNumericaCodBarra
														.substring(24, 35)
												+ " "
												+ representacaoNumericaCodBarra
														.substring(35, 36)
												+ " "
												+ representacaoNumericaCodBarra
														.substring(36, 47)
												+ " "
												+ representacaoNumericaCodBarra
														.substring(47, 48);

										cobrancaDocumentoTxt
												.append(representacaoNumericaCodBarraFormatada);

										cobrancaDocumentoTxt.append(Util
												.completaString("", 77));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 14
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 15
										// ==================================

										/*
										 * Canal ("") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append(" ");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 16
										// ==================================

										/*
										 * Canal ("-") Fonte ("3")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("3");

										// Cria o objeto para gerar o código de
										// barras
										// no
										// padrão
										// intercalado 2 de 5
										Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();

										// Recupera a representação númerica do
										// código
										// de
										// barras
										// sem
										// os dígitos verificadores
										String representacaoCodigoBarrasSemDigitoVerificador = representacaoNumericaCodBarra
												.substring(0, 11)
												+ representacaoNumericaCodBarra
														.substring(12, 23)
												+ representacaoNumericaCodBarra
														.substring(24, 35)
												+ representacaoNumericaCodBarra
														.substring(36, 47);

										cobrancaDocumentoTxt.append(Util
												.completaString("", 21));

										cobrancaDocumentoTxt
												.append(codigoBarraIntercalado2de5
														.encodeValue(representacaoCodigoBarrasSemDigitoVerificador));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 17));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 17
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Inscrição
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																inscricao
																		.getInscricaoFormatada(),
																20));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 14));

										// Data de Validade
//										if (cobrancaDocumento.getDataValidade() != null) {
//											cobrancaDocumentoTxt
//													.append(Util
//															.formatarData(cobrancaDocumento
//																	.getDataValidade()));
//										} else {
//											cobrancaDocumentoTxt.append(Util
//													.completaString("", 10));
//										}
										
										//alterado por Vivianne Sousa 15/09/2008
										//Data de Validade
										if (dataValidade != null) {
											cobrancaDocumentoTxt.append(Util.formatarData(dataValidade));
										} else {
											cobrancaDocumentoTxt.append(Util.completaString("", 10));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 105));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 18
										// ==================================

										/*
										 * Canal ("+") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("+");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 50));

										// Matrícula do imóvel
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																matriculaImovelFormatada,
																10));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 7));

										// Sequencial do documento de cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																""
																		+ cobrancaDocumento
																				.getNumeroSequenciaDocumento(),
																9));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 74));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 19
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 20
										// ==================================

										/*
										 * Canal ("0") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("0");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 61));

										// Valor total do documento de cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.formatarMoedaReal(cobrancaDocumento
																				.getValorDocumento()),
																16));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 73));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 21
										// ==================================

										/*
										 * Canal ("0") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("0");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA
										// 22==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(" ");

										// Constante "GR- "
										cobrancaDocumentoTxt.append("GR- ");

										// Grupo de Cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																cobrancaDocumento
																		.getQuadra()
																		.getRota()
																		.getCobrancaGrupo()
																		.getId()
																		.toString(),
																2));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 64));

										// Sequencial de impressão
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.retornaSequencialFormatado(sequencialImpressao),
																9));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 70));

										if (iteratorColecaoCobrancaDocumento
												.hasNext()) {
											cobrancaDocumentoTxt
													.append(System
															.getProperty("line.separator"));
										}

									}
									// adiciona o id da
									// conta e o sequencial
									// no para serem
									// atualizados
									mapAtualizaSequencial.put(cobrancaDocumento
											.getId(), sequencialImpressao);

									colecaoCobrancaDocumentoItem = null;
								}

							}// fim do laço que verifica
							// as 2
							// contas

						}// fim laço while do iterator do
						// objeto
						// helper
						countOrdem++;
						mapCobrancaoDocumentoDivididas = null;
					}
				}
			} else {
				flagFimPesquisa = true;
			}
			try {
				repositorioCobranca
						.atualizarSequencialCobrancaDocumentoImpressao(mapAtualizaSequencial);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
			mapAtualizaSequencial = null;
			colecaoCobrancaDocumento = null;
		}

		Date dataAtual = new Date();
		String nomeZip = null;
		if (idCronogramaAtividadeAcaoCobranca != null) {
			
			if(acaoCobranca != null && acaoCobranca.getId().equals(CobrancaAcao.VISITA_COBRANCA)){
				nomeZip = "VISITA_COBRANCA_" + grupoCobranca.getId() + "_"
				+ Util.formatarData(dataAtual);
			}else{
				nomeZip = "AVISO_CORTE_GRUPO_" + grupoCobranca.getId() + "_"
				+ Util.formatarData(dataAtual);
			}
			nomeZip = nomeZip.replace("/", "_");

		} else {
			String descricaoAbrevDocumentoTipo = "";
			if (acaoCobranca != null && acaoCobranca.getDocumentoTipo() != null) {
				descricaoAbrevDocumentoTipo = acaoCobranca.getDocumentoTipo()
						.getDescricaoAbreviado();
			}
			String tituloComandoEventual = cobrancaAcaoAtividadeComando
					.getDescricaoTitulo();

			nomeZip = descricaoAbrevDocumentoTipo + " " + tituloComandoEventual
					+ " " + Util.formatarData(dataAtual);
			nomeZip = nomeZip.replace("/", "_");
			nomeZip = nomeZip.replace(" ", "_");

		}

		// pegar o arquivo, zipar pasta e arquivo e escrever no stream
		try {

			System.out.println("***************************************");
			System.out.println("INICO DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

			if (cobrancaDocumentoTxt != null
					&& cobrancaDocumentoTxt.length() != 0) {

				cobrancaDocumentoTxt.append("\u0004");

				// criar o arquivo zip
				File compactado = new File(nomeZip + ".zip"); // nomeZip
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
						compactado));

				File leitura = new File(nomeZip + ".txt");
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(cobrancaDocumentoTxt.toString());
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			}
			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * Este caso de uso permite a emissão de um ou mais documentos de cobrança
	 * 
	 * [UC0476] Emitir Documento de Cobrança - Ordem de Corte
	 * 
	 * @author Ivan Sergio
	 * @data 21/05/2009
	 * 
	 * @param
	 * @return void
	 */
	public void emitirDocumentoCobrancaOrdemCorte(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {
		
		if (acaoCobranca.getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO) ||
            acaoCobranca.getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO_LIGADO_A_REVELIA)) {
			
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			this.emitirDocumentoCobrancaOrdemCorteAdministrativo(
					cobrancaAcaoAtividadeCronograma,
					cobrancaAcaoAtividadeComando,
					dataAtualPesquisa,
					acaoCobranca,
					grupoCobranca,
					cobrancaCriterio);
			
		} else if (acaoCobranca.getId().equals(CobrancaAcao.CORTE_FISICO) ||
				   acaoCobranca.getId().equals(CobrancaAcao.CORTE_FISICO_LIGADO_A_REVELIA) ||
				   acaoCobranca.getId().equals(CobrancaAcao.CORTE_FISICO_PUBLICO) ||
				   acaoCobranca.getId().equals(CobrancaAcao.RECORTE)) {

			// [UC0640] Gerar TXT Corte Fisico COMPESA
			this.gerarTxtCorteFisico(
					cobrancaAcaoAtividadeCronograma,
					cobrancaAcaoAtividadeComando,
					dataAtualPesquisa,
					acaoCobranca,
					grupoCobranca,
					cobrancaCriterio);
		}
	}	
	
	
	/**
	 * 
	 * Este caso de uso permite a emissão de um ou mais documentos de cobrança
	 * 
	 * [UC0476] Emitir Documento de Cobrança - Ordem de Corte
	 * 
	 * @author Ana Maria
	 * @data 07/09/2006
	 * 
	 * @param
	 * @return void
	 */
	private  void emitirDocumentoCobrancaOrdemCorteAdministrativo(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {

		boolean flagFimPesquisa = false;
		final int quantidadeCobrancaDocumento = 1000;
		int quantidadeCobrancaDocumentoInicio = 0;
		StringBuilder cobrancaDocumentoTxt = new StringBuilder();
		int sequencialImpressao = 0;

		System.out.println("***************************************");
		System.out.println("ENTROU NO CORTE ADMINISTRATIVO OU FISICO");
		System.out.println("***************************************");

		Collection colecaoEmitirDocumentoCobranca = null;
		Map<Integer, Integer> mapAtualizaSequencial = null;

		Integer idCronogramaAtividadeAcaoCobranca = null;
		Integer idComandoAtividadeAcaoCobranca = null;
		Integer idAcaoCobranca = null;
		if (cobrancaAcaoAtividadeCronograma != null
				&& cobrancaAcaoAtividadeCronograma.getId() != null) {
			idCronogramaAtividadeAcaoCobranca = cobrancaAcaoAtividadeCronograma
					.getId();
		}
		if (cobrancaAcaoAtividadeComando != null
				&& cobrancaAcaoAtividadeComando.getId() != null) {
			idComandoAtividadeAcaoCobranca = cobrancaAcaoAtividadeComando
					.getId();
		}
		if (acaoCobranca != null && acaoCobranca.getId() != null) {
			idAcaoCobranca = acaoCobranca.getId();
		}
		while (!flagFimPesquisa) {
			// map que armazena o sequencial e o numero da
			// conta para no final atualizar todos os
			// sequencias
			mapAtualizaSequencial = new HashMap();

			try {
				colecaoEmitirDocumentoCobranca = repositorioCobranca
						.pesquisarCobrancaDocumentoOrdemCortePorRota(
								idCronogramaAtividadeAcaoCobranca,
								idComandoAtividadeAcaoCobranca,
								dataAtualPesquisa, idAcaoCobranca,
								quantidadeCobrancaDocumentoInicio);
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}

			if (colecaoEmitirDocumentoCobranca != null
					&& !colecaoEmitirDocumentoCobranca.isEmpty()) {

				System.out.println("***************************************");
				System.out.println("QUANTIDADE DE COBRANÇA:"
						+ colecaoEmitirDocumentoCobranca.size());
				System.out.println("***************************************");

				String nomeCliente = null;
				Collection colecaoCobrancaDocumentoItem = null;

				if (colecaoEmitirDocumentoCobranca.size() < quantidadeCobrancaDocumento) {
					flagFimPesquisa = true;
				} else {
					quantidadeCobrancaDocumentoInicio = quantidadeCobrancaDocumentoInicio + 1000;
				}

				int metadeColecao = 0;
				if (colecaoEmitirDocumentoCobranca.size() % 2 == 0) {
					metadeColecao = colecaoEmitirDocumentoCobranca.size() / 2;
				} else {
					metadeColecao = (colecaoEmitirDocumentoCobranca.size() / 2) + 1;
				}

				System.out.println("***************************************");
				System.out.println("INICIO DIVIDE A COLECAO:");
				System.out.println("***************************************");

				Map<Integer, Map<Object, Object>> mapCobrancaDocumentoOrdenada = dividirColecao(colecaoEmitirDocumentoCobranca);
				System.out.println("***************************************");
				System.out.println("FIM DIVIDE A COLECAO:");
				System.out.println("***************************************");
				EmitirDocumentoCobrancaHelper emitirDocumentoCobrancaHelper = null;
				if (mapCobrancaDocumentoOrdenada != null) {
					int countOrdem = 0;

					while (countOrdem < mapCobrancaDocumentoOrdenada.size()) {
						Map<Object, Object> mapCobrancaoDocumentoDivididas = mapCobrancaDocumentoOrdenada
								.get(countOrdem);

						Iterator iteratorColecaoCobrancaDocumento = mapCobrancaoDocumentoDivididas
								.keySet().iterator();

						while (iteratorColecaoCobrancaDocumento.hasNext()) {

							emitirDocumentoCobrancaHelper = null;

							int situacao = 0;
							emitirDocumentoCobrancaHelper = (EmitirDocumentoCobrancaHelper) iteratorColecaoCobrancaDocumento
									.next();

							/*
							 * Estes objetos auxiliarão na formatação da
							 * inscrição que será composta por informações do
							 * documento de cobrança e do imóvel a ele associado
							 */
							Imovel inscricao = null;
							SetorComercial setorComercialInscricao = null;
							Quadra quadraInscricao = null;
							Localidade localidade = null;

							sequencialImpressao++;

							while (situacao < 2) {
								if (situacao == 0) {
									situacao = 1;
									sequencialImpressao = atualizaSequencial(
											sequencialImpressao, situacao,
											metadeColecao);

								} else {
									emitirDocumentoCobrancaHelper = (EmitirDocumentoCobrancaHelper) mapCobrancaoDocumentoDivididas
											.get(emitirDocumentoCobrancaHelper);
									situacao = 2;
									sequencialImpressao = atualizaSequencial(
											sequencialImpressao, situacao,
											metadeColecao);
								}

								if (emitirDocumentoCobrancaHelper != null) {

									/*
									 * Objeto que será utilizado para armazenar
									 * as informações do documento de cobrança
									 * de acordo com o layout definido no caso
									 * de uso
									 */

									try {

										nomeCliente = this.repositorioClienteImovel
												.pesquisarNomeClientePorImovel(emitirDocumentoCobrancaHelper
														.getIdImovel());

										CobrancaDocumento cobrancaDocumento = new CobrancaDocumento();
										cobrancaDocumento
												.setId(emitirDocumentoCobrancaHelper
														.getIdDocumentoCobranca());

										colecaoCobrancaDocumentoItem = this.repositorioCobranca
												.selecionarCobrancaDocumentoItemReferenteConta(cobrancaDocumento);

									} catch (ErroRepositorioException ex) {
										ex.printStackTrace();
										throw new ControladorException(
												"erro.sistema", ex);
									}

									if (colecaoCobrancaDocumentoItem != null
											&& !colecaoCobrancaDocumentoItem
													.isEmpty()) {
										// Início do processo de geração do
										// arquivo txt

										// LINHA 01
										// ==================================

										/*
										 * Canal ("1") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("1");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 35));

										// Nome da Localidade
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																emitirDocumentoCobrancaHelper
																.getDescricaoLocalidade(),
																30));
										
										//Rota
										if (emitirDocumentoCobrancaHelper.getCodigoRota() != null){
										
											cobrancaDocumentoTxt.append(Util
											.completaString("", 5));
													
											cobrancaDocumentoTxt.append("Rota: ");
													
											cobrancaDocumentoTxt
											.append(Util.adicionarZerosEsquedaNumero(5, 
											emitirDocumentoCobrancaHelper.getCodigoRota().toString()));
										
											if (emitirDocumentoCobrancaHelper.getNumeroSequencialRota() != null){
										
												cobrancaDocumentoTxt.append("/");
												
												cobrancaDocumentoTxt
												.append(Util.adicionarZerosEsquedaNumero(5, 
												emitirDocumentoCobrancaHelper.getNumeroSequencialRota().toString()));
											
												cobrancaDocumentoTxt.append(Util
												.completaString("", 63));
											}
											else{
											
												cobrancaDocumentoTxt.append(Util
												.completaString("", 69));
											}
										}
										else{
											
											cobrancaDocumentoTxt.append(Util
													.completaString("", 85));
										}
										
										
										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));
										

										// LINHA 02
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Inscrição
										quadraInscricao = new Quadra();
										setorComercialInscricao = new SetorComercial();
										localidade = new Localidade();
										inscricao = new Imovel();

										quadraInscricao
												.setNumeroQuadra(emitirDocumentoCobrancaHelper
														.getNumeroQuadra());
										setorComercialInscricao
												.setCodigo(emitirDocumentoCobrancaHelper
														.getCodigoSetorComercial());
										localidade
												.setId(emitirDocumentoCobrancaHelper
														.getIdLocalidade());
										inscricao.setLocalidade(localidade);
										inscricao
												.setSetorComercial(setorComercialInscricao);
										inscricao.setQuadra(quadraInscricao);
										inscricao
												.setLote(emitirDocumentoCobrancaHelper
														.getLote());
										inscricao
												.setSubLote(emitirDocumentoCobrancaHelper
														.getSubLote());

										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																inscricao
																		.getInscricaoFormatada(),
																20));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 14));

										// Nome do Cliente
										cobrancaDocumentoTxt
												.append(Util.completaString(
														nomeCliente, 50));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 65));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 03
										// ==================================

										/*
										 * Canal ("+") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("+");
										cobrancaDocumentoTxt.append("2");
										cobrancaDocumentoTxt.append(Util
												.completaString("", 66));

										// Matrícula do imóvel
										String matriculaImovelFormatada = Util
												.retornaMatriculaImovelFormatada(emitirDocumentoCobrancaHelper
														.getIdImovel());

										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																matriculaImovelFormatada,
																10));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 74));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 04
										// ==================================

										String enderecoFormatado = getControladorEndereco()
												.pesquisarEnderecoFormatado(
														emitirDocumentoCobrancaHelper
																.getIdImovel());
										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Endereço Formatado
										cobrancaDocumentoTxt.append(Util
												.completaString(
														enderecoFormatado, 61));
										cobrancaDocumentoTxt.append("  ");

										// Data de Validade
										if (emitirDocumentoCobrancaHelper
												.getEmissao() != null) {
											if (emitirDocumentoCobrancaHelper
													.getNumeroDiasValidade() != null) {
												cobrancaDocumentoTxt
														.append(Util
																.formatarData(Util
																		.adicionarNumeroDiasDeUmaData(
																				emitirDocumentoCobrancaHelper
																						.getEmissao(),
																				emitirDocumentoCobrancaHelper
																						.getNumeroDiasValidade()
																						.intValue())));
											} else {
												cobrancaDocumentoTxt
														.append(Util
																.formatarData(emitirDocumentoCobrancaHelper
																		.getEmissao()));
											}
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 10));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 75));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 05
										// ==================================

										/*
										 * Canal ("+") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("+");
										cobrancaDocumentoTxt.append("2");
										cobrancaDocumentoTxt.append(Util
												.completaString("", 67));
										
										//CRC2574 alterado por Vivianne Sousa 19/08/2009 - Francisco
										// Sequencial de impressão
										cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
											""+ emitirDocumentoCobrancaHelper.getNumeroOS(),9));
										//""+ emitirDocumentoCobrancaHelper.getNumeroSequenciaDocumento(),9));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 74));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 06
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										/*
										 * Quantidades de economias por
										 * categoria: 1º RESIDÊNCIAL 2º
										 * COMERCIAL 3º INDUSTRIAL 4º PÚBLICA
										 */
										Imovel imovel = new Imovel();
										imovel
												.setId(emitirDocumentoCobrancaHelper
														.getIdImovel());
										Collection colecaoCategorias = getControladorImovel()
												.obterQuantidadeEconomiasCategoria(
														imovel);
										String qtdResidencial = "";
										String qtdComercial = "";
										String qtdIndustrial = "";
										String qtdPublico = "";

										if (colecaoCategorias != null
												&& !colecaoCategorias.isEmpty()) {
											Iterator iteratorColecaoCategorias = colecaoCategorias
													.iterator();
											Categoria categoria = null;

											while (iteratorColecaoCategorias
													.hasNext()) {
												categoria = (Categoria) iteratorColecaoCategorias
														.next();

												if (categoria.getId().equals(
														Categoria.RESIDENCIAL)) {
													qtdResidencial = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												} else if (categoria
														.getId()
														.equals(
																Categoria.COMERCIAL)) {
													qtdComercial = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												} else if (categoria
														.getId()
														.equals(
																Categoria.INDUSTRIAL)) {
													qtdIndustrial = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												} else if (categoria
														.getId()
														.equals(
																Categoria.PUBLICO)) {
													qtdPublico = ""
															+ categoria
																	.getQuantidadeEconomiasCategoria();
												}
											}
										}

										colecaoCategorias = null;

										// Residêncial
										if (!qtdResidencial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdResidencial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 3));

										// Comercial
										if (!qtdComercial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdComercial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}
										cobrancaDocumentoTxt.append(Util
												.completaString("", 3));

										// Industrial
										if (!qtdIndustrial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdIndustrial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}
										cobrancaDocumentoTxt.append(Util
												.completaString("", 6));

										// Público
										if (!qtdPublico.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdPublico));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}
										cobrancaDocumentoTxt.append(Util
												.completaString("", 4));

										// Data de Emissão
										if (emitirDocumentoCobrancaHelper
												.getEmissao() != null) {
											cobrancaDocumentoTxt
													.append(Util
															.formatarData(emitirDocumentoCobrancaHelper
																	.getEmissao()));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 10));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 4));

										// Perfil do Imóvel
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																emitirDocumentoCobrancaHelper
																		.getDescricaoImovelPerfil(),
																8));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 6));

										String numeroHidrometro = getControladorAtendimentoPublico()
												.pesquisarNumeroHidrometroLigacaoAgua(
														emitirDocumentoCobrancaHelper
																.getIdImovel());

										// Número Hidrômetro
										if (numeroHidrometro != null) {
											cobrancaDocumentoTxt.append(Util
													.completaString(
															numeroHidrometro,
															10));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 10));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 7));

										// Grupo de Cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																""
																		+ emitirDocumentoCobrancaHelper
																				.getIdCobrancaGrupo(),
																6));

										// Sequencial de impressão
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.retornaSequencialFormatado(sequencialImpressao),
																9));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 61));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 07
										// ==================================

										/*
										 * Canal ("1") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("1");

										/*
										 * Selecionar os itens do documento de
										 * cobrança correspondentes a conta e
										 * ordenar por ano/mês de referência da
										 * conta
										 */
										if (colecaoCobrancaDocumentoItem != null
												&& !colecaoCobrancaDocumentoItem
														.isEmpty()) {
											Iterator iteratorColecaoCobrancaDocumentoItem = null;
											CobrancaDocumentoItem cobrancaDocumentoItem = null;

											int countImpressao = colecaoCobrancaDocumentoItem
													.size() - 13;

											cobrancaDocumentoTxt.append(Util
													.completaString("", 1));
											/*
											 * Caso a quantidade de itens
											 * selecionados seja superior a 15
											 * [SB0001 - Calcular Valor e Data
											 * de Vencimento Anterior]
											 * 
											 * Caso contrário: Dados do primeiro
											 * e segundo itens selecionados
											 */
											if (colecaoCobrancaDocumentoItem
													.size() > 15) {

												CalcularValorDataVencimentoAnteriorHelper calcularValorDataVencimentoAnteriorHelper = this
														.calcularValorDataVencimentoAnterior(
																colecaoCobrancaDocumentoItem,
																15);

												// Constante "DEBTO.ATE"
												cobrancaDocumentoTxt
														.append("DEBTO.ATE");

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 3));

												// Data de Vencimento anterior
												// retornado
												// pelo
												// [SB0001]
												cobrancaDocumentoTxt
														.append(Util
																.formatarData(calcularValorDataVencimentoAnteriorHelper
																		.getDataVencimentoAnterior()));

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 127));

											} else {

												iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem
														.iterator();
												cobrancaDocumentoItem = null;

												cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem
														.next();

												// Mês/Ano de referência da
												// conta
												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		Util
																				.formatarAnoMesParaMesAno(cobrancaDocumentoItem
																						.getContaGeral()
																						.getConta()
																						.getReferencia()),
																		7));

												cobrancaDocumentoTxt
														.append(".");

												// Dígito verificador da conta
												cobrancaDocumentoTxt
														.append(cobrancaDocumentoItem
																.getContaGeral()
																.getConta()
																.getDigitoVerificadorConta());

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 3));

												// Data de vencimento da conta
												cobrancaDocumentoTxt
														.append(Util
																.formatarData(cobrancaDocumentoItem
																		.getContaGeral()
																		.getConta()
																		.getDataVencimentoConta()));

												cobrancaDocumentoTxt
														.append(Util
																.completaString(
																		"", 127));
											}

											cobrancaDocumentoTxt
													.append(System
															.getProperty("line.separator"));

											// LINHA 08
											// ==================================

											/*
											 * Canal ("") Fonte ("1")
											 */
											if (countImpressao <= 0) {
												iteratorColecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem
														.iterator();
												cobrancaDocumentoItem = null;

												int countRegistros = 0;

												while (iteratorColecaoCobrancaDocumentoItem
														.hasNext()) {

													cobrancaDocumentoItem = (CobrancaDocumentoItem) iteratorColecaoCobrancaDocumentoItem
															.next();

													if (countRegistros > 0) {
														cobrancaDocumentoTxt
																.append(" ");
														cobrancaDocumentoTxt
																.append("1");
														cobrancaDocumentoTxt
																.append(" ");

														// Mês/Ano de referência
														// da
														// conta
														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				Util
																						.formatarAnoMesParaMesAno(cobrancaDocumentoItem
																								.getContaGeral()
																								.getConta()
																								.getReferencia()),
																				7));

														cobrancaDocumentoTxt
																.append(".");

														// Dígito verificador da
														// conta
														cobrancaDocumentoTxt
																.append(cobrancaDocumentoItem
																		.getContaGeral()
																		.getConta()
																		.getDigitoVerificadorConta());

														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				"",
																				3));

														// Data de vencimento da
														// conta
														cobrancaDocumentoTxt
																.append(Util
																		.formatarData(cobrancaDocumentoItem
																				.getContaGeral()
																				.getConta()
																				.getDataVencimentoConta()));

														cobrancaDocumentoTxt
																.append(Util
																		.completaString(
																				"",
																				127));
														cobrancaDocumentoTxt
																.append(System
																		.getProperty("line.separator"));
													}
													countRegistros++;
												}
											} else {
												while (countImpressao < colecaoCobrancaDocumentoItem
														.size()) {
													cobrancaDocumentoItem = (CobrancaDocumentoItem) ((List) colecaoCobrancaDocumentoItem)
															.get(countImpressao);
													cobrancaDocumentoTxt
															.append(" ");
													cobrancaDocumentoTxt
															.append("1");
													cobrancaDocumentoTxt
															.append(" ");

													// Mês/Ano de referência da
													// conta
													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			Util
																					.formatarAnoMesParaMesAno(cobrancaDocumentoItem
																							.getContaGeral()
																							.getConta()
																							.getReferencia()),
																			7));

													cobrancaDocumentoTxt
															.append(".");

													// Dígito verificador da
													// conta
													cobrancaDocumentoTxt
															.append(cobrancaDocumentoItem
																	.getContaGeral()
																	.getConta()
																	.getDigitoVerificadorConta());

													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			3));

													// Data de vencimento da
													// conta
													cobrancaDocumentoTxt
															.append(Util
																	.formatarData(cobrancaDocumentoItem
																			.getContaGeral()
																			.getConta()
																			.getDataVencimentoConta()));

													cobrancaDocumentoTxt
															.append(Util
																	.completaString(
																			"",
																			127));
													cobrancaDocumentoTxt
															.append(System
																	.getProperty("line.separator"));

													countImpressao++;
												}
											}
										}

										// LINHA 09
										// ==================================

										/*
										 * Canal ("1") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 6));

										// Valor total do documento de cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.formatarMoedaReal(emitirDocumentoCobrancaHelper
																				.getValorDocumento()),
																16));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 128));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 10
										// ==================================

										/*
										 * Canal ("1") Fonte ("4")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("4");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 1));

										float valorDocumento = emitirDocumentoCobrancaHelper
												.getValorDocumento()
												.floatValue();

										if (valorDocumento > 500) {
											cobrancaDocumentoTxt
													.append("PRIORIDADE - DÉBITO ACIMA DE R$ 500,00");
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 38));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 111));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 11
										// ==================================

										/*
										 * Canal ("-") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));
										
										// LINHA 12
										// ==================================

										/*
										 * Canal ("-") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));
										
										// LINHA 13
										// ==================================

										/*
										 * Canal ("-") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(Util
												.completaString("", 150));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 14
										// ==================================

										/*
										 * Canal ("-") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("2");

										cobrancaDocumentoTxt.append(" ");

										// Grupo de Cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																emitirDocumentoCobrancaHelper
																		.getIdCobrancaGrupo()
																		.toString(),
																2));

										cobrancaDocumentoTxt.append("-");

										// Sigla e descriçao da gerência
										// regional
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																emitirDocumentoCobrancaHelper
																		.getNomeAbreviadoGerencia(),
																3));

										cobrancaDocumentoTxt.append("-");

										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																emitirDocumentoCobrancaHelper
																		.getNomeGerencia(),
																25));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 25));

										// Código e descrição da empresa
										if (emitirDocumentoCobrancaHelper
												.getIdEmpresa() != null) {
											cobrancaDocumentoTxt
													.append(Util
															.completaStringComEspacoAEsquerda(
																	emitirDocumentoCobrancaHelper
																			.getIdEmpresa()
																			.toString(),
																	4));

											cobrancaDocumentoTxt.append("- ");

											cobrancaDocumentoTxt
													.append(Util
															.completaString(
																	emitirDocumentoCobrancaHelper
																			.getDescricaoEmpresa(),
																	10));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 16));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 76));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 15
										// ==================================

										/*
										 * Canal ("1") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																inscricao
																		.getInscricaoFormatada(),
																20));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 14));

										// Nome do Cliente
										cobrancaDocumentoTxt
												.append(Util.completaString(
														nomeCliente, 50));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 65));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 16
										// ==================================

										/*
										 * Canal ("+") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("+");
										cobrancaDocumentoTxt.append("2");
										cobrancaDocumentoTxt.append(Util
												.completaString("", 66));

										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																matriculaImovelFormatada,
																10));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 74));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 17
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Endereço Formatado
										cobrancaDocumentoTxt.append(Util
												.completaString(
														enderecoFormatado, 62));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 87));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 18
										// ==================================

										/*
										 * Canal ("+") Fonte ("2")
										 */
										cobrancaDocumentoTxt.append("+");
										cobrancaDocumentoTxt.append("2");
										cobrancaDocumentoTxt.append(Util
												.completaString("", 67));

										//CRC2574 alterado por Vivianne Sousa 19/08/2009 - Francisco
										// Sequencial do Documento de Cobrança
										cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda(
											""+ emitirDocumentoCobrancaHelper.getNumeroOS(),9));
										//""+ emitirDocumentoCobrancaHelper.getNumeroSequenciaDocumento(),9));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 74));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

										// LINHA 19
										// ==================================

										/*
										 * Canal ("-") Fonte ("1")
										 */
										cobrancaDocumentoTxt.append("-");
										cobrancaDocumentoTxt.append("1");
										cobrancaDocumentoTxt.append(" ");

										// Residêncial
										if (!qtdResidencial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdResidencial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 3));

										// Comercial
										if (!qtdComercial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdComercial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}
										cobrancaDocumentoTxt.append(Util
												.completaString("", 3));

										// Industrial
										if (!qtdIndustrial.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdIndustrial));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}
										cobrancaDocumentoTxt.append(Util
												.completaString("", 5));

										// Público
										if (!qtdPublico.equals("")) {
											cobrancaDocumentoTxt
													.append(Util
															.adicionarZerosEsquedaNumero(
																	3,
																	qtdPublico));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 3));
										}
										cobrancaDocumentoTxt.append(Util
												.completaString("", 1));

										// Data de Emissão
										if (emitirDocumentoCobrancaHelper
												.getEmissao() != null) {
											cobrancaDocumentoTxt
													.append(Util
															.formatarData(emitirDocumentoCobrancaHelper
																	.getEmissao()));
										} else {
											cobrancaDocumentoTxt.append(Util
													.completaString("", 10));
										}

										cobrancaDocumentoTxt.append(Util
												.completaString("", 4));

										// Perfil do Imóvel
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																emitirDocumentoCobrancaHelper
																		.getDescricaoImovelPerfil(),
																8));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 10));

										// Código da situação da ligação de água
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																""
																		+ emitirDocumentoCobrancaHelper
																				.getIdLigacaoAguaSituacao(),
																3));

										// Código da situação da ligação do
										// esgoto
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																""
																		+ emitirDocumentoCobrancaHelper
																				.getIdLigacaoEsgotoSituacao(),
																3));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 3));

										// Grupo de Cobrança
										cobrancaDocumentoTxt
												.append(Util
														.completaString(
																""
																		+ emitirDocumentoCobrancaHelper
																				.getIdCobrancaGrupo(),
																6));
										cobrancaDocumentoTxt.append(Util
												.completaString("", 9));

										// Sequencial de impressão
										cobrancaDocumentoTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.retornaSequencialFormatado(sequencialImpressao),
																7));

										cobrancaDocumentoTxt.append(Util
												.completaString("", 62));

										cobrancaDocumentoTxt.append(System
												.getProperty("line.separator"));

									}
									// adiciona o id da
									// conta e o sequencial
									// no para serem
									// atualizados
									mapAtualizaSequencial.put(
											emitirDocumentoCobrancaHelper
													.getIdDocumentoCobranca(),
											sequencialImpressao);
									colecaoCobrancaDocumentoItem = null;
								}
							}
						}
						mapCobrancaoDocumentoDivididas = null;
						countOrdem++;
					}
					mapCobrancaDocumentoOrdenada = null;
				}

			} else {
				flagFimPesquisa = true;
			}
			try {
				repositorioCobranca
						.atualizarSequencialCobrancaDocumentoImpressao(mapAtualizaSequencial);
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
			mapAtualizaSequencial = null;
			colecaoEmitirDocumentoCobranca = null;
		}

		System.out.println("***************************************");
		System.out.println("FIM DO CORTE ADMINISTRATIVO OU FISICO");
		System.out.println("***************************************");

		Date dataAtual = new Date();
		String nomeZip = null;

		if (idAcaoCobranca.equals(CobrancaAcao.CORTE_ADMINISTRATIVO)) {
			if (idCronogramaAtividadeAcaoCobranca != null) {
				nomeZip = "ORDEM_CORTE_ADMINISTRATIVO_GRUPO_"
						+ grupoCobranca.getId() + "_"
						+ Util.formatarData(dataAtual);
				nomeZip = nomeZip.replace("/", "_");
			} else {
				String descricaoAbrevDocumentoTipo = "";
				if (acaoCobranca != null
						&& acaoCobranca.getDocumentoTipo() != null) {
					descricaoAbrevDocumentoTipo = acaoCobranca
							.getDocumentoTipo().getDescricaoAbreviado();
				}
				String tituloComandoEventual = cobrancaAcaoAtividadeComando
						.getDescricaoTitulo();

				nomeZip = descricaoAbrevDocumentoTipo + " "
						+ tituloComandoEventual + " "
						+ Util.formatarData(dataAtual);
				nomeZip = nomeZip.replace("/", "_");
				nomeZip = nomeZip.replace(" ", "_");
			}
		} else {
			if (idAcaoCobranca.equals(CobrancaAcao.CORTE_FISICO)) {
				if (idCronogramaAtividadeAcaoCobranca != null) {
					nomeZip = "ORDEM_CORTE_FISICO_GRUPO_"
							+ grupoCobranca.getId() + "_"
							+ Util.formatarData(dataAtual);
					nomeZip = nomeZip.replace("/", "_");
				} else {
					String descricaoAbrevDocumentoTipo = "";
					if (acaoCobranca != null
							&& acaoCobranca.getDocumentoTipo() != null) {
						descricaoAbrevDocumentoTipo = acaoCobranca
								.getDocumentoTipo().getDescricaoAbreviado();
					}
					String tituloComandoEventual = cobrancaAcaoAtividadeComando
							.getDescricaoTitulo();

					nomeZip = descricaoAbrevDocumentoTipo + " "
							+ tituloComandoEventual + " "
							+ Util.formatarData(dataAtual);
					nomeZip = nomeZip.replace("/", "_");
					nomeZip = nomeZip.replace(" ", "_");
				}
			}
		}

		try {
			System.out.println("***************************************");
			System.out.println("INICIO CRIAR ARQUIVO");
			System.out.println("***************************************");

			if (cobrancaDocumentoTxt != null
					&& cobrancaDocumentoTxt.length() != 0) {

				cobrancaDocumentoTxt.append("\u0004");

				File leitura = new File(nomeZip + ".txt");
				// criar o arquivo zip
				File compactado = new File(nomeZip + ".zip"); // nomeZip
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
						compactado));

				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(cobrancaDocumentoTxt.toString());
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			}
			System.out.println("***************************************");
			System.out.println("FIM CRIAR ARQUIVO");
			System.out.println("***************************************");

		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 *
	 * @author Raphael Rossiter
	 * @date 20/07/2009
	 *
	 * @param helper
	 * @throws ControladorException
	 */
	public void gerarAtividadeAcaoCobrancaEmissaoDocumento(EmissaoDocumentoCobrancaHelper helper) 
		throws ControladorException{
		
		//7.1 para as ações aviso de corte
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.AVISO_CORTE) ||
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.AVISO_CORTE_A_REVELIA)||
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.VISITA_COBRANCA)) {
			
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobranca(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}
		
		// 7.2 para as ações corte administrativo ou corte fisico
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_FISICO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_ADMINISTRATIVO_LIGADO_A_REVELIA) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_FISICO_LIGADO_A_REVELIA) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CORTE_FISICO_PUBLICO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.RECORTE)) {
			
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobrancaOrdemCorte(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}
		
		// 7.3 para as ações supressão parcial ou supressão total
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.SUPRESSAO_PARCIAL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.SUPRESSAO_TOTAL)) {
			
			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobrancaOrdemSupressao(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}

		// 7.4 para as ações fiscalização suprimido ou fiscalização
		// cortado
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_SUPRIMIDO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_CORTADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_FACTIVEL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_POTENCIAL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_LIGADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_TOTAL) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.FISCALIZACAO_LIGADO_A_REVELIA)) {

			// [UC0349 Emitir Documento fr Cobrança - AvisoCorte]
			emitirDocumentoCobrancaOrdemFiscalizacao(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}
		
		//**************************************************************
		// Autor: Ivan Sergio
		// Data: 18/05/2009
		// CRC1902
		//**************************************************************
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.INSPECAO_LIGACOES)) {
			
			// [UC0904] Gerar TXT Inspecao Ligacoes
			gerarTxtInspecaoLigacoes(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());
		}
		//**************************************************************

		// 7.5 para as ações carta ligado ou carta cortado
		if (helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_SUPRIMIDO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_CORTADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_LIGADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_CORTADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_LIGADO) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_COBRANCA_LIGADO_A_REVELIA) || 
			helper.getAcaoCobranca().getId().equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_LIGADO_A_REVELIA)) {

			// [UC0575] Emitir Aviso de Cobrança
			emitirAvisoCobranca(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}

		// 7.4 para as ações fiscalização suprimido ou fiscalização
		// cortado
		if (helper.getAcaoCobranca().getId().equals(
				CobrancaAcao.CARTA_COBRANCA_PARCELAMENTO)) {

			// [UC0576 Emitir Parcelamento em atraso]
			emitirParcelamentoEmAtraso(helper.getCobrancaAcaoAtividadeCronograma(),
			helper.getCobrancaAcaoAtividadeComando(), helper.getDataAtual(), helper.getAcaoCobranca(), 
			helper.getGrupoCobranca(), helper.getCriterioCobranca());

		}
	}
}
