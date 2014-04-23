package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
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

/**
 * classe responsável por criar o relatório de Histórico Medição e Consumo da Ligação de Água
 * 
 * @author Ana Maria
 * @date 14/02/2007
 * 
 */
public class RelatorioMedicaoConsumoLigacaoAgua extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioMedicaoConsumoLigacaoAgua(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MEDICAO_CONSUMO_LIGACAO_AGUA);
	}
	
	@Deprecated
	public RelatorioMedicaoConsumoLigacaoAgua() {
		super(null, "");
	}

	private Collection<RelatorioMedicaoConsumoLigacaoAguaBean> inicializarBeanRelatorio(
			Collection<MedicaoHistorico> dadosRelatorio, Collection<ImovelMicromedicao> dadosRelatorio2) {

		Collection<RelatorioMedicaoConsumoLigacaoAguaBean> retorno = new ArrayList();
		
		//Histórico Medição
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			MedicaoHistorico medicaoHistorico = (MedicaoHistorico)iterator.next();
			
			String mesAnoMedicao = ""+medicaoHistorico.getMesAno();
			
			String dataLeituraInformada =  "";
			if(medicaoHistorico.getDataLeituraAtualInformada() != null){
				dataLeituraInformada = Util.formatarData(medicaoHistorico.getDataLeituraAtualInformada());
			}
			String leituraInformada = "";
			if (medicaoHistorico.getLeituraAtualInformada() != null){
				leituraInformada = ""+ medicaoHistorico.getLeituraAtualInformada();
			}
			
			String dataLeituraFaturada = "" ;
			if (medicaoHistorico.getDataLeituraAtualFaturamento() != null){
				dataLeituraFaturada = Util.formatarData(medicaoHistorico.getDataLeituraAtualFaturamento());
			}
			
			String leituraFaturada = "";
			if (medicaoHistorico.getLeituraAtualFaturamento() != 0){
				leituraFaturada = "" + medicaoHistorico.getLeituraAtualFaturamento();
			}
			String anormalidadeInformada = "";
			if(medicaoHistorico.getLeituraAnormalidadeInformada().getDescricao() != null){
				anormalidadeInformada = "" + medicaoHistorico.getLeituraAnormalidadeInformada().getDescricao();
			}
			
			String anormalidadeFaturada = "";
			if(medicaoHistorico.getLeituraAnormalidadeFaturamento().getDescricao() != null){
				anormalidadeFaturada = "" + medicaoHistorico.getLeituraAnormalidadeFaturamento().getDescricao();
			}
			
			String leituraAtual = "";
			if(medicaoHistorico.getLeituraSituacaoAtual().getDescricao() != null){
				leituraAtual = "" + medicaoHistorico.getLeituraSituacaoAtual().getDescricao();
			}
			
			String mesAnoConsumo = null;
			String consumoMedido = null;
			String consumoFaturado = null;
			String anormalidadeConsumo = null;
			String diasConsumo = null;
			String tipoConsumo = null;
			
			RelatorioMedicaoConsumoLigacaoAguaBean bean = new RelatorioMedicaoConsumoLigacaoAguaBean(mesAnoMedicao, dataLeituraInformada,
					leituraInformada, dataLeituraFaturada, leituraFaturada, anormalidadeInformada, anormalidadeFaturada,
					leituraAtual, mesAnoConsumo, consumoMedido, consumoFaturado, anormalidadeConsumo, diasConsumo, tipoConsumo);
			 
			retorno.add(bean);
						
		}
		
		//Histórico Consumo
		Iterator iterator2 = dadosRelatorio2.iterator();
		while (iterator2.hasNext()) {
			
			ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)iterator2.next();
			
			String mesAnoMedicao = null;
			String dataLeituraInformada = null;
			String leituraInformada = null;
			String dataLeituraFaturada = null;
			String leituraFaturada = null;
			String anormalidadeInformada = null;
			String anormalidadeFaturada = null;
			String leituraAtual = null;
			
			String  mesAnoConsumo = ""+imovelMicromedicao.getConsumoHistorico().getMesAno();
			
			String consumoMedido =  "";
			if(imovelMicromedicao.getMedicaoHistorico() != null){
				consumoMedido = ""+imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes();
			}
			
			String consumoFaturado = "";
			if (imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null){
				consumoFaturado = ""+ imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes();				
			}
			
			String anormalidadeConsumo = "" ;
			if (imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricao() != null){
				anormalidadeConsumo = "" + imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada();
			}
			
			String tipoConsumo = "";
			if(imovelMicromedicao.getConsumoHistorico().getConsumoTipo().getDescricao() != null){
				tipoConsumo = "" + imovelMicromedicao.getConsumoHistorico().getConsumoTipo().getDescricao();
			}
			
			String diasConsumo = "";
			if (imovelMicromedicao.getQtdDias() != null){
				diasConsumo = "" + imovelMicromedicao.getQtdDias();
			}
		
			RelatorioMedicaoConsumoLigacaoAguaBean bean = new RelatorioMedicaoConsumoLigacaoAguaBean(mesAnoMedicao, dataLeituraInformada,
					leituraInformada, dataLeituraFaturada, leituraFaturada, anormalidadeInformada, anormalidadeFaturada,
					leituraAtual, mesAnoConsumo, consumoMedido, consumoFaturado, anormalidadeConsumo, diasConsumo, tipoConsumo);
			 
			retorno.add(bean);
						
		}
		
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Collection colecaoMedicaoHistorico = (Collection)getParametro("colecaoMedicaoHistorico");
		Collection colecaoimoveisMicromedicao = (Collection)getParametro("colecaoimoveisMicromedicao");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", (String) getParametro("matricula"));
		parametros.put("sitLigacaoAgua", (String) getParametro("sitLigacaoAgua"));
		parametros.put("sitLigacaoEsgoto", (String) getParametro("sitLigacaoEsgoto"));
		parametros.put("endereco", (String) getParametro("endereco"));
		parametros.put("clienteUsuario", (String) getParametro("clienteUsuario"));
		
		//Collection dadosRelatorio = fachada.pesquisarGuiaDevolucaoRelatorio(ids);

		Collection<RelatorioMedicaoConsumoLigacaoAguaBean> colecaoBean = this
				.inicializarBeanRelatorio(colecaoMedicaoHistorico, colecaoimoveisMicromedicao);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MEDICAO_CONSUMO_LIGACAO_AGUA, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MEDICAO_CONSUMO_LIGACAO_AGUA,
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
		int retorno = 0;

		//retorno = ((Collection) getParametro("idsGuiaDevolucao")).size();

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMedicaoConsumoLigacaoAgua", this);
	}
}
