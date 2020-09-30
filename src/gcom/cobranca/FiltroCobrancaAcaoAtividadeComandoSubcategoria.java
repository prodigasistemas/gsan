package gcom.cobranca;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroCobrancaAcaoAtividadeComandoSubcategoria extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroCobrancaAcaoAtividadeComandoSubcategoria() {
	}

	public FiltroCobrancaAcaoAtividadeComandoSubcategoria(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String COBRANCA_ACAO_ATIVIDADE_COMANDO_ID = "cobrancaAcaoAtividadeComando.id";
	public final static String SUBCATEGORIA = "subcategoria";
	public final static String SUBCATEGORIA_ID = "subcategoria.id";
}
