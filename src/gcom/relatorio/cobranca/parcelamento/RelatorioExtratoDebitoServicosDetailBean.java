package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0444] Gerar e Emitir Extrato de Débito
 * @author Vivianne Sousa
 * @date 23/04/2010
 */
public class RelatorioExtratoDebitoServicosDetailBean implements RelatorioBean {

	private String descricaoServico;
	private String referenciaServico;
	private String parcelasServico;
	private String valorServico;
	private String vencimentoServico;
	
	public RelatorioExtratoDebitoServicosDetailBean(String descricaoServico,
			String referenciaServico, String parcelasServico, String valorServico,String vencimentoServico) {
		super();
		
		this.descricaoServico = descricaoServico;
		this.referenciaServico = referenciaServico;
		this.parcelasServico = parcelasServico;
		this.valorServico = valorServico;
		this.vencimentoServico = vencimentoServico;
	}
	public String getDescricaoServico() {
		return descricaoServico;
	}
	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}
	public String getParcelasServico() {
		return parcelasServico;
	}
	public void setParcelasServico(String parcelasServico) {
		this.parcelasServico = parcelasServico;
	}
	public String getReferenciaServico() {
		return referenciaServico;
	}
	public void setReferenciaServico(String referenciaServico) {
		this.referenciaServico = referenciaServico;
	}
	public String getValorServico() {
		return valorServico;
	}
	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}
	public String getVencimentoServico() {
		return vencimentoServico;
	}
	public void setVencimentoServico(String vencimentoServico) {
		this.vencimentoServico = vencimentoServico;
	}
	

}
