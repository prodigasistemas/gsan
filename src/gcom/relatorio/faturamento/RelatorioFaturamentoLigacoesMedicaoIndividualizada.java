package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
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
import java.util.Map;

/**
 * [UC0532] Gerar Relatório de Faturamento das Ligações com Medição Individualizada
 * 
 * @author Vivianne Sousa
 * @date 09/01/2007
 */

public class RelatorioFaturamentoLigacoesMedicaoIndividualizada extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
		
	public RelatorioFaturamentoLigacoesMedicaoIndividualizada(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA);
	}
	
	private Collection<RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean> inicializarBeanRelatorio(
			Collection colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper) {
		
		Collection<RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean> retorno =
			new ArrayList<RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean>();
		
		if (colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper != null
				&& !colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper
						.isEmpty()) {

			Iterator iter = colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper
					.iterator();
			while (iter.hasNext()) {

				FaturamentoLigacoesMedicaoIndividualizadaRelatorioHelper faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper = (FaturamentoLigacoesMedicaoIndividualizadaRelatorioHelper) iter
						.next();

				RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean relatorioFaturamentoLigacoesMedicaoIndividualizadaBean = new RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean(
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getIdLocalidade(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getNomeLocalidade(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getMatriculaImovel(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getInscricaoFormatada(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getNomeConsumidor(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getQtdeEconomias(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getLeituraAnterior(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getDataLeituraAnterior(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getLeituraAtual(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getDataLeituraAtual(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getMedia(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getConsumoImoveisVinculados(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getConsumoFaturado(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getRateio(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getConsumoEsgoto(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getAnormalidade(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getAnormalidadeConsumo(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getTipoConsumo(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getIndicadorPocoExtenso(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getIndicadorQuebraImovelCondominio(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getTotalConsumidoresRateioMacromedidor(),
						faturamentoLigacoesMedicaoIndividualizadaRelatorioHelper
								.getNumeroEconomiasRateio());

				retorno
						.add(relatorioFaturamentoLigacoesMedicaoIndividualizadaBean);

			}
		
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String anoMesFaturamentoGrupo = (String) getParametro("anoMesFaturamentoGrupo");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		String mesAnoPesquisa = (String) getParametro("mesAnoPesquisa");
		
		
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) getParametro("filtroMedicaoHistoricoSql");
		
		Collection colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper = null; 
		
		colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper = fachada
				.pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(
							filtroMedicaoHistoricoSql, Util.formatarMesAnoParaAnoMesSemBarra(mesAnoPesquisa));
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("anoMesFaturamentoGrupo",anoMesFaturamentoGrupo);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);

		Collection dadosRelatorio = colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper;

		Collection<RelatorioFaturamentoLigacoesMedicaoIndividualizadaBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA, parametros, ds,
				tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA,
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
		Integer retorno = 0;

		Fachada fachada = Fachada.getInstancia();

		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) getParametro("filtroMedicaoHistoricoSql");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		String mesAnoPesquisa = (String) getParametro("mesAnoPesquisa");
		String valorAguaEsgotoInicial = (String) getParametro("valorAguaEsgotoInicial");
		String valorAguaEsgotoFinal = (String) getParametro("valorAguaEsgotoFinal");
		
		retorno = fachada.filtrarExcecoesLeiturasConsumosCount(
					faturamentoGrupo, filtroMedicaoHistoricoSql,
					mesAnoPesquisa, valorAguaEsgotoInicial, valorAguaEsgotoFinal);
		
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFaturamentoLigacoesMedicaoIndividualizada", this);
	}
	
}
