package gcom.gui.cadastro.geografico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0006]	FILTRAR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 03/01/2007
 */

public class FiltrarMunicipioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoMunicipio;
	private String nomeMunicipio;
	private String tipoPesquisa;
	private String regiaoDesenv;
	private String regiao;
	private String microregiao;
	private String unidadeFederacao;
	private String indicadorUso;
	private String ordernarPor;
		

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}


	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}


	public String getIndicadorUso() {
		return indicadorUso;
	}


	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}


	public String getNomeMunicipio() {
		return nomeMunicipio;
	}


	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}


	public String getMicroregiao() {
		return microregiao;
	}


	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}


	public String getRegiao() {
		return regiao;
	}


	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}


	public String getRegiaoDesenv() {
		return regiaoDesenv;
	}


	public void setRegiaoDesenv(String regiaoDesenv) {
		this.regiaoDesenv = regiaoDesenv;
	}


	public String getTipoPesquisa() {
		return tipoPesquisa;
	}


	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}


	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}


	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}


	public String getOrdernarPor() {
		return ordernarPor;
	}


	public void setOrdernarPor(String ordernarPor) {
		this.ordernarPor = ordernarPor;
	}

}
