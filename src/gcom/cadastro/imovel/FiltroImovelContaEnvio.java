package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroImovelContaEnvio extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_CLIENTE_RESPONSAVEL = "indicadorClienteResponsavel";
    
    public FiltroImovelContaEnvio(){
    	
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroImovelContaEnvio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
