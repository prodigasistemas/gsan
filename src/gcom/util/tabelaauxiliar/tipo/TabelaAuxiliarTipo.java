package gcom.util.tabelaauxiliar.tipo;

import gcom.util.tabelaauxiliar.TabelaAuxiliar;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public abstract class TabelaAuxiliarTipo extends TabelaAuxiliar {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Description of the Field
     */
    protected String descricaoTipo;

    /**
     * Description of the Field
     */
    protected Integer idTipo;

    /**
     * Retorna o valor de descricaoTipo
     * 
     * @return O valor de descricaoTipo
     */
    public String getDescricaoTipo() {
        return descricaoTipo;
    }

    /**
     * Retorna o valor de idTipo
     * 
     * @return O valor de idTipo
     */
    public Integer getIdTipo() {
        return idTipo;
    }

}
