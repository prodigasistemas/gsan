package gcom.micromedicao.bean;

import gcom.micromedicao.TelemetriaRetMot;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Raphael Rossiter
 * @data 03/09/2010
 */
public class TelemetriaInformacoesGeraisHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TelemetriaRetMot telemetriaRetMot;
	
	private Integer quantidadeConsumidores;
	
	private Date envio;
	
	private Integer codigoEmpresa;
	
	public TelemetriaInformacoesGeraisHelper(){}

	public Integer getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public Date getEnvio() {
		return envio;
	}

	public void setEnvio(Date envio) {
		this.envio = envio;
	}

	public Integer getQuantidadeConsumidores() {
		return quantidadeConsumidores;
	}

	public void setQuantidadeConsumidores(Integer quantidadeConsumidores) {
		this.quantidadeConsumidores = quantidadeConsumidores;
	}

	public TelemetriaRetMot getTelemetriaRetMot() {
		return telemetriaRetMot;
	}

	public void setTelemetriaRetMot(TelemetriaRetMot telemetriaRetMot) {
		this.telemetriaRetMot = telemetriaRetMot;
	}
}
