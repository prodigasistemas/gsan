package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroContratoInstalacaoReservacao extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroContratoInstalacaoReservacao(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroContratoInstalacaoReservacao() {
	}

	public final static String CONTRATO = "contrato";
	public final static String CONTRATO_ID = "contrato.id";
	public final static String CONTRATO_TIPO = "contrato.contratoTipo";
	public final static String CLIENTE = "clienteImovel.cliente";
	public final static String IMOVEL = "clienteImovel.imovel";
}