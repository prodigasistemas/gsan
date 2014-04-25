package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * 
 * @author Tiago Moreno - 10/02/2010
 */
public class FiltroMotivoInterferenciaTipo extends Filtro {

	private static final long serialVersionUID = 1L;
    public FiltroMotivoInterferenciaTipo() {
    }

    public final static String ID = "id";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
     
    
    
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroMotivoInterferenciaTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
