package gcom.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PagamentoHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private BigDecimal valorPagamento;
    private BigDecimal valorExcedente;
    private Integer anoMesReferenciaPagamento;
    private Date dataPagamento;
    private Integer anoMesReferenciaArrecadacao;
    private Date ultimaAlteracao;
    private Integer codigoAgente;
    private ArrecadacaoForma arrecadacaoForma;
    private Imovel imovel;
    private DocumentoTipo documentoTipo;
    private ContaGeral contaGeral;
    private Localidade localidade;
	private Short indicadorExpurgado;
    private PagamentoSituacao pagamentoSituacaoAtual;
    private PagamentoSituacao pagamentoSituacaoAnterior;
    private ArrecadadorMovimentoItem arrecadadorMovimentoItem;
    private GuiaPagamento guiaPagamento;
	private DebitoACobrarGeral debitoACobrarGeral;
    private AvisoBancario avisoBancario;
    private DebitoTipo debitoTipo;
    private Cliente cliente;
    private CobrancaDocumento cobrancaDocumentoAgregador;
    private DocumentoTipo documentoTipoAgregador;
    private Fatura fatura;
    private Date dataHoraProcessamento;
    
    private GuiaPagamentoHistorico guiaPagamentoHistorico;

	public final static Short INDICADOR_EXPURGADO_SIM = new Short("1");
	public final static Short INDICADOR_EXPURGADO_NAO = new Short("2");

    /** full constructor */
    public PagamentoHistorico(BigDecimal valorPagamento, Integer anoMesReferenciaPagamento, Date dataPagamento, Integer anoMesReferenciaArrecadacao, Date ultimaAlteracao, Integer codigoAgente, ArrecadacaoForma arrecadacaoForma, Imovel imovel, DocumentoTipo documentoTipo, ContaGeral contaGeral, Localidade localidade, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior, ArrecadadorMovimentoItem arrecadadorMovimentoItem, gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento, AvisoBancario avisoBancario, DebitoTipo debitoTipo, Cliente cliente) {
        this.valorPagamento = valorPagamento;
        this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
        this.dataPagamento = dataPagamento;
        this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.codigoAgente = codigoAgente;
        this.arrecadacaoForma = arrecadacaoForma;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.localidade = localidade;
        this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
        this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
        this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
        this.guiaPagamento = guiaPagamento;
        this.avisoBancario = avisoBancario;
        this.debitoTipo = debitoTipo;
        this.cliente = cliente;
    }

    /** default constructor */
    public PagamentoHistorico() {
    }

    /** minimal constructor */
    public PagamentoHistorico(BigDecimal valorPagamento, Integer anoMesReferenciaPagamento, Date dataPagamento, Integer anoMesReferenciaArrecadacao, ArrecadacaoForma arrecadacaoForma, Imovel imovel, DocumentoTipo documentoTipo, ContaGeral contaGeral, Localidade localidade, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior, ArrecadadorMovimentoItem arrecadadorMovimentoItem, gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento, AvisoBancario avisoBancario, DebitoTipo debitoTipo, Cliente cliente) {
        this.valorPagamento = valorPagamento;
        this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
        this.dataPagamento = dataPagamento;
        this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
        this.arrecadacaoForma = arrecadacaoForma;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.localidade = localidade;
        this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
        this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
        this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
        this.guiaPagamento = guiaPagamento;
        this.avisoBancario = avisoBancario;
        this.debitoTipo = debitoTipo;
        this.cliente = cliente;
    }

   
	
	public String getFormatarAnoMesParaMesAnoArrecadacao() {

		String anoMesArrecadacaoRecebido = "" + this.getAnoMesReferenciaArrecadacao();
		String mesArrecadacao = anoMesArrecadacaoRecebido.substring(4, 6);
		String anoArrecadacao = anoMesArrecadacaoRecebido.substring(0, 4);
		String anoMesArrecadacaoFormatado = mesArrecadacao + "/" + anoArrecadacao;

		return anoMesArrecadacaoFormatado.toString();
	}

	public String getFormatarAnoMesReferenciaPagamento() {

		String anoMesReferenciaPagamento = "" + this.getAnoMesReferenciaPagamento();
		String mesPagamento = anoMesReferenciaPagamento.substring(4, 6);
		String anoPagamento = anoMesReferenciaPagamento.substring(0, 4);
		String anoMesPagamentoFormatado = mesPagamento + "/" + anoPagamento;

		return anoMesPagamentoFormatado.toString();
	}

	/**
	 * @return Retorna o campo anoMesReferenciaArrecadacao.
	 */
	public Integer getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	/**
	 * @param anoMesReferenciaArrecadacao O anoMesReferenciaArrecadacao a ser setado.
	 */
	public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	/**
	 * @return Retorna o campo anoMesReferenciaPagamento.
	 */
	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	/**
	 * @param anoMesReferenciaPagamento O anoMesReferenciaPagamento a ser setado.
	 */
	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	/**
	 * @return Retorna o campo arrecadacaoForma.
	 */
	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	/**
	 * @param arrecadacaoForma O arrecadacaoForma a ser setado.
	 */
	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	/**
	 * @return Retorna o campo arrecadadorMovimentoItem.
	 */
	public ArrecadadorMovimentoItem getArrecadadorMovimentoItem() {
		return arrecadadorMovimentoItem;
	}

	/**
	 * @param arrecadadorMovimentoItem O arrecadadorMovimentoItem a ser setado.
	 */
	public void setArrecadadorMovimentoItem(
			ArrecadadorMovimentoItem arrecadadorMovimentoItem) {
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
	}

	/**
	 * @return Retorna o campo avisoBancario.
	 */
	public AvisoBancario getAvisoBancario() {
		return avisoBancario;
	}

	/**
	 * @param avisoBancario O avisoBancario a ser setado.
	 */
	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	/**
	 * @return Retorna o campo cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Retorna o campo codigoAgente.
	 */
	public Integer getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * @param codigoAgente O codigoAgente a ser setado.
	 */
	public void setCodigoAgente(Integer codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * @return Retorna o campo conta.
	 */
	public ContaGeral getContaGeral() {
		return this.contaGeral;
	}

	/**
	 * @param conta O conta a ser setado.
	 */
	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	/**
	 * @return Retorna o campo debitoTipo.
	 */
	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	/**
	 * @param debitoTipo O debitoTipo a ser setado.
	 */
	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	/**
	 * @return Retorna o campo documentoTipo.
	 */
	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	/**
	 * @param documentoTipo O documentoTipo a ser setado.
	 */
	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	/**
	 * @return Retorna o campo guiaPagamento.
	 */
	public gcom.arrecadacao.pagamento.GuiaPagamento getGuiaPagamento() {
		return guiaPagamento;
	}

	/**
	 * @param guiaPagamento O guiaPagamento a ser setado.
	 */
	public void setGuiaPagamento(
			gcom.arrecadacao.pagamento.GuiaPagamento guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo pagamentoSituacaoAnterior.
	 */
	public gcom.arrecadacao.pagamento.PagamentoSituacao getPagamentoSituacaoAnterior() {
		return pagamentoSituacaoAnterior;
	}

	/**
	 * @param pagamentoSituacaoAnterior O pagamentoSituacaoAnterior a ser setado.
	 */
	public void setPagamentoSituacaoAnterior(
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior) {
		this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
	}

	/**
	 * @return Retorna o campo pagamentoSituacaoAtual.
	 */
	public gcom.arrecadacao.pagamento.PagamentoSituacao getPagamentoSituacaoAtual() {
		return this.pagamentoSituacaoAtual;
	}

	/**
	 * @param pagamentoSituacaoAtual O pagamentoSituacaoAtual a ser setado.
	 */
	public void setPagamentoSituacaoAtual(
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual) {
		this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorExcedente.
	 */
	public BigDecimal getValorExcedente() {
		return valorExcedente;
	}

	/**
	 * @param valorExcedente O valorExcedente a ser setado.
	 */
	public void setValorExcedente(BigDecimal valorExcedente) {
		this.valorExcedente = valorExcedente;
	}

	/**
	 * @return Retorna o campo valorPagamento.
	 */
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	/**
	 * @param valorPagamento O valorPagamento a ser setado.
	 */
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public Short getIndicadorExpurgado() {
		return indicadorExpurgado;
	}

	public void setIndicadorExpurgado(Short indicadorExpurgado) {
		this.indicadorExpurgado = indicadorExpurgado;
	}

	public CobrancaDocumento getCobrancaDocumentoAgregador() {
		return cobrancaDocumentoAgregador;
	}

	public void setCobrancaDocumentoAgregador(
			CobrancaDocumento cobrancaDocumentoAgregador) {
		this.cobrancaDocumentoAgregador = cobrancaDocumentoAgregador;
	}

	public Date getDataHoraProcessamento() {
		return dataHoraProcessamento;
	}

	public void setDataHoraProcessamento(Date dataHoraProcessamento) {
		this.dataHoraProcessamento = dataHoraProcessamento;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public GuiaPagamentoHistorico getGuiaPagamentoHistorico() {
		return guiaPagamentoHistorico;
	}

	public void setGuiaPagamentoHistorico(GuiaPagamentoHistorico guiaPagamentoHistorico) {
		this.guiaPagamentoHistorico = guiaPagamentoHistorico;
	}

   
}
