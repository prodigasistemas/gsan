package gcom.faturamento.conta;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Imposto Deduzidos
 * @author Fernanda Paiva
 * @since 22/09/2006
 */
public class GerarImpostosDeduzidosContaHelper {
	
	/**
	 * valor total dos impostos	 
	 */
	private BigDecimal valorTotalImposto;
	
	private BigDecimal valorBaseCalculo;

	/*
	 * listaImpostosDeduzidos
	 */
	private Collection<ImpostoDeduzidoHelper> listaImpostosDeduzidos;

	/**
	 * Constructor
	 */
	public GerarImpostosDeduzidosContaHelper() {
		
	}
	
	/**
	 * @param valorTotalImposto
	 * @param listaImpostosDeduzidos
	 */
	public GerarImpostosDeduzidosContaHelper(BigDecimal valorTotalImposto,
			Collection<ImpostoDeduzidoHelper> listaImpostosDeduzidos) {
		this.valorTotalImposto = valorTotalImposto;
		this.listaImpostosDeduzidos = listaImpostosDeduzidos;
	}

	/**
	 * @return Retorna o campo listaImpostosDeduzidos.
	 */
	public Collection<ImpostoDeduzidoHelper> getListaImpostosDeduzidos() {
		return listaImpostosDeduzidos;
	}

	/**
	 * @param listaImpostosDeduzidos O listaImpostosDeduzidos a ser setado.
	 */
	public void setListaImpostosDeduzidos(
			Collection<ImpostoDeduzidoHelper> listaImpostosDeduzidos) {
		this.listaImpostosDeduzidos = listaImpostosDeduzidos;
	}

	/**
	 * @return Retorna o campo valorTotalImposto.
	 */
	public BigDecimal getValorTotalImposto() {
		return valorTotalImposto;
	}

	/**
	 * @param valorTotalImposto O valorTotalImposto a ser setado.
	 */
	public void setValorTotalImposto(BigDecimal valorTotalImposto) {
		this.valorTotalImposto = valorTotalImposto;
	}

	public BigDecimal getValorBaseCalculo() {
		return valorBaseCalculo;
	}

	public void setValorBaseCalculo(BigDecimal valorBaseCalculo) {
		this.valorBaseCalculo = valorBaseCalculo;
	}

	
}
