package gcom.gui.faturamento.credito;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FiltrarTipoCreditoActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;

	private String descricao;

	private String abreviatura;

	private String tipoLancamentoContabil;

	private String indicativoUso;

	private String indicadorgeracaoAutomaica;

	private String valorLimiteCreditoInicial;

	private String valorLimiteCreditoFinal;
	
	private String atualizar;
	
	private String tipoPesquisa;

	/**
	 * @return Retorna o campo atualizar.
	 */
	public String getAtualizar() {
		return atualizar;
	}

	/**
	 * @param atualizar O atualizar a ser setado.
	 */
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

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
	 * @param abreviatura
	 *            O abreviatura a ser setado.
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
	 * @param descricao
	 *            O descricao a ser setado.
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
	 * @param indicadorgeracaoAutomaica
	 *            O indicadorgeracaoAutomaica a ser setado.
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
	 * @param tipoLancamentoContabil
	 *            O tipoLancamentoContabil a ser setado.
	 */
	public void setTipoLancamentoContabil(String tipoLancamentoContabil) {
		this.tipoLancamentoContabil = tipoLancamentoContabil;
	}

	/**
	 * @return Retorna o campo valorLimiteCreditoFinal.
	 */
	public String getValorLimiteCreditoFinal() {
		return valorLimiteCreditoFinal;
	}

	/**
	 * @param valorLimiteCreditoFinal
	 *            O valorLimiteCreditoFinal a ser setado.
	 */
	public void setValorLimiteCreditoFinal(String valorLimiteCreditoFinal) {
		this.valorLimiteCreditoFinal = valorLimiteCreditoFinal;
	}

	/**
	 * @return Retorna o campo valorLimiteCreditoInicial.
	 */
	public String getValorLimiteCreditoInicial() {
		return valorLimiteCreditoInicial;
	}

	/**
	 * @param valorLimiteCreditoInicial
	 *            O valorLimiteCreditoInicial a ser setado.
	 */
	public void setValorLimiteCreditoInicial(String valorLimiteCreditoInicial) {
		this.valorLimiteCreditoInicial = valorLimiteCreditoInicial;
	}

	/**
	 * @return Retorna o campo codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            O codigo a ser setado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Retorna o campo indicativoUso.
	 */
	public String getIndicativoUso() {
		return indicativoUso;
	}

	/**
	 * @param indicativoUso
	 *            O indicativoUso a ser setado.
	 */
	public void setIndicativoUso(String indicativoUso) {
		this.indicativoUso = indicativoUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
