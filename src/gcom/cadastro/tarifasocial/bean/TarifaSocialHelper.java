package gcom.cadastro.tarifasocial.bean;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class TarifaSocialHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TarifaSocialHelper(){}
	
	private ClienteImovel clienteImovel;
	
	private ClienteImovelEconomia clienteImovelEconomia;
	
	private TarifaSocialDadoEconomia tarifaSocialDadoEconomia;
	
	private Collection colecaoClientesInseridos;
	
	private Collection colecaoClientesEconomiaInseridos;
	
	private Collection colecaoClientesRemovidos;
	
	private Collection colecaoClientesEconomiaRemovidos;
	
	private Collection colecaoClientesImoveis;
	
	private Collection colecaoClientesImoveisEconomias;
	
	private Date dataComparecimentoCarta;

	/**
	 * @return Retorna o campo clienteImovel.
	 */
	public ClienteImovel getClienteImovel() {
		return clienteImovel;
	}

	/**
	 * @param clienteImovel O clienteImovel a ser setado.
	 */
	public void setClienteImovel(ClienteImovel clienteImovel) {
		this.clienteImovel = clienteImovel;
	}

	/**
	 * @return Retorna o campo tarifaSocialDadoEconomia.
	 */
	public TarifaSocialDadoEconomia getTarifaSocialDadoEconomia() {
		return tarifaSocialDadoEconomia;
	}

	/**
	 * @param tarifaSocialDadoEconomia O tarifaSocialDadoEconomia a ser setado.
	 */
	public void setTarifaSocialDadoEconomia(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia) {
		this.tarifaSocialDadoEconomia = tarifaSocialDadoEconomia;
	}

	/**
	 * @return Retorna o campo colecaoClientesInseridos.
	 */
	public Collection getColecaoClientesInseridos() {
		return colecaoClientesInseridos;
	}

	/**
	 * @param colecaoClientesInseridos O colecaoClientesInseridos a ser setado.
	 */
	public void setColecaoClientesInseridos(Collection colecaoClientesInseridos) {
		this.colecaoClientesInseridos = colecaoClientesInseridos;
	}

	/**
	 * @return Retorna o campo colecaoClientesRemovidos.
	 */
	public Collection getColecaoClientesRemovidos() {
		return colecaoClientesRemovidos;
	}

	/**
	 * @param colecaoClientesRemovidos O colecaoClientesRemovidos a ser setado.
	 */
	public void setColecaoClientesRemovidos(Collection colecaoClientesRemovidos) {
		this.colecaoClientesRemovidos = colecaoClientesRemovidos;
	}

	/**
	 * @return Retorna o campo clienteImovelEconomia.
	 */
	public ClienteImovelEconomia getClienteImovelEconomia() {
		return clienteImovelEconomia;
	}

	/**
	 * @param clienteImovelEconomia O clienteImovelEconomia a ser setado.
	 */
	public void setClienteImovelEconomia(ClienteImovelEconomia clienteImovelEconomia) {
		this.clienteImovelEconomia = clienteImovelEconomia;
	}

	/**
	 * @return Retorna o campo colecaoClientesImoveis.
	 */
	public Collection getColecaoClientesImoveis() {
		return colecaoClientesImoveis;
	}

	/**
	 * @param colecaoClientesImoveis O colecaoClientesImoveis a ser setado.
	 */
	public void setColecaoClientesImoveis(Collection colecaoClientesImoveis) {
		this.colecaoClientesImoveis = colecaoClientesImoveis;
	}

	/**
	 * @return Retorna o campo colecaoClientesImoveisEconomias.
	 */
	public Collection getColecaoClientesImoveisEconomias() {
		return colecaoClientesImoveisEconomias;
	}

	/**
	 * @param colecaoClientesImoveisEconomias O colecaoClientesImoveisEconomias a ser setado.
	 */
	public void setColecaoClientesImoveisEconomias(
			Collection colecaoClientesImoveisEconomias) {
		this.colecaoClientesImoveisEconomias = colecaoClientesImoveisEconomias;
	}

	/**
	 * @return Retorna o campo colecaoClientesEconomiaInseridos.
	 */
	public Collection getColecaoClientesEconomiaInseridos() {
		return colecaoClientesEconomiaInseridos;
	}

	/**
	 * @param colecaoClientesEconomiaInseridos O colecaoClientesEconomiaInseridos a ser setado.
	 */
	public void setColecaoClientesEconomiaInseridos(
			Collection colecaoClientesEconomiaInseridos) {
		this.colecaoClientesEconomiaInseridos = colecaoClientesEconomiaInseridos;
	}

	/**
	 * @return Retorna o campo colecaoClientesEconomiaRemovidos.
	 */
	public Collection getColecaoClientesEconomiaRemovidos() {
		return colecaoClientesEconomiaRemovidos;
	}

	/**
	 * @param colecaoClientesEconomiaRemovidos O colecaoClientesEconomiaRemovidos a ser setado.
	 */
	public void setColecaoClientesEconomiaRemovidos(
			Collection colecaoClientesEconomiaRemovidos) {
		this.colecaoClientesEconomiaRemovidos = colecaoClientesEconomiaRemovidos;
	}

	public Date getDataComparecimentoCarta() {
		return dataComparecimentoCarta;
	}

	public void setDataComparecimentoCarta(Date dataComparecimentoCarta) {
		this.dataComparecimentoCarta = dataComparecimentoCarta;
	}
	
	
}
