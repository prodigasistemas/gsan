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
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
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

public class RelatorioManterCriterioCobranca extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterCriterioCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CRITERIO_COBRANCA_MANTER);
	}
	
	@Deprecated
	public RelatorioManterCriterioCobranca() {
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

		FiltroCobrancaCriterio filtroCobrancaCriterio = (FiltroCobrancaCriterio) getParametro("filtroCobrancaCriterio");
		CobrancaCriterio cobrancaCriterioParametros = (CobrancaCriterio) getParametro("cobrancaCriterioParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterCriterioCobrancaBean relatorioBean = null;

		filtroCobrancaCriterio.setConsultaSemLimites(true);
		filtroCobrancaCriterio
				.setCampoOrderBy(FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO);

		Collection colecaoCobrancaCriterio = fachada.pesquisar(
				filtroCobrancaCriterio, CobrancaCriterio.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoCobrancaCriterio != null
				&& !colecaoCobrancaCriterio.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoCobrancaCriterioIterator = colecaoCobrancaCriterio
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoCobrancaCriterioIterator.hasNext()) {

				CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) colecaoCobrancaCriterioIterator
						.next();

				Collection colecaoCobrancaCriterioLinha = fachada.pesquisarCobrancaCriterioLinha(cobrancaCriterio.getId());
				
				if (colecaoCobrancaCriterioLinha != null
						&& !colecaoCobrancaCriterioLinha.isEmpty()) {

					Iterator colecaoCobrancaCriterioLinhaIterator = colecaoCobrancaCriterioLinha
							.iterator();

					while (colecaoCobrancaCriterioLinhaIterator.hasNext()) {

						CobrancaCriterioLinha cobrancaCriterioLinha = (CobrancaCriterioLinha) colecaoCobrancaCriterioLinhaIterator
								.next();

						relatorioBean = new RelatorioManterCriterioCobrancaBean(

								// Descricao
								cobrancaCriterio.getDescricaoCobrancaCriterio(),

								// Data Início Vigência
								Util.formatarData(cobrancaCriterio
										.getDataInicioVigencia()),

								// Número Anos
								cobrancaCriterio.getNumeroContaAntiga()
										.toString(),

								// Imóvel com Situação Especial de Cobrança
								cobrancaCriterio
										.getIndicadorEmissaoImovelParalisacao()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO) ? "SIM"
										: "NÃO",

								// Imóvel com Situação de Cobrança
								cobrancaCriterio
										.getIndicadorEmissaoImovelSituacaoCobranca()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO) ? "SIM"
										: "NÃO",

								// Considerar Contas em Revisão
								cobrancaCriterio
										.getIndicadorEmissaoContaRevisao()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO) ? "SIM"
										: "NÃO",

								// Imóvel com Débito só da Conta do Mês
								cobrancaCriterio
										.getIndicadorEmissaoDebitoContaMes()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO) ? "SIM"
										: "NÃO",

								// Inquilino com Débito só da Conta do Mês
								cobrancaCriterio
										.getIndicadorEmissaoInquilinoDebitoContaMes()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO) ? "SIM"
										: "NÃO",

								// Imóvel com Débito só de Contas Antigas
								cobrancaCriterio
										.getIndicadorEmissaoDebitoContaAntiga()
										.equals(
												ConstantesSistema.INDICADOR_USO_ATIVO) ? "SIM"
										: "NÃO",

								// Indicador de Uso
								cobrancaCriterio.getIndicadorUso().toString(),

								// Imóvel Perfil
								cobrancaCriterioLinha.getImovelPerfil()
										.getDescricao(),

								// Categoria
								cobrancaCriterioLinha.getCategoria()
										.getDescricao(),

								// Intervalo de Valor do Débito
								Util.formatarMoedaReal(cobrancaCriterioLinha
										.getValorMinimoDebito())
										+ " a "
										+ Util
												.formatarMoedaReal(cobrancaCriterioLinha
														.getValorMaximoDebito()),

								// Intervalo de Quantidade de Contas
								cobrancaCriterioLinha
										.getQuantidadeMinimaContas()
										+ " a "
										+ cobrancaCriterioLinha
												.getQuantidadeMaximaContas(),

								// Valor Mínimo da Conta do Mês
								Util.formatarMoedaReal(cobrancaCriterioLinha
										.getValorMinimoContaMes()),

								// Valor Mínimo do Débito para Cliente com
								// Débito Automático
								Util
										.formatarMoedaReal(cobrancaCriterioLinha
												.getValorMinimoDebitoDebitoAutomatico()),

								// Quantidade Mínima de Contas para Cliente com
								// Débito Automático
								cobrancaCriterioLinha
										.getQuantidadeMinimaContasDebitoAutomatico()
										.toString());

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

		// Descrição
		parametros.put("descricao", cobrancaCriterioParametros
				.getDescricaoCobrancaCriterio());

		// Data de Início da Vigência
		if (cobrancaCriterioParametros.getDataInicioVigencia() != null) {
			parametros.put("dataInicio", Util
					.formatarData(cobrancaCriterioParametros
							.getDataInicioVigencia()));
		} else {
			parametros.put("dataInicio", "");
		}

		// Número de Anos para Determinar Conta Antiga
		if (cobrancaCriterioParametros.getNumeroContaAntiga() != null) {
			parametros.put("numeroAnos", cobrancaCriterioParametros
					.getNumeroContaAntiga().toString());
		} else {
			parametros.put("numeroAnos", "");
		}

		// Imóvel com Situação Especial de Cobrança
		if (cobrancaCriterioParametros.getIndicadorEmissaoImovelParalisacao() != null) {
			if (cobrancaCriterioParametros
					.getIndicadorEmissaoImovelParalisacao().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("situacaoEspecialCobranca", "SIM");
			} else {
				parametros.put("situacaoEspecialCobranca", "NÃO");
			}
		} else {
			parametros.put("situacaoEspecialCobranca", "");
		}
		
		// Imóvel com Situação de Cobrança
		if (cobrancaCriterioParametros.getIndicadorEmissaoImovelSituacaoCobranca() != null) {
			if (cobrancaCriterioParametros
					.getIndicadorEmissaoImovelSituacaoCobranca().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("situacaoCobranca", "SIM");
			} else {
				parametros.put("situacaoCobranca", "NÃO");
			}
		} else {
			parametros.put("situacaoCobranca", "");
		}
		
		// Contas em Revisão
		if (cobrancaCriterioParametros.getIndicadorEmissaoContaRevisao() != null) {
			if (cobrancaCriterioParametros
					.getIndicadorEmissaoContaRevisao().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("contasRevisao", "SIM");
			} else {
				parametros.put("contasRevisao", "NÃO");
			}
		} else {
			parametros.put("contasRevisao", "");
		}
		
		// Imóvel com Débito só da Conta do Mês
		if (cobrancaCriterioParametros.getIndicadorEmissaoDebitoContaMes() != null) {
			if (cobrancaCriterioParametros
					.getIndicadorEmissaoDebitoContaMes().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("imovelDebitoContaMes", "SIM");
			} else {
				parametros.put("imovelDebitoContaMes", "NÃO");
			}
		} else {
			parametros.put("imovelDebitoContaMes", "");
		}
		
		// Inquilino com Débito só da Conta do Mês Independente do Valor da Conta
		if (cobrancaCriterioParametros.getIndicadorEmissaoInquilinoDebitoContaMes() != null) {
			if (cobrancaCriterioParametros
					.getIndicadorEmissaoInquilinoDebitoContaMes().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("inquilinoDebitoContaMes", "SIM");
			} else {
				parametros.put("inquilinoDebitoContaMes", "NÃO");
			}
		} else {
			parametros.put("inquilinoDebitoContaMes", "");
		}
		
		// Imóvel com Débito só de Contas do Antigas
		if (cobrancaCriterioParametros.getIndicadorEmissaoDebitoContaAntiga() != null) {
			if (cobrancaCriterioParametros
					.getIndicadorEmissaoDebitoContaAntiga().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("imovelDebitoContasAntigas", "SIM");
			} else {
				parametros.put("imovelDebitoContasAntigas", "NÃO");
			}
		} else {
			parametros.put("imovelDebitoContasAntigas", "");
		}
		
		// Indicador de Uso
		if (cobrancaCriterioParametros.getIndicadorUso() != null) {
			if (cobrancaCriterioParametros
					.getIndicadorUso().equals(
							ConstantesSistema.INDICADOR_USO_ATIVO)) {
				parametros.put("indicadorUso", "ATIVO");
			} else {
				parametros.put("indicadorUso", "INATIVO");
			}
		} else {
			parametros.put("indicadorUso", "");
		}

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CRITERIO_COBRANCA_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_CRITERIO_COBRANCA,
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
						(FiltroCobrancaCriterio) getParametro("filtroCobrancaCriterio"),
						CobrancaCriterio.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCriterioCobranca",
				this);
	}

}