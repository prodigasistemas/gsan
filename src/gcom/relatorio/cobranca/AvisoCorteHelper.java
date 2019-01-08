package gcom.relatorio.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.util.Util;

import java.util.List;

public class AvisoCorteHelper {

	private String nomeArquivo;

	private String nomeEmpresa;
	private String enderecoEmpresa;
	private String cepEmpresa;
	private String cnpjEmpresa;
	private String foneFaxEmpresa;

	private List<AvisoCorteDTO> avisos;

	public AvisoCorteHelper(SistemaParametro parametros, List<AvisoCorteDTO> avisos) {
		super();

		this.nomeArquivo = "AVISO_CORTE.pdf";

		this.nomeEmpresa = parametros.getNomeEmpresa();
		this.enderecoEmpresa = parametros.getEnderecoFormatado();
		this.cepEmpresa = "CEP: " + parametros.getCep().getCepFormatado();
		this.cnpjEmpresa = "CNPJ: " + Util.formatarCnpj(parametros.getCnpjEmpresa());
		this.foneFaxEmpresa = "FONE: " + Util.formatarTelefone(parametros.getNumeroTelefone()) + " / " + "FAX: " + Util.formatarTelefone(parametros.getNumeroFax());

		this.avisos = avisos;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public String getCepEmpresa() {
		return cepEmpresa;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public String getFoneFaxEmpresa() {
		return foneFaxEmpresa;
	}

	public List<AvisoCorteDTO> getAvisos() {
		return avisos;
	}
}
