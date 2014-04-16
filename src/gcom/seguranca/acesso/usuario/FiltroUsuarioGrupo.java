package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * @author Thiago Toscano
 */
public class FiltroUsuarioGrupo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 */
	public FiltroUsuarioGrupo() {
	}

	/**
	 * Constructor
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroUsuarioGrupo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Código
	 */
	public final static String ID = "id";

	public final static String USUARIO = "usuario";

	public final static String USUARIO_ID = "usuario.id";

	public final static String GRUPO = "grupo";

	public final static String GRUPO_ID = "grupo.id";

	public final static String USUARIO_TIPO_ID = "usuario.usuarioTipo.id";

	public final static String USUARIO_EMPRESA_ID = "usuario.empresa.id";

	public final static String USUARIO_FUNCIONARIO_ID = "usuario.funcionario.id";

	public final static String USUARIO_NOME = "usuario.nomeUsuario";

	public final static String USUARIO_LOTACAO_ID = "usuario.unidadeOrganizacional.id";

	public final static String USUARIO_SITUACAO_ID = "usuario.usuarioSituacao.id";

	public final static String USUARIO_ABRANGENCIA_ID = "usuario.usuarioAbrangencia.id";

	public final static String USUARIO_GERENCIA_REGIONAL_ID = "usuario.gerenciaRegional.id";
	
	public final static String USUARIO_UNIDADE_NEGOCIO_ID = "usuario.unidadeNegocio.id";

	public final static String USUARIO_ELO_ID = "usuario.localidadeElo.id";

	public final static String USUARIO_LOCALIDADE_ID = "usuario.localidade.id";

	public final static String USUARIO_DATA_CADASTRO_INICIO = "usuario.dataCadastroInicio";

	public final static String USUARIO_DATA_CADASTRO_FIM = "usuario.dataCadastroFim";

	public final static String USUARIO_DATA_EXPIRACAO = "usuario.dataExpiracaoAcesso";

	public final static String USUARIO_DATA_CADASTRAMENTO = "usuario.dataCadastroAcesso";
	
	public final static String USUARIO_LOGIN = "usuario.login";
	
	public final static String CPF = "usuario.cpf";
	
	public final static String DATA_NASCIMENTO = "usuario.dataNascimento";
	
	public final static String CPF_FUNCIONARIO = "usuario.funcionario.numeroCpf";
	
	public final static String DATA_NASCIMENTO_FUNCIONARIO = "usuario.funcionario.dataNascimento";

}
