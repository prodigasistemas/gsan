package gcom.micromedicao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroReleituraMobile extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ID_IMOVEL = "imovel.id";	
	public static final String ANO_MES_FATURAMENTO = "anoMesReferencia";	
	public static final String INDICADOR_RELEITURA = "indicadorReleitura";
	
	public static final String ID_GRUPO_FATURAMENTO = "imovel.quadra.rota.faturamentoGrupo.id";
	public static final String ID_ROTA = "imovel.quadra.rota.id";
	public static final String ID_QUADRA = "imovel.quadra.id";
}
