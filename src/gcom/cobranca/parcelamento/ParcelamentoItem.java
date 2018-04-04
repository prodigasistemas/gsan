package gcom.cobranca.parcelamento;

import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.debito.DebitoACobrarGeral;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ParcelamentoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private Date ultimaAlteracao;
    private gcom.cobranca.parcelamento.Parcelamento parcelamento;
    private DebitoACobrarGeral debitoACobrarGeral;
    private DocumentoTipo documentoTipo;
    private ContaGeral contaGeral;
    private GuiaPagamentoGeral guiaPagamentoGeral;
    private CreditoARealizarGeral creditoARealizarGeral;

    public ParcelamentoItem(Date ultimaAlteracao,gcom.cobranca.parcelamento.Parcelamento parcelamento, 
    		DebitoACobrarGeral debitoACobrarGeral, DocumentoTipo documentoTipo, ContaGeral contaGeral, 
    		GuiaPagamentoGeral guiaPagamentoGeral, CreditoARealizarGeral creditoARealizarGeral) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamento = parcelamento;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.creditoARealizarGeral = creditoARealizarGeral;
    }

    public ParcelamentoItem() {
    }

    public ParcelamentoItem(Parcelamento parcelamento, 
    		DebitoACobrarGeral debitoACobrarGeral, DocumentoTipo documentoTipo, ContaGeral contaGeral, 
    		Quadra quadra, GuiaPagamentoGeral guiaPagamentoGeral, CreditoARealizarGeral creditoARealizarGeral) {
        this.parcelamento = parcelamento;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.creditoARealizarGeral = creditoARealizarGeral;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.parcelamento.Parcelamento getParcelamento() {
        return this.parcelamento;
    }

    public void setParcelamento(gcom.cobranca.parcelamento.Parcelamento parcelamento) {
        this.parcelamento = parcelamento;
    }

    public DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
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

}
