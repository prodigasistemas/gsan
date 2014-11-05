package gcom.arrecadacao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.Localidade;
import gcom.financeiro.lancamento.LancamentoItem;

import java.math.BigDecimal;
import java.util.Map;

public class RecebimentosClassificadosBuilder {

	static RecebimentosClassificados recebimentos;
	
	public RecebimentosClassificadosBuilder(){
		recebimentos = new RecebimentosClassificados();
	}

	public RecebimentosClassificados buildRecebimentosContasAgua(Localidade localidade, Categoria categoria, 
			Integer referencia, Map<Integer, BigDecimal> mapValor) {
		
		if (mapValor.containsKey(categoria.getId())) {

			BigDecimal valor = mapValor.get(categoria.getId());

			if (valor != null && valor.doubleValue() > 0.00) {
				
				ResumoArrecadacao resumo = ResumoArrecadacaoBuilder.buildResumoRecebimentosClassificadosContas(localidade, categoria, referencia, valor, 
						new LancamentoItem(LancamentoItem.AGUA), null,  new Short("100"), new Short("0"));
						
				recebimentos.setContasAgua(resumo);
			}
		}

		return recebimentos;
	}
	

}
