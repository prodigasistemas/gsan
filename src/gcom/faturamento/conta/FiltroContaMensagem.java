package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContaMensagem extends Filtro {

	private static final long serialVersionUID = 1L;
    public FiltroContaMensagem() {
    }

    public final static String ID = "id";
    
    public final static String ANO_MES_REFERECIA_FATURAMENTO = "anoMesRreferenciaFaturamento";
    
    public final static String GRUPO_FATURAMENTO_ID = "faturamentoGrupo.id";
    
    public final static String MENSAGEM_CONTA_01 = "descricaoContaMensagem01";
    
    public final static String MENSAGEM_CONTA_02 = "descricaoContaMensagem02";
    
    public final static String MENSAGEM_CONTA_03 = "descricaoContaMensagem03";
    
    public final static String GERENCIA_REGIONAL_ID = "gerenciaRegional.id";
    
    public final static String LOCALIDADE_ID = "localidade.id";
    
    public final static String SETOR_COMERCIAL_ID = "setorComercial.id";
    
    public final static String SETOR_COMERCIAL_CD = "setorComercial.codigo";
    
    public final static String QUADRA_ID = "quadra.id";
    
    public final static String NUMERO_QUADRA = "quadra.numeroQuadra";
    
    
    
    
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaMensagem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
