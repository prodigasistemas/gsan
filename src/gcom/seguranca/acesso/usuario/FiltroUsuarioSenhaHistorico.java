package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


public class FiltroUsuarioSenhaHistorico extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroUsuarioSenhaHistorico() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUsuarioSenhaHistorico (String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
    public final static String ID = "id";
    public final static String SENHA = "senha";
    public final static String USUARIO = "usuario";
    public final static String USUARIO_ID = "usuario.id";
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";


}
