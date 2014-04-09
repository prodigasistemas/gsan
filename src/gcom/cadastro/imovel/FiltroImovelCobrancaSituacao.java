package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * Filtro Despejo Flávio Cordeiro
 * 
 * @author Administrador
 */
public class FiltroImovelCobrancaSituacao extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroDespejo
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroImovelCobrancaSituacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroDespejo
     */
    public FiltroImovelCobrancaSituacao() {
    }

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DATA_RETIRADA_COBRANCA = "dataRetiradaCobranca";
    /**
     * Description of the Field
     */
    public final static String DATA_IMPLANTACAO_COBRANCA = "dataImplantacaoCobranca";

    public final static String ID_COBRANCA_SITUACAO = "cobrancaSituacao.id";
    
}

