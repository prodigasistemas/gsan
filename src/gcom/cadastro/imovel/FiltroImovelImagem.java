package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroImovelImagem extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public FiltroImovelImagem() {
	}
	
	public FiltroImovelImagem(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	public final static String IMOVEL_ID = "idImovel";
}
