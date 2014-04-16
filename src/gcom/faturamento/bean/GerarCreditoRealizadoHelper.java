package gcom.faturamento.bean;

import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.credito.CreditoTipo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Retornar os créditos realizados que serão gerados
 *
 * @author Raphael Rossiter
 * 
 * @date 28/03/2008
 */
public class GerarCreditoRealizadoHelper {

	private Collection<CreditoARealizar> colecaoCreditoARealizar;
	
	private Map<CreditoRealizado, Collection<CreditoRealizadoCategoria>> mapCreditoRealizado;
	
	private BigDecimal valorTotalCredito;
	
	private Map<CreditoTipo,BigDecimal> mapValoresPorTipoCredito;
	
	public GerarCreditoRealizadoHelper(){}

	public Collection<CreditoARealizar> getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	public void setColecaoCreditoARealizar(
			Collection<CreditoARealizar> colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	public Map<CreditoRealizado, Collection<CreditoRealizadoCategoria>> getMapCreditoRealizado() {
		return mapCreditoRealizado;
	}

	public void setMapCreditoRealizado(
			Map<CreditoRealizado, Collection<CreditoRealizadoCategoria>> mapCreditoRealizado) {
		this.mapCreditoRealizado = mapCreditoRealizado;
	}

	public BigDecimal getValorTotalCredito() {
		return valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}

	public Map<CreditoTipo, BigDecimal> getMapValoresPorTipoCredito() {
		return mapValoresPorTipoCredito;
	}

	public void setMapValoresPorTipoCredito(
			Map<CreditoTipo, BigDecimal> mapvaloresPorTipoCredito) {
		this.mapValoresPorTipoCredito = mapvaloresPorTipoCredito;
	}

	
}
