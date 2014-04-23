package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class RetificarConjuntoContaActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String contaSelected;
	private String vencimentoConta;
	private String motivoRetificacaoID;
	private String ligacaoAguaSituacaoID;
	private String ligacaoEsgotoSituacaoID;
	private String consumoAgua;
	private String consumoEsgoto;
	private String indicadorCategoriaEconomiaConta;
	
	private String indicadorDataAlterada;
	
	public String getContaSelected() {
		return contaSelected;
	}
	public void setContaSelected(String contaSelected) {
		this.contaSelected = contaSelected;
	}
	public String getLigacaoAguaSituacaoID() {
		return ligacaoAguaSituacaoID;
	}
	public void setLigacaoAguaSituacaoID(String ligacaoAguaSituacaoID) {
		this.ligacaoAguaSituacaoID = ligacaoAguaSituacaoID;
	}
	public String getLigacaoEsgotoSituacaoID() {
		return ligacaoEsgotoSituacaoID;
	}
	public void setLigacaoEsgotoSituacaoID(String ligacaoEsgotoSituacaoID) {
		this.ligacaoEsgotoSituacaoID = ligacaoEsgotoSituacaoID;
	}
	public String getConsumoAgua() {
		return consumoAgua;
	}
	public void setConsumoAgua(String consumoAgua) {
		this.consumoAgua = consumoAgua;
	}
	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}
	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}
	public String getVencimentoConta() {
		return vencimentoConta;
	}
	public void setVencimentoConta(String vencimentoConta) {
		this.vencimentoConta = vencimentoConta;
	}
	public String getIndicadorDataAlterada() {
		return indicadorDataAlterada;
	}
	public void setIndicadorDataAlterada(String indicadorDataAlterada) {
		this.indicadorDataAlterada = indicadorDataAlterada;
	}
	public String getMotivoRetificacaoID() {
		return motivoRetificacaoID;
	}
	public void setMotivoRetificacaoID(String motivoRetificacaoID) {
		this.motivoRetificacaoID = motivoRetificacaoID;
	}
	/**
	 * @return Retorna o campo indicadorCategoriaEconomiaConta.
	 */
	public String getIndicadorCategoriaEconomiaConta() {
		return indicadorCategoriaEconomiaConta;
	}
	/**
	 * @param indicadorCategoriaEconomiaConta O indicadorCategoriaEconomiaConta a ser setado.
	 */
	public void setIndicadorCategoriaEconomiaConta(
			String indicadorCategoriaEconomiaConta) {
		this.indicadorCategoriaEconomiaConta = indicadorCategoriaEconomiaConta;
	}
}
