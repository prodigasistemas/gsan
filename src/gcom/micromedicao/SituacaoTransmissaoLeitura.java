package gcom.micromedicao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SituacaoTransmissaoLeitura implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static Integer DISPONIVEL = new Integer(1);
	public final static Integer LIBERADO = new Integer(2);
	public final static Integer EM_CAMPO = new Integer(3);
	public final static Integer TRANSMITIDO = new Integer(4);
	public final static Integer FINALIZADO_NAO_TRANSMITIDO = new Integer(5);
	public final static Integer FINALIZADO_INCOMPLETO = new Integer(6);
	public final static Integer FINALIZADO_USUARIO = new Integer(7);
	public final static Integer FINALIZADO_POR_DIGITACAO = new Integer(8);
	public final static Integer INFORMAR_MOTIVO_FINALIZACAO = new Integer(9);

	private Integer id;

	private String descricaoSituacao;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	public SituacaoTransmissaoLeitura(Integer id, String descricaoSituacao, Short indicadorUso, Date ultimaAlteracao) {
		this.id = id;
		this.descricaoSituacao = descricaoSituacao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public SituacaoTransmissaoLeitura(Integer id) {
		this.id = id;
	}

	public SituacaoTransmissaoLeitura() {
	}

	public SituacaoTransmissaoLeitura(Integer id, Date ultimaAlteracao) {
		this.id = id;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoSituacao() {
		return this.descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
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
