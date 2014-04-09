package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * Filtro Imovel Tipo Habitacao
 * 
 * @author Administrador
 */
public class FiltroImovelTipoHabitacao extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroDespejo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroImovelTipoHabitacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroDespejo
     */
    public FiltroImovelTipoHabitacao() {
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
