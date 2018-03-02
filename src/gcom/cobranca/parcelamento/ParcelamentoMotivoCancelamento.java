package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.util.Date;

public class ParcelamentoMotivoCancelamento implements Serializable {

	private static final long serialVersionUID = 3164556127789442533L;

	private Integer id;

	private String descricao;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	public ParcelamentoMotivoCancelamento() {
		super();
	}

	public ParcelamentoMotivoCancelamento(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}