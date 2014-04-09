package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.bean.GerarNumeracaoRAManualHelper;
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
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioNumeracaoRAManual extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioNumeracaoRAManual(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NUMERACAO_RA_MANUAL);
	}
	
	@Deprecated
	public RelatorioNumeracaoRAManual() {
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

		GerarNumeracaoRAManualHelper gerarNumeracaoRAManualHelper = (GerarNumeracaoRAManualHelper) getParametro("gerarNumeracaoRAManualHelper");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioNumeracaoRAManualBean relatorioBean = null;
		
		Collection colecaoNumeracao = gerarNumeracaoRAManualHelper.getColecaoNumeracao();

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoNumeracao != null
				&& !colecaoNumeracao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoNumeracaoIterator = colecaoNumeracao
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoNumeracaoIterator.hasNext()) {

				String numeracao = (String) colecaoNumeracaoIterator
						.next();
				
				relatorioBean = new RelatorioNumeracaoRAManualBean(
						
						// Unidade
						gerarNumeracaoRAManualHelper.getDescricaoUnidadeOrganizacional(),
						
						// Numeração
						numeracao);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
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

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_NUMERACAO_RA_MANUAL,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.NUMERACAO_RA_MANUAL,
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
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNumeracaoRAManual", this);
	}
}
