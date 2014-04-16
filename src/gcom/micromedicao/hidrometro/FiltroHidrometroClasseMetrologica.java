package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroHidrometroClasseMetrologica extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroHidrometroClasseMetrologica
     */
    public FiltroHidrometroClasseMetrologica() {
    }

    /**
     * Construtor da classe FiltroHidrometroClasseMetrologica
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroHidrometroClasseMetrologica(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
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
    public final static String DESCRICAO = "descricao";

}
