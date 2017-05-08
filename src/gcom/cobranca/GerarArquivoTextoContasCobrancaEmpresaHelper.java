package gcom.cobranca;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.faturamento.conta.Conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GerarArquivoTextoContasCobrancaEmpresaHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idComandoEmpresaCobrancaConta;

	private Integer idEmpresa;

	private String nomeEmpresa;

	private Integer codigoSetorComercialInicial;

	private Integer codigoSetorComercialFinal;

	private BigDecimal valorMinimoConta;

	private BigDecimal valorMaximoConta;

	private Integer referenciaContaInicial;

	private Integer referenciaContaFinal;

	private Date dataVencimentoContaInicial;

	private Date dataVencimentoContaFinal;

	private Date dataExecucao;

	private Integer idImovel;

	private Integer idCliente;

	private String nomeCliente;

	private Integer idLocalidadeInicial;

	private Integer idLocalidadeFinal;

	private Integer idUnidadeNegocio;

	private String nomeUnidadeNegocio;

	private Date ultimaAlteracao;

	private Integer qtdeTotalContasCobranca;

	private BigDecimal valorTotalContasCobranca;

	private Integer qtdeContasCriterioComando;

	private BigDecimal valorContasCriterioComando;

	private Integer idCobrancaConta;

	private Integer idFaturamentoGrupo;

	private Integer idLocalidade;

	private String nomeLocalidade;

	private Short codigoRota;

	private Integer numeroSequencialRota;

	private String nomeClienteConta;

	private String nomeLogradouro;

	private String numeroImovel;

	private String complementoEndereco;

	private Integer codigoCep;

	private String nomeBairro;

	private String telefone;

	private String cpf;

	private String cnpj;

	private String rg;

	private Integer anoMesReferencia;

	private Date dataVencimento;

	private BigDecimal valorLigacaoAgua;

	private BigDecimal valorLigacaoEsgoto;

	private BigDecimal valorServicos;

	private BigDecimal valorCreditos;

	private BigDecimal valorFatura;

	private Integer anoControle;

	private Integer controle;

	private Integer codigoSetorComercial;

	private Integer numeroQuadra;

	private Short numeroLote;

	private Short numeroSublote;

	private Integer idClienteTipo;

	private Conta conta;

	private String nomeAbreviadoCliente;

	private String tipoLogradouro;

	private Integer idGerenciaRegional;

	private String nomeGerenciaRegional;

	private Integer quantidadeContas;

	private Short codigoLayoutTxt;

	private List<Conta> colecaoConta;

	private Integer idOrdemServico;

	private String nomeMunicipio;

	private String ligacaoAguaSituacao;

	private String ligacaoEsgotoSituacao;

	private String fonteAbastecimento;

	private boolean imovelHidrometrado;
	
	private Short qtdEconomiasR1;
	private Short qtdEconomiasR2;
	private Short qtdEconomiasR3;
	private Short qtdEconomiasR4;
	
	private Short qtdEconomiasC1;
	private Short qtdEconomiasC2;
	private Short qtdEconomiasC3;
	private Short qtdEconomiasC4;
	
	private Short qtdEconomiasI1;
	private Short qtdEconomiasI2;
	private Short qtdEconomiasI3;
	private Short qtdEconomiasI4;

	private Short qtdEconomiasP1;
	private Short qtdEconomiasP2;
	private Short qtdEconomiasP3;
	private Short qtdEconomiasP4;
	
	private Short indicadorUsuarioDivida;
	private Integer codigoClienteUsuario;
	private String nomeClienteUsuario;
	private String telefone1ClienteUsuario;
	private String telefone2ClienteUsuario;
	private String telefone3ClienteUsuario;

	private Short indicadorResponsavelDivida;
	private Integer codigoClienteResponsavel;
	private String nomeClienteResponsavel;
	private String telefone1ClienteResponsavel;
	private String telefone2ClienteResponsavel;
	private String telefone3ClienteResponsavel;
	
	private Short indicadorProprietarioDivida;
	private Integer codigoClienteProprietario;
	private String nomeClienteProprietario;
	private String telefone1ClienteProprietario;
	private String telefone2ClienteProprietario;
	private String telefone3ClienteProprietario;
	
	private Short quantidadeParcelamentos;
	private Short quantidadeReparcelamentos;
	private Short quantidadeReparcelamentosConsecutivos;
	
	private Short indicadorImovelNegativado;
	private Date dataNegativacao;
	
	private Short indicadorDividaClienteAtual;
	
	private Date dataInicioCiclo;
	private Date dataFimCiclo;
	
	public GerarArquivoTextoContasCobrancaEmpresaHelper() {
	}

	public GerarArquivoTextoContasCobrancaEmpresaHelper(Integer idComandoEmpresaCobrancaConta, Integer idEmpresa, String nomeEmpresa, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, BigDecimal valorMinimoConta, BigDecimal valorMaximoConta, Integer referenciaContaInicial, Integer referenciaContaFinal, Date dataVencimentoContaInicial,
			Date dataVencimentoContaFinal, Date dataExecucao, Integer idImovel, Integer idCliente, String nomeCliente, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idUnidadeNegocio, String nomeUnidadeNegocio, Integer qtdeTotalContasCobranca, BigDecimal valorTotalContasCobranca, Date ultimaAlteracao) {
		super();

		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMaximoConta = valorMaximoConta;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
		this.dataExecucao = dataExecucao;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
		this.valorTotalContasCobranca = valorTotalContasCobranca;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public GerarArquivoTextoContasCobrancaEmpresaHelper(Integer idComandoEmpresaCobrancaConta, Integer idEmpresa, String nomeEmpresa, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, BigDecimal valorMinimoConta, BigDecimal valorMaximoConta, Integer referenciaContaInicial, Integer referenciaContaFinal, Date dataVencimentoContaInicial,
			Date dataVencimentoContaFinal, Date dataExecucao, Integer idImovel, Integer idCliente, String nomeCliente, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idUnidadeNegocio, String nomeUnidadeNegocio, Integer qtdeTotalContasCobranca, BigDecimal valorTotalContasCobranca, Integer qtdeContasCriterioComando,
			BigDecimal valorContasCriterioComando, Date ultimaAlteracao) {
		super();

		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMaximoConta = valorMaximoConta;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
		this.dataExecucao = dataExecucao;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
		this.valorTotalContasCobranca = valorTotalContasCobranca;
		this.qtdeContasCriterioComando = qtdeContasCriterioComando;
		this.valorContasCriterioComando = valorContasCriterioComando;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public GerarArquivoTextoContasCobrancaEmpresaHelper(Integer idComandoEmpresaCobrancaConta, Integer idEmpresa, String nomeEmpresa, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, BigDecimal valorMinimoConta, BigDecimal valorMaximoConta, Integer referenciaContaInicial, Integer referenciaContaFinal, Date dataVencimentoContaInicial,
			Date dataVencimentoContaFinal, Date dataExecucao, Integer idImovel, Integer idCliente, String nomeCliente, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idUnidadeNegocio, String nomeUnidadeNegocio, Date ultimaAlteracao) {
		super();

		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
		this.idEmpresa = idEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
		this.valorMinimoConta = valorMinimoConta;
		this.valorMaximoConta = valorMaximoConta;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
		this.dataExecucao = dataExecucao;
		this.idImovel = idImovel;
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idLocalidadeInicial = idLocalidadeInicial;
		this.idLocalidadeFinal = idLocalidadeFinal;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getIdComandoEmpresaCobrancaConta() {
		return idComandoEmpresaCobrancaConta;
	}

	public void setIdComandoEmpresaCobrancaConta(Integer idComandoEmpresaCobrancaConta) {
		this.idComandoEmpresaCobrancaConta = idComandoEmpresaCobrancaConta;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public BigDecimal getValorMinimoConta() {
		return valorMinimoConta;
	}

	public void setValorMinimoConta(BigDecimal valorMinimoConta) {
		this.valorMinimoConta = valorMinimoConta;
	}

	public BigDecimal getValorMaximoConta() {
		return valorMaximoConta;
	}

	public void setValorMaximoConta(BigDecimal valorMaximoConta) {
		this.valorMaximoConta = valorMaximoConta;
	}

	public Integer getReferenciaContaInicial() {
		return referenciaContaInicial;
	}

	public void setReferenciaContaInicial(Integer referenciaContaInicial) {
		this.referenciaContaInicial = referenciaContaInicial;
	}

	public Integer getReferenciaContaFinal() {
		return referenciaContaFinal;
	}

	public void setReferenciaContaFinal(Integer referenciaContaFinal) {
		this.referenciaContaFinal = referenciaContaFinal;
	}

	public Date getDataVencimentoContaInicial() {
		return dataVencimentoContaInicial;
	}

	public void setDataVencimentoContaInicial(Date dataVencimentoContaInicial) {
		this.dataVencimentoContaInicial = dataVencimentoContaInicial;
	}

	public Date getDataVencimentoContaFinal() {
		return dataVencimentoContaFinal;
	}

	public void setDataVencimentoContaFinal(Date dataVencimentoContaFinal) {
		this.dataVencimentoContaFinal = dataVencimentoContaFinal;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getQtdeTotalContasCobranca() {
		return qtdeTotalContasCobranca;
	}

	public void setQtdeTotalContasCobranca(Integer qtdeTotalContasCobranca) {
		this.qtdeTotalContasCobranca = qtdeTotalContasCobranca;
	}

	public BigDecimal getValorTotalContasCobranca() {
		return valorTotalContasCobranca;
	}

	public void setValorTotalContasCobranca(BigDecimal valorTotalContasCobranca) {
		this.valorTotalContasCobranca = valorTotalContasCobranca;
	}

	public Integer getQtdeContasCriterioComando() {
		return qtdeContasCriterioComando;
	}

	public void setQtdeContasCriterioComando(Integer qtdeContasCriterioComando) {
		this.qtdeContasCriterioComando = qtdeContasCriterioComando;
	}

	public BigDecimal getValorContasCriterioComando() {
		return valorContasCriterioComando;
	}

	public void setValorContasCriterioComando(BigDecimal valorContasCriterioComando) {
		this.valorContasCriterioComando = valorContasCriterioComando;
	}

	public Integer getIdCobrancaConta() {
		return idCobrancaConta;
	}

	public void setIdCobrancaConta(Integer idCobrancaConta) {
		this.idCobrancaConta = idCobrancaConta;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}

	public String getNomeClienteConta() {
		return nomeClienteConta;
	}

	public void setNomeClienteConta(String nomeClienteConta) {
		this.nomeClienteConta = nomeClienteConta;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public Integer getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(Integer codigoCep) {
		this.codigoCep = codigoCep;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getTelefone() {
		return telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorLigacaoAgua() {
		return valorLigacaoAgua;
	}

	public void setValorLigacaoAgua(BigDecimal valorLigacaoAgua) {
		this.valorLigacaoAgua = valorLigacaoAgua;
	}

	public BigDecimal getValorLigacaoEsgoto() {
		return valorLigacaoEsgoto;
	}

	public void setValorLigacaoEsgoto(BigDecimal valorLigacaoEsgoto) {
		this.valorLigacaoEsgoto = valorLigacaoEsgoto;
	}

	public BigDecimal getValorServicos() {
		return valorServicos;
	}

	public void setValorServicos(BigDecimal valorServicos) {
		this.valorServicos = valorServicos;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Integer getAnoControle() {
		return anoControle;
	}

	public void setAnoControle(Integer anoControle) {
		this.anoControle = anoControle;
	}

	public Integer getControle() {
		return controle;
	}

	public void setControle(Integer controle) {
		this.controle = controle;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Short getNumeroSublote() {
		return numeroSublote;
	}

	public void setNumeroSublote(Short numeroSublote) {
		this.numeroSublote = numeroSublote;
	}

	public Integer getIdClienteTipo() {
		return idClienteTipo;
	}

	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getNomeAbreviadoCliente() {
		return nomeAbreviadoCliente;
	}

	public void setNomeAbreviadoCliente(String nomeAbreviadoCliente) {
		this.nomeAbreviadoCliente = nomeAbreviadoCliente;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public Integer getQuantidadeContas() {
		return quantidadeContas;
	}

	public void setQuantidadeContas(Integer quantidadeContas) {
		this.quantidadeContas = quantidadeContas;
	}

	public Short getCodigoLayoutTxt() {
		return codigoLayoutTxt;
	}

	public void setCodigoLayoutTxt(Short codigoLayoutTxt) {
		this.codigoLayoutTxt = codigoLayoutTxt;
	}

	public List<Conta> getColecaoConta() {
		return colecaoConta;
	}

	public void setColecaoConta(List<Conta> colecaoConta) {
		this.colecaoConta = colecaoConta;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public String getFonteAbastecimento() {
		return fonteAbastecimento;
	}

	public void setFonteAbastecimento(String fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public boolean isImovelHidrometrado() {
		return imovelHidrometrado;
	}

	public void setImovelHidrometrado(boolean imovelHidrometrado) {
		this.imovelHidrometrado = imovelHidrometrado;
	}

	public Short getQtdEconomiasR1() {
		return qtdEconomiasR1;
	}

	public void setQtdEconomiasR1(Short qtdEconomiasR1) {
		this.qtdEconomiasR1 = qtdEconomiasR1;
	}

	public Short getQtdEconomiasR2() {
		return qtdEconomiasR2;
	}

	public void setQtdEconomiasR2(Short qtdEconomiasR2) {
		this.qtdEconomiasR2 = qtdEconomiasR2;
	}

	public Short getQtdEconomiasR3() {
		return qtdEconomiasR3;
	}

	public void setQtdEconomiasR3(Short qtdEconomiasR3) {
		this.qtdEconomiasR3 = qtdEconomiasR3;
	}

	public Short getQtdEconomiasR4() {
		return qtdEconomiasR4;
	}

	public void setQtdEconomiasR4(Short qtdEconomiasR4) {
		this.qtdEconomiasR4 = qtdEconomiasR4;
	}

	public Short getQtdEconomiasC1() {
		return qtdEconomiasC1;
	}

	public void setQtdEconomiasC1(Short qtdEconomiasC1) {
		this.qtdEconomiasC1 = qtdEconomiasC1;
	}

	public Short getQtdEconomiasC2() {
		return qtdEconomiasC2;
	}

	public void setQtdEconomiasC2(Short qtdEconomiasC2) {
		this.qtdEconomiasC2 = qtdEconomiasC2;
	}

	public Short getQtdEconomiasC3() {
		return qtdEconomiasC3;
	}

	public void setQtdEconomiasC3(Short qtdEconomiasC3) {
		this.qtdEconomiasC3 = qtdEconomiasC3;
	}

	public Short getQtdEconomiasC4() {
		return qtdEconomiasC4;
	}

	public void setQtdEconomiasC4(Short qtdEconomiasC4) {
		this.qtdEconomiasC4 = qtdEconomiasC4;
	}

	public Short getQtdEconomiasI1() {
		return qtdEconomiasI1;
	}

	public void setQtdEconomiasI1(Short qtdEconomiasI1) {
		this.qtdEconomiasI1 = qtdEconomiasI1;
	}

	public Short getQtdEconomiasI2() {
		return qtdEconomiasI2;
	}

	public void setQtdEconomiasI2(Short qtdEconomiasI2) {
		this.qtdEconomiasI2 = qtdEconomiasI2;
	}

	public Short getQtdEconomiasI3() {
		return qtdEconomiasI3;
	}

	public void setQtdEconomiasI3(Short qtdEconomiasI3) {
		this.qtdEconomiasI3 = qtdEconomiasI3;
	}

	public Short getQtdEconomiasI4() {
		return qtdEconomiasI4;
	}

	public void setQtdEconomiasI4(Short qtdEconomiasI4) {
		this.qtdEconomiasI4 = qtdEconomiasI4;
	}

	public Short getQtdEconomiasP1() {
		return qtdEconomiasP1;
	}

	public void setQtdEconomiasP1(Short qtdEconomiasP1) {
		this.qtdEconomiasP1 = qtdEconomiasP1;
	}

	public Short getQtdEconomiasP2() {
		return qtdEconomiasP2;
	}

	public void setQtdEconomiasP2(Short qtdEconomiasP2) {
		this.qtdEconomiasP2 = qtdEconomiasP2;
	}

	public Short getQtdEconomiasP3() {
		return qtdEconomiasP3;
	}

	public void setQtdEconomiasP3(Short qtdEconomiasP3) {
		this.qtdEconomiasP3 = qtdEconomiasP3;
	}

	public Short getQtdEconomiasP4() {
		return qtdEconomiasP4;
	}

	public void setQtdEconomiasP4(Short qtdEconomiasP4) {
		this.qtdEconomiasP4 = qtdEconomiasP4;
	}

	public Short getIndicadorUsuarioDivida() {
		return indicadorUsuarioDivida;
	}

	public void setIndicadorUsuarioDivida(Short indicadorUsuarioDivida) {
		this.indicadorUsuarioDivida = indicadorUsuarioDivida;
	}

	public Integer getCodigoClienteUsuario() {
		return codigoClienteUsuario;
	}

	public void setCodigoClienteUsuario(Integer codigoClienteUsuario) {
		this.codigoClienteUsuario = codigoClienteUsuario;
	}

	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	public String getTelefone1ClienteUsuario() {
		return telefone1ClienteUsuario;
	}

	public void setTelefone1ClienteUsuario(String telefone1ClienteUsuario) {
		this.telefone1ClienteUsuario = telefone1ClienteUsuario;
	}

	public String getTelefone2ClienteUsuario() {
		return telefone2ClienteUsuario;
	}

	public void setTelefone2ClienteUsuario(String telefone2ClienteUsuario) {
		this.telefone2ClienteUsuario = telefone2ClienteUsuario;
	}

	public String getTelefone3ClienteUsuario() {
		return telefone3ClienteUsuario;
	}

	public void setTelefone3ClienteUsuario(String telefone3ClienteUsuario) {
		this.telefone3ClienteUsuario = telefone3ClienteUsuario;
	}

	public Short getIndicadorResponsavelDivida() {
		return indicadorResponsavelDivida;
	}

	public void setIndicadorResponsavelDivida(Short indicadorResponsavelDivida) {
		this.indicadorResponsavelDivida = indicadorResponsavelDivida;
	}

	public Integer getCodigoClienteResponsavel() {
		return codigoClienteResponsavel;
	}

	public void setCodigoClienteResponsavel(Integer codigoClienteResponsavel) {
		this.codigoClienteResponsavel = codigoClienteResponsavel;
	}

	public String getNomeClienteResponsavel() {
		return nomeClienteResponsavel;
	}

	public void setNomeClienteResponsavel(String nomeClienteResponsavel) {
		this.nomeClienteResponsavel = nomeClienteResponsavel;
	}

	public String getTelefone1ClienteResponsavel() {
		return telefone1ClienteResponsavel;
	}

	public void setTelefone1ClienteResponsavel(String telefone1ClienteResponsavel) {
		this.telefone1ClienteResponsavel = telefone1ClienteResponsavel;
	}

	public String getTelefone2ClienteResponsavel() {
		return telefone2ClienteResponsavel;
	}

	public void setTelefone2ClienteResponsavel(String telefone2ClienteResponsavel) {
		this.telefone2ClienteResponsavel = telefone2ClienteResponsavel;
	}

	public String getTelefone3ClienteResponsavel() {
		return telefone3ClienteResponsavel;
	}

	public void setTelefone3ClienteResponsavel(String telefone3ClienteResponsavel) {
		this.telefone3ClienteResponsavel = telefone3ClienteResponsavel;
	}

	public Short getIndicadorProprietarioDivida() {
		return indicadorProprietarioDivida;
	}

	public void setIndicadorProprietarioDivida(Short indicadorProprietarioDivida) {
		this.indicadorProprietarioDivida = indicadorProprietarioDivida;
	}

	public Integer getCodigoClienteProprietario() {
		return codigoClienteProprietario;
	}

	public void setCodigoClienteProprietario(Integer codigoClienteProprietario) {
		this.codigoClienteProprietario = codigoClienteProprietario;
	}

	public String getNomeClienteProprietario() {
		return nomeClienteProprietario;
	}

	public void setNomeClienteProprietario(String nomeClienteProprietario) {
		this.nomeClienteProprietario = nomeClienteProprietario;
	}

	public String getTelefone1ClienteProprietario() {
		return telefone1ClienteProprietario;
	}

	public void setTelefone1ClienteProprietario(String telefone1ClienteProprietario) {
		this.telefone1ClienteProprietario = telefone1ClienteProprietario;
	}

	public String getTelefone2ClienteProprietario() {
		return telefone2ClienteProprietario;
	}

	public void setTelefone2ClienteProprietario(String telefone2ClienteProprietario) {
		this.telefone2ClienteProprietario = telefone2ClienteProprietario;
	}

	public String getTelefone3ClienteProprietario() {
		return telefone3ClienteProprietario;
	}

	public void setTelefone3ClienteProprietario(String telefone3ClienteProprietario) {
		this.telefone3ClienteProprietario = telefone3ClienteProprietario;
	}

	public Short getQuantidadeParcelamentos() {
		return quantidadeParcelamentos;
	}

	public void setQuantidadeParcelamentos(Short quantidadeParcelamentos) {
		this.quantidadeParcelamentos = quantidadeParcelamentos;
	}

	public Short getQuantidadeReparcelamentos() {
		return quantidadeReparcelamentos;
	}

	public void setQuantidadeReparcelamentos(Short quantidadeReparcelamentos) {
		this.quantidadeReparcelamentos = quantidadeReparcelamentos;
	}

	public Short getQuantidadeReparcelamentosConsecutivos() {
		return quantidadeReparcelamentosConsecutivos;
	}

	public void setQuantidadeReparcelamentosConsecutivos(Short quantidadeReparcelamentosConsecutivos) {
		this.quantidadeReparcelamentosConsecutivos = quantidadeReparcelamentosConsecutivos;
	}

	public Short getIndicadorImovelNegativado() {
		return indicadorImovelNegativado;
	}

	public void setIndicadorImovelNegativado(Short indicadorImovelNegativado) {
		this.indicadorImovelNegativado = indicadorImovelNegativado;
	}

	public Date getDataNegativacao() {
		return dataNegativacao;
	}

	public void setDataNegativacao(Date dataNegativacao) {
		this.dataNegativacao = dataNegativacao;
	}

	public Short getIndicadorDividaClienteAtual() {
		return indicadorDividaClienteAtual;
	}

	public void setIndicadorDividaClienteAtual(Short indicadorDividaClienteAtual) {
		this.indicadorDividaClienteAtual = indicadorDividaClienteAtual;
	}

	public Date getDataInicioCiclo() {
		return dataInicioCiclo;
	}

	public void setDataInicioCiclo(Date dataInicioCiclo) {
		this.dataInicioCiclo = dataInicioCiclo;
	}

	public Date getDataFimCiclo() {
		return dataFimCiclo;
	}

	public void setDataFimCiclo(Date dataFimCiclo) {
		this.dataFimCiclo = dataFimCiclo;
	}

	public void montarDadosClienteUsuario(ClienteImovel clienteImovel) {
		String[] telefones = obterTelefonesCliente(clienteImovel);
		
		this.indicadorUsuarioDivida = clienteImovel.getIndicadorNomeConta();
		this.codigoClienteUsuario = clienteImovel.getCliente().getId();
		this.nomeClienteUsuario = clienteImovel.getCliente().getNome();
		this.telefone1ClienteUsuario = telefones[0];
		this.telefone2ClienteUsuario = telefones[1];
		this.telefone3ClienteUsuario = telefones[2];
	}
	
	public void montarDadosClienteResponsavel(ClienteImovel clienteImovel) {
		String[] telefones = obterTelefonesCliente(clienteImovel);
		
		this.indicadorResponsavelDivida = clienteImovel.getIndicadorNomeConta();
		this.codigoClienteResponsavel = clienteImovel.getCliente().getId();
		this.nomeClienteResponsavel = clienteImovel.getCliente().getNome();
		this.telefone1ClienteResponsavel = telefones[0];
		this.telefone2ClienteResponsavel = telefones[1];
		this.telefone3ClienteResponsavel = telefones[2];
	}
	
	public void montarDadosClienteProprietario(ClienteImovel clienteImovel) {
		String[] telefones = obterTelefonesCliente(clienteImovel);
		
		this.indicadorProprietarioDivida = clienteImovel.getIndicadorNomeConta();
		this.codigoClienteProprietario = clienteImovel.getCliente().getId();
		this.nomeClienteProprietario = clienteImovel.getCliente().getNome();
		this.telefone1ClienteProprietario = telefones[0];
		this.telefone2ClienteProprietario = telefones[1];
		this.telefone3ClienteProprietario = telefones[2];
	}
	
	@SuppressWarnings("unchecked")
	private String[] obterTelefonesCliente(ClienteImovel clienteImovel) {
		String[] telefones = new String[3];
		
		Collection<ClienteFone> clienteFones = clienteImovel.getCliente().getClienteFones();
		List<ClienteFone> lista = new ArrayList<ClienteFone>();
		lista.addAll(clienteFones);
		
		for (int i = 0; i < lista.size() && lista.size() <= 3; i++) {
			ClienteFone clienteFone = lista.get(i);
			
			if (clienteFone.getDdd() != null && clienteFone.getTelefone() != null)
				telefones[i] = clienteFone.getDdd() + clienteFone.getTelefone();
		}
		return telefones;
	}
}
