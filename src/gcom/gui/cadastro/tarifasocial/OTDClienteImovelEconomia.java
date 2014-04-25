package gcom.gui.cadastro.tarifasocial;

import java.util.Date;

public class OTDClienteImovelEconomia {

    private Integer id;
    
    private Integer idImovelEconomia;

    private Date dataInicioRelacao;

    private Date dataFimRelacao;

    private gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo;

    private gcom.cadastro.cliente.Cliente cliente;

    private gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo;

	public gcom.cadastro.cliente.Cliente getCliente() {
		return cliente;
	}

	public Integer getIdImovelEconomia() {
		return idImovelEconomia;
	}

	public void setIdImovelEconomia(Integer idImovelEconomia) {
		this.idImovelEconomia = idImovelEconomia;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo getClienteImovelFimRelacaoMotivo() {
		return clienteImovelFimRelacaoMotivo;
	}

	public void setClienteImovelFimRelacaoMotivo(
			gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo) {
		this.clienteImovelFimRelacaoMotivo = clienteImovelFimRelacaoMotivo;
	}

	public gcom.cadastro.cliente.ClienteRelacaoTipo getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(
			gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}

	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}

	public Date getDataInicioRelacao() {
		return dataInicioRelacao;
	}

	public void setDataInicioRelacao(Date dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
