package gcom.gui.arrecadacao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirGuiaDevolucaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idRegistroAtendimento;
	private String nomeRegistroAtendimento;
	private String idOrdemServico;
	private String nomeOrdemServico;
	private String documentoTipo;
	private String documentoTipoHidden;
	private String idLocalidade;
	private String idLocalidadeHidden;
	private String descricaoLocalidade;
	private String idImovel;
	private String descricaoImovel;
	private String codigoCliente;
	private String nomeCliente;
	private String referenciaConta;
	private String descricaoConta;
	private String idGuiaPagamento;
	private String descricaoGuiaPagamento;
	private String valorGuiaPagamento;
	private String idDebitoACobrar;
	private String descricaoDebitoACobrar;
	private String valorDebitoACobrar;
	private String idTipoDebito;
	private String idTipoDebitoHidden;
	private String descricaoTipoDebito;
	private String valorDevolucao;
	
	private String idFuncionarioAnalista;
	private String nomeFuncionarioAnalista;
	
	private String idFuncionarioAutorizador;
	private String nomeFuncionarioAutorizador;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}	
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getDescricaoConta() {
		return descricaoConta;
	}
	public void setDescricaoConta(String descricaoConta) {
		this.descricaoConta = descricaoConta;
	}
	public String getDescricaoDebitoACobrar() {
		return descricaoDebitoACobrar;
	}
	public void setDescricaoDebitoACobrar(String descricaoDebitoACobrar) {
		this.descricaoDebitoACobrar = descricaoDebitoACobrar;
	}
	public String getDescricaoGuiaPagamento() {
		return descricaoGuiaPagamento;
	}
	public void setDescricaoGuiaPagamento(String descricaoGuiaPagamento) {
		this.descricaoGuiaPagamento = descricaoGuiaPagamento;
	}
	public String getDescricaoImovel() {
		return descricaoImovel;
	}
	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}
	public String getDocumentoTipo() {
		return documentoTipo;
	}
	public void setDocumentoTipo(String documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}
	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}
	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}
	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}
	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	public String getNomeRegistroAtendimento() {
		return nomeRegistroAtendimento;
	}
	public void setNomeRegistroAtendimento(String nomeRegistroAtendimento) {
		this.nomeRegistroAtendimento = nomeRegistroAtendimento;
	}
	public String getReferenciaConta() {
		return referenciaConta;
	}
	public void setReferenciaConta(String referenciaConta) {
		this.referenciaConta = referenciaConta;
	}
	public String getValorDebitoACobrar() {
		return valorDebitoACobrar;
	}
	public void setValorDebitoACobrar(String valorDebitoACobrar) {
		this.valorDebitoACobrar = valorDebitoACobrar;
	}
	public String getValorDevolucao() {
		return valorDevolucao;
	}
	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}
	public String getValorGuiaPagamento() {
		return valorGuiaPagamento;
	}
	public void setValorGuiaPagamento(String valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}

	public String getDocumentoTipoHidden() {
		return documentoTipoHidden;
	}

	public void setDocumentoTipoHidden(String documentoTipoHidden) {
		this.documentoTipoHidden = documentoTipoHidden;
	}

	public String getIdLocalidadeHidden() {
		return idLocalidadeHidden;
	}

	public void setIdLocalidadeHidden(String idLocalidadeHidden) {
		this.idLocalidadeHidden = idLocalidadeHidden;
	}

	public String getIdTipoDebitoHidden() {
		return idTipoDebitoHidden;
	}

	public void setIdTipoDebitoHidden(String idTipoDebitoHidden) {
		this.idTipoDebitoHidden = idTipoDebitoHidden;
	}

	public String getIdFuncionarioAnalista() {
		return idFuncionarioAnalista;
	}

	public void setIdFuncionarioAnalista(String idFuncionarioAnalista) {
		this.idFuncionarioAnalista = idFuncionarioAnalista;
	}

	public String getNomeFuncionarioAnalista() {
		return nomeFuncionarioAnalista;
	}

	public void setNomeFuncionarioAnalista(String nomeFuncionarioAnalista) {
		this.nomeFuncionarioAnalista = nomeFuncionarioAnalista;
	}

	public String getIdFuncionarioAutorizador() {
		return idFuncionarioAutorizador;
	}

	public void setIdFuncionarioAutorizador(String idFuncionarioAutorizador) {
		this.idFuncionarioAutorizador = idFuncionarioAutorizador;
	}

	public String getNomeFuncionarioAutorizador() {
		return nomeFuncionarioAutorizador;
	}

	public void setNomeFuncionarioAutorizador(String nomeFuncionarioAutorizador) {
		this.nomeFuncionarioAutorizador = nomeFuncionarioAutorizador;
	}
	
	
}
