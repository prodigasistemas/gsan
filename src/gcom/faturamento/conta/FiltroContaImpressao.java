package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContaImpressao extends Filtro {

	private static final long serialVersionUID = 1L;
    public FiltroContaImpressao() {
    }

    public final static String ID = "id";
    
    public final static String CONTA_TIPO = "contaTipo";
    
    public final static String CONTA_GERAL = "contaGeral";
    
    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaImpressao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
