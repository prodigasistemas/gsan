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
 * classe responsável por criar o relatório de Solicitação de Acesso
 * 
 * [UC1093] Gerar Relatório de Solicitação de Acesso
 * 
 * @author Hugo Leonardo
 *
 * @date 22/011/2010
 */
public class RelatorioSolicitacaoAcesso extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioSolicitacaoAcesso(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_SOLICITACAO_ACESSO);
	}

	@Deprecated
	public RelatorioSolicitacaoAcesso() {
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
		
		FiltrarRelatorioSolicitacaoAcessoHelper filtroHelper = 
			(FiltrarRelatorioSolicitacaoAcessoHelper) getParametro("filtroSolicitacaoAcesso");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String periodo = (String) getParametro("periodo");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioSolicitacaoAcessoBean relatorioSolicitacaoAcessoBean = null;

		Collection<RelatorioSolicitacaoAcessoHelper> colecao = 
			Fachada.getInstancia().pesquisarRelatorioSolicitacaoAcesso(filtroHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {
				
				RelatorioSolicitacaoAcessoHelper relatorioHelper= (RelatorioSolicitacaoAcessoHelper) colecaoIterator.next();

				relatorioSolicitacaoAcessoBean = new RelatorioSolicitacaoAcessoBean(
						relatorioHelper.getMatricula(),
						relatorioHelper.getIdFuncionarioSolicitante(),
						relatorioHelper.getNomeFuncionarioSolicitante(),
						relatorioHelper.getIdFuncionarioSuperior(),
						relatorioHelper.getNomeFuncionarioSuperior(),
						relatorioHelper.getNomeUsuario(),
						relatorioHelper.getCpf(),
						relatorioHelper.getIdLotacao(),
						relatorioHelper.getNomeLotacao(),
						relatorioHelper.getDataInicial(),
						relatorioHelper.getDataFinal(),
						relatorioHelper.getDataSolicitacao(),
						relatorioHelper.getDataAutorizacao(),
						relatorioHelper.getSituacaoAcesso()
				);
				
				// adiciona o bean a coleção
				relatorioBeans.add(relatorioSolicitacaoAcessoBean);				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());		
		
		parametros.put("periodo", periodo);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_SOLICITACAO_ACESSO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_SOLICITACAO_ACESSO,
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

		retorno = 10;
        
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioSolicitacaoAcesso", this);
	}

}
