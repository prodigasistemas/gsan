package gcom.cadastro.imovel;

import java.math.BigDecimal;
import java.util.Date;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FaturamentoTipo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.MergeProperties;
import gcom.util.filtro.Filtro;

public class ImovelHistorico extends ObjetoTransacao{
	private static final long serialVersionUID = -1586598739197597650L;

	private Integer id;
	private Short numeroReparcelamentoConsecutivos;
	private Short indicadorImovelCondominio;
	private Date dataSupressaoParcial;
	private Date dataInfracao;
	private Short numeroRetificacao;
	private Short numeroLeituraExcecao;
	private Short numeroParcelamento;
	private Short numeroReparcelamento;
	private Short indicadorConta;
	private ImovelContaEnvio imovelContaEnvio;
	private Short indicadorDebitoConta;
	private ImovelEnderecoAnterior imovelEnderecoAnterior;
	private LigacaoEsgoto ligacaoEsgoto;
	private ImovelHistorico imovelCondominio;
	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
	private EloAnormalidade eloAnormalidade;
	private FaturamentoSituacaoTipo faturamentoSituacaoTipo;
	private ConsumoTarifa consumoTarifa;
	private Date ultimaAlteracao;
	private FaturamentoTipo faturamentoTipo;
	private FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;
	private Short indicadorSuspensaoAbastecimento;
	private Short indicadorVencimentoMesSeguinte;
    private SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral;
    private String informacoesComplementares;
    private Integer indicadorReincidenciaInfracao;
    private Integer codigoDebitoAutomatico;
    private Integer numeroQuadraEntrega;
    private Integer anoMesExclusaoTarifaSocial;
    private Short indicadorNivelInstalacaoEsgoto;
    private Short indicadorImovelAreaComum;
    private Integer categoriaPrincipalId;
    private Integer subCategoriaPrincipalId;
    private Integer idCapacidadeHidrometro;
    private Integer idMarcaHidrometro;
    private Integer idProtecaoHidrometro;
    private String numeroHidrometro;
    private String tipoEntrevistado;
	private Integer idMunicipio;
	private String nomeMunicipio;
	private String dsUFSiglaMunicipio;
	private CobrancaSituacaoTipo cobrancaSituacaoTipo;
	private short lote;
	private short subLote;
	private Short testadaLote;
	private String numeroImovel;
	private String nomeImovel;
	private String complementoEndereco;
	private BigDecimal areaConstruida;
	private BigDecimal volumeReservatorioSuperior;
	private BigDecimal volumeReservatorioInferior;
	private BigDecimal volumePiscina;
	private Short numeroPontosUtilizacao;
	private Short numeroMorador;
	private Short diaVencimento;
	private String numeroIptu;
	private Long numeroCelpe;
	private Short indicadorEmissaoExtratoFaturamento;
	private Short indicadorExclusao;
	private String coordenadaX;
	private String coordenadaY;
	private LigacaoAgua ligacaoAgua;
	private ImovelHistorico imovelPrincipal;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private LeituraAnormalidade leituraAnormalidade;
	private SetorComercial setorComercial;
	private AreaConstruidaFaixa areaConstruidaFaixa;
	private PavimentoCalcada pavimentoCalcada;
	private ImovelPerfil imovelPerfil;
	private ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior;
	private ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior;
	private Localidade localidade;
	private Quadra quadra;
	private CobrancaSituacao cobrancaSituacao;
	private PavimentoRua pavimentoRua;
	private EnderecoReferencia enderecoReferencia;
	private CadastroOcorrencia cadastroOcorrencia;
	private PocoTipo pocoTipo;
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private Despejo despejo;
	private FonteAbastecimento fonteAbastecimento;
	private PiscinaVolumeFaixa piscinaVolumeFaixa;
	private ImovelTipoHabitacao imovelTipoHabitacao;
	private ImovelTipoPropriedade imovelTipoPropriedade;
	private ImovelTipoConstrucao imovelTipoConstrucao;
	private ImovelTipoCobertura imovelTipoCobertura;
	private Short quantidadeEconomias;
	private LogradouroBairro logradouroBairro;
	private LogradouroCep logradouroCep;
    private Short indicadorJardim;
    private Integer numeroSequencialRota;
    private Integer numeroSequencialRotaEntrega;
    private Rota rotaEntrega;
    private Rota rotaAlternativa;
    private Funcionario funcionario;
    private Logradouro perimetroInicial;
    private Logradouro perimetroFinal;
	private QuadraFace quadraFace;
    private String numeroMedidorEnergia;
    private Date dataVisitaComercial;
    private Short classeSocial;
    private Short quantidadeAnimaisDomesticos;
    private BigDecimal volumeCisterna;
    private BigDecimal volumeCaixaDagua;
    private Short tipoUso;
    private Short acessoHidrometro;
    private Integer quantidadeEconomiasSocial;
    private Integer quantidadeEconomiasOutra;
    private Short percentualAbastecimento;
    private Usuario usuario;
    private Imovel imovel;
    
