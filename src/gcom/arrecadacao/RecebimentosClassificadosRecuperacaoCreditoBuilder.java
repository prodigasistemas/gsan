package gcom.arrecadacao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.Localidade;
import gcom.financeiro.lancamento.LancamentoItem;

import java.math.BigDecimal;
import java.util.Map;

public class RecebimentosClassificadosRecuperacaoCreditoBuilder {

	static RecebimentosClassificadosRecuperacaoCredito recebimentos;
	
	public RecebimentosClassificadosRecuperacaoCreditoBuilder(){
		recebimentos = new RecebimentosClassificadosRecuperacaoCredito();
	}

	public RecebimentosClassificadosRecuperacaoCredito buildRecebimentosRecuperacaoCredito(Localidade localidade, Categoria categoria, 
			Integer referencia, Map<Integer, BigDecimal> mapValorDuplicidade, Map<Integer, BigDecimal> mapValorCancelado) {
		
		if (mapValorDuplicidade.containsKey(categoria.getId())) {

			BigDecimal valor = mapValorDuplicidade.get(categoria.getId());

			if (valor != null && valor.doubleValue() > 0.00) {
				
				ResumoArrecadacao resumo = ResumoArrecadacaoBuilder.buildResumoRecebimentosClassificadosRecuperacaoCredito(localidade, categoria, referencia, 
						valor, new LancamentoItem(LancamentoItem.RECUPERACAO_CREDITO_DUPLICIDADE), null, new Short("2010"), new Short("0")); 
				recebimentos.setRecuperacaoCreditoDuplicidade(resumo);
			}
		}
		
		if (mapValorCancelado.containsKey(categoria.getId())) {

			BigDecimal valor = mapValorCancelado.get(categoria.getId());

			if (valor != null && valor.doubleValue() > 0.00) {
				
				ResumoArrecadacao resumo = ResumoArrecadacaoBuilder.buildResumoRecebimentosClassificadosRecuperacaoCredito(localidade, categoria, referencia, 
						valor, new LancamentoItem(LancamentoItem.RECUPERACAO_CREDITO_CANCELADO), null, new Short("2011"), new Short("0")); 
				recebimentos.setRecuperacaoCreditoCancelado(resumo);
			}
		}

		return recebimentos;
	}
	
	public RecebimentosClassificadosRecuperacaoCredito buildRecebimentosRecuperacaoCreditoMesesAnteriores(Localidade localidade, Categoria categoria, 
			Integer referencia, Map<Integer, BigDecimal> mapValorDuplicidade, Map<Integer, BigDecimal> mapValorCancelado) {
		
		if (mapValorDuplicidade.containsKey(categoria.getId())) {

			BigDecimal valor = mapValorDuplicidade.get(categoria.getId());

			if (valor != null && valor.doubleValue() > 0.00) {
			
				ResumoArrecadacao resumo = ResumoArrecadacaoBuilder.buildResumoRecebimentosClassificadosRecuperacaoCreditoMesesAnteriores(localidade, categoria, referencia,
						valor, new LancamentoItem(LancamentoItem.RECUPERACAO_CREDITO_DUPLICIDADE), null, new Short("2012"), new Short("0"));
				recebimentos.setRecuperacaoCreditoDuplicidadeMesesAnteriores(resumo);
			}
		}
		
		if (mapValorCancelado.containsKey(categoria.getId())) {

			BigDecimal valor = mapValorCancelado.get(categoria.getId());

			if (valor != null && valor.doubleValue() > 0.00) {
			
				ResumoArrecadacao resumo = ResumoArrecadacaoBuilder.buildResumoRecebimentosClassificadosRecuperacaoCreditoMesesAnteriores(localidade, categoria, referencia,
						valor, new LancamentoItem(LancamentoItem.RECUPERACAO_CREDITO_CANCELADO), null, new Short("2013"), new Short("0"));
				recebimentos.setRecuperacaoCreditoCanceladoMesesAnteriores(resumo);
			}
		}
		
		return recebimentos;
	}
	
}
