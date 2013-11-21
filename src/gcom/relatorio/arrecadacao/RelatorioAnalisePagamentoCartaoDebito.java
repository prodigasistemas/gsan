package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.bean.ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioAnalisePagamentoCartaoDebito extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioAnalisePagamentoCartaoDebito(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISE_PAGAMENTO_CARTAO_DEBITO);
	}

	@Deprecated
	public RelatorioAnalisePagamentoCartaoDebito() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper 
			= (ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper) getParametro("helper");
		
		// coleção de beans do relatório
		List<RelatorioAnalisePagamentoCartaoDebitoBean> relatorioBeans = 
			Fachada.getInstancia().pesquisarBeansRelatorioAnalisePagamentoCartaoDebito(helper);
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("total", 
				Fachada.getInstancia()
				.relatorioAnalisePagamentoCartaoDebitoCount(helper)
				.toString());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ANALISE_PAGAMENTO_CARTAO_DEBITO, parametros, ds,
				tipoFormatoRelatorio);
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_ANALISE_PAGAMENTO_CARTAO_DEBITO,
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
		
		ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper = 
			(ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper) this.getParametro("helper");
		
		Integer count = Fachada.getInstancia().relatorioAnalisePagamentoCartaoDebitoCount(helper);
		
		if(count == null || (count!= null && count==0)){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return count;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnalisePagamentoCartaoDebito", this);
	}

}
