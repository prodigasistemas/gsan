package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.imovel.Imovel;

public interface IImovelRamoAtividade {
	
	public Imovel getImovel();
	
	public void setImovel(Imovel imovel);
	
	public RamoAtividade getRamoAtividade();
	
	public void setRamoAtividade(RamoAtividade ramoAtividade);
}
