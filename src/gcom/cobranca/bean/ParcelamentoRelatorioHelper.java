package gcom.cobranca.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Rafael Corrêa
 * @since 25/09/2006
 *
 */
public class ParcelamentoRelatorioHelper {
	
	/**
	 * Id do Imóvel
	 */
	private Integer idImovel;
	
	/**
	 * Nome do Cliente
	 */
	private String nomeCliente;
	
	/**
	 * Endereço
	 */
	private String endereco;
	
	/**
	 * CPF/CNPJ
	 */
	private String cpfCnpj;

	/**
	 * Telefone
	 */
	private String telefone;
	
	/**
	 * Data do Parcelamento
	 */
	private Date dataParcelamento;
	
	/**
	 * Valor das Faturas em Aberto
	 */
	private BigDecimal valorFaturasEmAberto;
	
	/**
	 * Valor dos Serviços a Cobrar
	 */
	private BigDecimal valorServicosACobrar;
	
	/**
	 * Valor de Atualizacao Monetaria
	 */
	private BigDecimal valorAtualizacaoMonetaria;
	
	/**
	 * Valor de Juros/Mora
	 */
	private BigDecimal valorJurosMora;
	
	/**
	 * Valor das Multas
	 */
	private BigDecimal valorMultas;
	
	/**
	 * Valor das Guias de Pagamento
	 */
	private BigDecimal valorGuiaPagamento;

	/**
	 * Valor do Parcelamento a Cobrar
	 */
	private BigDecimal valorParcelamentoACobrar;
	
	/**
	 * Valor Total dos Débitos
	 */
	private BigDecimal valorTotalDebitos;
	
	/**
	 * Valor dos Descontos de Acréscimos
	 */
	private BigDecimal valorDescontoAcrescimo;
	
	/**
	 * Valor dos Descontos em Antiguidade
	 */
	private BigDecimal valorDescontoAntiguidade;
	
	/**
	 * Valor dos Descontos de Inatividade
	 */
	private BigDecimal valorDescontoInatividade;
	
	/**
	 * Valor dos Créditos a Realizar
	 */
	private BigDecimal valorCreditosRealizar;
	
	/**
	 * Valor Total dos Descontos
	 */
	private BigDecimal valorTotalDescontos;
	
	/**
	 * Valor a Ser Negociado
	 */
	private BigDecimal valorASerNegociado;
	
	/**
	 * Valor da Entrada
	 */
	private BigDecimal valorEntrada;
	
	/**
	 * Número de Parcelas
	 */
	private Short numeroParcelas;
	
	/**
	 * Valor da Parcela
	 */
	private BigDecimal valorParcela;
	
	/**
	 * Valor a Ser Parcelado
	 */
	private BigDecimal valorASerParcelado;
	
	/**
	 * Solicitação de Restabelecimento
	 */
	private String solicitacaoRestabelecimento;
	
	/**
	 * Nome do Munícipio
	 */
	private String nomeMunicipio;
	
	/**
	 * Id do Funcionário
	 */
	private Integer idFuncionario;
	
	/**
	 * Unidade do Usuário
	 */
	private BigDecimal unidadeUsuario;
	
	/**
	 * Nome do Cliente do Parcelamento
	 */
	private String nomeClienteParcelamento;
	
	/**
	 * CPF do Cliente do Parcelamento
	 */
	private String cpfClienteParcelamento;
	
	/**
	 * RG do Cliente 
	 */
	private String rgCliente;
	
	/**
	 * orgao expedidor RG do Cliente 
	 */
	private String descOrgaoExpRgCliente;
	
	/**
	 * unidade federacao RG do Cliente 
	 */
	private String siglaUnidadeFederacaoRgCliente;


	private String mesAnoInicioParcelamento;
	
	private String mesAnoFinalParcelamento;
	
	/**
	 * RG do Cliente do Parcelamento 
	 */
	private String rgClienteParcelamento;
	
	
	private String taxaJuros;
	
	/**
	 * Valor dos Descontos de Sanções Regulamentares
	 */
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
    
	/**
	 * Valor Total dos Descontos sem Valor dos Creditos
	 */
	private BigDecimal valorTotalDescontosSemValorCreditos;
	
	public Integer getIdDevedor() {
		return idDevedor;
	}

	public BigDecimal getValorTotalDescontosSemValorCreditos() {
		return valorTotalDescontosSemValorCreditos;
	}

	public void setValorTotalDescontosSemValorCreditos(
			BigDecimal valorTotalDescontosSemValorCreditos) {
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

	/**
	 * @return Retorna o campo cpfClienteParcelamento.
	 */
	public String getCpfClienteParcelamento() {
		return cpfClienteParcelamento;
	}

	/**
	 * @param cpfClienteParcelamento O cpfClienteParcelamento a ser setado.
	 */
	public void setCpfClienteParcelamento(String cpfClienteParcelamento) {
		this.cpfClienteParcelamento = cpfClienteParcelamento;
	}

	/**
	 * @return Retorna o campo nomeClienteParcelamento.
	 */
	public String getNomeClienteParcelamento() {
		return nomeClienteParcelamento;
	}

	/**
	 * @param nomeClienteParcelamento O nomeClienteParcelamento a ser setado.
	 */
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

	public void setSiglaUnidadeFederacaoRgCliente(
			String siglaUnidadeFederacaoRgCliente) {
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

	public void setValorDescontoSancoesRegulamentares(
			BigDecimal valorDescontoSancoesRegulamentares) {
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
    	
//    	if (getValorTotalDescontosSemValorCreditos() != null &&
//    		!getValorTotalDescontosSemValorCreditos().equals(new BigDecimal("0.00"))){
//    		
//    		valorASerNegociadoSemDesconto = 
//    			valorASerNegociadoSemDesconto.add(getValorTotalDescontosSemValorCreditos());
//    	}
    	
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

	/**
	 * @return Returns the localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade The localidade to set.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Returns the setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial The setorComercial to set.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	
	

}
