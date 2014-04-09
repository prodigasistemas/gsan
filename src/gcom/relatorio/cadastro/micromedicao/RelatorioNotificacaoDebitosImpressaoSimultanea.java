package gcom.relatorio.cadastro.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.micromedicao.RelatorioNotificacaoDebitosImpressaoSimultaneaHelper;
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

public class RelatorioNotificacaoDebitosImpressaoSimultanea extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioNotificacaoDebitosImpressaoSimultanea(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITOS_IMPRESSAO_SIMULTANEA);
	}

	@Deprecated
	public RelatorioNotificacaoDebitosImpressaoSimultanea() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		RelatorioNotificacaoDebitosImpressaoSimultaneaHelper filtro = 
			(RelatorioNotificacaoDebitosImpressaoSimultaneaHelper) getParametro("relatorioNotificacaoDebitosImpressaoSimultaneaHelper");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();


		Collection<Object> colecaoNotificacaoDebitosImpressaoSimultanea = 
			fachada.pesquisarNotificacaoDebitosImpressaoSimultanea(filtro);
				
		
		RelatorioNotificacaoDebitosImpressaoSimultaneaBean relatorioBean = null;
				
		if (colecaoNotificacaoDebitosImpressaoSimultanea != null 
				&& !colecaoNotificacaoDebitosImpressaoSimultanea.isEmpty()) {
			
		    Iterator iteratorRNDIS = colecaoNotificacaoDebitosImpressaoSimultanea.iterator();		

			while (iteratorRNDIS.hasNext()) {
				relatorioBean = new RelatorioNotificacaoDebitosImpressaoSimultaneaBean();								
				Object[] objRNDIS = (Object[]) iteratorRNDIS.next();	
				
				relatorioBean.setAnoMesReferencia(String.valueOf(objRNDIS[0]));
				relatorioBean.setEmpresa((String)objRNDIS[1]);
				relatorioBean.setGrupo(String.valueOf(objRNDIS[2]));												
				relatorioBean.setLocalidade((String)objRNDIS[3]);
				relatorioBean.setLocalidadeID(String.valueOf(objRNDIS[4]));
				relatorioBean.setSetorComercial(String.valueOf(objRNDIS[5]));				
				relatorioBean.setRota(String.valueOf(objRNDIS[6]));				
				relatorioBean.setQuantidade((Integer)objRNDIS[7]);				
				
				relatorioBeans.add(relatorioBean);							
			}			
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMesReferencia", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferencia()));
		parametros.put("cabecalhoTipo", filtro.getCabecalhoTipo());
		parametros.put("tipoFormatoRelatorio", "R1022");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITOS_IMPRESSAO_SIMULTANEA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_NOTIFICACAO_DEBITOS_IMPRESSAO_SIMULTANEA,
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
	public int calcularTotalRegistrosRelatorio() {
		
		RelatorioNotificacaoDebitosImpressaoSimultaneaHelper filtro = 
			(RelatorioNotificacaoDebitosImpressaoSimultaneaHelper) getParametro("relatorioNotificacaoDebitosImpressaoSimultaneaHelper");

		Fachada fachada = Fachada.getInstancia();

		int retorno = 0;
		 
		retorno = fachada.pesquisarNotificacaoDebitosImpressaoSimultanea(filtro).size();
		
	    if(retorno == 0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioNotificacaoDebitosImpressaoSimultanea", this);

	}

}
