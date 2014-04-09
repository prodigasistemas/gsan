package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Thiago Tenório
 * @created 06 de agosto de 2007
 */
public class FiltroArquivoTextoRoteiroEmpresa extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroGerenciaRegional
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroArquivoTextoRoteiroEmpresa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLeiturista
     */
    public FiltroArquivoTextoRoteiroEmpresa() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String NUMERO_ARQUIVO = "numeroArquivo";

    /**
     * Description of the Field
     */
    public final static String QT_IMOVEL = "quantidadeImovel";
    
    /**
     * Description of the Field
     */
    public final static String LOCALIDADE = "localidade";
    
    /**
     * Description of the Field
     */
    public final static String FONE_LEITURISTA = "numeroFoneLeiturista";
    
    /**
     * Description of the Field
     */
    public final static String TEMPO_ENVIO_EMPRESA = "tempoEnvioEmpresa";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";
    
    /**
     * Description of the Field
     */
    public final static String GRUPO_FATURAMENTO = "faturamentoGrupo";
    
    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANS_LEITURA = "situacaoTransmissaoLeitura";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA = "leiturista";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA_CLIENTE = "leiturista.cliente";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA_FUNCIONARIO = "leiturista.funcionario";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    /**
     * Description of the Field
     */
    public final static String NUMERO_SEQUENCIA_LEITURA = "numeroSequenciaLeitura";
    
    /**
     * Description of the Field
     */
    public final static String LEITURISTA_ID = "leiturista.id";
    
    /**
     * Description of the Field
     */
    public final static String SITUACAO_TRANS_LEITURA_ID = "situacaoTransmissaoLeitura.id";
    
    /**
     * Description of the Field
     */
    public final static String ROTA = "rota";
    
    /**
     * Description of the Field
     */
    public final static String IMEI = "numeroImei";
    
    /**
     * Description of the Field
     */
    public final static String ROTA_ID = "rota.id";
   

    /**
     * Description of the Field
     */
    public final static String LOCALIDADE_ID = "localidade.id";
    
    public final static String SERVICO_TIPO_CELULAR = "servicoTipoCelular.id";
    
}
