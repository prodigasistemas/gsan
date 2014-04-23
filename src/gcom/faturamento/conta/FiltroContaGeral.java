package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContaGeral extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroContaGeral() {
    }
    
    public FiltroContaGeral(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String INDICADOR_HISTORICO = "indicadorHistorico";
    
    public final static String CONTA_IMPRESSAO = "contaImpressao";
}

