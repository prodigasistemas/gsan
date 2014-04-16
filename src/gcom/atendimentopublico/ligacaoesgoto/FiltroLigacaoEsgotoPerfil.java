package gcom.atendimentopublico.ligacaoesgoto;


import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroLigacaoEsgotoPerfil extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLigacaoEsgotoPerfil(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroLigacaoEsgotoPerfil() {
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
	
	public final static String PERCENTUAL = "percentualEsgotoConsumidaColetada";

	public final static String INDICADOR_PRINCIPAL = "indicadorPrincipal";

}
