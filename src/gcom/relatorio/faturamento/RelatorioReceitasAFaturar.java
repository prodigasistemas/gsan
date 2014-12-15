package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioReceitasAFaturar extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioReceitasAFaturar(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		Collection colecaoDados = (Collection) getParametro("colecaoDadosRelatorio");
		Collection<RelatorioReceitasAFaturarBean> colecaoBean = this.inicializarBeanRelatorio(colecaoDados);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("idGrupo", String.valueOf(getParametro("idGrupo")));
		parametros.put("ano", (String) getParametro("ano"));
		parametros.put("mes", (String) getParametro("mes"));
		
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioReceitasAFaturarBean>) colecaoBean);
		
		if(getParametro("idGrupo") == null)
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RECEITAS_A_FATURAR_SINTETICO, parametros, ds, TarefaRelatorio.TIPO_PDF);
		else {
			Format formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dataLeituraAnterior = formatter.format(getParametro("dataLeituraAnterior"));
			String dataLeituraAtual = formatter.format(getParametro("dataLeituraAtual"));
			parametros.put("dataLeituraAnterior", dataLeituraAnterior);
			parametros.put("dataLeituraAtual", dataLeituraAtual);
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RECEITAS_A_FATURAR_ANALITICO, parametros, ds, TarefaRelatorio.TIPO_PDF);
		}
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {}
	
	private Collection<RelatorioReceitasAFaturarBean> inicializarBeanRelatorio(Collection colecaoDados) {
		
		Collection<RelatorioReceitasAFaturarBean> retorno = new ArrayList<RelatorioReceitasAFaturarBean>();

		Iterator iterator = colecaoDados.iterator();
		
		while (iterator.hasNext()) {
			
			RelatorioReceitasAFaturarHelper helper = (RelatorioReceitasAFaturarHelper) iterator.next();

			RelatorioReceitasAFaturarBean relatorioBean = new RelatorioReceitasAFaturarBean(helper);
			
			retorno.add(relatorioBean);
		}

		return retorno;
	}
}
