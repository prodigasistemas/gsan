package gcom.relatorio.cadastro.atualizacaocadastral;

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

public class RelatorioImoveisSituacaoPeriodo extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioImoveisSituacaoPeriodo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_SITUACAO_PERIODO);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		String dataInicial = (String) getParametro("dataInicial");
		String dataFinal = (String) getParametro("dataFinal");
		String descricaoSituacaoCadastral = (String) getParametro("descricaoSituacaoCadastral");
		Collection<Integer> colecaoImoveis = (Collection) getParametro("colecaoImoveis");
		if (colecaoImoveis == null || colecaoImoveis.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		} else {
		Integer qtdeImoveis = colecaoImoveis.size();
		Collection<RelatorioImoveisSituacaoPeriodoBean> colecaoBean = this.inicializarBeanRelatorio(colecaoImoveis);
		

		Map<String, String> parametros = getParametros(dataInicial, dataFinal, descricaoSituacaoCadastral, qtdeImoveis, sistemaParametro);
		
		byte[] retorno = null;
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioImoveisSituacaoPeriodoBean>)colecaoBean);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_SITUACAO_PERIODO, parametros, ds, tipoFormatoRelatorio);
		
		try {
			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_SITUACAO_PERIODO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
		}
	}
	
	private Map<String, String> getParametros(String dataInicial, String dataFinal, String descricaoSituacaoCadastral, Integer qtdeImoveis, SistemaParametro sistemaParametro) {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("dataInicial", dataInicial);
		parametros.put("dataFinal", dataFinal);
		parametros.put("descricaoSituacaoCadastral", descricaoSituacaoCadastral);
		parametros.put("qtdeImoveis", String.valueOf(qtdeImoveis));
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisSituacaoPeriodo", this);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private Collection<RelatorioImoveisSituacaoPeriodoBean> inicializarBeanRelatorio(Collection<Integer> colecaoImoveis) {
		Collection<RelatorioImoveisSituacaoPeriodoBean> retorno = new ArrayList<RelatorioImoveisSituacaoPeriodoBean>();
		Iterator iter = colecaoImoveis.iterator();
		
		while(iter.hasNext()) {
			Integer idImovel = (Integer) iter.next();
			RelatorioImoveisSituacaoPeriodoBean bean = new RelatorioImoveisSituacaoPeriodoBean();
			bean.setIdImovel(idImovel);
			
			retorno.add(bean);
		}
		
		return retorno;
	}

}
