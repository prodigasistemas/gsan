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
			Integer referencia, Map<Integer, BigDecimal> mapValor) {
		
		if (mapValor.containsKey(categoria.getId())) {

			BigDecimal valor = mapValor.get(categoria.getId());

			if (valor != null && valor.doubleValue() > 0.00) {
				
				ResumoArrecadacao resumo = ResumoArrecadacaoBuilder.buildResumoRecebimentosClassificadosRecuperacaoCredito(localidade, categoria, referencia, 
						valor, new LancamentoItem(LancamentoItem.RECUPERACAO_CREDITO), null, new Short("2010"), new Short("0")); 
				recebimentos.setRecuperacaoCredito(resumo);
			}
		}

		return recebimentos;
	}
	
	public RecebimentosClassificadosRecuperacaoCredito buildRecebimentosRecuperacaoCreditoMesesAnteriores(Localidade localidade, Categoria categoria, 
			Integer referencia, Map<Integer, BigDecimal> mapValor) {
		
		if (mapValor.containsKey(categoria.getId())) {

			BigDecimal valor = mapValor.get(categoria.getId());

			if (valor != null && valor.doubleValue() > 0.00) {
			
				ResumoArrecadacao resumo = ResumoArrecadacaoBuilder.buildResumoRecebimentosClassificadosRecuperacaoCreditoMesesAnteriores(localidade, categoria, referencia,
						valor, new LancamentoItem(LancamentoItem.RECUPERACAO_CREDITO), null, new Short("2011"), new Short("0"));
				recebimentos.setRecuperacaoCreditoMesesAnteriores(resumo);
			}
		}
		
		return recebimentos;
	}
	
}
