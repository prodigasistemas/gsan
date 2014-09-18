package gcom.cadastro.localidade.bean;

import gcom.micromedicao.FiltroRota;
import gcom.util.filtro.ParametroSimples;

public class InserirQuadraHelper {

	private String localidadeID; 
	private String setorComercialCD; 
	private String quadraNM; 
	private String perfilQuadraID; 
	private String areaTipoID; 
	private String indicadorRedeAgua; 
	private String indicadorRedeEsgoto; 
	private String sistemaEsgotoID;
	private String baciaID;
	private String distritoOperacionalID;
	private String setorCensitarioID;
	private String zeisID;
	private String rotaCD;
	/*
     * Mantis 536 - Felipe Santos - 08/03/2012
     * 
     * Adição do id da rota no helper
     */
	private String rotaID;
    // fim da alteração	
	private String indicadorIncrementoLote;
	private String bairroCD;
	private String municipioID;
	
	private String quadraId;
	private String indicadorUso;
	
	public InserirQuadraHelper(){}
	
	public String getAreaTipoID() {
		return areaTipoID;
	}
	public void setAreaTipoID(String areaTipoID) {
		this.areaTipoID = areaTipoID;
	}
	public String getBaciaID() {
		return baciaID;
	}
	public void setBaciaID(String baciaID) {
		this.baciaID = baciaID;
	}
	public String getDistritoOperacionalID() {
		return distritoOperacionalID;
	}
	public void setDistritoOperacionalID(String distritoOperacionalID) {
		this.distritoOperacionalID = distritoOperacionalID;
	}
	public String getIndicadorIncrementoLote() {
		return indicadorIncrementoLote;
	}
	public void setIndicadorIncrementoLote(String indicadorIncrementoLote) {
		this.indicadorIncrementoLote = indicadorIncrementoLote;
	}
	public String getIndicadorRedeAgua() {
		return indicadorRedeAgua;
	}
	public void setIndicadorRedeAgua(String indicadorRedeAgua) {
		this.indicadorRedeAgua = indicadorRedeAgua;
	}
	public String getIndicadorRedeEsgoto() {
		return indicadorRedeEsgoto;
	}
	public void setIndicadorRedeEsgoto(String indicadorRedeEsgoto) {
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
	}
	public String getLocalidadeID() {
		return localidadeID;
	}
	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}
	public String getPerfilQuadraID() {
		return perfilQuadraID;
	}
	public void setPerfilQuadraID(String perfilQuadraID) {
		this.perfilQuadraID = perfilQuadraID;
	}
	public String getQuadraNM() {
		return quadraNM;
	}
	public void setQuadraNM(String quadraNM) {
		this.quadraNM = quadraNM;
	}
	public String getRotaCD() {
		return rotaCD;
	}
	public void setRotaCD(String rotaCD) {
		this.rotaCD = rotaCD;
	}
	public String getRotaID() {
		return rotaID;
	}
	public void setRotaID(String rotaID) {
		this.rotaID = rotaID;
	}
	public String getSetorCensitarioID() {
		return setorCensitarioID;
	}
	public void setSetorCensitarioID(String setorCensitarioID) {
		this.setorCensitarioID = setorCensitarioID;
	}
	public String getSetorComercialCD() {
		return setorComercialCD;
	}
	public void setSetorComercialCD(String setorComercialCD) {
		this.setorComercialCD = setorComercialCD;
	}
	public String getSistemaEsgotoID() {
		return sistemaEsgotoID;
	}
	public void setSistemaEsgotoID(String sistemaEsgotoID) {
		this.sistemaEsgotoID = sistemaEsgotoID;
	}
	public String getZeisID() {
		return zeisID;
	}
	public void setZeisID(String zeisID) {
		this.zeisID = zeisID;
	}

	public String getQuadraId() {
		return quadraId;
	}

	public void setQuadraId(String quadraId) {
		this.quadraId = quadraId;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getBairroCD() {
		return bairroCD;
	}

	public void setBairroCD(String bairroCD) {
		this.bairroCD = bairroCD;
	}

	public String getMunicipioID() {
		return municipioID;
	}

	public void setMunicipioID(String municipioID) {
		this.municipioID = municipioID;
	}

}
