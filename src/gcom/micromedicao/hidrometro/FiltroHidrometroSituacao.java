package gcom.micromedicao.hidrometro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroHidrometroSituacao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	public FiltroHidrometroSituacao() {
	}

	public FiltroHidrometroSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String DESCRICAO = "descricao";
	
	public final static String EXTRAVIADO = "extraviado";

}