	public ImovelHistorico(){
	}
	
    public ImovelHistorico(Imovel imovel, Usuario usuario) {
    	MergeProperties.mergeProperties(this, imovel);
    	this.usuario = usuario;
    	this.imovel  = imovel;
	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Short getNumeroReparcelamentoConsecutivos() {
		return numeroReparcelamentoConsecutivos;
	}
	public void setNumeroReparcelamentoConsecutivos(Short numeroReparcelamentoConsecutivos) {
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
	}
	public Short getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}
	public void setIndicadorImovelCondominio(Short indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}
	public Date getDataSupressaoParcial() {
		return dataSupressaoParcial;
	}
	public void setDataSupressaoParcial(Date dataSupressaoParcial) {
		this.dataSupressaoParcial = dataSupressaoParcial;
	}
	public Date getDataInfracao() {
		return dataInfracao;
	}
	public void setDataInfracao(Date dataInfracao) {
		this.dataInfracao = dataInfracao;
	}
	public Short getNumeroRetificacao() {
		return numeroRetificacao;
	}
	public void setNumeroRetificacao(Short numeroRetificacao) {
		this.numeroRetificacao = numeroRetificacao;
	}
	public Short getNumeroLeituraExcecao() {
		return numeroLeituraExcecao;
	}
	public void setNumeroLeituraExcecao(Short numeroLeituraExcecao) {
		this.numeroLeituraExcecao = numeroLeituraExcecao;
	}
	public Short getNumeroParcelamento() {
		return numeroParcelamento;
	}
	public void setNumeroParcelamento(Short numeroParcelamento) {
		this.numeroParcelamento = numeroParcelamento;
	}
	public Short getNumeroReparcelamento() {
		return numeroReparcelamento;
	}
	public void setNumeroReparcelamento(Short numeroReparcelamento) {
		this.numeroReparcelamento = numeroReparcelamento;
	}
	public Short getIndicadorConta() {
		return indicadorConta;
	}
	public void setIndicadorConta(Short indicadorConta) {
		this.indicadorConta = indicadorConta;
	}
	public ImovelContaEnvio getImovelContaEnvio() {
		return imovelContaEnvio;
	}
	public void setImovelContaEnvio(ImovelContaEnvio imovelContaEnvio) {
		this.imovelContaEnvio = imovelContaEnvio;
	}
	public Short getIndicadorDebitoConta() {
		return indicadorDebitoConta;
	}
	public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}
	public ImovelEnderecoAnterior getImovelEnderecoAnterior() {
		return imovelEnderecoAnterior;
	}
	public void setImovelEnderecoAnterior(ImovelEnderecoAnterior imovelEnderecoAnterior) {
		this.imovelEnderecoAnterior = imovelEnderecoAnterior;
	}
	public LigacaoEsgoto getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}
	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}
	public ImovelHistorico getImovelCondominio() {
		return imovelCondominio;
	}
	public void setImovelCondominio(ImovelHistorico imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}
	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}
	public void setHidrometroInstalacaoHistorico(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}
	public EloAnormalidade getEloAnormalidade() {
		return eloAnormalidade;
	}
	public void setEloAnormalidade(EloAnormalidade eloAnormalidade) {
		this.eloAnormalidade = eloAnormalidade;
	}
	public FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return faturamentoSituacaoTipo;
	}
	public void setFaturamentoSituacaoTipo(FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}
	public gcom.faturamento.consumotarifa.ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}
	public void setConsumoTarifa(gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public FaturamentoTipo getFaturamentoTipo() {
		return faturamentoTipo;
	}
	public void setFaturamentoTipo(FaturamentoTipo faturamentoTipo) {
		this.faturamentoTipo = faturamentoTipo;
	}
	public FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
		return faturamentoSituacaoMotivo;
	}
	public void setFaturamentoSituacaoMotivo(FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}
	public Short getIndicadorSuspensaoAbastecimento() {
		return indicadorSuspensaoAbastecimento;
	}
	public void setIndicadorSuspensaoAbastecimento(Short indicadorSuspensaoAbastecimento) {
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
	}
	public Short getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}
	public void setIndicadorVencimentoMesSeguinte(Short indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}
	public SituacaoAtualizacaoCadastral getSituacaoAtualizacaoCadastral() {
		return situacaoAtualizacaoCadastral;
	}
	public void setSituacaoAtualizacaoCadastral(SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral) {
		this.situacaoAtualizacaoCadastral = situacaoAtualizacaoCadastral;
	}
	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}
	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}
	public Integer getIndicadorReincidenciaInfracao() {
		return indicadorReincidenciaInfracao;
	}
	public void setIndicadorReincidenciaInfracao(Integer indicadorReincidenciaInfracao) {
		this.indicadorReincidenciaInfracao = indicadorReincidenciaInfracao;
	}
	public Integer getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}
	public void setCodigoDebitoAutomatico(Integer codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}
	public Integer getNumeroQuadraEntrega() {
		return numeroQuadraEntrega;
	}
	public void setNumeroQuadraEntrega(Integer numeroQuadraEntrega) {
		this.numeroQuadraEntrega = numeroQuadraEntrega;
	}
	public Integer getAnoMesExclusaoTarifaSocial() {
		return anoMesExclusaoTarifaSocial;
	}
	public void setAnoMesExclusaoTarifaSocial(Integer anoMesExclusaoTarifaSocial) {
		this.anoMesExclusaoTarifaSocial = anoMesExclusaoTarifaSocial;
	}
	public Short getIndicadorNivelInstalacaoEsgoto() {
		return indicadorNivelInstalacaoEsgoto;
	}
	public void setIndicadorNivelInstalacaoEsgoto(Short indicadorNivelInstalacaoEsgoto) {
		this.indicadorNivelInstalacaoEsgoto = indicadorNivelInstalacaoEsgoto;
	}
	public Short getIndicadorImovelAreaComum() {
		return indicadorImovelAreaComum;
	}
	public void setIndicadorImovelAreaComum(Short indicadorImovelAreaComum) {
		this.indicadorImovelAreaComum = indicadorImovelAreaComum;
	}
	public Integer getCategoriaPrincipalId() {
		return categoriaPrincipalId;
	}
	public void setCategoriaPrincipalId(Integer categoriaPrincipalId) {
		this.categoriaPrincipalId = categoriaPrincipalId;
	}
	public Integer getSubCategoriaPrincipalId() {
		return subCategoriaPrincipalId;
	}
	public void setSubCategoriaPrincipalId(Integer subCategoriaPrincipalId) {
		this.subCategoriaPrincipalId = subCategoriaPrincipalId;
	}
	public Integer getIdCapacidadeHidrometro() {
		return idCapacidadeHidrometro;
	}
	public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
		this.idCapacidadeHidrometro = idCapacidadeHidrometro;
	}
	public Integer getIdMarcaHidrometro() {
		return idMarcaHidrometro;
	}
	public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
		this.idMarcaHidrometro = idMarcaHidrometro;
	}
	public Integer getIdProtecaoHidrometro() {
		return idProtecaoHidrometro;
	}
	public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro) {
		this.idProtecaoHidrometro = idProtecaoHidrometro;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getTipoEntrevistado() {
		return tipoEntrevistado;
	}
	public void setTipoEntrevistado(String tipoEntrevistado) {
		this.tipoEntrevistado = tipoEntrevistado;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}
	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}
	public CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}
	public void setCobrancaSituacaoTipo(CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}
	public short getLote() {
		return lote;
	}
	public void setLote(short lote) {
		this.lote = lote;
	}
	public short getSubLote() {
		return subLote;
	}
	public void setSubLote(short subLote) {
		this.subLote = subLote;
	}
	public Short getTestadaLote() {
		return testadaLote;
	}
	public void setTestadaLote(Short testadaLote) {
		this.testadaLote = testadaLote;
	}
	public String getNumeroImovel() {
		return numeroImovel;
	}
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}
	public String getNomeImovel() {
		return nomeImovel;
	}
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public BigDecimal getAreaConstruida() {
		return areaConstruida;
	}
	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}
	public BigDecimal getVolumeReservatorioSuperior() {
		return volumeReservatorioSuperior;
	}
	public void setVolumeReservatorioSuperior(BigDecimal volumeReservatorioSuperior) {
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
	}
	public BigDecimal getVolumeReservatorioInferior() {
		return volumeReservatorioInferior;
	}
	public void setVolumeReservatorioInferior(BigDecimal volumeReservatorioInferior) {
		this.volumeReservatorioInferior = volumeReservatorioInferior;
	}
	public BigDecimal getVolumePiscina() {
		return volumePiscina;
	}
	public void setVolumePiscina(BigDecimal volumePiscina) {
		this.volumePiscina = volumePiscina;
	}
	public Short getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}
	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}
	public Short getNumeroMorador() {
		return numeroMorador;
	}
	public void setNumeroMorador(Short numeroMorador) {
		this.numeroMorador = numeroMorador;
	}
	public Short getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public String getNumeroIptu() {
		return numeroIptu;
	}
	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}
	public Long getNumeroCelpe() {
		return numeroCelpe;
	}
	public void setNumeroCelpe(Long numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}
	public Short getIndicadorEmissaoExtratoFaturamento() {
		return indicadorEmissaoExtratoFaturamento;
	}
	public void setIndicadorEmissaoExtratoFaturamento(Short indicadorEmissaoExtratoFaturamento) {
		this.indicadorEmissaoExtratoFaturamento = indicadorEmissaoExtratoFaturamento;
	}
	public Short getIndicadorExclusao() {
		return indicadorExclusao;
	}
	public void setIndicadorExclusao(Short indicadorExclusao) {
		this.indicadorExclusao = indicadorExclusao;
	}
	public String getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public String getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}
	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}
	public ImovelHistorico getImovelPrincipal() {
		return imovelPrincipal;
	}
	public void setImovelPrincipal(ImovelHistorico imovelPrincipal) {
		this.imovelPrincipal = imovelPrincipal;
	}
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}
	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}
	public LeituraAnormalidade getLeituraAnormalidade() {
		return leituraAnormalidade;
	}
	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}
	public AreaConstruidaFaixa getAreaConstruidaFaixa() {
		return areaConstruidaFaixa;
	}
	public void setAreaConstruidaFaixa(AreaConstruidaFaixa areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}
	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	public ReservatorioVolumeFaixa getReservatorioVolumeFaixaSuperior() {
		return reservatorioVolumeFaixaSuperior;
	}
	public void setReservatorioVolumeFaixaSuperior(
			ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior) {
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
	}
	public ReservatorioVolumeFaixa getReservatorioVolumeFaixaInferior() {
		return reservatorioVolumeFaixaInferior;
	}
	public void setReservatorioVolumeFaixaInferior(
			ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior) {
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public Quadra getQuadra() {
		return quadra;
	}
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}
	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}
	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}
	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}
	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	public EnderecoReferencia getEnderecoReferencia() {
		return enderecoReferencia;
	}
	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}
	public CadastroOcorrencia getCadastroOcorrencia() {
		return cadastroOcorrencia;
	}
	public void setCadastroOcorrencia(CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
	}
	public PocoTipo getPocoTipo() {
		return pocoTipo;
	}
	public void setPocoTipo(PocoTipo pocoTipo) {
		this.pocoTipo = pocoTipo;
	}
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}
	public Despejo getDespejo() {
		return despejo;
	}
	public void setDespejo(Despejo despejo) {
		this.despejo = despejo;
	}
	public FonteAbastecimento getFonteAbastecimento() {
		return fonteAbastecimento;
	}
	public void setFonteAbastecimento(FonteAbastecimento fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}
	public PiscinaVolumeFaixa getPiscinaVolumeFaixa() {
		return piscinaVolumeFaixa;
	}
	public void setPiscinaVolumeFaixa(PiscinaVolumeFaixa piscinaVolumeFaixa) {
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
	}
	public ImovelTipoHabitacao getImovelTipoHabitacao() {
		return imovelTipoHabitacao;
	}
	public void setImovelTipoHabitacao(ImovelTipoHabitacao imovelTipoHabitacao) {
		this.imovelTipoHabitacao = imovelTipoHabitacao;
	}
	public ImovelTipoPropriedade getImovelTipoPropriedade() {
		return imovelTipoPropriedade;
	}
	public void setImovelTipoPropriedade(ImovelTipoPropriedade imovelTipoPropriedade) {
		this.imovelTipoPropriedade = imovelTipoPropriedade;
	}
	public ImovelTipoConstrucao getImovelTipoConstrucao() {
		return imovelTipoConstrucao;
	}
	public void setImovelTipoConstrucao(ImovelTipoConstrucao imovelTipoConstrucao) {
		this.imovelTipoConstrucao = imovelTipoConstrucao;
	}
	public ImovelTipoCobertura getImovelTipoCobertura() {
		return imovelTipoCobertura;
	}
	public void setImovelTipoCobertura(ImovelTipoCobertura imovelTipoCobertura) {
		this.imovelTipoCobertura = imovelTipoCobertura;
	}
	public Short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	public void setQuantidadeEconomias(Short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
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
	public Short getIndicadorJardim() {
		return indicadorJardim;
	}
	public void setIndicadorJardim(Short indicadorJardim) {
		this.indicadorJardim = indicadorJardim;
	}
	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}
	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}
	public Integer getNumeroSequencialRotaEntrega() {
		return numeroSequencialRotaEntrega;
	}
	public void setNumeroSequencialRotaEntrega(Integer numeroSequencialRotaEntrega) {
		this.numeroSequencialRotaEntrega = numeroSequencialRotaEntrega;
	}
	public Rota getRotaEntrega() {
		return rotaEntrega;
	}
	public void setRotaEntrega(Rota rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}
	public Rota getRotaAlternativa() {
		return rotaAlternativa;
	}
	public void setRotaAlternativa(Rota rotaAlternativa) {
		this.rotaAlternativa = rotaAlternativa;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Logradouro getPerimetroInicial() {
		return perimetroInicial;
	}
	public void setPerimetroInicial(Logradouro perimetroInicial) {
		this.perimetroInicial = perimetroInicial;
	}
	public Logradouro getPerimetroFinal() {
		return perimetroFinal;
	}
	public void setPerimetroFinal(Logradouro perimetroFinal) {
		this.perimetroFinal = perimetroFinal;
	}
	public QuadraFace getQuadraFace() {
		return quadraFace;
	}
	public void setQuadraFace(QuadraFace quadraFace) {
		this.quadraFace = quadraFace;
	}
	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}
	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}
	public Date getDataVisitaComercial() {
		return dataVisitaComercial;
	}
	public void setDataVisitaComercial(Date dataVisitaComercial) {
		this.dataVisitaComercial = dataVisitaComercial;
	}
	public Short getClasseSocial() {
		return classeSocial;
	}
	public void setClasseSocial(Short classeSocial) {
		this.classeSocial = classeSocial;
	}
	public Short getQuantidadeAnimaisDomesticos() {
		return quantidadeAnimaisDomesticos;
	}
	public void setQuantidadeAnimaisDomesticos(Short quantidadeAnimaisDomesticos) {
		this.quantidadeAnimaisDomesticos = quantidadeAnimaisDomesticos;
	}
	public BigDecimal getVolumeCisterna() {
		return volumeCisterna;
	}
	public void setVolumeCisterna(BigDecimal volumeCisterna) {
		this.volumeCisterna = volumeCisterna;
	}
	public BigDecimal getVolumeCaixaDagua() {
		return volumeCaixaDagua;
	}
	public void setVolumeCaixaDagua(BigDecimal volumeCaixaDagua) {
		this.volumeCaixaDagua = volumeCaixaDagua;
	}
	public Short getTipoUso() {
		return tipoUso;
	}
	public void setTipoUso(Short tipoUso) {
		this.tipoUso = tipoUso;
	}
	public Short getAcessoHidrometro() {
		return acessoHidrometro;
	}
	public void setAcessoHidrometro(Short acessoHidrometro) {
		this.acessoHidrometro = acessoHidrometro;
	}
	public Integer getQuantidadeEconomiasSocial() {
		return quantidadeEconomiasSocial;
	}
	public void setQuantidadeEconomiasSocial(Integer quantidadeEconomiasSocial) {
		this.quantidadeEconomiasSocial = quantidadeEconomiasSocial;
	}
	public Integer getQuantidadeEconomiasOutra() {
		return quantidadeEconomiasOutra;
	}
	public void setQuantidadeEconomiasOutra(Integer quantidadeEconomiasOutra) {
		this.quantidadeEconomiasOutra = quantidadeEconomiasOutra;
	}
	public Short getPercentualAbastecimento() {
		return percentualAbastecimento;
	}
	public void setPercentualAbastecimento(Short percentualAbastecimento) {
		this.percentualAbastecimento = percentualAbastecimento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}
	@Override
	public String[] retornaCamposChavePrimaria() {
		return new String[]{"id"};
	}
}
