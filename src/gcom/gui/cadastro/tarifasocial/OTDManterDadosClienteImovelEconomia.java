package gcom.gui.cadastro.tarifasocial;

import gcom.gui.ObjetoTransmissaoDados;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class OTDManterDadosClienteImovelEconomia extends ObjetoTransmissaoDados  {

	private Integer idImovel;
	private int quantidadeEconomia;
	
	private Map idImovleEconomiaCollectionCliente = new HashMap();
	
	private  Collection otdClienteEconomia = new Vector();

	public OTDManterDadosClienteImovelEconomia(HttpServletRequest request, Integer idImovel, int quantidadeEconomia){
		super (request);
		this.idImovel = idImovel; 
		this.quantidadeEconomia = quantidadeEconomia ;
	}

	public void putIDEconomiaCliente(Integer id, OTDClienteImovelEconomia clienteImovelEconomia ){
		Collection cliente = null;
		if (idImovleEconomiaCollectionCliente.containsKey(id) && idImovleEconomiaCollectionCliente.get(id) != null) {
			cliente = (Collection) idImovleEconomiaCollectionCliente.get(id);
			
		} else {
			cliente = new Vector();
			idImovleEconomiaCollectionCliente.put(id, cliente);
		}
		
		cliente.add(clienteImovelEconomia);
	}

	public Collection getCollectionCliente(Integer id){
		if (idImovleEconomiaCollectionCliente.containsKey(id)) {
			return (Collection) idImovleEconomiaCollectionCliente.get(id);	
		} else {
			return new Vector();
		}
		
	}

	public void resetCollectionCliente(Integer id){
		idImovleEconomiaCollectionCliente.put(id,null);
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public int getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void addOtdClienteEconomia(OTDClienteEconomia OtdClienteEconomia ) {
		otdClienteEconomia.add(OtdClienteEconomia);
	}
	public Collection getOtdClienteEconomia() {
		return otdClienteEconomia;
	}

	public void setOtdClienteEconomia(Collection otdClienteEconomia) {
		this.otdClienteEconomia = otdClienteEconomia;
	}	
}
