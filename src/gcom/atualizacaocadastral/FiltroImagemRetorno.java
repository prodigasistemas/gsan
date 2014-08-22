package gcom.atualizacaocadastral;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroImagemRetorno extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FiltroImagemRetorno() {
	}
	
	public FiltroImagemRetorno(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	public final static String IMOVEL_ID = "idImovel";
}