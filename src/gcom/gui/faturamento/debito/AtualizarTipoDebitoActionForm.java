package gcom.gui.faturamento.debito;
import org.apache.struts.validator.ValidatorActionForm;
/** * Descrição da classe *  * @author Rômulo Aurélio * @date 14/03/2007 */
public class AtualizarTipoDebitoActionForm extends ValidatorActionForm {
	/**	 * 	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String idTipoDebito;
	private String descricao;
	private String descricaoAbreviada;
	private String lancamentoItemContabil;
	private String financiamentoTipo;
	private String indicadorGeracaoDebitoAutomatica;
	private String indicadorGeracaoDebitoConta;
	private String indicadorUso;
	private String valorLimiteDebito;		private String valorSugerido;		private String indicadorDebitoCartaoCredito;		private String indicadorJurosParCliente;	public String getIndicadorDebitoCartaoCredito() {		return indicadorDebitoCartaoCredito;	}	public void setIndicadorDebitoCartaoCredito(String indicadorDebitoCartaoCredito) {		this.indicadorDebitoCartaoCredito = indicadorDebitoCartaoCredito;	}	/**
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
	 * @return Retorna o campo codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo O codigo a ser setado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo idTipoDebito.
	 */
	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	/**
	 * @param idTipoDebito O idTipoDebito a ser setado.
	 */
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}	/**	 * @return Retorna o campo valorSugerido.	 */	public String getValorSugerido() {		return valorSugerido;	}	/**	 * @param valorSugerido O valorSugerido a ser setado.	 */	public void setValorSugerido(String valorSugerido) {		this.valorSugerido = valorSugerido;	}		/**	 * @return Retorna o campo indicadorJurosParCliente.	 */	public String getIndicadorJurosParCliente() {		return indicadorJurosParCliente;	}	/**	 * @param indicadorJurosParCliente O indicador de juros parcelado a ser setado.	 */		public void setIndicadorJurosParCliente(String indicadorJurosParCliente) {		this.indicadorJurosParCliente = indicadorJurosParCliente;	}
	
}
