package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioFichaFiscalizacaoCadastral extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioFichaFiscalizacaoCadastral(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FICHA_FISCALIZACAO_CADASTRAL);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFichaFiscalizacaoCadastral", this);
	}

	@Override
	public Object executar() throws TarefaException {

		@SuppressWarnings("rawtypes")
		Collection colecaoDadosFicha = (Collection) getParametro("colecaoDadosFicha");
		Collection<RelatorioFichaFiscalizacaoCadastralBean> colecaoBean = this.inicializarBeanRelatorio(colecaoDadosFicha);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		Map<String, String> parametros = getParametros();

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioFichaFiscalizacaoCadastralBean>) colecaoBean);

		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_FICHA_FISCALIZACAO_CADASTRAL,
				parametros, ds, TarefaRelatorio.TIPO_PDF);

		return retorno;
	}

	private Map<String, String> getParametros() {
		Map<String, String> parametros = new HashMap<String, String>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		return parametros;
	}
	
	@SuppressWarnings("rawtypes")
	private Collection<RelatorioFichaFiscalizacaoCadastralBean> inicializarBeanRelatorio(
			Collection colecaoDadosFicha) {
		
		Collection<RelatorioFichaFiscalizacaoCadastralBean> retorno = new ArrayList<RelatorioFichaFiscalizacaoCadastralBean>();
		
		Iterator iterator = colecaoDadosFicha.iterator();
		
		while (iterator.hasNext()) {
			
			RelatorioFichaFiscalizacaoCadastralHelper helper = (RelatorioFichaFiscalizacaoCadastralHelper) iterator.next();

			RelatorioFichaFiscalizacaoCadastralBean bean = new RelatorioFichaFiscalizacaoCadastralBean(helper);
			
			retorno.add(bean);
		}

		return retorno;
	}
}
