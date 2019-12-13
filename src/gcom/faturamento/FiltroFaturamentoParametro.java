package gcom.faturamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroFaturamentoParametro extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

    public FiltroFaturamentoParametro() {
    }

    public final static String ID = "id";
    public final static String NOME = "nome";
}
