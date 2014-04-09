package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

public class OrdemServicoUnidadeHistorico {

	/** identifier field */
	private Integer id;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Usuario usuario;

	/** persistent field */
	private OrdemServicoHistorico ordemServico;

	/** persistent field */
	private UnidadeOrganizacional unidadeOrganizacional;

	/** persistent field */
	private AtendimentoRelacaoTipo atendimentoRelacaoTipo;
	
	public OrdemServicoUnidadeHistorico(){}

	public AtendimentoRelacaoTipo getAtendimentoRelacaoTipo() {
		return atendimentoRelacaoTipo;
	}

	public void setAtendimentoRelacaoTipo(
			AtendimentoRelacaoTipo atendimentoRelacaoTipo) {
		this.atendimentoRelacaoTipo = atendimentoRelacaoTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServicoHistorico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(
			OrdemServicoHistorico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
