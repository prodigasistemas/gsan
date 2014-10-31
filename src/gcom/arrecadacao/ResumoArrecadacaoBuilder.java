package gcom.arrecadacao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.Localidade;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;

import java.math.BigDecimal;
import java.util.Date;

public class ResumoArrecadacaoBuilder {

	public static ResumoArrecadacao buildResumoRecebimentosClassificadosContas(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.CONTAS);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);
		
		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosParcelamentosCobrados(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.PARCELAMENTOS_COBRADOS_SUP_CONTAS);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);
		
		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosCreditosRealizados(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.CREDITOS_REALIZADOS_SUP_CONTAS);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosImpostosRetidos(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.IMPOSTOS_RETIDOS_NAS_CONTAS_RECEBIDAS);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosGuiasPagamento(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.GUIAS_PAGAMENTO);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosDoacoes(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.DOACOES_RECEBIDAS_EM_CONTA);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosDebitosACobrar(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.DEBITOS_A_COBRAR);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosTotalCreditos(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.TOTAL_CREDITOS_REALIZADOS);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosTotal(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.TOTAL_DOS_RECEBIMENTOS_CLASSIFICADOS);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosTotalContas(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.TOTAL_DOS_RECEBIMENTOS_DE_CONTA_CLASSIFICADOS);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosRecuperacaoCredito(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS_RECUPERACAO_CREDITO);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.RECUPERACAO_CREDITO);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);
		
		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosClassificadosRecuperacaoCreditoMesesAnteriores(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_CLASSIFICADOS_RECUPERACAO_CREDITO_MESES_ANTERIORES);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.RECUPERACAO_CREDITO);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	public static ResumoArrecadacao buildResumoRecebimentosNaoClassificadosDuplicidade(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		RecebimentoTipo recebimentoTipo = new RecebimentoTipo(RecebimentoTipo.RECEBIMENTOS_NAO_CLASSIFICADOS);
		LancamentoTipo lancamentoTipo = new LancamentoTipo(LancamentoTipo.PAGAMENTO_EM_DUPLICIDADE);
		
		ResumoArrecadacao resumo = buildResumo(localidade, categoria, referencia, valor, lancamentoItem, lancamentoItemContabil, seqTipoLancamento, seqItemTipoLancamento);
		resumo.setRecebimentoTipo(recebimentoTipo);
		resumo.setLancamentoTipo(lancamentoTipo);

		return resumo;
	}
	
	private static ResumoArrecadacao buildResumo(Localidade localidade, Categoria categoria, 
			Integer referencia, BigDecimal valor,
			LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, 
			Short seqTipoLancamento, Short seqItemTipoLancamento) {
		
		ResumoArrecadacao resumo = new ResumoArrecadacao();

		resumo.setGerenciaRegional(localidade.getGerenciaRegional());
		resumo.setLocalidade(localidade);
		resumo.setCategoria(categoria);
		resumo.setAnoMesReferencia(referencia);
		resumo.setLancamentoItem(lancamentoItem);
		resumo.setLancamentoItemContabil(lancamentoItemContabil);
		resumo.setSequenciaTipoLancamento(seqTipoLancamento);
		resumo.setSequenciaItemTipoLancamento(seqItemTipoLancamento);
		resumo.setValorItemArrecadacao(valor);
		resumo.setUltimaAlteracao(new Date());
		
		return resumo;
		
	}
}
