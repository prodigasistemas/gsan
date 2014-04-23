package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * FiltroUnidadeOrganizacional 
 *
 * @author Sávio Luiz
 * @date 27/07/2006
 */
public class FiltroServicoTipo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroUnidadeOrganizacional object
     */
    public FiltroServicoTipo() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroServicoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id da Unidade
     */
    public final static String ID = "id";
    
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    public final static String DESCRICAO = "descricao";
    public final static String DSABREVIADA = "descricaoAbreviada";
    public final static String VALORSERVICO = "valor";
    public final static String INDICADORPAVIMENTO = "indicadorPavimento";
    public final static String INDICADORATUALIZARCOMERCIAL = "indicadorAtualizaComercial";
    public final static String INDICADORTERCEIRIZADO = "indicadorTerceirizado";
    public final static String CODIGOSERVICOTIPO = "codigoServicoTipo";
    public final static String TEMPOMEDIOEXECUCAO = "tempoMedioExecucao";
    public final static String ULTIMAALTERACAO = "ultimaAlteracao";
    public final static String CREDITOTIPO = "creditoTipo.id";
    public final static String SERVICO_TIPO_REFERENCIA = "servicoTipoReferencia"; 
    public final static String SERVICOPERFILTIPO = "servicoPerfilTipo.id"; 
    public final static String SERVICOTIPOSUBGRUPO = "servicoTipoSubgrupo.id"; 
    public final static String SERVICOTIPOPRIORIDADE = "servicoTipoPrioridade.id"; 
    public final static String DEBITOTIPO = "debitoTipo.id"; 
    public final static String ATIVIDADETIPOSERVICO = "debitoTipo.id"; 
    public final static String INDICADOR_USO = "indicadorUso";
    public final static String INDICADOR_DIAGNOSTICO_SERVICO_TIPO_REF = "servicoTipoReferencia.indicadorDiagnostico";
    public final static String INDICADOR_FISCALIZACAO_SERVICO_TIPO_REF = "servicoTipoReferencia.indicadorFiscalizacao";
    public final static String CONSTANTE_FUNCIONALIDADE_TIPO_SERVICO = "constanteFuncionalidadeTipoServico";
    public final static String INDICADOR_EMPRESA_COBRANCA = "indicadorEmpresaCobranca";
    public final static String INDICADOR_SERVICO_ORDEM_SELETIVA = "indicadorServicoOrdemSeletiva";
    public static final String ID_OS_PROGRAMA_CALIBRAGEM = "programaCalibragem.id";
    public static final String INDICADOR_PERMITE_ALTERACAO_IMOVEL_EM_CAMPO = "indicadorPermiteAlteracaoImovelEmCampo";
    
    public static final String OS_PROGRAMA_CALIBRAGEM = "programaCalibragem";
    
    public static final String INDICADOR_GERAR_OS_INSP_ANORMALIDADE = "indicadorInspecaoAnormalidade";
    public static final String INDICADOR_PROGRAMACAO_AUTOMATICA = "indicadorProgramacaoAutomatica";
    
}
