package gcom.gui.seguranca.acesso.usuario;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ManterAbrangenciaUsuarioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigo;
	
    private String descricaoAbreviada;
	
	private String descricaoUsuarioSituacao;
   
   
    private String abrangenciaSuperior;
    
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricaoUsuarioSituacao() {
		return descricaoUsuarioSituacao;
	}

	public void setDescricaoUsuarioSituacao(String descricaoUsuarioSituacao) {
		this.descricaoUsuarioSituacao = descricaoUsuarioSituacao;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getAbrangenciaSuperior() {
		return abrangenciaSuperior;
	}

	public void setAbrangenciaSuperior(String abrangenciaSuperior) {
		this.abrangenciaSuperior = abrangenciaSuperior;
	}
}

	    
