package gcom.gui.cadastro;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * [UC1052] Informar Telefone
 *  
 * @author 	Daniel Alves 
 * @date  	05/08/2010
 */

public class InformarTelefoneActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	String tipoTelefone;
	
	Collection tiposTelefone;
	
	String telefonePrincipal;
	
	String municipioId;
	
	String municipio;
	
	String ddd;
	
	String numeroTelefone;
	
	String ramal;

	String nomeContato;
	
	String idCliente;
	
	String idImovel;
	
	String fecharTela;
	
	
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public Collection getTiposTelefone() {
		return tiposTelefone;
	}

	public void setTiposTelefone(Collection tiposTelefone) {
		this.tiposTelefone = tiposTelefone;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getFecharTela() {
		return fecharTela;
	}

	public void setFecharTela(String fecharTela) {
		this.fecharTela = fecharTela;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
					
}
