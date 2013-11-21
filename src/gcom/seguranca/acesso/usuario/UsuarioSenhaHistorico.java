package gcom.seguranca.acesso.usuario;

import java.io.Serializable;
import java.util.Date;

public class UsuarioSenhaHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Usuario usuario;
	private String senha;
	private Date ultimaAlteracao;

	public UsuarioSenhaHistorico() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
