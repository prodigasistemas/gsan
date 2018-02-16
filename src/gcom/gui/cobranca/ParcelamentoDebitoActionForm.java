package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ParcelamentoDebitoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoImovel;

	private String nomeCliente;

	private String inscricao;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private String parcelamento;

	private String reparcelamento;

	private String reparcelamentoConsecutivo;

	private String cpf;

	private String cnpj;

	private String cpfCnpj;

	private String parcelamentoMotivoDesfazer;

	private String imovelPerfil;

	private String nomeClienteResponsavel;

	private String dataParcelamentoDesfeito;

	private String parcelamentoMotivoCancelamento;
	
	private String situacao;
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(String parcelamento) {
		this.parcelamento = parcelamento;
	}

	public String getReparcelamento() {
		return reparcelamento;
	}

	public void setReparcelamento(String reparcelamento) {
		this.reparcelamento = reparcelamento;
	}

	public String getReparcelamentoConsecutivo() {
		return reparcelamentoConsecutivo;
	}

	public void setReparcelamentoConsecutivo(String reparcelamentoConsecutivo) {
		this.reparcelamentoConsecutivo = reparcelamentoConsecutivo;
	}

	public String getParcelamentoMotivoDesfazer() {
		return parcelamentoMotivoDesfazer;
	}

	public void setParcelamentoMotivoDesfazer(String parcelamentoMotivoDesfazer) {
		this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	public String getDataParcelamentoDesfeito() {
		return dataParcelamentoDesfeito;
	}

	public void setDataParcelamentoDesfeito(String dataParcelamentoDesfeito) {
		this.dataParcelamentoDesfeito = dataParcelamentoDesfeito;
	}

	public String getParcelamentoMotivoCancelamento() {
		return parcelamentoMotivoCancelamento;
	}

	public void setParcelamentoMotivoCancelamento(String parcelamentoMotivoCancelamento) {
		this.parcelamentoMotivoCancelamento = parcelamentoMotivoCancelamento;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
