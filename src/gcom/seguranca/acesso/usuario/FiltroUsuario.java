package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca do usuario
 * 
 * @author Thiago Toscano
 */
public class FiltroUsuario extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroUsuario() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUsuario (String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código 
     */
    public final static String ID = "id";
    public final static String SENHA = "senha";
    public final static String USUARIO_TIPO_ID = "usuarioTipo.id";
    public final static String USUARIO_TIPO = "usuarioTipo";
    public final static String NOME_USUARIO = "nomeUsuario";
    public final static String FUNCIONARIO_ID = "funcionario.id";
    public final static String FUNCIONARIO = "funcionario";
    public final static String EMAIL = "descricaoEmail";
    public final static String LOGIN = "login";
    public final static String USUARIO_SITUACAO = "usuarioSituacao";
    public final static String CPF = "cpf";
    public final static String DATA_NASCIMENTO = "dataNascimento";
    public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
    
    public final static String UNIDADE_ORGANIZACIONAL_SUPERIOR = "unidadeOrganizacional.unidadeSuperior";
    public final static String USUARIO_ABRANGENCIA = "usuarioAbrangencia";
    public final static String USUARIO_ID = "usuario.id";
    public final static String GERENCIA_REGIONAL = "gerenciaRegional";
    public final static String LOCALIDADE = "localidade";
    public final static String LOCALIDADE_ELO = "localidadeElo";
    public final static String EMPRESA = "empresa";
    public final static String UNIDADE_NEGOCIO = "unidadeNegocio";
    public final static String INDICADOR_USUARIO_INTERNET = "indicadorUsuarioInternet";
}
