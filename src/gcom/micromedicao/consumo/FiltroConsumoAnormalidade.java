package gcom.micromedicao.consumo;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConsumoAnormalidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsumoAnormalidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLigacaoAguaSituacao
     */
    public FiltroConsumoAnormalidade() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    public final static String MENSAGEM_CONTA = "mensagemConta";

}
