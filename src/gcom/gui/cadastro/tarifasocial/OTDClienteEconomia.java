package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;

public class OTDClienteEconomia {

	private Cliente cliente;
	private Imovel imovel;
	private ImovelEconomia imovelEconomia;
	private TarifaSocialDadoEconomia tarifaSocialDadoEconomia;
	
	

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public ImovelEconomia getImovelEconomia() {
		return imovelEconomia;
	}
	public void setImovelEconomia(ImovelEconomia imovelEconomia) {
		this.imovelEconomia = imovelEconomia;
	}
	public TarifaSocialDadoEconomia getTarifaSocialDadoEconomia() {
		return tarifaSocialDadoEconomia;
	}
	public void setTarifaSocialDadoEconomia(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia) {
		this.tarifaSocialDadoEconomia = tarifaSocialDadoEconomia;
	}
	
	public String getComplemento(){

		String complemento = "";
		if (imovel != null) {
			complemento = imovel.getComplementoEndereco();
		} else if (imovelEconomia != null) {
			complemento = imovelEconomia.getComplementoEndereco();
		}		
		return complemento;		
	}
	
	
	
}
