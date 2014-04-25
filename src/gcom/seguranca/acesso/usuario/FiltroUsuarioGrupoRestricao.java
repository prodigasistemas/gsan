package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Sávio Luiz
 */
public class FiltroUsuarioGrupoRestricao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroUsuarioGrupoRestricao() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroUsuarioGrupoRestricao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String USUARIO_ID = "usuarioGrupo.usuario.id";
	
	public final static String GRUPO_ID = "comp_id.grupoId";
	public final static String FUNCIONALIDADE_ID = "comp_id.funcionalidadeId";
	public final static String OPERACAO_ID = "comp_id.operacaoId";

}
