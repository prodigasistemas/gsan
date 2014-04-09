package gcom.util;

import gcom.faturamento.conta.Conta;

import java.math.BigDecimal;

public class Calculos {
	
	/**
	 * [UC0261] - Obter o percentual de variação do consumo faturado 
	 * Author : Fernanda Karla
	 * Data : 11/05/2006
	 * 
	 * Calcula o percentual de variacao do consumo faturado
	 * 
	 * @param consumoFaturado
	 *        consumoMedio do Imóvel
	 * @return valorPercentual
	 */
	public static String obterPercentualVariacaoConsumoFaturado(int consumoFaturado, int consumoMedio) {
		
		String retorno = null;
		
		if(consumoFaturado != 0 && consumoMedio != 0)
		{
		    int operacaoSubMult = (consumoFaturado - consumoMedio) * 100;
		    BigDecimal percentual = new BigDecimal(operacaoSubMult).divide(new BigDecimal(consumoMedio), 2, BigDecimal.ROUND_HALF_UP);
		    String valorPercentual = "" + percentual;
		    retorno = valorPercentual.replace(".", ",") + "%";
		}
		return retorno;
	}
	
	/**
	 * [UC0337] - Obter Calculos 
	 * Author : Sávio Luiz
	 * Data : 25/05/2006
	 * 
	 * Calcula o valor da conta.
	 * Nome do Cálculo = "Valor da Conta"
	 * 
	 * @param consumoFaturado
	 *        consumoMedio do Imóvel
	 * @return valorPercentual
	 */
	public static BigDecimal valorConta(Conta conta) {
		
		BigDecimal valorContaSemImpostos = conta.getValorTotal();
		BigDecimal valorConta = null;
		if (conta.getValorImposto() != null && !conta.getValorImposto().equals("")){
		 valorConta = valorContaSemImpostos.subtract(conta.getValorImposto());
		}else{
			valorConta = valorContaSemImpostos;
		}
		return valorConta;
	}
}
