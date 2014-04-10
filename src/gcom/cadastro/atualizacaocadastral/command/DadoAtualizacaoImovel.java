package gcom.cadastro.atualizacaocadastral.command;

import java.util.ArrayList;
import java.util.List;

public class DadoAtualizacaoImovel {
	private List<TipoEconomia> tiposEconomia = new ArrayList<TipoEconomia>();
	
	public List<TipoEconomia> getTiposEconomia() {
		return tiposEconomia;
	}

	public void addTipoEconomia(TipoEconomia tipoEconomia) {
		this.tiposEconomia.add(tipoEconomia);
	}
	
	public boolean contemTipoEconomia(TipoEconomia... tipos){
		for (TipoEconomia tipo : tipos) {
			if (tiposEconomia.contains(tipo))
				return true;			
		}
		return false;
	}
	
	public boolean contemApenasResidencial(){
		for (TipoEconomia tipo: TipoEconomia.values()){
			if (tipo != TipoEconomia.RESIDENCIAL && tiposEconomia.contains(tipo)){
				return false;
			}
		}
		return true;
	}
}
