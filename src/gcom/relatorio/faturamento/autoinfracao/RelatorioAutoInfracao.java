package gcom.relatorio.faturamento.autoinfracao;

import gcom.batch.Relatorio;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório
 * 
 * @author Rafael Corrêa
 * @created 10/09/2008
 */
public class RelatorioAutoInfracao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAutoInfracao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_AUTO_INFRACAO);
	}
	
	@Deprecated
	public RelatorioAutoInfracao() {
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

		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) getParametro("unidadeNegocio");
		Funcionario funcionario = (Funcionario) getParametro("funcionario");
		Integer dataPagamentoInicial = (Integer) getParametro("dataPagamentoInicial");
		Integer dataPagamentoFinal = (Integer) getParametro("dataPagamentoFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Collection colecaoRelatorioAutoInfracaoBean = fachada
				.pesquisarDadosRelatorioAutoInfracao(unidadeNegocio.getId(),
						funcionario.getId(), 
						dataPagamentoInicial,
						dataPagamentoFinal);
		
		if (colecaoRelatorioAutoInfracaoBean != null && !colecaoRelatorioAutoInfracaoBean.isEmpty()) {
			relatorioBeans.addAll(colecaoRelatorioAutoInfracaoBean);
		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		if (unidadeNegocio.getId() != null) {
			parametros.put("unidadeNegocio", unidadeNegocio.getNome());
		} else {
			parametros.put("unidadeNegocio", "");
		}
		
		if (funcionario.getId() != null) {
			parametros.put("funcionario", funcionario.getNome());
		} else {
			parametros.put("funcionario", "");
		}
		
		if (dataPagamentoInicial != null) {
			parametros.put("periodoPagamento", Util.formatarAnoMesParaMesAno(dataPagamentoInicial) + " a " + Util.formatarAnoMesParaMesAno(dataPagamentoFinal));
		} else {
			parametros.put("periodoPagamento", "");
		}
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_AUTO_INFRACAO,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_RESOLUCAO_DIRETORIA,
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

//		retorno = Fachada
//				.getInstancia()
//				.pesquisarDadosParaLeituraRelatorioCount(
//						(GerarDadosLeituraHelper) getParametro("gerarDadosLeituraHelper"));

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAutoInfracao", this);
	}
}
