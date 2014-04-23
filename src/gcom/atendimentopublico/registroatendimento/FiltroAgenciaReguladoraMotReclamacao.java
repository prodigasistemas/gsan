package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

/**
 * Filtro Agencia Reguladora Motivação Reclamacâo 
 *
 * @author Kassia Regina
 * @date 03/04/2007
 */
public class FiltroAgenciaReguladoraMotReclamacao extends Filtro {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroAgenciaReguladoraMotReclamacao() {
    }

    public FiltroAgenciaReguladoraMotReclamacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";

}
