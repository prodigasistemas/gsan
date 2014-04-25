package gcom.cadastro.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Ana Maria
 * @created 10/06/2009
 */
public class FiltroArquivoTextoAtualizacaoCadastral extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroGerenciaRegional
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroArquivoTextoAtualizacaoCadastral(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLeiturista
     */
    public FiltroArquivoTextoAtualizacaoCadastral() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoArquivo";

    /**
     * Description of the Field
     */
    public final static String LEITURISTA = "leiturista";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA_ID = "leiturista.id";
    
    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANSMISSAO_LEITURA = "situacaoTransmissaoLeitura";
    
    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANSMISSAO_LEITURA_ID = "situacaoTransmissaoLeitura.id";
    
    
    

}
