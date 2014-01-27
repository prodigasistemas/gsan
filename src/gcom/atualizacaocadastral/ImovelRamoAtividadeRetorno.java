package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.ImovelRamoAtividadePK;

import java.util.Date;

public class ImovelRamoAtividadeRetorno implements IImovelRamoAtividade {

	private ImovelRamoAtividadePK comp_id;
	private Date ultimaAlteracao;
	
	public ImovelRamoAtividadeRetorno() {
		
	}
	
	public ImovelRamoAtividadeRetorno(ImovelRamoAtividadePK pk) {
		this.comp_id = pk;
	}
	
	public ImovelRamoAtividadeRetorno(IImovelRamoAtividade imovelRamoAtividade) {
		this.comp_id = imovelRamoAtividade.getComp_id();
	}
	
	public ImovelRamoAtividadePK getComp_id() {
		return comp_id;
	}
	public void setComp_id(ImovelRamoAtividadePK comp_id) {
		this.comp_id = comp_id;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	
}
