package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;

public class InformarAcertoDocumentosNaoAceitosPagamentoHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date periodoPagamentoInicial;
	
	private Date periodoPagamentoFinal;
	
	private Integer idAvisoBancario;
	
	private Integer idFormaArrecadacao;
	
	private Integer idArrecadador;
	
	private Integer idArrecadadorMov;
	
	private Integer idClienteFicticio;


	public Integer getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(Integer idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public Integer getIdArrecadadorMov() {
		return idArrecadadorMov;
	}

	public void setIdArrecadadorMov(Integer idArrecadadorMov) {
		this.idArrecadadorMov = idArrecadadorMov;
	}

	public Integer getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(Integer idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	public Integer getIdClienteFicticio() {
		return idClienteFicticio;
	}

	public void setIdClienteFicticio(Integer idClienteFicticio) {
		this.idClienteFicticio = idClienteFicticio;
	}

	public Integer getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(Integer idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	public Date getPeriodoPagamentoFinal() {
		return periodoPagamentoFinal;
	}

	public void setPeriodoPagamentoFinal(Date periodoPagamentoFinal) {
		this.periodoPagamentoFinal = periodoPagamentoFinal;
	}

	public Date getPeriodoPagamentoInicial() {
		return periodoPagamentoInicial;
	}

	public void setPeriodoPagamentoInicial(Date periodoPagamentoInicial) {
		this.periodoPagamentoInicial = periodoPagamentoInicial;
	}

}
