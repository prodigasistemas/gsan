package gcom.gui.seguranca.acesso.usuario;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class InserirAbrangenciaUsuarioActionForm extends ValidatorActionForm {
    private String descricaoUsuarioAbrangencia;
    private static final long serialVersionUID = 1L;
    private String descricaoAbreviada;
    
    private String abrangenciaSuperior;

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public String getAbrangenciaSuperior() {
		return abrangenciaSuperior;
	}

	public void setAbrangenciaSuperior(String abrangenciaSuperior) {
		this.abrangenciaSuperior = abrangenciaSuperior;
	}

	public String getDescricaoUsuarioSituacao() {
		return descricaoUsuarioAbrangencia;
	}

	public void setDescricaoUsuarioSituacao(String descricaoUsuarioAbrangencia) {
		this.descricaoUsuarioAbrangencia = descricaoUsuarioAbrangencia;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}
}
  
