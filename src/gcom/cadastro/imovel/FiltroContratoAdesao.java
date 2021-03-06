package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroContratoAdesao extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroContratoAdesao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroContratoAdesao() {
	}

	public final static String CONTRATO = "contrato";
	public final static String CONTRATO_ID = "contrato.id";
	public final static String CONTRATO_TIPO = "contrato.contratoTipo";
	public final static String CLIENTE = "clienteImovel.cliente";
	public final static String IMOVEL = "clienteImovel.imovel";
}
