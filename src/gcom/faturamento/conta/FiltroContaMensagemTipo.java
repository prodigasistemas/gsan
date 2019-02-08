package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

public class FiltroContaMensagemTipo extends Filtro {
	private static final long serialVersionUID = 1L;
    
    public FiltroContaMensagemTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public FiltroContaMensagemTipo() {
    }

    public final static String ID = "id";
    public final static String DESCRICAO = "descricao";
    public final static String INDICADOR_USO = "indicadorUso";

}
