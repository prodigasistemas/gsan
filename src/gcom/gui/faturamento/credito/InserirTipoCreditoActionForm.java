package gcom.gui.faturamento.credito;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InserirTipoCreditoActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;
	
	private String abreviatura;
	
	private String tipoLancamentoContabil;
	
	private String indicadorgeracaoAutomaica;
	
	private String valorLimiteCredito;



	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Retorna o campo abreviatura.
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @param abreviatura O abreviatura a ser setado.
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
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
	 * @return Retorna o campo indicadorgeracaoAutomaica.
	 */
	public String getIndicadorgeracaoAutomaica() {
		return indicadorgeracaoAutomaica;
	}

	/**
	 * @param indicadorgeracaoAutomaica O indicadorgeracaoAutomaica a ser setado.
	 */
	public void setIndicadorgeracaoAutomaica(String indicadorgeracaoAutomaica) {
		this.indicadorgeracaoAutomaica = indicadorgeracaoAutomaica;
	}

	/**
	 * @return Retorna o campo tipoLancamentoContabil.
	 */
	public String getTipoLancamentoContabil() {
		return tipoLancamentoContabil;
	}

	/**
	 * @param tipoLancamentoContabil O tipoLancamentoContabil a ser setado.
	 */
	public void setTipoLancamentoContabil(String tipoLancamentoContabil) {
		this.tipoLancamentoContabil = tipoLancamentoContabil;
	}

	/**
	 * @return Retorna o campo valorLimiteCredito.
	 */
	public String getValorLimiteCredito() {
		return valorLimiteCredito;
	}

	/**
	 * @param valorLimiteCredito O valorLimiteCredito a ser setado.
	 */
	public void setValorLimiteCredito(String valorLimiteCredito) {
		this.valorLimiteCredito = valorLimiteCredito;
	}



}
