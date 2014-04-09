package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirHistogramaAguaEconomiaDetalheHelper;
import gcom.faturamento.bean.EmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaEconomiaHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * classe responsável por criar o relatório de histograma de ligação de agua por economia
 * 
 * @author Rafael Pinto / Rafael Correa
 * @created 18/06/2007
 */
public class RelatorioHistogramaAguaEconomia extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioHistogramaAguaEconomia(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA);
	}

	@Deprecated
	public RelatorioHistogramaAguaEconomia() {
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

		FiltrarEmitirHistogramaAguaEconomiaHelper filtrarEmitirHistogramaAguaEconomiaHelper = 
			(FiltrarEmitirHistogramaAguaEconomiaHelper) getParametro("filtrarEmitirHistogramaAguaEconomiaHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioHistogramaAguaEconomiaBean relatorioHistogramaAguaEconomiaBean = null;

		Collection<EmitirHistogramaAguaEconomiaHelper> colecao = 
			fachada.pesquisarEmitirHistogramaAguaEconomia(filtrarEmitirHistogramaAguaEconomiaHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				EmitirHistogramaAguaEconomiaHelper emitirHistogramaAguaEconomiaHelper = 
					(EmitirHistogramaAguaEconomiaHelper) colecaoIterator.next();
				
				String opcaoTotalizacao = emitirHistogramaAguaEconomiaHelper.getOpcaoTotalizacao();
				String descricao = emitirHistogramaAguaEconomiaHelper.getDescricaoCategoria();
				String descricaoSubcategoria = emitirHistogramaAguaEconomiaHelper.getDescricaoSubcategoria();				
				String descricaoTarifa = emitirHistogramaAguaEconomiaHelper.getDescricaoTarifa();
				
				Collection colecaoDetalhe = 
					emitirHistogramaAguaEconomiaHelper.getColecaoEmitirHistogramaAguaEconomiaDetalhe();
				
				String economiasMedido = null;
				String consumoMedioMedido = null;
				String consumoExcedenteMedido = null;
				String volumeConsumoMedido = null;
				String volumeFaturadoMedido = null;
				String receitaMedido = null;
				String ligacoesMedido = null;
				
				String economiasNaoMedido = null;
				String consumoMedioNaoMedido = null;
				String consumoExcedenteNaoMedido = null;
				String volumeConsumoNaoMedido = null;
				String volumeFaturadoNaoMedido = null;
				String receitaNaoMedido = null;
				String ligacoesNaoMedido = null;
				
				NumberFormat formato = NumberFormat.getInstance(new Locale("pt", "BR"));
				
				if (colecaoDetalhe != null && !colecaoDetalhe.isEmpty()) {
				
					Iterator colecaoDetalheIterator = colecaoDetalhe.iterator();
					
					while (colecaoDetalheIterator.hasNext()) {
						
						EmitirHistogramaAguaEconomiaDetalheHelper detalhe = 
							(EmitirHistogramaAguaEconomiaDetalheHelper) colecaoDetalheIterator.next();
						
						String faixa = detalhe.getDescricaoFaixa();
						
						economiasMedido = formato.format(detalhe.getEconomiasMedido());
						
						if(detalhe.getConsumoMedioMedido() != null){
							consumoMedioMedido = Util.formataBigDecimal( detalhe.getConsumoMedioMedido(), 2, true ); //(""+detalhe.getConsumoMedioMedido()).replace(".",",");	
						} else {
							consumoMedioMedido = null;
						}	
						if(detalhe.getConsumoExcedenteMedido() != null){
							consumoExcedenteMedido = Util.formataBigDecimal( detalhe.getConsumoExcedenteMedido(), 2, true );
						} else {
							consumoExcedenteMedido = null;
						}
						
						volumeConsumoMedido = formato.format(detalhe.getVolumeConsumoFaixaMedido());
						volumeFaturadoMedido = formato.format(detalhe.getVolumeFaturadoFaixaMedido());
						receitaMedido = Util.formatarMoedaReal(detalhe.getReceitaMedido());
						ligacoesMedido = formato.format(detalhe.getLigacoesMedido());
						
						economiasNaoMedido = formato.format(detalhe.getEconomiasNaoMedido());

						if(detalhe.getConsumoMedioNaoMedido() != null){
							consumoMedioNaoMedido = Util.formataBigDecimal( detalhe.getConsumoMedioNaoMedido(), 2, true);	
						} else {
							consumoMedioNaoMedido = null;
						}
						if(detalhe.getConsumoExcedenteNaoMedido() != null){
							consumoExcedenteNaoMedido = Util.formataBigDecimal( detalhe.getConsumoExcedenteNaoMedido(), 2, true);	
						} else {
							consumoExcedenteNaoMedido = null;
						}
						
						volumeConsumoNaoMedido = formato.format(detalhe.getVolumeConsumoFaixaNaoMedido());
						volumeFaturadoNaoMedido = formato.format(detalhe.getVolumeFaturadoFaixaNaoMedido());
						receitaNaoMedido = Util.formatarMoedaReal(detalhe.getReceitaNaoMedido());
						ligacoesNaoMedido = formato.format(detalhe.getLigacoesNaoMedido());
						descricaoSubcategoria = detalhe.getDescricaoSubcategoria();
						
						relatorioHistogramaAguaEconomiaBean = 
							new RelatorioHistogramaAguaEconomiaBean(
								// Opção de Totalização
								opcaoTotalizacao,
								// Categoria
								descricao,
								// Subcategoria
								descricaoSubcategoria,
								// Descrição da Faixa
								faixa,
								// Número de Economias
								economiasMedido,
								// Consumo Medio para Medido
								consumoMedioMedido,
								// Consumo Excedente para Medido
								consumoExcedenteMedido,						
								// Volume Consumo para Medido
								volumeConsumoMedido,						
								// Volume Faturado para Medido
								volumeFaturadoMedido,						
								// Receita para Medido
								receitaMedido,						
								// Número de Economias para Não Medido
								economiasNaoMedido,						
								// Consumo Medio para Não Medido
								consumoMedioNaoMedido,						
								// Consumo Excedente para Não Medido
								consumoExcedenteNaoMedido,						
								// Volume Consumo para Não Medido
								volumeConsumoNaoMedido,						
								// Volume Faturado para Não Medido
								volumeFaturadoNaoMedido,					
								// Receita para Não Medido 
								receitaNaoMedido,
								// Descricao Tarifa 
								descricaoTarifa,
								// Ligacoes Medido
								ligacoesMedido,
								// Ligacoes Nao Medido
								ligacoesNaoMedido);
		
						// adiciona o bean a coleção
						relatorioBeans.add(relatorioHistogramaAguaEconomiaBean);
					
					}
				
				}
				
				String faixa = emitirHistogramaAguaEconomiaHelper.getDescricaoFaixa();
				
				economiasMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalEconomiasMedido());
				volumeConsumoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeConsumoMedido());
				volumeFaturadoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeFaturadoMedido());
				receitaMedido = Util.formatarMoedaReal(emitirHistogramaAguaEconomiaHelper.getTotalReceitaMedido());
				ligacoesMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalLigacoesMedido());
				
				economiasNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalEconomiasNaoMedido());
				volumeConsumoNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeConsumoNaoMedido());
				volumeFaturadoNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalVolumeFaturadoNaoMedido());
				receitaNaoMedido = Util.formatarMoedaReal(emitirHistogramaAguaEconomiaHelper.getTotalReceitaNaoMedido());
				ligacoesNaoMedido = formato.format(emitirHistogramaAguaEconomiaHelper.getTotalLigacoesNaoMedido());
				
				relatorioHistogramaAguaEconomiaBean = 
					new RelatorioHistogramaAguaEconomiaBean(
						// Opção de Totalização
						opcaoTotalizacao,
						// Categoria
						descricao,
						// Subcategoria
						descricaoSubcategoria,
						// Descrição da Faixa
						faixa,
						// Número de Economias
						economiasMedido,
						// Consumo Medio para Medido
						null,
						// Consumo Excedente para Medido
						null,						
						// Volume Consumo para Medido
						volumeConsumoMedido,						
						// Volume Faturado para Medido
						volumeFaturadoMedido,						
						// Receita para Medido
						receitaMedido,						
						// Número de Economias para Não Medido
						economiasNaoMedido,						
						// Consumo Medio para Não Medido
						null,						
						// Consumo Excedente para Não Medido
						null,						
						// Volume Consumo para Não Medido
						volumeConsumoNaoMedido,						
						// Volume Faturado para Não Medido
						volumeFaturadoNaoMedido,					
						// Receita para Não Medido 
						receitaNaoMedido,
						// Descricao Tarifa 
						descricaoTarifa,
						// Ligacoes Medido								
						ligacoesMedido,
						// Ligacoes Nao Medido								
						ligacoesNaoMedido);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioHistogramaAguaEconomiaBean);

				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMes", Util.formatarAnoMesParaMesAno(filtrarEmitirHistogramaAguaEconomiaHelper.getMesAnoFaturamento()));
		
		parametros.put("tipoFormatoRelatorio", "R0604");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if ( filtrarEmitirHistogramaAguaEconomiaHelper.getIndicadorTarifaCategoria() == 1 ){
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA,
				parametros, ds, tipoFormatoRelatorio);
		} else {
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA_SUBCATEGORIA,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.HISTOGRAMA_AGUA_POR_ECONOMIA,
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

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioHistogramaAguaEconomia", this);

	}

}
