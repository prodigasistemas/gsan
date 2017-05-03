package gcom.cobranca;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;

import java.io.Serializable;
import java.util.Collection;

public class ComandoEmpresaCobrancaContaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;

	private Collection<UnidadeNegocio> colecaoUnidadeNegocio;

	private Collection<GerenciaRegional> colecaoGerenciaRegional;

	private Collection<ImovelPerfil> colecaoImovelPerfil;

	private Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao;

	public ComandoEmpresaCobrancaContaHelper() {
		super();
	}

	public ComandoEmpresaCobrancaContaHelper(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Collection<UnidadeNegocio> colecaoUnidadeNegocio,
			Collection<GerenciaRegional> colecaoGerenciaRegional, Collection<ImovelPerfil> colecaoImovelPerfil) {
		
		super();
		
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<GerenciaRegional> getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	public void setColecaoGerenciaRegional(Collection<GerenciaRegional> colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}

	public Collection<ImovelPerfil> getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}

	public void setColecaoImovelPerfil(Collection<ImovelPerfil> colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<UnidadeNegocio> getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}

	public void setColecaoUnidadeNegocio(Collection<UnidadeNegocio> colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public Collection<LigacaoAguaSituacao> getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}

	public void setColecaoLigacaoAguaSituacao(Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}

}
