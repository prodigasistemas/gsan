package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ProcessoSituacao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int AGENDADO = 4;

	public static final int EM_ESPERA = 3;

	public static final int INICIO_A_COMANDAR = 5;

	public static final int EM_PROCESSAMENTO = 1;

	public static final int CONCLUIDO = 2;

	public static final int CONCLUIDO_COM_ERRO = 6;

	public static final int EXECUCAO_CANCELADA = 7;

	public static final int AGUARDANDO_AUTORIZACAO = 8;

	private Integer id;

	private String descricao;

	private String descricaoAbreviada;

	private short indicadorUso;

	private Date ultimaAlteracao;

	private Set processosIniciados;

	public ProcessoSituacao(Integer id, String descricao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, Set processosIniciados) {
		this.id = id;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.processosIniciados = processosIniciados;
	}

	public ProcessoSituacao() {
	}

	public ProcessoSituacao(Integer id, short indicadorUso, Set processosIniciados) {
		this.id = id;
		this.indicadorUso = indicadorUso;
		this.processosIniciados = processosIniciados;
	}

	public ProcessoSituacao(Integer id) {
		super();
		this.id = id;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Set getProcessosIniciados() {
		return this.processosIniciados;
	}

	public void setProcessosIniciados(Set processosIniciados) {
		this.processosIniciados = processosIniciados;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof ProcessoSituacao))
			return false;
		ProcessoSituacao castOther = (ProcessoSituacao) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
