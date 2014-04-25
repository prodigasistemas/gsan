package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Sávio Luiz
 * 
 * Data:22/04/2008
 */
public class FiltroHidrometroRelojoaria extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroHidrometroMarca
     */
    public FiltroHidrometroRelojoaria() {
    }

    /**
     * Construtor da classe FiltroHidrometroMarca
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroHidrometroRelojoaria(String campoOrderBy) {
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

    public final static String DESCRICAO = "descricao";

}
