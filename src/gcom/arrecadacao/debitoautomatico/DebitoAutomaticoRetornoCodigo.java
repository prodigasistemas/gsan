package gcom.arrecadacao.debitoautomatico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DebitoAutomaticoRetornoCodigo implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DEBITADO = new String("00");
	public static final String DEBITO_EFETUADO_DATA_DIFERENTE_DA_DATA_INFORMADA = new String("31");

	private Integer id;

	private String descricaoDebitoAutomaticoRetornoCodigo;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	private String codigoBanco;

	public DebitoAutomaticoRetornoCodigo(
			String descricaoDebitoAutomaticoRetornoCodigo, 
			Short indicadorUso,
			Date ultimaAlteracao, 
			String codigoBanco) {
		
		this.descricaoDebitoAutomaticoRetornoCodigo = descricaoDebitoAutomaticoRetornoCodigo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.codigoBanco = codigoBanco;
	}

	public DebitoAutomaticoRetornoCodigo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoDebitoAutomaticoRetornoCodigo() {
		return descricaoDebitoAutomaticoRetornoCodigo;
	}

	public void setDescricaoDebitoAutomaticoRetornoCodigo(String descricaoDebitoAutomaticoRetornoCodigo) {
		this.descricaoDebitoAutomaticoRetornoCodigo = descricaoDebitoAutomaticoRetornoCodigo;
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

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
}
