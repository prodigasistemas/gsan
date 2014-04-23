package gcom.gui.faturamento.credito;

import org.apache.struts.action.ActionForm;

public class AtualizarTipoCreditoActionForm extends ActionForm {


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
	
	private String valorLimiteCredito;

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorgeracaoAutomaica() {
		return indicadorgeracaoAutomaica;
	}

	public void setIndicadorgeracaoAutomaica(String indicadorgeracaoAutomaica) {
		this.indicadorgeracaoAutomaica = indicadorgeracaoAutomaica;
	}

	public String getIndicativoUso() {
		return indicativoUso;
	}

	public void setIndicativoUso(String indicativoUso) {
		this.indicativoUso = indicativoUso;
	}

	public String getTipoLancamentoContabil() {
		return tipoLancamentoContabil;
	}

	public void setTipoLancamentoContabil(String tipoLancamentoContabil) {
		this.tipoLancamentoContabil = tipoLancamentoContabil;
	}

	public String getValorLimiteCredito() {
		return valorLimiteCredito;
	}

	public void setValorLimiteCredito(String valorLimiteCredito) {
		this.valorLimiteCredito = valorLimiteCredito;
	}

}
