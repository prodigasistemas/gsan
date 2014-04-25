package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de uma profissão
 * 
 * @author Rodrigo
 */
public class FiltroProfissao extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroProfissao
     */
    public FiltroProfissao() {
    }

    /**
     * Construtor da classe FiltroProfissao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroProfissao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
