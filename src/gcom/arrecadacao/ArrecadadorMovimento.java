package gcom.arrecadacao;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ArrecadadorMovimento implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date dataGeracao;

	/** nullable persistent field */
	private Integer numeroSequencialArquivo;

	/** nullable persistent field */
	private String codigoConvenio;

	/** nullable persistent field */
	private String nomeEmpresa;

	/** nullable persistent field */
	private Short codigoBanco;

	/** nullable persistent field */
	private Integer numeroVersaoLayout;

	/** nullable persistent field */
	private Short codigoRemessa;

	/** nullable persistent field */
	private String nomeBanco;

	/** nullable persistent field */
	private String descricaoIdentificacaoServico;

	/** nullable persistent field */
	private Integer numeroRegistrosMovimento;

	/** nullable persistent field */
	private BigDecimal valorTotalMovimento;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	private Set arrecadadorMovimentoItens;
	
	private Set avisoBancarios;

	public Set getAvisoBancarios() {
		return avisoBancarios;
	}

	public void setAvisoBancarios(Set avisoBancarios) {
		this.avisoBancarios = avisoBancarios;
	}

	/** full constructor */
	public ArrecadadorMovimento(Date dataGeracao,
			Integer numeroSequencialArquivo, String codigoConvenio,
			String nomeEmpresa, Short codigoBanco, Integer numeroVersaoLayout,
			Short codigoRemessa, String nomeBanco,
			String descricaoIdentificacaoServico,
			Integer numeroRegistrosMovimento, BigDecimal valorTotalMovimento,
			Date ultimaAlteracao) {
		this.dataGeracao = dataGeracao;
		this.numeroSequencialArquivo = numeroSequencialArquivo;
		this.codigoConvenio = codigoConvenio;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoBanco = codigoBanco;
		this.numeroVersaoLayout = numeroVersaoLayout;
		this.codigoRemessa = codigoRemessa;
		this.nomeBanco = nomeBanco;
		this.descricaoIdentificacaoServico = descricaoIdentificacaoServico;
		this.numeroRegistrosMovimento = numeroRegistrosMovimento;
		this.valorTotalMovimento = valorTotalMovimento;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ArrecadadorMovimento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataGeracao() {
		return this.dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = Util.converteStringSemBarraParaDateAnoSimples(dataGeracao);
	}
	
	public Integer getNumeroSequencialArquivo() {
		return this.numeroSequencialArquivo;
	}

	public void setNumeroSequencialArquivo(Integer numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}

	public String getCodigoConvenio() {
		return this.codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getNomeEmpresa() {
		return this.nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Short getCodigoBanco() {
		return this.codigoBanco;
	}

	public void setCodigoBanco(Short codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public Integer getNumeroVersaoLayout() {
		return this.numeroVersaoLayout;
	}

	public void setNumeroVersaoLayout(Integer numeroVersaoLayout) {
		this.numeroVersaoLayout = numeroVersaoLayout;
	}

	public Short getCodigoRemessa() {
		return this.codigoRemessa;
	}

	public void setCodigoRemessa(Short codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}

	public String getNomeBanco() {
		return this.nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getDescricaoIdentificacaoServico() {
		return this.descricaoIdentificacaoServico;
	}

	public void setDescricaoIdentificacaoServico(
			String descricaoIdentificacaoServico) {
		this.descricaoIdentificacaoServico = descricaoIdentificacaoServico;
	}

	public Integer getNumeroRegistrosMovimento() {
		return this.numeroRegistrosMovimento;
	}

	public void setNumeroRegistrosMovimento(Integer numeroRegistrosMovimento) {
		this.numeroRegistrosMovimento = numeroRegistrosMovimento;
	}

	public BigDecimal getValorTotalMovimento() {
		return this.valorTotalMovimento;
	}

	public void setValorTotalMovimento(BigDecimal valorTotalMovimento) {
		this.valorTotalMovimento = valorTotalMovimento;
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

	public Set getArrecadadorMovimentoItens() {
		return arrecadadorMovimentoItens;
	}

	public void setArrecadadorMovimentoItens(Set arrecadadorMovimentoItens) {
		this.arrecadadorMovimentoItens = arrecadadorMovimentoItens;
	}

}
