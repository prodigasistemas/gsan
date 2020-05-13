package gcom.cadastro.imovel;

import java.util.Date;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;

public class ContratoInstalacaoReservacao extends ObjetoTransacao {

	
	private static final long serialVersionUID = 1357231991214727953L;
	
	private Integer id;
	private Date ultimaAlteracao;
	
	private Contrato contrato;
	private ClienteImovel clienteImovel;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public ClienteImovel getClienteImovel() {
		return clienteImovel;
	}
	public void setClienteImovel(ClienteImovel clienteImovel) {
		this.clienteImovel = clienteImovel;
	}
	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	@Override
	public Filtro retornaFiltro() {
		return null;
	}
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public String gerarNumeroContrato() {
		return Util.formatarDataAAAAMMDD(new Date()) + clienteImovel.getCliente().getId();
	}
}
