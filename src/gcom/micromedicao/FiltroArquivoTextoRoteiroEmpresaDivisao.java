package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Hugo Leonardo
 * @created 05/06/2010
 */
public class FiltroArquivoTextoRoteiroEmpresaDivisao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroArquivoTextoRoteiroEmpresaDivisao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroArquivoTextoRoteiroEmpresaDivisao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroArquivoTextoRoteiroEmpresaDivisao
     */
    public FiltroArquivoTextoRoteiroEmpresaDivisao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ARQUIVO_TEXTO_ROTEIRO_EMPRESA = "arquivoTextoRoteiroEmpresa";
    
    /**
     * Description of the Field
     */
    public final static String ARQUIVO_TEXTO_ROTEIRO_EMPRESA_ID = "arquivoTextoRoteiroEmpresa.id";

    /**
     * Description of the Field
     */
    public final static String QT_IMOVEL = "quantidadeImovel";
    
    /**
     * Description of the Field
     */
    public final static String NOME_ARQUIVO = "nomeArquivo";
    
    /**
     * Description of the Field
     */
    public final static String NUMERO_SEQUENCIA_LEITURA = "numeroSequenciaArquivo";
    
    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANS_LEITURA = "situacaoTransmissaoLeitura";

    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANS_LEITURA_ID = "situacaoTransmissaoLeitura.id";
    

}
