package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UnidadeSituacao implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public static final int EM_PROCESSAMENTO = 1;

	public static final int CONCLUIDA = 2;

	public static final int EM_ESPERA = 3;

	public static final int CONCLUIDA_COM_ERRO = 4;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoOperacaoSituacao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Set unidadesIniciadas;

	/** full constructor */
	public UnidadeSituacao(String descricaoOperacaoSituacao,
			String descricaoAbreviada, short indicadorUso,
			Date ultimaAlteracao, Set unidadesIniciadas) {
		this.descricaoOperacaoSituacao = descricaoOperacaoSituacao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadesIniciadas = unidadesIniciadas;
	}

	/** default constructor */
	public UnidadeSituacao() {
	}

	/** minimal constructor */
	public UnidadeSituacao(short indicadorUso, Set unidadesIniciadas) {
		this.indicadorUso = indicadorUso;
		this.unidadesIniciadas = unidadesIniciadas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoOperacaoSituacao() {
		return this.descricaoOperacaoSituacao;
	}

	public void setDescricaoOperacaoSituacao(String descricaoOperacaoSituacao) {
		this.descricaoOperacaoSituacao = descricaoOperacaoSituacao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Set getUnidadesIniciadas() {
		return this.unidadesIniciadas;
	}

	public void setUnidadesIniciadas(Set unidadesIniciadas) {
		this.unidadesIniciadas = unidadesIniciadas;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
