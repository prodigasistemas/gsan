package gcom.arrecadacao.pagamento;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroPagamentoSituacao extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    public final static String INDICADOR_USO ="indicadorUso";
   
    public FiltroPagamentoSituacao() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroPagamentoSituacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
