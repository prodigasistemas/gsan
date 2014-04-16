package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
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

public class RelatorioManterCronogramaCobranca extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterCronogramaCobranca(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CRONOGRAMA_COBRANCA_MANTER);
	}

	@Deprecated
	public RelatorioManterCronogramaCobranca() {
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

		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = (FiltroCobrancaAcaoAtividadeCronograma) getParametro("filtroCobrancaAcaoAtividadeCronograma");
		CobrancaGrupoCronogramaMes cobrancaGrupoCronogramaMesParametros = (CobrancaGrupoCronogramaMes) getParametro("cobrancaGrupoCronogramaMesParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterCronogramaCobrancaBean relatorioBean = null;

		filtroCobrancaAcaoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividade");
		filtroCobrancaAcaoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma.cobrancaAcao");
		filtroCobrancaAcaoAtividadeCronograma
				.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo");
		filtroCobrancaAcaoAtividadeCronograma.setConsultaSemLimites(true);
		filtroCobrancaAcaoAtividadeCronograma
				.setCampoOrderBy(
						FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES_MES_ANO,
						FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES_COBRANCA_GRUPO_ID,
						FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO_CRONOGRAMA_COBRANCA_ACAO_ORDEM_REALIZACAO,
						FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ATIVIDADE_ORDEM_REALIZACAO);

		Collection colecaoCobrancaAcaoAtividadeCronograma = fachada.pesquisar(
				filtroCobrancaAcaoAtividadeCronograma,
				CobrancaAcaoAtividadeCronograma.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoCobrancaAcaoAtividadeCronograma != null
				&& !colecaoCobrancaAcaoAtividadeCronograma.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoCobrancaAcaoAtividadeCronogramaIterator = colecaoCobrancaAcaoAtividadeCronograma
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoCobrancaAcaoAtividadeCronogramaIterator.hasNext()) {

				CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) colecaoCobrancaAcaoAtividadeCronogramaIterator
						.next();

				// Faz as validações dos campos necessáriose e formata a String
				// para a forma como irá aparecer no relatório

				// Predecessora
				String predecessora = "";

				if (cobrancaAcaoAtividadeCronograma.getCobrancaAtividade()
						.getCobrancaAtividadePredecessora() != null) {
					predecessora = cobrancaAcaoAtividadeCronograma
							.getCobrancaAtividade().getCobrancaAtividadePredecessora()
							.getDescricaoCobrancaAtividade();
				}

				// Predecessora Ação
				String predecessoraAcao = "";

				if (cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
						.getCobrancaAcao().getCobrancaAcaoPredecessora() != null) {
					predecessoraAcao = cobrancaAcaoAtividadeCronograma
							.getCobrancaAcaoCronograma().getCobrancaAcao()
							.getCobrancaAcaoPredecessora()
							.getDescricaoCobrancaAcao();
				}

				// Obrigatória
				String obrigatoria = "";

				if (cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma()
						.getCobrancaAcao().getIndicadorObrigatoriedade() != null
						&& cobrancaAcaoAtividadeCronograma
								.getCobrancaAcaoCronograma()
								.getCobrancaAcao()
								.getIndicadorObrigatoriedade()
								.equals(
										new Short(
												ConstantesSistema.INDICADOR_USO_ATIVO))) {
					obrigatoria = "SIM";
				} else {
					obrigatoria = "NÃO";
				}

				// Data Prevista
				String dataPrevista = "";

				if (cobrancaAcaoAtividadeCronograma.getDataPrevista() != null) {
					dataPrevista = Util
							.formatarData(cobrancaAcaoAtividadeCronograma
									.getDataPrevista());
				}

				// Data Realização
				String dataRealizacao = "";

				if (cobrancaAcaoAtividadeCronograma.getRealizacao() != null) {
					dataRealizacao = Util
							.formatarDataComHora(cobrancaAcaoAtividadeCronograma
									.getRealizacao());
				}

				// Data Comando
				String dataComando = "";

				if (cobrancaAcaoAtividadeCronograma.getComando() != null) {
					dataComando = Util
							.formatarDataComHora(cobrancaAcaoAtividadeCronograma
									.getComando());
				}

				relatorioBean = new RelatorioManterCronogramaCobrancaBean(

				// Grupo
						cobrancaAcaoAtividadeCronograma
								.getCobrancaAcaoCronograma()
								.getCobrancaGrupoCronogramaMes()
								.getCobrancaGrupo().getDescricao(),

						// Ano/Mês
						cobrancaAcaoAtividadeCronograma
								.getCobrancaAcaoCronograma()
								.getCobrancaGrupoCronogramaMes().getMesAno(),

						// Ação Cobrança
						cobrancaAcaoAtividadeCronograma
								.getCobrancaAcaoCronograma().getCobrancaAcao()
								.getDescricaoCobrancaAcao(),

						// Atividade
						cobrancaAcaoAtividadeCronograma.getCobrancaAtividade()
								.getDescricaoCobrancaAtividade(),

						// Predecessora
						predecessora,

						// Predecessora Ação
						predecessoraAcao,

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

		if (cobrancaGrupoCronogramaMesParametros.getCobrancaGrupo()
				.getDescricao() != null) {
			parametros.put("grupo", cobrancaGrupoCronogramaMesParametros
					.getCobrancaGrupo().getDescricao());
		} else {
			parametros.put("grupo", "");
		}

		if (cobrancaGrupoCronogramaMesParametros.getAnoMesReferencia() != 0) {
			parametros.put("mesAno", cobrancaGrupoCronogramaMesParametros
					.getMesAno());
		} else {
			parametros.put("mesAno", "");
		}

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CRONOGRAMA_COBRANCA_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_CRONOGRAMA_COBRANCA,
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
						(FiltroCobrancaAcaoAtividadeCronograma) getParametro("filtroCobrancaAcaoAtividadeCronograma"),
						CobrancaAcaoAtividadeCronograma.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterCronogramaCobranca",
				this);
	}

}
