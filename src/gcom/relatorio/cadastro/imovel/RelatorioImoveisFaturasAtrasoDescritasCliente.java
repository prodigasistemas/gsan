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
 * Responsável pela lógica de Relatório de Imóveis com Fatura em Atraso
 * Descritas, com critétio de filtro por Cliente.
 *
 * @author Marlon Patrick
 * @since 02/09/2009
 */
public class RelatorioImoveisFaturasAtrasoDescritasCliente extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisFaturasAtrasoDescritasCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_CLIENTE);
	}

	@Deprecated
	public RelatorioImoveisFaturasAtrasoDescritasCliente() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			(FiltrarRelatorioImoveisFaturasAtrasoHelper) getParametro("filtrarRelatorioImoveisFaturasAtrasoHelper");
		
		RelatorioDataSource ds = new RelatorioDataSource( executarConsultaECriarColecaoRelatorioBeans(filtro) );

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		byte[] retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_CLIENTE,
				criarParametrosRelatorio(), ds, tipoFormatoRelatorio);

		try {
			persistirRelatorioConcluido(retorno, Relatorio.IMOVEIS_FATURAS_ATRASO_DESCRITAS_CLIENTE,
					this.getIdFuncionalidadeIniciada());
			
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
	}

	/**
	 * Executa a consulta no banco e com o resultado
	 * cria os objetos RelatorioBeans.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private List<RelatorioImoveisFaturasAtrasoBean> executarConsultaECriarColecaoRelatorioBeans(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) {
		
		List<RelatorioImoveisFaturasAtrasoBean> relatorioBeans = new ArrayList<RelatorioImoveisFaturasAtrasoBean>();

		Collection<RelatorioImoveisFaturasAtrasoHelper> colecao =  
			Fachada.getInstancia().pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(filtro);

		if ( !Util.isVazioOrNulo(colecao)) {

			Iterator<RelatorioImoveisFaturasAtrasoHelper> colecaoIterator = colecao.iterator();
			
			RelatorioImoveisFaturasAtrasoHelper helperAnterior = null;
			
			Collection<RelatorioImoveisFaturasAtrasoContasBean> colecaoDadosSubrelatorio = new ArrayList<RelatorioImoveisFaturasAtrasoContasBean>();
			
			BigDecimal totalFaturasSemEncargos = BigDecimal.ZERO;
			BigDecimal totalFaturasComEncargos = BigDecimal.ZERO;
			int qtdFaturasAtraso = 0;

			while (colecaoIterator.hasNext()) {

				RelatorioImoveisFaturasAtrasoHelper helper = colecaoIterator.next();
				
				if (helperAnterior == null) {
					helperAnterior = helper;
				}
				
				if (helperAnterior.getIdImovel().equals(helper.getIdImovel()) && 
						helperAnterior.getIdCliente().equals(helper.getIdCliente())) {
					
					colecaoDadosSubrelatorio.add(new RelatorioImoveisFaturasAtrasoContasBean(helper));
					
					qtdFaturasAtraso++;
					totalFaturasSemEncargos = totalFaturasSemEncargos.add(helper.getValorFaturasAtrasoSemEncargos());
					totalFaturasComEncargos = totalFaturasComEncargos.add(helper.getValorFaturasAtrasoComEncargos());
					
				} else {
				
					helperAnterior.setQuantidadeFaturasAtraso(qtdFaturasAtraso);
					RelatorioImoveisFaturasAtrasoBean relatorioAux = new RelatorioImoveisFaturasAtrasoBean(helperAnterior, colecaoDadosSubrelatorio);
					relatorioAux.setValorTotalFaturaAtrasoSemEncargo(totalFaturasSemEncargos);
					relatorioAux.setValorTotalFaturaAtrasoComEncargo(totalFaturasComEncargos);

					if(isQtdContasDentroIntervaloInformado(filtro, relatorioAux)
							&& isValorFaturasDentroIntervaloInformado(filtro, relatorioAux)){
						relatorioBeans.add(relatorioAux);
					}
					
					colecaoDadosSubrelatorio.clear();
					
					colecaoDadosSubrelatorio.add(new RelatorioImoveisFaturasAtrasoContasBean(helper));
					
					totalFaturasSemEncargos = BigDecimal.ZERO;
					totalFaturasComEncargos = BigDecimal.ZERO;

					totalFaturasSemEncargos = totalFaturasSemEncargos.add(helper.getValorFaturasAtrasoSemEncargos());
					totalFaturasComEncargos = totalFaturasComEncargos.add(helper.getValorFaturasAtrasoComEncargos());
					qtdFaturasAtraso = 1;
				}
				
				if(!colecaoIterator.hasNext()){

					helperAnterior.setQuantidadeFaturasAtraso(qtdFaturasAtraso);
					RelatorioImoveisFaturasAtrasoBean relatorioAux = new RelatorioImoveisFaturasAtrasoBean(helperAnterior, colecaoDadosSubrelatorio);
					relatorioAux.setValorTotalFaturaAtrasoSemEncargo(totalFaturasSemEncargos);
					relatorioAux.setValorTotalFaturaAtrasoComEncargo(totalFaturasComEncargos);

					if(isQtdContasDentroIntervaloInformado(filtro, relatorioAux)
							&& isValorFaturasDentroIntervaloInformado(filtro, relatorioAux)){

						relatorioBeans.add(relatorioAux);
					}
					
					colecaoDadosSubrelatorio.clear();
					
					colecaoDadosSubrelatorio.add(new RelatorioImoveisFaturasAtrasoContasBean(helper));
					
					totalFaturasSemEncargos = totalFaturasSemEncargos.add(helper.getValorFaturasAtrasoSemEncargos());
					totalFaturasComEncargos = totalFaturasComEncargos.add(helper.getValorFaturasAtrasoComEncargos());

					totalFaturasSemEncargos = BigDecimal.ZERO;
					totalFaturasComEncargos = BigDecimal.ZERO;
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
		
		if(filtro.getQuantidadeFaturasAtrasoInicial() == null || filtro.getQuantidadeFaturasAtrasoFinal() == null){
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
		
		if(filtro.getValorFaturasAtrasoInicial() == null || filtro.getValorFaturasAtrasoFinal() == null){
			return true;
		}
		
		return (relatorioAux.getValorTotalFaturaAtrasoSemEncargo().doubleValue() >= filtro.getValorFaturasAtrasoInicial() 
					&& relatorioAux.getValorTotalFaturaAtrasoSemEncargo().doubleValue() <= filtro.getValorFaturasAtrasoFinal());
	}

	/**
	 * Método configura os parametros que serão exibidos no relatório
	 * como sendo os filtros utilizados pelo usuario para gerá-lo.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private Map<String, Object> criarParametrosRelatorio() {

		Map<String,Object> parametros = new HashMap<String,Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("clienteSuperiorFiltro", getParametro("clienteSuperiorFiltro"));
		parametros.put("clienteFiltro", getParametro("clienteFiltro"));
		parametros.put("tipoRelacaoFiltro", getParametro("tipoRelacaoFiltro"));
		parametros.put("responsavelFiltro", getParametro("responsavelFiltro"));
		parametros.put("hidrometro", getParametro("hidrometro"));

		parametros.put("esfPoderFiltro", getParametro("esfPoderFiltro"));
		parametros.put("LigAguaFiltro", getParametro("LigAguaFiltro"));
		parametros.put("categoriaFiltro", getParametro("categoriaFiltro"));
		parametros.put("situacaoCobrancaFiltro", getParametro("situacaoCobrancaFiltro"));

		parametros.put("qtdFaturasFiltro", getParametro("qtdFaturasFiltro"));
		parametros.put("refFaturasFiltro", getParametro("refFaturasFiltro"));
		parametros.put("valorFaturasFiltro",getParametro("valorFaturasFiltro"));
		
		return parametros;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;

		retorno = 
			Fachada.getInstancia().pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
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
