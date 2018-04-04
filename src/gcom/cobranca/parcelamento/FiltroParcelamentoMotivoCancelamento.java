package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroParcelamentoMotivoCancelamento extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroParcelamentoMotivoCancelamento() {
		this.campoOrderBy = DESCRICAO;
	}

	public FiltroParcelamentoMotivoCancelamento(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String PARCELAMENTO = "parcelamento.id";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String DESCRICAO = "descricao";
}
