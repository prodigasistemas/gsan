package gcom.gui.faturamento.debito;
import org.apache.struts.validator.ValidatorActionForm;
/**
 * Descrição da classe 
 *
 * @author RômuloAurélio
 * @date 09/03/2007
 */
public class InserirTipoDebitoActionForm  extends ValidatorActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private String descricaoAbreviada;
	private String lancamentoItemContabil;
	private String financiamentoTipo;
	private String indicadorGeracaoDebitoAutomatica;
	private String indicadorGeracaoDebitoConta;
	private String valorLimiteDebito;
	private String valorSugerido;		private String indicadorDebitoCartaoCredito;		private String indicadorJurosParCliente;	public String getIndicadorDebitoCartaoCredito() {		return indicadorDebitoCartaoCredito;	}	public void setIndicadorDebitoCartaoCredito(String indicadorDebitoCartaoCredito) {		this.indicadorDebitoCartaoCredito = indicadorDebitoCartaoCredito;	}	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Retorna o campo descricaoAbreviada.
	 */
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	/**
	 * @param descricaoAbreviada O descricaoAbreviada a ser setado.
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	/**
	 * @return Retorna o campo financiamentoTipo.
	 */
	public String getFinanciamentoTipo() {
		return financiamentoTipo;
	}
	/**
	 * @param financiamentoTipo O financiamentoTipo a ser setado.
	 */
	public void setFinanciamentoTipo(String financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}
	/**
	 * @return Retorna o campo indicadorGeracaoDebitoAutomatica.
	 */
	public String getIndicadorGeracaoDebitoAutomatica() {
		return indicadorGeracaoDebitoAutomatica;
	}
	/**
	 * @param indicadorGeracaoDebitoAutomatica O indicadorGeracaoDebitoAutomatica a ser setado.
	 */
	public void setIndicadorGeracaoDebitoAutomatica(
			String indicadorGeracaoDebitoAutomatica) {
		this.indicadorGeracaoDebitoAutomatica = indicadorGeracaoDebitoAutomatica;
	}
	/**
	 * @return Retorna o campo indicadorGeracaoDebitoConta.
	 */
	public String getIndicadorGeracaoDebitoConta() {
		return indicadorGeracaoDebitoConta;
	}
	/**
	 * @param indicadorGeracaoDebitoConta O indicadorGeracaoDebitoConta a ser setado.
	 */
	public void setIndicadorGeracaoDebitoConta(String indicadorGeracaoDebitoConta) {
		this.indicadorGeracaoDebitoConta = indicadorGeracaoDebitoConta;
	}
	/**
	 * @return Retorna o campo lancamentoItemContabil.
	 */
	public String getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}
	/**
	 * @param lancamentoItemContabil O lancamentoItemContabil a ser setado.
	 */
	public void setLancamentoItemContabil(String lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}
	/**
	 * @return Retorna o campo valorLimiteDebito.
	 */
	public String getValorLimiteDebito() {
		return valorLimiteDebito;
	}
	/**
	 * @param valorLimiteDebito O valorLimiteDebito a ser setado.
	 */
	public void setValorLimiteDebito(String valorLimiteDebito) {
		this.valorLimiteDebito = valorLimiteDebito;
	}
	/**
	 * @return Retorna o campo valorSugerido.
	 */
	public String getValorSugerido() {
		return valorSugerido;
	}
	/**
	 * @param valorSugerido O valorSugerido a ser setado.
	 */
	public void setValorSugerido(String valorSugerido) {
		this.valorSugerido = valorSugerido;
	}		public String getIndicadorJurosParCliente() {		return indicadorJurosParCliente;	}	public void setIndicadorJurosParCliente(String indicadorJurosParCliente) {		this.indicadorJurosParCliente = indicadorJurosParCliente;	}
}
