package gcom.gui.arrecadacao.pagamento;

import org.apache.struts.action.ActionForm;

public class FiltrarGuiaPagamentoEmAtrasoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String financiamentoTipoId;
	private String vencimentoInicial;
	private String vencimentoFinal;
	private String referenciaInicialContabil;
	private String referenciaFinalContabil;
	public String getFinanciamentoTipoId() {
		return financiamentoTipoId;
	}
	public void setFinanciamentoTipoId(String financiamentoTipoId) {
		this.financiamentoTipoId = financiamentoTipoId;
	}
	public String getReferenciaFinalContabil() {
		return referenciaFinalContabil;
	}
	public void setReferenciaFinalContabil(String referenciaFinalContabil) {
		this.referenciaFinalContabil = referenciaFinalContabil;
	}
	public String getReferenciaInicialContabil() {
		return referenciaInicialContabil;
	}
	public void setReferenciaInicialContabil(String referenciaInicialContabil) {
		this.referenciaInicialContabil = referenciaInicialContabil;
	}
	public String getVencimentoInicial() {
		return vencimentoInicial;
	}
	public void setVencimentoInicial(String vencimanetoInicial) {
		this.vencimentoInicial = vencimanetoInicial;
	}
	public String getVencimentoFinal() {
		return vencimentoFinal;
	}
	public void setVencimentoFinal(String vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}
	

}
