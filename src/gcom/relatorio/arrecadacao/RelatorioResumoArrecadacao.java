package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.bean.ResumoArrecadacaoRelatorioHelper;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioResumoArrecadacao extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoArrecadacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_ARRECADACAO);
	}
	
	@Deprecated
	public RelatorioResumoArrecadacao() {
		super(null, "");
	}

	private Collection<RelatorioResumoArrecadacaoBean> inicializarBeanRelatorio(
			Collection listaCamposConsultaRelatorio) {

		Iterator iterator = listaCamposConsultaRelatorio.iterator();

		Collection<RelatorioResumoArrecadacaoBean> retorno = new ArrayList();

		while (iterator.hasNext()) {
			ResumoArrecadacaoRelatorioHelper resumoArrecadacaoRelatorioHelper = 
				(ResumoArrecadacaoRelatorioHelper) iterator.next();
			
			RelatorioResumoArrecadacaoBean relatorioResumoArrecadacaoBean = 
				new RelatorioResumoArrecadacaoBean(
						resumoArrecadacaoRelatorioHelper.getValorItemArrecadacao(),
						resumoArrecadacaoRelatorioHelper.getDescricaoTipoRecebimento(), 
						resumoArrecadacaoRelatorioHelper.getDescricaoTipoLancamento(),
						resumoArrecadacaoRelatorioHelper.getDescricaoItemLancamento(),
						resumoArrecadacaoRelatorioHelper.getDescricaoItemContabil(),
						resumoArrecadacaoRelatorioHelper.getIndicadorImpressao(),
						resumoArrecadacaoRelatorioHelper.getIndicadorTotal(),
						resumoArrecadacaoRelatorioHelper.getLancamentoTipo(), 
						resumoArrecadacaoRelatorioHelper.getLancamentoTipoSuperior(),
						false,
						resumoArrecadacaoRelatorioHelper.getDescricaoGerencia(), 
						resumoArrecadacaoRelatorioHelper.getGerencia(),
						resumoArrecadacaoRelatorioHelper.getDescricaoLocalidade(), 
						resumoArrecadacaoRelatorioHelper.getLocalidade(),
						resumoArrecadacaoRelatorioHelper.getDescricaoMunicipio(),
						resumoArrecadacaoRelatorioHelper.getMunicipio(),
						resumoArrecadacaoRelatorioHelper.getDescLancamentoTipoSuperior(),
						resumoArrecadacaoRelatorioHelper.getDescricaoUnidadeNegocio(),
						resumoArrecadacaoRelatorioHelper.getUnidadeNegocio(),
						resumoArrecadacaoRelatorioHelper.getCodigoCentroCusto() );

			retorno.add(relatorioResumoArrecadacaoBean);
			
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

		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");		
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		Integer codigoMunicipio = (Integer) getParametro("municipio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection dadosRelatorio = fachada.consultarResumoArrecadacaoRelatorio(opcaoTotalizacao, mesAno,
						codigoGerencia, codigoLocalidade, unidadeNegocio, codigoMunicipio);

		Collection<RelatorioResumoArrecadacaoBean> colecaoBean = this.inicializarBeanRelatorio(dadosRelatorio);

		Integer lancamentoTipoAnterior = null;
		Integer lancamentoTipoAnteriorSuperior = null;
		
		for (Iterator<RelatorioResumoArrecadacaoBean> iter = colecaoBean.iterator(); iter.hasNext();) {
			RelatorioResumoArrecadacaoBean bean = iter.next();

			// Se o tipo de lançamento for subordinado a outro tipo de
			// lançamento
			if (!bean.getLancamentoTipo().equals(bean.getLancamentoTipoSuperior())) {

				// Recua a descrição neste caso
				bean.setDescricaoTipoLancamento("    " 	+ bean.getDescricaoTipoLancamento());

				bean.setDescricaoItemLancamento("    "	+ bean.getDescricaoItemLancamento());

				if (bean.getDescricaoItemContabil() != null){
					bean.setDescricaoItemContabil("    " + bean.getDescricaoItemContabil());	
				}

				if (lancamentoTipoAnterior != null 
						&& !lancamentoTipoAnterior.equals(bean.getLancamentoTipoSuperior())
						&& !lancamentoTipoAnteriorSuperior.equals(bean.getLancamentoTipoSuperior())){
					String descricaoLancamentoTipoSuperior = fachada.obterDescricaoLancamentoTipo(bean.getLancamentoTipoSuperior());
					bean.setDescLancamentoTipoSuperior(descricaoLancamentoTipoSuperior);
				}
				
			}
			
			lancamentoTipoAnterior = bean.getLancamentoTipo();
			lancamentoTipoAnteriorSuperior = bean.getLancamentoTipoSuperior();
			
			// Se o indicador impressão não estiver setado então a linha de
			// detalhe não aparecerá no relatório
			if (bean.getIndicadorImpressao() == null || !bean.getIndicadorImpressao().toString().equals("1")) {
				bean.setDescricaoTipoLancamento("");
				continue;
			}
		}

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		String opcaoTotalizacaoDesc = "";
		if (opcaoTotalizacao.equalsIgnoreCase("estado")) {
			opcaoTotalizacaoDesc = "Estado";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			opcaoTotalizacaoDesc = "Estado por Gerência Regional";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoLocalidade")) {
			opcaoTotalizacaoDesc = "Estado por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio")) {
			opcaoTotalizacaoDesc = "Estado por Município";
		} else if (opcaoTotalizacao.equalsIgnoreCase("gerenciaRegional")) {
			opcaoTotalizacaoDesc = "Gerência Regional";
		} else if (opcaoTotalizacao
				.equalsIgnoreCase("gerenciaRegionalLocalidade")) {
			opcaoTotalizacaoDesc = "Gerência Regional por Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("localidade")) {
			opcaoTotalizacaoDesc = "Localidade";
		} else if (opcaoTotalizacao.equalsIgnoreCase("municipio")) {
			opcaoTotalizacaoDesc = "Município";
		} else if (opcaoTotalizacao.equals("estadoUnidadeNegocio")) {
			opcaoTotalizacaoDesc = "Estado por Unidade de Negócio";
		} else if (opcaoTotalizacao.equals("unidadeNegocio")) {
			opcaoTotalizacaoDesc = "Unidade de Negócio";
		}

		parametros.put("opcaoTotalizacaoDesc", opcaoTotalizacaoDesc);

		String mesAnoString = "" + mesAno;
		if (mesAnoString.length() == 5) {
			mesAnoString = "0" + mesAnoString;
		}
		mesAnoString = mesAnoString.substring(0, 2) + "/"
				+ mesAnoString.substring(2, 6);

		parametros.put("mesAnoArrecadacao", mesAnoString);

		parametros.put("tipoFormatoRelatorio", "");

		if (opcaoTotalizacao.equalsIgnoreCase("unidadeNegocio") || opcaoTotalizacao.equalsIgnoreCase("estadoUnidadeNegocio")) {
			parametros.put("agrupaPorUnidadeNegocio", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoGerencia")) {
			parametros.put("agrupaPorGerencia", "1");
		}else if (opcaoTotalizacao.equalsIgnoreCase("estadoMunicipio") || opcaoTotalizacao.equalsIgnoreCase("municipio")) {
			parametros.put("agrupaPorMunicipio", "1");
		}else {
			parametros.put("agrupaPorGerencia", "0");
		}
		
		
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("tipoFormatoRelatorio", "R0345");

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_ARRECADACAO, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_ARRECADACAO,
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
		Fachada fachada = Fachada.getInstancia();
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer idLocalidade = (Integer) getParametro("localidade");
		Integer idMunicipio = (Integer) getParametro("municipio");
		Integer idGerencia = (Integer) getParametro("gerenciaRegional");
		
		Integer totalRegistrosRel = fachada.consultarQtdeRegistrosResumoArrecadacaoRelatorio(
						opcaoTotalizacao, mesAno, idGerencia, idLocalidade,idMunicipio);
		return totalRegistrosRel.intValue();
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoArrecadacao", this);
	}
}
