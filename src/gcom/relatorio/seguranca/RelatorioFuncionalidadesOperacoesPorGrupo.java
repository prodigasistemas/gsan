package gcom.relatorio.seguranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de Funcionalidades e Operacoes por Grupo
 * 
 * [UC1039] Gerar Relatório de Funcionalidades e Operacoes por Grupo
 * 
 * @author Hugo Leonardo
 *
 * @date 15/07/2010
 */
public class RelatorioFuncionalidadesOperacoesPorGrupo extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioFuncionalidadesOperacoesPorGrupo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO);
	}

	@Deprecated
	public RelatorioFuncionalidadesOperacoesPorGrupo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper relatorioHelper = 
			(FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper) getParametro("FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioFuncionalidadeOperacoesPorGrupoBean relatorioFuncionalidadeOperacoesPorGrupoBean = null;

		Collection<RelatorioFuncionalidadeOperacoesPorGrupoHelper> colecao =  
			fachada.pesquisarRelatorioFuncionalidadeOperacoesPorGrupo(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioFuncionalidadeOperacoesPorGrupoHelper helper = 
					(RelatorioFuncionalidadeOperacoesPorGrupoHelper) colecaoIterator.next();
				
				relatorioFuncionalidadeOperacoesPorGrupoBean = 
					new RelatorioFuncionalidadeOperacoesPorGrupoBean(
							helper.getGrupo(),
							helper.getModulo(),
							helper.getFuncionalidade(),
							helper.getOperacao() );

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioFuncionalidadeOperacoesPorGrupoBean);				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());		
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO,
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

		retorno = Fachada.getInstancia().pesquisarTotalRelatorioFuncionalidadeOperacoesPorGrupo(
				(FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper) 
					getParametro("FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper"));
        
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFuncionalidadesOperacoesPorGrupo", this);
	}

}
