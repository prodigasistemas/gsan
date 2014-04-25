package gcom.seguranca.acesso;

import gcom.cadastro.empresa.Empresa;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class PesquisarOperacao implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String nomeOperacao;

	/** nullable persistent field */
	private String tipoOperacao;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** persistent field */
	private gcom.seguranca.acesso.PesquisarOperacao pesquisarOperacao;

	private Operacao operacao;
	
	private String descricaoOperacao;

	private String siglaUnidade;

	/* full constructor */
	public PesquisarOperacao(String descricaoOperacao, String siglaUnidade,
			Date ultimaAlteracao,
			gcom.seguranca.acesso.PesquisarOperacao pesquisarOperacao,
			Operacao operacao) {
		this.descricaoOperacao = descricaoOperacao;
		this.siglaUnidade = siglaUnidade;
		this.ultimaAlteracao = ultimaAlteracao;
		this.pesquisarOperacao = pesquisarOperacao;
		this.operacao = operacao;
	}

	public PesquisarOperacao() {
	}

	public PesquisarOperacao(
			gcom.cadastro.unidade.UnidadeOrganizacional unidadeEmpresa, Empresa empresa) {
		//this.pesquisarOperacao = pesquisarOperacao;
		
	}



	public String getDescricaoOperacao() {
		return descricaoOperacao;
	}

	public void setDescricaoOperacao(String descricaoOperacao) {
		this.descricaoOperacao = descricaoOperacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeOperacao() {
		return nomeOperacao;
	}

	public void setNomeOperacao(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public gcom.seguranca.acesso.PesquisarOperacao getPesquisarOperacao() {
		return pesquisarOperacao;
	}

	public void setPesquisarOperacao(
			gcom.seguranca.acesso.PesquisarOperacao pesquisarOperacao) {
		this.pesquisarOperacao = pesquisarOperacao;
	}

	public String getSiglaUnidade() {
		return siglaUnidade;
	}

	public void setSiglaUnidade(String siglaUnidade) {
		this.siglaUnidade = siglaUnidade;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
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
