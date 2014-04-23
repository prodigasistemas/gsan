package gcom.cadastro.localidade.bean;

import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;

import java.io.Serializable;

public class IntegracaoQuadraFaceHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** nullable persistent field */
	private Short indicadorRedeAgua;

	/** nullable persistent field */
	private Short indicadorRedeEsgoto;
	
	/** persistent field */
	private DistritoOperacional distritoOperacional;

	/** persistent field */
	private Bacia bacia;
	
	public IntegracaoQuadraFaceHelper(){}

	public Bacia getBacia() {
		return bacia;
	}

	public void setBacia(Bacia bacia) {
		this.bacia = bacia;
	}

	public DistritoOperacional getDistritoOperacional() {
		return distritoOperacional;
	}

	public void setDistritoOperacional(DistritoOperacional distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}

	public Short getIndicadorRedeAgua() {
		return indicadorRedeAgua;
	}

	public void setIndicadorRedeAgua(Short indicadorRedeAgua) {
		this.indicadorRedeAgua = indicadorRedeAgua;
	}

	public Short getIndicadorRedeEsgoto() {
		return indicadorRedeEsgoto;
	}

	public void setIndicadorRedeEsgoto(Short indicadorRedeEsgoto) {
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
	}

}
