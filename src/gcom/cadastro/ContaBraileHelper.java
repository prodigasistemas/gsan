package gcom.cadastro;

import java.io.Serializable;

/**
 * [UC1128] Solicitar Conta Braile
 *
 * @author Hugo Leonardo
 * @date 04/03/2011
 */
public class ContaBraileHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private String nomeCliente;
	private String cpfCnpjCliente;
	private String email;
	private String nomeSolicitante;
	private String cpfSolicitante;
	private String telefoneContato;
	private String rg;
	private String orgaoExpeditor;
	private String dataExpedicao;
	private String unidadeFederacao;
	private String protocoloAtendimento;
	
	private boolean indicadorCpf = false;
	private boolean indicadorCnpj = false;
	
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getCpfSolicitante() {
		return cpfSolicitante;
	}

	public void setCpfSolicitante(String cpfSolicitante) {
		this.cpfSolicitante = cpfSolicitante;
	}

	public String getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(String dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getOrgaoExpeditor() {
		return orgaoExpeditor;
	}

	public void setOrgaoExpeditor(String orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public boolean isIndicadorCnpj() {
		return indicadorCnpj;
	}

	public void setIndicadorCnpj(boolean indicadorCnpj) {
		this.indicadorCnpj = indicadorCnpj;
	}

	public boolean isIndicadorCpf() {
		return indicadorCpf;
	}

	public void setIndicadorCpf(boolean indicadorCpf) {
		this.indicadorCpf = indicadorCpf;
	}

	public String getProtocoloAtendimento() {
		return protocoloAtendimento;
	}

	public void setProtocoloAtendimento(String protocoloAtendimento) {
		this.protocoloAtendimento = protocoloAtendimento;
	}
	
}
