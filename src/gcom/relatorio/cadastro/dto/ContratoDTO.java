package gcom.relatorio.cadastro.dto;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Contrato;
import gcom.cadastro.imovel.ContratoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

public class ContratoDTO {

	private String nomeArquivo;

	private String matricula;
	private String nomeCliente;
	private String cpfCnpjCliente;
	private String endereco;
	private String cidade;

	private String numeroContrato;
	private int tipoContrato;
	private String nomeTipoContrato;

	private String nomeUsuario;
	private String cpfUsuario;
	private String grupoUsuario;

	private String dataGeracao;

	public ContratoDTO() {}

	public ContratoDTO(Imovel imovel, Cliente cliente, String endereco, Contrato contrato, Usuario usuario, Grupo grupo) {
		super();

		this.nomeArquivo = criarNomeArquivo(contrato);
		this.matricula = imovel.getId().toString();
		this.nomeCliente = cliente.getNome();
		this.cpfCnpjCliente = cliente.getCpfOuCnpjFormatado();
		this.endereco = endereco;
		this.cidade = imovel.getNomeMunicipio();
		this.numeroContrato = contrato.getNumeroContrato();
		this.tipoContrato = contrato.getContratoTipo().getId();
		this.nomeTipoContrato = definirNomeTipoContrato();
		this.nomeUsuario = usuario.getNomeUsuario();
		this.cpfUsuario = Util.formatarCpf(usuario.getCpf());
		this.grupoUsuario = grupo.getDescricao();
		this.dataGeracao = Util.formatarData(contrato.getDataContratoInicio());
	}

	public ContratoDTO(Imovel imovel, Cliente cliente, String endereco, Contrato contrato) {
		super();

		this.nomeArquivo = criarNomeArquivo(contrato);
		this.matricula = imovel.getId().toString();
		this.nomeCliente = cliente.getNome();
		this.cpfCnpjCliente = cliente.getCpfOuCnpjFormatado();
		this.endereco = imovel.getEnderecoFormatado();
		this.cidade = imovel.getNomeMunicipio();
		this.numeroContrato = contrato.getNumeroContrato();
		this.tipoContrato = contrato.getContratoTipo().getId();
		this.nomeTipoContrato = definirNomeTipoContrato();
	}

	private String criarNomeArquivo(Contrato contrato) {
		String nome = "CONTRATO_" + contrato.getContratoTipo().getDescricao() + "-" + contrato.getNumeroContrato() + ".pdf";
		return nome.replace(" ", "_");
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public int getTipoContrato() {
		return tipoContrato;
	}

	public String getNomeTipoContrato() {
		return nomeTipoContrato;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}
	
	public String getGrupoUsuario() {
		return grupoUsuario;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	private String definirNomeTipoContrato() {
		if (tipoContrato == ContratoTipo.ADESAO) {
			return "ADESÃO";
		} else if (tipoContrato == ContratoTipo.INSTALACAO_RESERVACAO) {
			return "INSTALAÇÃO DE RESERVAÇÃO";
		} else {
			return "";
		}
	}
}
