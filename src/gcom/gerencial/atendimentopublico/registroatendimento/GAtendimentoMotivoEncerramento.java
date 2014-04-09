package gcom.gerencial.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Ivan Sergio */
public class GAtendimentoMotivoEncerramento implements Serializable {
	private static final long serialVersionUID = 1L;
    
	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private short indicadorUso;
	private Date ultimaAlteracao;
	private short indicadorExecucao;
	private short indicadorDuplicidade;
    private Set unResumoRegistroAtendimentos;

    public GAtendimentoMotivoEncerramento(Integer id, String descricao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, short indicadorExecucao, short indicadorDuplicidade, Set unResumoRegistroAtendimentos) {
		this.id = id;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExecucao = indicadorExecucao;
		this.indicadorDuplicidade = indicadorDuplicidade;
		this.unResumoRegistroAtendimentos = unResumoRegistroAtendimentos;
	}
    
    public GAtendimentoMotivoEncerramento() {}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo descricaoAbreviada.
	 */
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	/**
	 * @param descricaoAbreviada O descricaoAbreviada a ser setado.
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorDuplicidade.
	 */
	public short getIndicadorDuplicidade() {
		return indicadorDuplicidade;
	}

	/**
	 * @param indicadorDuplicidade O indicadorDuplicidade a ser setado.
	 */
	public void setIndicadorDuplicidade(short indicadorDuplicidade) {
		this.indicadorDuplicidade = indicadorDuplicidade;
	}

	/**
	 * @return Retorna o campo indicadorExecucao.
	 */
	public short getIndicadorExecucao() {
		return indicadorExecucao;
	}

	/**
	 * @param indicadorExecucao O indicadorExecucao a ser setado.
	 */
	public void setIndicadorExecucao(short indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo unResumoRegistroAtendimentos.
	 */
	public Set getUnResumoRegistroAtendimentos() {
		return unResumoRegistroAtendimentos;
	}

	/**
	 * @param unResumoRegistroAtendimentos O unResumoRegistroAtendimentos a ser setado.
	 */
	public void setUnResumoRegistroAtendimentos(Set unResumoRegistroAtendimentos) {
		this.unResumoRegistroAtendimentos = unResumoRegistroAtendimentos;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
}
