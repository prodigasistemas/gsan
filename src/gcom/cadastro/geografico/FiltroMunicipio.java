package gcom.cadastro.geografico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de uma municipio
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public class FiltroMunicipio extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroMunicipio object
     */
    public FiltroMunicipio() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMunicipio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código do municipio
     */
    public final static String ID = "id";

    /**
     * Nome do municipio
     */
    public final static String NOME = "nome";

    /**
     * Código da Regiao de Desenvolvimento
     */
    public final static String REGIAO_DESENVOLVOMENTO_ID = "regiaoDesenvolvimento.id";

    /**
     * Description of the Field
     */
    public final static String REGIAO_ID = "microrregiao.regiao.id";
    
    /**
     * Description of the Field
     */
    public final static String REGIAO = "microrregiao.regiao";

    /**
     * Código da Microrregiao
     */
    public final static String MICRORREGICAO_ID = "microrregiao.id";
    
    /**
     * Código da Microrregiao
     */
    public final static String MICRORREGICAO = "microrregiao";

    /**
     * Código da Unidade Federação
     */
    public final static String UNIDADE_FEDERACAO_ID = "unidadeFederacao.id";
    
    /**
     * Unidade Federação
     */
    public final static String UNIDADE_FEDERACAO = "unidadeFederacao";

    /**
     * CEP Inicial
     */
    public final static String CEPINICIO = "cepInicio";

    /**
     * CEP Final
     */
    public final static String CEPFIM = "cepFim";

    /**
     * Indicador de Uso
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Código do DDD
     */
    public final static String DDD = "ddd";
}
