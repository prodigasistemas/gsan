package gcom.arrecadacao.pagamento;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.GuiaPagamentoGeral;

import java.io.Serializable;
import java.util.Date;

public class GuiaPagamentoParcelamentoCartao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private gcom.arrecadacao.pagamento.GuiaPagamentoParcelamentoCartaoPK comp_id;
	
	private GuiaPagamentoGeral guiaPagamentoGeral;

    private Parcelamento parcelamento;
    
    private Date ultimaAlteracao;
    
    public GuiaPagamentoParcelamentoCartao(){}

	public GuiaPagamentoParcelamentoCartao(GuiaPagamentoParcelamentoCartaoPK comp_id, GuiaPagamentoGeral guiaPagamentoGeral, Parcelamento parcelamento, Date ultimaAlteracao) {
		super();
		// TODO Auto-generated constructor stub
		this.comp_id = comp_id;
		this.guiaPagamentoGeral = guiaPagamentoGeral;
		this.parcelamento = parcelamento;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.arrecadacao.pagamento.GuiaPagamentoParcelamentoCartaoPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(
			gcom.arrecadacao.pagamento.GuiaPagamentoParcelamentoCartaoPK comp_id) {
		this.comp_id = comp_id;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
