package gcom.cadastro.imovel;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;

import gcom.atualizacaocadastral.IImovelRamoAtividade;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ImovelRamoAtividade extends ObjetoTransacao implements IImovelRamoAtividade {
    
	private static final long serialVersionUID = 1L;

	private ImovelRamoAtividadePK comp_id;
	
	private Date ultimaAlteracao;
	
	
	public ImovelRamoAtividade() {
		
	}
	
	public ImovelRamoAtividade(int idImovel, int idRamoAtividade) {
	this.comp_id = new ImovelRamoAtividadePK(idImovel, idRamoAtividade);	
	}
	
	public ImovelRamoAtividade(ImovelRamoAtividadePK comp_id, Date ultimaAlteracao) {
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
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

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroImovelRamoAtividade();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelRamoAtividade.RAMO_ATIVIDADE);
		filtro.adicionarParametro(
				new ParametroSimples(FiltroImovelRamoAtividade.IMOVEL_ID, this.getComp_id().getImovel().getId()));
		filtro.adicionarParametro(
				new ParametroSimples(FiltroImovelSubCategoria.SUBCATEGORIA_ID, 
						this.getComp_id().getRamo_atividade().getId()));
		return filtro; 
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"comp_id"};
		return retorno;
	}
	
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ImovelSubcategoria)) {
			return false;
		}
		ImovelRamoAtividade castOther = (ImovelRamoAtividade) other;

		// return
		// ((this.getComp_id().getSubcategoria().getId().equals(castOther.getComp_id().getSubcategoria().getId()))
		// &&
		// (this.getComp_id().getSubcategoria().getCategoria().getId().equals(castOther.getComp_id().getSubcategoria().getCategoria().getId())));
		return new EqualsBuilder().append(this.getComp_id(),
				castOther.getComp_id()).isEquals();
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public int hashCode() {
		return this.ultimaAlteracao.hashCode();
	}



	public Imovel getImovel() {
		return (comp_id != null) ? comp_id.getImovel(): null;
	}

	public void setImovel(Imovel imovel) {
		if (comp_id == null){
			comp_id = new ImovelRamoAtividadePK();
		}
		comp_id.setImovel(imovel);
	}

	public RamoAtividade getRamoAtividade() {
		return (comp_id != null) ? comp_id.getRamo_atividade(): null;
	}

	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		if (comp_id == null){
			comp_id = new ImovelRamoAtividadePK();
		}
		comp_id.setRamo_atividade(ramoAtividade);
	}
}
