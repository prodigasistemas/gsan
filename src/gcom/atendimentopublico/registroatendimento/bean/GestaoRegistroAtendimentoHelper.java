package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Date;

public class GestaoRegistroAtendimentoHelper {
	
	private Integer idRegistroAtendimento;
	private Short situacao;
	private Date dataEncerramento;
	private Integer idAtendimentoMotivoEncerramento;
	private Integer idUnidadeAtual;
	private Integer idSolicitacaoTipoEspecificacao;
	private String descricaoSolicitacaoTipoEspecificacao;
	private Date dataPrevistaAtual;
	private Boolean flag0800;
	
	public GestaoRegistroAtendimentoHelper() { }

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataPrevistaAtual() {
		return dataPrevistaAtual;
	}

	public void setDataPrevistaAtual(Date dataPrevistaAtual) {
		this.dataPrevistaAtual = dataPrevistaAtual;
	}

	public String getDescricaoSolicitacaoTipoEspecificacao() {
		return descricaoSolicitacaoTipoEspecificacao;
	}

	public void setDescricaoSolicitacaoTipoEspecificacao(
			String descricaoSolicitacaoTipoEspecificacao) {
		this.descricaoSolicitacaoTipoEspecificacao = descricaoSolicitacaoTipoEspecificacao;
	}

	public Boolean getFlag0800() {
		return flag0800;
	}

	public void setFlag0800(Boolean flag0800) {
		this.flag0800 = flag0800;
	}

	public Integer getIdAtendimentoMotivoEncerramento() {
		return idAtendimentoMotivoEncerramento;
	}

	public void setIdAtendimentoMotivoEncerramento(
			Integer idAtendimentoMotivoEncerramento) {
		this.idAtendimentoMotivoEncerramento = idAtendimentoMotivoEncerramento;
	}

	public Integer getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	public void setIdRegistroAtendimento(Integer idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	public Integer getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}

	public void setIdSolicitacaoTipoEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}

	public Integer getIdUnidadeAtual() {
		return idUnidadeAtual;
	}

	public void setIdUnidadeAtual(Integer idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}

	public Short getSituacao() {
		return situacao;
	}

	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

}
