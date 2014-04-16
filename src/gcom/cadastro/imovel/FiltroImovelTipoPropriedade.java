package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * Filtro Imovel Tipo Propriedade
 * 
 * @author Administrador
 */
public class FiltroImovelTipoPropriedade extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroDespejo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroImovelTipoPropriedade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroDespejo
     */
    public FiltroImovelTipoPropriedade() {
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
