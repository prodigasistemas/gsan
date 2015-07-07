package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class ConsultarDebitoClienteActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	
	private String nomeCliente;
	
	private String enderecoCliente;
	
	private String clienteFone;
	
	private String cpfCnpj;
	
	private String tipoRelacao;

	private String referenciaInicial;

	private String referenciaFinal;

	private String dataVencimentoInicial;

	private String dataVencimentoFinal;
	
	private String ramoAtividade;
	
	private String profissao;
	
	private String logradouro;

	private String logradouroTipo;
	
	private String logradouroTitulo;
	
	private String enderecoReferencia;
	
	private String bairro;
	
	private String bairroMunicipio;
	
	private String municipioUnidadeFederacao;
	
	private String cep;
	
	private String cepMunicipio;
	
	private String cepSigla;
	
	private String clienteTipo;
	
	private String descricaoTipoRelacao;
	
	private String responsavel;
	
	private String codigoClienteSuperior;
	
	private String nomeClienteSuperior;
	
	private String[] contasSelecionadas = {};
	
	private String[] debitosSelecionados = {};
	
	private String[] creditosSelecionados = {};
	
	private String[] guiasSelecionadas = {};

	public String[] getContasSelecionadas() {
		return contasSelecionadas;
	}

	public void setContasSelecionadas(String[] contasSelecionadas) {
		this.contasSelecionadas = contasSelecionadas;
	}

	public String[] getDebitosSelecionados() {
		return debitosSelecionados;
	}

	public void setDebitosSelecionados(String[] debitosSelecionados) {
		this.debitosSelecionados = debitosSelecionados;
	}

	public String[] getCreditosSelecionados() {
		return creditosSelecionados;
	}

	public void setCreditosSelecionados(String[] creditosSelecionados) {
		this.creditosSelecionados = creditosSelecionados;
	}

	public String[] getGuiasSelecionadas() {
		return guiasSelecionadas;
	}

	public void setGuiasSelecionadas(String[] guiasSelecionadas) {
		this.guiasSelecionadas = guiasSelecionadas;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public String getReferenciaFinal() {
		return referenciaFinal;
	}

	public void setReferenciaFinal(String referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	public String getReferenciaInicial() {
		return referenciaInicial;
	}

	public void setReferenciaInicial(String referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getClienteFone() {
		return clienteFone;
	}

	public void setClienteFone(String clienteFone) {
		this.clienteFone = clienteFone;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLogradouroTipo() {
		return logradouroTipo;
	}

	public void setLogradouroTipo(String logradouroTipo) {
		this.logradouroTipo = logradouroTipo;
	}

	public String getLogradouroTitulo() {
		return logradouroTitulo;
	}

	public void setLogradouroTitulo(String logradouroTitulo) {
		this.logradouroTitulo = logradouroTitulo;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairroMunicipio() {
		return bairroMunicipio;
	}

	public void setBairroMunicipio(String bairroMunicipio) {
		this.bairroMunicipio = bairroMunicipio;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCepMunicipio() {
		return cepMunicipio;
	}

	public void setCepMunicipio(String cepMunicipio) {
		this.cepMunicipio = cepMunicipio;
	}

	public String getCepSigla() {
		return cepSigla;
	}

	public void setCepSigla(String cepSigla) {
		this.cepSigla = cepSigla;
	}

	public String getEnderecoReferencia() {
		return enderecoReferencia;
	}

	public void setEnderecoReferencia(String enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public String getMunicipioUnidadeFederacao() {
		return municipioUnidadeFederacao;
	}

	public void setMunicipioUnidadeFederacao(String municipioUnidadeFederacao) {
		this.municipioUnidadeFederacao = municipioUnidadeFederacao;
	}

	public String getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(String clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getDescricaoTipoRelacao() {
		return descricaoTipoRelacao;
	}

	public void setDescricaoTipoRelacao(String descricaoTipoRelacao) {
		this.descricaoTipoRelacao = descricaoTipoRelacao;
	}

	/**
	 * @return Retorna o campo responsavel.
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel O responsavel a ser setado.
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * @return Retorna o campo codigoClienteSuperior.
	 */
	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	/**
	 * @param codigoClienteSuperior O codigoClienteSuperior a ser setado.
	 */
	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	/**
	 * @return Retorna o campo nomeClienteSuperior.
	 */
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}

	/**
	 * @param nomeClienteSuperior O nomeClienteSuperior a ser setado.
	 */
	public void setNomeClienteSuperior(String nomeClienteSuperior) {
		this.nomeClienteSuperior = nomeClienteSuperior;
	}
	
	public boolean possuiCliente() {
		return this.getCodigoCliente() != null && !this.getCodigoCliente().equals("");
	}
	
}
