package gcom.relatorio.cadastro.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1180] Gerar Relatório de Imóveis Com Leituras
 * 
 * @author Magno Gouveia
 * @date 03/06/2011
 */
public class RelatorioImoveisComLeituras extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisComLeituras(Usuario usuario, String constanteRelatorio) {
		super(usuario, constanteRelatorio);
	}
	
	// TODO verificar com Lira se relamente
	public Object executar() throws TarefaException {

		Collection<? extends Object> beans = null;
		
		FiltrarRelatorioImoveisComLeiturasHelper helper = (FiltrarRelatorioImoveisComLeiturasHelper) getParametro("filtro");
		
		switch ((Integer) getParametro("opcaoTipoRelatorio")) {
			case 1:
			case 2:
			case 3:
				beans = Fachada.getInstancia().filtrarRelatorioImoveisComLeiturasQuantitativos(helper, (Integer) getParametro("parametroPersistirRelatorio"));
				break;
			case 4:
			case 5:
			case 6:
				beans = Fachada.getInstancia().filtrarRelatorioImoveisComLeiturasRelacao(helper, (Integer) getParametro("parametroPersistirRelatorio"));
				break;
			case 7:
				beans = Fachada.getInstancia().filtrarRelatorioImoveisComLeiturasTipo7(helper);
				break;
		}

		// Parâmetros do Relatório
		Map<String, Object> params = new HashMap<String, Object>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		params.put("imagem", sistemaParametro.getImagemRelatorio());
		params.put("numeroRelatorio", "R1180");
		params.put("filtrosUtilizados", (String) getParametro("filtrosUtilizados"));
		params.put("opcaoTipoRelatorioNome", (String) getParametro("opcaoTipoRelatorioNome"));

		byte[] retorno = null;
		
		// Não existem dados para a exibição do relatório.
		if (beans == null || beans.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		RelatorioDataSource ds = new RelatorioDataSource((List) beans);
		
		retorno = this.gerarRelatorio(super.getNomeRelatorio(), params, ds, TarefaRelatorio.TIPO_PDF);

		try {
			persistirRelatorioConcluido(retorno, (Integer) getParametro("parametroPersistirRelatorio"), this.getIdFuncionalidadeIniciada());
		} catch (Exception e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {

		return 1;
	}

	public void agendarTarefaBatch() {
		// AgendadorTarefas.agendarTarefa("RelatorioImoveisComLeituristasQuantitativos", this);
	}
}
