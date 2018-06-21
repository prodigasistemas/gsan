package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AtendimentoRelacaoTipo implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static Integer ABRIR_REGISTRAR = 1;

	public final static Integer ENCERRAR = 3;

	private Integer id;

	private String descricao;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	public AtendimentoRelacaoTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public AtendimentoRelacaoTipo() {
	}
	
	public AtendimentoRelacaoTipo(Integer id) {
		this.id = id;
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
