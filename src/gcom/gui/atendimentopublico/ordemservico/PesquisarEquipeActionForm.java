package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form Bean do Pesquisar Equipe
 * 
 * @author Leonardo Regis
 * @date 31/07/2006
 */
public class PesquisarEquipeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String equipeId;
	private String nomeEquipe;
    private String placaVeiculo;
    private String cargaTrabalhoDia;
    private String unidadeOrganizacionalId;
    private String unidadeOrganizacionalDescricao;
    private String tipoPerfilServicoId;
    private String tipoPerfilServicoDescricao;
    // Controle
    private String validaUnidadeOrganizacional = "false";
    private String validaTipoPerfilServico = "false";
    
	public String getValidaTipoPerfilServico() {
		return validaTipoPerfilServico;
	}
	public void setValidaTipoPerfilServico(String validaTipoPerfilServico) {
		this.validaTipoPerfilServico = validaTipoPerfilServico;
	}
	public String getValidaUnidadeOrganizacional() {
		return validaUnidadeOrganizacional;
	}
	public void setValidaUnidadeOrganizacional(String validaUnidadeOrganizacional) {
		this.validaUnidadeOrganizacional = validaUnidadeOrganizacional;
	}
	public String getCargaTrabalhoDia() {
		return cargaTrabalhoDia;
	}
	public void setCargaTrabalhoDia(String cargaTrabalhoDia) {
		this.cargaTrabalhoDia = cargaTrabalhoDia;
	}
	public String getNomeEquipe() {
		return nomeEquipe;
	}
	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	public String getTipoPerfilServicoDescricao() {
		return tipoPerfilServicoDescricao;
	}
	public void setTipoPerfilServicoDescricao(String tipoPerfilServicoDescricao) {
		this.tipoPerfilServicoDescricao = tipoPerfilServicoDescricao;
	}
	public String getTipoPerfilServicoId() {
		return tipoPerfilServicoId;
	}
	public void setTipoPerfilServicoId(String tipoPerfilServicoId) {
		this.tipoPerfilServicoId = tipoPerfilServicoId;
	}
	public String getUnidadeOrganizacionalDescricao() {
		return unidadeOrganizacionalDescricao;
	}
	public void setUnidadeOrganizacionalDescricao(
			String unidadeOrganizacionalDescricao) {
		this.unidadeOrganizacionalDescricao = unidadeOrganizacionalDescricao;
	}
	public String getUnidadeOrganizacionalId() {
		return unidadeOrganizacionalId;
	}
	public void setUnidadeOrganizacionalId(String unidadeOrganizacionalId) {
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
	}
	public String getEquipeId() {
		return equipeId;
	}
	public void setEquipeId(String equipeId) {
		this.equipeId = equipeId;
	}
}
