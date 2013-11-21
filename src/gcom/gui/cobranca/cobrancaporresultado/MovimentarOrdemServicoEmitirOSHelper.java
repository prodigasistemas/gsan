package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.ComandoEmpresaCobrancaConta;

import java.io.Serializable;
import java.util.Collection;

public class MovimentarOrdemServicoEmitirOSHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private Collection<UnidadeNegocio> colecaoUnidadeNegocio;
	
	private Collection<GerenciaRegional> colecaoGerenciaRegional;
	
	private Collection<ImovelPerfil> colecaoImovelPerfil;

	private String numeroOSInicial;

	private String numeroOSFinal;

	public MovimentarOrdemServicoEmitirOSHelper() {
		super();
	}

	public MovimentarOrdemServicoEmitirOSHelper(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Collection<UnidadeNegocio> colecaoUnidadeNegocio, Collection<GerenciaRegional> colecaoGerenciaRegional, Collection<ImovelPerfil> colecaoImovelPerfil) {
		super();
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<GerenciaRegional> getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	public void setColecaoGerenciaRegional(
			Collection<GerenciaRegional> colecaoGerenciaRegional) {
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

	public void setColecaoUnidadeNegocio(
			Collection<UnidadeNegocio> colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public String getNumeroOSFinal() {
		return numeroOSFinal;
	}

	public void setNumeroOSFinal(String numeroOSFinal) {
		this.numeroOSFinal = numeroOSFinal;
	}

	public String getNumeroOSInicial() {
		return numeroOSInicial;
	}

	public void setNumeroOSInicial(String numeroOSInicial) {
		this.numeroOSInicial = numeroOSInicial;
	}

}
