package gcom.gui.cadastro;



import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho
 * @date  	14/08/2008
 */

public class AtualizarFonteAbastecimentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigo;
	
	private String descricao;	
	
	private String descricaoAbreviada;
	
	private Short indicadorCalcularVolumeFixo;
	
	private Short indicadorUso;	 
	
	private Short indicadorPermitePoco;
	
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Short getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public Short getIndicadorCalcularVolumeFixo() {
		return indicadorCalcularVolumeFixo;
	}
	public void setIndicadorCalcularVolumeFixo(Short indicadorCalcularVolumeFixo) {
		this.indicadorCalcularVolumeFixo = indicadorCalcularVolumeFixo;
	}
	public Short getIndicadorPermitePoco() {
		return indicadorPermitePoco;
	}
	public void setIndicadorPermitePoco(Short indicadorPermitePoco) {
		this.indicadorPermitePoco = indicadorPermitePoco;
	}	
		
}
