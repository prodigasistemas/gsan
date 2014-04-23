package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0883] Inserir CEP
 * 
 * @author Vinicius Medeiros
 */

public class InserirCepActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	String cepId;
	
	String codigo;
	
	String sigla;
	
	String descricaoIntervaloNumeracao;
	
	String cepTipo;
	
	String municipioId;
	
	String municipio;

	String bairroId;
	
	String bairro;

	String logradouroTipo;
	
	String logradouro;
	
	String indicadorUso;

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCepId() {
		return cepId;
	}

	public void setCepId(String cepId) {
		this.cepId = cepId;
	}

	public String getCepTipo() {
		return cepTipo;
	}

	public void setCepTipo(String cepTipo) {
		this.cepTipo = cepTipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricaoIntervaloNumeracao() {
		return descricaoIntervaloNumeracao;
	}

	public void setDescricaoIntervaloNumeracao(String descricaoIntervaloNumeracao) {
		this.descricaoIntervaloNumeracao = descricaoIntervaloNumeracao;
	}

	public String getLogradouroTipo() {
		return logradouroTipo;
	}

	public void setLogradouroTipo(String logradouroTipo) {
		this.logradouroTipo = logradouroTipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getBairroId() {
		return bairroId;
	}

	public void setBairroId(String bairroId) {
		this.bairroId = bairroId;
	}

	public String getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}

}
