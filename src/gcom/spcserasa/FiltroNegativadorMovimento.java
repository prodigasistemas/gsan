package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroNegativadorMovimento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    public FiltroNegativadorMovimento() {
    }

    public FiltroNegativadorMovimento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String NEGATIVADOR_ID = "negativador.id";
    
    public final static String NEGATIVADOR = "negativador";

    public final static String NEGATIVADOR_CLIENTE = "negativador.cliente";
    
    public final static String NEGATIVADOR_CLIENTE_ID = "negativador.cliente.id";

    public final static String NEGATIVADOR_CLIENTE_NOME = "negativador.cliente.nome";
    
    public final static String CODIGO_MOVIMENTO = "codigoMovimento";
    
    public final static String NUMERO_SEQUENCIAL_ENVIO = "numeroSequencialEnvio";
    
    public final static String DATA_PROCESSAMENTO_ENVIO = "dataProcessamentoEnvio";
    
    public final static String DATA_PROCESSAMENTO_RETORNO = "dataProcessamentoRetorno";
    
    public final static String NEGATIVACAO_COMANDO = "negativacaoComando";
    
    public final static String NEGATIVACAO_COMANDO_ID = "negativacaoComando.id";
    
}
