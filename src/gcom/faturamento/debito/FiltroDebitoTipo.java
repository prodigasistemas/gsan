package gcom.faturamento.debito;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroDebitoTipo extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroDebitoTipo() {
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
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    

    public final static String FINANCIAMENTO_TIPO = "financiamentoTipo.id";  
    
    public final static String INDICADOR_GERACAO_AUTOMATICA = "indicadorGeracaoAutomatica";
    
    /**
     * @since 09/03/2006
     */
    public final static String LANCAMENTO_ITEM_CONTABIL_ID = "lancamentoItemContabil.id";
    
    /**
     * @since 09/03/2006
     */
    public final static String VALOR_LIMITE = "valorLimite";
    
    public final static String VALOR_SUGERIDO = "valorSugerido";
    
    
    /**
     * Construtor da classe FiltroDebitoTipo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroDebitoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String INDICADOR_GERACAO_DEBITO_CONTA = "indicadorGeracaoConta";
     
    /**
     * Description of the Field
     */
    public final static String CODIGO_CONSTANTE = "codigoConstante";
    
    
}

