package gcom.micromedicao.medicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroMedicaoHistoricoSql extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMedicaoHistoricoSql(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroPavimentoCalcada
     */
    public FiltroMedicaoHistoricoSql() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String IMOVEL_ID = "imovel.imov_id";
    
    public final static String IMOVEL_INDICADOR_EXCLUSAO = "imovel.imov_icexclusao";

    public final static String MEDICAO_TIPO_ID = "medicaoHistorico.medt_id";

    public final static String ANO_MES_REFERENCIA_FATURAMENTO = "medicaoHistorico.mdhi_amleitura";
    
    public final static String ANORMALIDADE_INFORMADA = "medicaoHistorico.ltan_idleitanorminformada";
    
    public final static String ANORMALIDADE_FATURADA = "medicaoHistorico.ltan_idleitanormfatmt";
    
    public final static String LOCALIDADE_IMOVEL = "imovel.loca_id"; 
    
    public final static String SETOR_COMERCIAL_IMOVEL = "imovel.stcm_id";
    
    public final static String QUADRA_IMOVEL = "imovel.qdra_id";
    
    public final static String ROTA_QUADRA = "rota.rota_cdrota";
    
    public final static String GRUPO_FATURAMENTO = "faturamentoGrupo.ftgr_id";
    
    public final static String IMOVEL_EMPRESA = "empresa.empr_id";
    
    public final static String INDICADOR_IMOVEL_CONDOMINIO = "imovel.imov_icimovelcondominio";
    
    public final static String PERFIL_IMOVEL = "imovel.iper_id";
    
    public final static String IMOVEL_CONDOMINIO_ID = "imovel.imov_idimovelcondominio";
    
    public final static String LIGACAO_AGUA_ID = "medicaoHistorico.lagu_id";
    
    //Filtrar analise excecoes leituras e consumo -- Constantes usadas    
    public final static String MEDICAO_EMPRESA = "empresa.empr_id";
    
    public final static String CONSUMO_HISTORICO_LIGACAO_TIPO = "consumoHistorico.lgti_id";
    
    public final static String CONSUMO_HISTORICO_ANORMALIDADE_CONSUMO = "consumoHistorico.csan_id";
    
    public final static String CONSUMO_HISTORICO_FATURADO_MES = "consumoHistorico.cshi_nnconsumofaturadomes";
    
    public final static String CONSUMO_HISTORICO_CONSUMO_MEDIDO = "consumoHistorico.cshi_nnconsumofaturadomes";
    
    public final static String MH_IMOVEL_QTD_ECONOMIAS = "imovel.imov_qteconomia";
    
    public final static String IMOVEL_CATEGORIA = "categoria.catg_id";
    
    public final static String MH_LIGACAO_AGUA_ID = "medicaoHistorico.lagu_id";
    
    public final static String MH_ANORMALIDADE_INFORMADA = "medicaoHistorico.ltan_idleitanorminformada";
    
    public final static String MH_ANORMALIDADE_FATURADA = "medicaoHistorico.ltan_idleitanormfatmt";
    
    public final static String MH_MEDICAO_TIPO_ID = "medicaoHistorico.medt_id";
    
    public final static String CONSUMO_HISTORICO_CONSUMO_MEDIO = "consumoHistorico.cshi_nnconsumomedio";        

    public final static String SETOR_COMERCIAL_IMOVEL_CODIGO = "setorComercial.stcm_cdsetorcomercial";

    public final static String QUADRA_IMOVEL_NUMERO = "imovel.quadra.numeroQuadra";
    
    public final static String LIGACAO_AGUA_SITUACAO_IMOVEL = "imovel.last_id";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_IMOVEL = "imovel.lest_id";
    
    public final static String INDICADOR_DEBITO_AUTOMATICO = "imovel.imov_icdebitoconta";
    
    public final static String MH_CONSUMO_MEDIDO_MES = "medicaoHistorico.mdhi_nnconsumomedidomes";
    
    public final static String MH_LEITURA_SITUACAO_ATUAL = "medicaoHistorico.ltst_idleiturasituacaoatual";
    
    public final static String MH_INDICADOR_ANALISADO = "medicaoHistorico.mdhi_icanalisado";
    
    public final static String MH_USUARIO_ALTERACAO = "medicaoHistorico.usur_idalteracao";
    
}

