package gcom.gui.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.faturamento.RelatorioImpostosPorClienteResponsavelBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * Essa classe é responsável por realizar a formatação
 * e lógica do relatório
 *
 * @author José Guilherme Macedo Vieira
 * @date 09/06/2009
 */
public class RelatorioImpostosPorClienteResponsavel extends TarefaRelatorio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioImpostosPorClienteResponsavel(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int tamanhoColecao = new Integer((Integer)this.getParametro("tamanhoColecao"));
		
		return tamanhoColecao;
	}
	
	@Override
	public Object executar() throws TarefaException {
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String tipoRelatorio = ((String) getParametro("tipoRelatorio")).toLowerCase();
		
		String anoMes = (String) getParametro("anoMes");
		
		//valor de retorno
		byte[] retorno = null;
			
		// coleção de beans do relatório
		List relatorioBeans = (List) getParametro("beansRelatorio");
		
		String tipoImposto = (String) getParametro("tipoImposto");
		
		Fachada fachada = Fachada.getInstancia();
		
		//Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Adiciona a imagem do relatorio identificando a empresa aos parâmetros
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMes",anoMes);
		parametros.put("tipoImposto", tipoImposto);
		
		if(tipoRelatorio != null && tipoRelatorio.equalsIgnoreCase("analitico")){
			tipoRelatorio = "ANALÍTICO";
		}else if(tipoRelatorio.equalsIgnoreCase("sintetico")){
			tipoRelatorio = "SINTÉTICO";
		}
		parametros.put("tipoRelatorio",tipoRelatorio);
				
		if (relatorioBeans != null && !relatorioBeans.isEmpty()) {
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL,
						parametros, ds, tipoFormatoRelatorio);	
		} else {
			/*
			 * Adicionado por: Mariana Victor - 24/02/2011
			 */
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioImpostosPorClienteResponsavelBean relatorioBean = 
				new RelatorioImpostosPorClienteResponsavelBean();
				
			relatorioBeans.add(relatorioBean);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImpostosPorClienteResponsavel", this);
		
	}
	
	
	
	
}
