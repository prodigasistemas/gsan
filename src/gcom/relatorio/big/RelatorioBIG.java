package gcom.relatorio.big;

import gcom.batch.Relatorio;
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

public class RelatorioBIG extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioBIG(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BIG);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioBIG", this);
	}

	@Override
	public Object executar() throws TarefaException {
				
		@SuppressWarnings("rawtypes")
		Collection colecaoDadosBIG = (Collection) getParametro("colecaoDadosBIG");
		Collection<RelatorioBIGBean> colecaoBean = this.inicializarBeanRelatorio(colecaoDadosBIG);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("mesAno", (String) getParametro("mesAno"));
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioBIGBean>) colecaoBean);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_BIG,
				parametros, ds, TarefaRelatorio.TIPO_XLS);
		
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	private Collection<RelatorioBIGBean> inicializarBeanRelatorio(
			Collection colecaoDadosBIG) {
		
		Collection<RelatorioBIGBean> retorno = new ArrayList<RelatorioBIGBean>();

		Iterator iterator = colecaoDadosBIG.iterator();
		
		while (iterator.hasNext()) {
			
			RelatorioBIGHelper helper = (RelatorioBIGHelper) iterator.next();

			RelatorioBIGBean relatorioBIGBean = new RelatorioBIGBean(helper);
			
			retorno.add(relatorioBIGBean);
		}

		return retorno;
	}

}
