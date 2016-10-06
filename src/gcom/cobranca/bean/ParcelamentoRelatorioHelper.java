package gcom.cobranca.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ParcelamentoRelatorioHelper {

	private Integer idImovel;

	private String nomeCliente;

	private String endereco;

	private String cpfCnpj;

	private String telefone;

	private Date dataParcelamento;

	private BigDecimal valorFaturasEmAberto;

	private BigDecimal valorServicosACobrar;

	private BigDecimal valorAtualizacaoMonetaria;

	private BigDecimal valorJurosMora;

	private BigDecimal valorMultas;

	private BigDecimal valorGuiaPagamento;

	private BigDecimal valorParcelamentoACobrar;

	private BigDecimal valorTotalDebitos;

	private BigDecimal valorDescontoFaixaReferenciaConta;

	private BigDecimal valorDescontoAcrescimo;

	private BigDecimal valorDescontoAntiguidade;

	private BigDecimal valorDescontoInatividade;

	private BigDecimal valorCreditosRealizar;

	private BigDecimal valorTotalDescontos;

	private BigDecimal valorASerNegociado;

	private BigDecimal valorEntrada;

	private Short numeroParcelas;

	private BigDecimal valorParcela;

	private BigDecimal valorASerParcelado;

	private String solicitacaoRestabelecimento;

	private String nomeMunicipio;

	private Integer idFuncionario;

	private BigDecimal unidadeUsuario;

	private String nomeClienteParcelamento;

	private String cpfClienteParcelamento;

	private String rgCliente;

	private String descOrgaoExpRgCliente;

	private String siglaUnidadeFederacaoRgCliente;

	private String mesAnoInicioParcelamento;

	private String mesAnoFinalParcelamento;

	private String rgClienteParcelamento;

	private String taxaJuros;

	private BigDecimal valorDescontoSancoesRegulamentares;

	private BigDecimal valorDescontoTarifaSocial;

	private String nomeDiretorComercial;

	private String cpfDiretorComercial;

	private String profissao;

	private String nomeDevedor;

	private String cnpjDevedor;

	private String enderecoDevedor;

	private String telefoneDevedor;

	private Short indicadorPessoaJuridica;

	private Integer idDevedor;

	private String NomeUsuarioParcelamento;

	private String bairro;

	private Short codigoRota;

	private String localidade;

	private String setorComercial;

	private String cpfUsuario;

	private BigDecimal valorTotalDescontosSemValorCreditos;

	public Integer getIdDevedor() {
		return idDevedor;
	}

	public BigDecimal getValorTotalDescontosSemValorCreditos() {
		return valorTotalDescontosSemValorCreditos;
	}

	public void setValorTotalDescontosSemValorCreditos(BigDecimal valorTotalDescontosSemValorCreditos) {
		this.valorTotalDescontosSemValorCreditos = valorTotalDescontosSemValorCreditos;
	}

	public void setIdDevedor(Integer idDevedor) {
		this.idDevedor = idDevedor;
	}

	public String getCnpjDevedor() {
		return cnpjDevedor;
	}

	public void setCnpjDevedor(String cnpjDevedor) {
		this.cnpjDevedor = cnpjDevedor;
	}

	public String getEnderecoDevedor() {
		return enderecoDevedor;
	}

	public void setEnderecoDevedor(String enderecoDevedor) {
		this.enderecoDevedor = enderecoDevedor;
	}

	public String getNomeDevedor() {
		return nomeDevedor;
	}

	public void setNomeDevedor(String nomeDevedor) {
		this.nomeDevedor = nomeDevedor;
	}

	public String getTelefoneDevedor() {
		return telefoneDevedor;
	}

	public void setTelefoneDevedor(String telefoneDevedor) {
		this.telefoneDevedor = telefoneDevedor;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCpfDiretorComercial() {
		return cpfDiretorComercial;
	}

	public void setCpfDiretorComercial(String cpfDiretorComercial) {
		this.cpfDiretorComercial = cpfDiretorComercial;
	}

	public String getNomeDiretorComercial() {
		return nomeDiretorComercial;
	}

	public void setNomeDiretorComercial(String nomeDiretorComercial) {
		this.nomeDiretorComercial = nomeDiretorComercial;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Date getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(Date dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public Short getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Short numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getSolicitacaoRestabelecimento() {
		return solicitacaoRestabelecimento;
	}

	public void setSolicitacaoRestabelecimento(String solicitacaoRestabelecimento) {
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getUnidadeUsuario() {
		return unidadeUsuario;
	}

	public void setUnidadeUsuario(BigDecimal unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}

	public BigDecimal getValorASerNegociado() {
		return valorASerNegociado;
	}

	public void setValorASerNegociado(BigDecimal valorASerNegociado) {
		this.valorASerNegociado = valorASerNegociado;
	}

	public BigDecimal getValorASerParcelado() {
		return valorASerParcelado;
	}

	public void setValorASerParcelado(BigDecimal valorASerParcelado) {
		this.valorASerParcelado = valorASerParcelado;
	}

	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	public BigDecimal getValorCreditosRealizar() {
		return valorCreditosRealizar;
	}

	public void setValorCreditosRealizar(BigDecimal valorCreditosRealizar) {
		this.valorCreditosRealizar = valorCreditosRealizar;
	}

	public BigDecimal getValorDescontoAcrescimo() {
		return valorDescontoAcrescimo;
	}

	public void setValorDescontoAcrescimo(BigDecimal valorDescontoAcrescimo) {
		this.valorDescontoAcrescimo = valorDescontoAcrescimo;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorFaturasEmAberto() {
		return valorFaturasEmAberto;
	}

	public void setValorFaturasEmAberto(BigDecimal valorFaturasEmAberto) {
		this.valorFaturasEmAberto = valorFaturasEmAberto;
	}

	public BigDecimal getValorGuiaPagamento() {
		return valorGuiaPagamento;
	}

	public void setValorGuiaPagamento(BigDecimal valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}

	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorMultas() {
		return valorMultas;
	}

	public void setValorMultas(BigDecimal valorMultas) {
		this.valorMultas = valorMultas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getValorParcelamentoACobrar() {
		return valorParcelamentoACobrar;
	}

	public void setValorParcelamentoACobrar(BigDecimal valorParcelamentoACobrar) {
		this.valorParcelamentoACobrar = valorParcelamentoACobrar;
	}

	public BigDecimal getValorServicosACobrar() {
		return valorServicosACobrar;
	}

	public void setValorServicosACobrar(BigDecimal valorServicosACobrar) {
		this.valorServicosACobrar = valorServicosACobrar;
	}

	public BigDecimal getValorTotalDebitos() {
		return valorTotalDebitos;
	}

	public void setValorTotalDebitos(BigDecimal valorTotalDebitos) {
		this.valorTotalDebitos = valorTotalDebitos;
	}

	public BigDecimal getValorTotalDescontos() {
		return valorTotalDescontos;
	}

	public void setValorTotalDescontos(BigDecimal valorTotalDescontos) {
		this.valorTotalDescontos = valorTotalDescontos;
	}

	public String getCpfClienteParcelamento() {
		return cpfClienteParcelamento;
	}

	public void setCpfClienteParcelamento(String cpfClienteParcelamento) {
		this.cpfClienteParcelamento = cpfClienteParcelamento;
	}

	public String getNomeClienteParcelamento() {
		return nomeClienteParcelamento;
	}

	public void setNomeClienteParcelamento(String nomeClienteParcelamento) {
		this.nomeClienteParcelamento = nomeClienteParcelamento;
	}

	public String getRgCliente() {
		return rgCliente;
	}

	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}

	public String getDescOrgaoExpRgCliente() {
		return descOrgaoExpRgCliente;
	}

	public void setDescOrgaoExpRgCliente(String descOrgaoExpRgCliente) {
		this.descOrgaoExpRgCliente = descOrgaoExpRgCliente;
	}

	public String getSiglaUnidadeFederacaoRgCliente() {
		return siglaUnidadeFederacaoRgCliente;
	}

	public void setSiglaUnidadeFederacaoRgCliente(String siglaUnidadeFederacaoRgCliente) {
		this.siglaUnidadeFederacaoRgCliente = siglaUnidadeFederacaoRgCliente;
	}

	public String getMesAnoFinalParcelamento() {
		return mesAnoFinalParcelamento;
	}

	public void setMesAnoFinalParcelamento(String mesAnoFinalParcelamento) {
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
	}

	public String getMesAnoInicioParcelamento() {
		return mesAnoInicioParcelamento;
	}

	public void setMesAnoInicioParcelamento(String mesAnoInicioParcelamento) {
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
	}

	public String getRgClienteParcelamento() {
		return rgClienteParcelamento;
	}

	public void setRgClienteParcelamento(String rgClienteParcelamento) {
		this.rgClienteParcelamento = rgClienteParcelamento;
	}

	public String getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public BigDecimal getValorDescontoSancoesRegulamentares() {
		return valorDescontoSancoesRegulamentares;
	}

	public void setValorDescontoSancoesRegulamentares(BigDecimal valorDescontoSancoesRegulamentares) {
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
	}

	public BigDecimal getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(BigDecimal valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

	public Short getIndicadorPessoaJuridica() {
		return indicadorPessoaJuridica;
	}

	public void setIndicadorPessoaJuridica(Short indicadorPessoaJuridica) {
		this.indicadorPessoaJuridica = indicadorPessoaJuridica;
	}

	public String getNomeUsuarioParcelamento() {
		return NomeUsuarioParcelamento;
	}

	public void setNomeUsuarioParcelamento(String nomeUsuarioParcelamento) {
		NomeUsuarioParcelamento = nomeUsuarioParcelamento;
	}

	public BigDecimal getValorASerNegociadoSemDesconto() {

		BigDecimal valorASerNegociadoSemDesconto = getValorASerNegociado();

		return valorASerNegociadoSemDesconto;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public BigDecimal getValorDescontoFaixaReferenciaConta() {
		return valorDescontoFaixaReferenciaConta;
	}

	public void setValorDescontoFaixaReferenciaConta(BigDecimal valorDescontoFaixaReferenciaConta) {
		this.valorDescontoFaixaReferenciaConta = valorDescontoFaixaReferenciaConta;
	}
}
