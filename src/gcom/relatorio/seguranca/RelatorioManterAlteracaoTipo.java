package gcom.relatorio.seguranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroAlteracaoTipo;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class RelatorioManterAlteracaoTipo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterAlteracaoTipo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_ALTERACAO_TIPO);
	}
	
	@Deprecated
	public RelatorioManterAlteracaoTipo() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param atividades
	 *            Description of the Parameter
	 * @param atividadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroAlteracaoTipo filtroAlteracaoTipo = (FiltroAlteracaoTipo) getParametro("filtroAlteracaoTipo");
		AlteracaoTipo alteracaoTipoParametros = (AlteracaoTipo) getParametro("alteracaoTipoParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterAlteracaoTipoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		filtroAlteracaoTipo.setConsultaSemLimites(true);

		Collection<AlteracaoTipo> colecaoAlteracoesTipo = fachada.pesquisar(filtroAlteracaoTipo,
				AlteracaoTipo.class.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoAlteracoesTipo != null && !colecaoAlteracoesTipo.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (AlteracaoTipo alteracaoTipo : colecaoAlteracoesTipo) {
				
				relatorioBean = new RelatorioManterAlteracaoTipoBean(
						// Código
						alteracaoTipo.getId().toString(), 
						
						// Descrição
						alteracaoTipo.getDescricao(),
						
						// Descrição Abreviada
						alteracaoTipo.getDescricaoAbreviada());
						
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

		if (alteracaoTipoParametros.getId() != null) {
			parametros.put("id",
					alteracaoTipoParametros.getId().toString());
		} else {
			parametros.put("id", "");
		}

		parametros.put("descricao", alteracaoTipoParametros.getDescricao());

		parametros.put("descricaoAbreviada", alteracaoTipoParametros.getDescricaoAbreviada());
		
		
		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MANTER_ALTERACAO_TIPO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_ATIVIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
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
		AgendadorTarefas.agendarTarefa("RelatorioManterAlteracaoTipo", this);
	}

}
