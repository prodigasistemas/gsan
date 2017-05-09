package gcom.cobranca;

import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CobrancaDocumentoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private BigDecimal valorItemCobrado;
    private Date ultimaAlteracao;
    private DebitoACobrarGeral debitoACobrarGeral;
    private CobrancaDocumento cobrancaDocumento;
    private DocumentoTipo documentoTipo;
    private ContaGeral contaGeral;
    private GuiaPagamentoGeral guiaPagamentoGeral;
    private Date dataSituacaoDebito;
    private BigDecimal  valorAcrescimos;
    private CobrancaDebitoSituacao cobrancaDebitoSituacao;
    private CreditoARealizarGeral creditoARealizarGeral;
    private Integer numeroParcelasAntecipadas;
    private PrestacaoContratoParcelamento prestacaoContratoParcelamento;

    /** full constructor */
    public CobrancaDocumentoItem(BigDecimal valorItemCobrado, Date ultimaAlteracao, DebitoACobrarGeral debitoACobrarGeral, CobrancaDocumento cobrancaDocumento, DocumentoTipo documentoTipo, ContaGeral contaGeral, GuiaPagamentoGeral guiaPagamentoGeral) {
        this.valorItemCobrado = valorItemCobrado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.cobrancaDocumento = cobrancaDocumento;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
    }

    /** default constructor */
    public CobrancaDocumentoItem() {
    }

    /** minimal constructor */
    public CobrancaDocumentoItem(DebitoACobrarGeral debitoACobrarGeral, CobrancaDocumento cobrancaDocumento, DocumentoTipo documentoTipo, ContaGeral contaGeral, GuiaPagamentoGeral guiaPagamentoGeral) {
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.cobrancaDocumento = cobrancaDocumento;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorItemCobrado() {
        return this.valorItemCobrado;
    }

    public void setValorItemCobrado(BigDecimal valorItemCobrado) {
        this.valorItemCobrado = valorItemCobrado;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }


    public CobrancaDocumento getCobrancaDocumento() {
        return this.cobrancaDocumento;
    }

    public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
        this.cobrancaDocumento = cobrancaDocumento;
    }
    
    

    public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public gcom.cobranca.DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(gcom.cobranca.DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }


    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	public void setCobrancaDebitoSituacao(CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}

	public Integer getNumeroParcelasAntecipadas() {
		return numeroParcelasAntecipadas;
	}

	public void setNumeroParcelasAntecipadas(Integer numeroParcelasAntecipadas) {
		this.numeroParcelasAntecipadas = numeroParcelasAntecipadas;
	}

	public PrestacaoContratoParcelamento getPrestacaoContratoParcelamento() {
		return prestacaoContratoParcelamento;
	}

	public void setPrestacaoContratoParcelamento(
			PrestacaoContratoParcelamento prestacaoContratoParcelamento) {
		this.prestacaoContratoParcelamento = prestacaoContratoParcelamento;
	}
}
