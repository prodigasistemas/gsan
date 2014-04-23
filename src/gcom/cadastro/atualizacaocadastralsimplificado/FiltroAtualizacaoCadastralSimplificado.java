package gcom.cadastro.atualizacaocadastralsimplificado;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAtualizacaoCadastralSimplificado extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroAtualizacaoCadastralSimplificado(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public FiltroAtualizacaoCadastralSimplificado() {
	}
	
	public final static String ID = "id";
	
	public final static String NOME = "nome";
	
}
