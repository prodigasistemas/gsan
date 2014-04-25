package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OrdemServicoUnidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Usuario usuario;

	/** persistent field */
	private gcom.atendimentopublico.ordemservico.OrdemServico ordemServico;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;

	/** persistent field */
	private AtendimentoRelacaoTipo atendimentoRelacaoTipo;

	/** full constructor */
	public OrdemServicoUnidade(Date ultimaAlteracao, Usuario usuario,
			gcom.atendimentopublico.ordemservico.OrdemServico ordemServico,
			UnidadeOrganizacional unidadeOrganizacional,
			AtendimentoRelacaoTipo atendimentoRelacaoTipo) {
		this.ultimaAlteracao = ultimaAlteracao;
		this.usuario = usuario;
		this.ordemServico = ordemServico;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.atendimentoRelacaoTipo = atendimentoRelacaoTipo;
	}

	/** default constructor */
	public OrdemServicoUnidade() {
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public gcom.atendimentopublico.ordemservico.OrdemServico getOrdemServico() {
		return this.ordemServico;
	}

	public void setOrdemServico(
			gcom.atendimentopublico.ordemservico.OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return this.unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(
			UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public AtendimentoRelacaoTipo getAtendimentoRelacaoTipo() {
		return this.atendimentoRelacaoTipo;
	}

	public void setAtendimentoRelacaoTipo(
			AtendimentoRelacaoTipo atendimentoRelacaoTipo) {
		this.atendimentoRelacaoTipo = atendimentoRelacaoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof OrdemServicoUnidade)) {
			return false;
		}
		OrdemServicoUnidade castOther = (OrdemServicoUnidade) other;

		return ((this.getId().equals(castOther.getId()))
				&& (this.getUltimaAlteracao().equals(castOther
						.getUltimaAlteracao()))
				&& (this.getUsuario().getId().equals(castOther.getUsuario()
						.getId()))
				&& (this.getUnidadeOrganizacional().getId().equals(castOther
						.getUnidadeOrganizacional().getId()))
				&& (this.getAtendimentoRelacaoTipo().getId().equals(castOther
						.getAtendimentoRelacaoTipo().getId())) && (this
				.getOrdemServico().getId().equals(castOther.getOrdemServico()
				.getId())));
	}

}
