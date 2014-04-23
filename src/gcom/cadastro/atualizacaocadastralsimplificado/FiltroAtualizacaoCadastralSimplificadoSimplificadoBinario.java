package gcom.cadastro.atualizacaocadastralsimplificado;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario() {
	}
	
	public final static String ID = "id";
	
	public final static String BINARIO = "binario";

	public final static String ARQUIVO_ID = "arquivo.id";
	
}
