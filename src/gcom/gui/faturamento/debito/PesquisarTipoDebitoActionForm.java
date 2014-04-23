package gcom.gui.faturamento.debito;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form utilizado na pesquisa de tipos de débito
 *
 * @author Pedro Alexandre 
 * @date 09/03/2006
 */
public class PesquisarTipoDebitoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	/**
	 * @since 09/03/2006
	 */
	private String idTipoDebito;

	/**
	 * @since 09/03/2006
	 */
	private String descricao;

    /**
     * @since 09/03/2006
     */
    private String[] idTipoFinanciamento;
    
    /**
     * @since 09/03/2006
     */
    private String[] idItemLancamentoContabil;
	
    /**
     * @since 09/03/2006
     */
    private String intervaloValorLimiteInicial;
	
    /**
     * @since 09/03/2006
     */
    private String intervaloValorLimiteFinal;
    
    private String tipoPesquisa;
    
	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String[] getIdItemLancamentoContabil() {
		return idItemLancamentoContabil;
	}

	public void setIdItemLancamentoContabil(String[] idItemLancamentoContabil) {
		this.idItemLancamentoContabil = idItemLancamentoContabil;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String[] getIdTipoFinanciamento() {
		return idTipoFinanciamento;
	}

	public void setIdTipoFinanciamento(String[] idTipoFinanciamento) {
		this.idTipoFinanciamento = idTipoFinanciamento;
	}

	public String getIntervaloValorLimiteFinal() {
		return intervaloValorLimiteFinal;
	}

	public void setIntervaloValorLimiteFinal(String intervaloValorLimiteFinal) {
		this.intervaloValorLimiteFinal = intervaloValorLimiteFinal;
	}

	public String getIntervaloValorLimiteInicial() {
		return intervaloValorLimiteInicial;
	}

	public void setIntervaloValorLimiteInicial(String intervaloValorLimiteInicial) {
		this.intervaloValorLimiteInicial = intervaloValorLimiteInicial;
	}

	
	

}
