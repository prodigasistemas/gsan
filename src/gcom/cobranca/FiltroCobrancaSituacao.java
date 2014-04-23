package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Rhawi Dantas
 * @created 23 de Dezembro de 2005
 */

public class FiltroCobrancaSituacao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaSituacao object
     */
    public FiltroCobrancaSituacao() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaSituacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_EXIGENCIA_ADVOGADO = "indicadorExigenciaAdvogado";
    
    public final static String INDICADOR_BLOQUEIO_PARCELAMENTO = "indicadorBloqueioParcelamento";
    
    public final static String INDICADOR_BLOQUEIO_RETIRADA = "indicadorBloqueioRetirada";
    
    public final static String PROFISSAO = "profissao";
    
    public final static String RAMO_ATIVIDADE = "ramoAtividade";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String CONTA_MOTIVO_REVISAO = "contaMotivoRevisao";
    
    public final static String INDICADOR_BLOQUEIO_INCLUSAO = "indicadorBloqueioInclusao";
    
    public final static String INDICADOR_SELECAO_APENAS_COM_PERMISSAO = "indicadorSelecaoApenasComPermissao";
	
    public final static String INDICADOR_PRESCRICAO_IMOVEIS_PARTICULARES = "indicadorPrescricaoImoveisParticulares";
}
