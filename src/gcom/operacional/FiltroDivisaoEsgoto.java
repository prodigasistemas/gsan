package gcom.operacional;

import gcom.util.filtro.Filtro;

/**
 * FiltroDivisaoEsgoto 
 *
 * @author Raphael Rossiter
 * @date 03/08/2006
 */
public class FiltroDivisaoEsgoto extends Filtro {
	private static final long serialVersionUID = 1L;
	public FiltroDivisaoEsgoto() {
    }

    public FiltroDivisaoEsgoto(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";

    public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
}
