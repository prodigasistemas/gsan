package gcom.micromedicao.bean;

import gcom.micromedicao.TelemetriaMovReg;
import gcom.micromedicao.TelemetriaRetMot;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Raphael Rossiter
 * @data 03/09/2010
 */
public class TelemetriaDadosLeituraHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TelemetriaRetMot telemetriaRetMot;
	
	private Collection<TelemetriaMovReg> colecaoTelemetriaMovReg;
	
	public TelemetriaDadosLeituraHelper(){}

	public Collection<TelemetriaMovReg> getColecaoTelemetriaMovReg() {
		return colecaoTelemetriaMovReg;
	}

	public void setColecaoTelemetriaMovReg(
			Collection<TelemetriaMovReg> colecaoTelemetriaMovReg) {
		this.colecaoTelemetriaMovReg = colecaoTelemetriaMovReg;
	}

	public TelemetriaRetMot getTelemetriaRetMot() {
		return telemetriaRetMot;
	}

	public void setTelemetriaRetMot(TelemetriaRetMot telemetriaRetMot) {
		this.telemetriaRetMot = telemetriaRetMot;
	}
}
