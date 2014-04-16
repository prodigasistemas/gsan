package gcom.gui.operacional;


import org.apache.struts.validator.ValidatorForm;


/**
 * [UC005] DISTRITO OPERACIONAL
 *
 * @author Eduardo Bianchi
 * @date 02/02/2007
 */

public class AtualizarDistritoOperacionalActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	
	private String codigoDistritoOperacional;	
	private String descricao;
	private String descricaoAbreviada;	
	private String indicadorUso;	    
    private String sistemaAbastecimento;    
    private String setorAbastecimento;
    private String ultimaAlteracao;
    
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public String getCodigoDistritoOperacional() {
		return codigoDistritoOperacional;
	}
	public void setCodigoDistritoOperacional(String codigoDistritoOperacional) {
		this.codigoDistritoOperacional = codigoDistritoOperacional;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getSetorAbastecimento() {
		return setorAbastecimento;
	}
	public void setSetorAbastecimento(String setorAbastecimento) {
		this.setorAbastecimento = setorAbastecimento;
	}
	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}
	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}
    
}
