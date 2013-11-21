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
package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.bean.OSRelatorioAcompanhamentoExecucaoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de regitro atendimento manter de
 * água
 * 
 * @author Rafael Corrêa
 * @created 11 de Julho de 2005
 */
public class RelatorioAcompanhamentoExecucaoOS extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAcompanhamentoExecucaoOS(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS);
	}

	@Deprecated
	public RelatorioAcompanhamentoExecucaoOS() {
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

		String origemServico = (String) getParametro("origemServico");
		String situacaoOS = (String) getParametro("situacaoOS");
		String[] idsServicosTipos = (String[]) getParametro("idsServicosTipos");
		String idUnidadeAtendimento = (String) getParametro("idUnidadeAtendimento");
		String idUnidadeAtual = (String) getParametro("idUnidadeAtual");
		String idUnidadeEncerramento = (String) getParametro("idUnidadeEncerramento");
		Date periodoAtendimentoInicial = (Date) getParametro("periodoAtendimentoInicial");
		Date periodoAtendimentoFinal = (Date) getParametro("periodoAtendimentoFinal");
		Date periodoEncerramentoInicial = (Date) getParametro("periodoEncerramentoInicial");
		Date periodoEncerramentoFinal = (Date) getParametro("periodoEncerramentoFinal");
		String idEquipeProgramacao = (String) getParametro("idEquipeProgramacao");
		String idEquipeExecucao = (String) getParametro("idEquipeExecucao");
		String tipoOrdenacao = (String) getParametro("tipoOrdenacao");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAcompanhamentoExecucaoOSBean relatorioBean = null;

		Collection colecaoOSRelatorioAcompanhamentoExecucaoHelper = fachada
				.pesquisarOSGerarRelatorioAcompanhamentoExecucao(origemServico,
						situacaoOS, idsServicosTipos, idUnidadeAtendimento,
						idUnidadeAtual, idUnidadeEncerramento,
						periodoAtendimentoInicial, periodoAtendimentoFinal,
						periodoEncerramentoInicial, periodoEncerramentoFinal,
						idEquipeProgramacao, idEquipeExecucao, tipoOrdenacao);

		if (colecaoOSRelatorioAcompanhamentoExecucaoHelper != null
				&& !colecaoOSRelatorioAcompanhamentoExecucaoHelper.isEmpty()) {
			
			if (situacaoOS == null || situacaoOS.equals("")) {
				situacaoOS = "AMBOS";
			} else if (situacaoOS.equals("" + ConstantesSistema.SITUACAO_REFERENCIA_PENDENTE)) {
				situacaoOS = "PENDENTES";
			} else {
				situacaoOS = "ENCERRADOS";
			}

			Iterator colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator = colecaoOSRelatorioAcompanhamentoExecucaoHelper
					.iterator();

			OSRelatorioAcompanhamentoExecucaoHelper osRelatorioAcompanhamentoExecucaoHelper = null;

			String tipoServicoAnterior = null;

			int totalServico = 0;

			int totalGeral = 0;

			while (colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator
					.hasNext()) {

				osRelatorioAcompanhamentoExecucaoHelper = (OSRelatorioAcompanhamentoExecucaoHelper) colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator
						.next();

				totalServico = totalServico + 1;

				totalGeral = totalGeral + 1;

				// Prepara os campos na formatação correta ou passando-os para
				// String
				// para ser colocado no Bean do relatório

				// Tipo de Serviço
				String tipoServico = "";
				if (osRelatorioAcompanhamentoExecucaoHelper.getIdServicoTipo() != null) {
					tipoServico = osRelatorioAcompanhamentoExecucaoHelper
							.getIdServicoTipo().toString()
							+ " - "
							+ osRelatorioAcompanhamentoExecucaoHelper
									.getDescricaoServicoTipo();
				}

				// Id do RA
				String idRA = "";
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getIdRegistroAtendimento() != null) {
					idRA = osRelatorioAcompanhamentoExecucaoHelper
							.getIdRegistroAtendimento().toString();
				}

				// Origem
				String origem = "";
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getIdUnidadeAtendimento() != null) {
					origem = osRelatorioAcompanhamentoExecucaoHelper
							.getIdUnidadeAtendimento().toString()
							+ " - "
							+ osRelatorioAcompanhamentoExecucaoHelper
									.getNomeUnidadeAtendimento();
				}

				// Data Solicitação
				String dataSolicitacao = "";

				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataSolicitacao() != null) {
					dataSolicitacao = Util
							.formatarData(osRelatorioAcompanhamentoExecucaoHelper
									.getDataSolicitacao());
				}

				// Data da Programação
				String dataProgramacao = "";

				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataProgramacao() != null) {
					dataProgramacao = Util
							.formatarData(osRelatorioAcompanhamentoExecucaoHelper
									.getDataProgramacao());
				}

				// Data de Encerramento
				String dataEncerramento = "";

				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataEncerramento() != null) {
					dataEncerramento = Util
							.formatarData(osRelatorioAcompanhamentoExecucaoHelper
									.getDataEncerramento());
				}

				// Dias de Atraso
				String diasAtraso = "";

				// Verifica se o RA foi encerrado
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataEncerramentoRA() == null) {

					// Verifica se a data do RA é diferente de nulo
					if (osRelatorioAcompanhamentoExecucaoHelper
							.getDataSolicitacao() != null) {

						// Verifica se a quantidade de dias de prazo é diferente
						// de nulo
						if (osRelatorioAcompanhamentoExecucaoHelper
								.getDiasPrazo() != null) {

							Date dataAtual = new Date();
							int diasPrazo = osRelatorioAcompanhamentoExecucaoHelper
									.getDiasPrazo().intValue();
							Date dataPrevista = Util
									.adicionarNumeroDiasDeUmaData(
											osRelatorioAcompanhamentoExecucaoHelper
													.getDataSolicitacao(),
											diasPrazo);

							// Verifica se a data atual é posterior a data
							// prevista
							if (Util.compararData(dataAtual, dataPrevista) == 1) {
								diasAtraso = ""
										+ Util
												.obterQuantidadeDiasEntreDuasDatas(
														dataPrevista, dataAtual);
							}
						}
					}
				}

				if (tipoServicoAnterior == null) {
					tipoServicoAnterior = tipoServico;
				}

				if (!tipoServicoAnterior.equalsIgnoreCase(tipoServico)) {
					tipoServicoAnterior = tipoServico;
					totalServico = 1;
				}

				if (colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator
						.hasNext()) {

					relatorioBean = new RelatorioAcompanhamentoExecucaoOSBean(

					// Número OS
							osRelatorioAcompanhamentoExecucaoHelper
									.getIdOrdemServico().toString(),

							// Unidade Atual
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeUnidadeAtual(),

							// Situação da OS
							situacaoOS,

							// Tipo de Serviço
							tipoServico,

							// Número do RA
							idRA,

							// Endereço
							osRelatorioAcompanhamentoExecucaoHelper
									.getEndereco(),

							// Origem
							origem,

							// Data Solicitação
							dataSolicitacao,

							// Data Programação
							dataProgramacao,

							// Data Encerramento
							dataEncerramento,

							// Equipe
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeEquipe(),

							// Dias de Atraso
							diasAtraso,

							// Quantidade de OS por Tipo de Serviço (TOTAL)
							"" + totalServico,

							// Quantidade de OS no relatório (TOTAL GERAL)
							"");

				} else {
					
					relatorioBean = new RelatorioAcompanhamentoExecucaoOSBean(

					// Número OS
							osRelatorioAcompanhamentoExecucaoHelper
									.getIdOrdemServico().toString(),

							// Unidade Atual
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeUnidadeAtual(),

							// Situação da OS
							osRelatorioAcompanhamentoExecucaoHelper
									.getSituacaoOS(),

							// Tipo de Serviço
							tipoServico,

							// Número do RA
							idRA,

							// Endereço
							osRelatorioAcompanhamentoExecucaoHelper
									.getEndereco(),

							// Origem
							origem,

							// Data Solicitação
							dataSolicitacao,

							// Data Programação
							dataProgramacao,

							// Data Encerramento
							dataEncerramento,

							// Equipe
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeEquipe(),

							// Dias de Atraso
							diasAtraso,

							// Quantidade de OS por Tipo de Serviço (TOTAL)
							"" + totalServico,

							// Quantidade de OS no relatório (TOTAL GERAL)
							"" + totalGeral);
				}

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);

			}
		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// Período de Atendimento
		String periodoAtendimento = "";

		if (periodoAtendimentoInicial != null) {
			String periodoAtendimentoInicialFormatado = Util
					.formatarData(periodoAtendimentoInicial);
			String periodoAtendimentoFinalFormatado = Util
					.formatarData(periodoAtendimentoFinal);
			periodoAtendimento = periodoAtendimentoInicialFormatado + " a "
					+ periodoAtendimentoFinalFormatado;
			parametros.put("periodoAtendimento", periodoAtendimento);
		} else {
			parametros.put("periodoAtendimento", periodoAtendimento);
		}

		// Período de Encerramento
		String periodoEncerramento = "";

		if (periodoEncerramentoInicial != null) {
			String periodoEncerramentoInicialFormatado = Util
					.formatarData(periodoEncerramentoInicial);
			String periodoEncerramentoFinalFormatado = Util
					.formatarData(periodoEncerramentoFinal);
			periodoEncerramento = periodoEncerramentoInicialFormatado + " a "
					+ periodoEncerramentoFinalFormatado;
			parametros.put("periodoEncerramento", periodoEncerramento);
		} else {
			parametros.put("periodoEncerramento", periodoEncerramento);
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ACOMPANHAMENTO_EXECUCAO_OS,
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
		Fachada fachada = Fachada.getInstancia();

		int retorno = 0;

		String origemServico = (String) getParametro("origemServico");
		String situacaoOS = (String) getParametro("situacaoOS");
		String[] idsServicosTipos = (String[]) getParametro("idsServicosTipos");
		String idUnidadeAtendimento = (String) getParametro("idUnidadeAtendimento");
		String idUnidadeAtual = (String) getParametro("idUnidadeAtual");
		String idUnidadeEncerramento = (String) getParametro("idUnidadeEncerramento");
		Date periodoAtendimentoInicial = (Date) getParametro("periodoAtendimentoInicial");
		Date periodoAtendimentoFinal = (Date) getParametro("periodoAtendimentoFinal");
		Date periodoEncerramentoInicial = (Date) getParametro("periodoEncerramentoInicial");
		Date periodoEncerramentoFinal = (Date) getParametro("periodoEncerramentoFinal");
		String idEquipeProgramacao = (String) getParametro("idEquipeProgramacao");
		String idEquipeExecucao = (String) getParametro("idEquipeExecucao");

		retorno = fachada.pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
				origemServico, situacaoOS, idsServicosTipos,
				idUnidadeAtendimento, idUnidadeAtual, idUnidadeEncerramento,
				periodoAtendimentoInicial, periodoAtendimentoFinal,
				periodoEncerramentoInicial, periodoEncerramentoFinal,
				idEquipeProgramacao, idEquipeExecucao);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoExecucaoOS",
				this);
	}
}