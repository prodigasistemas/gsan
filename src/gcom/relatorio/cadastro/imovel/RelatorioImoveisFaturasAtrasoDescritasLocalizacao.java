package gcom.relatorio.cadastro.imovel;

import gcom.batch.Relatorio;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de imoveis com faturas em atraso
 * 
 * @author Bruno Barros
 * @created 10/12/2007
 */
public class RelatorioImoveisFaturasAtrasoDescritasLocalizacao extends TarefaRelatorio {
	
	public RelatorioImoveisFaturasAtrasoDescritasLocalizacao(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisFaturasAtrasoDescritasLocalizacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO);
	}

	@Deprecated
	public RelatorioImoveisFaturasAtrasoDescritasLocalizacao() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			(FiltrarRelatorioImoveisFaturasAtrasoHelper) getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper");

		RelatorioDataSource ds = new RelatorioDataSource( executarConsultaECriarBeans(filtro) );

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO,
				criarParametros(), ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO,
					this.getIdFuncionalidadeIniciada());
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 15/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametros() {
		Map<String,Object> parametros = new HashMap<String,Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		parametros.put("gerRegionalFiltro", getParametro("gerRegionalFiltro"));
		parametros.put("uniNegocioFiltro",getParametro("uniNegocioFiltro"));
		parametros.put("qtdFaturasFiltro", getParametro("qtdFaturasFiltro"));
		parametros.put("localidadeFiltro", getParametro("localidadeFiltro"));
		parametros.put("setorFiltro", getParametro("setorFiltro"));
		parametros.put("rotaFiltro", getParametro("rotaFiltro"));
		parametros.put("refFaturasFiltro", getParametro("refFaturasFiltro"));
		parametros.put("seqRotaFiltro", getParametro("seqRotaFiltro"));
		parametros.put("valorFaturasFiltro",getParametro("valorFaturasFiltro"));
		parametros.put("esfPoderFiltro", getParametro("esfPoderFiltro"));
		parametros.put("LigAguaFiltro", getParametro("LigAguaFiltro"));
		parametros.put("categoriaFiltro", getParametro("categoriaFiltro"));
		parametros.put("hidrometro", getParametro("hidrometro"));
		
		return parametros;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 15/09/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioImoveisFaturasAtrasoBean> executarConsultaECriarBeans(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) {
		
		List<RelatorioImoveisFaturasAtrasoBean> relatorioBeans = new ArrayList<RelatorioImoveisFaturasAtrasoBean>();
		RelatorioImoveisFaturasAtrasoBean relatorioImoveisFaturasAtrasoBean = null;

		Collection<RelatorioImoveisFaturasAtrasoHelper> colecao =  
			Fachada.getInstancia().pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(filtro);

		if ( !Util.isVazioOrNulo(colecao)) {

			Iterator<RelatorioImoveisFaturasAtrasoHelper> colecaoIterator = colecao.iterator();
			
			RelatorioImoveisFaturasAtrasoHelper helperAnterior = null;
			List<RelatorioImoveisFaturasAtrasoContasBean> colecaoDadosSubrelatorio = new ArrayList<RelatorioImoveisFaturasAtrasoContasBean>();
			
			BigDecimal totalImovel = BigDecimal.ZERO;
			int qtdFaturasAtraso = 0;
			
			while (colecaoIterator.hasNext()) {

				RelatorioImoveisFaturasAtrasoHelper helper = colecaoIterator.next();
				
				if (helperAnterior == null) {
					helperAnterior = helper;
				}
				
				if (helperAnterior.getMatriculaImovel().equals(helper.getMatriculaImovel())) {
					
					RelatorioImoveisFaturasAtrasoContasBean relatorioImoveisFaturasAtrasoContasBean = new RelatorioImoveisFaturasAtrasoContasBean(Util.formatarAnoMesParaMesAno(helper.getReferenciaFaturasAtrasoInicial()), helper.getValorFaturasAtrasoSemEncargos(), helper.getVencimento());
					colecaoDadosSubrelatorio.add(relatorioImoveisFaturasAtrasoContasBean);

					qtdFaturasAtraso++;
					totalImovel = totalImovel.add(helper.getValorFaturasAtrasoSemEncargos());
				} else {
					
					helperAnterior.setQuantidadeFaturasAtraso(qtdFaturasAtraso);
					relatorioImoveisFaturasAtrasoBean = 
						new RelatorioImoveisFaturasAtrasoBean(helperAnterior, colecaoDadosSubrelatorio);
					relatorioImoveisFaturasAtrasoBean.setValorTotalFaturaAtrasoSemEncargo(totalImovel);

					if(isQtdContasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)
							&& isValorFaturasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)){

						relatorioBeans.add(relatorioImoveisFaturasAtrasoBean);
					}

					colecaoDadosSubrelatorio.clear();
					
					RelatorioImoveisFaturasAtrasoContasBean relatorioImoveisFaturasAtrasoContasBean = new RelatorioImoveisFaturasAtrasoContasBean(Util.formatarAnoMesParaMesAno(helper.getReferenciaFaturasAtrasoInicial()), helper.getValorFaturasAtrasoSemEncargos(), helper.getVencimento());
					
					colecaoDadosSubrelatorio.add(relatorioImoveisFaturasAtrasoContasBean);
					
					totalImovel = BigDecimal.ZERO;
					
					totalImovel = totalImovel.add(helper.getValorFaturasAtrasoSemEncargos());
					qtdFaturasAtraso = 1;
				
				}
				
				if(!colecaoIterator.hasNext()){

					relatorioImoveisFaturasAtrasoBean = 
						new RelatorioImoveisFaturasAtrasoBean(helperAnterior, colecaoDadosSubrelatorio);
					relatorioImoveisFaturasAtrasoBean.setValorTotalFaturaAtrasoSemEncargo(totalImovel);
					
					if(isQtdContasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)
							&& isValorFaturasDentroIntervaloInformado(filtro, relatorioImoveisFaturasAtrasoBean)){

						relatorioBeans.add(relatorioImoveisFaturasAtrasoBean);
					}
					
					colecaoDadosSubrelatorio.clear();
					
					RelatorioImoveisFaturasAtrasoContasBean relatorioImoveisFaturasAtrasoContasBean = new RelatorioImoveisFaturasAtrasoContasBean(Util.formatarAnoMesParaMesAno(helper.getReferenciaFaturasAtrasoInicial()), helper.getValorFaturasAtrasoSemEncargos(), helper.getVencimento());
					
					colecaoDadosSubrelatorio.add(relatorioImoveisFaturasAtrasoContasBean);
					
					totalImovel = BigDecimal.ZERO;
					totalImovel = totalImovel.add(helper.getValorFaturasAtrasoSemEncargos());
					qtdFaturasAtraso = 0;
				}
				
				helperAnterior = helper;
			}
		}
		
		return relatorioBeans;
	}

	/**
	 *[CRC] - 1672 - Melhorar performance dos relatórios de Imóveis com Fatura em Atraso.<br/>
	 *
	 *Esse método verifica se um RelatorioBean possui um numero de contas
	 *dentro do intervalo informado pelo usuário. Essa verificação tornou-se necessária
	 *neste momento da geração do relatório pois aqui sabemos exatamente quantas contas
	 *em atraso o imóvel tem. Foi preferido fazer isso via código Java ao invés de sql/hql
	 *pois no caso desse relatorio a query não agrupa nenhum valor de conta,
	 *assim ao colocarmos isso num having count o mesmo não traria o resultado correto.
	 *
	 *@since 10/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isQtdContasDentroIntervaloInformado(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro,
			RelatorioImoveisFaturasAtrasoBean relatorioAux){
		
		if(filtro.getQuantidadeFaturasAtrasoInicial()==null || filtro.getQuantidadeFaturasAtrasoFinal() == null){
			return true;
		}
		
		return (relatorioAux.getArrayRelatorioImoveisFaturasAtrasoContasBean().size() >= filtro.getQuantidadeFaturasAtrasoInicial() 
					&& relatorioAux.getArrayRelatorioImoveisFaturasAtrasoContasBean().size() <= filtro.getQuantidadeFaturasAtrasoFinal());
	}

	/**
	 * Idem isQtdContasDentroIntervaloInformado(filtro,relatorioAux)
	 *
	 *@since 10/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isValorFaturasDentroIntervaloInformado(FiltrarRelatorioImoveisFaturasAtrasoHelper filtro,
			RelatorioImoveisFaturasAtrasoBean relatorioAux){
		
		if(filtro.getValorFaturasAtrasoInicial() == null || filtro.getValorFaturasAtrasoFinal()== null){
			return true;
		}
		
		return (relatorioAux.getValorTotalFaturaAtrasoSemEncargo().doubleValue() >= filtro.getValorFaturasAtrasoInicial() 
					&& relatorioAux.getValorTotalFaturaAtrasoSemEncargo().doubleValue() <= filtro.getValorFaturasAtrasoFinal());
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
				(FiltrarRelatorioImoveisFaturasAtrasoHelper) 
					getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper"));
		
		if(retorno == 0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Relatório");
		}

		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisFaturasAtraso", this);

	}

}
