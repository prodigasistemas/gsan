package gcom.faturamento;

import gcom.batch.Processo;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FaturamentoAtividade extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Short indicadorObrigatoriedadeAtividade;

	/** nullable persistent field */
	private Short indicadorPossibilidadeRepeticaoAtividade;

	/** nullable persistent field */
	private Short indicadorPossibilidadeComandoAtividade;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente;

	private Processo processo;

	/** persistent field */
	private Short ordemRealizacao;

	// --CONSTANTES
	/**
	 * Description of the Field
	 */
	public final static Integer GERAR_ARQUIVO_LEITURA = new Integer(1);

	public final static Integer EFETUAR_LEITURA = new Integer(2);

	public final static Integer REGISTRAR_LEITURA_ANORMALIDADE = new Integer(3);

	public final static Integer GERAR_FISCALIZACAO = new Integer(4);

	public final static Integer TRANSMITIR_ARQUIVO = new Integer(7);

	public final static Integer DISTRIBUIR_CONTAS = new Integer(6);

	public final static Integer FATURAR_GRUPO = new Integer(5);

	public final static Integer SIMULAR_FATURAMENTO = new Integer(8);
	
	public final static Integer PRE_FATURAR_GRUPO = new Integer(0);

	public final static Integer CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS = new Integer(
			9);

	public final static Short ATIVIDADE_POSSIVEL_COMANDO = new Short("1");

	public final static Short ATIVIDADE_POSSIVEL_REPETICAO = new Short("1");

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/** full constructor */
	public FaturamentoAtividade(
			String descricao,
			Short indicadorUso,
			Short indicadorObrigatoriedadeAtividade,
			Short indicadorPossibilidadeRepeticaoAtividade,
			Short indicadorPossibilidadeComandoAtividade,
			Date ultimaAlteracao,
			gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente,
			Short ordemRealizacao) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.indicadorObrigatoriedadeAtividade = indicadorObrigatoriedadeAtividade;
		this.indicadorPossibilidadeRepeticaoAtividade = indicadorPossibilidadeRepeticaoAtividade;
		this.indicadorPossibilidadeComandoAtividade = indicadorPossibilidadeComandoAtividade;
		this.ultimaAlteracao = ultimaAlteracao;
		this.faturamentoAtividadePrecedente = faturamentoAtividadePrecedente;
		this.ordemRealizacao = ordemRealizacao;
	}

	/** default constructor */
	public FaturamentoAtividade() {
	}

	/** minimal constructor */
	public FaturamentoAtividade(
			gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente) {
		this.faturamentoAtividadePrecedente = faturamentoAtividadePrecedente;
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

	public Short getIndicadorObrigatoriedadeAtividade() {
		return this.indicadorObrigatoriedadeAtividade;
	}

	public void setIndicadorObrigatoriedadeAtividade(
			Short indicadorObrigatoriedadeAtividade) {
		this.indicadorObrigatoriedadeAtividade = indicadorObrigatoriedadeAtividade;
	}

	public Short getIndicadorPossibilidadeRepeticaoAtividade() {
		return this.indicadorPossibilidadeRepeticaoAtividade;
	}

	public void setIndicadorPossibilidadeRepeticaoAtividade(
			Short indicadorPossibilidadeRepeticaoAtividade) {
		this.indicadorPossibilidadeRepeticaoAtividade = indicadorPossibilidadeRepeticaoAtividade;
	}

	public Short getIndicadorPossibilidadeComandoAtividade() {
		return this.indicadorPossibilidadeComandoAtividade;
	}

	public void setIndicadorPossibilidadeComandoAtividade(
			Short indicadorPossibilidadeComandoAtividade) {
		this.indicadorPossibilidadeComandoAtividade = indicadorPossibilidadeComandoAtividade;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.faturamento.FaturamentoAtividade getFaturamentoAtividadePrecedente() {
		return this.faturamentoAtividadePrecedente;
	}

	public void setFaturamentoAtividadePrecedente(
			gcom.faturamento.FaturamentoAtividade faturamentoAtividadePrecedente) {
		this.faturamentoAtividadePrecedente = faturamentoAtividadePrecedente;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Returns the ordemRealizacao.
	 */
	public Short getOrdemRealizacao() {
		return ordemRealizacao;
	}

	/**
	 * @param ordemRealizacao
	 *            The ordemRealizacao to set.
	 */
	public void setOrdemRealizacao(Short ordemRealizacao) {
		this.ordemRealizacao = ordemRealizacao;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

}
