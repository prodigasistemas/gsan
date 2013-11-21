package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.relatorio.micromedicao.FiltrarRelatorioAcompanhamentoLeituristaForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioAcompanhamentoLeiturista extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioAcompanhamentoLeiturista(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_LEITURISTA);
	}
	
	@Deprecated
	public RelatorioAcompanhamentoLeiturista() {
		super(null, "");
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection rotas = (Collection) getParametro("colecaoRotas");
		FiltrarRelatorioAcompanhamentoLeituristaForm form = (FiltrarRelatorioAcompanhamentoLeituristaForm) getParametro("filtrarRelatorioAcompanhamentoLeituristaForm");
		
		RelatorioAcompanhamentoLeituristaHelper helper;
		
		helper = criarHelper(form,rotas);
		
		Collection rotasLeituristas = fachada.pesquisarLeituristasDasRotas(helper);
		
		
		return rotasLeituristas.size();
	}

	@Override
	public Object executar() throws TarefaException {
		//valor de retorno
		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		
		Collection rotas = (Collection) getParametro("colecaoRotas");
		FiltrarRelatorioAcompanhamentoLeituristaForm form = (FiltrarRelatorioAcompanhamentoLeituristaForm) getParametro("filtrarRelatorioAcompanhamentoLeituristaForm");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAcompanhamentoLeituristaBean relatorioBean = null;
		
		RelatorioAcompanhamentoLeituristaHelper helper;
		
		helper = criarHelper(form,rotas);
			
		
		Collection<RelatorioAcompanhamentoLeituristaHelper> colecao = fachada
				.pesquisarRelatorioAcompanhamentoLeiturista(helper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();
			
			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {
				
				RelatorioAcompanhamentoLeituristaHelper relatorioAcompanhamentoLeituristaHelper = (RelatorioAcompanhamentoLeituristaHelper) colecaoIterator
						.next();

				relatorioBean = new RelatorioAcompanhamentoLeituristaBean(relatorioAcompanhamentoLeituristaHelper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
				

			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
		parametros.put("imprimirHorarios",helper.getIndicadorHorario());
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_LEITURISTA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_ACOMPANHAMENTO_LEITURISTA,
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
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoLeiturista", this);
	}
	
	private RelatorioAcompanhamentoLeituristaHelper criarHelper(FiltrarRelatorioAcompanhamentoLeituristaForm form, Collection rotas ){
		RelatorioAcompanhamentoLeituristaHelper retorno = new RelatorioAcompanhamentoLeituristaHelper();
		
		if(form.getMesAno()!=null &&
				!form.getMesAno().equals("")){
			retorno.setMesAno(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno()));
		}
			
		retorno.setRotas(rotas);
		
		if(form.getIndicadorHorario()!=null && !form.getIndicadorHorario().equals("")){
			retorno.setIndicadorHorario(form.getIndicadorHorario());
		}
		
		if(form.getIdEmpresa()!=null && !form.getIdEmpresa().equals("-1")){
			retorno.setIdEmpresa(form.getIdEmpresa());
		}
		if(form.getIdLeiturista()!=null && !form.getIdLeiturista().equals("-1")){
			retorno.setIdLeiturista(form.getIdLeiturista());
		}
		
		if(form.getIdGrupoFaturamento()!=null && !form.getIdGrupoFaturamento().equals("-1")){
			retorno.setIdGrupoFaturamento(form.getIdGrupoFaturamento());
		}
		return retorno;
		
	}

}
