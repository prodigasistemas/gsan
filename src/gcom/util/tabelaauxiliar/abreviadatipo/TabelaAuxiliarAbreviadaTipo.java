package gcom.util.tabelaauxiliar.abreviadatipo;

import gcom.operacional.SistemaAbastecimento;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;
/**
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public abstract class TabelaAuxiliarAbreviadaTipo extends TabelaAuxiliarAbreviada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Description of the Field
     */
    protected SistemaAbastecimento sistemaAbastecimento;

	public SistemaAbastecimento getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	public void setSistemaAbastecimento(SistemaAbastecimento sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}
}
