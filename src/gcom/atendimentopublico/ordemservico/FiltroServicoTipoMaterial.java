package gcom.atendimentopublico.ordemservico;


import gcom.util.filtro.Filtro;

/**
 * Filtrar Serviço Tipo Referencia
 * 
 * @author Leandro Cavalcanti
 * @since 04/08/2006
 */
public class FiltroServicoTipoMaterial extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroServicoTipoMaterial() {
	}
	
	public FiltroServicoTipoMaterial(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID_MATERIAL = "material.id";
	public final static String ID_SERVICO_TIPO = "servicoTipo.id";
	public final static String INDICADOR_EXISTENCIA_OS_REFERENCIA = "indicadorExistenciaOsReferencia";

}
