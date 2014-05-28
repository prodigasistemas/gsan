package gcom.faturamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoGeral implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorHistorico;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GuiaPagamento guiaPagamento;

    /** persistent field */
    private Set cobrancaDocumentoItems;

    /** persistent field */
    private Set parcelamentoItems;
    
    private GuiaPagamentoHistorico guiaPagamentoHistorico;
    
    /**
     * Description of the Field
     */

    public final static short INDICADOR_HISTORICO = 1;
    

    /** full constructor */
    public GuiaPagamentoGeral(Integer id, short indicadorHistorico, Date ultimaAlteracao, GuiaPagamento guiaPagamento, Set cobrancaDocumentoItems, Set parcelamentoItems) {
        this.id = id;
        this.indicadorHistorico = indicadorHistorico;
        this.ultimaAlteracao = ultimaAlteracao;
        this.guiaPagamento = guiaPagamento;
        this.cobrancaDocumentoItems = cobrancaDocumentoItems;
        this.parcelamentoItems = parcelamentoItems;
    }

    /** default constructor */
    public GuiaPagamentoGeral() {
    }

    /** minimal constructor */
    public GuiaPagamentoGeral(Integer id, short indicadorHistorico, Date ultimaAlteracao, Set cobrancaDocumentoItems, Set parcelamentoItems) {
        this.id = id;
        this.indicadorHistorico = indicadorHistorico;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cobrancaDocumentoItems = cobrancaDocumentoItems;
        this.parcelamentoItems = parcelamentoItems;
    }

   
    public Set getCobrancaDocumentoItems() {
        return this.cobrancaDocumentoItems;
    }

    public void setCobrancaDocumentoItems(Set cobrancaDocumentoItems) {
        this.cobrancaDocumentoItems = cobrancaDocumentoItems;
    }

    public Set getParcelamentoItems() {
        return this.parcelamentoItems;
    }

    public void setParcelamentoItems(Set parcelamentoItems) {
        this.parcelamentoItems = parcelamentoItems;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public GuiaPagamento getGuiaPagamento() {
        return guiaPagamento;
    }

    public void setGuiaPagamento(GuiaPagamento guiaPagamento) {
        this.guiaPagamento = guiaPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorHistorico() {
        return indicadorHistorico;
    }

    public void setIndicadorHistorico(short indicadorHistorico) {
        this.indicadorHistorico = indicadorHistorico;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

	public GuiaPagamentoHistorico getGuiaPagamentoHistorico() {
		return guiaPagamentoHistorico;
	}

	public void setGuiaPagamentoHistorico(GuiaPagamentoHistorico guiaPagamentoHistorico) {
		this.guiaPagamentoHistorico = guiaPagamentoHistorico;
	}
    
    

}