package gcom.batch;

import gcom.seguranca.acesso.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ProcessoIniciado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date dataHoraAgendamento;

	/** nullable persistent field */
	private Date dataHoraInicio;

	/** nullable persistent field */
	private Date dataHoraTermino;

	/** nullable persistent field */
	private Date dataHoraComando;

	/** persistent field */
	private gcom.batch.Processo processo;

	/** persistent field */
	private Usuario usuario;

	/** persistent field */
	private gcom.batch.ProcessoIniciado processoIniciadoPrecedente;

	/** persistent field */
	private gcom.batch.ProcessoSituacao processoSituacao;

	private Integer codigoGrupoProcesso;

	/** persistent field */
	private Set funcionalidadesIniciadas;

	/** full constructor */
	public ProcessoIniciado(Date dataHoraAgendamento, Date dataHoraInicio,
			Date dataHoraTermino, Date dataHoraComando,
			gcom.batch.Processo processo, Usuario usuario,
			gcom.batch.ProcessoIniciado processoIniciado,
			gcom.batch.ProcessoSituacao processoSituacao,
			Set funcionalidadesIniciadas) {
		this.dataHoraAgendamento = dataHoraAgendamento;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
		this.dataHoraComando = dataHoraComando;
		this.processo = processo;
		this.usuario = usuario;
		this.processoIniciadoPrecedente = processoIniciado;
		this.processoSituacao = processoSituacao;
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	/** default constructor */
	public ProcessoIniciado() {
	}

	/** minimal constructor */
	public ProcessoIniciado(gcom.batch.Processo processo, Usuario usuario,
			gcom.batch.ProcessoIniciado processoIniciado,
			gcom.batch.ProcessoSituacao processoSituacao,
			Set funcionalidadesIniciadas) {
		this.processo = processo;
		this.usuario = usuario;
		this.processoIniciadoPrecedente = processoIniciado;
		this.processoSituacao = processoSituacao;
		this.funcionalidadesIniciadas = funcionalidadesIniciadas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraAgendamento() {
		return this.dataHoraAgendamento;
	}

	public void setDataHoraAgendamento(Date dataHoraAgendamento) {
		this.dataHoraAgendamento = dataHoraAgendamento;
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

	public Date getDataHoraComando() {
		return this.dataHoraComando;
	}

	public void setDataHoraComando(Date dataHoraComando) {
		this.dataHoraComando = dataHoraComando;
	}

	public gcom.batch.Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(gcom.batch.Processo processo) {
		this.processo = processo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public gcom.batch.ProcessoIniciado getProcessoIniciadoPrecedente() {
		return this.processoIniciadoPrecedente;
	}

	public void setProcessoIniciadoPrecedente(
			gcom.batch.ProcessoIniciado processoIniciado) {
		this.processoIniciadoPrecedente = processoIniciado;
	}

	public gcom.batch.ProcessoSituacao getProcessoSituacao() {
		return this.processoSituacao;
	}

	public void setProcessoSituacao(gcom.batch.ProcessoSituacao processoSituacao) {
		this.processoSituacao = processoSituacao;
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

	/**
	 * @return Retorna o campo codigoProcessoIniciado.
	 */
	public Integer getCodigoGrupoProcesso() {
		return codigoGrupoProcesso;
	}

	/**
	 * @param codigoProcessoIniciado
	 *            O codigoProcessoIniciado a ser setado.
	 */
	public void setCodigoGrupoProcesso(Integer codigoProcessoIniciado) {
		this.codigoGrupoProcesso = codigoProcessoIniciado;
	}

}
