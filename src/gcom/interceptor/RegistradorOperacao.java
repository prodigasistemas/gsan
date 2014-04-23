package gcom.interceptor;

import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

/**
 * 
 *  
 *
 * @author Thiago Toscano
 * @date 29/04/2006
 */
public class RegistradorOperacao {

	/** operacao realizada pelo usuario */
	private OperacaoEfetuada operacaoEfetuada = null;

	private UsuarioAcaoUsuarioHelper[] usuarioAcaoUsuarioHelper;

	/** 
	 * que recebe o id da op
	 * Construtor de RegistradorOperacao 
	 * 
	 * @param idOperacao
	 * @param usuarioAcaoUsuarioHelper
	 */
	public RegistradorOperacao(Integer idOperacao, UsuarioAcaoUsuarioHelper... usuarioAcaoUsuarioHelper) {
		Operacao operacao = new Operacao();
		operacao.setId(idOperacao);

		operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		this.usuarioAcaoUsuarioHelper = usuarioAcaoUsuarioHelper; 
	}
	
	public RegistradorOperacao(Integer idOperacao, Integer valorArgumento,
			Integer idObjetoPrincipal,
			UsuarioAcaoUsuarioHelper... usuarioAcaoUsuarioHelper) {
		Operacao operacao = new Operacao();
		operacao.setId(idOperacao);

		operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		operacaoEfetuada.setArgumentoValor(valorArgumento);
		operacaoEfetuada.setIdObjetoPrincipal(idObjetoPrincipal);

		this.usuarioAcaoUsuarioHelper = usuarioAcaoUsuarioHelper; 
	}

	/**
	 * Método que registra os parametros a cada objeto a ser registrado 
	 *
	 * @author Administrador
	 * @date 29/04/2006
	 *
	 * @param objetoTransacao
	 */
	public void registrarOperacao(ObjetoTransacao objetoTransacao) {
		// seta a operacao efetuada
		objetoTransacao.setOperacaoEfetuada(this.operacaoEfetuada);
			
		// pra cada usuario com sua acao seta no objeto que deseja registra a operacao
		if (usuarioAcaoUsuarioHelper != null) {
			for (int i = 0; i < usuarioAcaoUsuarioHelper.length; i++) {
				UsuarioAcaoUsuarioHelper uAUH = usuarioAcaoUsuarioHelper[i];
				if (uAUH.getUsuario() != null){
					objetoTransacao.adicionarUsuario(uAUH.getUsuario(),uAUH.getUsuarioAcao());
				}
			}
		}
	}
}
