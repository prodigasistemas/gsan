package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ImovelEconomiaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ImovelEconomiaHelper(){}
	
	private String clienteNome;
	
	private String complementoEndereco;
	
	private Short numeroPontosUtilizacao;
	
	private Short numeroMoradores;
	
	private BigDecimal numeroIptu;
	
	private Short areaConstruida;
	
	private BigDecimal areaConstruidaImovelEconomia;
	
	private Collection clienteImovelEconomiaHelper;
	
	public Long numeroContratoCelpe;

	public Short getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(Short areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public Collection getClienteImovelEconomiaHelper() {
		return clienteImovelEconomiaHelper;
	}

	public void setClienteImovelEconomiaHelper(
			Collection clienteImovelEconomiaHelper) {
		this.clienteImovelEconomiaHelper = clienteImovelEconomiaHelper;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public Long getNumeroContratoCelpe() {
		return numeroContratoCelpe;
	}

	public void setNumeroContratoCelpe(Long numeroContratoCelpe) {
		this.numeroContratoCelpe = numeroContratoCelpe;
	}

	public BigDecimal getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(BigDecimal numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public Short getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(Short numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public Short getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}

	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	public BigDecimal getAreaConstruidaImovelEconomia() {
		return areaConstruidaImovelEconomia;
	}

	public void setAreaConstruidaImovelEconomia(
			BigDecimal areaConstruidaImovelEconomia) {
		this.areaConstruidaImovelEconomia = areaConstruidaImovelEconomia;
	}
	
}
