package gcom.atualizacaocadastral;

import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.imovel.Imovel;

import java.util.Date;

public class ImovelRamoAtividadeRetorno implements IImovelRamoAtividade {
	
	private Integer id;
	
    private Imovel imovel;

    private RamoAtividade ramoAtividade;
	
	private Date ultimaAlteracao;

	private Integer idImovelRetorno;
	
	public ImovelRamoAtividadeRetorno() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Integer getIdImovelRetorno() {
		return idImovelRetorno;
	}
	public void setIdImovelRetorno(Integer idImovelRetorno) {
		this.idImovelRetorno = idImovelRetorno;
	}
}
