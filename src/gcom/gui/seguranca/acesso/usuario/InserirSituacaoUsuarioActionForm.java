package gcom.gui.seguranca.acesso.usuario;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class InserirSituacaoUsuarioActionForm extends ValidatorActionForm {
    private String descricaoUsuarioSituacao;
    private static final long serialVersionUID = 1L;
    private String descricaoAbreviada;
    
    private String indicadorUsoSistema;

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getDescricaoUsuarioSituacao() {
		return descricaoUsuarioSituacao;
	}

	public void setDescricaoUsuarioSituacao(String descricaoUsuarioSituacao) {
		this.descricaoUsuarioSituacao = descricaoUsuarioSituacao;
	}

	public String getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	public void setIndicadorUsoSistema(String indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}
}


  
    
