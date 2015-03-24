package gcom.seguranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroSegurancaParametro extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

    public FiltroSegurancaParametro() {
    }

    public final static String ID = "id";
    public final static String NOME = "nome";
}
