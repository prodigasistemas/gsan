package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * Filtro Consumo Faixa Ligacao
 * 
 * @author  Rafael Francisco Pinto
 * 
 * @created 01/06/2007
 */
public class FiltroConsumoFaixaLigacao extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_HIDROMETRO = "indicadorHidrometro";

    /**
     * Description of the Field
     */
    public final static String NUMERO_FAIXA_INICIO = "numeroFaixaInicio";

    /**
     * Description of the Field
     */
    public final static String NUMERO_FAIXA_FIM = "numeroFaixaFim";
    
    /**
     * Construtor da classe FiltroImpostosDeduzidos
     */
    public FiltroConsumoFaixaLigacao() {
    }

    /**
     * Construtor da classe FiltroImpostosDeduzidos
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroConsumoFaixaLigacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
