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
package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoRelatorioHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class RelatorioManterPerfilParcelamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterPerfilParcelamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PERFIL_PARCELAMENTO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterPerfilParcelamento() {
		super(null, "");
	}


	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = (FiltroParcelamentoPerfil) getParametro("filtroParcelamentoPerfil");
		ParcelamentoPerfil parcelamentoPerfilParametros = (ParcelamentoPerfil) getParametro("parcelamentoPerfilParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterPerfilParcelamentoBean relatorioBean = null;

		filtroParcelamentoPerfil.setConsultaSemLimites(true);

		Collection colecaoParcelamentoPerfil = fachada.pesquisar(
				filtroParcelamentoPerfil, ParcelamentoPerfil.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoParcelamentoPerfil != null
				&& !colecaoParcelamentoPerfil.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoParcelamentoPerfilIterator = colecaoParcelamentoPerfil
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoParcelamentoPerfilIterator.hasNext()) {

				ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil) colecaoParcelamentoPerfilIterator
						.next();

				// Pesquisa a coleção de reparcelamentos consecutivos referente
				// ao perfil de parcelamento com os dados do reparcelamento e da
				// prestação
				Collection colecaoReparcelamentosConsecutivos = fachada
						.pesquisarReparcelamentoConsecutivo(parcelamentoPerfil
								.getId());

				if (colecaoReparcelamentosConsecutivos != null
						&& !colecaoReparcelamentosConsecutivos.isEmpty()) {

					Iterator colecaoReparcelamentosConsecutivosIterator = colecaoReparcelamentosConsecutivos
							.iterator();

					while (colecaoReparcelamentosConsecutivosIterator.hasNext()) {

						ParcelamentoQuantidadeReparcelamentoRelatorioHelper parcelamentoQuantidadeReparcelamentoRelatorioHelper = (ParcelamentoQuantidadeReparcelamentoRelatorioHelper) colecaoReparcelamentosConsecutivosIterator
								.next();

						// Cria o Bean setando para nulo os campos que não são
						// referentes a pesquisa de reparcelamento para que o
						// agrupamento fique de maneira correta
						relatorioBean = new RelatorioManterPerfilParcelamentoBean(

								// Dados do Perfil de Parcelamento:

								// Id Parcelamento Perfil
								parcelamentoPerfil.getId().toString(),

								// RD
								parcelamentoPerfil.getResolucaoDiretoria()
										.getNumeroResolucaoDiretoria(),

								// Tipo da Situação do Imóvel
								parcelamentoPerfil.getImovelSituacaoTipo() == null ? ""
										: parcelamentoPerfil
												.getImovelSituacaoTipo()
												.getDescricaoImovelSituacaoTipo(),

								// Perfil do Imóvel
								parcelamentoPerfil.getImovelPerfil() == null ? ""
										: parcelamentoPerfil.getImovelPerfil()
												.getDescricao(),

								// Subcategoria
								parcelamentoPerfil.getSubcategoria() == null ? ""
										: parcelamentoPerfil.getSubcategoria()
												.getDescricao(),

												/*
												 * TODO - COSANPA
												 */
												// Percentual Desconto Multa
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoMulta()),
														
												// Percentual Desconto Juros Mora
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoJurosMora()),
														
												// Percentual Desconto Atualização Monetária
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoAtualizacaoMonetaria()),
												// fim alteração

												// Percentual da Tarifa Mínima para Cálculo do
												// Valor Mínimo da Prestação
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualTarifaMinimaPrestacao()),

								// Dados de Reparcelamento Consecutivos e da
								// Prestação

								// Id Reparcelamento
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getIdReparcelamento().toString(),

								// Quantidade Máxima de Reparcelamentos
								// Consecutivos
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getQuantidadeMaximaReparcelamento()
										.toString(),

								// Id Prestação
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getIdPrestacao().toString(),

								// Quantidade Máxima de Prestações do
								// Parcelamento
								parcelamentoQuantidadeReparcelamentoRelatorioHelper
										.getQuantidadeMaximaPrestacoes()
										.toString(),

								// Taxa de Juros
								Util
										.formatarMoedaReal(parcelamentoQuantidadeReparcelamentoRelatorioHelper
												.getTaxaJuros()),

								// Percentual Mínimo de Entrada
								Util
										.formatarMoedaReal(parcelamentoQuantidadeReparcelamentoRelatorioHelper
												.getPercentualMinimoEntrada()),

								// Dados do Desconto por Antiguidade do Débito

								// Id Desconto Antiguidade
								null,

								// Quantidade Mínima Meses de Débito p/ Desconto
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null,

								// Percentual de Desconto Ativo
								null,

								// Dados do Desconto por Inatividade do Débito

								// Id Desconto Inatividade
								null,

								// Quantidade Máxima Meses de Inatividade
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null

						);

						// adiciona o bean a coleção
						relatorioBeans.add(relatorioBean);
						
					}

				}
				
				// Pesquisa a coleção de parcelamento desconto antiguidade referente
				// ao perfil de parcelamento
				Collection colecaoDescontosAntiguidade = fachada
						.pesquisarParcelamentoDescontoAntiguidade(parcelamentoPerfil
								.getId());
				
				if (colecaoDescontosAntiguidade != null
						&& !colecaoDescontosAntiguidade.isEmpty()) {

					Iterator colecaoDescontosAntiguidadeIterator = colecaoDescontosAntiguidade
							.iterator();

					while (colecaoDescontosAntiguidadeIterator.hasNext()) {

						ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) colecaoDescontosAntiguidadeIterator
								.next();

						// Cria o Bean setando para nulo os campos que não são
						// referentes a pesquisa de desconto antiguidade para que o
						// agrupamento fique de maneira correta
						relatorioBean = new RelatorioManterPerfilParcelamentoBean(

								// Dados do Perfil de Parcelamento:

								// Id Parcelamento Perfil
								parcelamentoPerfil.getId().toString(),

								// RD
								parcelamentoPerfil.getResolucaoDiretoria()
										.getNumeroResolucaoDiretoria(),

								// Tipo da Situação do Imóvel
								parcelamentoPerfil.getImovelSituacaoTipo() == null ? ""
										: parcelamentoPerfil
												.getImovelSituacaoTipo()
												.getDescricaoImovelSituacaoTipo(),

								// Perfil do Imóvel
								parcelamentoPerfil.getImovelPerfil() == null ? ""
										: parcelamentoPerfil.getImovelPerfil()
												.getDescricao(),

								// Subcategoria
								parcelamentoPerfil.getSubcategoria() == null ? ""
										: parcelamentoPerfil.getSubcategoria()
												.getDescricao(),

												/*
												 * TODO - COSANPA
												 */
												// Percentual Desconto Multa
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoMulta()),
														
												// Percentual Desconto Juros Mora
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoJurosMora()),
														
												// Percentual Desconto Atualização Monetária
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoAtualizacaoMonetaria()),
												// fim alteração

												// Percentual da Tarifa Mínima para Cálculo do
												// Valor Mínimo da Prestação
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualTarifaMinimaPrestacao()),

								// Dados de Reparcelamento Consecutivos e da
								// Prestação

								// Id Reparcelamento
								null,

								// Quantidade Máxima de Reparcelamentos
								// Consecutivos
								null,

								// Id Prestação
								null,

								// Quantidade Máxima de Prestações do
								// Parcelamento
								null,

								// Taxa de Juros
								null,

								// Percentual Mínimo de Entrada
								null,

								// Dados do Desconto por Antiguidade do Débito

								// Id Desconto Antiguidade
								parcelamentoDescontoAntiguidade.getId().toString(),

								// Quantidade Mínima Meses de Débito p/ Desconto
								parcelamentoDescontoAntiguidade.getQuantidadeMinimaMesesDebito().toString(),

								// Percentual de Desconto Sem Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoSemRestabelecimento()),

								// Percentual de Desconto Com Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoComRestabelecimento()),

								// Percentual de Desconto Ativo
								Util.formatarMoedaReal(parcelamentoDescontoAntiguidade.getPercentualDescontoAtivo()),

								// Dados do Desconto por Inatividade do Débito

								// Id Desconto Inatividade
								null,

								// Quantidade Máxima Meses de Inatividade
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null

						);

						// adiciona o bean a coleção
						relatorioBeans.add(relatorioBean);
						
					}

				}

				// Pesquisa a coleção de parcelamento desconto antiguidade referente
				// ao perfil de parcelamento
				Collection colecaoDescontosInatividade = fachada
						.pesquisarParcelamentoDescontoInatividade(parcelamentoPerfil
								.getId());
				
				if (colecaoDescontosInatividade != null
						&& !colecaoDescontosInatividade.isEmpty()) {

					Iterator colecaoDescontosInatividadeIterator = colecaoDescontosInatividade
							.iterator();

					while (colecaoDescontosInatividadeIterator.hasNext()) {

						ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = (ParcelamentoDescontoInatividade) colecaoDescontosInatividadeIterator
								.next();

						// Cria o Bean setando para nulo os campos que não são
						// referentes a pesquisa de desconto antiguidade para que o
						// agrupamento fique de maneira correta
						relatorioBean = new RelatorioManterPerfilParcelamentoBean(

								// Dados do Perfil de Parcelamento:

								// Id Parcelamento Perfil
								parcelamentoPerfil.getId().toString(),

								// RD
								parcelamentoPerfil.getResolucaoDiretoria()
										.getNumeroResolucaoDiretoria(),

								// Tipo da Situação do Imóvel
								parcelamentoPerfil.getImovelSituacaoTipo() == null ? ""
										: parcelamentoPerfil
												.getImovelSituacaoTipo()
												.getDescricaoImovelSituacaoTipo(),

								// Perfil do Imóvel
								parcelamentoPerfil.getImovelPerfil() == null ? ""
										: parcelamentoPerfil.getImovelPerfil()
												.getDescricao(),

								// Subcategoria
								parcelamentoPerfil.getSubcategoria() == null ? ""
										: parcelamentoPerfil.getSubcategoria()
												.getDescricao(),

												/*
												 * TODO - COSANPA
												 */
												// Percentual Desconto Multa
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoMulta()),
																		
												// Percentual Desconto Juros Mora
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoJurosMora()),
																		
												// Percentual Desconto Atualização Monetária
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualDescontoAcrescimoAtualizacaoMonetaria()),
												// fim alteração

												// Percentual da Tarifa Mínima para Cálculo do
												// Valor Mínimo da Prestação
												Util.formatarMoedaReal(parcelamentoPerfil
														.getPercentualTarifaMinimaPrestacao()),

								// Dados de Reparcelamento Consecutivos e da
								// Prestação

								// Id Reparcelamento
								null,

								// Quantidade Máxima de Reparcelamentos
								// Consecutivos
								null,

								// Id Prestação
								null,

								// Quantidade Máxima de Prestações do
								// Parcelamento
								null,

								// Taxa de Juros
								null,

								// Percentual Mínimo de Entrada
								null,

								// Dados do Desconto por Antiguidade do Débito

								// Id Desconto Antiguidade
								null,

								// Quantidade Mínima Meses de Débito p/ Desconto
								null,

								// Percentual de Desconto Sem Restabelecimento
								null,

								// Percentual de Desconto Com Restabelecimento
								null,

								// Percentual de Desconto Ativo
								null,

								// Dados do Desconto por Inatividade do Débito

								// Id Desconto Inatividade
								parcelamentoDescontoInatividade.getId().toString(),

								// Quantidade Máxima Meses de Inatividade
								parcelamentoDescontoInatividade.getQuantidadeMaximaMesesInatividade().toString(),

								// Percentual de Desconto Sem Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoSemRestabelecimento()),

								// Percentual de Desconto Com Restabelecimento
								Util.formatarMoedaReal(parcelamentoDescontoInatividade.getPercentualDescontoComRestabelecimento())

						);

						// adiciona o bean a coleção
						relatorioBeans.add(relatorioBean);
						
					}
				}
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// RD
		if (parcelamentoPerfilParametros.getResolucaoDiretoria()
				.getNumeroResolucaoDiretoria() != null) {
			parametros.put("rd", parcelamentoPerfilParametros
					.getResolucaoDiretoria().getNumeroResolucaoDiretoria());
		} else {
			parametros.put("rd", "");
		}

		// Tipo da Situação do Imóvel
		if (parcelamentoPerfilParametros.getImovelSituacaoTipo()
				.getDescricaoImovelSituacaoTipo() != null) {
			parametros.put("imovelSituacaoTipo", parcelamentoPerfilParametros
					.getImovelSituacaoTipo().getDescricaoImovelSituacaoTipo());
		} else {
			parametros.put("imovelSituacaoTipo", "");
		}

		// Perfil do Imóvel
		if (parcelamentoPerfilParametros.getImovelPerfil().getDescricao() != null) {
			parametros.put("imovelPerfil", parcelamentoPerfilParametros
					.getImovelPerfil().getDescricao());
		} else {
			parametros.put("imovelPerfil", "");
		}

		// Subcategoria
		if (parcelamentoPerfilParametros.getSubcategoria().getDescricao() != null) {
			parametros.put("subcategoria", parcelamentoPerfilParametros
					.getSubcategoria().getDescricao());
		} else {
			parametros.put("subcategoria", "");
		}

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PERFIL_PARCELAMENTO_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_PERFIL_PARCELAMENTO,
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
		int retorno = 0;

		retorno = Fachada
				.getInstancia()
				.totalRegistrosPesquisa(
						(FiltroParcelamentoPerfil) getParametro("filtroParcelamentoPerfil"),
						ParcelamentoPerfil.class.getName());

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterPerfilParcelamento",
				this);
	}

}