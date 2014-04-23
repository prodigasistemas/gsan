package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UnidadeProcessamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int ROTA = 1;

	public static final int CONTA = 2;

	public static final int FATURAMENTO_ATIVIDADE_CRONOGRAMA_ROTA = 3;

	public static final int RELATORIO = 4;

	public static final int FUNCIONALIDADE = 5;

	public static final int LOCALIDADE = 6;

	public static final int COBRANCA_GRUPO_CRONOGRAMA_MES = 7;

	public static final int SETOR_COMERCIAL = 8;

	public static final int HIDROMETRO_MARCA = 9;

	public static final int COB_ACAO_ATIV_CRONOG = 10;

	public static final int COB_ACAO_ATIV_COMAND = 11;
	
	public static final int RA_ENCERRAMENTO_COM = 12;
	
	public static final int NEGATIVADOR_MOVIMENTO_REG = 14;
	
	public static final int COMANDO_EMPRESA_COBRANCA_CONTA = 15;
	
	public static final int COMANDO_EMPRESA_COBRANCA_CONTA_EXTENSAO = 16;
	
	public static final int UNIDADE_NEGOCIO = 17;
	
	public static final int HIDROMETRO = 18;
	
	public static final int EMPRESA = 19;
	
	public static final int QUADRA = 20;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoUnidadeProcessamento;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private short indicadorUso;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** persistent field */
	private Set unidadesIniciadas;

	/** persistent field */
	private Set processosFuncionalidades;

	/** full constructor */
	public UnidadeProcessamento(String descricaoUnidadeProcessamento,
			Date ultimaAlteracao, short indicadorUso,
			String descricaoAbreviada, Set unidadesIniciadas,
			Set processosFuncionalidades) {
		this.descricaoUnidadeProcessamento = descricaoUnidadeProcessamento;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorUso = indicadorUso;
		this.descricaoAbreviada = descricaoAbreviada;
		this.unidadesIniciadas = unidadesIniciadas;
		this.processosFuncionalidades = processosFuncionalidades;
	}

	/** default constructor */
	public UnidadeProcessamento() {
	}

	/** minimal constructor */
	public UnidadeProcessamento(short indicadorUso, Set unidadesIniciadas,
			Set processosFuncionalidades) {
		this.indicadorUso = indicadorUso;
		this.unidadesIniciadas = unidadesIniciadas;
		this.processosFuncionalidades = processosFuncionalidades;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoUnidadeProcessamento() {
		return this.descricaoUnidadeProcessamento;
	}

	public void setDescricaoUnidadeProcessamento(
			String descricaoUnidadeProcessamento) {
		this.descricaoUnidadeProcessamento = descricaoUnidadeProcessamento;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Set getUnidadesIniciadas() {
		return this.unidadesIniciadas;
	}

	public void setUnidadesIniciadas(Set unidadesIniciadas) {
		this.unidadesIniciadas = unidadesIniciadas;
	}

	public Set getProcessosFuncionalidades() {
		return this.processosFuncionalidades;
	}

	public void setProcessosFuncionalidades(Set processosFuncionalidades) {
		this.processosFuncionalidades = processosFuncionalidades;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
