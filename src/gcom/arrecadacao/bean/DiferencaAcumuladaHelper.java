package gcom.arrecadacao.bean;

import java.math.BigDecimal;

public class DiferencaAcumuladaHelper {
	
	private Integer anoMesArrecadacao;
	private Integer idArrecadador;
	private Integer idArrecadacaoForma;
	private BigDecimal diferencaAcumulada;
	
	public DiferencaAcumuladaHelper() { }

	public Integer getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}

	public void setAnoMesArrecadacao(Integer anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

	public BigDecimal getDiferencaAcumulada() {
		return diferencaAcumulada;
	}

	public void setDiferencaAcumulada(BigDecimal diferencaAcumulada) {
		this.diferencaAcumulada = diferencaAcumulada;
	}

	public Integer getIdArrecadacaoForma() {
		return idArrecadacaoForma;
	}

	public void setIdArrecadacaoForma(Integer idArrecadacaoForma) {
		this.idArrecadacaoForma = idArrecadacaoForma;
	}

	public Integer getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(Integer idArrecadador) {
		this.idArrecadador = idArrecadador;
	}
	
}
