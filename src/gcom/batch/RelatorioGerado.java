package gcom.batch;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class RelatorioGerado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private int numeroPaginas;

	/** nullable persistent field */
	private byte[] arquivoRelatorio;

	/** persistent field */
	private gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada;

	/** persistent field */
	private gcom.batch.Relatorio relatorio;

	/** full constructor */
	public RelatorioGerado(Date ultimaAlteracao, int numeroPaginas,
			byte[] arquivoRelatorio,
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada,
			gcom.batch.Relatorio relatorio) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.numeroPaginas = numeroPaginas;
		this.arquivoRelatorio = arquivoRelatorio;
		this.funcionalidadeIniciada = funcionalidadeIniciada;
		this.relatorio = relatorio;
	}

	/** default constructor */
	public RelatorioGerado() {
	}

	/** minimal constructor */
	public RelatorioGerado(int numeroPaginas,
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada,
			gcom.batch.Relatorio relatorio) {
		this.numeroPaginas = numeroPaginas;
		this.funcionalidadeIniciada = funcionalidadeIniciada;
		this.relatorio = relatorio;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getNumeroPaginas() {
		return this.numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public byte[] getArquivoRelatorio() {
		return this.arquivoRelatorio;
	}

	public void setArquivoRelatorio(byte[] arquivoRelatorio) {
		this.arquivoRelatorio = arquivoRelatorio;
	}

	public gcom.batch.FuncionalidadeIniciada getFuncionalidadeIniciada() {
		return this.funcionalidadeIniciada;
	}

	public void setFuncionalidadeIniciada(
			gcom.batch.FuncionalidadeIniciada funcionalidadeIniciada) {
		this.funcionalidadeIniciada = funcionalidadeIniciada;
	}

	public gcom.batch.Relatorio getRelatorio() {
		return this.relatorio;
	}

	public void setRelatorio(gcom.batch.Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
