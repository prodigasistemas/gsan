package gcom.faturamento.autoinfracao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroAutoInfracaoSituacao extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroAutoInfracaoSituacao() {}

	public FiltroAutoInfracaoSituacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String INDICADOR_USO = "indicadorUso";
	
	public final static String DESCRICAO = "descricao";
}
