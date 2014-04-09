package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroFonteAbastecimento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroFonteAbastecimento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroFonteAbastecimento
     */
    public FiltroFonteAbastecimento() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao"; 
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada"; 

    /**
     * Description of the Field
     */
    public final static String INDICADOR_CALCULAR_VOLUME_FIXO = "indicadorCalcularVolumeFixo"; 
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_PERMITE_POCO = "indicadorPermitePoco"; 

}
