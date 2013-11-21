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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
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

public class RelatorioManterCronogramaFaturamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterCronogramaFaturamento(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CRONOGRAMA_FATURAMENTO_MANTER);
	}

	@Deprecated
	public RelatorioManterCronogramaFaturamento() {
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

		FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = (FiltroFaturamentoAtividadeCronograma) getParametro("filtroFaturamentoAtividadeCronograma");
		FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensalParametros = (FaturamentoGrupoCronogramaMensal) getParametro("faturamentoGrupoCronogramaMensalParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterCronogramaFaturamentoBean relatorioBean = null;

		filtroFaturamentoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade");
		filtroFaturamentoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");
		filtroFaturamentoAtividadeCronograma.setConsultaSemLimites(true);
		filtroFaturamentoAtividadeCronograma
				.setCampoOrderBy(
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ID,
						FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ORDEM_REALIZACAO);

		Collection colecaoFaturamentoAtividadeCronograma = fachada.pesquisar(
				filtroFaturamentoAtividadeCronograma,
				FaturamentoAtividadeCronograma.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoFaturamentoAtividadeCronograma != null
				&& !colecaoFaturamentoAtividadeCronograma.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoFaturamentoAtividadeCronogramaIterator = colecaoFaturamentoAtividadeCronograma
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoFaturamentoAtividadeCronogramaIterator.hasNext()) {

				FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) colecaoFaturamentoAtividadeCronogramaIterator
						.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório

				// Predecessora
				String predecessora = "";

				if (faturamentoAtividadeCronograma.getFaturamentoAtividade()
						.getFaturamentoAtividadePrecedente() != null) {
					predecessora = faturamentoAtividadeCronograma
							.getFaturamentoAtividade()
							.getFaturamentoAtividadePrecedente().getDescricao();
				}

				// Obrigatória
				String obrigatoria = "";

				if (faturamentoAtividadeCronograma.getFaturamentoAtividade()
						.getIndicadorObrigatoriedadeAtividade() != null
						&& faturamentoAtividadeCronograma
								.getFaturamentoAtividade()
								.getIndicadorObrigatoriedadeAtividade()
								.equals(
										new Short(
												ConstantesSistema.INDICADOR_USO_ATIVO))) {

					obrigatoria = "SIM";

				} else {
					obrigatoria = "NÃO";
				}

				// Data Prevista
				String dataPrevista = "";

				if (faturamentoAtividadeCronograma.getDataPrevista() != null) {
					dataPrevista = Util
							.formatarData(faturamentoAtividadeCronograma
									.getDataPrevista());
				}

				// Data Realização
				String dataRealizacao = "";

				if (faturamentoAtividadeCronograma.getDataRealizacao() != null) {
					dataRealizacao = Util
							.formatarDataComHora(faturamentoAtividadeCronograma
									.getDataRealizacao());
				}

				// Data Comando
				String dataComando = "";

				if (faturamentoAtividadeCronograma.getComando() != null) {
					dataComando = Util
							.formatarDataComHora(faturamentoAtividadeCronograma
									.getComando());
				}

				relatorioBean = new RelatorioManterCronogramaFaturamentoBean(

						// Grupo
						faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo().getDescricao(),

						// Ano/Mês
						faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal()
								.getMesAno(),

						// Atividade
						faturamentoAtividadeCronograma
								.getFaturamentoAtividade().getDescricao(),

						// Predecessora
						predecessora,

						// Obrigatória
						obrigatoria,

						// Data Prevista
						dataPrevista,

						// Data Realização
						dataRealizacao,

						// Data Comando
						dataComando);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (faturamentoGrupoCronogramaMensalParametros.getFaturamentoGrupo()
				.getDescricao() != null) {
			parametros.put("grupo", faturamentoGrupoCronogramaMensalParametros
					.getFaturamentoGrupo().getDescricao());
		} else {
			parametros.put("grupo", "");
		}

		if (faturamentoGrupoCronogramaMensalParametros.getAnoMesReferencia() != null
				&& faturamentoGrupoCronogramaMensalParametros
						.getAnoMesReferencia().intValue() != 0) {
			parametros.put("mesAno", faturamentoGrupoCronogramaMensalParametros
					.getMesAno());
		} else {
			parametros.put("mesAno", "");
		}

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CRONOGRAMA_FATURAMENTO_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_CRONOGRAMA_FATURAMENTO,
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
						(FiltroFaturamentoAtividadeCronograma) getParametro("filtroFaturamentoAtividadeCronograma"),
						FaturamentoAtividadeCronograma.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCronogramaFaturamento",
				this);
	}

}