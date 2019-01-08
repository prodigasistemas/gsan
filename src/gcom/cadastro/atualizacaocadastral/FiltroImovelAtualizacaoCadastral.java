package gcom.cadastro.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroImovelAtualizacaoCadastral extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public FiltroImovelAtualizacaoCadastral() {
	}

	public FiltroImovelAtualizacaoCadastral(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "idImovel";
	public final static String ID_SITUACAO_ATUALIZACAO_CADASTRAL = "idSituacaoAtualizacaoCadastral";
	public final static String ID_ARQUIVO_TEXTO = "idArquivoTexto";
}
