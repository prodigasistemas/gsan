package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroReservatorioVolumeFaixa extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroReservatorioVolumeFaixa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroReservatorioVolumeFaixa
     */
    public FiltroReservatorioVolumeFaixa() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String VOLUME_MENOR_FAIXA = "volumeMenorFaixa";

    /**
     * Description of the Field
     */
    public final static String VOLUME_MAIOR_FAIXA = "volumeMaiorFaixa";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

}
