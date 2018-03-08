package gcom.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
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
    private GuiaPagamentoGeral guiaPagamentoGeral;
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

    public PagamentoHistorico(BigDecimal valorPagamento, Integer anoMesReferenciaPagamento, Date dataPagamento, 
    		Integer anoMesReferenciaArrecadacao, Date ultimaAlteracao, Integer codigoAgente, 
    		ArrecadacaoForma arrecadacaoForma, Imovel imovel, DocumentoTipo documentoTipo, 
    		ContaGeral contaGeral, Localidade localidade, PagamentoSituacao pagamentoSituacaoAtual, 
    		PagamentoSituacao pagamentoSituacaoAnterior, ArrecadadorMovimentoItem arrecadadorMovimentoItem, 
    		GuiaPagamentoGeral guiaPagamento, AvisoBancario avisoBancario, DebitoTipo debitoTipo, Cliente cliente) {
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
        this.guiaPagamentoGeral = guiaPagamento;
        this.avisoBancario = avisoBancario;
        this.debitoTipo = debitoTipo;
        this.cliente = cliente;
    }

    public PagamentoHistorico() {
    }
    
    public PagamentoHistorico(Pagamento pagamento) {
    	this.setId(pagamento.getId());
		this.setAnoMesReferenciaArrecadacao(pagamento.getAnoMesReferenciaArrecadacao());
		this.setAnoMesReferenciaPagamento(pagamento.getAnoMesReferenciaPagamento());
		this.setArrecadacaoForma(pagamento.getArrecadacaoForma());
		this.setArrecadadorMovimentoItem(pagamento.getArrecadadorMovimentoItem());
		this.setAvisoBancario(pagamento.getAvisoBancario());
		this.setCliente(pagamento.getCliente());
		this.setDataPagamento(pagamento.getDataPagamento());
		this.setDebitoACobrarGeral(pagamento.getDebitoACobrarGeral());
		this.setDebitoTipo(pagamento.getDebitoTipo());
		this.setDocumentoTipo(pagamento.getDocumentoTipo());
		this.setImovel(pagamento.getImovel());
		this.setLocalidade(pagamento.getLocalidade());
		this.setPagamentoSituacaoAnterior(pagamento.getPagamentoSituacaoAnterior());
		this.setPagamentoSituacaoAtual(pagamento.getPagamentoSituacaoAtual());
		this.setUltimaAlteracao(new Date());
		this.setValorPagamento(pagamento.getValorPagamento());
		this.setValorExcedente(pagamento.getValorExcedente());
		this.setIndicadorExpurgado(pagamento.getIndicadorExpurgado());

		
		if (pagamento.getContaGeral() != null && pagamento.getContaGeral().getId() != null) {
			this.setContaGeral(pagamento.getContaGeral());
		}
		
		if (pagamento.getCobrancaDocumento() != null){
			this.setCobrancaDocumentoAgregador(pagamento.getCobrancaDocumento());
		}
		
		if (pagamento.getDocumentoTipoAgregador() != null){
			this.setDocumentoTipoAgregador(pagamento.getDocumentoTipoAgregador());
		}
		
		if (pagamento.getFatura() != null){
			this.setFatura(pagamento.getFatura());
		}
		
		if (pagamento.getDataProcessamento() != null){
			this.setDataHoraProcessamento(pagamento.getDataProcessamento());
		}

		if (pagamento.getGuiaPagamento() != null && pagamento.getGuiaPagamento().getId() != null) {
			this.setGuiaPagamentoGeral(pagamento.getGuiaPagamento());
		} 

    }

    public PagamentoHistorico(BigDecimal valorPagamento, Integer anoMesReferenciaPagamento, Date dataPagamento, 
    		Integer anoMesReferenciaArrecadacao, ArrecadacaoForma arrecadacaoForma, Imovel imovel, DocumentoTipo documentoTipo, 
    		ContaGeral contaGeral, Localidade localidade, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual, 
    		PagamentoSituacao pagamentoSituacaoAnterior, ArrecadadorMovimentoItem arrecadadorMovimentoItem, GuiaPagamentoGeral guiaPagamento, 
    		AvisoBancario avisoBancario, DebitoTipo debitoTipo, Cliente cliente) {
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
        this.guiaPagamentoGeral = guiaPagamento;
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

	public Integer getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	public ArrecadadorMovimentoItem getArrecadadorMovimentoItem() {
		return arrecadadorMovimentoItem;
	}

	public void setArrecadadorMovimentoItem(ArrecadadorMovimentoItem arrecadadorMovimentoItem) {
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
	}

	public AvisoBancario getAvisoBancario() {
		return avisoBancario;
	}

	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCodigoAgente() {
		return codigoAgente;
	}

	public void setCodigoAgente(Integer codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	public ContaGeral getContaGeral() {
		return this.contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public PagamentoSituacao getPagamentoSituacaoAnterior() {
		return pagamentoSituacaoAnterior;
	}

	public void setPagamentoSituacaoAnterior(PagamentoSituacao pagamentoSituacaoAnterior) {
		this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
	}

	public PagamentoSituacao getPagamentoSituacaoAtual() {
		return this.pagamentoSituacaoAtual;
	}

	public void setPagamentoSituacaoAtual(PagamentoSituacao pagamentoSituacaoAtual) {
		this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorExcedente() {
		return valorExcedente;
	}

	public void setValorExcedente(BigDecimal valorExcedente) {
		this.valorExcedente = valorExcedente;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

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
