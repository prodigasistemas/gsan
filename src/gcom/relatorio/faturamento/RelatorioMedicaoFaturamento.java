package gcom.relatorio.faturamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class RelatorioMedicaoFaturamento extends TarefaRelatorio {
	


	public RelatorioMedicaoFaturamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MEDICAO_FATURAMENTO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		Collection colecaoMedicaoFaturamento = (Collection) getParametro("colecaoMedicaoFaturamento");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		parametros.put("nomeEmpresa",nomeEmpresa);
		parametros.put("tipoFormatoRelatorio", "R0003-C");
		
		Collection dadosRelatorio = colecaoMedicaoFaturamento;

		Collection<RelatorioMedicaoFaturamentoBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MEDICAO_FATURAMENTO, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_MEDICAO_FATURAMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	
	}

	private Collection<RelatorioMedicaoFaturamentoBean> inicializarBeanRelatorio(
			Collection colecaoMedicaoFaturamento) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioMedicaoFaturamentoBean> retorno = new ArrayList<RelatorioMedicaoFaturamentoBean>();

		String anoMesReferencia;
		String unidadeDeNegocio;
		String grupo;
		String empresa;
		Integer qtdContasLidasEImpressas;
		Integer qtdContasLidas;
		Integer qtdContasImpressas;

		Iterator iter = colecaoMedicaoFaturamento.iterator();
		
		while (iter.hasNext()) {
			
			RelatorioMedicaoFaturamentoHelper rel = (RelatorioMedicaoFaturamentoHelper) iter.next();

			unidadeDeNegocio = rel.getUnidadeDeNegocio();
			grupo = rel.getGrupo();
			empresa = rel.getEmpresa();
			qtdContasLidasEImpressas = rel.getQtdContasLidasEImpressas();
			qtdContasLidas = rel.getQtdContasSoLidas();
			qtdContasImpressas = rel.getQtdContasSoImpressas();
			
			RelatorioMedicaoFaturamentoBean relatorioMedicaoFaturamentoBean = new RelatorioMedicaoFaturamentoBean();
			
			relatorioMedicaoFaturamentoBean.setAnoMesReferencia(rel.getAnoMesReferencia());
			relatorioMedicaoFaturamentoBean.setUnidadeDeNegocio(rel.getUnidadeDeNegocio());
			relatorioMedicaoFaturamentoBean.setGrupo(rel.getGrupo());
			relatorioMedicaoFaturamentoBean.setEmpresa(rel.getEmpresa());
			relatorioMedicaoFaturamentoBean.setQtdContasLidasEImpressas(
					rel.getQtdContasLidasEImpressas() != null ? new Integer(rel.getQtdContasLidasEImpressas()) : new Integer(0));
			relatorioMedicaoFaturamentoBean.setQtdContasSoImpressas(
					rel.getQtdContasSoImpressas() != null ? new Integer(rel.getQtdContasSoImpressas()) : new Integer(0));
			relatorioMedicaoFaturamentoBean.setQtdContasSoLidas(
					rel.getQtdContasSoLidas() != null ? new Integer(rel.getQtdContasSoLidas()) : new Integer(0));
				
			retorno.add(relatorioMedicaoFaturamentoBean);
			
		}

		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMedicaoFaturamento", this);
	}



}
