package gcom.gui.micromedicao;



import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho
 * @date  	06/08/2008
 */

public class AtualizarLocalArmazenagemHidrometroActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;	
	private String descricaoAbreviada;
	private Short indicadorUso;	 
    private Short indicadorOficina;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Short getIndicadorOficina() {
		return indicadorOficina;
	}
	public void setIndicadorOficina(Short indicadorOficina) {
		this.indicadorOficina = indicadorOficina;
	}
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}	
		
}
