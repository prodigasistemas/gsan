package gcom.relatorio.cobranca;


import gcom.faturamento.conta.ImpostoDeduzidoHelper;

import java.util.Collection;


public class RelatorioImpostosPorClienteResponsavelHelper {
	
	//o id do cliente responsavel
	private String clienteID;
	
	//o nome do cliente responsavel
	private String nomeCliente;	
	
	
	private String imovel;

	private String idFatura;
	//valor da fatura do cliente
	private String valorFatura;
	
	
    private Collection<ImpostoDeduzidoHelper> impostosDeduzidos;  
    

	public RelatorioImpostosPorClienteResponsavelHelper() {

	}

	
	public String getIdFatura() {
		return idFatura;
	}


	public void setIdFatura(String idFatura) {
		this.idFatura = idFatura;
	}


	public String getClienteID() {
		return clienteID;
	}

	public void setClienteID(String clienteID) {
		this.clienteID = clienteID;
	}

	
	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public Collection<ImpostoDeduzidoHelper> getImpostosDeduzidos() {
		return impostosDeduzidos;
	}

	public void setImpostosDeduzidos(
			Collection<ImpostoDeduzidoHelper> impostosDeduzidos) {
		this.impostosDeduzidos = impostosDeduzidos;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(String valorFatura) {
		this.valorFatura = valorFatura;
	}
}
