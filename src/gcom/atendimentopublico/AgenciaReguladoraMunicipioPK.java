package gcom.atendimentopublico;

import java.io.Serializable;

public class AgenciaReguladoraMunicipioPK implements Serializable {

	private static final long serialVersionUID = 8289521161872046622L;
	
	private Integer agenciaReguladoraId;
	private Integer municipioId;
	
	public AgenciaReguladoraMunicipioPK() {}
	
	public AgenciaReguladoraMunicipioPK(Integer agenciaReguladoraId, Integer municipioId) {
		this.agenciaReguladoraId = agenciaReguladoraId;
		this.municipioId = municipioId;
	}

	public Integer getAgenciaReguladoraId() {
		return agenciaReguladoraId;
	}

	public void setAgenciaReguladoraId(Integer agenciaReguladoraId) {
		this.agenciaReguladoraId = agenciaReguladoraId;
	}

	public Integer getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(Integer municipioId) {
		this.municipioId = municipioId;
	}
}
