package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroIndicesAcrescimosImpontualidade;
import gcom.cobranca.IndicesAcrescimosImpontualidade;
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
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar as contas apartir do txt
 * 
 * @author Sávio Luiz
 * @created 28/09/2007
 */
public class RelatorioGerarIndicesAcrescimosImpontualidade extends
		TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioGerarIndicesAcrescimosImpontualidade(Usuario usuario) {
		super(
				usuario,
				ConstantesRelatorios.RELATORIO_GERAR_INDICE_ACRESCIMOS_IMPONTUALIDADE);
	}

	@Deprecated
	public RelatorioGerarIndicesAcrescimosImpontualidade() {
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

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String todosAcrescimos = (String) getParametro("todosAcrescimos");
		String mesAnoReferenciaInicial = (String) getParametro("mesAnoReferenciaInicial");
		String mesAnoReferenciaFinal = (String) getParametro("mesAnoReferenciaFinal");
		RelatorioGerarIndicesAcrescimosImpontualidadeBean relatorioGerarIndicesAcrescimosImpontualidadeBean = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Collection colecaoIndicesAcrescimosImpontualidade = (ArrayList) getParametro("colecaoIndicesAcrescimosImpontualidade");

		// Parâmetros do relatório
		Map parametros = new HashMap();

		if (colecaoIndicesAcrescimosImpontualidade != null
				&& !colecaoIndicesAcrescimosImpontualidade.isEmpty()) {

			Iterator itera = colecaoIndicesAcrescimosImpontualidade.iterator();

			BigDecimal fatorAtualizacaoMonetariaAnterior = new BigDecimal(
					"0.00");

			boolean primeiraVez = true;

			while (itera.hasNext()) {
				IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade = (IndicesAcrescimosImpontualidade) itera
						.next();

				// caso seja a primeira vez e não seja o primeiro ano mes da
				// tabela então recupera o ano mes anterior
				if (primeiraVez) {
					FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();
					filtroIndicesAcrescimosImpontualidade
							.adicionarParametro(new ParametroSimples(
									FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA,
									Util.subtraiAteSeisMesesAnoMesReferencia(
											indicesAcrescimosImpontualidade
													.getAnoMesReferencia(), 1)));

					Collection colecaoIndeces = fachada.pesquisar(
							filtroIndicesAcrescimosImpontualidade,
							IndicesAcrescimosImpontualidade.class.getName());
					if (colecaoIndeces != null && !colecaoIndeces.isEmpty()) {
						IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidadeAnterior = (IndicesAcrescimosImpontualidade) Util
								.retonarObjetoDeColecao(colecaoIndeces);
						fatorAtualizacaoMonetariaAnterior = indicesAcrescimosImpontualidadeAnterior
								.getFatorAtualizacaoMonetaria();
					}
					primeiraVez = false;
				}

				relatorioGerarIndicesAcrescimosImpontualidadeBean = new RelatorioGerarIndicesAcrescimosImpontualidadeBean(
						indicesAcrescimosImpontualidade,
						fatorAtualizacaoMonetariaAnterior);

				fatorAtualizacaoMonetariaAnterior = indicesAcrescimosImpontualidade
						.getFatorAtualizacaoMonetaria();

				relatorioBeans
						.add(relatorioGerarIndicesAcrescimosImpontualidadeBean);

			}

		}

		// __________________________________________________________________

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (todosAcrescimos != null && todosAcrescimos.equals("1")) {
			parametros.put("todosAcrescimos", "Sim");
		} else {
			parametros.put("todosAcrescimos", "Não");
		}

		parametros.put("mesAnoReferenciaInicial", mesAnoReferenciaInicial);
		parametros.put("mesAnoReferenciaFinal", mesAnoReferenciaFinal);

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_GERAR_INDICE_ACRESCIMOS_IMPONTUALIDADE,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.GERAR_INDICES_ACRESCIMOS_IMPONTUALIDADE,
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

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"RelatorioGerarIndicesAcrescimosImpontualidade", this);

	}

}
