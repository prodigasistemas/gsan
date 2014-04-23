package gcom.gui.seguranca;



import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author Arthur Carvalho
 * @date  	26/08/2008
 */

public class AtualizarUsuarioTipoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;	
	private Short indicadorFuncionario;
	private Short indicadorUso;	 
	
    public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public Short getIndicadorFuncionario() {
		return indicadorFuncionario;
	}
	public void setIndicadorFuncionario(Short indicadorFuncionario) {
		this.indicadorFuncionario = indicadorFuncionario;
	}
		
}
