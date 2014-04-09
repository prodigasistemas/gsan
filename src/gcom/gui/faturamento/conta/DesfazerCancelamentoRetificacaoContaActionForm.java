package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class DesfazerCancelamentoRetificacaoContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    private String idRegistroAtualizacao;

    private String[] idRegistrosRemocao;
	
	private String idImovel;

	private String inscricaoImovel;

	private String nomeClienteUsuario;

	private String situacaoAgua;

	private String situacaoEsgoto;

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getIdRegistroAtualizacao() {
		return idRegistroAtualizacao;
	}

	public void setIdRegistroAtualizacao(String idRegistroAtualizacao) {
		this.idRegistroAtualizacao = idRegistroAtualizacao;
	}

	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

}
