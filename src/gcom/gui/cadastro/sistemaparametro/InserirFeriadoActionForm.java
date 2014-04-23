package gcom.gui.cadastro.sistemaparametro;


import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.util.Util;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0534]	INSERIR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 12/01/2007
 */

public class InserirFeriadoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String indicadorTipoFeriado;
	
	private String idMunicipio;

	private String descricaoMunicipio;
	
	private String dataFeriado;
	
	private String descricaoFeriado;

	public String getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(String dataFeriado) {
		this.dataFeriado = dataFeriado;
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
	
	// Esse método carrega todos os valores da base de dados 
	// necesários para exibição da tela InserirMunicipio, quando este se trata de um Feriado Municipal.
	public MunicipioFeriado setFormValuesMunicipal(MunicipioFeriado municipioFeriado) {
		
		if(getIdMunicipio() != null && !getIdMunicipio().equals("")){
		  Municipio municipio = new Municipio();
		  municipio.setId(Integer.parseInt(getIdMunicipio()));
		  municipioFeriado.setMunicipio(municipio);
		}
		
		municipioFeriado.setDataFeriado(Util.converteStringParaDate(getDataFeriado()));
		municipioFeriado.setDescricaoFeriado(getDescricaoFeriado());
		municipioFeriado.setUltimaAlteracao(new Date());
		
		return municipioFeriado;
	}
	
	//	 Esse método carrega todos os valores da base de dados 
	// necesários para exibição da tela InserirMunicipio, quando este se trata de um Feriado Nacional.
	public NacionalFeriado setFormValuesNacional(NacionalFeriado nacionalFeriado) {
		
		nacionalFeriado.setData(Util.converteStringParaDate(getDataFeriado()));
		nacionalFeriado.setDescricao(getDescricaoFeriado());
		nacionalFeriado.setUltimaAlteracao(new Date());
		
		return nacionalFeriado;
	}
}
