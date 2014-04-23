package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContaBancaria extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOcupacaoTipo
     */
    public FiltroContaBancaria() {
    }
    
    public FiltroContaBancaria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String AGENCIA_ID = "agencia.id";
    
    public final static String AGENCIA_BANCO_ID = "agencia.banco.id";
    
    public final static String NUMERO_CONTA = "numeroConta";
}

