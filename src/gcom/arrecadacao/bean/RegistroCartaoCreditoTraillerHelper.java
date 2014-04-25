package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * Trailler do TXT do retorno do cartão de crédito
 * 
 * @author Raphael Rossiter
 * @date 28/01/2010
 */
public class RegistroCartaoCreditoTraillerHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private Integer totalRegistros;
	
	//CONSTANTE TRAILLER
	public final static Short CODIGO_TRAILLER = new Short("9");
	
	public RegistroCartaoCreditoTraillerHelper(){}

	public Short getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
}
