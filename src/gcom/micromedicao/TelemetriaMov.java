package gcom.micromedicao;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class TelemetriaMov implements Serializable {

	private static final long serialVersionUID = 1L;
    
	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer quantidadeConsumidores;

    /** persistent field */
    private Date envio;
    
    /** persistent field */
    private Integer codigoEmpresa;
    
    /** persistent field */
    private Short indicadorConsistenciaLeit;
    
    /** persistent field */
    private Date ultimaAlteracao;
    
    private TelemetriaLog telemetriaLog;

	
    public TelemetriaMov() {}


	public TelemetriaMov(Integer id, Integer quantidadeConsumidores, Date envio, Integer codigoEmpresa, Short indicadorConsistenciaLeit, Date ultimaAlteracao, TelemetriaLog telemetriaLog) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.quantidadeConsumidores = quantidadeConsumidores;
		this.envio = envio;
		this.codigoEmpresa = codigoEmpresa;
		this.indicadorConsistenciaLeit = indicadorConsistenciaLeit;
		this.ultimaAlteracao = ultimaAlteracao;
		this.telemetriaLog = telemetriaLog;
	}


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


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Short getIndicadorConsistenciaLeit() {
		return indicadorConsistenciaLeit;
	}


	public void setIndicadorConsistenciaLeit(Short indicadorConsistenciaLeit) {
		this.indicadorConsistenciaLeit = indicadorConsistenciaLeit;
	}


	public Integer getQuantidadeConsumidores() {
		return quantidadeConsumidores;
	}


	public void setQuantidadeConsumidores(Integer quantidadeConsumidores) {
		this.quantidadeConsumidores = quantidadeConsumidores;
	}


	public TelemetriaLog getTelemetriaLog() {
		return telemetriaLog;
	}


	public void setTelemetriaLog(TelemetriaLog telemetriaLog) {
		this.telemetriaLog = telemetriaLog;
	}


	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
