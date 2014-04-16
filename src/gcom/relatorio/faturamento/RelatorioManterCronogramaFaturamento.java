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
