package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de uma Pessoa Sexo
 * 
 * @author Rodrigo
 */
public class FiltroPessoaSexo extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroPessoaSexo
     */
    public FiltroPessoaSexo() {
    }

    /**
     * Construtor da classe FiltroPessoaSexo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroPessoaSexo(String campoOrderBy) {
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
