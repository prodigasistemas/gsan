package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * Filtro Despejo Flávio Cordeiro
 * 
 * @author Administrador
 */
public class FiltroDespejo extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroDespejo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroDespejo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroDespejo
     */
    public FiltroDespejo() {
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
