package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos 
 *
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioGerarRelacaoDebitosTipoDebitoCreditoBean implements RelatorioBean {
	
	
	private String debitoTipoDebito;
	private String debitoValor;
	
	/**
	 * @return Retorna o campo debitoTipoDebito.
	 */
	public String getDebitoTipoDebito() {
		return debitoTipoDebito;
	}
	/**
	 * @param debitoTipoDebito O debitoTipoDebito a ser setado.
	 */
	public void setDebitoTipoDebito(String debitoTipoDebito) {
		this.debitoTipoDebito = debitoTipoDebito;
	}
	/**
	 * @return Retorna o campo debitoValor.
	 */
	public String getDebitoValor() {
		return debitoValor;
	}
	/**
	 * @param debitoValor O debitoValor a ser setado.
	 */
	public void setDebitoValor(String debitoValor) {
		this.debitoValor = debitoValor;
	}
}
