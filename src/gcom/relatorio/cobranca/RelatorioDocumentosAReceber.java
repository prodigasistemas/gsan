package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.cobranca.FiltroRelatorioDocumentosAReceberHelper;
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

public class RelatorioDocumentosAReceber extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioDocumentosAReceber(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DOCUMENTOS_A_RECEBER);
	}

	@Deprecated
	public RelatorioDocumentosAReceber() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		FiltroRelatorioDocumentosAReceberHelper helper 
			= (FiltroRelatorioDocumentosAReceberHelper) getParametro("filtro");
		
		Integer qtdRegistros = Fachada.getInstancia().countRelatorioDocumentosAReceber(helper);
		
		 if(qtdRegistros == 0){
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		 }
		
		return qtdRegistros;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDocumentosAReceber", this);

	}

	@Override
	public Object executar() throws TarefaException {


		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		FiltroRelatorioDocumentosAReceberHelper helper 
			= (FiltroRelatorioDocumentosAReceberHelper) getParametro("filtro");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoDados = fachada.pesquisarRelatorioDocumentosAReceber(helper);

		for (Iterator iterator = colecaoDados.iterator(); iterator.hasNext();) {
			RelatorioDocumentosAReceberBean bean = (RelatorioDocumentosAReceberBean) iterator.next();
			relatorioBeans.add(bean);
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(helper.getMesAno()));

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_DOCUMENTOS_A_RECEBER, parametros, ds,
				tipoFormatoRelatorio);
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_DOCUMENTOS_A_RECEBER,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

}
