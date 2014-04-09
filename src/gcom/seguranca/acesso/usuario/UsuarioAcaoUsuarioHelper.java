package gcom.seguranca.acesso.usuario;
 
import java.io.Serializable;



/**
 * Classe que representa a unicao de um usuario com usuario acao
 *
 * @author thiago toscano
 * @date 30/03/2006
 */
public class UsuarioAcaoUsuarioHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private Usuario usuario;
	private UsuarioAcao usuarioAcao;

	/**
	 * Construtor de UsuarioAcaoUsuarioHelp que recebe um usuario e um usuario acao 
	 * 
	 * @param usuario
	 * @param usuarioAcao
	 */
	public UsuarioAcaoUsuarioHelper(Usuario usuario, UsuarioAcao usuarioAcao) {
		this.usuario = usuario;
		this.usuarioAcao = usuarioAcao;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @return Retorna o campo usuarioAcao.
	 */
	public UsuarioAcao getUsuarioAcao() {
		return usuarioAcao;
	}
	
	public boolean equals(Object arg) {
		if (arg == null){
			return false;
		}
		if (!(arg instanceof UsuarioAcaoUsuarioHelper)){
			return false;
		} 		
		UsuarioAcaoUsuarioHelper uauh = (UsuarioAcaoUsuarioHelper) arg;
		return this.usuario.equals(uauh.usuario) &&
			this.usuarioAcao.equals(uauh.usuarioAcao);
	}
}
