package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Ana Maria
 * @date 01/08/2006
 */
public class PesquisarTipoPerfilServicoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	private String idServicoPerfil;

	private String descricaoServicoPerfil;

	private String abreviaturaServicoPerfil;

	private String componenteEquipe;

	private String equipamentoEspecial;
	
	private String descricaoEquipamentoEspecial;

	private String veiculoProprio;
	
	private String tipoPesquisa;
	
	private String tipoPesquisaAbreviada;


	public void reset(){
		this.descricaoServicoPerfil = null;
		this.abreviaturaServicoPerfil= null;
		this.componenteEquipe = null;
		this.equipamentoEspecial= null;
		this.descricaoEquipamentoEspecial = null;
		this.veiculoProprio= null;
		this.tipoPesquisa= null;
		this.tipoPesquisaAbreviada= null;
	}
	
	public String getAbreviaturaServicoPerfil() {
		return abreviaturaServicoPerfil;
	}

	public void setAbreviaturaServicoPerfil(String abreviaturaServicoPerfil) {
		this.abreviaturaServicoPerfil = abreviaturaServicoPerfil;
	}

	public String getDescricaoServicoPerfil() {
		return descricaoServicoPerfil;
	}

	public void setDescricaoServicoPerfil(String descricaoServicoPerfil) {
		this.descricaoServicoPerfil = descricaoServicoPerfil;
	}

	public String getIdServicoPerfil() {
		return idServicoPerfil;
	}

	public void setIdServicoPerfil(String idServicoPerfil) {
		this.idServicoPerfil = idServicoPerfil;
	}

	public String getComponenteEquipe() {
		return componenteEquipe;
	}

	public void setComponenteEquipe(String componenteEquipe) {
		this.componenteEquipe = componenteEquipe;
	}

	public String getEquipamentoEspecial() {
		return equipamentoEspecial;
	}

	public void setEquipamentoEspecial(String equipamentoEspecial) {
		this.equipamentoEspecial = equipamentoEspecial;
	}


	public String getVeiculoProprio() {
		return veiculoProprio;
	}

	public void setVeiculoProprio(String veiculoProprio) {
		this.veiculoProprio = veiculoProprio;
	}

	public String getDescricaoEquipamentoEspecial() {
		return descricaoEquipamentoEspecial;
	}

	public void setDescricaoEquipamentoEspecial(String descricaoEquipamentoEspecial) {
		this.descricaoEquipamentoEspecial = descricaoEquipamentoEspecial;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getTipoPesquisaAbreviada() {
		return tipoPesquisaAbreviada;
	}

	public void setTipoPesquisaAbreviada(String tipoPesquisaAbreviada) {
		this.tipoPesquisaAbreviada = tipoPesquisaAbreviada;
	}

}
