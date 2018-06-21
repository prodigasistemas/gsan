package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroContrato extends Filtro {
	private static final long serialVersionUID = 1L;

	public FiltroContrato(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroContrato() {
	}

	public final static String ID = "id";
	public final static String MUMEROCONTRATO = "numeroContrato";
	public final static String IMOVEL = "imovel.id";
	public final static String DATACONTRATOINICIO = "dataContratoInicio";
	public final static String DATACONTRATOFIM = "dataContratoFim";
	public final static String DATACONTRATOENCERRAMENTO = "dataContratoEncerrado";
	public final static String CONTRATO_TIPO = "contratoTipo.id";
}
