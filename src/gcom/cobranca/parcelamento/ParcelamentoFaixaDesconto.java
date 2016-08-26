package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.util.Date;

public class ParcelamentoFaixaDesconto implements Serializable {

	private static final long serialVersionUID = 656378329631476730L;

	private long id;

	private int referenciaMinima;

	private int referenciaMaxima;

	private double percentualDesconto;

	private Date dataCriacao;

	private Date ultimaAlteracao;

	public ParcelamentoFaixaDesconto() {
		super();
	}

	public ParcelamentoFaixaDesconto(long id, int referenciaMinima, int referenciaMaxima, double percentualDesconto, Date dataCriacao, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.referenciaMinima = referenciaMinima;
		this.referenciaMaxima = referenciaMaxima;
		this.percentualDesconto = percentualDesconto;
		this.dataCriacao = dataCriacao;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getReferenciaMinima() {
		return referenciaMinima;
	}

	public void setReferenciaMinima(int referenciaMinima) {
		this.referenciaMinima = referenciaMinima;
	}

	public int getReferenciaMaxima() {
		return referenciaMaxima;
	}

	public void setReferenciaMaxima(int referenciaMaxima) {
		this.referenciaMaxima = referenciaMaxima;
	}

	public double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
