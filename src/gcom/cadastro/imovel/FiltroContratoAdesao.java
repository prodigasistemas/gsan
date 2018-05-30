package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroContratoAdesao extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroContratoAdesao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroContratoAdesao() {
	}

	public final static String ID = "id";
	public final static String ID_CONTRATO = "contrato.id";
	public final static String ID_CLIENTE = "cliente.id";
	public final static String CONTRATO = "contrato";
	public final static String CLIENTE_IMOVEL = "clienteImovel";
	public final static String CLIENTE = "clienteImovel.cliente";
	public final static String IMOVEL = "contrato.imovel.id";
	public final static String DATACONTRATOINICIO = "contrato.dataContratoInicio";
	public final static String DATACONTRATOFIM = "contrato.dataContratoFim";
	public final static String DATACONTRATOENCERRAMENTO = "contrato.dataContratoEncerrado";
	public final static String CONTRATO_TIPO = "contrato.contratoTipo.id";

}
