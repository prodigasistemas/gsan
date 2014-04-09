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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */

public class RelatorioAnaliticoFaturamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAnaliticoFaturamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALITICO_FATURAMENTO);
	}
	
	@Deprecated
	public RelatorioAnaliticoFaturamento() {
		super(null, "");
	}
	
	private Collection<RelatorioAnaliticoFaturamentoBean> inicializarBeanRelatorio(
			Collection colecaoAnaliticoRelatorio) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioAnaliticoFaturamentoBean> retorno = new ArrayList<RelatorioAnaliticoFaturamentoBean>();

		BigDecimal valorTotalGeral= BigDecimal.ZERO;
		BigDecimal valorTotal = BigDecimal.ZERO;
		BigDecimal valorAgua = BigDecimal.ZERO;
		BigDecimal valorEsgoto = BigDecimal.ZERO;
		BigDecimal valorDebito = BigDecimal.ZERO;
		BigDecimal valorCredito = BigDecimal.ZERO;
		Iterator iter = colecaoAnaliticoRelatorio.iterator();
		
		while (iter.hasNext()) {
			
			RelatorioAnaliticoFaturamentoHelper rel = (RelatorioAnaliticoFaturamentoHelper) iter.next();

			valorAgua = BigDecimal.ZERO;
			valorEsgoto = BigDecimal.ZERO;
			valorDebito = BigDecimal.ZERO;
			valorCredito = BigDecimal.ZERO;
			
			valorAgua = rel.getValorAgua();
			valorEsgoto = rel.getValorEsgoto();
			valorDebito = rel.getDebitos();
			valorCredito = rel.getValorCreditos();
			
			valorTotal = BigDecimal.ZERO;
			valorTotal = valorTotal.add(valorAgua);
			valorTotal = valorTotal.add(valorEsgoto);
			valorTotal = valorTotal.add(valorDebito);
			valorTotal = valorTotal.subtract(valorCredito);
			
			valorTotalGeral = valorTotalGeral.add(valorTotal);
			
			
			String codigoBarra = fachada.obterRepresentacaoNumericaCodigoBarra(3,
						valorTotal,rel.getIdLocalidade(),rel.getIdImovel(),
						rel.getMesAnoFaturamento(),new Integer(rel.getDigitoVerificador()), null,
						null,null,null,null,null,null);
			
			
			RelatorioAnaliticoFaturamentoBean relatorioAnaliticoFaturamentoBean = 
					new RelatorioAnaliticoFaturamentoBean(rel.getIdImovel()+ "",
							rel.getConsumoAgua() != null ? new BigDecimal(rel.getConsumoAgua()) : BigDecimal.ZERO, 
							rel.getConsumoRateioAgua() != null ? new BigDecimal(rel.getConsumoRateioAgua()) : BigDecimal.ZERO, 
							rel.getValorAgua() != null ? rel.getValorAgua() : BigDecimal.ZERO, 
							rel.getConsumoEsgoto() != null ? new BigDecimal(rel.getConsumoEsgoto())  : BigDecimal.ZERO, 
							rel.getConsumoRateioEsgoto() != null ? new BigDecimal(rel.getConsumoRateioEsgoto()) : BigDecimal.ZERO, 
							rel.getValorEsgoto() != null ? rel.getValorEsgoto() : BigDecimal.ZERO,
							rel.getDebitos() != null ? rel.getDebitos() : BigDecimal.ZERO, 
							rel.getValorCreditos() != null ? rel.getValorCreditos() : BigDecimal.ZERO, 
							valorTotal != null ? valorTotal  : BigDecimal.ZERO,
							valorTotalGeral != null ? valorTotalGeral + "" : "0", 
							rel.getCodigoSetorComercial() != null 
							? Util.adicionarZerosEsquedaNumero(3,rel.getCodigoSetorComercial() + "") : "0",
							rel.getInscricao() != null ? rel.getInscricao() + "" : "0",
							rel.getIdLocalidade() != null 
							? Util.adicionarZerosEsquedaNumero(3,rel.getIdLocalidade() + "") : "0",
							Util.formatarCodigoBarra(codigoBarra), rel.getIdDescricaoLocalidade(), rel.getIdDescricaoUnidadeNegocio(),
							rel.getNomeCliente());
				
			retorno.add(relatorioAnaliticoFaturamentoBean);
			
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

		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		String vencimento = (String) getParametro("vencimento");
		Collection colecaoAnaliticoFaturamento = (Collection) getParametro("colecaoAnaliticoFaturamento");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("vencimento", vencimento);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		parametros.put("tipoFormatoRelatorio", "R0593");
		
		Collection dadosRelatorio = colecaoAnaliticoFaturamento;

		Collection<RelatorioAnaliticoFaturamentoBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ANALITICO_FATURAMENTO, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.ANALITICO_FATURAMENTO,
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
//		Integer mesAnoInteger = (Integer) getParametro("mesAnoInteger");
//		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
//		Collection idEsferaPoder = (Collection) getParametro("colecaoIdEsferaPoder");
		
		
		return 1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnaliticoFaturamento", this);
	}
	
}
