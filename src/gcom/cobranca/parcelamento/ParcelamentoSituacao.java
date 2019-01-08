package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ParcelamentoSituacao implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static Integer NORMAL = new Integer(1);
	public final static Integer DESFEITO = new Integer(2);
	public final static Integer CONCLUIDO = new Integer(3);
	public final static Integer CANCELADO = new Integer(4);

	private Integer id;

	private String descricao;

	private String descricaoAbreviada;

	private Date ultimaAlteracao;

	public ParcelamentoSituacao() {
	}

	public ParcelamentoSituacao(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
}
