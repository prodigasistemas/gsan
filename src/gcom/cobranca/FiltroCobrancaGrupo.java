package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre
 * @created 08 de Fevereiro de 2006
 */

public class FiltroCobrancaGrupo extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaGrupo object
     */
    public FiltroCobrancaGrupo() {
    }

    /**
     * Constructor for the FiltroCobrancaGrupo object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaGrupo(String campoOrderBy) {
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
    
    public final static String INDICADOR_USO = "indicadorUso";

    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    public final static String EMPRESA_ID = "contratoEmpresaServico.empresa.id";
    
    public final static String CONTRATO_EMPRESA_SERVICO = "contratoEmpresaServico";  

}
