package gcom.cadastro.sistemaparametro;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SistemaParametro extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	public static SistemaParametro instancia;

	private Integer parmId;

	private Integer anoMesFaturamento;

	private Integer anoMesArrecadacao;

	private String nomeEstado;

	private String nomeEmpresa;

	private String nomeAbreviadoEmpresa;

	private String cnpjEmpresa;

	private String numeroImovel;

	private String complementoEndereco;

	private String numeroTelefone;

	private String numeroRamal;

	private String nomeSiteEmpresa;

	private String numero0800Empresa;

	private String numeroFax;

	private String descricaoEmail;

	private Integer menorConsumoGrandeUsuario;

	private Integer areaMaximaTarifaSocial;

	private Integer consumoEnergiaMaximoTarifaSocial;

	private BigDecimal valorMinimoEmissaoConta;

	private BigDecimal valorSalarioMinimo;

	private Short menorEconomiasGrandeUsuario;

	private Short mesesMediaConsumo;

	private Short indicadorFaixaFalsa;

	private BigDecimal percentualFaixaFalsa;

	private Short indicadorUsoFaixaFalsa;

	private Short indicadorUsoFiscalizadorLeitura;

	private BigDecimal percentualFiscalizacaoLeitura;

	private Short indicadorPercentualFiscalizacaoLeitura;

	private Integer incrementoMaximoConsumoRateio;

	private Integer decrementoMaximoConsumoRateio;

	private Short numeroMinimoDiasEmissaoVencimento;

	private Short numeroDiasAdicionaisCorreios;

	private Short numeroMesesValidadeConta;

	private Date ultimaAlteracao;

	private Short numeroMaximoParcelasFinanciamento;

	private Short numeroMaximoParcelaCredito;

	private BigDecimal percentualTaxaJurosFinanciamento;

	private BigDecimal percentualMaximoAbatimento;

	private BigDecimal percentualFinanciamentoEntradaMinima;

	private Short numeroMesesMinimoAlteracaoVencimento;

	private Short numeroDiasExpiracaoAcesso;

	private Short numeroDiasMensagemExpiracao;

	private Short numeroMaximoLoginFalho;

	private Short numeroLayoutFebraban;

	private Short numeroDiasVencimentoCobranca;

	private Integer numeroMaximoFavorito;

	private Logradouro logradouro;

	private Bairro bairro;

	private EnderecoReferencia enderecoReferencia;

	private HidrometroCapacidade hidrometroCapacidade;

	private Short indicadorSugestaoTramite;

	private Integer diasMaximoAlterarOS;

	private Short diasReativacao;

	private Cep cep;

	private Short codigoEmpresaFebraban;

	private String imagemLogomarca;

	private String imagemRelatorio;

	private String imagemConta;

	private Integer ultimoRAManual;

	private String tituloPagina;

	private String mensagemSistema;

	private BigDecimal percentualToleranciaRateio;

	private ContaBancaria contaBancaria;

	private BigDecimal percentualMediaIndice;

	private Short numeroMaximoMesesSancoes;

	private String ipServidorSmtp;

	private String dsEmailResponsavel;

	private BigDecimal valorSegundaVia;

	private String numeroContratoPrestacaoServico;

	private Integer numeroDiasBloqueioCelular;

	private Cliente clientePresidente;

	private UnidadeOrganizacional unidadeOrganizacionalIdPresidencia;

	private Cliente clienteDiretorComercial;

	private short indicadorCobrarTaxaExtrato;

	private short indicadorAtualizacaoTarifaria;

	private Integer anoMesAtualizacaoTarifaria;

	private Short indicadorRoteiroEmpresa;

	private Short indicadorFaturamentoAntecipado;

	private Date dataHoraDadosDiariosArrecadacao;

	private Date dataHoraResumoAcoesCobranca;

	private Short codigoPeriodicidadeNegativacao;

	private String ipServidorModuloGerencial;

	private short numeroDiasCalculoAcrescimos;

	private Short numeroMesesCalculoCorrecao;

	private String inscricaoEstadual;

	private String inscricaoMunicipal;

	private Short indicadorTarifaCategoria;

	private Short numeroMesesMaximoCalculoMedia;

	private Short numeroDiasEncerramentoOrdemServico;

	private Short numeroDiasEncerramentoOSSeletiva;

	private Short numeroDiasAlteracaoVencimentoPosterior;

	private Short indicadorLoginUnico;

	private Short indicadorRetificacaoValorMenor;

	private Short indicadorLimiteAlteracaoVencimento;

	private Short indicadorCalculaVencimento;

	private Short indicadorTransferenciaComDebito;

	private Integer numeroExecucaoResumoNegativacao;

	private LogradouroBairro logradouroBairro;

	private LogradouroCep logradouroCep;

	private short indicadorParcelamentoConfirmado;

	private short indicadorCertidaoNegativaEfeitoPositivo;

	private short indicadorControlaAutoInfracao;

	private Short numeroDiasValidadeExtrato;

	private Short numeroDiasValidadeExtratoPermissaoEspecial;

	private Short numeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos;

	private Short indicadorExibeMensagemNaoReceberPagamento;

	private Short indicadorNaoMedidoTarifa;

	private Short indicadorControleTramitacaoRA;

	private Short indicadorDocumentoValido;

	private Short indicadorDebitoACobrarValidoCertidaoNegativa;

	private Short indicadorUsaRota;

	private Short indicadorCalculoPrevisaoRADiasUteis;

	private Short numeroDiasVariacaoConsumo;

	private Short indicadorSupressao;

	private Short indicadorQuadraFace;

	private Short indicadorEmissaoExtratoNaConsulta;

	private Integer numeroDiasPrazoRecursoAutoInfracao;

	private Short indicadorImprimeUsuarioSegundaVia;

	private short numeroDigitosQuadra;

	private Integer numeroMaximoParcelasAutoInfracao;

	private Integer numeroMaximoMesesCalculoConsumoAutoInfracao;

	private int qtdMaxContasRetificadas;

	private String urlhelp;

	private short indicadorAutorizacaoRelatorio;

	private short indicadorDividaAtiva;

	private BigDecimal percentualBonusSocial;

	private String nomeControlador;

	private ContaMotivoRetificacao contaMotivoRetificacaoPagamentoAntecipado;

	private String diasVencimentoAlternativo;

	private Integer intervaloHorasProcesso;

	private Integer horaInicioProcesso;

	private Short indicadorValidarLocalizacaoEncerramentoOS;

	private Integer indicadorControleExpiracaoSenhaPorGrupo;

	private Integer indicadorControleBloqueioSenhaAnterior;

	private Integer indicadorSenhaForte;

	private String versaoCelular;

	private Cliente clienteResponsavelProgramaEspecial;

	private ImovelPerfil perfilProgramaEspecial;

	private UnidadeOrganizacional unidadeOrganizacionalTramiteGrandeConsumidor;

	private Short numeroModuloDigitoVerificador;

	private Integer numeroMesesPesquisaImoveisRamaisSuprimidos;

	private Integer numeroAnoQuitacao;

	private Short indicadorContaParcelada;

	private Short indicadorCobrancaJudical;

	private Integer numeroMesesAnterioresParaDeclaracaoQuitacao;

	private Integer indicadorValorMovimentoArrecadador;

	private Short indicadorBloqueioContaMobile;

	private Short numeroDiasVencimentoEntradaParcelamento;

	private Date dataImplantacaoSistema;

	private Integer numeroDiasRevisaoComPermEspecial;

	private BigDecimal percentualConvergenciaRepavimentacao;

	private Short indicadorDocumentoObrigatorio;

	private Short indicadorDocumentoObrigatorioManterCliente;

	private Short indicadorConsultaDocumentoReceita;

	private String loginSenhaCdl;

	private Integer numeroLimiteAlteracaoVencimento;

	private BigDecimal valorExtratoFichaComp;

	private BigDecimal valorContaFichaComp;

	private ResolucaoDiretoria resolucaoDiretoria;

	private Short ultimoDiaVencimentoAlternativo;

	private Short indicadorAlteracaoInscricaoImovel;

	private Short indicadorDuplicidadeCliente;

	private Short indicadorNomeMenorDez;

	private Short indicadorNomeClienteGenerico;

	private Integer numeroMesesRetificarConta;

	private Short indicadorNormaRetificacao;

	private BigDecimal valorGuiaFichaComp;

	private BigDecimal valorDemonstrativoParcelamentoFichaComp;

	private Integer qtdeDiasEncerraOSFiscalizacao;

	private Integer qtdeDiasValidadeOSFiscalizacao;

	private Integer codigoTipoCalculoNaoMedido;

	private Short indicadorBloqueioContasContratoParcelDebitos;

	private Short indicadorBloqueioContasContratoParcelManterConta;

	private Short indicadorBloqueioDebitoACobrarContratoParcelDebito;

	private Short indicadorBloqueioGuiasOuAcresContratoParcelDebito;

	private Short indicadorBloqueioGuiasOuAcresContratoParcelManterConta;

	private Short indicadorBloqueioDebitoACobrarContratoParcelManterDebito;

	public String ipServidorModuloOperacional;

	private Integer numeroMaximoParcelasContratosParcelamento;

	private Integer qtdeDiasEnvioEmailConta;

	public static Short INDICADOR_USO_FAIXA_FALSA_SISTEMA_PARAMETRO = new Short("1");

	public static Short INDICADOR_USO_FAIXA_FALSA_ROTA = new Short("2");

	public static Short INDICADOR_FAIXA_FALSA_NAO_USO = new Short("2");

	public static Short INDICADOR_FAIXA_FALSA_ROTA = new Short("3");

	public static Short INDICADOR_PERCENTUAL_FISCALIZACAO_LEITURA_ROTA = new Short("2");

	public static Short INDICADOR_USO_FISCALIZADOR_LEITURA_SISTEMA_PARAMETRO = new Short("2");

	public static Short INDICADOR_USO_FISCALIZADOR_LEITURA_ROTA = new Short("3");

	public static Short CODIGO_EMPRESA_FEBRABAN_CAERN = new Short("6");

	public static Short CODIGO_EMPRESA_FEBRABAN_CAER = new Short("4");

	public static Short CODIGO_EMPRESA_FEBRABAN_COMPESA = new Short("18");

	public static Short CODIGO_EMPRESA_FEBRABAN_JUAZEIRO = new Short("135");

	public static Short CODIGO_EMPRESA_FEBRABAN_CAEMA = new Short("2");

	public static Short CODIGO_EMPRESA_FEBRABAN_COSANPA = new Short("22");
	public static Short CODIGO_EMPRESA_FEBRABAN_COSAMA = new Short("21");

	public static String EMPRESA_COMPESA = "COMPESA";

	public static String EMPRESA_CAER = "CAER";

	public static String EMPRESA_CAERN = "CAERN";

	public static String EMPRESA_SAAE = "SAAE-JUAZEIRO";

	public static String EMPRESA_COSANPA = "COSANPA";
	public static String EMPRESA_COSAMA = "COSAMA";

	public static Short CODIGO_PERIODICIDADE_NEGATIVACAO_DIARIA = new Short("0");

	public static Short CODIGO_PERIODICIDADE_NEGATIVACAO_SEMANAL = new Short("1");

	public static Short CODIGO_PERIODICIDADE_NEGATIVACAO_QUINZENAL = new Short("2");

	public static Short CODIGO_PERIODICIDADE_NEGATIVACAO_MENSAL = new Short("3");

	public static Short INDICADOR_TARIFA_CATEGORIA = new Short("1");

	public static Short INDICADOR_TARIFA_SUBCATEGORIA = new Short("2");

	public static Short INDICADOR_AUTORIZACAO_RELATORIO;

	public static short NUMERO_DIGITOS_QUADRA = 3;

	public String url2ViaConta;

	public String urlAcessoInternet;

	public Short indicadorPopupAtualizacaoCadastral;

	private Short indicadorUsoNMCliReceitaFantasia;

	public String mensagemContaBraile;

	private Short indicadorTabelaPrice;

	private Short indicadorVariaHierarquiaUnidade;

	private String descricaoDecreto;

	private byte[] arquivoDecreto;

	private Integer qtdeDiasValidadeOSAnormalidadeFiscalizacao;

	private String descricaoLeiEstTarif;

	private byte[] arquivoLeiEstTarif;

	private String descricaoLeiIndividualizacao;

	private byte[] arquivoLeiIndividualizacao;

	private String descricaoNormaCO;

	private byte[] arquivoNormaCO;

	private String descricaoNormaCM;

	private byte[] arquivoNormaCM;

	private Integer cdDadosDiarios;

	private Short numeroDiasEncerrarOsFiscalizacaoDecursoPrazo;

	private Cliente clienteFicticioParaAssociarOsPagamentosNaoIdentificados;

	private Integer numeroVezesSuspendeLeitura;
	private Integer numeroMesesLeituraSuspensa;
	private Integer numeroMesesReinicioSitEspFaturamento;
	
	private String dddTelefone;
	
	private Cliente clienteResponsavelNegativacao;
	
	private String descricaoAliquotaImposto;

	private BigDecimal valorAliquotaImposto;
	
	private Short indicadorGeracaoBoletoBB;

	public Short getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() {
		return numeroDiasEncerrarOsFiscalizacaoDecursoPrazo;
	}

	public void setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(Short numeroDiasEncerrarOsFiscalizacaoDecursoPrazo) {
		this.numeroDiasEncerrarOsFiscalizacaoDecursoPrazo = numeroDiasEncerrarOsFiscalizacaoDecursoPrazo;
	}

	public Integer getCdDadosDiarios() {
		return cdDadosDiarios;
	}

	public void setCdDadosDiarios(Integer cdDadosDiarios) {
		this.cdDadosDiarios = cdDadosDiarios;
	}

	public Short getIndicadorTabelaPrice() {
		return indicadorTabelaPrice;
	}

	public void setIndicadorTabelaPrice(Short indicadorTabelaPrice) {
		this.indicadorTabelaPrice = indicadorTabelaPrice;
	}

	public Short getIndicadorUsoNMCliReceitaFantasia() {
		return indicadorUsoNMCliReceitaFantasia;
	}

	public void setIndicadorUsoNMCliReceitaFantasia(Short indicadorUsoNMCliReceitaFantasia) {
		this.indicadorUsoNMCliReceitaFantasia = indicadorUsoNMCliReceitaFantasia;
	}

	public String getMensagemContaBraile() {
		return mensagemContaBraile;
	}

	public void setMensagemContaBraile(String mensagemContaBraile) {
		this.mensagemContaBraile = mensagemContaBraile;
	}

	public String getUrlAcessoInternet() {
		return urlAcessoInternet;
	}

	public void setUrlAcessoInternet(String urlAcessoInternet) {
		this.urlAcessoInternet = urlAcessoInternet;
	}

	public String getUrl2ViaConta() {
		return url2ViaConta;
	}

	public void setUrl2ViaConta(String url2ViaConta) {
		this.url2ViaConta = url2ViaConta;
	}

	public int getQtdMaxContasRetificadas() {
		return qtdMaxContasRetificadas;
	}

	public void setQtdMaxContasRetificadas(int qtdMaxContasRetificadas) {
		this.qtdMaxContasRetificadas = qtdMaxContasRetificadas;
	}

	public String getImagemConta() {
		return imagemConta;
	}

	public void setImagemConta(String imagemConta) {
		this.imagemConta = imagemConta;
	}

	public String getImagemLogomarca() {
		return imagemLogomarca;
	}

	public void setImagemLogomarca(String imagemLogomarca) {
		this.imagemLogomarca = imagemLogomarca;
	}

	public String getImagemRelatorio() {
		return imagemRelatorio;
	}

	public void setImagemRelatorio(String imagemRelatorio) {
		this.imagemRelatorio = imagemRelatorio;
	}

	/** default constructor */
	public SistemaParametro() {

	}

	/** minimal constructor */
	public SistemaParametro(Logradouro logradouro, Bairro bairro, EnderecoReferencia enderecoReferencia, HidrometroCapacidade hidrometroCapacidade, Cep cep) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.enderecoReferencia = enderecoReferencia;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.cep = cep;
	}

	public Integer getDiasMaximoAlterarOS() {
		return diasMaximoAlterarOS;
	}

	public void setDiasMaximoAlterarOS(Integer diasMaximoAlterarOS) {
		this.diasMaximoAlterarOS = diasMaximoAlterarOS;
	}

	public Short getDiasReativacao() {
		return diasReativacao;
	}

	public void setDiasReativacao(Short diasReativacao) {
		this.diasReativacao = diasReativacao;
	}

	public Short getIndicadorSugestaoTramite() {
		return indicadorSugestaoTramite;
	}

	public void setIndicadorSugestaoTramite(Short indicadorSugestaoTramite) {
		this.indicadorSugestaoTramite = indicadorSugestaoTramite;
	}

	public Integer getParmId() {
		return this.parmId;
	}

	public void setParmId(Integer parmId) {
		this.parmId = parmId;
	}

	public Integer getAnoMesFaturamento() {
		return this.anoMesFaturamento;
	}

	public void setAnoMesFaturamento(Integer anoMesFaturamento) {
		this.anoMesFaturamento = anoMesFaturamento;
	}

	public Integer getAnoMesArrecadacao() {
		return this.anoMesArrecadacao;
	}

	public void setAnoMesArrecadacao(Integer anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getNomeEmpresa() {
		return this.nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeAbreviadoEmpresa() {
		return this.nomeAbreviadoEmpresa;
	}

	public void setNomeAbreviadoEmpresa(String nomeAbreviadoEmpresa) {
		this.nomeAbreviadoEmpresa = nomeAbreviadoEmpresa;
	}

	public String getCnpjEmpresa() {
		return this.cnpjEmpresa;
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public String getNumeroImovel() {
		return this.numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getComplementoEndereco() {
		return this.complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getNumeroTelefone() {
		return this.numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getNumeroRamal() {
		return this.numeroRamal;
	}

	public void setNumeroRamal(String numeroRamal) {
		this.numeroRamal = numeroRamal;
	}

	public String getNumeroFax() {
		return this.numeroFax;
	}

	public void setNumeroFax(String numeroFax) {
		this.numeroFax = numeroFax;
	}

	public String getDescricaoEmail() {
		return this.descricaoEmail;
	}

	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}

	public Integer getMenorConsumoGrandeUsuario() {
		return this.menorConsumoGrandeUsuario;
	}

	public void setMenorConsumoGrandeUsuario(Integer menorConsumoGrandeUsuario) {
		this.menorConsumoGrandeUsuario = menorConsumoGrandeUsuario;
	}

	public Integer getAreaMaximaTarifaSocial() {
		return this.areaMaximaTarifaSocial;
	}

	public void setAreaMaximaTarifaSocial(Integer areaMaximaTarifaSocial) {
		this.areaMaximaTarifaSocial = areaMaximaTarifaSocial;
	}

	public Integer getConsumoEnergiaMaximoTarifaSocial() {
		return this.consumoEnergiaMaximoTarifaSocial;
	}

	public void setConsumoEnergiaMaximoTarifaSocial(Integer consumoEnergiaMaximoTarifaSocial) {
		this.consumoEnergiaMaximoTarifaSocial = consumoEnergiaMaximoTarifaSocial;
	}

	public BigDecimal getValorMinimoEmissaoConta() {
		return this.valorMinimoEmissaoConta;
	}

	public void setValorMinimoEmissaoConta(BigDecimal valorMinimoEmissaoConta) {
		this.valorMinimoEmissaoConta = valorMinimoEmissaoConta;
	}

	public BigDecimal getValorSalarioMinimo() {
		return this.valorSalarioMinimo;
	}

	public void setValorSalarioMinimo(BigDecimal valorSalarioMinimo) {
		this.valorSalarioMinimo = valorSalarioMinimo;
	}

	public Short getMenorEconomiasGrandeUsuario() {
		return this.menorEconomiasGrandeUsuario;
	}

	public void setMenorEconomiasGrandeUsuario(Short menorEconomiasGrandeUsuario) {
		this.menorEconomiasGrandeUsuario = menorEconomiasGrandeUsuario;
	}

	public Short getMesesMediaConsumo() {
		return this.mesesMediaConsumo;
	}

	public void setMesesMediaConsumo(Short mesesMediaConsumo) {
		this.mesesMediaConsumo = mesesMediaConsumo;
	}

	public Short getIndicadorFaixaFalsa() {
		return this.indicadorFaixaFalsa;
	}

	public void setIndicadorFaixaFalsa(Short indicadorFaixaFalsa) {
		this.indicadorFaixaFalsa = indicadorFaixaFalsa;
	}

	public BigDecimal getPercentualFaixaFalsa() {
		return this.percentualFaixaFalsa;
	}

	public void setPercentualFaixaFalsa(BigDecimal percentualFaixaFalsa) {
		this.percentualFaixaFalsa = percentualFaixaFalsa;
	}

	public Short getIndicadorUsoFaixaFalsa() {
		return this.indicadorUsoFaixaFalsa;
	}

	public void setIndicadorUsoFaixaFalsa(Short indicadorUsoFaixaFalsa) {
		this.indicadorUsoFaixaFalsa = indicadorUsoFaixaFalsa;
	}

	public Short getIndicadorUsoFiscalizadorLeitura() {
		return this.indicadorUsoFiscalizadorLeitura;
	}

	public void setIndicadorUsoFiscalizadorLeitura(Short indicadorUsoFiscalizadorLeitura) {
		this.indicadorUsoFiscalizadorLeitura = indicadorUsoFiscalizadorLeitura;
	}

	public BigDecimal getPercentualFiscalizacaoLeitura() {
		return this.percentualFiscalizacaoLeitura;
	}

	public void setPercentualFiscalizacaoLeitura(BigDecimal percentualFiscalizacaoLeitura) {
		this.percentualFiscalizacaoLeitura = percentualFiscalizacaoLeitura;
	}

	public Short getIndicadorPercentualFiscalizacaoLeitura() {
		return this.indicadorPercentualFiscalizacaoLeitura;
	}

	public void setIndicadorPercentualFiscalizacaoLeitura(Short indicadorPercentualFiscalizacaoLeitura) {
		this.indicadorPercentualFiscalizacaoLeitura = indicadorPercentualFiscalizacaoLeitura;
	}

	public Integer getIncrementoMaximoConsumoRateio() {
		return this.incrementoMaximoConsumoRateio;
	}

	public void setIncrementoMaximoConsumoRateio(Integer incrementoMaximoConsumoRateio) {
		this.incrementoMaximoConsumoRateio = incrementoMaximoConsumoRateio;
	}

	public Integer getDecrementoMaximoConsumoRateio() {
		return this.decrementoMaximoConsumoRateio;
	}

	public void setDecrementoMaximoConsumoRateio(Integer decrementoMaximoConsumoRateio) {
		this.decrementoMaximoConsumoRateio = decrementoMaximoConsumoRateio;
	}

	public Short getNumeroMinimoDiasEmissaoVencimento() {
		return this.numeroMinimoDiasEmissaoVencimento;
	}

	public void setNumeroMinimoDiasEmissaoVencimento(Short numeroMinimoDiasEmissaoVencimento) {
		this.numeroMinimoDiasEmissaoVencimento = numeroMinimoDiasEmissaoVencimento;
	}

	public Short getNumeroDiasAdicionaisCorreios() {
		return this.numeroDiasAdicionaisCorreios;
	}

	public void setNumeroDiasAdicionaisCorreios(Short numeroDiasAdicionaisCorreios) {
		this.numeroDiasAdicionaisCorreios = numeroDiasAdicionaisCorreios;
	}

	public Short getNumeroMesesValidadeConta() {
		return this.numeroMesesValidadeConta;
	}

	public void setNumeroMesesValidadeConta(Short numeroMesesValidadeConta) {
		this.numeroMesesValidadeConta = numeroMesesValidadeConta;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getNumeroMaximoParcelasFinanciamento() {
		return this.numeroMaximoParcelasFinanciamento;
	}

	public void setNumeroMaximoParcelasFinanciamento(Short numeroMaximoParcelasFinanciamento) {
		this.numeroMaximoParcelasFinanciamento = numeroMaximoParcelasFinanciamento;
	}

	public BigDecimal getPercentualTaxaJurosFinanciamento() {
		return this.percentualTaxaJurosFinanciamento;
	}

	public void setPercentualTaxaJurosFinanciamento(BigDecimal percentualTaxaJurosFinanciamento) {
		this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
	}

	public BigDecimal getPercentualMaximoAbatimento() {
		return this.percentualMaximoAbatimento;
	}

	public void setPercentualMaximoAbatimento(BigDecimal percentualMaximoAbatimento) {
		this.percentualMaximoAbatimento = percentualMaximoAbatimento;
	}

	public BigDecimal getPercentualFinanciamentoEntradaMinima() {
		return this.percentualFinanciamentoEntradaMinima;
	}

	public void setPercentualFinanciamentoEntradaMinima(BigDecimal percentualFinanciamentoEntradaMinima) {
		this.percentualFinanciamentoEntradaMinima = percentualFinanciamentoEntradaMinima;
	}

	public Short getNumeroMesesMinimoAlteracaoVencimento() {
		return this.numeroMesesMinimoAlteracaoVencimento;
	}

	public void setNumeroMesesMinimoAlteracaoVencimento(Short numeroMesesMinimoAlteracaoVencimento) {
		this.numeroMesesMinimoAlteracaoVencimento = numeroMesesMinimoAlteracaoVencimento;
	}

	public Short getNumeroDiasExpiracaoAcesso() {
		return this.numeroDiasExpiracaoAcesso;
	}

	public void setNumeroDiasExpiracaoAcesso(Short numeroDiasExpiracaoAcesso) {
		this.numeroDiasExpiracaoAcesso = numeroDiasExpiracaoAcesso;
	}

	public Short getNumeroDiasMensagemExpiracao() {
		return this.numeroDiasMensagemExpiracao;
	}

	public void setNumeroDiasMensagemExpiracao(Short numeroDiasMensagemExpiracao) {
		this.numeroDiasMensagemExpiracao = numeroDiasMensagemExpiracao;
	}

	public Short getNumeroMaximoLoginFalho() {
		return this.numeroMaximoLoginFalho;
	}

	public void setNumeroMaximoLoginFalho(Short numeroMaximoLoginFalho) {
		this.numeroMaximoLoginFalho = numeroMaximoLoginFalho;
	}

	public Short getNumeroLayoutFebraban() {
		return this.numeroLayoutFebraban;
	}

	public void setNumeroLayoutFebraban(Short numeroLayoutFebraban) {
		this.numeroLayoutFebraban = numeroLayoutFebraban;
	}

	public Short getNumeroDiasVencimentoCobranca() {
		return this.numeroDiasVencimentoCobranca;
	}

	public void setNumeroDiasVencimentoCobranca(Short numeroDiasVencimentoCobranca) {
		this.numeroDiasVencimentoCobranca = numeroDiasVencimentoCobranca;
	}

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public EnderecoReferencia getEnderecoReferencia() {
		return this.enderecoReferencia;
	}

	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return this.hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public Cep getCep() {
		return this.cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public Short getCodigoEmpresaFebraban() {
		return codigoEmpresaFebraban;
	}

	public void setCodigoEmpresaFebraban(Short codigoEmpresaFebraban) {
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
	}

	public String toString() {
		return new ToStringBuilder(this).append("parmId", getParmId()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof SistemaParametro)) {
			return false;
		}
		SistemaParametro castOther = (SistemaParametro) other;

		return new EqualsBuilder()
				.append(this.getAnoMesFaturamento(), castOther.getAnoMesFaturamento())
				.append(this.getAnoMesArrecadacao(), castOther.getAnoMesArrecadacao())
				.append(this.getNomeEstado(), castOther.getNomeEstado())
				.append(this.getNomeEmpresa(), castOther.getNomeEmpresa())
				.append(this.getNomeAbreviadoEmpresa(), castOther.getNomeAbreviadoEmpresa())
				.append(this.getCnpjEmpresa(), castOther.getCnpjEmpresa())
				.append(this.getNumeroImovel(), castOther.getNumeroImovel())
				.append(this.getComplementoEndereco(), castOther.getComplementoEndereco())
				.append(this.getDddTelefone(), castOther.getDddTelefone())
				.append(this.getNumeroTelefone(), castOther.getNumeroTelefone())
				.append(this.getNumeroRamal(), castOther.getNumeroRamal())
				.append(this.getNumeroFax(), castOther.getNumeroFax())
				.append(this.getDescricaoEmail(), castOther.getDescricaoEmail())
				.append(this.getMenorConsumoGrandeUsuario(), castOther.getMenorConsumoGrandeUsuario())
				.append(this.getValorMinimoEmissaoConta(), castOther.getValorMinimoEmissaoConta())
				.append(this.getMenorEconomiasGrandeUsuario(), castOther.getMenorEconomiasGrandeUsuario())
				.append(this.getMesesMediaConsumo(), castOther.getMesesMediaConsumo())
				.append(this.getIndicadorFaixaFalsa(), castOther.getIndicadorFaixaFalsa())
				.append(this.getPercentualFaixaFalsa(), castOther.getPercentualFaixaFalsa())
				.append(this.getIndicadorUsoFaixaFalsa(), castOther.getIndicadorUsoFaixaFalsa())
				.append(this.getIndicadorUsoFiscalizadorLeitura(), castOther.getIndicadorUsoFiscalizadorLeitura())
				.append(this.getPercentualFiscalizacaoLeitura(), castOther.getPercentualFiscalizacaoLeitura())
				.append(this.getIndicadorPercentualFiscalizacaoLeitura(), castOther.getIndicadorPercentualFiscalizacaoLeitura())
				.append(this.getIncrementoMaximoConsumoRateio(), castOther.getIncrementoMaximoConsumoRateio())
				.append(this.getDecrementoMaximoConsumoRateio(), castOther.getDecrementoMaximoConsumoRateio())
				.append(this.getPercentualToleranciaRateio(), castOther.getPercentualToleranciaRateio())
				.append(this.getNumeroMinimoDiasEmissaoVencimento(), castOther.getNumeroMinimoDiasEmissaoVencimento())
				.append(this.getNumeroDiasAdicionaisCorreios(), castOther.getNumeroDiasAdicionaisCorreios())
				.append(this.getNumeroMesesValidadeConta(), castOther.getNumeroMesesValidadeConta())
				.append(this.getUltimaAlteracao(), castOther.getUltimaAlteracao())
				.append(this.getNumeroMaximoParcelasFinanciamento(), castOther.numeroMaximoParcelasFinanciamento)
				.append(this.getPercentualTaxaJurosFinanciamento(), castOther.percentualTaxaJurosFinanciamento)
				.append(this.getPercentualMaximoAbatimento(), castOther.percentualMaximoAbatimento)
				.append(this.getPercentualFinanciamentoEntradaMinima(), castOther.percentualFinanciamentoEntradaMinima)
				.append(this.getDiasVencimentoAlternativo(), castOther.getDiasVencimentoAlternativo())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder()
				.append(getAnoMesFaturamento())
				.append(getAnoMesArrecadacao())
				.append(getNomeEstado())
				.append(getNomeEmpresa())
				.append(getNomeAbreviadoEmpresa())
				.append(getCnpjEmpresa())
				.append(getNumeroImovel())
				.append(getComplementoEndereco())
				.append(getDddTelefone())
				.append(getNumeroTelefone())
				.append(getNumeroRamal())
				.append(getNumeroFax())
				.append(getDescricaoEmail())
				.append(getMenorConsumoGrandeUsuario())
				.append(getValorMinimoEmissaoConta())
				.append(getMenorEconomiasGrandeUsuario())
				.append(getMesesMediaConsumo())
				.append(getIndicadorFaixaFalsa())
				.append(getPercentualFaixaFalsa())
				.append(getIndicadorUsoFaixaFalsa())
				.append(getIndicadorUsoFiscalizadorLeitura())
				.append(getPercentualFiscalizacaoLeitura())
				.append(getIndicadorPercentualFiscalizacaoLeitura())
				.append(getIncrementoMaximoConsumoRateio())
				.append(getDecrementoMaximoConsumoRateio())
				.append(getPercentualToleranciaRateio())
				.append(getNumeroMinimoDiasEmissaoVencimento())
				.append(getNumeroDiasAdicionaisCorreios())
				.append(getNumeroMesesValidadeConta())
				.append(getUltimaAlteracao())
				.append(getNumeroMaximoParcelasFinanciamento())
				.append(getPercentualTaxaJurosFinanciamento())
				.append(getPercentualMaximoAbatimento())
				.append(getPercentualFinanciamentoEntradaMinima())
				.append(getDiasVencimentoAlternativo()).toHashCode();
	}

	public Integer getNumeroMaximoFavorito() {
		return numeroMaximoFavorito;
	}

	public void setNumeroMaximoFavorito(Integer numeroMaximoFavorito) {
		this.numeroMaximoFavorito = numeroMaximoFavorito;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public BigDecimal getPercentualMediaIndice() {
		return percentualMediaIndice;
	}

	public void setPercentualMediaIndice(BigDecimal percentualMediaIndice) {
		this.percentualMediaIndice = percentualMediaIndice;
	}

	public Integer getUltimoRAManual() {
		return ultimoRAManual;
	}

	public void setUltimoRAManual(Integer ultimoRAManual) {
		this.ultimoRAManual = ultimoRAManual;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public Short getNumeroMaximoParcelaCredito() {
		return numeroMaximoParcelaCredito;
	}

	public void setNumeroMaximoParcelaCredito(Short numeroMaximoParcelaCredito) {
		this.numeroMaximoParcelaCredito = numeroMaximoParcelaCredito;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		filtroSistemaParametro.adicionarParametro(new ParametroSimples(FiltroSistemaParametro.Parm_Id, this.getParmId()));

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouro");

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");

		return filtroSistemaParametro;
	}

	public String getMensagemSistema() {
		return mensagemSistema;
	}

	public void setMensagemSistema(String mensagemSistema) {
		this.mensagemSistema = mensagemSistema;
	}

	public Short getNumeroMaximoMesesSancoes() {
		return numeroMaximoMesesSancoes;
	}

	public void setNumeroMaximoMesesSancoes(Short numeroMaximoMesesSancoes) {
		this.numeroMaximoMesesSancoes = numeroMaximoMesesSancoes;
	}

	public String getDsEmailResponsavel() {
		return dsEmailResponsavel;
	}

	public void setDsEmailResponsavel(String dsEmailResponsavel) {
		this.dsEmailResponsavel = dsEmailResponsavel;
	}

	public String getIpServidorSmtp() {
		return ipServidorSmtp;
	}

	public void setIpServidorSmtp(String ipServidorSmtp) {
		this.ipServidorSmtp = ipServidorSmtp;
	}

	public Cliente getClienteDiretorComercial() {
		return clienteDiretorComercial;
	}

	public void setClienteDiretorComercial(Cliente clienteDiretorComercial) {
		this.clienteDiretorComercial = clienteDiretorComercial;
	}

	public Cliente getClientePresidente() {
		return clientePresidente;
	}

	public void setClientePresidente(Cliente clientePresidente) {
		this.clientePresidente = clientePresidente;
	}

	public BigDecimal getPercentualToleranciaRateio() {
		return percentualToleranciaRateio;
	}

	public void setPercentualToleranciaRateio(BigDecimal percentualToleranciaRateio) {
		this.percentualToleranciaRateio = percentualToleranciaRateio;
	}

	public String getNumeroContratoPrestacaoServico() {
		return numeroContratoPrestacaoServico;
	}

	public void setNumeroContratoPrestacaoServico(String numeroContratoPrestacaoServico) {
		this.numeroContratoPrestacaoServico = numeroContratoPrestacaoServico;
	}

	public short getIndicadorAtualizacaoTarifaria() {
		return indicadorAtualizacaoTarifaria;
	}

	public void setIndicadorAtualizacaoTarifaria(short indicadorAtualizacaoTarifaria) {
		this.indicadorAtualizacaoTarifaria = indicadorAtualizacaoTarifaria;
	}

	public short getIndicadorCobrarTaxaExtrato() {
		return indicadorCobrarTaxaExtrato;
	}

	public void setIndicadorCobrarTaxaExtrato(short indicadorCobrarTaxaExtrato) {
		this.indicadorCobrarTaxaExtrato = indicadorCobrarTaxaExtrato;
	}

	public Integer getAnoMesAtualizacaoTarifaria() {
		return anoMesAtualizacaoTarifaria;
	}

	public void setAnoMesAtualizacaoTarifaria(Integer anoMesAtualizacaoTarifaria) {
		this.anoMesAtualizacaoTarifaria = anoMesAtualizacaoTarifaria;
	}

	public Short getIndicadorRoteiroEmpresa() {
		return indicadorRoteiroEmpresa;
	}

	public void setIndicadorRoteiroEmpresa(Short indicadorRoteiroEmpresa) {
		this.indicadorRoteiroEmpresa = indicadorRoteiroEmpresa;
	}

	public Short getIndicadorFaturamentoAntecipado() {
		return indicadorFaturamentoAntecipado;
	}

	public void setIndicadorFaturamentoAntecipado(Short indicadorFaturamentoAntecipado) {
		this.indicadorFaturamentoAntecipado = indicadorFaturamentoAntecipado;
	}

	public Date getDataHoraDadosDiariosArrecadacao() {
		return dataHoraDadosDiariosArrecadacao;
	}

	public void setDataHoraDadosDiariosArrecadacao(Date dataHoraDadosDiariosArrecadacao) {
		this.dataHoraDadosDiariosArrecadacao = dataHoraDadosDiariosArrecadacao;
	}

	public Short getCodigoPeriodicidadeNegativacao() {
		return codigoPeriodicidadeNegativacao;
	}

	public void setCodigoPeriodicidadeNegativacao(Short codigoPeriodicidadeNegativacao) {
		this.codigoPeriodicidadeNegativacao = codigoPeriodicidadeNegativacao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacionalIdPresidencia() {
		return unidadeOrganizacionalIdPresidencia;
	}

	public void setUnidadeOrganizacionalIdPresidencia(UnidadeOrganizacional unidadeOrganizacionalIdPresidencia) {
		this.unidadeOrganizacionalIdPresidencia = unidadeOrganizacionalIdPresidencia;
	}

	public String getNomeSiteEmpresa() {
		return nomeSiteEmpresa;
	}

	public void setNomeSiteEmpresa(String nomeSiteEmpresa) {
		this.nomeSiteEmpresa = nomeSiteEmpresa;
	}

	public String getNumero0800Empresa() {
		return numero0800Empresa;
	}

	public void setNumero0800Empresa(String numero0800Empresa) {
		this.numero0800Empresa = numero0800Empresa;
	}

	public short getNumeroDiasCalculoAcrescimos() {
		return numeroDiasCalculoAcrescimos;
	}

	public void setNumeroDiasCalculoAcrescimos(short numeroDiasCalculoAcrescimos) {
		this.numeroDiasCalculoAcrescimos = numeroDiasCalculoAcrescimos;
	}

	public Short getNumeroMesesCalculoCorrecao() {
		return numeroMesesCalculoCorrecao;
	}

	public void setNumeroMesesCalculoCorrecao(Short numeroMesesCalculoCorrecao) {
		this.numeroMesesCalculoCorrecao = numeroMesesCalculoCorrecao;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getIpServidorModuloGerencial() {
		return ipServidorModuloGerencial;
	}

	public void setIpServidorModuloGerencial(String ipServidorModuloGerencial) {
		this.ipServidorModuloGerencial = ipServidorModuloGerencial;
	}

	public Short getIndicadorTarifaCategoria() {
		return indicadorTarifaCategoria;
	}

	public void setIndicadorTarifaCategoria(Short indicadorTarifaCategoria) {
		this.indicadorTarifaCategoria = indicadorTarifaCategoria;
	}

	public Date getDataHoraResumoAcoesCobranca() {
		return dataHoraResumoAcoesCobranca;
	}

	public void setDataHoraResumoAcoesCobranca(Date dataHoraResumoAcoesCobranca) {
		this.dataHoraResumoAcoesCobranca = dataHoraResumoAcoesCobranca;
	}

	public Short getNumeroMesesMaximoCalculoMedia() {
		return numeroMesesMaximoCalculoMedia;
	}

	public void setNumeroMesesMaximoCalculoMedia(Short numeroMesesMaximoCalculoMedia) {
		this.numeroMesesMaximoCalculoMedia = numeroMesesMaximoCalculoMedia;
	}

	public Short getNumeroDiasEncerramentoOrdemServico() {
		return numeroDiasEncerramentoOrdemServico;
	}

	public void setNumeroDiasEncerramentoOrdemServico(Short numeroDiasEncerramentoOrdemServico) {
		this.numeroDiasEncerramentoOrdemServico = numeroDiasEncerramentoOrdemServico;
	}

	public Short getNumeroDiasEncerramentoOSSeletiva() {
		return numeroDiasEncerramentoOSSeletiva;
	}

	public void setNumeroDiasEncerramentoOSSeletiva(Short numeroDiasEncerramentoOSSeletiva) {
		this.numeroDiasEncerramentoOSSeletiva = numeroDiasEncerramentoOSSeletiva;
	}

	public Short getNumeroDiasAlteracaoVencimentoPosterior() {
		return numeroDiasAlteracaoVencimentoPosterior;
	}

	public void setNumeroDiasAlteracaoVencimentoPosterior(Short numeroDiasAlteracaoVencimentoPosterior) {
		this.numeroDiasAlteracaoVencimentoPosterior = numeroDiasAlteracaoVencimentoPosterior;
	}

	public Short getIndicadorLoginUnico() {
		return indicadorLoginUnico;
	}

	public void setIndicadorLoginUnico(Short indicadorLoginUnico) {
		this.indicadorLoginUnico = indicadorLoginUnico;
	}

	public Short getIndicadorRetificacaoValorMenor() {
		return indicadorRetificacaoValorMenor;
	}

	public Short getIndicadorCalculaVencimento() {
		return indicadorCalculaVencimento;
	}

	public void setIndicadorCalculaVencimento(Short indicadorCalculaVencimento) {
		this.indicadorCalculaVencimento = indicadorCalculaVencimento;
	}

	public Short getIndicadorLimiteAlteracaoVencimento() {
		return indicadorLimiteAlteracaoVencimento;
	}

	public void setIndicadorLimiteAlteracaoVencimento(Short indicadorLimiteAlteracaoVencimento) {
		this.indicadorLimiteAlteracaoVencimento = indicadorLimiteAlteracaoVencimento;
	}

	public void setIndicadorRetificacaoValorMenor(Short indicadorRetificacaoValorMenor) {
		this.indicadorRetificacaoValorMenor = indicadorRetificacaoValorMenor;
	}

	public Integer getNumeroExecucaoResumoNegativacao() {
		return numeroExecucaoResumoNegativacao;
	}

	public void setNumeroExecucaoResumoNegativacao(Integer numeroExecucaoResumoNegativacao) {
		this.numeroExecucaoResumoNegativacao = numeroExecucaoResumoNegativacao;
	}

	public LogradouroBairro getLogradouroBairro() {
		return logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public short getIndicadorParcelamentoConfirmado() {
		return indicadorParcelamentoConfirmado;
	}

	public void setIndicadorParcelamentoConfirmado(short indicadorParcelamentoConfirmado) {
		this.indicadorParcelamentoConfirmado = indicadorParcelamentoConfirmado;
	}

	public Short getIndicadorTransferenciaComDebito() {
		return indicadorTransferenciaComDebito;
	}

	public void setIndicadorTransferenciaComDebito(Short indicadorTransferenciaComDebito) {
		this.indicadorTransferenciaComDebito = indicadorTransferenciaComDebito;
	}

	public short getIndicadorControlaAutoInfracao() {
		return indicadorControlaAutoInfracao;
	}

	public void setIndicadorControlaAutoInfracao(short indicadorControlaAutoInfracao) {
		this.indicadorControlaAutoInfracao = indicadorControlaAutoInfracao;
	}

	public Short getNumeroDiasValidadeExtrato() {
		return numeroDiasValidadeExtrato;
	}

	public void setNumeroDiasValidadeExtrato(Short numeroDiasValidadeExtrato) {
		this.numeroDiasValidadeExtrato = numeroDiasValidadeExtrato;
	}

	public Short getNumeroDiasValidadeExtratoPermissaoEspecial() {
		return numeroDiasValidadeExtratoPermissaoEspecial;
	}

	public void setNumeroDiasValidadeExtratoPermissaoEspecial(Short numeroDiasValidadeExtratoPermissaoEspecial) {
		this.numeroDiasValidadeExtratoPermissaoEspecial = numeroDiasValidadeExtratoPermissaoEspecial;
	}

	public BigDecimal getValorSegundaVia() {
		return valorSegundaVia;
	}

	public void setValorSegundaVia(BigDecimal valorSegundaVia) {
		this.valorSegundaVia = valorSegundaVia;
	}

	public Short getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos() {
		return numeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos;
	}

	public void setNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos(Short numeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos) {
		this.numeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos = numeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos;
	}

	public Short getIndicadorExibeMensagemNaoReceberPagamento() {
		return indicadorExibeMensagemNaoReceberPagamento;
	}

	public void setIndicadorExibeMensagemNaoReceberPagamento(Short indicadorExibeMensagemNaoReceberPagamento) {
		this.indicadorExibeMensagemNaoReceberPagamento = indicadorExibeMensagemNaoReceberPagamento;
	}

	public Short getIndicadorNaoMedidoTarifa() {
		return indicadorNaoMedidoTarifa;
	}

	public void setIndicadorNaoMedidoTarifa(Short indicadorNaoMedidoTarifa) {
		this.indicadorNaoMedidoTarifa = indicadorNaoMedidoTarifa;
	}

	public short getIndicadorCertidaoNegativaEfeitoPositivo() {
		return indicadorCertidaoNegativaEfeitoPositivo;
	}

	public void setIndicadorCertidaoNegativaEfeitoPositivo(short indicadorCertidaoNegativaEfeitoPositivo) {
		this.indicadorCertidaoNegativaEfeitoPositivo = indicadorCertidaoNegativaEfeitoPositivo;
	}

	public Short getIndicadorControleTramitacaoRA() {
		return indicadorControleTramitacaoRA;
	}

	public void setIndicadorControleTramitacaoRA(Short indicadorControleTramitacaoRA) {
		this.indicadorControleTramitacaoRA = indicadorControleTramitacaoRA;
	}

	public Short getIndicadorDocumentoValido() {
		return indicadorDocumentoValido;
	}

	public void setIndicadorDocumentoValido(Short indicadorDocumentoValido) {
		this.indicadorDocumentoValido = indicadorDocumentoValido;
	}

	public Short getIndicadorDebitoACobrarValidoCertidaoNegativa() {
		return indicadorDebitoACobrarValidoCertidaoNegativa;
	}

	public void setIndicadorDebitoACobrarValidoCertidaoNegativa(Short indicadorDebitoACobrarValidoCertidaoNegativa) {
		this.indicadorDebitoACobrarValidoCertidaoNegativa = indicadorDebitoACobrarValidoCertidaoNegativa;
	}

	public Short getIndicadorUsaRota() {
		return indicadorUsaRota;
	}

	public void setIndicadorUsaRota(Short indicadorUsaRota) {
		this.indicadorUsaRota = indicadorUsaRota;
	}

	public Short getIndicadorCalculoPrevisaoRADiasUteis() {
		return indicadorCalculoPrevisaoRADiasUteis;
	}

	public void setIndicadorCalculoPrevisaoRADiasUteis(Short indicadorCalculoPrevisaoRADiasUteis) {
		this.indicadorCalculoPrevisaoRADiasUteis = indicadorCalculoPrevisaoRADiasUteis;
	}

	public Short getNumeroDiasVariacaoConsumo() {
		return numeroDiasVariacaoConsumo;
	}

	public void setNumeroDiasVariacaoConsumo(Short numeroDiasVariacaoConsumo) {
		this.numeroDiasVariacaoConsumo = numeroDiasVariacaoConsumo;
	}

	public Short getIndicadorSupressao() {
		return indicadorSupressao;
	}

	public void setIndicadorSupressao(Short indicadorSupressao) {
		this.indicadorSupressao = indicadorSupressao;
	}

	public Short getIndicadorQuadraFace() {
		return indicadorQuadraFace;
	}

	public void setIndicadorQuadraFace(Short indicadorQuadraFace) {
		this.indicadorQuadraFace = indicadorQuadraFace;
	}

	public Short getIndicadorEmissaoExtratoNaConsulta() {
		return indicadorEmissaoExtratoNaConsulta;
	}

	public void setIndicadorEmissaoExtratoNaConsulta(Short indicadorEmissaoExtratoNaConsulta) {
		this.indicadorEmissaoExtratoNaConsulta = indicadorEmissaoExtratoNaConsulta;
	}

	public Integer getNumeroDiasPrazoRecursoAutoInfracao() {
		return numeroDiasPrazoRecursoAutoInfracao;
	}

	public void setNumeroDiasPrazoRecursoAutoInfracao(Integer numeroDiasPrazoRecursoAutoInfracao) {
		this.numeroDiasPrazoRecursoAutoInfracao = numeroDiasPrazoRecursoAutoInfracao;
	}

	public Short getIndicadorImprimeUsuarioSegundaVia() {
		return indicadorImprimeUsuarioSegundaVia;
	}

	public void setIndicadorImprimeUsuarioSegundaVia(Short indicadorImprimeUsuarioSegundaVia) {
		this.indicadorImprimeUsuarioSegundaVia = indicadorImprimeUsuarioSegundaVia;
	}

	public short getNumeroDigitosQuadra() {
		return numeroDigitosQuadra;
	}

	public void setNumeroDigitosQuadra(short numeroDigitosQuadra) {
		this.numeroDigitosQuadra = numeroDigitosQuadra;
	}

	public void setarConstantes() {
		NUMERO_DIGITOS_QUADRA = this.getNumeroDigitosQuadra();
		INDICADOR_AUTORIZACAO_RELATORIO = this.getIndicadorAutorizacaoRelatorio();
	}

	public String getUrlhelp() {
		return urlhelp;
	}

	public void setUrlhelp(String urlhelp) {
		this.urlhelp = urlhelp;
	}

	public Integer getNumeroMaximoMesesCalculoConsumoAutoInfracao() {
		return numeroMaximoMesesCalculoConsumoAutoInfracao;
	}

	public void setNumeroMaximoMesesCalculoConsumoAutoInfracao(Integer numeroMaximoMesesCalculoConsumoAutoInfracao) {
		this.numeroMaximoMesesCalculoConsumoAutoInfracao = numeroMaximoMesesCalculoConsumoAutoInfracao;
	}

	public Integer getNumeroMaximoParcelasAutoInfracao() {
		return numeroMaximoParcelasAutoInfracao;
	}

	public void setNumeroMaximoParcelasAutoInfracao(Integer numeroMaximoParcelasAutoInfracao) {
		this.numeroMaximoParcelasAutoInfracao = numeroMaximoParcelasAutoInfracao;
	}

	public short getIndicadorAutorizacaoRelatorio() {
		return indicadorAutorizacaoRelatorio;
	}

	public void setIndicadorAutorizacaoRelatorio(short indicadorAutorizacaoRelatorio) {
		this.indicadorAutorizacaoRelatorio = indicadorAutorizacaoRelatorio;
	}

	public short getIndicadorDividaAtiva() {
		return indicadorDividaAtiva;
	}

	public void setIndicadorDividaAtiva(short indicadorDividaAtiva) {
		this.indicadorDividaAtiva = indicadorDividaAtiva;
	}

	public BigDecimal getPercentualBonusSocial() {
		return percentualBonusSocial;
	}

	public void setPercentualBonusSocial(BigDecimal percentualBonusSocial) {
		this.percentualBonusSocial = percentualBonusSocial;
	}

	public String getNomeControlador() {
		return nomeControlador;
	}

	public void setNomeControlador(String nomeControlador) {
		this.nomeControlador = nomeControlador;
	}

	public String getDiasVencimentoAlternativo() {
		return diasVencimentoAlternativo;
	}

	public ContaMotivoRetificacao getContaMotivoRetificacaoPagamentoAntecipado() {
		return contaMotivoRetificacaoPagamentoAntecipado;
	}

	public void setContaMotivoRetificacaoPagamentoAntecipado(ContaMotivoRetificacao contaMotivoRetificacaoPagamentoAntecipado) {
		this.contaMotivoRetificacaoPagamentoAntecipado = contaMotivoRetificacaoPagamentoAntecipado;
	}

	public void setDiasVencimentoAlternativo(String diasVencimentoAlternativo) {
		this.diasVencimentoAlternativo = diasVencimentoAlternativo;
	}

	public Short getIndicadorValidarLocalizacaoEncerramentoOS() {
		return indicadorValidarLocalizacaoEncerramentoOS;
	}

	public void setIndicadorValidarLocalizacaoEncerramentoOS(Short indicadorValidarLocalizacaoEncerramentoOS) {
		this.indicadorValidarLocalizacaoEncerramentoOS = indicadorValidarLocalizacaoEncerramentoOS;
	}

	public String getEnderecoFormatado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null && !this.getLogradouroCep().getLogradouro().getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricao().trim();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricao().trim();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null && !this.getEnderecoReferencia().getDescricao().equals("")) {
					endereco = endereco + " - " + this.getEnderecoReferencia().getDescricao().trim();
				}
			}
			if (this.getNumeroImovel() != null && !this.getNumeroImovel().equals("")) {
				// concate o numero do imovel
				endereco = endereco + " - " + this.getNumeroImovel().trim();
			}

			if (this.getComplementoEndereco() != null && !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId().intValue() != 0) {
				endereco = endereco + " - " + this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " " + this.getLogradouroCep().getCep().getCepFormatado().trim();
			}

		}

		return endereco;
	}

	public String getEnderecoFormatadoAbreviado() {
		String endereco = null;

		// verifica se o logradouro é diferente de null
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			// verifica se o logradouro tipo é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null && !this.getLogradouroCep().getLogradouro().getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricaoAbreviada().equals("")) {
					// concatena o logradouro tipo
					endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricaoAbreviada().trim();
				}
			}
			// verifica se o logradouro titulo é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricaoAbreviada().equals("")) {
					// concatena o logradouro titulo
					endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricaoAbreviada().trim();
				}
			}

			// concatena o logradouro
			endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricaoAbreviada() != null && !this.getEnderecoReferencia().getDescricaoAbreviada().equals("")) {
					endereco = endereco + ", " + this.getEnderecoReferencia().getDescricaoAbreviada().trim();
				}
			}

			// concate o numero
			endereco = endereco + " " + this.getNumeroImovel().trim();

			if (this.getComplementoEndereco() != null && !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId().intValue() != 0) {
				endereco = endereco + " - " + this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado
				endereco = endereco + " " + this.getLogradouroCep().getCep().getCepFormatado().trim();
			}

		}

		return endereco;
	}

	public String getEnderecoFormatadoParte01() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null && !this.getLogradouroCep().getLogradouro().getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricao().trim();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricao().trim();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null && !this.getEnderecoReferencia().getDescricao().equals("")) {
					endereco = endereco + " - " + this.getEnderecoReferencia().getDescricao().trim();
				}
			}
			if (this.getNumeroImovel() != null && !this.getNumeroImovel().equals("")) {
				// concate o numero do imovel
				endereco = endereco + " - " + this.getNumeroImovel().trim();
			}

			if (this.getComplementoEndereco() != null && !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - " + this.getComplementoEndereco().trim();
			}

		}

		return endereco;
	}

	public String getEnderecoFormatadoParte02() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId().intValue() != 0) {
				endereco = this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " " + this.getLogradouroCep().getCep().getCepFormatado().trim();
			}

		}

		return endereco;
	}

	public String getEnderecoFormatadoSemComplemento() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null && this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null && !this.getLogradouroCep().getLogradouro().getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro().getLogradouroTipo().getDescricao().trim();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro().getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getLogradouroTitulo().getDescricao().trim();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " " + this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null && !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null && !this.getEnderecoReferencia().getDescricao().equals("")) {
					endereco = endereco + " - " + this.getEnderecoReferencia().getDescricao().trim();
				}
			}
			if (this.getNumeroImovel() != null && !this.getNumeroImovel().equals("")) {
				// concate o numero do imovel
				endereco = endereco + " - " + this.getNumeroImovel().trim();
			}

			if (this.getLogradouroBairro() != null && this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId().intValue() != 0) {
				endereco = endereco + " - " + this.getLogradouroBairro().getBairro().getNome().trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getId().intValue() != 0) {
					endereco = endereco + " " + this.getLogradouroBairro().getBairro().getMunicipio().getUnidadeFederacao().getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null && this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco + " " + this.getLogradouroCep().getCep().getCepFormatado().trim();
			}

		}

		return endereco;
	}

	public Integer getIntervaloHorasProcesso() {
		return intervaloHorasProcesso;
	}

	public void setIntervaloHorasProcesso(Integer intervaloHorasProcesso) {
		this.intervaloHorasProcesso = intervaloHorasProcesso;
	}

	public Integer getIndicadorControleExpiracaoSenhaPorGrupo() {
		return indicadorControleExpiracaoSenhaPorGrupo;
	}

	public void setIndicadorControleExpiracaoSenhaPorGrupo(Integer indicarControleExpiracaoSenhaPorGrupo) {
		this.indicadorControleExpiracaoSenhaPorGrupo = indicarControleExpiracaoSenhaPorGrupo;
	}

	public Integer getIndicadorControleBloqueioSenhaAnterior() {
		return indicadorControleBloqueioSenhaAnterior;
	}

	public Integer getHoraInicioProcesso() {
		return horaInicioProcesso;
	}

	public void setHoraInicioProcesso(Integer horaInicioProcesso) {
		this.horaInicioProcesso = horaInicioProcesso;
	}

	public void setIndicadorControleBloqueioSenhaAnterior(Integer indicarControleBloqueioSenha) {
		this.indicadorControleBloqueioSenhaAnterior = indicarControleBloqueioSenha;
	}

	public Integer getIndicadorSenhaForte() {
		return indicadorSenhaForte;
	}

	public void setIndicadorSenhaForte(Integer indicadorSenhaForte) {
		this.indicadorSenhaForte = indicadorSenhaForte;
	}

	public Cliente getClienteResponsavelProgramaEspecial() {
		return clienteResponsavelProgramaEspecial;
	}

	public void setClienteResponsavelProgramaEspecial(Cliente clienteResponsavelProgramaEspecial) {
		this.clienteResponsavelProgramaEspecial = clienteResponsavelProgramaEspecial;
	}

	public ImovelPerfil getPerfilProgramaEspecial() {
		return perfilProgramaEspecial;
	}

	public void setPerfilProgramaEspecial(ImovelPerfil perfilProgramaEspecial) {
		this.perfilProgramaEspecial = perfilProgramaEspecial;
	}

	public UnidadeOrganizacional getUnidadeOrganizacionalTramiteGrandeConsumidor() {
		return unidadeOrganizacionalTramiteGrandeConsumidor;
	}

	public void setUnidadeOrganizacionalTramiteGrandeConsumidor(UnidadeOrganizacional unidadeOrganizacionalTramiteGrandeConsumidor) {
		this.unidadeOrganizacionalTramiteGrandeConsumidor = unidadeOrganizacionalTramiteGrandeConsumidor;
	}

	public String getVersaoCelular() {
		return versaoCelular;
	}

	public void setVersaoCelular(String versaoCelular) {
		this.versaoCelular = versaoCelular;
	}

	public Short getNumeroModuloDigitoVerificador() {
		return numeroModuloDigitoVerificador;
	}

	public void setNumeroModuloDigitoVerificador(Short numeroModuloDigitoVerificador) {
		this.numeroModuloDigitoVerificador = numeroModuloDigitoVerificador;
	}

	public Integer getNumeroMesesPesquisaImoveisRamaisSuprimidos() {
		return numeroMesesPesquisaImoveisRamaisSuprimidos;
	}

	public void setNumeroMesesPesquisaImoveisRamaisSuprimidos(Integer numeroMesesPesquisaImoveisRamaisSuprimidos) {
		this.numeroMesesPesquisaImoveisRamaisSuprimidos = numeroMesesPesquisaImoveisRamaisSuprimidos;
	}

	public Integer getNumeroAnoQuitacao() {
		return numeroAnoQuitacao;
	}

	public void setNumeroAnoQuitacao(Integer numeroAnoQuitacao) {
		this.numeroAnoQuitacao = numeroAnoQuitacao;
	}

	public Short getIndicadorContaParcelada() {
		return indicadorContaParcelada;
	}

	public void setIndicadorContaParcelada(Short indicadorContaParcelada) {
		this.indicadorContaParcelada = indicadorContaParcelada;
	}

	public Short getIndicadorCobrancaJudical() {
		return indicadorCobrancaJudical;
	}

	public void setIndicadorCobrancaJudical(Short indicadorCobrancaJudical) {
		this.indicadorCobrancaJudical = indicadorCobrancaJudical;
	}

	public Integer getNumeroMesesAnterioresParaDeclaracaoQuitacao() {
		return numeroMesesAnterioresParaDeclaracaoQuitacao;
	}

	public void setNumeroMesesAnterioresParaDeclaracaoQuitacao(Integer numeroMesesAnterioresParaDeclaracaoQuitacao) {
		this.numeroMesesAnterioresParaDeclaracaoQuitacao = numeroMesesAnterioresParaDeclaracaoQuitacao;
	}

	public Integer getIndicadorValorMovimentoArrecadador() {
		return indicadorValorMovimentoArrecadador;
	}

	public void setIndicadorValorMovimentoArrecadador(Integer indicadorValorMovimentoArrecadador) {
		this.indicadorValorMovimentoArrecadador = indicadorValorMovimentoArrecadador;

	}

	public Short getIndicadorBloqueioContaMobile() {
		return indicadorBloqueioContaMobile;
	}

	public void setIndicadorBloqueioContaMobile(Short indicadorBloqueioContaMobile) {
		this.indicadorBloqueioContaMobile = indicadorBloqueioContaMobile;

	}

	public Short getNumeroDiasVencimentoEntradaParcelamento() {
		return numeroDiasVencimentoEntradaParcelamento;
	}

	public void setNumeroDiasVencimentoEntradaParcelamento(Short numeroDiasVencimentoEntradaParcelamento) {
		this.numeroDiasVencimentoEntradaParcelamento = numeroDiasVencimentoEntradaParcelamento;
	}

	public Date getDataImplantacaoSistema() {
		return dataImplantacaoSistema;
	}

	public void setDataImplantacaoSistema(Date dataImplantacaoSistema) {
		this.dataImplantacaoSistema = dataImplantacaoSistema;
	}

	public BigDecimal getPercentualConvergenciaRepavimentacao() {
		return percentualConvergenciaRepavimentacao;
	}

	public void setPercentualConvergenciaRepavimentacao(BigDecimal percentualConvergenciaRepavimentacao) {
		this.percentualConvergenciaRepavimentacao = percentualConvergenciaRepavimentacao;
	}

	public Integer getNumeroDiasRevisaoComPermEspecial() {
		return numeroDiasRevisaoComPermEspecial;
	}

	public void setNumeroDiasRevisaoComPermEspecial(Integer numeroDiasRevisaoComPermEspecial) {
		this.numeroDiasRevisaoComPermEspecial = numeroDiasRevisaoComPermEspecial;
	}

	public Short getIndicadorDocumentoObrigatorio() {
		return indicadorDocumentoObrigatorio;
	}

	public void setIndicadorDocumentoObrigatorio(Short indicadorDocumentoObrigatorio) {
		this.indicadorDocumentoObrigatorio = indicadorDocumentoObrigatorio;
	}

	public Short getIndicadorDocumentoObrigatorioManterCliente() {
		return indicadorDocumentoObrigatorioManterCliente;
	}

	public void setIndicadorDocumentoObrigatorioManterCliente(Short indicadorDocumentoObrigatorioManterCliente) {
		this.indicadorDocumentoObrigatorioManterCliente = indicadorDocumentoObrigatorioManterCliente;
	}

	public Integer getNumeroLimiteAlteracaoVencimento() {
		return numeroLimiteAlteracaoVencimento;
	}

	public void setNumeroLimiteAlteracaoVencimento(Integer numeroLimiteAlteracaoVencimento) {
		this.numeroLimiteAlteracaoVencimento = numeroLimiteAlteracaoVencimento;
	}

	public BigDecimal getValorExtratoFichaComp() {
		return valorExtratoFichaComp;
	}

	public void setValorExtratoFichaComp(BigDecimal valorExtratoFichaComp) {
		this.valorExtratoFichaComp = valorExtratoFichaComp;
	}

	public Short getIndicadorPopupAtualizacaoCadastral() {
		return indicadorPopupAtualizacaoCadastral;
	}

	public void setIndicadorPopupAtualizacaoCadastral(Short indicadorPopupAtualizacaoCadastral) {
		this.indicadorPopupAtualizacaoCadastral = indicadorPopupAtualizacaoCadastral;
	}

	public Short getIndicadorConsultaDocumentoReceita() {
		return indicadorConsultaDocumentoReceita;
	}

	public void setIndicadorConsultaDocumentoReceita(Short indicadorConsultaDocumentoReceita) {
		this.indicadorConsultaDocumentoReceita = indicadorConsultaDocumentoReceita;
	}

	public BigDecimal getValorContaFichaComp() {
		return valorContaFichaComp;
	}

	public void setValorContaFichaComp(BigDecimal valorContaFichaComp) {
		this.valorContaFichaComp = valorContaFichaComp;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public Integer getNumeroDiasBloqueioCelular() {
		return numeroDiasBloqueioCelular;
	}

	public void setNumeroDiasBloqueioCelular(Integer numeroDiasBloqueioCelular) {
		this.numeroDiasBloqueioCelular = numeroDiasBloqueioCelular;
	}

	public String getIpServidorModuloOperacional() {
		return ipServidorModuloOperacional;
	}

	public void setIpServidorModuloOperacional(String ipServidorModuloOperacional) {
		this.ipServidorModuloOperacional = ipServidorModuloOperacional;
	}

	public String getLoginSenhaCdl() {
		return loginSenhaCdl;
	}

	public void setLoginSenhaCdl(String loginSenhaCdl) {
		this.loginSenhaCdl = loginSenhaCdl;
	}

	public Short getUltimoDiaVencimentoAlternativo() {
		return ultimoDiaVencimentoAlternativo;
	}

	public void setUltimoDiaVencimentoAlternativo(Short ultimoDiaVencimentoAlternativo) {
		this.ultimoDiaVencimentoAlternativo = ultimoDiaVencimentoAlternativo;
	}

	public Short getIndicadorDuplicidadeCliente() {
		return indicadorDuplicidadeCliente;
	}

	public void setIndicadorDuplicidadeCliente(Short indicadorDuplicidadeCliente) {
		this.indicadorDuplicidadeCliente = indicadorDuplicidadeCliente;
	}

	public Short getIndicadorNomeClienteGenerico() {
		return indicadorNomeClienteGenerico;
	}

	public void setIndicadorNomeClienteGenerico(Short indicadorNomeClienteGenerico) {
		this.indicadorNomeClienteGenerico = indicadorNomeClienteGenerico;
	}

	public Short getIndicadorNomeMenorDez() {
		return indicadorNomeMenorDez;
	}

	public void setIndicadorNomeMenorDez(Short indicadorNomeMenorDez) {
		this.indicadorNomeMenorDez = indicadorNomeMenorDez;
	}

	public Integer getNumeroMesesRetificarConta() {
		return numeroMesesRetificarConta;
	}

	public void setNumeroMesesRetificarConta(Integer numeroMesesRetificarConta) {
		this.numeroMesesRetificarConta = numeroMesesRetificarConta;
	}

	public Short getIndicadorNormaRetificacao() {
		return indicadorNormaRetificacao;
	}

	public void setIndicadorNormaRetificacao(Short indicadorNormaRetificacao) {
		this.indicadorNormaRetificacao = indicadorNormaRetificacao;
	}

	public Short getIndicadorAlteracaoInscricaoImovel() {
		return indicadorAlteracaoInscricaoImovel;
	}

	public void setIndicadorAlteracaoInscricaoImovel(Short indicadorAlteracaoInscricaoImovel) {
		this.indicadorAlteracaoInscricaoImovel = indicadorAlteracaoInscricaoImovel;
	}

	public BigDecimal getValorGuiaFichaComp() {
		return valorGuiaFichaComp;
	}

	public void setValorGuiaFichaComp(BigDecimal valorGuiaFichaComp) {
		this.valorGuiaFichaComp = valorGuiaFichaComp;
	}

	public BigDecimal getValorDemonstrativoParcelamentoFichaComp() {
		return valorDemonstrativoParcelamentoFichaComp;
	}

	public void setValorDemonstrativoParcelamentoFichaComp(BigDecimal valorDemonstrativoParcelamentoFichaComp) {
		this.valorDemonstrativoParcelamentoFichaComp = valorDemonstrativoParcelamentoFichaComp;
	}

	public Short getIndicadorBloqueioContasContratoParcelDebitos() {
		return indicadorBloqueioContasContratoParcelDebitos;
	}

	public void setIndicadorBloqueioContasContratoParcelDebitos(Short indicadorBloqueioContasContratoParcelDebitos) {
		this.indicadorBloqueioContasContratoParcelDebitos = indicadorBloqueioContasContratoParcelDebitos;
	}

	public Short getIndicadorBloqueioContasContratoParcelManterConta() {
		return indicadorBloqueioContasContratoParcelManterConta;
	}

	public void setIndicadorBloqueioContasContratoParcelManterConta(Short indicadorBloqueioContasContratoParcelManterConta) {
		this.indicadorBloqueioContasContratoParcelManterConta = indicadorBloqueioContasContratoParcelManterConta;
	}

	public Short getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() {
		return indicadorBloqueioGuiasOuAcresContratoParcelManterConta;
	}

	public void setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(Short indicadorBloqueioGuiasOuAcresContratoParcelManterConta) {
		this.indicadorBloqueioGuiasOuAcresContratoParcelManterConta = indicadorBloqueioGuiasOuAcresContratoParcelManterConta;
	}

	public Integer getNumeroMaximoParcelasContratosParcelamento() {
		return numeroMaximoParcelasContratosParcelamento;
	}

	public void setNumeroMaximoParcelasContratosParcelamento(Integer numeroMaximoParcelasContratosParcelamento) {
		this.numeroMaximoParcelasContratosParcelamento = numeroMaximoParcelasContratosParcelamento;
	}

	public Short getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() {
		return indicadorBloqueioGuiasOuAcresContratoParcelDebito;
	}

	public void setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(Short indicadorBloqueioGuiasOuAcresContratoParcelDebito) {
		this.indicadorBloqueioGuiasOuAcresContratoParcelDebito = indicadorBloqueioGuiasOuAcresContratoParcelDebito;
	}

	public Short getIndicadorBloqueioDebitoACobrarContratoParcelDebito() {
		return indicadorBloqueioDebitoACobrarContratoParcelDebito;
	}

	public void setIndicadorBloqueioDebitoACobrarContratoParcelDebito(Short indicadorBloqueioDebitoACobrarContratoParcelDebito) {
		this.indicadorBloqueioDebitoACobrarContratoParcelDebito = indicadorBloqueioDebitoACobrarContratoParcelDebito;
	}

	public Short getIndicadorBloqueioDebitoACobrarContratoParcelManterDebito() {
		return indicadorBloqueioDebitoACobrarContratoParcelManterDebito;
	}

	public void setIndicadorBloqueioDebitoACobrarContratoParcelManterDebito(Short indicadorBloqueioDebitoACobrarContratoParcelManterDebito) {
		this.indicadorBloqueioDebitoACobrarContratoParcelManterDebito = indicadorBloqueioDebitoACobrarContratoParcelManterDebito;
	}

	public Short getIndicadorVariaHierarquiaUnidade() {
		return indicadorVariaHierarquiaUnidade;
	}

	public void setIndicadorVariaHierarquiaUnidade(Short indicadorVariaHierarquiaUnidade) {
		this.indicadorVariaHierarquiaUnidade = indicadorVariaHierarquiaUnidade;
	}

	public Integer getQtdeDiasEncerraOSFiscalizacao() {
		return qtdeDiasEncerraOSFiscalizacao;
	}

	public void setQtdeDiasEncerraOSFiscalizacao(Integer qtdeDiasEncerraOSFiscalizacao) {
		this.qtdeDiasEncerraOSFiscalizacao = qtdeDiasEncerraOSFiscalizacao;
	}

	public Integer getCodigoTipoCalculoNaoMedido() {
		return codigoTipoCalculoNaoMedido;
	}

	public void setCodigoTipoCalculoNaoMedido(Integer codigoTipoCalculoNaoMedido) {
		this.codigoTipoCalculoNaoMedido = codigoTipoCalculoNaoMedido;
	}

	public Integer getQtdeDiasValidadeOSFiscalizacao() {
		return qtdeDiasValidadeOSFiscalizacao;
	}

	public void setQtdeDiasValidadeOSFiscalizacao(Integer qtdeDiasValidadeOSFiscalizacao) {
		this.qtdeDiasValidadeOSFiscalizacao = qtdeDiasValidadeOSFiscalizacao;
	}

	public Integer getQtdeDiasEnvioEmailConta() {
		return qtdeDiasEnvioEmailConta;
	}

	public void setQtdeDiasEnvioEmailConta(Integer qtdeDiasEnvioEmailConta) {
		this.qtdeDiasEnvioEmailConta = qtdeDiasEnvioEmailConta;
	}

	public byte[] getArquivoDecreto() {
		return arquivoDecreto;
	}

	public void setArquivoDecreto(byte[] arquivoDecreto) {
		this.arquivoDecreto = arquivoDecreto;
	}

	public String getDescricaoDecreto() {
		return descricaoDecreto;
	}

	public void setDescricaoDecreto(String descricaoDecreto) {
		this.descricaoDecreto = descricaoDecreto;
	}

	public Integer getQtdeDiasValidadeOSAnormalidadeFiscalizacao() {
		return qtdeDiasValidadeOSAnormalidadeFiscalizacao;
	}

	public void setQtdeDiasValidadeOSAnormalidadeFiscalizacao(Integer qtdeDiasValidadeOSAnormalidadeFiscalizacao) {
		this.qtdeDiasValidadeOSAnormalidadeFiscalizacao = qtdeDiasValidadeOSAnormalidadeFiscalizacao;
	}

	public byte[] getArquivoLeiEstTarif() {
		return arquivoLeiEstTarif;
	}

	public void setArquivoLeiEstTarif(byte[] arquivoLeiEstTarif) {
		this.arquivoLeiEstTarif = arquivoLeiEstTarif;
	}

	public byte[] getArquivoLeiIndividualizacao() {
		return arquivoLeiIndividualizacao;
	}

	public void setArquivoLeiIndividualizacao(byte[] arquivoLeiIndividualizacao) {
		this.arquivoLeiIndividualizacao = arquivoLeiIndividualizacao;
	}

	public byte[] getArquivoNormaCM() {
		return arquivoNormaCM;
	}

	public void setArquivoNormaCM(byte[] arquivoNormaCM) {
		this.arquivoNormaCM = arquivoNormaCM;
	}

	public byte[] getArquivoNormaCO() {
		return arquivoNormaCO;
	}

	public void setArquivoNormaCO(byte[] arquivoNormaCO) {
		this.arquivoNormaCO = arquivoNormaCO;
	}

	public String getDescricaoLeiEstTarif() {
		return descricaoLeiEstTarif;
	}

	public void setDescricaoLeiEstTarif(String descricaoLeiEstTarif) {
		this.descricaoLeiEstTarif = descricaoLeiEstTarif;
	}

	public String getDescricaoLeiIndividualizacao() {
		return descricaoLeiIndividualizacao;
	}

	public void setDescricaoLeiIndividualizacao(String descricaoLeiIndividualizacao) {
		this.descricaoLeiIndividualizacao = descricaoLeiIndividualizacao;
	}

	public String getDescricaoNormaCM() {
		return descricaoNormaCM;
	}

	public void setDescricaoNormaCM(String descricaoNormaCM) {
		this.descricaoNormaCM = descricaoNormaCM;
	}

	public String getDescricaoNormaCO() {
		return descricaoNormaCO;
	}

	public void setDescricaoNormaCO(String descricaoNormaCO) {
		this.descricaoNormaCO = descricaoNormaCO;
	}

	public Cliente getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() {
		return clienteFicticioParaAssociarOsPagamentosNaoIdentificados;
	}

	public void setClienteFicticioParaAssociarOsPagamentosNaoIdentificados(Cliente clienteFicticioParaAssociarOsPagamentosNaoIdentificados) {
		this.clienteFicticioParaAssociarOsPagamentosNaoIdentificados = clienteFicticioParaAssociarOsPagamentosNaoIdentificados;
	}

	public Integer getNumeroMesesLeituraSuspensa() {
		return numeroMesesLeituraSuspensa;
	}

	public void setNumeroMesesLeituraSuspensa(Integer numeroMesesLeituraSuspensa) {
		this.numeroMesesLeituraSuspensa = numeroMesesLeituraSuspensa;
	}

	public Integer getNumeroMesesReinicioSitEspFaturamento() {
		return numeroMesesReinicioSitEspFaturamento;
	}

	public void setNumeroMesesReinicioSitEspFaturamento(Integer numeroMesesReinicioSitEspFaturamento) {
		this.numeroMesesReinicioSitEspFaturamento = numeroMesesReinicioSitEspFaturamento;
	}

	public Integer getNumeroVezesSuspendeLeitura() {
		return numeroVezesSuspendeLeitura;
	}

	public void setNumeroVezesSuspendeLeitura(Integer numeroVezesSuspendeLeitura) {
		this.numeroVezesSuspendeLeitura = numeroVezesSuspendeLeitura;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public Cliente getClienteResponsavelNegativacao() {
		return clienteResponsavelNegativacao;
	}

	public void setClienteResponsavelNegativacao(Cliente clienteResponsavelNegativacao) {
		this.clienteResponsavelNegativacao = clienteResponsavelNegativacao;
	}

	public String getDescricaoAliquotaImposto() {
		return descricaoAliquotaImposto;
	}

	public void setDescricaoAliquotaImposto(String descricaoAliquotaImposto) {
		this.descricaoAliquotaImposto = descricaoAliquotaImposto;
	}

	public BigDecimal getValorAliquotaImposto() {
		return valorAliquotaImposto;
	}

	public void setValorAliquotaImposto(BigDecimal valorAliquotaImposto) {
		this.valorAliquotaImposto = valorAliquotaImposto;
	}

	public Short getIndicadorGeracaoBoletoBB() {
		return indicadorGeracaoBoletoBB;
	}

	public void setIndicadorGeracaoBoletoBB(Short indicadorGeracaoBoletoBB) {
		this.indicadorGeracaoBoletoBB = indicadorGeracaoBoletoBB;
	}
}
