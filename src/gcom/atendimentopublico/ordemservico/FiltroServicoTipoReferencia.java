package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

/**
 * Filtrar Serviço Tipo Referencia
 * 
 * @author lms
 * @since 27/07/2006
 */
public class FiltroServicoTipoReferencia extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroServicoTipoReferencia() {
	}
	
	public FiltroServicoTipoReferencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String DESCRICAO = "descricao";
	public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
	public final static String INDICADOR_USO = "indicadorUso";
	public final static String INDICADOR_EXISTENCIA_OS_REFERENCIA = "indicadorExistenciaOsReferencia";

}
