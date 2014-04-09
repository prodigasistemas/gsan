package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Serviço Tipo Subgrupo
 * 
 * @author lms
 * @since 01/08/2006
 */
public class FiltroServicoTipoSubgrupo extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroServicoTipoSubgrupo() {
	}
	
	public FiltroServicoTipoSubgrupo(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String DESCRICAO = "descricao";
	public final static String INDICADOR_USO = "indicadorUso";

}
