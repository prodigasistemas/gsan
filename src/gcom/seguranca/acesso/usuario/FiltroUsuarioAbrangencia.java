package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Thiago Toscnao
 * @created 02/05/2006
 */
public class FiltroUsuarioAbrangencia extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUsuarioAbrangencia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroUsuarioAbrangencia() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ID_SUPERIOR = "usuarioAbrangenciaSuperior.id";
    
    public final static String INDICADOR_USO = "indicadorUso";
}
