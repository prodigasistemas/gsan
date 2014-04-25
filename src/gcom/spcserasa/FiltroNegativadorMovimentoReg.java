package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroNegativadorMovimentoReg
 *
 * @author Yara Taciane 
 * @date 10/01/2008
 */
public class FiltroNegativadorMovimentoReg extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativadorMovimentoReg() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativadorMovimentoReg(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String NEGATIVADOR_MOVIMENTO_ID = "negativadorMovimento.id";

    public final static String NEGATIVADOR_MOVIMENTO_NEGATIVACAO_COMANDO = "negativadorMovimento.negativacaoComando";

    public final static String NEGATIVADOR_MOVIMENTO_CODIGOMOVIMENTO = "negativadorMovimento.codigoMovimento";
    
    public final static String NEGATIVADOR_ID = "negativadorMovimento.negativador.id";
    
    public final static String NEGATIVADOR = "negativadorMovimento.negativador";

    public final static String CLIENTE = "negativadorMovimento.negativador.cliente";
    
    public final static String CLIENTE_ID = "cliente.id";

    public final static String NUMERO_REGISTRO = "numeroRegistro";

    public final static String CODIGO_EXCLUSAO_TIPO = "codigoExclusaoTipo";

    public final static String IMOVEL = "imovel";

    public final static String IMOVEL_ID = "imovel.id";

    public final static String NEGATIVADOR_MOVIMENTO_NEGATIVACAO_COMANDO_ID = "negativadorMovimento.negativacaoComando.id";
    
    public final static String INDICADOR_ACEITO = "indicadorAceito";
    
    public final static String NEGATIVADOR_EXLUSAO_MOTIVO = "negativadorExclusaoMotivo";
    
    public final static String USUARIO = "usuario";
    
    public final static String COBRANCA_DEBITO_SITUACAO = "cobrancaDebitoSituacao";
}
