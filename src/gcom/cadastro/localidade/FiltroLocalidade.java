package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 19 de Maio de 2005
 */
public class FiltroLocalidade extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroLocalidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroLocalidade() {
    }

    public final static String DESCRICAO = "descricao";

    public final static String ID_GERENCIA = "gerenciaRegional.id";
    
    public final static String GERENCIA = "gerenciaRegional";

    public final static String ID_UNIDADE_NEGOCIO = "unidadeNegocio.id";
    
    public final static String UNIDADE_NEGOCIO = "unidadeNegocio";

    public final static String UNIDADE_NEGOCIO_ID = "unidadeNegocio.id";
    
    public final static String ID_ELO = "localidade";

    public final static String INDICADOR_LOCALIDADE_SEDE = "indicadorLocalidadeSede";
    
    public final static String CENTRO_CUSTO = "codigoCentroCusto";
}
