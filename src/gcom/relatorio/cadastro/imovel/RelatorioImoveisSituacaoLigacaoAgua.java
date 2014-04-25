package gcom.relatorio.cadastro.imovel;

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
 * classe responsável por criar o relatório de imoveis por situação da ligação de agua
 * 
 * @author Rafael Pinto
 * @created 03/12/2007
 */
public class RelatorioImoveisSituacaoLigacaoAgua extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisSituacaoLigacaoAgua(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA);
	}

	@Deprecated
	public RelatorioImoveisSituacaoLigacaoAgua() {
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

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro = 
			(FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper) getParametro("filtrarRelatorioImoveisSituacaoLigacaoAguaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioImoveisSituacaoLigacaoAguaBean relatorioImoveisSituacaoLigacaoAguaBean = null;

		Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> colecao =  
			fachada.pesquisarRelatorioImoveisSituacaoLigacaoAgua(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisSituacaoLigacaoAguaHelper helper = 
					(RelatorioImoveisSituacaoLigacaoAguaHelper) colecaoIterator.next();
				
				relatorioImoveisSituacaoLigacaoAguaBean = 
					new RelatorioImoveisSituacaoLigacaoAguaBean(helper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioImoveisSituacaoLigacaoAguaBean);				
				
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_SITUACAO_LIGACAO_AGUA,
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

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
				(FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper) 
					getParametro("filtrarRelatorioImoveisSituacaoLigacaoAguaHelper"));

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisSituacaoLigacaoAgua", this);

	}

}
