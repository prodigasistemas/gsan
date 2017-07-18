package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FuncionalidadeSituacao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer EM_ESPERA = 3;

	public static final int EM_PROCESSAMENTO = 1;

	public static final int CONCLUIDA = 2;

	public static final int CONCLUIDA_COM_ERRO = 4;

	public static final int AGENDADA = 5;

	public static final Integer EXECUCAO_CANCELADA = 6;

	public static final Integer AGUARDANDO_AUTORIZACAO = 7;

	private Integer id;

	private String descricaoOperacaoSituacao;

	private String descricaoAbreviada;

	private short indicadorUso;

	private Date ultimaAlteracao;

	private Set funcionalidadesIniciadas;

	public FuncionalidadeSituacao(String descricaoOperacaoSituacao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, Set funcionalidadesIniciadas) {
		this.descricaoOperacaoSituacao = descricaoOperacaoSituacao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	public FuncionalidadeSituacao() {
	}

	public FuncionalidadeSituacao(short indicadorUso, Set funcionalidadesIniciadas) {
		this.indicadorUso = indicadorUso;
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	public FuncionalidadeSituacao(Integer id) {
		super();
		this.id = id;
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

	public Set getFuncionalidadesIniciadas() {
		return this.funcionalidadesIniciadas;
	}

	public void setFuncionalidadesIniciadas(Set funcionalidadesIniciadas) {
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
