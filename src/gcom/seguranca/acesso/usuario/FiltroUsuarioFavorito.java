package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro usuado na pesquisa das últimas funcionalidades acessadas pelo usuário
 *
 * @author Pedro Alexandre
 * @date 10/08/2006
 */
public class FiltroUsuarioFavorito extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroUsuarioFavorito() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUsuarioFavorito(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String USUARIO_ID = "usuario.id";
    public final static String FUNCIONALIDADE_ID = "funcionalidade.id";
    public final static String INDICADOR_FAVORITO_ULTIMO_ACESSADO = "indicadorFavoritosUltimosAcessados";
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
}
