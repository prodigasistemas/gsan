package gcom.relatorio.transacao;

import gcom.relatorio.RelatorioBean;

public class RelatorioOperacaoUsuariosBean implements RelatorioBean {

	private String nome;
	private String acaoUsuario;
	private String tipoUsuario;

	public RelatorioOperacaoUsuariosBean (String nome, String acaoUsuario, String tipoUsuario) {
		this.nome = nome;
		this.acaoUsuario = acaoUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	public String getAcaoUsuario() {
		return acaoUsuario;
	}
	public void setAcaoUsuario(String acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
