package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

/*
 *  O filtro serve para armazenar os critérios de busca de um cliente
 *
 *  @author     Sávio Luiz
 *  @created    22 de Abril de 2005
 */
/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroClienteTipo extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroTipoCliente object
     */
    public FiltroClienteTipo() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroClienteTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";

    /**
     * Código da unidade de medição
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Description of the Field
     */
    public final static String ESFERA_PODER = "esferaPoder";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_PESSOA_FISICA_JURIDICA = "indicadorPessoaFisicaJuridica";
    
    

}
