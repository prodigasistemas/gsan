package gcom.relatorio.micromedicao;

import gcom.fachada.Fachada;
import gcom.micromedicao.TelemetriaMovReg;
import gcom.micromedicao.bean.FiltrarLeiturasTelemetriaHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioLeiturasTelemetria extends TarefaRelatorio {
	
private static final long serialVersionUID = 1L;
	
	public RelatorioLeiturasTelemetria(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LEITURAS_TELEMETRIA);
	}
	
	@Deprecated
	public RelatorioLeiturasTelemetria() {
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		
		// helper pesquisa
		FiltrarLeiturasTelemetriaHelper helper = 
			(FiltrarLeiturasTelemetriaHelper) getParametro("filtroHelper");
		
		//  tipo relatorio
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		String periodoEnvio = "";
		String periodoLeitura = "";
		
		if(helper.getPeriodoEnvioInicial()!=null &&
				helper.getPeriodoEnvioFinal()!=null){
			periodoEnvio =
				Util.formatarData(helper.getPeriodoEnvioInicial())+" a "+
				Util.formatarData(helper.getPeriodoEnvioFinal());
		}
		if(helper.getPeriodoLeituraInicial()!=null &&
				helper.getPeriodoLeituraFinal()!=null){
			periodoLeitura =
				Util.formatarData(helper.getPeriodoLeituraInicial())+" a "+
				Util.formatarData(helper.getPeriodoLeituraFinal());
		}
			
		String situcaoLeitura = "";
		
		switch (Integer.parseInt(helper.getSituacaoLeitura())) {
		case 1:
			situcaoLeitura = "Processadas"; 
			break;
		case 2:
			situcaoLeitura = "Não Processadas"; 
			break;
		case 3:
			situcaoLeitura = "Processadas / Não Processadas"; 
			break;	
		default:
			break;
		}
		
		parametros.put("imagem", fachada.pesquisarParametrosDoSistema().getImagemRelatorio());
		parametros.put("relatorio", "R1069");
		parametros.put("leitura", situcaoLeitura);
		parametros.put("periodoEnvio", periodoEnvio);
		parametros.put("periodoLeitura", periodoLeitura);

		Collection<TelemetriaMovReg> colecaoDados = fachada.filtrarLeiturasTelemetria(helper);
		HashMap<String, Integer> mapTotalizadoresData = new HashMap<String, Integer>();
		
		for (TelemetriaMovReg telemetriaMovReg : colecaoDados) {

			RelatorioLeiturasTelemetriaBean relatorioLeiturasTelemetriaBean = 
				new RelatorioLeiturasTelemetriaBean(
						Util.formatarDataComHora(telemetriaMovReg.getDataLeitura()),
						this.formatarInscricao(telemetriaMovReg.getInscricao().toString()),
						telemetriaMovReg.getLeitura().toString(),
						telemetriaMovReg.getNumeroHidrometro().toString(),
						Util.retornaMatriculaImovelFormatada(telemetriaMovReg.getImovel().getId()),
						telemetriaMovReg.getMedicaoTipo().getId().toString()						
						);

			relatorioBeans.add(relatorioLeiturasTelemetriaBean);
			
			//Totalizar por Data
			
			String dataFluxo = Util.formatarData(telemetriaMovReg.getTelemetriaMov().getEnvio());
			
			if(mapTotalizadoresData.containsKey(dataFluxo)){
				mapTotalizadoresData.put(dataFluxo,(mapTotalizadoresData.get(dataFluxo) + 1));
			}else{
				mapTotalizadoresData.put(dataFluxo, new Integer(1));
			}
			
		}
			
		relatorioBeans.add(new RelatorioLeiturasTelemetriaBean());
		relatorioBeans.add(new RelatorioLeiturasTelemetriaBean());
		relatorioBeans.add(new RelatorioLeiturasTelemetriaBean());
		
		for (Map.Entry<String, Integer> entry : mapTotalizadoresData.entrySet()) {  
			String data = entry.getKey();  
		    Integer quantidade = entry.getValue(); 
		    
		    RelatorioLeiturasTelemetriaBean relatorioLeiturasTelemetriaBean = 
				new RelatorioLeiturasTelemetriaBean("Total: "+data,quantidade.toString());
		    
		    relatorioBeans.add(relatorioLeiturasTelemetriaBean);
		    
		}
		
		RelatorioLeiturasTelemetriaBean relatorioLeiturasTelemetriaBean = 
			new RelatorioLeiturasTelemetriaBean("Total Geral: ",colecaoDados.size()+"");
	    
	    relatorioBeans.add(relatorioLeiturasTelemetriaBean);
		
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(this.nomeRelatorio,parametros, ds, tipoFormatoRelatorio);
			
//		// ------------------------------------
//		// Grava o relatório no sistema
//		try {
//
//			Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
//			
//			persistirRelatorioConcluido(retorno,
//					Relatorio.???,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relatório no sistema", e);
//		}
//		// ------------------------------------
		
		// retorna o relatório gerado
		return retorno;
	}
	
	private String formatarInscricao(String inscricao){
		
		String parte1 = "";
		String parte2 = "";
		String parte3 = "";
		String parte4 = "";
		String parte5 = "";
		
		String retorno = inscricao;
			
		if(inscricao.length()==16){
			parte1 = inscricao.substring(0, 3);
			parte2 = inscricao.substring(3, 6);
			parte3 = inscricao.substring(6, 9);
			parte4 = inscricao.substring(9, 13);
			parte5 = inscricao.substring(13);
			
			retorno = parte1+"."+parte2+"."+parte3+"."+parte4+"."+parte5;
		}
		
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioLeiturasTelemetria", this);

	}

}
