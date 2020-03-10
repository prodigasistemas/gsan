package gcom.relatorio.cobranca;

import java.util.List;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.util.Util;

public class OrdemSuspensaoFornecimentoHelper {

	private String nomeArquivo;
	private String nomeEmpresa;
	private String nomeAbreviadoEmpresa;
	private String cnpjEmpresa;
	private String enderecoEmpresa;
	private String cepEmpresa;
	private String telefonesEmpresa;

	private List<OrdemSuspensaoFornecimentoDTO> ordens;

	public OrdemSuspensaoFornecimentoHelper(SistemaParametro parametros, List<OrdemSuspensaoFornecimentoDTO> ordens) {
		super();

		this.nomeArquivo = "ORDEM_SUSPENSAO_FORNECIMENTO.pdf";

		this.nomeEmpresa = parametros.getNomeEmpresa();
		this.nomeAbreviadoEmpresa = parametros.getNomeAbreviadoEmpresa();
		this.enderecoEmpresa = parametros.getEnderecoFormatado();
		this.cepEmpresa = parametros.getCep().getCepFormatado();
		this.cnpjEmpresa = Util.formatarCnpj(parametros.getCnpjEmpresa());
		this.telefonesEmpresa = Util.formatarTelefone(parametros.getNumeroTelefone()) + " / " + parametros.getNumero0800Empresa();
		this.nomeAbreviadoEmpresa = parametros.getNomeAbreviadoEmpresa();

		this.ordens = ordens;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public String getNomeAbreviadoEmpresa() {
		return nomeAbreviadoEmpresa;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public String getCepEmpresa() {
		return cepEmpresa;
	}

	public String getTelefonesEmpresa() {
		return telefonesEmpresa;
	}

	public List<OrdemSuspensaoFornecimentoDTO> getOrdens() {
		return ordens;
	}
}
