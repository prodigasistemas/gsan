package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um ramo de atividade
 * 
 * @author Rodrigo
 */
public class FiltroRamoAtividade extends Filtro {
	
	private static final long serialVersionUID = 1L;
    public FiltroRamoAtividade() {
    }

    /**
     * Construtor da classe FiltroProfissao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroRamoAtividade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     *  Description of the Field
     */
    public final static String CODIGO = "codigo";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
