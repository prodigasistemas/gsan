package gcom.gui.cadastro.sistemaparametro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0536]FILTRAR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 19/01/2006
 */


public class FiltrarFeriadoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
private String indicadorTipoFeriado;
private String idMunicipio;
private String descricaoMunicipio;
private String dataFeriadoInicio;
private String dataFeriadoFim;
private String descricaoFeriado;



public String getDataFeriadoFim() {
	return dataFeriadoFim;
}
public void setDataFeriadoFim(String dataFeriadoFim) {
	this.dataFeriadoFim = dataFeriadoFim;
}
public String getDataFeriadoInicio() {
	return dataFeriadoInicio;
}
public void setDataFeriadoInicio(String dataFeriadoInicio) {
	this.dataFeriadoInicio = dataFeriadoInicio;
}
public String getDescricaoFeriado() {
	return descricaoFeriado;
}
public void setDescricaoFeriado(String descricaoFeriado) {
	this.descricaoFeriado = descricaoFeriado;
}
public String getDescricaoMunicipio() {
	return descricaoMunicipio;
}
public void setDescricaoMunicipio(String descricaoMunicipio) {
	this.descricaoMunicipio = descricaoMunicipio;
}
public String getIdMunicipio() {
	return idMunicipio;
}
public void setIdMunicipio(String idMunicipio) {
	this.idMunicipio = idMunicipio;
}
public String getIndicadorTipoFeriado() {
	return indicadorTipoFeriado;
}
public void setIndicadorTipoFeriado(String indicadorTipoFeriado) {
	this.indicadorTipoFeriado = indicadorTipoFeriado;
}




}





	
	
	

