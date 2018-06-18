package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ProcessoTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final int MENSAL = 3;
	public static final int EVENTUAL = 4;
	public static final int FATURAMENTO_COMANDADO = 1;
	public static final int COBRANCA_COMANDADO = 2;
	public static final int RELATORIO = 5;
    public static final int SEMANAL = 6;
    public static final int DIARIO = 7;

	private Integer id;
	private String descricao;
	private short indicadorUso;
	private Date ultimaAlteracao;
	@SuppressWarnings("rawtypes")
	private Set processos;

	public ProcessoTipo(Integer id) {
		this.id = id;
	}
			
	@SuppressWarnings("rawtypes")
	public ProcessoTipo(Integer id, String descricao, short indicadorUso, Date ultimaAlteracao, Set processos) {
		this.id = id;
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.processos = processos;
	}

	public ProcessoTipo() {
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

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	@SuppressWarnings("rawtypes")
	public Set getProcessos() {
		return processos;
	}

	@SuppressWarnings("rawtypes")
	public void setProcessos(Set processos) {
		this.processos = processos;
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

}
