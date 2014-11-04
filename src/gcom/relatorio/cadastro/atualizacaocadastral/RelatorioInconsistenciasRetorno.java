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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RelatorioInconsistenciasRetorno extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioInconsistenciasRetorno(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_INCONSISTENCIAS_RETORNO_ATUALIZACAO_CADASTRAL);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioInconsistenciasRetornoAtualizacaoCadastral", this);
	}

	@Override
	public Object executar() throws TarefaException {

		@SuppressWarnings("rawtypes")
		Map<String, List<String>> colecaoErrosCadastro = (Map<String, List<String>>) getParametro("colecaoErrosCadastro");
		Collection<RelatorioInconsistenciasRetornoBean> colecaoBean = this.inicializarBeanRelatorio(colecaoErrosCadastro);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		Map<String, String> parametros = getParametros();

		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioInconsistenciasRetornoBean>) colecaoBean);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_INCONSISTENCIAS_RETORNO_ATUALIZACAO_CADASTRAL,
				parametros, ds, TarefaRelatorio.TIPO_PDF);

		return retorno;
	}

	private Map<String, String> getParametros() {
		Map<String, String> parametros = new HashMap<String, String>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeArquivo", (String) getParametro("nomeArquivo"));
		parametros.put("totalImoveis", (String) getParametro("totalImoveis"));
		parametros.put("totalImoveisComErro", (String) getParametro("totalImoveisComErro"));
		
		return parametros;
	}

	@SuppressWarnings("rawtypes")
	private Collection<RelatorioInconsistenciasRetornoBean> inicializarBeanRelatorio(
			Map<String, List<String>> mapErrosCadastro) {

		Collection<RelatorioInconsistenciasRetornoBean> retorno = new ArrayList<RelatorioInconsistenciasRetornoBean>();
		
		for (Entry<String, List<String>> entry : mapErrosCadastro.entrySet()) {
			RelatorioInconsistenciasRetornoBean bean = new RelatorioInconsistenciasRetornoBean();
			bean.setMatricula(entry.getKey());

			bean.setErros(this.getErrosImovel(entry));

			retorno.add(bean);
		} 

		return retorno;
	}

	private String getErrosImovel(Entry<String, List<String>> entry) {
		List<String> listaErros = entry.getValue();
		
		String errosImovel = "\n";
		
		for (String erro : listaErros) {
			errosImovel += erro + "\n";
		}
		
		return errosImovel;
	}
}
