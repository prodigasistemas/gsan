package gcom.faturamento.bean;

import gcom.faturamento.conta.GerarImpostosDeduzidosContaHelper;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.faturamento.debito.DebitoTipo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Retornar os débitos cobrados que serão gerados
 *
 * @author Raphael Rossiter
 * 
 * @date 28/03/2008
 */
public class GerarDebitoCobradoHelper {

	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	
	private Map<DebitoCobrado, Collection<DebitoCobradoCategoria>> mapDebitosCobrados;
	
	private BigDecimal valorTotalDebito;
	
	private Map<DebitoTipo,BigDecimal> mapValoresPorTipoDebito;
	
	private GerarImpostosDeduzidosContaHelper gerarImpostosDeduzidosContaHelper;
	
	public GerarDebitoCobradoHelper(){}

	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Map<DebitoCobrado, Collection<DebitoCobradoCategoria>> getMapDebitosCobrados() {
		return mapDebitosCobrados;
	}

	public void setMapDebitosCobrados(
			Map<DebitoCobrado, Collection<DebitoCobradoCategoria>> mapDebitosCobrados) {
		this.mapDebitosCobrados = mapDebitosCobrados;
	}

	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}

	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}

	public Map<DebitoTipo, BigDecimal> getMapValoresPorTipoDebito() {
		return mapValoresPorTipoDebito;
	}

	public void setMapValoresPorTipoDebito(
			Map<DebitoTipo, BigDecimal> mapValoresPorTipoDebito) {
		this.mapValoresPorTipoDebito = mapValoresPorTipoDebito;
	}

	public GerarImpostosDeduzidosContaHelper getGerarImpostosDeduzidosContaHelper() {
		return gerarImpostosDeduzidosContaHelper;
	}

	public void setGerarImpostosDeduzidosContaHelper(
			GerarImpostosDeduzidosContaHelper gerarImpostosDeduzidosContaHelper) {
		this.gerarImpostosDeduzidosContaHelper = gerarImpostosDeduzidosContaHelper;
	}
}
