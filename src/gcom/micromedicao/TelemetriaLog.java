package gcom.micromedicao;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class TelemetriaLog implements Serializable {

	private static final long serialVersionUID = 1L;
    
	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String conteudoStringEnviada;

    /** persistent field */
    private Date recepcao;
    
    /** persistent field */
    private Date ultimaAlteracao;
    
    private TelemetriaRetMot telemetriaRetMot;

	
    public TelemetriaLog() {}


	public TelemetriaLog(Integer id, String conteudoStringEnviada, Date recepcao, Date ultimaAlteracao, TelemetriaRetMot telemetriaRetMot) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.conteudoStringEnviada = conteudoStringEnviada;
		this.recepcao = recepcao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.telemetriaRetMot = telemetriaRetMot;
	}


	public String getConteudoStringEnviada() {
		return conteudoStringEnviada;
	}


	public void setConteudoStringEnviada(String conteudoStringEnviada) {
		this.conteudoStringEnviada = conteudoStringEnviada;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getRecepcao() {
		return recepcao;
	}


	public void setRecepcao(Date recepcao) {
		this.recepcao = recepcao;
	}


	public TelemetriaRetMot getTelemetriaRetMot() {
		return telemetriaRetMot;
	}


	public void setTelemetriaRetMot(TelemetriaRetMot telemetriaRetMot) {
		this.telemetriaRetMot = telemetriaRetMot;
	}


	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
