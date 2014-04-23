package gcom.cadastro.empresa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 19 de Maio de 2005
 */
public class FiltroEmpresa extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEmpresa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroEmpresa() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";

    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_EMPRESA_PRINCIPAL = "indicadorEmpresaPrincipal";
    
    /**
     * Description of the Field
     */
    public final static String EMAIL = "email";
    
    public final static String INDICADOR_LEITURA = "indicadorLeitura";
    
    public final static String INDICADOR_EMPRESA_CONTRATADA_COBRANCA = "indicadorEmpresaContratadaCobranca";
}
