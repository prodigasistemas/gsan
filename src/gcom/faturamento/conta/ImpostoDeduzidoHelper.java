package gcom.faturamento.conta;

import java.math.BigDecimal;

/**
 * Imposto Deduzidos
 * @author Fernanda Paiva
 * @since 22/09/2006
 */
public class ImpostoDeduzidoHelper {
	
	/**
	 * idImpostoDeduzido	 
	 * */
	private Integer idImpostoTipo;
	
	/**
	 * valor total dos impostos	 
	 */
	private BigDecimal valor;

	private String descricaoImposto;
		
	/**
	 * percentual da aliquota 
	 */
	private BigDecimal percentualAliquota;
	
	private Integer idCliente;
	
	private String nomeCliente;
	
	private BigDecimal valorFatura;
	
	private Integer idImovel;
	
	private String cnpjCliente;
	
	/*
	 * Coleção de Notas Promissorias?
	 */
	//private Collection<NotaPromissoria> colecaoNotasPromissorias;

	/**
	 * Constructor
	 */
	public ImpostoDeduzidoHelper() {
		
	}
	
	/**
	 * @param idImpostoTipo
	 * @param valorTotalImposto
	 * @param percentualAliquota
	 */
	public ImpostoDeduzidoHelper(Integer idImpostoTipo, BigDecimal valor,
			BigDecimal percentualAliquota) {
		this.idImpostoTipo = idImpostoTipo;
		this.valor = valor;
		this.percentualAliquota = percentualAliquota;
	}
	
	

	public String getDescricaoImposto() {
		return descricaoImposto;
	}

	public void setDescricaoImposto(String descricaoImposto) {
		this.descricaoImposto = descricaoImposto;
	}

	/**
	 * @return Returns the idImpostoTipo.
	 */
	public Integer getIdImpostoTipo() {
		return idImpostoTipo;
	}

	/**
	 * @param idImpostoTipo The idImpostoTipo to set.
	 */
	public void setIdImpostoTipo(
			Integer idImpostoTipo) {
		this.idImpostoTipo = idImpostoTipo;
	}

	/**
	 * @return Returns the valorTotalImposto.
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valorTotalImposto The valorTotalImposto to set.
	 */
	public void setValor(
			BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return Returns the percentualAliquota.
	 */
	public BigDecimal getPercentualAliquota() {
		return percentualAliquota;
	}

	/**
	 * @param percentualAliquota The percentualAliquota to set.
	 */
	public void setPercentualAliquota(
			BigDecimal percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descricaoImposto == null) ? 0 : descricaoImposto.hashCode());
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result
				+ ((idImpostoTipo == null) ? 0 : idImpostoTipo.hashCode());
		result = prime * result
				+ ((nomeCliente == null) ? 0 : nomeCliente.hashCode());
		result = prime
				* result
				+ ((percentualAliquota == null) ? 0 : percentualAliquota
						.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImpostoDeduzidoHelper other = (ImpostoDeduzidoHelper) obj;
		if (descricaoImposto == null) {
			if (other.descricaoImposto != null)
				return false;
		} else if (!descricaoImposto.equals(other.descricaoImposto))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (idImpostoTipo == null) {
			if (other.idImpostoTipo != null)
				return false;
		} else if (!idImpostoTipo.equals(other.idImpostoTipo))
			return false;
		if (nomeCliente == null) {
			if (other.nomeCliente != null)
				return false;
		} else if (!nomeCliente.equals(other.nomeCliente))
			return false;
		if (percentualAliquota == null) {
			if (other.percentualAliquota != null)
				return false;
		} else if (!percentualAliquota.equals(other.percentualAliquota))
			return false;
		
		return true;
	}
	
}
