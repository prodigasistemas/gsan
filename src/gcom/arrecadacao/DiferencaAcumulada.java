package gcom.arrecadacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DiferencaAcumulada implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Arrecadador arrecadador;
	
	private ArrecadacaoForma arrecadacaoForma;
	
	private int anoMesReferenciaArrecadacao;
	
	private BigDecimal valorDiferencaAcumulada;
	
	private Date ultimaAlteracao;
	
	public DiferencaAcumulada() { }

	public int getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(int anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	public Arrecadador getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorDiferencaAcumulada() {
		return valorDiferencaAcumulada;
	}

	public void setValorDiferencaAcumulada(BigDecimal valorDiferencaAcumulada) {
		this.valorDiferencaAcumulada = valorDiferencaAcumulada;
	}

}
