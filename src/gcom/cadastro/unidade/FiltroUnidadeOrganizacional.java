package gcom.cadastro.unidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * FiltroUnidadeOrganizacional 
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class FiltroUnidadeOrganizacional extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroUnidadeOrganizacional object
     */
    public FiltroUnidadeOrganizacional() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUnidadeOrganizacional(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do Unidade
     */
    public final static String ID = "id";

    /**
     * Descrição da Unidade
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Descrição da IndicadorUso
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String ID_UNIDADE_CENTRALIZADORA = "unidadeCentralizadora.id";
    
    public final static String ID_UNIDADE_REPAVIMENTADORA = "unidadeRepavimentadora.id";

    public final static String ID_UNIDADE_TIPO = "unidadeTipo.id";

    public final static String UNIDADE_TIPO_NIVEL = "unidadeTipo.nivel";

    public final static String UNIDADE_TIPO_CODIGO = "unidadeTipo.codigoTipo";

    public final static String ID_LOCALIDADE = "localidade.id";

    public final static String GERENCIAL_REGIONAL = "gerenciaRegional.id";
    
    public final static String UNIDADE_NEGOCIO = "unidadeNegocio.id"; 

    public final static String SIGLA = "sigla";

    public final static String EMPRESA = "empresa.id";

    public final static String ID_UNIDADE_SUPERIOR = "unidadeSuperior.id";

    public final static String MEIO_SOLICITACAO = "meioSolicitacao.id";

    public final static String INDICADOR_ESGOTO = "indicadorEsgoto";

    public final static String INDICADOR_ABERTURA_RA = "indicadorAberturaRa";

    public final static String INDICADOR_TRAMITE = "indicadorTramite";
    
    public final static String INDICADOR_CENTRAL_ATENDIMENTO = "indicadorCentralAtendimento";
    
    public final static String UNIDADE_TIPO = "unidadeTipo";
    
    public final static String UNIDADE_SUPERIOR = "unidadeSuperior";
    
    public final static String CODIGO_CONSTANTE = "codigoConstante";
    
}
