package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

/**
 * Filtra Avisos Bancários
 * 
 * @author Tiago Moreno
 */
public class FiltroAvisoBancario extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ID = "id";

    public final static String ARRECADADOR_ID = "arrecadador.id";
    
    public final static String DATA_LANCAMENTO = "dataLancamento";
    
    public final static String ANO_MES_REFERENCIA_ARRECADACAO = "anoMesReferenciaArrecadacao";
    
    public final static String INDICADOR_CREDITO_DEBITO = "indicadorCreditoDebito";
    
    public final static String CONTA_BANCARIA = "contaBancaria";
    
    public final static String MOVIMENTO_ARRECADADOR = "arrecadadorMovimento";
    
    public final static String DATA_PREVISTA = "dataPrevista";
    
    public final static String DATA_REALIZADA = "dataRealizada";
    
    public final static String VALOR_PREVISTO = "valorPrevisto";
    
    public final static String VALOR_REALIZADO = "valorRealizado";
    
    public final static String NUMERO_SEQUENCIAL = "numeroSequencial";

    public final static String ARRECADADOR = "arrecadador";
    
    public final static String ARRECADADOR_CLIENTE = "arrecadador.cliente";
    
    public final static String AGENCIA = "contaBancaria.agencia";

    public final static String BANCO = "contaBancaria.agencia.banco";
    
    public final static String CONTA_BANCARIA_ID = "contaBancaria.id";
    
    public final static String ARRECADADOR_MOVIMENTO_ID =  "arrecadadorMovimento.id";
    
    public final static String ARRECADADOR_CODIGO_AGENTE = "arrecadador.codigoAgente";
    
    public final static String ARRECADACAO_FORMA = "arrecadacaoForma";

    public FiltroAvisoBancario() {
    }

    public FiltroAvisoBancario(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
