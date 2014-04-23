package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UnidadeIniciada implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date dataHoraInicio;

	/** nullable persistent field */
	private Date dataHoraTermino;

	/** persistent field */
	private gcom.batch.UnidadeProcessamento unidadeProcessamento;

	/** persistent field */
	private gcom.batch.UnidadeSituacao unidadeSituacao;

	/** persistent field */
	private gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada;

	private Integer codigoRealUnidadeProcessamento;

	/** full constructor */
	public UnidadeIniciada(Date dataHoraInicio, Date dataHoraTermino,
			gcom.batch.UnidadeProcessamento unidadeProcessamento,
			gcom.batch.UnidadeSituacao unidadeSituacao,
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada) {
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
		this.unidadeProcessamento = unidadeProcessamento;
		this.unidadeSituacao = unidadeSituacao;
		this.funcionalidadeIniciada = funcionalidadeIniciada;
	}

	/** default constructor */
	public UnidadeIniciada() {
	}

	/** minimal constructor */
	public UnidadeIniciada(
			gcom.batch.UnidadeProcessamento unidadeProcessamento,
			gcom.batch.UnidadeSituacao unidadeSituacao,
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada) {
		this.unidadeProcessamento = unidadeProcessamento;
		this.unidadeSituacao = unidadeSituacao;
		this.funcionalidadeIniciada = funcionalidadeIniciada;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraInicio() {
		return this.dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraTermino() {
		return this.dataHoraTermino;
	}

	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

	public gcom.batch.UnidadeProcessamento getUnidadeProcessamento() {
		return this.unidadeProcessamento;
	}

	public void setUnidadeProcessamento(
			gcom.batch.UnidadeProcessamento unidadeProcessamento) {
		this.unidadeProcessamento = unidadeProcessamento;
	}

	public gcom.batch.UnidadeSituacao getUnidadeSituacao() {
		return this.unidadeSituacao;
	}

	public void setUnidadeSituacao(gcom.batch.UnidadeSituacao unidadeSituacao) {
		this.unidadeSituacao = unidadeSituacao;
	}

	public gcom.batch.FuncionalidadeIniciada getFuncionalidadeIniciada() {
		return this.funcionalidadeIniciada;
	}

	public void setFuncionalidadeIniciada(
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada) {
		this.funcionalidadeIniciada = funcionalidadeIniciada;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo codigoRealUnidadeProcessamento.
	 */
	public Integer getCodigoRealUnidadeProcessamento() {
		return codigoRealUnidadeProcessamento;
	}

	/**
	 * @param codigoRealUnidadeProcessamento
	 *            O codigoRealUnidadeProcessamento a ser setado.
	 */
	public void setCodigoRealUnidadeProcessamento(
			Integer codigoRealUnidadeProcessamento) {
		this.codigoRealUnidadeProcessamento = codigoRealUnidadeProcessamento;
	}

}
