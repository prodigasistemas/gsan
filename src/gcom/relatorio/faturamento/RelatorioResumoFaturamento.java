package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.ResumoFaturamentoRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioResumoFaturamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoFaturamento (Usuario usuario) {
		super(usuario,ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO);
	}


	private Collection<RelatorioResumoFaturamentoBean> inicializarBeanRelatorio(Collection listaCamposConsultaRelatorio) {

		Iterator iterator = listaCamposConsultaRelatorio.iterator();

		Collection<RelatorioResumoFaturamentoBean> retorno = new ArrayList();

		while (iterator.hasNext()) {
			ResumoFaturamentoRelatorioHelper resumoFaturamentoRelatorioHelper = (ResumoFaturamentoRelatorioHelper) iterator.next();
			
			RelatorioResumoFaturamentoBean relatorioResumoFaturamentoBean = 
				new RelatorioResumoFaturamentoBean(
						resumoFaturamentoRelatorioHelper.getValorItemFaturamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoTipoLancamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoItemLancamento(),
						resumoFaturamentoRelatorioHelper.getDescricaoItemContabil(),
						resumoFaturamentoRelatorioHelper.getIndicadorImpressao(),
						resumoFaturamentoRelatorioHelper.getIndicadorTotal(),
						resumoFaturamentoRelatorioHelper.getLancamentoTipo(),
						resumoFaturamentoRelatorioHelper.getLancamentoTipoSuperior(),
						false,
						resumoFaturamentoRelatorioHelper.getDescricaoGerencia(),
						resumoFaturamentoRelatorioHelper.getGerencia(),
						resumoFaturamentoRelatorioHelper.getDescricaoLocalidade(),
						resumoFaturamentoRelatorioHelper.getLocalidade(),
						resumoFaturamentoRelatorioHelper.getDescricaoMunicipio(),
						resumoFaturamentoRelatorioHelper.getMunicipio(),
						resumoFaturamentoRelatorioHelper.getDescLancamentoTipoSuperior(),
						resumoFaturamentoRelatorioHelper.getDescricaoUnidadeNegocio(),
						resumoFaturamentoRelatorioHelper.getUnidadeNegocio(),
						resumoFaturamentoRelatorioHelper.getCodigoCentroCusto() );

			retorno.add(relatorioResumoFaturamentoBean);
		}		
		return retorno;
	}

	
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String opcaoTotalizacao = (String) getParametro("opcaoTotalizacao");
		int mesAno = (Integer) getParametro("mesAnoInteger");
		Integer codigoLocalidade = (Integer) getParametro("localidade");
		Integer codigoMunicipio = (Integer) getParametro("municipio");
		Integer codigoGerencia = (Integer) getParametro("gerenciaRegional");
		Integer unidadeNegocio = (Integer) getParametro("unidadeNegocio");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String opcaoRelatorio = (String) getParametro("opcaoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map parametros = new HashMap();

		Fachada fachada = Fachada.getInstancia();

		Collection dadosRelatorio = fachada.consultarResumoFaturamentoRelatorio(opcaoTotalizacao, mesAno,
						codigoGerencia, codigoLocalidade, codigoMunicipio, unidadeNegocio,opcaoRelatorio);

		Collection<RelatorioResumoFaturamentoBean> colecaoBean = this.inicializarBeanRelatorio(dadosRelatorio);

		Integer lancamentoTipoAnterior = null;
		Integer lancamentoTipoAnteriorSuperior = null;
		
		for (Iterator<RelatorioResumoFaturamentoBean> iter = colecaoBean.iterator(); iter.hasNext();) {
			RelatorioResumoFaturamentoBean bean = iter.next();

			// Se o tipo de lançamento for subordinado a outro tipo de
			// lançamento
			if (!bean.getLancamentoTipo().equals(bean.getLancamentoTipoSuperior())) {

				// Recua a descrição neste caso
				bean.setDescricaoTipoLancamento("    "	+ bean.getDescricaoTipoLancamento());

				bean.setDescricaoItemLancamento("    " 	+ bean.getDescricaoItemLancamento());

				if (bean.getDescricaoItemContabil() != null){
					bean.setDescricaoItemContabil("    "  + bean.getDescricaoItemContabil());	
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
				//iter.remove();
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
		mesAnoString = mesAnoString.substring(0, 2) + "/" + mesAnoString.substring(2, 6);

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
		
		if (opcaoRelatorio != null && opcaoRelatorio.equals("2")) {
			parametros.put("opcaoRelatorio", "Opção de Relatório:Resumido");
		} else {
			parametros.put("opcaoRelatorio", "");
		}
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("tipoFormatoRelatorio", "R0173");

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO, parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RESUMO_FATURAMENTO,idFuncionalidadeIniciada);
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
		Integer idGerencia = (Integer) getParametro("gerenciaRegional");
		Integer idMunicipio = (Integer) getParametro("municipio");
		
		Integer totalRegistrosRel;
		totalRegistrosRel = fachada.consultarQtdeRegistrosResumoFaturamentoRelatorio(mesAno, idLocalidade, 
					idMunicipio, idGerencia, opcaoTotalizacao);
		return totalRegistrosRel.intValue();
	}


	public void agendarTarefaBatch() {
	}
}
