package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cobranca.ComandoEmpresaCobrancaConta;

import java.io.Serializable;
import java.util.List;

public class MovimentarOrdemServicoEmitirOSHelper implements Serializable {
	private static final long serialVersionUID = 1L;

	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;

	private List<Integer> idsUnidadeNegocio;

	private List<Integer> idsGerenciaRegional;

	private List<Integer> idsImovelPerfil;

	private String numeroOSInicial;

	private String numeroOSFinal;

	public MovimentarOrdemServicoEmitirOSHelper() {
		super();
	}

	public MovimentarOrdemServicoEmitirOSHelper(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, List<Integer> idsUnidadeNegocio, List<Integer> idsGerenciaRegional,
			List<Integer> idsImovelPerfil, String numeroOSInicial, String numeroOSFinal) {
		super();
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.idsUnidadeNegocio = idsUnidadeNegocio;
		this.idsGerenciaRegional = idsGerenciaRegional;
		this.idsImovelPerfil = idsImovelPerfil;
		this.numeroOSInicial = numeroOSInicial;
		this.numeroOSFinal = numeroOSFinal;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
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

	public String getNumeroOSInicial() {
		return numeroOSInicial;
	}

	public void setNumeroOSInicial(String numeroOSInicial) {
		this.numeroOSInicial = numeroOSInicial;
	}

	public String getNumeroOSFinal() {
		return numeroOSFinal;
	}

	public void setNumeroOSFinal(String numeroOSFinal) {
		this.numeroOSFinal = numeroOSFinal;
	}
}
