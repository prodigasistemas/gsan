package gcom.micromedicao.medicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroMedicaoHistorico extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMedicaoHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroPavimentoCalcada
     */
    public FiltroMedicaoHistorico() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String IMOVEL_INDICADOR_EXCLUSAO = "imovel.indicadorExclusao";

    public final static String MEDICAO_TIPO_ID = "medicaoTipo.id";

    public final static String ANO_MES_REFERENCIA_FATURAMENTO = "anoMesReferencia";
    
    public final static String ANORMALIDADE_INFORMADA = "leituraAnormalidadeInformada.id";
    
    public final static String ANORMALIDADE_FATURADA = "leituraAnormalidadeFaturamento.id";
    
    public final static String LOCALIDADE_IMOVEL = "imovel.localidade.id"; 
    
    public final static String SETOR_COMERCIAL_IMOVEL = "imovel.setorComercial.id";
    
    public final static String QUADRA_IMOVEL = "imovel.quadra.id";
    
    public final static String GRUPO_FATURAMENTO = "imovel.quadra.rota.faturamentoGrupo.id";
    
    public final static String IMOVEL_EMPRESA = "imovel.quadra.rota.empresa.id";
    
    public final static String INDICADOR_IMOVEL_CONDOMINIO = "imovel.indicadorImovelCondominio";
    
    public final static String PERFIL_IMOVEL = "imovel.imovelPerfil.id";
    
    public final static String IMOVEL_CONDOMINIO_ID = "imovel.imovelCondominio.id";
    
    public final static String LIGACAO_AGUA_ID = "ligacaoAgua.id";
    
    //Filtrar analise excecoes leituras e consumo -- Constantes usadas    
    public final static String MEDICAO_EMPRESA = "imovel.quadra.rota.empresa.id";
    
    public final static String CONSUMO_HISTORICO_LIGACAO_TIPO = "ch.ligacaoTipo.id";
    
    public final static String CONSUMO_HISTORICO_ANORMALIDADE_CONSUMO = "ch.consumoAnormalidade.id";
    
    public final static String CONSUMO_HISTORICO_FATURADO_MES = "ch.numeroConsumoFaturadoMes";
    
    public final static String CONSUMO_HISTORICO_CONSUMO_MEDIDO = "ch.numeroConsumoMes";
    
    public final static String MH_IMOVEL_QTD_ECONOMIAS = "imovel.quantidadeEconomias";
    
    public final static String IMOVEL_CATEGORIA = "cat.id";
    
    public final static String MH_LIGACAO_AGUA_ID = "lagu.id";
    
    public final static String MH_ANORMALIDADE_INFORMADA = "mh.leituraAnormalidadeInformada.id";
    
    public final static String MH_ANORMALIDADE_FATURADA = "mh.leituraAnormalidadeFaturamento.id";
    
    public final static String MH_MEDICAO_TIPO_ID = "mh.medicaoTipo.id";
    
    public final static String CONSUMO_HISTORICO_CONSUMO_MEDIO = "ch.consumoMedioHidrometro";        

    public final static String SETOR_COMERCIAL_IMOVEL_CODIGO = "imovel.setorComercial.codigo";

    public final static String QUADRA_IMOVEL_NUMERO = "imovel.quadra.numeroQuadra";
    
    public final static String LIGACAO_AGUA_SITUACAO_IMOVEL = "imovel.ligacaoAguaSituacao.id";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_IMOVEL = "imovel.ligacaoEsgotoSituacao.id";
    
    public final static String LEITURA_SITUACAO_ATUAL = "leituraSituacaoAtual";
    
    public final static String MOTIVO_INTERFERENCIA_TIPO = "motivoInterferenciaTipo";
    
    public final static String INFORMADA_ANORMALIDADE = "leituraAnormalidadeInformada";
    
    public final static String FATURADA_ANORMALIDADE = "leituraAnormalidadeFaturamento";
    
    public final static String USUARIO = "usuarioAlteracao";
    
    public final static String IMOVEL = "imovel";
    
    
       
}

