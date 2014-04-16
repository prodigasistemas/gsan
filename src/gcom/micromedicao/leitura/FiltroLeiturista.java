package gcom.micromedicao.leitura;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Thiago Tenório
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
    public final static String TELEFONE = "numeroFone";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";

    /**
     * Description of the Field
     */
    public final static String DDD = "codigoDDD";
    


}
