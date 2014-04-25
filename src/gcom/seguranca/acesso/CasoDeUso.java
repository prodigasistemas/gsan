package gcom.seguranca.acesso;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CasoDeUso implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.seguranca.acesso.CasoDeUsoTipo casoDeUsoTipo;

	/** full constructor */
	public CasoDeUso(String descricao, String descricaoAbreviada,
			Date ultimaAlteracao,
			gcom.seguranca.acesso.CasoDeUsoTipo casoDeUsoTipo) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.casoDeUsoTipo = casoDeUsoTipo;
	}

	/** default constructor */
	public CasoDeUso() {
	}

	/** minimal constructor */
	public CasoDeUso(gcom.seguranca.acesso.CasoDeUsoTipo casoDeUsoTipo) {
		this.casoDeUsoTipo = casoDeUsoTipo;
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

	public gcom.seguranca.acesso.CasoDeUsoTipo getCasoDeUsoTipo() {
		return this.casoDeUsoTipo;
	}

	public void setCasoDeUsoTipo(
			gcom.seguranca.acesso.CasoDeUsoTipo casoDeUsoTipo) {
		this.casoDeUsoTipo = casoDeUsoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
