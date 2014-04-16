package gcom.cadastro.cliente.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ClienteImovelEconomiaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ClienteImovelEconomiaHelper(){}
	
	private String clienteNome;
	
	private String relacaoTipo;
	
	private String cpf;
	
	private String cnpj;
	
	private Date relacaoDataInicio;
	
	private Date relacaoDataFim;
	
	private String motivoFimRelacao;
	
	private String matriculaFormatadaImovel;
	
	private String categoria;

	private String subCategoria;

	private String complementoEndereco;

	private Short numeroMoradores;
	
	private Short numeroPontosUtilizacao;

	private String numeroIptu;

	private String areaConstruida;

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMotivoFimRelacao() {
		return motivoFimRelacao;
	}

	public void setMotivoFimRelacao(String motivoFimRelacao) {
		this.motivoFimRelacao = motivoFimRelacao;
	}

	public Date getRelacaoDataFim() {
		return relacaoDataFim;
	}

	public void setRelacaoDataFim(Date relacaoDataFim) {
		this.relacaoDataFim = relacaoDataFim;
	}

	public Date getRelacaoDataInicio() {
		return relacaoDataInicio;
	}

	public void setRelacaoDataInicio(Date relacaoDataInicio) {
		this.relacaoDataInicio = relacaoDataInicio;
	}

	public String getRelacaoTipo() {
		return relacaoTipo;
	}

	public void setRelacaoTipo(String relacaoTipo) {
		this.relacaoTipo = relacaoTipo;
	}

	public String getMatriculaFormatadaImovel() {
		return matriculaFormatadaImovel;
	}

	public void setMatriculaFormatadaImovel(String matriculaFormatadaImovel) {
		this.matriculaFormatadaImovel = matriculaFormatadaImovel;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public Short getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(Short numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public Short getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}

	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	public String getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
}
