package gcom.faturamento.autoinfracao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAutosInfracaoDebitoACobrar extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroAutosInfracaoDebitoACobrar() {}

	public FiltroAutosInfracaoDebitoACobrar(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID_DEBITO_A_COBRAR = "debitoACobrarGeral.id";
	public final static String AUTOS_INFRACAO_ID = "autosInfracao.id";
}
