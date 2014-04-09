package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Referente a Tabela Pavimento Rua
 * 
 * @author Rafael Santos
 * @since 23/02/2006
 */
public class FiltroPavimentoRua extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroPavimentoRua object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroPavimentoRua(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroPavimentoRua
     */
    public FiltroPavimentoRua() {
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
