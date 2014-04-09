package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.medicao.MedicaoHistorico;
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

/**
 * @author Arthur Carvalho
 * @date 09/10/2009
 * 
 */
public class RelatorioHistoricoMedicaoPoco extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioHistoricoMedicaoPoco(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HISTORICO_MEDICAO_POCO);
	}
	
	@Deprecated
	public RelatorioHistoricoMedicaoPoco() {
		super(null, "");
	}

	private Collection<RelatorioHistoricoMedicaoPocoBean> inicializarBeanRelatorio(Collection dadosRelatorio2) {

		Collection<RelatorioHistoricoMedicaoPocoBean> retorno = new ArrayList();
		
		if (dadosRelatorio2 != null && !dadosRelatorio2.isEmpty()){
			//Histórico de Medição do Poço
			Iterator iterator = dadosRelatorio2.iterator();
			while (iterator.hasNext()) {
				
				RelatorioHistoricoMedicaoPocoHelper helper = (RelatorioHistoricoMedicaoPocoHelper)iterator.next();
				
				
				String mesAnoMedicao = "" + helper.getMedicaoHistorico().getMesAno();
				
				String dataLeituraInformada =  "";
				if(helper.getMedicaoHistorico().getDataLeituraAtualInformada() != null){
					dataLeituraInformada = Util.formatarData(
							helper.getMedicaoHistorico().getDataLeituraAtualInformada());
				}
				
				String leituraInformada = "";
				if (helper.getMedicaoHistorico().getLeituraAtualInformada() != null){
					leituraInformada = ""+ helper.getMedicaoHistorico().getLeituraAtualInformada();
				}
				
				String dataLeituraFaturada = "" ;
				if (helper.getMedicaoHistorico().getDataLeituraAtualFaturamento() != null){
					dataLeituraFaturada = Util.formatarData(
							helper.getMedicaoHistorico().getDataLeituraAtualFaturamento());
				}
				
				String leituraFaturada = "";
				if (helper.getMedicaoHistorico().getLeituraAtualFaturamento() != 0){
					leituraFaturada = "" + helper.getMedicaoHistorico().getLeituraAtualFaturamento();
				}
				
				String consumo = "";
				if ( helper.getConsumoHistorico() != null && helper.getConsumoHistorico().getConsumoMedio() != null ) {
					consumo = helper.getConsumoHistorico().getConsumoMedio().toString();
				}
				
				String media = "";
				if(helper.getMedicaoHistorico().getConsumoMedioHidrometro() != null){
					media= "" + helper.getMedicaoHistorico().getConsumoMedioHidrometro();
				}
				
				String anormalidadeConsumo = "";
				if ( helper.getConsumoAnormalidade() != null && 
						helper.getConsumoAnormalidade().getDescricaoAbreviada() != null ) {
					anormalidadeConsumo = helper.getConsumoAnormalidade().getDescricaoAbreviada();
					
				}
				
				String anormalidadeLeitura = "";
				if(helper.getMedicaoHistorico().getLeituraAnormalidadeFaturamento() != null 
						&&helper.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId() != null){
					anormalidadeLeitura = "" + helper.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId();
				}
				
				String sitLeituraAtual = "";
				
				if(helper.getMedicaoHistorico().getLeituraSituacaoAtual() != null 
						&& helper.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao() != null){
					sitLeituraAtual = "" + helper.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao();
				}
				
				RelatorioHistoricoMedicaoPocoBean bean = new RelatorioHistoricoMedicaoPocoBean(mesAnoMedicao, dataLeituraInformada,
						leituraInformada, dataLeituraFaturada, leituraFaturada, consumo, media, anormalidadeConsumo, anormalidadeLeitura, sitLeituraAtual);
				 
				retorno.add(bean);

							
			}	
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
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String matricula = (String) getParametro("matricula");
		
		Collection<MedicaoHistorico> medicoesHistoricoPoco = new ArrayList<MedicaoHistorico>();
		
		//imoveisMicromedicaoEsgoto  = (Collection) getParametro("imoveisMicromedicaoEsgoto");
		if ( matricula != null && !matricula.equals("") ) {
			medicoesHistoricoPoco   = Fachada.getInstancia().
				carregarDadosMedicaoConsumoHistoricoLigacaoEsgoto(new Integer(matricula));
		}
		
		if ( medicoesHistoricoPoco == null || medicoesHistoricoPoco.isEmpty() ) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String matriculaFormatada = (matricula).substring(0, (matricula).length() - 1) + "." + (matricula).substring((matricula).length() - 1);
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", matriculaFormatada);
		parametros.put("inscricao", (String) getParametro("inscricao"));
		parametros.put("sitLigacaoAgua", (String) getParametro("sitLigacaoAgua"));
		parametros.put("sitLigacaoEsgoto", (String) getParametro("sitLigacaoEsgoto"));
		parametros.put("endereco", (String) getParametro("endereco"));
		parametros.put("clienteUsuario", (String) getParametro("clienteUsuario"));
		parametros.put("numeroHidrometro",(String) getParametro("numeroHidrometro"));

		Collection<RelatorioHistoricoMedicaoPocoBean> colecaoBean = null;
		
		if ( medicoesHistoricoPoco != null && !medicoesHistoricoPoco.isEmpty() ) {
			colecaoBean = this
				.inicializarBeanRelatorio(medicoesHistoricoPoco);
		} else {
			colecaoBean = new ArrayList<RelatorioHistoricoMedicaoPocoBean>();
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_HISTORICO_MEDICAO_POCO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_HISTORICO_MEDICAO_POCO,
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

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHistoricoMedicaoPoco", this);
	}
}
