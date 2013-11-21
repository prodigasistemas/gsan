package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContaRevisaoFaixaValor extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroContaMotivoRevisao
     */
    public FiltroContaRevisaoFaixaValor() {
    }
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String VALOR_INICIO_FAIXA = "valorFaixaInicio";
    
    /**
     * Description of the Field
     */
    public final static String VALOR_FIM_FAIXA = "valorFaixaFim";
    
    /**
     * Description of the Field
     */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";
  
    
    /**
     * Construtor da classe FiltroContaRevisaoFaixaValor
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaRevisaoFaixaValor(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

