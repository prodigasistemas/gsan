package gcom.gerencial.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroResumoCobrancaSituacaoEspecial extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroResumoCobrancaSituacaoEspecial(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroResumoCobrancaSituacaoEspecial() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_SITUCACAO_TIPO_ID = "cobrancaSituacaoTipo.id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_SITUCACAO_MOTIVO_ID = "cobrancaSituacaoMotivo.id";
    
    public final static String GERENCIA_REGIONAL_ID = "gerenciaRegional.id";
 
}
