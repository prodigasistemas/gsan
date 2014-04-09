package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.bean.RelatorioAnaliseConsumoHelper;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
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
import java.util.Map;

/**
 * 
 * @author Vivianne Sousa
 * @date 06/11/2007
 */

public class RelatorioAnaliseConsumo extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioAnaliseConsumo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISE_CONSUMO);
	}
	
	@Deprecated
	public RelatorioAnaliseConsumo() {
		super(null, "");
	}

	
	private Collection<RelatorioAnaliseConsumoBean> inicializarBeanRelatorio(
			Collection colecaoAnaliseConsumoHelper, String mesAnoPesquisa,
			boolean verificaLeituraAtual) {
		
		Fachada fachada = Fachada.getInstancia();
		Collection<RelatorioAnaliseConsumoBean> retorno = new ArrayList<RelatorioAnaliseConsumoBean>();
		
		if (colecaoAnaliseConsumoHelper != null && !colecaoAnaliseConsumoHelper.isEmpty()) {
		
			Iterator iter = colecaoAnaliseConsumoHelper.iterator();
			while (iter.hasNext()) {
				
				RelatorioAnaliseConsumoHelper relatorioAnaliseConsumoHelper = (RelatorioAnaliseConsumoHelper) iter.next();
				
				try {
					Collection colecaoObjeto = fachada.pesquisarLeiturasImovel(relatorioAnaliseConsumoHelper.getMatriculaImovel(),
							Util.formatarMesAnoParaAnoMesSemBarra(mesAnoPesquisa));
					if(!colecaoObjeto.isEmpty()){
						Object[] objeto = (Object[]) colecaoObjeto.iterator().next();
						String leitura = "";
						
						//******************************************************************************
						// autor: Ivan Sérgio
						// data: 24/07/2008
						// solicitante: Leonardo Vieira
						// Caso a Empresa selecionada NAO seja COMPESA ou CAER nao informar a Leitura Atual;
						// Exibir a descricao da Leitura Anormalidade Informada;
						//******************************************************************************
						if (verificaLeituraAtual) {
							// leitura atual informada							
							if(objeto[0] != null){
								leitura = objeto[0].toString();
							}
							
							// Leitura Anormalidade Informada
							String anormalidade = "";
							if(objeto[3] != null){
								anormalidade = objeto[3].toString();
							}
							
							relatorioAnaliseConsumoHelper.setLeituraAtual(leitura);
							relatorioAnaliseConsumoHelper.setDescricaoLeituraAnormalidadeInformada(anormalidade);
							relatorioAnaliseConsumoHelper.setIndicadorExibirLeituraAtual("1");
						}else {
							// leitura anterior faturada
							if(objeto[1] != null){
								leitura = objeto[1].toString();
							}
							
							// leitura atual informada
							String leituraAtual = "";
							if(objeto[0] != null){
								leituraAtual = objeto[0].toString();
								leitura = leitura + "/" + leituraAtual;
							}
							
							if(objeto[0] != null || objeto[1] != null){
								relatorioAnaliseConsumoHelper.setLeituraAtual(leitura);
							}
							
							// Leitura Anormalidade Informada
							String anormalidade = "";
							if(objeto[3] != null){
								anormalidade = objeto[3].toString();								
							}
							
							relatorioAnaliseConsumoHelper.setLeituraAtual(leitura);
							relatorioAnaliseConsumoHelper.setDescricaoLeituraAnormalidadeInformada(anormalidade);
							relatorioAnaliseConsumoHelper.setIndicadorExibirLeituraAtual("2");
						}	
						//******************************************************************************
						
						//numero hidrometro
						String hidrometro = "";
						if(objeto[2] != null){
							hidrometro = objeto[2]+ "";
							relatorioAnaliseConsumoHelper.setHidrometro(hidrometro);
						}
						
						if (relatorioAnaliseConsumoHelper.getSeqRota() == null) {
							relatorioAnaliseConsumoHelper.setSeqRota("");
						}
					
						//Alterado por Yara T. Souza - 15/09/2008 					
						RelatorioAnaliseConsumoBean relatorioAnaliseConsumoBean = new RelatorioAnaliseConsumoBean(relatorioAnaliseConsumoHelper);
						retorno.add(relatorioAnaliseConsumoBean);
						
					}
					
				} catch (ControladorException e) {
					e.printStackTrace();
				}
			}
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String mesAnoPesquisa = (String) getParametro("mesAnoPesquisa");
		String mesAnoArrecadacao = (String) getParametro("mesAnoArrecadacao");
		String grupo = (String) getParametro("grupo");
		String empresa = (String) getParametro("empresa");
		String matriculaImovel = (String) getParametro("matImovel");
		String matriculaImovelCondominio = (String) getParametro("matImovelCondominio");
		String localidadeCodigo = (String) getParametro("localidadeCodigo");
		String localidadeDescricao = (String) getParametro("localidadeDescricao");
		String setorComercial = (String) getParametro("setorComercial");
		String quadraInicial = (String) getParametro("quadraInicial");
		String quadraFinal = (String) getParametro("quadraFinal");
		String rota = (String) getParametro("rota");
		String idUsuario = (String) getParametro("loginUsuario");
		String nomeUsuario = (String) getParametro("nomeUsuario");
		String indicadorImovelCondominio = (String) getParametro("indicadorImovelCondominio");
		String indicadorDebitoAutomatico = (String) getParametro("indicadorDebitoAutomatico");
		String indicadorAnalisado = (String) getParametro("indicadorAnalisado");
		String imovelPerfil = (String) getParametro("imovelPerfil");
		String categoriaImovel = (String) getParametro("categoriaImovel");
		String quantidadeEconomias = (String) getParametro("quantidadeEconomias");
		String medicaoTipo = (String) getParametro("medicaoTipo");
		String ligacaoTipo = (String) getParametro("ligacaoTipo");
		String leituraAnormalidadeInformada = (String) getParametro("leituraAnormalidadeInformada");
		String leituraAnormalidadeFaturada = (String) getParametro("leituraAnormalidadeFaturada");
		String anormalidadeConsumo = (String) getParametro("anormalidadeConsumo");
		String leituraSituacao = (String) getParametro("leituraSituacao");
		String consumoFaturadoInicial = (String) getParametro("consumoFaturadoInicial");
		String consumoFaturadoFinal = (String) getParametro("consumoFaturadoFinal");
		String consumoMedidoInicial = (String) getParametro("consumoMedidoInicial");
		String consumoMedidoFinal = (String) getParametro("consumoMedidoFinal");
		String consumoMedioInicial = (String) getParametro("consumoMedioInicial");
		String consumoMedioFinal = (String) getParametro("consumoMedioFinal");
		
		
		Collection colecaoImoveisGerarRelatorio = (Collection) getParametro("colecaoImoveisGerarRelatorio");
		FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper = (FiltrarAnaliseExcecoesLeiturasHelper) getParametro("filtrarAnaliseExcecoesLeiturasHelper");
		
		Collection colecaoAnaliseConsumoHelper = new ArrayList();
		
		if (colecaoImoveisGerarRelatorio != null && !colecaoImoveisGerarRelatorio.isEmpty()) {
			colecaoAnaliseConsumoHelper = fachada.pesquisarDadosImovel(colecaoImoveisGerarRelatorio);
		} else {
			colecaoAnaliseConsumoHelper = fachada.pesquisarDadosImovel(
					filtrarAnaliseExcecoesLeiturasHelper, Util
							.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
		}
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		
		parametros.put("mesAnoFaturamento", mesAnoPesquisa);
		parametros.put("mesAnoArrecadacao", mesAnoArrecadacao);
		parametros.put("grupo", grupo);
		parametros.put("empresa",empresa);
		parametros.put("matImovel",matriculaImovel);
		parametros.put("matImovelCondominio",matriculaImovelCondominio);
		parametros.put("localidadeCodigo",localidadeCodigo);
		parametros.put("localidadeDescricao",localidadeDescricao);
		parametros.put("setorComercial",setorComercial);
		parametros.put("quadraInicial",quadraInicial);
		parametros.put("quadraFinal",quadraFinal);
		parametros.put("rota",rota);
		parametros.put("loginUsuario",idUsuario);
		parametros.put("nomeUsuario",nomeUsuario);
		parametros.put("indicadorImovelCondominio",indicadorImovelCondominio);
		parametros.put("indicadorDebitoAutomatico",indicadorDebitoAutomatico);
		parametros.put("indicadorAnalisado",indicadorAnalisado);
		parametros.put("imovelPerfil",imovelPerfil);
		parametros.put("categoriaImovel",categoriaImovel);
		parametros.put("quantidadeEconomias",quantidadeEconomias);
		parametros.put("medicaoTipo",medicaoTipo);
		parametros.put("ligacaoTipo",ligacaoTipo);
		parametros.put("leituraAnormalidadeInformada",leituraAnormalidadeInformada);
		parametros.put("leituraAnormalidadeFaturada",leituraAnormalidadeFaturada);
		parametros.put("anormalidadeConsumo",anormalidadeConsumo);
		parametros.put("leituraSituacao",leituraSituacao);
		parametros.put("consumoFaturadoInicial",consumoFaturadoInicial);
		parametros.put("consumoFaturadoFinal",consumoFaturadoFinal);
		parametros.put("consumoMedidoInicial",consumoMedidoInicial);
		parametros.put("consumoMedidoFinal",consumoMedidoFinal);
		parametros.put("consumoMedioInicial",consumoMedioInicial);
		parametros.put("consumoMedioFinal",consumoMedioFinal);
		

		Collection dadosRelatorio = colecaoAnaliseConsumoHelper;
		
		//******************************************************************************
		// autor: Ivan Sérgio
		// data: 24/07/2008
		// solicitante: Leonardo Vieira
		// Caso a Empresa selecionada NAO seja COMPESA ou CAER nao informar Leitura Atual
		//******************************************************************************
		boolean verificaLeituraAtual = false;
		//boolean verificaAnormalidade = true;
		if (sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_COMPESA)) {
			verificaLeituraAtual = true;
		}
		
//		if(sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_CAER)){
//			verificaAnormalidade = true;
//		}
		
		//******************************************************************************

		
		Collection<RelatorioAnaliseConsumoBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio, 
					mesAnoPesquisa, 
					verificaLeituraAtual);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ANALISE_CONSUMO, parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ANALISE_CONSUMO, idFuncionalidadeIniciada);
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
		Integer qtde = 0;
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoImoveisGerarRelatorio = (Collection) getParametro("colecaoImoveisGerarRelatorio");
		
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) getParametro("filtroMedicaoHistoricoSql");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		String mesAnoPesquisa = (String) getParametro("mesAnoPesquisa");
		String valorAguaEsgotoInicial = (String) getParametro("valorAguaEsgotoInicial");
		String valorAguaEsgotoFinal = (String) getParametro("valorAguaEsgotoFinal");
		
		if (colecaoImoveisGerarRelatorio != null
				&& !colecaoImoveisGerarRelatorio.isEmpty()) {
			qtde = colecaoImoveisGerarRelatorio.size();
		} else {
			qtde = fachada.filtrarExcecoesLeiturasConsumosCount(
					faturamentoGrupo, filtroMedicaoHistoricoSql,
					mesAnoPesquisa, valorAguaEsgotoInicial, valorAguaEsgotoFinal);
		}
		
		return qtde;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnaliseConsumo", this);
	}
	
}
