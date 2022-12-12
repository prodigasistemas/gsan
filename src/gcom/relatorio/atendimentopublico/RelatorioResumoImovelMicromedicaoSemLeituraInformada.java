package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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
 * @author Guilherme Aguiar
 * @date 26/11/2022
 * 
 */
public class RelatorioResumoImovelMicromedicaoSemLeituraInformada extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoImovelMicromedicaoSemLeituraInformada(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_IMOVEL_MICROMEDICAO_SEM_LEITURA_INFORMADA);
	}
	
	@Deprecated
	public RelatorioResumoImovelMicromedicaoSemLeituraInformada() {
		super(null, "");
	}

	private Collection<RelatorioResumoImovelMicromedicaoSemLeituraInformadaBean> inicializarBeanRelatorio(
			Collection dadosRelatorio) {

		Collection<RelatorioResumoImovelMicromedicaoSemLeituraInformadaBean> retorno = new ArrayList();
		
		//Histórico Medição e Consumo
		Iterator iterator = dadosRelatorio.iterator();
		while (iterator.hasNext()) {
			
			ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)iterator.next();
			
			String mesAnoMedicao = "";
			
			if (imovelMicromedicao.getMedicaoHistorico().getMesAno() != null) {
				mesAnoMedicao = ""+imovelMicromedicao.getMedicaoHistorico().getMesAno();
			} else {
				mesAnoMedicao = ""+imovelMicromedicao.getConsumoHistorico().getMesAno();
			}
			
			String dataLeituraFaturada = "" ;
			if (imovelMicromedicao.getMedicaoHistorico().getDataLeituraAtualFaturamento() != null){
				dataLeituraFaturada = Util.formatarData(imovelMicromedicao.getMedicaoHistorico().getDataLeituraAtualFaturamento());
			}
			
			String leituraFaturada = "";
			if (imovelMicromedicao.getMedicaoHistorico().getLeituraAtualFaturamento() != 0){
				leituraFaturada = "" + imovelMicromedicao.getMedicaoHistorico().getLeituraAtualFaturamento();
			}
			
			String consumo = "";
			if(imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null){
				consumo = "" + imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes();
			}
			
			String media = "";
			if(imovelMicromedicao.getConsumoHistorico().getConsumoMedio() != null){
				media= "" + imovelMicromedicao.getConsumoHistorico().getConsumoMedio();
			}
			
			String anormalidadeConsumo = "";
			if(imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade() != null && imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada() != null){
				anormalidadeConsumo = "" + imovelMicromedicao.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada();
			}
			
			String anormalidadeLeitura = "";
			if(imovelMicromedicao.getMedicaoHistorico().getLeituraAnormalidadeFaturamento() != null && imovelMicromedicao.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId() != null){
				anormalidadeLeitura = "" + imovelMicromedicao.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId();
			}
			
			String sitLeituraAtual = "";
			if(imovelMicromedicao.getMedicaoHistorico().getLeituraSituacaoAtual() != null && 
					imovelMicromedicao.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao() != null){
				sitLeituraAtual = "" + imovelMicromedicao.getMedicaoHistorico().getLeituraSituacaoAtual().getDescricao();
			}
			
			RelatorioResumoImovelMicromedicaoSemLeituraInformadaBean bean = new RelatorioResumoImovelMicromedicaoSemLeituraInformadaBean(mesAnoMedicao, dataLeituraFaturada, leituraFaturada, consumo, media, anormalidadeConsumo, anormalidadeLeitura, sitLeituraAtual);
			 
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
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String matricula = (String) getParametro("matricula");

		Collection colecaoImovelMicromedicao = 
			Fachada.getInstancia().carregarDadosConsumo(new Integer(matricula), true); 
//			(Collection)getParametro("colecaoImovelMicromedicao");
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
		parametros.put("dataInstalacao",(String) getParametro("dataInstalacao"));
		parametros.put("numeroRetirado",(String) getParametro("numeroRetirado"));
		parametros.put("dataRetirada",(String) getParametro("dataRetirada"));

		Collection<RelatorioResumoImovelMicromedicaoSemLeituraInformadaBean> colecaoBean = null;
		
		if (colecaoImovelMicromedicao != null && !colecaoImovelMicromedicao.isEmpty()) {
			colecaoBean = this
				.inicializarBeanRelatorio(colecaoImovelMicromedicao);
		} else {
			colecaoBean = new ArrayList<RelatorioResumoImovelMicromedicaoSemLeituraInformadaBean>();
		}

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_IMOVEL_MICROMEDICAO_SEM_LEITURA_INFORMADA, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_IMOVEL_MEDICAO,
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
		AgendadorTarefas.agendarTarefa("RelatorioResumoImovelMicromedicao", this);
	}
}
