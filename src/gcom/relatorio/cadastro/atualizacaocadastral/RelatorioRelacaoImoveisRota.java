package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioRelacaoImoveisRota extends TarefaRelatorio {

	public RelatorioRelacaoImoveisRota(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELACAO_IMOVEIS_ROTA);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
	
	@Override
	public void agendarTarefaBatch() {
		
	}

	@Override
	public Object executar() throws TarefaException {
		@SuppressWarnings("rawtypes")
		ArrayList<RelatorioRelacaoImoveisRotaBean> colecaoBean = (ArrayList<RelatorioRelacaoImoveisRotaBean>) getParametro("colecaoDadosRelacaoImoveisRota");

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("idLocalidade", (String) getParametro("idLocalidade"));
		parametros.put("nomeLocalidade", (String) colecaoBean.get(0).getNomeLocalidade());
		parametros.put("cdSetorComercial", (String) getParametro("cdSetorComercial"));
		parametros.put("cdRota", (String) getParametro("cdRota"));
		parametros.put("totalRegistros", (String) getParametro("totalRegistros"));
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioRelacaoImoveisRotaBean>) colecaoBean);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RELACAO_IMOVEIS_ROTA,
				parametros, ds, TarefaRelatorio.TIPO_PDF);
		
		return retorno;
	}

}
