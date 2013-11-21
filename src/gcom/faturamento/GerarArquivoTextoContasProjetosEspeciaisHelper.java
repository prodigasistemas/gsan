package gcom.faturamento;

import java.io.Serializable;

public class GerarArquivoTextoContasProjetosEspeciaisHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String idLocalidade;
	private String nomeLocalidade;
	private String matriculaImovel;
	private String nomeUsuario;
	private String endereco;
	private String numeroHidrometro;
	private String anoMesReferenciaConta;
	private String consumoAgua;
	private String valorConta;
	private String cpf;
	private String setorComercial;
	private String grupoFaturamento;
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}
	public void setAnoMesReferenciaConta(String anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}
	public String getConsumoAgua() {
		return consumoAgua;
	}
	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}
	public String getValorConta() {
		return valorConta;
	}
	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}
	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
}