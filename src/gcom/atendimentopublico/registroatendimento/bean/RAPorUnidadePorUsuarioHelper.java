package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Date;

public class RAPorUnidadePorUsuarioHelper {

	private Integer idRegistroAtendimento;
	private Short situacao;
	private Date dataAtendimento;
	private Integer idUnidadeAtual;
	private Integer idSolicitacaoTipoEspecificacao;
	private String descricaoSolicitacaoTipoEspecificacao;
	private Integer idUsuarioAtendimento;
	
	public Integer getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}
	public void setIdRegistroAtendimento(Integer idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}
	public Short getSituacao() {
		return situacao;
	}
	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}
	public Date getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public Integer getIdUnidadeAtual() {
		return idUnidadeAtual;
	}
	public void setIdUnidadeAtual(Integer idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}
	public Integer getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}
	public void setIdSolicitacaoTipoEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}
	public String getDescricaoSolicitacaoTipoEspecificacao() {
		return descricaoSolicitacaoTipoEspecificacao;
	}
	public void setDescricaoSolicitacaoTipoEspecificacao(
			String descricaoSolicitacaoTipoEspecificacao) {
		this.descricaoSolicitacaoTipoEspecificacao = descricaoSolicitacaoTipoEspecificacao;
	}
	public Integer getIdUsuarioAtendimento() {
		return idUsuarioAtendimento;
	}
	public void setIdUsuarioAtendimento(Integer idUsuarioAtendimento) {
		this.idUsuarioAtendimento = idUsuarioAtendimento;
	}
}
