package gcom.cobranca.contratoparcelamento;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

@ControleAlteracao()
public class ContratoParcelamento extends ObjetoTransacao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Short RESP_INDICADOR_NA_CONTA = 1;
	
	public static final Short RESP_ATUAL_DO_IMOVEL = 2;
	
	public static final Short RESP_TODOS = 3;
	
	public static final Short DEBITO_ACRESCIMO_SIM = 1;
	public static final Short DEBITO_ACRESCIMO_NAO = 2;
	
	/** identifier field */
	private Integer id;
	
	private String numero;
	
	private ContratoParcelamento contratoAnterior;
	
	private TipoRelacao relacaoAnterior;

	private ParcelamentoSituacao parcelamentoSituacao;
	
	private Date dataContrato;
	
	private Usuario usuarioResponsavel;
	
	private ClienteRelacaoTipo relacaoCliente;
	
	private Short indicadorResponsavel;
	
	private Integer anoMesDebitoInicio;
	
	private Integer anoMesDebitoFinal;
	
	private Date dataVencimentoInicio;
	
	private Date dataVencimentoFinal;
	
	private String observacao;
	
	private ContratoParcelamentoRD resolucaoDiretoria;
	
	private Short indicadorDebitosAcrescimos;
	
	private Short indicadorParcelamentoJuros;
	
	private Short indicadorPermiteInformarValorParcel;
	
	private CobrancaForma cobrancaForma;
	
	private Date dataVencimentoPrimParcela;
	
	private Integer numeroDiasEntreVencimentoParcel;

	private Integer qtdFaturasParceladas;

	private BigDecimal valorTotalConta;
	
	private BigDecimal valorTotalDebitosCobrar;
	
	private BigDecimal valorTotalAcrescImpontualidade;
	
	private Short indicadorContasRevisao;
	
	private Short indicadorDebitoACobrar;
	
	private Short indicadorCreditoARealizar;
	
	private Integer numeroPrestacoes;
	
	private BigDecimal taxaJuros;
	
	private BigDecimal valorJurosMora;
	
	private BigDecimal valorJurosParcelamento;
	
	private BigDecimal valorTotalParcelado;
	
	private BigDecimal valorDebitoAtualizado;
	
	private BigDecimal valorParcelamentoACobrar;
	
	private Integer anoMesReferenciaFaturamento;
	
	private Date dataImplantacao;
	
	private ParcelamentoMotivoDesfazer motivoDesfazer;
	
	private Date dataCancelamento;
	
	private QuantidadePrestacoes qtdPrestacoesDaRDEscolhida;
	
	private Short indicadorParcelaInformadaPeloUsuario;
	
	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** default constructor */
	public ContratoParcelamento() {
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumero() {
		return numero;
	}

	public Integer getAnoMesDebitoFinal() {
		return anoMesDebitoFinal;
	}

	public void setAnoMesDebitoFinal(Integer anoMesDebitoFinal) {
		this.anoMesDebitoFinal = anoMesDebitoFinal;
	}

	public Integer getAnoMesDebitoInicio() {
		return anoMesDebitoInicio;
	}

	public void setAnoMesDebitoInicio(Integer anoMesDebitoInicio) {
		this.anoMesDebitoInicio = anoMesDebitoInicio;
	}

	public ContratoParcelamento getContratoAnterior() {
		return contratoAnterior;
	}

	public void setContratoAnterior(ContratoParcelamento contratoAnterior) {
		this.contratoAnterior = contratoAnterior;
	}

	public Date getDataContrato() {
		return dataContrato;
	}
	
	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}

	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public Date getDataVencimentoInicio() {
		return dataVencimentoInicio;
	}

	public void setDataVencimentoInicio(Date dataVencimentoInicio) {
		this.dataVencimentoInicio = dataVencimentoInicio;
	}

	public Short getIndicadorResponsavel() {
		return indicadorResponsavel;
	}

	public void setIndicadorResponsavel(Short indicadorResponsavel) {
		this.indicadorResponsavel = indicadorResponsavel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ParcelamentoSituacao getParcelamentoSituacao() {
		return parcelamentoSituacao;
	}

	public void setParcelamentoSituacao(ParcelamentoSituacao parcelamentoSituacao) {
		this.parcelamentoSituacao = parcelamentoSituacao;
	}

	public TipoRelacao getRelacaoAnterior() {
		return relacaoAnterior;
	}

	public void setRelacaoAnterior(TipoRelacao relacaoAnterior) {
		this.relacaoAnterior = relacaoAnterior;
	}

	public ClienteRelacaoTipo getRelacaoCliente() {
		return relacaoCliente;
	}

	public void setRelacaoCliente(ClienteRelacaoTipo relacaoCliente) {
		this.relacaoCliente = relacaoCliente;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroContratoParcelamento filtro = new FiltroContratoParcelamento();

		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.CONTRATO_ANTERIOR);
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.MOTIVO_DESFAZER);
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.RELACAO_ANTERIOR);
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.PARCEL_SITUACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.USUARIO_RESPONSAVEL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.RELACAO_CLIENTE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.RESOLUCAO_DIRETORIA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContratoParcelamento.COBRANCA_FORMA);
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContratoParcelamento.ID, this.getId()));
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public String getDataContratoFormatada(){
		return Util.formatarData(this.getDataContrato());
	}
	
	public String getAnoMesDebitoInicioFormatado(){
		if(this.getAnoMesDebitoInicio() == null){
			return "";
		}
		return this.getAnoMesDebitoInicio().toString().substring(4, 6) +"/"+ this.getAnoMesDebitoInicio().toString().substring(0, 4);
	}
	
	public String getAnoMesDebitoFinalFormatado(){
		if(this.getAnoMesDebitoFinal() == null){
			return "";
		}
		return this.getAnoMesDebitoFinal().toString().substring(4, 6) +"/"+ this.getAnoMesDebitoFinal().toString().substring(0, 4);
	}
	
	public String getDataVencimentoInicioFormatada(){
		return Util.formatarData(this.getDataVencimentoInicio());
	}
	
	public String getDataVencimentoFinalFormatada(){
		return Util.formatarData(this.getDataVencimentoFinal());
	}

	public ContratoParcelamentoRD getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ContratoParcelamentoRD resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public Short getIndicadorDebitosAcrescimos() {
		return indicadorDebitosAcrescimos;
	}

	public void setIndicadorDebitosAcrescimos(Short indicadorDebitosAcrescimos) {
		this.indicadorDebitosAcrescimos = indicadorDebitosAcrescimos;
	}

	public Short getIndicadorParcelamentoJuros() {
		return indicadorParcelamentoJuros;
	}

	public void setIndicadorParcelamentoJuros(Short indicadorParcelamentoJuros) {
		this.indicadorParcelamentoJuros = indicadorParcelamentoJuros;
	}

	public Short getIndicadorPermiteInformarValorParcel() {
		return indicadorPermiteInformarValorParcel;
	}

	public void setIndicadorPermiteInformarValorParcel(
			Short indicadorPermiteInformarValorParcel) {
		this.indicadorPermiteInformarValorParcel = indicadorPermiteInformarValorParcel;
	}

	public CobrancaForma getCobrancaForma() {
		return cobrancaForma;
	}

	public void setCobrancaForma(CobrancaForma cobrancaForma) {
		this.cobrancaForma = cobrancaForma;
	}

	public Date getDataVencimentoPrimParcela() {
		return dataVencimentoPrimParcela;
	}

	public void setDataVencimentoPrimParcela(Date dataVencimentoPrimParcela) {
		this.dataVencimentoPrimParcela = dataVencimentoPrimParcela;
	}
	
	public String getDataVencimentoPrimParcelaFormatada(){
		return Util.formatarData(this.getDataVencimentoPrimParcela());
	}

	public Integer getNumeroDiasEntreVencimentoParcel() {
		return numeroDiasEntreVencimentoParcel;
	}

	public void setNumeroDiasEntreVencimentoParcel(
			Integer numeroDiasEntreVencimentoParcel) {
		this.numeroDiasEntreVencimentoParcel = numeroDiasEntreVencimentoParcel;
	}

	public Integer getQtdFaturasParceladas() {
		return qtdFaturasParceladas;
	}

	public void setQtdFaturasParceladas(Integer qtdFaturasParceladas) {
		this.qtdFaturasParceladas = qtdFaturasParceladas;
	}

	public BigDecimal getValorTotalConta() {
		return valorTotalConta;
	}

	public void setValorTotalConta(BigDecimal valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}

	public BigDecimal getValorTotalDebitosCobrar() {
		return valorTotalDebitosCobrar;
	}

	public void setValorTotalDebitosCobrar(BigDecimal valorTotalDebitosCobrar) {
		this.valorTotalDebitosCobrar = valorTotalDebitosCobrar;
	}

	public BigDecimal getValorTotalAcrescImpontualidade() {
		return valorTotalAcrescImpontualidade;
	}

	public void setValorTotalAcrescImpontualidade(
			BigDecimal valorTotalAcrescImpontualidade) {
		this.valorTotalAcrescImpontualidade = valorTotalAcrescImpontualidade;
	}

	public Short getIndicadorContasRevisao() {
		return indicadorContasRevisao;
	}

	public void setIndicadorContasRevisao(Short indicadorContasRevisao) {
		this.indicadorContasRevisao = indicadorContasRevisao;
	}

	public Short getIndicadorDebitoACobrar() {
		return indicadorDebitoACobrar;
	}

	public void setIndicadorDebitoACobrar(Short indicadorDebitoACobrar) {
		this.indicadorDebitoACobrar = indicadorDebitoACobrar;
	}

	public Short getIndicadorCreditoARealizar() {
		return indicadorCreditoARealizar;
	}

	public void setIndicadorCreditoARealizar(Short indicadorCreditoARealizar) {
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
	}

	public Integer getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Integer numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorJurosParcelamento() {
		return valorJurosParcelamento;
	}

	public void setValorJurosParcelamento(BigDecimal valorJurosParcelamento) {
		this.valorJurosParcelamento = valorJurosParcelamento;
	}

	public BigDecimal getValorTotalParcelado() {
		return valorTotalParcelado;
	}

	public void setValorTotalParcelado(BigDecimal valorTotalParcelado) {
		this.valorTotalParcelado = valorTotalParcelado;
	}

	public BigDecimal getValorDebitoAtualizado() {
		return valorDebitoAtualizado;
	}

	public void setValorDebitoAtualizado(BigDecimal valorDebitoAtualizado) {
		this.valorDebitoAtualizado = valorDebitoAtualizado;
	}

	public BigDecimal getValorParcelamentoACobrar() {
		return valorParcelamentoACobrar;
	}

	public void setValorParcelamentoACobrar(BigDecimal valorParcelamentoACobrar) {
		this.valorParcelamentoACobrar = valorParcelamentoACobrar;
	}

	public Integer getAnoMesReferenciaFaturamento() {
		return anoMesReferenciaFaturamento;
	}

	public void setAnoMesReferenciaFaturamento(Integer anoMesReferenciaFaturamento) {
		this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
	}

	public Date getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public ParcelamentoMotivoDesfazer getMotivoDesfazer() {
		return motivoDesfazer;
	}

	public void setMotivoDesfazer(ParcelamentoMotivoDesfazer motivoDesfazer) {
		this.motivoDesfazer = motivoDesfazer;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public QuantidadePrestacoes getQtdPrestacoesDaRDEscolhida() {
		return qtdPrestacoesDaRDEscolhida;
	}

	public void setQtdPrestacoesDaRDEscolhida(QuantidadePrestacoes qtdPrestacoesDaRDEscolhida) {
		this.qtdPrestacoesDaRDEscolhida = qtdPrestacoesDaRDEscolhida;
	}

	public Short getIndicadorParcelaInformadaPeloUsuario() {
		return indicadorParcelaInformadaPeloUsuario;
	}

	public void setIndicadorParcelaInformadaPeloUsuario(
			Short indicadorParcelaInformadaPeloUsuario) {
		this.indicadorParcelaInformadaPeloUsuario = indicadorParcelaInformadaPeloUsuario;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getNumero().toString();
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "numero"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Num. Contrato"};
		return labels;		
	}
}
