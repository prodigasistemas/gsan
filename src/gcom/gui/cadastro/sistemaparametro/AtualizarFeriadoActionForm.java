package gcom.gui.cadastro.sistemaparametro;


import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorForm;


/**
 * [UC0535] Manter Feriado [SB0001]Atualizar Feriado
 *
 * @author Kássia Albuquerque
 * @date 24/01/2007
 */

public class AtualizarFeriadoActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	private String indicadorTipoFeriado;
	
	private String codigoFeriado;
	
	private String idMunicipio;

	private String descricaoMunicipio;
	
	private String dataFeriado;
	
	private String descricaoFeriado;

	
	
	public String getCodigoFeriado() {
		return codigoFeriado;
	}

	public void setCodigoFeriado(String codigoFeriado) {
		this.codigoFeriado = codigoFeriado;
	}

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
	
	
	
	
	public MunicipioFeriado setFormValuesMunicipal(MunicipioFeriado municipioFeriado) {
		
		if(getIdMunicipio() != null && !getIdMunicipio().equals("")){
		  Municipio municipio = new Municipio();
		  municipio.setId(Integer.parseInt(getIdMunicipio()));
		  municipioFeriado.setMunicipio(municipio);
		}
		
		municipioFeriado.setDataFeriado(Util.converteStringParaDate(getDataFeriado()));
		municipioFeriado.setDescricaoFeriado(getDescricaoFeriado());
				
		return municipioFeriado;
	}
	
	
	public NacionalFeriado setFormValuesNacional(NacionalFeriado nacionalFeriado) {
		
		nacionalFeriado.setData(Util.converteStringParaDate(getDataFeriado()));
		nacionalFeriado.setDescricao(getDescricaoFeriado());
				
		return nacionalFeriado;
	}
	

}
