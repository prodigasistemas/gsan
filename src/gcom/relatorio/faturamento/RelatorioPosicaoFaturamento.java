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

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioPosicaoFaturamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioPosicaoFaturamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_POSICAO_FATURAMENTO);
	}

	@Deprecated
	public RelatorioPosicaoFaturamento() {
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

		Map<FaturamentoGrupo, Collection<FaturamentoAtividadeCronograma>> map = (Map<FaturamentoGrupo, Collection<FaturamentoAtividadeCronograma>>) getParametro("map");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		if (map != null && !map.isEmpty()) {

			Collection<FaturamentoGrupo> colecaoFaturamentoGrupo = map.keySet();

			for (FaturamentoGrupo grupo : colecaoFaturamentoGrupo) {

				Collection<FaturamentoAtividadeCronograma> colecaoFaturamentoAtividadeCronograma = map
						.get(grupo);

				for (FaturamentoAtividadeCronograma faturamentoAtividadeCronograma : colecaoFaturamentoAtividadeCronograma) {

					// Faz as validações dos campos necessáriose e formata a
					// String
					// para a forma como irá aparecer no relatório

					// Grupo de Faturamento / Mês/Ano
					String grupoFaturamento = "";
					String mesAno = "";

					if (faturamentoAtividadeCronograma
							.getFaturamentoGrupoCronogramaMensal() != null
							&& faturamentoAtividadeCronograma
									.getFaturamentoGrupoCronogramaMensal()
									.getFaturamentoGrupo() != null) {
						grupoFaturamento = faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal()
								.getFaturamentoGrupo().getDescricao();
						mesAno = Util
								.formatarAnoMesParaMesAno(faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getFaturamentoGrupo()
										.getAnoMesReferencia());
					}

					// Atividade / Predecessora / Obrigatoriedade
					String atividade = "";
					String predecessora = "";
					String obrigatoria = "";

					if (faturamentoAtividadeCronograma
							.getFaturamentoAtividade() != null) {

						atividade = faturamentoAtividadeCronograma
								.getFaturamentoAtividade().getDescricao();

						if (faturamentoAtividadeCronograma
								.getFaturamentoAtividade()
								.getIndicadorObrigatoriedadeAtividade().equals(
										ConstantesSistema.SIM)) {
							obrigatoria = "SIM";
						} else {
							obrigatoria = "NÃO";
						}

						if (faturamentoAtividadeCronograma
								.getFaturamentoAtividade()
								.getFaturamentoAtividadePrecedente() != null) {
							predecessora = faturamentoAtividadeCronograma
									.getFaturamentoAtividade()
									.getFaturamentoAtividadePrecedente()
									.getDescricao();
						}

					}

					// Data Previsão / Usuário Previsão
					String dataPrevisao = "";
					String usuarioPrevisao = "";

					if (faturamentoAtividadeCronograma.getDataPrevista() != null) {
						dataPrevisao = Util
								.formatarData(faturamentoAtividadeCronograma
										.getDataPrevista());

						if (faturamentoAtividadeCronograma
								.getFaturamentoGrupoCronogramaMensal() != null
								&& faturamentoAtividadeCronograma
										.getFaturamentoGrupoCronogramaMensal()
										.getUsuario() != null) {
							usuarioPrevisao = faturamentoAtividadeCronograma
									.getFaturamentoGrupoCronogramaMensal()
									.getUsuario().getNomeUsuario();
						}
					}

					// Data Comando / Usuário Comando
					String dataComando = "";
					String usuarioComando = "";

					if (faturamentoAtividadeCronograma.getComando() != null) {
						dataComando = Util
								.formatarDataComHora(faturamentoAtividadeCronograma
										.getComando());

						if (faturamentoAtividadeCronograma.getUsuario() != null) {
							usuarioComando = faturamentoAtividadeCronograma
									.getUsuario().getNomeUsuario();
						}

					}

					// Data Realização
					String dataRealizacao = "";

					if (faturamentoAtividadeCronograma.getDataRealizacao() != null) {
						dataRealizacao = Util
								.formatarDataComHora(faturamentoAtividadeCronograma
										.getDataRealizacao());
					}

					RelatorioPosicaoFaturamentoBean relatorioBean = new RelatorioPosicaoFaturamentoBean(

					// Grupo de Faturamento
							grupoFaturamento,

							// Mês/Ano
							mesAno,

							// Atividade
							atividade,

							// Predecessora
							predecessora,

							// Obrigatória
							obrigatoria,

							// Data Previsão
							dataPrevisao,

							// Usuário Previsão
							usuarioPrevisao,

							// Data Comando
							dataComando,

							// Usuário Comando
							usuarioComando,

							// Data Realização
							dataRealizacao);

					// adiciona o bean a coleção
					relatorioBeans.add(relatorioBean);
				}
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_POSICAO_FATURAMENTO, parametros,
				ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPosicaoFaturamento", this);
	}
}