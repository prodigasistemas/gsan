package gcom.seguranca.transacao;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

public class AlteracaoTipo extends TabelaAuxiliarAbreviada implements Serializable {

	private static final long serialVersionUID = 1L;

	public static Integer ALTERACAO = new Integer(1);
	public static Integer INCLUSAO = new Integer(2);
	public static Integer EXCLUSAO = new Integer(3);

	private Integer id;

	private String descricao;

	private String descricaoAbreviada;

	private Date ultimaAlteracao;

	public AlteracaoTipo(Integer id) {
		this.id = id;
	}

	public AlteracaoTipo(Integer id, String descricao, String descricaoAbreviada, Date ultimaAlteracao) {
		this.id = id;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public AlteracaoTipo() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
