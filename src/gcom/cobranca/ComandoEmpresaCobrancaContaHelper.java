package gcom.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.List;

public class ComandoEmpresaCobrancaContaHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private ComandoEmpresaCobrancaConta comando;

	private List<Integer> idsUnidadeNegocio;

	private List<Integer> idsGerenciaRegional;

	private List<Integer> idsImovelPerfil;

	private List<Integer> idsLigacaoAguaSituacao;

	private Usuario usuario;

	public ComandoEmpresaCobrancaContaHelper() {
	}

	public ComandoEmpresaCobrancaConta getComando() {
		return comando;
	}

	public void setComando(ComandoEmpresaCobrancaConta comando) {
		this.comando = comando;
	}

	public List<Integer> getIdsUnidadeNegocio() {
		return idsUnidadeNegocio;
	}

	public void setIdsUnidadeNegocio(List<Integer> idsUnidadeNegocio) {
		this.idsUnidadeNegocio = idsUnidadeNegocio;
	}

	public List<Integer> getIdsGerenciaRegional() {
		return idsGerenciaRegional;
	}

	public void setIdsGerenciaRegional(List<Integer> idsGerenciaRegional) {
		this.idsGerenciaRegional = idsGerenciaRegional;
	}

	public List<Integer> getIdsImovelPerfil() {
		return idsImovelPerfil;
	}

	public void setIdsImovelPerfil(List<Integer> idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}

	public List<Integer> getIdsLigacaoAguaSituacao() {
		return idsLigacaoAguaSituacao;
	}

	public void setIdsLigacaoAguaSituacao(List<Integer> idsLigacaoAguaSituacao) {
		this.idsLigacaoAguaSituacao = idsLigacaoAguaSituacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}