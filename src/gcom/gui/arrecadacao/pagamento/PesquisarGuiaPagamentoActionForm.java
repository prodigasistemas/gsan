package gcom.gui.arrecadacao.pagamento;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Form utilizado na pesquisa de guias de pagamento de clientes ou imóvel 
 *
 * @author Pedro Alexandre
 * @date 07/03/2006
 */
public class PesquisarGuiaPagamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String dataEmissaoGuiaPagamentoInicial;

	private String dataEmissaoGuiaPagamentoFinal;

	private String dataVencimentoGuiaPagamentoInicial;

	private String dataVencimentoGuiaPagamentoFinal;
	
	private String idImovel;

	private String[] idSituacaoGuiaPagamento;
	
	private String[] idTipoDebito;
	
	private String[] idTipoDebitoSelecionados;

    private String inscricaoImovel;
    
    private String nomeClienteUsuario;
	
    private String situacaoAguaImovel;
	
    private String situacaoEsgotoImovel;
    
    private String idCliente;
    
    private String nomeCliente;
    
    private String cpf_cnpj;
    
    private String profissao;
    
    private String ramoAtividade;
    
    private String enderecoCliente;
    
    private String telefoneCliente;

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getDataEmissaoGuiaPagamentoFinal() {
		return dataEmissaoGuiaPagamentoFinal;
	}

	public void setDataEmissaoGuiaPagamentoFinal(
			String dataEmissaoGuiaPagamentoFinal) {
		this.dataEmissaoGuiaPagamentoFinal = dataEmissaoGuiaPagamentoFinal;
	}

	public String getDataEmissaoGuiaPagamentoInicial() {
		return dataEmissaoGuiaPagamentoInicial;
	}

	public void setDataEmissaoGuiaPagamentoInicial(
			String dataEmissaoGuiaPagamentoInicial) {
		this.dataEmissaoGuiaPagamentoInicial = dataEmissaoGuiaPagamentoInicial;
	}

	public String getDataVencimentoGuiaPagamentoFinal() {
		return dataVencimentoGuiaPagamentoFinal;
	}

	public void setDataVencimentoGuiaPagamentoFinal(
			String dataVencimentoGuiaPagamentoFinal) {
		this.dataVencimentoGuiaPagamentoFinal = dataVencimentoGuiaPagamentoFinal;
	}

	public String getDataVencimentoGuiaPagamentoInicial() {
		return dataVencimentoGuiaPagamentoInicial;
	}

	public void setDataVencimentoGuiaPagamentoInicial(
			String dataVencimentoGuiaPagamentoInicial) {
		this.dataVencimentoGuiaPagamentoInicial = dataVencimentoGuiaPagamentoInicial;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String[] getIdSituacaoGuiaPagamento() {
		return idSituacaoGuiaPagamento;
	}

	public void setIdSituacaoGuiaPagamento(String[] idSituacaoGuiaPagamento) {
		this.idSituacaoGuiaPagamento = idSituacaoGuiaPagamento;
	}

	public String[] getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String[] idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String situacaoAguaImovel) {
		this.situacaoAguaImovel = situacaoAguaImovel;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
		this.situacaoEsgotoImovel = situacaoEsgotoImovel;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String[] getIdTipoDebitoSelecionados() {
		return idTipoDebitoSelecionados;
	}

	public void setIdTipoDebitoSelecionados(String[] idTipoDebitoSelecionados) {
		this.idTipoDebitoSelecionados = idTipoDebitoSelecionados;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

		

}
