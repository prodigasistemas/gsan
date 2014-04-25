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


/** @author Hibernate CodeGenerator */
public class ParcelamentoItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    //private CreditoARealizarHistorico creditoARealizarHistorico;

    /** persistent field */
    //private DebitoACobrarHistorico debitoACobrarHistorico;

    /** persistent field */
    private gcom.cobranca.parcelamento.Parcelamento parcelamento;

    /** persistent field */
    private DebitoACobrarGeral debitoACobrarGeral;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private ContaGeral contaGeral;

    /** persistent field */
    //private ContaHistorico contaHistorico;

    /** persistent field */
    private GuiaPagamentoGeral guiaPagamentoGeral;

    /** persistent field */
    private CreditoARealizarGeral creditoARealizarGeral;

    /** full constructor */
    //public ParcelamentoItem(Date ultimaAlteracao, CreditoARealizarHistorico creditoARealizarHistorico, DebitoACobrarHistorico debitoACobrarHistorico, gcom.cobranca.parcelamento.Parcelamento parcelamento, DebitoACobrar debitoACobrar, DocumentoTipo documentoTipo, Conta conta, ContaHistorico contaHistorico, GuiaPagamento guiaPagamento, CreditoARealizar creditoARealizar) {
    public ParcelamentoItem(Date ultimaAlteracao,gcom.cobranca.parcelamento.Parcelamento parcelamento, 
    		DebitoACobrarGeral debitoACobrarGeral, DocumentoTipo documentoTipo, ContaGeral contaGeral, 
    		GuiaPagamentoGeral guiaPagamentoGeral, CreditoARealizarGeral creditoARealizarGeral) {
        this.ultimaAlteracao = ultimaAlteracao;
        //this.creditoARealizarHistorico = creditoARealizarHistorico;
        //this.debitoACobrarHistorico = debitoACobrarHistorico;
        this.parcelamento = parcelamento;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        //this.contaHistorico = contaHistorico;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.creditoARealizarGeral = creditoARealizarGeral;
    }

    /** default constructor */
    public ParcelamentoItem() {
    }

    /** minimal constructor */
    //public ParcelamentoItem(CreditoARealizarHistorico creditoARealizarHistorico, DebitoACobrarHistorico debitoACobrarHistorico, gcom.cobranca.parcelamento.Parcelamento parcelamento, DebitoACobrar debitoACobrar, DocumentoTipo documentoTipo, Conta conta, Quadra quadra, ContaHistorico contaHistorico, GuiaPagamento guiaPagamento, CreditoARealizar creditoARealizar) {
    public ParcelamentoItem(Parcelamento parcelamento, 
    		DebitoACobrarGeral debitoACobrarGeral, DocumentoTipo documentoTipo, ContaGeral contaGeral, 
    		Quadra quadra, GuiaPagamentoGeral guiaPagamentoGeral, CreditoARealizarGeral creditoARealizarGeral) {
        //this.creditoARealizarHistorico = creditoARealizarHistorico;
        //this.debitoACobrarHistorico = debitoACobrarHistorico;
        this.parcelamento = parcelamento;
        this.debitoACobrarGeral = debitoACobrarGeral;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        //this.contaHistorico = contaHistorico;
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
/*
    public CreditoARealizarHistorico getCreditoARealizarHistorico() {
        return this.creditoARealizarHistorico;
    }

    public void setCreditoARealizarHistorico(CreditoARealizarHistorico creditoARealizarHistorico) {
        this.creditoARealizarHistorico = creditoARealizarHistorico;
    }

    public DebitoACobrarHistorico getDebitoACobrarHistorico() {
        return this.debitoACobrarHistorico;
    }

    public void setDebitoACobrarHistorico(DebitoACobrarHistorico debitoACobrarHistorico) {
        this.debitoACobrarHistorico = debitoACobrarHistorico;
    }
*/
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

  

   /* public ContaHistorico getContaHistorico() {
        return this.contaHistorico;
    }

    public void setContaHistorico(ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }*/

    
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
