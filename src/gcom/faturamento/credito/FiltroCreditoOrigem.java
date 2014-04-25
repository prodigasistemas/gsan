package gcom.faturamento.credito;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Roberta Costa
 * @since  12/01/2006
 */
public class FiltroCreditoOrigem extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroCreditoOrigem() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoCreditoOrigem";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    

    
    /**
     * Construtor da classe FiltroDebitoTipo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroCreditoOrigem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
