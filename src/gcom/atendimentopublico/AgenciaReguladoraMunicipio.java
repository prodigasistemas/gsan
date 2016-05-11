package gcom.atendimentopublico;

import gcom.cadastro.geografico.Municipio;

import java.io.Serializable;
import java.util.Date;

public class AgenciaReguladoraMunicipio implements Serializable {

	private static final long serialVersionUID = -7529386649851704773L;
	
	private AgenciaReguladoraMunicipioPK comp_id;
	
	private AgenciaReguladora agenciaReguladora;
	private Municipio municipio;
	private Date ultimaAlteracao;
	
	public AgenciaReguladoraMunicipioPK getComp_id() {
		return comp_id;
	}
	public void setComp_id(AgenciaReguladoraMunicipioPK comp_id) {
		this.comp_id = comp_id;
	}
	public AgenciaReguladora getAgenciaReguladora() {
		return agenciaReguladora;
	}
	public void setAgenciaReguladora(AgenciaReguladora agenciaReguladora) {
		this.agenciaReguladora = agenciaReguladora;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
