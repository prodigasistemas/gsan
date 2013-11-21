package gcom.relatorio.cadastro;

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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1170] - Gerar Relatório Acesso ao SPC
 * 
 * @author Diogo Peixoto
 * @since 09/05/2011
 * 
 */
public class RelatorioAcessoSPC extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;

	public RelatorioAcessoSPC(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public Object executar() throws TarefaException {

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Collection<RelatorioAcessoSPCBean> beans = (Collection<RelatorioAcessoSPCBean>)getParametro("dadosRelatorio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
		String dataReferencia = (String) getParametro("dataReferencia");
		String unidade = (String) getParametro("unidade");
		String usuario = (String) getParametro("usuario");
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1170");
		parametros.put("periodoReferencia", dataReferencia);
		parametros.put("usuario", usuario);
		parametros.put("unidade", unidade);
		
		byte[] retorno = null;

		if (beans == null || beans.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) beans);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ACESSO_SPC, parametros, ds, tipoFormatoRelatorio);
		
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_ACESSO_SPC, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcessoSPC", this);
	}
}