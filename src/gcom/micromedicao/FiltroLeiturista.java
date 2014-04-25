package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Francisco do Nascimento
 * @created 06 de agosto de 2007
 */
public class FiltroLeiturista extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroGerenciaRegional
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroLeiturista(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLeiturista
     */
    public FiltroLeiturista() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Description of the Field
     */
    public final static String CLIENTE = "cliente";
    
    /**
     * Description of the Field
     */
    public final static String FUNCIONARIO = "funcionario";
    
    /**
     * Description of the Field
     */
    public final static String FUNCIONARIO_NOME = "funcionario.nome";
    
    /**
     * Description of the Field
     */
    public final static String TELEFONE = "numeroFone";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";

    /**
     * Description of the Field
     */
    public final static String DDD = "codigoDDD";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA_ID = "empresa.id";
    
    /**
     * Description of the Field
     */
    public final static String IMEI = "numeroImei";
    
    public final static String USUARIO_ID = "usuario.id";
    public final static String USUARIO = "usuario";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE_NOME = "cliente.nome";
}
