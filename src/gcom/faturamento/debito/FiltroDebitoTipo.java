package gcom.faturamento.debito;

import gcom.util.filtro.Filtro;

public class FiltroDebitoTipo extends Filtro {
	private static final long serialVersionUID = 1L;

    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    public final static String INDICADOR_USO = "indicadorUso";
    public final static String FINANCIAMENTO_TIPO = "financiamentoTipo.id";  
    public final static String INDICADOR_GERACAO_AUTOMATICA = "indicadorGeracaoAutomatica";
    public final static String LANCAMENTO_ITEM_CONTABIL_ID = "lancamentoItemContabil.id";
    public final static String VALOR_LIMITE = "valorLimite";
    public final static String VALOR_SUGERIDO = "valorSugerido";
    public final static String INDICADOR_GERACAO_DEBITO_CONTA = "indicadorGeracaoConta";
    public final static String CODIGO_CONSTANTE = "codigoConstante";
    
    public FiltroDebitoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroDebitoTipo() {
    }
    
}

