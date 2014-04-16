package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroImovelEloAnormalidade extends Filtro {
	
	private static final long serialVersionUID = 1L;


    public FiltroImovelEloAnormalidade() {
    }

    public final static String ID = "id";
    
    public final static String DATA_ANORMALIDADE = "dataAnormalidade";
    
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String ELO_ANORMALIDADE = "eloAnormalidade"; 
    
    
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroImovelEloAnormalidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
