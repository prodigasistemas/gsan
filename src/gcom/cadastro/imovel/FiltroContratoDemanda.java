package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroContratoDemanda extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroContratoDemanda(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroContratoDemanda() {
	}

	public final static String ID = "id";
	public final static String ID_CONTRATO = "contrato.id";
	public final static String ID_USUARIO = "usuario.id";
	public final static String CONTRATO = "contrato";
	public final static String USUARIO = "usuario";
	public final static String IMOVEL = "contrato.imovel.id";
	public final static String DATACONTRATOINICIO = "contrato.dataContratoInicio";
	public final static String DATACONTRATOFIM = "contrato.dataContratoFim";
	public final static String DATACONTRATOENCERRAMENTO = "contrato.dataContratoEncerrado";
	public final static String CONTRATO_TIPO = "contrato.contratoTipo.id";

}
