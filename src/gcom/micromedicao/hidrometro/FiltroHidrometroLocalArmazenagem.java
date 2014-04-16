package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroHidrometroLocalArmazenagem extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroHidrometroLocalArmazenagem
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroHidrometroLocalArmazenagem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroHidrometroLocalArmazenagem
     */
    public FiltroHidrometroLocalArmazenagem() {
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
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_OFICINA = "indicadorOficina";

}
