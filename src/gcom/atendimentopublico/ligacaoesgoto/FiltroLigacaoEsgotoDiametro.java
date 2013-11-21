package gcom.atendimentopublico.ligacaoesgoto;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * [UC0488] Informar Retorno Ordem de Fiscalização
 * 
 * Filtro de ligação de estogo diâmetro
 * 
 * @author Diogo Peixoto
 * @since 09/08/2011
 *
 */

public class FiltroLigacaoEsgotoDiametro extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ID = "id";
	public static final String DESCRICAO = "descricao";
	public static final String INDICADOR_USO = "indicadorUso";

	public FiltroLigacaoEsgotoDiametro(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroLigacaoEsgotoDiametro() {

	}
}
