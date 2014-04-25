package gcom.relatorio.cadastro.micromedicao;

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
 * [UC0997] Gerar Resumo de Ligações por Capacidade de Hidrômetro
 * 
 * @author Hugo Leonardo
 *
 * @date 29/03/2010
 */
public class RelatorioResumoLigacoesCapacidadeHidrometro extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioResumoLigacoesCapacidadeHidrometro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO);
	}

	@Deprecated
	public RelatorioResumoLigacoesCapacidadeHidrometro() {
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

		RelatorioResumoLigacoesCapacidadeHidrometroHelper relatorioHelper = 
			(RelatorioResumoLigacoesCapacidadeHidrometroHelper) getParametro("relatorioResumoLigacoesCapacidadeHidrometroHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		String mesAno = (String) getParametro("mesAno");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection colecao =  
			fachada.pesquisarRelatorioResumoLigacoesCapacidadeHidrometro(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			for (Iterator iterator = colecao.iterator(); iterator.hasNext();) {
				RelatorioResumoLigacoesCapacidadeHidrometroBean bean = (RelatorioResumoLigacoesCapacidadeHidrometroBean) iterator.next();
				relatorioBeans.add(bean);
			}
		
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());		
	
		parametros.put("mesAno" , mesAno);
				
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO,
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
		
		retorno = 
			Fachada.getInstancia().countRelatorioResumoLigacoesCapacidadeHidrometro(
				(RelatorioResumoLigacoesCapacidadeHidrometroHelper) 
					getParametro("relatorioResumoLigacoesCapacidadeHidrometroHelper"));
        
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");

		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoLigacoesCapacidadeHidrometro", this);

	}

}
