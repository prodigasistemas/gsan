package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.funcionario.Funcionario;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OsExecucaoEquipeComponentes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private short indicadorResponsavel;

	/** nullable persistent field */
	private String nomeComponente;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Funcionario funcionario;
	
	/** persistent field */
	private OsExecucaoEquipe osExecucaoEquipe;

	/** full constructor */
	public OsExecucaoEquipeComponentes(short indicadorResponsavel,
			String nomeComponente, Date ultimaAlteracao,
			Funcionario funcionario) {
		this.indicadorResponsavel = indicadorResponsavel;
		this.nomeComponente = nomeComponente;
		this.ultimaAlteracao = ultimaAlteracao;
		this.funcionario = funcionario;
	}

	/** default constructor */
	public OsExecucaoEquipeComponentes() {
	}

	/** minimal constructor */
	public OsExecucaoEquipeComponentes(short indicadorResponsavel,
			Date ultimaAlteracao, Funcionario funcionario) {
		this.indicadorResponsavel = indicadorResponsavel;
		this.ultimaAlteracao = ultimaAlteracao;
		this.funcionario = funcionario;
	}
	
	

	public OsExecucaoEquipe getOsExecucaoEquipe() {
		return osExecucaoEquipe;
	}

	public void setOsExecucaoEquipe(OsExecucaoEquipe osExecucaoEquipe) {
		this.osExecucaoEquipe = osExecucaoEquipe;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorResponsavel() {
		return this.indicadorResponsavel;
	}

	public void setIndicadorResponsavel(short indicadorResponsavel) {
		this.indicadorResponsavel = indicadorResponsavel;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public void setNomeComponente(String nomeComponente) {
		this.nomeComponente = nomeComponente;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
