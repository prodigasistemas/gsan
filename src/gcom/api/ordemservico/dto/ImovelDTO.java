package gcom.api.ordemservico.dto;

import gcom.cadastro.cliente.ClienteImovel;

public class ImovelDTO {

	private int matricula;
	private String inscricao;
	private String endereco;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String clienteNome;
	private String clienteCpfCnpj;

	public ImovelDTO(ClienteImovel clienteImovel) {
		super();

		this.matricula = clienteImovel.getImovel().getId();
		this.inscricao = clienteImovel.getImovel().getInscricaoFormatada();
		this.endereco = clienteImovel.getImovel().getEnderecoFormatadoAbreviado();
		this.situacaoAgua = clienteImovel.getImovel().getLigacaoAguaSituacao().getDescricao();
		this.situacaoEsgoto = clienteImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao();
		this.clienteNome = clienteImovel.getCliente().getNome();
		this.clienteCpfCnpj = clienteImovel.getCliente().getCpfOuCnpjFormatado();
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getClienteCpfCnpj() {
		return clienteCpfCnpj;
	}

	public void setClienteCpfCnpj(String clienteCpfCnpj) {
		this.clienteCpfCnpj = clienteCpfCnpj;
	}
}
