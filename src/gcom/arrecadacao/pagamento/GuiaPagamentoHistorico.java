package gcom.arrecadacao.pagamento;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


public class GuiaPagamentoHistorico implements Serializable, IGuiaPagamento {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private int anoMesReferenciaContabil;
    private Date dataEmissao;
    private Date dataVencimento;
    private BigDecimal valorDebito;
    private Date ultimaAlteracao;
    private short indicadorMulta;
    private Short numeroPrestacaoDebito;
    private Short numeroPrestacaoTotal;
    private String observacao;
	private Short indicadorEmitirObservacao;
    private GuiaPagamentoGeral guiaPagamentoGeral;
    private Cliente cliente;
    private Imovel imovel;
    private FinanciamentoTipo financiamentoTipo;
    private Localidade localidade;
    private DebitoTipo debitoTipo;
    private LancamentoItemContabil lancamentoItemContabil;
    private Parcelamento parcelamento;
    private DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdanterior;
    private DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdatual;
    private RegistroAtendimento registroAtendimento;
    private DocumentoTipo documentoTipo;
    private OrdemServico ordemServico;
    
    @SuppressWarnings("rawtypes")
	private Set guiaPagamentoCategoriaHistoricos;
    
    private GuiaPagamentoGeral origem;
    private Usuario usuario;
    private String numeroGuiaFatura;

    
    public GuiaPagamentoHistorico() { }
    
    public GuiaPagamentoHistorico(Integer id) { 
    	this.id = id;
    }
    
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("gpagId", getId())
            .toString();
    }

	public Integer getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoByDcstIdanterior() {
		return debitoCreditoSituacaoByDcstIdanterior;
	}

	public void setDebitoCreditoSituacaoByDcstIdanterior(DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdanterior) {
		this.debitoCreditoSituacaoByDcstIdanterior = debitoCreditoSituacaoByDcstIdanterior;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoByDcstIdatual() {
		return debitoCreditoSituacaoByDcstIdatual;
	}

	public void setDebitoCreditoSituacaoByDcstIdatual(DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdatual) {
		this.debitoCreditoSituacaoByDcstIdatual = debitoCreditoSituacaoByDcstIdatual;
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

	public FinanciamentoTipo getFinanciamentoTipo() {
		return financiamentoTipo;
	}

	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public Set getGuiaPagamentoCategoriaHistoricos() {
		return guiaPagamentoCategoriaHistoricos;
	}


	public void setGuiaPagamentoCategoriaHistoricos(Set guiaPagamentoCategoriaHistoricos) {
		this.guiaPagamentoCategoriaHistoricos = guiaPagamentoCategoriaHistoricos;
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

	public short getIndicadorMulta() {
		return indicadorMulta;
	}

	public void setIndicadorMulta(short indicadorMulta) {
		this.indicadorMulta = indicadorMulta;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public GuiaPagamentoGeral getOrigem() {
		return origem;
	}

	public void setOrigem(GuiaPagamentoGeral origem) {
		this.origem = origem;
	}

	public Short getNumeroPrestacaoDebito() {
		return numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public Short getNumeroPrestacaoTotal() {
		return numeroPrestacaoTotal;
	}

	public void setNumeroPrestacaoTotal(Short numeroPrestacaoTotal) {
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
	}

	public String getPrestacaoFormatada(){
        String prestacaoFormatada = "";
        
        prestacaoFormatada = prestacaoFormatada + getNumeroPrestacaoDebito() + "/" + getNumeroPrestacaoTotal();
        
        return  prestacaoFormatada ;
    }

	public Short getIndicadorEmitirObservacao() {
		return indicadorEmitirObservacao;
	}

	public void setIndicadorEmitirObservacao(Short indicadorEmitirObservacao) {
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNumeroGuiaFatura() {
		return numeroGuiaFatura;
	}

	public void setNumeroGuiaFatura(String numeroGuiaFatura) {
		this.numeroGuiaFatura = numeroGuiaFatura;
	}
}
