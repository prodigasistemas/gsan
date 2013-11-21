package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

public class FiltroContaMotivoInclusao extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String ID = "id";


    /**
     * Construtor da classe FiltroContaMotivoRetificacao
     */
    public FiltroContaMotivoInclusao() {
    }

    /**
     * Construtor da classe FiltroContaMotivoRetificacao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaMotivoInclusao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
