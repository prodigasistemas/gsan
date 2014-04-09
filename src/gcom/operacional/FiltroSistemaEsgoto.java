package gcom.operacional;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroSistemaEsgoto extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSistemaEsgoto(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroSistemaEsgoto() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    
    /**
     * Descrição do Sistema de Esgoto
     */
    public final static String DESCRICAO = "descricao";
    
    /**
     * Código da Divisão de Esgoto
     */
    public final static String DIVISAOESGOTO_ID = "divisaoEsgoto.id";
    
    /**
     * Código do Tipo de Tratamento
     */
    public final static String TIPOTRATAMENTO_ID = "sistemaEsgotoTratamentoTipo.id";

    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";
}
