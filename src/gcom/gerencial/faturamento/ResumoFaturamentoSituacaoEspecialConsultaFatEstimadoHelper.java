package gcom.gerencial.faturamento;

import java.math.BigDecimal;

/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper {

	private BigDecimal faturamentoEstimado;
	
	public ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper(BigDecimal somatorioValorAgua,BigDecimal somatorioValorEsgoto,BigDecimal somatorioDebito,BigDecimal somatorioValorCredito) {

		double agua = 0;
		if (somatorioValorAgua != null ) {
			agua = somatorioValorAgua.doubleValue();
		}

		double esgoto = 0;
		if (somatorioValorEsgoto != null ) {
			esgoto = somatorioValorEsgoto.doubleValue();
		}

		double debito = 0;
		if (somatorioDebito != null ) {
			debito = somatorioDebito.doubleValue();
		}

		double credito = 0;
		if (somatorioValorCredito != null  ) {
			credito = somatorioValorCredito.doubleValue();
		}

		faturamentoEstimado = new BigDecimal(agua + esgoto + debito - credito);
		
	}
	
	public ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper() {}

	
	public BigDecimal getFaturamentoEstimado() {
		return faturamentoEstimado;
	}
	public void setFaturamentoEstimado(BigDecimal faturamentoEstimado) {
		this.faturamentoEstimado = faturamentoEstimado;
	}
		
}
