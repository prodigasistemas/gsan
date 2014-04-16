package gcom.relatorio.gerencial.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.FiltrarRelatorioQuadroMetasAcumuladoHelper;
import gcom.gerencial.bean.QuadroMetasAcumuladoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioQuadroMetasAcumuladoBean;
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
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de quadro de metas acumulado
 * 
 * @author Bruno Barros
 * @created 20/12/2007
 */
public class RelatorioQuadroMetasAcumulado extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioQuadroMetasAcumulado(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_QUADRO_METAS_ACUMULADO);
	}

	@Deprecated
	public RelatorioQuadroMetasAcumulado() {
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

		FiltrarRelatorioQuadroMetasAcumuladoHelper filtro = 
			(FiltrarRelatorioQuadroMetasAcumuladoHelper) getParametro("filtrarRelatorioQuadroMetasAcumuladoHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioQuadroMetasAcumuladoBean relatorioQuadroMetasAcumuladoBean = null;

		Collection<QuadroMetasAcumuladoHelper> colecao = 
			fachada.pesquisarRelatorioQuadroMetasAcumulado(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				QuadroMetasAcumuladoHelper helper = 
					(QuadroMetasAcumuladoHelper) colecaoIterator.next();
				
				relatorioQuadroMetasAcumuladoBean = 
					new RelatorioQuadroMetasAcumuladoBean(helper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioQuadroMetasAcumuladoBean);				
				
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(filtro.getMesAnoReferencia()));
		

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_QUADRO_METAS_ACUMULADO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.QUADRO_METAS_ACUMULADO,
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
		int retorno = -1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioQuadoMetasAcumulado", this);

	}

}
