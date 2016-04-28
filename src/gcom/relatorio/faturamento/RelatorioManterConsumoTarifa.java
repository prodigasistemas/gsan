package gcom.relatorio.faturamento;

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
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de manter consumo tarifa
 * 
 * @author Rafael Corrêa
 * @created 11/05/2007
 */
public class RelatorioManterConsumoTarifa extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterConsumoTarifa(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSUMO_TARIFA_MANTER);
	}

	@Deprecated
	public RelatorioManterConsumoTarifa() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		String descricao = (String) getParametro("descricao");
		Date dataVigenciaInicial = (Date) getParametro("dataVigenciaInicial");
		Date dataVigenciaFinal = (Date) getParametro("dataVigenciaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterConsumoTarifaBean relatorioBean = null;

		Collection colecaoConsumoTarifaRelatorioHelper = fachada
				.pesquisarConsumoTarifaRelatorio(descricao,
						dataVigenciaInicial, dataVigenciaFinal);

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoConsumoTarifaRelatorioHelper != null && !colecaoConsumoTarifaRelatorioHelper.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoConsumoTarifaRelatorioHelperIterator = colecaoConsumoTarifaRelatorioHelper.iterator();

			// laço para criar a coleção de parâmetros da analise
			
			String categoriaAnterior = "";
			String idConsumoTarifaAnterior = "";
			Date dataVigenciaInicialAnterior = null;
			
			ConsumoTarifaRelatorioHelper consumoTarifaRelatorioHelper = null;
			
			while (colecaoConsumoTarifaRelatorioHelperIterator.hasNext()) {

				if (consumoTarifaRelatorioHelper != null) {
					idConsumoTarifaAnterior = consumoTarifaRelatorioHelper.getIdConsumoTarifa().toString(); 
				}
				
				consumoTarifaRelatorioHelper = (ConsumoTarifaRelatorioHelper) colecaoConsumoTarifaRelatorioHelperIterator
						.next();
				
				// Validade
				String validade = "";
				
				if (consumoTarifaRelatorioHelper.getDataValidadeInicial() != null
						&& ((!consumoTarifaRelatorioHelper
								.getDataValidadeInicial().equals(
										dataVigenciaInicialAnterior)) || (!idConsumoTarifaAnterior
								.equals(consumoTarifaRelatorioHelper
										.getIdConsumoTarifa().toString())))) {
					validade = validade + Util.formatarData(consumoTarifaRelatorioHelper.getDataValidadeInicial());
					dataVigenciaInicialAnterior = consumoTarifaRelatorioHelper.getDataValidadeInicial();
					
					Date dataValidadeFinal = fachada.pesquisarDataFinalValidadeConsumoTarifa(consumoTarifaRelatorioHelper.getIdConsumoTarifa(), consumoTarifaRelatorioHelper.getDataValidadeInicial());
					
					if (dataValidadeFinal != null) {
						validade = validade + " A " + Util.formatarData(dataValidadeFinal);
					}

				}
				
				// Categoria
				String categoria = "";
				
				if (consumoTarifaRelatorioHelper.getCategoria() != null) {
					categoria = consumoTarifaRelatorioHelper.getCategoria();
					categoriaAnterior = consumoTarifaRelatorioHelper.getCategoria();
				}
				
				// Faixa de Consumo
				String faixa = "";
				
				if (consumoTarifaRelatorioHelper.getFaixaInicial() != null) {
					faixa = faixa + consumoTarifaRelatorioHelper.getFaixaInicial();
				}
				
				if (consumoTarifaRelatorioHelper.getFaixaFinal() != null) {
					faixa = faixa + " A " + consumoTarifaRelatorioHelper.getFaixaFinal();
				}
					
				// Custo	
				String custo = "";
				
				if (consumoTarifaRelatorioHelper.getCusto() != null) {
					custo = Util.formatarMoedaReal(consumoTarifaRelatorioHelper.getCusto());
				}
					
				// Tarifa Mínima	
				String tarifaMinima = "";
				
				if (consumoTarifaRelatorioHelper.getTarifaMinima() != null && !categoria.equals("")) {
					tarifaMinima = Util.formatarMoedaReal(consumoTarifaRelatorioHelper.getTarifaMinima());
				}
				
				// Consumo Mínimo
				// Inclui mais um bean com o valor do consumo minimo, a data de validade e a categoria
				if (consumoTarifaRelatorioHelper.getConsumoMinimo() != null && !categoria.equals("")) {
					String faixaMinima = "ATE " + consumoTarifaRelatorioHelper.getConsumoMinimo(); 
					
					relatorioBean = new RelatorioManterConsumoTarifaBean(

							// Tarifa de Consumo
							consumoTarifaRelatorioHelper.getDescricaoConsumoTarifa(),
							
							// Validade
							validade,
							
							// Categoria
							categoria,
							
							// Faixa de Consumo
							faixaMinima,
							
							// Custo do M³
							"",
							
							// Tarifa Mínima
							tarifaMinima);
					
					// adiciona o bean a coleção
					relatorioBeans.add(relatorioBean);
					
					// Seta os valores para vazio para não serem impressos novamente
					validade = "";
					categoria = "";
					tarifaMinima = "";
				}

				relatorioBean = new RelatorioManterConsumoTarifaBean(

						// Tarifa de Consumo
						consumoTarifaRelatorioHelper.getDescricaoConsumoTarifa(),
						
						// Validade
						validade,
						
						// Categoria
						categoria,
						
						// Faixa de Consumo
						faixa,
						
						// Custo do M³
						custo,
						
						// Tarifa Mínima
						tarifaMinima);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		// Descrição 
		if (descricao != null) {
			parametros.put("descricao", descricao);
		} else {
			parametros.put("descricao", "");
		}
		
		// Data Vigência
		if (dataVigenciaInicial != null) {
			parametros.put("dataVigencia", Util.formatarData(dataVigenciaInicial) + " a " + Util.formatarData(dataVigenciaFinal));
		} else {
			parametros.put("dataVigencia", "");
		}

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSUMO_TARIFA_MANTER,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MANTER_BAIRRO,
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

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroBairro) getParametro("filtroBairro"),
//				Bairro.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterBairro", this);

	}

}
