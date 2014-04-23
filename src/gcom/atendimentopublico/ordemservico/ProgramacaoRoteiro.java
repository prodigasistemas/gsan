package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ProgramacaoRoteiro extends ObjetoGcom  {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private Date dataRoteiro;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;

	/** full constructor */
	public ProgramacaoRoteiro(Date dataRoteiro, Date ultimaAlteracao,
			UnidadeOrganizacional unidadeOrganizacional) {
		this.dataRoteiro = dataRoteiro;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	/** default constructor */
	public ProgramacaoRoteiro() {
	}

	/** minimal constructor */
	public ProgramacaoRoteiro(Date dataRoteiro, Date ultimaAlteracao) {
		this.dataRoteiro = dataRoteiro;
		this.ultimaAlteracao = ultimaAlteracao;

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataRoteiro() {
		return this.dataRoteiro;
	}

	public void setDataRoteiro(Date dataRoteiro) {
		this.dataRoteiro = dataRoteiro;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	
}
