package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipeComponentes;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarDadosEquipeActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	// Dados da Equipe
	private String idEquipe;
    private String nomeEquipe;
    private String placaVeiculo;
    private String cargaTrabalhoDia;
    private String unidadeOrganizacionalId;
    private String unidadeOrganizacionalDescricao;
    private String tipoPerfilServicoId;
    private String tipoPerfilServicoDescricao;
    private String codigoDdd;
    private String numeroTelefone;
    private String numeroImei;
    
    // Equipe Componente
    private Collection<EquipeComponentes> equipeComponentes = new ArrayList<EquipeComponentes>();
    
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    
    	this.idEquipe = null;
        this.nomeEquipe = null;
        this.placaVeiculo = null;
        this.cargaTrabalhoDia = null;
        this.unidadeOrganizacionalId = null;
        this.unidadeOrganizacionalDescricao = null;
        this.tipoPerfilServicoId = null;
        this.tipoPerfilServicoDescricao = null;
        this.codigoDdd = null;
        this.numeroTelefone = null;
        this.numeroImei = null;
    }

    /**
	 * @return Retorna a carga de trabalho por dia.
	 */
	public String getCargaTrabalhoDia() {
		return cargaTrabalhoDia;
	}
	/**
	 * @param cargaTrabalhoDia A carga de trabalho por dia a ser setada.
	 */
	public void setCargaTrabalhoDia(String cargaTrabalhoDia) {
		this.cargaTrabalhoDia = cargaTrabalhoDia;
	}
    /**
	 * @return Retorna o nome da equipe.
	 */
	public String getNomeEquipe() {
		return nomeEquipe;
	}
	/**
	 * @param nomeEquipe O nome da equipe a ser setado.
	 */
	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}
    /**
	 * @return Retorna a placa do veículo.
	 */
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	/**
	 * @param placaVeiculo A placa do veículo a ser setada.
	 */
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
    /**
	 * @return Retorna a descrição do tipo de perfil do serviço.
	 */
	public String getTipoPerfilServicoDescricao() {
		return tipoPerfilServicoDescricao;
	}
	/**
	 * @param tipoPerfilServicoDescricao A descrição do tipo de perfil de serviço a ser setada.
	 */
	public void setTipoPerfilServicoDescricao(String tipoPerfilServicoDescricao) {
		this.tipoPerfilServicoDescricao = tipoPerfilServicoDescricao;
	}
    /**
	 * @return Retorna o id do tipo de perfil do serviço.
	 */
	public String getTipoPerfilServicoId() {
		return tipoPerfilServicoId;
	}
	/**
	 * @param tipoPerfilServicoDescricao O id do tipo de perfil de serviço a ser setado.
	 */
	public void setTipoPerfilServicoId(String tipoPerfilServicoId) {
		this.tipoPerfilServicoId = tipoPerfilServicoId;
	}
    /**
	 * @return Retorna a descrição da unidade organizacional.
	 */
	public String getUnidadeOrganizacionalDescricao() {
		return unidadeOrganizacionalDescricao;
	}
	/**
	 * @param unidadeOrganizacionalDescricao A descrição da unidade organizacional a ser setada.
	 */
	public void setUnidadeOrganizacionalDescricao(
			String unidadeOrganizacionalDescricao) {
		this.unidadeOrganizacionalDescricao = unidadeOrganizacionalDescricao;
	}
    /**
	 * @return Retorna o id da unidade organizacional.
	 */
	public String getUnidadeOrganizacionalId() {
		return unidadeOrganizacionalId;
	}
	/**
	 * @param unidadeOrganizacionalId O id da unidade organizacional a ser setado.
	 */
	public void setUnidadeOrganizacionalId(String unidadeOrganizacionalId) {
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
	}

	public Collection<EquipeComponentes> getEquipeComponentes() {
		return equipeComponentes;
	}
	public void setEquipeComponentes(Collection<EquipeComponentes> equipeComponentes) {
		this.equipeComponentes = equipeComponentes;
	}
	public String getIdEquipe() {
		return idEquipe;
	}
	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(String numeroImei) {
		this.numeroImei = numeroImei;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	
}
