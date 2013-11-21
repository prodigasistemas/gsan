package gcom.relatorio.faturamento;

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

public class RelatorioContasRetidas extends TarefaRelatorio {

	private static final long serialVersionUID = 5761889246495751619L;

	public RelatorioContasRetidas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTAS_RETIDAS);
	}

	@Override
	public Object executar() throws TarefaException {
		
		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
				
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		Map<String, String> parametros = getParametros(mesAno, idGrupoFaturamento, sistemaParametro);
		
		@SuppressWarnings("rawtypes")
		Collection colecaoContasRetidas = (Collection) getParametro("colecaoContasRetidas");
		Collection<RelatorioContasRetidasBean> colecaoBean = this.inicializarBeanRelatorio(colecaoContasRetidas);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		byte[] retorno = null;
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioContasRetidasBean>)colecaoBean);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CONTAS_RETIDAS, parametros, ds, tipoFormatoRelatorio);
		
		try {
			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_CONTAS_RETIDAS, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
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
		AgendadorTarefas.agendarTarefa("RelatorioContasRetidas", this);
	}

	private Map<String, String> getParametros(String mesAno, String idGrupoFaturamento, SistemaParametro sistemaParametro) {
		Map<String, String> parametros = new HashMap<String, String>();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		parametros.put("tipoFormatoRelatorio", "R0002-C");
		
		return parametros;
	}


	@SuppressWarnings("rawtypes")
	private Collection<RelatorioContasRetidasBean> inicializarBeanRelatorio(Collection colecaoContasRetidas) {
		
		Collection<RelatorioContasRetidasBean> retorno = new ArrayList<RelatorioContasRetidasBean>();

		Iterator iter = colecaoContasRetidas.iterator();
		
		while (iter.hasNext()) {
			
			RelatorioContasRetidasHelper rel = (RelatorioContasRetidasHelper) iter.next();

			RelatorioContasRetidasBean relatorioContasRetidasBean = new RelatorioContasRetidasBean();
			
			relatorioContasRetidasBean.setAnoMesReferencia(rel.getAnoMesReferencia());
			relatorioContasRetidasBean.setUnidadeDeNegocio(rel.getUnidadeDeNegocio());
			relatorioContasRetidasBean.setGrupo(rel.getGrupo());
			relatorioContasRetidasBean.setQtdContasRetidas(rel.getQtdContasRetidas() != null ? Integer.valueOf(rel.getQtdContasRetidas()) : Integer.valueOf(0));
			
			retorno.add(relatorioContasRetidasBean);
		}

		return retorno;
	}
	
}