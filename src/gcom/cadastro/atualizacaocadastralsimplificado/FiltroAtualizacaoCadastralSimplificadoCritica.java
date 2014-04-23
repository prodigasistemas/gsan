package gcom.cadastro.atualizacaocadastralsimplificado;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAtualizacaoCadastralSimplificadoCritica extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroAtualizacaoCadastralSimplificadoCritica(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public FiltroAtualizacaoCadastralSimplificadoCritica() {
	}
	
	public final static String ID = "id";
	
}
