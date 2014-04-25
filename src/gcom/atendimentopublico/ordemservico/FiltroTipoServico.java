package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma municipio
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroTipoServico extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroBairro object
     */
    public FiltroTipoServico() {
    }

    /**
     * Constructor for the Filtro object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroTipoServico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do bairro
     */
    public final static String ID = "id";

    /**
     * Indicador uso
     */
    public final static String DESCRICAO = "descricao";
    
    /**
     * Indicador uso
     */
    public final static String ABREVIADA = "descricaoAbreviada";
    
    /**
     * Indicador uso
     */
    public final static String VALOR = "valor";
    
    /**
     * Indicador uso
     */
    public final static String SUBGRUPO = "servicoTipoSubgrupo.id";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_PAVIMENTO = "indicadorPavimento";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_ATUALIZACAO_COMERCIAL = "indicadorAtualizaComercial";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_TERCEIRIZADO = "indicadorTerceirizado";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_FISCALIZACAO_INFRACAO = "indicadorFiscalizacaoInfracao";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_VISTORIA = "indicadorVistoria";
    
    /**
     * Indicador uso
     */
    public final static String CODIGO_TIPO_SERVICO = "codigoServicoTipo";
    
    /**
     * Indicador uso
     */
    public final static String TEMPO_MEDIO_ESECUCAO = "tempoMedioExecucao";
    
    /**
     * Indicador uso
     */
    public final static String DEBITO_TIPO_ID = "debitoTipo.id";
    
    /**
     * Indicador uso
     */
    public final static String CREDITO_TIPO_ID = "creditoTipo.id";
    
    /**
     * Indicador uso
     */
    public final static String PRIORIDADE_SERVICO = "servicoTipoPrioridade.id";
    
    /**
     * Indicador uso
     */
    public final static String PERFIL_TIPO = "servicoPerfilTipo.id";
    
    /**
     * Indicador uso
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Indicador pavimento de rua
     */
    public final static String INDICADOR_PAVIMENTO_RUA = "indicadorPavimentoRua";

    /**
     * Indicador pavimento de calçada
     */
    public final static String INDICADOR_PAVIMENTO_CALCADA = "indicadorPavimentoCalcada";
    
    /**
     * Indicador pavimento de calçada
     */
    public final static String INDICADOR_CONSIDERA_ECONOMIAS = "indicativoTipoSevicoEconomias";
    
    /**
     * Indicador de Empresa de cobrança
     */
    public final static String INDICADOR_EMPRESA_COBRANCA = "indicadorEmpresaCobranca";
    
    /**
     * Indicaodr de Inspeção de anormalidade
     */
    public final static String INDICADOR_INSPECAO_ANORMALIDADE = "indicadorInspecaoAnormalidade";
    
    /**
     * Indicador de Programação automática
     */
    public final static String INDICADOR_PROGRAMACAO_AUTOMATICA = "indicadorProgramacaoAutomatica";
    
    public static final String ID_OS_PROGRAMA_CALIBRAGEM = "programaCalibragem.id";
    
    public static final String OS_PROGRAMA_CALIBRAGEM = "programaCalibragem";
    
    /**
     * Indicador de encerramento automático do RA no encerramento de sua última/única OS pendente
     */
    public final static String INDICADOR_ENCERRAMENTO_AUTOMATICO_RA_QUANDO_ENCERRAR_ULTIMA_OS = "indicadorEncAutomaticoRAQndEncOS";
    
}
