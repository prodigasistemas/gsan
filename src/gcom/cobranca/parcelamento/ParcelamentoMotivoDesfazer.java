package gcom.cobranca.parcelamento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ParcelamentoMotivoDesfazer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String descricaoParcelamentoMotivoDesfazer;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	public final static Integer ENTRADA_NAO_PAGA = new Integer(4);

	public ParcelamentoMotivoDesfazer(String descricaoParcelamentoMotivoDesfazer, Short indicadorUso, Date ultimaAlteracao) {
		this.descricaoParcelamentoMotivoDesfazer = descricaoParcelamentoMotivoDesfazer;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ParcelamentoMotivoDesfazer() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoParcelamentoMotivoDesfazer() {
		return this.descricaoParcelamentoMotivoDesfazer;
	}

	public void setDescricaoParcelamentoMotivoDesfazer(String descricaoParcelamentoMotivoDesfazer) {
		this.descricaoParcelamentoMotivoDesfazer = descricaoParcelamentoMotivoDesfazer;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
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